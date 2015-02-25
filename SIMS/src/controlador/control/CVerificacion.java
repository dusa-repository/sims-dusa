package controlador.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import modelo.transacciones.Consulta;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Tab;

import componentes.Botonera;
import componentes.Mensaje;
import controlador.maestros.CGenerico;

public class CVerificacion extends CGenerico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire
	private Longbox txtCodigo;
	@Wire
	private Div botoneraControl;
	@Wire
	private Div divVVerificacion;
	private String titulo;

	@Override
	public void inicializar() throws IOException {
		HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (map != null) {
			if (map.get("tabsGenerales") != null) {
				tabs = (List<Tab>) map.get("tabsGenerales");
				titulo = (String) map.get("titulo");
				map.clear();
				map = null;
			}
		}
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divVVerificacion, titulo, tabs);
			}

			@Override
			public void limpiar() {
				txtCodigo.setValue(null);
			}

			@Override
			public void guardar() {
				if (validar()) {
					if (!servicioConsultaMedicina.buscarPorConsulta(
							servicioConsulta.buscar(txtCodigo.getValue()))
							.isEmpty())
						Clients.evalJavaScript("window.open('"
								+ damePath()
								+ "Reportero?valor=1&valor2="
								+ txtCodigo.getValue()
								+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
					else
						Mensaje.mensajeAlerta("El numero de recipe no existe");
				}
			}

			@Override
			public void eliminar() {
			}
		};
		Button guardar = (Button) botonera.getChildren().get(0);
		guardar.setLabel("Validar");
		botonera.getChildren().get(1).setVisible(false);
		botoneraControl.appendChild(botonera);
	}

	protected boolean validar() {
		if (txtCodigo.getText().compareTo("") == 0) {
			Mensaje.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

}
