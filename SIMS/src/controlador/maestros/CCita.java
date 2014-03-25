package controlador.maestros;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import modelo.maestros.Cita;
import modelo.maestros.MotivoCita;
import modelo.maestros.Paciente;
import modelo.seguridad.Usuario;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;

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
	private Combobox cmbMotivo;
	@Wire
	private Datebox dtbFechaCita;
	@Wire
	private Listbox ltbCitas;

	long id = 0;
	long idDoctor = 0;
	long idPaciente = 0;
	Catalogo<Usuario> catalogo;
	Catalogo<Paciente> catalogoPaciente;

	@Override
	public void inicializar() throws IOException {

		comboMotivo();

		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divCita, "Cita");

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
					Paciente paciente = servicioPaciente.buscar(idPaciente);
					Usuario usuario = servicioUsuario
							.buscarUsuarioPorId(idDoctor);
					String estado = "";

					Cita cita = new Cita(id, estado, fechaHora, fechaCrea,
							fechaHora, horaAuditoria, usuario, observacion,
							nombreUsuarioSesion(), motivo, paciente);

					servicioCita.guardar(cita);
					llenarListaCitas(usuario);
					limpiar2();
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
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
		lblCedulaPaciente.setValue("");
		lblNombrePaciente.setValue("");
		lblApellidoPaciente.setValue("");
		lblEmpresaPaciente.setValue("");
		txtObservacion.setValue("");
		dtbFechaCita.setValue(null);
		cmbMotivo.setValue("");
		cmbMotivo.setPlaceholder("Seleccione un Motivo");
		id = 0;
		idPaciente = 0;

	}

	/* Muestra un catalogo de Usuarios */
	@Listen("onClick = #btnBuscarDoctor")
	public void mostrarCatalogo() throws IOException {
		List<Usuario> usuarios = servicioUsuario.buscarTodos();
		catalogo = new Catalogo<Usuario>(catalogoUsuarios,
				"Catalogo de Doctores", usuarios, "Cedula", "Ficha", "Nombre",
				"Especialidad") {

			@Override
			protected List<Usuario> buscar(String valor, String combo) {
				if (combo.equals("Cedula"))
					return servicioUsuario.filtroCedula(valor);
				else {
					if (combo.equals("Ficha"))
						return servicioUsuario.filtroFicha(valor);
					else {
						if (combo.equals("Nombre"))
							return servicioUsuario.filtroNombre(valor);
						else {
							if (combo.equals("Especialidad"))
								return servicioUsuario
										.filtroEspecialidad(valor);
							else
								return servicioUsuario.buscarTodos();
						}
					}
				}
			}

			@Override
			protected String[] crearRegistros(Usuario objeto) {
				String[] registros = new String[4];
				registros[0] = objeto.getCedula();
				registros[1] = objeto.getFicha();
				registros[2] = objeto.getNombre();
				registros[3] = objeto.getEspecialidad().getDescripcion();
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
		lblApellidoDoctor.setValue(usuario.getEspecialidad().getDescripcion());
		lblNombreDoctor.setValue(usuario.getNombre());
		lblCedulaDoctor.setValue(usuario.getCedula());
		idDoctor = usuario.getIdUsuario();
		llenarListaCitas(usuario);
		limpiar2();
		catalogo.setParent(null);
	}

	/* Llena la lista de citas segun un usuario determinado */
	public void llenarListaCitas(Usuario usuario) {
		List<Cita> citasDoctor = servicioCita.buscarPorUsuario(usuario);

		for (int i = 0; i < citasDoctor.size(); i++) {

			String nombre = citasDoctor.get(i).getPaciente().getPrimerNombre();
			String apellido = citasDoctor.get(i).getPaciente()
					.getPrimerApellido();
			Paciente paciente = citasDoctor.get(i).getPaciente();
			paciente.setHoraAuditoria(nombre + " " + apellido);
		}
		ltbCitas.setModel(new ListModelList<Cita>(citasDoctor));
	}

	/* Muestra un catalogo de Pacientes */
	@Listen("onClick = #btnBuscarPaciente")
	public void mostrarCatalogoPaciente() throws IOException {
		List<Paciente> pacientes = servicioPaciente.buscarTodos();
		catalogoPaciente = new Catalogo<Paciente>(divCatalogoPacientes,
				"Catalogo de Pacientes", pacientes, "Cedula", "Nombre",
				"Apellido", "Empresa") {

			@Override
			protected List<Paciente> buscar(String valor, String combo) {

				if (combo.equals("Nombre"))
					return servicioPaciente.filtroNombre1(valor);
				else {
					if (combo.equals("Cedula"))
						return servicioPaciente.filtroCedula(valor);
					else {
						if (combo.equals("Apellido"))
							return servicioPaciente.filtroApellido1(valor);
						else {
							if (combo.equals("Empresa"))
								return servicioPaciente.filtroEmpresa(valor);
							else
								return servicioPaciente.buscarTodos();
						}
					}
				}
			}

			@Override
			protected String[] crearRegistros(Paciente objeto) {
				String[] registros = new String[4];
				registros[0] = objeto.getCedula();
				registros[1] = objeto.getPrimerNombre();
				registros[2] = objeto.getPrimerApellido();
				registros[3] = objeto.getEmpresa().getNombre();
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
		lblCedulaPaciente.setValue(paciente.getCedula());
		lblNombrePaciente.setValue(paciente.getPrimerNombre());
		lblApellidoPaciente.setValue(paciente.getPrimerApellido());
		lblEmpresaPaciente.setValue(paciente.getEmpresa().getNombre());
		idPaciente = paciente.getIdPaciente();
		catalogoPaciente.setParent(null);
	}

	/* Llena el combo de Motivos */
	public void comboMotivo() {
		List<MotivoCita> motivoCitas = servicioMotivoCita.buscarTodos();
		cmbMotivo.setModel(new ListModelList<MotivoCita>(motivoCitas));
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtObservacion.getText().compareTo("") == 0
				|| cmbMotivo.getText().compareTo("") == 0
				|| dtbFechaCita.getText().compareTo("") == 0 || idDoctor == 0
				|| idPaciente == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
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

}
