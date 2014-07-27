package controlador.maestros;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import modelo.maestros.Cita;
import modelo.maestros.MotivoCita;

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

public class CMotivoCita extends CGenerico {

	private static final long serialVersionUID = 8064356600535312313L;

	@Wire
	private Textbox txtDescripcionMotivoCita;
	@Wire
	private Div botoneraMotivoCita;
	@Wire
	private Div catalogoMotivoCita;
	@Wire
	private Div divMotivoCita;
	@Wire
	private Button btnBuscarMotivoCita;

	Catalogo<MotivoCita> catalogo;
	long id = 0;

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
				cerrarVentana(divMotivoCita, "Motivo", tabs);

			}

			@Override
			public void limpiar() {
				txtDescripcionMotivoCita.setText("");
				id = 0;

			}

			@Override
			public void guardar() {
				if (validar()) {
					String Descripcion = txtDescripcionMotivoCita.getValue();

					MotivoCita motivoCita = new MotivoCita(id, Descripcion,
							fechaHora, horaAuditoria, nombreUsuarioSesion());
					servicioMotivoCita.guardar(motivoCita);
					msj.mensajeInformacion(Mensaje.guardado);
					limpiar();
				}

			}

			@Override
			public void eliminar() {
				if (id != 0
						&& txtDescripcionMotivoCita.getText().compareTo("") != 0) {
					Messagebox.show(
							"¿Esta Seguro de Eliminar la el Motivo de Cita?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										MotivoCita motivoCita = servicioMotivoCita
												.buscar(id);
										List<Cita> citas = servicioCita
												.buscarPorMotivo(motivoCita);
										if (!citas.isEmpty()) {
											msj.mensajeError(Mensaje.noEliminar);
										} else {
											servicioMotivoCita
													.eliminar(motivoCita);
											limpiar();
											msj.mensajeInformacion(Mensaje.eliminado);
										}
									}
								}
							});
				} else
					msj.mensajeAlerta(Mensaje.noSeleccionoRegistro);

			}
		};

		botoneraMotivoCita.appendChild(botonera);

	}

	/* Muestra un catalogo de motivos de Cita */
	@Listen("onClick = #btnBuscarMotivoCita")
	public void mostrarCatalogo() throws IOException {
		List<MotivoCita> motivoCitas = servicioMotivoCita.buscarTodos();
		catalogo = new Catalogo<MotivoCita>(catalogoMotivoCita,
				"Catalogo de Motivos de Cita", motivoCitas, "Descripcion") {

			@Override
			protected String[] crearRegistros(MotivoCita motivoCita) {
				String[] registros = new String[1];
				registros[0] = motivoCita.getDescripcion();
				return registros;
			}

			@Override
			protected List<MotivoCita> buscar(String valor, String combo) {
				if (combo.equals("Descripcion"))
					return servicioMotivoCita.filtroDescripcion(valor);
				else
					return servicioMotivoCita.buscarTodos();
			}
		};
		catalogo.setParent(catalogoMotivoCita);
		catalogo.doModal();
	}

	/* Validaciones de pantalla para poder realizar el guardar */
	public boolean validar() {

		if (txtDescripcionMotivoCita.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	/* Busca si existe un motivo con el mismo nombre escrito */
	@Listen("onChange = #txtDescripcionMotivoCita")
	public void buscarPorNombre() {
		MotivoCita motivoCita = servicioMotivoCita
				.buscarPorDescripcion(txtDescripcionMotivoCita.getValue());
		if (motivoCita != null)
			llenarCampos(motivoCita);
	}

	/* Selecciona un motivo del catalogo y llena los campos con la informacion */
	@Listen("onSeleccion = #catalogoMotivoCita")
	public void seleccion() {
		MotivoCita motivoCita = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(motivoCita);
		catalogo.setParent(null);
	}

	/* LLena los campos del formulario dada un motivo de cita */
	public void llenarCampos(MotivoCita motivoCita) {
		txtDescripcionMotivoCita.setValue(motivoCita.getDescripcion());
		id = motivoCita.getIdMotivoCita();
	}
}
