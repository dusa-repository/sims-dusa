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

import modelo.maestros.Cargo;
import modelo.maestros.CategoriaDiagnostico;
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
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Tab;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;
import controlador.maestros.CGenerico;

public class CMorbilidad extends CGenerico {

	@Wire
	private Div catalogoUsuarios;
	@Wire
	private Combobox cmbArea;
	@Wire
	private Combobox cmbCargo;
	@Wire
	private Datebox dtbDesde;
	@Wire
	private Datebox dtbHasta;
	@Wire
	private Div divMorbilidad;
	@Wire
	private Div botoneraMorbilidad;
	@Wire
	private Combobox cmbTipoConsulta;
	@Wire
	private Combobox cmbTipoPreventiva;
	@Wire
	private Row rowArea;
	@Wire
	private Row rowPaciente;
	@Wire
	private Row rowTipoConsulta;
	@Wire
	private Row rowDiagnostico;
	@Wire
	private Row rowDoctor;
	@Wire
	private Row rowFamiliar;
	@Wire
	private Button btnBuscarPaciente;
	@Wire
	private Spinner spnDe;
	@Wire
	private Spinner spnA;
	@Wire
	private Button btnBuscarDoctor;
	@Wire
	private Combobox cmbUnidad;
	@Wire
	private Combobox cmbDiagnostico;
	@Wire
	private Radiogroup rdgFamiliar;
	@Wire
	private Radio rdoFamiliares;
	@Wire
	private Radio rdoTrabajadores;
	@Wire
	private Radio rdoTodos;
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

	private String[] consultaPreventiva = { "TODAS", "Pre-Empleo",
			"Pre-Vacacional", "Post-Vacacional", "Egreso", "Cambio de Puesto",
			"Promocion", "Reintegro", "Por Area", "Checkeo General" };
	private String[] consultaCurativa = { "TODAS", "Primera", "Control", "IC" };

	private String[] consultaTodas = { "TODAS" };

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
		case "Morbilidad Por Area":
			rowArea.setVisible(true);
			rowDiagnostico.setVisible(false);
			rowDoctor.setVisible(false);
			rowFamiliar.setVisible(false);
			rowPaciente.setVisible(false);
			rowTipoConsulta.setVisible(false);
			tipo = "area";
			break;
		case "Morbilidad Por Tipo de Consulta":
			rowArea.setVisible(false);
			rowDiagnostico.setVisible(false);
			rowDoctor.setVisible(false);
			rowFamiliar.setVisible(false);
			rowPaciente.setVisible(false);
			rowTipoConsulta.setVisible(true);
			tipo = "tipoConsulta";
			break;
		case "Morbilidad Por Diagnostico":
			rowArea.setVisible(false);
			rowDiagnostico.setVisible(true);
			rowDoctor.setVisible(false);
			rowFamiliar.setVisible(true);
			rowPaciente.setVisible(false);
			rowTipoConsulta.setVisible(false);
			tipo = "diagnostico";
			break;
		case "Morbilidad Por Doctor":
			rowArea.setVisible(false);
			rowDiagnostico.setVisible(false);
			rowDoctor.setVisible(true);
			rowFamiliar.setVisible(false);
			rowPaciente.setVisible(false);
			rowTipoConsulta.setVisible(false);
			tipo = "doctor";
			break;
		}

		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				// Pasar el nombre del arbol por el CARBOL
				cerrarVentana(divMorbilidad, titulo, tabs);
			}

			@Override
			public void limpiar() {
				dtbDesde.setValue(fecha);
				dtbHasta.setValue(fecha);

				switch (tipo) {
				case "area":
					cmbArea.setValue("TODAS");
					break;
				case "tipoConsulta":
					cmbTipoConsulta.setValue("TODAS");
					cmbTipoPreventiva.setValue("TODAS");
					// lblPaciente.setValue("");
					// idPaciente = "";
					break;
				case "diagnostico":
					cmbDiagnostico.setValue("TODOS");
					spnDe.setValue(0);
					spnA.setValue(100);
					rdoFamiliares.setChecked(false);
					rdoTodos.setChecked(false);
					rdoTrabajadores.setChecked(false);
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
					case "tipoConsulta":
						if (validarTipoConsulta())
							reporteTipoConsulta();
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
		guardar.setSrc("/public/imagenes/botones/reporte.png");
		botonera.getChildren().get(1).setVisible(false);
		botoneraMorbilidad.appendChild(botonera);
	}

	private void cargarCombos() {
		String todos = "TODAS";
		Area area = new Area();
		area.setNombre(todos);
		area.setIdArea(0);
		List<Area> areas = new ArrayList<Area>();
		areas.add(area);
		areas.addAll(servicioArea.buscarTodos());
		Cargo cargo = new Cargo();
		cargo.setNombre(todos);
		cargo.setIdCargo(0);
		List<Cargo> cargos = new ArrayList<Cargo>();
		cargos.add(cargo);
		cargos.addAll(servicioCargo.buscarTodos());
		cmbArea.setModel(new ListModelList<Area>(areas));
		cmbCargo.setModel(new ListModelList<Cargo>(cargos));
	}

	@Listen("onSelect = #cmbTipoConsulta")
	public void buscarPreventiva() {
		if (cmbTipoConsulta.getValue().equals("Preventiva")) {
			cmbTipoPreventiva.setModel(new ListModelList<String>(
					consultaPreventiva));

		} else {
			if (cmbTipoConsulta.getValue().equals("Curativa")) {
				cmbTipoPreventiva.setModel(new ListModelList<String>(
						consultaCurativa));
			} else {
				cmbTipoPreventiva.setModel(new ListModelList<String>(
						consultaTodas));

			}
		}
		cmbTipoPreventiva.setVisible(true);
		cmbTipoPreventiva.setValue("TODAS");
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

	public boolean validarTipoConsulta() {
		if (cmbTipoConsulta.getText().compareTo("") == 0
				|| cmbTipoPreventiva.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		}
		return true;
	}

	public boolean validarDiagnostico() {
		if (cmbDiagnostico.getText().compareTo("") == 0
				|| spnA.getText().compareTo("") == 0
				|| spnDe.getText().compareTo("") == 0
				|| (!rdoFamiliares.isChecked() && !rdoTrabajadores.isChecked() && !rdoTodos
						.isChecked())) {
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

		if ((area.equals("") && servicioConsulta
				.buscarEntreFechas(desde, hasta).isEmpty())
				|| (!area.equals("") && servicioConsulta
						.buscarEntreFechasyArea(desde, hasta, area2).isEmpty()))
			msj.mensajeAlerta(Mensaje.noHayRegistros);
		else {
			Clients.evalJavaScript("window.open('"
					+ damePath()
					+ "Reportero?valor=9&valor6="
					+ fecha1
					+ "&valor7="
					+ fecha2
					+ "&valor8="
					+ area
					+ "&valor9="
					+ cmbCargo.getValue()
					+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
		}

	}

	public byte[] reporteMorbilidadPorArea(String part1, String part2,
			String area, String cargo) throws JRException {
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
			consuta = getServicioConsulta().buscarEntreFechas(fecha1, fecha2);
		else {
			Area area2 = getServicioArea().buscar(Long.parseLong(area));
			consuta = getServicioConsulta().buscarEntreFechasyArea(fecha1,
					fecha2, area2);
		}
		Map p = new HashMap();
		p.put("desde", part1);
		p.put("hasta", part2);
		p.put("area", area);
		p.put("cargo", cargo);

		for (int i = 0; i < consuta.size(); i++) {
			Consulta cons = consuta.get(i);
			List<ConsultaDiagnostico> dig = getServicioConsultaDiagnostico()
					.buscarPorConsulta(cons);
			if (!dig.isEmpty()) {
				if (dig.get(0) != null) {
					cons.setEnfermedadActual(dig.get(0).getDiagnostico()
							.getNombre());
					cons.setMotivoConsulta(dig.get(0).getTipo());
					Paciente paciente = cons.getPaciente();
					paciente.setEdad(calcularEdad(paciente.getFechaNacimiento()));
				}
			} else {
				cons.setEnfermedadActual("");
				cons.setMotivoConsulta("");
			}
		}

		JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass()
				.getResource("/reporte/RMorbilidadPorArea.jasper"));
		fichero = JasperRunManager.runReportToPdf(reporte, p,
				new JRBeanCollectionDataSource(consuta));
		return fichero;
	}

	public void reporteTipoConsulta() {
		Date desde = dtbDesde.getValue();
		Date hasta = dtbHasta.getValue();
		DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
		String fecha1 = fecha.format(desde);
		String fecha2 = fecha.format(hasta);
		String tipoC = "";
		String subTipo = "";

		if (cmbTipoConsulta.getValue().equals("TODAS"))
			tipoC = "";
		else
			tipoC = cmbTipoConsulta.getValue();

		if (cmbTipoPreventiva.getValue().equals("TODAS"))
			subTipo = "";
		else
			subTipo = cmbTipoPreventiva.getValue();

		if ((tipoC.equals("") && subTipo.equals("") && servicioConsulta
				.buscarEntreFechasOrdenadasPorTipo(desde, hasta).isEmpty())
				|| (!tipoC.equals("") && subTipo.equals("") && servicioConsulta
						.buscarEntreFechasyTipoConsulta(desde, hasta, tipoC)
						.isEmpty())
				|| (!tipoC.equals("") && !subTipo.equals("") && servicioConsulta
						.buscarEntreFechasTipoConsultaySubTipo(desde, hasta,
								tipoC, subTipo).isEmpty()))
			msj.mensajeAlerta(Mensaje.noHayRegistros);
		else {
			Clients.evalJavaScript("window.open('"
					+ damePath()
					+ "Reportero?valor=10&valor6="
					+ fecha1
					+ "&valor7="
					+ fecha2
					+ "&valor8="
					+ tipoC
					+ "&valor9="
					+ subTipo
					+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
		}

	}

	public byte[] reporteMorbilidadPorTipo(String part1, String part2,
			String tipo, String subTipo) throws JRException {
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

		if (tipo.equals("") && subTipo.equals(""))
			consuta = getServicioConsulta().buscarEntreFechasOrdenadasPorTipo(
					fecha1, fecha2);
		else {
			if (!tipo.equals("") && subTipo.equals(""))
				consuta = getServicioConsulta().buscarEntreFechasyTipoConsulta(
						fecha1, fecha2, tipo);
			else {
				if (!tipo.equals("") && !subTipo.equals(""))
					consuta = getServicioConsulta()
							.buscarEntreFechasTipoConsultaySubTipo(fecha1,
									fecha2, tipo, subTipo);
			}

		}

		Map p = new HashMap();
		p.put("desde", part1);
		p.put("hasta", part2);

		for (int i = 0; i < consuta.size(); i++) {
			Consulta cons = consuta.get(i);
			List<ConsultaDiagnostico> dig = getServicioConsultaDiagnostico()
					.buscarPorConsulta(cons);
			if (!dig.isEmpty()) {
				if (dig.get(0) != null) {
					cons.setEnfermedadActual(dig.get(0).getDiagnostico()
							.getNombre());
					cons.setMotivoConsulta(dig.get(0).getTipo());
					Paciente paciente = cons.getPaciente();
					paciente.setEdad(calcularEdad(paciente.getFechaNacimiento()));
				}
			} else {
				cons.setEnfermedadActual("");
				cons.setMotivoConsulta("");
			}
		}

		JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass()
				.getResource("/reporte/RMorbilidadPorTipoConsulta.jasper"));
		fichero = JasperRunManager.runReportToPdf(reporte, p,
				new JRBeanCollectionDataSource(consuta));
		return fichero;
	}

	public void reporteDiagnostico() {
		Date desde = dtbDesde.getValue();
		Date hasta = dtbHasta.getValue();
		DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
		String fecha1 = fecha.format(desde);
		String fecha2 = fecha.format(hasta);
		String diagnostico = "";
		String radio = "";
		if (cmbDiagnostico.getValue().equals("TODOS"))
			diagnostico = "";
		else
			diagnostico = cmbDiagnostico.getValue();

		if (rdoFamiliares.isChecked())
			radio = "Familiares";
		if (rdoTodos.isChecked())
			radio = "Todos";
		if (rdoTrabajadores.isChecked())
			radio = "Trabajadores";

		int aa = spnA.getValue();
		int dea = spnDe.getValue();
		String a = String.valueOf(aa);
		String de = String.valueOf(dea);

		if ((diagnostico.equals("") && radio.equals("Todos") && servicioConsultaDiagnostico
				.buscarEntreFechasEntreEdades(desde, hasta, dea, aa).isEmpty())
				|| (!diagnostico.equals("") && radio.equals("Todos") && servicioConsultaDiagnostico
						.buscarEntreFechasEntreEdadesyTipoDiagnostico(desde,
								hasta, dea, aa, diagnostico).isEmpty())
				|| (diagnostico.equals("") && radio.equals("Familiares") && servicioConsultaDiagnostico
						.buscarEntreFechasEntreEdadesyFamiliar(desde, hasta,
								dea, aa, false).isEmpty())
				|| (diagnostico.equals("") && radio.equals("Trabajadores") && servicioConsultaDiagnostico
						.buscarEntreFechasEntreEdadesyFamiliar(desde, hasta,
								dea, aa, true).isEmpty())
				|| (!diagnostico.equals("") && radio.equals("Familiares") && servicioConsultaDiagnostico
						.buscarEntreFechasEntreEdadesTipoDiagnosticoyFamiliar(
								desde, hasta, dea, aa, diagnostico, false)
						.isEmpty())
				|| (!diagnostico.equals("") && radio.equals("Trabajadores") && servicioConsultaDiagnostico
						.buscarEntreFechasEntreEdadesTipoDiagnosticoyFamiliar(
								desde, hasta, dea, aa, diagnostico, true)
						.isEmpty()))
			msj.mensajeAlerta(Mensaje.noHayRegistros);
		else {

			Clients.evalJavaScript("window.open('"
					+ damePath()
					+ "Reportero?valor=11&valor6="
					+ fecha1
					+ "&valor7="
					+ fecha2
					+ "&valor8="
					+ diagnostico
					+ "&valor9="
					+ radio
					+ "&valor10="
					+ a
					+ "&valor11="
					+ de
					+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
		}

	}

	public byte[] reporteMorbilidadPorDiagnostico(String part1, String part2,
			String diagnostico, String familiar, String a, String de)
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

		List<ConsultaDiagnostico> consutaDiag = new ArrayList<ConsultaDiagnostico>();
		int dea = Integer.valueOf(de);
		int aa = Integer.valueOf(a);
		if (diagnostico.equals("") && familiar.equals("Todos"))
			consutaDiag = getServicioConsultaDiagnostico()
					.buscarEntreFechasEntreEdades(fecha1, fecha2, dea, aa);
		else {
			if (!diagnostico.equals("") && familiar.equals("Todos"))
				consutaDiag = getServicioConsultaDiagnostico()
						.buscarEntreFechasEntreEdadesyTipoDiagnostico(fecha1,
								fecha2, dea, aa, diagnostico);
			else {
				if (diagnostico.equals("") && familiar.equals("Familiares"))
					consutaDiag = getServicioConsultaDiagnostico()
							.buscarEntreFechasEntreEdadesyFamiliar(fecha1,
									fecha2, dea, aa, false);
				else {
					if (diagnostico.equals("")
							&& familiar.equals("Trabajadores"))
						consutaDiag = getServicioConsultaDiagnostico()
								.buscarEntreFechasEntreEdadesyFamiliar(fecha1,
										fecha2, dea, aa, true);
					else {
						if (!diagnostico.equals("")
								&& familiar.equals("Familiares"))
							consutaDiag = getServicioConsultaDiagnostico()
									.buscarEntreFechasEntreEdadesTipoDiagnosticoyFamiliar(
											fecha1, fecha2, dea, aa,
											diagnostico, false);
						else {
							if (!diagnostico.equals("")
									&& familiar.equals("Trabajadores"))
								consutaDiag = getServicioConsultaDiagnostico()
										.buscarEntreFechasEntreEdadesTipoDiagnosticoyFamiliar(
												fecha1, fecha2, dea, aa,
												diagnostico, true);
						}
					}
				}
			}
		}

		Map p = new HashMap();
		p.put("desde", part1);
		p.put("hasta", part2);
		p.put("edad1", dea);
		p.put("edad2", aa);
		p.put("paciente", familiar);
		
		List<Long> consuta = getServicioConsultaDiagnostico().cantidadConsultas(consutaDiag);
		p.put("total",consuta.size());

		for (int i = 0; i < consutaDiag.size(); i++) {
			Consulta cons = consutaDiag.get(i).getConsulta();
			Paciente paciente = cons.getPaciente();
			paciente.setEdad(calcularEdad(paciente.getFechaNacimiento()));

		}

		JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass()
				.getResource("/reporte/RMorbilidadPorDiagnostico.jasper"));

		fichero = JasperRunManager.runReportToPdf(reporte, p,
				new JRBeanCollectionDataSource(consutaDiag));

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

		if ((unidad.equals("") && idDoctor.equals("TODOS") && servicioConsulta
				.buscarEntreFechasOrdenadasPorUnidad(desde, hasta).isEmpty())
				|| (!unidad.equals("") && idDoctor.equals("TODOS") && servicioConsulta
						.buscarEntreFechasPorUnidad(desde, hasta, unidad)
						.isEmpty())
				|| (unidad.equals("") && !idDoctor.equals("TODOS") && servicioConsulta
						.buscarEntreFechasPorDoctor(desde, hasta,
								servicioUsuario.buscarPorCedula(idDoctor))
						.isEmpty())
				|| (!unidad.equals("") && !idDoctor.equals("TODOS") && servicioConsulta
						.buscarEntreFechasPorDoctor(desde, hasta,
								servicioUsuario.buscarPorCedula(idDoctor))
						.isEmpty()))
			msj.mensajeAlerta(Mensaje.noHayRegistros);
		else {
			Clients.evalJavaScript("window.open('"
					+ damePath()
					+ "Reportero?valor=12&valor6="
					+ fecha1
					+ "&valor7="
					+ fecha2
					+ "&valor8="
					+ unidad
					+ "&valor9="
					+ idDoctor
					+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
		}

	}

	public byte[] reporteMorbilidadPorDoctor(String part1, String part2,
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
					.buscarEntreFechasOrdenadasPorUnidad(fecha1, fecha2);
		else {
			if (!unidad.equals("") && doctor.equals("TODOS"))
				consuta = getServicioConsulta().buscarEntreFechasPorUnidad(
						fecha1, fecha2, unidad);
			else {
				if (unidad.equals("") && !doctor.equals("TODOS")) {
					Usuario doc = getServicioUsuario().buscarPorCedula(doctor);
					consuta = getServicioConsulta().buscarEntreFechasPorDoctor(
							fecha1, fecha2, doc);
				} else {
					if (!unidad.equals("") && !doctor.equals("TODOS")) {
						Usuario doc = getServicioUsuario().buscarPorCedula(
								doctor);
						consuta = getServicioConsulta()
								.buscarEntreFechasPorDoctor(fecha1, fecha2, doc);
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
			if (!dig.isEmpty()) {
				if (dig.get(0) != null) {
					cons.setEnfermedadActual(dig.get(0).getDiagnostico()
							.getNombre());
					cons.setMotivoConsulta(dig.get(0).getTipo());
					Paciente paciente = cons.getPaciente();
					paciente.setEdad(calcularEdad(paciente.getFechaNacimiento()));
				}
			} else {
				cons.setEnfermedadActual("");
				cons.setMotivoConsulta("");
			}
		}

		JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass()
				.getResource("/reporte/RMorbilidadPorDoctor.jasper"));
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
