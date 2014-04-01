package controlador.maestros;

import java.io.IOException;
import java.util.List;

import modelo.maestros.CategoriaMedicina;
import modelo.maestros.Medicina;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;

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
		Botonera botonera = new Botonera() {
			
			@Override
			public void salir() {
				cerrarVentana(divCategoriaMedicina, "Categoria Medicina");
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
											Messagebox
													.show("No se Puede Eliminar el Registro, Esta siendo Utilizado",
															"Informacion",
															Messagebox.OK,
															Messagebox.INFORMATION);
										} else {
											servicioCategoriaMedicina.eliminar(categoriaMedicina);
											limpiar();
											Messagebox
													.show("Registro Eliminado Exitosamente",
															"Informacion",
															Messagebox.OK,
															Messagebox.INFORMATION);
										}

									}
								}
							});
				} else {
					Messagebox.show("No ha Seleccionado Ningun Registro",
							"Alerta", Messagebox.OK, Messagebox.EXCLAMATION);
				}
			}

			@Override
			public void guardar() {
				if (validar()) {
					CategoriaMedicina categoriaMedicina = new CategoriaMedicina(id, fechaHora, horaAuditoria,
							txtNombreCategoriaMedicina.getValue(), nombreUsuarioSesion());
					servicioCategoriaMedicina.guardar(categoriaMedicina);
					limpiar();
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
				}
			}
		};
		botoneraCategoriaMedicina.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtNombreCategoriaMedicina.getText().compareTo("") == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else
			return true;
	}

	/* Muestra el catalogo de las categorias */
	@Listen("onClick = #btnBuscarCategoriaMedicina")
	public void mostrarCatalogo() {
		final List<CategoriaMedicina> categoriasMedicinas = servicioCategoriaMedicina.buscarTodas();
		catalogo = new Catalogo<CategoriaMedicina>(catalogoCategoriaMedicina, "Catalogo de Categorias de Medicina",
				categoriasMedicinas, "Nombre") {

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
