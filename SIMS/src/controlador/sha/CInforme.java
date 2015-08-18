package controlador.sha;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import modelo.maestros.Empresa;
import modelo.maestros.Paciente;
import modelo.sha.Area;
import modelo.sha.ClasificacionAccidente;
import modelo.sha.Condicion;
import modelo.sha.Informe;
import modelo.sha.PlanAccion;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
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
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;

import controlador.maestros.CGenerico;

public class CInforme extends CGenerico {

	private static final long serialVersionUID = -6378965001066569507L;

	@Wire
	private Button btnReporte;
	@Wire
	private Div divInforme;
	@Wire
	private Tab tab1y2;
	@Wire
	private Tab tab3;
	@Wire
	private Tab tab4;
	@Wire
	private Tab tab5;
	@Wire
	private Tab tab6y7;
	@Wire
	private Tab tab8;
	@Wire
	private Tab tab9;
	@Wire
	private Tab tab10;
	@Wire
	private Textbox txtDescripcion;
	@Wire
	private Textbox txtQuien;
	@Wire
	private Textbox txtDonde;
	@Wire
	private Textbox txtComo;
	@Wire
	private Datebox dtbCuando;
	@Wire
	private Listbox ltbPlan;
	@Wire
	private Listbox ltb822;
	@Wire
	private Listbox ltb829;
	@Wire
	private Listbox ltb8210;
	@Wire
	private Listbox ltb8213;
	@Wire
	private Listbox ltb84;
	@Wire
	private Listbox ltb841;
	@Wire
	private Textbox txt1;
	@Wire
	private Button btnBuscar21;
	@Wire
	private Label lbl211;
	@Wire
	private Label lbl212;
	@Wire
	private Label lbl213;
	@Wire
	private Label lbl214;
	@Wire
	private Button btnBuscar22;
	@Wire
	private Label lbl221;
	@Wire
	private Label lbl222;
	@Wire
	private Label lbl223;
	@Wire
	private Label lbl224;
	@Wire
	private Button btnBuscar23;
	@Wire
	private Label lbl231;
	@Wire
	private Label lbl232;
	@Wire
	private Label lbl233;
	@Wire
	private Label lbl234;
	@Wire
	private Button btnBuscar24;
	@Wire
	private Label lbl241;
	@Wire
	private Label lbl242;
	@Wire
	private Label lbl243;
	@Wire
	private Label lbl244;
	@Wire
	private Button btnBuscar25;
	@Wire
	private Label lbl251;
	@Wire
	private Label lbl252;
	@Wire
	private Label lbl253;
	@Wire
	private Label lbl254;
	@Wire
	private Button btnBuscar26;
	@Wire
	private Label lbl261;
	@Wire
	private Label lbl262;
	@Wire
	private Label lbl263;
	@Wire
	private Label lbl264;
	// tab1
	@Wire
	private Label lbl31;
	@Wire
	private Label lbl32;
	@Wire
	private Button btnBuscar3;
	@Wire
	private Label lbl33;
	@Wire
	private Label lbl34;
	@Wire
	private Label lbl35;
	@Wire
	private Label lbl36;
	@Wire
	private Label lbl37;
	@Wire
	private Label lbl38;
	@Wire
	private Label lbl39;
	@Wire
	private Label lbl310;
	@Wire
	private Label lbl311;
	@Wire
	private Label lbl312;
	@Wire
	private Label lbl313;
	@Wire
	private Label lbl314;
	@Wire
	private Label lbl315;
	@Wire
	private Label lbl316;
	@Wire
	private Label lbl317;
	@Wire
	private Label lbl318;
	@Wire
	private Label lbl319;
	@Wire
	private Label lbl320;
	@Wire
	private Label lbl321;
	@Wire
	private Label lbl322;
	@Wire
	private Label lbl323;
	@Wire
	private Label lbl324;
	@Wire
	private Label lbl325;
	@Wire
	private Label lbl326;
	@Wire
	private Label lbl327;
	@Wire
	private Label lbl328;
	@Wire
	private Label lbl329;
	@Wire
	private Label lbl330;
	@Wire
	private Label lbl331;
	@Wire
	private Label lbl332;
	@Wire
	private Label lbl333;
	@Wire
	private Label lbl334;
	// tab2
	@Wire
	private Label lbl41;
	@Wire
	private Label lbl42;
	@Wire
	private Button btnBuscar4;
	@Wire
	private Label lbl43;
	@Wire
	private Label lbl44;
	@Wire
	private Label lbl45;
	@Wire
	private Label lbl46;
	@Wire
	private Label lbl47;
	@Wire
	private Label lbl48;
	@Wire
	private Label lbl49;
	@Wire
	private Label lbl410;
	@Wire
	private Label lbl411;
	@Wire
	private Label lbl412;
	@Wire
	private Label lbl413;
	@Wire
	private Label lbl414;
	@Wire
	private Label lbl415;
	@Wire
	private Label lbl416;
	@Wire
	private Label lbl417;
	@Wire
	private Label lbl418;
	@Wire
	private Label lbl419;
	@Wire
	private Label lbl420;
	@Wire
	private Label lbl421;
	@Wire
	private Label lbl422;
	@Wire
	private Label lbl423;
	@Wire
	private Label lbl424;
	@Wire
	private Label lbl425;
	@Wire
	private Label lbl426;
	@Wire
	private Label lbl427;
	@Wire
	private Label lbl428;
	@Wire
	private Label lbl429;
	@Wire
	private Label lbl430;
	@Wire
	private Label lbl431;
	@Wire
	private Label lbl432;
	@Wire
	private Label lbl433;
	@Wire
	private Label lbl434;
	// tab3
	@Wire
	private Label lbl51;
	@Wire
	private Label lbl52;
	@Wire
	private Button btnBuscar5;
	@Wire
	private Label lbl53;
	@Wire
	private Label lbl54;
	@Wire
	private Label lbl55;
	@Wire
	private Label lbl56;
	@Wire
	private Label lbl57;
	@Wire
	private Label lbl58;
	@Wire
	private Label lbl59;
	@Wire
	private Label lbl510;
	@Wire
	private Label lbl511;
	@Wire
	private Label lbl512;
	@Wire
	private Label lbl513;
	@Wire
	private Label lbl514;
	@Wire
	private Label lbl515;
	@Wire
	private Label lbl516;
	@Wire
	private Label lbl517;
	@Wire
	private Label lbl518;
	@Wire
	private Label lbl519;
	@Wire
	private Label lbl520;
	@Wire
	private Label lbl521;
	@Wire
	private Label lbl522;
	@Wire
	private Radiogroup rdg523;
	@Wire
	private Textbox txt5236;
	@Wire
	private Radiogroup rdg524;
	@Wire
	private Radiogroup rdg525;
	@Wire
	private Textbox txt5257;
	@Wire
	private Radiogroup rdg526;
	@Wire
	private Radiogroup rdg527;
	@Wire
	private Textbox txt5275;
	// tab4
	@Wire
	private Datebox dtb61;
	@Wire
	private Combobox cmb62;
	@Wire
	private Timebox tmb63;
	@Wire
	private Textbox txt64;
	@Wire
	private Textbox txt65;
	@Wire
	private Textbox txt66;
	@Wire
	private Combobox cmb67;
	@Wire
	private Textbox txt671;
	@Wire
	private Textbox txt672;
	@Wire
	private Textbox txt673;
	@Wire
	private Textbox txt674;
	@Wire
	private Textbox txt675;
	@Wire
	private Combobox cmb676;
	@Wire
	private Radio rdo6771;
	@Wire
	private Radio rdo6772;
	@Wire
	private Radio rdo6781;
	@Wire
	private Radio rdo6782;
	@Wire
	private Textbox txt678;
	@Wire
	private Radiogroup rdg679;
	@Wire
	private Radio rdo6791;
	@Wire
	private Radio rdo6792;
	@Wire
	private Textbox txt6710;
	@Wire
	private Textbox txt6711;
	@Wire
	private Textbox txt6712;
	@Wire
	private Combobox cmb6713;
	@Wire
	private Textbox txt6714;
	@Wire
	private Textbox txt711;
	@Wire
	private Textbox txt811;
	@Wire
	private Textbox txt812;
	@Wire
	private Textbox txt813;
	@Wire
	private Radio rdo8141;
	@Wire
	private Radio rdo8142;
	@Wire
	private Radiogroup rdg815;
	@Wire
	private Radio rdo8151;
	@Wire
	private Radio rdo8152;
	@Wire
	private Textbox txt815;
	@Wire
	private Textbox txt816;
	@Wire
	private Textbox txt821;
	@Wire
	private Textbox txt823;
	@Wire
	private Radio rdo8242;
	@Wire
	private Radio rdo8241;
	@Wire
	private Textbox txt8241;
	@Wire
	private Radiogroup rdg825;
	@Wire
	private Radio rdo8251;
	@Wire
	private Radio rdo8252;
	@Wire
	private Radio rdo8253;
	@Wire
	private Textbox txt825;
	@Wire
	private Radio rdo8261;
	@Wire
	private Radio rdo8262;
	@Wire
	private Radio rdo8271;
	@Wire
	private Radio rdo8272;
	@Wire
	private Radiogroup rdg828;
	@Wire
	private Radio rdo8281;
	@Wire
	private Radio rdo8282;
	@Wire
	private Radio rdo8283;
	@Wire
	private Radio rdo8284;
	@Wire
	private Radio rdo82111;
	@Wire
	private Radio rdo82112;
	@Wire
	private Textbox txt8211;
	@Wire
	private Radio rdo82121;
	@Wire
	private Radio rdo82122;
	@Wire
	private Textbox txt8212;
	@Wire
	private Textbox txt831;
	@Wire
	private Radio rdo8321;
	@Wire
	private Radio rdo8322;
	@Wire
	private Radio rdo8331;
	@Wire
	private Radio rdo8332;
	@Wire
	private Radio rdo8341;
	@Wire
	private Radio rdo8342;
	@Wire
	private Radiogroup rdg835;
	@Wire
	private Textbox txt835;
	@Wire
	private Radio rdo8351;
	@Wire
	private Radio rdo8352;
	@Wire
	private Radio rdo8353;
	@Wire
	private Radiogroup rdg836;
	@Wire
	private Textbox txt836;
	@Wire
	private Radio rdo8361;
	@Wire
	private Radio rdo8362;
	@Wire
	private Radio rdo8363;
	@Wire
	private Radio rdo8371;
	@Wire
	private Radio rdo8372;
	@Wire
	private Radiogroup rdg838;
	@Wire
	private Textbox txt838;
	@Wire
	private Radio rdo8381;
	@Wire
	private Radio rdo8382;
	@Wire
	private Radio rdo8383;
	@Wire
	private Radio rdo8384;
	@Wire
	private Radiogroup rdg839;
	@Wire
	private Textbox txt839;
	@Wire
	private Radio rdo8391;
	@Wire
	private Radio rdo8392;
	@Wire
	private Radio rdo8393;
	@Wire
	private Radio rdo83101;
	@Wire
	private Radio rdo83102;
	@Wire
	private Radio rdo83111;
	@Wire
	private Radio rdo83112;
	@Wire
	private Textbox txt8311;
	@Wire
	private Radio rdo83121;
	@Wire
	private Radio rdo83122;
	@Wire
	private Textbox txt8312;
	@Wire
	private Radio rdo83131;
	@Wire
	private Radio rdo83132;
	@Wire
	private Textbox txt8314;
	@Wire
	private Radiogroup rdg8315;
	@Wire
	private Textbox txt8315;
	@Wire
	private Textbox txt8316;
	@Wire
	private Radio rdo831711;
	@Wire
	private Radio rdo831712;
	@Wire
	private Radio rdo831722;
	@Wire
	private Radio rdo831721;
	@Wire
	private Radio rdo831731;
	@Wire
	private Radio rdo831732;
	@Wire
	private Radio rdo831741;
	@Wire
	private Radio rdo831742;
	@Wire
	private Radio rdo831751;
	@Wire
	private Radio rdo831752;
	@Wire
	private Radio rdo831762;
	@Wire
	private Radio rdo831761;
	@Wire
	private Textbox txt83176;
	@Wire
	private Radio rdo831771;
	@Wire
	private Radio rdo831772;
	@Wire
	private Radio rdo831781;
	@Wire
	private Radio rdo831782;
	@Wire
	private Radio rdo831791;
	@Wire
	private Radio rdo831792;
	@Wire
	private Textbox txt841;
	@Wire
	private Radio rdo8421;
	@Wire
	private Radio rdo8422;
	@Wire
	private Textbox txt842;
	@Wire
	private Radiogroup rdg843;
	@Wire
	private Radio rdo8431;
	@Wire
	private Radio rdo8432;
	@Wire
	private Textbox txt843;
	@Wire
	private Textbox txt844;
	@Wire
	private Textbox txt8451;
	@Wire
	private Textbox txt8452;
	@Wire
	private Textbox txt8453;
	@Wire
	private Textbox txt8454;
	@Wire
	private Textbox txt846;
	// tab 6
	@Wire
	private Radio rdo9111;
	@Wire
	private Radio rdo9112;
	@Wire
	private Radio rdo9121;
	@Wire
	private Radio rdo9122;
	@Wire
	private Radio rdo9131;
	@Wire
	private Radio rdo9132;
	@Wire
	private Radio rdo9141;
	@Wire
	private Radio rdo9142;
	@Wire
	private Textbox txt914;
	@Wire
	private Datebox dtb915;
	@Wire
	private Radio rdo9161;
	@Wire
	private Radio rdo9162;
	@Wire
	private Radio rdo9171;
	@Wire
	private Radio rdo9172;
	@Wire
	private Textbox txt917;
	@Wire
	private Radio rdo9181;
	@Wire
	private Radio rdo9182;
	@Wire
	private Textbox txt919;
	@Wire
	private Textbox txt9110;
	@Wire
	private Radio rdo9211;
	@Wire
	private Radio rdo9212;
	@Wire
	private Textbox txt9212;
	@Wire
	private Datebox dtb922;
	@Wire
	private Textbox txt923;
	@Wire
	private Datebox dtb924;
	@Wire
	private Radio rdo9251;
	@Wire
	private Radio rdo9252;
	@Wire
	private Radio rdo9261;
	@Wire
	private Radio rdo9262;
	@Wire
	private Radio rdo9271;
	@Wire
	private Radio rdo9272;
	@Wire
	private Radio rdo9281;
	@Wire
	private Radio rdo9282;
	@Wire
	private Radio rdo9291;
	@Wire
	private Radio rdo9292;
	@Wire
	private Radio rdo92101;
	@Wire
	private Radio rdo92102;
	@Wire
	private Radio rdo92111;
	@Wire
	private Radio rdo92112;
	@Wire
	private Textbox txt9213;
	@Wire
	private Radio rdo9311;
	@Wire
	private Radio rdo9312;
	@Wire
	private Datebox dtb932;
	@Wire
	private Textbox txt933;
	@Wire
	private Radiogroup rdg934;
	@Wire
	private Radio rdo9341;
	@Wire
	private Radio rdo9342;
	@Wire
	private Radio rdo9351;
	@Wire
	private Radio rdo9352;
	@Wire
	private Radio rdo9361;
	@Wire
	private Radio rdo9362;
	@Wire
	private Radio rdo9371;
	@Wire
	private Radio rdo9372;
	@Wire
	private Radio rdo9381;
	@Wire
	private Radio rdo9382;
	@Wire
	private Radio rdo9391;
	@Wire
	private Radio rdo9392;
	@Wire
	private Datebox dtb9310;
	@Wire
	private Textbox txt9311;
	@Wire
	private Radiogroup rdg9312;
	@Wire
	private Radio rdo93121;
	@Wire
	private Radio rdo93122;
	@Wire
	private Radiogroup rdg9313;
	@Wire
	private Textbox txt9315;
	@Wire
	private Radio rdo9411;
	@Wire
	private Radio rdo9412;
	@Wire
	private Radio rdo9421;
	@Wire
	private Radio rdo9422;
	@Wire
	private Radio rdo9431;
	@Wire
	private Radio rdo9432;
	@Wire
	private Radio rdo9441;
	@Wire
	private Radio rdo9442;
	@Wire
	private Radio rdo9451;
	@Wire
	private Radio rdo9452;
	@Wire
	private Radio rdo9461;
	@Wire
	private Radio rdo9462;
	@Wire
	private Radio rdo9471;
	@Wire
	private Radio rdo9472;
	@Wire
	private Radio rdo9481;
	@Wire
	private Radio rdo9482;
	@Wire
	private Radio rdo9491;
	@Wire
	private Radio rdo9492;
	@Wire
	private Radio rdo94101;
	@Wire
	private Radio rdo94102;
	@Wire
	private Datebox dtb9411;
	@Wire
	private Textbox txt9412;
	@Wire
	private Radio rdo94131;
	@Wire
	private Radio rdo94132;
	@Wire
	private Textbox txt9414;
	@Wire
	private Label lbl931411;
	@Wire
	private Label lbl931412;
	@Wire
	private Label lbl931413;
	@Wire
	private Label lbl931414;
	@Wire
	private Label lbl931415;
	@Wire
	private Label lbl931416;
	@Wire
	private Button btnBuscar93141;
	@Wire
	private Label lbl931421;
	@Wire
	private Label lbl931422;
	@Wire
	private Label lbl931423;
	@Wire
	private Label lbl931424;
	@Wire
	private Label lbl931425;
	@Wire
	private Label lbl931426;
	@Wire
	private Button btnBuscar93142;
	@Wire
	private Label lbl931431;
	@Wire
	private Label lbl931432;
	@Wire
	private Label lbl931433;
	@Wire
	private Label lbl931434;
	@Wire
	private Label lbl931435;
	@Wire
	private Label lbl931436;
	@Wire
	private Button btnBuscar93143;
	@Wire
	private Label lbl931441;
	@Wire
	private Label lbl931442;
	@Wire
	private Label lbl931443;
	@Wire
	private Label lbl931444;
	@Wire
	private Label lbl931445;
	@Wire
	private Label lbl931446;
	@Wire
	private Button btnBuscar93144;
	@Wire
	private Label lbl931451;
	@Wire
	private Label lbl931452;
	@Wire
	private Label lbl931453;
	@Wire
	private Label lbl931454;
	@Wire
	private Label lbl931455;
	@Wire
	private Label lbl931456;
	@Wire
	private Button btnBuscar93145;
	@Wire
	private Label lbl931461;
	@Wire
	private Label lbl931462;
	@Wire
	private Label lbl931463;
	@Wire
	private Label lbl931464;
	@Wire
	private Label lbl931465;
	@Wire
	private Label lbl931466;
	@Wire
	private Button btnBuscar93146;
	@Wire
	private Div catalogoPaciente;
	@Wire
	private Div catalogoEmpresa;
	@Wire
	private Div catalogoInforme;
	@Wire
	private Div botoneraInforme;

	@Wire
	private Textbox txtDescripcionVisita;
	@Wire
	private Textbox txtQuienVisita;
	@Wire
	private Textbox txtDondeVisita;
	@Wire
	private Textbox txtComoVisita;
	@Wire
	private Datebox dtbCuandoVisita;
	@Wire
	private Listbox ltbPlanVisita;
	@Wire
	private Textbox txtFuncionario;
	@Wire
	private Textbox txtOrdenamientos;
	@Wire
	private Datebox dtbFechaVisita;
	@Wire
	private Image imagen1;
	@Wire
	private Fileupload fudImagen1;
	@Wire
	private Media media1;
	@Wire
	private Image imagen2;
	@Wire
	private Fileupload fudImagen2;
	@Wire
	private Media media2;
	@Wire
	private Image imagen3;
	@Wire
	private Fileupload fudImagen3;
	@Wire
	private Media media3;
	@Wire
	private Image imagen4;
	@Wire
	private Fileupload fudImagen4;
	@Wire
	private Media media4;
	@Wire
	private Image imagen5;
	@Wire
	private Fileupload fudImagen5;
	@Wire
	private Media media5;
	@Wire
	private Textbox txtImagen1;
	@Wire
	private Checkbox chk1;
	@Wire
	private Textbox txtImagen2;
	@Wire
	private Checkbox chk2;
	@Wire
	private Textbox txtImagen3;
	@Wire
	private Checkbox chk3;
	@Wire
	private Textbox txtImagen4;
	@Wire
	private Checkbox chk4;
	@Wire
	private Textbox txtImagen5;
	@Wire
	private Checkbox chk5;

	Catalogo<Paciente> catalogoP;
	Catalogo<Empresa> catalogoE;
	Catalogo<Informe> catalogoI;
	ListModelList<Condicion> condicionesA;
	ListModelList<Condicion> condicionesB;
	ListModelList<Condicion> condicionesC;
	ListModelList<Condicion> condicionesD;
	ListModelList<Condicion> condicionesE;
	ListModelList<Condicion> condicionesF;
	String idBotonPaciente = "";
	String idBotonEmpresa = "";
	List<Listbox> listas = new ArrayList<Listbox>();
	long idInforme = 0;
	long idEmpresa = 0;
	long idEmpresa2 = 0;

	private List<PlanAccion> planes = new ArrayList<PlanAccion>();
	private List<PlanAccion> planes2 = new ArrayList<PlanAccion>();
	Botonera botonera;

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
		listas.add(ltb8210);
		listas.add(ltb8213);
		listas.add(ltb822);
		listas.add(ltb829);
		listas.add(ltb84);
		listas.add(ltb841);
		listasMultiples();
		llenarComboCLasificacion();
		llenarComboArea();
		txt1.setDisabled(true);
		Integer c2 = servicioInforme.buscarMaxCodigo();
		String c = String.valueOf(c2);
		int n = c.length();
		String nro ="";
		for (int i = 4; i < n; i++) {
			nro = nro+c.charAt(i);
		}
		Integer co = Integer.parseInt(nro);
		String finaal = String.valueOf(co + 1);
		txt1.setValue(cod + finaal);

		botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divInforme, "Informe", tabs);
			}

			@Override
			public void limpiar() {
				idInforme = 0;
				Integer c2 = servicioInforme.buscarMaxCodigo();
				String c = String.valueOf(c2);
				int n = c.length();
				String nro ="";
				for (int i = 4; i < n; i++) {
					nro = nro+c.charAt(i);
				}
				Integer co = Integer.parseInt(nro);
				String finaal = String.valueOf(co + 1);
				txt1.setValue(cod + finaal);
				ltbPlan.getItems().clear();
				planes.clear();
				ltbPlanVisita.getItems().clear();
				planes2.clear();
				botonera.getChildren().get(0).setVisible(true);
				limpiarCampos();
				limpiarCamposPlan();
				limpiarCamposPlan2();
				org.zkoss.image.Image imagenUsuario1 = null;
				imagen1.setContent(imagenUsuario1);
				imagen2.setContent(imagenUsuario1);
				imagen3.setContent(imagenUsuario1);
				imagen4.setContent(imagenUsuario1);
				imagen5.setContent(imagenUsuario1);
				txtImagen1.setValue("");
				txtImagen2.setValue("");
				txtImagen3.setValue("");
				txtImagen4.setValue("");
				txtImagen5.setValue("");
				chk1.setChecked(false);
				chk2.setChecked(false);
				chk3.setChecked(false);
				chk4.setChecked(false);
				chk5.setChecked(false);
			}

			@Override
			public void guardar() {
				if (validar()) {
					Informe informe = new Informe();
					informe.setIdInforme(idInforme);
					informe.setCodigo(txt1.getValue());
					informe.setFuncionario(txtFuncionario.getValue());
					informe.setOrdenamientos(txtOrdenamientos.getValue());
					Date fecha2 = dtbFechaVisita.getValue();
					Timestamp fechaN2 = new Timestamp(fecha2.getTime());
					informe.setFechaVisita(fechaN2);
					Paciente pacientePrincipal = servicioPaciente
							.buscarPorCedula(lbl53.getValue());
					informe.setPacienteA(pacientePrincipal);

					if (rdg523.getSelectedItem() != null) {
						informe.setEbc(rdg523.getSelectedItem().getLabel());
						informe.setEbcf(txt5236.getValue());
					}
					if (rdg524.getSelectedItem() != null)
						informe.setEbd(rdg524.getSelectedItem().getLabel());
					if (rdg525.getSelectedItem() != null) {
						informe.setEbe(rdg525.getSelectedItem().getLabel());
						informe.setEbeg(txt5257.getValue());
					}
					if (rdg526.getSelectedItem() != null)
						informe.setEbf(rdg526.getSelectedItem().getLabel());

					if (rdg527.getSelectedItem() != null) {
						informe.setEbg(rdg527.getSelectedItem().getLabel());
						informe.setEbge(txt5275.getValue());
					}

					Paciente paciente = servicioPaciente.buscarPorCedula(lbl212
							.getValue());
					informe.setPacienteB(paciente);
					paciente = servicioPaciente.buscarPorCedula(lbl222
							.getValue());
					informe.setPacienteC(paciente);
					paciente = servicioPaciente.buscarPorCedula(lbl232
							.getValue());
					informe.setPacienteD(paciente);
					paciente = servicioPaciente.buscarPorCedula(lbl242
							.getValue());
					informe.setPacienteE(paciente);
					paciente = servicioPaciente.buscarPorCedula(lbl252
							.getValue());
					informe.setPacienteF(paciente);
					paciente = servicioPaciente.buscarPorCedula(lbl262
							.getValue());
					informe.setPacienteG(paciente);
					paciente = servicioPaciente.buscarPorCedula(lbl931412
							.getValue());
					informe.setPacienteH(paciente);
					paciente = servicioPaciente.buscarPorCedula(lbl931422
							.getValue());
					informe.setPacienteI(paciente);
					paciente = servicioPaciente.buscarPorCedula(lbl931432
							.getValue());
					informe.setPacienteJ(paciente);
					paciente = servicioPaciente.buscarPorCedula(lbl931442
							.getValue());
					informe.setPacienteK(paciente);
					paciente = servicioPaciente.buscarPorCedula(lbl931452
							.getValue());
					informe.setPacienteL(paciente);
					paciente = servicioPaciente.buscarPorCedula(lbl931462
							.getValue());
					informe.setPacienteM(paciente);
					if (cmb6713.getSelectedItem() != null) {
						Area area = servicioArea.buscar(Long.valueOf(cmb6713
								.getSelectedItem().getContext()));
						informe.setArea(area);
					}
					if (cmb67.getSelectedItem() != null) {
						ClasificacionAccidente clasificacion = servicioClasificacionAccidente
								.buscar(Long.valueOf(cmb67.getSelectedItem()
										.getContext()));
						informe.setClasificacion(clasificacion);
					}
					Empresa empresa = servicioEmpresa.buscar(idEmpresa);
					informe.setEmpresaA(empresa);
					empresa = servicioEmpresa.buscar(idEmpresa2);
					informe.setEmpresaB(empresa);

					Set<Condicion> condiciones = new HashSet<Condicion>();
					for (int i = 0; i < ltb822.getItemCount(); i++) {
						Listitem listItem = ltb822.getItemAtIndex(i);
						if (listItem.isSelected()) {
							Condicion condicion = listItem.getValue();
							condiciones.add(condicion);
						}
					}
					informe.setCondicionA(condiciones);

					Set<Condicion> condiciones2 = new HashSet<Condicion>();
					for (int i = 0; i < ltb829.getItemCount(); i++) {
						Listitem listItem = ltb829.getItemAtIndex(i);
						if (listItem.isSelected()) {
							Condicion condicion = listItem.getValue();
							condiciones2.add(condicion);
						}
					}
					informe.setCondicionB(condiciones2);

					condiciones = new HashSet<Condicion>();
					for (int i = 0; i < ltb8210.getItemCount(); i++) {
						Listitem listItem = ltb8210.getItemAtIndex(i);
						if (listItem.isSelected()) {
							Condicion condicion = listItem.getValue();
							condiciones.add(condicion);
						}
					}
					informe.setCondicionC(condiciones);

					condiciones = new HashSet<Condicion>();
					for (int i = 0; i < ltb8213.getItemCount(); i++) {
						Listitem listItem = ltb8213.getItemAtIndex(i);
						if (listItem.isSelected()) {
							Condicion condicion = listItem.getValue();
							condiciones.add(condicion);
						}
					}
					informe.setCondicionD(condiciones);

					condiciones = new HashSet<Condicion>();
					for (int i = 0; i < ltb84.getItemCount(); i++) {
						Listitem listItem = ltb84.getItemAtIndex(i);
						if (listItem.isSelected()) {
							Condicion condicion = listItem.getValue();
							condiciones.add(condicion);
						}
					}
					informe.setCondicionE(condiciones);

					condiciones = new HashSet<Condicion>();
					for (int i = 0; i < ltb841.getItemCount(); i++) {
						Listitem listItem = ltb841.getItemAtIndex(i);
						if (listItem.isSelected()) {
							Condicion condicion = listItem.getValue();
							condiciones.add(condicion);
						}
					}
					informe.setCondicionF(condiciones);
					Date fecha = dtb61.getValue();
					Timestamp fechaN = new Timestamp(fecha.getTime());
					informe.setFa(fechaN);
					informe.setFb(cmb62.getValue());
					Date horaEventos = tmb63.getValue();
					informe.setFc(df.format(horaEventos));
					informe.setFd(txt64.getValue());
					informe.setFe(txt65.getValue());
					informe.setFf(txt66.getValue());
					informe.setFga(txt671.getValue());
					informe.setFgb(txt672.getValue());
					informe.setFgc(txt673.getValue());
					informe.setFgd(txt674.getValue());
					informe.setFge(txt675.getValue());
					informe.setFgf(cmb676.getValue());
					if (rdo6771.isChecked())
						informe.setFgga(true);
					else
						informe.setFgga(false);
					if (rdo6781.isChecked()) {
						informe.setFgha(true);
						informe.setFgh(txt678.getValue());
					} else
						informe.setFgha(false);
					if (rdg679.getSelectedItem() != null)
						informe.setFgi(rdg679.getSelectedItem().getLabel());
					informe.setFgj(txt6710.getValue());
					informe.setFgaa(txt6711.getValue());
					informe.setFgab(txt6712.getValue());
					informe.setFgad(txt6714.getValue());
					informe.setGaa(txt711.getValue());
					informe.setHaa(txt811.getValue());
					informe.setHab(txt812.getValue());
					informe.setHac(txt813.getValue());
					if (rdo8141.isChecked())
						informe.setHada(true);
					else
						informe.setHada(false);
					if (rdg815.getSelectedItem() != null)
						informe.setHaea(rdg815.getSelectedItem().getLabel());
					informe.setHae(txt815.getValue());
					informe.setHaf(txt816.getValue());
					informe.setHba(txt821.getValue());
					informe.setHbc(txt823.getValue());

					if (rdo8241.isChecked())
						informe.setHbd(true);
					else
						informe.setHbd(false);
					informe.setHbda(txt8241.getValue());
					if (rdg825.getSelectedItem() != null)
						informe.setHbe(rdg825.getSelectedItem().getLabel());
					informe.setHbea(txt825.getValue());
					if (rdo8262.isChecked())
						informe.setHbf(true);
					else
						informe.setHbf(false);
					if (rdo8272.isChecked())
						informe.setHbg(true);
					else
						informe.setHbg(false);
					if (rdg828.getSelectedItem() != null)
						informe.setHbh(rdg828.getSelectedItem().getLabel());
					if (rdo82112.isChecked())
						informe.setHbaaa(true);
					else {
						informe.setHbaaa(false);
						informe.setHbaa(txt8211.getValue());
					}
					if (rdo82122.isChecked())
						informe.setHbaab(true);
					else {
						informe.setHbaab(false);
						informe.setHbab(txt8212.getValue());
					}

					informe.setHca(txt831.getValue());
					if (rdo8322.isChecked())
						informe.setHcb(true);
					else
						informe.setHcb(false);
					if (rdo8332.isChecked())
						informe.setHcc(true);
					else
						informe.setHcc(false);
					if (rdo8341.isChecked())
						informe.setHcd(true);
					else
						informe.setHcd(false);
					if (rdg835.getSelectedItem() != null)
						informe.setHcae(rdg835.getSelectedItem().getLabel());
					informe.setHcea(txt835.getValue());
					if (rdg836.getSelectedItem() != null)
						informe.setHcf(rdg836.getSelectedItem().getLabel());
					informe.setHcfa(txt836.getValue());
					if (rdo8371.isChecked())
						informe.setHcg(true);
					else
						informe.setHcg(false);
					if (rdg838.getSelectedItem() != null)
						informe.setHch(rdg838.getSelectedItem().getLabel());
					informe.setHcha(txt838.getValue());
					if (rdg839.getSelectedItem() != null)
						informe.setHci(rdg839.getSelectedItem().getLabel());
					informe.setHcia(txt839.getValue());
					if (rdo83101.isChecked())
						informe.setHcj(true);
					else
						informe.setHcj(false);
					if (rdo83111.isChecked()) {
						informe.setHcaa(true);

					} else
						informe.setHcaa(false);
					informe.setHcaaa(txt8311.getValue());
					if (rdo83122.isChecked())
						informe.setHcab(true);
					else {
						informe.setHcab(false);
					}
					informe.setHcaba(txt8312.getValue());
					if (rdo83131.isChecked())
						informe.setHcac(true);
					else
						informe.setHcac(false);

					informe.setHcad(txt8314.getValue());
					if (rdg8315.getSelectedItem() != null)
						informe.setHcae(rdg8315.getSelectedItem().getLabel());
					informe.setHcaea(txt8315.getValue());
					informe.setHcaf(txt8316.getValue());
					if (rdo831711.isChecked())
						informe.setHcaga(true);
					else
						informe.setHcaga(false);
					if (rdo831721.isChecked())
						informe.setHcagb(true);
					else
						informe.setHcagb(false);
					if (rdo831731.isChecked())
						informe.setHcagc(true);
					else
						informe.setHcagc(false);
					if (rdo831741.isChecked())
						informe.setHcagd(true);
					else
						informe.setHcagd(false);
					if (rdo831751.isChecked())
						informe.setHcage(true);
					else
						informe.setHcage(false);
					if (rdo831762.isChecked())
						informe.setHcagf(true);
					else
						informe.setHcagf(false);
					informe.setHcagfa(txt83176.getValue());
					if (rdo831771.isChecked())
						informe.setHcagg(true);
					else
						informe.setHcagg(false);
					if (rdo831781.isChecked())
						informe.setHcagh(true);
					else
						informe.setHcagh(false);
					if (rdo831791.isChecked())
						informe.setHcagi(true);
					else
						informe.setHcagi(false);

					informe.setHda(txt841.getValue());

					if (rdo8421.isChecked())
						informe.setHdb(true);
					else {
						informe.setHdb(false);
					}
					informe.setHdba(txt842.getValue());
					if (rdg843.getSelectedItem() != null)
						informe.setHdc(rdg843.getSelectedItem().getLabel());
					informe.setHdca(txt843.getValue());
					informe.setHdd(txt844.getValue());
					informe.setHdea(txt8451.getValue());
					informe.setHdeb(txt8452.getValue());
					informe.setHdec(txt8453.getValue());
					informe.setHded(txt8454.getValue());
					informe.setHdf(txt846.getValue());

					if (rdo9111.isChecked())
						informe.setIaa(true);
					else
						informe.setIaa(false);
					if (rdo9121.isChecked())
						informe.setIab(true);
					else
						informe.setIab(false);
					if (rdo9131.isChecked())
						informe.setIac(true);
					else
						informe.setIac(false);
					if (rdo9142.isChecked())
						informe.setIad(true);
					else {
						informe.setIad(false);
					}
					informe.setIada(txt914.getValue());
					Date fecha914 = dtb915.getValue();
					Timestamp fe914 = new Timestamp(fecha914.getTime());
					informe.setIae(fe914);
					if (rdo9161.isChecked())
						informe.setIaf(true);
					else
						informe.setIaf(false);
					if (rdo9171.isChecked())
						informe.setIag(false);
					else
						informe.setIag(true);

					informe.setIaga(txt917.getValue());
					if (rdo9181.isChecked())
						informe.setIah(true);
					else
						informe.setIah(false);
					informe.setIai(txt919.getValue());
					informe.setIaj(txt9110.getValue());
					if (rdo9211.isChecked())
						informe.setIba(true);
					else
						informe.setIba(false);
					Date fecha922 = dtb922.getValue();
					Timestamp fe922 = new Timestamp(fecha922.getTime());
					informe.setIbb(fe922);
					informe.setIbc(txt923.getValue());
					Date fecha924 = dtb924.getValue();
					Timestamp fe924 = new Timestamp(fecha924.getTime());
					informe.setIbd(fe924);
					if (rdo9251.isChecked())
						informe.setIbe(true);
					else
						informe.setIbe(false);
					if (rdo9261.isChecked())
						informe.setIbf(true);
					else
						informe.setIbf(false);
					if (rdo9271.isChecked())
						informe.setIbg(true);
					else
						informe.setIbg(false);
					if (rdo9281.isChecked())
						informe.setIbh(true);
					else
						informe.setIbh(false);
					if (rdo9291.isChecked())
						informe.setIbi(true);
					else
						informe.setIbi(false);
					if (rdo92101.isChecked())
						informe.setIbj(true);
					else
						informe.setIbj(false);
					if (rdo92111.isChecked())
						informe.setIbaa(true);
					else {
						informe.setIbaa(false);

					}
					informe.setIbab(txt9212.getValue());
					informe.setIbac(txt9213.getValue());
					if (rdo9311.isChecked())
						informe.setIca(true);
					else
						informe.setIca(false);
					Date fecha932 = dtb932.getValue();
					Timestamp fe932 = new Timestamp(fecha932.getTime());
					informe.setIcb(fe932);
					informe.setIcc(txt933.getValue());
					if (rdg934.getSelectedItem() != null)
						informe.setIcd(rdg934.getSelectedItem().getLabel());
					if (rdo9351.isChecked())
						informe.setIce(true);
					else
						informe.setIce(false);
					if (rdo9361.isChecked())
						informe.setIcf(true);
					else
						informe.setIcf(false);
					if (rdo9371.isChecked())
						informe.setIcg(true);
					else
						informe.setIcg(false);
					if (rdo9381.isChecked())
						informe.setIch(true);
					else
						informe.setIch(false);
					if (rdo9391.isChecked())
						informe.setIci(true);
					else
						informe.setIci(false);
					Date fecha9310 = dtb9310.getValue();
					Timestamp fe9310 = new Timestamp(fecha9310.getTime());
					informe.setIcj(fe9310);
					informe.setIcaa(txt9311.getValue());
					if (rdg9312.getSelectedItem() != null)
						informe.setIcab(rdg9312.getSelectedItem().getLabel());
					if (rdg9313.getSelectedItem() != null)
						informe.setIcac(rdg9313.getSelectedItem().getLabel());
					informe.setIcae(txt9315.getValue());
					if (rdo9411.isChecked())
						informe.setIda(true);
					else
						informe.setIda(false);
					if (rdo9421.isChecked())
						informe.setIdb(true);
					else
						informe.setIdb(false);
					if (rdo9431.isChecked())
						informe.setIdc(true);
					else
						informe.setIdc(false);
					if (rdo9441.isChecked())
						informe.setIdd(true);
					else
						informe.setIdd(false);
					if (rdo9451.isChecked())
						informe.setIde(true);
					else
						informe.setIde(false);
					if (rdo9461.isChecked())
						informe.setIdf(true);
					else
						informe.setIdf(false);
					if (rdo9471.isChecked())
						informe.setIdg(true);
					else
						informe.setIdg(false);
					if (rdo9481.isChecked())
						informe.setIdh(true);
					else
						informe.setIdh(false);
					if (rdo9491.isChecked())
						informe.setIdi(true);
					else
						informe.setIdi(false);
					if (rdo94101.isChecked())
						informe.setIdj(true);
					else
						informe.setIdj(false);
					Date fecha9411 = dtb9411.getValue();
					Timestamp fe9411 = new Timestamp(fecha9411.getTime());
					informe.setIdaa(fe9411);
					informe.setIdab(txt9412.getValue());
					if (rdo94131.isChecked())
						informe.setIdac(true);
					else
						informe.setIdac(false);
					informe.setIdad(txt9414.getValue());
					servicioInforme.guardar(informe);

					// Guardar
					// planes---------------------------------------------------------------------------

					informe = new Informe();
					if (idInforme != 0)
						informe = servicioInforme.buscar(idInforme);
					else
						informe = servicioInforme.buscarUltimo();
					if (ltbPlan.getItemCount() != 0) {
						guardarPlanes(informe);
					}
					if (ltbPlanVisita.getItemCount() != 0) {
						guardarPlanes2(informe);
					}
					guardarImagenes(informe);
					Mensaje.mensajeInformacion(Mensaje.guardado);
					limpiar();
					listasMultiples();
				}

			}

			@Override
			public void eliminar() {
				// TODO Auto-generated method stub

			}
		};

		botonera.getChildren().get(1).setVisible(false);
		botoneraInforme.appendChild(botonera);
	}

	public ListModelList<Condicion> getCondicionesA() {

		condicionesA = new ListModelList<>(
				servicioCondicion.buscarPorTipo("Instalaciones"));
		return condicionesA;
	}

	public ListModelList<Condicion> getCondicionesB() {

		condicionesB = new ListModelList<>(
				servicioCondicion.buscarPorTipo("Equipos"));
		return condicionesB;
	}

	public ListModelList<Condicion> getCondicionesC() {

		condicionesC = new ListModelList<>(
				servicioCondicion.buscarPorTipo("Materiales"));
		return condicionesC;
	}

	public ListModelList<Condicion> getCondicionesD() {

		condicionesD = new ListModelList<>(
				servicioCondicion.buscarPorTipo("Ambiente"));
		return condicionesD;
	}

	public ListModelList<Condicion> getCondicionesE() {

		condicionesE = new ListModelList<>(
				servicioCondicion.buscarPorTipo("Organizacion"));
		return condicionesE;
	}

	public ListModelList<Condicion> getCondicionesF() {

		condicionesF = new ListModelList<>(
				servicioCondicion.buscarPorTipo("Disergonomicos"));
		return condicionesF;
	}

	protected boolean validar() {
		if (txt1.getText().compareTo("") == 0 || lbl53.getValue() == ""
				|| lbl31.getValue() == "") {
			Mensaje.mensajeError(Mensaje.camposVacios);
			return false;
		} else {
			int n = 0;
			if (chk1.isChecked())
				n = n + 1;
			if (chk2.isChecked())
				n = n + 1;
			if (chk3.isChecked())
				n = n + 1;
			if (chk4.isChecked())
				n = n + 1;
			if (chk5.isChecked())
				n = n + 1;
			if (n > 2) {
				Mensaje.mensajeError("Solo puede Seleccionar dos (2) imagenes para el informe");
				return false;
			} else
				return true;
		}

	}

	@Listen("onClick =  #btnBuscar21,#btnBuscar22,#btnBuscar23,#btnBuscar24,#btnBuscar25,#btnBuscar26,#btnBuscar5,#btnBuscar93141,#btnBuscar93142,#btnBuscar93143,#btnBuscar93144,#btnBuscar93145,#btnBuscar93146")
	public void buscarPaciente(Event e) {
		Button boton;
		if (e.getTarget() != null) {
			boton = (Button) e.getTarget();
			idBotonPaciente = boton.getId();
		}
		final List<Paciente> pacientes = servicioPaciente.buscarTodos();
		catalogoP = new Catalogo<Paciente>(catalogoPaciente,
				"Catalogo de Pacientes", pacientes, false, "Cedula",
				"Primer Nombre", "Segundo Nombre", "Primer Apellido",
				"Segundo Apellido") {

			@Override
			protected List<Paciente> buscar(String valor, String combo) {

				switch (combo) {
				case "Primer Nombre":
					return servicioPaciente.filtroNombre1(valor);
				case "Segundo Nombre":
					return servicioPaciente.filtroNombre2(valor);
				case "Cedula":
					return servicioPaciente.filtroCedula(valor);
				case "Primer Apellido":
					return servicioPaciente.filtroApellido1(valor);
				case "Segundo Apellido":
					return servicioPaciente.filtroApellido2(valor);
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
		catalogoP.setParent(catalogoPaciente);
		catalogoP.doModal();
	}

	@Listen("onSeleccion = #catalogoPaciente")
	public void seleccionarCatalogoPaciente() {
		Paciente paciente = catalogoP.objetoSeleccionadoDelCatalogo();
		switch (idBotonPaciente) {
		case "btnBuscar21":
			setear21(paciente);
			break;
		case "btnBuscar22":
			setear22(paciente);
			break;
		case "btnBuscar23":
			setear23(paciente);
			break;
		case "btnBuscar24":
			setear24(paciente);
			break;
		case "btnBuscar25":
			setear25(paciente);
			break;
		case "btnBuscar26":
			setear26(paciente);
			break;
		case "btnBuscar5":
			setear5(paciente);
			break;
		case "btnBuscar93141":
			setear93141(paciente);
			break;
		case "btnBuscar93142":
			setear93142(paciente);
			break;
		case "btnBuscar93143":
			setear93143(paciente);
			break;
		case "btnBuscar93144":
			setear93144(paciente);
			break;
		case "btnBuscar93145":
			setear93145(paciente);
			break;
		case "btnBuscar93146":
			setear93146(paciente);
			break;
		default:
			break;
		}
		catalogoP.setParent(null);
	}

	private void setear93146(Paciente paciente) {
		lbl931461.setValue(paciente.getPrimerNombre() + " "
				+ paciente.getPrimerApellido());
		lbl931462.setValue(paciente.getCedula());
		lbl931463.setValue(paciente.getProfesion());
		if (paciente.getCargoReal() != null)
			lbl931464.setValue(paciente.getCargoReal().getNombre());
		lbl931465.setValue(paciente.getTurno());
		lbl931466.setValue(paciente.getNroInpsasel());
	}

	private void setear93145(Paciente paciente) {
		lbl931451.setValue(paciente.getPrimerNombre() + " "
				+ paciente.getPrimerApellido());
		lbl931452.setValue(paciente.getCedula());
		lbl931453.setValue(paciente.getProfesion());
		if (paciente.getCargoReal() != null)
			lbl931454.setValue(paciente.getCargoReal().getNombre());
		lbl931455.setValue(paciente.getTurno());
		lbl931456.setValue(paciente.getNroInpsasel());
	}

	private void setear93144(Paciente paciente) {
		lbl931441.setValue(paciente.getPrimerNombre() + " "
				+ paciente.getPrimerApellido());
		lbl931442.setValue(paciente.getCedula());
		lbl931443.setValue(paciente.getProfesion());
		if (paciente.getCargoReal() != null)
			lbl931444.setValue(paciente.getCargoReal().getNombre());
		lbl931445.setValue(paciente.getTurno());
		lbl931446.setValue(paciente.getNroInpsasel());
	}

	private void setear93143(Paciente paciente) {
		lbl931431.setValue(paciente.getPrimerNombre() + " "
				+ paciente.getPrimerApellido());
		lbl931432.setValue(paciente.getCedula());
		lbl931433.setValue(paciente.getProfesion());
		if (paciente.getCargoReal() != null)
			lbl931434.setValue(paciente.getCargoReal().getNombre());
		lbl931435.setValue(paciente.getTurno());
		lbl931436.setValue(paciente.getNroInpsasel());
	}

	private void setear93142(Paciente paciente) {
		lbl931421.setValue(paciente.getPrimerNombre() + " "
				+ paciente.getPrimerApellido());
		lbl931422.setValue(paciente.getCedula());
		lbl931423.setValue(paciente.getProfesion());
		if (paciente.getCargoReal() != null)
			lbl931424.setValue(paciente.getCargoReal().getNombre());
		lbl931425.setValue(paciente.getTurno());
		lbl931426.setValue(paciente.getNroInpsasel());
	}

	private void setear93141(Paciente paciente) {
		lbl931411.setValue(paciente.getPrimerNombre() + " "
				+ paciente.getPrimerApellido());
		lbl931412.setValue(paciente.getCedula());
		lbl931413.setValue(paciente.getProfesion());
		if (paciente.getCargoReal() != null)
			lbl931414.setValue(paciente.getCargoReal().getNombre());
		lbl931415.setValue(paciente.getTurno());
		lbl931416.setValue(paciente.getNroInpsasel());
	}

	private void setear5(Paciente paciente) {
		lbl51.setValue(paciente.getPrimerApellido() + " "
				+ paciente.getSegundoApellido());
		lbl52.setValue(paciente.getPrimerNombre() + " "
				+ paciente.getSegundoNombre());
		lbl53.setValue(paciente.getCedula());
		lbl54.setValue(paciente.getNacionalidad());
		lbl55.setValue(paciente.getSexo());
		if (paciente.getFechaNacimiento() != null) {
			lbl57.setValue(String.valueOf(formatoFecha.format(paciente
					.getFechaNacimiento())));
			lbl56.setValue(String.valueOf(calcularEdad(paciente
					.getFechaNacimiento())));
		}
		lbl58.setValue(paciente.getLugarNacimiento());
		if (paciente.getEstadoCivil() != null)
			lbl59.setValue(paciente.getEstadoCivil().getNombre());
		lbl510.setValue(paciente.getMano());
		lbl511.setValue(paciente.getNivelEducativo());
		if (paciente.getFechaIngreso() != null)
			lbl512.setValue(String.valueOf(formatoFecha.format(paciente
					.getFechaIngreso())));
		if (paciente.getFechaInscripcionIVSS() != null)
			lbl513.setValue(String.valueOf(formatoFecha.format(paciente
					.getFechaInscripcionIVSS())));
		lbl514.setValue(paciente.getRetiroIVSS());
		if (paciente.getFechaEgreso() != null)
			lbl515.setValue(String.valueOf(formatoFecha.format(paciente
					.getFechaEgreso())));
		lbl516.setValue(paciente.getDireccion());
		lbl517.setValue(paciente.getCiudadVivienda().getNombre());
		lbl518.setValue(paciente.getTelefono1());
		lbl519.setValue(paciente.getTelefono2());
		lbl520.setValue(paciente.getNombresEmergencia() + " "
				+ paciente.getApellidosEmergencia());
		lbl521.setValue(paciente.getTelefono1Emergencia());
		if (paciente.getCarga() != null)
			lbl522.setValue(String.valueOf(paciente.getCarga()));
	}

	private void setear26(Paciente paciente) {
		lbl261.setValue(paciente.getPrimerNombre() + " "
				+ paciente.getPrimerApellido());
		lbl262.setValue(paciente.getCedula());
		lbl264.setValue(paciente.getNroInpsasel());
	}

	private void setear25(Paciente paciente) {
		lbl251.setValue(paciente.getPrimerNombre() + " "
				+ paciente.getPrimerApellido());
		lbl252.setValue(paciente.getCedula());
		lbl254.setValue(paciente.getNroInpsasel());
	}

	private void setear24(Paciente paciente) {
		lbl241.setValue(paciente.getPrimerNombre() + " "
				+ paciente.getPrimerApellido());
		lbl242.setValue(paciente.getCedula());
		lbl244.setValue(paciente.getNroInpsasel());
	}

	private void setear23(Paciente paciente) {
		lbl231.setValue(paciente.getPrimerNombre() + " "
				+ paciente.getPrimerApellido());
		lbl232.setValue(paciente.getCedula());
		lbl234.setValue(paciente.getNroInpsasel());
	}

	private void setear22(Paciente paciente) {
		lbl221.setValue(paciente.getPrimerNombre() + " "
				+ paciente.getPrimerApellido());
		lbl222.setValue(paciente.getCedula());
		lbl224.setValue(paciente.getNroInpsasel());
	}

	private void setear21(Paciente paciente) {
		lbl211.setValue(paciente.getPrimerNombre() + " "
				+ paciente.getPrimerApellido());
		lbl212.setValue(paciente.getCedula());
		lbl214.setValue(paciente.getNroInpsasel());
	}

	@Listen("onClick =  #btnBuscar3,#btnBuscar4")
	public void buscarEmpresa(Event e) {
		Button boton;
		if (e.getTarget() != null) {
			boton = (Button) e.getTarget();
			idBotonEmpresa = boton.getId();
		}
		final List<Empresa> empresas = servicioEmpresa.buscarTodas();
		catalogoE = new Catalogo<Empresa>(catalogoEmpresa,
				"Catalogo de Empresas", empresas, false, "Rif", "Nombre",
				"Direccion") {

			@Override
			protected List<Empresa> buscar(String valor, String combo) {

				switch (combo) {
				case "Rif":
					return servicioEmpresa.filtroRif(valor);
				case "Nombre":
					return servicioEmpresa.filtroNombre(valor);
				case "Direccion":
					return servicioEmpresa.filtroDireccionCentro(valor);
				default:
					return empresas;
				}

			}

			@Override
			protected String[] crearRegistros(Empresa objeto) {
				String[] registros = new String[3];
				registros[0] = objeto.getRif();
				registros[1] = objeto.getNombre();
				registros[2] = objeto.getDireccionCentro();
				return registros;
			}

		};
		catalogoE.setParent(catalogoEmpresa);
		catalogoE.doModal();
	}

	@Listen("onSeleccion = #catalogoEmpresa")
	public void seleccionarCatalogoEmpresa() {
		Empresa empresa = catalogoE.objetoSeleccionadoDelCatalogo();
		switch (idBotonEmpresa) {
		case "btnBuscar3":
			setear3(empresa);
			break;
		case "btnBuscar4":
			setear4(empresa);
			break;
		default:
			break;
		}
		catalogoE.setParent(null);
	}

	private void setear4(Empresa empresa) {
		idEmpresa2 = empresa.getIdEmpresa();
		lbl41.setValue(empresa.getRazon());
		lbl42.setValue(empresa.getNombre());
		lbl43.setValue(empresa.getDireccionCentro());
		lbl44.setValue(empresa.getDireccionRazon());
		lbl45.setValue(empresa.getRif());
		lbl46.setValue(empresa.getNil());
		lbl47.setValue(empresa.getNroIvss());
		lbl48.setValue(empresa.getActividadEconomica());
		lbl49.setValue(empresa.getCodigoCiiu());
		lbl410.setValue(empresa.getTelefono());
		lbl411.setValue(empresa.getCorreo());
		lbl412.setValue(empresa.getRegistroMercantil());
		if (empresa.getFechaRegistro() != null)
			lbl413.setValue(String.valueOf(formatoFecha.format(empresa
					.getFechaRegistro())));
		lbl414.setValue(empresa.getBajoNroEmpresa());
		lbl415.setValue(empresa.getTomoEmpresa());
		lbl416.setValue(empresa.getRepresentanteEmpresa());
		lbl417.setValue(empresa.getCedulaRepresentante());
		lbl418.setValue(empresa.getTelefonoRepresentante());
		lbl419.setValue(empresa.getCargo());
		if (empresa.getFechaActualizacion() != null)
			lbl420.setValue(String.valueOf(formatoFecha.format(empresa
					.getFechaActualizacion())));
		lbl421.setValue(empresa.getBajoNro2Empresa());
		lbl422.setValue(empresa.getTomo2Empresa());
		lbl423.setValue(empresa.getRepresentante2Empresa());
		lbl424.setValue(empresa.getCedula2Representante());
		lbl425.setValue(empresa.getTelefono2Representante());
		lbl426.setValue(empresa.getCargo2());
		if (empresa.getNroTrabajadores() != null)
			lbl427.setValue(String.valueOf(empresa.getNroTrabajadores()));
		if (empresa.getHombres() != null)
			lbl428.setValue(String.valueOf(empresa.getHombres()));
		if (empresa.getMujeres() != null)
			lbl429.setValue(String.valueOf(empresa.getMujeres()));
		if (empresa.getAdolescentes() != null)
			lbl430.setValue(String.valueOf(empresa.getAdolescentes()));
		if (empresa.getAprendices() != null)
			lbl431.setValue(String.valueOf(empresa.getAprendices()));
		if (empresa.getLopcymat() != null)
			lbl432.setValue(String.valueOf(empresa.getLopcymat()));
		if (empresa.getConapdis() != null)
			lbl433.setValue(String.valueOf(empresa.getConapdis()));
		if (empresa.getExtranjeros() != null)
			lbl434.setValue(String.valueOf(empresa.getExtranjeros()));
	}

	private void setear3(Empresa empresa) {
		idEmpresa = empresa.getIdEmpresa();
		lbl31.setValue(empresa.getRazon());
		lbl32.setValue(empresa.getNombre());
		lbl33.setValue(empresa.getDireccionCentro());
		lbl34.setValue(empresa.getDireccionRazon());
		lbl35.setValue(empresa.getRif());
		lbl36.setValue(empresa.getNil());
		lbl37.setValue(empresa.getNroIvss());
		lbl38.setValue(empresa.getActividadEconomica());
		lbl39.setValue(empresa.getCodigoCiiu());
		lbl310.setValue(empresa.getTelefono());
		lbl311.setValue(empresa.getCorreo());
		lbl312.setValue(empresa.getRegistroMercantil());
		if (empresa.getFechaRegistro() != null)
			lbl313.setValue(String.valueOf(formatoFecha.format(empresa
					.getFechaRegistro())));
		lbl314.setValue(empresa.getBajoNroEmpresa());
		lbl315.setValue(empresa.getTomoEmpresa());
		lbl316.setValue(empresa.getRepresentanteEmpresa());
		lbl317.setValue(empresa.getCedulaRepresentante());
		lbl318.setValue(empresa.getTelefonoRepresentante());
		lbl319.setValue(empresa.getCargo());
		if (empresa.getFechaActualizacion() != null)
			lbl320.setValue(String.valueOf(formatoFecha.format(empresa
					.getFechaActualizacion())));
		lbl321.setValue(empresa.getBajoNro2Empresa());
		lbl322.setValue(empresa.getTomo2Empresa());
		lbl323.setValue(empresa.getRepresentante2Empresa());
		lbl324.setValue(empresa.getCedula2Representante());
		lbl325.setValue(empresa.getTelefono2Representante());
		lbl326.setValue(empresa.getCargo2());
		if (empresa.getNroTrabajadores() != null)
			lbl327.setValue(String.valueOf(empresa.getNroTrabajadores()));
		if (empresa.getHombres() != null)
			lbl328.setValue(String.valueOf(empresa.getHombres()));
		if (empresa.getMujeres() != null)
			lbl329.setValue(String.valueOf(empresa.getMujeres()));
		if (empresa.getAdolescentes() != null)
			lbl330.setValue(String.valueOf(empresa.getAdolescentes()));
		if (empresa.getAprendices() != null)
			lbl331.setValue(String.valueOf(empresa.getAprendices()));
		if (empresa.getLopcymat() != null)
			lbl332.setValue(String.valueOf(empresa.getLopcymat()));
		if (empresa.getConapdis() != null)
			lbl333.setValue(String.valueOf(empresa.getConapdis()));
		if (empresa.getExtranjeros() != null)
			lbl334.setValue(String.valueOf(empresa.getExtranjeros()));
	}

	@Listen("onClick = #btnSiguiente1,#btnSiguiente2,#btnSiguiente3,#btnSiguiente4,#btnSiguiente5,#btnSiguiente6,#btnSiguiente7")
	public void siguiente() {
		if (tab9.isSelected())
			tab10.setSelected(true);
		if (tab8.isSelected())
			tab9.setSelected(true);
		if (tab6y7.isSelected())
			tab8.setSelected(true);
		if (tab5.isSelected())
			tab6y7.setSelected(true);
		if (tab4.isSelected())
			tab5.setSelected(true);
		if (tab3.isSelected())
			tab4.setSelected(true);
		if (tab1y2.isSelected())
			tab3.setSelected(true);
	}

	@Listen("onClick = #btnAtras2,#btnAtras3,#btnAtras4,#btnAtras5,#btnAtras6,#btnAtras7, #btnAtras8")
	public void atras() {
		if (tab3.isSelected())
			tab1y2.setSelected(true);
		if (tab4.isSelected())
			tab3.setSelected(true);
		if (tab5.isSelected())
			tab4.setSelected(true);
		if (tab6y7.isSelected())
			tab5.setSelected(true);
		if (tab8.isSelected())
			tab6y7.setSelected(true);
		if (tab9.isSelected())
			tab8.setSelected(true);
		if (tab10.isSelected())
			tab9.setSelected(true);
	}

	private void listasMultiples() {
		for (int i = 0; i < listas.size(); i++) {
			listas.get(i).renderAll();
			listas.get(i).setMultiple(false);
			listas.get(i).setCheckmark(false);
			listas.get(i).setMultiple(true);
			listas.get(i).setCheckmark(true);
		}
	}

	@Listen("onOpen = #cmb67")
	public void llenarComboCLasificacion() {
		List<ClasificacionAccidente> clasificacion = servicioClasificacionAccidente
				.buscarTodos();
		cmb67.setModel(new ListModelList<ClasificacionAccidente>(clasificacion));
	}

	@Listen("onOpen = #cmb6713")
	public void llenarComboArea() {
		List<Area> area = servicioArea.buscarTodos();
		cmb6713.setModel(new ListModelList<Area>(area));
	}

	@Listen("onClick =  #btnBuscar1")
	public void buscarInforme(Event e) {

		final List<Informe> informes = servicioInforme.buscarTodos();
		catalogoI = new Catalogo<Informe>(catalogoInforme,
				"Catalogo de Informes", informes, false, "Fecha", "Codigo",
				"Nombre Trabajador", "Apellido Trabajador", "Empresa") {

			@Override
			protected List<Informe> buscar(String valor, String combo) {

				switch (combo) {
				case "Codigo":
					return servicioInforme.filtroCodigo(valor);
				case "Nombre Trabajador":
					return servicioInforme.filtroNombreTrabajador(valor);
				case "Apellido Trabajador":
					return servicioInforme.filtroApellidoTrabajador(valor);
				case "Empresa":
					return servicioInforme.filtroEmpresa(valor);
				case "Fecha":
					return servicioInforme.filtroFecha(valor);
				default:
					return informes;
				}

			}

			@Override
			protected String[] crearRegistros(Informe objeto) {
				String nombreEmpresa = "";
				if (objeto.getEmpresaA() != null)
					nombreEmpresa = objeto.getEmpresaA().getNombre();
				String fecha = "";
				if (objeto.getFa() != null)
					fecha = traerFecha2(objeto.getFa());
				String[] registros = new String[5];
				registros[0] = fecha;
				registros[1] = objeto.getCodigo();
				registros[2] = objeto.getPacienteA().getPrimerNombre();
				registros[3] = objeto.getPacienteA().getPrimerApellido();
				registros[4] = nombreEmpresa;
				return registros;
			}

		};
		catalogoI.setParent(catalogoInforme);
		catalogoI.doModal();
	}

	@Listen("onSeleccion = #catalogoInforme")
	public void seleccion() {
		Informe informe = catalogoI.objetoSeleccionadoDelCatalogo();
		ltbPlan.getItems().clear();
		planes = servicioPlanAccion.buscarPorInformeyTipo(informe, "normal");
		if (!planes.isEmpty()) {
			ltbPlan.setModel(new ListModelList<PlanAccion>(planes));
			ltbPlan.setCheckmark(false);
			ltbPlan.setCheckmark(true);
		}
		ltbPlanVisita.getItems().clear();
		planes2 = servicioPlanAccion
				.buscarPorInformeyTipo(informe, "inspector");
		if (!planes2.isEmpty()) {
			ltbPlanVisita.setModel(new ListModelList<PlanAccion>(planes2));
			ltbPlanVisita.setCheckmark(false);
			ltbPlanVisita.setCheckmark(true);
		}
		if (servicioPlanAccion.buscarPorActivos(informe))
			botonera.getChildren().get(0).setVisible(false);
		else
			botonera.getChildren().get(0).setVisible(true);

		setearImagenes(informe);
		txtOrdenamientos.setValue(informe.getOrdenamientos());
		txtFuncionario.setValue(informe.getFuncionario());
		if (informe.getFechaVisita() != null)
			dtbFechaVisita.setValue(informe.getFechaVisita());
		txt1.setValue(informe.getCodigo());
		setear5(informe.getPacienteA());
		if (informe.getEmpresaA() != null)
			setear3(informe.getEmpresaA());
		if (informe.getPacienteB() != null)
			setear21(informe.getPacienteB());
		if (informe.getPacienteC() != null)
			setear22(informe.getPacienteC());
		if (informe.getPacienteD() != null)
			setear23(informe.getPacienteD());
		if (informe.getPacienteE() != null)
			setear24(informe.getPacienteE());
		if (informe.getPacienteF() != null)
			setear25(informe.getPacienteF());
		if (informe.getPacienteG() != null)
			setear26(informe.getPacienteG());
		if (informe.getEmpresaB() != null)
			setear4(informe.getEmpresaB());
		idInforme = informe.getIdInforme();
		llenarCampos(informe);
		catalogoI.setParent(null);
	}

	private void llenarCampos(Informe informe) {

		// seccion 5

		if (informe.getEbc() != null) {
			Radio radio = new Radio();
			switch (informe.getEbc()) {
			case "Trabajando":
				radio = (Radio) rdg523.getChildren().get(2).getChildren()
						.get(0);
				radio.setChecked(true);
				break;
			case "Reposo":
				radio = (Radio) rdg523.getChildren().get(2).getChildren()
						.get(1);
				radio.setChecked(true);
				break;
			case "Retirado":
				radio = (Radio) rdg523.getChildren().get(2).getChildren()
						.get(2);
				radio.setChecked(true);
				break;
			case "Despedido":
				radio = (Radio) rdg523.getChildren().get(2).getChildren()
						.get(3);
				radio.setChecked(true);
				break;
			case "Reubicado":
				radio = (Radio) rdg523.getChildren().get(2).getChildren()
						.get(4);
				radio.setChecked(true);
				break;
			case "Otro (Indique)":
				radio = (Radio) rdg523.getChildren().get(2).getChildren()
						.get(5);
				radio.setChecked(true);
				txt5236.setValue(informe.getEbcf());
				break;
			default:
				break;
			}
		}

		if (informe.getEbd() != null) {
			Radio radio = new Radio();
			switch (informe.getEbd()) {
			case "Jornada Completa":
				radio = (Radio) rdg524.getChildren().get(3).getChildren()
						.get(0);
				radio.setChecked(true);
				break;
			case "Jornada Parcial":
				radio = (Radio) rdg524.getChildren().get(3).getChildren()
						.get(1);
				radio.setChecked(true);
				break;
			case "Turno Fijo Mañanas":
				radio = (Radio) rdg524.getChildren().get(3).getChildren()
						.get(2);
				radio.setChecked(true);
				break;
			case "Turno Fijo Tardes":
				radio = (Radio) rdg524.getChildren().get(3).getChildren()
						.get(3);
				radio.setChecked(true);
				break;
			case "Turno Fijo Noches":
				radio = (Radio) rdg524.getChildren().get(3).getChildren()
						.get(4);
				radio.setChecked(true);
				break;
			case "Turno Rotativo":
				radio = (Radio) rdg524.getChildren().get(3).getChildren()
						.get(5);
				radio.setChecked(true);
				break;
			case "Turno Mixto":
				radio = (Radio) rdg524.getChildren().get(3).getChildren()
						.get(6);
				radio.setChecked(true);
				break;
			default:
				break;
			}
		}

		if (informe.getEbe() != null) {
			Radio radio = new Radio();
			switch (informe.getEbe()) {
			case "Fijo Nomina":
				radio = (Radio) rdg525.getChildren().get(0).getChildren()
						.get(1);
				radio.setChecked(true);
				break;
			case "Contrato Tiempo Determinado":
				radio = (Radio) rdg525.getChildren().get(0).getChildren()
						.get(2);
				radio.setChecked(true);
				break;
			case "Contrato Obra Determinada":
				radio = (Radio) rdg525.getChildren().get(0).getChildren()
						.get(3);
				radio.setChecked(true);
				break;
			case "Contrato Destajo":
				radio = (Radio) rdg525.getChildren().get(0).getChildren()
						.get(4);
				radio.setChecked(true);
				break;
			case "Aprendiz":
				radio = (Radio) rdg525.getChildren().get(0).getChildren()
						.get(5);
				radio.setChecked(true);
				break;
			case "Ocasional":
				radio = (Radio) rdg525.getChildren().get(0).getChildren()
						.get(6);
				radio.setChecked(true);
				break;
			case "Otra (Indique)":
				radio = (Radio) rdg525.getChildren().get(0).getChildren()
						.get(7);
				radio.setChecked(true);
				txt5257.setValue(informe.getEbeg());
				break;
			default:
				break;
			}
		}

		if (informe.getEbf() != null) {
			Radio radio = new Radio();
			switch (informe.getEbf()) {
			case "Por unidad de Tiempo":
				radio = (Radio) rdg526.getChildren().get(0).getChildren()
						.get(3);
				radio.setChecked(true);
				break;
			case "Por unidad de Obra":
				radio = (Radio) rdg526.getChildren().get(0).getChildren()
						.get(5);
				radio.setChecked(true);
				break;
			case "Por pieza o a Destajo":
				radio = (Radio) rdg526.getChildren().get(0).getChildren()
						.get(7);
				radio.setChecked(true);
				break;
			case "Por Tarea":
				radio = (Radio) rdg526.getChildren().get(0).getChildren()
						.get(9);
				radio.setChecked(true);
				break;
			case "Por Productividad":
				radio = (Radio) rdg526.getChildren().get(0).getChildren()
						.get(11);
				radio.setChecked(true);
				break;
			default:
				break;
			}
		}

		if (informe.getEbg() != null) {
			Radio radio = new Radio();
			switch (informe.getEbg()) {
			case "Diario":
				radio = (Radio) rdg527.getChildren().get(0).getChildren()
						.get(2);
				radio.setChecked(true);
				break;
			case "Semanal":
				radio = (Radio) rdg527.getChildren().get(0).getChildren()
						.get(4);
				radio.setChecked(true);
				break;
			case "Quincenal":
				radio = (Radio) rdg527.getChildren().get(0).getChildren()
						.get(6);
				radio.setChecked(true);
				break;
			case "Mensual":
				radio = (Radio) rdg527.getChildren().get(0).getChildren()
						.get(8);
				radio.setChecked(true);
				break;
			case "Otro (Indique)":
				radio = (Radio) rdg527.getChildren().get(0).getChildren()
						.get(10);
				radio.setChecked(true);
				txt5275.setValue(informe.getEbge());
				break;
			default:
				break;
			}
		}

		// seccion 6 y 7
		if (informe.getFa() != null)
			dtb61.setValue(informe.getFa());
		cmb62.setValue(informe.getFb());
		if (informe.getFc() != null) {
			try {
				tmb63.setValue(df.parse(informe.getFc()));
			} catch (WrongValueException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		txt64.setValue(informe.getFd());
		txt65.setValue(informe.getFe());
		txt66.setValue(informe.getFf());
		if (informe.getClasificacion() != null)
			cmb67.setValue(informe.getClasificacion().getNombre());
		txt671.setValue(informe.getFga());
		txt672.setValue(informe.getFgb());
		txt673.setValue(informe.getFgc());
		txt674.setValue(informe.getFgd());
		txt675.setValue(informe.getFge());
		cmb676.setValue(informe.getFgf());
		if (informe.getFgga() != null)
			if (informe.getFgga())
				rdo6771.setChecked(true);
			else
				rdo6772.setChecked(true);
		if (informe.getFgha() != null)
			if (informe.getFgha()) {
				rdo6781.setChecked(true);
				txt678.setValue(informe.getFgh());
			} else
				rdo6782.setChecked(true);
		if (informe.getFgi() != null && informe.getFgi().equals("Publico"))
			rdo6791.setChecked(true);
		else {
			if (informe.getFgi() != null && informe.getFgi().equals("Privado"))
				rdo6792.setChecked(true);
		}
		txt6710.setValue(informe.getFgj());
		txt6711.setValue(informe.getFgaa());
		txt6712.setValue(informe.getFgab());
		if (informe.getArea() != null)
			cmb6713.setValue(informe.getArea().getNombre());
		txt6714.setValue(informe.getFgad());
		txt711.setValue(informe.getGaa());

		// Seccion 8

		txt811.setValue(informe.getHaa());
		txt812.setValue(informe.getHab());
		txt813.setValue(informe.getHac());
		if (informe.getHada() != null)
			if (informe.getHada())
				rdo8141.setChecked(true);
			else
				rdo8142.setChecked(true);
		if (informe.getHaea() != null
				&& informe.getHaea()
						.equals("Porque la Habitual Estaba Agotada"))
			rdo8151.setChecked(true);
		else {
			rdo8152.setChecked(true);
			txt815.setValue(informe.getHae());
		}
		txt816.setValue(informe.getHaf());
		txt821.setValue(informe.getHba());
		// lista822
		txt823.setValue(informe.getHbc());
		if (informe.getHbd() != null)
			if (informe.getHbd()) {
				rdo8241.setChecked(true);
				txt8241.setValue(informe.getHbda());
			} else
				rdo8242.setChecked(true);
		if (informe.getHbe() != null
				&& informe.getHbe().equals(
						"El Trabajador o Trabajadora Accidentado"))
			rdo8251.setChecked(true);
		else {
			if (informe.getHbe() != null
					&& informe.getHbe().equals("Otro Trabajador o Trabajadora"))
				rdo8252.setChecked(true);
			else {
				rdo8253.setChecked(true);
				txt825.setValue(informe.getHbea());
			}
		}
		if (informe.getHbf() != null)
			if (informe.getHbf())
				rdo8262.setChecked(true);
			else
				rdo8261.setChecked(true);
		if (informe.getHbg() != null)
			if (informe.getHbg())
				rdo8272.setChecked(true);
			else
				rdo8271.setChecked(true);

		if (informe.getHbh() != null
				&& informe.getHbh().equals("Inexistencia del/la  Mismo/a"))
			rdo8281.setChecked(true);
		else {
			if (informe.getHbh() != null
					&& informe.getHbh().equals(
							"Lo Estaba Utilizando otra Persona."))
				rdo8282.setChecked(true);
			else {
				if (informe.getHbh() != null
						&& informe.getHbh().equals(
								"Deteriorado/a o en mal Estado"))
					rdo8283.setChecked(true);
				else {
					if (informe.getHbh() != null
							&& informe.getHbh().equals("Otros"))
						rdo8284.setChecked(true);
				}
			}
		}
		if (informe.getHbaaa() != null)
			if (informe.getHbaaa()) {
				rdo82112.setChecked(true);
				txt8211.setValue(informe.getHbaa());
			} else
				rdo82111.setChecked(true);
		if (informe.getHbaab() != null)
			if (informe.getHbaab()) {
				rdo82122.setChecked(true);
				txt8212.setValue(informe.getHbab());
			} else
				rdo82121.setChecked(true);

		txt831.setValue(informe.getHca());
		if (informe.getHcb() != null)
			if (informe.getHcb())
				rdo8322.setChecked(true);
			else
				rdo8321.setChecked(true);

		if (informe.getHcc() != null)
			if (informe.getHcc())
				rdo8332.setChecked(true);
			else
				rdo8331.setChecked(true);

		if (informe.getHcd() != null)
			if (informe.getHcd())
				rdo8341.setChecked(true);
			else
				rdo8342.setChecked(true);
		if (informe.getHce() != null
				&& informe.getHce().equals(
						"Desconocia la Forma Habitual de Realizarla"))
			rdo8351.setChecked(true);
		else {
			if (informe.getHce() != null
					&& informe
							.getHce()
							.equals("Habia Recibido Instrucciones para Realizarla de esa Manera."))
				rdo8352.setChecked(true);
			else {
				rdo8353.setChecked(true);

			}
		}
		txt835.setValue(informe.getHcea());
		if (informe.getHcf() != null
				&& informe.getHcf().equals("Era la Primera Vez"))
			rdo8361.setChecked(true);
		else {
			if (informe.getHbe() != null
					&& informe.getHbe().equals("De manera Esporadica"))
				rdo8362.setChecked(true);
			else {
				rdo8363.setChecked(true);

			}
		}
		txt836.setValue(informe.getHcfa());
		if (informe.getHcg() != null)
			if (informe.getHcg())
				rdo8371.setChecked(true);
			else
				rdo8372.setChecked(true);

		if (informe.getHch() != null && informe.getHch().equals("Escritas"))
			rdo8381.setChecked(true);
		else {
			if (informe.getHch() != null && informe.getHch().equals("Verbales"))
				rdo8382.setChecked(true);
			else {
				if (informe.getHch() != null
						&& informe.getHch().equals("Ambas"))
					rdo8383.setChecked(true);
				else {
					rdo8384.setChecked(true);

				}
			}
		}
		txt838.setValue(informe.getHcha());
		if (informe.getHci() != null
				&& informe.getHci().equals("Del Empleador"))
			rdo8391.setChecked(true);
		else {
			if (informe.getHci() != null
					&& informe.getHci().equals("Del Supervisor"))
				rdo8392.setChecked(true);
			else {
				rdo8393.setChecked(true);

			}
		}
		txt839.setValue(informe.getHcia());
		if (informe.getHcj() != null)
			if (informe.getHcj())
				rdo83101.setChecked(true);
			else
				rdo83102.setChecked(true);
		if (informe.getHcaa() != null)
			if (informe.getHcaa()) {
				rdo83111.setChecked(true);

			} else
				rdo83112.setChecked(true);
		txt8311.setValue(informe.getHcaaa());
		if (informe.getHcab() != null)
			if (informe.getHcab()) {
				rdo83122.setChecked(true);

			} else
				rdo83121.setChecked(true);
		txt8312.setValue(informe.getHcaba());
		if (informe.getHcac() != null)
			if (informe.getHcac())
				rdo83131.setChecked(true);
			else
				rdo83132.setChecked(true);
		txt8314.setValue(informe.getHcad());

		if (informe.getHcae() != null) {
			Radio radio = new Radio();
			switch (informe.getHcae()) {
			case "Al Incio de la Jornada":
				radio = (Radio) rdg8315.getChildren().get(0).getChildren()
						.get(0);
				radio.setChecked(true);
				break;
			case "En el Intermedio de la Jornada":
				radio = (Radio) rdg8315.getChildren().get(0).getChildren()
						.get(1);
				radio.setChecked(true);
				break;
			case "Al Final de la Jornada":
				radio = (Radio) rdg8315.getChildren().get(0).getChildren()
						.get(2);
				radio.setChecked(true);
				break;
			case "Durante una Pausa en el Proceso del Trabajo":
				radio = (Radio) rdg8315.getChildren().get(0).getChildren()
						.get(3);
				radio.setChecked(true);
				break;
			case "Despues de una Pausa del Proceso de Trabajo":
				radio = (Radio) rdg8315.getChildren().get(0).getChildren()
						.get(4);
				radio.setChecked(true);
				break;
			case "Durante el Tiempo de Descanso":
				radio = (Radio) rdg8315.getChildren().get(0).getChildren()
						.get(5);
				radio.setChecked(true);
				break;
			case "Horas Extras":
				radio = (Radio) rdg8315.getChildren().get(0).getChildren()
						.get(6);
				radio.setChecked(true);
				break;
			case "Doblando un  Turno":
				radio = (Radio) rdg8315.getChildren().get(0).getChildren()
						.get(7);
				radio.setChecked(true);
				break;
			case "Otros (Indique)":
				radio = (Radio) rdg8315.getChildren().get(0).getChildren()
						.get(8);
				radio.setChecked(true);
				break;
			default:
				break;
			}
		}
		txt8315.setValue(informe.getHcaea());
		txt8316.setValue(informe.getHcaf());
		if (informe.getHcaga() != null)
			if (informe.getHcaga())
				rdo831711.setChecked(true);
			else
				rdo831712.setChecked(true);

		if (informe.getHcagb() != null)
			if (informe.getHcagb())
				rdo831721.setChecked(true);
			else
				rdo831722.setChecked(true);

		if (informe.getHcagc() != null)
			if (informe.getHcagc())
				rdo831731.setChecked(true);
			else
				rdo831732.setChecked(true);

		if (informe.getHcagd() != null)
			if (informe.getHcagd())
				rdo831741.setChecked(true);
			else
				rdo831742.setChecked(true);

		if (informe.getHcage() != null)
			if (informe.getHcage())
				rdo831751.setChecked(true);
			else
				rdo831752.setChecked(true);

		if (informe.getHcagf() != null)
			if (informe.getHcagf())
				rdo831762.setChecked(true);
			else
				rdo831761.setChecked(true);
		txt83176.setValue(informe.getHcagfa());
		if (informe.getHcagg() != null)
			if (informe.getHcagg())
				rdo831771.setChecked(true);
			else
				rdo831772.setChecked(true);

		if (informe.getHcagh() != null)
			if (informe.getHcagh())
				rdo831781.setChecked(true);
			else
				rdo831782.setChecked(true);

		if (informe.getHcagi() != null)
			if (informe.getHcagi())
				rdo831791.setChecked(true);
			else
				rdo831792.setChecked(true);

		txt841.setValue(informe.getHda());
		if (informe.getHdb() != null)
			if (informe.getHdb())
				rdo8421.setChecked(true);
			else
				rdo8422.setChecked(true);

		txt842.setValue(informe.getHdba());

		if (informe.getHdc() != null
				&& informe
						.getHdc()
						.equals("Habia Recibido Instrucciones de Realizarla en Otro Lugar"))
			rdo8431.setChecked(true);
		else {
			if (informe.getHdc() != null
					&& informe.getHdc().equals("Otros (Especifique)")) {
				rdo8432.setChecked(true);
			}

		}
		txt843.setValue(informe.getHdca());
		txt844.setValue(informe.getHdd());
		txt8451.setValue(informe.getHdea());
		txt8452.setValue(informe.getHdeb());
		txt8453.setValue(informe.getHdec());
		txt8454.setValue(informe.getHded());
		txt846.setValue(informe.getHdf());

		// Seccion 9
		if (informe.getIaa() != null)
			if (informe.getIaa())
				rdo9111.setChecked(true);
			else
				rdo9112.setChecked(true);
		if (informe.getIab() != null)
			if (informe.getIab())
				rdo9121.setChecked(true);
			else
				rdo9122.setChecked(true);
		if (informe.getIac() != null)
			if (informe.getIac())
				rdo9131.setChecked(true);
			else
				rdo9132.setChecked(true);
		if (informe.getIad() != null)
			if (informe.getIad()) {
				rdo9142.setChecked(true);
			} else
				rdo9141.setChecked(true);
		txt914.setValue(informe.getIada());
		if (informe.getIae() != null)
			dtb915.setValue(informe.getIae());
		if (informe.getIaf() != null)
			if (informe.getIaf())
				rdo9161.setChecked(true);
			else
				rdo9162.setChecked(true);
		if (informe.getIag() != null)
			if (informe.getIag()) {
				rdo9172.setChecked(true);

			} else
				rdo9171.setChecked(true);
		txt917.setValue(informe.getIaga());
		if (informe.getIah() != null)
			if (informe.getIah())
				rdo9181.setChecked(true);
			else
				rdo9182.setChecked(true);
		txt919.setValue(informe.getIai());
		txt9110.setValue(informe.getIaj());
		if (informe.getIba() != null)
			if (informe.getIba())
				rdo9211.setChecked(true);
			else
				rdo9212.setChecked(true);
		if (informe.getIbb() != null)
			dtb922.setValue(informe.getIbb());
		txt923.setValue(informe.getIbc());
		if (informe.getIbd() != null)
			dtb924.setValue(informe.getIbd());
		if (informe.getIbe() != null)
			if (informe.getIbe())
				rdo9251.setChecked(true);
			else
				rdo9252.setChecked(true);
		if (informe.getIbf() != null)
			if (informe.getIbf())
				rdo9261.setChecked(true);
			else
				rdo9262.setChecked(true);
		if (informe.getIbg() != null)
			if (informe.getIbg())
				rdo9271.setChecked(true);
			else
				rdo9272.setChecked(true);
		if (informe.getIbh() != null)
			if (informe.getIbh())
				rdo9281.setChecked(true);
			else
				rdo9282.setChecked(true);
		if (informe.getIbi() != null)
			if (informe.getIbi())
				rdo9291.setChecked(true);
			else
				rdo9292.setChecked(true);
		if (informe.getIbj() != null)
			if (informe.getIbj())
				rdo92101.setChecked(true);
			else
				rdo92102.setChecked(true);
		if (informe.getIbaa() != null)
			if (informe.getIbaa())
				rdo92111.setChecked(true);
			else
				rdo92112.setChecked(true);
		txt9212.setValue(informe.getIbab());
		txt9213.setValue(informe.getIbac());
		if (informe.getIca() != null)
			if (informe.getIca())
				rdo9311.setChecked(true);
			else
				rdo9312.setChecked(true);
		if (informe.getIcb() != null)
			dtb932.setValue(informe.getIcb());
		txt933.setValue(informe.getIcc());

		if (informe.getIcd() != null && informe.getIcd().equals("Propio"))
			rdo9341.setChecked(true);
		else {
			if (informe.getIcd() != null
					&& informe.getIcd().equals("Mancomunado"))
				rdo9342.setChecked(true);
		}
		if (informe.getIce() != null)
			if (informe.getIce())
				rdo9351.setChecked(true);
			else
				rdo9352.setChecked(true);
		if (informe.getIcf() != null)
			if (informe.getIcf())
				rdo9361.setChecked(true);
			else
				rdo9362.setChecked(true);
		if (informe.getIcg() != null)
			if (informe.getIcg())
				rdo9371.setChecked(true);
			else
				rdo9372.setChecked(true);
		if (informe.getIch() != null)
			if (informe.getIch())
				rdo9381.setChecked(true);
			else
				rdo9382.setChecked(true);
		if (informe.getIci() != null)
			if (informe.getIci())
				rdo9391.setChecked(true);
			else
				rdo9392.setChecked(true);
		if (informe.getIcj() != null)
			dtb9310.setValue(informe.getIcj());
		txt9311.setValue(informe.getIcaa());

		if (informe.getIcab() != null && informe.getIcab().equals("Propio"))
			rdo93121.setChecked(true);
		else {
			if (informe.getIcab() != null
					&& informe.getIcab().equals("Mancomunado"))
				rdo93122.setChecked(true);
		}

		if (informe.getIcac() != null) {
			Radio radio = new Radio();
			switch (informe.getIcac()) {
			case "Mañana":
				radio = (Radio) rdg9313.getChildren().get(0).getChildren()
						.get(0);
				radio.setChecked(true);
				break;
			case "Tarde":
				radio = (Radio) rdg9313.getChildren().get(0).getChildren()
						.get(1);
				radio.setChecked(true);
				break;
			case "Noche":
				radio = (Radio) rdg9313.getChildren().get(0).getChildren()
						.get(2);
				radio.setChecked(true);
				break;
			case "Mixto":
				radio = (Radio) rdg9313.getChildren().get(0).getChildren()
						.get(3);
				radio.setChecked(true);
				break;
			case "Todos":
				radio = (Radio) rdg9313.getChildren().get(0).getChildren()
						.get(4);
				radio.setChecked(true);
				break;
			default:
				break;
			}
		}
		if (informe.getPacienteH() != null)
			setear93141(informe.getPacienteH());
		if (informe.getPacienteI() != null)
			setear93142(informe.getPacienteI());
		if (informe.getPacienteJ() != null)
			setear93143(informe.getPacienteJ());
		if (informe.getPacienteK() != null)
			setear93144(informe.getPacienteK());
		if (informe.getPacienteL() != null)
			setear93145(informe.getPacienteL());
		if (informe.getPacienteM() != null)
			setear93146(informe.getPacienteM());
		txt9315.setValue(informe.getIcae());

		if (informe.getIda() != null)
			if (informe.getIda())
				rdo9411.setChecked(true);
			else
				rdo9412.setChecked(true);
		if (informe.getIdb() != null)
			if (informe.getIdb())
				rdo9421.setChecked(true);
			else
				rdo9422.setChecked(true);
		if (informe.getIdc() != null)
			if (informe.getIdc())
				rdo9431.setChecked(true);
			else
				rdo9432.setChecked(true);
		if (informe.getIdd() != null)
			if (informe.getIdd())
				rdo9441.setChecked(true);
			else
				rdo9442.setChecked(true);
		if (informe.getIde() != null)
			if (informe.getIde())
				rdo9451.setChecked(true);
			else
				rdo9452.setChecked(true);
		if (informe.getIdf() != null)
			if (informe.getIdf())
				rdo9461.setChecked(true);
			else
				rdo9462.setChecked(true);
		if (informe.getIdg() != null)
			if (informe.getIdg())
				rdo9471.setChecked(true);
			else
				rdo9472.setChecked(true);
		if (informe.getIdh() != null)
			if (informe.getIdh())
				rdo9481.setChecked(true);
			else
				rdo9482.setChecked(true);
		if (informe.getIdi() != null)
			if (informe.getIdi())
				rdo9491.setChecked(true);
			else
				rdo9492.setChecked(true);
		if (informe.getIdj() != null)
			if (informe.getIdj())
				rdo94101.setChecked(true);
			else
				rdo94102.setChecked(true);
		if (informe.getIdaa() != null)
			dtb9411.setValue(informe.getIdaa());
		txt9412.setValue(informe.getIdab());
		if (informe.getIdac() != null)
			if (informe.getIdac())
				rdo94131.setChecked(true);
			else
				rdo94132.setChecked(true);
		txt9414.setValue(informe.getIdad());

		// Setear Condiciones
		List<Condicion> condicionesA = servicioCondicion.buscarPorInformeYTipo(
				informe, "Instalaciones");
		List<Condicion> condicionesB = servicioCondicion
				.buscarPorInformeBYTipo(informe, "Equipos");
		List<Condicion> condicionesC = servicioCondicion
				.buscarPorInformeCYTipo(informe, "Materiales");
		List<Condicion> condicionesD = servicioCondicion
				.buscarPorInformeDYTipo(informe, "Ambiente");
		List<Condicion> condicionesE = servicioCondicion
				.buscarPorInformeEYTipo(informe, "Organizacion");
		List<Condicion> condicionesF = servicioCondicion
				.buscarPorInformeFYTipo(informe, "Disergonomicos");

		if (!condicionesA.isEmpty()) {
			for (int i = 0; i < condicionesA.size(); i++) {
				long id = condicionesA.get(i).getIdCondicion();
				for (int j = 0; j < ltb822.getItemCount(); j++) {
					Listitem listItem = ltb822.getItemAtIndex(j);
					Condicion a = listItem.getValue();
					long id2 = a.getIdCondicion();
					if (id == id2) {
						listItem.setSelected(true);
						j = ltb822.getItemCount();
					}
				}

			}
		}
		if (!condicionesB.isEmpty()) {
			for (int i = 0; i < condicionesB.size(); i++) {
				long id = condicionesB.get(i).getIdCondicion();
				for (int j = 0; j < ltb829.getItemCount(); j++) {
					Listitem listItem = ltb829.getItemAtIndex(j);
					Condicion a = listItem.getValue();
					long id2 = a.getIdCondicion();
					if (id == id2) {
						listItem.setSelected(true);
						j = ltb829.getItemCount();
					}
				}

			}
		}
		if (!condicionesC.isEmpty()) {
			for (int i = 0; i < condicionesC.size(); i++) {
				long id = condicionesC.get(i).getIdCondicion();
				for (int j = 0; j < ltb8210.getItemCount(); j++) {
					Listitem listItem = ltb8210.getItemAtIndex(j);
					Condicion a = listItem.getValue();
					long id2 = a.getIdCondicion();
					if (id == id2) {
						listItem.setSelected(true);
						j = ltb8210.getItemCount();
					}
				}
			}
		}

		if (!condicionesD.isEmpty()) {
			for (int i = 0; i < condicionesD.size(); i++) {
				long id = condicionesD.get(i).getIdCondicion();
				for (int j = 0; j < ltb8213.getItemCount(); j++) {
					Listitem listItem = ltb8213.getItemAtIndex(j);
					Condicion a = listItem.getValue();
					long id2 = a.getIdCondicion();
					if (id == id2) {
						listItem.setSelected(true);
						j = ltb8213.getItemCount();
					}
				}
			}
		}

		if (!condicionesE.isEmpty()) {
			for (int i = 0; i < condicionesE.size(); i++) {
				long id = condicionesE.get(i).getIdCondicion();
				for (int j = 0; j < ltb84.getItemCount(); j++) {
					Listitem listItem = ltb84.getItemAtIndex(j);
					Condicion a = listItem.getValue();
					long id2 = a.getIdCondicion();
					if (id == id2) {
						listItem.setSelected(true);
						j = ltb84.getItemCount();
					}
				}
			}
		}

		if (!condicionesF.isEmpty()) {
			for (int i = 0; i < condicionesF.size(); i++) {
				long id = condicionesF.get(i).getIdCondicion();
				for (int j = 0; j < ltb841.getItemCount(); j++) {
					Listitem listItem = ltb841.getItemAtIndex(j);
					Condicion a = listItem.getValue();
					long id2 = a.getIdCondicion();
					if (id == id2) {
						listItem.setSelected(true);
						j = ltb841.getItemCount();
					}
				}
			}
		}

	}

	// Cosas relacionadas al limpiar

	public void limpiarCampos() {
		Radio radio = new Radio();
		radio = (Radio) rdg523.getChildren().get(2).getChildren().get(0);
		radio.setChecked(false);
		radio = (Radio) rdg523.getChildren().get(2).getChildren().get(1);
		radio.setChecked(false);
		radio = (Radio) rdg523.getChildren().get(2).getChildren().get(2);
		radio.setChecked(false);
		radio = (Radio) rdg523.getChildren().get(2).getChildren().get(3);
		radio.setChecked(false);
		radio = (Radio) rdg523.getChildren().get(2).getChildren().get(4);
		radio.setChecked(false);
		radio = (Radio) rdg523.getChildren().get(2).getChildren().get(5);
		radio.setChecked(false);
		txt5236.setValue("");

		radio = (Radio) rdg524.getChildren().get(3).getChildren().get(0);
		radio.setChecked(false);
		radio = (Radio) rdg524.getChildren().get(3).getChildren().get(1);
		radio.setChecked(false);
		radio = (Radio) rdg524.getChildren().get(3).getChildren().get(2);
		radio.setChecked(false);
		radio = (Radio) rdg524.getChildren().get(3).getChildren().get(3);
		radio.setChecked(false);
		radio = (Radio) rdg524.getChildren().get(3).getChildren().get(4);
		radio.setChecked(false);
		radio = (Radio) rdg524.getChildren().get(3).getChildren().get(5);
		radio.setChecked(false);
		radio = (Radio) rdg524.getChildren().get(3).getChildren().get(6);
		radio.setChecked(false);

		radio = (Radio) rdg525.getChildren().get(0).getChildren().get(1);
		radio.setChecked(false);
		radio = (Radio) rdg525.getChildren().get(0).getChildren().get(2);
		radio.setChecked(false);
		radio = (Radio) rdg525.getChildren().get(0).getChildren().get(3);
		radio.setChecked(false);
		radio = (Radio) rdg525.getChildren().get(0).getChildren().get(4);
		radio.setChecked(false);
		radio = (Radio) rdg525.getChildren().get(0).getChildren().get(5);
		radio.setChecked(false);
		radio = (Radio) rdg525.getChildren().get(0).getChildren().get(6);
		radio.setChecked(false);
		radio = (Radio) rdg525.getChildren().get(0).getChildren().get(7);
		radio.setChecked(false);
		txt5257.setValue("");

		radio = (Radio) rdg526.getChildren().get(0).getChildren().get(3);
		radio.setChecked(false);
		radio = (Radio) rdg526.getChildren().get(0).getChildren().get(5);
		radio.setChecked(false);
		radio = (Radio) rdg526.getChildren().get(0).getChildren().get(7);
		radio.setChecked(false);
		radio = (Radio) rdg526.getChildren().get(0).getChildren().get(9);
		radio.setChecked(false);
		radio = (Radio) rdg526.getChildren().get(0).getChildren().get(11);
		radio.setChecked(false);

		radio = (Radio) rdg527.getChildren().get(0).getChildren().get(2);
		radio.setChecked(false);
		radio = (Radio) rdg527.getChildren().get(0).getChildren().get(4);
		radio.setChecked(false);
		radio = (Radio) rdg527.getChildren().get(0).getChildren().get(6);
		radio.setChecked(false);
		radio = (Radio) rdg527.getChildren().get(0).getChildren().get(8);
		radio.setChecked(false);
		radio = (Radio) rdg527.getChildren().get(0).getChildren().get(10);
		radio.setChecked(false);
		txt5275.setValue("");

		txtOrdenamientos.setValue("");
		txtFuncionario.setValue("");
		dtbFechaVisita.setValue(fechaHora);
		limpiar5();
		limpiar3();
		limpiar21();
		limpiar22();
		limpiar23();
		limpiar24();
		limpiar25();
		limpiar26();
		limpiar4();
		idInforme = 0;
		dtb61.setValue(fechaHora);
		cmb62.setValue("");
		txt64.setValue("");
		txt65.setValue("");
		txt66.setValue("");
		cmb67.setValue("");
		txt671.setValue("");
		txt672.setValue("");
		txt673.setValue("");
		txt674.setValue("");
		txt675.setValue("");
		cmb676.setValue("");
		rdo6771.setChecked(false);
		rdo6772.setChecked(false);
		rdo6781.setChecked(false);
		txt678.setValue("");
		rdo6782.setChecked(false);
		rdo6791.setChecked(false);
		rdo6792.setChecked(false);
		txt6710.setValue("");
		txt6711.setValue("");
		txt6712.setValue("");
		cmb6713.setValue("");
		txt6714.setValue("");
		txt711.setValue("");
		txt811.setValue("");
		txt812.setValue("");
		txt813.setValue("");
		rdo8141.setChecked(false);
		rdo8142.setChecked(false);
		rdo8151.setChecked(false);
		rdo8152.setChecked(false);
		txt815.setValue("");
		txt816.setValue("");
		txt821.setValue("");
		txt823.setValue("");
		rdo8241.setChecked(false);
		txt8241.setValue("");
		rdo8242.setChecked(false);
		rdo8251.setChecked(false);
		rdo8252.setChecked(false);
		rdo8253.setChecked(false);
		txt825.setValue("");
		rdo8262.setChecked(false);
		rdo8261.setChecked(false);
		rdo8272.setChecked(false);
		rdo8271.setChecked(false);
		rdo8281.setChecked(false);
		rdo8282.setChecked(false);
		rdo8283.setChecked(false);
		rdo8284.setChecked(false);
		rdo82112.setChecked(false);
		txt8211.setValue("");
		rdo82111.setChecked(false);
		rdo82122.setChecked(false);
		txt8212.setValue("");
		rdo82121.setChecked(false);
		txt831.setValue("");
		rdo8322.setChecked(false);
		rdo8321.setChecked(false);
		rdo8332.setChecked(false);
		rdo8331.setChecked(false);
		rdo8341.setChecked(false);
		rdo8342.setChecked(false);
		rdo8351.setChecked(false);
		rdo8352.setChecked(false);
		rdo8353.setChecked(false);
		txt835.setValue("");
		rdo8361.setChecked(false);
		rdo8362.setChecked(false);
		rdo8363.setChecked(false);
		txt836.setValue("");
		rdo8371.setChecked(false);
		rdo8372.setChecked(false);
		rdo8381.setChecked(false);
		rdo8382.setChecked(false);
		rdo8383.setChecked(false);
		rdo8384.setChecked(false);
		txt838.setValue("");
		rdo8391.setChecked(false);
		rdo8392.setChecked(false);
		rdo8393.setChecked(false);
		txt839.setValue("");
		rdo83101.setChecked(false);
		rdo83102.setChecked(false);
		rdo83111.setChecked(false);
		txt8311.setValue("");
		rdo83112.setChecked(false);
		rdo83122.setChecked(false);
		txt8312.setValue("");
		rdo83121.setChecked(false);
		rdo83131.setChecked(false);
		rdo83132.setChecked(false);
		txt8314.setValue("");

		radio = (Radio) rdg8315.getChildren().get(0).getChildren().get(0);
		radio.setChecked(false);
		radio = (Radio) rdg8315.getChildren().get(0).getChildren().get(1);
		radio.setChecked(true);
		radio = (Radio) rdg8315.getChildren().get(0).getChildren().get(2);
		radio.setChecked(false);
		radio = (Radio) rdg8315.getChildren().get(0).getChildren().get(3);
		radio.setChecked(false);
		radio = (Radio) rdg8315.getChildren().get(0).getChildren().get(4);
		radio.setChecked(false);
		radio = (Radio) rdg8315.getChildren().get(0).getChildren().get(5);
		radio.setChecked(false);
		radio = (Radio) rdg8315.getChildren().get(0).getChildren().get(6);
		radio.setChecked(false);
		radio = (Radio) rdg8315.getChildren().get(0).getChildren().get(7);
		radio.setChecked(false);
		radio = (Radio) rdg8315.getChildren().get(0).getChildren().get(8);
		radio.setChecked(false);

		txt8315.setValue("");
		txt8316.setValue("");
		rdo831711.setChecked(false);
		rdo831712.setChecked(false);
		rdo831721.setChecked(false);
		rdo831722.setChecked(false);
		rdo831731.setChecked(false);
		rdo831732.setChecked(false);
		rdo831741.setChecked(false);
		rdo831742.setChecked(false);
		rdo831751.setChecked(false);
		rdo831752.setChecked(false);
		rdo831762.setChecked(false);
		txt83176.setValue("");
		rdo831761.setChecked(false);
		rdo831771.setChecked(false);
		rdo831772.setChecked(false);
		rdo831781.setChecked(false);
		rdo831782.setChecked(false);
		rdo831791.setChecked(false);
		rdo831792.setChecked(false);
		txt841.setValue("");
		rdo8421.setChecked(false);
		rdo8422.setChecked(false);
		txt842.setValue("");
		rdo8431.setChecked(false);
		rdo8432.setChecked(false);
		txt843.setValue("");
		txt844.setValue("");
		txt8451.setValue("");
		txt8452.setValue("");
		txt8453.setValue("");
		txt8454.setValue("");
		txt846.setValue("");
		rdo9111.setChecked(false);
		rdo9112.setChecked(false);
		rdo9121.setChecked(false);
		rdo9122.setChecked(false);
		rdo9131.setChecked(false);
		rdo9132.setChecked(false);
		txt914.setValue("");
		rdo9142.setChecked(false);
		rdo9141.setChecked(false);
		dtb915.setValue(fechaHora);
		rdo9161.setChecked(false);
		rdo9162.setChecked(false);
		rdo9172.setChecked(false);
		txt917.setValue("");
		rdo9171.setChecked(false);
		rdo9181.setChecked(false);
		rdo9182.setChecked(false);
		txt919.setValue("");
		txt9110.setValue("");
		rdo9211.setChecked(false);
		rdo9212.setChecked(false);
		dtb922.setValue(fechaHora);
		txt923.setValue("");
		dtb924.setValue(fechaHora);
		rdo9251.setChecked(false);
		rdo9252.setChecked(false);
		rdo9261.setChecked(false);
		rdo9262.setChecked(false);
		rdo9271.setChecked(false);
		rdo9272.setChecked(false);
		rdo9281.setChecked(false);
		rdo9282.setChecked(false);
		rdo9291.setChecked(false);
		rdo9292.setChecked(false);
		rdo92101.setChecked(false);
		rdo92102.setChecked(false);
		rdo92111.setChecked(false);
		rdo92112.setChecked(false);
		txt9212.setValue("");
		txt9213.setValue("");
		rdo9311.setChecked(false);
		rdo9312.setChecked(false);
		dtb932.setValue(fechaHora);
		txt933.setValue("");
		rdo9341.setChecked(false);
		rdo9342.setChecked(false);
		rdo9351.setChecked(false);
		rdo9352.setChecked(false);
		rdo9361.setChecked(false);
		rdo9362.setChecked(false);
		rdo9371.setChecked(false);
		rdo9372.setChecked(false);
		rdo9381.setChecked(false);
		rdo9382.setChecked(false);
		rdo9391.setChecked(false);
		rdo9392.setChecked(false);
		dtb9310.setValue(fechaHora);
		txt9311.setValue("");
		rdo93121.setChecked(false);
		rdo93122.setChecked(false);
		Radio radio2 = new Radio();
		radio2 = (Radio) rdg9313.getChildren().get(0).getChildren().get(0);
		radio2.setChecked(false);
		radio2 = (Radio) rdg9313.getChildren().get(0).getChildren().get(1);
		radio2.setChecked(false);
		radio2 = (Radio) rdg9313.getChildren().get(0).getChildren().get(2);
		radio2.setChecked(false);
		radio2 = (Radio) rdg9313.getChildren().get(0).getChildren().get(3);
		radio2.setChecked(false);
		radio2 = (Radio) rdg9313.getChildren().get(0).getChildren().get(4);
		radio2.setChecked(false);
		limpiar93141();
		limpiar93142();
		limpiar93143();
		limpiar93144();
		limpiar93145();
		limpiar93146();
		txt9315.setValue("");
		rdo9411.setChecked(false);
		rdo9412.setChecked(false);
		rdo9421.setChecked(false);
		rdo9422.setChecked(false);
		rdo9431.setChecked(false);
		rdo9432.setChecked(false);
		rdo9441.setChecked(false);
		rdo9442.setChecked(false);
		rdo9451.setChecked(false);
		rdo9452.setChecked(false);
		rdo9461.setChecked(false);
		rdo9462.setChecked(false);
		rdo9471.setChecked(false);
		rdo9472.setChecked(false);
		rdo9481.setChecked(false);
		rdo9482.setChecked(false);
		rdo9491.setChecked(false);
		rdo9492.setChecked(false);
		rdo94101.setChecked(false);
		rdo94102.setChecked(false);
		dtb9411.setValue(fechaHora);
		txt9412.setValue("");
		rdo94131.setChecked(false);
		rdo94132.setChecked(false);
		txt9414.setValue("");

		llenarListas();

	}

	private void limpiar26() {
		lbl261.setValue("");
		lbl262.setValue("");
		lbl264.setValue("");
	}

	private void limpiar25() {
		lbl251.setValue("");
		lbl252.setValue("");
		lbl254.setValue("");
	}

	private void limpiar24() {
		lbl241.setValue("");
		lbl242.setValue("");
		lbl244.setValue("");
	}

	private void limpiar23() {
		lbl231.setValue("");
		lbl232.setValue("");
		lbl234.setValue("");
	}

	private void limpiar22() {
		lbl221.setValue("");
		lbl222.setValue("");
		lbl224.setValue("");
	}

	private void limpiar21() {
		lbl211.setValue("");
		lbl212.setValue("");
		lbl214.setValue("");
	}

	private void limpiar5() {
		lbl51.setValue("");
		lbl52.setValue("");
		lbl53.setValue("");
		lbl54.setValue("");
		lbl55.setValue("");
		lbl56.setValue("");
		lbl57.setValue("");
		lbl58.setValue("");
		lbl59.setValue("");
		lbl510.setValue("");
		lbl511.setValue("");
		lbl512.setValue("");
		lbl513.setValue("");
		lbl515.setValue("");
		lbl516.setValue("");
		lbl517.setValue("");
		lbl518.setValue("");
		lbl519.setValue("");
		lbl520.setValue("");
		lbl521.setValue("");
		lbl522.setValue("");
	}

	private void limpiar4() {
		idEmpresa2 = 0;
		lbl41.setValue("");
		lbl42.setValue("");
		lbl43.setValue("");
		lbl44.setValue("");
		lbl45.setValue("");
		lbl46.setValue("");
		lbl47.setValue("");
		lbl48.setValue("");
		lbl49.setValue("");
		lbl410.setValue("");
		lbl411.setValue("");
		lbl412.setValue("");
		lbl413.setValue("");
		lbl414.setValue("");
		lbl415.setValue("");
		lbl416.setValue("");
		lbl417.setValue("");
		lbl418.setValue("");
		lbl419.setValue("");
		lbl420.setValue("");
		lbl421.setValue("");
		lbl422.setValue("");
		lbl423.setValue("");
		lbl424.setValue("");
		lbl425.setValue("");
		lbl426.setValue("");
		lbl427.setValue("");
		lbl428.setValue("");
		lbl429.setValue("");
		lbl430.setValue("");
		lbl431.setValue("");
		lbl432.setValue("");
		lbl433.setValue("");
		lbl434.setValue("");
	}

	private void limpiar3() {
		idEmpresa = 0;
		lbl31.setValue("");
		lbl32.setValue("");
		lbl33.setValue("");
		lbl34.setValue("");
		lbl35.setValue("");
		lbl36.setValue("");
		lbl37.setValue("");
		lbl38.setValue("");
		lbl39.setValue("");
		lbl310.setValue("");
		lbl311.setValue("");
		lbl312.setValue("");
		lbl313.setValue("");
		lbl314.setValue("");
		lbl315.setValue("");
		lbl316.setValue("");
		lbl317.setValue("");
		lbl318.setValue("");
		lbl319.setValue("");
		lbl320.setValue("");
		lbl321.setValue("");
		lbl322.setValue("");
		lbl323.setValue("");
		lbl324.setValue("");
		lbl325.setValue("");
		lbl326.setValue("");
		lbl327.setValue("");
		lbl328.setValue("");
		lbl329.setValue("");
		lbl330.setValue("");
		lbl331.setValue("");
		lbl332.setValue("");
		lbl333.setValue("");
		lbl334.setValue("");
	}

	private void limpiar93146() {
		lbl931461.setValue("");
		lbl931462.setValue("");
		lbl931463.setValue("");
		lbl931464.setValue("");
		lbl931465.setValue("");
		lbl931466.setValue("");
	}

	private void limpiar93145() {
		lbl931451.setValue("");
		lbl931452.setValue("");
		lbl931453.setValue("");
		lbl931454.setValue("");
		lbl931455.setValue("");
		lbl931456.setValue("");
	}

	private void limpiar93144() {
		lbl931441.setValue("");
		lbl931442.setValue("");
		lbl931443.setValue("");
		lbl931444.setValue("");
		lbl931445.setValue("");
		lbl931446.setValue("");
	}

	private void limpiar93143() {
		lbl931431.setValue("");
		lbl931432.setValue("");
		lbl931433.setValue("");
		lbl931434.setValue("");
		lbl931435.setValue("");
		lbl931436.setValue("");
	}

	private void limpiar93142() {
		lbl931421.setValue("");
		lbl931422.setValue("");
		lbl931423.setValue("");
		lbl931424.setValue("");
		lbl931425.setValue("");
		lbl931426.setValue("");
	}

	private void limpiar93141() {
		lbl931411.setValue("");
		lbl931412.setValue("");
		lbl931413.setValue("");
		lbl931414.setValue("");
		lbl931415.setValue("");
		lbl931416.setValue("");
	}

	public void llenarListas() {
		ltb822.setModel(getCondicionesA());
		ltb829.setModel(getCondicionesB());
		ltb8210.setModel(getCondicionesC());
		ltb8213.setModel(getCondicionesD());
		ltb84.setModel(getCondicionesE());
		ltb841.setModel(getCondicionesF());
		listasMultiples();
	}

	@Listen("onClick =#btnReporte")
	public void reporte() {

		if (idInforme != 0) {
			Clients.evalJavaScript("window.open('"
					+ damePath()
					+ "Reportero?valor=33&valor6="
					+ String.valueOf(idInforme)
					+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
		} else
			Mensaje.mensajeAlerta(Mensaje.seleccionarInforme);
	}

	public byte[] reporteInpsasel(String par6) throws JRException {

		byte[] fichero = null;
		Informe informe = getServicioInforme().buscar(Long.parseLong(par6));

		Map<String, Object> p = new HashMap();
		p.put("codigo", informe.getCodigo());

		if (informe.getPacienteB() != null) {
			p.put("nombre211", informe.getPacienteB().getPrimerNombre() + " "
					+ informe.getPacienteB().getPrimerApellido());
			p.put("cedula211", informe.getPacienteB().getCedula());
			p.put("nro211", informe.getPacienteB().getNroInpsasel());
		}
		if (informe.getPacienteC() != null) {
			p.put("nombre212", informe.getPacienteC().getPrimerNombre() + " "
					+ informe.getPacienteC().getPrimerApellido());
			p.put("cedula212", informe.getPacienteC().getCedula());
			p.put("nro212", informe.getPacienteC().getNroInpsasel());
		}
		if (informe.getPacienteD() != null) {
			p.put("nombre213", informe.getPacienteD().getPrimerNombre() + " "
					+ informe.getPacienteD().getPrimerApellido());
			p.put("cedula213", informe.getPacienteD().getCedula());
			p.put("nro213", informe.getPacienteD().getNroInpsasel());
		}
		if (informe.getPacienteE() != null) {
			p.put("nombre214", informe.getPacienteE().getPrimerNombre() + " "
					+ informe.getPacienteE().getPrimerApellido());
			p.put("cedula214", informe.getPacienteE().getCedula());
			p.put("nro214", informe.getPacienteE().getNroInpsasel());
		}
		if (informe.getPacienteF() != null) {
			p.put("nombre215", informe.getPacienteF().getPrimerNombre() + " "
					+ informe.getPacienteF().getPrimerApellido());
			p.put("cedula215", informe.getPacienteF().getCedula());
			p.put("nro215", informe.getPacienteF().getNroInpsasel());
		}
		if (informe.getPacienteG() != null) {
			p.put("nombre216", informe.getPacienteG().getPrimerNombre() + " "
					+ informe.getPacienteG().getPrimerApellido());
			p.put("cedula216", informe.getPacienteG().getCedula());
			p.put("nro216", informe.getPacienteG().getNroInpsasel());
		}

		if (informe.getEmpresaA() != null) {
			p.put("razonA", informe.getEmpresaA().getRazon());
			p.put("centroTrabajoA", informe.getEmpresaA().getNombre());
			p.put("direccionCentroA", informe.getEmpresaA()
					.getDireccionCentro());
			p.put("direccionRazonA", informe.getEmpresaA().getDireccionRazon());
			p.put("rifA", informe.getEmpresaA().getRif());
			p.put("nillA", informe.getEmpresaA().getNil());
			p.put("nroIvssA", informe.getEmpresaA().getNroIvss());
			p.put("actividadEconomicaA", informe.getEmpresaA()
					.getActividadEconomica());
			p.put("codigoCiiuA", informe.getEmpresaA().getCodigoCiiu());
			p.put("telefonoFaxA", informe.getEmpresaA().getTelefono());
			p.put("correoA", informe.getEmpresaA().getCorreo());
			p.put("registroMercantilA", informe.getEmpresaA()
					.getRegistroMercantil());
			p.put("fechaRegistroA", informe.getEmpresaA().getFechaRegistro());
			p.put("nroRegistroA", informe.getEmpresaA().getBajoNroEmpresa());
			p.put("tomoRegistroA", informe.getEmpresaA().getTomoEmpresa());
			p.put("representanteA1", informe.getEmpresaA()
					.getRepresentanteEmpresa());
			p.put("cedulaRepresentanteA1", informe.getEmpresaA()
					.getCedulaRepresentante());
			p.put("telefonoRepresentanteA1", informe.getEmpresaA()
					.getTelefonoRepresentante());
			p.put("cargoRepresentanteA1", informe.getEmpresaA().getCargo());
			p.put("representanteA2", informe.getEmpresaA()
					.getRepresentante2Empresa());
			p.put("cedulaRepresentanteA2", informe.getEmpresaA()
					.getCedula2Representante());
			p.put("telefonoRepresentanteA2", informe.getEmpresaA()
					.getTelefono2Representante());
			p.put("cargoRepresentanteA2", informe.getEmpresaA().getCargo2());
			p.put("nroRegistroA1", informe.getEmpresaA().getBajoNro2Empresa());
			p.put("tomoRegistroA1", informe.getEmpresaA().getTomo2Empresa());
			p.put("fechaActualizacionA", informe.getEmpresaA()
					.getFechaActualizacion());
			p.put("nroTrabajadoresA", informe.getEmpresaA()
					.getNroTrabajadores());
			p.put("hombresA", informe.getEmpresaA().getHombres());
			p.put("mujeresA", informe.getEmpresaA().getMujeres());
			p.put("adolescentesA", informe.getEmpresaA().getAdolescentes());
			p.put("aprendicesA", informe.getEmpresaA().getAprendices());
			p.put("trabajadoresLocymatA", informe.getEmpresaA().getLopcymat());
			p.put("trabajadoresConapdisA", informe.getEmpresaA().getConapdis());
			p.put("extranjerosA", informe.getEmpresaA().getExtranjeros());

		}

		if (informe.getEmpresaB() != null) {
			p.put("razonB", informe.getEmpresaB().getRazon());
			p.put("centroTrabajoB", informe.getEmpresaB().getNombre());
			p.put("direccionCentroB", informe.getEmpresaB()
					.getDireccionCentro());
			p.put("direccionRazonB", informe.getEmpresaB().getDireccionRazon());
			p.put("rifB", informe.getEmpresaB().getRif());
			p.put("nillB", informe.getEmpresaB().getNil());
			p.put("nroIvssB", informe.getEmpresaB().getNroIvss());
			p.put("actividadEconomicaB", informe.getEmpresaB()
					.getActividadEconomica());
			p.put("codigoCiiuB", informe.getEmpresaB().getCodigoCiiu());
			p.put("telefonoFaxB", informe.getEmpresaB().getTelefono());
			p.put("correoB", informe.getEmpresaB().getCorreo());
			p.put("registroMercantilB", informe.getEmpresaB()
					.getRegistroMercantil());
			p.put("fechaRegistroB", informe.getEmpresaB().getFechaRegistro());
			p.put("nroRegistroB", informe.getEmpresaB().getBajoNroEmpresa());
			p.put("tomoRegistroB", informe.getEmpresaB().getTomoEmpresa());
			p.put("representanteB1", informe.getEmpresaB()
					.getRepresentanteEmpresa());
			p.put("cedulaRepresentanteB1", informe.getEmpresaB()
					.getCedulaRepresentante());
			p.put("telefonoRepresentanteB1", informe.getEmpresaB()
					.getTelefonoRepresentante());
			p.put("cargoRepresentanteB1", informe.getEmpresaB().getCargo());
			p.put("representanteB2", informe.getEmpresaB()
					.getRepresentante2Empresa());
			p.put("cedulaRepresentanteB2", informe.getEmpresaB()
					.getCedula2Representante());
			p.put("telefonoRepresentanteB2", informe.getEmpresaB()
					.getTelefono2Representante());
			p.put("cargoRepresentanteB2", informe.getEmpresaB().getCargo2());
			p.put("nroRegistroB1", informe.getEmpresaB().getBajoNro2Empresa());
			p.put("tomoRegistroB1", informe.getEmpresaB().getTomo2Empresa());
			p.put("nroTrabajadoresB", informe.getEmpresaB()
					.getNroTrabajadores());
			p.put("fechaActualizacionB", informe.getEmpresaB()
					.getFechaActualizacion());
			p.put("hombresB", informe.getEmpresaB().getHombres());
			p.put("mujeresB", informe.getEmpresaB().getMujeres());
			p.put("adolescentesB", informe.getEmpresaB().getAdolescentes());
			p.put("aprendicesB", informe.getEmpresaB().getAprendices());
			p.put("trabajadoresLocymatB", informe.getEmpresaB().getLopcymat());
			p.put("trabajadoresConapdisB", informe.getEmpresaB().getConapdis());
			p.put("extranjerosB", informe.getEmpresaB().getExtranjeros());
		}
		if (informe.getPacienteA() != null) {
			p.put("apellidoTrabajador", informe.getPacienteA()
					.getPrimerApellido()
					+ " "
					+ informe.getPacienteA().getSegundoApellido());
			p.put("nombreTrabajador", informe.getPacienteA().getPrimerNombre()
					+ " " + informe.getPacienteA().getSegundoNombre());
			p.put("cedulaTrabajador", informe.getPacienteA().getCedula());
			p.put("nacionalidadTrabajador", informe.getPacienteA()
					.getNacionalidad());
			p.put("sexoTrabajador", informe.getPacienteA().getSexo());
			p.put("edadTrabajador", calcularEdad(informe.getPacienteA()
					.getFechaNacimiento()));
			p.put("fechaNacTrabajador", informe.getPacienteA()
					.getFechaNacimiento());
			p.put("lugarNacTrabajador", informe.getPacienteA()
					.getLugarNacimiento());
			if (informe.getPacienteA().getEstadoCivil() != null)
				p.put("estadoCivilTrabajador", informe.getPacienteA()
						.getEstadoCivil().getNombre());

			if (informe.getPacienteA().getMano().equals("Derecho")) {
				p.put("manoTrabajador1", "x");
				p.put("manoTrabajador2", "");
			} else if (informe.getPacienteA().getMano().equals("Zurdo")) {
				p.put("manoTrabajador1", "");
				p.put("manoTrabajador2", "x");
			} else {
				p.put("manoTrabajador", informe.getPacienteA().getMano());
			}
			p.put("nivelEducativoTrabajador", informe.getPacienteA()
					.getNivelEducativo());
			if (informe.getPacienteA().getNivelEducativo() != null) {
				if (informe.getPacienteA().getNivelEducativo()
						.equals("Iletrado")) {
					p.put("nivelEducativoTrabajador1", "x");
					p.put("nivelEducativoTrabajador2", "");
					p.put("nivelEducativoTrabajador3", "");
					p.put("nivelEducativoTrabajador4", "");
					p.put("nivelEducativoTrabajador5", "");
				}
				if (informe.getPacienteA().getNivelEducativo()
						.equals("Primaria")) {
					p.put("nivelEducativoTrabajador1", "");
					p.put("nivelEducativoTrabajador2", "x");
					p.put("nivelEducativoTrabajador3", "");
					p.put("nivelEducativoTrabajador4", "");
					p.put("nivelEducativoTrabajador5", "");
				}
				if (informe.getPacienteA().getNivelEducativo()
						.equals("Secundaria")) {
					p.put("nivelEducativoTrabajador1", "");
					p.put("nivelEducativoTrabajador2", "");
					p.put("nivelEducativoTrabajador3", "x");
					p.put("nivelEducativoTrabajador4", "");
					p.put("nivelEducativoTrabajador5", "");
				}
				if (informe.getPacienteA().getNivelEducativo()
						.equals("Tecnica")) {
					p.put("nivelEducativoTrabajador1", "");
					p.put("nivelEducativoTrabajador2", "");
					p.put("nivelEducativoTrabajador3", "");
					p.put("nivelEducativoTrabajador4", "x");
					p.put("nivelEducativoTrabajador5", "");
					;
				}
				if (informe.getPacienteA().getNivelEducativo()
						.equals("Superior")) {
					p.put("nivelEducativoTrabajador1", "");
					p.put("nivelEducativoTrabajador2", "");
					p.put("nivelEducativoTrabajador3", "");
					p.put("nivelEducativoTrabajador4", "");
					p.put("nivelEducativoTrabajador5", "x");
				}
			}
			p.put("fechaIngresoTrabajador", informe.getPacienteA()
					.getFechaIngreso());
			p.put("inscripcionIvss", informe.getPacienteA()
					.getFechaInscripcionIVSS());
			p.put("retiroIvss", informe.getPacienteA().getRetiroIVSS());
			p.put("fechaEgresoTrabajador", informe.getPacienteA()
					.getFechaEgreso());
			p.put("direccionTrabajador", informe.getPacienteA().getDireccion()
					+ "  "
					+ informe.getPacienteA().getCiudadVivienda().getNombre());
			p.put("estadoT", informe.getPacienteA().getCiudadVivienda()
					.getEstado().getNombre());
			p.put("telefonoHabT", informe.getPacienteA().getTelefono1());
			p.put("telefonoCelT", informe.getPacienteA().getTelefono2());
			p.put("familiarContactoT", informe.getPacienteA()
					.getNombresEmergencia()
					+ " "
					+ informe.getPacienteA().getApellidosEmergencia());
			p.put("telefonoContactoT", informe.getPacienteA()
					.getTelefono1Emergencia());
			p.put("cargaT", informe.getPacienteA().getCarga());
		}
		p.put("ebc", informe.getEbc());
		if (informe.getEbc() != null) {
			if (informe.getEbc().equals("Trabajando")) {
				p.put("ebc1", "x");
				p.put("ebc2", "");
				p.put("ebc3", "");
				p.put("ebc4", "");
				p.put("ebc5", "");
			} else if (informe.getEbc().equals("Reposo")) {
				p.put("ebc1", "");
				p.put("ebc2", "x");
				p.put("ebc3", "");
				p.put("ebc4", "");
				p.put("ebc5", "");
			} else if (informe.getEbc().equals("Retirado")) {
				p.put("ebc1", "");
				p.put("ebc2", "");
				p.put("ebc3", "x");
				p.put("ebc4", "");
				p.put("ebc5", "");
			} else if (informe.getEbc().equals("Despedido")) {
				p.put("ebc1", "");
				p.put("ebc2", "");
				p.put("ebc3", "");
				p.put("ebc4", "x");
				p.put("ebc5", "");
			} else if (informe.getEbc().equals("Reubicado")) {
				p.put("ebc1", "");
				p.put("ebc2", "");
				p.put("ebc3", "");
				p.put("ebc4", "");
				p.put("ebc5", "x");
			} else {
				p.put("ebc6", "x");
			}
		}
		p.put("ebd", informe.getEbd());
		if (informe.getEbd() != null) {
			if (informe.getEbd().equals("Jornada Completa")) {
				p.put("ebd1", "x");
			}
			if (informe.getEbd().equals("Jornada Parcial")) {
				p.put("ebd2", "x");
			}
			if (informe.getEbd().equals("Fijo Turno Mannanas")) {
				p.put("ebd3", "x");
			}
			if (informe.getEbd().equals("Fijo Turno Tardes")) {
				p.put("ebd4", "x");
			}
			if (informe.getEbd().equals("Fijo Turno Noches")) {
				p.put("ebd5", "x");
			}
			if (informe.getEbd().equals("Turno Rotativo")) {
				p.put("ebd6", "x");
			}
			if (informe.getEbd().equals("Turno Mixto")) {
				p.put("ebd7", "x");
			}
		}
		p.put("ebe", informe.getEbe());
		if (informe.getEbe() != null) {
			if (informe.getEbe().equals("Fijo Nomina")) {
				p.put("ebe1", "x");
			} else if (informe.getEbe().equals("Contrato Tiempo Determinado")) {
				p.put("ebe2", "x");
			} else if (informe.getEbe().equals("Contrato Obra Determinada")) {
				p.put("ebe3", "x");
			} else if (informe.getEbe().equals("Contrato Destajo")) {
				p.put("ebe4", "x");
			} else if (informe.getEbe().equals("Aprendiz")) {
				p.put("ebe5", "x");
			} else if (informe.getEbe().equals("Ocasional")) {
				p.put("ebe6", "x");
			} else {
				p.put("ebe7", "x");
			}
		}
		p.put("ebf", informe.getEbf());
		if (informe.getEbf() != null) {
			if (informe.getEbf().equals("Por unidad de Tiempo")) {
				p.put("ebf1", "x");
			}
			if (informe.getEbf().equals("Por unidad de Obra")) {
				p.put("ebf2", "x");
			}
			if (informe.getEbf().equals("Por pieza o a Destajo")) {
				p.put("ebf3", "x");
			}
			if (informe.getEbf().equals("Por Tarea")) {
				p.put("ebf4", "x");
			}
			if (informe.getEbf().equals("Por Productividad")) {
				p.put("ebf5", "x");
			}
		}
		p.put("ebg", informe.getEbg());
		if (informe.getEbg() != null) {
			if (informe.getEbg().equals("Diario")) {
				p.put("ebg1", "x");
			} else if (informe.getEbg().equals("Semanal")) {
				p.put("ebg2", "x");
			} else if (informe.getEbg().equals("Quincenal")) {
				p.put("ebg3", "x");
			} else if (informe.getEbg().equals("Mensual")) {
				p.put("ebg4", "x");
			} else {
				p.put("ebg5", "x");
			}
		}
		p.put("ebge", informe.getEbge());
		p.put("fa", informe.getFa());
		p.put("fb", informe.getFb());
		if (informe.getFb() != null) {
			if (informe.getFb().equals("Lunes")) {
				p.put("fb1", "x");
			}
			if (informe.getFb().equals("Martes")) {
				p.put("fb2", "x");
			}
			if (informe.getFb().equals("Miercoles")) {
				p.put("fb3", "x");
			}
			if (informe.getFb().equals("Jueves")) {
				p.put("fb4", "x");
			}
			if (informe.getFb().equals("Viernes")) {
				p.put("fb5", "x");
			}
			if (informe.getFb().equals("Sabado")) {
				p.put("fb6", "x");
			}
			if (informe.getFb().equals("Domingo")) {
				p.put("fb7", "x");
			}
		}
		p.put("fc", informe.getFc());
		p.put("fd", informe.getFd());
		p.put("fe", informe.getFe());
		p.put("ff", informe.getFf());
		p.put("fga", informe.getFga());
		p.put("fgb", informe.getFgb());
		p.put("fgc", informe.getFgc());
		p.put("fgd", informe.getFgd());
		p.put("fge", informe.getFge());
		p.put("fgf", informe.getFgf());
		if (informe.getFgf() != null) {
			if (informe.getFgf().equals("Leve")) {
				p.put("fgf1", "x");
				p.put("fgf2", "");
				p.put("fgf3", "");
				p.put("fgf4", "");
				p.put("fgf5", "");
			}
			if (informe.getFgf().equals("Moderado")) {
				p.put("fgf1", "");
				p.put("fgf2", "x");
				p.put("fgf3", "");
				p.put("fgf4", "");
				p.put("fgf5", "");
			}
			if (informe.getFgf().equals("Grave")) {
				p.put("fgf1", "");
				p.put("fgf2", "");
				p.put("fgf3", "x");
				p.put("fgf4", "");
				p.put("fgf5", "");
			}
			if (informe.getFgf().equals("Muy Grave")) {
				p.put("fgf1", "");
				p.put("fgf2", "");
				p.put("fgf3", "");
				p.put("fgf4", "x");
				p.put("fgf5", "");
			}
			if (informe.getFgf().equals("Mortal")) {
				p.put("fgf1", "");
				p.put("fgf2", "");
				p.put("fgf3", "");
				p.put("fgf4", "");
				p.put("fgf5", "x");
			}
			if (!informe.getFgf().equals("Mortal")
					&& !informe.getFgf().equals("Muy Grave")
					&& !informe.getFgf().equals("Grave")
					&& !informe.getFgf().equals("Moderado")
					&& !informe.getFgf().equals("Leve")) {
				p.put("fgf1", "");
				p.put("fgf2", "");
				p.put("fgf3", "");
				p.put("fgf4", "");
				p.put("fgf5", "");
			}
		} else {
			p.put("fgf1", "");
			p.put("fgf2", "");
			p.put("fgf3", "");
			p.put("fgf4", "");
			p.put("fgf5", "");
		}

		if (informe.getClasificacion() != null) {
			p.put("clasificacion", informe.getClasificacion().getNombre());
		}
		p.put("auxilioInmediato", informe.getFgga());
		if (informe.getFgga() != null) {
			if (informe.getFgga()) {
				p.put("auxilioInmediatoSI", "x");
				p.put("auxilioInmediatoNO", "");
			} else {
				p.put("auxilioInmediatoNO", "x");
				p.put("auxilioInmediatoSI", "");
			}
		}
		if (informe.getArea() != null) {
			p.put("area", informe.getArea().getNombre());
		}
		// p.put("fgh",informe.getFgh());
		p.put("fgi", informe.getFgi());
		if (informe.getFgi() != null) {
			if (informe.getFgi().equals("Publico")) {
				p.put("fgi1", "x");
				p.put("fgi2", "");
			} else {
				p.put("fgi2", "x");
				p.put("fgi1", "");
			}
		}
		p.put("fgj", informe.getFgj());
		p.put("fgaa", informe.getFgaa());
		p.put("fgab", informe.getFgab());
		p.put("fgad", informe.getFgad());
		if (informe.getArea() != null) {
			p.put("fgac", informe.getArea().getNombre());
		}
		// p.put("fgac", informe.getArea());
		// p.put("fgad", informe.getFgad());
		if (informe.getFgga() != null) {
			if (informe.getFgga())
				p.put("fgg", "SI");
			else
				p.put("fgg", "NO");
			p.put("fgh", "SI");
		}
		if (informe.getFgha() != null) {
			if (informe.getFgha())
				p.put("fghSI", "x");
			else
				p.put("fghNO", "x");
		}
		p.put("gaa", informe.getGaa());
		p.put("haa", informe.getHaa());
		p.put("hab", informe.getHab());
		p.put("hac", informe.getHac());
		if (informe.getHada() != null) {
			if (informe.getHada()) {
				p.put("hadSI", "x");
			} else {
				p.put("hadNO", "x");
			}
		}
		if (informe.getHaea() != null) {
			if (informe.getHaea().equals("Porque la Habitual Estaba Agotada")) {
				p.put("haeSI", "x");
			} else if (informe.getHaea().equals("Otros (Especifique)")) {
				p.put("haeNO", "x");
			}
		}
		if (informe.getHae() != null)
			p.put("hae", informe.getHae());
		else
			p.put("hae", "");
		p.put("haf", informe.getHaf());
		p.put("hba", informe.getHba());
		p.put("hbc", informe.getHbc());

		if (informe.getHbd() != null)
			if (informe.getHbd()) {
				p.put("hbdSI", "x");
				p.put("hbd", informe.getHbda());
			} else
				p.put("hbdNO", "x");

		if (informe.getHbe() != null) {
			if (informe.getHbe().equals(
					"El Trabajador o Trabajadora Accidentado")) {
				p.put("hbe1", "x");
			} else if (informe.getHbe().equals("Otro Trabajador o Trabajadora")) {
				p.put("hbe2", "x");
			} else if (informe.getHbe().equals("Otros (Especifique)")) {
				p.put("hbe3", "x");
				p.put("hbe", informe.getHbea());
			}
		}
		// p.put("hbf", informe.getHbf());
		if (informe.getHbf() != null) {
			if (informe.getHbf()) {
				p.put("hbfSI", "x");
			} else
				p.put("hbfNO", "x");

		}
		p.put("hbg", informe.getHbg());
		if (informe.getHbg() != null) {
			if (informe.getHbg()) {
				p.put("hbgSI", "x");
			} else
				p.put("hbgNO", "x");

		}
		p.put("hbh", informe.getHbh());
		if (informe.getHbh() != null) {
			if (informe.getHbh().equals("Inexistencia del/la  Mismo/a")) {
				p.put("hbh1", "x");
			} else if (informe.getHbh().equals(
					"Lo Estaba Utilizando otra Persona.")) {
				p.put("hbh2", "x");
			} else if (informe.getHbh().equals("Deteriorado/a o en mal Estado")) {
				p.put("hbh3", "x");
			} else if (informe.getHbh().equals("Otros")) {
				p.put("hbh4", "x");
			}

		}
		if (informe.getHbaaa() != null)
			if (informe.getHbaaa())
				p.put("hbaaSI", "x");
			else
				p.put("hbaaNO", "x");

		// p.put("hbab", informe.getHbab());
		if (informe.getHbaab() != null) {
			if (informe.getHbaab()) {
				p.put("hbabSI", "x");
			} else if (informe.getHbaab()) {
				p.put("hbabNO", "x");
			}
		}

		p.put("hca", informe.getHca());
		p.put("hcb", informe.getHcb());
		if (informe.getHcb() != null)
			if (informe.getHcb()) {
				p.put("hcbSI", "x");
			} else {
				p.put("hcbNO", "x");
			}
		p.put("hcc", informe.getHcc());

		if (informe.getHcc() != null)
			if (informe.getHcc()) {
				p.put("hccSI", "x");
			} else {
				p.put("hccNO", "x");
			}
		p.put("hcd", informe.getHcd());
		if (informe.getHcd() != null)
			if (informe.getHcd()) {
				p.put("hcdSI", "x");
			} else {
				p.put("hcdNO", "x");
			}

		p.put("hce", informe.getHcea());
		if (informe.getHce() != null) {
			if (informe.getHce().equals(
					"Desconocia la Forma Habitual de Realizarla")) {
				p.put("hce1", "x");
			} else if (informe
					.getHce()
					.equals("Habia Recibido Instrucciones para Realizarla de esa Manera.")) {
				p.put("hce2", "x");
			} else if (informe.getHce().equals("Otros (Especifique)")) {
				p.put("hce3", "x");
			}
		}
		p.put("hcf", informe.getHcfa());
		if (informe.getHcf() != null) {
			if (informe.getHcf().equals("Era la Primera Vez")) {
				p.put("hcf1", "x");
			} else if (informe.getHcf().equals("De manera Esporadica")) {
				p.put("hcf2", "x");
			} else if (informe.getHcf().equals("Frecuentemente (Especifique)")) {
				p.put("hcf3", "x");
			}
		}
		// p.put("hcg", informe.getHcg());
		if (informe.getHcg() != null) {
			if (informe.getHcg()) {
				p.put("hcgSI", "x");
			} else
				p.put("hcgNO", "x");

		}
		p.put("hch", informe.getHcha());
		if (informe.getHch() != null) {
			if (informe.getHch().equals("Escritas")) {
				p.put("hch1", "x");
			} else if (informe.getHch().equals("Verbales")) {
				p.put("hch2", "x");
			} else if (informe.getHch().equals("Ambas")) {
				p.put("hch3", "x");
			} else if (informe.getHch().equals("Otras (Especifique)")) {
				p.put("hch4", "x");
			}
		}
		p.put("hci", informe.getHcia());
		if (informe.getHci() != null) {
			if (informe.getHci().equals("Del Empleador")) {
				p.put("hci1", "x");
			} else if (informe.getHci().equals("Del Supervisor")) {
				p.put("hci2", "x");
			} else if (informe.getHci().equals("De Otros (Especifique)")) {
				p.put("hci3", "x");
			}
		}
		p.put("hcj", informe.getHcj());

		if (informe.getHcj() != null)
			if (informe.getHcj()) {
				p.put("hcjSI", "x");
			} else {
				p.put("hcjNO", "x");
			}
		p.put("hcaa", informe.getHcaaa());

		if (informe.getHcaa() != null)
			if (informe.getHcaa()) {
				p.put("hcaaSI", "x");
			} else {
				p.put("hcaaNO", "x");
			}
		p.put("hcab", informe.getHcaba());

		if (informe.getHcab() != null)
			if (informe.getHcab()) {
				p.put("hcabSI", "x");
			} else {
				p.put("hcabNO", "x");
			}
		p.put("hcac", informe.getHcac());
		if (informe.getHcac() != null)
			if (informe.getHcac()) {
				p.put("hcacSI", "x");
			} else {
				p.put("hcacNO", "x");
			}
		p.put("hcad", informe.getHcad());
		p.put("hcae", informe.getHcaea());
		if (informe.getHcae() != null) {
			if (informe.getHcae().equals("Al Inicio de la Jornada")) {
				p.put("hcae1", "x");
			} else if (informe.getHcae().equals(
					"En el Intermedio de la Jornada")) {
				p.put("hcae2", "x");
			} else if (informe.getHcae().equals("Al Final de la Jornada")) {
				p.put("hcae3", "x");
			} else if (informe.getHcae().equals(
					"Durante una Pausa en el Proceso del Trabajo")) {
				p.put("hcae4", "x");
			} else if (informe.getHcae().equals(
					"Despues de una Pausa del Proceso de Trabajo")) {
				p.put("hcae5", "x");
			} else if (informe.getHcae()
					.equals("Durante el Tiempo de Descanso")) {
				p.put("hcae6", "x");
			} else if (informe.getHcae().equals("Horas Extras")) {
				p.put("hcae7", "x");
			} else if (informe.getHcae().equals("Doblando un Turno")) {
				p.put("hcae8", "x");
			} else if (informe.getHcae().equals("Otros (Indique)")) {
				p.put("hcae9", "x");
			}
		}
		p.put("hcaf", informe.getHcaf());

		p.put("hcaga", informe.getHcaga());
		if (informe.getHcaga() != null)
			if (informe.getHcaga()) {
				p.put("hcagaSI", "x");
			} else {
				p.put("hcagaNO", "x");
			}
		p.put("hcagb", informe.getHcagb());
		if (informe.getHcagb() != null)
			if (informe.getHcagb()) {
				p.put("hcagbSI", "x");
			} else {
				p.put("hcagbNO", "x");
			}
		p.put("hcagc", informe.getHcagc());
		if (informe.getHcagc() != null)
			if (informe.getHcagc()) {
				p.put("hcagcSI", "x");
			} else {
				p.put("hcagcNO", "x");
			}
		p.put("hcagd", informe.getHcagd());
		if (informe.getHcagd() != null)
			if (informe.getHcagd()) {
				p.put("hcagdSI", "x");
			} else {
				p.put("hcagdNO", "x");
			}
		p.put("hcage", informe.getHcage());
		if (informe.getHcage() != null)
			if (informe.getHcage()) {
				p.put("hcageSI", "x");
			} else {
				p.put("hcageNO", "x");
			}
		p.put("hcagfa", informe.getHcagfa());

		if (informe.getHcagf() != null)
			if (informe.getHcagf())
				p.put("hcagfSI", "x");
			else
				p.put("hcagfNO", "x");

		p.put("hcagg", informe.getHcagg());
		if (informe.getHcagg() != null)
			if (informe.getHcagg()) {
				p.put("hcaggSI", "x");
			} else {
				p.put("hcaggNO", "x");
			}
		p.put("hcagh", informe.getHcagh());
		if (informe.getHcagh() != null)
			if (informe.getHcagh()) {
				p.put("hcaghSI", "x");
			} else {
				p.put("hcaghNO", "x");
			}
		p.put("hcagi", informe.getHcagi());
		if (informe.getHcagi() != null)
			if (informe.getHcagi()) {
				p.put("hcagiSI", "x");
			} else {
				p.put("hcagiNO", "x");
			}
		p.put("hcafa", informe.getHcafa());
		p.put("hda", informe.getHda());
		p.put("hdb", informe.getHdba());

		if (informe.getHdb() != null)
			if (informe.getHdb()) {
				p.put("hdbSI", "x");
			} else {
				p.put("hdbNO", "x");
			}
		p.put("hdc", informe.getHdca());
		if (informe.getHdc() != null) {
			if (informe.getHdc().equals(
					"Habia Recibido Instrucciones de Realizarla en Otro Lugar")) {
				p.put("hdcSI", "x");
			} else if (informe.getHdc().equals("Otros (Especifique)")) {
				p.put("hdcNO", "x");
			}
		}
		p.put("hdd", informe.getHdd());
		p.put("hdea", informe.getHdea());
		p.put("hdeb", informe.getHdeb());
		p.put("hdec", informe.getHdec());
		p.put("hded", informe.getHded());
		p.put("hdf", informe.getHdf());
		p.put("iaa", informe.getIaa());
		if (informe.getIaa() != null)
			if (informe.getIaa()) {
				p.put("iaaSI", "x");
			} else {
				p.put("iaaNO", "x");
			}
		p.put("iab", informe.getIab());
		if (informe.getIab() != null)
			if (informe.getIab()) {
				p.put("iabSI", "x");
			} else {
				p.put("iabNO", "x");
			}
		p.put("iac", informe.getIac());
		if (informe.getIac() != null)
			if (informe.getIac()) {
				p.put("iacSI", "x");
			} else {
				p.put("iacNO", "x");
			}
		p.put("iad", informe.getIada());
		if (informe.getIad() != null)
			if (informe.getIad()) {
				p.put("iadSI", "x");
			} else {
				p.put("iadNO", "x");
			}
		p.put("iae", informe.getIae());
		p.put("iaf", informe.getIaf());
		if (informe.getIaf() != null)
			if (informe.getIaf()) {
				p.put("iafSI", "x");
			} else {
				p.put("iafNO", "x");
			}
		p.put("iag", informe.getIaga());
		if (informe.getIag() != null)
			if (informe.getIag()) {
				p.put("iagSI", "x");
			} else {
				p.put("iagNO", "x");
			}
		p.put("iah", informe.getIah());
		if (informe.getIah() != null)
			if (informe.getIah()) {
				p.put("iahSI", "x");
			} else {
				p.put("iahNO", "x");
			}
		p.put("iai", informe.getIai());
		p.put("iaj", informe.getIaj());
		// aqui
		p.put("iba", informe.getIba());
		if (informe.getIba() != null)
			if (informe.getIba()) {
				p.put("ibaSI", "x");
			} else {
				p.put("ibaNO", "x");
			}
		p.put("ibb", informe.getIbb());
		p.put("ibc", informe.getIbc());
		p.put("teta", informe.getIbd());

		p.put("ibe", informe.getIbe());
		if (informe.getIbe() != null)
			if (informe.getIbe()) {
				p.put("ibeSI", "x");
			} else {
				p.put("ibeNO", "x");
			}
		p.put("ibf", informe.getIbf());
		if (informe.getIbf() != null)
			if (informe.getIbf()) {
				p.put("ibfSI", "x");
			} else {
				p.put("ibfNO", "x");
			}
		p.put("ibg", informe.getIbg());
		if (informe.getIbg() != null)
			if (informe.getIbg()) {
				p.put("ibgSI", "x");
			} else {
				p.put("ibgNO", "x");
			}
		p.put("ibh", informe.getIbh());

		if (informe.getIbh() != null)
			if (informe.getIbh()) {
				p.put("ibhSI", "x");
			} else {
				p.put("ibhNO", "x");
			}
		p.put("ibi", informe.getIbi());
		if (informe.getIbi() != null)
			if (informe.getIbi()) {
				p.put("ibiSI", "x");
			} else {
				p.put("ibiNO", "x");
			}
		p.put("ibj", informe.getIbj());
		if (informe.getIbj() != null)
			if (informe.getIbj()) {
				p.put("ibjSI", "x");
			} else {
				p.put("ibjNO", "x");
			}
		p.put("ibaa", informe.getIbaa());
		if (informe.getIbaa() != null)
			if (informe.getIbaa()) {
				p.put("ibaaSI", "x");
			} else {
				p.put("ibaaNO", "x");
			}
		p.put("ibab", informe.getIbab());
		p.put("ibac", informe.getIbac());
		if (informe.getPacienteH() != null) {
			p.put("nombrePacienteH", informe.getPacienteH().getPrimerNombre()
					+ " " + informe.getPacienteH().getPrimerApellido());
			p.put("cedulaPacienteH", informe.getPacienteH().getCedula());
			p.put("profesionPacienteH", informe.getPacienteH().getProfesion());
			if (informe.getPacienteH().getCargoReal() != null)
				p.put("cargoPacienteH", informe.getPacienteH().getCargoReal()
						.getNombre());
			p.put("turnoPacienteH", informe.getPacienteH().getTurno());
			p.put("registroPacienteH", informe.getPacienteH().getNroInpsasel());
		}
		if (informe.getPacienteI() != null) {
			p.put("nombrePacienteI", informe.getPacienteI().getPrimerNombre()
					+ " " + informe.getPacienteI().getPrimerApellido());
			p.put("cedulaPacienteI", informe.getPacienteI().getCedula());
			p.put("profesionPacienteI", informe.getPacienteI().getProfesion());
			if (informe.getPacienteI().getCargoReal() != null)
				p.put("cargoPacienteI", informe.getPacienteI().getCargoReal()
						.getNombre());
			p.put("turnoPacienteI", informe.getPacienteI().getTurno());
			p.put("registroPacienteI", informe.getPacienteI().getNroInpsasel());
		}
		if (informe.getPacienteJ() != null) {
			p.put("nombrePacienteJ", informe.getPacienteJ().getPrimerNombre()
					+ " " + informe.getPacienteJ().getPrimerApellido());
			p.put("cedulaPacienteJ", informe.getPacienteJ().getCedula());
			p.put("profesionPacienteJ", informe.getPacienteJ().getProfesion());
			if (informe.getPacienteJ().getCargoReal() != null)
				p.put("cargoPacienteJ", informe.getPacienteJ().getCargoReal()
						.getNombre());
			p.put("turnoPacienteJ", informe.getPacienteJ().getTurno());
			p.put("registroPacienteJ", informe.getPacienteJ().getNroInpsasel());
		}
		if (informe.getPacienteK() != null) {
			p.put("nombrePacienteK", informe.getPacienteK().getPrimerNombre()
					+ " " + informe.getPacienteK().getPrimerApellido());
			p.put("cedulaPacienteK", informe.getPacienteK().getCedula());
			p.put("profesionPacienteK", informe.getPacienteK().getProfesion());
			if (informe.getPacienteK().getCargoReal() != null)
				p.put("cargoPacienteK", informe.getPacienteK().getCargoReal()
						.getNombre());
			p.put("turnoPacienteK", informe.getPacienteK().getTurno());
			p.put("registroPacienteK", informe.getPacienteK().getNroInpsasel());
		}
		if (informe.getPacienteL() != null) {
			p.put("nombrePacienteL", informe.getPacienteL().getPrimerNombre()
					+ " " + informe.getPacienteL().getPrimerApellido());
			p.put("cedulaPacienteL", informe.getPacienteL().getCedula());
			p.put("profesionPacienteL", informe.getPacienteL().getProfesion());
			if (informe.getPacienteL().getCargoReal() != null)
				p.put("cargoPacienteL", informe.getPacienteL().getCargoReal()
						.getNombre());
			p.put("turnoPacienteL", informe.getPacienteL().getTurno());
			p.put("registroPacienteL", informe.getPacienteL().getNroInpsasel());
		}
		if (informe.getPacienteM() != null) {
			p.put("nombrePacienteM", informe.getPacienteM().getPrimerNombre()
					+ " " + informe.getPacienteM().getPrimerApellido());
			p.put("cedulaPacienteM", informe.getPacienteM().getCedula());
			p.put("profesionPacienteM", informe.getPacienteM().getProfesion());
			if (informe.getPacienteM().getCargoReal() != null)
				p.put("cargoPacienteM", informe.getPacienteM().getCargoReal()
						.getNombre());
			p.put("turnoPacienteM", informe.getPacienteM().getTurno());
			p.put("registroPacienteM", informe.getPacienteM().getNroInpsasel());
		}
		p.put("ica", informe.getIca());
		if (informe.getIca() != null)
			if (informe.getIca()) {
				p.put("icaSI", "x");
			} else {
				p.put("icaNO", "x");
			}
		p.put("icb", informe.getIcb());
		p.put("icc", informe.getIcc());
		p.put("icd", informe.getIcd());
		if (informe.getIcd() != null) {
			if (informe.getIcd().equals("Propio")) {
				p.put("icd1", "x");
			} else if (informe.getIcd().equals("Mancomunado")) {
				p.put("icd2", "x");
			}
		}
		p.put("ice", informe.getIce());
		if (informe.getIce() != null)
			if (informe.getIce()) {
				p.put("iceSI", "x");
			} else {
				p.put("iceNO", "x");
			}
		p.put("icf", informe.getIcf());
		if (informe.getIcf() != null)
			if (informe.getIcf()) {
				p.put("icfSI", "x");
			} else {
				p.put("icfNO", "x");
			}
		p.put("icg", informe.getIcg());
		if (informe.getIcg() != null)
			if (informe.getIcg()) {
				p.put("icgSI", "x");
			} else {
				p.put("icgNO", "x");
			}
		p.put("ich", informe.getIch());
		if (informe.getIch() != null)
			if (informe.getIch()) {
				p.put("ichSI", "x");
			} else {
				p.put("ichNO", "x");
			}
		p.put("ici", informe.getIci());
		if (informe.getIci() != null)
			if (informe.getIci()) {
				p.put("iciSI", "x");
			} else {
				p.put("iciNO", "x");
			}
		p.put("icj", informe.getIcj());
		p.put("icaa", informe.getIcaa());
		p.put("icab", informe.getIcab());
		if (informe.getIcab() != null) {
			if (informe.getIcab().equals("Propio")) {
				p.put("icab1", "x");
			} else if (informe.getIcab().equals("Mancomunado")) {
				p.put("icab2", "x");
			}
		}
		p.put("icac", informe.getIcac());
		if (informe.getIcac() != null) {
			if (informe.getIcac().equals("Mañana")) {
				p.put("icac1", "x");
			} else if (informe.getIcac().equals("Tarde")) {
				p.put("icac2", "x");
			} else if (informe.getIcac().equals("Noche")) {
				p.put("icac3", "x");
			} else if (informe.getIcac().equals("Mixto")) {
				p.put("icac4", "x");
			} else if (informe.getIcac().equals("Todos")) {
				p.put("icac5", "x");
			}
		}
		p.put("icae", informe.getIcae());
		p.put("ida", informe.getIda());
		if (informe.getIda() != null)
			if (informe.getIda()) {
				p.put("idaSI", "x");
			} else {
				p.put("idaNO", "x");
			}
		p.put("ibd", informe.getIdb());
		if (informe.getIdb() != null)
			if (informe.getIdb()) {
				p.put("idbSI", "x");
			} else {
				p.put("idbNO", "x");
			}
		p.put("idc", informe.getIdc());
		if (informe.getIdc() != null)
			if (informe.getIdc()) {
				p.put("idcSI", "x");
			} else {
				p.put("idcNO", "x");
			}
		p.put("idd", informe.getIdd());
		if (informe.getIdd() != null)
			if (informe.getIdd()) {
				p.put("iddSI", "x");
			} else {
				p.put("iddNO", "x");
			}
		p.put("ide", informe.getIde());
		if (informe.getIde() != null)
			if (informe.getIde()) {
				p.put("ideSI", "x");
			} else {
				p.put("ideNO", "x");
			}
		p.put("idf", informe.getIdf());
		if (informe.getIdf() != null)
			if (informe.getIdf()) {
				p.put("idfSI", "x");
			} else {
				p.put("idfNO", "x");
			}
		p.put("idg", informe.getIdg());
		if (informe.getIdg() != null)
			if (informe.getIdg()) {
				p.put("idgSI", "x");
			} else {
				p.put("idgNO", "x");
			}
		p.put("idh", informe.getIdh());
		if (informe.getIdh() != null)
			if (informe.getIdh()) {
				p.put("idhSI", "x");
			} else {
				p.put("idhNO", "x");
			}
		p.put("idi", informe.getIdi());
		if (informe.getIdi() != null)
			if (informe.getIdi()) {
				p.put("idiSI", "x");
			} else {
				p.put("idiNO", "x");
			}
		p.put("idj", informe.getIdj());
		if (informe.getIdj() != null)
			if (informe.getIdj()) {
				p.put("idjSI", "x");
			} else {
				p.put("idjNO", "x");
			}
		p.put("idaa", informe.getIdaa());
		p.put("idab", informe.getIdab());
		p.put("idac", informe.getIdac());
		if (informe.getIdc() != null)
			if (informe.getIdc()) {
				p.put("idacSI", "x");
			} else {
				p.put("idacNO", "x");
			}
		p.put("idad", informe.getIdad());

		List<Condicion> condicionesA = getServicioCondicion()
				.buscarPorInformeYTipo(informe, "Instalaciones");
		List<Condicion> condicionesB = getServicioCondicion()
				.buscarPorInformeBYTipo(informe, "Equipos");
		List<Condicion> condicionesC = getServicioCondicion()
				.buscarPorInformeCYTipo(informe, "Materiales");
		List<Condicion> condicionesD = getServicioCondicion()
				.buscarPorInformeDYTipo(informe, "Ambiente");
		List<Condicion> condicionesE = getServicioCondicion()
				.buscarPorInformeEYTipo(informe, "Organizacion");
		List<Condicion> condicionesF = getServicioCondicion()
				.buscarPorInformeFYTipo(informe, "Disergonomicos");
		p.put("condicionA", new JRBeanCollectionDataSource(condicionesA));
		p.put("condicionB", new JRBeanCollectionDataSource(condicionesB));
		p.put("condicionC", new JRBeanCollectionDataSource(condicionesC));
		p.put("condicionD", new JRBeanCollectionDataSource(condicionesD));
		p.put("condicionE", new JRBeanCollectionDataSource(condicionesE));
		p.put("condicionF", new JRBeanCollectionDataSource(condicionesF));

		JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass()
				.getResource("/reporte/RReporteSHA.jasper"));
		fichero = JasperRunManager.runReportToPdf(reporte, p);
		return fichero;
	}

	// / EJEMPLO DE AGREGAR
	// ********************************************************************************************************************************************************************

	// ------------------------------------------------------------------------------------------------------------------
	@Listen("onClick = #btnEditar")
	public void seleccionarPlan() {
		if (ltbPlan.getItemCount() != 0) {
			if (ltbPlan.getSelectedItems().size() == 1) {
				Listitem listItem = ltbPlan.getSelectedItem();
				boolean error = false;
				if (listItem != null) {
					PlanAccion plan = listItem.getValue();
					if (plan.getEstado() != null) {
						if (plan.getEstado().equals("Finalizado")) {
							Mensaje.mensajeAlerta("No se pueden editar Acciones que ya han sido Finalizadas");
							error = true;
						}
					}
					if (!error) {
						planes.remove(plan);
						ltbPlan.setModel(new ListModelList<PlanAccion>(planes));
						ltbPlan.setCheckmark(false);
						ltbPlan.setCheckmark(true);
						txtDonde.setValue(plan.getDonde());
						txtComo.setValue(plan.getComo());
						txtQuien.setValue(plan.getQuien());
						dtbCuando.setValue(plan.getCuando());
						txtDescripcion.setValue(plan.getDescripcion());
					}
				}
			} else
				Mensaje.mensajeError("Debe Seleccionar un Item");
		} else
			Mensaje.mensajeError("No existen Item Registrados");
	}

	@Listen("onClick = #btnRemover")
	public void removerItem() {
		if (ltbPlan.getItemCount() != 0) {
			if (ltbPlan.getSelectedItems().size() == 1) {
				Listitem listItem = ltbPlan.getSelectedItem();
				boolean error = false;
				if (listItem != null) {
					PlanAccion plan = listItem.getValue();
					if (plan.getEstado() != null) {
						if (plan.getEstado().equals("Finalizado")) {
							Mensaje.mensajeAlerta("No se pueden remover Acciones que ya han sido Finalizadas");
							error = true;
						}
					}
					if (!error) {
						planes.remove(plan);
						ltbPlan.setModel(new ListModelList<PlanAccion>(planes));
						ltbPlan.setCheckmark(false);
						ltbPlan.setCheckmark(true);
					}
				}
			} else
				Mensaje.mensajeError("Debe Seleccionar un Item");
		} else
			Mensaje.mensajeError("No existen Item Registrados");
	}

	@Listen("onClick = #btnAgregarItems")
	public void agregarPlan() {
		if (validarPlan()) {
			Timestamp fechaPlan = new Timestamp(dtbCuando.getValue().getTime());
			PlanAccion planAccion = new PlanAccion(0, null,
					txtDescripcion.getValue(), txtQuien.getValue(),
					txtComo.getValue(), txtDonde.getValue(), fechaPlan, null,
					null, null, null, null, null);

			planes.add(planAccion);
			ltbPlan.setModel(new ListModelList<PlanAccion>(planes));
			ltbPlan.setCheckmark(false);
			ltbPlan.setCheckmark(true);
			limpiarCamposPlan();
		} else
			Mensaje.mensajeError("Debe llenar todos los campos del plan especifico");
	}

	private void limpiarCamposPlan() {
		txtDonde.setValue("");
		txtComo.setValue("");
		txtDescripcion.setValue("");
		txtQuien.setValue("");
		dtbCuando.setValue(fecha);
	}

	private boolean validarPlan() {
		if (txtDescripcion.getText().compareTo("") == 0
				|| txtQuien.getText().compareTo("") == 0
				|| txtComo.getText().compareTo("") == 0
				|| txtDonde.getText().compareTo("") == 0
				|| dtbCuando.getText().compareTo("") == 0)
			return false;
		else
			return true;
	}

	protected void guardarPlanes(Informe informe) {
		List<PlanAccion> planesAccion = servicioPlanAccion
				.buscarPorInformeEstadoyTipo(informe, "Programado", "normal");
		if (!planesAccion.isEmpty())
			servicioPlanAccion.eliminarVarios(planesAccion);
		planesAccion.clear();
		planesAccion = new ArrayList<PlanAccion>();
		ltbPlan.renderAll();
		for (int i = 0; i < ltbPlan.getItemCount(); i++) {
			Listitem listItem = ltbPlan.getItemAtIndex(i);
			PlanAccion planItem = listItem.getValue();
			PlanAccion planAccion = new PlanAccion();
			boolean registro = true;
			if (planItem.getEstado() != null) {
				if (planItem.getEstado().equals("Finalizado"))
					registro = false;
			}
			if (registro) {
				planAccion = new PlanAccion(0, informe,
						planItem.getDescripcion(), planItem.getQuien(),
						planItem.getComo(), planItem.getDonde(),
						planItem.getCuando(), null, "", "Programado",
						fechaHora, horaAuditoria, nombreUsuarioSesion());
				planAccion.setTipo("normal");
				planesAccion.add(planAccion);
			}
		}
		servicioPlanAccion.guardarVarios(planesAccion);
	}

	// -------------------------------------------------------------------------------------------------------------------------
	protected void guardarPlanes2(Informe informe) {
		List<PlanAccion> planesAccion = servicioPlanAccion
				.buscarPorInformeEstadoyTipo(informe, "Programado", "inspector");
		if (!planesAccion.isEmpty())
			servicioPlanAccion.eliminarVarios(planesAccion);
		planesAccion.clear();
		planesAccion = new ArrayList<PlanAccion>();
		ltbPlanVisita.renderAll();
		for (int i = 0; i < ltbPlanVisita.getItemCount(); i++) {
			Listitem listItem = ltbPlanVisita.getItemAtIndex(i);
			PlanAccion planItem = listItem.getValue();
			PlanAccion planAccion = new PlanAccion();
			boolean registro = true;
			if (planItem.getEstado() != null) {
				if (planItem.getEstado().equals("Finalizado"))
					registro = false;
			}
			if (registro) {
				planAccion = new PlanAccion(0, informe,
						planItem.getDescripcion(), planItem.getQuien(),
						planItem.getComo(), planItem.getDonde(),
						planItem.getCuando(), null, "", "Programado",
						fechaHora, horaAuditoria, nombreUsuarioSesion());
				planAccion.setTipo("inspector");
				planesAccion.add(planAccion);
			}
		}
		servicioPlanAccion.guardarVarios(planesAccion);
	}

	@Listen("onClick = #btnEditarVisita")
	public void seleccionarPlanVisita() {
		if (ltbPlanVisita.getItemCount() != 0) {
			if (ltbPlanVisita.getSelectedItems().size() == 1) {
				Listitem listItem = ltbPlanVisita.getSelectedItem();
				boolean error = false;
				if (listItem != null) {
					PlanAccion plan = listItem.getValue();
					if (plan.getEstado() != null) {
						if (plan.getEstado().equals("Finalizado")) {
							Mensaje.mensajeAlerta("No se pueden editar Acciones que ya han sido Finalizadas");
							error = true;
						}
					}
					if (!error) {
						planes2.remove(plan);
						ltbPlanVisita.setModel(new ListModelList<PlanAccion>(
								planes2));
						ltbPlanVisita.setCheckmark(false);
						ltbPlanVisita.setCheckmark(true);
						txtDondeVisita.setValue(plan.getDonde());
						txtComoVisita.setValue(plan.getComo());
						txtQuienVisita.setValue(plan.getQuien());
						dtbCuandoVisita.setValue(plan.getCuando());
						txtDescripcionVisita.setValue(plan.getDescripcion());
					}
				}
			} else
				Mensaje.mensajeError("Debe Seleccionar un Item");
		} else
			Mensaje.mensajeError("No existen Regitros");
	}

	@Listen("onClick = #btnRemoverVisita")
	public void removerItemVisita() {
		if (ltbPlanVisita.getItemCount() != 0) {
			if (ltbPlanVisita.getSelectedItems().size() == 1) {
				Listitem listItem = ltbPlanVisita.getSelectedItem();
				boolean error = false;
				if (listItem != null) {
					PlanAccion plan = listItem.getValue();
					if (plan.getEstado() != null) {
						if (plan.getEstado().equals("Finalizado")) {
							Mensaje.mensajeAlerta("No se pueden remover Acciones que ya han sido Finalizadas");
							error = true;
						}
					}
					if (!error) {
						planes2.remove(plan);
						ltbPlanVisita.setModel(new ListModelList<PlanAccion>(
								planes2));
						ltbPlanVisita.setCheckmark(false);
						ltbPlanVisita.setCheckmark(true);
					}
				}
			} else
				Mensaje.mensajeError("Debe Seleccionar un Item");
		} else
			Mensaje.mensajeError("No existen Item Registrados");
	}

	@Listen("onClick = #btnAgregarItemsVisita")
	public void agregarPlanVisita() {
		if (validarPlan2()) {
			Timestamp fechaPlan = new Timestamp(dtbCuandoVisita.getValue()
					.getTime());
			PlanAccion planAccion = new PlanAccion(0, null,
					txtDescripcionVisita.getValue(), txtQuienVisita.getValue(),
					txtComoVisita.getValue(), txtDondeVisita.getValue(),
					fechaPlan, null, null, null, null, null, null);
			planes2.add(planAccion);
			ltbPlanVisita.setModel(new ListModelList<PlanAccion>(planes2));
			ltbPlanVisita.setCheckmark(false);
			ltbPlanVisita.setCheckmark(true);
			limpiarCamposPlan2();
		} else
			Mensaje.mensajeError("Debe llenar todos los campos del plan especifico");
	}

	private void limpiarCamposPlan2() {
		txtDondeVisita.setValue("");
		txtComoVisita.setValue("");
		txtDescripcionVisita.setValue("");
		txtQuienVisita.setValue("");
		dtbCuandoVisita.setValue(fecha);
	}

	private boolean validarPlan2() {
		if (txtDescripcionVisita.getText().compareTo("") == 0
				|| txtQuienVisita.getText().compareTo("") == 0
				|| txtComoVisita.getText().compareTo("") == 0
				|| txtDondeVisita.getText().compareTo("") == 0
				|| dtbCuandoVisita.getText().compareTo("") == 0)
			return false;
		else
			return true;
	}

	public void guardarImagenes(Informe informe) {
		byte[] imagenUsuario1 = null;
		if (media1 instanceof org.zkoss.image.Image
				&& imagen1.getContent() != null) {
			imagenUsuario1 = imagen1.getContent().getByteData();
		}
		byte[] imagenUsuario2 = null;
		if (media2 instanceof org.zkoss.image.Image
				&& imagen2.getContent() != null) {
			imagenUsuario2 = imagen2.getContent().getByteData();
		}
		byte[] imagenUsuario3 = null;
		if (media3 instanceof org.zkoss.image.Image
				&& imagen3.getContent() != null) {
			imagenUsuario3 = imagen3.getContent().getByteData();
		}
		byte[] imagenUsuario4 = null;
		if (media4 instanceof org.zkoss.image.Image
				&& imagen4.getContent() != null) {
			imagenUsuario4 = imagen4.getContent().getByteData();
		}
		byte[] imagenUsuario5 = null;
		if (media5 instanceof org.zkoss.image.Image
				&& imagen5.getContent() != null) {
			imagenUsuario5 = imagen5.getContent().getByteData();
		}
		informe.setImagenA(imagenUsuario1);
		informe.setImagenB(imagenUsuario2);
		informe.setImagenC(imagenUsuario3);
		informe.setImagenD(imagenUsuario4);
		informe.setImagenE(imagenUsuario5);
		informe.setObsImagenA(txtImagen1.getValue());
		informe.setObsImagenB(txtImagen2.getValue());
		informe.setObsImagenC(txtImagen3.getValue());
		informe.setObsImagenD(txtImagen4.getValue());
		informe.setObsImagenE(txtImagen5.getValue());
		String a = "";
		String b = "";
		if (chk1.isChecked() && a.equals(""))
			a = "A";
		if (chk2.isChecked() && a.equals(""))
			a = "B";
		if (chk3.isChecked() && a.equals(""))
			a = "C";
		if (chk4.isChecked() && a.equals(""))
			a = "D";
		if (chk5.isChecked() && a.equals(""))
			a = "E";

		if (chk2.isChecked() && b.equals("") && !a.equals("B"))
			b = "B";
		if (chk3.isChecked() && b.equals("") && !a.equals("C"))
			b = "C";
		if (chk4.isChecked() && b.equals("") && !a.equals("D"))
			b = "D";
		if (chk5.isChecked() && b.equals("") && !a.equals("E"))
			b = "E";
		informe.setSeleccionadaA(a);
		informe.setSeleccionadaB(b);
		servicioInforme.guardar(informe);
	}

	private void setearImagenes(Informe informe) {
		BufferedImage imag1;
		if (informe.getImagenA() != null) {
			try {
				imag1 = ImageIO.read(new ByteArrayInputStream(informe
						.getImagenA()));
				imagen1.setContent(imag1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		BufferedImage imag2;
		if (informe.getImagenB() != null) {
			try {
				imag2 = ImageIO.read(new ByteArrayInputStream(informe
						.getImagenB()));
				imagen2.setContent(imag2);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		BufferedImage imag3;
		if (informe.getImagenC() != null) {
			try {
				imag3 = ImageIO.read(new ByteArrayInputStream(informe
						.getImagenC()));
				imagen3.setContent(imag3);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		BufferedImage imag4;
		if (informe.getImagenD() != null) {
			try {
				imag4 = ImageIO.read(new ByteArrayInputStream(informe
						.getImagenD()));
				imagen4.setContent(imag4);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		BufferedImage imag5;
		if (informe.getImagenE() != null) {
			try {
				imag5 = ImageIO.read(new ByteArrayInputStream(informe
						.getImagenE()));
				imagen5.setContent(imag5);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		txtImagen1.setValue(informe.getObsImagenA());
		txtImagen2.setValue(informe.getObsImagenB());
		txtImagen3.setValue(informe.getObsImagenC());
		txtImagen4.setValue(informe.getObsImagenD());
		txtImagen5.setValue(informe.getObsImagenE());

		if (informe.getSeleccionadaA() != null) {
			switch (informe.getSeleccionadaA()) {
			case "A":
				chk1.setChecked(true);
				break;
			case "B":
				chk2.setChecked(true);
				break;
			case "C":
				chk3.setChecked(true);
				break;
			case "D":
				chk4.setChecked(true);
				break;
			case "E":
				chk5.setChecked(true);
				break;
			}
		}
		if (informe.getSeleccionadaB() != null) {
			switch (informe.getSeleccionadaB()) {
			case "A":
				chk1.setChecked(true);
				break;
			case "B":
				chk2.setChecked(true);
				break;
			case "C":
				chk3.setChecked(true);
				break;
			case "D":
				chk4.setChecked(true);
				break;
			case "E":
				chk5.setChecked(true);
				break;
			}
		}
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

	@Listen("onUpload = #fudImagen2")
	public void processMedia2(UploadEvent event) {
		media2 = event.getMedia();
		if (media2 != null) {
			if (media2.getContentType().equals("image/jpeg")
					|| media2.getContentType().equals("image/png")) {
				if (media2.getByteData().length >= 512000
						&& media2.getByteData().length <= 2048000) {
					imagen2.setContent((org.zkoss.image.Image) media2);
				} else {
					msj.mensajeAlerta(Mensaje.tamanioMuyGrande);
				}
			} else {
				msj.mensajeAlerta(Mensaje.noPermitido);
			}
		}
	}

	@Listen("onUpload = #fudImagen3")
	public void processMedia3(UploadEvent event) {
		media3 = event.getMedia();
		if (media3 != null) {
			if (media3.getContentType().equals("image/jpeg")
					|| media3.getContentType().equals("image/png")) {
				if (media3.getByteData().length >= 512000
						&& media3.getByteData().length <= 2048000) {
					imagen3.setContent((org.zkoss.image.Image) media3);
				} else {
					msj.mensajeAlerta(Mensaje.tamanioMuyGrande);
				}
			} else {
				msj.mensajeAlerta(Mensaje.noPermitido);
			}
		}
	}

	@Listen("onUpload = #fudImagen4")
	public void processMedia4(UploadEvent event) {
		media4 = event.getMedia();
		if (media4 != null) {
			if (media4.getContentType().equals("image/jpeg")
					|| media4.getContentType().equals("image/png")) {
				if (media4.getByteData().length >= 512000
						&& media4.getByteData().length <= 2048000) {
					imagen4.setContent((org.zkoss.image.Image) media4);
				} else {
					msj.mensajeAlerta(Mensaje.tamanioMuyGrande);
				}
			} else {
				msj.mensajeAlerta(Mensaje.noPermitido);
			}
		}
	}

	@Listen("onUpload = #fudImagen5")
	public void processMedia5(UploadEvent event) {
		media5 = event.getMedia();
		if (media5 != null) {
			if (media5.getContentType().equals("image/jpeg")
					|| media5.getContentType().equals("image/png")) {
				if (media5.getByteData().length >= 512000
						&& media5.getByteData().length <= 2048000) {
					imagen5.setContent((org.zkoss.image.Image) media5);
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

	@Listen("onClick = #btnRemover2")
	public void limpiarI2() {
		org.zkoss.image.Image imagenUsuario1 = null;
		imagen2.setContent(imagenUsuario1);
	}

	@Listen("onClick = #btnRemover3")
	public void limpiarI3() {
		org.zkoss.image.Image imagenUsuario1 = null;
		imagen3.setContent(imagenUsuario1);
	}

	@Listen("onClick = #btnRemover4")
	public void limpiarI4() {
		org.zkoss.image.Image imagenUsuario1 = null;
		imagen4.setContent(imagenUsuario1);
	}

	@Listen("onClick = #btnRemover5")
	public void limpiarI5() {
		org.zkoss.image.Image imagenUsuario1 = null;
		imagen5.setContent(imagenUsuario1);
	}
}
