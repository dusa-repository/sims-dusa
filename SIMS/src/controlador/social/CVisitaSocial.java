package controlador.social;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import modelo.maestros.Estado;
import modelo.maestros.EstadoCivil;
import modelo.maestros.Paciente;
import modelo.maestros.Pais;
import modelo.sha.Informe;
import modelo.sha.PlanAccion;
import modelo.social.ComposicionFamiliar;
import modelo.social.VisitaSocial;

import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.CheckEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublespinner;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;
//import componentes.Catalogo;
import componentes.Mensaje;

import controlador.maestros.CGenerico;

public class CVisitaSocial extends CGenerico {

	@Wire
	private Div botoneraSocial;
	@Wire
	private Div divCatalogoVisita;
	@Wire
	private Div divCatalogoPaciente;

	// Label
	@Wire
	private Label labelSuma;
	@Wire
	private Label lblCedula;
	@Wire
	private Label lblFicha;
	@Wire
	private Label lblNombres;
	@Wire
	private Label lblSexo;
	@Wire
	private Label lblFechaNacimiento;
	@Wire
	private Label lblEdad;
	@Wire
	private Label lblLugarNac;
	@Wire
	private Label lblFechaIngreso;
	@Wire
	private Label lblEstadoCivil;
	@Wire
	private Label lblGradoInst;
	@Wire
	private Label lblOcupacion;
	@Wire
	private Label lblProfesion;
	@Wire
	private Label lblCondicion;
	@Wire
	private Label lblJefe;
	@Wire
	private Label lblUrb;
	@Wire
	private Label lblCalle;
	@Wire
	private Label lblSector;
	@Wire
	private Label lblNro;
	@Wire
	private Label lblPuntoRef;
	@Wire
	private Label lblCiudad;
	@Wire
	private Label lblParroquia;
	@Wire
	private Label lblMunicipio;
	@Wire
	private Label lblTelefono1;
	@Wire
	private Label lblTelefono2;
	@Wire
	private Label lblTransporte;

	// Botones
	@Wire
	private Button btnBuscar1;

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
	@Wire
	private Combobox cmb17;
	@Wire
	private Combobox cmb21;
	@Wire
	private Combobox cmb22;

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
	@Wire
	private Checkbox check19_1;
	@Wire
	private Checkbox check19_2;
	@Wire
	private Checkbox check19_3;
	@Wire
	private Checkbox check19_4;
	@Wire
	private Checkbox check19_5;
	@Wire
	private Checkbox check19_6;
	@Wire
	private Checkbox check19_7;
	@Wire
	private Checkbox check19_8;
	@Wire
	private Checkbox check19_9;
	@Wire
	private Checkbox check19_10;
	@Wire
	private Checkbox check19_11;
	@Wire
	private Checkbox check19_12;
	@Wire
	private Checkbox check19_13;
	@Wire
	private Checkbox check19_14;
	@Wire
	private Checkbox check19_15;
	@Wire
	private Checkbox check19_16;
	@Wire
	private Checkbox check19_17;
	@Wire
	private Checkbox check19_18;
	@Wire
	private Checkbox check23_1;
	@Wire
	private Checkbox check23_2;
	@Wire
	private Checkbox check23_3;

	// Radio
	@Wire
	private Radiogroup rdg8;
	@Wire
	private Radio rdo8_1;
	@Wire
	private Radio rdo8_2;
	@Wire
	private Radio rdo13_1;
	@Wire
	private Radio rdo13_2;
	@Wire
	private Radiogroup rdg13;

	// Spinner
	@Wire
	private Spinner spinner11;
	@Wire
	private Spinner spinner14;
	@Wire
	private Spinner spinner16;
	@Wire
	private Spinner spinner18;
	@Wire
	private Spinner spinner24;
	@Wire
	private Spinner spinner25;

	// DoubleSpinner
	@Wire
	private Doublespinner doubleSpinner20;
	@Wire
	private Doublespinner doubleSpinner30_1;
	@Wire
	private Doublespinner doubleSpinner30_2;
	@Wire
	private Doublespinner doubleSpinner30_3;
	@Wire
	private Doublespinner doubleSpinner30_4;
	@Wire
	private Doublespinner doubleSpinner30_5;
	@Wire
	private Doublespinner doubleSpinner30_6;
	@Wire
	private Doublespinner doubleSpinner30_7;
	@Wire
	private Doublespinner doubleSpinner30_8;
	@Wire
	private Doublespinner doubleSpinner30_9;
	@Wire
	private Doublespinner doubleSpinner30_10;
	@Wire
	private Doublespinner doubleSpinner30_11;
	@Wire
	private Doublespinner doubleSpinner30_12;
	@Wire
	private Doublespinner doubleSpinner30_13;
	@Wire
	private Doublespinner doubleSpinner30_14;
	@Wire
	private Doublespinner doubleSpinner30_15;

	// textbox
	@Wire
	private Textbox textBox46_1;
	@Wire
	private Textbox textBox46_2;
	@Wire
	private Textbox textBox46_3;
	@Wire
	private Textbox textBox46_4;
	@Wire
	private Textbox textBox46_5;
	@Wire
	private Textbox textBox46_6;
	@Wire
	private Textbox textBox46_7;
	@Wire
	private Textbox textBox46_8;
	@Wire
	private Textbox textBox47_1;
	@Wire
	private Textbox textBox47_2;
	@Wire
	private Textbox textBox47_3;
	@Wire
	private Textbox textBox47_4;
	@Wire
	private Textbox textBox47_5;
	@Wire
	private Textbox textBox47_6;
	@Wire
	private Textbox textBox47_7;
	@Wire
	private Textbox textBox10;
	@Wire
	private Textbox textBox11;
	// datebox
	@Wire
	private Datebox dateBoxAplicacion;
	@Wire
	private Datebox dateBoxProcesamiento;
	@Wire
	private Datebox dateBoxInforme;
	//Campos de Alimentacion
	@Wire
	private Combobox cmbRes;
	@Wire
	private Textbox textBoxRes;
	@Wire
	private Checkbox checkRes_1;
	@Wire
	private Checkbox checkRes_2;
	@Wire
	private Checkbox checkRes_3;
	@Wire
	private Checkbox checkRes_4;
	@Wire
	private Checkbox checkRes_5;
	@Wire
	private Combobox cmbPollo;
	@Wire
	private Textbox textBoxPollo;
	@Wire
	private Checkbox checkPollo_1;
	@Wire
	private Checkbox checkPollo_2;
	@Wire
	private Checkbox checkPollo_3;
	@Wire
	private Checkbox checkPollo_4;
	@Wire
	private Checkbox checkPollo_5;
	@Wire
	private Combobox cmbCerdo;
	@Wire
	private Textbox textBoxCerdo;
	@Wire
	private Checkbox checkCerdo_1;
	@Wire
	private Checkbox checkCerdo_2;
	@Wire
	private Checkbox checkCerdo_3;
	@Wire
	private Checkbox checkCerdo_4;
	@Wire
	private Checkbox checkCerdo_5;
	@Wire
	private Combobox cmbPescado;
	@Wire
	private Textbox textBoxPescado;
	@Wire
	private Checkbox checkPescado_1;
	@Wire
	private Checkbox checkPescado_2;
	@Wire
	private Checkbox checkPescado_3;
	@Wire
	private Checkbox checkPescado_4;
	@Wire
	private Checkbox checkPescado_5;
	@Wire
	private Combobox cmbLeche;
	@Wire
	private Textbox textBoxLeche;
	@Wire
	private Checkbox checkLeche_1;
	@Wire
	private Checkbox checkLeche_2;
	@Wire
	private Checkbox checkLeche_3;
	@Wire
	private Checkbox checkLeche_4;
	@Wire
	private Checkbox checkLeche_5;
	@Wire
	private Combobox cmbHuevos;
	@Wire
	private Textbox textBoxHuevos;
	@Wire
	private Checkbox checkHuevos_1;
	@Wire
	private Checkbox checkHuevos_2;
	@Wire
	private Checkbox checkHuevos_3;
	@Wire
	private Checkbox checkHuevos_4;
	@Wire
	private Checkbox checkHuevos_5;
	@Wire
	private Combobox cmbCereales;
	@Wire
	private Textbox textBoxCereales;
	@Wire
	private Checkbox checkCereales_1;
	@Wire
	private Checkbox checkCereales_2;
	@Wire
	private Checkbox checkCereales_3;
	@Wire
	private Checkbox checkCereales_4;
	@Wire
	private Checkbox checkCereales_5;
	@Wire
	private Combobox cmbFrutas;
	@Wire
	private Textbox textBoxFrutas;
	@Wire
	private Checkbox checkFrutas_1;
	@Wire
	private Checkbox checkFrutas_2;
	@Wire
	private Checkbox checkFrutas_3;
	@Wire
	private Checkbox checkFrutas_4;
	@Wire
	private Checkbox checkFrutas_5;
	@Wire
	private Combobox cmbVerduras;
	@Wire
	private Textbox textBoxVerduras;
	@Wire
	private Checkbox checkVerduras_1;
	@Wire
	private Checkbox checkVerduras_2;
	@Wire
	private Checkbox checkVerduras_3;
	@Wire
	private Checkbox checkVerduras_4;
	@Wire
	private Checkbox checkVerduras_5;
	@Wire
	private Combobox cmbGranos;
	@Wire
	private Textbox textBoxGranos;
	@Wire
	private Checkbox checkGranos_1;
	@Wire
	private Checkbox checkGranos_2;
	@Wire
	private Checkbox checkGranos_3;
	@Wire
	private Checkbox checkGranos_4;
	@Wire
	private Checkbox checkGranos_5;
	//Composicion Familiar
	@Wire
	private Textbox txtNombre;
	@Wire
	private Listbox ltbComposicion;
	@Wire
	private Button btnAgregarItems;
	@Wire
	private Button btnEditar;
	@Wire
	private Button btnRemover;
	@Wire
	private Combobox cmb32;
	@Wire
	private Combobox cmb33;
	@Wire
	private Datebox dateBox35;
	@Wire
	private Label lbl35;
	@Wire
	private Combobox cmb36;
	@Wire
	private Checkbox check37_1;
	@Wire
	private Checkbox check37_2;
	@Wire
	private Checkbox check37_3;
	@Wire
	private Checkbox check37_4;
	@Wire
	private Checkbox check37_5;
	@Wire
	private Checkbox check37_6;
	@Wire
	private Checkbox check37_7;
	@Wire
	private Checkbox check37_8;
	@Wire
	private Checkbox check37_9;
	@Wire
	private Checkbox check37_10;
	@Wire
	private Combobox cmb38;
	@Wire
	private Textbox txt38;
	@Wire
	private Radiogroup rdg39;
	@Wire
	private Radio rdo39_1;
	@Wire
	private Radio rdo39_2;
	@Wire
	private Textbox txt40;
	@Wire
	private Textbox txt41;
	@Wire
	private Textbox txt42;
	@Wire
	private Textbox txt43;
	@Wire
	private Image imagen1;
	@Wire
	private Fileupload fudImagen1;
	@Wire
	private Media media1;
	
	private List<ComposicionFamiliar> listaComposicion = new ArrayList<ComposicionFamiliar>();

	Catalogo<Paciente> catalogoPaciente;
	Catalogo<VisitaSocial> catalogoVisita;

	// Variables
	private String nombre;
	Long idVisita = (long) 0;
	private String cedula = "";

	@Override
	public void inicializar() throws IOException {
		// TODO Auto-generated method stub
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

		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(botoneraSocial, nombre, tabs);
			}

			@Override
			public void limpiar() {

				idVisita = (long) 0;
				cedula = "";
				
				//Limpiar campos de paciente
				lblCedula.setValue("");
				
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
				cmb17.setValue("");
				cmb21.setValue("");
				cmb22.setValue("");
				// Spinner
				spinner11.setValue(0);
				spinner14.setValue(0);
				spinner16.setValue(0);
				spinner18.setValue(0);
				spinner24.setValue(0);
				spinner25.setValue(0);
				// DoubleSpinner
				doubleSpinner20.setValue(0.0);
				doubleSpinner30_1.setValue(0.0);
				doubleSpinner30_2.setValue(0.0);
				doubleSpinner30_3.setValue(0.0);
				doubleSpinner30_4.setValue(0.0);
				doubleSpinner30_5.setValue(0.0);
				doubleSpinner30_6.setValue(0.0);
				doubleSpinner30_7.setValue(0.0);
				doubleSpinner30_8.setValue(0.0);
				doubleSpinner30_9.setValue(0.0);
				doubleSpinner30_10.setValue(0.0);
				doubleSpinner30_11.setValue(0.0);
				doubleSpinner30_12.setValue(0.0);
				doubleSpinner30_13.setValue(0.0);
				doubleSpinner30_14.setValue(0.0);
				doubleSpinner30_15.setValue(0.0);
				// Checkbox
				check4_1.setChecked(false);
				check4_2.setChecked(false);
				check4_3.setChecked(false);
				check4_4.setChecked(false);
				check4_5.setChecked(false);
				check19_1.setChecked(false);
				check19_2.setChecked(false);
				check19_3.setChecked(false);
				check19_4.setChecked(false);
				check19_5.setChecked(false);
				check19_6.setChecked(false);
				check19_7.setChecked(false);
				check19_8.setChecked(false);
				check19_9.setChecked(false);
				check19_10.setChecked(false);
				check19_11.setChecked(false);
				check19_12.setChecked(false);
				check19_13.setChecked(false);
				check19_14.setChecked(false);
				check19_15.setChecked(false);
				check19_16.setChecked(false);
				check19_17.setChecked(false);
				check19_18.setChecked(false);
				check23_1.setChecked(false);
				check23_2.setChecked(false);
				check23_3.setChecked(false);
				// textBox
				textBox46_1.setValue("");
				textBox46_2.setValue("");
				textBox46_3.setValue("");
				textBox46_4.setValue("");
				textBox46_5.setValue("");
				textBox46_6.setValue("");
				textBox46_7.setValue("");
				textBox46_8.setValue("");
				textBox47_1.setValue("");
				textBox47_2.setValue("");
				textBox47_3.setValue("");
				textBox47_4.setValue("");
				textBox47_5.setValue("");
				textBox47_6.setValue("");
				textBox47_7.setValue("");
				textBox10.setValue("");
				textBox11.setValue("");
				// datebox
				dateBoxAplicacion.setValue(fecha);
				dateBoxProcesamiento.setValue(fecha);
				dateBoxInforme.setValue(fecha);
				// Label
				labelSuma.setValue("");
				lblCedula.setValue("");
				lblFicha.setValue("");
				lblNombres.setValue("");
				lblSexo.setValue("");
				lblFechaNacimiento.setValue("");
				lblEdad.setValue("");
				lblLugarNac.setValue("");
				lblFechaIngreso.setValue("");
				lblEstadoCivil.setValue("");
				lblGradoInst.setValue("");
				lblOcupacion.setValue("");
				lblProfesion.setValue("");
				lblCondicion.setValue("");
				lblJefe.setValue("");
				lblUrb.setValue("");
				lblCalle.setValue("");
				lblSector.setValue("");
				lblNro.setValue("");
				lblPuntoRef.setValue("");
				lblCiudad.setValue("");
				lblParroquia.setValue("");
				lblMunicipio.setValue("");
				lblTelefono1.setValue("");
				lblTelefono2.setValue("");
				lblTransporte.setValue("");
				//Radio
				rdo13_1.setChecked(false);
				rdo13_2.setChecked(false);
				rdo8_1.setChecked(false);
				rdo8_2.setChecked(false);
				//Campos de Alimentacion
				cmbRes.setValue("");
				textBoxRes.setValue("");
				checkRes_1.setChecked(false);
				checkRes_2.setChecked(false);
				checkRes_3.setChecked(false);
				checkRes_4.setChecked(false);
				checkRes_5.setChecked(false);
				cmbPollo.setValue("");
				textBoxPollo.setValue("");
				checkPollo_1.setChecked(false);
				checkPollo_2.setChecked(false);
				checkPollo_3.setChecked(false);
				checkPollo_4.setChecked(false);
				checkPollo_5.setChecked(false);
				cmbCerdo.setValue("");
				textBoxCerdo.setValue("");
				checkCerdo_1.setChecked(false);
				checkCerdo_2.setChecked(false);
				checkCerdo_3.setChecked(false);
				checkCerdo_4.setChecked(false);
				checkCerdo_5.setChecked(false);
				cmbPescado.setValue("");
				textBoxPescado.setValue("");
				checkPescado_1.setChecked(false);
				checkPescado_2.setChecked(false);
				checkPescado_3.setChecked(false);
				checkPescado_4.setChecked(false);
				checkPescado_5.setChecked(false);
				cmbLeche.setValue("");
				textBoxLeche.setValue("");
				checkLeche_1.setChecked(false);
				checkLeche_2.setChecked(false);
				checkLeche_3.setChecked(false);
				checkLeche_4.setChecked(false);
				checkLeche_5.setChecked(false);
				cmbHuevos.setValue("");
				textBoxHuevos.setValue("");
				checkHuevos_1.setChecked(false);
				checkHuevos_2.setChecked(false);
				checkHuevos_3.setChecked(false);
				checkHuevos_4.setChecked(false);
				checkHuevos_5.setChecked(false);
				cmbCereales.setValue("");
				textBoxCereales.setValue("");
				checkCereales_1.setChecked(false);
				checkCereales_2.setChecked(false);
				checkCereales_3.setChecked(false);
				checkCereales_4.setChecked(false);
				checkCereales_5.setChecked(false);
				cmbFrutas.setValue("");
				textBoxFrutas.setValue("");
				checkFrutas_1.setChecked(false);
				checkFrutas_2.setChecked(false);
				checkFrutas_3.setChecked(false);
				checkFrutas_4.setChecked(false);
				checkFrutas_5.setChecked(false);
				cmbVerduras.setValue("");
				textBoxVerduras.setValue("");
				checkVerduras_1.setChecked(false);
				checkVerduras_2.setChecked(false);
				checkVerduras_3.setChecked(false);
				checkVerduras_4.setChecked(false);
				checkVerduras_5.setChecked(false);
				cmbGranos.setValue("");
				textBoxGranos.setValue("");
				checkGranos_1.setChecked(false);
				checkGranos_2.setChecked(false);
				checkGranos_3.setChecked(false);
				checkGranos_4.setChecked(false);
				checkGranos_5.setChecked(false);
				ltbComposicion.getItems().clear();
				org.zkoss.image.Image imagenUsuario1 = null;
				imagen1.setContent(imagenUsuario1);

			}

			@Override
			public void guardar() {

				if (validar()) {
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
					String ag = cmb17.getValue();
					visitaSocial.setAg(ag);
					String ba = cmb21.getValue();
					visitaSocial.setBa(ba);
					String bb = cmb22.getValue();
					visitaSocial.setBb(bb);

					// Radio
					if (rdg8.getSelectedItem() != null) {
						Radio h = rdg8.getSelectedItem();
						if (h.getId().equals("rdo8_1")) {
							visitaSocial.setH(true);
						} else {
							visitaSocial.setH(false);
						}
					}

					if (rdg13.getSelectedItem() != null) {
						Radio ac = rdg13.getSelectedItem();
						if (ac.getId().equals("rdo13_1")) {
							visitaSocial.setAc(true);
						} else {
							visitaSocial.setAc(false);
						}
					}

					// Spinner
					if (spinner11.getValue() != null) {
						Integer aa = spinner11.getValue();
						visitaSocial.setAa(aa);
					}
					if (spinner14.getValue() != null) {
						Integer ad = spinner14.getValue();
						visitaSocial.setAd(ad);
					}
					if (spinner16.getValue() != null) {
						Integer af = spinner16.getValue();
						visitaSocial.setAf(af);
					}
					if (spinner18.getValue() != null) {
						Integer ah = spinner18.getValue();
						visitaSocial.setAh(ah);
					}
					if (spinner24.getValue() != null) {
						Integer bd = spinner24.getValue();
						visitaSocial.setBd(bd);
					}
					if (spinner25.getValue() != null) {
						Integer be = spinner25.getValue();
						visitaSocial.setBe(be);
					}
					// DoubleSpinner
					if (doubleSpinner20.getValue() != null) {
						Double bj = doubleSpinner20.getValue();
						visitaSocial.setBj(bj);
					}
					if (doubleSpinner30_1.getValue() != null) {
						Double cjAlimentacion = doubleSpinner30_1.getValue();
						visitaSocial.setCjAlimentacion(cjAlimentacion);
					}
					if (doubleSpinner30_2.getValue() != null) {
						Double cjGasolina = doubleSpinner30_2.getValue();
						visitaSocial.setCjGasolina(cjGasolina);
					}
					if (doubleSpinner30_3.getValue() != null) {
						Double cjAlquiler = doubleSpinner30_3.getValue();
						visitaSocial.setCjAlquiler(cjAlquiler);
					}
					if (doubleSpinner30_4.getValue() != null) {
						Double cjAgua = doubleSpinner30_4.getValue();
						visitaSocial.setCjAgua(cjAgua);
					}
					if (doubleSpinner30_5.getValue() != null) {
						Double cjElectricidad = doubleSpinner30_5.getValue();
						visitaSocial.setCjElectricidad(cjElectricidad);
					}
					if (doubleSpinner30_6.getValue() != null) {
						Double cjResidencia = doubleSpinner30_6.getValue();
						visitaSocial.setCjResidencia(cjResidencia);
					}
					if (doubleSpinner30_7.getValue() != null) {
						Double cjCelular = doubleSpinner30_7.getValue();
						visitaSocial.setCjCelular(cjCelular);
					}
					if (doubleSpinner30_8.getValue() != null) {
						Double cjTransporte = doubleSpinner30_8.getValue();
						visitaSocial.setCjTransporte(cjTransporte);
					}
					if (doubleSpinner30_9.getValue() != null) {
						Double cjEducacion = doubleSpinner30_9.getValue();
						visitaSocial.setCjEducacion(cjEducacion);
					}
					if (doubleSpinner30_10.getValue() != null) {
						Double cjMedico = doubleSpinner30_10.getValue();
						visitaSocial.setCjMedico(cjMedico);
					}
					if (doubleSpinner30_11.getValue() != null) {
						Double cjRecreacion = doubleSpinner30_11.getValue();
						visitaSocial.setCjRecreacion(cjRecreacion);
					}
					if (doubleSpinner30_12.getValue() != null) {
						Double cjCredito = doubleSpinner30_12.getValue();
						visitaSocial.setCjCredito(cjCredito);
					}
					if (doubleSpinner30_13.getValue() != null) {
						Double cjRopa = doubleSpinner30_13.getValue();
						visitaSocial.setCjRopa(cjRopa);
					}
					if (doubleSpinner30_14.getValue() != null) {
						Double cjFondo = doubleSpinner30_14.getValue();
						visitaSocial.setCjFondo(cjFondo);
					}
					if (doubleSpinner30_15.getValue() != null) {
						Double cjHabitacional = doubleSpinner30_15.getValue();
						visitaSocial.setCjHabitacional(cjHabitacional);
					}

					// Checkbox
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

					String hogarDispone = "";
					if (check19_1.isChecked())
						hogarDispone = hogarDispone + ","
								+ check19_1.getLabel();
					if (check19_2.isChecked())
						hogarDispone = hogarDispone + ","
								+ check19_2.getLabel();
					if (check19_3.isChecked())
						hogarDispone = hogarDispone + ","
								+ check19_3.getLabel();
					if (check19_4.isChecked())
						hogarDispone = hogarDispone + ","
								+ check19_4.getLabel();
					if (check19_5.isChecked())
						hogarDispone = hogarDispone + ","
								+ check19_5.getLabel();
					if (check19_6.isChecked())
						hogarDispone = hogarDispone + ","
								+ check19_6.getLabel();
					if (check19_7.isChecked())
						hogarDispone = hogarDispone + ","
								+ check19_7.getLabel();
					if (check19_8.isChecked())
						hogarDispone = hogarDispone + ","
								+ check19_8.getLabel();
					if (check19_9.isChecked())
						hogarDispone = hogarDispone + ","
								+ check19_9.getLabel();
					if (check19_10.isChecked())
						hogarDispone = hogarDispone + ","
								+ check19_10.getLabel();
					if (check19_11.isChecked())
						hogarDispone = hogarDispone + ","
								+ check19_11.getLabel();
					if (check19_12.isChecked())
						hogarDispone = hogarDispone + ","
								+ check19_12.getLabel();
					if (check19_13.isChecked())
						hogarDispone = hogarDispone + ","
								+ check19_13.getLabel();
					if (check19_14.isChecked())
						hogarDispone = hogarDispone + ","
								+ check19_14.getLabel();
					if (check19_15.isChecked())
						hogarDispone = hogarDispone + ","
								+ check19_15.getLabel();
					if (check19_16.isChecked())
						hogarDispone = hogarDispone + ","
								+ check19_16.getLabel();
					if (check19_17.isChecked())
						hogarDispone = hogarDispone + ","
								+ check19_17.getLabel();
					if (check19_18.isChecked())
						hogarDispone = hogarDispone + ","
								+ check19_18.getLabel();
					visitaSocial.setAi(hogarDispone);

					String dondeAcude = "";
					if (check23_1.isChecked())
						dondeAcude = dondeAcude + "," + check23_1.getLabel();
					if (check23_2.isChecked())
						dondeAcude = dondeAcude + "," + check23_2.getLabel();
					if (check23_3.isChecked())
						dondeAcude = dondeAcude + "," + check23_3.getLabel();
					visitaSocial.setBc(dondeAcude);

					// textbox
					String dfa = textBox46_1.getValue();
					visitaSocial.setDfa(dfa);
					String dfb = textBox46_2.getValue();
					visitaSocial.setDfb(dfb);
					String dfc = textBox46_3.getValue();
					visitaSocial.setDfc(dfc);
					String dfd = textBox46_4.getValue();
					visitaSocial.setDfd(dfd);
					String dfe = textBox46_5.getValue();
					visitaSocial.setDfe(dfe);
					String dff = textBox46_6.getValue();
					visitaSocial.setDff(dff);
					String dfg = textBox46_7.getValue();
					visitaSocial.setDfg(dfg);
					String dfh = textBox46_8.getValue();
					visitaSocial.setDfh(dfh);
					String dga = textBox47_1.getValue();
					visitaSocial.setDga(dga);
					String dgb = textBox47_2.getValue();
					visitaSocial.setDgb(dgb);
					String dgc = textBox47_3.getValue();
					visitaSocial.setDgc(dgc);
					String dgd = textBox47_4.getValue();
					visitaSocial.setDgd(dgd);
					String dge = textBox47_5.getValue();
					visitaSocial.setDge(dge);
					String dgf = textBox47_6.getValue();
					visitaSocial.setDgf(dgf);
					String dgg = textBox47_7.getValue();
					visitaSocial.setDgg(dgg);
					String diagnosticoSocial = textBox10.getValue();
					visitaSocial.setDiagnosticoSocial(diagnosticoSocial);
					String observacion = textBox11.getValue();
					visitaSocial.setObservacion(observacion);

					// datebox
					if(dateBoxAplicacion.getValue()!=null){
					Date fechaAplicacion = dateBoxAplicacion.getValue();
					Timestamp fechaA = new Timestamp(fechaAplicacion.getTime());
					visitaSocial.setFechaAplicacion(fechaA);
					}
					if(dateBoxProcesamiento.getValue()!=null){
					Date fechaProcesamiento = dateBoxProcesamiento.getValue();
					Timestamp fechaP = new Timestamp(
							fechaProcesamiento.getTime());
					visitaSocial.setFechaProcesamiento(fechaP);
					}
					if(dateBoxInforme.getValue()!=null){
					Date fechaInforme = dateBoxInforme.getValue();
					Timestamp fechaI = new Timestamp(fechaInforme.getTime());
					visitaSocial.setFechaInforme(fechaI);
					}
					//Campos de Alimentacion
					String resFrecuencia = cmbRes.getValue();
					visitaSocial.setResFrecuencia(resFrecuencia);
					String resDonde = textBoxRes.getValue();
					visitaSocial.setResDonde(resDonde);
					String resComo="";
					if (checkRes_1.isChecked())
						resComo = resComo + "," + checkRes_1.getLabel();
					if (checkRes_2.isChecked())
						resComo = resComo + "," + checkRes_2.getLabel();
					if (checkRes_3.isChecked())
						resComo = resComo + "," + checkRes_3.getLabel();
					if (checkRes_4.isChecked())
						resComo = resComo + "," + checkRes_4.getLabel();
					if (checkRes_5.isChecked())
						resComo = resComo + "," + checkRes_5.getLabel();
					visitaSocial.setResComo(resComo);
					String polloFrecuencia = cmbPollo.getValue();
					visitaSocial.setPolloFrecuencia(polloFrecuencia);
					String polloDonde = textBoxPollo.getValue();
					visitaSocial.setPolloDonde(polloDonde);
					String polloComo="";
					if (checkPollo_1.isChecked())
						polloComo = polloComo + "," + checkPollo_1.getLabel();
					if (checkPollo_2.isChecked())
						polloComo = polloComo + "," + checkPollo_2.getLabel();
					if (checkPollo_3.isChecked())
						polloComo = polloComo + "," + checkPollo_3.getLabel();
					if (checkPollo_4.isChecked())
						polloComo = polloComo + "," + checkPollo_4.getLabel();
					if (checkPollo_5.isChecked())
						polloComo = polloComo + "," + checkPollo_5.getLabel();
					visitaSocial.setPolloComo(polloComo);
					String cerdoFrecuencia = cmbCerdo.getValue();
					visitaSocial.setCerdoFrecuencia(cerdoFrecuencia);
					String cerdoDonde = textBoxCerdo.getValue();
					visitaSocial.setCerdoDonde(cerdoDonde);
					String cerdoComo="";
					if (checkCerdo_1.isChecked())
						cerdoComo = cerdoComo + "," + checkCerdo_1.getLabel();
					if (checkCerdo_2.isChecked())
						cerdoComo = cerdoComo + "," + checkCerdo_2.getLabel();
					if (checkCerdo_3.isChecked())
						cerdoComo = cerdoComo + "," + checkCerdo_3.getLabel();
					if (checkCerdo_4.isChecked())
						cerdoComo = cerdoComo + "," + checkCerdo_4.getLabel();
					if (checkCerdo_5.isChecked())
						cerdoComo = cerdoComo + "," + checkCerdo_5.getLabel();
					visitaSocial.setCerdoComo(cerdoComo);
					String pescadoFrecuencia = cmbPescado.getValue();
					visitaSocial.setPescadoFrecuencia(pescadoFrecuencia);
					String pescadoDonde = textBoxPescado.getValue();
					visitaSocial.setPescadoDonde(pescadoDonde);
					String pescadoComo="";
					if (checkPescado_1.isChecked())
						pescadoComo = pescadoComo + "," + checkPescado_1.getLabel();
					if (checkPescado_2.isChecked())
						pescadoComo = pescadoComo + "," + checkPescado_2.getLabel();
					if (checkPescado_3.isChecked())
						pescadoComo = pescadoComo + "," + checkPescado_3.getLabel();
					if (checkPescado_4.isChecked())
						pescadoComo = pescadoComo + "," + checkPescado_4.getLabel();
					if (checkPescado_5.isChecked())
						pescadoComo = pescadoComo + "," + checkPescado_5.getLabel();
					visitaSocial.setPescadoComo(pescadoComo);
					String lecheFrecuencia = cmbLeche.getValue();
					visitaSocial.setLecheFrecuencia(lecheFrecuencia);
					String lecheDonde = textBoxLeche.getValue();
					visitaSocial.setLecheDonde(lecheDonde);
					String lecheComo="";
					if (checkLeche_1.isChecked())
						lecheComo = lecheComo + "," + checkLeche_1.getLabel();
					if (checkLeche_2.isChecked())
						lecheComo = lecheComo + "," + checkLeche_2.getLabel();
					if (checkLeche_3.isChecked())
						lecheComo = lecheComo + "," + checkLeche_3.getLabel();
					if (checkLeche_4.isChecked())
						lecheComo = lecheComo + "," + checkLeche_4.getLabel();
					if (checkLeche_5.isChecked())
						lecheComo = lecheComo + "," + checkLeche_5.getLabel();
					visitaSocial.setLecheComo(lecheComo);
					String huevosFrecuencia = cmbHuevos.getValue();
					visitaSocial.setHuevosFrecuencia(huevosFrecuencia);
					String huevosDonde = textBoxHuevos.getValue();
					visitaSocial.setHuevosDonde(huevosDonde);
					String huevosComo="";
					if (checkHuevos_1.isChecked())
						huevosComo = huevosComo + "," + checkHuevos_1.getLabel();
					if (checkHuevos_2.isChecked())
						huevosComo = huevosComo + "," + checkHuevos_2.getLabel();
					if (checkHuevos_3.isChecked())
						huevosComo = huevosComo + "," + checkHuevos_3.getLabel();
					if (checkHuevos_4.isChecked())
						huevosComo = huevosComo + "," + checkHuevos_4.getLabel();
					if (checkHuevos_5.isChecked())
						huevosComo = huevosComo + "," + checkHuevos_5.getLabel();
					visitaSocial.setHuevosComo(huevosComo);
					String cerealesFrecuencia = cmbCereales.getValue();
					visitaSocial.setCerealesFrecuencia(cerealesFrecuencia);
					String cerealesDonde = textBoxCereales.getValue();
					visitaSocial.setCerealesDonde(cerealesDonde);
					String cerealesComo="";
					if (checkCereales_1.isChecked())
						cerealesComo = cerealesComo + "," + checkCereales_1.getLabel();
					if (checkCereales_2.isChecked())
						cerealesComo = cerealesComo + "," + checkCereales_2.getLabel();
					if (checkCereales_3.isChecked())
						cerealesComo = cerealesComo + "," + checkCereales_3.getLabel();
					if (checkCereales_4.isChecked())
						cerealesComo = cerealesComo + "," + checkCereales_4.getLabel();
					if (checkCereales_5.isChecked())
						cerealesComo = cerealesComo + "," + checkCereales_5.getLabel();
					visitaSocial.setCerealesComo(cerealesComo);
					String frutasFrecuencia = cmbFrutas.getValue();
					visitaSocial.setFrutasFrecuencia(frutasFrecuencia);
					String frutasDonde = textBoxFrutas.getValue();
					visitaSocial.setFrutasDonde(frutasDonde);
					String frutasComo="";
					if (checkFrutas_1.isChecked())
						frutasComo = frutasComo + "," + checkFrutas_1.getLabel();
					if (checkFrutas_2.isChecked())
						frutasComo = frutasComo + "," + checkFrutas_2.getLabel();
					if (checkFrutas_3.isChecked())
						frutasComo = frutasComo + "," + checkFrutas_3.getLabel();
					if (checkFrutas_4.isChecked())
						frutasComo = frutasComo + "," + checkFrutas_4.getLabel();
					if (checkFrutas_5.isChecked())
						frutasComo = frutasComo + "," + checkFrutas_5.getLabel();
					visitaSocial.setFrutasComo(frutasComo);
					String verdurasFrecuencia = cmbVerduras.getValue();
					visitaSocial.setVerdurasFrecuencia(verdurasFrecuencia);
					String verdurasDonde = textBoxVerduras.getValue();
					visitaSocial.setVerdurasComo(verdurasDonde);
					String verdurasComo="";
					if (checkVerduras_1.isChecked())
						verdurasComo = verdurasComo + "," + checkVerduras_1.getLabel();
					if (checkVerduras_2.isChecked())
						verdurasComo = verdurasComo + "," + checkVerduras_2.getLabel();
					if (checkVerduras_3.isChecked())
						verdurasComo = verdurasComo + "," + checkVerduras_3.getLabel();
					if (checkVerduras_4.isChecked())
						verdurasComo = verdurasComo + "," + checkVerduras_4.getLabel();
					if (checkVerduras_5.isChecked())
						verdurasComo = verdurasComo + "," + checkVerduras_5.getLabel();
					visitaSocial.setVerdurasComo(verdurasComo);
					String granosFrecuencia = cmbGranos.getValue();
					visitaSocial.setGranosFrecuencia(granosFrecuencia);
					String granosDonde = textBoxGranos.getValue();
					visitaSocial.setGranosDonde(granosDonde);
					String granosComo="";
					if (checkGranos_1.isChecked())
						granosComo = granosComo + "," + checkGranos_1.getLabel();
					if (checkGranos_2.isChecked())
						granosComo = granosComo + "," + checkGranos_2.getLabel();
					if (checkGranos_3.isChecked())
						granosComo = granosComo + "," + checkGranos_3.getLabel();
					if (checkGranos_4.isChecked())
						granosComo = granosComo + "," + checkGranos_4.getLabel();
					if (checkGranos_5.isChecked())
						granosComo = granosComo + "," + checkGranos_5.getLabel();
					visitaSocial.setGranosComo(granosComo);
					
					byte[] imagenUsuario1 = null;
					if (media1 instanceof org.zkoss.image.Image
							&& imagen1.getContent() != null) {
						imagenUsuario1 = imagen1.getContent().getByteData();
					}
					visitaSocial.setImagen(imagenUsuario1);

					visitaSocial.setIdVisita(idVisita);

					// Para guardar al paciente es la misma idea, busco el q
					// seleccione del catalogo y se lo asigno a la visita
					Paciente paciente = servicioPaciente
							.buscarPorCedula(cedula);
					if (paciente != null)
						visitaSocial.setPaciente(paciente);
					// Guardar
					servicioVisitaSocial.guardar(visitaSocial);
					if (ltbComposicion.getItemCount() != 0) {
						visitaSocial = new VisitaSocial();
						if (idVisita != 0)
							visitaSocial = servicioVisitaSocial.buscar(idVisita);
						else
							visitaSocial = servicioVisitaSocial.buscarUltimo();
						guardarFamiliar(visitaSocial);
					}
					
					msj.mensajeInformacion(Mensaje.guardado);
					limpiar();
				}
			}

			@Override
			public void eliminar() {

			}
		};
		botonera.getChildren().get(1).setVisible(false);
		botoneraSocial.appendChild(botonera);

	}

	private boolean validar() {
		if (cedula.equals("")) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	public void llenarCamposVisita(VisitaSocial visitaSocial) {
		
		idVisita = visitaSocial.getIdVisita();

		//ComboBox
		cmb1.setValue(visitaSocial.getA());
		cmb2.setValue(visitaSocial.getB());
		cmb3.setValue(visitaSocial.getC());
		cmb5.setValue(visitaSocial.getE());
		cmb6.setValue(visitaSocial.getF());
		cmb7.setValue(visitaSocial.getG());
		cmb9.setValue(visitaSocial.getI());
		cmb10.setValue(visitaSocial.getAj());
		cmb12.setValue(visitaSocial.getAb());
		cmb17.setValue(visitaSocial.getAg());
		cmb21.setValue(visitaSocial.getBa());
		cmb22.setValue(visitaSocial.getBb());
		//Radio
		if (visitaSocial.getH() != null)
			if (visitaSocial.getH())
				rdo8_1.setChecked(true);
			else
				rdo8_2.setChecked(true);
		
		if (visitaSocial.getAc() != null)
			if (visitaSocial.getAc())
				rdo13_1.setChecked(true);
			else
				rdo13_2.setChecked(true);
		
		//CheckBox
		String valores[] = visitaSocial.getD().split(",");
		if (valores.length != 0) {
			int j = 0;
			while (j < valores.length) {
				if (valores[j].equals(check4_1.getLabel()))
					check4_1.setChecked(true);
				if (valores[j].equals(check4_2.getLabel()))
					check4_2.setChecked(true);
				if (valores[j].equals(check4_3.getLabel()))
					check4_3.setChecked(true);
				if (valores[j].equals(check4_4.getLabel()))
					check4_4.setChecked(true);
				if (valores[j].equals(check4_5.getLabel()))
					check4_5.setChecked(true);
				j++;
			}
		}
		String valores2[] = visitaSocial.getAi().split(",");
		if (valores2.length != 0) {
			int i = 0;
			while (i < valores2.length) {
				if (valores2[i].equals(check19_1.getLabel()))
					check19_1.setChecked(true);
				if (valores2[i].equals(check19_2.getLabel()))
					check19_2.setChecked(true);
				if (valores2[i].equals(check19_3.getLabel()))
					check19_3.setChecked(true);
				if (valores2[i].equals(check19_4.getLabel()))
					check19_4.setChecked(true);
				if (valores2[i].equals(check19_5.getLabel()))
					check19_5.setChecked(true);
				if (valores2[i].equals(check19_6.getLabel()))
					check19_6.setChecked(true);
				if (valores2[i].equals(check19_7.getLabel()))
					check19_7.setChecked(true);
				if (valores2[i].equals(check19_8.getLabel()))
					check19_8.setChecked(true);
				if (valores2[i].equals(check19_9.getLabel()))
					check19_9.setChecked(true);
				if (valores2[i].equals(check19_10.getLabel()))
					check19_10.setChecked(true);
				if (valores2[i].equals(check19_11.getLabel()))
					check19_11.setChecked(true);
				if (valores2[i].equals(check19_12.getLabel()))
					check19_12.setChecked(true);
				if (valores2[i].equals(check19_13.getLabel()))
					check19_13.setChecked(true);
				if (valores2[i].equals(check19_14.getLabel()))
					check19_14.setChecked(true);
				if (valores2[i].equals(check19_15.getLabel()))
					check19_15.setChecked(true);
				if (valores2[i].equals(check19_16.getLabel()))
					check19_16.setChecked(true);
				if (valores2[i].equals(check19_17.getLabel()))
					check19_17.setChecked(true);
				if (valores2[i].equals(check19_18.getLabel()))
					check19_18.setChecked(true);
				i++;
			}
		}
		String valores3[] = visitaSocial.getBc().split(",");
		if (valores3.length != 0) {
			int a = 0;
			while (a < valores3.length) {
				if (valores3[a].equals(check23_1.getLabel()))
					check23_1.setChecked(true);
				if (valores3[a].equals(check23_2.getLabel()))
					check23_2.setChecked(true);
				if (valores3[a].equals(check23_3.getLabel()))
					check23_3.setChecked(true);
				a++;
			}
		}
		//Spinner
		if(visitaSocial.getAa()!=null){
			spinner11.setValue(visitaSocial.getAa());
		}
		if(visitaSocial.getAd()!=null){
			spinner14.setValue(visitaSocial.getAd());
		}
		if(visitaSocial.getAf()!=null){
			spinner16.setValue(visitaSocial.getAf());
		}
		if(visitaSocial.getAh()!=null){
			spinner18.setValue(visitaSocial.getAh());
		}
		if(visitaSocial.getBd()!=null){
			spinner24.setValue(visitaSocial.getBd());
		}
		if(visitaSocial.getBe()!=null){
			spinner25.setValue(visitaSocial.getBe());
		}
		// DoubleSpinner
		if (visitaSocial.getBj()!= null) {
			doubleSpinner20.setValue(visitaSocial.getBj());
		}
		if (visitaSocial.getCjAlimentacion()!= null) {
			doubleSpinner30_1.setValue(visitaSocial.getCjAlimentacion());
		}
		if (visitaSocial.getCjGasolina()!= null) {
			doubleSpinner30_2.setValue(visitaSocial.getCjGasolina());
		}
		if (visitaSocial.getCjAlquiler()!= null) {
			doubleSpinner30_3.setValue(visitaSocial.getCjAlquiler());
		}
		if (visitaSocial.getCjAgua()!= null) {
			doubleSpinner30_4.setValue(visitaSocial.getCjAgua());
		}
		if (visitaSocial.getCjElectricidad()!= null) {
			doubleSpinner30_5.setValue(visitaSocial.getCjElectricidad());
		}
		if (visitaSocial.getCjResidencia()!= null) {
			doubleSpinner30_6.setValue(visitaSocial.getCjResidencia());
		}
		if (visitaSocial.getCjCelular()!= null) {
			doubleSpinner30_7.setValue(visitaSocial.getCjCelular());
		}
		if (visitaSocial.getCjTransporte()!= null) {
			doubleSpinner30_8.setValue(visitaSocial.getCjTransporte());
		}
		if (visitaSocial.getCjEducacion()!= null) {
			doubleSpinner30_9.setValue(visitaSocial.getCjEducacion());
		}
		if (visitaSocial.getCjMedico()!= null) {
			doubleSpinner30_10.setValue(visitaSocial.getCjMedico());
		}
		if (visitaSocial.getCjRecreacion()!= null) {
			doubleSpinner30_11.setValue(visitaSocial.getCjRecreacion());
		}
		if (visitaSocial.getCjCredito()!= null) {
			doubleSpinner30_12.setValue(visitaSocial.getCjCredito());
		}
		if (visitaSocial.getCjRopa()!= null) {
			doubleSpinner30_13.setValue(visitaSocial.getCjRopa());
		}
		if (visitaSocial.getCjFondo()!= null) {
			doubleSpinner30_14.setValue(visitaSocial.getCjFondo());
		}
		if (visitaSocial.getCjHabitacional()!= null) {
			doubleSpinner30_15.setValue(visitaSocial.getCjHabitacional());
		}
		//label
		Double suma = doubleSpinner30_1.getValue()
				+ doubleSpinner30_2.getValue() + doubleSpinner30_3.getValue()
				+ doubleSpinner30_4.getValue() + doubleSpinner30_5.getValue()
				+ doubleSpinner30_6.getValue() + doubleSpinner30_7.getValue()
				+ doubleSpinner30_8.getValue() + doubleSpinner30_9.getValue()
				+ doubleSpinner30_10.getValue() + doubleSpinner30_11.getValue()
				+ doubleSpinner30_12.getValue() + doubleSpinner30_13.getValue()
				+ doubleSpinner30_14.getValue() + doubleSpinner30_15.getValue();
		labelSuma.setValue(suma.toString());
		// Textbox
		textBox46_1.setValue(visitaSocial.getDfa());
		textBox46_2.setValue(visitaSocial.getDfb());
		textBox46_3.setValue(visitaSocial.getDfc());
		textBox46_4.setValue(visitaSocial.getDfd());
		textBox46_5.setValue(visitaSocial.getDfe());
		textBox46_6.setValue(visitaSocial.getDff());
		textBox46_7.setValue(visitaSocial.getDfg());
		textBox46_8.setValue(visitaSocial.getDfh());
		textBox47_1.setValue(visitaSocial.getDga());
		textBox47_2.setValue(visitaSocial.getDgb());
		textBox47_3.setValue(visitaSocial.getDgc());
		textBox47_4.setValue(visitaSocial.getDgd());
		textBox47_5.setValue(visitaSocial.getDge());
		textBox47_6.setValue(visitaSocial.getDgf());
		textBox47_7.setValue(visitaSocial.getDgg());
		textBox10.setValue(visitaSocial.getDiagnosticoSocial());
		textBox11.setValue(visitaSocial.getObservacion());
		// Datebox
		if(visitaSocial.getFechaAplicacion()!=null){
		dateBoxAplicacion.setValue(visitaSocial.getFechaAplicacion());
		}
		if(visitaSocial.getFechaProcesamiento()!=null){
		dateBoxProcesamiento.setValue(visitaSocial.getFechaProcesamiento());
		}
		if(visitaSocial.getFechaInforme()!=null){
		dateBoxInforme.setValue(visitaSocial.getFechaInforme());
		}
		//Campos de Alimentacion
		cmbRes.setValue(visitaSocial.getResFrecuencia());
		textBoxRes.setValue(visitaSocial.getResDonde());
		String valoresRes[] = visitaSocial.getResComo().split(",");
		if (valoresRes.length != 0) {
			int r = 0;
			while (r < valoresRes.length) {
				if (valoresRes[r].equals(checkRes_1.getLabel()))
					checkRes_1.setChecked(true);
				if (valoresRes[r].equals(checkRes_2.getLabel()))
					checkRes_2.setChecked(true);
				if (valoresRes[r].equals(checkRes_3.getLabel()))
					checkRes_3.setChecked(true);
				if (valoresRes[r].equals(checkRes_4.getLabel()))
					checkRes_4.setChecked(true);
				if (valoresRes[r].equals(checkRes_5.getLabel()))
					checkRes_5.setChecked(true);
				r++;
			}
		}
		cmbPollo.setValue(visitaSocial.getPolloFrecuencia());
		textBoxPollo.setValue(visitaSocial.getPolloDonde());
		String valoresPollo[] = visitaSocial.getPolloComo().split(",");
		if (valoresPollo.length != 0) {
			int p = 0;
			while (p < valoresPollo.length) {
				if (valoresPollo[p].equals(checkPollo_1.getLabel()))
					checkPollo_1.setChecked(true);
				if (valoresPollo[p].equals(checkPollo_2.getLabel()))
					checkPollo_2.setChecked(true);
				if (valoresPollo[p].equals(checkPollo_3.getLabel()))
					checkPollo_3.setChecked(true);
				if (valoresPollo[p].equals(checkPollo_4.getLabel()))
					checkPollo_4.setChecked(true);
				if (valoresPollo[p].equals(checkPollo_5.getLabel()))
					checkPollo_5.setChecked(true);
				p++;
			}
		}
		cmbCerdo.setValue(visitaSocial.getCerdoFrecuencia());
		textBoxCerdo.setValue(visitaSocial.getCerdoDonde());
		String valoresCerdo[] = visitaSocial.getCerdoComo().split(",");
		if (valoresCerdo.length != 0) {
			int c = 0;
			while (c < valoresCerdo.length) {
				if (valoresCerdo[c].equals(checkCerdo_1.getLabel()))
					checkCerdo_1.setChecked(true);
				if (valoresCerdo[c].equals(checkCerdo_2.getLabel()))
					checkCerdo_2.setChecked(true);
				if (valoresCerdo[c].equals(checkCerdo_3.getLabel()))
					checkCerdo_3.setChecked(true);
				if (valoresCerdo[c].equals(checkCerdo_4.getLabel()))
					checkCerdo_4.setChecked(true);
				if (valoresCerdo[c].equals(checkCerdo_5.getLabel()))
					checkCerdo_5.setChecked(true);
				c++;
			}
		}
		cmbPescado.setValue(visitaSocial.getPescadoFrecuencia());
		textBoxPescado.setValue(visitaSocial.getPescadoDonde());
		String valoresPescado[] = visitaSocial.getPescadoComo().split(",");
		if (valoresPescado.length != 0) {
			int pes = 0;
			while (pes < valoresPescado.length) {
				if (valoresPescado[pes].equals(checkPescado_1.getLabel()))
					checkPescado_1.setChecked(true);
				if (valoresPescado[pes].equals(checkPescado_2.getLabel()))
					checkPescado_2.setChecked(true);
				if (valoresPescado[pes].equals(checkPescado_3.getLabel()))
					checkPescado_3.setChecked(true);
				if (valoresPescado[pes].equals(checkPescado_4.getLabel()))
					checkPescado_4.setChecked(true);
				if (valoresPescado[pes].equals(checkPescado_5.getLabel()))
					checkPescado_5.setChecked(true);
				pes++;
			}
		}
		cmbLeche.setValue(visitaSocial.getLecheFrecuencia());
		textBoxLeche.setValue(visitaSocial.getLecheDonde());
		String valoresLeche[] = visitaSocial.getLecheComo().split(",");
		if (valoresLeche.length != 0) {
			int l = 0;
			while (l < valoresLeche.length) {
				if (valoresLeche[l].equals(checkLeche_1.getLabel()))
					checkLeche_1.setChecked(true);
				if (valoresLeche[l].equals(checkLeche_2.getLabel()))
					checkLeche_2.setChecked(true);
				if (valoresLeche[l].equals(checkLeche_3.getLabel()))
					checkLeche_3.setChecked(true);
				if (valoresLeche[l].equals(checkLeche_4.getLabel()))
					checkLeche_4.setChecked(true);
				if (valoresLeche[l].equals(checkLeche_5.getLabel()))
					checkLeche_5.setChecked(true);
				l++;
			}
		}
		cmbHuevos.setValue(visitaSocial.getHuevosFrecuencia());
		textBoxHuevos.setValue(visitaSocial.getHuevosDonde());
		String valoresHuevos[] = visitaSocial.getHuevosComo().split(",");
		if (valoresHuevos.length != 0) {
			int h = 0;
			while (h < valoresHuevos.length) {
				if (valoresHuevos[h].equals(checkHuevos_1.getLabel()))
					checkHuevos_1.setChecked(true);
				if (valoresHuevos[h].equals(checkHuevos_2.getLabel()))
					checkHuevos_2.setChecked(true);
				if (valoresHuevos[h].equals(checkHuevos_3.getLabel()))
					checkHuevos_3.setChecked(true);
				if (valoresHuevos[h].equals(checkHuevos_4.getLabel()))
					checkHuevos_4.setChecked(true);
				if (valoresHuevos[h].equals(checkHuevos_5.getLabel()))
					checkHuevos_5.setChecked(true);
				h++;
			}
		}
		cmbCereales.setValue(visitaSocial.getCerealesFrecuencia());
		textBoxCereales.setValue(visitaSocial.getCerealesDonde());
		String valoresCereales[] = visitaSocial.getCerealesComo().split(",");
		if (valoresCereales.length != 0) {
			int ce = 0;
			while (ce < valoresCereales.length) {
				if (valoresCereales[ce].equals(checkCereales_1.getLabel()))
					checkCereales_1.setChecked(true);
				if (valoresCereales[ce].equals(checkCereales_2.getLabel()))
					checkCereales_2.setChecked(true);
				if (valoresCereales[ce].equals(checkCereales_3.getLabel()))
					checkCereales_3.setChecked(true);
				if (valoresCereales[ce].equals(checkCereales_4.getLabel()))
					checkCereales_4.setChecked(true);
				if (valoresCereales[ce].equals(checkCereales_5.getLabel()))
					checkCereales_5.setChecked(true);
				ce++;
			}
		}
		cmbFrutas.setValue(visitaSocial.getFrutasFrecuencia());
		textBoxFrutas.setValue(visitaSocial.getFrutasDonde());
		String valoresFrutas[] = visitaSocial.getFrutasComo().split(",");
		if (valoresFrutas.length != 0) {
			int f = 0;
			while (f < valoresFrutas.length) {
				if (valoresFrutas[f].equals(checkFrutas_1.getLabel()))
					checkFrutas_1.setChecked(true);
				if (valoresFrutas[f].equals(checkFrutas_2.getLabel()))
					checkFrutas_2.setChecked(true);
				if (valoresFrutas[f].equals(checkFrutas_3.getLabel()))
					checkFrutas_3.setChecked(true);
				if (valoresFrutas[f].equals(checkFrutas_4.getLabel()))
					checkFrutas_4.setChecked(true);
				if (valoresFrutas[f].equals(checkFrutas_5.getLabel()))
					checkFrutas_5.setChecked(true);
				f++;
			}
		}
		cmbVerduras.setValue(visitaSocial.getVerdurasFrecuencia());
		textBoxVerduras.setValue(visitaSocial.getVerdurasDonde());
		String valoresVerduras[] = visitaSocial.getVerdurasComo().split(",");
		if (valoresVerduras.length != 0) {
			int v = 0;
			while (v < valoresVerduras.length) {
				if (valoresVerduras[v].equals(checkVerduras_1.getLabel()))
					checkVerduras_1.setChecked(true);
				if (valoresVerduras[v].equals(checkVerduras_2.getLabel()))
					checkVerduras_2.setChecked(true);
				if (valoresVerduras[v].equals(checkVerduras_3.getLabel()))
					checkVerduras_3.setChecked(true);
				if (valoresVerduras[v].equals(checkVerduras_4.getLabel()))
					checkVerduras_4.setChecked(true);
				if (valoresVerduras[v].equals(checkVerduras_5.getLabel()))
					checkVerduras_5.setChecked(true);
				v++;
			}
		}
		cmbGranos.setValue(visitaSocial.getGranosFrecuencia());
		textBoxGranos.setValue(visitaSocial.getGranosDonde());
		String valoresGranos[] = visitaSocial.getGranosComo().split(",");
		if (valoresGranos.length != 0) {
			int g = 0;
			while (g < valoresGranos.length) {
				if (valoresGranos[g].equals(checkGranos_1.getLabel()))
					checkGranos_1.setChecked(true);
				if (valoresGranos[g].equals(checkGranos_2.getLabel()))
					checkGranos_2.setChecked(true);
				if (valoresGranos[g].equals(checkGranos_3.getLabel()))
					checkGranos_3.setChecked(true);
				if (valoresGranos[g].equals(checkGranos_4.getLabel()))
					checkGranos_4.setChecked(true);
				if (valoresGranos[g].equals(checkGranos_5.getLabel()))
					checkGranos_5.setChecked(true);
				g++;
			}
		}

		ltbComposicion.getItems().clear();
		listaComposicion = servicioComposicionFamiliar.buscarPorVisitaSocial(visitaSocial);
		if (!listaComposicion.isEmpty()) {
			ltbComposicion.setModel(new ListModelList<ComposicionFamiliar>(listaComposicion));
			ltbComposicion.setCheckmark(false);
			ltbComposicion.setCheckmark(true);
		}
		
		BufferedImage imag1;
		if (visitaSocial.getImagen() != null) {
			try {
				imag1 = ImageIO.read(new ByteArrayInputStream(visitaSocial
						.getImagen()));
				imagen1.setContent(imag1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	// Botones

	@Listen("onClick =  #btnBuscar1")
	public void buscarTrabajador() {
		final List<Paciente> pacientes = servicioPaciente
				.buscarTodosTrabajadores();
		catalogoPaciente = new Catalogo<Paciente>(divCatalogoPaciente,
				"Catalogo de Trabajadores", pacientes, false, "Cedula",
				"Primer Nombre", "Segundo Nombre", "Primer Apellido",
				"Segundo Apellido") {

			@Override
			protected List<Paciente> buscar(String valor, String combo) {

				switch (combo) {
				case "Primer Nombre":
					return servicioPaciente.filtroNombre1T(valor);
				case "Segundo Nombre":
					return servicioPaciente.filtroNombre2T(valor);
				case "Cedula":
					return servicioPaciente.filtroCedulaT(valor);
				case "Primer Apellido":
					return servicioPaciente.filtroApellido1T(valor);
				case "Segundo Apellido":
					return servicioPaciente.filtroApellido2T(valor);
				default:
					return pacientes;
				}
			}

			@Override
			protected String[] crearRegistros(Paciente objeto) {
				String[] registros = new String[5];
				registros[0] = objeto.getCedula();
				registros[1] = objeto.getPrimerNombre();
				registros[2] = objeto.getSegundoNombre();
				registros[3] = objeto.getPrimerApellido();
				registros[4] = objeto.getSegundoApellido();
				return registros;
			}

		};
		catalogoPaciente.setParent(divCatalogoPaciente);
		catalogoPaciente.doModal();
	}

	@Listen("onSeleccion = #divCatalogoPaciente")
	public void seleccionPaciente() {
		Paciente paciente = catalogoPaciente.objetoSeleccionadoDelCatalogo();
		cedula = paciente.getCedula();
		llenarCamposPaciente(paciente);
		if(paciente.getVisita()!=null)
		llenarCamposVisita(paciente.getVisita());
		lblCedula.setValue(paciente.getCedula());
		catalogoPaciente.setParent(null);
	}

	// Metodo q llena los campos de paciente, bien sea porq se selecciono del catalogo o porq se selecciono una visita.
	private void llenarCamposPaciente(Paciente paciente) {
		lblCedula.setValue(paciente.getCedula());
		lblFicha.setValue(paciente.getFicha());
		lblNombres.setValue(paciente.getPrimerApellido()+"  "+paciente.getSegundoApellido()+"  "+paciente.getPrimerNombre()+"  "+paciente.getSegundoNombre());
		lblSexo.setValue(paciente.getSexo());
		if (paciente.getFechaNacimiento()!= null)
			lblFechaNacimiento.setValue(String.valueOf(formatoFecha.format(paciente.getFechaNacimiento())));
		int edad= calcularEdad(paciente.getFechaNacimiento());
		lblEdad.setValue(String.valueOf(edad));
		lblLugarNac.setValue(paciente.getLugarNacimiento());
		if (paciente.getFechaIngreso()!= null)
			lblFechaIngreso.setValue(String.valueOf(formatoFecha.format(paciente.getFechaIngreso())));
		
		if(paciente.getEstadoCivil()!=null)
		lblEstadoCivil.setValue(paciente.getEstadoCivil().getNombre());
		
		lblGradoInst.setValue(paciente.getNivelEducativo());
		lblOcupacion.setValue(paciente.getOficio());
		lblProfesion.setValue(paciente.getProfesion());
		lblCondicion.setValue(paciente.getCondicion());
		if(paciente.isJefe()){
			lblJefe.setValue("Si");
		}else{
			lblJefe.setValue("No");
		};
		lblUrb.setValue(paciente.getUrb());
		lblCalle.setValue(paciente.getAvCalle());
		lblSector.setValue(paciente.getSector());
		lblNro.setValue(paciente.getNro());
		lblPuntoRef.setValue(paciente.getPuntoReferencia());
		lblCiudad.setValue("Edo: "+paciente.getCiudadVivienda().getEstado().getNombre());
		lblParroquia.setValue(paciente.getParroquia());
		lblMunicipio.setValue(paciente.getMunicipio());
		lblTelefono1.setValue(paciente.getTelefono1());
		lblTelefono2.setValue(paciente.getTelefono2());
		if(paciente.getTransporte()!=""){
		lblTransporte.setValue(paciente.getTransporte());
		}else{
			lblTransporte.setValue(paciente.getOtroTransporte());
		}
		
		//Completar todos sus campos
	}

	// Combobox
	@Listen("onChange = #cmb1")
	public void tipoDeVivienda() {
		cmb1.getValue();
	}

	@Listen("onChange = #cmb2")
	public void terrenoDeVivienda() {
		cmb2.getValue();
	}

	@Listen("onChange = #cmb3")
	public void condicionDeVivienda() {
		cmb3.getValue();
	}

	@Listen("onChange = #cmb5")
	public void abastecimientoAgua() {
		cmb5.getValue();
	}

	@Listen("onChange = #cmb6")
	public void frecuenciaDeAgua() {
		cmb6.getValue();
	}

	@Listen("onChange = #cmb7")
	public void viviendaP() {
		cmb7.getValue();
	}

	@Listen("onChange = #cmb9")
	public void eliminarBasura() {
		cmb9.getValue();
	}

	@Listen("onChange = #cmb10")
	public void combustibleCocina() {
		cmb10.getValue();
	}

	@Listen("onChange = #cmb17")
	public void viviendaEs() {
		cmb17.getValue();
	}

	@Listen("onChange = #cmb21")
	public void adquiereAlimento() {
		cmb21.getValue();
	}

	@Listen("onChange = #cmb22")
	public void acudirEnProblema() {
		cmb22.getValue();
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

	@Listen("onCheck = #hogarDispone,#hogarDispone2,#hogarDispone3 > checkbox")
	public void seleccioneHogarDispone(CheckEvent event) {
		check19_1.isChecked();
		check19_2.isChecked();
		check19_3.isChecked();
		check19_4.isChecked();
		check19_5.isChecked();
		check19_6.isChecked();
		check19_7.isChecked();
		check19_8.isChecked();
		check19_9.isChecked();
		check19_10.isChecked();
		check19_11.isChecked();
		check19_12.isChecked();
		check19_13.isChecked();
		check19_14.isChecked();
		check19_15.isChecked();
		check19_16.isChecked();
		check19_17.isChecked();
		check19_18.isChecked();

	}

	@Listen("onCheck = #ultimosMesesHogar > checkbox")
	public void seleccioneContraQuien(CheckEvent event) {
		check23_1.isChecked();
		check23_2.isChecked();
		check23_3.isChecked();

	}

	// Radio
	@Listen("onCheck = #rdg8")
	public void servicioElectrico() {
		rdg8.getSelectedItem();
	}

	@Listen("onCheck = #rdg13")
	public void gastosSeparados() {
		rdg13.getSelectedItem();
	}

	// Spinner
	@Listen("onChange = #spinner11")
	public void cantidadAmbientes() {
		spinner11.getValue();
	}

	@Listen("onChange = #spinner14")
	public void gruposGastos() {
		spinner14.getValue();
	}

	@Listen("onChange = #spinner16")
	public void numeroPersonas() {
		spinner16.getValue();
	}

	@Listen("onChange = #spinner18")
	public void numeroCuartos() {
		spinner18.getValue();
	}

	@Listen("onChange = #spinner24")
	public void numeroMujeres() {
		spinner24.getValue();
	}

	@Listen("onChange = #spinner25")
	public void cantidadPersona() {
		spinner25.getValue();
	}

	@Listen("onChange = #dateBox35")
	public void edad() {
		int edadComposicion= calcularEdad(dateBox35.getValue());
		lbl35.setValue(String.valueOf(edadComposicion));
	}

	
	// DoubleSpinner
	@Listen("onChange = #doubleSpinner20")
	public void cantidadBolivares() {
		doubleSpinner20.getValue();
	}

	@Listen("onChange = #doubleSpinner30_1,#doubleSpinner30_2,#doubleSpinner30_3,#doubleSpinner30_4,#doubleSpinner30_5,#doubleSpinner30_6,#doubleSpinner30_7,#doubleSpinner30_8,#doubleSpinner30_9,#doubleSpinner30_10,#doubleSpinner30_11,#doubleSpinner30_12,#doubleSpinner30_13,#doubleSpinner30_14,#doubleSpinner30_15")
	public void calcularBolivares() {
		Double suma = doubleSpinner30_1.getValue()
				+ doubleSpinner30_2.getValue() + doubleSpinner30_3.getValue()
				+ doubleSpinner30_4.getValue() + doubleSpinner30_5.getValue()
				+ doubleSpinner30_6.getValue() + doubleSpinner30_7.getValue()
				+ doubleSpinner30_8.getValue() + doubleSpinner30_9.getValue()
				+ doubleSpinner30_10.getValue() + doubleSpinner30_11.getValue()
				+ doubleSpinner30_12.getValue() + doubleSpinner30_13.getValue()
				+ doubleSpinner30_14.getValue() + doubleSpinner30_15.getValue();

		labelSuma.setValue(suma.toString());

	}
	@Listen("onClick = #btnAgregarItems")
	public void agregarFamiliar() {
		if (validarComposicion()) {
			//Timestamp fechaPlan = new Timestamp(dtbCuando.getValue().getTime());
			ComposicionFamiliar composicionFamiliar = new ComposicionFamiliar();
			composicionFamiliar.setNombre(txtNombre.getValue());
			composicionFamiliar.setParentesco(cmb32.getValue());
			composicionFamiliar.setSexo(cmb33.getValue());
			if(dateBox35.getValue()!=null){
				Date fechaNacimiento = dateBox35.getValue();
				Timestamp fechaN = new Timestamp(fechaNacimiento.getTime());
				composicionFamiliar.setFechaNacimiento(fechaN);
				}
			composicionFamiliar.setDiscapacidad(cmb36.getValue());
			composicionFamiliar.setNivelEducativo(cmb38.getValue());
			composicionFamiliar.setUltimoGrado(txt38.getValue());
			if (rdg39.getSelectedItem() != null) {
				Radio estudia = rdg39.getSelectedItem();
				if (estudia.getId().equals("rdo39_1")) {
					composicionFamiliar.setEstudia(true);
				} else {
					composicionFamiliar.setEstudia(false);
				}
			}
			composicionFamiliar.setCedula(txt40.getValue());
			composicionFamiliar.setDedica(txt41.getValue());
			composicionFamiliar.setOficio(txt42.getValue());
			composicionFamiliar.setLugarTrabajo(txt43.getValue());
			String enfermedades="";
			if (check37_1.isChecked())
				enfermedades = enfermedades + "/" + check37_1.getLabel();
			if (check37_2.isChecked())
				enfermedades = enfermedades + "/" + check37_2.getLabel();
			if (check37_3.isChecked())
				enfermedades = enfermedades + "/" + check37_3.getLabel();
			if (check37_4.isChecked())
				enfermedades = enfermedades + "/" + check37_4.getLabel();
			if (check37_5.isChecked())
				enfermedades = enfermedades + "/" + check37_5.getLabel();
			if (check37_6.isChecked())
				enfermedades = enfermedades + "/" + check37_6.getLabel();
			if (check37_7.isChecked())
				enfermedades = enfermedades + "/" + check37_7.getLabel();
			if (check37_8.isChecked())
				enfermedades = enfermedades + "/" + check37_8.getLabel();
			if (check37_9.isChecked())
				enfermedades = enfermedades + "/" + check37_9.getLabel();
			if (check37_10.isChecked())
				enfermedades = enfermedades + "/" + check37_10.getLabel();
			composicionFamiliar.setEnfermedades(enfermedades);
			//completar campos
			listaComposicion.add(composicionFamiliar);
			ltbComposicion.setModel(new ListModelList<ComposicionFamiliar>(listaComposicion));
			ltbComposicion.setCheckmark(false);
			ltbComposicion.setCheckmark(true);
			int edadComposicionF= calcularEdad(composicionFamiliar.getFechaNacimiento());
			lbl35.setValue(String.valueOf(edadComposicionF));
			limpiarCampos();
		} else
			Mensaje.mensajeError("Debe llenar los datos requeridos del familiar");
	}

	private void limpiarCampos() {
		txtNombre.setValue("");
		cmb32.setValue("");
		cmb33.setValue("");
		dateBox35.setValue(null);
		lbl35.setValue("");
		cmb36.setValue("");
		check37_1.setChecked(false);
		check37_2.setChecked(false);
		check37_3.setChecked(false);
		check37_4.setChecked(false);
		check37_5.setChecked(false);
		check37_6.setChecked(false);
		check37_7.setChecked(false);
		check37_8.setChecked(false);
		check37_9.setChecked(false);
		check37_10.setChecked(false);
		cmb38.setValue("");
		txt38.setValue("");
		rdo39_1.setChecked(false);
		rdo39_2.setChecked(false);
		txt40.setValue("");
		txt41.setValue("");
		txt42.setValue("");
		txt43.setValue("");
		lbl35.setValue("");
	}

	private boolean validarComposicion() {
		if (txtNombre.getText().compareTo("") == 0
				||cmb32.getText().compareTo("") == 0
				||cmb33.getText().compareTo("") == 0
				||cmb36.getText().compareTo("") == 0
				||dateBox35.getText().compareTo("") == 0)
			return false;
		else
			return true;
	}
	@Listen("onClick = #btnEditar")
	public void seleccionarFamiliar() {
		if (ltbComposicion.getItemCount() != 0) {
			if (ltbComposicion.getSelectedItems().size() == 1) {
				Listitem listItem = ltbComposicion.getSelectedItem();
				boolean error = false;
				if (listItem != null) {
					ComposicionFamiliar composicion = listItem.getValue();
					if (!error) {
						listaComposicion.remove(composicion);
						ltbComposicion.setModel(new ListModelList<ComposicionFamiliar>(listaComposicion));
						ltbComposicion.setCheckmark(false);
						ltbComposicion.setCheckmark(true);
						txtNombre.setValue(composicion.getNombre());
						cmb32.setValue(composicion.getParentesco());
						cmb33.setValue(composicion.getSexo());
						dateBox35.setValue(composicion.getFechaNacimiento());
						int edadComposicion= calcularEdad(composicion.getFechaNacimiento());
						lbl35.setValue(String.valueOf(edadComposicion));
						cmb36.setValue(composicion.getDiscapacidad());
						String valoresComposicion[] = composicion.getEnfermedades().split("/");
						if (valoresComposicion.length != 0) {
							int i = 0;
							while (i < valoresComposicion.length) {
								if (valoresComposicion[i].equals(check37_1.getLabel()))
									check37_1.setChecked(true);
								if (valoresComposicion[i].equals(check37_2.getLabel()))
									check37_2.setChecked(true);
								if (valoresComposicion[i].equals(check37_3.getLabel()))
									check37_3.setChecked(true);
								if (valoresComposicion[i].equals(check37_4.getLabel()))
									check37_4.setChecked(true);
								if (valoresComposicion[i].equals(check37_5.getLabel()))
									check37_5.setChecked(true);
								if (valoresComposicion[i].equals(check37_6.getLabel()))
									check37_6.setChecked(true);
								if (valoresComposicion[i].equals(check37_7.getLabel()))
									check37_7.setChecked(true);
								if (valoresComposicion[i].equals(check37_8.getLabel()))
									check37_8.setChecked(true);
								if (valoresComposicion[i].equals(check37_9.getLabel()))
									check37_9.setChecked(true);
								if (valoresComposicion[i].equals(check37_10.getLabel()))
									check37_10.setChecked(true);
								i++;
							}
						}
						if(composicion.isEstudia()){
							rdo39_1.setChecked(true);
						}else{
							rdo39_2.setChecked(true);
						};
						cmb38.setValue(composicion.getNivelEducativo());
						txt38.setValue(composicion.getUltimoGrado());
						txt40.setValue(composicion.getCedula());
						txt41.setValue(composicion.getDedica());
						txt42.setValue(composicion.getOficio());
						txt43.setValue(composicion.getLugarTrabajo());
						//completar campos
					}
				}
			} else
				Mensaje.mensajeError("Debe Seleccionar un Item");
		} else
			Mensaje.mensajeError("No existen Item Registrados");
	}
	@Listen("onClick = #btnRemover")
	public void removerItem() {
		if (ltbComposicion.getItemCount() != 0) {
			if (ltbComposicion.getSelectedItems().size() == 1) {
				Listitem listItem = ltbComposicion.getSelectedItem();
				boolean error = false;
				if (listItem != null) {
					ComposicionFamiliar composicionF  = listItem.getValue();
					if (!error) {
						listaComposicion.remove(composicionF);
						ltbComposicion.setModel(new ListModelList<ComposicionFamiliar>(listaComposicion));
						ltbComposicion.setCheckmark(false);
						ltbComposicion.setCheckmark(true);
					}
				}
			} else
				Mensaje.mensajeError("Debe Seleccionar un Item");
		} else
			Mensaje.mensajeError("No existen Item Registrados");
	}
	protected void guardarFamiliar(VisitaSocial visitaSocial) {
		List<ComposicionFamiliar> composicionFamiliar = servicioComposicionFamiliar
				.buscarPorVisitaSocial(visitaSocial);
		if (!composicionFamiliar.isEmpty())
			servicioComposicionFamiliar.eliminarVarios(composicionFamiliar);
		composicionFamiliar.clear();
		composicionFamiliar = new ArrayList<ComposicionFamiliar>();
		ltbComposicion.renderAll();
		for (int i = 0; i < ltbComposicion.getItemCount(); i++) {
			Listitem listItem = ltbComposicion.getItemAtIndex(i);
			ComposicionFamiliar composicionItem = listItem.getValue();
			ComposicionFamiliar composicion = new ComposicionFamiliar();
			composicion.setIdComposicion(0);
			composicion.setNombre(composicionItem.getNombre());
			composicion.setParentesco(composicionItem.getParentesco());
			composicion.setSexo(composicionItem.getSexo());
			composicion.setCedula(composicionItem.getCedula());
			composicion.setDedica(composicionItem.getDedica());
			composicion.setDiscapacidad(composicionItem.getDiscapacidad());
			composicion.setEnfermedades(composicionItem.getEnfermedades());
			composicion.setLugarTrabajo(composicionItem.getLugarTrabajo());
			composicion.setNivelEducativo(composicionItem.getNivelEducativo());
			composicion.setOficio(composicionItem.getOficio());
			composicion.setUltimoGrado(composicionItem.getUltimoGrado());
			composicion.setFechaNacimiento(composicionItem.getFechaNacimiento());
			composicion.setVisita(visitaSocial);
			composicionFamiliar.add(composicion);
			
		}
		servicioComposicionFamiliar.guardarVarios(composicionFamiliar);
	}
	@Listen("onUpload = #fudImagen1")
	public void processMedia1(UploadEvent event) {
		media1 = event.getMedia();
		if (media1 != null) {
			if (media1.getContentType().equals("image/jpeg")
					|| media1.getContentType().equals("image/png")) {
				if (media1.getByteData().length >= 512000
						&& media1.getByteData().length <= 2048000) {
					imagen1.setContent((org.zkoss.image.Image) media1);
				} else {
					msj.mensajeAlerta(Mensaje.tamanioMuyGrande);
				}
			} else {
				msj.mensajeAlerta(Mensaje.noPermitido);
			}
		}
	}
	
	@Listen("onClick = #btnRemover1")
	public void limpiarI1() {
		org.zkoss.image.Image imagenUsuario1 = null;
		imagen1.setContent(imagenUsuario1);
	}
}
