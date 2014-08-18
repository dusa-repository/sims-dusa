package controlador.seguridad;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
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
	private Label lblNombrePaciente;
	
	@Override
	public void inicializar() throws IOException {
		
		contenido = (Include) divImportar.getParent();
		Tabbox tabox = (Tabbox) divImportar.getParent().getParent()
				.getParent().getParent();
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
				if(validar())
				{
					
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
	
	@Listen("onUpload = #btnImportarMedicina")
	public void cargarMedicina(UploadEvent event) {

		
	}
	@Listen("onUpload = #btnImportarDiagostico")
	public void cargarDiagostico(UploadEvent event) {

		
	}

	@Listen("onUpload = #btnImportarExamen")
	public void cargarExamen(UploadEvent event) {

		
	}

	@Listen("onUpload = #btnImportarArea")
	public void cargarArea(UploadEvent event) {

		
	}

	@Listen("onUpload = #btnImportarPaciente")
	public void cargarPaciente(UploadEvent event) {

		
	}
	
	public boolean validar()
	{
		return true;
	}


}
