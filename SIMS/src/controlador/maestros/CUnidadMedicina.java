package controlador.maestros;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import modelo.maestros.MedicinaPresentacionUnidad;
import modelo.maestros.UnidadMedicina;

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

public class CUnidadMedicina extends CGenerico{

	private static final long serialVersionUID = 7074258423494221163L;
	@Wire
	private Div botoneraUnidadMedicina;
	@Wire
	private Textbox txtNombreUnidadMedicina;
	@Wire
	private Button btnBuscarUnidadMedicina;
	@Wire
	private Div catalogoUnidadMedicina;
	@Wire
	private Div divUnidadMedicina;
	private long id = 0;
	Catalogo<UnidadMedicina> catalogo;
	@Override
	public void inicializar() throws IOException {
		HashMap<String, Object> mapa = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (mapa != null) {
			if (mapa.get("tabsGenerales") != null) {
				tabs = (List<Tab>) mapa.get("tabsGenerales");
				mapa.clear();
				mapa = null;
			}
		}
		Botonera botonera = new Botonera() {
			
			@Override
			public void salir() {
				cerrarVentana(divUnidadMedicina, "Unidad Medicina", tabs);
			}
			
			@Override
			public void limpiar() {
				txtNombreUnidadMedicina.setValue("");
				id = 0;
			}
			
			@Override
			public void guardar() {
				if (validar()) {
					UnidadMedicina unidadMedicina = new UnidadMedicina(id, fechaHora, horaAuditoria,
							txtNombreUnidadMedicina.getValue(), nombreUsuarioSesion());
					servicioUnidadMedicina.guardar(unidadMedicina);
					limpiar();
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
				}
			}
			
			@Override
			public void eliminar() {
				if (id != 0 && txtNombreUnidadMedicina.getText().compareTo("") != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar la Unidad de Medicina?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										UnidadMedicina unidadMedicina = servicioUnidadMedicina
												.buscar(id);
										List<MedicinaPresentacionUnidad> medicinas = servicioMedicinaPresentacionUnidad.buscarPorUnidad(unidadMedicina);
										if (!medicinas.isEmpty()) {
											Messagebox
													.show("No se Puede Eliminar el Registro, Esta siendo Utilizado",
															"Informacion",
															Messagebox.OK,
															Messagebox.INFORMATION);
										} else {
											servicioUnidadMedicina.eliminar(unidadMedicina);
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
		};
		botoneraUnidadMedicina.appendChild(botonera);
	}
	
	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtNombreUnidadMedicina.getText().compareTo("") == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else
			return true;
	}

	/* Muestra el catalogo de las categorias */
	@Listen("onClick = #btnBuscarUnidadMedicina")
	public void mostrarCatalogo() {
		final List<UnidadMedicina> unidadesMedicinas = servicioUnidadMedicina.buscarTodas();
		catalogo = new Catalogo<UnidadMedicina>(catalogoUnidadMedicina, "Catalogo de Unidades de Medicina",
				unidadesMedicinas, "Nombre") {

			@Override
			protected List<UnidadMedicina> buscar(String valor,String combo) {
				if(combo.equals("Nombre"))
					return servicioUnidadMedicina.filtroNombre(valor);
					else
						return unidadesMedicinas;
			}

			@Override
			protected String[] crearRegistros(UnidadMedicina objeto) {
				String[] registros = new String[1];
				registros[0] = objeto.getNombre();
				return registros;
			}

		};
		catalogo.setParent(catalogoUnidadMedicina);
		catalogo.doModal();
	}
	
	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoUnidadMedicina")
	public void seleccinar() {
		UnidadMedicina unidadMedicina = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(unidadMedicina);
		catalogo.setParent(null);
	}

	/* Busca si existe una unidad con el mismo nombre escrito */
	@Listen("onChange = #txtNombreUnidadMedicina")
	public void buscarPorNombre() {
		UnidadMedicina unidadMedicina = servicioUnidadMedicina.buscarPorNombre(txtNombreUnidadMedicina
				.getValue());
		if (unidadMedicina != null)
			llenarCampos(unidadMedicina);
	}

	/* LLena los campos del formulario dada una unidad */
	private void llenarCampos(UnidadMedicina unidad) {
		txtNombreUnidadMedicina.setValue(unidad.getNombre());
		id = unidad.getIdUnidad();
	}
}
