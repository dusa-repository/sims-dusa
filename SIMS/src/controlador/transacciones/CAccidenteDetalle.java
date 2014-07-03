package controlador.transacciones;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import modelo.generico.DetalleAccidente;
import modelo.maestros.Diagnostico;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
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
	private List<DetalleAccidente> lista = new ArrayList<DetalleAccidente>();
	private long id = 0;
	private DetalleAccidente detalle = new DetalleAccidente();

	@Override
	public void inicializar() throws IOException {
		HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("consulta");
		if (map != null) {
			if (map.get("idDiagnostico") != null) {
				id = (long) map.get("idDiagnostico");
				Diagnostico diagnostico = servicioDiagnostico.buscar((long) map
						.get("idDiagnostico"));
				lista = (List<DetalleAccidente>) map.get("lista");
				lblDiagnostio.setValue(diagnostico.getNombre());
				for (int i = 0; i < lista.size(); i++) {
					if (id == lista.get(i).getDiagnostico()) {
						settearCampos(lista.get(i));
						detalle = lista.get(i);
					}
				}
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
				lista = null;
				id = 0;
				detalle = null;
			}

			@Override
			public void guardar() {
				if (validar()) {
					String lugar = txtLugar.getValue();
					String motivo = txtRazon.getValue();
					Date fechaCon = dtbFecha.getValue();
					Timestamp fecha = new Timestamp(fechaCon.getTime());
					DetalleAccidente detalleAccidente = new DetalleAccidente(
							id, lugar, motivo, fecha);
					if (detalle != null)
						lista.remove(detalle);
					lista.add(detalleAccidente);
					controlador.recibirLista(lista);
					limpiar();
					salir();
				}
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

	private void settearCampos(DetalleAccidente detalleAccidente) {
		txtLugar.setValue(detalleAccidente.getLugar());
		txtRazon.setValue(detalleAccidente.getMotivo());
		dtbFecha.setValue(detalleAccidente.getFecha());
	}

	protected boolean validar() {
		if (txtLugar.getText().compareTo("") == 0
				|| txtRazon.getText().compareTo("") == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else
			return true;
	}
}
