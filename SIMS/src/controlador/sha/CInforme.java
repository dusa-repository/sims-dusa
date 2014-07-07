package controlador.sha;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import modelo.maestros.Empresa;
import modelo.maestros.Paciente;
import modelo.sha.Area;
import modelo.sha.ClasificacionAccidente;
import modelo.sha.Condicion;
import modelo.sha.Informe;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
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

import controlador.maestros.CGenerico;

public class CInforme extends CGenerico {

	private static final long serialVersionUID = -6378965001066569507L;
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
	private Radiogroup rdg836;
	@Wire
	private Textbox txt836;
	@Wire
	private Radio rdo8371;
	@Wire
	private Radio rdo8372;
	@Wire
	private Radiogroup rdg838;
	@Wire
	private Textbox txt838;
	@Wire
	private Radiogroup rdg839;
	@Wire
	private Textbox txt839;
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
	private Div botoneraInforme;
	Catalogo<Paciente> catalogoP;
	Catalogo<Empresa> catalogoE;
	ListModelList<Condicion> condiciones;
	String idBotonPaciente = "";
	String idBotonEmpresa = "";
	List<Listbox> listas = new ArrayList<Listbox>();
	long idInforme = 0;
	long idEmpresa = 0;
	long idEmpresa2 = 0;

	@Override
	public void inicializar() throws IOException {
		listas.add(ltb8210);
		listas.add(ltb8213);
		listas.add(ltb822);
		listas.add(ltb829);
		listas.add(ltb84);
		listas.add(ltb841);
		listasMultiples();
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divInforme, "Informe");
			}

			@Override
			public void limpiar() {
				// TODO Auto-generated method stub

			}

			@Override
			public void guardar() {
				if (validar()) {
					Informe informe = new Informe();
					informe.setIdInforme(idInforme);
					informe.setCodigo(txt1.getValue());
					Paciente pacientePrincipal = servicioPaciente
							.buscarPorCedula(lbl53.getValue());
					informe.setPacienteA(pacientePrincipal);
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
					Area area = servicioArea.buscar(Long.valueOf(cmb6713
							.getSelectedItem().getContext()));
					informe.setArea(area);
					ClasificacionAccidente clasificacion = servicioClasificacionAccidente
							.buscar(Long.valueOf(cmb67.getSelectedItem()
									.getContext()));
					informe.setClasificacion(clasificacion);
					Empresa empresa = servicioEmpresa.buscar(idEmpresa);
					informe.setEmpresaA(empresa);
					empresa = servicioEmpresa.buscar(idEmpresa2);
					informe.setEmpresaB(empresa);

					Set<Condicion> condiciones = new HashSet<Condicion>();
					for (int i = 0; i < ltb8210.getItemCount(); i++) {
						Listitem listItem = ltb8210.getItemAtIndex(i);
						if (listItem.isSelected()) {
							Condicion condicion = listItem.getValue();
							condiciones.add(condicion);
						}
					}
					informe.setCondicionA(condiciones);

					condiciones = new HashSet<Condicion>();
					for (int i = 0; i < ltb8213.getItemCount(); i++) {
						Listitem listItem = ltb8213.getItemAtIndex(i);
						if (listItem.isSelected()) {
							Condicion condicion = listItem.getValue();
							condiciones.add(condicion);
						}
					}
					informe.setCondicionB(condiciones);

					condiciones = new HashSet<Condicion>();
					for (int i = 0; i < ltb822.getItemCount(); i++) {
						Listitem listItem = ltb822.getItemAtIndex(i);
						if (listItem.isSelected()) {
							Condicion condicion = listItem.getValue();
							condiciones.add(condicion);
						}
					}
					informe.setCondicionC(condiciones);

					condiciones = new HashSet<Condicion>();
					for (int i = 0; i < ltb829.getItemCount(); i++) {
						Listitem listItem = ltb829.getItemAtIndex(i);
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
					informe.setFgh(cmb676.getValue());
					if (rdo6771.isChecked())
						informe.setFgga(true);
					else
						informe.setFgga(false);
					if (rdo6781.isChecked()) {
						informe.setFgha(true);
						informe.setFgh(txt678.getValue());
					} else
						informe.setFgha(false);
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
					informe.setHaea(rdg815.getSelectedItem().getLabel());
					informe.setHae(txt815.getValue());
					informe.setHaf(txt816.getValue());
					informe.setHba(txt821.getValue());
					informe.setHbc(txt823.getValue());

					if (rdo8242.isChecked())
						informe.setHbd(false);
					else
						informe.setHbd(true);
					informe.setHbda(txt8241.getValue());
					informe.setHbe(rdg825.getSelectedItem().getLabel());
					informe.setHbea(txt825.getValue());
					if (rdo8261.isChecked())
						informe.setHbf(false);
					else
						informe.setHbf(true);
					if (rdo8271.isChecked())
						informe.setHbg(false);
					else
						informe.setHbg(true);
					informe.setHbh(rdg828.getSelectedItem().getLabel());
					if (rdo82111.isChecked())
						informe.setHbaaa(false);
					else {
						informe.setHbaaa(true);
						informe.setHbaa(txt8211.getValue());
					}
					if (rdo82121.isChecked())
						informe.setHbaab(false);
					else {
						informe.setHbaab(true);
						informe.setHbab(txt8212.getValue());
					}

					informe.setHca(txt831.getValue());
					if (rdo8321.isChecked())
						informe.setHcb(false);
					else
						informe.setHcb(true);
					if (rdo8331.isChecked())
						informe.setHcc(false);
					else
						informe.setHcc(true);
					if (rdo8341.isChecked())
						informe.setHcd(true);
					else
						informe.setHcd(false);
					informe.setHcae(rdg835.getSelectedItem().getLabel());
					informe.setHcaea(txt835.getValue());
					informe.setHcaf(rdg836.getSelectedItem().getLabel());
					informe.setHcafa(txt836.getValue());
					if (rdo8371.isChecked())
						informe.setHcg(true);
					else
						informe.setHcg(false);
					informe.setHch(rdg838.getSelectedItem().getLabel());
					informe.setHcha(txt838.getValue());
					informe.setHci(rdg839.getSelectedItem().getLabel());
					informe.setHcia(txt839.getValue());
					if (rdo83101.isChecked())
						informe.setHcj(true);
					else
						informe.setHcj(false);
					if (rdo83111.isChecked()) {
						informe.setHcaa(true);
						informe.setHcaaa(txt8311.getValue());
					} else
						informe.setHcaa(false);
					if (rdo83121.isChecked())
						informe.setHcab(false);
					else {
						informe.setHcab(true);
						informe.setHcaba(txt8312.getValue());
					}
					if (rdo83131.isChecked())
						informe.setHcac(true);
					else
						informe.setHcac(false);

					informe.setHcad(txt8314.getValue());
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
					if (rdo831761.isChecked())
						informe.setHcagf(false);
					else {
						informe.setHcagf(true);
						informe.setHcagfa(txt83176.getValue());
					}
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
						informe.setHdba(txt842.getValue());
					}

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
					if (rdo9141.isChecked())
						informe.setIad(false);
					else {
						informe.setIad(true);
						informe.setIada(txt914.getValue());
					}
					Date fecha914 = dtb915.getValue();
					Timestamp fe914 = new Timestamp(fecha914.getTime());
					informe.setIae(fe914);
					if (rdo9161.isChecked())
						informe.setIaf(true);
					else
						informe.setIaf(false);
					if (rdo9171.isChecked())
						informe.setIag(false);
					else {
						informe.setIaga(txt917.getValue());
						informe.setIag(false);
					}
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
						informe.setIbab(txt9212.getValue());
					}
					informe.setIbac(txt9213.getValue());
					if (rdo9311.isChecked())
						informe.setIca(true);
					else
						informe.setIca(false);
					Date fecha932 = dtb932.getValue();
					Timestamp fe932 = new Timestamp(fecha932.getTime());
					informe.setIcb(fe932);
					informe.setIcc(txt933.getValue());
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
					informe.setIcab(rdg9312.getSelectedItem().getLabel());
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

	protected boolean validar() {
		// TODO Auto-generated method stub
		return false;
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
				"Catalogo de Pacientes", pacientes, "Cedula", "Nombre",
				"Apellido") {

			@Override
			protected List<Paciente> buscar(String valor, String combo) {

				switch (combo) {
				case "Nombre":
					return servicioPaciente.filtroNombre1(valor);
				case "Cedula":
					return servicioPaciente.filtroCedula(valor);
				case "Apellido":
					return servicioPaciente.filtroApellido1(valor);
					// case "Empresa":
					// return servicioPaciente.filtroEmpresa(valor);
				default:
					return pacientes;
				}
			}

			@Override
			protected String[] crearRegistros(Paciente objeto) {
				String[] registros = new String[3];
				registros[0] = objeto.getCedula();
				registros[1] = objeto.getPrimerNombre();
				registros[2] = objeto.getPrimerApellido();
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
		lbl931464.setValue(paciente.getCargoReal().getNombre());
		lbl931465.setValue(paciente.getTurno());
		lbl931466.setValue(paciente.getNroInpsasel());
	}

	private void setear93145(Paciente paciente) {
		lbl931451.setValue(paciente.getPrimerNombre() + " "
				+ paciente.getPrimerApellido());
		lbl931452.setValue(paciente.getCedula());
		lbl931453.setValue(paciente.getProfesion());
		lbl931454.setValue(paciente.getCargoReal().getNombre());
		lbl931455.setValue(paciente.getTurno());
		lbl931456.setValue(paciente.getNroInpsasel());
	}

	private void setear93144(Paciente paciente) {
		lbl931441.setValue(paciente.getPrimerNombre() + " "
				+ paciente.getPrimerApellido());
		lbl931442.setValue(paciente.getCedula());
		lbl931443.setValue(paciente.getProfesion());
		lbl931444.setValue(paciente.getCargoReal().getNombre());
		lbl931445.setValue(paciente.getTurno());
		lbl931446.setValue(paciente.getNroInpsasel());
	}

	private void setear93143(Paciente paciente) {
		lbl931431.setValue(paciente.getPrimerNombre() + " "
				+ paciente.getPrimerApellido());
		lbl931432.setValue(paciente.getCedula());
		lbl931433.setValue(paciente.getProfesion());
		lbl931434.setValue(paciente.getCargoReal().getNombre());
		lbl931435.setValue(paciente.getTurno());
		lbl931436.setValue(paciente.getNroInpsasel());
	}

	private void setear93142(Paciente paciente) {
		lbl931421.setValue(paciente.getPrimerNombre() + " "
				+ paciente.getPrimerApellido());
		lbl931422.setValue(paciente.getCedula());
		lbl931423.setValue(paciente.getProfesion());
		lbl931424.setValue(paciente.getCargoReal().getNombre());
		lbl931425.setValue(paciente.getTurno());
		lbl931426.setValue(paciente.getNroInpsasel());
	}

	private void setear93141(Paciente paciente) {
		lbl931411.setValue(paciente.getPrimerNombre() + " "
				+ paciente.getPrimerApellido());
		lbl931412.setValue(paciente.getCedula());
		lbl931413.setValue(paciente.getProfesion());
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
		if (paciente.getEdad() != null)
			lbl56.setValue(String.valueOf(paciente.getEdad()));
		if (paciente.getFechaNacimiento() != null)
			lbl57.setValue(String.valueOf(formatoFecha.format(paciente
					.getFechaNacimiento())));
		lbl58.setValue(paciente.getLugarNacimiento());
		lbl59.setValue(paciente.getEstadoCivil());
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
				"Catalogo de Empresas", empresas, "Rif", "Nombre", "Direccion") {

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
		lbl48.setValue(empresa.getCodigoCiiu());
		lbl49.setValue(empresa.getActividadEconomica());
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
		lbl38.setValue(empresa.getCodigoCiiu());
		lbl39.setValue(empresa.getActividadEconomica());
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

	public ListModelList<Condicion> getCondiciones() {
		condiciones = new ListModelList<>(servicioCondicion.buscarTodos());
		return condiciones;
	}

	@Listen("onClick = #btnSiguiente1,#btnSiguiente2,#btnSiguiente3,#btnSiguiente4,#btnSiguiente5")
	public void siguiente() {
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

	@Listen("onClick = #btnAtras2,#btnAtras3,#btnAtras4,#btnAtras5")
	public void atras() {
		if (tab3.isSelected())
			tab1y2.setSelected(true);
		if (tab4.isSelected())
			tab3.setSelected(true);
		if (tab5.isSelected())
			tab4.setSelected(true);
		if (tab6y7.isSelected())
			tab5.setSelected(true);
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
}
