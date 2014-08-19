package controlador.seguridad;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import modelo.maestros.Cargo;
import modelo.maestros.CategoriaDiagnostico;
import modelo.maestros.CategoriaMedicina;
import modelo.maestros.Ciudad;
import modelo.maestros.Diagnostico;
import modelo.maestros.Empresa;
import modelo.maestros.Examen;
import modelo.maestros.Laboratorio;
import modelo.maestros.Medicina;
import modelo.maestros.Nomina;
import modelo.maestros.Paciente;
import modelo.maestros.Proveedor;
import modelo.maestros.Recipe;
import modelo.seguridad.Usuario;
import modelo.sha.Area;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaDiagnostico;
import modelo.transacciones.ConsultaExamen;
import modelo.transacciones.ConsultaMedicina;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.A;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;

import componentes.Botonera;
import componentes.Validador;

import controlador.maestros.CGenerico;

public class CImportar extends CGenerico {

	private static final long serialVersionUID = -4335686245390014965L;
	@Wire
	private Button btnImportarMedicina;
	@Wire
	private Div botoneraImportar;
	@Wire
	private Div divImportar;
	@Wire
	private Label lblNombreExamen;
	@Wire
	private Label lblNombreMedicina;
	@Wire
	private Label lblNombreArea;
	@Wire
	private Label lblNombreDiagnostico;
	@Wire
	private Label lblNombreConsulta;
	@Wire
	private Label lblNombrePaciente;
	@Wire
	private Label lblNombreExamenConsulta;
	@Wire
	private Label lblNombreDiagnosticoConsulta;
	@Wire
	private Label lblNombreMedicinaConsulta;
	@Wire
	private org.zkoss.zul.Row rowDiagnostico;
	@Wire
	private org.zkoss.zul.Row rowMedicina;
	@Wire
	private org.zkoss.zul.Row rowExamen;
	@Wire
	private org.zkoss.zul.Row rowArea;
	@Wire
	private org.zkoss.zul.Row rowConsulta;
	@Wire
	private org.zkoss.zul.Row rowPaciente;
	@Wire
	private org.zkoss.zul.Row rowConsultaExamen;
	@Wire
	private org.zkoss.zul.Row rowConsultaDiagnostico;
	@Wire
	private org.zkoss.zul.Row rowConsultaMedicina;
	@Wire
	private Image imgUsuario;
	URL url = getClass().getResource("/controlador/maestros/usuario.png");
	private Media mediaExamen;
	private Media mediaMedicina;
	private Media mediaArea;
	private Media mediaDiagnostico;
	private Media mediaConsulta;
	private Media mediaConsultaExamen;
	private Media mediaConsultaMedicina;
	private Media mediaConsultaDiagnostico;
	private Media mediaPaciente;

	@Override
	public void inicializar() throws IOException {

		contenido = (Include) divImportar.getParent();
		Tabbox tabox = (Tabbox) divImportar.getParent().getParent().getParent()
				.getParent();
		tabBox = tabox;
		tab = (Tab) tabox.getTabs().getLastChild();
		HashMap<String, Object> mapa = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (mapa != null) {
			if (mapa.get("tabsGenerales") != null) {
				tabs = (List<Tab>) mapa.get("tabsGenerales");
				System.out.println(tabs.size());
				mapa.clear();
				mapa = null;
			}
		}

		Botonera botonera = new Botonera() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -3983017778342516154L;

			@Override
			public void salir() {
				cerrarVentana(divImportar, "Importar", tabs);

			}

			@Override
			public void limpiar() {
				// TODO Auto-generated method stub

			}

			@Override
			public void guardar() {
				if (validar()) {
					importarDiagnosticos();
					importarAreas();
					importarExamenes();
					importarMedicinas();
					// importarPacientes();
					importarConsultas();
					importarDiagnosticoConsulta();
					importarExamenConsulta();
					importarMedicinaConsulta();
				}

			}

			@Override
			public void eliminar() {
				// TODO Auto-generated method stub

			}

		};
		botonera.getChildren().get(1).setVisible(false);
		botonera.getChildren().get(2).setVisible(false);
		botoneraImportar.appendChild(botonera);

	}

	protected void importarMedicinaConsulta() {
		if (mediaConsultaMedicina != null) {
			XSSFWorkbook workbook = null;
			try {
				workbook = new XSSFWorkbook(
						mediaConsultaMedicina.getStreamData());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			Recipe recipe = new Recipe(0, "1 (Urgente)", fechaHora,
					fechaHora, horaAuditoria, nombreUsuarioSesion());
			servicioRecipe.guardar(recipe);
			recipe = servicioRecipe.buscarUltimo();
			List<ConsultaMedicina> consultasMedicina = new ArrayList<ConsultaMedicina>();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Medicina medicina = new Medicina();
				Consulta consulta = new Consulta();
				ConsultaMedicina consultaMedicina = new ConsultaMedicina();
				Double idReferenciaC = (double) 0;
				long idRefC = 0;
				Double idReferenciaD = (double) 0;
				long idRefD = 0;
				Double cedReferencia = (double) 0;
				String cedRef = "";
				Double dosisReferencia = (double) 0;
				String dosis = "";
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					switch (cell.getColumnIndex()) {
					case 0:
						idReferenciaC = cell.getNumericCellValue();
						if (idReferenciaC != null)
							idRefC = idReferenciaC.longValue();
						break;
					case 1:
						idReferenciaD = cell.getNumericCellValue();
						if (idReferenciaD != null)
							idRefD = idReferenciaD.longValue();
						break;
					case 2:
						if (cell.getCellType() == 1) {
							cedRef = cell.getStringCellValue();
						} else {
							cedReferencia = cell.getNumericCellValue();
							if (cedReferencia != null)
								cedRef = String.valueOf(cedReferencia
										.longValue());
						}
						break;
					case 3:
						if (cell.getCellType() == 1) {
							dosis = cell.getStringCellValue();
						} else {
							dosisReferencia = cell.getNumericCellValue();
							if (dosisReferencia != null)
								dosis = String.valueOf(dosisReferencia
										.longValue());
						}
						break;
					default:
						break;
					}
				}
				medicina = servicioMedicina.buscarPorReferencia(idRefD);
				consulta = servicioConsulta
						.buscarPorReferencias(idRefC, cedRef);
				if (medicina != null && consulta != null) {
					consultaMedicina.setConsulta(consulta);
					consultaMedicina.setMedicina(medicina);
					consultaMedicina.setDosis(dosis);
					consultaMedicina.setRecipe(recipe);
					consultasMedicina.add(consultaMedicina);
				}
			}
			servicioConsultaMedicina.guardar(consultasMedicina);
		}
	}

	protected void importarExamenConsulta() {
		if (mediaConsultaExamen != null) {
			XSSFWorkbook workbook = null;
			try {
				workbook = new XSSFWorkbook(
						mediaConsultaExamen.getStreamData());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			Proveedor proveedor = servicioProveedor.buscar(1);
			List<ConsultaExamen> consultasExamen = new ArrayList<ConsultaExamen>();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Examen examen = new Examen();
				Consulta consulta = new Consulta();
				ConsultaExamen consultaExamen = new ConsultaExamen();
				Double idReferenciaC = (double) 0;
				long idRefC = 0;
				Double idReferenciaD = (double) 0;
				long idRefD = 0;
				Double cedReferencia = (double) 0;
				String cedRef = "";
				Double dosisReferencia = (double) 0;
				String dosis = "";
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					switch (cell.getColumnIndex()) {
					case 0:
						idReferenciaC = cell.getNumericCellValue();
						if (idReferenciaC != null)
							idRefC = idReferenciaC.longValue();
						break;
					case 1:
						idReferenciaD = cell.getNumericCellValue();
						if (idReferenciaD != null)
							idRefD = idReferenciaD.longValue();
						break;
					case 2:
						if (cell.getCellType() == 1) {
							cedRef = cell.getStringCellValue();
						} else {
							cedReferencia = cell.getNumericCellValue();
							if (cedReferencia != null)
								cedRef = String.valueOf(cedReferencia
										.longValue());
						}
						break;
					case 3:
						if (cell.getCellType() == 1) {
							dosis = cell.getStringCellValue();
						} else {
							dosisReferencia = cell.getNumericCellValue();
							if (dosisReferencia != null)
								dosis = String.valueOf(dosisReferencia
										.longValue());
						}
						break;
					default:
						break;
					}
				}
				examen = servicioExamen.buscarPorReferencia(idRefD);
				consulta = servicioConsulta
						.buscarPorReferencias(idRefC, cedRef);
				if (examen != null && consulta != null) {
					consultaExamen.setConsulta(consulta);
					consultaExamen.setExamen(examen);
					consultaExamen.setObservacion(dosis);
					consultaExamen.setCosto(0);
					consultaExamen.setProveedor(proveedor);
					consultaExamen.setPrioridad("1 (Urgente)");
					consultasExamen.add(consultaExamen);
				}
			}
			servicioConsultaExamen.guardar(consultasExamen);
		}
	}

	protected void importarDiagnosticoConsulta() {
		if (mediaConsultaDiagnostico != null) {
			System.out.println("entroooooo");
			XSSFWorkbook workbook = null;
			try {
				workbook = new XSSFWorkbook(
						mediaConsultaDiagnostico.getStreamData());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			List<ConsultaDiagnostico> consultasDiagnostico = new ArrayList<ConsultaDiagnostico>();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Diagnostico diagnostico = new Diagnostico();
				Consulta consulta = new Consulta();
				ConsultaDiagnostico consultaDagnostico = new ConsultaDiagnostico();
				Double idReferenciaC = (double) 0;
				long idRefC = 0;
				Double idReferenciaD = (double) 0;
				long idRefD = 0;
				Double cedReferencia = (double) 0;
				String cedRef = "";
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					switch (cell.getColumnIndex()) {
					case 0:
						idReferenciaD = cell.getNumericCellValue();
						if (idReferenciaD != null)
							idRefD = idReferenciaD.longValue();
						break;
					case 1:
						idReferenciaC = cell.getNumericCellValue();
						if (idReferenciaC != null)
							idRefC = idReferenciaC.longValue();
						break;
					case 2:
						if (cell.getCellType() == 1) {
							System.out.println(cell.getStringCellValue());
							cedRef = cell.getStringCellValue();
						} else {
							cedReferencia = cell.getNumericCellValue();
							if (cedReferencia != null)
								cedRef = String.valueOf(cedReferencia
										.longValue());
						}
						break;
					default:
						break;
					}
				}
				diagnostico = servicioDiagnostico.buscarPorReferencia(idRefD);
				consulta = servicioConsulta
						.buscarPorReferencias(idRefC, cedRef);
				if (diagnostico != null && consulta != null) {
					consultaDagnostico.setConsulta(consulta);
					consultaDagnostico.setDiagnostico(diagnostico);
					consultaDagnostico.setAccidente(null);
					consultaDagnostico.setClasificacion("");
					consultaDagnostico.setLugar("");
					consultaDagnostico.setMotivo("");
					consultaDagnostico.setFecha(null);
					consultaDagnostico.setObservacion("");
					consultaDagnostico.setTipo("Otro");
					consultasDiagnostico.add(consultaDagnostico);
				}
			}
			servicioConsultaDiagnostico.guardar(consultasDiagnostico);
		}
	}

	protected void importarMedicinas() {
		if (mediaMedicina != null) {
			XSSFWorkbook workbook = null;
			try {
				workbook = new XSSFWorkbook(mediaMedicina.getStreamData());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			CategoriaMedicina categoria = servicioCategoriaMedicina.buscar(1);
			Laboratorio laboratorio = servicioLaboratorio.buscar(1);
			List<Medicina> medicinas = new ArrayList<Medicina>();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Medicina medicina = new Medicina();
				Double idReferencia = (double) 0;
				long idRef = 0;
				String nombre = "";
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					switch (cell.getColumnIndex()) {
					case 0:
						idReferencia = cell.getNumericCellValue();
						if (idReferencia != null)
							idRef = idReferencia.longValue();
						break;
					case 1:
						nombre = cell.getStringCellValue();
						break;
					default:
						break;
					}
				}
				medicina.setNombre(nombre);
				medicina.setCategoriaMedicina(categoria);
				medicina.setLaboratorio(laboratorio);
				medicina.setIdReferencia(idRef);
				medicina.setFechaAuditoria(fechaHora);
				medicina.setHoraAuditoria(horaAuditoria);
				medicina.setUsuarioAuditoria("frivero");
				medicinas.add(medicina);
			}
			String nombre = "";
			for (int i = 0; i < medicinas.size(); i++) {
				nombre = medicinas.get(i).getNombre();
				if (i < medicinas.size() - 1) {
					for (int j = i + 1; j < medicinas.size(); j++) {
						if (medicinas.get(j).getNombre().equals(nombre)) {
							medicinas.get(j).setNombre(nombre + "2");
							j = medicinas.size();
						}
					}
				}
			}
			servicioMedicina.guardarVarios(medicinas);
		}
	}

	protected void importarExamenes() {
		if (mediaExamen != null) {
			XSSFWorkbook workbook = null;
			try {
				workbook = new XSSFWorkbook(mediaExamen.getStreamData());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			List<Examen> examenes = new ArrayList<Examen>();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Examen examen = new Examen();
				Double idReferencia = (double) 0;
				long idRef = 0;
				String nombre = "";
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					switch (cell.getColumnIndex()) {
					case 0:
						idReferencia = cell.getNumericCellValue();
						if (idReferencia != null)
							idRef = idReferencia.longValue();
						break;
					case 1:
						nombre = cell.getStringCellValue();
						break;
					default:
						break;
					}
				}
				examen.setNombre(nombre);
				examen.setIdReferencia(idRef);
				examen.setCosto(0);
				examen.setMaximo(0);
				examen.setMinimo(0);
				examen.setFechaAuditoria(fechaHora);
				examen.setHoraAuditoria(horaAuditoria);
				examen.setUsuarioAuditoria("frivero");
				examenes.add(examen);
			}
			String nombre = "";
			for (int i = 0; i < examenes.size(); i++) {
				nombre = examenes.get(i).getNombre();
				if (i < examenes.size() - 1) {
					for (int j = i + 1; j < examenes.size(); j++) {
						if (examenes.get(j).getNombre().equals(nombre)) {
							examenes.get(j).setNombre(nombre + "2");
							j = examenes.size();
						}
					}
				}
			}
			servicioExamen.guardarVarios(examenes);
		}
	}

	protected void importarAreas() {
		if (mediaArea != null) {
			XSSFWorkbook workbook = null;
			try {
				workbook = new XSSFWorkbook(mediaArea.getStreamData());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			List<Area> areas = new ArrayList<Area>();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Area area = new Area();
				Double idReferencia = (double) 0;
				String idRef = "";
				String nombre = "";
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					switch (cell.getColumnIndex()) {
					case 0:
						if (cell.getCellType() == 1)
							idRef = cell.getStringCellValue();
						else {
							idReferencia = cell.getNumericCellValue();
							if (idReferencia != null)
								idRef = String
										.valueOf(idReferencia.longValue());
						}
						break;
					case 1:
						nombre = cell.getStringCellValue();
						break;
					default:
						break;
					}
				}
				area.setNombre(nombre);
				area.setCodigo(idRef);
				area.setFechaAuditoria(fechaHora);
				area.setHoraAuditoria(horaAuditoria);
				area.setUsuarioAuditoria("frivero");
				areas.add(area);
			}
			String nombre = "";
			for (int i = 0; i < areas.size(); i++) {
				nombre = areas.get(i).getNombre();
				if (i < areas.size() - 1) {
					for (int j = i + 1; j < areas.size(); j++) {
						if (areas.get(j).getNombre().equals(nombre)) {
							areas.get(j).setNombre(nombre + "2");
							j = areas.size();
						}
					}
				}
			}
			servicioArea.guardarVarios(areas);
		}
	}

	protected void importarDiagnosticos() {
		if (mediaDiagnostico != null) {
			XSSFWorkbook workbook = null;
			try {
				workbook = new XSSFWorkbook(mediaDiagnostico.getStreamData());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			CategoriaDiagnostico categoria = new CategoriaDiagnostico();
			categoria = servicioCategoriaDiagnostico.buscar(1);
			List<Diagnostico> diagnosticos = new ArrayList<Diagnostico>();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Diagnostico diagnostico = new Diagnostico();
				Double idReferencia = (double) 0;
				long idRef = 0;
				String nombre = "";
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					switch (cell.getColumnIndex()) {
					case 0:
						idReferencia = cell.getNumericCellValue();
						if (idReferencia != null)
							idRef = idReferencia.longValue();
						break;
					case 1:
						nombre = cell.getStringCellValue();
						break;
					default:
						break;
					}
				}
				diagnostico.setNombre(nombre);
				diagnostico.setEpi(false);
				diagnostico.setCategoria(categoria);
				diagnostico.setIdReferencia(idRef);
				diagnostico.setCodigo(String.valueOf(idRef));
				diagnostico.setGrupo("Sin Grupo");
				diagnostico.setFechaAuditoria(fechaHora);
				diagnostico.setHoraAuditoria(horaAuditoria);
				diagnostico.setUsuarioAuditoria("frivero");
				diagnosticos.add(diagnostico);
			}
			String nombre = "";
			for (int i = 0; i < diagnosticos.size(); i++) {
				nombre = diagnosticos.get(i).getNombre();
				if (i < diagnosticos.size() - 1) {
					for (int j = i + 1; j < diagnosticos.size(); j++) {
						if (diagnosticos.get(j).getNombre().equals(nombre)) {
							diagnosticos.get(j).setNombre(nombre + "2");
							j = diagnosticos.size();
						}
					}
				}
			}
			servicioDiagnostico.guardarVarios(diagnosticos);
		}
	}

	protected void importarPacientes() {
		if (mediaPaciente != null) {
			XSSFWorkbook workbook = null;
			try {
				workbook = new XSSFWorkbook(mediaPaciente.getStreamData());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			Empresa empresa = servicioEmpresa.buscar(1);
			Ciudad ciudad = servicioCiudad.buscar(1);
			Nomina nomina = servicioNomina.buscar(1);
			Cargo cargo = servicioCargo.buscar(1);
			try {
				imgUsuario.setContent(new AImage(url));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			List<Paciente> pacientes = new ArrayList<Paciente>();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Area area = new Area();
				Paciente paciente = new Paciente();
				String primerNombre = "", cedula = null, segundoNombre = "", primerApellido = "", segundoApellido = "", direccion = "", sexo = "", ficha = "", codigoArea = "";
				Timestamp fechaNac = null;
				Date fechaNac2 = new Date();
				Double cedRef = (double) 0;
				Double fichaRef = (double) 0;
				Double codigoRef = (double) 0;
				byte[] imagenUsuario = null;

				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					System.out.println("paso");
					Cell cell = cellIterator.next();

					switch (cell.getColumnIndex()) {
					case 0:
						if (cell.getCellType() == 1)
							cedula = cell.getStringCellValue();
						else {
							cedRef = cell.getNumericCellValue();
							if (cedRef != null)
								cedula = String.valueOf(cedRef.longValue());
						}
						break;
					case 1:
						primerNombre = cell.getStringCellValue();
						break;
					case 2:
						segundoNombre = cell.getStringCellValue();
						break;
					case 3:
						primerApellido = cell.getStringCellValue();
						break;
					case 4:
						segundoApellido = cell.getStringCellValue();
						break;
					case 5:
						direccion = cell.getStringCellValue();
						break;
					case 7:
						fechaNac2 = cell.getDateCellValue();
						fechaNac = new Timestamp(fechaNac2.getTime());
						break;
					case 8:
						sexo = cell.getStringCellValue();
					case 10:
						if (cell.getCellType() == 1)
							ficha = cell.getStringCellValue();
						else {
							fichaRef = cell.getNumericCellValue();
							if (fichaRef != null)
								ficha = String.valueOf(fichaRef.longValue());
						}
						break;
					case 11:
						if (cell.getCellType() == 1)
							codigoArea = cell.getStringCellValue();
						else {
							codigoRef = cell.getNumericCellValue();
							if (codigoRef != null)
								codigoArea = String.valueOf(codigoRef
										.longValue());
						}
						break;
					default:
						break;
					}
				}
				paciente.setCedula(cedula);
				paciente.setPrimerNombre(primerNombre);
				paciente.setSegundoNombre(segundoNombre);
				paciente.setPrimerApellido(primerApellido);
				paciente.setSegundoApellido(segundoApellido);
				paciente.setDireccion(direccion);
				if (sexo.equalsIgnoreCase("Femenino"))
					paciente.setSexo("Femenino");
				else
					paciente.setSexo("Masculino");
				imagenUsuario = imgUsuario.getContent().getByteData();
				paciente.setImagen(imagenUsuario);
				paciente.setFicha(ficha);
				area = servicioArea.buscarPorCodigo(codigoArea);
				if (area == null || codigoArea.equals("-")
						|| codigoArea.equals("NULL"))
					area = servicioArea.buscar(1); // PENDIENTE
				paciente.setArea(area);
				paciente.setNacionalidad("V");
				paciente.setEstatus(true);
				paciente.setMuerte(false);
				paciente.setDiscapacidad(false);
				paciente.setAlergia(false);
				paciente.setLentes(false);
				paciente.setProfesion("");
				paciente.setEdad(0);
				paciente.setEstatura(0.0);
				paciente.setEstadoCivil("Otro");
				paciente.setNivelEducativo("Primaria");
				paciente.setFechaAuditoria(fechaHora);
				paciente.setFechaNacimiento(fechaNac);
				paciente.setFechaEgreso(fechaHora);
				paciente.setFechaIngreso(fechaHora);
				paciente.setFechaInscripcionIVSS(fechaHora);
				paciente.setCarga(0);
				paciente.setGrupoSanguineo("A+");
				paciente.setMano("Derecho");
				paciente.setPeso(0.0);
				paciente.setEmpresa(empresa);
				paciente.setCargoReal(cargo);
				paciente.setNomina(nomina);
				paciente.setCiudadVivienda(ciudad);
				paciente.setTurno("Jornada Completa");
				paciente.setTelefono1("");
				paciente.setTelefono2("");
				paciente.setTelefono1Emergencia("");
				paciente.setTelefono2Emergencia("");
				paciente.setEmail("");
				paciente.setNombresEmergencia("");
				paciente.setApellidosEmergencia("");
				paciente.setParentescoEmergencia("Otro");
				if (Validador.validarNumero(cedula))
					paciente.setTrabajador(true);
				else {
					paciente.setTrabajador(false);
					paciente.setParentescoFamiliar("Otro");
					String a = "";
					String familiar = "";
					for (int i = 0; i < cedula.length(); i++) {
						a = Character.toString(cedula.charAt(i));
						if (!a.equals("-"))
							familiar += a;
						else
							paciente.setCedulaFamiliar(familiar);
					}
				}
				paciente.setUsuarioAuditoria("frivero");
				paciente.setHoraAuditoria(horaAuditoria);
				pacientes.add(paciente);
			}
			servicioPaciente.guardarVarios(pacientes);
		}
	}

	public void importarConsultas() {
		if (mediaConsulta != null) {
			XSSFWorkbook workbook = null;
			try {
				workbook = new XSSFWorkbook(mediaConsulta.getStreamData());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			Usuario usuario = servicioUsuario.buscarUsuarioPorId("1");
			Area area = servicioArea.buscar(1);
			Cargo cargo = servicioCargo.buscar(1);
			List<Consulta> consultas = new ArrayList<Consulta>();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Consulta consulta = new Consulta();
				Double cedReferencia = (double) 0;
				String cedRef = "";
				Double idReferencia = (double) 0;
				long idRef = 0;
				Date fecha2 = new Date();
				Timestamp fechaReal = null;
				String enfermedadActual = "";
				String doctor = "";
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					System.out.println("paso1");
					Cell cell = cellIterator.next();

					switch (cell.getColumnIndex()) {
					case 0:
						if (cell.getCellType() == 1) {
							System.out.println(cell.getStringCellValue());
							cedRef = cell.getStringCellValue();
						} else {
							cedReferencia = cell.getNumericCellValue();
							if (cedReferencia != null)
								cedRef = String.valueOf(cedReferencia
										.longValue());
						}
						break;
					case 1:
						System.out.println(cell.getDateCellValue());
						fecha2 = cell.getDateCellValue();
						fechaReal = new Timestamp(fecha2.getTime());
						break;
					case 3:
						enfermedadActual = cell.getStringCellValue();
						break;
					case 4:
						doctor = cell.getStringCellValue();
						break;
					case 5:
						idReferencia = cell.getNumericCellValue();
						if (idReferencia != null)
							idRef = idReferencia.longValue();
						break;
					default:
						break;
					}
				}
				consulta.setIdReferencia(idRef);
				consulta.setCedulaReferencia(cedRef);
				consulta.setEnfermedadActual(enfermedadActual);
				consulta.setMotivoConsulta("");
				consulta.setTipoConsulta("Preventiva");
				consulta.setTipoConsultaSecundaria("Reintegro");
				consulta.setDoctor(doctor);
				consulta.setFechaConsulta(fechaReal);
				consulta.setUsuario(usuario);
				consulta.setCargo(cargo);
				consulta.setArea(area);
				Paciente paciente = servicioPaciente.buscarPorCedula(cedRef);
				consulta.setPaciente(paciente);
				consulta.setReposo(false);
				consulta.setDiasReposo(0);
				consulta.setAccidenteLaboral(false);
				consulta.setConsultaAsociada(0);
				consulta.setFrecuencia(0);
				consulta.setFrecuenciaEsfuerzo(0);
				consulta.setFrecuenciaPost(0);
				consulta.setFrecuenciaReposo(0);
				consulta.setPerimetroForzada((double) 0);
				consulta.setPerimetroOmbligo((double) 0);
				consulta.setPerimetroPlena((double) 0);
				consulta.setPeso((double) 0);
				consulta.setSistolicaPrimera(0);
				consulta.setSistolicaSegunda(0);
				consulta.setSistolicaTercera(0);
				consulta.setDiastolicaPrimera(0);
				consulta.setDiastolicaSegunda(0);
				consulta.setDiastolicaTercera(0);
				consulta.setEstatura((double) 0);
				consulta.setExtraEsfuerzo(0);
				consulta.setExtraPost(0);
				consulta.setExtraReposo(0);
				consulta.setRitmico(false);
				consulta.setRitmicoEsfuerzo(false);
				consulta.setRitmicoPost(false);
				consulta.setRitmicoReposo(false);
				consulta.setApto(false);
				consulta.setFechaAuditoria(fechaHora);
				consulta.setHoraAuditoria(horaAuditoria);
				consulta.setUsuarioAuditoria("frivero");
				consultas.add(consulta);
			}
			servicioConsulta.guardarVarios(consultas);
		}
	}

	@Listen("onUpload = #btnImportarMedicina")
	public void cargarMedicina(UploadEvent event) {
		mediaMedicina = event.getMedia();
		lblNombreMedicina.setValue(mediaMedicina.getName());
		final A rm = new A("Remover");
		rm.addEventListener(Events.ON_CLICK,
				new org.zkoss.zk.ui.event.EventListener<Event>() {
					public void onEvent(Event event) throws Exception {
						lblNombreMedicina.setValue("");
						rm.detach();
						mediaMedicina = null;
					}
				});
		rowMedicina.appendChild(rm);
		mostrarConsulta();
	}

	@Listen("onUpload = #btnImportarDiagostico")
	public void cargarDiagostico(UploadEvent event) {
		mediaDiagnostico = event.getMedia();
		lblNombreDiagnostico.setValue(mediaDiagnostico.getName());
		final A rm = new A("Remover");
		rm.addEventListener(Events.ON_CLICK,
				new org.zkoss.zk.ui.event.EventListener<Event>() {
					public void onEvent(Event event) throws Exception {
						lblNombreDiagnostico.setValue("");
						rm.detach();
						mediaDiagnostico = null;
					}
				});
		rowDiagnostico.appendChild(rm);
		mostrarConsulta();
	}

	@Listen("onUpload = #btnImportarExamen")
	public void cargarExamen(UploadEvent event) {
		mediaExamen = event.getMedia();
		lblNombreExamen.setValue(mediaExamen.getName());
		final A rm = new A("Remover");
		rm.addEventListener(Events.ON_CLICK,
				new org.zkoss.zk.ui.event.EventListener<Event>() {
					public void onEvent(Event event) throws Exception {
						lblNombreExamen.setValue("");
						rm.detach();
						mediaExamen = null;
					}
				});
		rowExamen.appendChild(rm);
		mostrarConsulta();
	}

	@Listen("onUpload = #btnImportarArea")
	public void cargarArea(UploadEvent event) {
		mediaArea = event.getMedia();
		lblNombreArea.setValue(mediaArea.getName());
		final A rm = new A("Remover");
		rm.addEventListener(Events.ON_CLICK,
				new org.zkoss.zk.ui.event.EventListener<Event>() {
					public void onEvent(Event event) throws Exception {
						lblNombreArea.setValue("");
						rm.detach();
						mediaArea = null;
					}
				});
		rowArea.appendChild(rm);
		mostrarConsulta();
	}

	@Listen("onUpload = #btnImportarConsulta")
	public void cargarConsulta(UploadEvent event) {
		mediaConsulta = event.getMedia();
		lblNombreConsulta.setValue(mediaConsulta.getName());
		final A rm = new A("Remover");
		rm.addEventListener(Events.ON_CLICK,
				new org.zkoss.zk.ui.event.EventListener<Event>() {
					public void onEvent(Event event) throws Exception {
						lblNombreConsulta.setValue("");
						rm.detach();
						mediaConsulta = null;
					}
				});
		rowConsulta.appendChild(rm);
	}

	@Listen("onUpload = #btnImportarPaciente")
	public void cargarPaciente(UploadEvent event) {
		mediaPaciente = event.getMedia();
		lblNombrePaciente.setValue(mediaPaciente.getName());
		final A rm = new A("Remover");
		rm.addEventListener(Events.ON_CLICK,
				new org.zkoss.zk.ui.event.EventListener<Event>() {
					public void onEvent(Event event) throws Exception {
						lblNombrePaciente.setValue("");
						rm.detach();
						mediaPaciente = null;
					}
				});
		rowPaciente.appendChild(rm);
		mostrarConsulta();
	}

	@Listen("onUpload = #btnImportarExamenConsulta")
	public void cargarExamenConsulta(UploadEvent event) {
		mediaConsultaExamen = event.getMedia();
		lblNombreExamenConsulta.setValue(mediaConsultaExamen.getName());
		final A rm = new A("Remover");
		rm.addEventListener(Events.ON_CLICK,
				new org.zkoss.zk.ui.event.EventListener<Event>() {
					public void onEvent(Event event) throws Exception {
						lblNombreExamenConsulta.setValue("");
						rm.detach();
						mediaConsultaExamen = null;
					}
				});
		rowConsultaExamen.appendChild(rm);
	}

	@Listen("onUpload = #btnImportarDiagnosticoConsulta")
	public void cargarDiagnosticoConsulta(UploadEvent event) {
		mediaConsultaDiagnostico = event.getMedia();
		lblNombreDiagnosticoConsulta.setValue(mediaConsultaDiagnostico
				.getName());
		final A rm = new A("Remover");
		rm.addEventListener(Events.ON_CLICK,
				new org.zkoss.zk.ui.event.EventListener<Event>() {
					public void onEvent(Event event) throws Exception {
						lblNombreDiagnosticoConsulta.setValue("");
						rm.detach();
						mediaConsultaDiagnostico = null;
					}
				});
		rowConsultaDiagnostico.appendChild(rm);
	}

	@Listen("onUpload = #btnImportarMedicinaConsulta")
	public void cargarMedicinaConsulta(UploadEvent event) {
		mediaConsultaMedicina = event.getMedia();
		lblNombreMedicinaConsulta.setValue(mediaConsultaMedicina.getName());
		final A rm = new A("Remover");
		rm.addEventListener(Events.ON_CLICK,
				new org.zkoss.zk.ui.event.EventListener<Event>() {
					public void onEvent(Event event) throws Exception {
						lblNombreMedicinaConsulta.setValue("");
						rm.detach();
						mediaConsultaMedicina = null;
					}
				});
		rowConsultaMedicina.appendChild(rm);
	}

	public void mostrarConsulta() {
		if (lblNombreArea.getValue().compareTo("") != 0
				&& lblNombreDiagnostico.getValue().compareTo("") != 0
				&& lblNombreExamen.getValue().compareTo("") != 0
				&& lblNombreMedicina.getValue().compareTo("") != 0
		// && lblNombrePaciente.getValue().compareTo("") != 0
		)
			rowConsulta.setVisible(true);
		else
			rowConsulta.setVisible(false);
	}

	public boolean validar() {
		return true;
	}

}
