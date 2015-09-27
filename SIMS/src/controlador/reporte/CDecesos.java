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

import modelo.generico.Resumen;
import modelo.maestros.Paciente;
import modelo.transacciones.Consulta;
import modelo.transacciones.Orden;
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

public class CDecesos extends CGenerico {

	private static final long serialVersionUID = 1L;
	@Wire
	private Datebox dtbDesde;
	@Wire
	private Datebox dtbHasta;
	@Wire
	private Combobox cmbTipo;
	@Wire
	private Div botoneraDecesos;
	@Wire
	private Div divDecesos;
	private String nombre;

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
				cerrarVentana(divDecesos, nombre, tabs);
			}

			@Override
			public void limpiar() {
				dtbDesde.setValue(fecha);
				dtbHasta.setValue(fecha);
			}

			@Override
			public void guardar() {
				Date desde = dtbDesde.getValue();
				Date hasta = dtbHasta.getValue();
				String fecha1 = formatoReporte.format(desde);
				String fecha2 = formatoReporte.format(hasta);
				String tipoReporte = cmbTipo.getValue();

				List<Paciente> decesos= servicioPaciente.buscarEntreFechasDecesos(desde, hasta);
				if (!decesos.isEmpty())
					Clients.evalJavaScript("window.open('"
							+ damePath()
							+ "Reportero?valor=59&valor6="
							+ fecha1
							+ "&valor7="
							+ fecha2
							+ "&valor20="
							+ tipoReporte
							+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
				else
					Mensaje.mensajeAlerta(Mensaje.noHayRegistros);
			}

			@Override
			public void eliminar() {
				// TODO Auto-generated method stub

			}
		};
		Button guardar = (Button) botonera.getChildren().get(0);
		guardar.setLabel("Reporte");
		guardar.setImage("/public/imagenes/botones/reporte.png");
		botonera.getChildren().get(1).setVisible(false);
		botoneraDecesos.appendChild(botonera);
	}

	public byte[] reporteResumen(String par6, String par7,
			String tipo2) {
		byte[] fichero = null;
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		Date fecha1 = null;
		try {
			fecha1 = formato.parse(par6);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date fecha2 = null;
		try {
			fecha2 = formato.parse(par7);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		fecha2 = agregarDia(fecha2);

		List<Paciente> pacientes = getServicioPaciente()
				.buscarEntreFechasDecesos(fecha1, fecha2);

		Map<String, Object> p = new HashMap<String, Object>();
		p.put("desde", par6);
		p.put("hasta", par7);
		JasperReport reporte = null;
		try {
			reporte = (JasperReport) JRLoader.loadObject(getClass()
					.getResource("/reporte/RDecesos.jasper"));
		} catch (JRException e) {
			msj = new Mensaje();
			Mensaje.mensajeError("Recurso no Encontrado");
		}
		if (tipo2.equals("EXCEL")) {

			JasperPrint jasperPrint = null;
			try {
				jasperPrint = JasperFillManager.fillReport(reporte, p,
						new JRBeanCollectionDataSource(pacientes));
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
						new JRBeanCollectionDataSource(pacientes));
			} catch (JRException e) {
				Mensaje.mensajeError("Error en Reporte");
			}
			return fichero;
		}
	}


}
