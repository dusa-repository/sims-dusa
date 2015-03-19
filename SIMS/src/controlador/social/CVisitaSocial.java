package controlador.social;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import modelo.maestros.Estado;
import modelo.maestros.Pais;
import modelo.social.VisitaSocial;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.CheckEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Tab;

import componentes.Botonera;
import componentes.Mensaje;

import controlador.maestros.CGenerico;

public class CVisitaSocial extends CGenerico {

	@Wire
	private Div botoneraSocial;
	// Combobox
	@Wire
	private Combobox cmbTipoVivienda;
	@Wire
	private Combobox cmbTerrenoVivienda;
	@Wire
	private Combobox cmbCondicionVivienda;
	@Wire
	private Combobox cmbAbasteceAgua;
	@Wire
	private Combobox cmbFrecuenciaAgua;
	@Wire
	private Combobox cmbViviendaPosee;
	@Wire
	private Combobox cmbEliminaBasura;
	@Wire
	private Combobox cmbCombustibleCocina;
	@Wire
	private Combobox cmbCantidadPersonas;

	// Checkbox
	@Wire
	private Checkbox checkEstructura1;
	@Wire
	private Checkbox checkEstructura2;
	@Wire
	private Checkbox checkEstructura3;
	@Wire
	private Checkbox checkEstructura4;
	@Wire
	private Checkbox checkEstructura5;

	// Radio
	@Wire
	private Radiogroup radiogroupServicioElectrico;
	@Wire
	private Radiogroup radiogroupGastosSeparados;

	// Spinner
	@Wire
	private Spinner SpinnerCuantosAmbientes;
	@Wire
	private Spinner SpinnerGruposGastos;

	@Override
	public void inicializar() throws IOException {
		// TODO Auto-generated method stub
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
				cerrarVentana(botoneraSocial, "Visita Social", tabs);
			}

			@Override
			public void limpiar() {
				// Combobox
				cmbTipoVivienda.setValue("");
				cmbTerrenoVivienda.setValue("");
				cmbCondicionVivienda.setValue("");
				cmbAbasteceAgua.setValue("");
				cmbFrecuenciaAgua.setValue("");
				cmbViviendaPosee.setValue("");
				cmbEliminaBasura.setValue("");
				cmbCombustibleCocina.setValue("");
				cmbCantidadPersonas.setValue("");
				// Spinner
				SpinnerCuantosAmbientes.setValue(0);
				SpinnerCuantosAmbientes
						.setPlaceholder("Seleccione una cantidad");
				SpinnerGruposGastos.setValue(0);
				SpinnerGruposGastos.setPlaceholder("Seleccione una cantidad");
				// Checkbox
				checkEstructura1.setChecked(false);
				checkEstructura2.setChecked(false);
				checkEstructura3.setChecked(false);
				checkEstructura4.setChecked(false);
				checkEstructura5.setChecked(false);

			}

			@Override
			public void guardar() {
				VisitaSocial visitaSocial = new VisitaSocial();
				// ComboBox
				String a = cmbTipoVivienda.getValue();
				visitaSocial.setA(a);
				String b = cmbTerrenoVivienda.getValue();
				visitaSocial.setB(b);
				String c = cmbCondicionVivienda.getValue();
				visitaSocial.setC(c);
				String e = cmbAbasteceAgua.getValue();
				visitaSocial.setC(e);
				String f = cmbFrecuenciaAgua.getValue();
				visitaSocial.setC(f);
				String g = cmbViviendaPosee.getValue();
				visitaSocial.setC(g);
				String i = cmbEliminaBasura.getValue();
				visitaSocial.setC(i);
				String aj = cmbCombustibleCocina.getValue();
				visitaSocial.setC(aj);
				String ab = cmbCantidadPersonas.getValue();
				visitaSocial.setC(ab);
				// Radio
				Radio h = radiogroupServicioElectrico.getSelectedItem();
				if (h.getId().equals("Si")) {
					visitaSocial.setH(true);
				} else {
					visitaSocial.setH(false);
				}
				Radio ac = radiogroupGastosSeparados.getSelectedItem();
				if (ac.getId().equals("rSi")) {
					visitaSocial.setAc(true);
				} else {
					visitaSocial.setAc(false);
				}
				// Spinner
				Integer aa = SpinnerCuantosAmbientes.getValue();
				visitaSocial.setAa(aa);
				Integer ad = SpinnerGruposGastos.getValue();
				visitaSocial.setAd(ad);

				String estructura = "";
				if (checkEstructura1.isChecked())
					estructura = estructura + "," + checkEstructura1.getLabel();
				if (checkEstructura2.isChecked())
					estructura = estructura + "," + checkEstructura2.getLabel();
				if (checkEstructura3.isChecked())
					estructura = estructura + "," + checkEstructura3.getLabel();
				if (checkEstructura4.isChecked())
					estructura = estructura + "," + checkEstructura4.getLabel();
				if (checkEstructura5.isChecked())
					estructura = estructura + "," + checkEstructura5.getLabel();

				visitaSocial.setD(estructura);
				servicioVisitaSocial.guardar(visitaSocial);
				msj.mensajeInformacion(Mensaje.guardado);
				limpiar();
			}

			@Override
			public void eliminar() {

			}
		};
		botonera.getChildren().get(1).setVisible(false);
		botoneraSocial.appendChild(botonera);

	}

	// Combobox
	@Listen("onChange = #cmbTipoVivienda")
	public void tipoDeVivienda() {
		String tipoVivienda = cmbTipoVivienda.getValue();
		showNotify("Has seleccionado " + tipoVivienda, cmbTipoVivienda);
	}

	@Listen("onChange = #cmbTerrenoVivienda")
	public void terrenoDeVivienda() {
		String terrenoVivienda = cmbTerrenoVivienda.getValue();
		showNotify("Has seleccionado " + terrenoVivienda, cmbTerrenoVivienda);
	}

	@Listen("onChange = #cmbCondicionVivienda")
	public void condicionDeVivienda() {
		String condicionVivienda = cmbCondicionVivienda.getValue();
		showNotify("Has seleccionado " + condicionVivienda,
				cmbCondicionVivienda);
	}

	@Listen("onChange = #cmbAbasteceAgua")
	public void abastecimientoAgua() {
		String abasteceAgua = cmbAbasteceAgua.getValue();
		showNotify("Has seleccionado " + abasteceAgua, cmbAbasteceAgua);
	}

	@Listen("onChange = #cmbFrecuenciaAgua")
	public void frecuenciaDeAgua() {
		String frecuenciaAgua = cmbFrecuenciaAgua.getValue();
		showNotify("Has seleccionado " + frecuenciaAgua, cmbFrecuenciaAgua);
	}

	@Listen("onChange = #cmbViviendaPosee")
	public void viviendaP() {
		String viviendaPosee = cmbViviendaPosee.getValue();
		showNotify("Has seleccionado " + viviendaPosee, cmbViviendaPosee);
	}

	@Listen("onChange = #cmbEliminaBasura")
	public void eliminarBasura() {
		String eliminarB = cmbEliminaBasura.getValue();
		showNotify("Has seleccionado " + eliminarB, cmbEliminaBasura);
	}

	@Listen("onChange = #cmbCombustibleCocina")
	public void combustibleCocina() {
		String combustibleC = cmbCombustibleCocina.getValue();
		showNotify("Has seleccionado " + combustibleC, cmbCombustibleCocina);
	}

	@Listen("onChange = #cmbCantidadPersonas")
	public void cantidadPerosonas() {
		String cantidadP = cmbCantidadPersonas.getValue();
		showNotify("Has seleccionado " + cantidadP, cmbCantidadPersonas);
	}

	// Checkbox
	@Listen("onCheck = #condicionesEstructurales > checkbox")
	public void seleccione(CheckEvent event) {
		String seleccionados = "";
		checkEstructura1.isChecked();
		checkEstructura2.isChecked();
		checkEstructura3.isChecked();
		checkEstructura4.isChecked();
		checkEstructura5.isChecked();

		showNotify("Has seleccionado " + seleccionados,
				checkEstructura1.getParent());
	}

	// Radio
	@Listen("onCheck = #radiogroupServicioElectrico")
	public void servicioElectrico() {
		Radio selectedItem = radiogroupServicioElectrico.getSelectedItem();
		showNotify("Has seleccionado " + selectedItem.getLabel(),
				radiogroupServicioElectrico.getParent());
	}

	@Listen("onCheck = #radiogroupGastosSeparados")
	public void gastosSeparados() {
		Radio selectedItem = radiogroupGastosSeparados.getSelectedItem();
		showNotify("Has seleccionado " + selectedItem.getLabel(),
				radiogroupGastosSeparados.getParent());
	}

	// Spinner
	@Listen("onChange = #SpinnerCuantosAmbientes")
	public void cantidadAmbientes() {
		Integer cuantosAmbientes = SpinnerCuantosAmbientes.getValue();

		showNotify("Ha Seleccionado: " + cuantosAmbientes,
				SpinnerCuantosAmbientes);
	}

	// Spinner
	@Listen("onChange = #SpinnerGruposGastos")
	public void gruposGastos() {
		Integer gruposGasto = SpinnerGruposGastos.getValue();

		showNotify("Ha Seleccionado: " + gruposGasto, SpinnerGruposGastos);
	}

	// Notificacion
	private void showNotify(String msg, Component ref) {
		Clients.showNotification(msg, "info", ref, "end_center", 2000);
	}
}
