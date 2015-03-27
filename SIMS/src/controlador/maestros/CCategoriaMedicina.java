package controlador.maestros;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import modelo.maestros.CategoriaMedicina;
import modelo.maestros.Medicina;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;

public class CCategoriaMedicina extends CGenerico {

	
	private static final long serialVersionUID = -5627101879654208757L;
	@Wire
	private Div botoneraCategoriaMedicina;
	@Wire
	private Textbox txtNombreCategoriaMedicina;
	@Wire
	private Button btnBuscarCategoriaMedicina;
	@Wire
	private Div catalogoCategoriaMedicina;
	@Wire
	private Div divCategoriaMedicina;
	private long id = 0;
	Catalogo<CategoriaMedicina> catalogo;
	
	@Override
	public void inicializar() throws IOException {
		HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (map != null) {
			if (map.get("tabsGenerales") != null) {
				tabs = (List<Tab>) map.get("tabsGenerales");
				map.clear();
				map = null;
			}
		}
		Botonera botonera = new Botonera() {
			
			@Override
			public void salir() {
				cerrarVentana(divCategoriaMedicina, "Categoria Medicina", tabs);
			}
			
			@Override
			public void limpiar() {
				txtNombreCategoriaMedicina.setValue("");
				id = 0;
			}
			
			@Override
			public void eliminar() {
				if (id != 0 && txtNombreCategoriaMedicina.getText().compareTo("") != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar la Categoria de Medicina?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										CategoriaMedicina categoriaMedicina = servicioCategoriaMedicina
												.buscar(id);
										List<Medicina> medicinas = servicioMedicina.buscarPorCategoria(categoriaMedicina);
										if (!medicinas.isEmpty()) {
											msj.mensajeError(Mensaje.noEliminar);
										} else {
											servicioCategoriaMedicina.eliminar(categoriaMedicina);
											limpiar();
											msj.mensajeInformacion(Mensaje.eliminado);
										}

									}
								}
							});
				} else {
					msj.mensajeAlerta(Mensaje.noSeleccionoRegistro);
				}
			}

			@Override
			public void guardar() {
				if (validar()) {
					CategoriaMedicina categoriaMedicina = new CategoriaMedicina(id, fechaHora, horaAuditoria,
							txtNombreCategoriaMedicina.getValue(), nombreUsuarioSesion());
					servicioCategoriaMedicina.guardar(categoriaMedicina);
					limpiar();
					msj.mensajeInformacion(Mensaje.guardado);
				}
			}
		};
		botoneraCategoriaMedicina.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtNombreCategoriaMedicina.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	/* Muestra el catalogo de las categorias */
	@Listen("onClick = #btnBuscarCategoriaMedicina")
	public void mostrarCatalogo() {
		final List<CategoriaMedicina> categoriasMedicinas = servicioCategoriaMedicina.buscarTodas();
		catalogo = new Catalogo<CategoriaMedicina>(catalogoCategoriaMedicina, "Catalogo de Categorias de Medicina",
				categoriasMedicinas,false,"Nombre") {

			@Override
			protected List<CategoriaMedicina> buscar(String valor,String combo) {
				if(combo.equals("Nombre"))
					return servicioCategoriaMedicina.filtroNombre(valor);
					else
						return categoriasMedicinas;
			}

			@Override
			protected String[] crearRegistros(CategoriaMedicina objeto) {
				String[] registros = new String[1];
				registros[0] = objeto.getNombre();
				return registros;
			}

		};
		catalogo.setParent(catalogoCategoriaMedicina);
		catalogo.doModal();
	}
	
	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoCategoriaMedicina")
	public void seleccinar() {
		CategoriaMedicina categoriaMedicina = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(categoriaMedicina);
		catalogo.setParent(null);
	}

	/* Busca si existe una unidad con el mismo nombre escrito */
	@Listen("onChange = #txtNombreCategoriaMedicina")
	public void buscarPorNombre() {
		CategoriaMedicina categoriaMedicina = servicioCategoriaMedicina.buscarPorNombre(txtNombreCategoriaMedicina
				.getValue());
		if (categoriaMedicina != null)
			llenarCampos(categoriaMedicina);
	}

	/* LLena los campos del formulario dada una unidad */
	private void llenarCampos(CategoriaMedicina categoriaMedicina) {
		txtNombreCategoriaMedicina.setValue(categoriaMedicina.getNombre());
		id = categoriaMedicina.getIdCategoriaMedicina();
	}
}
