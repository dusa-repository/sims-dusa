package controlador.sha;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import modelo.maestros.Empresa;
import modelo.maestros.Paciente;
import modelo.sha.Condicion;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Tab;

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
	// tab4
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
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void eliminar() {
				// TODO Auto-generated method stub
				
			}
		};
		botoneraInforme.appendChild(botonera);
	}

	@Listen("onClick =  #btnBuscar21,#btnBuscar22,#btnBuscar23,#btnBuscar24,#btnBuscar25,#btnBuscar26,#btnBuscar5")
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
		default:
			break;
		}
		catalogoP.setParent(null);
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
			listas.get(i).setMultiple(false);
			listas.get(i).setCheckmark(false);
			listas.get(i).setMultiple(true);
			listas.get(i).setCheckmark(true);
		}
	}
}
