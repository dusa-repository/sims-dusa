package controlador.sha;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import modelo.sha.GrupoInspectores;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Mensaje;
import componentes.Validador;

import controlador.maestros.CGenerico;

public class CGrupoInspectores extends CGenerico {

	@Wire
	private Div botoneraGrupoInspectores;
	@Wire
	private Textbox txtGrupo;
	@Wire
	private Div divGrupoInspectores;
	private String nombre;

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
		actualizar();
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divGrupoInspectores, nombre, tabs);

			}

			@Override
			public void limpiar() {

			}

			@Override
			public void guardar() {
				if (validar()) {
					String nombre = txtGrupo.getValue();
					GrupoInspectores grupo = new GrupoInspectores();
					grupo.setId(1);
					grupo.setGrupo(nombre);
					servicioGrupoInspectores.guardar(grupo);
					msj.mensajeInformacion(Mensaje.guardado);
					actualizar();
				}

			}

			@Override
			public void eliminar() {

			}
		};

		botonera.getChildren().get(1).setVisible(false);
		botoneraGrupoInspectores.appendChild(botonera);
	}

	private void actualizar() {
		txtGrupo.setValue("");
		GrupoInspectores grupo = servicioGrupoInspectores.buscar(1);
		if (grupo != null)
			txtGrupo.setValue(grupo.getGrupo());

	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtGrupo.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else {
			if (!Validador.validarCorreo(txtGrupo.getValue())) {
				msj.mensajeError(Mensaje.correoInvalido);
				return false;
			} else
				return true;
		}
	}
}
