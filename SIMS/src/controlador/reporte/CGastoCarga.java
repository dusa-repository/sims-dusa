package controlador.reporte;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

public class CGastoCarga extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Datebox dtbDesde;
	@Wire
	private Datebox dtbHasta;
	@Wire
	private Combobox cmbParentesco;
	@Wire
	private Combobox cmbTipo;
	@Wire
	private Div botoneraGastoCarga;
	@Wire
	private Div divGastoCarga;
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
				cerrarVentana(divGastoCarga, nombre, tabs);
			}

			@Override
			public void limpiar() {
				dtbDesde.setValue(fecha);
				dtbHasta.setValue(fecha);
				cmbParentesco.setValue("TODOS");
			}

			@Override
			public void guardar() {
				Date desde = dtbDesde.getValue();
				Date hasta = dtbHasta.getValue();
				String fecha1 = formatoReporte.format(desde);
				String fecha2 = formatoReporte.format(hasta);
				String parentesco = cmbParentesco.getValue();
				if (parentesco.equals("TODOS"))
					parentesco = "%";
				String tipoReporte = cmbTipo.getValue();
				List<Consulta> consultas = getServicioConsulta()
						.buscarEntreFechasFamiliaresTodosTrabajadoresYParentescoLike(
								desde, hasta, parentesco);
				List<Orden> ordenes = getServicioOrden()
						.buscarEntreFechasFamiliaresTodosTrabajadoresYParentescoLike(
								desde, hasta, parentesco);
				if (parentesco.equals("%"))
					parentesco = "TODOS";
				if (!consultas.isEmpty() || !ordenes.isEmpty())
					Clients.evalJavaScript("window.open('"
							+ damePath()
							+ "Reportero?valor=56&valor6="
							+ fecha1
							+ "&valor7="
							+ fecha2
							+ "&valor8="
							+ parentesco
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
		botoneraGastoCarga.appendChild(botonera);
	}

	public byte[] reporteResumenGasto(String par6, String par7, String par8,
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
		if (par8.equals("TODOS"))
			par8 = "%";
		List<Consulta> consultas = getServicioConsulta()
				.buscarEntreFechasFamiliaresTodosTrabajadoresYParentescoLike(
						fecha1, fecha2, par8);
		List<Orden> ordenes = getServicioOrden()
				.buscarEntreFechasFamiliaresTodosTrabajadoresYParentescoLike(
						fecha1, fecha2, par8);
		List<Resumen> listaFinal = new ArrayList<Resumen>();
		List<Resumen> listaConsultas = new ArrayList<Resumen>();
		List<Resumen> listaOrdenes = new ArrayList<Resumen>();
		String parentesco = "";
		if (!consultas.isEmpty() && !ordenes.isEmpty()) {
			parentesco = consultas.get(0).getPaciente().getParentescoFamiliar();
			listaConsultas = recorrerConsultas(consultas, parentesco);
			listaOrdenes = recorrerOrdenes(ordenes, parentesco);
			for (int i = 0; i < listaOrdenes.size(); i++) {
				String parentescoReal = listaOrdenes.get(i).getNombre1();
				listaFinal.add(listaOrdenes.get(i));
				for (int j = 0; j < listaConsultas.size(); j++) {
					if (listaConsultas.get(j).getNombre1()
							.equals(parentescoReal)) {
						listaFinal.add(listaConsultas.get(j));
						listaConsultas.remove(j);
						j--;
					}
				}
				if (i == listaOrdenes.size() - 1 && listaConsultas.size() != 0) {
					listaFinal.addAll(listaConsultas);
				}
			}
		} else {
			if (consultas.isEmpty()) {
				parentesco = ordenes.get(0).getPaciente()
						.getParentescoFamiliar();
				listaFinal.addAll(recorrerOrdenes(ordenes, parentesco));
			} else {
				parentesco = consultas.get(0).getPaciente()
						.getParentescoFamiliar();
				listaFinal.addAll(recorrerConsultas(consultas, parentesco));
			}

		}
		if (par8.equals("%"))
			par8 = "TODOS";
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("desde", par6);
		p.put("hasta", par7);
		p.put("parentesco", par8);
		JasperReport reporte = null;
		try {
			reporte = (JasperReport) JRLoader.loadObject(getClass()
					.getResource("/reporte/RGastoCarga.jasper"));
		} catch (JRException e) {
			msj = new Mensaje();
			Mensaje.mensajeError("Recurso no Encontrado");
		}
		if (tipo2.equals("EXCEL")) {

			JasperPrint jasperPrint = null;
			try {
				jasperPrint = JasperFillManager.fillReport(reporte, p,
						new JRBeanCollectionDataSource(listaFinal));
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
						new JRBeanCollectionDataSource(listaFinal));
			} catch (JRException e) {
				Mensaje.mensajeError("Error en Reporte");
			}
			return fichero;
		}
	}

	private List<Resumen> recorrerConsultas(List<Consulta> consultas,
			String parentesco) {
		List<Resumen> lista = new ArrayList<Resumen>();
		for (int i = 0; i < consultas.size(); i++) {
			double costoMedicinas, costoExamenes, costoEspecialistas, costoEstudios, costoConsultas;
			costoMedicinas = getServicioF4111().sumarPorOrden(
					consultas.get(i).getIdConsulta());
			costoExamenes = getServicioConsultaExamen().sumPorConsulta(
					consultas.get(i));
			costoEspecialistas = getServicioConsultaEspecialista()
					.sumPorConsulta(consultas.get(i));
			costoEstudios = getServicioConsultaServicioExterno()
					.sumPorConsulta(consultas.get(i));
			costoConsultas = (costoMedicinas * -1) + costoExamenes
					+ costoEspecialistas + costoEstudios;
			Resumen resumen = new Resumen();
			resumen.setNombre1(consultas.get(i).getPaciente()
					.getParentescoFamiliar());
			resumen.setValor7(costoConsultas);
			lista.add(resumen);
		}
		return lista;
	}

	private List<Resumen> recorrerOrdenes(List<Orden> ordenes, String parentesco) {
		List<Resumen> lista = new ArrayList<Resumen>();
		for (int i = 0; i < ordenes.size(); i++) {
			double costoMedicinas, costoExamenes, costoEspecialistas, costoEstudios, costoConsultas;
			// Suma lo que ha entregado
			costoMedicinas = getServicioF4111().sumarPorOrden(
					ordenes.get(i).getIdOrden());
			costoExamenes = getServicioOrdenExamen()
					.sumPorOrden(ordenes.get(i));
			costoEspecialistas = getServicioOrdenEspecialista().sumPorOrden(
					ordenes.get(i));
			costoEstudios = getServicioOrdenServicioExterno().sumPorOrden(
					ordenes.get(i));
			costoConsultas = (costoMedicinas * -1) + costoExamenes
					+ costoEspecialistas + costoEstudios;
			Resumen resumen = new Resumen();
			resumen.setNombre1(ordenes.get(i).getPaciente()
					.getParentescoFamiliar());
			resumen.setValor7(costoConsultas);
			lista.add(resumen);
		}
		return lista;
	}
}
