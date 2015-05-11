package controlador.reporte;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.maestros.Paciente;
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
import org.zkoss.zul.Div;
import org.zkoss.zul.Tab;

import componentes.Botonera;

import controlador.maestros.CGenerico;

public class CResumenTrabajadores extends CGenerico {

	@Wire
	private Div divResumenTrabajadores;
	@Wire
	private Div botoneraResumenTrabajadores;
	@Wire
	private Combobox cmbTipo;

	@Override
	public void inicializar() throws IOException {

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

		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divResumenTrabajadores, titulo, tabs);

			}

			@Override
			public void limpiar() {
				cmbTipo.setValue("PDF");
			}

			@Override
			public void guardar() {

				Clients.evalJavaScript("window.open('"
						+ damePath()
						+ "Reportero?valor=55&valor20="
						+ cmbTipo.getValue()
						+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
			}

			@Override
			public void eliminar() {
			}
		};
		Button guardar = (Button) botonera.getChildren().get(0);
		guardar.setLabel("Reporte");
		guardar.setSrc("/public/imagenes/botones/reporte.png");
		botonera.getChildren().get(1).setVisible(false);
		botoneraResumenTrabajadores.appendChild(botonera);
	}

	public byte[] reporteResumen(String tipoReporte) throws JRException {
		byte[] fichero = null;
		List<Paciente> pacientes = new ArrayList<Paciente>();
		List<Paciente> pacientes2 = new ArrayList<Paciente>();
		List<Paciente> pacientes3 = new ArrayList<Paciente>();

		pacientes = getServicioPaciente()
				.buscarTodosTrabajadoresOrdenadosNomina();
		pacientes2 = getServicioPaciente()
				.buscarTodosTrabajadoresOrdenadosNivel();
		pacientes3 = getServicioPaciente()
				.buscarTodosTrabajadoresOrdenadosCiudad();

		int edades = 0;
		double promedio = 0;
		if (!pacientes.isEmpty()) {
			for (int i = 0; i < pacientes.size(); i++) {
				if (pacientes.get(i).getFechaNacimiento() != null) {
					edades = edades + pacientes.get(i).getEdad();
				}

				promedio = (edades / pacientes.size());
			}
		}
		Map<String, Object> p = new HashMap<String, Object>();

		p.put("17M", getServicioPaciente().buscarEdadySexo("Masculino", 0, 17)
				.size());
		p.put("17F", getServicioPaciente().buscarEdadySexo("Femenino", 0, 17)
				.size());
		p.put("18M", getServicioPaciente().buscarEdadySexo("Masculino", 18, 25)
				.size());
		p.put("18F", getServicioPaciente().buscarEdadySexo("Femenino", 18, 25)
				.size());
		p.put("26M", getServicioPaciente().buscarEdadySexo("Masculino", 26, 30)
				.size());
		p.put("26F", getServicioPaciente().buscarEdadySexo("Femenino", 26, 30)
				.size());
		p.put("31M", getServicioPaciente().buscarEdadySexo("Masculino", 31, 35)
				.size());
		p.put("31F", getServicioPaciente().buscarEdadySexo("Femenino", 31, 35)
				.size());
		p.put("36M", getServicioPaciente().buscarEdadySexo("Masculino", 36, 40)
				.size());
		p.put("36F", getServicioPaciente().buscarEdadySexo("Femenino", 36, 40)
				.size());
		p.put("41M", getServicioPaciente().buscarEdadySexo("Masculino", 41, 45)
				.size());
		p.put("41F", getServicioPaciente().buscarEdadySexo("Femenino", 41, 45)
				.size());
		p.put("46M", getServicioPaciente().buscarEdadySexo("Masculino", 46, 50)
				.size());
		p.put("46F", getServicioPaciente().buscarEdadySexo("Femenino", 46, 50)
				.size());
		p.put("51M", getServicioPaciente().buscarEdadySexo("Masculino", 51, 55)
				.size());
		p.put("51F", getServicioPaciente().buscarEdadySexo("Femenino", 51, 55)
				.size());
		p.put("56M", getServicioPaciente().buscarEdadySexo("Masculino", 56, 60)
				.size());
		p.put("56F", getServicioPaciente().buscarEdadySexo("Femenino", 56, 60)
				.size());
		p.put("61M", getServicioPaciente().buscarEdadySexo("Masculino", 61, 65)
				.size());
		p.put("61F", getServicioPaciente().buscarEdadySexo("Femenino", 61, 65)
				.size());
		p.put("66M", getServicioPaciente()
				.buscarEdadySexo("Masculino", 66, 150).size());
		p.put("66F", getServicioPaciente().buscarEdadySexo("Femenino", 66, 150)
				.size());

		DecimalFormat df = new DecimalFormat("##.##");
		df.setRoundingMode(RoundingMode.DOWN);
		p.put("promedio", df.format(promedio));
		p.put("total", pacientes.size());

		p.put("dataNomina", new JRBeanCollectionDataSource(pacientes));
		p.put("dataNomina2", new JRBeanCollectionDataSource(pacientes));
		p.put("dataNivelEducativo", new JRBeanCollectionDataSource(pacientes2));
		p.put("dataCiudad", new JRBeanCollectionDataSource(pacientes3));

		JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass()
				.getResource("/reporte/RResumenTrabajadores.jasper"));


		if (tipoReporte.equals("EXCEL")) {

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
			fichero = JasperRunManager.runReportToPdf(reporte, p,
					new JRBeanCollectionDataSource(pacientes));
			return fichero;
		}
	}
}
