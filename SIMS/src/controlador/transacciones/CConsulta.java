package controlador.transacciones;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.maestros.Diagnostico;
import modelo.maestros.Especialista;
import modelo.maestros.Examen;
import modelo.maestros.Medicina;
import modelo.maestros.Paciente;
import modelo.maestros.Recipe;
import modelo.maestros.ServicioExterno;
import modelo.seguridad.Usuario;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaDiagnostico;
import modelo.transacciones.ConsultaEspecialista;
import modelo.transacciones.ConsultaExamen;
import modelo.transacciones.ConsultaMedicina;
import modelo.transacciones.ConsultaServicioExterno;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublespinner;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;

import componentes.Botonera;
import componentes.Catalogo;

import controlador.maestros.CGenerico;

public class CConsulta extends CGenerico {

	private static final long serialVersionUID = -6277014704105198573L;
	@Wire
	private Datebox dtbFechaConsulta;
	@Wire
	private Timebox tmbHoraConsulta;
	@Wire
	private Textbox txtObservacion;
	@Wire
	private Combobox cmbPrioridad;
	@Wire
	private Datebox dtbValido;
	@Wire
	private Groupbox gpxResumen;
	@Wire
	private Groupbox gpxMedicina;
	@Wire
	private Groupbox gpxDiagnostico;
	@Wire
	private Groupbox gpxServicio;
	@Wire
	private Groupbox gpxExamen;
	@Wire
	private Groupbox gpxEspecialista;
	@Wire
	private Groupbox gpxServicioExterno;
	@Wire
	private Div botoneraConsulta;
	@Wire
	private Button btnBuscarPaciente;
	@Wire
	private Div divCatalogoPacientes;
	@Wire
	private Div divConsulta;
	@Wire
	private Textbox txtCedula;
	@Wire
	private Label lblNombre;
	@Wire
	private Label lblApellido;
	@Wire
	private Label lblEmpresa;
	@Wire
	private Label lblFicha;
	@Wire
	private Listbox ltbMedicinas;
	@Wire
	private Listbox ltbMedicinasAgregadas;
	@Wire
	private Listbox ltbExamenes;
	@Wire
	private Listbox ltbExamenesAgregados;
	@Wire
	private Listbox ltbDiagnosticos;
	@Wire
	private Listbox ltbDiagnosticosAgregados;
	@Wire
	private Listbox ltbServicioExterno;
	@Wire
	private Listbox ltbServicioExternoAgregados;
	@Wire
	private Listbox ltbEspecialistas;
	@Wire
	private Listbox ltbEspecialistasAgregados;
	@Wire
	private Listbox ltbResumenMedicinas;
	@Wire
	private Listbox ltbResumenDiagnosticos;
	@Wire
	private Listbox ltbResumenExamenes;
	@Wire
	private Listbox ltbResumenEspecialistas;
	@Wire
	private Listbox ltbResumenServicios;
	@Wire
	private Listbox ltbCargaFamiliar;
	@Wire
	private Listbox ltbConsultas;

	List<Listbox> listas = new ArrayList<Listbox>();

	List<Medicina> medicinasDisponibles = new ArrayList<Medicina>();
	List<ConsultaMedicina> medicinasAgregadas = new ArrayList<ConsultaMedicina>();
	List<ConsultaMedicina> medicinasResumen = new ArrayList<ConsultaMedicina>();

	List<Diagnostico> diagnosticosDisponibles = new ArrayList<Diagnostico>();
	List<ConsultaDiagnostico> diagnosticosAgregados = new ArrayList<ConsultaDiagnostico>();
	List<ConsultaDiagnostico> diagnosticosResumen = new ArrayList<ConsultaDiagnostico>();

	List<Examen> examenesDisponibles = new ArrayList<Examen>();
	List<ConsultaExamen> examenesAgregado = new ArrayList<ConsultaExamen>();
	List<ConsultaExamen> examenesResumen = new ArrayList<ConsultaExamen>();

	List<Especialista> especialistasDisponibles = new ArrayList<Especialista>();
	List<ConsultaEspecialista> especialistasAgregados = new ArrayList<ConsultaEspecialista>();
	List<ConsultaEspecialista> especialistasResumen = new ArrayList<ConsultaEspecialista>();

	List<ServicioExterno> serviciosDisponibles = new ArrayList<ServicioExterno>();
	List<ConsultaServicioExterno> serviciosAgregados = new ArrayList<ConsultaServicioExterno>();
	List<ConsultaServicioExterno> serviciosResumen = new ArrayList<ConsultaServicioExterno>();
	long idPaciente = 0;
	long idConsulta = 0;
	Catalogo<Paciente> catalogoPaciente;

	@Override
	public void inicializar() throws IOException {

		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divConsulta, "Consulta");

			}

			@Override
			public void limpiar() {
				// TODO Auto-generated method stub

			}

			@Override
			public void guardar() {
				if (validar()) {
					if (idConsulta != 0) {
						Consulta consulta = servicioConsulta.buscar(idConsulta);
						servicioConsultaExamen
								.borrarExamenesDeConsulta(consulta);
						servicioConsultaMedicina
								.borrarMedicinasDeConsulta(consulta);
						servicioConsultaDiagnostico
								.borrarDiagnosticosDeConsulta(consulta);
						servicioConsultaEspecialista
								.borrarEspecialistasDeConsulta(consulta);
						servicioConsultaServicioExterno
								.borrarServiciosDeConsulta(consulta);
					}
					Date hConsulta = tmbHoraConsulta.getValue();
					Date fechaCon = dtbFechaConsulta.getValue();
					Timestamp fechaConsulta = new Timestamp(fechaCon.getTime());
					String horaConsulta = df.format(hConsulta);
					String observacion = txtObservacion.getValue();
					Usuario usuario = null;
					Paciente paciente = servicioPaciente
							.buscarPorCedula(txtCedula.getValue());
					Consulta consulta = new Consulta(idConsulta, paciente,
							usuario, fechaConsulta, horaConsulta, observacion,
							horaAuditoria, fechaHora, nombreUsuarioSesion());
					servicioConsulta.guardar(consulta);
					Consulta consultaDatos = new Consulta();
					if (idConsulta != 0)
						consultaDatos = servicioConsulta.buscar(idConsulta);
					else
						consultaDatos = servicioConsulta.buscarUltima();
					guardarMedicinas(consultaDatos);
					guardarDiagnosticos(consultaDatos);
					guardarExamenes(consultaDatos);
					guardarEspecialistas(consultaDatos);
					guardarServicios(consultaDatos);
					limpiar();
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
				}
			}

			@Override
			public void eliminar() {
				// TODO Auto-generated method stub

			}
		};

		botonera.getChildren().get(1).setVisible(false);
		botoneraConsulta.appendChild(botonera);

	}

	public void guardarServicios(Consulta consultaDatos) {
		List<ConsultaServicioExterno> listaServicioExterno = new ArrayList<ConsultaServicioExterno>();
		for (int i = 0; i < ltbServicioExternoAgregados.getItemCount(); i++) {
			Listitem listItem = ltbServicioExternoAgregados.getItemAtIndex(i);
			Integer id = ((Spinner) ((listItem.getChildren().get(2)))
					.getFirstChild()).getValue();
			ServicioExterno servicioExterno = servicioServicioExterno
					.buscar(id);
			double valor = ((Doublespinner) ((listItem.getChildren().get(1)))
					.getFirstChild()).getValue();
			ConsultaServicioExterno consultaServicio = new ConsultaServicioExterno(
					consultaDatos, servicioExterno, valor);
			listaServicioExterno.add(consultaServicio);
		}
		servicioConsultaServicioExterno.guardar(listaServicioExterno);
	}

	public void guardarEspecialistas(Consulta consultaDatos) {
		List<ConsultaEspecialista> listaConsultaEspecialista = new ArrayList<ConsultaEspecialista>();
		for (int i = 0; i < ltbEspecialistasAgregados.getItemCount(); i++) {
			Listitem listItem = ltbEspecialistasAgregados.getItemAtIndex(i);
			Integer id = ((Spinner) ((listItem.getChildren().get(2)))
					.getFirstChild()).getValue();
			Especialista especialista = servicioEspecialista.buscar(String
					.valueOf(id));
			double valor = ((Doublespinner) ((listItem.getChildren().get(1)))
					.getFirstChild()).getValue();
			ConsultaEspecialista consultaEspecialista = new ConsultaEspecialista(
					consultaDatos, especialista, valor);
			listaConsultaEspecialista.add(consultaEspecialista);
		}
		servicioConsultaEspecialista.guardar(listaConsultaEspecialista);
	}

	public void guardarExamenes(Consulta consultaDatos) {
		List<ConsultaExamen> listaConsultaExamen = new ArrayList<ConsultaExamen>();
		for (int i = 0; i < ltbExamenesAgregados.getItemCount(); i++) {
			Listitem listItem = ltbExamenesAgregados.getItemAtIndex(i);
			Integer idExamen = ((Spinner) ((listItem.getChildren().get(2)))
					.getFirstChild()).getValue();
			Examen examen = servicioExamen.buscar(idExamen);
			String valor = ((Textbox) ((listItem.getChildren().get(1)))
					.getFirstChild()).getValue();
			ConsultaExamen consultaExamen = new ConsultaExamen(consultaDatos,
					examen, valor);
			listaConsultaExamen.add(consultaExamen);
		}
		servicioConsultaExamen.guardar(listaConsultaExamen);
	}

	public void guardarDiagnosticos(Consulta consultaDatos) {
		List<ConsultaDiagnostico> listaDiagnostico = new ArrayList<ConsultaDiagnostico>();
		for (int i = 0; i < ltbDiagnosticosAgregados.getItemCount(); i++) {
			Listitem listItem = ltbDiagnosticosAgregados.getItemAtIndex(i);
			Integer idDiagnostico = ((Spinner) ((listItem.getChildren().get(2)))
					.getFirstChild()).getValue();
			Diagnostico diagnostico = servicioDiagnostico.buscar(idDiagnostico);
			String valor = ((Textbox) ((listItem.getChildren().get(1)))
					.getFirstChild()).getValue();
			ConsultaDiagnostico consultaDiagnostico = new ConsultaDiagnostico(
					consultaDatos, diagnostico, valor);
			listaDiagnostico.add(consultaDiagnostico);
		}
		servicioConsultaDiagnostico.guardar(listaDiagnostico);
	}

	public void guardarMedicinas(Consulta consultaDatos) {
		Date vali = dtbValido.getValue();
		Timestamp validez = new Timestamp(vali.getTime());
		Recipe recipe = new Recipe(0, cmbPrioridad.getValue(), validez,
				fechaHora, horaAuditoria, nombreUsuarioSesion());
		servicioRecipe.guardar(recipe);
		recipe = servicioRecipe.buscarUltimo();
		List<ConsultaMedicina> listaMedicina = new ArrayList<ConsultaMedicina>();
		for (int i = 0; i < ltbMedicinasAgregadas.getItemCount(); i++) {
			Listitem listItem = ltbMedicinasAgregadas.getItemAtIndex(i);
			Integer idMedicina = ((Spinner) ((listItem.getChildren().get(2)))
					.getFirstChild()).getValue();
			Medicina medicina = servicioMedicina.buscar(idMedicina);
			String valor = ((Textbox) ((listItem.getChildren().get(1)))
					.getFirstChild()).getValue();
			ConsultaMedicina consultaMedicina = new ConsultaMedicina(
					consultaDatos, medicina, valor, recipe);
			listaMedicina.add(consultaMedicina);
		}
		servicioConsultaMedicina.guardar(listaMedicina);
	}

	public boolean validar() {
		if (txtCedula.getText().compareTo("") == 0) {
			Messagebox.show("Debe Seleccionar un Paciente", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else {
			if (dtbFechaConsulta.getText().compareTo("") == 0
					|| tmbHoraConsulta.getText().compareTo("") == 0
					|| txtObservacion.getText().compareTo("") == 0
					|| cmbPrioridad.getText().compareTo("") == 0
					|| dtbValido.getText().compareTo("") == 0) {
				Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
						Messagebox.OK, Messagebox.INFORMATION);
				return false;
			} else {
				if (!agregarMedicina()) {
					Messagebox
							.show("Debe Llenar Todos los Campos de la Lista de Medicinas",
									"Informacion", Messagebox.OK,
									Messagebox.INFORMATION);
					return false;
				} else {
					if (!agregarDiagnostico()) {
						Messagebox
								.show("Debe Llenar Todos los Campos de la Lista de Diagnosticos",
										"Informacion", Messagebox.OK,
										Messagebox.INFORMATION);
						return false;
					} else {
						if (!agregarExamen()) {
							Messagebox
									.show("Debe Llenar Todos los Campos de la Lista de Examenes",
											"Informacion", Messagebox.OK,
											Messagebox.INFORMATION);
							return false;
						} else {
							if (!agregarEspecialista()) {
								Messagebox
										.show("Debe Llenar Todos los Campos de la Lista de Especialistas",
												"Informacion", Messagebox.OK,
												Messagebox.INFORMATION);
								return false;
							} else {
								if (!agregarServicio()) {
									Messagebox
											.show("Debe Llenar Todos los Campos de la Lista de Servicios Externos",
													"Informacion",
													Messagebox.OK,
													Messagebox.INFORMATION);
									return false;
								} else
									return true;
							}
						}
					}
				}
			}
		}
	}

	/* Llena la listas al iniciar con todo lo existente */
	private void llenarListas() {
		Consulta consulta = servicioConsulta.buscar(idConsulta);
		List<Paciente> carga = servicioPaciente.buscarParientes(String
				.valueOf(idPaciente));
		ltbCargaFamiliar.setModel(new ListModelList<Paciente>(carga));

		medicinasDisponibles = servicioMedicina.buscarDisponibles(consulta);
		ltbMedicinas
				.setModel(new ListModelList<Medicina>(medicinasDisponibles));
		medicinasAgregadas = servicioConsultaMedicina
				.buscarPorConsulta(consulta);
		ltbMedicinasAgregadas.setModel(new ListModelList<ConsultaMedicina>(
				medicinasAgregadas));
		medicinasResumen = medicinasAgregadas;
		ltbResumenMedicinas.setModel(new ListModelList<ConsultaMedicina>(
				medicinasResumen));

		diagnosticosDisponibles = servicioDiagnostico
				.buscarDisponibles(consulta);
		ltbDiagnosticos.setModel(new ListModelList<Diagnostico>(
				diagnosticosDisponibles));
		diagnosticosAgregados = servicioConsultaDiagnostico
				.buscarPorConsulta(consulta);
		ltbDiagnosticosAgregados
				.setModel(new ListModelList<ConsultaDiagnostico>(
						diagnosticosAgregados));
		diagnosticosResumen = diagnosticosAgregados;
		ltbResumenDiagnosticos.setModel(new ListModelList<ConsultaDiagnostico>(
				diagnosticosResumen));

		examenesDisponibles = servicioExamen.buscarDisponibles(consulta);
		ltbExamenes.setModel(new ListModelList<Examen>(examenesDisponibles));
		examenesAgregado = servicioConsultaExamen.buscarPorConsulta(consulta);
		ltbExamenesAgregados.setModel(new ListModelList<ConsultaExamen>(
				examenesAgregado));
		examenesResumen = examenesAgregado;
		ltbResumenExamenes.setModel(new ListModelList<ConsultaExamen>(
				examenesResumen));

		especialistasDisponibles = servicioEspecialista
				.buscarDisponibles(consulta);
		ltbEspecialistas.setModel(new ListModelList<Especialista>(
				especialistasDisponibles));
		especialistasAgregados = servicioConsultaEspecialista
				.buscarPorConsulta(consulta);
		ltbEspecialistasAgregados
				.setModel(new ListModelList<ConsultaEspecialista>(
						especialistasAgregados));
		especialistasResumen = especialistasAgregados;
		ltbResumenEspecialistas
				.setModel(new ListModelList<ConsultaEspecialista>(
						especialistasResumen));

		serviciosDisponibles = servicioServicioExterno
				.buscarDisponibles(consulta);
		ltbServicioExterno.setModel(new ListModelList<ServicioExterno>(
				serviciosDisponibles));
		serviciosAgregados = servicioConsultaServicioExterno
				.buscarPorConsulta(consulta);
		ltbServicioExternoAgregados
				.setModel(new ListModelList<ConsultaServicioExterno>(
						serviciosAgregados));
		serviciosResumen = serviciosAgregados;
		ltbResumenServicios
				.setModel(new ListModelList<ConsultaServicioExterno>(
						serviciosResumen));

		listasMultiples();
	}

	private void listasMultiples() {
		if (listas.isEmpty()) {
			listas.add(ltbDiagnosticos);
			listas.add(ltbDiagnosticosAgregados);
			listas.add(ltbEspecialistas);
			listas.add(ltbEspecialistasAgregados);
			listas.add(ltbExamenes);
			listas.add(ltbExamenesAgregados);
			listas.add(ltbMedicinas);
			listas.add(ltbMedicinasAgregadas);
			listas.add(ltbServicioExterno);
			listas.add(ltbServicioExternoAgregados);
		}
		for (int i = 0; i < listas.size(); i++) {
			listas.get(i).setMultiple(false);
			listas.get(i).setCheckmark(false);
			listas.get(i).setMultiple(true);
			listas.get(i).setCheckmark(true);
		}
	}

	/* Muestra un catalogo de Pacientes */
	@Listen("onClick = #btnBuscarPaciente")
	public void mostrarCatalogoPaciente() throws IOException {
		final List<Paciente> pacientes = servicioPaciente.buscarTodos();
		catalogoPaciente = new Catalogo<Paciente>(divCatalogoPacientes,
				"Catalogo de Pacientes", pacientes, "Cedula", "Nombre",
				"Apellido", "Empresa") {

			@Override
			protected List<Paciente> buscar(String valor, String combo) {

				switch (combo) {
				case "Nombre":
					return servicioPaciente.filtroNombre1(valor);
				case "Cedula":
					return servicioPaciente.filtroCedula(valor);
				case "Apellido":
					return servicioPaciente.filtroApellido1(valor);
				case "Empresa":
					return servicioPaciente.filtroEmpresa(valor);
				default:
					return pacientes;
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

	@Listen("onClick = #ltbConsultas")
	public void seleccionarConsulta() {
		if (ltbConsultas.getItemCount() != 0) {
			Listitem listItem = ltbConsultas.getSelectedItem();
			Consulta consulta = listItem.getValue();
			idConsulta = consulta.getIdConsulta();
			idPaciente = Long.parseLong(consulta.getPaciente().getCedula());
			llenarCampos(consulta.getPaciente());
			llenarListas();
		}
	}

	private void llenarCampos(Paciente paciente) {
		// TODO Auto-generated method stub
		txtCedula.setValue(paciente.getCedula());
		lblNombre.setValue(paciente.getPrimerNombre() + " "
				+ paciente.getSegundoNombre());
		lblApellido.setValue(paciente.getPrimerApellido() + " "
				+ paciente.getSegundoApellido());
		lblEmpresa.setValue(paciente.getEmpresa().getNombre());
		txtCedula.setDisabled(true);
	}

	/* Permite la seleccion de un item del catalogo de pacientes */
	@Listen("onSeleccion = #divCatalogoPacientes")
	public void seleccionarPaciente() {
		Paciente paciente = catalogoPaciente.objetoSeleccionadoDelCatalogo();
		llenarCampos(paciente);
		idPaciente = Long.valueOf(paciente.getCedula());
		List<Consulta> consultas = servicioConsulta.buscarPorPaciente(paciente);
		ltbConsultas.setModel(new ListModelList<Consulta>(consultas));
		llenarListas();
		catalogoPaciente.setParent(null);
	}

	@Listen("onClick = #pasar1Medicina")
	public void derechaMedicina() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem = ltbMedicinas.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					Medicina Medicina = listItem.get(i).getValue();
					medicinasDisponibles.remove(Medicina);
					ConsultaMedicina consultaMedicina = new ConsultaMedicina();
					consultaMedicina.setMedicina(Medicina);
					medicinasAgregadas.add(consultaMedicina);
					ltbMedicinasAgregadas
							.setModel(new ListModelList<ConsultaMedicina>(
									medicinasAgregadas));
					listitemEliminar.add(listItem.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbMedicinas.removeItemAt(listitemEliminar.get(i).getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar2Medicina")
	public void izquierdaMedicina() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem2 = ltbMedicinasAgregadas.getItems();
		if (listItem2.size() != 0) {
			for (int i = 0; i < listItem2.size(); i++) {
				if (listItem2.get(i).isSelected()) {
					ConsultaMedicina consultaMedicina = listItem2.get(i)
							.getValue();
					medicinasAgregadas.remove(consultaMedicina);
					medicinasDisponibles.add(consultaMedicina.getMedicina());
					ltbMedicinas.setModel(new ListModelList<Medicina>(
							medicinasDisponibles));
					listitemEliminar.add(listItem2.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbMedicinasAgregadas.removeItemAt(listitemEliminar.get(i)
					.getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar1Diagnostico")
	public void derechaDiagnostico() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem = ltbDiagnosticos.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					Diagnostico diagnostico = listItem.get(i).getValue();
					diagnosticosDisponibles.remove(diagnostico);
					ConsultaDiagnostico consultaDiagnostico = new ConsultaDiagnostico();
					consultaDiagnostico.setDiagnostico(diagnostico);
					diagnosticosAgregados.add(consultaDiagnostico);
					ltbDiagnosticosAgregados
							.setModel(new ListModelList<ConsultaDiagnostico>(
									diagnosticosAgregados));
					listitemEliminar.add(listItem.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbDiagnosticos.removeItemAt(listitemEliminar.get(i).getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar2Diagnostico")
	public void izquierdaDiagnostico() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem2 = ltbDiagnosticosAgregados.getItems();
		if (listItem2.size() != 0) {
			for (int i = 0; i < listItem2.size(); i++) {
				if (listItem2.get(i).isSelected()) {
					ConsultaDiagnostico consultaDiagnostico = listItem2.get(i)
							.getValue();
					diagnosticosAgregados.remove(consultaDiagnostico);
					diagnosticosDisponibles.add(consultaDiagnostico
							.getDiagnostico());
					ltbDiagnosticos.setModel(new ListModelList<Diagnostico>(
							diagnosticosDisponibles));
					listitemEliminar.add(listItem2.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbDiagnosticosAgregados.removeItemAt(listitemEliminar.get(i)
					.getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar1Examen")
	public void derechaExamen() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem = ltbExamenes.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					Examen examen = listItem.get(i).getValue();
					examenesDisponibles.remove(examen);
					ConsultaExamen consultaExamen = new ConsultaExamen();
					consultaExamen.setExamen(examen);
					examenesAgregado.add(consultaExamen);
					ltbExamenesAgregados
							.setModel(new ListModelList<ConsultaExamen>(
									examenesAgregado));
					listitemEliminar.add(listItem.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbExamenes.removeItemAt(listitemEliminar.get(i).getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar2Examen")
	public void izquierdaExamen() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem2 = ltbExamenesAgregados.getItems();
		if (listItem2.size() != 0) {
			for (int i = 0; i < listItem2.size(); i++) {
				if (listItem2.get(i).isSelected()) {
					ConsultaExamen consultaExamen = listItem2.get(i).getValue();
					examenesAgregado.remove(consultaExamen);
					examenesDisponibles.add(consultaExamen.getExamen());
					ltbExamenes.setModel(new ListModelList<Examen>(
							examenesDisponibles));
					listitemEliminar.add(listItem2.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbExamenesAgregados.removeItemAt(listitemEliminar.get(i)
					.getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar1Especialista")
	public void derechaEspecialista() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem = ltbEspecialistas.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					Especialista especialista = listItem.get(i).getValue();
					especialistasDisponibles.remove(especialista);
					ConsultaEspecialista consultaEspecialista = new ConsultaEspecialista();
					consultaEspecialista.setEspecialista(especialista);
					especialistasAgregados.add(consultaEspecialista);
					ltbEspecialistasAgregados
							.setModel(new ListModelList<ConsultaEspecialista>(
									especialistasAgregados));
					listitemEliminar.add(listItem.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbEspecialistas.removeItemAt(listitemEliminar.get(i).getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar2Especialista")
	public void izquierdaEspecialista() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem2 = ltbEspecialistasAgregados.getItems();
		if (listItem2.size() != 0) {
			for (int i = 0; i < listItem2.size(); i++) {
				if (listItem2.get(i).isSelected()) {
					ConsultaEspecialista consultaEspecialista = listItem2
							.get(i).getValue();
					especialistasAgregados.remove(consultaEspecialista);
					especialistasDisponibles.add(consultaEspecialista
							.getEspecialista());
					ltbEspecialistas.setModel(new ListModelList<Especialista>(
							especialistasDisponibles));
					listitemEliminar.add(listItem2.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbEspecialistasAgregados.removeItemAt(listitemEliminar.get(i)
					.getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar1ServicioExterno")
	public void derechaServicioExterno() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem = ltbServicioExterno.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					ServicioExterno servicio = listItem.get(i).getValue();
					serviciosDisponibles.remove(servicio);
					ConsultaServicioExterno consultaServicio = new ConsultaServicioExterno();
					consultaServicio.setServicioExterno(servicio);
					serviciosAgregados.add(consultaServicio);
					ltbServicioExternoAgregados
							.setModel(new ListModelList<ConsultaServicioExterno>(
									serviciosAgregados));
					listitemEliminar.add(listItem.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbServicioExterno.removeItemAt(listitemEliminar.get(i).getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar2ServicioExterno")
	public void izquierdaServicioExterno() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem2 = ltbServicioExternoAgregados.getItems();
		if (listItem2.size() != 0) {
			for (int i = 0; i < listItem2.size(); i++) {
				if (listItem2.get(i).isSelected()) {
					ConsultaServicioExterno consultaServicio = listItem2.get(i)
							.getValue();
					serviciosAgregados.remove(consultaServicio);
					serviciosDisponibles.add(consultaServicio
							.getServicioExterno());
					ltbServicioExterno
							.setModel(new ListModelList<ServicioExterno>(
									serviciosDisponibles));
					listitemEliminar.add(listItem2.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbServicioExternoAgregados.removeItemAt(listitemEliminar.get(i)
					.getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #btnAgregarMedicinas")
	public boolean agregarMedicina() {
		boolean falta = false;
		medicinasResumen.clear();
		if (ltbMedicinasAgregadas.getItemCount() != 0) {
			ConsultaMedicina consultaMedicina = new ConsultaMedicina();
			List<Listitem> listItem2 = ltbMedicinasAgregadas.getItems();
			for (int i = 0; i < ltbMedicinasAgregadas.getItemCount(); i++) {
				Listitem listItem = ltbMedicinasAgregadas.getItemAtIndex(i);
				consultaMedicina = new ConsultaMedicina();
				consultaMedicina = listItem2.get(i).getValue();
				String valor = ((Textbox) ((listItem.getChildren().get(1)))
						.getFirstChild()).getValue();
				if (valor.equals("")) {
					falta = true;
				}
				consultaMedicina.setDosis(valor);
				medicinasResumen.add(consultaMedicina);
			}
			ltbResumenMedicinas.setModel(new ListModelList<ConsultaMedicina>(
					medicinasResumen));
		}
		medicinasResumen.clear();
		if (falta)
			return false;
		else
			return true;
	}

	@Listen("onClick = #btnAgregarDiagnosticos")
	public boolean agregarDiagnostico() {
		boolean falta = false;
		diagnosticosResumen.clear();
		if (ltbDiagnosticosAgregados.getItemCount() != 0) {
			ConsultaDiagnostico consultaDiagnostico = new ConsultaDiagnostico();
			List<Listitem> listItem2 = ltbDiagnosticosAgregados.getItems();
			for (int i = 0; i < ltbDiagnosticosAgregados.getItemCount(); i++) {
				Listitem listItem = ltbDiagnosticosAgregados.getItemAtIndex(i);
				consultaDiagnostico = new ConsultaDiagnostico();
				consultaDiagnostico = listItem2.get(i).getValue();
				String valor = ((Textbox) ((listItem.getChildren().get(1)))
						.getFirstChild()).getValue();
				if (valor.equals("")) {
					falta = true;
				}
				consultaDiagnostico.setObservacion(valor);
				diagnosticosResumen.add(consultaDiagnostico);
			}
			ltbResumenDiagnosticos
					.setModel(new ListModelList<ConsultaDiagnostico>(
							diagnosticosResumen));
		}
		diagnosticosResumen.clear();
		if (falta)
			return false;
		else
			return true;
	}

	@Listen("onClick = #btnAgregarExamenes")
	public boolean agregarExamen() {
		boolean falta = false;
		examenesResumen.clear();
		if (ltbExamenesAgregados.getItemCount() != 0) {
			ConsultaExamen consulta = new ConsultaExamen();
			List<Listitem> listItem2 = ltbExamenesAgregados.getItems();
			for (int i = 0; i < ltbExamenesAgregados.getItemCount(); i++) {
				Listitem listItem = ltbExamenesAgregados.getItemAtIndex(i);
				consulta = new ConsultaExamen();
				consulta = listItem2.get(i).getValue();
				String valor = ((Textbox) ((listItem.getChildren().get(1)))
						.getFirstChild()).getValue();
				if (valor.equals("")) {
					falta = true;
				}
				consulta.setObservacion(valor);
				examenesResumen.add(consulta);
			}
			ltbResumenExamenes.setModel(new ListModelList<ConsultaExamen>(
					examenesResumen));
		}
		examenesResumen.clear();
		if (falta)
			return false;
		else
			return true;
	}

	@Listen("onClick = #btnAgregarEspecialistas")
	public boolean agregarEspecialista() {
		boolean falta = false;
		especialistasResumen.clear();
		if (ltbEspecialistasAgregados.getItemCount() != 0) {
			ConsultaEspecialista consulta = new ConsultaEspecialista();
			List<Listitem> listItem2 = ltbEspecialistasAgregados.getItems();
			for (int i = 0; i < ltbEspecialistasAgregados.getItemCount(); i++) {
				Listitem listItem = ltbEspecialistasAgregados.getItemAtIndex(i);
				consulta = new ConsultaEspecialista();
				consulta = listItem2.get(i).getValue();
				double valor = ((Doublespinner) ((listItem.getChildren().get(1)))
						.getFirstChild()).getValue();
				if (valor == 0) {
					falta = true;
				}
				consulta.setCosto(valor);
				especialistasResumen.add(consulta);
			}
			ltbResumenEspecialistas
					.setModel(new ListModelList<ConsultaEspecialista>(
							especialistasResumen));
		}
		examenesResumen.clear();
		if (falta)
			return false;
		else
			return true;
	}

	@Listen("onClick = #btnAgregarServicios")
	public boolean agregarServicio() {
		boolean falta = false;
		serviciosResumen.clear();
		if (ltbServicioExternoAgregados.getItemCount() != 0) {
			ConsultaServicioExterno consulta = new ConsultaServicioExterno();
			List<Listitem> listItem2 = ltbServicioExternoAgregados.getItems();
			for (int i = 0; i < ltbServicioExternoAgregados.getItemCount(); i++) {
				Listitem listItem = ltbServicioExternoAgregados
						.getItemAtIndex(i);
				consulta = new ConsultaServicioExterno();
				consulta = listItem2.get(i).getValue();
				double valor = ((Doublespinner) ((listItem.getChildren().get(1)))
						.getFirstChild()).getValue();
				if (valor == 0) {
					falta = true;
				}
				consulta.setCosto(valor);
				serviciosResumen.add(consulta);
			}
			ltbResumenServicios
					.setModel(new ListModelList<ConsultaServicioExterno>(
							serviciosResumen));
		}
		serviciosResumen.clear();
		if (falta)
			return false;
		else
			return true;
	}

	@Listen("onClick = #gpxResumen")
	public void abrirResumen() {
		gpxResumen.setOpen(true);
		gpxDiagnostico.setOpen(false);
		gpxEspecialista.setOpen(false);
		gpxExamen.setOpen(false);
		gpxMedicina.setOpen(false);
		gpxServicio.setOpen(false);
		gpxServicioExterno.setOpen(false);
	}

	@Listen("onClick = #gpxMedicina")
	public void abrirMedicina() {
		gpxResumen.setOpen(false);
		gpxDiagnostico.setOpen(false);
		gpxEspecialista.setOpen(false);
		gpxExamen.setOpen(false);
		gpxMedicina.setOpen(true);
		gpxServicio.setOpen(false);
		gpxServicioExterno.setOpen(false);
	}

	@Listen("onClick = #gpxDiagnostico")
	public void abrirDiagnostico() {
		gpxResumen.setOpen(false);
		gpxDiagnostico.setOpen(true);
		gpxEspecialista.setOpen(false);
		gpxExamen.setOpen(false);
		gpxMedicina.setOpen(false);
		gpxServicio.setOpen(false);
		gpxServicioExterno.setOpen(false);
	}

	@Listen("onClick = #gpxServicio")
	public void abrirServicio() {
		gpxResumen.setOpen(false);
		gpxDiagnostico.setOpen(false);
		gpxEspecialista.setOpen(false);
		gpxExamen.setOpen(false);
		gpxMedicina.setOpen(false);
		gpxServicio.setOpen(true);
		gpxServicioExterno.setOpen(false);
	}

	@Listen("onClick = #gpxExamen")
	public void abrirExamen() {
		gpxResumen.setOpen(false);
		gpxDiagnostico.setOpen(false);
		gpxEspecialista.setOpen(false);
		gpxExamen.setOpen(true);
		gpxMedicina.setOpen(false);
		gpxServicio.setOpen(true);
		gpxServicioExterno.setOpen(false);
	}

	@Listen("onClick = #gpxEspecialista")
	public void abrirEspecialista() {
		gpxResumen.setOpen(false);
		gpxDiagnostico.setOpen(false);
		gpxEspecialista.setOpen(true);
		gpxExamen.setOpen(false);
		gpxMedicina.setOpen(false);
		gpxServicio.setOpen(true);
		gpxServicioExterno.setOpen(false);
	}

	@Listen("onClick = #gpxServicioExterno")
	public void abrirServicioE() {
		gpxResumen.setOpen(false);
		gpxDiagnostico.setOpen(false);
		gpxEspecialista.setOpen(false);
		gpxExamen.setOpen(false);
		gpxMedicina.setOpen(false);
		gpxServicio.setOpen(true);
		gpxServicioExterno.setOpen(true);
	}

}
