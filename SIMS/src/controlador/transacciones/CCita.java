package controlador.transacciones;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import modelo.maestros.Cita;
import modelo.maestros.MotivoCita;
import modelo.maestros.Paciente;
import modelo.seguridad.Arbol;
import modelo.seguridad.Usuario;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;

import arbol.CArbol;

import componentes.Botonera;
import componentes.Buscar;
import componentes.Catalogo;
import componentes.Mensaje;

import controlador.maestros.CGenerico;

public class CCita extends CGenerico {

	private static final long serialVersionUID = 6527893397646342573L;
	@Wire
	private Div botoneraCita;
	@Wire
	private Div catalogoUsuarios;
	@Wire
	private Div divCatalogoPacientes;
	@Wire
	private Div divCita;
	@Wire
	private Button btnSiguientePestanna;
	@Wire
	private Button btnAnteriorPestanna;
	@Wire
	private Tab tabCita;
	@Wire
	private Tab tabConsultar;
	@Wire
	private Label lblCedulaDoctor;
	@Wire
	private Label lblNombreDoctor;
	@Wire
	private Label lblApellidoDoctor;
	@Wire
	private Button btnBuscarDoctor;
	@Wire
	private Button btnBuscarPaciente;
	@Wire
	private Label lblCedulaPaciente;
	@Wire
	private Label lblNombrePaciente;
	@Wire
	private Label lblApellidoPaciente;
	@Wire
	private Label lblEmpresaPaciente;
	@Wire
	private Textbox txtObservacion;
	@Wire
	private Timebox tmbHoraCita;
	@Wire
	private Combobox cmbMotivo;
	@Wire
	private Datebox dtbFechaCita;
	@Wire
	private Listbox ltbCitas;
	@Wire
	private Button btnAnularCita;
	@Wire
	private Button btnCancelarCita;
	long id = 0;
	long idDoctor = 0;
	String idPaciente = "";
	Catalogo<Usuario> catalogo;
	Catalogo<Paciente> catalogoPaciente;

	private CArbol cArbol = new CArbol();
	Buscar<Cita> buscador;
	List<Cita> citas = new ArrayList<Cita>();
	@Wire
	private Textbox txtBuscador;
	@Wire
	private Combobox cmbBuscador;
	private String[] valores = { "Paciente", "Empresa", "Fecha", "Motivo" };

	@Override
	public void inicializar() throws IOException {
		contenido = (Include) divCita.getParent();
		Tabbox tabox = (Tabbox) divCita.getParent().getParent().getParent()
				.getParent();
		tabBox = tabox;
		tab = (Tab) tabox.getTabs().getLastChild();
		HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (map != null) {
			if (map.get("tabsGenerales") != null) {
				tabs = (List<Tab>) map.get("tabsGenerales");
				System.out.println(tabs.size());
				map.clear();
				map = null;
			}
		}
		buscar();
		llenarComboMotivo();
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divCita, "Cita", tabs);

			}

			@Override
			public void limpiar() {
				lblApellidoDoctor.setValue("");
				lblNombreDoctor.setValue("");
				lblCedulaDoctor.setValue("");
				idDoctor = 0;
				ltbCitas.getItems().clear();
				limpiar2();
			}

			@Override
			public void guardar() {
				if (validar()) {
					String observacion;
					Date fechaCreacion;
					observacion = txtObservacion.getValue();
					fechaCreacion = dtbFechaCita.getValue();
					Timestamp fechaCrea = new Timestamp(fechaCreacion.getTime());
					String idMotivo = cmbMotivo.getSelectedItem().getContext();
					MotivoCita motivo = servicioMotivoCita.buscar(Long
							.parseLong(idMotivo));
					Paciente paciente = servicioPaciente.buscarPorCedula(String
							.valueOf(idPaciente));
					Usuario usuario = servicioUsuario.buscarPorCedula(String
							.valueOf(idDoctor));
					String estado = "Pendiente";
					Date hora = tmbHoraCita.getValue();
					String horaCita = df.format(hora);
					Cita cita = new Cita(id, estado, fechaHora, fechaCrea,
							fechaHora, horaAuditoria, horaCita, usuario,
							observacion, nombreUsuarioSesion(), motivo,
							paciente);

					servicioCita.guardar(cita);
					llenarListaCitas(usuario);
					limpiar2();
					msj.mensajeInformacion(Mensaje.guardado);
				}

			}

			@Override
			public void eliminar() {
			}
		};
		botonera.getChildren().get(1).setVisible(false);
		botoneraCita.appendChild(botonera);

	}

	/*
	 * Borra los datos referentes a la cita, brindando la opcion de seguir
	 * agregando citas al doctor actual
	 */
	private void limpiar2() {
		Date dt = new Date();
		lblCedulaPaciente.setValue("");
		lblNombrePaciente.setValue("");
		lblApellidoPaciente.setValue("");
		lblEmpresaPaciente.setValue("");
		txtObservacion.setValue("");
		tmbHoraCita.setValue(dt);
		dtbFechaCita.setValue(null);
		cmbMotivo.setValue("");
		cmbMotivo.setPlaceholder("Seleccione un Motivo");
		id = 0;
		idPaciente = "";

	}

	/* Muestra un catalogo de Usuarios */
	@Listen("onClick = #btnBuscarDoctor")
	public void mostrarCatalogo() throws IOException {
		final List<Usuario> usuarios = servicioUsuario.buscarDoctores();
		catalogo = new Catalogo<Usuario>(catalogoUsuarios,
				"Catalogo de Doctores", usuarios, "Cedula", "Ficha", "Nombre",
				"Apellido", "Especialidad") {

			@Override
			protected List<Usuario> buscar(String valor, String combo) {
				switch (combo) {
				case "Cedula":
					return servicioUsuario.filtroCedula(valor);
				case "Ficha":
					return servicioUsuario.filtroFicha(valor);
				case "Nombre":
					return servicioUsuario.filtroNombre(valor);
				case "Especialidad":
					return servicioUsuario.filtroEspecialidad(valor);
				case "Apellido":
					return servicioUsuario.filtroApellido(valor);
				default:
					return usuarios;
				}
			}

			@Override
			protected String[] crearRegistros(Usuario objeto) {
				String[] registros = new String[5];
				registros[0] = objeto.getCedula();
				registros[1] = objeto.getFicha();
				registros[2] = objeto.getPrimerNombre();
				registros[3] = objeto.getPrimerApellido();
				registros[4] = objeto.getEspecialidad().getDescripcion();
				return registros;
			}

		};
		catalogo.setParent(catalogoUsuarios);
		catalogo.doModal();
	}

	/* Permite la seleccion de un item del catalogo de doctores */
	@Listen("onSeleccion = #catalogoUsuarios")
	public void seleccionarDoctor() {
		Usuario usuario = catalogo.objetoSeleccionadoDelCatalogo();
		lblApellidoDoctor.setValue(usuario.getPrimerApellido() + " "
				+ usuario.getSegundoApellido());
		lblNombreDoctor.setValue(usuario.getPrimerNombre() + " "
				+ usuario.getSegundoNombre());
		lblCedulaDoctor.setValue(usuario.getCedula());
		idDoctor = Long.valueOf(usuario.getCedula());
		llenarListaCitas(usuario);
		limpiar2();
		catalogo.setParent(null);
	}

	/* Llena la lista de citas segun un usuario determinado */
	public void llenarListaCitas(Usuario usuario) {
		List<Cita> citasDoctor = servicioCita.buscarPorUsuarioYEstado(usuario,
				"Pendiente");
		citas = citasDoctor;
		for (int i = 0; i < citasDoctor.size(); i++) {

			String nombre = citasDoctor.get(i).getPaciente().getPrimerNombre();
			String apellido = citasDoctor.get(i).getPaciente()
					.getPrimerApellido();
			Paciente paciente = citasDoctor.get(i).getPaciente();
			paciente.setHoraAuditoria(nombre + " " + apellido);
		}
		ltbCitas.setModel(new ListModelList<Cita>(citasDoctor));
		ltbCitas.setMultiple(false);
		ltbCitas.setCheckmark(false);
		ltbCitas.setMultiple(true);
		ltbCitas.setCheckmark(true);
	}

	/* Muestra un catalogo de Pacientes */
	@Listen("onClick = #btnBuscarPaciente")
	public void mostrarCatalogoPaciente() throws IOException {
		final List<Paciente> pacientes = servicioPaciente.buscarTodosActivos();
		catalogoPaciente = new Catalogo<Paciente>(divCatalogoPacientes,
				"Catalogo de Pacientes", pacientes, "Cedula", "Nombre",
				"Apellido") {

			@Override
			protected List<Paciente> buscar(String valor, String combo) {

				switch (combo) {
				case "Nombre":
					return servicioPaciente.filtroNombre1(valor);
				case "Cedula":
					return servicioPaciente.filtroCedula(valor);
				case "Apellido":
				default:
					return pacientes;
				}
			}

			@Override
			protected String[] crearRegistros(Paciente objeto) {
				String[] registros = new String[3];
				registros[0] = objeto.getCedula();
				registros[1] = objeto.getPrimerNombre();
				registros[2] = objeto.getPrimerApellido();
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
		if (!paciente.isTrabajador()
				&& paciente.getParentescoFamiliar().equals("Hijo(a)")) {
			Paciente representante = servicioPaciente.buscarPorCedula(paciente
					.getCedulaFamiliar());
			if (representante.isMuerte()) {
				if (calcularEdad(representante.getFechaMuerte()) >= 1)
					msj.mensajeAlerta(Mensaje.pacienteFallecido);
			} else {
				if (calcularEdad(paciente.getFechaNacimiento()) >= 18)
					msj.mensajeAlerta(Mensaje.pacienteMayor);
			}
		}
		lblCedulaPaciente.setValue(paciente.getCedula());
		lblNombrePaciente.setValue(paciente.getPrimerNombre() + " "
				+ paciente.getSegundoNombre());
		lblApellidoPaciente.setValue(paciente.getPrimerApellido() + " "
				+ paciente.getSegundoApellido());
		idPaciente = paciente.getCedula();
		catalogoPaciente.setParent(null);
	}

	/* Llena el combo de Motivos cada vez que se abre */
	@Listen("onOpen = #cmbMotivo")
	public void llenarComboMotivo() {
		List<MotivoCita> motivoCitas = servicioMotivoCita.buscarTodos();
		cmbMotivo.setModel(new ListModelList<MotivoCita>(motivoCitas));
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (cmbMotivo.getText().compareTo("") == 0
				|| tmbHoraCita.getText().compareTo("") == 0
				|| dtbFechaCita.getText().compareTo("") == 0 || idDoctor == 0
				|| idPaciente.equals("")) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	/* Abre la pestanna de consultar citas */
	@Listen("onClick = #btnSiguientePestanna")
	public void siguientePestanna() {
		tabConsultar.setSelected(true);
	}

	/* Abre la pestanna de crear cita */
	@Listen("onClick = #btnAnteriorPestanna")
	public void anteriorPestanna() {
		tabCita.setSelected(true);
	}

	/*
	 * Se ejecuta al darle click al boton de cancelar en la pestanna de
	 * consultar citas, cancela la cita seleccionada
	 */
	@Listen("onClick = #btnCancelarCita")
	public void cancelarCita() {
		Boolean hayCitas = false;
		if (ltbCitas.getItemCount() != 0) {
			final List<Listitem> list1 = ltbCitas.getItems();
			for (int i = 0; i < list1.size(); i++) {
				if (list1.get(i).isSelected())
					hayCitas = true;
			}
			if (hayCitas) {
				hayCitas = false;
				Messagebox.show("¿Esta Seguro de Cancelar la(s) Citas(s)?",
						"Alerta", Messagebox.OK | Messagebox.CANCEL,
						Messagebox.QUESTION, new EventListener<Event>() {
							public void onEvent(Event evt) {
								switch (((Integer) evt.getData()).intValue()) {
								case Messagebox.OK:
									respuestaOk();
									break;
								case Messagebox.CANCEL:
									respuestaCancelar();
									break;
								}
							}

							private void respuestaCancelar() {
							}

							private void respuestaOk() {
								for (int i = 0; i < list1.size(); i++) {
									if (list1.get(i).isSelected()) {
										Cita cita = list1.get(i).getValue();
										cita.setEstado("Cancelada");
										servicioCita.guardar(cita);
									}
								}
								Usuario usuario = servicioUsuario
										.buscarPorCedula(String
												.valueOf(idDoctor));
								llenarListaCitas(usuario);
								msj.mensajeInformacion(Mensaje.citasCanceladas);
							}
						});
			} else
				msj.mensajeAlerta(Mensaje.seleccioneCitaCancelar);
		} else
			msj.mensajeAlerta(Mensaje.noCitasCancelacion);
	}

	/*
	 * Se ejecuta al darle click al boton de cancelar en la pestanna de
	 * consultar citas, anula la cita seleccionada
	 */
	@Listen("onClick = #btnAnularCita")
	public void anularCita() {
		Boolean hayCitas = false;
		if (ltbCitas.getItemCount() != 0) {
			final List<Listitem> list1 = ltbCitas.getItems();
			for (int i = 0; i < list1.size(); i++) {
				if (list1.get(i).isSelected())
					hayCitas = true;
			}
			if (hayCitas) {
				hayCitas = false;
				Messagebox.show("¿Esta Seguro de Anular la(s) Citas(s)?",
						"Alerta", Messagebox.OK | Messagebox.CANCEL,
						Messagebox.QUESTION, new EventListener<Event>() {
							public void onEvent(Event evt) {
								switch (((Integer) evt.getData()).intValue()) {
								case Messagebox.OK:
									respuestaOk();
									break;
								case Messagebox.CANCEL:
									respuestaCancelar();
									break;
								}
							}

							private void respuestaCancelar() {
							}

							private void respuestaOk() {
								for (int i = 0; i < list1.size(); i++) {
									if (list1.get(i).isSelected()) {
										Cita cita = list1.get(i).getValue();
										cita.setEstado("Anulada");
										servicioCita.guardar(cita);
									}
								}
								Usuario usuario = servicioUsuario
										.buscarPorCedula(String
												.valueOf(idDoctor));
								llenarListaCitas(usuario);
								msj.mensajeInformacion(Mensaje.citasAnuladas);
							}
						});
			} else
				msj.mensajeAlerta(Mensaje.seleccioneCitaAnular);
		} else
			msj.mensajeAlerta(Mensaje.noCitasAnulacion);
	}

	public void buscar() {
		cmbBuscador.setModel(new ListModelList<String>(valores));
		buscador = new Buscar<Cita>(ltbCitas, txtBuscador) {

			@Override
			protected List<Cita> buscar(String valor) {
				switch (cmbBuscador.getValue()) {
				case "Paciente":
					return recorrer(servicioCita.filtroPaciente(valor));
				case "Empresa":
					return recorrer(servicioCita.filtroEmpresa(valor));
				case "Fecha":
					return recorrer(servicioCita.filtroFecha(valor));
				case "Motivo":
					return recorrer(servicioCita.filtroMotivo(valor));
				default:
					return citas;
				}

			}
		};
	}

	public List<Cita> recorrer(List<Cita> lis) {
		for (int i = 0; i < lis.size(); i++) {
			String nombre = lis.get(i).getPaciente().getPrimerNombre();
			String apellido = lis.get(i).getPaciente().getPrimerApellido();
			Paciente paciente = lis.get(i).getPaciente();
			paciente.setHoraAuditoria(nombre + " " + apellido);
		}
		return lis;
	}

	/* Abre la vista de Motivo */
	@Listen("onClick = #btnAbrirMotivo")
	public void abrirMotivo() {
		List<Arbol> arboles = servicioArbol.buscarPorNombreArbol("Motivo");
		if (!arboles.isEmpty()) {
			Arbol arbolItem = arboles.get(0);
			cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
		}
	}
}
