package controlador.sha;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import modelo.maestros.Accidente;
import modelo.sha.ClasificacionAccidente;
import modelo.sha.HorasHombre;
import modelo.sha.Informe;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublespinner;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;

import controlador.maestros.CGenerico;

public class CHorasHombre extends CGenerico {

	@Wire
	private Div botoneraHorasHombre;
	@Wire
	private Doublespinner dbsHoras;
	@Wire
	private Datebox dtbFecha;
	@Wire
	private Button btnBuscarHorasHombre;
	@Wire
	private Div catalogoHorasHombre;
	@Wire
	private Div divHorasHombre;
	private long id = 0;
	Catalogo<HorasHombre> catalogo;

	@Override
	public void inicializar() throws IOException {
		HashMap<String, Object> mapa = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (mapa != null) {
			if (mapa.get("tabsGenerales") != null) {
				tabs = (List<Tab>) mapa.get("tabsGenerales");
				mapa.clear();
				mapa = null;
			}
		}
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divHorasHombre,
						"Horas Hombre", tabs);

			}

			@Override
			public void limpiar() {
				dbsHoras.setValue(0.0);
				dbsHoras.setFocus(true);
				dtbFecha.setValue(fechaHora);
				id = 0;
			}

			@Override
			public void guardar() {
				if (validar()) {
					Double horas = dbsHoras.getValue();
					Timestamp fecha = fechaHora;
					Date fechaN = dtbFecha.getValue();
					if(dtbFecha.getValue()!=null)
					fecha = new Timestamp(fechaN.getTime());

					HorasHombre horasHombre = new HorasHombre(
							id, horas, fecha,fechaHora, horaAuditoria,
							nombreUsuarioSesion());
					servicioHorasHombre
							.guardar(horasHombre);
					msj.mensajeInformacion(Mensaje.guardado);
					limpiar();
				}
			}

			@Override
			public void eliminar() {
			}
		};
		botonera.getChildren().get(1).setVisible(false);
		botoneraHorasHombre.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (dbsHoras.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	/* Muestra el catalogo de los roles */
	@Listen("onClick = #btnBuscarHorasHombre")
	public void mostrarCatalogo() {
		final List<HorasHombre> horasHombres = servicioHorasHombre
				.buscarTodos();
		catalogo = new Catalogo<HorasHombre>(
				catalogoHorasHombre,
				"Catalogo de Horas Hombre", horasHombres,false,
				"Horas","Fecha") {

			@Override
			protected List<HorasHombre> buscar(String valor,
					String combo) {

				switch (combo) {
				case "Horas":
					return servicioHorasHombre.filtroHoras(valor);
				case "Fecha":
					return servicioHorasHombre.filtroFecha(valor);
				default:
					return horasHombres;
				}
			}

			@Override
			protected String[] crearRegistros(HorasHombre rol) {
				String[] registros = new String[2];
				registros[0] = String.valueOf(rol.getHoras());
				registros[1] =formatoHorasHombre.format(rol.getFecha());
				return registros;
			}
		};
		catalogo.getListbox().setPageSize(6);
		catalogo.setParent(catalogoHorasHombre);
		catalogo.doModal();
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoHorasHombre")
	public void seleccionar() {
		HorasHombre horasHombre = catalogo
				.objetoSeleccionadoDelCatalogo();
		llenarCampos(horasHombre);
		catalogo.setParent(null);
	}

	/* LLena los campos del formulario dado un pais */
	private void llenarCampos(HorasHombre horasHombre) {
		dbsHoras.setValue(horasHombre.getHoras());
		dtbFecha.setValue(horasHombre.getFecha());
		id = horasHombre.getIdHorasHombre();
	}

}