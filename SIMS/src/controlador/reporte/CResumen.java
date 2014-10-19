package controlador.reporte;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.generico.Resumen;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaDiagnostico;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Row;
import org.zkoss.zul.Tab;

import componentes.Botonera;
import componentes.Mensaje;
import controlador.maestros.CGenerico;

public class CResumen extends CGenerico {

	private static final long serialVersionUID = -2646596275451419084L;
	@Wire
	private Datebox dtbDesde;
	@Wire
	private Datebox dtbHasta;
	@Wire
	private Radio rdoFamiliar;
	@Wire
	private Radio rdoTrabajador;
	@Wire
	private Radio rdoTodos;
	@Wire
	private Div divResumen;
	@Wire
	private Div botoneraResumen;
	@Wire
	private Row row;
	private String nombre;
	private String tipo;

	@Override
	public void inicializar() throws IOException {
		HashMap<String, Object> mapa = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (mapa != null) {
			if (mapa.get("tabsGenerales") != null) {
				tabs = (List<Tab>) mapa.get("tabsGenerales");
				nombre = (String) mapa.get("nombre");
				mapa.clear();
				mapa = null;
			}
		}
		switch (nombre) {
		case "Resumen de Morbilidad por Area y Tipo de Diagnostico":
			row.setVisible(false);
			tipo = "1";
			break;
		case "Resumen de Morbilidad por Diagnostico":
			row.setVisible(true);
			tipo = "2";
			break;
		case "Resumen de Morbilidad por Tipo de Consulta":
			row.setVisible(true);
			tipo = "3";
			break;
		}
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divResumen, nombre, tabs);
			}

			@Override
			public void limpiar() {
				dtbDesde.setValue(fecha);
				dtbHasta.setValue(fecha);
				rdoFamiliar.setChecked(false);
				rdoTrabajador.setChecked(false);
				rdoTodos.setChecked(false);
			}

			@Override
			public void guardar() {
				if (validar()) {
					Date desde = dtbDesde.getValue();
					Date hasta = dtbHasta.getValue();
					DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
					String parentesco = "";
					boolean trabajador = false;
					String trabaja = "";
					if (rdoFamiliar.isChecked()) {
						parentesco = "familiar";
						trabaja = "no";
					}
					if (rdoTrabajador.isChecked()) {
						parentesco = "trabajador";
						trabajador = true;
						trabaja = "si";
					}
					if (rdoTodos.isChecked())
						parentesco = "todos";
					String fecha1 = fecha.format(desde);
					String fecha2 = fecha.format(hasta);
					List<ConsultaDiagnostico> consultas = new ArrayList<ConsultaDiagnostico>();
					List<Consulta> consultasSolas = new ArrayList<Consulta>();
					switch (tipo) {
					// Reporte 1
					case "1":
						consultas = servicioConsultaDiagnostico
								.buscarEntreFechasResumen(desde, hasta, true);
						if (!consultas.isEmpty())
							Clients.evalJavaScript("window.open('"
									+ damePath()
									+ "Reportero?valor=16&valor6="
									+ fecha1
									+ "&valor7="
									+ fecha2
									+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
						else
							msj.mensajeAlerta(Mensaje.noHayRegistros);
						break;
					// Reporte 2
					case "2":
						if (parentesco.equals("todos"))
							consultas = servicioConsultaDiagnostico
									.buscarDiagnosticoEntreFechasResumen(desde,
											hasta);
						else
							consultas = servicioConsultaDiagnostico
									.buscarDiagnosticoEntreFechasYTrabajadorResumen(
											desde, hasta, trabajador);
						if (!consultas.isEmpty())
							Clients.evalJavaScript("window.open('"
									+ damePath()
									+ "Reportero?valor=17&valor6="
									+ fecha1
									+ "&valor7="
									+ fecha2
									+ "&valor8="
									+ parentesco
									+ "&valor9="
									+ trabaja
									+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
						else
							msj.mensajeAlerta(Mensaje.noHayRegistros);
						break;
					// Reporte 3
					case "3":
						if (parentesco.equals("todos"))
							consultasSolas = servicioConsulta
									.buscarTipoDeConsultaEntreFechasResumen(
											desde, hasta);
						else
							consultasSolas = servicioConsulta
									.buscarTipoDeConsultaEntreFechasYTrabajadorResumen(
											desde, hasta, trabajador);
						if (!consultasSolas.isEmpty())
							Clients.evalJavaScript("window.open('"
									+ damePath()
									+ "Reportero?valor=18&valor6="
									+ fecha1
									+ "&valor7="
									+ fecha2
									+ "&valor8="
									+ parentesco
									+ "&valor9="
									+ trabaja
									+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
						else
							msj.mensajeAlerta(Mensaje.noHayRegistros);
						break;
					}
				}
			}

			@Override
			public void eliminar() {
				// TODO Auto-generated method stub

			}
		};
		botoneraResumen.appendChild(botonera);
	}

	protected boolean validar() {
		if ((tipo.equals("2") || tipo.equals("3"))
				&& (!rdoFamiliar.isChecked() && !rdoTrabajador.isChecked() && !rdoTodos
						.isChecked())) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	public byte[] reporteAreaTipoDiagnostico(String par6, String par7) {
		byte[] fichero = null;
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		Date fecha1 = null;
		try {
			fecha1 = formato.parse(par6);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date fecha2 = null;
		try {
			fecha2 = formato.parse(par7);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<ConsultaDiagnostico> consultas = getServicioConsultaDiagnostico()
				.buscarEntreFechasResumen(fecha1, fecha2, true);
		List<Resumen> lista = new ArrayList<Resumen>();
		Map p = new HashMap();
		p.put("desde", par6);
		p.put("hasta", par7);
		String area = consultas.get(0).getConsulta().getPaciente().getArea()
				.getNombre();
		int contadorEC = 0, contadorEL = 0, contadorAC = 0, contadorAL = 0, contadorI = 0, contadorO = 0;
		for (int i = 0; i < consultas.size(); i++) {
			if (area.equals(consultas.get(i).getConsulta().getPaciente()
					.getArea().getNombre())) {
				switch (consultas.get(i).getTipo()) {
				case "Accidente Comun":
					contadorAC = contadorAC + 1;
					break;
				case "Accidente Laboral":
					contadorAL = contadorAL + 1;
					break;
				case "Enfermedad Comun":
					contadorEC = contadorEC + 1;
					break;
				case "Enfermedad Laboral":
					contadorEL = contadorEL + 1;
					break;
				case "Incidente":
					contadorI = contadorI + 1;
					break;
				case "Otro":
					contadorO = contadorO + 1;
					break;
				}
			} else {
				Resumen objeto = new Resumen();
				objeto.setNombre1(area);
				objeto.setValor1(contadorEC);
				objeto.setValor2(contadorEL);
				objeto.setValor3(contadorAC);
				objeto.setValor4(contadorAL);
				objeto.setValor5(contadorI);
				objeto.setValor6(contadorO);
				lista.add(objeto);
				area = consultas.get(i).getConsulta().getPaciente().getArea()
						.getNombre();
				contadorEC = contadorEL = contadorAC = contadorAL = contadorI = contadorO = 0;
				i--;
			}
			if (i == (consultas.size() - 1)) {
				Resumen objeto = new Resumen();
				objeto.setNombre1(area);
				objeto.setValor1(contadorEC);
				objeto.setValor2(contadorEL);
				objeto.setValor3(contadorAC);
				objeto.setValor4(contadorAL);
				objeto.setValor5(contadorI);
				objeto.setValor6(contadorO);
				lista.add(objeto);
			}
		}
		JasperReport reporte = null;
		try {
			reporte = (JasperReport) JRLoader.loadObject(getClass()
					.getResource("/reporte/RResumenArea.jasper"));
		} catch (JRException e) {
			msj.mensajeError("Recurso no Encontrado");
		}

		try {
			fichero = JasperRunManager.runReportToPdf(reporte, p,
					new JRBeanCollectionDataSource(lista));
		} catch (JRException e) {
			msj.mensajeError("Error en Reporte");
		}
		return fichero;
	}

	public byte[] reporteDiagnostico(String par6, String par7, String par8,
			String par9) {
		byte[] fichero = null;
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		Date fecha1 = null;
		try {
			fecha1 = formato.parse(par6);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date fecha2 = null;
		try {
			fecha2 = formato.parse(par7);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<ConsultaDiagnostico> consultas = new ArrayList<ConsultaDiagnostico>();
		List<Resumen> lista = new ArrayList<Resumen>();
		boolean trabajador = false;
		String tipoPaciente = "Todos";
		if (par9.equals("si")) {
			trabajador = true;
			tipoPaciente = "Trabajadores";
		} else {
			if (par9.equals("no"))
				tipoPaciente = "Familiares";
		}
		if (par9.equals("si"))
			trabajador = true;
		if (par8.equals("todos"))
			consultas = getServicioConsultaDiagnostico()
					.buscarDiagnosticoEntreFechasResumen(fecha1, fecha2);
		else
			consultas = getServicioConsultaDiagnostico()
					.buscarDiagnosticoEntreFechasYTrabajadorResumen(fecha1,
							fecha2, trabajador);
		String diagnostico = consultas.get(0).getDiagnostico().getNombre();
		int contador = 0;
		for (int i = 0; i < consultas.size(); i++) {
			if (diagnostico.equals(consultas.get(i).getDiagnostico()
					.getNombre())) {
				contador = contador + 1;
			} else {
				Resumen objeto = new Resumen();
				objeto.setNombre1(diagnostico);
				objeto.setValor1(contador);
				lista.add(objeto);
				diagnostico = consultas.get(i).getDiagnostico().getNombre();
				contador = 0;
				i--;
			}
			if (i == (consultas.size() - 1)) {
				Resumen objeto = new Resumen();
				objeto.setNombre1(diagnostico);
				objeto.setValor1(contador);
				lista.add(objeto);
			}
		}

		Map p = new HashMap();
		p.put("desde", par6);
		p.put("hasta", par7);
		p.put("tipoPaciente", tipoPaciente);
		p.put("cantidad", lista.size());

		JasperReport reporte = null;
		try {
			reporte = (JasperReport) JRLoader.loadObject(getClass()
					.getResource("/reporte/RResumenDiagnostico.jasper"));
		} catch (JRException e) {
			msj.mensajeError("Recurso no Encontrado");
		}

		try {
			fichero = JasperRunManager.runReportToPdf(reporte, p,
					new JRBeanCollectionDataSource(lista));
		} catch (JRException e) {
			msj.mensajeError("Error en Reporte");
		}
		return fichero;
	}

	public byte[] reporteTipoConsulta(String par6, String par7, String par8,
			String par9) {
		byte[] fichero = null;
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		Date fecha1 = null;
		try {
			fecha1 = formato.parse(par6);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date fecha2 = null;
		try {
			fecha2 = formato.parse(par7);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Consulta> consultasSolas = new ArrayList<Consulta>();
		List<Resumen> lista = new ArrayList<Resumen>();
		boolean trabajador = false;
		String tipoPaciente = "Todos";
		if (par9.equals("si")) {
			trabajador = true;
			tipoPaciente = "Trabajadores";
		} else {
			if (par9.equals("no"))
				tipoPaciente = "Familiares";
		}
		if (par8.equals("todos"))
			consultasSolas = getServicioConsulta()
					.buscarTipoDeConsultaEntreFechasResumen(fecha1, fecha2);
		else
			consultasSolas = getServicioConsulta()
					.buscarTipoDeConsultaEntreFechasYTrabajadorResumen(fecha1,
							fecha2, trabajador);
		
		String tipoConsulta = consultasSolas.get(0).getTipoConsulta();
		String tipoConsultaSecundaria = consultasSolas.get(0)
				.getTipoConsultaSecundaria();
		int contador = 0;
		for (int i = 0; i < consultasSolas.size(); i++) {
			if (tipoConsulta.equals(consultasSolas.get(i).getTipoConsulta())) {
				switch (consultasSolas.get(i).getTipoConsultaSecundaria()) {

				case "Pre-Vacacional":
				case "Pre-Empleo":
				case "Post-Vacacional":
				case "Egreso":
				case "Cambio de Puesto":
				case "Promocion":
				case "Reintegro":
				case "Por Area":
				case "Checkeo General":
				case "Primera":
				case "Control":
				case "IC":
					if (tipoConsultaSecundaria.equals(consultasSolas.get(i)
							.getTipoConsultaSecundaria()))
						contador = contador + 1;
					else {
						Resumen objeto = new Resumen();
						objeto.setNombre1(tipoConsulta);
						objeto.setNombre2(tipoConsultaSecundaria);
						objeto.setValor1(contador);
						lista.add(objeto);
						tipoConsultaSecundaria = consultasSolas.get(i)
								.getTipoConsultaSecundaria();
						tipoConsulta = consultasSolas.get(i).getTipoConsulta();
						contador = 0;
						i--;
					}
					break;
				}
			} else {
				Resumen objeto = new Resumen();
				objeto.setNombre1(tipoConsulta);
				objeto.setNombre2(tipoConsultaSecundaria);
				objeto.setValor1(contador);
				lista.add(objeto);
				tipoConsulta = consultasSolas.get(i).getTipoConsulta();
				tipoConsultaSecundaria = consultasSolas.get(i)
						.getTipoConsultaSecundaria();
				contador = 0;
				i--;
			}
			if (i == (consultasSolas.size() - 1)) {
				Resumen objeto = new Resumen();
				objeto.setNombre1(tipoConsulta);
				objeto.setNombre2(tipoConsultaSecundaria);
				objeto.setValor1(contador);
				lista.add(objeto);
			}
		}
		Map p = new HashMap();
		p.put("desde", par6);
		p.put("hasta", par7);
		p.put("tipoPaciente", tipoPaciente);
		p.put("cantidad", lista.size());

		JasperReport reporte = null;
		try {
			reporte = (JasperReport) JRLoader.loadObject(getClass()
					.getResource("/reporte/RResumenTipoConsulta.jasper"));
		} catch (JRException e) {
			msj.mensajeError("Recurso no Encontrado");
		}

		try {
			fichero = JasperRunManager.runReportToPdf(reporte, p,
					new JRBeanCollectionDataSource(lista));
		} catch (JRException e) {
			msj.mensajeError("Error en Reporte");
		}

		return fichero;
	}
}
