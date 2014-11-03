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

import modelo.maestros.Especialista;
import modelo.maestros.Examen;
import modelo.maestros.Paciente;
import modelo.maestros.Proveedor;
import modelo.seguridad.Usuario;
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
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Tab;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;
import controlador.maestros.CGenerico;

public class COrdenProveedor extends CGenerico {

	private static final long serialVersionUID = -2596222968490717986L;
	@Wire
	private Div catalogoEspecialista;
	@Wire
	private Div catalogoProveedor;
	@Wire
	private Datebox dtbDesde;
	@Wire
	private Datebox dtbHasta;
	@Wire
	private Div divOrdenProveedor;
	@Wire
	private Div botoneraOrdenProveedor;
	@Wire
	private Row rowProveedor;
	@Wire
	private Row rowEspecialista;
	@Wire
	private Label lblProveedor;
	@Wire
	private Label lblEspecialista;
	@Wire
	private Combobox cmbTipo;
	private String titulo = "";
	private Long idProveedor = null;
	private String idEspecialista = null;
	Catalogo<Proveedor> catalogoP;
	Catalogo<Especialista> catalogoE;
	private int tipo;

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
		switch (titulo) {
		case "Gastos por Proveedor":
			rowProveedor.setVisible(true);
			tipo = 1;
			break;
		case "Gastos por Especialista":
			rowEspecialista.setVisible(true);
			tipo = 2;
			break;
		}

		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divOrdenProveedor, titulo, tabs);
			}

			@Override
			public void limpiar() {
				dtbDesde.setValue(fecha);
				dtbHasta.setValue(fecha);
				lblEspecialista.setValue("");
				lblProveedor.setValue("");
				idEspecialista = null;
				idProveedor = null;
			}

			@Override
			public void guardar() {
				if (validar()) {

					Date desde = dtbDesde.getValue();
					Date hasta = dtbHasta.getValue();
					String tipoReporte = cmbTipo.getValue();
					DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
					String fecha1 = fecha.format(desde);
					String fecha2 = fecha.format(hasta);
					List<ConsultaEspecialista> especialistas = new ArrayList<ConsultaEspecialista>();
					List<ConsultaExamen> examenes = new ArrayList<ConsultaExamen>();
					List<ConsultaServicioExterno> estudios = new ArrayList<ConsultaServicioExterno>();
					switch (tipo) {
					case 1:
						Proveedor proveedor = servicioProveedor
								.buscar(idProveedor);
						if (idProveedor != 0) {
							examenes = servicioConsultaExamen
									.buscarPorProveedorEntreFechas(desde,
											hasta, proveedor);
							estudios = servicioConsultaServicioExterno
									.buscarPorProveedorEntreFechas(desde,
											hasta, proveedor);
						} else {
							examenes = servicioConsultaExamen
									.buscarEntreFechas(desde, hasta);
							estudios = servicioConsultaServicioExterno
									.buscarEntreFechas(desde, hasta);
						}
						if (examenes.isEmpty() && estudios.isEmpty())
							msj.mensajeAlerta(Mensaje.noHayRegistros);
						else
							Clients.evalJavaScript("window.open('"
									+ damePath()
									+ "Reportero?valor=31&valor6="
									+ fecha1
									+ "&valor7="
									+ fecha2
									+ "&valor2="
									+ idProveedor
									+ "&valor20="
									+ tipoReporte
									+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
						break;

					case 2:
						Especialista especialista = servicioEspecialista
								.buscar(idEspecialista);
						if (!idEspecialista.equals("0"))
							especialistas = servicioConsultaEspecialista
									.buscarPorEspecialistaEntreFechas(desde,
											hasta, especialista);
						else
							especialistas = servicioConsultaEspecialista
									.buscarEntreFechas(desde, hasta);
						if (!especialistas.isEmpty())
							Clients.evalJavaScript("window.open('"
									+ damePath()
									+ "Reportero?valor=32&valor6="
									+ fecha1
									+ "&valor7="
									+ fecha2
									+ "&valor8="
									+ idEspecialista
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
		botoneraOrdenProveedor.appendChild(botonera);
	}

	public byte[] reporteProveedor(String par6, String par7, Long part2,
			String tipo2) {
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
		List<ConsultaExamen> examenes = new ArrayList<ConsultaExamen>();
		List<ConsultaServicioExterno> estudios = new ArrayList<ConsultaServicioExterno>();
		List<ConsultaExamen> lista = new ArrayList<ConsultaExamen>();
		Proveedor proveedor = getServicioProveedor().buscar(part2);
		if (part2 != 0) {
			examenes = getServicioConsultaExamen()
					.buscarPorProveedorEntreFechas(fecha1, fecha2, proveedor);
			estudios = getServicioConsultaServicioExterno()
					.buscarPorProveedorEntreFechas(fecha1, fecha2, proveedor);
		} else {
			examenes = getServicioConsultaExamen().buscarEntreFechas(fecha1,
					fecha2);
			estudios = getServicioConsultaServicioExterno().buscarEntreFechas(
					fecha1, fecha2);
		}
		if (!examenes.isEmpty()) {
			lista.addAll(examenes);
			for (int j = 0; j < lista.size(); j++) {
				lista.get(j).setResultado("EXAMENES");
			}
			for (int i = 0; i < estudios.size(); i++) {
				ConsultaExamen consultaExamen = new ConsultaExamen();
				consultaExamen.setConsulta(estudios.get(i).getConsulta());
				consultaExamen.setProveedor(estudios.get(i).getProveedor());
				consultaExamen.setCosto(estudios.get(i).getCosto());
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
				consultaExamen.setCosto(estudios.get(i).getCosto());
				Examen examen = new Examen();
				examen.setNombre(estudios.get(i).getServicioExterno()
						.getNombre());
				consultaExamen.setExamen(examen);
				consultaExamen.setResultado("ESTUDIOS");
				lista.add(consultaExamen);
			}
		}

		Map p = new HashMap();
		p.put("desde", par6);
		p.put("hasta", par7);
		JasperReport reporte = null;
		try {
			reporte = (JasperReport) JRLoader.loadObject(getClass()
					.getResource("/reporte/RCostoProveedor.jasper"));
		} catch (JRException e) {
			msj.mensajeError("Recurso no Encontrado");
		}

		if (tipo2.equals("EXCEL")) {

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
				msj.mensajeError("Error en Reporte");
			}
			return fichero;
		}
	}

	public byte[] reporteEspecialista(String par6, String par7, String par8,
			String tipo2) {
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

		List<ConsultaEspecialista> especialistas = new ArrayList<ConsultaEspecialista>();
		Especialista especialista = getServicioEspecialista().buscar(par8);
		if (!par8.equals("0"))
			especialistas = getServicioConsultaEspecialista()
					.buscarPorEspecialistaEntreFechas(fecha1, fecha2,
							especialista);
		else
			especialistas = getServicioConsultaEspecialista()
					.buscarEntreFechas(fecha1, fecha2);

		Map p = new HashMap();
		p.put("desde", par6);
		p.put("hasta", par7);
		JasperReport reporte = null;
		try {
			reporte = (JasperReport) JRLoader.loadObject(getClass()
					.getResource("/reporte/RCostoEspecialista.jasper"));
		} catch (JRException e) {
			msj.mensajeError("Recurso no Encontrado");
		}

		if (tipo2.equals("EXCEL")) {

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
				msj.mensajeError("Error en Reporte");
			}
			return fichero;
		}
	}

	protected boolean validar() {
		if ((tipo == 1 && idProveedor == null)
				|| (tipo == 2 && idEspecialista == null)) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	@Listen("onClick = #btnBuscarEspecialista")
	public void mostrarCatalogo() {
		final List<Especialista> especialistas = new ArrayList<Especialista>();
		Especialista especialista = new Especialista("0", "TODOS", "TODOS",
				0.0, fechaHora, "TODOS", "TODOS", null, "TODOS", "TODOS");
		especialistas.add(especialista);
		especialistas.addAll(servicioEspecialista.buscarTodos());
		catalogoE = new Catalogo<Especialista>(catalogoEspecialista,
				"Catalogo de Especialistas", especialistas, "Cedula", "Nombre",
				"Apellido", "Costo Servicio", "Especialidad") {

			@Override
			protected List<Especialista> buscar(String valor, String combo) {

				switch (combo) {
				case "Cedula":
					return servicioEspecialista.filtroCedula(valor);
				case "Nombre":
					return servicioEspecialista.filtroNombre(valor);
				case "Apellido":
					return servicioEspecialista.filtroApellido(valor);
				case "Costo Servicio":
					return servicioEspecialista.filtroCosto(valor);
				case "Especialidad":
					return servicioEspecialista.filtroEspecialidad(valor);
				default:
					return especialistas;
				}
			}

			@Override
			protected String[] crearRegistros(Especialista objeto) {
				String especialidad = "";
				if (objeto.getEspecialidad() != null)
					especialidad = objeto.getEspecialidad().getDescripcion();
				String[] registros = new String[5];
				registros[0] = objeto.getCedula();
				registros[1] = objeto.getNombre();
				registros[2] = objeto.getApellido();
				registros[3] = String.valueOf(objeto.getCosto());
				registros[4] = especialidad;
				return registros;
			}

		};
		catalogoE.setParent(catalogoEspecialista);
		catalogoE.doModal();
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoEspecialista")
	public void seleccinarEspecialista() {
		Especialista especialista = catalogoE.objetoSeleccionadoDelCatalogo();
		lblEspecialista.setValue(especialista.getNombre() + " "
				+ especialista.getApellido());
		idEspecialista = especialista.getCedula();
		catalogoE.setParent(null);
	}

	@Listen("onClick = #btnBuscarProveedor")
	public void mostrarCatalogoProveedor() {
		final List<Proveedor> proveedores = new ArrayList<Proveedor>();
		Proveedor proveedor = new Proveedor(0, "TODOS", "TODOS", "TODOS",
				fechaHora, "TODOS", "TODOS", null, 0.0);
		proveedores.add(proveedor);
		proveedores.addAll(servicioProveedor.buscarTodos());
		catalogoP = new Catalogo<Proveedor>(catalogoProveedor,
				"Catalogo de Proveedores", proveedores, "Nombre", "Direccion",
				"Telefono", "Ciudad") {

			@Override
			protected List<Proveedor> buscar(String valor, String combo) {

				switch (combo) {
				case "Nombre":
					return servicioProveedor.filtroNombre(valor);
				case "Direccion":
					return servicioProveedor.filtroDireccion(valor);
				case "Telefono":
					return servicioProveedor.filtroTelefono(valor);
				case "Ciudad":
					return servicioProveedor.filtroCiudad(valor);
				default:
					return proveedores;
				}
			}

			@Override
			protected String[] crearRegistros(Proveedor objeto) {
				String ciudad = "";
				if (objeto.getCiudad() != null)
					ciudad = objeto.getCiudad().getNombre();
				String[] registros = new String[4];
				registros[0] = objeto.getNombre();
				registros[1] = objeto.getDireccion();
				registros[2] = objeto.getTelefono();
				registros[3] = ciudad;
				return registros;
			}

		};
		catalogoP.setParent(catalogoProveedor);
		catalogoP.doModal();
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoProveedor")
	public void seleccinar() {
		Proveedor proveedor = catalogoP.objetoSeleccionadoDelCatalogo();
		lblProveedor.setValue(proveedor.getNombre());
		idProveedor = proveedor.getIdProveedor();
		catalogoP.setParent(null);
	}

}
