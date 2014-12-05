package controlador.sha;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import modelo.sha.Informe;
import modelo.sha.PlanAccion;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;
import controlador.maestros.CGenerico;

public class CPlanAccion extends CGenerico {

	private static final long serialVersionUID = 2270951477292100773L;
	@Wire
	private Div botoneraPlan;
	@Wire
	private Textbox txtDescripcion;
	@Wire
	private Textbox txtQuien;
	@Wire
	private Textbox txtDonde;
	@Wire
	private Textbox txtComo;
	@Wire
	private Textbox txtCuando;
	@Wire
	private Div catalogoPlan;
	@Wire
	private Div divPlan;
	private long id = 0;
	String nombre = "";
	Catalogo<PlanAccion> catalogo;

	@SuppressWarnings("unchecked")
	@Override
	public void inicializar() throws IOException {
		HashMap<String, Object> mapa = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (mapa != null) {
			if (mapa.get("tabsGenerales") != null) {
				tabs = (List<Tab>) mapa.get("tabsGenerales");
				nombre = (String) mapa.get("titulo");
				mapa.clear();
				mapa = null;
			}
		}
		Botonera botonera = new Botonera() {

			private static final long serialVersionUID = 1L;

			@Override
			public void salir() {
				cerrarVentana(divPlan, nombre, tabs);
			}

			@Override
			public void limpiar() {
				txtComo.setValue("");
				txtCuando.setValue("");
				txtDescripcion.setValue("");
				txtDonde.setValue("");
				txtQuien.setValue("");
				id = 0;
			}

			@Override
			public void guardar() {
				if (validar()) {
					PlanAccion plan = new PlanAccion(id,
							txtDescripcion.getValue(), txtQuien.getValue(),
							txtComo.getValue(), txtDonde.getValue(),
							txtCuando.getValue(), fechaHora, horaAuditoria,
							nombreUsuarioSesion());
					servicioPlanAccion.guardar(plan);
					msj.mensajeInformacion(Mensaje.guardado);
					limpiar();
				}
			}

			@Override
			public void eliminar() {
				if (id != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar el Plan de Accion?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										List<Informe> informes = servicioInforme
												.buscarPorIdPlan(id);
										if (!informes.isEmpty()) {
											msj.mensajeError(Mensaje.noEliminar);
										} else {
											servicioPlanAccion.eliminar(id);
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
		};
		botoneraPlan.appendChild(botonera);
	}

	protected boolean validar() {
		if (txtComo.getText().compareTo("") == 0
				|| txtCuando.getText().compareTo("") == 0
				|| txtDescripcion.getText().compareTo("") == 0
				|| txtDonde.getText().compareTo("") == 0
				|| txtQuien.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	/* Muestra el catalogo de los paises */
	@Listen("onClick = #btnBuscarPlan")
	public void mostrarCatalogo() {
		final List<PlanAccion> planes = servicioPlanAccion.buscarTodos();
		catalogo = new Catalogo<PlanAccion>(catalogoPlan,
				"Catalogo de Planes de Accion", planes, "Descripcion", "Quien",
				"Donde", "Cuando", "Como") {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<PlanAccion> buscar(String valor, String combo) {
				switch (combo) {
				case "Descripcion":
					return servicioPlanAccion.filtroDescripcion(valor);
				case "Quien":
					return servicioPlanAccion.filtroQuien(valor);
				case "Donde":
					return servicioPlanAccion.filtroDonde(valor);
				case "Cuando":
					return servicioPlanAccion.filtroCuando(valor);
				case "Como":
					return servicioPlanAccion.filtroComo(valor);
				default:
					return planes;
				}
			}

			@Override
			protected String[] crearRegistros(PlanAccion object) {
				String[] registros = new String[5];
				registros[0] = object.getDescripcion();
				registros[1] = object.getQuien();
				registros[2] = object.getDonde();
				registros[3] = object.getCuando();
				registros[4] = object.getComo();
				return registros;
			}
		};
		catalogo.setParent(catalogoPlan);
		catalogo.doModal();
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoPlan")
	public void seleccinar() {
		PlanAccion object = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(object);
		catalogo.setParent(null);
	}

	/* Busca si existe un pais con el mismo nombre escrito */
	@Listen("onChange = #txtDescripcion")
	public void buscarPorNombre() {
		PlanAccion object = servicioPlanAccion.buscarPorNombre(txtDescripcion
				.getValue());
		if (object != null)
			llenarCampos(object);
	}

	/* LLena los campos del formulario dado un pais */
	private void llenarCampos(PlanAccion object) {
		txtComo.setValue(object.getComo());
		txtCuando.setValue(object.getCuando());
		txtDescripcion.setValue(object.getDescripcion());
		txtDonde.setValue(object.getDonde());
		txtQuien.setValue(object.getQuien());
		id = object.getIdPlan();
	}
}
