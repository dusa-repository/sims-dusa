package controlador.reporte;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.maestros.Paciente;
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
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Tab;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;
import controlador.maestros.CGenerico;

public class CGasto extends CGenerico {

	@Wire
	private Datebox dtbDesde;
	@Wire
	private Datebox dtbHasta;
	@Wire
	private Label lblPaciente;
	@Wire
	private Hbox boxParentesco;
	@Wire
	private Combobox cmbParentescoFamiliar;
	@Wire
	private Combobox cmbTipo;
	@Wire
	private Div divGasto;
	@Wire
	private Div botoneraGasto;
	@Wire
	private Div divCatalogoPaciente;
	Catalogo<Paciente> catalogo;
	String nombre;
	String idPaciente="";
	int tipo;

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
		case "Gastos por Familiares":
			tipo = 1;
			break;
		case "Gastos por Trabajador":
			boxParentesco.setVisible(false);
			cmbParentescoFamiliar.setVisible(false);
			tipo = 2;
			break;
		}
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divGasto, nombre, tabs);
			}

			@Override
			public void limpiar() {
				dtbDesde.setValue(fecha);
				dtbHasta.setValue(fecha);
				cmbParentescoFamiliar.setValue("TODOS");
				lblPaciente.setValue("");
			}

			@Override
			public void guardar() {
				if (validar()) {
					Date desde = dtbDesde.getValue();
					Date hasta = dtbHasta.getValue();
					DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
					String fecha1 = fecha.format(desde);
					String fecha2 = fecha.format(hasta);
					String paciente = idPaciente;
					String parentesco = cmbParentescoFamiliar.getValue();
					String tipoReporte = cmbTipo.getValue();
					List<Consulta> consultas = new ArrayList<Consulta>();
					switch (tipo) {
					// Reporte 1

					// No se toma en cuenta si tiene un familiar trabajando en
					// la empresa
					case 1:
						if (parentesco.equals("TODOS")) {
							if (paciente.equals("TODOS"))
								consultas = servicioConsulta
										.buscarEntreFechasFamiliaresTodosTrabajadores(
												desde, hasta);
							else
								consultas = servicioConsulta
										.buscarEntreFechasFamiliaresYUnTrabajador(
												desde, hasta, paciente);
						} else {
							if (paciente.equals("TODOS"))
								consultas = servicioConsulta
										.buscarEntreFechasFamiliaresTodosTrabajadoresUnParentesco(
												desde, hasta, parentesco);
							else
								consultas = servicioConsulta
										.buscarEntreFechasFamiliaresUnTrabajadorYunParentesco(
												desde, hasta, paciente,
												parentesco);
						}
						if (!consultas.isEmpty())
							Clients.evalJavaScript("window.open('"
									+ damePath()
									+ "Reportero?valor=28&valor6="
									+ fecha1
									+ "&valor7="
									+ fecha2
									+ "&valor8="
									+ parentesco
									+ "&valor9="
									+ paciente
									+ "&valor20="
									+ tipoReporte
									+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
						else
							msj.mensajeAlerta(Mensaje.noHayRegistros);
						break;
					// Reporte 2
					case 2:
						if (paciente.equals("TODOS"))
							consultas = servicioConsulta
									.buscarEntreFechasTrabajadores(desde, hasta);
						else
							consultas = servicioConsulta
									.buscarEntreFechasUnTrabajador(desde,
											hasta, paciente);
						if (!consultas.isEmpty())
							Clients.evalJavaScript("window.open('"
									+ damePath()
									+ "Reportero?valor=29&valor6="
									+ fecha1
									+ "&valor7="
									+ fecha2
									+ "&valor9="
									+ paciente
									+ "&valor20="
									+ tipoReporte
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
		Button guardar = (Button) botonera.getChildren().get(0);
		guardar.setLabel("Reporte");
		guardar.setSrc("/public/imagenes/botones/reporte.png");
		botonera.getChildren().get(1).setVisible(false);
		botoneraGasto.appendChild(botonera);
	}

	protected boolean validar() {
		if ((tipo == 1 && (lblPaciente.getValue().compareTo("") == 0 || cmbParentescoFamiliar
				.getValue().compareTo("") == 0))) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else {
			if (tipo == 2 && lblPaciente.getValue().compareTo("") == 0) {
				msj.mensajeError(Mensaje.camposVacios);
				return false;
			} else
				return true;
		}
	}

	/* Muestra el catalogo de los Pacientes */
	@Listen("onClick = #btnBuscarPaciente")
	public void mostrarCatalogoFamiliar() {
		Paciente paciente = new Paciente();
		paciente.setCedula("TODOS");
		paciente.setFicha("TODOS");
		paciente.setPrimerNombre("TODOS");
		paciente.setPrimerApellido("TODOS");
		List<Paciente> lista = new ArrayList<Paciente>();
		lista.add(paciente);
		lista.addAll(servicioPaciente.buscarTodosTrabajadores());
		final List<Paciente> pacientes = lista;
		catalogo = new Catalogo<Paciente>(divCatalogoPaciente,
				"Catalogo de Pacientes", pacientes, "Cedula", "Ficha",
				"Nombre", "Apellido") {

			@Override
			protected List<Paciente> buscar(String valor, String combo) {
				switch (combo) {
				case "Nombre":
					return servicioPaciente.filtroNombre1T(valor);
				case "Cedula":
					return servicioPaciente.filtroCedulaT(valor);
				case "Ficha":
					return servicioPaciente.filtroFichaT(valor);
				case "Apellido":
					return servicioPaciente.filtroApellido1T(valor);
				default:
					return pacientes;
				}
			}

			@Override
			protected String[] crearRegistros(Paciente objeto) {
				String[] registros = new String[4];
				registros[0] = objeto.getCedula();
				registros[1] = objeto.getFicha();
				registros[2] = objeto.getPrimerNombre();
				registros[3] = objeto.getPrimerApellido();
				return registros;
			}

		};
		catalogo.setParent(divCatalogoPaciente);
		catalogo.doModal();
	}

	/* Permite la seleccion de un item del catalogo de trabajadores */
	@Listen("onSeleccion = #divCatalogoPaciente")
	public void seleccinarTrabajador() {
		Paciente paciente = catalogo.objetoSeleccionadoDelCatalogo();
		lblPaciente.setValue(paciente.getPrimerNombre()+" "+paciente.getPrimerApellido());
		idPaciente = paciente.getCedula();
		catalogo.setParent(null);
	}

	public byte[] reporteGastoPorFamiliar(String par6, String par7,
			String par8, String par9, String tipo2) {
		msj = new Mensaje();
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

		List<Consulta> consultas = new ArrayList<Consulta>();
		if (par8.equals("TODOS")) {
			if (par9.equals("TODOS"))
				consultas = getServicioConsulta()
						.buscarEntreFechasFamiliaresTodosTrabajadores(fecha1,
								fecha2);
			else
				consultas = getServicioConsulta()
						.buscarEntreFechasFamiliaresYUnTrabajador(fecha1,
								fecha2, par9);
		} else {
			if (par9.equals("TODOS"))
				consultas = getServicioConsulta()
						.buscarEntreFechasFamiliaresTodosTrabajadoresUnParentesco(
								fecha1, fecha2, par8);
			else
				consultas = getServicioConsulta()
						.buscarEntreFechasFamiliaresUnTrabajadorYunParentesco(
								fecha1, fecha2, par9, par8);
		}
		List<Consulta> consultasFinales = new ArrayList<Consulta>();
		for (int i = 0; i < consultas.size(); i++) {
			Paciente trabajador = getServicioPaciente().buscarPorCedula(
					consultas.get(i).getPaciente().getCedulaFamiliar());
			if (trabajador != null) {
				consultas
						.get(i)
						.getPaciente()
						.setCedulaFamiliar(
								trabajador.getCedula() + " "
										+ trabajador.getPrimerNombre() + " "
										+ trabajador.getPrimerApellido());
				consultas
				.get(i)
				.getPaciente().setDireccion(trabajador.getPrimerNombre() + " "
										+ trabajador.getPrimerApellido());
				double costoMedicinas, costoExamenes, costoEspecialistas, costoEstudios, costoConsultas;
				// Suma lo que ha entregado
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
				consultas.get(i).setEstatura(costoMedicinas * -1);
				consultas.get(i).setPeso(costoExamenes);
				consultas.get(i).setPerimetroForzada(costoEspecialistas);
				consultas.get(i).setPerimetroOmbligo(costoEstudios);
				consultas.get(i).setPerimetroPlena(costoConsultas);
				consultas
						.get(i)
						.getPaciente()
						.setEdad(
								calcularEdad(consultas.get(i).getPaciente()
										.getFechaNacimiento()));
				consultasFinales.add(consultas.get(i));
				consultas.get(i).getPaciente()
						.setCedulaFamiliar(trabajador.getCedula());
			} else {
				consultas.remove(i);
				i--;
			}
		}
		Map p = new HashMap();
		p.put("desde", par6);
		p.put("hasta", par7);
		JasperReport reporte = null;
		try {
			reporte = (JasperReport) JRLoader.loadObject(getClass()
					.getResource("/reporte/RGastosFamiliar.jasper"));
		} catch (JRException e) {
			msj = new Mensaje();
			msj.mensajeError("Recurso no Encontrado");
		}
		if (tipo2.equals("EXCEL")) {

			JasperPrint jasperPrint = null;
			try {
				jasperPrint = JasperFillManager.fillReport(reporte, p,
						new JRBeanCollectionDataSource(consultasFinales));
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
						new JRBeanCollectionDataSource(consultasFinales));
			} catch (JRException e) {
				msj.mensajeError("Error en Reporte");
			}
			return fichero;
		}
	}

	public byte[] reporteGastoPorTrabajador(String par6, String par7,
			String par9, String tipo2) {
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

		List<Consulta> consultas = new ArrayList<Consulta>();
		if (par9.equals("TODOS"))
			consultas = getServicioConsulta().buscarEntreFechasTrabajadores(
					fecha1, fecha2);
		else
			consultas = getServicioConsulta().buscarEntreFechasUnTrabajador(
					fecha1, fecha2, par9);
		List<Consulta> consultasFinales = new ArrayList<Consulta>();
		for (int i = 0; i < consultas.size(); i++) {
			Consulta consulta = consultas.get(i);
			Paciente trabajador = consulta.getPaciente();
			consultas
					.get(i)
					.getPaciente()
					.setCedulaFamiliar(
							trabajador.getCedula() + " "
									+ trabajador.getPrimerNombre() + " "
									+ trabajador.getPrimerApellido());
			double costoMedicinas, costoExamenes, costoEspecialistas, costoEstudios, costoConsultas;
			// Suma lo que ha entregado
			costoMedicinas = getServicioF4111().sumarPorOrden(
					consulta.getIdConsulta());
			costoExamenes = getServicioConsultaExamen()
					.sumPorConsulta(consulta);
			costoEspecialistas = getServicioConsultaEspecialista()
					.sumPorConsulta(consulta);
			costoEstudios = getServicioConsultaServicioExterno()
					.sumPorConsulta(consulta);
			costoConsultas = (costoMedicinas * -1) + costoExamenes
					+ costoEspecialistas + costoEstudios;
			consultas.get(i).setEstatura(costoMedicinas * -1);
			consultas.get(i).setPeso(costoExamenes);
			consultas.get(i).setPerimetroForzada(costoEspecialistas);
			consultas.get(i).setPerimetroOmbligo(costoEstudios);
			consultas.get(i).setPerimetroPlena(costoConsultas);
			consultas
					.get(i)
					.getPaciente()
					.setEdad(
							calcularEdad(consultas.get(i).getPaciente()
									.getFechaNacimiento()));
			consultasFinales.add(consultas.get(i));
		}
		Map p = new HashMap();
		p.put("desde", par6);
		p.put("hasta", par7);
		JasperReport reporte = null;
		try {
			reporte = (JasperReport) JRLoader.loadObject(getClass()
					.getResource("/reporte/RGastosTrabajador.jasper"));
		} catch (JRException e) {
			msj.mensajeError("Recurso no Encontrado");
		}
		if (tipo2.equals("EXCEL")) {

			JasperPrint jasperPrint = null;
			try {
				jasperPrint = JasperFillManager.fillReport(reporte, p,
						new JRBeanCollectionDataSource(consultasFinales));
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
						new JRBeanCollectionDataSource(consultasFinales));
			} catch (JRException e) {
				msj.mensajeError("Error en Reporte");
			}
			return fichero;
		}
	}

}
