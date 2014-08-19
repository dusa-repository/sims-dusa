package controlador.seguridad;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import modelo.maestros.CategoriaDiagnostico;
import modelo.maestros.Diagnostico;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
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
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;

import componentes.Botonera;

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
	private org.zkoss.zul.Row rowDiagnostico;
	@Wire
	private Label lblNombrePaciente;
	private Media mediaExamen;
	private Media mediaMedicina;
	private Media mediaArea;
	private Media mediaDiagnostico;
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

	protected void importarDiagnosticos() {
		if (mediaDiagnostico != null) {
			XSSFWorkbook workbook = null;
			try {
				workbook = new XSSFWorkbook(mediaDiagnostico.getStreamData());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			XSSFSheet sheet = workbook.getSheetAt(1);
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

	@Listen("onUpload = #btnImportarMedicina")
	public void cargarMedicina(UploadEvent event) {
		mediaMedicina = event.getMedia();

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
	}

	@Listen("onUpload = #btnImportarExamen")
	public void cargarExamen(UploadEvent event) {
		mediaExamen = event.getMedia();

	}

	@Listen("onUpload = #btnImportarArea")
	public void cargarArea(UploadEvent event) {
		mediaArea = event.getMedia();

	}

	@Listen("onUpload = #btnImportarPaciente")
	public void cargarPaciente(UploadEvent event) {
		mediaPaciente = event.getMedia();

	}

	public boolean validar() {
		return true;
	}

}
