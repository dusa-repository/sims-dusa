package controlador.transacciones;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import modelo.control.ControlConsulta;
import modelo.control.ControlOrden;
import modelo.maestros.Cita;
import modelo.maestros.Paciente;
import modelo.maestros.PacienteAntecedente;
import modelo.maestros.PeriodoPaciente;
import modelo.sha.Informe;
import modelo.transacciones.Consulta;
import modelo.transacciones.Historia;
import modelo.transacciones.Orden;
import modelo.transacciones.PacienteMedicina;

import org.springframework.util.SerializationUtils;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;
import controlador.maestros.CGenerico;

public class CCambiarCedula extends CGenerico {

	private static final long serialVersionUID = 6205446277570693516L;
	@Wire
	private Div divCambiarCedula;
	@Wire
	private Div botoneraCambiarCedula;
	@Wire
	private Div divCatalogoPacientes;
	@Wire
	private Button btnBuscarPaciente;
	@Wire
	private Textbox txtCedulaPaciente;
	@Wire
	private Textbox txtCedulaPaciente2;
	@Wire
	private Label lblNombrePaciente;
	@Wire
	private Label lblApellidoPaciente;
	private String idPaciente = null;
	Catalogo<Paciente> catalogoPaciente;
	private String nombre;

	@Override
	public void inicializar() throws IOException {
		contenido = (Include) divCambiarCedula.getParent();
		Tabbox tabox = (Tabbox) divCambiarCedula.getParent().getParent()
				.getParent().getParent();
		tabBox = tabox;
		tab = (Tab) tabox.getTabs().getLastChild();
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
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divCambiarCedula, nombre, tabs);
			}

			@Override
			public void limpiar() {
				limpiarCampos();
			}

			@Override
			public void guardar() {
				if (validar()) {
					String nuevaCedula = txtCedulaPaciente2.getValue();
					Paciente paciente = servicioPaciente
							.buscarPorCedula(nuevaCedula);
					if (paciente == null) {
						paciente = servicioPaciente.buscarPorCedula(idPaciente);
						modificarHistoriaPaciente(paciente, nuevaCedula);
						limpiarCampos();
						Mensaje.mensajeInformacion("Cedula Modificada Correctamente");
					} else
						Mensaje.mensajeInformacion("La Cedula que ingreso ya esta siendo utilizada por un Paciente");
				}

			}

			@Override
			public void eliminar() {
				// TODO Auto-generated method stub

			}
		};
		botonera.getChildren().get(1).setVisible(false);
		botoneraCambiarCedula.appendChild(botonera);

	}

	public void modificarHistoriaPaciente(Paciente pacienteAModificar,
			String nuevaCedula) {
		// informe a-m,
		// citas, ordenes, consulta ,
		// historia,pacienteAntecedente,pacienteMedicina,periodoPaciente,
		Paciente pacienteNuevo = new Paciente(nuevaCedula,
				pacienteAModificar.getFicha(),
				pacienteAModificar.getPrimerApellido(),
				pacienteAModificar.getPrimerNombre(),
				pacienteAModificar.getSegundoApellido(),
				pacienteAModificar.getSegundoNombre(),
				pacienteAModificar.isTrabajador(),
				pacienteAModificar.isDiscapacidad(),
				pacienteAModificar.isAlergia(), pacienteAModificar.isLentes(),
				pacienteAModificar.getFechaNacimiento(),
				pacienteAModificar.getLugarNacimiento(),
				pacienteAModificar.getSexo(),
				pacienteAModificar.getEdad(),
				pacienteAModificar.getGrupoSanguineo(),
				pacienteAModificar.getObservacionAlergias(),
				pacienteAModificar.getMano(), pacienteAModificar.getEstatura(),
				pacienteAModificar.getPeso(),
				pacienteAModificar.getOrigenDiscapacidad(),
				pacienteAModificar.getTipoDiscapacidad(),
				pacienteAModificar.getObservacionDiscapacidad(), fechaHora,
				horaAuditoria, nombreUsuarioSesion(),
				pacienteAModificar.getImagen(),
				pacienteAModificar.getDireccion(),
				pacienteAModificar.getEmail(),
				pacienteAModificar.getTelefono1(),
				pacienteAModificar.getTelefono2(),
				pacienteAModificar.getNombresEmergencia(),
				pacienteAModificar.getApellidosEmergencia(),
				pacienteAModificar.getParentescoEmergencia(),
				pacienteAModificar.getTelefono1Emergencia(),
				pacienteAModificar.getTelefono2Emergencia(),
				pacienteAModificar.getCedulaFamiliar(),
				pacienteAModificar.getParentescoFamiliar(),
				pacienteAModificar.getEmpresa(),
				pacienteAModificar.getCiudadVivienda(),
				pacienteAModificar.getCargoReal(),
				pacienteAModificar.getArea(), pacienteAModificar.getVisita(),
				pacienteAModificar.getFechaVisita(),
				pacienteAModificar.getResumenVisita(),
				pacienteAModificar.getBrigadista(),
				pacienteAModificar.getCronico());
		pacienteNuevo.setNacionalidad(pacienteAModificar.getNacionalidad());
		pacienteNuevo.setCarga(pacienteAModificar.getCarga());
		pacienteNuevo.setNivelEducativo(pacienteAModificar.getNivelEducativo());
		pacienteNuevo.setProfesion(pacienteAModificar.getProfesion());
		pacienteNuevo.setNroInpsasel(pacienteAModificar.getNroInpsasel());
		pacienteNuevo.setRetiroIVSS(pacienteAModificar.getRetiroIVSS());
		pacienteNuevo.setFechaIngreso(pacienteAModificar.getFechaIngreso());
		pacienteNuevo.setFechaInscripcionIVSS(pacienteAModificar
				.getFechaInscripcionIVSS());
		pacienteNuevo.setFechaEgreso(pacienteAModificar.getFechaEgreso());
		pacienteNuevo.setTurno(pacienteAModificar.getTurno());
		pacienteNuevo.setNomina(pacienteAModificar.getNomina());
		pacienteNuevo.setEstadoCivil(pacienteAModificar.getEstadoCivil());
		pacienteNuevo.setEstatus(pacienteAModificar.isEstatus());
		pacienteNuevo.setObservacionEstatus(pacienteAModificar
				.getObservacionEstatus());
		pacienteNuevo.setMuerte(pacienteAModificar.isMuerte());
		pacienteNuevo.setFechaMuerte(pacienteAModificar.getFechaMuerte());
		getServicioPaciente().guardar(pacienteNuevo);
		pacienteNuevo = getServicioPaciente().buscarPorCedula(nuevaCedula);
		if (pacienteNuevo.isTrabajador()) {
			List<Paciente> familiares = getServicioPaciente().buscarParientes(
					pacienteAModificar.getCedula());
			if (!familiares.isEmpty()) {
				for (int i = 0; i < familiares.size(); i++) {
					familiares.get(i).setCedulaFamiliar(
							pacienteNuevo.getCedula());
					getServicioPaciente().guardar(familiares.get(i));
				}
			}
		}
		List<Informe> informes = getServicioInforme().buscarPorPaciente(
				pacienteAModificar);
		if (!informes.isEmpty()) {
			for (Iterator<Informe> iterator = informes.iterator(); iterator
					.hasNext();) {
				Informe informe = (Informe) iterator.next();
				informe.setPacienteA(pacienteNuevo);
			}
			getServicioInforme().guardarVarios(informes);
		}
		informes = getServicioInforme().buscarPorPacienteB(pacienteAModificar);
		if (!informes.isEmpty()) {
			for (Iterator<Informe> iterator = informes.iterator(); iterator
					.hasNext();) {
				Informe informe = (Informe) iterator.next();
				informe.setPacienteB(pacienteNuevo);
			}
			getServicioInforme().guardarVarios(informes);
		}
		informes = getServicioInforme().buscarPorPacienteC(pacienteAModificar);
		if (!informes.isEmpty()) {
			for (Iterator<Informe> iterator = informes.iterator(); iterator
					.hasNext();) {
				Informe informe = (Informe) iterator.next();
				informe.setPacienteC(pacienteNuevo);
			}
			getServicioInforme().guardarVarios(informes);
		}
		informes = getServicioInforme().buscarPorPacienteD(pacienteAModificar);
		if (!informes.isEmpty()) {
			for (Iterator<Informe> iterator = informes.iterator(); iterator
					.hasNext();) {
				Informe informe = (Informe) iterator.next();
				informe.setPacienteD(pacienteNuevo);
			}
			getServicioInforme().guardarVarios(informes);
		}
		informes = getServicioInforme().buscarPorPacienteE(pacienteAModificar);
		if (!informes.isEmpty()) {
			for (Iterator<Informe> iterator = informes.iterator(); iterator
					.hasNext();) {
				Informe informe = (Informe) iterator.next();
				informe.setPacienteE(pacienteNuevo);
			}
			getServicioInforme().guardarVarios(informes);
		}
		informes = getServicioInforme().buscarPorPacienteF(pacienteAModificar);
		if (!informes.isEmpty()) {
			for (Iterator<Informe> iterator = informes.iterator(); iterator
					.hasNext();) {
				Informe informe = (Informe) iterator.next();
				informe.setPacienteF(pacienteNuevo);
			}
			getServicioInforme().guardarVarios(informes);
		}
		informes = getServicioInforme().buscarPorPacienteG(pacienteAModificar);
		if (!informes.isEmpty()) {
			for (Iterator<Informe> iterator = informes.iterator(); iterator
					.hasNext();) {
				Informe informe = (Informe) iterator.next();
				informe.setPacienteG(pacienteNuevo);
			}
			getServicioInforme().guardarVarios(informes);
		}
		informes = getServicioInforme().buscarPorPacienteH(pacienteAModificar);
		if (!informes.isEmpty()) {
			for (Iterator<Informe> iterator = informes.iterator(); iterator
					.hasNext();) {
				Informe informe = (Informe) iterator.next();
				informe.setPacienteH(pacienteNuevo);
			}
			getServicioInforme().guardarVarios(informes);
		}
		informes = getServicioInforme().buscarPorPacienteI(pacienteAModificar);
		if (!informes.isEmpty()) {
			for (Iterator<Informe> iterator = informes.iterator(); iterator
					.hasNext();) {
				Informe informe = (Informe) iterator.next();
				informe.setPacienteI(pacienteNuevo);
			}
			getServicioInforme().guardarVarios(informes);
		}
		informes = getServicioInforme().buscarPorPacienteJ(pacienteAModificar);
		if (!informes.isEmpty()) {
			for (Iterator<Informe> iterator = informes.iterator(); iterator
					.hasNext();) {
				Informe informe = (Informe) iterator.next();
				informe.setPacienteJ(pacienteNuevo);
			}
			getServicioInforme().guardarVarios(informes);
		}
		informes = getServicioInforme().buscarPorPacienteK(pacienteAModificar);
		if (!informes.isEmpty()) {
			for (Iterator<Informe> iterator = informes.iterator(); iterator
					.hasNext();) {
				Informe informe = (Informe) iterator.next();
				informe.setPacienteK(pacienteNuevo);
			}
			getServicioInforme().guardarVarios(informes);
		}
		informes = getServicioInforme().buscarPorPacienteL(pacienteAModificar);
		if (!informes.isEmpty()) {
			for (Iterator<Informe> iterator = informes.iterator(); iterator
					.hasNext();) {
				Informe informe = (Informe) iterator.next();
				informe.setPacienteL(pacienteNuevo);
			}
			getServicioInforme().guardarVarios(informes);
		}
		informes = getServicioInforme().buscarPorPacienteM(pacienteAModificar);
		if (!informes.isEmpty()) {
			for (Iterator<Informe> iterator = informes.iterator(); iterator
					.hasNext();) {
				Informe informe = (Informe) iterator.next();
				informe.setPacienteM(pacienteNuevo);
			}
			getServicioInforme().guardarVarios(informes);
		}
		List<PeriodoPaciente> periodos = getServicioPeriodoPaciente()
				.buscarPorPaciente(pacienteAModificar);
		getServicioPeriodoPaciente().limpiar(pacienteAModificar);
		if (!periodos.isEmpty()) {
			for (Iterator<PeriodoPaciente> iterator = periodos.iterator(); iterator
					.hasNext();) {
				PeriodoPaciente periodoPaciente = (PeriodoPaciente) iterator
						.next();
				periodoPaciente.setPaciente(pacienteNuevo);
			}
			getServicioPeriodoPaciente().guardarVarios(periodos);
		}

		List<ControlConsulta> controlConsulta = getServicioControlConsulta()
				.buscarPorPaciente(pacienteAModificar);
		getServicioControlConsulta().limpiar(pacienteAModificar);
		if (!controlConsulta.isEmpty()) {
			for (Iterator<ControlConsulta> iterator = controlConsulta.iterator(); iterator
					.hasNext();) {
				ControlConsulta periodoPaciente = (ControlConsulta) iterator
						.next();
				periodoPaciente.setPaciente(pacienteNuevo);
			}
			getServicioControlConsulta().guardarVarios(controlConsulta);
		}

		List<ControlOrden> controlOrden = getServicioControlOrden()
				.buscarPorPaciente(pacienteAModificar);
		getServicioControlOrden().limpiar(pacienteAModificar);
		if (!controlOrden.isEmpty()) {
			for (Iterator<ControlOrden> iterator = controlOrden.iterator(); iterator
					.hasNext();) {
				ControlOrden periodoPaciente = (ControlOrden) iterator
						.next();
				periodoPaciente.setPaciente(pacienteNuevo);
			}
			getServicioControlOrden().guardarVarios(controlOrden);
		}
		List<PacienteMedicina> medicinas = getServicioPacienteMedicina()
				.buscarPorPaciente(pacienteAModificar);
		getServicioPacienteMedicina().limpiarMedicinas(pacienteAModificar);
		if (!medicinas.isEmpty()) {
			for (Iterator<PacienteMedicina> iterator = medicinas.iterator(); iterator
					.hasNext();) {
				PacienteMedicina pacienteMedicina = (PacienteMedicina) iterator
						.next();
				pacienteMedicina.setPaciente(pacienteNuevo);
			}
			getServicioPacienteMedicina().guardar(medicinas);
		}
		List<PacienteAntecedente> antecedentes = getServicioPacienteAntecedente()
				.buscarAntecedentesPorPaciente(pacienteAModificar);
		getServicioPacienteAntecedente().borrarAntecedentesPaciente(
				pacienteAModificar);
		if (!antecedentes.isEmpty()) {
			for (Iterator<PacienteAntecedente> iterator = antecedentes
					.iterator(); iterator.hasNext();) {
				PacienteAntecedente pacienteAntecedente = (PacienteAntecedente) iterator
						.next();
				pacienteAntecedente.setPaciente(pacienteNuevo);
			}
			getServicioPacienteAntecedente().guardar(antecedentes);
		}
		List<Cita> citas = getServicioCita().buscarPorPaciente(
				pacienteAModificar);
		if (!citas.isEmpty()) {
			for (Iterator<Cita> iterator = citas.iterator(); iterator.hasNext();) {
				Cita cita = (Cita) iterator.next();
				cita.setPaciente(pacienteNuevo);
			}
			getServicioCita().guardarVarias(citas);
		}
		List<Orden> ordenes = getServicioOrden().buscarPorPaciente(
				pacienteAModificar);
		if (!ordenes.isEmpty()) {
			for (Iterator<Orden> iterator = ordenes.iterator(); iterator
					.hasNext();) {
				Orden orden = (Orden) iterator.next();
				orden.setPaciente(pacienteNuevo);
			}
			getServicioOrden().guardarVarias(ordenes);
		}
		List<Consulta> consultas = getServicioConsulta().buscarPorPaciente(
				pacienteAModificar);
		if (!consultas.isEmpty()) {
			for (Iterator<Consulta> iterator = consultas.iterator(); iterator
					.hasNext();) {
				Consulta consulta = (Consulta) iterator.next();
				consulta.setPaciente(pacienteNuevo);
			}
			getServicioConsulta().guardarVarias(consultas);
		}
		Historia historia = getServicioHistoria().buscarPorPaciente(
				pacienteAModificar);
		if (historia != null) {
			historia.setPaciente(pacienteNuevo);
			getServicioHistoria().guardar(historia);
		}
		getServicioPaciente().eliminar(pacienteAModificar);

	}

	protected boolean validar() {
		if (idPaciente == null) {
			Mensaje.mensajeError("Debe seleccionar un Paciente");
			return false;
		} else {
			if (txtCedulaPaciente2.getText().compareTo("") == 0) {
				Mensaje.mensajeError("Debe llenar el campo de la Nueva Cedula");
				return false;
			} else
				return true;
		}
	}

	@Listen("onClick = #btnBuscarPaciente")
	public void mostrarCatalogoPaciente() {
		List<Paciente> pacientesBuscar = servicioPaciente.buscarTodosActivos();
		final List<Paciente> pacientes = pacientesBuscar;
		catalogoPaciente = new Catalogo<Paciente>(divCatalogoPacientes,
				"Catalogo de Pacientes", pacientes, false,"Cedula", "Ficha",
				"Primer Nombre","Segundo Nombre", "Primer Apellido", "Segundo Apellido", "Trabajador Asociado") {

			@Override
			protected List<Paciente> buscar(String valor, String combo) {
				switch (combo) {
				case "Ficha":
					return servicioPaciente.filtroFichaActivos(valor);
				case "Primer Nombre":
					return servicioPaciente.filtroNombre1Activos(valor);
				case "Segundo Nombre":
					return servicioPaciente.filtroNombre2Activos(valor);
				case "Cedula":
					return servicioPaciente.filtroCedulaActivos(valor);
				case "Primer Apellido":
					return servicioPaciente.filtroApellido1Activos(valor);
				case "Segundo Apellido":
					return servicioPaciente.filtroApellido2Activos(valor);
				case "Trabajador Asociado":
					return servicioPaciente.filtroCedulaFamiliar1Activos(valor);
				default:
					return pacientes;
				}
			}

			@Override
			protected String[] crearRegistros(Paciente objeto) {
				String[] registros = new String[7];
				registros[0] = objeto.getCedula();
				registros[1] = objeto.getFicha();
				registros[2] = objeto.getPrimerNombre();
				registros[3] = objeto.getSegundoNombre();
				registros[4] = objeto.getPrimerApellido();
				registros[5] = objeto.getSegundoApellido();
				registros[6] = objeto.getCedulaFamiliar();
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
			limpiarCampos();
			Mensaje.mensajeError(Mensaje.pacienteNoExiste);
		}
	}

	private void limpiarCampos() {
		txtCedulaPaciente2.setValue("");
		txtCedulaPaciente.setValue("");
		lblNombrePaciente.setValue("");
		lblApellidoPaciente.setValue("");
		idPaciente = null;
	}

}
