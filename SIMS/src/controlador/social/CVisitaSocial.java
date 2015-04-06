package controlador.social;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import modelo.maestros.Estado;
import modelo.maestros.Paciente;
import modelo.maestros.Pais;
import modelo.sha.Informe;
import modelo.social.VisitaSocial;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.CheckEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublespinner;
import org.zkoss.zul.Label;
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
	private Label labelSuma;
	@Wire
	private Div divCatalogoVisita;

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

	// Catalogo<Paciente> catalogoT;
	Catalogo<VisitaSocial> catalogoVisita;

	// Variables
	private String nombre;
	Long idVisita = (long) 0;

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
					hogarDispone = hogarDispone + "," + check19_1.getLabel();
				if (check19_2.isChecked())
					hogarDispone = hogarDispone + "," + check19_2.getLabel();
				if (check19_3.isChecked())
					hogarDispone = hogarDispone + "," + check19_3.getLabel();
				if (check19_4.isChecked())
					hogarDispone = hogarDispone + "," + check19_4.getLabel();
				if (check19_5.isChecked())
					hogarDispone = hogarDispone + "," + check19_5.getLabel();
				if (check19_6.isChecked())
					hogarDispone = hogarDispone + "," + check19_6.getLabel();
				if (check19_7.isChecked())
					hogarDispone = hogarDispone + "," + check19_7.getLabel();
				if (check19_8.isChecked())
					hogarDispone = hogarDispone + "," + check19_8.getLabel();
				if (check19_9.isChecked())
					hogarDispone = hogarDispone + "," + check19_9.getLabel();
				if (check19_10.isChecked())
					hogarDispone = hogarDispone + "," + check19_10.getLabel();
				if (check19_11.isChecked())
					hogarDispone = hogarDispone + "," + check19_11.getLabel();
				if (check19_12.isChecked())
					hogarDispone = hogarDispone + "," + check19_12.getLabel();
				if (check19_13.isChecked())
					hogarDispone = hogarDispone + "," + check19_13.getLabel();
				if (check19_14.isChecked())
					hogarDispone = hogarDispone + "," + check19_14.getLabel();
				if (check19_15.isChecked())
					hogarDispone = hogarDispone + "," + check19_15.getLabel();
				if (check19_16.isChecked())
					hogarDispone = hogarDispone + "," + check19_16.getLabel();
				if (check19_17.isChecked())
					hogarDispone = hogarDispone + "," + check19_17.getLabel();
				if (check19_18.isChecked())
					hogarDispone = hogarDispone + "," + check19_18.getLabel();
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
				Date fechaAplicacion = dateBoxAplicacion.getValue();
				Timestamp fechaA = new Timestamp(fechaAplicacion.getTime());
				visitaSocial.setFechaAplicacion(fechaA);

				Date fechaProcesamiento = dateBoxProcesamiento.getValue();
				Timestamp fechaP = new Timestamp(fechaProcesamiento.getTime());
				visitaSocial.setFechaProcesamiento(fechaP);

				Date fechaInforme = dateBoxInforme.getValue();
				Timestamp fechaI = new Timestamp(fechaInforme.getTime());
				visitaSocial.setFechaInforme(fechaI);

				// En caso de que sea una modificacion la variable idVisita se
				// lleno con el catalogo por lo q se modificaria en la bd, si no
				// es una modificacion idVisita es 0, entonces se crea un nuevo
				// registro
				visitaSocial.setIdVisita(idVisita);
				// Guardar
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

	@Listen("onClick =  #btnBuscar2")
	public void buscarInforme(Event e) {

		final List<VisitaSocial> informes = servicioVisitaSocial.buscarTodas();
		catalogoVisita = new Catalogo<VisitaSocial>(divCatalogoVisita,
				"Catalogo de Visitas", informes, false, "Id") {

			@Override
			protected List<VisitaSocial> buscar(String valor, String combo) {

				switch (combo) {
				case "Codigo":
					return servicioVisitaSocial.filtroId(valor);
				default:
					return informes;
				}

			}

			@Override
			protected String[] crearRegistros(VisitaSocial objeto) {
				String[] registros = new String[1];
				registros[0] = String.valueOf(objeto.getIdVisita());
				return registros;
			}

		};
		catalogoVisita.setParent(divCatalogoVisita);
		catalogoVisita.doModal();
	}

	@Listen("onSeleccion = #divCatalogoVisita")
	public void seleccion() {
		VisitaSocial visitaSocial = catalogoVisita
				.objetoSeleccionadoDelCatalogo();
		idVisita = visitaSocial.getIdVisita();
		// LLENAR TODOS LOS CAMPOS

		cmb1.setValue(visitaSocial.getA());
		if (visitaSocial.getH() != null)
			if (visitaSocial.getH())
				rdo8_1.setChecked(true);
			else
				rdo8_2.setChecked(true);

		String valores[] = visitaSocial.getD().split(",");
		if (valores.length != 0) {
			int j = 0;
			while (j < valores.length) {
				if (valores[j].equals("En buen estado"))
					check4_1.setChecked(true);
				if (valores[j]
						.equals("Requiere mejoras en piso techo y paredes"))
					check4_2.setChecked(true);
				if (valores[j]
						.equals("Requiere construcción de nuevos ambientes"))
					check4_3.setChecked(true);
				if (valores[j]
						.equals("Requiere Mantenimiento de tuberías o de electricidad"))
					check4_4.setChecked(true);
				if (valores[j].equals("Se encuentra deteriorada (inhabitable)"))
					check4_5.setChecked(true);
				j++;
			}
		}

		catalogoVisita.setParent(null);
	}

	// Botones
	/*
	 * @Listen("onClick =  #btnBuscar1") public void buscarTrabajador(Event e) {
	 * final List<Paciente> pacientes = servicioPaciente.buscarTodos();
	 * catalogoT = new Catalogo<Paciente>(catalogoTrabajador,
	 * "Catalogo de Trabajadores", pacientes, false, "Cedula", "Primer Nombre",
	 * "Segundo Nombre", "Primer Apellido", "Segundo Apellido") {
	 * 
	 * @Override protected List<Paciente> buscar(String valor, String combo) {
	 * 
	 * switch (combo) { case "Primer Nombre": return
	 * servicioPaciente.filtroNombre1(valor); case "Segundo Nombre": return
	 * servicioPaciente.filtroNombre2(valor); case "Cedula": return
	 * servicioPaciente.filtroCedula(valor); case "Primer Apellido": return
	 * servicioPaciente.filtroApellido1(valor); case "Segundo Apellido": return
	 * servicioPaciente.filtroApellido2(valor); default: return pacientes; } }
	 * 
	 * @Override protected String[] crearRegistros(Paciente objeto) { String[]
	 * registros = new String[5]; registros[0] = objeto.getCedula();
	 * registros[1] = objeto.getPrimerNombre(); registros[2] =
	 * objeto.getSegundoNombre(); registros[3] = objeto.getPrimerApellido();
	 * registros[4] = objeto.getSegundoApellido(); return registros; }
	 * 
	 * }; catalogoT.setParent(catalogoTrabajador); catalogoT.doModal(); }
	 */

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
}
