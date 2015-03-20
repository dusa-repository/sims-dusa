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
	private Combobox cmb1;
	@Wire
	private Combobox cmb2;
	@Wire
	private Combobox cmb3;
	@Wire
	private Combobox cmb5;
	@Wire
	private Combobox cmb6;
	@Wire
	private Combobox cmb7;
	@Wire
	private Combobox cmb9;
	@Wire
	private Combobox cmb10;
	@Wire
	private Combobox cmb12;

	// Checkbox
	@Wire
	private Checkbox check4_1;
	@Wire
	private Checkbox check4_2;
	@Wire
	private Checkbox check4_3;
	@Wire
	private Checkbox check4_4;
	@Wire
	private Checkbox check4_5;

	// Radio
	@Wire
	private Radiogroup rdg8;
	@Wire
	private Radiogroup rdg13;

	// Spinner
	@Wire
	private Spinner spinner11;
	@Wire
	private Spinner spinner14;

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
				cmb1.setValue("");
				cmb2.setValue("");
				cmb3.setValue("");
				cmb5.setValue("");
				cmb6.setValue("");
				cmb7.setValue("");
				cmb9.setValue("");
				cmb10.setValue("");
				cmb12.setValue("");
				// Spinner
				spinner11.setValue(0);
				spinner11.setPlaceholder("Seleccione una cantidad");
				spinner14.setValue(0);
				spinner14.setPlaceholder("Seleccione una cantidad");
				// Checkbox
				check4_1.setChecked(false);
				check4_2.setChecked(false);
				check4_3.setChecked(false);
				check4_4.setChecked(false);
				check4_5.setChecked(false);

			}

			@Override
			public void guardar() {
				VisitaSocial visitaSocial = new VisitaSocial();
				// ComboBox
				String a = cmb1.getValue();
				visitaSocial.setA(a);
				String b = cmb2.getValue();
				visitaSocial.setB(b);
				String c = cmb3.getValue();
				visitaSocial.setC(c);
				String e = cmb5.getValue();
				visitaSocial.setE(e);
				String f = cmb6.getValue();
				visitaSocial.setF(f);
				String g = cmb7.getValue();
				visitaSocial.setG(g);
				String i = cmb9.getValue();
				visitaSocial.setI(i);
				String aj = cmb10.getValue();
				visitaSocial.setAj(aj);
				String ab = cmb12.getValue();
				visitaSocial.setAb(ab);
				// Radio
				Radio h = rdg8.getSelectedItem();
				if (h.getId().equals("rdo8_1")) {
					visitaSocial.setH(true);
				} else {
					visitaSocial.setH(false);
				}
				Radio ac = rdg13.getSelectedItem();
				if (ac.getId().equals("rdo13_1")) {
					visitaSocial.setAc(true);
				} else {
					visitaSocial.setAc(false);
				}
				// Spinner
				Integer aa = spinner11.getValue();
				visitaSocial.setAa(aa);
				Integer ad = spinner14.getValue();
				visitaSocial.setAd(ad);

				String estructura = "";
				if (check4_1.isChecked())
					estructura = estructura + "," + check4_1.getLabel();
				if (check4_2.isChecked())
					estructura = estructura + "," + check4_2.getLabel();
				if (check4_3.isChecked())
					estructura = estructura + "," + check4_3.getLabel();
				if (check4_4.isChecked())
					estructura = estructura + "," + check4_4.getLabel();
				if (check4_5.isChecked())
					estructura = estructura + "," + check4_5.getLabel();

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
		cmb1.getValue();
	}

	@Listen("onChange = #cmbTerrenoVivienda")
	public void terrenoDeVivienda() {
		cmb2.getValue();
	}


	@Listen("onChange = #cmbCondicionVivienda")
	public void condicionDeVivienda() {
		cmb3.getValue();
	}


	@Listen("onChange = #cmbAbasteceAgua")
	public void abastecimientoAgua() {
		cmb5.getValue();
	}


	@Listen("onChange = #cmbFrecuenciaAgua")
	public void frecuenciaDeAgua() {
		cmb6.getValue();
	}


	@Listen("onChange = #cmbViviendaPosee")
	public void viviendaP() {
		cmb7.getValue();
	}


	@Listen("onChange = #cmbEliminaBasura")
	public void eliminarBasura() {
		cmb9.getValue();
	}


	@Listen("onChange = #cmbCombustibleCocina")
	public void combustibleCocina() {
		cmb10.getValue();
	}


	@Listen("onChange = #cmbCantidadPersonas")
	public void cantidadPerosonas() {
		cmb12.getValue();
	}

	// Checkbox
	@Listen("onCheck = #condicionesEstructurales > checkbox")
	public void seleccione(CheckEvent event) {
		check4_1.isChecked();
		check4_2.isChecked();
		check4_3.isChecked();
		check4_4.isChecked();
		check4_5.isChecked();

	}

	// Radio
	@Listen("onCheck = #radiogroupServicioElectrico")
	public void servicioElectrico() {
		rdg8.getSelectedItem();
	}


	@Listen("onCheck = #radiogroupGastosSeparados")
	public void gastosSeparados() {
		rdg13.getSelectedItem();
	}

	// Spinner
	@Listen("onChange = #SpinnerCuantosAmbientes")
	public void cantidadAmbientes() {
		spinner11.getValue();
	}

	// Spinner
	@Listen("onChange = #SpinnerGruposGastos")
	public void gruposGastos() {
		spinner14.getValue();
	}
}
