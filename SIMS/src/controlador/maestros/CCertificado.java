package controlador.maestros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import modelo.maestros.Paciente;
import modelo.maestros.Periodo;
import modelo.maestros.PeriodoPaciente;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.A;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Row;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;
import componentes.Validador;

public class CCertificado extends CGenerico {

	private static final long serialVersionUID = 613737464784723431L;
	@Wire
	private Div botoneraCertificado;
	@Wire
	private Textbox txtPeriodo;
	@Wire
	private Button btnBuscar;
	@Wire
	private Div catalogoPeriodo;
	@Wire
	private Div divCertificado;
	@Wire
	private Row row;
	@Wire
	private Listbox ltbPacientes;
	@Wire
	private Listbox ltbPacientesAgregados;
	@Wire
	private Label lblNombre;
	List<Paciente> pacientes = new ArrayList<Paciente>();
	List<PeriodoPaciente> pacientesAgregados = new ArrayList<PeriodoPaciente>();
	private long idPeriodo = 0;
	private Media media;
	Catalogo<Periodo> catalogo;
	String nombre = "";
	private String errorLongitud = "La siguiente ubicacion excede el limite establecido de longitud:";
	private String archivoConError = "Existe un error en el siguiente archivo adjunto: ";

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
		llenarListas();
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				// TODO Auto-generated method stub

			}

			@Override
			public void limpiar() {
				if (row.getChildren().size() == 4) {
					A linea = (A) row.getChildren().get(3);
					Events.postEvent("onClick", linea, null);
				}
				idPeriodo = 0;
				txtPeriodo.setValue("");
				llenarListas();
			}

			@Override
			public void guardar() {
				if (validar()) {
					Periodo periodo = servicioPeriodo.buscar(idPeriodo);
					if (media == null){
						guardarLista(periodo);
						msj.mensajeInformacion(Mensaje.guardado);
					}
					else
						guardarArchivo(periodo);
					limpiar();
				}
			}

			@Override
			public void eliminar() {
			}
		};
		botonera.getChildren().get(1).setVisible(false);
		botoneraCertificado.appendChild(botonera);
	}

	protected void guardarArchivo(Periodo periodo) {
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(media.getStreamData());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheetAt(0);
		Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = sheet
				.iterator();
		String mostrarError = "";
		boolean error = false;
		boolean errorLong = false;
		if (rowIterator.hasNext()) {
			List<PeriodoPaciente> periodosPacientes = new ArrayList<PeriodoPaciente>();
			int contadorRow = 0;
			boolean entro = false;
			while (rowIterator.hasNext()) {
				contadorRow = contadorRow + 1;
				org.apache.poi.ss.usermodel.Row row = rowIterator.next();
				if (!entro) {
					row = rowIterator.next();
					contadorRow = contadorRow + 1;
					entro = true;
				}
				PeriodoPaciente periodoPaciente = new PeriodoPaciente();
				Paciente paciente = null;
				String idPaciente = null;
				Double refPaciente = null;
				String vdrl = null;
				Double idVdrl = null;
				String heces = null;
				Double idHeces = null;
				String citologia = null;
				Double idCitologia = null;
				String observacion = null;
				Double idObservacion = null;
				Iterator<Cell> cellIterator = row.cellIterator();
				int contadorCell = 0;
				while (cellIterator.hasNext()) {
					contadorCell = contadorCell + 1;
					Cell cell = cellIterator.next();
					switch (cell.getColumnIndex()) {
					case 0:
						idPaciente = obtenerStringCualquiera(cell, refPaciente,
								idPaciente);
						if (idPaciente != null) {
							if (idPaciente.length() > 15) {
								mostrarError = mensajeErrorLongitud(
										mostrarError, contadorRow, contadorCell);
								errorLong = true;
							} else {
								paciente = servicioPaciente
										.buscarPorCedula(idPaciente);
								if (paciente == null) {
									mostrarError = mensajeErrorNoEncontrado(
											mostrarError, idPaciente,
											contadorRow, contadorCell,
											"PAciente");
									error = true;
								}
							}
						} else {
							mostrarError = mensajeErrorNull(mostrarError,
									contadorRow, contadorCell);
							error = true;
						}
						break;
					case 1:
						vdrl = obtenerStringCualquiera(cell, idVdrl, vdrl);
						if (vdrl != null) {
							if (vdrl.length() > 250) {
								mostrarError = mensajeErrorLongitud(
										mostrarError, contadorRow, contadorCell);
								errorLong = true;
							}
						} else {
							mostrarError = mensajeErrorNull(mostrarError,
									contadorRow, contadorCell);
							error = true;
						}
						break;
					case 2:
						heces = obtenerStringCualquiera(cell, idHeces, heces);
						if (heces != null) {
							if (heces.length() > 250) {
								mostrarError = mensajeErrorLongitud(
										mostrarError, contadorRow, contadorCell);
								errorLong = true;
							}
						} else {
							mostrarError = mensajeErrorNull(mostrarError,
									contadorRow, contadorCell);
							error = true;
						}
						break;
					case 3:
						citologia = obtenerStringCualquiera(cell, idCitologia,
								citologia);
						if (citologia != null) {
							if (citologia.length() > 250) {
								mostrarError = mensajeErrorLongitud(
										mostrarError, contadorRow, contadorCell);
								errorLong = true;
							}
						} else {
							mostrarError = mensajeErrorNull(mostrarError,
									contadorRow, contadorCell);
							error = true;
						}
						break;
					case 4:
						observacion = obtenerStringCualquiera(cell,
								idObservacion, observacion);
						if (observacion != null) {
							if (observacion.length() > 500) {
								mostrarError = mensajeErrorLongitud(
										mostrarError, contadorRow, contadorCell);
								errorLong = true;
							}
						} else {
							mostrarError = mensajeErrorNull(mostrarError,
									contadorRow, contadorCell);
							error = true;
						}
						break;
					}
				}
				if (!error && !errorLong && paciente != null
						&& observacion != null && vdrl != null && heces != null
						&& citologia != null) {
					periodoPaciente.setPeriodo(periodo);
					periodoPaciente.setPaciente(paciente);
					periodoPaciente.setCitologia(citologia);
					periodoPaciente.setHeces(heces);
					periodoPaciente.setObservacion(observacion);
					periodoPaciente.setVdrl(vdrl);
					periodosPacientes.add(periodoPaciente);
				}
			}
			if (!error && !errorLong) {
				servicioPeriodoPaciente.eliminar(periodo);
				servicioPeriodoPaciente.guardarVarios(periodosPacientes);
				msj.mensajeInformacion("Archivo importado con exito" + "\n"
						+ "Cantidad de Filas evaluadas:" + (contadorRow - 1)
						+ "\n" + "Cantidad de Filas insertadas:"
						+ (contadorRow - 1));
			} else
				msj.mensajeError("El archivo no ha podido ser importado, causas:"
						+ "\n"
						+ mostrarError
						+ "\n"
						+ "Cantidad de Filas evaluadas:"
						+ (contadorRow - 1)
						+ "\n" + "Cantidad de Filas insertadas: 0");
		}
	}

	protected void guardarLista(Periodo periodo) {

		List<PeriodoPaciente> lista = new ArrayList<PeriodoPaciente>();
		for (int i = 0; i < ltbPacientesAgregados.getItemCount(); i++) {
			Listitem listItem = ltbPacientesAgregados.getItemAtIndex(i);
			Listcell celda = (Listcell) listItem.getChildren().get(1);
			String cedula = celda.getLabel();
			Paciente pacientej = servicioPaciente.buscarPorCedula(cedula);
			String vdrl = ((Combobox) ((listItem.getChildren().get(3)))
					.getFirstChild()).getValue();
			String heces = ((Combobox) ((listItem.getChildren().get(4)))
					.getFirstChild()).getValue();
			String citologia = ((Combobox) ((listItem.getChildren().get(5)))
					.getFirstChild()).getValue();
			String observacion = ((Textbox) ((listItem.getChildren().get(6)))
					.getFirstChild()).getValue();
			PeriodoPaciente periodoPaciente = new PeriodoPaciente(periodo,
					pacientej, vdrl, heces, citologia, observacion);
			lista.add(periodoPaciente);
		}
		servicioPeriodoPaciente.eliminar(periodo);
		servicioPeriodoPaciente.guardarVarios(lista);

	}

	protected boolean validar() {
		if (idPeriodo == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	private void llenarListas() {
		Periodo periodo = servicioPeriodo.buscar(idPeriodo);
		pacientes = servicioPaciente.buscarDisponiblesPeriodo(periodo);
		ltbPacientes.setModel(new ListModelList<Paciente>(pacientes));
		pacientesAgregados = servicioPeriodoPaciente.buscarPorPeriodo(periodo);
		ltbPacientesAgregados.setModel(new ListModelList<PeriodoPaciente>(
				pacientesAgregados));
		listasMultiples();
	}

	private void listasMultiples() {
		ltbPacientes.setMultiple(false);
		ltbPacientes.setCheckmark(false);
		ltbPacientes.setMultiple(true);
		ltbPacientes.setCheckmark(true);
		ltbPacientesAgregados.setMultiple(false);
		ltbPacientesAgregados.setCheckmark(false);
		ltbPacientesAgregados.setMultiple(true);
		ltbPacientesAgregados.setCheckmark(true);
	}

	@Listen("onUpload = #btnImportar")
	public void cargarDiagnosticoConsulta(UploadEvent event) {
		media = event.getMedia();
		if (media != null && Validador.validarExcel(media)) {
			lblNombre.setValue(media.getName());
			final A rm = new A("Remover");
			rm.addEventListener(Events.ON_CLICK,
					new org.zkoss.zk.ui.event.EventListener<Event>() {
						public void onEvent(Event event) throws Exception {
							lblNombre.setValue("");
							rm.detach();
							media = null;
						}
					});
			row.appendChild(rm);
		} else
			msj.mensajeError(Mensaje.archivoExcel);
	}

	/* Muestra el catalogo de los paises */
	@Listen("onClick = #btnBuscar")
	public void mostrarCatalogo() {
		final List<Periodo> periodos = servicioPeriodo.buscarTodos();
		catalogo = new Catalogo<Periodo>(catalogoPeriodo,
				"Catalogo de Periodos", periodos, "Nombre", "Fecha Inicio",
				"Fecha Fin") {
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
	@Listen("onChange = #txtNombre")
	public void buscarPorNombre() {
		Periodo periodo = servicioPeriodo
				.buscarPorNombre(txtPeriodo.getValue());
		if (periodo != null)
			llenarCampos(periodo);
		else {
			idPeriodo = 0;
			txtPeriodo.setValue("");
			llenarListas();
		}
	}

	/* LLena los campos del formulario dado un pais */
	private void llenarCampos(Periodo periodo) {
		txtPeriodo.setValue(periodo.getNombre());
		idPeriodo = periodo.getIdPeriodo();
		llenarListas();
	}

	@Listen("onClick = #pasar1")
	public void derecha() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem = ltbPacientes.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					Paciente paciente = listItem.get(i).getValue();
					pacientes.remove(paciente);
					PeriodoPaciente periodoPaciente = new PeriodoPaciente();
					periodoPaciente.setPaciente(paciente);
					pacientesAgregados.clear();
					for (int j = 0; j < ltbPacientesAgregados.getItemCount(); j++) {
						Listitem listItemj = ltbPacientesAgregados
								.getItemAtIndex(j);
						Listcell celda = (Listcell) listItemj.getChildren()
								.get(1);
						String cedula = celda.getLabel();
						Paciente pacientej = servicioPaciente
								.buscarPorCedula(cedula);
						String vdrl = ((Combobox) ((listItemj.getChildren()
								.get(3))).getFirstChild()).getValue();
						String heces = ((Combobox) ((listItemj.getChildren()
								.get(4))).getFirstChild()).getValue();
						String citologia = ((Combobox) ((listItemj
								.getChildren().get(5))).getFirstChild())
								.getValue();
						String observacion = ((Textbox) ((listItemj
								.getChildren().get(6))).getFirstChild())
								.getValue();
						PeriodoPaciente periodoPacientej = new PeriodoPaciente(
								null, pacientej, vdrl, heces, citologia,
								observacion);
						pacientesAgregados.add(periodoPacientej);
					}
					pacientesAgregados.add(periodoPaciente);
					ltbPacientesAgregados
							.setModel(new ListModelList<PeriodoPaciente>(
									pacientesAgregados));
					ltbPacientesAgregados.renderAll();
					listitemEliminar.add(listItem.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbPacientes.renderAll();
			ltbPacientes.removeItemAt(listitemEliminar.get(i).getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar2")
	public void izquierda() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem2 = ltbPacientesAgregados.getItems();
		if (listItem2.size() != 0) {
			for (int i = 0; i < listItem2.size(); i++) {
				if (listItem2.get(i).isSelected()) {
					PeriodoPaciente periodoPaciente = listItem2.get(i)
							.getValue();
					pacientesAgregados.remove(periodoPaciente);
					pacientes.add(periodoPaciente.getPaciente());
					ltbPacientes
							.setModel(new ListModelList<Paciente>(pacientes));
					ltbPacientes.renderAll();
					listitemEliminar.add(listItem2.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbPacientesAgregados.renderAll();
			ltbPacientesAgregados.removeItemAt(listitemEliminar.get(i)
					.getIndex());
		}
		listasMultiples();
	}

	private String obtenerStringCualquiera(Cell cell, Double idReferencia,
			String idRef) {
		if (cell.getCellType() == 0) {
			idReferencia = cell.getNumericCellValue();
			if (idReferencia != null)
				return idRef = String.valueOf(idReferencia.longValue());
			else
				return null;
		} else {
			if (cell.getCellType() == 1) {
				if (!cell.getStringCellValue().equals("NULL"))
					return idRef = cell.getStringCellValue();
				else
					return null;
			}
			return null;
		}
	}

	private String mensajeErrorNull(String mostrarError, int contadorRow,
			int contadorCell) {
		return mostrarError + archivoConError + ". Fila: " + contadorRow
				+ ". Columna: " + contadorCell + "\n";
	}

	private String mensajeErrorLongitud(String mostrarError, int contadorRow,
			int contadorCell) {
		return mostrarError + errorLongitud + ". Fila: " + contadorRow
				+ ". Columna: " + contadorCell + "\n";
	}

	private String mensajeErrorNoEncontrado(String mostrarError,
			String idCliente, int contadorRow, int contadorCell, String nombre) {
		return mostrarError + " El valor " + idCliente
				+ " no se ha encontrado en la tabla  " + nombre + ".  Fila: "
				+ contadorRow + ". Columna: " + contadorCell + "\n";
	}
}
