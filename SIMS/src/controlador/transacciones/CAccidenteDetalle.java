package controlador.transacciones;

import java.io.IOException;
import java.util.HashMap;

import modelo.maestros.Diagnostico;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import componentes.Botonera;

import controlador.maestros.CGenerico;

public class CAccidenteDetalle extends CGenerico {

	@Wire
	private Window wdwRegistro;
	@Wire
	private Div botoneraDetalle;
	@Wire
	private Label lblDiagnostio;
	@Wire
	private Textbox txtLugar;
	@Wire
	private Datebox dtbFecha;
	@Wire
	private Textbox txtRazon;
	private CConsulta controlador = new CConsulta();

	@Override
	public void inicializar() throws IOException {
		HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("consulta");
		if (map != null) {
			if (map.get("idDiagnostico") != null) {
				Diagnostico diagnostico = servicioDiagnostico.buscar((long) map
						.get("idDiagnostico"));
				lblDiagnostio.setValue(diagnostico.getNombre());
				map.clear();
				map = null;
			}
		}

		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				wdwRegistro.onClose();
			}

			@Override
			public void limpiar() {
				// TODO Auto-generated method stub

			}

			@Override
			public void guardar() {
				// TODO Auto-generated method stub

			}

			@Override
			public void eliminar() {
				// TODO Auto-generated method stub

			}
		};
		botonera.getChildren().get(1).setVisible(false);
		botonera.getChildren().get(2).setVisible(false);
		botoneraDetalle.appendChild(botonera);
	}
}
