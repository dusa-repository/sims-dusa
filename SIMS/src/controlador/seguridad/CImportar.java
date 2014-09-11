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
import componentes.Mensaje;
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
	private boolean errorGeneral = false;
	private String archivoVacio = "El siguiente archivo no posee registros, por lo tanto no fue importado.";
	private String archivoConError = "Existe un error en el siguiente archivo adjunto: ";
	private String errorLongitud = "La siguiente ubicacion excede el limite establecido de longitud:";
	private String valorNoEncontrado = "Valor no encontrado, ";

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
				if (rowArea.getChildren().size() == 4) {
					A linea = (A) rowArea.getChildren().get(3);
					Events.postEvent("onClick", linea, null);
				}
				if (rowConsulta.getChildren().size() == 4) {
					A linea = (A) rowConsulta.getChildren().get(3);
					Events.postEvent("onClick", linea, null);
				}
				if (rowConsultaDiagnostico.getChildren().size() == 4) {
					A linea = (A) rowConsultaDiagnostico.getChildren().get(3);
					Events.postEvent("onClick", linea, null);
				}
				if (rowConsultaExamen.getChildren().size() == 4) {
					A linea = (A) rowConsultaExamen.getChildren().get(3);
					Events.postEvent("onClick", linea, null);
				}
				if (rowConsultaMedicina.getChildren().size() == 4) {
					A linea = (A) rowConsultaMedicina.getChildren().get(3);
					Events.postEvent("onClick", linea, null);
				}
				if (rowDiagnostico.getChildren().size() == 4) {
					A linea = (A) rowDiagnostico.getChildren().get(3);
					Events.postEvent("onClick", linea, null);
				}
				if (rowExamen.getChildren().size() == 4) {
					A linea = (A) rowExamen.getChildren().get(3);
					Events.postEvent("onClick", linea, null);
				}
				if (rowMedicina.getChildren().size() == 4) {
					A linea = (A) rowMedicina.getChildren().get(3);
					Events.postEvent("onClick", linea, null);
				}
				if (rowPaciente.getChildren().size() == 4) {
					A linea = (A) rowPaciente.getChildren().get(3);
					Events.postEvent("onClick", linea, null);
				}
				errorGeneral = false;
			}

			@Override
			public void guardar() {
				importarDiagnosticos();
				importarAreas();
				importarExamenes();
				importarMedicinas();
				importarPacientes();
				importarConsultas();
				importarDiagnosticoConsulta();
				importarExamenConsulta();
				importarMedicinaConsulta();
				if (!errorGeneral) {
					msj.mensajeInformacion(Mensaje.guardadosArchivos);
					limpiar();
				}
			}

			@Override
			public void eliminar() {
				// TODO Auto-generated method stub

			}

		};
		botonera.getChildren().get(1).setVisible(false);
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
			if (rowIterator.hasNext()) {
				Recipe recipe = new Recipe(0, "1 (Urgente)", fechaHora,
						fechaHora, horaAuditoria, nombreUsuarioSesion());
				servicioRecipe.guardar(recipe);
				recipe = servicioRecipe.buscarUltimo();
				List<ConsultaMedicina> consultasMedicina = new ArrayList<ConsultaMedicina>();
				int contadorRow = 0;
				boolean error = false;
				while (rowIterator.hasNext()) {
					error = false;
					contadorRow = contadorRow + 1;
					System.out.println(contadorRow);
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
					int contadorCell = 0;
					while (cellIterator.hasNext()) {
						contadorCell = contadorCell + 1;
						Cell cell = cellIterator.next();
						switch (cell.getColumnIndex()) {
						case 0:
							if (cell.getCellType() == 0) {
								idReferenciaC = cell.getNumericCellValue();
								if (idReferenciaC != null)
									idRefC = idReferenciaC.longValue();
							} else
								error = true;
							break;
						case 1:
							if (cell.getCellType() == 0) {
								idReferenciaD = cell.getNumericCellValue();
								if (idReferenciaD != null)
									idRefD = idReferenciaD.longValue();
							} else
								error = true;
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
					if (!error) {
						if (row.getPhysicalNumberOfCells() != 0) {
							if (row.getPhysicalNumberOfCells() == 1
									&& consultasMedicina.size() != 0) {
								int total = consultasMedicina.size();
								String dosis2 = consultasMedicina
										.get(total - 1).getDosis();
								consultasMedicina.get(total - 1).setDosis(
										dosis + " " + dosis2);
							} else {
								medicina = servicioMedicina
										.buscarPorReferencia(idRefD);
								consulta = servicioConsulta
										.buscarPorReferencias(idRefC,
												cedRef.replaceAll("\\s", ""));
								if (medicina != null) {
									if (consulta != null) {
										consultaMedicina.setConsulta(consulta);
										consultaMedicina.setMedicina(medicina);
										consultaMedicina.setDosis(dosis);
										consultaMedicina.setRecipe(recipe);
										consultasMedicina.add(consultaMedicina);
									}
									// else {
									// msj.mensajeError(valorNoEncontrado
									// + " la consulta con referencia de ID: "
									// + idRefC
									// + " y Paciente:"
									// + cedRef
									// +
									// " no ha sido encontrada, por lo tanto no ha sido importado el archivo"
									// + " Fila: " + contadorRow
									// + ". Columna: " + contadorCell);
									// error = true;
									// }
								}
								// else {
								// msj.mensajeError(valorNoEncontrado
								// + " la Medicina con ID: "
								// + idRefD
								// +
								// " no ha sido encontrada, por lo tanto no ha sido importado el archivo"
								// + " Fila: " + contadorRow
								// + ". Columna: " + contadorCell);
								// error = true;
								// }
							}
						}
					}
					// else {
					// msj.mensajeError(archivoConError
					// + lblNombreMedicinaConsulta.getValue()
					// + ". Fila: " + contadorRow + ". Columna: "
					// + contadorCell);
					// error = true;
					// }
				}
				// if (!error)
				servicioConsultaMedicina.guardar(consultasMedicina);
				// else
				// errorGeneral = true;
			} else
				msj.mensajeAlerta(archivoVacio + " "
						+ lblNombreMedicinaConsulta.getValue());
		}
	}

	protected void importarExamenConsulta() {
		if (mediaConsultaExamen != null) {
			XSSFWorkbook workbook = null;
			try {
				workbook = new XSSFWorkbook(mediaConsultaExamen.getStreamData());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			if (rowIterator.hasNext()) {
				Proveedor proveedor = servicioProveedor.buscar(1);
				List<ConsultaExamen> consultasExamen = new ArrayList<ConsultaExamen>();
				int contadorRow = 0;
				boolean error = false;
				while (rowIterator.hasNext()) {
					error = false;
					contadorRow = contadorRow + 1;
					System.out.println(contadorRow);
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
					int contadorCell = 0;
					while (cellIterator.hasNext()) {
						contadorCell = contadorCell + 1;
						Cell cell = cellIterator.next();
						switch (cell.getColumnIndex()) {
						case 0:
							if (cell.getCellType() == 0) {
								idReferenciaC = cell.getNumericCellValue();
								if (idReferenciaC != null)
									idRefC = idReferenciaC.longValue();
							} else
								error = true;
							break;
						case 1:
							if (cell.getCellType() == 0) {
								idReferenciaD = cell.getNumericCellValue();
								if (idReferenciaD != null)
									idRefD = idReferenciaD.longValue();
							} else
								error = true;
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
					if (!error) {
						examen = servicioExamen.buscarPorReferencia(idRefD);
						consulta = servicioConsulta.buscarPorReferencias(
								idRefC, cedRef.replaceAll("\\s", ""));
						if (examen != null) {
							if (consulta != null) {
								consultaExamen.setConsulta(consulta);
								consultaExamen.setExamen(examen);
								consultaExamen.setObservacion(dosis);
								consultaExamen.setCosto(0);
								consultaExamen.setProveedor(proveedor);
								consultaExamen.setPrioridad("1 (Urgente)");
								consultasExamen.add(consultaExamen);
							}
							// else {
							// msj.mensajeError(valorNoEncontrado
							// + " la consulta con referencia de ID: "
							// + idRefC
							// + " y Paciente:"
							// + cedRef
							// +
							// " no ha sido encontrada, por lo tanto no ha sido importado el archivo"
							// + " Fila: " + contadorRow
							// + ". Columna: " + contadorCell);
							// error = true;
							// }
						}
						// else {
						// msj.mensajeError(valorNoEncontrado
						// + " el examen con ID: "
						// + idRefD
						// +
						// " no ha sido encontrado, por lo tanto no ha sido importado el archivo"
						// + " Fila: " + contadorRow + ". Columna: "
						// + contadorCell);
						// error = true;
						// }
					}
					// else {
					// msj.mensajeError(archivoConError
					// + lblNombreExamenConsulta.getValue()
					// + ". Fila: " + contadorRow + ". Columna: "
					// + contadorCell);
					// error = true;
					// }
				}
				// if (!error)
				servicioConsultaExamen.guardar(consultasExamen);
				// else
				// errorGeneral = true;
			} else
				msj.mensajeAlerta(archivoVacio + " "
						+ lblNombreExamenConsulta.getValue());
		}
	}

	protected void importarDiagnosticoConsulta() {
		if (mediaConsultaDiagnostico != null) {
			XSSFWorkbook workbook = null;
			try {
				workbook = new XSSFWorkbook(
						mediaConsultaDiagnostico.getStreamData());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			if (rowIterator.hasNext()) {
				List<ConsultaDiagnostico> consultasDiagnostico = new ArrayList<ConsultaDiagnostico>();
				int contadorRow = 0;
				boolean error = false;
				while (rowIterator.hasNext()) {
					error = false;
					contadorRow = contadorRow + 1;
					System.out.println(contadorRow);
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
					int contadorCell = 0;
					while (cellIterator.hasNext()) {
						contadorCell = contadorCell + 1;
						Cell cell = cellIterator.next();
						switch (cell.getColumnIndex()) {
						case 0:
							if (cell.getCellType() == 0) {
								idReferenciaD = cell.getNumericCellValue();
								if (idReferenciaD != null)
									idRefD = idReferenciaD.longValue();
							} else
								error = true;
							break;
						case 1:
							if (cell.getCellType() == 0) {
								idReferenciaC = cell.getNumericCellValue();
								if (idReferenciaC != null)
									idRefC = idReferenciaC.longValue();
							} else
								error = true;
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
						default:
							break;
						}
					}
					if (!error) {
						diagnostico = servicioDiagnostico
								.buscarPorReferencia(idRefD);
						consulta = servicioConsulta.buscarPorReferencias(
								idRefC, cedRef.replaceAll("\\s", ""));
						if (diagnostico != null) {
							if (consulta != null) {
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
							// else {
							// msj.mensajeError(valorNoEncontrado
							// + " la consulta con referencia de ID: "
							// + idRefC
							// + " y Paciente:"
							// + cedRef
							// +
							// " no ha sido encontrada, por lo tanto no ha sido importado el archivo"
							// + " Fila: " + contadorRow
							// + ". Columna: " + contadorCell);
							// error = true;
							// }
						}
						// else {
						// msj.mensajeError(valorNoEncontrado
						// + " el diagnostico con ID: "
						// + idRefD
						// +
						// " no ha sido encontrado, por lo tanto no ha sido importado el archivo"
						// + " Fila: " + contadorRow + ". Columna: "
						// + contadorCell);
						// error = true;
						// }
					}
					// else {
					// msj.mensajeError(archivoConError
					// + lblNombreDiagnosticoConsulta.getValue()
					// + ". Fila: " + contadorRow + ". Columna: "
					// + contadorCell);
					// error = true;
					// }
				}
				// if (!error)
				servicioConsultaDiagnostico.guardar(consultasDiagnostico);
				// else
				// errorGeneral = true;
			} else
				msj.mensajeAlerta(archivoVacio + " "
						+ lblNombreDiagnosticoConsulta.getValue());
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
			if (rowIterator.hasNext()) {
				CategoriaMedicina categoria = servicioCategoriaMedicina
						.buscar(1);
				Laboratorio laboratorio = servicioLaboratorio.buscar(1);
				List<Medicina> medicinas = new ArrayList<Medicina>();
				int contadorRow = 0;
				boolean error = false;
				boolean errorLong = false;
				while (rowIterator.hasNext() && !error && !errorLong) {
					contadorRow = contadorRow + 1;
					Row row = rowIterator.next();
					Medicina medicina = new Medicina();
					Double idReferencia = (double) 0;
					long idRef = 0;
					String nombre = "";
					int contadorCell = 0;
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext() && !error && !errorLong) {
						contadorCell = contadorCell + 1;
						Cell cell = cellIterator.next();
						switch (cell.getColumnIndex()) {
						case 0:
							if (cell.getCellType() == 0) {
								idReferencia = cell.getNumericCellValue();
								if (idReferencia != null)
									idRef = idReferencia.longValue();
							} else {
								errorGeneral = true;
								error = true;
							}
							break;
						case 1:
							nombre = cell.getStringCellValue();
							if (nombre.length() > 500)
								errorLong = true;
							break;
						default:
							break;
						}
					}
					if (!errorLong) {
						if (!error) {
							medicina.setNombre(nombre);
							medicina.setCategoriaMedicina(categoria);
							medicina.setLaboratorio(laboratorio);
							medicina.setIdReferencia(idRef);
							medicina.setFechaAuditoria(fechaHora);
							medicina.setHoraAuditoria(horaAuditoria);
							medicina.setUsuarioAuditoria("frivero");
							medicinas.add(medicina);
							String nombrel = "";
							for (int i = 0; i < medicinas.size(); i++) {
								nombrel = medicinas.get(i).getNombre();
								if (i < medicinas.size() - 1) {
									for (int j = i + 1; j < medicinas.size(); j++) {
										if (medicinas.get(j).getNombre()
												.equals(nombrel)) {
											medicinas.get(j).setNombre(
													nombrel + "2");
											j = medicinas.size();
										}
									}
								}
							}
						} else {
							msj.mensajeError(archivoConError
									+ lblNombreMedicina.getValue() + ". Fila: "
									+ contadorRow + ". Columna: "
									+ contadorCell);
							error = true;
						}
					} else {
						msj.mensajeError(errorLongitud
								+ lblNombreMedicina.getValue()
								+ ". Fila: "
								+ contadorRow
								+ ". Columna: "
								+ contadorCell
								+ ". Longitudes permitidas: campo1 19, campo2 500");
						error = true;
					}

				}
				if (!error)
					servicioMedicina.guardarVarios(medicinas);
				else
					errorGeneral = true;
			} else
				msj.mensajeAlerta(archivoVacio + " "
						+ lblNombreMedicina.getValue());
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
			if (rowIterator.hasNext()) {
				List<Examen> examenes = new ArrayList<Examen>();
				int contadorRow = 0;
				boolean error = false;
				boolean errorLong = false;
				while (rowIterator.hasNext() && !error && !errorLong) {
					contadorRow = contadorRow + 1;
					Row row = rowIterator.next();
					Examen examen = new Examen();
					Double idReferencia = (double) 0;
					long idRef = 0;
					String nombre = "";
					int contadorCell = 0;
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext() && !error && !errorLong) {
						contadorCell = contadorCell + 1;
						Cell cell = cellIterator.next();
						switch (cell.getColumnIndex()) {
						case 0:
							if (cell.getCellType() == 0) {
								idReferencia = cell.getNumericCellValue();
								if (idReferencia != null)
									idRef = idReferencia.longValue();
							} else {
								errorGeneral = true;
								error = true;
							}
							break;
						case 1:
							nombre = cell.getStringCellValue();
							if (nombre.length() > 50)
								errorLong = true;
							break;
						default:
							break;
						}
					}
					if (!errorLong) {
						if (!error) {
							examen.setNombre(nombre);
							examen.setIdReferencia(idRef);
							examen.setCosto(0);
							examen.setMaximo(0);
							examen.setMinimo(0);
							examen.setFechaAuditoria(fechaHora);
							examen.setHoraAuditoria(horaAuditoria);
							examen.setUsuarioAuditoria("frivero");
							examenes.add(examen);
							String nombrej = "";
							for (int i = 0; i < examenes.size(); i++) {
								nombrej = examenes.get(i).getNombre();
								if (i < examenes.size() - 1) {
									for (int j = i + 1; j < examenes.size(); j++) {
										if (examenes.get(j).getNombre()
												.equals(nombrej)) {
											examenes.get(j).setNombre(
													nombrej + "2");
											j = examenes.size();
										}
									}
								}
							}
						} else {
							msj.mensajeError(archivoConError
									+ lblNombreExamen.getValue() + ". Fila: "
									+ contadorRow + ". Columna: "
									+ contadorCell);
							error = true;
						}
					} else {
						msj.mensajeError(errorLongitud
								+ lblNombreExamen.getValue()
								+ ". Fila: "
								+ contadorRow
								+ ". Columna: "
								+ contadorCell
								+ ". Longitudes permitidas: campo1 19 y campo2 50");
						error = true;
					}
				}
				if (!error)
					servicioExamen.guardarVarios(examenes);
				else
					errorGeneral = true;
			} else
				msj.mensajeAlerta(archivoVacio + " "
						+ lblNombreExamen.getValue());
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
			if (rowIterator.hasNext()) {
				List<Area> areas = new ArrayList<Area>();
				int contadorRow = 0;
				boolean error = false;
				boolean errorLong = false;
				while (rowIterator.hasNext() && !error && !errorLong) {
					contadorRow = contadorRow + 1;
					Row row = rowIterator.next();
					Area area = new Area();
					Double idReferencia = (double) 0;
					String idRef = null;
					String nombre = "";
					int contadorCell = 0;
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext() && !error && !errorLong) {
						contadorCell = contadorCell + 1;
						Cell cell = cellIterator.next();
						switch (cell.getColumnIndex()) {
						case 0:
							if (cell.getCellType() == 1)
								idRef = cell.getStringCellValue();
							else {
								idReferencia = cell.getNumericCellValue();
								if (idReferencia != null)
									idRef = String.valueOf(idReferencia
											.longValue());
							}
							if (idRef.length() > 100)
								errorLong = true;
							break;
						case 1:
							nombre = cell.getStringCellValue();
							if (nombre.length() > 100)
								errorLong = true;
							break;
						default:
							break;
						}
					}

					if (!errorLong) {
						if (!error && idRef != null && !idRef.equals("NULL")) {
							area.setNombre(nombre);
							area.setCodigo(idRef);
							area.setFechaAuditoria(fechaHora);
							area.setHoraAuditoria(horaAuditoria);
							area.setUsuarioAuditoria("frivero");
							areas.add(area);
							String nombrej = "";
							for (int i = 0; i < areas.size(); i++) {
								nombrej = areas.get(i).getNombre();
								if (i < areas.size() - 1) {
									for (int j = i + 1; j < areas.size(); j++) {
										if (areas.get(j).getNombre()
												.equals(nombrej)) {
											areas.get(j).setNombre(
													nombrej + "2");
											j = areas.size();
										}
									}
								}
							}

						} else {
							msj.mensajeError(archivoConError
									+ lblNombreArea.getValue() + ". Fila: "
									+ contadorRow + ". Columna: "
									+ contadorCell);
							error = true;
						}
					} else {
						msj.mensajeError(errorLongitud
								+ lblNombreArea.getValue()
								+ ". Fila: "
								+ contadorRow
								+ ". Columna: "
								+ contadorCell
								+ ". Longitudes permitidas: campo1 100 y campo2 100");
						error = true;
					}
				}
				if (!error)
					servicioArea.guardarVarios(areas);
				else
					errorGeneral = true;
			} else
				msj.mensajeAlerta(archivoVacio + " " + lblNombreArea.getValue());
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
			if (rowIterator.hasNext()) {
				CategoriaDiagnostico categoria = new CategoriaDiagnostico();
				categoria = servicioCategoriaDiagnostico.buscar(1);
				List<Diagnostico> diagnosticos = new ArrayList<Diagnostico>();
				int contadorRow = 0;
				boolean error = false;
				boolean errorLong = false;
				while (rowIterator.hasNext() && !error && !errorLong) {
					contadorRow = contadorRow + 1;
					Row row = rowIterator.next();
					Diagnostico diagnostico = new Diagnostico();
					Double idReferencia = (double) 0;
					long idRef = 0;
					String nombre = "";
					Iterator<Cell> cellIterator = row.cellIterator();
					int contadorCell = 0;
					while (cellIterator.hasNext() && !error && !errorLong) {
						contadorCell = contadorCell + 1;
						Cell cell = cellIterator.next();
						switch (cell.getColumnIndex()) {
						case 0:
							if (cell.getCellType() == 0) {
								idReferencia = cell.getNumericCellValue();
								if (idReferencia != null)
									idRef = idReferencia.longValue();
							} else {
								errorGeneral = true;
								error = true;
							}
							break;
						case 1:
							nombre = cell.getStringCellValue();
							if (nombre.length() > 500)
								errorLong = true;
							break;
						default:
							break;
						}
					}
					if (!errorLong) {
						if (!error) {
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
							String nombrej = "";
							for (int i = 0; i < diagnosticos.size(); i++) {
								nombrej = diagnosticos.get(i).getNombre();
								if (i < diagnosticos.size() - 1) {
									for (int j = i + 1; j < diagnosticos.size(); j++) {
										if (diagnosticos.get(j).getNombre()
												.equals(nombrej)) {
											diagnosticos.get(j).setNombre(
													nombrej + "2");
											j = diagnosticos.size();
										}
									}
								}
							}
						} else {
							msj.mensajeError(archivoConError
									+ lblNombreDiagnostico.getValue()
									+ ". Fila: " + contadorRow + ". Columna: "
									+ contadorCell);
							error = true;
						}
					} else {
						msj.mensajeError(errorLongitud
								+ lblNombreDiagnostico.getValue()
								+ ". Fila: "
								+ contadorRow
								+ ". Columna: "
								+ contadorCell
								+ ". Longitudes permitidas: campo1 19 y campo2 500");
						errorLong = true;
					}
				}
				if (!error)
					servicioDiagnostico.guardarVarios(diagnosticos);
				else
					errorGeneral = true;
			} else
				msj.mensajeAlerta(archivoVacio + " "
						+ lblNombreDiagnostico.getValue());
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
			if (rowIterator.hasNext()) {
				Empresa empresa = servicioEmpresa.buscar(1);
				Ciudad ciudad = servicioCiudad.buscar(1);
				Nomina nomina = servicioNomina.buscar(1);
				Cargo cargo = servicioCargo.buscar(1);
				try {
					imgUsuario.setContent(new AImage(url));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				List<Paciente> pacientes = new ArrayList<Paciente>();
				int contadorRow = 0;
				boolean error = false;
				boolean errorLong = false;
				while (rowIterator.hasNext() && !error && !errorLong) {
					contadorRow = contadorRow + 1;
					Row row = rowIterator.next();
					Area area = new Area();
					Paciente paciente = new Paciente();
					String primerNombre = "", cedula = null, segundoNombre = "", primerApellido = "", segundoApellido = "", direccion = "", sexo = "", ficha = "", codigoArea = "";
					Date fechaNac2 = new Date();
					Timestamp fechaNac = new Timestamp(fechaNac2.getTime());
					Double cedRef = (double) 0;
					Double dirRef = (double) 0;
					Double fichaRef = (double) 0;
					Double codigoRef = (double) 0;
					byte[] imagenUsuario = null;
					Iterator<Cell> cellIterator = row.cellIterator();
					int contadorCell = 0;
					while (cellIterator.hasNext() && !error && !errorLong) {
						contadorCell = contadorCell + 1;
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
							if (cedula.length() > 15)
								errorLong = true;
							break;
						case 1:
							primerNombre = cell.getStringCellValue();
							if (primerNombre.length() > 100)
								errorLong = true;
							break;
						case 2:
							segundoNombre = cell.getStringCellValue();
							if (segundoNombre.length() > 100)
								errorLong = true;
							break;
						case 3:
							primerApellido = cell.getStringCellValue();
							if (primerApellido.length() > 100)
								errorLong = true;
							break;
						case 4:
							segundoApellido = cell.getStringCellValue();
							if (segundoApellido.length() > 100)
								errorLong = true;
							break;
						case 5:
							if (cell.getCellType() == 1)
								direccion = cell.getStringCellValue();
							else {
								dirRef = cell.getNumericCellValue();
								if (dirRef != null)
									direccion = String.valueOf(dirRef
											.longValue());
							}
							if (direccion.length() > 1000)
								errorLong = true;
							break;
						case 7:
							if (cell.getCellType() == 0) {
								fechaNac2 = cell.getDateCellValue();
								fechaNac = new Timestamp(fechaNac2.getTime());
							} else
								error = true;
							break;
						case 8:
							sexo = cell.getStringCellValue();
							break;
						case 11:
							if (cell.getCellType() == 1) {
								if (cell.getStringCellValue().equals("NULL"))
									ficha = "";
								else
									ficha = cell.getStringCellValue();
							} else {
								fichaRef = cell.getNumericCellValue();
								if (fichaRef != null) {
									ficha = String
											.valueOf(fichaRef.longValue());
								}
							}
							break;
						case 12:
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

					if (!errorLong) {
						if (!error) {
							paciente.setCedula(cedula.replaceAll("\\s", ""));
							paciente.setPrimerNombre(primerNombre);
							paciente.setSegundoNombre(segundoNombre);
							paciente.setPrimerApellido(primerApellido);
							paciente.setSegundoApellido(segundoApellido);
							paciente.setDireccion(direccion);
							if (sexo.equalsIgnoreCase("Femenino"))
								paciente.setSexo("Femenino");
							else
								paciente.setSexo("Masculino");
							imagenUsuario = imgUsuario.getContent()
									.getByteData();
							paciente.setImagen(imagenUsuario);
							paciente.setFicha(ficha);
							area = servicioArea.buscarPorCodigo(codigoArea);
							if (area == null || codigoArea.equals("-")
									|| codigoArea.equals("NULL"))
								area = servicioArea.buscar(1); //
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
						} else {
							msj.mensajeError(archivoConError
									+ lblNombrePaciente.getValue() + ". Fila: "
									+ contadorRow + ". Columna: "
									+ contadorCell);
							error = true;
						}
					} else {
						msj.mensajeError(errorLongitud
								+ lblNombrePaciente.getValue()
								+ ". Fila: "
								+ contadorRow
								+ ". Columna: "
								+ contadorCell
								+ ". Longitudes permitidas: campo1 20 y campo2 12");
						errorLong = true;
						error = true;
					}
				}
				if (!error)
					servicioPaciente.guardarVarios(pacientes);
				else
					errorGeneral = true;
			} else
				msj.mensajeAlerta(archivoVacio + " "
						+ lblNombrePaciente.getValue());
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
			if (rowIterator.hasNext()) {
				Usuario usuario = servicioUsuario.buscarUsuarioPorId("1");
				Area area = servicioArea.buscar(1);
				Cargo cargo = servicioCargo.buscar(1);
				List<Consulta> consultas = new ArrayList<Consulta>();
				int contadorRow = 0;
				boolean error = false;
				boolean errorLong = false;
				while (rowIterator.hasNext() && !error && !errorLong) {
					contadorRow = contadorRow + 1;
					System.out.println(contadorRow);
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
					int contadorCell = 0;
					while (cellIterator.hasNext() && !error && !errorLong) {
						contadorCell = contadorCell + 1;
						Cell cell = cellIterator.next();
						switch (cell.getColumnIndex()) {
						case 0:
							if (cell.getCellType() == 1) {
								cedRef = cell.getStringCellValue();
							} else {
								cedReferencia = cell.getNumericCellValue();
								if (cedReferencia != null)
									cedRef = String.valueOf(cedReferencia
											.longValue());
							}
							if (cedRef.length() > 15)
								errorLong = true;
							break;
						case 1:
							if (cell.getCellType() == 0) {
								fecha2 = cell.getDateCellValue();
								fechaReal = new Timestamp(fecha2.getTime());
							} else {
								error = true;
								errorGeneral = true;
							}
							break;
						case 3:
							if (cell.getCellType() == 1) {
								enfermedadActual = cell.getStringCellValue();
								if (enfermedadActual.length() > 1500)
									errorLong = true;
							} else
								error = true;
							break;
						case 4:
							doctor = cell.getStringCellValue();
							if (doctor.length() > 256)
								errorLong = true;
							break;
						case 5:
							if (cell.getCellType() == 0) {
								idReferencia = cell.getNumericCellValue();
								if (idReferencia != null)
									idRef = idReferencia.longValue();
							} else {
								error = true;
								errorGeneral = true;
							}
							break;
						default:
							break;
						}
					}
					if (!errorLong) {
						if (!error) {
							consulta.setIdReferencia(idRef);
							consulta.setCedulaReferencia(cedRef.replaceAll(
									"\\s", ""));
							consulta.setEnfermedadActual(enfermedadActual);
							consulta.setMotivoConsulta("");
							consulta.setTipoConsulta("Preventiva");
							consulta.setTipoConsultaSecundaria("Reintegro");
							consulta.setDoctor(doctor);
							consulta.setFechaConsulta(fechaReal);
							consulta.setUsuario(usuario);
							consulta.setCargo(cargo);
							consulta.setArea(area);
							Paciente paciente = servicioPaciente
									.buscarPorCedula(cedRef.replaceAll("\\s",
											""));
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
						} else {
							msj.mensajeError(archivoConError
									+ lblNombreConsulta.getValue() + ". Fila: "
									+ contadorRow + ". Columna: "
									+ contadorCell);
							error = true;
						}
					} else {
						msj.mensajeError(errorLongitud
								+ lblNombreConsulta.getValue()
								+ ". Fila: "
								+ contadorRow
								+ ". Columna: "
								+ contadorCell
								+ ". Longitudes permitidas: campo1 15, campo4 1500, campo5 256");
						errorLong = true;
						error = true;
					}
				}
				if (!error)
					servicioConsulta.guardarVarios(consultas);
				else
					errorGeneral = true;
			} else
				msj.mensajeAlerta(archivoVacio + " "
						+ lblNombreConsulta.getValue());
		}
	}

	@Listen("onUpload = #btnImportarMedicina")
	public void cargarMedicina(UploadEvent event) {
		mediaMedicina = event.getMedia();
		if (mediaMedicina != null && Validador.validarExcel(mediaMedicina)) {
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
		} else
			msj.mensajeError(Mensaje.archivoExcel);
	}

	@Listen("onUpload = #btnImportarDiagostico")
	public void cargarDiagostico(UploadEvent event) {
		mediaDiagnostico = event.getMedia();
		if (mediaDiagnostico != null
				&& Validador.validarExcel(mediaDiagnostico)) {
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
		} else
			msj.mensajeError(Mensaje.archivoExcel);
	}

	@Listen("onUpload = #btnImportarExamen")
	public void cargarExamen(UploadEvent event) {
		mediaExamen = event.getMedia();
		if (mediaExamen != null && Validador.validarExcel(mediaExamen)) {
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
		} else
			msj.mensajeError(Mensaje.archivoExcel);
	}

	@Listen("onUpload = #btnImportarArea")
	public void cargarArea(UploadEvent event) {
		mediaArea = event.getMedia();
		if (mediaArea != null && Validador.validarExcel(mediaArea)) {
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
		} else
			msj.mensajeError(Mensaje.archivoExcel);
	}

	@Listen("onUpload = #btnImportarConsulta")
	public void cargarConsulta(UploadEvent event) {
		mediaConsulta = event.getMedia();
		if (mediaConsulta != null && Validador.validarExcel(mediaConsulta)) {
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
		} else
			msj.mensajeError(Mensaje.archivoExcel);
	}

	@Listen("onUpload = #btnImportarPaciente")
	public void cargarPaciente(UploadEvent event) {
		mediaPaciente = event.getMedia();
		if (mediaPaciente != null && Validador.validarExcel(mediaPaciente)) {
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
		} else
			msj.mensajeError(Mensaje.archivoExcel);
	}

	@Listen("onUpload = #btnImportarExamenConsulta")
	public void cargarExamenConsulta(UploadEvent event) {
		mediaConsultaExamen = event.getMedia();
		if (mediaConsultaExamen != null
				&& Validador.validarExcel(mediaConsultaExamen)) {
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
		} else
			msj.mensajeError(Mensaje.archivoExcel);
	}

	@Listen("onUpload = #btnImportarDiagnosticoConsulta")
	public void cargarDiagnosticoConsulta(UploadEvent event) {
		mediaConsultaDiagnostico = event.getMedia();
		if (mediaConsultaDiagnostico != null
				&& Validador.validarExcel(mediaConsultaDiagnostico)) {
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
		} else
			msj.mensajeError(Mensaje.archivoExcel);
	}

	@Listen("onUpload = #btnImportarMedicinaConsulta")
	public void cargarMedicinaConsulta(UploadEvent event) {
		mediaConsultaMedicina = event.getMedia();
		if (mediaConsultaMedicina != null
				&& Validador.validarExcel(mediaConsultaMedicina)) {
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
		} else
			msj.mensajeError(Mensaje.archivoExcel);
	}
}
