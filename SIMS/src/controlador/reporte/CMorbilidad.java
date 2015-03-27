package controlador.reporte;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import modelo.maestros.Cargo;
import modelo.maestros.CategoriaDiagnostico;
import modelo.maestros.ClasificacionDiagnostico;
import modelo.maestros.Diagnostico;
import modelo.maestros.Empresa;
import modelo.maestros.Nomina;
import modelo.maestros.Paciente;
import modelo.seguridad.Usuario;
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

import org.json.JSONException;
import org.json.JSONObject;
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
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Buscar;
import componentes.Catalogo;
import componentes.Mensaje;
import controlador.maestros.CGenerico;

public class CMorbilidad extends CGenerico {

	private static final long serialVersionUID = 7679068123639788205L;
	@Wire
	private Textbox txtBuscadorDiagnostico;
	@Wire
	private Div catalogoUsuarios;
	@Wire
	private Combobox cmbArea;
	@Wire
	private Combobox cmbCargo;
	@Wire
	private Combobox cmbCargo2;
	@Wire
	private Combobox cmbEmpresa;
	@Wire
	private Combobox cmbNomina;
	@Wire
	private Combobox cmbClasificacion;
	@Wire
	private Combobox cmbCategoria;
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
	private Row rowCargo;
	@Wire
	private Row rowEmpresa;
	@Wire
	private Row rowNomina;
	@Wire
	private Row rowClasificacion;
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
	@Wire
	private Combobox cmbTipo;
	@Wire
	private Hbox box;
	@Wire
	private Hbox box2;
	@Wire
	private Listbox ltbDiagnosticos;
	@Wire
	private Listbox ltbDiagnosticosAgregados;
	List<Diagnostico> diagnosticosDisponibles = new ArrayList<Diagnostico>();
	List<Diagnostico> diagnosticosAgregados = new ArrayList<Diagnostico>();
	Buscar<Diagnostico> buscarDiagnostico;
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
		String todos = "TODAS";
		switch (titulo) {
		case "Morbilidad Por Clasificacion de Diagnostico":
			rowClasificacion.setVisible(true);
			rowArea.setVisible(false);
			rowDiagnostico.setVisible(false);
			rowDoctor.setVisible(false);
			rowFamiliar.setVisible(false);
			rowPaciente.setVisible(false);
			rowTipoConsulta.setVisible(false);
			tipo = "clasificacion";
			ClasificacionDiagnostico clasificacion = new ClasificacionDiagnostico();
			clasificacion.setNombre(todos);
			clasificacion.setIdClasificacion(0);
			List<ClasificacionDiagnostico> clasificaciones = new ArrayList<ClasificacionDiagnostico>();
			clasificaciones.add(clasificacion);
			clasificaciones.addAll(servicioClasificacion.buscarTodas());
			cmbClasificacion
					.setModel(new ListModelList<ClasificacionDiagnostico>(
							clasificaciones));
			CategoriaDiagnostico categoria = new CategoriaDiagnostico();
			categoria.setNombre(todos);
			categoria.setIdCategoriaDiagnostico(0);
			List<CategoriaDiagnostico> categorias = new ArrayList<CategoriaDiagnostico>();
			categorias.add(categoria);
			categorias.addAll(servicioCategoriaDiagnostico.buscarTodas());
			cmbCategoria.setModel(new ListModelList<CategoriaDiagnostico>(
					categorias));
			break;
		case "Morbilidad Por Nomina":
			rowNomina.setVisible(true);
			rowArea.setVisible(false);
			rowDiagnostico.setVisible(false);
			rowDoctor.setVisible(false);
			rowFamiliar.setVisible(false);
			rowPaciente.setVisible(false);
			rowTipoConsulta.setVisible(false);
			tipo = "nomina";
			Nomina nomina = new Nomina();
			nomina.setNombre(todos);
			nomina.setIdNomina(0);
			List<Nomina> nominas = new ArrayList<Nomina>();
			nominas.add(nomina);
			nominas.addAll(servicioNomina.buscarTodos());
			cmbNomina.setModel(new ListModelList<Nomina>(nominas));
			break;
		case "Morbilidad Por Empresa":
			rowEmpresa.setVisible(true);
			rowArea.setVisible(false);
			rowDiagnostico.setVisible(false);
			rowDoctor.setVisible(false);
			rowFamiliar.setVisible(false);
			rowPaciente.setVisible(false);
			rowTipoConsulta.setVisible(false);
			tipo = "empresa";
			Empresa empresa = new Empresa();
			empresa.setNombre(todos);
			empresa.setIdEmpresa(0);
			List<Empresa> empresas = new ArrayList<Empresa>();
			empresas.add(empresa);
			empresas.addAll(servicioEmpresa.buscarTodas());
			cmbEmpresa.setModel(new ListModelList<Empresa>(empresas));
			break;
		case "Morbilidad Por Cargo":
			rowCargo.setVisible(true);
			rowArea.setVisible(false);
			rowDiagnostico.setVisible(false);
			rowDoctor.setVisible(false);
			rowFamiliar.setVisible(false);
			rowPaciente.setVisible(false);
			rowTipoConsulta.setVisible(false);
			tipo = "cargo";
			Cargo cargo = new Cargo();
			cargo.setNombre("TODOS");
			cargo.setIdCargo(0);
			List<Cargo> cargos = new ArrayList<Cargo>();
			cargos.add(cargo);
			cargos.addAll(servicioCargo.buscarTodos());
			cmbCargo.setModel(new ListModelList<Cargo>(cargos));
			break;
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
			buscadorDiagnostico();
			rowArea.setVisible(false);
			rowDiagnostico.setVisible(true);
			rowDoctor.setVisible(false);
			rowFamiliar.setVisible(true);
			rowPaciente.setVisible(false);
			rowTipoConsulta.setVisible(false);
			box.setVisible(true);
			box2.setVisible(true);
			cargarLista();
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
			private static final long serialVersionUID = 1L;

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
					cargarLista();
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
					case "empresa":
						if (validarEmpresa())
							reporteEmpresa();
						break;
					case "cargo":
						if (validarCargo())
							reporteCargo();
						break;
					case "nomina":
						if (validarNomina())
							reporteNomina();
						break;
					case "clasificacion":
						if (validarClasificacion())
							reporteClasificacion();
						break;
					}
				}
			}

			@Override
			public void eliminar() {

			}
		};
		Button guardar = (Button) botonera.getChildren().get(0);
		guardar.setLabel("Reporte");
		guardar.setImage("/public/imagenes/botones/reporte.png");
		botonera.getChildren().get(1).setVisible(false);
		botoneraMorbilidad.appendChild(botonera);
	}

	protected void reporteClasificacion() {
		Date desde = dtbDesde.getValue();
		Date hasta = dtbHasta.getValue();
		DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
		String fecha1 = fecha.format(desde);
		String fecha2 = fecha.format(hasta);
		String tipoReporte = cmbTipo.getValue();
		String clasificacion = "";
		// ClasificacionDiagnostico clasificacion2 = new
		// ClasificacionDiagnostico();
		if (cmbClasificacion.getValue().equals("TODAS"))
			clasificacion = "";
		else {
			clasificacion = cmbClasificacion.getSelectedItem().getContext();
			// clasificacion2 = getServicioClasificacion().buscar(
			// Long.parseLong(clasificacion));
		}
		String categoria = "";
		// CategoriaDiagnostico categoria2 = new CategoriaDiagnostico();
		if (cmbCategoria.getValue().equals("TODAS"))
			categoria = "";
		else {
			categoria = cmbCategoria.getSelectedItem().getContext();
			// categoria2 = getServicioCategoria().buscar(
			// Long.parseLong(categoria));
		}

		if ((clasificacion.equals("") && categoria.equals("") && servicioConsultaDiagnostico
				.buscarEntreFechas(desde, hasta).isEmpty())
				|| (clasificacion.equals("") && !categoria.equals("") && servicioConsultaDiagnostico
						.buscarEntreFechasyCategoria(desde, hasta,
								Long.parseLong(categoria)).isEmpty())
				|| (!clasificacion.equals("") && categoria.equals("") && servicioConsultaDiagnostico
						.buscarEntreFechasyClasificacion(desde, hasta,
								Long.parseLong(clasificacion)).isEmpty())
				|| (!clasificacion.equals("") && !categoria.equals("") && servicioConsultaDiagnostico
						.buscarEntreFechasyCategoria(desde, hasta,
								Long.parseLong(categoria)).isEmpty()))
			Mensaje.mensajeAlerta(Mensaje.noHayRegistros);
		else {
			Clients.evalJavaScript("window.open('"
					+ damePath()
					+ "Reportero?valor=37&valor6="
					+ fecha1
					+ "&valor7="
					+ fecha2
					+ "&valor8="
					+ clasificacion
					+ "&valor9="
					+ categoria
					+ "&valor20="
					+ tipoReporte
					+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
		}
	}

	protected boolean validarClasificacion() {
		if (cmbClasificacion.getText().compareTo("") == 0
				|| cmbCategoria.getText().compareTo("") == 0) {
			Mensaje.mensajeError(Mensaje.camposVacios);
			return false;
		}
		return true;
	}

	protected void reporteNomina() {
		Date desde = dtbDesde.getValue();
		Date hasta = dtbHasta.getValue();
		DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
		String fecha1 = fecha.format(desde);
		String fecha2 = fecha.format(hasta);
		String tipoReporte = cmbTipo.getValue();
		String nomina = "";
		Nomina nomina2 = new Nomina();
		if (cmbNomina.getValue().equals("TODAS"))
			nomina = "";
		else {
			nomina = cmbNomina.getSelectedItem().getContext();
			nomina2 = getServicioNomina().buscar(Long.parseLong(nomina));
		}

		if ((nomina.equals("") && servicioConsulta.buscarEntreFechas(desde,
				hasta).isEmpty())
				|| (!nomina.equals("") && servicioConsulta
						.buscarEntreFechasyNomina(desde, hasta, nomina2)
						.isEmpty()))
			Mensaje.mensajeAlerta(Mensaje.noHayRegistros);
		else {
			Clients.evalJavaScript("window.open('"
					+ damePath()
					+ "Reportero?valor=36&valor6="
					+ fecha1
					+ "&valor7="
					+ fecha2
					+ "&valor8="
					+ nomina
					+ "&valor20="
					+ tipoReporte
					+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
		}
	}

	protected boolean validarNomina() {
		if (cmbNomina.getText().compareTo("") == 0) {
			Mensaje.mensajeError(Mensaje.camposVacios);
			return false;
		}
		return true;
	}

	protected void reporteCargo() {
		Date desde = dtbDesde.getValue();
		Date hasta = dtbHasta.getValue();
		DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
		String fecha1 = fecha.format(desde);
		String fecha2 = fecha.format(hasta);
		String tipoReporte = cmbTipo.getValue();
		String cargo = "";
		Cargo cargo2 = new Cargo();
		if (cmbCargo2.getValue().equals("TODOS"))
			cargo = "";
		else {
			cargo = cmbCargo2.getSelectedItem().getContext();
			cargo2 = getServicioCargo().buscar(Long.parseLong(cargo));
		}

		if ((cargo.equals("") && servicioConsulta.buscarEntreFechas(desde,
				hasta).isEmpty())
				|| (!cargo.equals("") && servicioConsulta
						.buscarEntreFechasyCargo(desde, hasta, cargo2)
						.isEmpty()))
			Mensaje.mensajeAlerta(Mensaje.noHayRegistros);
		else {
			Clients.evalJavaScript("window.open('"
					+ damePath()
					+ "Reportero?valor=34&valor6="
					+ fecha1
					+ "&valor7="
					+ fecha2
					+ "&valor8="
					+ cargo
					+ "&valor20="
					+ tipoReporte
					+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
		}
	}

	protected boolean validarCargo() {
		if (cmbCargo2.getText().compareTo("") == 0) {
			Mensaje.mensajeError(Mensaje.camposVacios);
			return false;
		}
		return true;
	}

	protected void reporteEmpresa() {
		Date desde = dtbDesde.getValue();
		Date hasta = dtbHasta.getValue();
		DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
		String fecha1 = fecha.format(desde);
		String fecha2 = fecha.format(hasta);
		String tipoReporte = cmbTipo.getValue();
		String empresa = "";
		Empresa empresa2 = new Empresa();
		if (cmbEmpresa.getValue().equals("TODAS"))
			empresa = "";
		else {
			empresa = cmbEmpresa.getSelectedItem().getContext();
			empresa2 = getServicioEmpresa().buscar(Long.parseLong(empresa));
		}

		if ((empresa.equals("") && servicioConsulta.buscarEntreFechas(desde,
				hasta).isEmpty())
				|| (!empresa.equals("") && servicioConsulta
						.buscarEntreFechasyEmpresa(desde, hasta, empresa2)
						.isEmpty()))
			Mensaje.mensajeAlerta(Mensaje.noHayRegistros);
		else {
			Clients.evalJavaScript("window.open('"
					+ damePath()
					+ "Reportero?valor=35&valor6="
					+ fecha1
					+ "&valor7="
					+ fecha2
					+ "&valor8="
					+ empresa
					+ "&valor20="
					+ tipoReporte
					+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
		}
	}

	protected boolean validarEmpresa() {
		if (cmbEmpresa.getText().compareTo("") == 0) {
			Mensaje.mensajeError(Mensaje.camposVacios);
			return false;
		}
		return true;
	}

	@Listen("onSelect = #cmbClasificacion")
	public void selectClasification() {
		List<CategoriaDiagnostico> categorias = new ArrayList<CategoriaDiagnostico>();
		CategoriaDiagnostico categoria = new CategoriaDiagnostico();
		categoria.setNombre("TODAS");
		categoria.setIdCategoriaDiagnostico(0);
		categorias.add(categoria);
		if (!cmbClasificacion.getValue().equals("TODAS")) {
			long clave = Long.parseLong(cmbClasificacion.getSelectedItem()
					.getContext());
			categorias.addAll(servicioCategoriaDiagnostico
					.buscarPorClasificacion(clave));
		} else
			categorias.addAll(servicioCategoriaDiagnostico.buscarTodas());
		cmbCategoria.setModel(new ListModelList<CategoriaDiagnostico>(
				categorias));
	}

	private void buscadorDiagnostico() {
		buscarDiagnostico = new Buscar<Diagnostico>(ltbDiagnosticos,
				txtBuscadorDiagnostico) {
			@Override
			protected List<Diagnostico> buscar(String valor) {
				List<Diagnostico> diagnosticosFiltradas = new ArrayList<Diagnostico>();
				List<Diagnostico> diagnosticos = servicioDiagnostico
						.filtroNombre(valor);
				Diagnostico diag = new Diagnostico();
				diag.setNombre("TODOS");
				diag.setIdDiagnostico(0);
				diagnosticosFiltradas.add(diag);
				for (int i = 0; i < diagnosticosDisponibles.size(); i++) {
					Diagnostico diagnostico = diagnosticosDisponibles.get(i);
					for (int j = 0; j < diagnosticos.size(); j++) {
						if (diagnostico.getIdDiagnostico() == diagnosticos.get(
								j).getIdDiagnostico())
							diagnosticosFiltradas.add(diagnostico);
					}
				}
				return diagnosticosFiltradas;
			}
		};
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
		cargo.setNombre("TODOS");
		cargo.setIdCargo(0);
		List<Cargo> cargos = new ArrayList<Cargo>();
		cargos.add(cargo);
		cargos.addAll(servicioCargo.buscarTodos());
		cmbArea.setModel(new ListModelList<Area>(areas));
		cmbCargo.setModel(new ListModelList<Cargo>(cargos));
		cmbCargo2.setModel(new ListModelList<Cargo>(cargos));
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
			Mensaje.mensajeError(Mensaje.camposVacios);
			return false;
		} else {
			if (dtbDesde.getValue().after(dtbHasta.getValue())) {
				Mensaje.mensajeError(Mensaje.fechaPosterior);
				return false;

			} else
				return true;
		}

	}

	public boolean validarArea() {
		if (cmbArea.getText().compareTo("") == 0) {
			Mensaje.mensajeError(Mensaje.camposVacios);
			return false;
		}
		return true;
	}

	public boolean validarTipoConsulta() {
		if (cmbTipoConsulta.getText().compareTo("") == 0
				|| cmbTipoPreventiva.getText().compareTo("") == 0) {
			Mensaje.mensajeError(Mensaje.camposVacios);
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
			Mensaje.mensajeError(Mensaje.camposVacios);
			return false;
		} else {
			if (ltbDiagnosticosAgregados.getItemCount() == 0) {
				Mensaje.mensajeError("Debe seleccionar al menos un diagnostico");
				return false;
			} else
				return true;
		}
	}

	public boolean validarDoctor() {
		if (cmbUnidad.getText().compareTo("") == 0 || idDoctor.equals("")) {
			Mensaje.mensajeError(Mensaje.camposVacios);
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
		String tipoReporte = cmbTipo.getValue();
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
			Mensaje.mensajeAlerta(Mensaje.noHayRegistros);
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
					+ "&valor20="
					+ tipoReporte
					+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
		}

	}

	public byte[] reporteMorbilidadPorArea(String part1, String part2,
			String area, String cargo, String tipoReporte) throws JRException {
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

		fecha2 = agregarDia(fecha2);
		List<Consulta> consuta = new ArrayList<Consulta>();

		if (area.equals(""))
			consuta = getServicioConsulta().buscarEntreFechas(fecha1, fecha2);
		else {
			Area area2 = getServicioArea().buscar(Long.parseLong(area));
			consuta = getServicioConsulta().buscarEntreFechasyArea(fecha1,
					fecha2, area2);
		}
		Map<String, Object> p = new HashMap<String, Object>();
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

		p.put("data", new JRBeanCollectionDataSource(consuta));
		JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass()
				.getResource("/reporte/RMorbilidadPorArea.jasper"));
		if (tipoReporte.equals("EXCEL")) {

			JasperPrint jasperPrint = null;
			try {
				jasperPrint = JasperFillManager.fillReport(reporte, p,
						new JRBeanCollectionDataSource(consuta));
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

			fichero = JasperRunManager.runReportToPdf(reporte, p,
					new JRBeanCollectionDataSource(consuta));
			return fichero;
		}
	}

	public void reporteTipoConsulta() {
		Date desde = dtbDesde.getValue();
		Date hasta = dtbHasta.getValue();
		DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
		String fecha1 = fecha.format(desde);
		String fecha2 = fecha.format(hasta);
		String tipoC = "";
		String subTipo = "";
		String tipoReporte = cmbTipo.getValue();

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
			Mensaje.mensajeAlerta(Mensaje.noHayRegistros);
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
					+ "&valor20="
					+ tipoReporte
					+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
		}

	}

	public byte[] reporteMorbilidadPorTipo(String part1, String part2,
			String tipo, String subTipo, String tipoReporte) throws JRException {
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

		fecha2 = agregarDia(fecha2);
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

		Map<String, Object> p = new HashMap<String, Object>();
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

		p.put("data", new JRBeanCollectionDataSource(consuta));
		JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass()
				.getResource("/reporte/RMorbilidadPorTipoConsulta.jasper"));
		if (tipoReporte.equals("EXCEL")) {

			JasperPrint jasperPrint = null;
			try {
				jasperPrint = JasperFillManager.fillReport(reporte, p,
						new JRBeanCollectionDataSource(consuta));
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
			fichero = JasperRunManager.runReportToPdf(reporte, p,
					new JRBeanCollectionDataSource(consuta));
			return fichero;
		}
	}

	public void reporteDiagnostico() {
		Date desde = dtbDesde.getValue();
		Date hasta = dtbHasta.getValue();
		DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
		String fecha1 = fecha.format(desde);
		String fecha2 = fecha.format(hasta);
		String diagnostico = "";
		String radio = "";
		String tipoReporte = cmbTipo.getValue();
		if (cmbDiagnostico.getValue().equals("TODOS"))
			diagnostico = "";
		else
			diagnostico = cmbDiagnostico.getValue();

		String diagnosticoReal = "";
		JSONObject json = new JSONObject();
		List<Long> ids = new ArrayList<Long>();
		for (int i = 0; i < diagnosticosAgregados.size(); i++) {
			Diagnostico object = diagnosticosAgregados.get(i);
			ids.add(object.getIdDiagnostico());
			try {
				json.put("valor" + i, object.getIdDiagnostico());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			if (object.getIdDiagnostico() == 0) {
				diagnosticoReal = "TODOS";
				json = new JSONObject();
				try {
					json.put("valor", 0);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				i = diagnosticosAgregados.size();
			}
		}

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

		if ((diagnostico.equals("") && diagnosticoReal.equals("TODOS")
				&& radio.equals("Todos") && servicioConsultaDiagnostico
				.buscarEntreFechasEntreEdades(desde, hasta, dea, aa).isEmpty())
				|| (!diagnostico.equals("") && diagnosticoReal.equals("TODOS")
						&& radio.equals("Todos") && servicioConsultaDiagnostico
						.buscarEntreFechasEntreEdadesyTipoDiagnostico(desde,
								hasta, dea, aa, diagnostico).isEmpty())
				|| (diagnostico.equals("") && diagnosticoReal.equals("TODOS")
						&& radio.equals("Familiares") && servicioConsultaDiagnostico
						.buscarEntreFechasEntreEdadesyFamiliar(desde, hasta,
								dea, aa, false).isEmpty())
				|| (diagnostico.equals("") && diagnosticoReal.equals("TODOS")
						&& radio.equals("Trabajadores") && servicioConsultaDiagnostico
						.buscarEntreFechasEntreEdadesyFamiliar(desde, hasta,
								dea, aa, true).isEmpty())
				|| (!diagnostico.equals("") && diagnosticoReal.equals("TODOS")
						&& radio.equals("Familiares") && servicioConsultaDiagnostico
						.buscarEntreFechasEntreEdadesTipoDiagnosticoyFamiliar(
								desde, hasta, dea, aa, diagnostico, false)
						.isEmpty())
				|| (!diagnostico.equals("") && diagnosticoReal.equals("TODOS")
						&& radio.equals("Trabajadores") && servicioConsultaDiagnostico
						.buscarEntreFechasEntreEdadesTipoDiagnosticoyFamiliar(
								desde, hasta, dea, aa, diagnostico, true)
						.isEmpty())

				|| (diagnostico.equals("") && diagnosticoReal.equals("")
						&& radio.equals("Todos") && servicioConsultaDiagnostico
						.buscarEntreFechasEntreEdadesYDiagnosticos(desde,
								hasta, dea, aa, ids).isEmpty())
				|| (!diagnostico.equals("") && diagnosticoReal.equals("")
						&& radio.equals("Todos") && servicioConsultaDiagnostico
						.buscarEntreFechasEntreEdadesyTipoDiagnosticoYDiagnosticos(
								desde, hasta, dea, aa, diagnostico, ids)
						.isEmpty())
				|| (diagnostico.equals("") && diagnosticoReal.equals("")
						&& radio.equals("Familiares") && servicioConsultaDiagnostico
						.buscarEntreFechasEntreEdadesyFamiliarYDiagnosticos(
								desde, hasta, dea, aa, false, ids).isEmpty())
				|| (diagnostico.equals("") && diagnosticoReal.equals("")
						&& radio.equals("Trabajadores") && servicioConsultaDiagnostico
						.buscarEntreFechasEntreEdadesyFamiliarYDiagnosticos(
								desde, hasta, dea, aa, true, ids).isEmpty())
				|| (!diagnostico.equals("") && diagnosticoReal.equals("")
						&& radio.equals("Familiares") && servicioConsultaDiagnostico
						.buscarEntreFechasEntreEdadesTipoDiagnosticoyFamiliarYDiagnosticos(
								desde, hasta, dea, aa, diagnostico, false, ids)
						.isEmpty())
				|| (!diagnostico.equals("") && diagnosticoReal.equals("")
						&& radio.equals("Trabajadores") && servicioConsultaDiagnostico
						.buscarEntreFechasEntreEdadesTipoDiagnosticoyFamiliarYDiagnosticos(
								desde, hasta, dea, aa, diagnostico, true, ids)
						.isEmpty()))
			Mensaje.mensajeAlerta(Mensaje.noHayRegistros);
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
					+ "&valor40="
					+ json.toString()
					+ "&valor20="
					+ tipoReporte
					+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
		}

	}

	public byte[] reporteMorbilidadPorDiagnostico(String part1, String part2,
			String diagnostico, String familiar, String a, String de,
			String tipoReporte, JSONObject jObj) throws JRException {
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

		fecha2 = agregarDia(fecha2);
		List<Long> ids = new ArrayList<Long>();
		String diagnosticoReal = "";
		Iterator<?> it = jObj.keys();
		while (it.hasNext()) {
			String key = (String) it.next();
			Integer o;
			try {
				o = (Integer) jObj.get(key);
				ids.add(Long.valueOf(o));
				if (o == 0)
					diagnosticoReal = "TODOS";
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		List<ConsultaDiagnostico> consutaDiag = new ArrayList<ConsultaDiagnostico>();
		int dea = Integer.valueOf(de);
		int aa = Integer.valueOf(a);
		if (diagnosticoReal.equals("TODOS")) {
			if (diagnostico.equals("") && familiar.equals("Todos"))
				consutaDiag = getServicioConsultaDiagnostico()
						.buscarEntreFechasEntreEdades(fecha1, fecha2, dea, aa);
			else {
				if (!diagnostico.equals("") && familiar.equals("Todos"))
					consutaDiag = getServicioConsultaDiagnostico()
							.buscarEntreFechasEntreEdadesyTipoDiagnostico(
									fecha1, fecha2, dea, aa, diagnostico);
				else {
					if (diagnostico.equals("") && familiar.equals("Familiares"))
						consutaDiag = getServicioConsultaDiagnostico()
								.buscarEntreFechasEntreEdadesyFamiliar(fecha1,
										fecha2, dea, aa, false);
					else {
						if (diagnostico.equals("")
								&& familiar.equals("Trabajadores"))
							consutaDiag = getServicioConsultaDiagnostico()
									.buscarEntreFechasEntreEdadesyFamiliar(
											fecha1, fecha2, dea, aa, true);
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
		} else {

			if (diagnostico.equals("") && familiar.equals("Todos"))
				consutaDiag = getServicioConsultaDiagnostico()
						.buscarEntreFechasEntreEdadesYDiagnosticos(fecha1,
								fecha2, dea, aa, ids);
			else {
				if (!diagnostico.equals("") && familiar.equals("Todos"))
					consutaDiag = getServicioConsultaDiagnostico()
							.buscarEntreFechasEntreEdadesyTipoDiagnosticoYDiagnosticos(
									fecha1, fecha2, dea, aa, diagnostico, ids);
				else {
					if (diagnostico.equals("") && familiar.equals("Familiares"))
						consutaDiag = getServicioConsultaDiagnostico()
								.buscarEntreFechasEntreEdadesyFamiliarYDiagnosticos(
										fecha1, fecha2, dea, aa, false, ids);
					else {
						if (diagnostico.equals("")
								&& familiar.equals("Trabajadores"))
							consutaDiag = getServicioConsultaDiagnostico()
									.buscarEntreFechasEntreEdadesyFamiliarYDiagnosticos(
											fecha1, fecha2, dea, aa, true, ids);
						else {
							if (!diagnostico.equals("")
									&& familiar.equals("Familiares"))
								consutaDiag = getServicioConsultaDiagnostico()
										.buscarEntreFechasEntreEdadesTipoDiagnosticoyFamiliarYDiagnosticos(
												fecha1, fecha2, dea, aa,
												diagnostico, false, ids);
							else {
								if (!diagnostico.equals("")
										&& familiar.equals("Trabajadores"))
									consutaDiag = getServicioConsultaDiagnostico()
											.buscarEntreFechasEntreEdadesTipoDiagnosticoyFamiliarYDiagnosticos(
													fecha1, fecha2, dea, aa,
													diagnostico, true, ids);
							}
						}
					}
				}
			}
		}

		Map<String, Object> p = new HashMap<String, Object>();
		p.put("desde", part1);
		p.put("hasta", part2);
		p.put("edad1", dea);
		p.put("edad2", aa);
		p.put("paciente", familiar);
		p.put("data", new JRBeanCollectionDataSource(consutaDiag));

		// List<Long> consuta = getServicioConsultaDiagnostico()
		// .cantidadConsultas(consutaDiag);
		// p.put("total", consuta.size());

		for (int i = 0; i < consutaDiag.size(); i++) {
			Consulta cons = consutaDiag.get(i).getConsulta();
			Paciente paciente = cons.getPaciente();
			paciente.setEdad(calcularEdad(paciente.getFechaNacimiento()));

		}

		JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass()
				.getResource("/reporte/RMorbilidadPorDiagnostico.jasper"));

		if (tipoReporte.equals("EXCEL")) {

			JasperPrint jasperPrint = null;
			try {
				jasperPrint = JasperFillManager.fillReport(reporte, p,
						new JRBeanCollectionDataSource(consutaDiag));
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

			fichero = JasperRunManager.runReportToPdf(reporte, p,
					new JRBeanCollectionDataSource(consutaDiag));

			return fichero;
		}
	}

	public void reporteDoctor() {
		Date desde = dtbDesde.getValue();
		Date hasta = dtbHasta.getValue();
		DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
		String fecha1 = fecha.format(desde);
		String fecha2 = fecha.format(hasta);
		String unidad = "";
		String tipoReporte = cmbTipo.getValue();

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
			Mensaje.mensajeAlerta(Mensaje.noHayRegistros);
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
					+ "&valor20="
					+ tipoReporte
					+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
		}

	}

	public byte[] reporteMorbilidadPorDoctor(String part1, String part2,
			String unidad, String doctor, String tipoReporte)
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

		fecha2 = agregarDia(fecha2);
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

		Map<String, Object> p = new HashMap<String, Object>();
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

		p.put("data", new JRBeanCollectionDataSource(consuta));
		JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass()
				.getResource("/reporte/RMorbilidadPorDoctor.jasper"));

		if (tipoReporte.equals("EXCEL")) {

			JasperPrint jasperPrint = null;
			try {
				jasperPrint = JasperFillManager.fillReport(reporte, p,
						new JRBeanCollectionDataSource(consuta));
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

			fichero = JasperRunManager.runReportToPdf(reporte, p,
					new JRBeanCollectionDataSource(consuta));
			// }
			return fichero;
		}
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
				"Catalogo de Doctores", usuarios,false, "Cedula", "Ficha", "Nombre",
				"Apellido", "Especialidad") {
			private static final long serialVersionUID = 1L;

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

	@Listen("onSeleccion = #divCatalogoPaciente")
	public void seleccionar() {
		Paciente paciente = catalogo.objetoSeleccionadoDelCatalogo();
		lblPaciente.setValue(paciente.getPrimerNombre() + " "
				+ paciente.getPrimerApellido());
		idPaciente = paciente.getCedula();
		catalogo.setParent(null);
	}

	@Listen("onClick = #pasar1")
	public void derechaDiagnostico() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem = ltbDiagnosticos.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					Diagnostico diagnostico = listItem.get(i).getValue();
					diagnosticosDisponibles.remove(diagnostico);
					diagnosticosAgregados.add(diagnostico);
					ltbDiagnosticosAgregados
							.setModel(new ListModelList<Diagnostico>(
									diagnosticosAgregados));
					ltbDiagnosticosAgregados.renderAll();
					listitemEliminar.add(listItem.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbDiagnosticos.removeItemAt(listitemEliminar.get(i).getIndex());
			ltbDiagnosticos.renderAll();
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar2")
	public void izquierdaDiagnostico() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem2 = ltbDiagnosticosAgregados.getItems();
		if (listItem2.size() != 0) {
			for (int i = 0; i < listItem2.size(); i++) {
				if (listItem2.get(i).isSelected()) {
					Diagnostico diagnostico = listItem2.get(i).getValue();
					diagnosticosAgregados.remove(diagnostico);
					diagnosticosDisponibles.add(diagnostico);
					ltbDiagnosticos.setModel(new ListModelList<Diagnostico>(
							diagnosticosDisponibles));
					ltbDiagnosticos.renderAll();
					listitemEliminar.add(listItem2.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbDiagnosticosAgregados.removeItemAt(listitemEliminar.get(i)
					.getIndex());
			ltbDiagnosticosAgregados.renderAll();
		}
		listasMultiples();
	}

	private void listasMultiples() {
		ltbDiagnosticosAgregados.setMultiple(false);
		ltbDiagnosticosAgregados.setCheckmark(false);
		ltbDiagnosticosAgregados.setMultiple(true);
		ltbDiagnosticosAgregados.setCheckmark(true);
		ltbDiagnosticos.setMultiple(false);
		ltbDiagnosticos.setCheckmark(false);
		ltbDiagnosticos.setMultiple(true);
		ltbDiagnosticos.setCheckmark(true);
	}

	private void cargarLista() {
		if (box.isVisible()) {
			diagnosticosDisponibles.clear();
			Diagnostico diag = new Diagnostico();
			diag.setNombre("TODOS");
			diag.setIdDiagnostico(0);
			diagnosticosDisponibles.add(diag);
			diagnosticosDisponibles.addAll(servicioConsultaDiagnostico
					.buscarDiagnosticosExistentesSimples(dtbDesde.getValue(),
							dtbHasta.getValue()));
			ltbDiagnosticos.setModel(new ListModelList<Diagnostico>(
					diagnosticosDisponibles));
			diagnosticosAgregados.clear();
			ltbDiagnosticosAgregados.getItems().clear();
			listasMultiples();
		}
	}

	@Listen("onChange = #dtbDesde, #dtbHasta")
	public void changeList() {
		cargarLista();
	}

	public byte[] reporteMorbilidadPorCargo(String part1, String part2,
			String cargo, String tipo2) {

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

		fecha2 = agregarDia(fecha2);
		List<Consulta> consuta = new ArrayList<Consulta>();

		if (cargo.equals(""))
			consuta = getServicioConsulta().buscarEntreFechas(fecha1, fecha2);
		else {
			Cargo cargo2 = getServicioCargo().buscar(Long.parseLong(cargo));
			consuta = getServicioConsulta().buscarEntreFechasyCargo(fecha1,
					fecha2, cargo2);
		}
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("desde", part1);
		p.put("hasta", part2);
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

		p.put("data", new JRBeanCollectionDataSource(consuta));
		JasperReport reporte = null;
		try {
			reporte = (JasperReport) JRLoader.loadObject(getClass()
					.getResource("/reporte/RMorbilidadPorCargo.jasper"));
		} catch (JRException e1) {
			e1.printStackTrace();
		}
		if (tipo2.equals("EXCEL")) {

			JasperPrint jasperPrint = null;
			try {
				jasperPrint = JasperFillManager.fillReport(reporte, p,
						new JRBeanCollectionDataSource(consuta));
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
						new JRBeanCollectionDataSource(consuta));
			} catch (JRException e) {
				e.printStackTrace();
			}
			return fichero;
		}
	}

	public byte[] reporteMorbilidadPorEmpresa(String part1, String part2,
			String empresa, String tipo2) {

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

		fecha2 = agregarDia(fecha2);
		List<Consulta> consuta = new ArrayList<Consulta>();

		if (empresa.equals(""))
			consuta = getServicioConsulta().buscarEntreFechas(fecha1, fecha2);
		else {
			Empresa empresa2 = getServicioEmpresa().buscar(
					Long.parseLong(empresa));
			consuta = getServicioConsulta().buscarEntreFechasyEmpresa(fecha1,
					fecha2, empresa2);
		}
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("desde", part1);
		p.put("hasta", part2);
		p.put("empresa", empresa);

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

		p.put("data", new JRBeanCollectionDataSource(consuta));
		JasperReport reporte = null;
		try {
			reporte = (JasperReport) JRLoader.loadObject(getClass()
					.getResource("/reporte/RMorbilidadPorEmpresa.jasper"));
		} catch (JRException e1) {
			e1.printStackTrace();
		}
		if (tipo2.equals("EXCEL")) {

			JasperPrint jasperPrint = null;
			try {
				jasperPrint = JasperFillManager.fillReport(reporte, p,
						new JRBeanCollectionDataSource(consuta));
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
						new JRBeanCollectionDataSource(consuta));
			} catch (JRException e) {
				e.printStackTrace();
			}
			return fichero;
		}
	}

	public byte[] reporteMorbilidadPorNomina(String part1, String part2,
			String nomina, String tipo2) {

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

		fecha2 = agregarDia(fecha2);
		List<Consulta> consuta = new ArrayList<Consulta>();

		if (nomina.equals(""))
			consuta = getServicioConsulta().buscarEntreFechas(fecha1, fecha2);
		else {
			Nomina nomina2 = getServicioNomina().buscar(Long.parseLong(nomina));
			consuta = getServicioConsulta().buscarEntreFechasyNomina(fecha1,
					fecha2, nomina2);
		}
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("desde", part1);
		p.put("hasta", part2);
		p.put("nomina", nomina);

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

		p.put("data", new JRBeanCollectionDataSource(consuta));
		JasperReport reporte = null;
		try {
			reporte = (JasperReport) JRLoader.loadObject(getClass()
					.getResource("/reporte/RMorbilidadPorNomina.jasper"));
		} catch (JRException e1) {
			e1.printStackTrace();
		}
		if (tipo2.equals("EXCEL")) {

			JasperPrint jasperPrint = null;
			try {
				jasperPrint = JasperFillManager.fillReport(reporte, p,
						new JRBeanCollectionDataSource(consuta));
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
						new JRBeanCollectionDataSource(consuta));
			} catch (JRException e) {
				e.printStackTrace();
			}
			return fichero;
		}
	}

	public byte[] reporteMorbilidadPorClasificacion(String part1, String part2,
			String clasificacion, String categoria, String tipoReporte) {

		byte[] fichero = null;
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		Date desde = null;
		try {
			desde = formato.parse(part1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date hasta = null;
		try {
			hasta = formato.parse(part2);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		hasta = agregarDia(hasta);
		List<ConsultaDiagnostico> consutaDiag = new ArrayList<ConsultaDiagnostico>();
		if (clasificacion.equals("") && categoria.equals(""))
			consutaDiag = getServicioConsultaDiagnostico().buscarEntreFechas(
					desde, hasta);
		else {
			if (!clasificacion.equals("") && categoria.equals("")) {
				consutaDiag = getServicioConsultaDiagnostico()
						.buscarEntreFechasyClasificacion(desde, hasta,
								Long.parseLong(clasificacion));
			} else
				consutaDiag = getServicioConsultaDiagnostico()
						.buscarEntreFechasyCategoria(desde, hasta,
								Long.parseLong(categoria));
		}

		Map<String, Object> p = new HashMap<String, Object>();
		p.put("desde", part1);
		p.put("hasta", part2);

		p.put("data", new JRBeanCollectionDataSource(consutaDiag));
		// List<Long> consuta = getServicioConsultaDiagnostico()
		// .cantidadConsultas(consutaDiag);
		// p.put("total", consuta.size());

		for (int i = 0; i < consutaDiag.size(); i++) {
			Consulta cons = consutaDiag.get(i).getConsulta();
			Paciente paciente = cons.getPaciente();
			paciente.setEdad(calcularEdad(paciente.getFechaNacimiento()));

		}

		JasperReport reporte = null;
		try {
			reporte = (JasperReport) JRLoader
					.loadObject(getClass().getResource(
							"/reporte/RMorbilidadPorClasificacion.jasper"));
		} catch (JRException e1) {
			e1.printStackTrace();
		}

		if (tipoReporte.equals("EXCEL")) {

			JasperPrint jasperPrint = null;
			try {
				jasperPrint = JasperFillManager.fillReport(reporte, p,
						new JRBeanCollectionDataSource(consutaDiag));
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
						new JRBeanCollectionDataSource(consutaDiag));
			} catch (JRException e) {
				e.printStackTrace();
			}

			return fichero;
		}
	}
}