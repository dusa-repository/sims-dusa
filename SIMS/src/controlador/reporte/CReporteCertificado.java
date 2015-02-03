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

import modelo.maestros.Paciente;
import modelo.maestros.Periodo;
import modelo.maestros.PeriodoPaciente;
import modelo.sha.Area;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaDiagnostico;
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
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;
import controlador.maestros.CGenerico;

public class CReporteCertificado extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 490625274979903974L;
	@Wire
	private Div divReporteCertificado;
	@Wire
	private Div botoneraReporteCertificado;
	@Wire
	private Div catalogoPeriodo;
	@Wire
	private Textbox txtPeriodo;
	@Wire
	private Combobox cmbResultados;
	@Wire
	private Combobox cmbTipo;
	@Wire
	private Radio rdoSi;
	@Wire
	private Radio rdoNo;
	Catalogo<Periodo> catalogo;
	String nombre = "";
	Long idPeriodo = null;

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
				cerrarVentana(divReporteCertificado, nombre, tabs);
			}

			@Override
			public void limpiar() {
				idPeriodo = null;
				txtPeriodo.setValue("");
				cmbResultados.setValue("TODOS");
				rdoSi.setChecked(true);
			}

			@Override
			public void guardar() {
				if (txtPeriodo.getText().compareTo("") != 0) {
					String tipoReporte = cmbTipo.getValue();
					String resultado = cmbResultados.getValue();
					String vdrl = "NO REACTIVO";
					String todos = "Si";
					if (rdoNo.isChecked())
						todos = "No";
					if (resultado.equals("NORMALES"))
						resultado = "NORMAL";
					List<PeriodoPaciente> lista = new ArrayList<PeriodoPaciente>();
					if (todos.equalsIgnoreCase("Si"))
						if (!resultado.contains("ANORMALES")) {
							lista = servicioPeriodoPaciente
									.buscarPorIdPeriodoYLikePositivo(idPeriodo,
											vdrl, resultado);
						} else
							lista = servicioPeriodoPaciente
									.buscarPorIdPeriodoYNotLikePositivo(
											idPeriodo, vdrl, resultado);
					else
						lista = servicioPeriodoPaciente
								.buscarSinCertificado(idPeriodo);
					if (!lista.isEmpty())
						Clients.evalJavaScript("window.open('"
								+ damePath()
								+ "Reportero?valor=41&valor2="
								+ idPeriodo
								+ "&valor7="
								+ todos
								+ "&valor8="
								+ resultado
								+ "&valor9="
								+ vdrl
								+ "&valor20="
								+ tipoReporte
								+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
					else
						Mensaje.mensajeAlerta(Mensaje.noHayRegistros);
				} else
					Mensaje.mensajeError("Debe seleccionar un Periodo");
			}

			@Override
			public void eliminar() {
			}
		};
		Button guardar = (Button) botonera.getChildren().get(0);
		guardar.setLabel("Reporte");
		guardar.setImage("/public/imagenes/botones/reporte.png");
		botonera.getChildren().get(1).setVisible(false);
		botoneraReporteCertificado.appendChild(botonera);
	}

	public byte[] reporteCertificado(Long part2, String todos,
			String resultado, String vdrl, String tipo) {
		byte[] fichero = null;

		List<PeriodoPaciente> lista = new ArrayList<PeriodoPaciente>();
		if (todos.equalsIgnoreCase("Si"))
			if (!resultado.contains("ANORMALES")) {
				lista = getServicioPeriodoPaciente()
						.buscarPorIdPeriodoYLikePositivo(part2, vdrl, resultado);
			} else
				lista = getServicioPeriodoPaciente()
						.buscarPorIdPeriodoYNotLikePositivo(part2, vdrl,
								resultado);
		else
			lista = getServicioPeriodoPaciente().buscarSinCertificado(part2);

		Periodo periodo = lista.get(0).getPeriodo();
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("periodo", periodo.getNombre());
		p.put("desde", periodo.getFechaInicio());
		p.put("hasta", periodo.getFechaFin());
		if (todos.equalsIgnoreCase("Si"))
			if (!resultado.equals("ANORMALES"))
				if (resultado.equals("NORMALES"))
					p.put("pacientes",
							"Con entrega de resultados (SOLO VALORES NORMALES)");
				else
					p.put("pacientes",
							"Con entrega de resultados (TODOS LOS VALORES)");
			else
				p.put("pacientes",
						"Con entrega de resultados (SOLO VALORES ANORMALES)");
		else
			p.put("pacientes", "Sin entrega de resultados");
		// p.put("data", new JRBeanCollectionDataSource(consuta));

		JasperReport reporte = null;
		try {
			reporte = (JasperReport) JRLoader.loadObject(getClass()
					.getResource("/reporte/RCertificado.jasper"));
		} catch (JRException e1) {
			e1.printStackTrace();
		}
		if (tipo.equals("EXCEL")) {

			JasperPrint jasperPrint = null;
			try {
				jasperPrint = JasperFillManager.fillReport(reporte, p,
						new JRBeanCollectionDataSource(lista));
			} catch (JRException e) {
				e.printStackTrace();
			}
			ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
			try {
				exporter.exportReport();
			} catch (JRException e) {
				e.printStackTrace();
			}
			return xlsReport.toByteArray();
		} else {

			try {
				fichero = JasperRunManager.runReportToPdf(reporte, p,
						new JRBeanCollectionDataSource(lista));
			} catch (JRException e) {
				e.printStackTrace();
			}
			return fichero;
		}
	}

	/* Muestra el catalogo de los paises */
	@Listen("onClick = #btnBuscar")
	public void mostrarCatalogo() {
		final List<Periodo> periodos = servicioPeriodo.buscarTodos();
		catalogo = new Catalogo<Periodo>(catalogoPeriodo,
				"Catalogo de Periodos", periodos, false, "Nombre",
				"Fecha Inicio", "Fecha Fin") {
			private static final long serialVersionUID = 2968389472159832753L;

			@Override
			protected List<Periodo> buscar(String valor, String combo) {
				switch (combo) {
				case "Nombre":
					return servicioPeriodo.filtroNombre(valor);
				case "Fecha Inicio":
					return servicioPeriodo.filtroInicio(valor);
				case "Fecha Fin":
					return servicioPeriodo.filtroFin(valor);
				default:
					return periodos;
				}

			}

			@Override
			protected String[] crearRegistros(Periodo estado) {
				String[] registros = new String[3];
				registros[0] = estado.getNombre();
				registros[1] = traerFecha2(estado.getFechaInicio());
				registros[2] = traerFecha2(estado.getFechaFin());
				return registros;
			}
		};
		catalogo.setParent(catalogoPeriodo);
		catalogo.doModal();
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoPeriodo")
	public void seleccinar() {
		Periodo periodo = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(periodo);
		catalogo.setParent(null);
	}

	/* Busca si existe un pais con el mismo nombre escrito */
	@Listen("onChange = #txtPeriodo")
	public void buscarPorNombre() {
		Periodo periodo = servicioPeriodo
				.buscarPorNombre(txtPeriodo.getValue());
		if (periodo != null)
			llenarCampos(periodo);
		else {
			idPeriodo = null;
			txtPeriodo.setValue("");
			Mensaje.mensajeAlerta(Mensaje.noHayRegistros);
		}
	}

	/* LLena los campos del formulario dado un pais */
	private void llenarCampos(Periodo periodo) {
		txtPeriodo.setValue(periodo.getNombre());
		idPeriodo = periodo.getIdPeriodo();
	}

}
