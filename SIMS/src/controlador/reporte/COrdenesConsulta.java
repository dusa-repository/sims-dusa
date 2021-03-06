package controlador.reporte;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.maestros.Examen;
import modelo.transacciones.ConsultaEspecialista;
import modelo.transacciones.ConsultaExamen;
import modelo.transacciones.ConsultaServicioExterno;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Tab;

import componentes.Botonera;
import componentes.Mensaje;

import controlador.maestros.CGenerico;

public class COrdenesConsulta extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Datebox dtbDesde;
	@Wire
	private Datebox dtbHasta;
	@Wire
	private Div divVOrdenConsulta;
	@Wire
	private Div botoneraOrdenConsulta;
	@Wire
	private Combobox cmbTipo;
	String nombre;

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

			@Override
			public void salir() {
				cerrarVentana(divVOrdenConsulta, nombre, tabs);
			}

			@Override
			public void limpiar() {
				dtbDesde.setValue(fecha);
				dtbHasta.setValue(fecha);
			}

			@Override
			public void guardar() {
				int numeroReporte = 0;
				Date desde = dtbDesde.getValue();
				Date hasta = dtbHasta.getValue();
				String fecha1 = formatoReporte.format(desde);
				String fecha2 = formatoReporte.format(hasta);
				String tipoReporte = cmbTipo.getValue();
				boolean listaVacia = true;
				switch (nombre) {
				case "Ordenes de Consulta Por Proveedor":
					listaVacia = reporteProveedor(desde, hasta);
					numeroReporte = 45;
					break;
				case "Ordenes de Consulta Por Especialista":
					listaVacia = reporteEspecialista(desde, hasta);
					numeroReporte = 46;
					break;
				}
				if (!listaVacia)
					Clients.evalJavaScript("window.open('"
							+ damePath()
							+ "Reportero?valor6="
							+ fecha1
							+ "&valor7="
							+ fecha2
							+ "&valor="
							+ numeroReporte
							+ "&valor20="
							+ tipoReporte
							+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
				else
					Mensaje.mensajeAlerta(Mensaje.noHayRegistros);
			}

			@Override
			public void eliminar() {
			}
		};
		Button guardar = (Button) botonera.getChildren().get(0);
		guardar.setLabel("Reporte");
		guardar.setImage("/public/imagenes/botones/reporte.png");
		botonera.getChildren().get(1).setVisible(false);
		botoneraOrdenConsulta.appendChild(botonera);
	}


	protected boolean reporteProveedor(Date desde, Date hasta) {
		List<ConsultaExamen> examenes = new ArrayList<ConsultaExamen>();
		List<ConsultaServicioExterno> estudios = new ArrayList<ConsultaServicioExterno>();
		examenes = servicioConsultaExamen
				.buscarEntreFechas(desde, hasta);
		estudios = servicioConsultaServicioExterno
				.buscarEntreFechas(desde, hasta);
	if (examenes.isEmpty() && estudios.isEmpty())
			return true;
		else
			return false;
	}

	protected boolean reporteEspecialista(Date desde, Date hasta) {
		List<ConsultaEspecialista> especialistas = new ArrayList<ConsultaEspecialista>();
		especialistas = servicioConsultaEspecialista
				.buscarEntreFechas(desde, hasta);
		if (especialistas.isEmpty())
			return true;
		else
			return false;

	}

	public byte[] jasperEspecialista(String par6, String par7, String tipo) {
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

		fecha2 = agregarDia(fecha2);
		List<ConsultaEspecialista> especialistas = new ArrayList<ConsultaEspecialista>();
			especialistas = getServicioConsultaEspecialista()
					.buscarEntreFechas(fecha1, fecha2);

		Map<String, Object> p = new HashMap<String, Object>();
		p.put("desde", par6);
		p.put("hasta", par7);
		p.put("data", new JRBeanCollectionDataSource(especialistas));
		JasperReport reporte = null;
		try {
			reporte = (JasperReport) JRLoader.loadObject(getClass()
					.getResource("/reporte/ROrdenesConsultaEspecialista.jasper"));
		} catch (JRException e) {
			Mensaje.mensajeError("Recurso no Encontrado");
		}

		if (tipo.equals("EXCEL")) {

			JasperPrint jasperPrint = null;
			try {
				jasperPrint = JasperFillManager.fillReport(reporte, p,
						new JRBeanCollectionDataSource(especialistas));
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
			try {
				exporter.exportReport();
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return xlsReport.toByteArray();
		} else {
			try {
				fichero = JasperRunManager.runReportToPdf(reporte, p,
						new JRBeanCollectionDataSource(especialistas));
			} catch (JRException e) {
				Mensaje.mensajeError("Error en Reporte");
			}
			return fichero;
		}
	}

	public byte[] jasperProveedor(String par6, String par7, String tipo) {
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
		fecha2 = agregarDia(fecha2);
		List<ConsultaExamen> examenes = new ArrayList<ConsultaExamen>();
		List<ConsultaServicioExterno> estudios = new ArrayList<ConsultaServicioExterno>();
		List<ConsultaExamen> lista = new ArrayList<ConsultaExamen>();
	
			examenes = getServicioConsultaExamen().buscarEntreFechas(fecha1,
					fecha2);
			estudios = getServicioConsultaServicioExterno().buscarEntreFechas(
					fecha1, fecha2);
			
		if (!examenes.isEmpty()) {
			lista.addAll(examenes);
			for (int j = 0; j < lista.size(); j++) {
				lista.get(j).setResultado("EXAMENES");
			}
			for (int i = 0; i < estudios.size(); i++) {
				ConsultaExamen consultaExamen = new ConsultaExamen();
				consultaExamen.setConsulta(estudios.get(i).getConsulta());
				consultaExamen.setProveedor(estudios.get(i).getProveedor());
				Examen examen = new Examen();
				examen.setNombre(estudios.get(i).getServicioExterno()
						.getNombre());
				consultaExamen.setExamen(examen);
				consultaExamen.setResultado("ESTUDIOS");
				long id = consultaExamen.getProveedor().getIdProveedor();
				boolean entro = false;
				for (int j = 0; j < lista.size(); j++) {
					long id2 = lista.get(j).getProveedor().getIdProveedor();
					if (id == id2) {
						entro = true;
						lista.add(j, consultaExamen);
						j = lista.size();
					}
				}
				if (!entro) {
					ConsultaExamen removida = lista.set(lista.size() - 1,
							consultaExamen);
					lista.add(lista.size() - 1, removida);
				}
			}
		} else {
			for (int i = 0; i < estudios.size(); i++) {
				ConsultaExamen consultaExamen = new ConsultaExamen();
				consultaExamen.setConsulta(estudios.get(i).getConsulta());
				consultaExamen.setProveedor(estudios.get(i).getProveedor());
				Examen examen = new Examen();
				examen.setNombre(estudios.get(i).getServicioExterno()
						.getNombre());
				consultaExamen.setExamen(examen);
				consultaExamen.setResultado("ESTUDIOS");
				lista.add(consultaExamen);
			}
		}

		Map<String, Object> p = new HashMap<String, Object>();
		p.put("desde", par6);
		p.put("hasta", par7);
		p.put("data", new JRBeanCollectionDataSource(lista));
		JasperReport reporte = null;
		try {
			reporte = (JasperReport) JRLoader.loadObject(getClass()
					.getResource("/reporte/ROrdenesConsultaProveedor.jasper"));
		} catch (JRException e) {
			Mensaje.mensajeError("Recurso no Encontrado");
		}

		if (tipo.equals("EXCEL")) {

			JasperPrint jasperPrint = null;
			try {
				jasperPrint = JasperFillManager.fillReport(reporte, p,
						new JRBeanCollectionDataSource(examenes));
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
			try {
				exporter.exportReport();
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return xlsReport.toByteArray();
		} else {
			try {
				fichero = JasperRunManager.runReportToPdf(reporte, p,
						new JRBeanCollectionDataSource(lista));
			} catch (JRException e) {
				Mensaje.mensajeError("Error en Reporte");
			}
			return fichero;
		}
	}

}
