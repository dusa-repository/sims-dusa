package controlador.reporte;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import componentes.Mensaje;

import controlador.maestros.CGenerico;

public class CResumenParentesco extends CGenerico {

	@Wire
	private Div divResumenParentesco;
	@Wire
	private Div botoneraResumenParentesco;
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
				cerrarVentana(divResumenParentesco, titulo, tabs);

			}

			@Override
			public void limpiar() {
				cmbTipo.setValue("PDF");
			}

			@Override
			public void guardar() {

				Clients.evalJavaScript("window.open('"
						+ damePath()
						+ "Reportero?valor=58&valor20="
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
		botoneraResumenParentesco.appendChild(botonera);
	}

	public byte[] reporteResumen(String tipo2) throws JRException {
		byte[] fichero = null;

		Map<String, Object> p = new HashMap<String, Object>();

		int con = 0, hermano = 0, concu = 0, hijastro = 0, hijo = 0, padre = 0, madre = 0, nieto = 0, yerno = 0, suegro = 0, abuelo = 0, otro = 0, no = 0;

		List<Paciente> pa = getServicioPaciente()
				.buscarPorParentesco("Conyuge");
		if (pa != null)
			con = pa.size();
		List<Paciente> he = getServicioPaciente().buscarPorParentesco(
				"Hermano(a)");
		if (he != null)
			hermano = he.size();
		List<Paciente> conc = getServicioPaciente().buscarPorParentesco(
				"Concubino(a)");
		if (conc != null)
			concu = conc.size();
		List<Paciente> hijas = getServicioPaciente().buscarPorParentesco(
				"Hijastro(a)");
		if (hijas != null)
			hijastro = hijas.size();
		List<Paciente> hij = getServicioPaciente().buscarPorParentesco("Hijo(a)");
		if (hij != null)
			hijo = hij.size();
		List<Paciente> pad = getServicioPaciente().buscarPorParentesco("Padre");
		if (pad != null)
			padre = pad.size();
		List<Paciente> mad = getServicioPaciente().buscarPorParentesco("Madre");
		if (mad != null)
			madre = mad.size();
		List<Paciente> nie = getServicioPaciente().buscarPorParentesco("Nieto(a)");
		if (nie != null)
			nieto = nie.size();
		List<Paciente> yer = getServicioPaciente().buscarPorParentesco("Yerno/Nuera");
		if (yer != null)
			yerno = yer.size();
		List<Paciente> sue = getServicioPaciente().buscarPorParentesco("Suegro(a)");
		if (sue != null)
			suegro = sue.size();
		List<Paciente> abu = getServicioPaciente().buscarPorParentesco("Abuelo(a)");
		if (abu != null)
			abuelo = abu.size();
		List<Paciente> ot = getServicioPaciente().buscarPorParentesco("Otro");
		if (ot != null)
			otro = ot.size();
		List<Paciente> n = getServicioPaciente().buscarPorParentesco("N/A");
		if (n != null)
			no = n.size();

		p.put("conyuge", con);
		p.put("hermano", hermano);
		p.put("concubino", concu);
		p.put("hijastro", hijastro);
		p.put("hijo", hijo);
		p.put("padre", padre);
		p.put("madre", madre);
		p.put("nieto", nieto);
		p.put("yerno", yerno);
		p.put("suegro", suegro);
		p.put("abuelo", abuelo);
		p.put("otro", otro);
		p.put("no", no);
		p.put("total", con + hermano + concu + hijastro + hijo + padre + madre
				+ nieto + yerno + suegro + abuelo + otro + no);

		JasperReport reporte = null;
		try {
			reporte = (JasperReport) JRLoader.loadObject(getClass()
					.getResource("/reporte/RResumenParentesco.jasper"));
		} catch (JRException e) {
			msj = new Mensaje();
			Mensaje.mensajeError("Recurso no Encontrado");
		}
		if (tipo2.equals("EXCEL")) {

			JasperPrint jasperPrint = null;
			try {
				jasperPrint = JasperFillManager.fillReport(reporte, p);
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
				fichero = JasperRunManager.runReportToPdf(reporte, p);
			} catch (JRException e) {
				Mensaje.mensajeError("Error en Reporte");
			}
			return fichero;
		}
	}
}
