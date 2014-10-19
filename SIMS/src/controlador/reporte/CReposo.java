package controlador.reporte;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.maestros.Paciente;
import modelo.seguridad.Usuario;
import modelo.sha.Area;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaDiagnostico;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Row;
import org.zkoss.zul.Tab;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;

import controlador.maestros.CGenerico;

public class CReposo extends CGenerico {

	@Wire
	private Div catalogoUsuarios;
	@Wire
	private Combobox cmbArea;
	@Wire
	private Datebox dtbDesde;
	@Wire
	private Datebox dtbHasta;
	@Wire
	private Div divReposo;
	@Wire
	private Div botoneraReposo;
	@Wire
	private Row rowArea;
	@Wire
	private Row rowPaciente;
	@Wire
	private Row rowDiagnostico;
	@Wire
	private Row rowDoctor;
	@Wire
	private Button btnBuscarPaciente;
	@Wire
	private Button btnBuscarDoctor;
	@Wire
	private Combobox cmbUnidad;
	@Wire
	private Combobox cmbDiagnostico;
	@Wire
	private Label lblPaciente;
	@Wire
	private Label lblNombreDoctor;
	@Wire
	private Div divCatalogoPaciente;

	private String tipo = "";
	private String titulo = "";
	private String idPaciente = "";
	private String idDoctor = "";
	Catalogo<Paciente> catalogo;
	Catalogo<Usuario> catalogoDoctor;

	public String getTitulo() {
		return titulo;
	}

	@Override
	public void inicializar() throws IOException {
		cargarCombos();
		HashMap<String, Object> mapa = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (mapa != null) {
			if (mapa.get("tabsGenerales") != null) {
				tabs = (List<Tab>) mapa.get("tabsGenerales");
				titulo = (String) mapa.get("titulo");
				mapa.clear();
				mapa = null;
			}
		}

		switch (titulo) {
		case "Por Area":
			rowArea.setVisible(true);
			rowDiagnostico.setVisible(false);
			rowDoctor.setVisible(false);
			rowPaciente.setVisible(false);
			tipo = "area";
			break;
		case "Por Diagnostico":
			rowArea.setVisible(false);
			rowDiagnostico.setVisible(true);
			rowDoctor.setVisible(false);
			rowPaciente.setVisible(false);
			tipo = "diagnostico";
			break;
		case "Por Doctor":
			rowArea.setVisible(false);
			rowDiagnostico.setVisible(false);
			rowDoctor.setVisible(true);
			rowPaciente.setVisible(false);
			tipo = "doctor";
			break;
		}

		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				// Pasar el nombre del arbol por el CARBOL
				cerrarVentana(divReposo, titulo, tabs);
			}

			@Override
			public void limpiar() {
				dtbDesde.setValue(fecha);
				dtbHasta.setValue(fecha);

				switch (tipo) {
				case "area":
					cmbArea.setValue("TODAS");
					break;
				case "diagnostico":
					cmbDiagnostico.setValue("TODOS");
					break;
				case "doctor":
					idDoctor = "";
					cmbUnidad.setValue("TODAS");
					break;
				}
			}

			@Override
			public void guardar() {
				if (validar()) {
					switch (tipo) {
					case "area":
						if (validarArea())
							reporteArea();
						break;
					case "diagnostico":
						if (validarDiagnostico())
							reporteDiagnostico();
						break;
					case "doctor":
						if (validarDoctor())
							reporteDoctor();
						break;
					}
				}
			}

			@Override
			public void eliminar() {
				// TODO Auto-generated method stub

			}
		};
		Button guardar = (Button) botonera.getChildren().get(0);
		guardar.setLabel("Reporte");
		botonera.getChildren().get(1).setVisible(false);
		botoneraReposo.appendChild(botonera);
	}

	private void cargarCombos() {
		String todos = "TODAS";
		Area area = new Area();
		area.setNombre(todos);
		area.setIdArea(0);
		List<Area> areas = new ArrayList<Area>();
		areas.add(area);
		areas.addAll(servicioArea.buscarTodos());
		cmbArea.setModel(new ListModelList<Area>(areas));
	}

	public boolean validar() {
		if (dtbDesde.getText().compareTo("") == 0
				|| dtbHasta.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else {
			if (dtbDesde.getValue().after(dtbHasta.getValue())) {
				msj.mensajeError(Mensaje.fechaPosterior);
				return false;

			} else
				return true;
		}

	}

	public boolean validarArea() {
		if (cmbArea.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		}
		return true;
	}

	public boolean validarDiagnostico() {
		if (cmbDiagnostico.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		}
		return true;
	}

	public boolean validarDoctor() {
		if (cmbUnidad.getText().compareTo("") == 0 || idDoctor.equals("")) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		}
		return true;
	}

	public void reporteArea() {
		Date desde = dtbDesde.getValue();
		Date hasta = dtbHasta.getValue();
		DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
		String fecha1 = fecha.format(desde);
		String fecha2 = fecha.format(hasta);
		String area = "";
		Area area2 = new Area();
		if (cmbArea.getValue().equals("TODAS"))
			area = "";
		else {
			area = cmbArea.getSelectedItem().getContext();
			area2 = getServicioArea().buscar(Long.parseLong(area));
		}

		// if ((area.equals("") && servicioConsulta
		// .buscarEntreFechas(desde, hasta).isEmpty())
		// || (!area.equals("") && servicioConsulta
		// .buscarEntreFechasyArea(desde, hasta, area2).isEmpty()))
		// msj.mensajeAlerta(Mensaje.noHayRegistros);
		// else {
		Clients.evalJavaScript("window.open('"
				+ damePath()
				+ "Reportero?valor=13&valor6="
				+ fecha1
				+ "&valor7="
				+ fecha2
				+ "&valor8="
				+ area
				+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");

	}

	public byte[] reporteReposoPorArea(String part1, String part2, String area)
			throws JRException {
		byte[] fichero = null;
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		Date fecha1 = null;
		try {
			fecha1 = formato.parse(part1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date fecha2 = null;
		try {
			fecha2 = formato.parse(part2);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Consulta> consuta = new ArrayList<Consulta>();

		if (area.equals(""))
			consuta = getServicioConsulta()
					.buscarEntreFechasReposoyTrabajadores(fecha1, fecha2);
		else {
			Area area2 = getServicioArea().buscar(Long.parseLong(area));
			consuta = getServicioConsulta()
					.buscarEntreFechasReposoAreayTrabajadores(fecha1, fecha2,
							area2);
		}

		// if (consuta.isEmpty())
		// msj.mensajeAlerta(Mensaje.noHayRegistros);
		// else {
		Map p = new HashMap();
		p.put("desde", part1);
		p.put("hasta", part2);

		for (int i = 0; i < consuta.size(); i++) {
			Consulta cons = consuta.get(i);
			List<ConsultaDiagnostico> dig = getServicioConsultaDiagnostico()
					.buscarPorConsulta(cons);
			Calendar c = Calendar.getInstance();
			c.setTime(cons.getFechaConsulta());
			c.add(Calendar.DAY_OF_YEAR, cons.getDiasReposo());
			Date fechaHasta = c.getTime();
			Timestamp fechaHasta2 = new Timestamp(fechaHasta.getTime());
			// Timestamp fechaHasta = (Timestamp) c.getTime();
			cons.setFechaAuditoria(fechaHasta2);
			if (!dig.isEmpty()) {
				if (dig.get(0) != null) {

					cons.setEnfermedadActual(dig.get(0).getDiagnostico()
							.getNombre());
					cons.setMotivoConsulta(dig.get(0).getTipo());

				}
			} else {
				cons.setEnfermedadActual("");
				cons.setMotivoConsulta("");
			}
		}

		JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass()
				.getResource("/reporte/RRepososPorArea.jasper"));
		fichero = JasperRunManager.runReportToPdf(reporte, p,
				new JRBeanCollectionDataSource(consuta));
		// }
		return fichero;
	}

	public void reporteDiagnostico() {
		Date desde = dtbDesde.getValue();
		Date hasta = dtbHasta.getValue();
		DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
		String fecha1 = fecha.format(desde);
		String fecha2 = fecha.format(hasta);
		String diagnostico = "";
		if (cmbDiagnostico.getValue().equals("TODOS"))
			diagnostico = "";
		else
			diagnostico = cmbDiagnostico.getValue();

		Clients.evalJavaScript("window.open('"
				+ damePath()
				+ "Reportero?valor=15&valor6="
				+ fecha1
				+ "&valor7="
				+ fecha2
				+ "&valor8="
				+ diagnostico
				+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");

	}

	public byte[] reporteReposoPorDiagnostico(String part1, String part2,
			String diagnostico) throws JRException {
		byte[] fichero = null;
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		Date fecha1 = null;
		try {
			fecha1 = formato.parse(part1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date fecha2 = null;
		try {
			fecha2 = formato.parse(part2);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<ConsultaDiagnostico> consutaDiag = new ArrayList<ConsultaDiagnostico>();

		if (diagnostico.equals(""))
			consutaDiag = getServicioConsultaDiagnostico()
					.buscarEntreFechasOrdenadoPorDiagnostico(fecha1, fecha2);
		else {
			consutaDiag = getServicioConsultaDiagnostico()
					.buscarEntreFechasyTipoDiagnostico(fecha1,
							fecha2, diagnostico);
		}
		// if (consutaDiag.isEmpty())
		// msj.mensajeAlerta(Mensaje.noHayRegistros);
		// else {
		Map p = new HashMap();
		p.put("desde", part1);
		p.put("hasta", part2);
		
		for (int i = 0; i < consutaDiag.size(); i++) {
			Consulta cons = consutaDiag.get(i).getConsulta();
			Calendar c = Calendar.getInstance();
			c.setTime(cons.getFechaConsulta());
			c.add(Calendar.DAY_OF_YEAR, cons.getDiasReposo());
			Date fechaHasta = c.getTime();
			Timestamp fechaHasta2 = new Timestamp(fechaHasta.getTime());
			cons.setFechaAuditoria(fechaHasta2);
		}

		JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass()
				.getResource("/reporte/RRepososPorDiagnostico.jasper"));

		fichero = JasperRunManager.runReportToPdf(reporte, p,
				new JRBeanCollectionDataSource(consutaDiag));

		//
		return fichero;
	}

	public void reporteDoctor() {
		Date desde = dtbDesde.getValue();
		Date hasta = dtbHasta.getValue();
		DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
		String fecha1 = fecha.format(desde);
		String fecha2 = fecha.format(hasta);
		String unidad = "";

		if (cmbUnidad.getValue().equals("TODAS"))
			unidad = "";
		else
			unidad = cmbUnidad.getValue();

		Clients.evalJavaScript("window.open('"
				+ damePath()
				+ "Reportero?valor=14&valor6="
				+ fecha1
				+ "&valor7="
				+ fecha2
				+ "&valor8="
				+ unidad
				+ "&valor9="
				+ idDoctor
				+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");

	}

	public byte[] reporteReposoPorDoctor(String part1, String part2,
			String unidad, String doctor) throws JRException {
		byte[] fichero = null;
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		Date fecha1 = null;
		try {
			fecha1 = formato.parse(part1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date fecha2 = null;
		try {
			fecha2 = formato.parse(part2);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Consulta> consuta = new ArrayList<Consulta>();

		if (unidad.equals("") && doctor.equals("TODOS"))
			consuta = getServicioConsulta()
					.buscarEntreFechasOrdenadasPorUnidadReposoyTrabajadores(
							fecha1, fecha2);
		else {
			if (!unidad.equals("") && doctor.equals("TODOS"))
				consuta = getServicioConsulta()
						.buscarEntreFechasPorUnidadReposoyTrabajadores(fecha1,
								fecha2, unidad);
			else {
				if (unidad.equals("") && !doctor.equals("TODOS")) {
					Usuario doc = getServicioUsuario().buscarPorCedula(doctor);
					consuta = getServicioConsulta()
							.buscarEntreFechasPorDoctorReposoyTrabajadores(
									fecha1, fecha2, doc);
				} else {
					if (!unidad.equals("") && !doctor.equals("TODOS")) {
						Usuario doc = getServicioUsuario().buscarPorCedula(
								doctor);
						consuta = getServicioConsulta()
								.buscarEntreFechasPorDoctorReposoyTrabajadores(
										fecha1, fecha2, doc);
					}
				}
			}
		}

		Map p = new HashMap();
		p.put("desde", part1);
		p.put("hasta", part2);
		if (unidad.equals(""))
			p.put("unidad", "TODAS");
		else
			p.put("unidad", unidad);

		for (int i = 0; i < consuta.size(); i++) {
			Consulta cons = consuta.get(i);
			List<ConsultaDiagnostico> dig = getServicioConsultaDiagnostico()
					.buscarPorConsulta(cons);
			Calendar c = Calendar.getInstance();
			c.setTime(cons.getFechaConsulta());
			c.add(Calendar.DAY_OF_YEAR, cons.getDiasReposo());
			Date fechaHasta = c.getTime();
			Timestamp fechaHasta2 = new Timestamp(fechaHasta.getTime());
			// Timestamp fechaHasta = (Timestamp) c.getTime();
			cons.setFechaAuditoria(fechaHasta2);
			if (!dig.isEmpty()) {
				if (dig.get(0) != null) {
					cons.setEnfermedadActual(dig.get(0).getDiagnostico()
							.getNombre());
					cons.setMotivoConsulta(dig.get(0).getTipo());

				}
			} else {
				cons.setEnfermedadActual("");
				cons.setMotivoConsulta("");
			}
		}

		JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass()
				.getResource("/reporte/RRepososPorDoctor.jasper"));
		fichero = JasperRunManager.runReportToPdf(reporte, p,
				new JRBeanCollectionDataSource(consuta));
		// }
		return fichero;
	}

	/* Muestra un catalogo de Usuarios */
	@Listen("onClick = #btnBuscarDoctor")
	public void mostrarCatalogo() throws IOException {

		final List<Usuario> usuarios = new ArrayList<Usuario>();

		Usuario usuarioT = new Usuario();
		usuarioT.setCedula("TODOS");
		usuarioT.setFicha("TODOS");
		usuarioT.setPrimerNombre("TODOS");
		usuarioT.setPrimerApellido("TODOS");
		usuarios.add(usuarioT);
		usuarios.addAll(servicioUsuario.buscarDoctores());

		catalogoDoctor = new Catalogo<Usuario>(catalogoUsuarios,
				"Catalogo de Doctores", usuarios, "Cedula", "Ficha", "Nombre",
				"Apellido", "Especialidad") {

			@Override
			protected List<Usuario> buscar(String valor, String combo) {
				switch (combo) {
				case "Cedula":
					return servicioUsuario.filtroCedulaDoctor(valor);
				case "Ficha":
					return servicioUsuario.filtroFichaDoctor(valor);
				case "Nombre":
					return servicioUsuario.filtroNombreDoctor(valor);
				case "Especialidad":
					return servicioUsuario.filtroEspecialidadDoctor(valor);
				case "Apellido":
					return servicioUsuario.filtroApellidoDoctor(valor);
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
				if (objeto.getEspecialidad() != null)
					registros[4] = objeto.getEspecialidad().getDescripcion();
				return registros;
			}

		};
		catalogoDoctor.setParent(catalogoUsuarios);
		catalogoDoctor.doModal();
	}

	/* Permite la seleccion de un item del catalogo de doctores */
	@Listen("onSeleccion = #catalogoUsuarios")
	public void seleccionarDoctor() {
		Usuario usuario = catalogoDoctor.objetoSeleccionadoDelCatalogo();
		lblNombreDoctor.setValue(usuario.getPrimerNombre() + " "
				+ usuario.getPrimerApellido());
		idDoctor = usuario.getCedula();
		catalogoDoctor.setParent(null);
	}

	// /* Muestra el catalogo de los Pacientes */
	// @Listen("onClick = #btnBuscarPaciente")
	// public void mostrarCatalogo() {
	// final List<Paciente> pacientes = new ArrayList<Paciente>();
	//
	// Paciente pacienteT = new Paciente();
	// pacienteT.setCedula("TODOS");
	// pacienteT.setFicha("TODOS");
	// pacienteT.setPrimerNombre("TODOS");
	// pacienteT.setPrimerApellido("TODOS");
	// pacientes.add(pacienteT);
	// pacientes.addAll(servicioPaciente.buscarTodosActivos());
	//
	// catalogo = new Catalogo<Paciente>(divCatalogoPaciente,
	// "Catalogo de Pacientes", pacientes, "Cedula", "Ficha",
	// "Nombre", "Apellido") {
	//
	// @Override
	// protected List<Paciente> buscar(String valor, String combo) {
	//
	// switch (combo) {
	// case "Nombre":
	// return servicioPaciente.filtroNombre1Activos(valor);
	// case "Cedula":
	// return servicioPaciente.filtroCedulaActivos(valor);
	// case "Ficha":
	// return servicioPaciente.filtroFichaActivos(valor);
	// case "Apellido":
	// return servicioPaciente.filtroApellido1Activos(valor);
	// default:
	// return pacientes;
	// }
	// }
	//
	// @Override
	// protected String[] crearRegistros(Paciente objeto) {
	// String[] registros = new String[4];
	// registros[0] = objeto.getCedula();
	// registros[1] = objeto.getFicha();
	// registros[2] = objeto.getPrimerNombre();
	// registros[3] = objeto.getPrimerApellido();
	// return registros;
	// }
	//
	// };
	// catalogo.setParent(divCatalogoPaciente);
	// catalogo.doModal();
	// }
	//
	// @Listen("onSeleccion = #divCatalogoPaciente")
	// public void seleccionar() {
	// Paciente paciente = catalogo.objetoSeleccionadoDelCatalogo();
	// lblPaciente.setValue(paciente.getPrimerNombre() + " "
	// + paciente.getPrimerApellido());
	// idPaciente = paciente.getCedula();
	// catalogo.setParent(null);
	// }

}
