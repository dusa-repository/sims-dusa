package controlador.control;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import modelo.control.ControlConsulta;
import modelo.control.ControlOrden;
import modelo.maestros.Cita;
import modelo.maestros.Paciente;
import modelo.seguridad.Usuario;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Caption;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;
import controlador.maestros.CGenerico;

public class CControl extends CGenerico {

	private static final long serialVersionUID = -4322133483227649322L;
	@Wire
	private Div botoneraControl;
	@Wire
	private Div divCatalogoPacientes;
	@Wire
	private Div divVControl;
	@Wire
	private Div divCatalogoControlOrdenes;
	@Wire
	private Div divCatalogoControlConsultas;
	@Wire
	private Groupbox gxpGeneral;
	@Wire
	private Label lblRequerido;
	@Wire
	private Caption caption;
	@Wire
	private Button btnEliminar;
	@Wire
	private Button btnCancelar;
	@Wire
	private Button btnAprobar;
	@Wire
	private Button btnVer;
	@Wire
	private Textbox txtCedulaPaciente;
	@Wire
	private Label lblNombrePaciente;
	@Wire
	private Label lblApellidoPaciente;
	@Wire
	private Textbox txtObservacion;
	@Wire
	private Datebox dtbFecha;
	@Wire
	private Combobox cmbTipoConsulta;
	@Wire
	private Combobox cmbTipoPreventiva;
	@Wire
	private Label lblPreventiva;
	@Wire
	private Row row;
	Long id = null;
	private boolean ordenConsulta = true;
	private String nombre;
	private String idPaciente = null;
	private Catalogo<Paciente> catalogoPaciente;
	private Catalogo<ControlConsulta> catalogoControlConsulta;
	private Catalogo<ControlOrden> catalogoControlOrden;
	private List<ControlConsulta> controlesConsulta = new ArrayList<ControlConsulta>();
	private List<ControlOrden> controlesOrden = new ArrayList<ControlOrden>();
	URL urlPendiente = getClass().getResource("../maestros/pendiente.png");
	URL urlCancelada = getClass().getResource("../maestros/cancelar.png");
	URL urlAprobada = getClass().getResource("../maestros/seleccionar.png");
	private String[] consultaPreventiva = { "Pre-Empleo", "Pre-Vacacional",
			"Post-Vacacional", "Egreso", "Cambio de Puesto", "Promocion",
			"Reintegro", "Por Area", "Checkeo General" };
	private String[] consultaCurativa = { "Primera", "Control", "IC" };

	@Override
	public void inicializar() throws IOException {
		HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (map != null) {
			if (map.get("tabsGenerales") != null) {
				tabs = (List<Tab>) map.get("tabsGenerales");
				nombre = (String) map.get("titulo");
				map.clear();
				map = null;
			}
		}
		if (nombre.contains("Ordenes"))
			ordenConsulta = false;
		if (ordenConsulta) {
			row.setVisible(true);
			mostrarCatalogoConsulta();
			gxpGeneral.setTitle("Control de Consultas");
			lblRequerido.setVisible(false);
			caption.setLabel("Solicitudes de Consultas");
			btnAprobar.setLabel("Ingresar Consulta");
			btnCancelar.setLabel("Cancelar Consulta");
			btnEliminar.setLabel("Eliminar Consulta");
			btnVer.setLabel("Ver Consulta");
			btnVer.setTooltiptext("Seleccione un Registro para ver la Solicitud de consulta");
			btnAprobar
					.setTooltiptext("Seleccione uno o varios registros para indicar que el paciente ingreso a la consulta");
			btnCancelar
					.setTooltiptext("Seleccione uno o varios registros para indicar que el paciente cancelo su solicitud de consulta");
			btnEliminar
					.setTooltiptext("Seleccione uno o varios registros para eliminar la solicitud de consulta");
		} else {
			mostrarCatalogoOrden();
			gxpGeneral.setTitle("Control de Ordenes");
			caption.setLabel("Solicitudes de Ordenes");
		}

		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divVControl, nombre, tabs);
			}

			@Override
			public void limpiar() {
				txtCedulaPaciente.setValue("");
				txtObservacion.setValue("");
				lblNombrePaciente.setValue("");
				lblApellidoPaciente.setValue("");
				idPaciente = null;
				id = null;
				if (ordenConsulta) {
					cmbTipoConsulta.setValue("");
					cmbTipoPreventiva.setValue("");
				}
			}

			@Override
			public void guardar() {
				if (validar()) {
					String observacion = txtObservacion.getValue();
					Paciente paciente = servicioPaciente
							.buscarPorCedula(idPaciente);
					String estado = "Pendiente";
					String format = formatoFecha.format(dtbFecha.getValue());
					Date fechaNueva = null;
					try {
						fechaNueva = formatoFecha.parse(format);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					Timestamp fechaConsulta = new Timestamp(
							fechaNueva.getTime());
					String hora = horaAuditoria;
					if (ordenConsulta) {
						if (id != null) {
							ControlConsulta control = servicioControlConsulta
									.buscar(id);
							estado = control.getEstado();
							fechaConsulta = control.getFechaLLegada();
							hora = control.getHoraLLegada();
						} else
							id = (long) 0;
						String secundaria = cmbTipoPreventiva.getValue();
						ControlConsulta control = new ControlConsulta(id,
								paciente, observacion, estado, fechaConsulta,
								hora, null, null, fechaHora, horaAuditoria,
								nombreUsuarioSesion(),
								cmbTipoConsulta.getValue(), secundaria);
						servicioControlConsulta.guardar(control);
						controlesConsulta = servicioControlConsulta
								.buscarPorFecha(dtbFecha.getValue());
						catalogoControlConsulta.actualizarLista(
								controlesConsulta, true);
					} else {
						if (id != null) {
							ControlOrden control = servicioControlOrden
									.buscar(id);
							estado = control.getEstado();
							fechaConsulta = control.getFechaRecepcion();
							hora = control.getHoraRecepcion();
						} else
							id = (long) 0;
						ControlOrden control = new ControlOrden(id, paciente,
								observacion, estado, fechaConsulta, hora, null,
								null, fechaHora, horaAuditoria,
								nombreUsuarioSesion());
						servicioControlOrden.guardar(control);
						controlesOrden = servicioControlOrden
								.buscarPorFecha(dtbFecha.getValue());
						catalogoControlOrden.actualizarLista(controlesOrden,
								true);
					}
					Mensaje.mensajeInformacion(Mensaje.guardado);
					limpiar();
				}
			}

			@Override
			public void eliminar() {
				// TODO Auto-generated method stub

			}
		};
		botonera.getChildren().get(1).setVisible(false);
		botoneraControl.appendChild(botonera);
	}

	protected boolean validar() {
		if (ordenConsulta) {
			if (txtCedulaPaciente.getText().compareTo("") == 0
					|| cmbTipoConsulta.getText().compareTo("") == 0
					|| cmbTipoPreventiva.getText().compareTo("") == 0) {
				Mensaje.mensajeError(Mensaje.camposVacios);
				return false;
			} else
				return true;
		} else {
			if (txtObservacion.getText().compareTo("") == 0
					|| txtCedulaPaciente.getText().compareTo("") == 0) {
				Mensaje.mensajeError(Mensaje.camposVacios);
				return false;
			} else
				return true;
		}
	}

	private void mostrarCatalogoOrden() {
		controlesOrden = servicioControlOrden.buscarPorFecha(fecha);
		catalogoControlOrden = new Catalogo<ControlOrden>(
				divCatalogoControlOrdenes, "Catalogo de Solicitudes de orden",
				controlesOrden, true, "Cedula", "Nombre", "Apellido",
				"Observacion", "Fecha", "Hora", "Estado") {

			@Override
			protected List<ControlOrden> buscar(String valor, String combo) {
				switch (combo) {
				case "Cedula":
					return servicioControlOrden.filtroCedula(valor,
							dtbFecha.getValue());
				case "Nombre":
					return servicioControlOrden.filtroNombre(valor,
							dtbFecha.getValue());
				case "Apellido":
					return servicioControlOrden.filtroApellido(valor,
							dtbFecha.getValue());
				case "Observacion":
					return servicioControlOrden.filtroObservacion(valor,
							dtbFecha.getValue());
				case "Hora":
					return servicioControlOrden.filtroHora(valor,
							dtbFecha.getValue());
				case "Estado":
					return servicioControlOrden.filtroEstado(valor,
							dtbFecha.getValue());
				default:
					return controlesOrden;
				}
			}

			@Override
			protected String[] crearRegistros(ControlOrden objeto) {
				String imagenm = null;
				Timestamp fechaL = objeto.getFechaRecepcion();
				Date fechaNueva = new Date(fechaL.getTime());
				if (objeto.getEstado().equals("Pendiente")) {
					imagenm = urlPendiente.toString();
				} else {
					if (objeto.getEstado().equals("Aprobado"))
						imagenm = urlAprobada.toString();
					else
						imagenm = urlCancelada.toString();
				}
				String[] registros = new String[7];
				registros[0] = objeto.getPaciente().getCedula();
				registros[1] = objeto.getPaciente().getPrimerNombre();
				registros[2] = objeto.getPaciente().getPrimerApellido();
				registros[3] = objeto.getObservacion();
				registros[4] = formatoFecha.format(fechaNueva);
				registros[5] = objeto.getHoraRecepcion();
				registros[6] = imagenm;
				return registros;
			}

		};
		catalogoControlOrden.setParent(divCatalogoControlOrdenes);
	}

	private void mostrarCatalogoConsulta() {
		controlesConsulta = servicioControlConsulta.buscarPorFecha(fecha);
		catalogoControlConsulta = new Catalogo<ControlConsulta>(
				divCatalogoPacientes, "Catalogo de Solicitudes de consulta",
				controlesConsulta, true, "Cedula", "Nombre", "Apellido",
				"Tipo", "Observacion", "Fecha", "Hora", "Estado") {

			@Override
			protected List<ControlConsulta> buscar(String valor, String combo) {
				switch (combo) {
				case "Cedula":
					return servicioControlConsulta.filtroCedula(valor,
							dtbFecha.getValue());
				case "Nombre":
					return servicioControlConsulta.filtroNombre(valor,
							dtbFecha.getValue());
				case "Apellido":
					return servicioControlConsulta.filtroApellido(valor,
							dtbFecha.getValue());
				case "Observacion":
					return servicioControlConsulta.filtroObservacion(valor,
							dtbFecha.getValue());
				case "Tipo":
					return servicioControlConsulta.filtroTipo(valor,
							dtbFecha.getValue());
				case "Hora":
					return servicioControlConsulta.filtroHora(valor,
							dtbFecha.getValue());
				case "Estado":
					return servicioControlConsulta.filtroEstado(valor,
							dtbFecha.getValue());
				default:
					return controlesConsulta;
				}
			}

			@Override
			protected String[] crearRegistros(ControlConsulta objeto) {
				String imagenm = null;
				Timestamp fechaL = objeto.getFechaLLegada();
				Date fechaNueva = new Date(fechaL.getTime());
				if (objeto.getEstado().equals("Pendiente")) {
					imagenm = urlPendiente.toString();
				} else {
					if (objeto.getEstado().equals("Aprobado"))
						imagenm = urlAprobada.toString();
					else
						imagenm = urlCancelada.toString();
				}
				String[] registros = new String[8];
				registros[0] = objeto.getPaciente().getCedula();
				registros[1] = objeto.getPaciente().getPrimerNombre();
				registros[2] = objeto.getPaciente().getPrimerApellido();
				registros[3] = objeto.getTipoConsultaSecundaria();
				registros[4] = objeto.getObservacion();
				registros[5] = formatoFecha.format(fechaNueva);
				registros[6] = objeto.getHoraLLegada();
				registros[7] = imagenm;
				return registros;
			}

		};
		catalogoControlConsulta.setParent(divCatalogoControlConsultas);
	}

	@Listen("onClick = #btnBuscarPaciente")
	public void mostrarCatalogoPaciente() throws IOException {

		final List<Paciente> pacientes = servicioPaciente.buscarTodosActivos();
		catalogoPaciente = new Catalogo<Paciente>(divCatalogoPacientes,
				"Catalogo de Pacientes", pacientes, false, "Cedula", "Ficha",
				"Nombre", "Apellido", "Trabajador Asociado") {

			@Override
			protected List<Paciente> buscar(String valor, String combo) {
				switch (combo) {
				case "Ficha":
					return servicioPaciente.filtroFichaActivos(valor);
				case "Nombre":
					return servicioPaciente.filtroNombre1Activos(valor);
				case "Cedula":
					return servicioPaciente.filtroCedulaActivos(valor);
				case "Apellido":
					return servicioPaciente.filtroApellido1Activos(valor);
				case "Trabajador Asociado":
					return servicioPaciente.filtroCedulaFamiliar1Activos(valor);
				default:
					return pacientes;
				}
			}

			@Override
			protected String[] crearRegistros(Paciente objeto) {
				String[] registros = new String[5];
				registros[0] = objeto.getCedula();
				registros[1] = objeto.getFicha();
				registros[2] = objeto.getPrimerNombre();
				registros[3] = objeto.getPrimerApellido();
				registros[4] = objeto.getCedulaFamiliar();
				return registros;
			}

		};
		catalogoPaciente.setParent(divCatalogoPacientes);
		catalogoPaciente.doModal();
	}

	/* Permite la seleccion de un item del catalogo de pacientes */
	@Listen("onSeleccion = #divCatalogoPacientes")
	public void seleccionarPaciente() {
		Paciente paciente = catalogoPaciente.objetoSeleccionadoDelCatalogo();
		llenarCamposPaciente(paciente);
		catalogoPaciente.setParent(null);
	}

	public void llenarCamposPaciente(Paciente paciente) {
		if (!paciente.isTrabajador()
				&& paciente.getParentescoFamiliar().equals("Hijo(a)")) {
			Paciente representante = servicioPaciente.buscarPorCedula(paciente
					.getCedulaFamiliar());
			if (representante.isMuerte()) {
				if (calcularEdad(representante.getFechaMuerte()) >= 1)
					Mensaje.mensajeAlerta(Mensaje.pacienteFallecido);
			} else {
				if (calcularEdad(paciente.getFechaNacimiento()) >= 18)
					Mensaje.mensajeAlerta(Mensaje.pacienteMayor);
			}
		}
		txtCedulaPaciente.setValue(paciente.getCedula());
		lblNombrePaciente.setValue(paciente.getPrimerNombre() + " "
				+ paciente.getSegundoNombre());
		lblApellidoPaciente.setValue(paciente.getPrimerApellido() + " "
				+ paciente.getSegundoApellido());
		idPaciente = paciente.getCedula();
	}

	@Listen("onOK = #txtCedulaPaciente; onChange = #txtCedulaPaciente")
	public void buscarCedula() {
		Paciente paciente = servicioPaciente
				.buscarPorCedulaActivo(txtCedulaPaciente.getValue());
		if (paciente != null) {
			llenarCamposPaciente(paciente);
		} else {
			txtCedulaPaciente.setValue("");
			lblNombrePaciente.setValue("");
			lblApellidoPaciente.setValue("");
			idPaciente = null;
			Mensaje.mensajeError(Mensaje.pacienteNoExiste);
		}
	}

	@Listen("onClick = #btnVer")
	public void ver() {
		if (ordenConsulta) {
			if (validarSeleccionConsulta(catalogoControlConsulta)) {
				if (catalogoControlConsulta.obtenerSeleccionados().size() == 1) {
					ControlConsulta control = catalogoControlConsulta
							.objetoSeleccionadoDelCatalogo();
					if (control.getEstado().equals("Pendiente")) {
						llenarCamposPaciente(control.getPaciente());
						id = control.getIdControlConsulta();
						dtbFecha.setValue(control.getFechaLLegada());
						txtObservacion.setValue(control.getObservacion());
						cmbTipoConsulta.setValue(control.getTipoConsulta());
						cmbTipoPreventiva.setVisible(true);
						cmbTipoPreventiva.setValue(control
								.getTipoConsultaSecundaria());
					} else
						Mensaje.mensajeAlerta("Solo puede Modificar Solicitudes en Estado Pendiente");
				} else
					Mensaje.mensajeAlerta(Mensaje.editarSoloUno);
			}

		} else {
			if (validarSeleccionOrden(catalogoControlOrden)) {
				if (catalogoControlOrden.obtenerSeleccionados().size() == 1) {
					ControlOrden control = catalogoControlOrden
							.objetoSeleccionadoDelCatalogo();
					if (control.getEstado().equals("Pendiente")) {
						llenarCamposPaciente(control.getPaciente());
						id = control.getIdControlOrden();
						dtbFecha.setValue(control.getFechaRecepcion());
						txtObservacion.setValue(control.getObservacion());
					} else
						Mensaje.mensajeAlerta("Solo puede Modificar Solicitudes en Estado Pendiente");
				} else
					Mensaje.mensajeAlerta(Mensaje.editarSoloUno);
			}
		}
	}

	@Listen("onClick = #btnAprobar, #btnCancelar, #btnEliminar")
	public void cambiarEstado(Event evento) {
		String estado = null;
		boolean borrar = false;
		Timestamp fechaFinal = null;
		String horaFinal = null;
		if (evento.getTarget().getId().equals("btnAprobar")) {
			fechaFinal = new Timestamp(fecha.getTime());
			estado = "Aprobado";
			horaFinal = horaAuditoria;
		} else {
			if (evento.getTarget().getId().equals("btnCancelar"))
				estado = "Cancelada";
			else {
				borrar = true;
				estado = "Eliminar";
			}
		}
		final boolean eliminar = borrar;
		if (ordenConsulta) {
			if (validarSeleccionConsulta(catalogoControlConsulta)) {
				List<ControlConsulta> lista = catalogoControlConsulta
						.obtenerSeleccionados();
				final List<ControlConsulta> seleccionados = cambiarEstadoConsulta(
						lista, estado, fechaFinal, horaFinal);
				if (!seleccionados.isEmpty()) {
					Messagebox.show(
							"¿Desea Aprobar las " + seleccionados.size()
									+ " Solicitudes de Consulta?", "Alerta",
							Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										if (!eliminar)
											servicioControlConsulta
													.guardarVarios(seleccionados);
										else
											servicioControlConsulta
													.eliminarVarios(seleccionados);
										controlesConsulta = servicioControlConsulta
												.buscarPorFecha(dtbFecha
														.getValue());
										catalogoControlConsulta
												.actualizarLista(
														controlesConsulta, true);
										Mensaje.mensajeInformacion("Operacion Realizada con Exito");
									}
								}
							});
				}
			} else {
				if (validarSeleccionOrden(catalogoControlOrden)) {
					List<ControlOrden> lista = catalogoControlOrden
							.obtenerSeleccionados();
					final List<ControlOrden> seleccionados = cambiarEstadoOrden(
							lista, estado, fechaFinal, horaFinal);
					if (!seleccionados.isEmpty()) {
						Messagebox
								.show("¿Desea Entregar las "
										+ seleccionados.size() + " Ordenes?",
										"Alerta",
										Messagebox.OK | Messagebox.CANCEL,
										Messagebox.QUESTION,
										new org.zkoss.zk.ui.event.EventListener<Event>() {
											public void onEvent(Event evt)
													throws InterruptedException {
												if (evt.getName()
														.equals("onOK")) {
													if (!eliminar)
														servicioControlOrden
																.guardarVarios(seleccionados);
													else
														servicioControlOrden
																.eliminarVarios(seleccionados);
													controlesOrden = servicioControlOrden
															.buscarPorFecha(dtbFecha
																	.getValue());
													catalogoControlOrden
															.actualizarLista(
																	controlesOrden,
																	true);
													Mensaje.mensajeInformacion("Operacion Realizada con Exito");
												}
											}
										});
					}
				}
			}
		}
	}

	public List<ControlConsulta> cambiarEstadoConsulta(
			List<ControlConsulta> lista, String estado, Timestamp fechaFinal,
			String horaFinal) {
		for (Iterator<ControlConsulta> iterator = lista.iterator(); iterator
				.hasNext();) {
			ControlConsulta controlConsulta = (ControlConsulta) iterator.next();
			controlConsulta.setEstado(estado);
			controlConsulta.setFechaIngreso(fechaFinal);
			controlConsulta.setHoraIngreso(horaFinal);
			controlConsulta.setFechaAuditoria(fechaHora);
			controlConsulta.setHoraAuditoria(horaAuditoria);
			controlConsulta.setUsuarioAuditoria(nombreUsuarioSesion());
		}
		return lista;
	}

	public List<ControlOrden> cambiarEstadoOrden(List<ControlOrden> lista,
			String estado, Timestamp fechaFinal, String horaFinal) {
		for (Iterator<ControlOrden> iterator = lista.iterator(); iterator
				.hasNext();) {
			ControlOrden controlConsulta = (ControlOrden) iterator.next();
			controlConsulta.setEstado(estado);
			controlConsulta.setFechaEntrega(fechaFinal);
			controlConsulta.setHoraEntrega(horaFinal);
			controlConsulta.setFechaAuditoria(fechaHora);
			controlConsulta.setHoraAuditoria(horaAuditoria);
			controlConsulta.setUsuarioAuditoria(nombreUsuarioSesion());
		}
		return lista;
	}

	public boolean validarSeleccionConsulta(Catalogo<ControlConsulta> catalogo) {
		List<ControlConsulta> seleccionados = catalogo.obtenerSeleccionados();
		if (seleccionados == null) {
			Mensaje.mensajeAlerta(Mensaje.noHayRegistros);
			return false;
		} else {
			if (seleccionados.isEmpty()) {
				Mensaje.mensajeAlerta(Mensaje.noSeleccionoItem);
				return false;
			} else {
				return true;
			}
		}
	}

	public boolean validarSeleccionOrden(Catalogo<ControlOrden> catalogo) {
		List<ControlOrden> seleccionados = catalogo.obtenerSeleccionados();
		if (seleccionados == null) {
			Mensaje.mensajeAlerta(Mensaje.noHayRegistros);
			return false;
		} else {
			if (seleccionados.isEmpty()) {
				Mensaje.mensajeAlerta(Mensaje.noSeleccionoItem);
				return false;
			} else {
				return true;
			}
		}
	}

	@Listen("onChange = #dtbFecha")
	public void cambiarCatalogo() {
		if (ordenConsulta) {
			controlesConsulta = servicioControlConsulta.buscarPorFecha(dtbFecha
					.getValue());
			catalogoControlConsulta.actualizarLista(controlesConsulta, true);

		} else {
			controlesOrden = servicioControlOrden.buscarPorFecha(dtbFecha
					.getValue());
			catalogoControlOrden.actualizarLista(controlesOrden, true);
		}
	}

	@Listen("onSelect = #cmbTipoConsulta")
	public void buscarPreventiva() {
		if (cmbTipoConsulta.getValue().equals("Preventiva")) {
			cmbTipoPreventiva.setModel(new ListModelList<String>(
					consultaPreventiva));
			lblPreventiva.setValue("Tipo de Consulta Preventiva");
		} else {
			if (cmbTipoConsulta.getValue().equals("Curativa")) {
				cmbTipoPreventiva.setModel(new ListModelList<String>(
						consultaCurativa));
				lblPreventiva.setValue("Tipo de Consulta Curativa");
			}
		}
		cmbTipoPreventiva.setVisible(true);
		lblPreventiva.setVisible(true);
		cmbTipoPreventiva.setValue("");

	}
}
