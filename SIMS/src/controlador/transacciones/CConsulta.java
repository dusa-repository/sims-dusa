package controlador.transacciones;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import modelo.maestros.Accidente;
import modelo.maestros.Antecedente;
import modelo.maestros.AntecedenteTipo;
import modelo.maestros.Cargo;
import modelo.maestros.Diagnostico;
import modelo.maestros.Especialista;
import modelo.maestros.Examen;
import modelo.maestros.Intervencion;
import modelo.maestros.Medicina;
import modelo.maestros.Paciente;
import modelo.maestros.PacienteAntecedente;
import modelo.maestros.ParteCuerpo;
import modelo.maestros.PresentacionMedicina;
import modelo.maestros.Proveedor;
import modelo.maestros.ProveedorServicio;
import modelo.maestros.Recipe;
import modelo.maestros.ServicioExterno;
import modelo.maestros.UnidadMedicina;
import modelo.maestros.Vacuna;
import modelo.seguridad.Arbol;
import modelo.seguridad.Usuario;
import modelo.sha.Area;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaDiagnostico;
import modelo.transacciones.ConsultaEspecialista;
import modelo.transacciones.ConsultaExamen;
import modelo.transacciones.ConsultaMedicina;
import modelo.transacciones.ConsultaParteCuerpo;
import modelo.transacciones.ConsultaServicioExterno;
import modelo.transacciones.Historia;
import modelo.transacciones.HistoriaAccidente;
import modelo.transacciones.HistoriaIntervencion;
import modelo.transacciones.HistoriaVacuna;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublespinner;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.GroupsModel;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.SimpleGroupsModel;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;
import org.zkoss.zul.Window;

import com.sun.org.glassfish.external.arc.Taxonomy;

import arbol.CArbol;

import componentes.Botonera;
import componentes.Buscar;
import componentes.Catalogo;

import controlador.maestros.CEspecialista;
import controlador.maestros.CGenerico;

public class CConsulta extends CGenerico {

	private static final long serialVersionUID = -6277014704105198573L;
	@Wire
	private Datebox dtbFechaConsulta;
	@Wire
	private Textbox txtBuscadorExamen;
	@Wire
	private Textbox txtBuscadorEspecialista;
	@Wire
	private Textbox txtBuscadorMedicina;
	@Wire
	private Textbox txtBuscadorServicioExterno;
	@Wire
	private Textbox txtBuscadorDiagnostico;
	@Wire
	private Textbox txtCedula;
	@Wire
	private Combobox cmbPrioridad;
	@Wire
	private Datebox dtbValido;
	@Wire
	private Groupbox gpxResumen;
	@Wire
	private Groupbox gpxMedicos;
	@Wire
	private Groupbox gpxLaborales;
	@Wire
	private Div botoneraConsulta;
	@Wire
	private Button btnBuscarPaciente;
	@Wire
	private Div divCatalogoPacientes;
	@Wire
	private Div divConsulta;
	@Wire
	private Listbox ltbMedicinas;
	@Wire
	private Listbox ltbMedicinasAgregadas;
	@Wire
	private Listbox ltbExamenes;
	@Wire
	private Listbox ltbExamenesAgregados;
	@Wire
	private Listbox ltbDiagnosticos;
	@Wire
	private Listbox ltbDiagnosticosAgregados;
	@Wire
	private Listbox ltbServicioExterno;
	@Wire
	private Listbox ltbServicioExternoAgregados;
	@Wire
	private Listbox ltbEspecialistas;
	@Wire
	private Listbox ltbEspecialistasAgregados;
	@Wire
	private Listbox ltbResumenMedicinas;
	@Wire
	private Listbox ltbResumenDiagnosticos;
	@Wire
	private Listbox ltbResumenExamenes;
	@Wire
	private Listbox ltbResumenEspecialistas;
	@Wire
	private Listbox ltbResumenServicios;
	@Wire
	private Listbox ltbCargaFamiliar;
	@Wire
	private Listbox ltbConsultas;
	@Wire
	private Listbox ltbMedicos;
	@Wire
	private Listbox ltbLaborales;
	@Wire
	private Listbox ltbFamiliares;
	// .---------------------------
	@Wire
	private Image imagenPaciente;
	@Wire
	private Label lblNombres;
	@Wire
	private Label lblCedula;
	@Wire
	private Label lblApellidos;
	@Wire
	private Label lblEmpresa;
	@Wire
	private Label lblFicha;
	@Wire
	private Label lblTrabajador;
	@Wire
	private Label lblFechaNac;
	@Wire
	private Label lblLugarNac;
	@Wire
	private Label lblAlergias;
	@Wire
	private Label lblAlergico;
	@Wire
	private Label lblLentes;
	@Wire
	private Label lblEdad;
	@Wire
	private Label lblSexo;
	@Wire
	private Label lblEstadoCivil;
	@Wire
	private Label lblGrupoSanguineo;
	@Wire
	private Label lblMano;
	@Wire
	private Label lblEstatura;
	@Wire
	private Label lblPeso;
	@Wire
	private Label lblDiscapacidad;
	@Wire
	private Label lblOrigen;
	@Wire
	private Label lblTipoDiscapacidad;
	@Wire
	private Label lblObservacionDiscapacidad;
	@Wire
	private Label lblCargo;
	@Wire
	private Label lblCiudad;
	@Wire
	private Label lblDireccion;
	@Wire
	private Label lblTelefono1;
	@Wire
	private Label lblTelefono2;
	@Wire
	private Label lblCorreo;
	@Wire
	private Label lblNombresE;
	@Wire
	private Label lblApellidosE;
	@Wire
	private Label lblParentesco;
	@Wire
	private Label lblTelefono1E;
	@Wire
	private Label lblTelefono2E;
	@Wire
	private Tab tabCarga;
	@Wire
	private Combobox cmbTipoConsulta;
	@Wire
	private Combobox cmbTipoPreventiva;
	@Wire
	private Textbox txtMotivo;
	@Wire
	private Textbox txtEnfermedad;
	@Wire
	private Label lblPreventiva;
	@Wire
	private Combobox cmbConsulta;
	@Wire
	private Combobox cmbProveedor;
	@Wire
	private Label lblConsulta;
	// ----------------------------------------------
	@Wire
	private Radio rdoSiPeso;
	@Wire
	private Radio rdoNoPeso;
	@Wire
	private Textbox txtCantidadPeso;
	@Wire
	private Textbox txtCausasPeso;
	@Wire
	private Radio rdoSiCafe;
	@Wire
	private Radio rdoNoCafe;
	@Wire
	private Spinner spnCantidadCafe;
	@Wire
	private Radio rdoSiDuerme;
	@Wire
	private Radio rdoNoDuerme;
	@Wire
	private Radio rdoSiCabeza;
	@Wire
	private Radio rdoNoCabeza;
	@Wire
	private Radio rdoSiFisica;
	@Wire
	private Radio rdoNoFisica;
	@Wire
	private Textbox txtTipoFisica;
	@Wire
	private Textbox txtFrecuenciaFisica;
	@Wire
	private Textbox txtTiempoFisica;
	@Wire
	private Radio rdoSiExtra;
	@Wire
	private Radio rdoNoExtra;
	@Wire
	private Combobox cmbExtra;
	@Wire
	private Radio rdoSiCigarro;
	@Wire
	private Radio rdoNoCigarro;
	@Wire
	private Radio rdoSiFumaActualmente;
	@Wire
	private Radio rdoNoFumaActualmente;
	@Wire
	private Spinner spnNumeroCigarro;
	@Wire
	private Datebox dtbFechaFinCigarro;
	@Wire
	private Datebox dtbFechaInicioCigarro;
	@Wire
	private Textbox txtRazonCigarro;
	@Wire
	private Radio rdoSiAlcohol;
	@Wire
	private Radio rdoNoAlcohol;
	@Wire
	private Radio rdoSiMesAlcohol;
	@Wire
	private Radio rdoNoMesAlcohol;
	@Wire
	private Radiogroup rdgFrecuenciaAlcohol;
	@Wire
	private Textbox txtTipoAlcohol;
	@Wire
	private Spinner spnCantidadAlcohol;
	@Wire
	private Radio rdoSiBorracho;
	@Wire
	private Radio rdoNoBorracho;
	@Wire
	private Radio rdoSiAccidenteAlcohol;
	@Wire
	private Radio rdoNoAccidenteAlcohol;
	@Wire
	private Radio rdoSiTratamientoAlcohol;
	@Wire
	private Radio rdoNoTratamientoAlcohol;
	@Wire
	private Radio rdoSiRehabilitacionAlcohol;
	@Wire
	private Radio rdoNoRehabilitacionAlcohol;
	@Wire
	private Radio rdoSiDrogas;
	@Wire
	private Radio rdoNoDrogas;
	@Wire
	private Radio rdoSiTratamientoDrogas;
	@Wire
	private Radio rdoNoTratamientoDrogas;
	@Wire
	private Textbox txtExplicacionDrogas;
	@Wire
	private Radio rdoSiRehabilitacion;
	@Wire
	private Radio rdoNoRehabilitacion;
	@Wire
	private Radio rdoSiMedicamentoDrogas;
	@Wire
	private Radio rdoNoMedicamentoDrogas;
	@Wire
	private Textbox txtMedicamentoDroga;
	@Wire
	private Textbox txtCantidadMedicamento;
	@Wire
	private Datebox dtbFechaMedicamento;
	@Wire
	private Radio rdoSiEnfermedad;
	@Wire
	private Radio rdoNoEnfermedad;
	@Wire
	private Textbox txtAlgunaEnfermedad;
	@Wire
	private Radio rdoSiMedico;
	@Wire
	private Radio rdoNoMedico;
	@Wire
	private Radio rdoSiTratamiento;
	@Wire
	private Radio rdoNoTratamiento;
	@Wire
	private Radio rdoSiTransfusiones;
	@Wire
	private Radio rdoNoTransfusiones;
	@Wire
	private Radio rdoSiETS;
	@Wire
	private Radio rdoNoETS;
	@Wire
	private Textbox txtVIH;
	@Wire
	private Radio rdoSiFlujo;
	@Wire
	private Radio rdoNoFlujo;
	@Wire
	private Radio rdoSiPezones;
	@Wire
	private Radio rdoNoPezones;
	@Wire
	private Radio rdoSiMenstrual;
	@Wire
	private Radio rdoNoMenstrual;
	@Wire
	private Radio rdoSiEndurecimiento;
	@Wire
	private Radio rdoNoEndurecimiento;
	@Wire
	private Radio rdoSiInfeccion;
	@Wire
	private Radio rdoNoInfeccion;
	@Wire
	private Radio rdoSiAnticonceptivos;
	@Wire
	private Radio rdoNoAnticonceptivos;
	@Wire
	private Radio rdoSiDolor;
	@Wire
	private Radio rdoNoDolor;
	@Wire
	private Radio rdoSiEsterilizacion;
	@Wire
	private Radio rdoNoEsterilizacion;
	@Wire
	private Radio rdoSiIntra;
	@Wire
	private Radio rdoNoIntra;
	@Wire
	private Radio rdoSiVIH;
	@Wire
	private Radio rdoNoVIH;
	@Wire
	private Spinner spnEdadDesarrollo;
	@Wire
	private Datebox dtbFechaUltima;
	@Wire
	private Spinner spnEmbarazos;
	@Wire
	private Spinner spnPartos;
	@Wire
	private Spinner spnCesareas;
	@Wire
	private Spinner spnAbortos;
	@Wire
	private Datebox dtbFechaUltimaCito;
	@Wire
	private Radio rdoSiOvarios;
	@Wire
	private Radio rdoNoOvarios;
	@Wire
	private Radio rdoSiEmbarazada;
	@Wire
	private Radio rdoNoEmbarazada;
	@Wire
	private Spinner spnGestacion;
	@Wire
	private Radio rdoSiEco;
	@Wire
	private Radio rdoNoEco;
	@Wire
	private Radio rdoSiMamografia;
	@Wire
	private Radio rdoNoMamografia;
	@Wire
	private Textbox txtResultadoEco;
	@Wire
	private Textbox txtResultadoMamografia;
	@Wire
	private Listbox ltbExamenFisico;
	@Wire
	private Listbox ltbVacunas;
	@Wire
	private Listbox ltbAccidentesLaborales;
	@Wire
	private Listbox ltbAccidentesLaboralesAgregados;
	@Wire
	private Listbox ltbAccidentesComunes;
	@Wire
	private Listbox ltbAccidentesComunesAgregados;
	@Wire
	private Listbox ltbIntervenciones;
	@Wire
	private Listbox ltbIntervencionesAgregadas;
	@Wire
	private Label lblIndice;
	@Wire
	private Doublespinner spnPeso;
	@Wire
	private Doublespinner spnEstatura;
	@Wire
	private Doublespinner spnPlena;
	@Wire
	private Doublespinner spnForzada;
	@Wire
	private Doublespinner spnOmbligo;
	@Wire
	private Spinner spnCardiaca;
	@Wire
	private Radio rdoSiRitmico;
	@Wire
	private Radio rdoNoRitmico;
	@Wire
	private Spinner frecuencia11;
	@Wire
	private Spinner frecuencia12;
	@Wire
	private Spinner frecuencia13;
	@Wire
	private Spinner s11;
	@Wire
	private Spinner s12;
	@Wire
	private Spinner s13;
	@Wire
	private Spinner d11;
	@Wire
	private Spinner d12;
	@Wire
	private Spinner d13;
	@Wire
	private Spinner ex11;
	@Wire
	private Spinner ex12;
	@Wire
	private Spinner ex13;
	@Wire
	private Radio rdoSiRitmicoF1;
	@Wire
	private Radio rdoNoRitmicoF1;
	@Wire
	private Radio rdoSiRitmicoF2;
	@Wire
	private Radio rdoNoRitmicoF2;
	@Wire
	private Radio rdoSiRitmicoF3;
	@Wire
	private Radio rdoNoRitmicoF3;
	// --------------------------
	@Wire
	private Combobox cmbDiente1;
	@Wire
	private Combobox cmbDiente2;
	@Wire
	private Combobox cmbDiente3;
	@Wire
	private Combobox cmbDiente4;
	@Wire
	private Combobox cmbDiente5;
	@Wire
	private Combobox cmbDiente6;
	@Wire
	private Combobox cmbDiente7;
	@Wire
	private Combobox cmbDiente8;
	@Wire
	private Combobox cmbDiente9;
	@Wire
	private Combobox cmbDiente10;
	@Wire
	private Combobox cmbDiente11;
	@Wire
	private Combobox cmbDiente12;
	@Wire
	private Combobox cmbDiente13;
	@Wire
	private Combobox cmbDiente14;
	@Wire
	private Combobox cmbDiente15;
	@Wire
	private Combobox cmbDiente16;
	@Wire
	private Combobox cmbDiente17;
	@Wire
	private Combobox cmbDiente18;
	@Wire
	private Combobox cmbDiente19;
	@Wire
	private Combobox cmbDiente20;
	@Wire
	private Combobox cmbDiente21;
	@Wire
	private Combobox cmbDiente22;
	@Wire
	private Combobox cmbDiente23;
	@Wire
	private Combobox cmbDiente24;
	@Wire
	private Combobox cmbDiente25;
	@Wire
	private Combobox cmbDiente26;
	@Wire
	private Combobox cmbDiente27;
	@Wire
	private Combobox cmbDiente28;
	@Wire
	private Combobox cmbDiente29;
	@Wire
	private Combobox cmbDiente30;
	@Wire
	private Combobox cmbDiente31;
	@Wire
	private Combobox cmbDiente32;
	@Wire
	private Textbox txtTelefonoDoc;
	@Wire
	private Combobox cmbCarta;
	@Wire
	private Radio rdoSiColores;
	@Wire
	private Radio rdoNoColores;
	@Wire
	private Doublespinner spnAlturaHombro;
	@Wire
	private Doublespinner spnAnchuraHombro;
	@Wire
	private Doublespinner spnAlturaCodo;
	@Wire
	private Doublespinner spnLongIzquierdo;
	@Wire
	private Doublespinner spnLongDerecho;
	@Wire
	private Doublespinner spnPoplitea;
	@Wire
	private Doublespinner spnSillaOjo;
	@Wire
	private Doublespinner spnCodoSilla;
	@Wire
	private Doublespinner spnCircunferenciaA;
	@Wire
	private Doublespinner spnManoPiso;
	@Wire
	private Doublespinner spnCircunferenciaC;
	@Wire
	private Doublespinner spnCaderaAbdominal;
	//
	@Wire
	private Radio rdoSiReposo;
	@Wire
	private Radio rdoNoReposo;
	@Wire
	private Radio rdoSiApto;
	@Wire
	private Radio rdoNoApto;
	@Wire
	private Row row;
	@Wire
	private Row rowPromocion;
	@Wire
	private Textbox txtExamenPreempleo;
	@Wire
	private Textbox txtBuscadorAccidenteLaboral;
	@Wire
	private Textbox txtBuscadorAccidenteComun;
	@Wire
	private Textbox txtBuscadorIntervencion;
	@Wire
	private Combobox cmbCargo;
	@Wire
	private Combobox cmbArea;
	//
	@Wire
	private Div botoneraConsultaGeneral;
	@Wire
	private Button btnGuardarHistoria;

	// -----------------------------
	@Wire("#wdwRegistro")
	private Window wdwRegistro;
	//
	List<Listbox> listas = new ArrayList<Listbox>();

	List<Intervencion> intervencionesDisponibles = new ArrayList<Intervencion>();
	List<HistoriaIntervencion> intervencionesAgregadas = new ArrayList<HistoriaIntervencion>();

	List<Accidente> accidentesLaboralesDisponibles = new ArrayList<Accidente>();
	List<HistoriaAccidente> accidentesLaboralesAgregadas = new ArrayList<HistoriaAccidente>();

	List<Accidente> accidentesComunesDisponibles = new ArrayList<Accidente>();
	List<HistoriaAccidente> accidentesComunesAgregadas = new ArrayList<HistoriaAccidente>();

	List<Medicina> medicinasDisponibles = new ArrayList<Medicina>();
	List<ConsultaMedicina> medicinasAgregadas = new ArrayList<ConsultaMedicina>();
	List<ConsultaMedicina> medicinasResumen = new ArrayList<ConsultaMedicina>();

	List<Diagnostico> diagnosticosDisponibles = new ArrayList<Diagnostico>();
	List<ConsultaDiagnostico> diagnosticosAgregados = new ArrayList<ConsultaDiagnostico>();
	List<ConsultaDiagnostico> diagnosticosResumen = new ArrayList<ConsultaDiagnostico>();

	List<Examen> examenesDisponibles = new ArrayList<Examen>();
	List<ConsultaExamen> examenesAgregado = new ArrayList<ConsultaExamen>();
	List<ConsultaExamen> examenesResumen = new ArrayList<ConsultaExamen>();

	List<Especialista> especialistasDisponibles = new ArrayList<Especialista>();
	List<ConsultaEspecialista> especialistasAgregados = new ArrayList<ConsultaEspecialista>();
	List<ConsultaEspecialista> especialistasResumen = new ArrayList<ConsultaEspecialista>();

	List<ServicioExterno> serviciosDisponibles = new ArrayList<ServicioExterno>();
	List<ConsultaServicioExterno> serviciosAgregados = new ArrayList<ConsultaServicioExterno>();
	List<ConsultaServicioExterno> serviciosResumen = new ArrayList<ConsultaServicioExterno>();

	long idPaciente = 0;
	long idConsulta = 0;

	Catalogo<Paciente> catalogoPaciente;

	GroupsModel<Antecedente, Object, Antecedente> model;
	GroupsModel<Antecedente, Object, Antecedente> modelo;
	GroupsModel<Antecedente, Object, Antecedente> modelFamiliares;
	ListModelList<Proveedor> proveedores;
	ListModelList<Vacuna> modelVacunas;
	ListModelList<ParteCuerpo> modelFisico;
	ListModelList<String> dientes;

	ListitemRenderer renderer;

	Buscar<Accidente> buscarAccidenteLaboral;
	Buscar<Accidente> buscarAccidenteComun;
	Buscar<Intervencion> buscarIntervencion;
	Buscar<Medicina> buscarMedicina;
	Buscar<Diagnostico> buscarDiagnostico;
	Buscar<Examen> buscarExamen;
	Buscar<Especialista> buscarEspecialista;
	Buscar<ServicioExterno> buscarServicio;

	private CArbol cArbol = new CArbol();
	String cambio;
	private String[] tipoDiente = { "Normal", "Protesis", "Amalgama",
			"Ausencia" };
	private String[] consultaPreventiva = { "Pre-Empleo", "Pre-Vacacional",
			"Post-Vacacional", "Egreso", "Cambio de Puesto", "Promocion",
			"Reintegro" };
	private String[] consultaCurativa = { "Primera", "Control" };

	@Override
	public void inicializar() throws IOException {

		listas.add(ltbConsultas);
		listas.add(ltbDiagnosticos);
		listas.add(ltbDiagnosticosAgregados);
		listas.add(ltbEspecialistas);
		listas.add(ltbEspecialistasAgregados);
		listas.add(ltbExamenes);
		listas.add(ltbExamenesAgregados);
		listas.add(ltbMedicinas);
		listas.add(ltbMedicinasAgregadas);
		listas.add(ltbServicioExterno);
		listas.add(ltbServicioExternoAgregados);
		listas.add(ltbLaborales);
		listas.add(ltbFamiliares);
		listas.add(ltbMedicos);
		listas.add(ltbExamenFisico);
		listas.add(ltbVacunas);
		listas.add(ltbAccidentesComunes);
		listas.add(ltbAccidentesComunesAgregados);
		listas.add(ltbAccidentesLaborales);
		listas.add(ltbAccidentesLaboralesAgregados);
		listas.add(ltbIntervenciones);
		listas.add(ltbIntervencionesAgregadas);
		llenarListas();
		listasMultiples();
		buscadorMedicina();
		buscadorDiagnostico();
		buscadorServicio();
		buscadorExamen();
		buscadorEspecialista();
		buscadorLaborales();
		buscadorComunes();
		buscadorIntervenciones();
		cmbArea.setModel(new ListModelList<Area>(servicioArea.buscarTodos()));
		cmbCargo.setModel(new ListModelList<>(servicioCargo.buscarTodos()));
		Botonera botonera = new Botonera() {
			@Override
			public void salir() {
				cerrarVentana(divConsulta, "Consulta");
			}

			@Override
			public void limpiar() {
				limpiarCampos();
			}

			@Override
			public void guardar() {
				if (validar()) {
					if (idConsulta != 0) {
						Consulta consulta = servicioConsulta.buscar(idConsulta);
						servicioConsultaExamen
								.borrarExamenesDeConsulta(consulta);
						servicioConsultaMedicina
								.borrarMedicinasDeConsulta(consulta);
						servicioConsultaDiagnostico
								.borrarDiagnosticosDeConsulta(consulta);
						servicioConsultaEspecialista
								.borrarEspecialistasDeConsulta(consulta);
						servicioConsultaServicioExterno
								.borrarServiciosDeConsulta(consulta);
					}
					// Date hConsulta = tmbHoraConsulta.getValue();
					Date fechaCon = dtbFechaConsulta.getValue();
					Timestamp fechaConsulta = new Timestamp(fechaCon.getTime());
					// String horaConsulta = df.format(hConsulta);
					Usuario usuario = null;
					Paciente paciente = servicioPaciente
							.buscarPorCedula(txtCedula.getValue());
					boolean accidente = false;
					String motivo = txtMotivo.getValue();
					String enfermedad = txtEnfermedad.getValue();
					String tipo = "";
					Accidente accidenteL = null;
					long consultaAsociada = 0;
					if (cmbTipoConsulta.getValue().equals("Preventiva"))
						tipo = cmbTipoConsulta.getValue();
					else
						tipo = cmbTipoConsulta.getValue();
					if (cmbTipoConsulta.getValue().equals("Control"))
						consultaAsociada = Long.parseLong(cmbConsulta
								.getSelectedItem().getContext());
					double peso = spnPeso.getValue();
					double estatura = spnEstatura.getValue();
					double ombligo = spnOmbligo.getValue();
					double plena = spnPlena.getValue();
					double forzada = spnForzada.getValue();
					int frecuencia = spnCardiaca.getValue();
					int frecuencia1 = frecuencia11.getValue();
					int frecuencia2 = frecuencia12.getValue();
					int frecuencia3 = frecuencia13.getValue();
					int sistolica1 = s11.getValue();
					int sistolica2 = s12.getValue();
					int sistolica3 = s13.getValue();
					int diastolica1 = d11.getValue();
					int diastolica2 = d12.getValue();
					int diastolica3 = d13.getValue();
					int extra1 = ex11.getValue();
					int extra2 = ex12.getValue();
					int extra3 = ex13.getValue();
					boolean ritmico = false;
					if (rdoSiRitmico.isChecked())
						ritmico = true;
					boolean ritmico1 = false;
					if (rdoSiRitmicoF1.isChecked())
						ritmico1 = true;
					boolean ritmico2 = false;
					if (rdoSiRitmicoF2.isChecked())
						ritmico2 = true;
					boolean ritmico3 = false;
					if (rdoSiRitmicoF3.isChecked())
						ritmico3 = true;
					boolean reposo = false;
					if (rdoSiReposo.isChecked())
						reposo = true;
					Boolean apto = null;
					if (rdoSiApto.isChecked())
						apto = true;
					if (rdoNoApto.isChecked())
						apto = false;

					String tipoSecundaria = cmbTipoPreventiva.getValue();
					String examenesPre = txtExamenPreempleo.getValue();
					Cargo cargoDeseado = new Cargo();
					if (cmbCargo.getSelectedItem() != null) {
						cargoDeseado = servicioCargo.buscar(Long
								.parseLong(cmbCargo.getSelectedItem()
										.getContext()));
					}

					Area areaDeseado = new Area();
					if (cmbArea.getSelectedItem() != null) {
						areaDeseado = servicioArea.buscar(Long
								.parseLong(cmbArea.getSelectedItem()
										.getContext()));
					}

					Consulta consulta = new Consulta(idConsulta, paciente,
							usuario, accidenteL, fechaConsulta, horaAuditoria,
							horaAuditoria, fechaHora, nombreUsuarioSesion(),
							accidente, motivo, tipo, enfermedad,
							consultaAsociada, estatura, peso, ombligo, plena,
							forzada, frecuencia, frecuencia1, frecuencia2,
							frecuencia3, sistolica1, sistolica2, sistolica3,
							diastolica1, diastolica2, diastolica3, extra1,
							extra2, extra3, ritmico, ritmico1, ritmico2,
							ritmico3, paciente.getCargoReal(), cargoDeseado,
							paciente.getArea(), areaDeseado, apto, reposo,
							tipoSecundaria, examenesPre);
					servicioConsulta.guardar(consulta);
					Consulta consultaDatos = new Consulta();
					if (idConsulta != 0)
						consultaDatos = servicioConsulta.buscar(idConsulta);
					else
						consultaDatos = servicioConsulta.buscarUltima();
					guardarMedicinas(consultaDatos);
					guardarDiagnosticos(consultaDatos);
					guardarExamenes(consultaDatos);
					guardarEspecialistas(consultaDatos);
					guardarServicios(consultaDatos);
					guardarExamenFisico(consultaDatos);
					guardarAntecedentes(paciente);
					guardarHistoria(paciente);
					limpiar();
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
				}
			}

			@Override
			public void eliminar() {
			}
		};

		botonera.getChildren().get(1).setVisible(false);
		botonera.getChildren().get(2).setVisible(false);
		botonera.getChildren().get(3).setVisible(false);
		botoneraConsulta.appendChild(botonera);

		Botonera botoneraGeneral = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divConsulta, "Consulta");
			}

			@Override
			public void limpiar() {
				limpiarCampos();
			}

			@Override
			public void guardar() {
			}

			@Override
			public void eliminar() {
			}
		};
		botoneraGeneral.getChildren().get(0).setVisible(false);
		botoneraGeneral.getChildren().get(1).setVisible(false);
		botoneraConsultaGeneral.appendChild(botoneraGeneral);
	}

	private void buscadorIntervenciones() {
		buscarIntervencion = new Buscar<Intervencion>(ltbIntervenciones,
				txtBuscadorIntervencion) {
			@Override
			protected List<Intervencion> buscar(String valor) {
				List<Intervencion> accidentesFiltrados = new ArrayList<Intervencion>();
				List<Intervencion> accidentes = servicioIntervencion
						.filtroNombre(valor);
				for (int i = 0; i < intervencionesDisponibles.size(); i++) {
					Intervencion intervencion = intervencionesDisponibles
							.get(i);
					for (int j = 0; j < accidentes.size(); j++) {
						if (intervencion.getIdIntervencion() == accidentes.get(
								j).getIdIntervencion())
							accidentesFiltrados.add(intervencion);
					}
				}
				return accidentesFiltrados;
			}
		};
	}

	private void buscadorComunes() {
		buscarAccidenteComun = new Buscar<Accidente>(ltbAccidentesComunes,
				txtBuscadorAccidenteComun) {
			@Override
			protected List<Accidente> buscar(String valor) {
				List<Accidente> accidentesFiltrados = new ArrayList<Accidente>();
				List<Accidente> accidentes = servicioAccidente.filtroNombre(
						valor, "Accidente Comun");
				for (int i = 0; i < accidentesComunesDisponibles.size(); i++) {
					Accidente accidente = accidentesComunesDisponibles.get(i);
					for (int j = 0; j < accidentes.size(); j++) {
						if (accidente.getIdAccidente() == accidentes.get(j)
								.getIdAccidente())
							accidentesFiltrados.add(accidente);
					}
				}
				return accidentesFiltrados;
			}
		};
	}

	private void buscadorLaborales() {
		buscarAccidenteLaboral = new Buscar<Accidente>(ltbAccidentesLaborales,
				txtBuscadorAccidenteLaboral) {
			@Override
			protected List<Accidente> buscar(String valor) {
				List<Accidente> accidentesFiltrados = new ArrayList<Accidente>();
				List<Accidente> accidentes = servicioAccidente.filtroNombre(
						valor, "Accidente Laboral");
				for (int i = 0; i < accidentesLaboralesDisponibles.size(); i++) {
					Accidente accidente = accidentesLaboralesDisponibles.get(i);
					for (int j = 0; j < accidentes.size(); j++) {
						if (accidente.getIdAccidente() == accidentes.get(j)
								.getIdAccidente())
							accidentesFiltrados.add(accidente);
					}
				}
				return accidentesFiltrados;
			}
		};
	}

	private void buscadorEspecialista() {
		buscarEspecialista = new Buscar<Especialista>(ltbEspecialistas,
				txtBuscadorEspecialista) {
			@Override
			protected List<Especialista> buscar(String valor) {
				List<Especialista> presentacionesFiltradas = new ArrayList<Especialista>();
				List<Especialista> presentaciones = servicioEspecialista
						.filtroNombre(valor);
				for (int i = 0; i < especialistasDisponibles.size(); i++) {
					Especialista especialista = especialistasDisponibles.get(i);
					for (int j = 0; j < presentaciones.size(); j++) {
						if (especialista.getCedula().equals(
								presentaciones.get(j).getCedula()))
							presentacionesFiltradas.add(especialista);
					}
				}
				return presentacionesFiltradas;
			}
		};
	}

	private void buscadorExamen() {
		buscarExamen = new Buscar<Examen>(ltbExamenes, txtBuscadorExamen) {

			@Override
			protected List<Examen> buscar(String valor) {
				List<Examen> examenesFiltradas = new ArrayList<Examen>();
				List<Examen> examenes = servicioExamen.filtroNombre(valor);
				for (int i = 0; i < examenesDisponibles.size(); i++) {
					Examen examen = examenesDisponibles.get(i);
					for (int j = 0; j < examenes.size(); j++) {
						if (examen.getIdExamen() == examenes.get(j)
								.getIdExamen())
							examenesFiltradas.add(examen);
					}
				}
				return examenesFiltradas;
			}
		};
	}

	private void buscadorServicio() {
		buscarServicio = new Buscar<ServicioExterno>(ltbServicioExterno,
				txtBuscadorServicioExterno) {

			@Override
			protected List<ServicioExterno> buscar(String valor) {
				List<ServicioExterno> serviciosFiltradas = new ArrayList<ServicioExterno>();
				List<ServicioExterno> servicios = servicioServicioExterno
						.filtroNombre(valor);
				for (int i = 0; i < serviciosDisponibles.size(); i++) {
					ServicioExterno servicio = serviciosDisponibles.get(i);
					for (int j = 0; j < servicios.size(); j++) {
						if (servicio.getIdServicioExterno() == servicios.get(j)
								.getIdServicioExterno())
							serviciosFiltradas.add(servicio);
					}
				}
				return serviciosFiltradas;
			}
		};
	}

	private void buscadorDiagnostico() {
		buscarDiagnostico = new Buscar<Diagnostico>(ltbDiagnosticos,
				txtBuscadorDiagnostico) {

			@Override
			protected List<Diagnostico> buscar(String valor) {
				List<Diagnostico> diagnosticosFiltradas = new ArrayList<Diagnostico>();
				List<Diagnostico> diagnosticos = servicioDiagnostico
						.filtroNombre(valor);
				for (int i = 0; i < diagnosticosDisponibles.size(); i++) {
					Diagnostico diagnostico = diagnosticosDisponibles.get(i);
					for (int j = 0; j < diagnosticos.size(); j++) {
						if (diagnostico.getIdDiagnostico() == diagnosticos.get(
								j).getIdDiagnostico())
							diagnosticosFiltradas.add(diagnostico);
					}
				}
				return diagnosticosFiltradas;
			}
		};
	}

	private void buscadorMedicina() {
		buscarMedicina = new Buscar<Medicina>(ltbMedicinas, txtBuscadorMedicina) {

			@Override
			protected List<Medicina> buscar(String valor) {
				List<Medicina> medicinasFiltradas = new ArrayList<Medicina>();
				List<Medicina> medicinas = servicioMedicina.filtroNombre(valor);
				for (int i = 0; i < medicinasDisponibles.size(); i++) {
					Medicina medicina = medicinasDisponibles.get(i);
					for (int j = 0; j < medicinas.size(); j++) {
						if (medicina.getIdMedicina() == medicinas.get(j)
								.getIdMedicina())
							medicinasFiltradas.add(medicina);
					}
				}
				return medicinasFiltradas;
			}
		};
	}

	public GroupsModel<Antecedente, Object, Antecedente> getModelFamiliares() {
		List<Antecedente> antecedentesLaborales = servicioAntecedente
				.buscarFamiliares();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Antecedente> tipos = new ArrayList<Antecedente>();
		List<List<Antecedente>> ante = new ArrayList<List<Antecedente>>();
		map = listasModelo(antecedentesLaborales);
		tipos = (List<Antecedente>) map.get("Tipos");
		ante = (List<List<Antecedente>>) map.get("ListaDoble");
		modelFamiliares = new SimpleGroupsModel<Antecedente, Antecedente, Antecedente, Antecedente>(
				ante, tipos);
		return modelFamiliares;
	}

	public GroupsModel getModel() {
		List<Antecedente> antecedentesLaborales = servicioAntecedente
				.buscarLaborales();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Antecedente> tipos = new ArrayList<Antecedente>();
		List<List<Antecedente>> ante = new ArrayList<List<Antecedente>>();
		map = listasModelo(antecedentesLaborales);
		tipos = (List<Antecedente>) map.get("Tipos");
		ante = (List<List<Antecedente>>) map.get("ListaDoble");
		model = new SimpleGroupsModel<Antecedente, Antecedente, Antecedente, Antecedente>(
				ante, tipos);
		return model;
	}

	public GroupsModel getModelo() {
		List<Antecedente> antecedentesMedicos = servicioAntecedente
				.buscarMedicos();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Antecedente> tipos = new ArrayList<Antecedente>();
		List<List<Antecedente>> ante = new ArrayList<List<Antecedente>>();
		map = listasModelo(antecedentesMedicos);
		tipos = (List<Antecedente>) map.get("Tipos");
		ante = (List<List<Antecedente>>) map.get("ListaDoble");
		modelo = new SimpleGroupsModel<Antecedente, Antecedente, Antecedente, Antecedente>(
				ante, tipos);
		return modelo;
	}

	public Map<String, Object> listasModelo(List<Antecedente> antecedentes) {
		List<Antecedente> tipos = new ArrayList<Antecedente>();
		List<List<Antecedente>> ante = new ArrayList<List<Antecedente>>();
		long id = 0;
		for (int i = 0; i < antecedentes.size(); i++) {
			long id2 = antecedentes.get(i).getAntecedenteTipo()
					.getIdAntecedenteTipo();
			if (id2 != id) {
				id = id2;
				tipos.add(antecedentes.get(i));
				List<Antecedente> lista = new ArrayList<Antecedente>();
				ante.add(lista);
			}
		}
		for (int i = 0; i < tipos.size(); i++) {
			long a = tipos.get(i).getAntecedenteTipo().getIdAntecedenteTipo();
			for (int j = 0; j < antecedentes.size(); j++) {
				if (a == antecedentes.get(j).getAntecedenteTipo()
						.getIdAntecedenteTipo()) {
					ante.get(i).add(antecedentes.get(j));
				}
			}
		}
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Tipos", tipos);
		map.put("ListaDoble", ante);
		return map;
	}

	public ListitemRenderer getRenderer() {
		renderer = new ListitemRenderer<Antecedente>() {
			long id = 0;

			@Override
			public void render(Listitem arg0, Antecedente arg1, int arg2)
					throws Exception {
				boolean tipoAntecedente = false;
				if (id == arg1.getAntecedenteTipo().getIdAntecedenteTipo()) {
					arg0.setValue(arg1);
					arg0.setContext(String.valueOf(arg1.getIdAntecedente()));
					Listcell list2 = new Listcell(arg1.getNombre());
					list2.setParent(arg0);
				} else {
					tipoAntecedente = true;
					id = arg1.getAntecedenteTipo().getIdAntecedenteTipo();
					arg0.setValue(arg1.getAntecedenteTipo());
					Listcell list2 = new Listcell(arg1.getAntecedenteTipo()
							.getNombre());
					list2.setParent(arg0);
					arg0.setCheckable(false);
					list2.setStyle("text-align:center; font-weight:bold; background:#D8D2D2; color:black");
					arg0.setStyle("text-align:center; font-weight:bold; background:#D8D2D2; color:black");
				}

				Listcell list3 = new Listcell();
				Textbox tex = new Textbox("");
				tex.setPlaceholder("Ingrese una Observacion");
				tex.setWidth("100%");
				tex.setParent(list3);
				list3.setParent(arg0);

				if (tipoAntecedente) {
					list3.setVisible(false);
					list3.setStyle("text-align:center; font-weight:bold; background:#D8D2D2; color:black");
					arg0.setStyle("text-align:center; font-weight:bold; background:#D8D2D2; color:black");
				}

			}
		};
		return renderer;
	}

	protected void guardarExamenFisico(Consulta consultaDatos) {
		List<ConsultaParteCuerpo> examenFisico = new ArrayList<ConsultaParteCuerpo>();
		servicioConsultaParteCuerpo.borrarExamenAnterior(consultaDatos);
		if (ltbExamenFisico.getItemCount() != 0) {
			for (int i = 0; i < ltbExamenFisico.getItemCount(); i++) {
				Listitem listItem = ltbExamenFisico.getItemAtIndex(i);
				if (listItem.isSelected()) {
					ParteCuerpo organo = listItem.getValue();
					Textbox text = (Textbox) listItem.getChildren().get(1)
							.getChildren().get(0);
					String observacion = text.getValue();
					ConsultaParteCuerpo examen = new ConsultaParteCuerpo(
							consultaDatos, organo, observacion);
					examenFisico.add(examen);
				}
			}
			servicioConsultaParteCuerpo.guardar(examenFisico);
		}
	}

	public void guardarAntecedentes(Paciente paciente) {
		List<PacienteAntecedente> antecedentes = new ArrayList<PacienteAntecedente>();
		servicioPacienteAntecedente.borrarAntecedentesPaciente(paciente);
		if (ltbLaborales.getItemCount() != 0) {
			for (int i = 0; i < ltbLaborales.getItemCount(); i++) {
				Listitem listItem = ltbLaborales.getItemAtIndex(i);
				if (listItem.isSelected() && listItem.getContext() != null) {
					Antecedente antecedente = listItem.getValue();
					Textbox text = (Textbox) listItem.getChildren().get(1)
							.getChildren().get(0);
					String observacion = text.getValue();
					PacienteAntecedente pacienteAntecedente = new PacienteAntecedente(
							paciente, antecedente, observacion);
					antecedentes.add(pacienteAntecedente);
				}
			}
		}

		if (ltbFamiliares.getItemCount() != 0) {
			for (int i = 0; i < ltbFamiliares.getItemCount(); i++) {
				Listitem listItem = ltbFamiliares.getItemAtIndex(i);
				if (listItem.isSelected() && listItem.getContext() != null) {
					Antecedente antecedente = listItem.getValue();
					Textbox text = (Textbox) listItem.getChildren().get(1)
							.getChildren().get(0);
					String observacion = text.getValue();
					PacienteAntecedente pacienteAntecedente = new PacienteAntecedente(
							paciente, antecedente, observacion);
					antecedentes.add(pacienteAntecedente);
				}
			}
		}

		if (ltbMedicos.getItemCount() != 0) {
			for (int i = 0; i < ltbMedicos.getItemCount(); i++) {
				Listitem listItem = ltbMedicos.getItemAtIndex(i);
				if (listItem.isSelected() && listItem.getContext() != null) {
					Antecedente antecedente = listItem.getValue();
					Textbox text = (Textbox) listItem.getChildren().get(1)
							.getChildren().get(0);
					String observacion = text.getValue();
					PacienteAntecedente pacienteAntecedente = new PacienteAntecedente(
							paciente, antecedente, observacion);
					antecedentes.add(pacienteAntecedente);
				}
			}
		}
		servicioPacienteAntecedente.guardar(antecedentes);
	}

	public void guardarServicios(Consulta consultaDatos) {
		List<ConsultaServicioExterno> listaServicioExterno = new ArrayList<ConsultaServicioExterno>();
		for (int i = 0; i < ltbServicioExternoAgregados.getItemCount(); i++) {
			Listitem listItem = ltbServicioExternoAgregados.getItemAtIndex(i);
			Integer id = ((Spinner) ((listItem.getChildren().get(3)))
					.getFirstChild()).getValue();
			ServicioExterno servicioExterno = servicioServicioExterno
					.buscar(id);
			double valor = ((Doublespinner) ((listItem.getChildren().get(1)))
					.getFirstChild()).getValue();
			String idProveedor = ((Combobox) ((listItem.getChildren().get(2)))
					.getFirstChild()).getSelectedItem().getContext();
			Proveedor proveedor = servicioProveedor.buscar(Long
					.parseLong(idProveedor));
			ConsultaServicioExterno consultaServicio = new ConsultaServicioExterno(
					consultaDatos, servicioExterno, proveedor, valor);
			listaServicioExterno.add(consultaServicio);
		}
		servicioConsultaServicioExterno.guardar(listaServicioExterno);
	}

	public void guardarEspecialistas(Consulta consultaDatos) {
		List<ConsultaEspecialista> listaConsultaEspecialista = new ArrayList<ConsultaEspecialista>();
		for (int i = 0; i < ltbEspecialistasAgregados.getItemCount(); i++) {
			Listitem listItem = ltbEspecialistasAgregados.getItemAtIndex(i);
			Integer id = ((Spinner) ((listItem.getChildren().get(2)))
					.getFirstChild()).getValue();
			Especialista especialista = servicioEspecialista.buscar(String
					.valueOf(id));
			double valor = ((Doublespinner) ((listItem.getChildren().get(1)))
					.getFirstChild()).getValue();
			ConsultaEspecialista consultaEspecialista = new ConsultaEspecialista(
					consultaDatos, especialista, valor);
			listaConsultaEspecialista.add(consultaEspecialista);
		}
		servicioConsultaEspecialista.guardar(listaConsultaEspecialista);
	}

	public void guardarExamenes(Consulta consultaDatos) {
		List<ConsultaExamen> listaConsultaExamen = new ArrayList<ConsultaExamen>();
		Proveedor proveedor = new Proveedor();
		if (cmbProveedor.getText().compareTo("") != 0)
			proveedor = servicioProveedor.buscar(Long.parseLong(cmbProveedor
					.getSelectedItem().getContext()));
		for (int i = 0; i < ltbExamenesAgregados.getItemCount(); i++) {
			Listitem listItem = ltbExamenesAgregados.getItemAtIndex(i);
			Integer idExamen = ((Spinner) ((listItem.getChildren().get(2)))
					.getFirstChild()).getValue();
			Examen examen = servicioExamen.buscar(idExamen);
			String valor = ((Textbox) ((listItem.getChildren().get(1)))
					.getFirstChild()).getValue();
			// buscar precio del servicio externo
			double precio = 0;
			ConsultaExamen consultaExamen = new ConsultaExamen(consultaDatos,
					examen, valor, proveedor, precio);
			listaConsultaExamen.add(consultaExamen);
		}
		servicioConsultaExamen.guardar(listaConsultaExamen);
	}

	public void guardarDiagnosticos(Consulta consultaDatos) {
		List<ConsultaDiagnostico> listaDiagnostico = new ArrayList<ConsultaDiagnostico>();
		for (int i = 0; i < ltbDiagnosticosAgregados.getItemCount(); i++) {
			Listitem listItem = ltbDiagnosticosAgregados.getItemAtIndex(i);
			Integer idDiagnostico = ((Spinner) ((listItem.getChildren().get(3)))
					.getFirstChild()).getValue();
			Diagnostico diagnostico = servicioDiagnostico.buscar(idDiagnostico);
			String tipo = ((Combobox) ((listItem.getChildren().get(1)))
					.getFirstChild()).getValue();
			String valor = ((Textbox) ((listItem.getChildren().get(2)))
					.getFirstChild()).getValue();
			ConsultaDiagnostico consultaDiagnostico = new ConsultaDiagnostico(
					consultaDatos, diagnostico, tipo, valor);
			listaDiagnostico.add(consultaDiagnostico);
		}
		servicioConsultaDiagnostico.guardar(listaDiagnostico);
	}

	public void guardarMedicinas(Consulta consultaDatos) {
		Date vali = dtbValido.getValue();
		Timestamp validez = new Timestamp(vali.getTime());
		Recipe recipe = new Recipe(0, cmbPrioridad.getValue(), validez,
				fechaHora, horaAuditoria, nombreUsuarioSesion());
		servicioRecipe.guardar(recipe);
		recipe = servicioRecipe.buscarUltimo();
		List<ConsultaMedicina> listaMedicina = new ArrayList<ConsultaMedicina>();
		for (int i = 0; i < ltbMedicinasAgregadas.getItemCount(); i++) {
			Listitem listItem = ltbMedicinasAgregadas.getItemAtIndex(i);
			Integer idMedicina = ((Spinner) ((listItem.getChildren().get(2)))
					.getFirstChild()).getValue();
			Medicina medicina = servicioMedicina.buscar(idMedicina);
			String valor = ((Textbox) ((listItem.getChildren().get(1)))
					.getFirstChild()).getValue();
			ConsultaMedicina consultaMedicina = new ConsultaMedicina(
					consultaDatos, medicina, valor, recipe);
			listaMedicina.add(consultaMedicina);
		}
		servicioConsultaMedicina.guardar(listaMedicina);
	}

	public boolean validar() {
		if (txtCedula.getText().compareTo("") == 0) {
			Messagebox.show("Debe Seleccionar un Paciente", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else {
			if (dtbFechaConsulta.getText().compareTo("") == 0
					|| cmbTipoConsulta.getText().compareTo("") == 0
					|| dtbValido.getText().compareTo("") == 0) {
				Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
						Messagebox.OK, Messagebox.INFORMATION);
				return false;
			} else {
				if (!agregarMedicina()) {
					Messagebox
							.show("Debe Llenar Todos los Campos de la Lista de Medicinas",
									"Informacion", Messagebox.OK,
									Messagebox.INFORMATION);
					return false;
				} else {
					if (!agregarDiagnostico()) {
						Messagebox
								.show("Debe Llenar Todos los Campos de la Lista de Diagnosticos",
										"Informacion", Messagebox.OK,
										Messagebox.INFORMATION);
						return false;
					} else {
						if (!agregarExamen()) {
							Messagebox
									.show("Debe Llenar Todos los Campos de la Lista de Examenes",
											"Informacion", Messagebox.OK,
											Messagebox.INFORMATION);
							return false;
						} else {
							if (!agregarEspecialista()) {
								Messagebox
										.show("Debe Llenar Todos los Campos de la Lista de Especialistas",
												"Informacion", Messagebox.OK,
												Messagebox.INFORMATION);
								return false;
							} else {
								if (!agregarServicio()) {
									Messagebox
											.show("Debe Llenar Todos los Campos de la Lista de Servicios Externos",
													"Informacion",
													Messagebox.OK,
													Messagebox.INFORMATION);
									return false;
								} else {
									if (cmbTipoConsulta.getValue().equals(
											"Control")
											&& cmbConsulta.getText().compareTo(
													"") == 0) {
										Messagebox.show(
												"Debe Llenar Todos los Campos",
												"Informacion", Messagebox.OK,
												Messagebox.INFORMATION);
										return false;
									} else {
										if (ltbMedicinasAgregadas
												.getItemCount() != 0
												&& cmbPrioridad.getText()
														.compareTo("") == 0) {
											Messagebox
													.show("Debe Seleccionar la Prioridad del Recipe",
															"Informacion",
															Messagebox.OK,
															Messagebox.INFORMATION);
											return false;
										} else {
											if (ltbExamenesAgregados
													.getItemCount() != 0
													&& cmbProveedor.getText()
															.compareTo("") == 0) {
												Messagebox
														.show("Debe Seleccionar el Laboratorio que Realizara los Examenes",
																"Informacion",
																Messagebox.OK,
																Messagebox.INFORMATION);
												return false;
											} else {
												if (ltbDiagnosticosAgregados
														.getItemCount() == 0) {
													Messagebox
															.show("Debe seleccionar al menos un diagnostico",
																	"Informacion",
																	Messagebox.OK,
																	Messagebox.INFORMATION);
													return false;
												} else
													return true;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/* Llena la listas al iniciar con todo lo existente */
	private void llenarListas() {
		Consulta consulta = servicioConsulta.buscar(idConsulta);
		Paciente paciente = servicioPaciente.buscarPorCedula(String
				.valueOf(idPaciente));
		List<Paciente> carga = servicioPaciente.buscarParientes(String
				.valueOf(idPaciente));
		ltbCargaFamiliar.setModel(new ListModelList<Paciente>(carga));

		medicinasDisponibles = servicioMedicina.buscarDisponibles(consulta);
		ltbMedicinas
				.setModel(new ListModelList<Medicina>(medicinasDisponibles));
		medicinasAgregadas = servicioConsultaMedicina
				.buscarPorConsulta(consulta);
		ltbMedicinasAgregadas.setModel(new ListModelList<ConsultaMedicina>(
				medicinasAgregadas));
		medicinasResumen = medicinasAgregadas;
		ltbResumenMedicinas.setModel(new ListModelList<ConsultaMedicina>(
				medicinasResumen));

		diagnosticosDisponibles = servicioDiagnostico
				.buscarDisponibles(consulta);
		ltbDiagnosticos.setModel(new ListModelList<Diagnostico>(
				diagnosticosDisponibles));
		diagnosticosAgregados = servicioConsultaDiagnostico
				.buscarPorConsulta(consulta);
		ltbDiagnosticosAgregados
				.setModel(new ListModelList<ConsultaDiagnostico>(
						diagnosticosAgregados));
		diagnosticosResumen = diagnosticosAgregados;
		ltbResumenDiagnosticos.setModel(new ListModelList<ConsultaDiagnostico>(
				diagnosticosResumen));

		examenesDisponibles = servicioExamen.buscarDisponibles(consulta);
		ltbExamenes.setModel(new ListModelList<Examen>(examenesDisponibles));
		examenesAgregado = servicioConsultaExamen.buscarPorConsulta(consulta);
		ltbExamenesAgregados.setModel(new ListModelList<ConsultaExamen>(
				examenesAgregado));
		examenesResumen = examenesAgregado;
		ltbResumenExamenes.setModel(new ListModelList<ConsultaExamen>(
				examenesResumen));

		especialistasDisponibles = servicioEspecialista
				.buscarDisponibles(consulta);
		for (int i = 0; i < especialistasDisponibles.size(); i++) {

			String nombre = especialistasDisponibles.get(i).getNombre();
			String apellido = especialistasDisponibles.get(i).getApellido();
			Especialista especialista = especialistasDisponibles.get(i);
			especialista.setNombre(nombre + " " + apellido);
		}
		ltbEspecialistas.setModel(new ListModelList<Especialista>(
				especialistasDisponibles));
		especialistasAgregados = servicioConsultaEspecialista
				.buscarPorConsulta(consulta);
		ltbEspecialistasAgregados
				.setModel(new ListModelList<ConsultaEspecialista>(
						especialistasAgregados));
		especialistasResumen = especialistasAgregados;
		ltbResumenEspecialistas
				.setModel(new ListModelList<ConsultaEspecialista>(
						especialistasResumen));

		serviciosDisponibles = servicioServicioExterno
				.buscarDisponibles(consulta);
		ltbServicioExterno.setModel(new ListModelList<ServicioExterno>(
				serviciosDisponibles));
		serviciosAgregados = servicioConsultaServicioExterno
				.buscarPorConsulta(consulta);
		ltbServicioExternoAgregados
				.setModel(new ListModelList<ConsultaServicioExterno>(
						serviciosAgregados));
		serviciosResumen = serviciosAgregados;
		ltbResumenServicios
				.setModel(new ListModelList<ConsultaServicioExterno>(
						serviciosResumen));

		//
		Historia historia = servicioHistoria.buscarPorPaciente(paciente);
		intervencionesDisponibles = servicioIntervencion
				.buscarDisponibles(historia);
		ltbIntervenciones.setModel(new ListModelList<Intervencion>(
				intervencionesDisponibles));
		intervencionesAgregadas = servicioHistoriaIntervencion
				.buscarPorHistoria(historia);
		ltbIntervencionesAgregadas
				.setModel(new ListModelList<HistoriaIntervencion>(
						intervencionesAgregadas));

		accidentesComunesDisponibles = servicioAccidente.buscarDisponibles(
				historia, "Accidente Comun");
		ltbAccidentesComunes.setModel(new ListModelList<Accidente>(
				accidentesComunesDisponibles));
		accidentesComunesAgregadas = servicioHistoriaAccidente
				.buscarPorHistoria(historia, "Accidente Comun");
		ltbAccidentesComunesAgregados
				.setModel(new ListModelList<HistoriaAccidente>(
						accidentesComunesAgregadas));

		accidentesLaboralesDisponibles = servicioAccidente.buscarDisponibles(
				historia, "Accidente Laboral");
		ltbAccidentesLaborales.setModel(new ListModelList<Accidente>(
				accidentesLaboralesDisponibles));
		accidentesLaboralesAgregadas = servicioHistoriaAccidente
				.buscarPorHistoria(historia, "Accidente Laboral");
		ltbAccidentesLaboralesAgregados
				.setModel(new ListModelList<HistoriaAccidente>(
						accidentesLaboralesAgregadas));

		List<PacienteAntecedente> laboralesPaciente = servicioPacienteAntecedente
				.buscarAntecedentesPaciente(paciente, "Laboral");

		List<PacienteAntecedente> medicosPaciente = servicioPacienteAntecedente
				.buscarAntecedentesPaciente(paciente, "Medico");

		List<PacienteAntecedente> familiaresPaciente = servicioPacienteAntecedente
				.buscarAntecedentesPaciente(paciente, "Familiar");

		List<HistoriaVacuna> vacunasHistoricas = servicioHistoriaVacuna
				.buscarPorHistoria(historia);

		List<ConsultaParteCuerpo> examenFisicoConsulta = servicioConsultaParteCuerpo
				.buscarPorConsulta(consulta);

		listasMultiples();

		if (!examenFisicoConsulta.isEmpty()) {
			for (int i = 0; i < examenFisicoConsulta.size(); i++) {
				long id = examenFisicoConsulta.get(i).getParte().getIdParte();
				for (int j = 0; j < ltbExamenFisico.getItemCount(); j++) {
					Listitem listItem = ltbExamenFisico.getItemAtIndex(j);
					ParteCuerpo a = listItem.getValue();
					long id2 = a.getIdParte();
					if (id == id2) {
						listItem.setSelected(true);
						Textbox tex2 = (Textbox) listItem.getChildren().get(1)
								.getChildren().get(0);
						tex2.setValue(examenFisicoConsulta.get(i)
								.getObservacion());
						j = ltbExamenFisico.getItemCount();
					}
				}

			}
		}

		if (!vacunasHistoricas.isEmpty()) {
			for (int i = 0; i < vacunasHistoricas.size(); i++) {
				long id = vacunasHistoricas.get(i).getVacuna().getIdVacuna();
				for (int j = 0; j < ltbVacunas.getItemCount(); j++) {
					Listitem listItem = ltbVacunas.getItemAtIndex(j);
					Vacuna a = listItem.getValue();
					long id2 = a.getIdVacuna();
					if (id == id2) {
						listItem.setSelected(true);
						Datebox tex = (Datebox) listItem.getChildren().get(1)
								.getChildren().get(0);
						tex.setValue(vacunasHistoricas.get(i).getFecha());
						j = ltbVacunas.getItemCount();
					}
				}

			}
		}

		if (!familiaresPaciente.isEmpty()) {
			for (int i = 0; i < familiaresPaciente.size(); i++) {
				long id = familiaresPaciente.get(i).getAntecedente()
						.getIdAntecedente();
				for (int j = 0; j < ltbFamiliares.getItemCount(); j++) {
					Listitem listItem = ltbFamiliares.getItemAtIndex(j);
					if (listItem.getContext() != null) {
						Antecedente a = listItem.getValue();
						long id2 = a.getIdAntecedente();
						if (id == id2) {
							listItem.setSelected(true);
							Textbox tex = (Textbox) listItem.getChildren()
									.get(1).getChildren().get(0);
							tex.setValue(familiaresPaciente.get(i)
									.getObservacion());
							j = ltbFamiliares.getItemCount();
						}
					}
				}
			}
		}

		if (!laboralesPaciente.isEmpty()) {
			for (int i = 0; i < laboralesPaciente.size(); i++) {
				long id = laboralesPaciente.get(i).getAntecedente()
						.getIdAntecedente();
				for (int j = 0; j < ltbLaborales.getItemCount(); j++) {
					Listitem listItem = ltbLaborales.getItemAtIndex(j);
					if (listItem.getContext() != null) {
						Antecedente a = listItem.getValue();
						long id2 = a.getIdAntecedente();
						if (id == id2) {
							listItem.setSelected(true);
							Textbox tex = (Textbox) listItem.getChildren()
									.get(1).getChildren().get(0);
							tex.setValue(laboralesPaciente.get(i)
									.getObservacion());
							j = ltbLaborales.getItemCount();
						}
					}
				}
			}
		}

		if (!medicosPaciente.isEmpty()) {
			for (int i = 0; i < medicosPaciente.size(); i++) {
				long id = medicosPaciente.get(i).getAntecedente()
						.getIdAntecedente();
				for (int j = 0; j < ltbMedicos.getItemCount(); j++) {
					Listitem listItem = ltbMedicos.getItemAtIndex(j);
					if (listItem.getContext() != null) {
						Antecedente a = listItem.getValue();
						long id2 = a.getIdAntecedente();
						if (id == id2) {
							listItem.setSelected(true);
							Textbox tex = (Textbox) listItem.getChildren()
									.get(1).getChildren().get(0);
							tex.setValue(medicosPaciente.get(i)
									.getObservacion());
							j = ltbMedicos.getItemCount();
						}
					}
				}
			}
		}

		colorIzquierda();
		colorDerecha();
	}

	private void listasMultiples() {
		for (int i = 0; i < listas.size(); i++) {
			if (!listas.get(i).getId().equals("ltbConsultas")) {
				listas.get(i).setMultiple(false);
				listas.get(i).setCheckmark(false);
				listas.get(i).setMultiple(true);
				listas.get(i).setCheckmark(true);
			}
		}
	}

	@Listen("onClick = #btnGuardarHistoria")
	public void botonHistoria() {
		Paciente paciente = servicioPaciente.buscarPorCedula(txtCedula
				.getValue());
		guardarHistoria(paciente);
		Messagebox.show("Registro Guardado Exitosamente", "Informacion",
				Messagebox.OK, Messagebox.INFORMATION);
	}

	/* Muestra un catalogo de Pacientes */
	@Listen("onClick = #btnBuscarPaciente")
	public void mostrarCatalogoPaciente() throws IOException {
		final List<Paciente> pacientes = servicioPaciente.buscarTodos();
		catalogoPaciente = new Catalogo<Paciente>(divCatalogoPacientes,
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
		catalogoPaciente.setParent(divCatalogoPacientes);
		catalogoPaciente.doModal();
	}

	/* Permite la seleccion de un item del catalogo de pacientes */
	@Listen("onSeleccion = #divCatalogoPacientes")
	public void seleccionarPaciente() {
		limpiarCampos();
		Paciente paciente = catalogoPaciente.objetoSeleccionadoDelCatalogo();
		llenarCampos(paciente);
		idPaciente = Long.valueOf(paciente.getCedula());
		List<Consulta> consultas = servicioConsulta.buscarPorPaciente(paciente);
		ltbConsultas.setModel(new ListModelList<Consulta>(consultas));
		List<Consulta> consultasAccidentes = servicioConsulta
				.buscarPorAccidente(paciente);
		cmbConsulta.setModel(new ListModelList<Consulta>(consultasAccidentes));
		llenarListas();
		catalogoPaciente.setParent(null);
	}

	@Listen("onClick = #ltbConsultas")
	public void seleccionarConsulta() {
		if (ltbConsultas.getItemCount() != 0) {
			Listitem listItem = ltbConsultas.getSelectedItem();
			if (listItem != null) {
				Consulta consulta = listItem.getValue();
				idConsulta = consulta.getIdConsulta();
				idPaciente = Long.parseLong(consulta.getPaciente().getCedula());
				llenarCamposConsulta(consulta);
				llenarCampos(consulta.getPaciente());
				llenarListas();
			}
		}
	}

	private void llenarCamposConsulta(Consulta consulta) {
		limpiarCamposConsulta();
		if (consulta.getAccidente() != null) {
		}
		if (consulta.getConsultaAsociada() != 0) {
			Consulta consultaAsociada = servicioConsulta.buscar(consulta
					.getConsultaAsociada());
			cmbTipoConsulta.setValue("Control");
			lblConsulta.setVisible(true);
			cmbConsulta.setVisible(true);
			cmbConsulta.setValue(consultaAsociada.getAccidente().getNombre());
			cmbConsulta.getSelectedItem().setDescription(
					String.valueOf(consultaAsociada.getFechaConsulta()));

		}
		boolean mostrar = false;
		switch (consulta.getTipoConsulta()) {
		case "Pre-Ingreso":
			mostrar = true;
			break;
		case "Pre-Vacacional":
			mostrar = true;
			break;
		case "Post-Vacacional":
			mostrar = true;
			break;
		case "Egreso":
			mostrar = true;
			break;
		default:
			break;
		}
		if (mostrar) {
			cmbTipoConsulta.setValue("Preventiva");
			cmbTipoPreventiva.setVisible(true);
			cmbTipoPreventiva.setValue(consulta.getTipoConsulta());
		}
		if (mostrar && consulta.getAccidente() == null) {
		}
		if (!mostrar && consulta.getAccidente() != null) {
			cmbTipoConsulta.setValue("Curativa");
		}
		txtMotivo.setValue(consulta.getMotivoConsulta());
		txtEnfermedad.setValue(consulta.getEnfermedadActual());
		spnPeso.setValue(consulta.getPeso());
		spnEstatura.setValue(consulta.getEstatura());
		spnOmbligo.setValue(consulta.getPerimetroOmbligo());
		spnPlena.setValue(consulta.getPerimetroPlena());
		spnForzada.setValue(consulta.getPerimetroForzada());
		spnCardiaca.setValue(consulta.getFrecuencia());
		frecuencia11.setValue(consulta.getFrecuenciaReposo());
		frecuencia12.setValue(consulta.getFrecuenciaEsfuerzo());
		frecuencia13.setValue(consulta.getFrecuenciaPost());
		s11.setValue(consulta.getSistolicaPrimera());
		s12.setValue(consulta.getSistolicaSegunda());
		s13.setValue(consulta.getSistolicaTercera());
		d11.setValue(consulta.getDiastolicaPrimera());
		d12.setValue(consulta.getDiastolicaSegunda());
		d13.setValue(consulta.getDiastolicaTercera());
		ex11.setValue(consulta.getExtraReposo());
		ex12.setValue(consulta.getExtraEsfuerzo());
		ex13.setValue(consulta.getExtraPost());
		if (consulta.getApto() != null) {
			boolean apto = consulta.getApto();
			if (apto)
				rdoSiApto.setChecked(true);
			else
				rdoNoApto.setChecked(true);
		}
		boolean ritmico = consulta.getRitmico();
		if (ritmico)
			rdoSiRitmico.setChecked(true);
		else
			rdoNoRitmico.setChecked(true);
		boolean reposo = consulta.getReposo();
		if (reposo)
			rdoSiReposo.setChecked(true);
		else
			rdoNoReposo.setChecked(true);
		boolean ritmico1 = consulta.getRitmicoReposo();
		if (ritmico1)
			rdoSiRitmicoF1.setChecked(true);
		else
			rdoNoRitmicoF1.setChecked(true);
		boolean ritmico2 = consulta.getRitmicoEsfuerzo();
		if (ritmico2)
			rdoSiRitmicoF2.setChecked(true);
		else
			rdoNoRitmicoF2.setChecked(true);
		boolean ritmico3 = consulta.getRitmicoPost();
		if (ritmico3)
			rdoSiRitmicoF3.setChecked(true);
		else
			rdoNoRitmicoF3.setChecked(true);
		calcularIMC();
	}

	private void llenarCampos(Paciente paciente) {
		// TODO Auto-generated method stub
		Historia historia = paciente.getHistoria();
		if (historia != null) {
			btnGuardarHistoria.setVisible(true);
			llenarCamposHistoria(historia);
		}
		txtCedula.setValue(paciente.getCedula());
		lblNombres.setValue(paciente.getPrimerNombre() + " "
				+ paciente.getSegundoNombre());
		lblApellidos.setValue(paciente.getPrimerApellido() + " "
				+ paciente.getSegundoApellido());
		if (paciente.getEmpresa() != null)
			lblEmpresa.setValue(paciente.getEmpresa().getNombre());
		lblCedula.setValue(paciente.getCedula());
		lblCiudad.setValue(paciente.getCiudadVivienda().getNombre());
		lblFicha.setValue(paciente.getFicha());
		lblAlergias.setValue(paciente.getObservacionAlergias());
		lblLugarNac.setValue(paciente.getLugarNacimiento());
		lblSexo.setValue(paciente.getSexo());
		lblEstadoCivil.setValue(paciente.getEstadoCivil());
		lblGrupoSanguineo.setValue(paciente.getGrupoSanguineo());
		lblMano.setValue(paciente.getMano());
		lblOrigen.setValue(paciente.getOrigenDiscapacidad());
		lblTipoDiscapacidad.setValue(paciente.getTipoDiscapacidad());
		lblObservacionDiscapacidad.setValue(paciente
				.getObservacionDiscapacidad());
		lblCargo.setValue(paciente.getCargo());
		lblDireccion.setValue(paciente.getDireccion());
		lblTelefono1.setValue(paciente.getTelefono1());
		lblTelefono2.setValue(paciente.getTelefono2());
		lblCorreo.setValue(paciente.getEmail());
		lblNombresE.setValue(paciente.getNombresEmergencia());
		lblApellidosE.setValue(paciente.getApellidosEmergencia());
		lblTelefono1E.setValue(paciente.getTelefono1Emergencia());
		lblTelefono2E.setValue(paciente.getTelefono2Emergencia());
		lblParentesco.setValue(paciente.getParentescoEmergencia());
		// lblParentescoFamiliar.setValue(paciente.getParentescoFamiliar());
		lblEdad.setValue(String.valueOf(paciente.getEdad()));
		lblEstatura.setValue(String.valueOf(paciente.getEstatura()));
		lblPeso.setValue(String.valueOf(paciente.getPeso()));
		// lblCiudad.setValue(paciente.getCiudadVivienda().getNombre());

		if (paciente.isAlergia())
			lblAlergico.setValue("SI");
		else
			lblAlergico.setValue("NO");

		if (paciente.isTrabajador())
			lblTrabajador.setValue("SI");
		else
			lblTrabajador.setValue("NO");

		if (paciente.isDiscapacidad())
			lblDiscapacidad.setValue("SI");
		else
			lblDiscapacidad.setValue("NO");

		if (paciente.isLentes())
			lblLentes.setValue("SI");
		else
			lblLentes.setValue("NO");

		BufferedImage imag;
		if (paciente.getImagen() != null) {
			imagenPaciente.setVisible(true);
			try {
				imag = ImageIO.read(new ByteArrayInputStream(paciente
						.getImagen()));
				imagenPaciente.setContent(imag);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			imagenPaciente.setVisible(false);

		if (paciente.isTrabajador()) {
			List<Paciente> familiares = servicioPaciente
					.buscarParientes(paciente.getCedula());
			for (int i = 0; i < familiares.size(); i++) {

				String nombre = familiares.get(i).getPrimerNombre();
				String apellido = familiares.get(i).getPrimerApellido();
				Paciente pacienteFor = familiares.get(i);
				pacienteFor.setPrimerNombre(nombre + " " + apellido);
			}
			ltbCargaFamiliar.setModel(new ListModelList<Paciente>(familiares));
			tabCarga.setVisible(true);
			gpxLaborales.setVisible(true);
		} else {
			tabCarga.setVisible(false);
			gpxLaborales.setVisible(false);
		}
	}

	@Listen("onClick = #pasar1Medicina")
	public void derechaMedicina() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem = ltbMedicinas.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					Medicina Medicina = listItem.get(i).getValue();
					medicinasDisponibles.remove(Medicina);
					ConsultaMedicina consultaMedicina = new ConsultaMedicina();
					consultaMedicina.setMedicina(Medicina);
					medicinasAgregadas.add(consultaMedicina);
					ltbMedicinasAgregadas
							.setModel(new ListModelList<ConsultaMedicina>(
									medicinasAgregadas));
					listitemEliminar.add(listItem.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbMedicinas.removeItemAt(listitemEliminar.get(i).getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar2Medicina")
	public void izquierdaMedicina() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem2 = ltbMedicinasAgregadas.getItems();
		if (listItem2.size() != 0) {
			for (int i = 0; i < listItem2.size(); i++) {
				if (listItem2.get(i).isSelected()) {
					ConsultaMedicina consultaMedicina = listItem2.get(i)
							.getValue();
					medicinasAgregadas.remove(consultaMedicina);
					medicinasDisponibles.add(consultaMedicina.getMedicina());
					ltbMedicinas.setModel(new ListModelList<Medicina>(
							medicinasDisponibles));
					listitemEliminar.add(listItem2.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbMedicinasAgregadas.removeItemAt(listitemEliminar.get(i)
					.getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar1Diagnostico")
	public void derechaDiagnostico() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem = ltbDiagnosticos.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					Diagnostico diagnostico = listItem.get(i).getValue();
					diagnosticosDisponibles.remove(diagnostico);
					ConsultaDiagnostico consultaDiagnostico = new ConsultaDiagnostico();
					consultaDiagnostico.setDiagnostico(diagnostico);
					diagnosticosAgregados.add(consultaDiagnostico);
					ltbDiagnosticosAgregados
							.setModel(new ListModelList<ConsultaDiagnostico>(
									diagnosticosAgregados));
					listitemEliminar.add(listItem.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbDiagnosticos.removeItemAt(listitemEliminar.get(i).getIndex());
		}
		listasMultiples();

		colorDerecha();
	}

	@Listen("onClick = #pasar2Diagnostico")
	public void izquierdaDiagnostico() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem2 = ltbDiagnosticosAgregados.getItems();
		if (listItem2.size() != 0) {
			for (int i = 0; i < listItem2.size(); i++) {
				if (listItem2.get(i).isSelected()) {
					ConsultaDiagnostico consultaDiagnostico = listItem2.get(i)
							.getValue();
					diagnosticosAgregados.remove(consultaDiagnostico);
					diagnosticosDisponibles.add(consultaDiagnostico
							.getDiagnostico());
					ltbDiagnosticos.setModel(new ListModelList<Diagnostico>(
							diagnosticosDisponibles));
					listitemEliminar.add(listItem2.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbDiagnosticosAgregados.removeItemAt(listitemEliminar.get(i)
					.getIndex());
		}
		listasMultiples();

		colorIzquierda();
	}

	@Listen("onClick = #pasar1Examen")
	public void derechaExamen() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem = ltbExamenes.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					Examen examen = listItem.get(i).getValue();
					examenesDisponibles.remove(examen);
					ConsultaExamen consultaExamen = new ConsultaExamen();
					consultaExamen.setExamen(examen);
					examenesAgregado.add(consultaExamen);
					ltbExamenesAgregados
							.setModel(new ListModelList<ConsultaExamen>(
									examenesAgregado));
					listitemEliminar.add(listItem.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbExamenes.removeItemAt(listitemEliminar.get(i).getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar2Examen")
	public void izquierdaExamen() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem2 = ltbExamenesAgregados.getItems();
		if (listItem2.size() != 0) {
			for (int i = 0; i < listItem2.size(); i++) {
				if (listItem2.get(i).isSelected()) {
					ConsultaExamen consultaExamen = listItem2.get(i).getValue();
					examenesAgregado.remove(consultaExamen);
					examenesDisponibles.add(consultaExamen.getExamen());
					ltbExamenes.setModel(new ListModelList<Examen>(
							examenesDisponibles));
					listitemEliminar.add(listItem2.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbExamenesAgregados.removeItemAt(listitemEliminar.get(i)
					.getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar1Especialista")
	public void derechaEspecialista() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem = ltbEspecialistas.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					Especialista especialista = listItem.get(i).getValue();
					especialistasDisponibles.remove(especialista);
					ConsultaEspecialista consultaEspecialista = new ConsultaEspecialista();
					consultaEspecialista.setEspecialista(especialista);
					consultaEspecialista.setCosto(especialista.getCosto());
					especialistasAgregados.add(consultaEspecialista);
					ltbEspecialistasAgregados
							.setModel(new ListModelList<ConsultaEspecialista>(
									especialistasAgregados));
					listitemEliminar.add(listItem.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbEspecialistas.removeItemAt(listitemEliminar.get(i).getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar2Especialista")
	public void izquierdaEspecialista() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem2 = ltbEspecialistasAgregados.getItems();
		if (listItem2.size() != 0) {
			for (int i = 0; i < listItem2.size(); i++) {
				if (listItem2.get(i).isSelected()) {
					ConsultaEspecialista consultaEspecialista = listItem2
							.get(i).getValue();
					especialistasAgregados.remove(consultaEspecialista);
					especialistasDisponibles.add(consultaEspecialista
							.getEspecialista());
					ltbEspecialistas.setModel(new ListModelList<Especialista>(
							especialistasDisponibles));
					listitemEliminar.add(listItem2.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbEspecialistasAgregados.removeItemAt(listitemEliminar.get(i)
					.getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar1AccidentesLaborales")
	public void derechaLaborales() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem = ltbAccidentesLaborales.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					Accidente accidente = listItem.get(i).getValue();
					accidentesLaboralesDisponibles.remove(accidente);
					HistoriaAccidente historia = new HistoriaAccidente();
					historia.setAccidente(accidente);
					accidentesLaboralesAgregadas.add(historia);
					ltbAccidentesLaboralesAgregados
							.setModel(new ListModelList<HistoriaAccidente>(
									accidentesLaboralesAgregadas));
					listitemEliminar.add(listItem.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbAccidentesLaborales.removeItemAt(listitemEliminar.get(i)
					.getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar2AccidentesLaborales")
	public void izquierdaLAborales() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem2 = ltbAccidentesLaboralesAgregados.getItems();
		if (listItem2.size() != 0) {
			for (int i = 0; i < listItem2.size(); i++) {
				if (listItem2.get(i).isSelected()) {
					HistoriaAccidente historia = listItem2.get(i).getValue();
					accidentesLaboralesAgregadas.remove(historia);
					accidentesLaboralesDisponibles.add(historia.getAccidente());
					ltbAccidentesLaborales
							.setModel(new ListModelList<Accidente>(
									accidentesLaboralesDisponibles));
					listitemEliminar.add(listItem2.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbAccidentesLaboralesAgregados.removeItemAt(listitemEliminar
					.get(i).getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar1AccidentesComunes")
	public void derechaComunes() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem = ltbAccidentesComunes.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					Accidente accidente = listItem.get(i).getValue();
					accidentesComunesDisponibles.remove(accidente);
					HistoriaAccidente historia = new HistoriaAccidente();
					historia.setAccidente(accidente);
					accidentesComunesAgregadas.add(historia);
					ltbAccidentesComunesAgregados
							.setModel(new ListModelList<HistoriaAccidente>(
									accidentesComunesAgregadas));
					listitemEliminar.add(listItem.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbAccidentesComunes.removeItemAt(listitemEliminar.get(i)
					.getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar2AccidentesComunes")
	public void izquierdaComunes() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem2 = ltbAccidentesComunesAgregados.getItems();
		if (listItem2.size() != 0) {
			for (int i = 0; i < listItem2.size(); i++) {
				if (listItem2.get(i).isSelected()) {
					HistoriaAccidente historia = listItem2.get(i).getValue();
					accidentesComunesAgregadas.remove(historia);
					accidentesComunesDisponibles.add(historia.getAccidente());
					ltbAccidentesComunes.setModel(new ListModelList<Accidente>(
							accidentesComunesDisponibles));
					listitemEliminar.add(listItem2.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbAccidentesComunesAgregados.removeItemAt(listitemEliminar.get(i)
					.getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar1Intervenciones")
	public void derechaIntervencion() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem = ltbIntervenciones.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					Intervencion intervencion = listItem.get(i).getValue();
					intervencionesDisponibles.remove(intervencion);
					HistoriaIntervencion historia = new HistoriaIntervencion();
					historia.setIntervencion(intervencion);
					intervencionesAgregadas.add(historia);
					ltbIntervencionesAgregadas
							.setModel(new ListModelList<HistoriaIntervencion>(
									intervencionesAgregadas));
					listitemEliminar.add(listItem.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbIntervenciones.removeItemAt(listitemEliminar.get(i).getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar2Intervenciones")
	public void izquierdaIntervencion() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem2 = ltbIntervencionesAgregadas.getItems();
		if (listItem2.size() != 0) {
			for (int i = 0; i < listItem2.size(); i++) {
				if (listItem2.get(i).isSelected()) {
					HistoriaIntervencion historia = listItem2.get(i).getValue();
					intervencionesAgregadas.remove(historia);
					intervencionesDisponibles.add(historia.getIntervencion());
					ltbIntervenciones.setModel(new ListModelList<Intervencion>(
							intervencionesDisponibles));
					listitemEliminar.add(listItem2.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbIntervencionesAgregadas.removeItemAt(listitemEliminar.get(i)
					.getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar1ServicioExterno")
	public void derechaServicioExterno() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem = ltbServicioExterno.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					ServicioExterno servicio = listItem.get(i).getValue();
					serviciosDisponibles.remove(servicio);
					ConsultaServicioExterno consultaServicio = new ConsultaServicioExterno();
					consultaServicio.setServicioExterno(servicio);
					serviciosAgregados.add(consultaServicio);
					ltbServicioExternoAgregados
							.setModel(new ListModelList<ConsultaServicioExterno>(
									serviciosAgregados));
					listitemEliminar.add(listItem.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbServicioExterno.removeItemAt(listitemEliminar.get(i).getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #pasar2ServicioExterno")
	public void izquierdaServicioExterno() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem2 = ltbServicioExternoAgregados.getItems();
		if (listItem2.size() != 0) {
			for (int i = 0; i < listItem2.size(); i++) {
				if (listItem2.get(i).isSelected()) {
					ConsultaServicioExterno consultaServicio = listItem2.get(i)
							.getValue();
					serviciosAgregados.remove(consultaServicio);
					serviciosDisponibles.add(consultaServicio
							.getServicioExterno());
					ltbServicioExterno
							.setModel(new ListModelList<ServicioExterno>(
									serviciosDisponibles));
					listitemEliminar.add(listItem2.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbServicioExternoAgregados.removeItemAt(listitemEliminar.get(i)
					.getIndex());
		}
		listasMultiples();
	}

	@Listen("onClick = #btnAgregarMedicinas")
	public boolean agregarMedicina() {
		boolean falta = false;
		medicinasResumen.clear();
		System.out.println("arriba" + medicinasAgregadas.size());
		if (ltbMedicinasAgregadas.getItemCount() != 0) {
			ConsultaMedicina consultaMedicina = new ConsultaMedicina();
			List<Listitem> listItem2 = ltbMedicinasAgregadas.getItems();
			for (int i = 0; i < ltbMedicinasAgregadas.getItemCount(); i++) {
				Listitem listItem = ltbMedicinasAgregadas.getItemAtIndex(i);
				consultaMedicina = new ConsultaMedicina();
				consultaMedicina = listItem2.get(i).getValue();
				String valor = ((Textbox) ((listItem.getChildren().get(1)))
						.getFirstChild()).getValue();
				if (valor.equals("")) {
					falta = true;
				}
				consultaMedicina.setDosis(valor);
				medicinasResumen.add(consultaMedicina);
			}
			ltbResumenMedicinas.setModel(new ListModelList<ConsultaMedicina>(
					medicinasResumen));
			System.out.println("abajo" + medicinasAgregadas.size());
		}
		// medicinasResumen.clear();
		if (falta)
			return false;
		else
			return true;
	}

	@Listen("onClick = #btnAgregarDiagnosticos")
	public boolean agregarDiagnostico() {
		boolean falta = false;
		diagnosticosResumen.clear();
		if (ltbDiagnosticosAgregados.getItemCount() != 0) {
			ConsultaDiagnostico consultaDiagnostico = new ConsultaDiagnostico();
			List<Listitem> listItem2 = ltbDiagnosticosAgregados.getItems();
			for (int i = 0; i < ltbDiagnosticosAgregados.getItemCount(); i++) {
				Listitem listItem = ltbDiagnosticosAgregados.getItemAtIndex(i);
				consultaDiagnostico = new ConsultaDiagnostico();
				consultaDiagnostico = listItem2.get(i).getValue();
				String valor = ((Textbox) ((listItem.getChildren().get(1)))
						.getFirstChild()).getValue();
				if (valor.equals("")) {
					falta = true;
				}
				consultaDiagnostico.setObservacion(valor);
				diagnosticosResumen.add(consultaDiagnostico);
			}
			ltbResumenDiagnosticos
					.setModel(new ListModelList<ConsultaDiagnostico>(
							diagnosticosResumen));
		}
		// diagnosticosResumen.clear();
		if (falta)
			return false;
		else
			return true;
	}

	@Listen("onClick = #btnAgregarExamenes")
	public boolean agregarExamen() {
		boolean falta = false;
		examenesResumen.clear();
		if (ltbExamenesAgregados.getItemCount() != 0) {
			ConsultaExamen consulta = new ConsultaExamen();
			List<Listitem> listItem2 = ltbExamenesAgregados.getItems();
			for (int i = 0; i < ltbExamenesAgregados.getItemCount(); i++) {
				Listitem listItem = ltbExamenesAgregados.getItemAtIndex(i);
				consulta = new ConsultaExamen();
				consulta = listItem2.get(i).getValue();
				String valor = ((Textbox) ((listItem.getChildren().get(1)))
						.getFirstChild()).getValue();
				if (valor.equals("")) {
					falta = true;
				}
				consulta.setObservacion(valor);
				examenesResumen.add(consulta);
			}
			ltbResumenExamenes.setModel(new ListModelList<ConsultaExamen>(
					examenesResumen));
		}
		// examenesResumen.clear();
		if (falta)
			return false;
		else
			return true;
	}

	@Listen("onClick = #btnAgregarEspecialistas")
	public boolean agregarEspecialista() {
		boolean falta = false;
		especialistasResumen.clear();
		if (ltbEspecialistasAgregados.getItemCount() != 0) {
			ConsultaEspecialista consulta = new ConsultaEspecialista();
			List<Listitem> listItem2 = ltbEspecialistasAgregados.getItems();
			for (int i = 0; i < ltbEspecialistasAgregados.getItemCount(); i++) {
				Listitem listItem = ltbEspecialistasAgregados.getItemAtIndex(i);
				consulta = new ConsultaEspecialista();
				consulta = listItem2.get(i).getValue();
				double valor = ((Doublespinner) ((listItem.getChildren().get(1)))
						.getFirstChild()).getValue();
				if (valor == 0) {
					falta = true;
				}
				consulta.setCosto(valor);
				especialistasResumen.add(consulta);
			}
			ltbResumenEspecialistas
					.setModel(new ListModelList<ConsultaEspecialista>(
							especialistasResumen));
		}
		// examenesResumen.clear();
		if (falta)
			return false;
		else
			return true;
	}

	@Listen("onClick = #btnAgregarServicios")
	public boolean agregarServicio() {
		boolean falta = false;
		serviciosResumen.clear();
		if (ltbServicioExternoAgregados.getItemCount() != 0) {
			ConsultaServicioExterno consulta = new ConsultaServicioExterno();
			List<Listitem> listItem2 = ltbServicioExternoAgregados.getItems();
			for (int i = 0; i < ltbServicioExternoAgregados.getItemCount(); i++) {
				Listitem listItem = ltbServicioExternoAgregados
						.getItemAtIndex(i);
				consulta = new ConsultaServicioExterno();
				consulta = listItem2.get(i).getValue();
				double valor = ((Doublespinner) ((listItem.getChildren().get(1)))
						.getFirstChild()).getValue();
				String proveedor = ((Combobox) ((listItem.getChildren().get(2)))
						.getFirstChild()).getSelectedItem().getContext();
				Proveedor provee = servicioProveedor.buscar(Long
						.parseLong(proveedor));
				if (valor == 0) {
					falta = true;
				}
				consulta.setProveedor(provee);
				consulta.setCosto(valor);
				serviciosResumen.add(consulta);
			}
			ltbResumenServicios
					.setModel(new ListModelList<ConsultaServicioExterno>(
							serviciosResumen));
		}
		// serviciosResumen.clear();
		if (falta)
			return false;
		else
			return true;
	}

	public void limpiarListas() {
		List<List<?>> limpiador = new ArrayList<List<?>>();
		limpiador.add(diagnosticosAgregados);
		limpiador.add(diagnosticosDisponibles);
		limpiador.add(diagnosticosResumen);
		limpiador.add(especialistasAgregados);
		limpiador.add(especialistasDisponibles);
		limpiador.add(especialistasResumen);
		limpiador.add(examenesAgregado);
		limpiador.add(examenesDisponibles);
		limpiador.add(examenesResumen);
		limpiador.add(medicinasAgregadas);
		limpiador.add(medicinasDisponibles);
		limpiador.add(medicinasResumen);
		limpiador.add(serviciosAgregados);
		limpiador.add(serviciosDisponibles);
		limpiador.add(serviciosResumen);
		limpiador.add(accidentesComunesAgregadas);
		limpiador.add(accidentesComunesDisponibles);
		limpiador.add(accidentesLaboralesAgregadas);
		limpiador.add(accidentesLaboralesDisponibles);
		limpiador.add(intervencionesAgregadas);
		limpiador.add(intervencionesDisponibles);
		for (int q = 0; q < limpiador.size(); q++) {
			limpiador.get(q).clear();
		}
	}

	public void limpiarListBox() {
		for (int i = 0; i < listas.size(); i++) {
			if (!listas.get(i).getId().equals("ltbLaborales")) {
				if (!listas.get(i).getId().equals("ltbMedicos")) {
					if (!listas.get(i).getId().equals("ltbFamiliares")) {
						if (!listas.get(i).getId().equals("ltbVacunas")) {
							if (!listas.get(i).getId()
									.equals("ltbExamenFisico")) {
								listas.get(i).getItems().clear();
							}
						}
					}
				}

			}
		}
	}

	private void limpiarCamposConsulta() {
		txtMotivo.setValue("");
		txtEnfermedad.setValue("");
		txtCedula.setValue("");
		dtbFechaConsulta.setValue(fecha);
		dtbValido.setValue(fecha);
		cmbPrioridad.setValue("");
		cmbProveedor.setValue("");
		cmbConsulta.setValue("");
		cmbConsulta.setVisible(false);
		lblConsulta.setVisible(false);
		cmbTipoConsulta.setValue("");
		cmbTipoPreventiva.setValue("");
		if (rdoSiReposo.isChecked())
			rdoSiReposo.setChecked(false);
		if (rdoNoReposo.isChecked())
			rdoNoReposo.setChecked(false);
		if (rdoSiApto.isChecked())
			rdoSiApto.setChecked(false);
		if (rdoNoApto.isChecked())
			rdoNoApto.setChecked(false);
	}

	public void limpiarCampos() {
		btnGuardarHistoria.setVisible(false);
		idPaciente = 0;
		idConsulta = 0;
		limpiarListBox();
		limpiarListas();
		llenarListas();
		limpiarCamposConsulta();

		for (int i = 0; i < ltbLaborales.getItemCount(); i++) {
			Listitem listItem = ltbLaborales.getItemAtIndex(i);
			if (listItem.isSelected() && listItem.getContext() != null) {
				Textbox tex = (Textbox) listItem.getChildren().get(1)
						.getChildren().get(0);
				tex.setValue("");
				tex.setPlaceholder("Ingrese una Observacion");
				listItem.setSelected(false);
			}
		}
		for (int i = 0; i < ltbMedicos.getItemCount(); i++) {
			Listitem listItem = ltbMedicos.getItemAtIndex(i);
			if (listItem.isSelected() && listItem.getContext() != null) {
				Textbox tex = (Textbox) listItem.getChildren().get(1)
						.getChildren().get(0);
				tex.setValue("");
				tex.setPlaceholder("Ingrese una Observacion");
				listItem.setSelected(false);
			}
		}
		for (int i = 0; i < ltbFamiliares.getItemCount(); i++) {
			Listitem listItem = ltbFamiliares.getItemAtIndex(i);
			if (listItem.isSelected() && listItem.getContext() != null) {
				Textbox tex = (Textbox) listItem.getChildren().get(1)
						.getChildren().get(0);
				tex.setValue("");
				tex.setPlaceholder("Ingrese una Observacion");
				listItem.setSelected(false);
			}
		}
		for (int i = 0; i < ltbExamenFisico.getItemCount(); i++) {
			Listitem listItem = ltbExamenFisico.getItemAtIndex(i);
			if (listItem.isSelected()) {
				Textbox tex = (Textbox) listItem.getChildren().get(1)
						.getChildren().get(0);
				tex.setValue("");
				tex.setPlaceholder("Ingrese una Observacion");
				listItem.setSelected(false);
			}
		}
		lblNombres.setValue("");
		lblCedula.setValue("");
		lblApellidos.setValue("");
		lblEmpresa.setValue("");
		imagenPaciente.setVisible(false);
		lblFicha.setValue("");
		lblAlergico.setValue("");
		lblLugarNac.setValue("");
		lblSexo.setValue("");
		lblEstadoCivil.setValue("");
		lblGrupoSanguineo.setValue("");
		lblMano.setValue("");
		lblOrigen.setValue("");
		lblTipoDiscapacidad.setValue("");
		lblObservacionDiscapacidad.setValue("");
		lblCargo.setValue("");
		lblDireccion.setValue("");
		lblTelefono1.setValue("");
		lblTelefono2.setValue("");
		lblCorreo.setValue("");
		lblNombresE.setValue("");
		lblApellidosE.setValue("");
		lblTelefono1E.setValue("");
		lblTelefono2E.setValue("");
		lblParentesco.setValue("");
		lblPeso.setValue("");
		lblEdad.setValue("");
		lblEstatura.setValue("");
		lblCiudad.setValue("");
		lblAlergias.setValue("");
		lblTrabajador.setValue("");
		lblDiscapacidad.setValue("");
		lblLentes.setValue("");
		lblIndice.setValue("");
		spnPeso.setValue((double) 0);
		spnEstatura.setValue((double) 0);
		spnOmbligo.setValue((double) 0);
		spnPlena.setValue((double) 0);
		spnForzada.setValue((double) 0);
		spnCardiaca.setValue(0);
		frecuencia11.setValue(0);
		frecuencia12.setValue(0);
		frecuencia13.setValue(0);
		s11.setValue(0);
		s12.setValue(0);
		s13.setValue(0);
		d11.setValue(0);
		d12.setValue(0);
		d13.setValue(0);
		ex11.setValue(0);
		ex12.setValue(0);
		ex13.setValue(0);
		if (rdoSiRitmico.isChecked())
			rdoSiRitmico.setChecked(true);
		if (rdoNoRitmico.isChecked())
			rdoNoRitmico.setChecked(true);
		if (rdoSiRitmicoF1.isChecked())
			rdoSiRitmicoF1.setChecked(true);
		if (rdoNoRitmicoF1.isChecked())
			rdoNoRitmicoF1.setChecked(true);
		if (rdoSiRitmicoF2.isChecked())
			rdoSiRitmicoF2.setChecked(true);
		if (rdoNoRitmicoF2.isChecked())
			rdoNoRitmicoF2.setChecked(true);
		if (rdoSiRitmicoF3.isChecked())
			rdoSiRitmicoF3.setChecked(true);
		if (rdoNoRitmicoF3.isChecked())
			rdoNoRitmicoF3.setChecked(true);
		if (rdoSiPeso.isChecked())
			rdoSiPeso.setChecked(false);
		if (rdoNoPeso.isChecked())
			rdoNoPeso.setChecked(false);
		if (rdoSiAccidenteAlcohol.isChecked())
			rdoSiAccidenteAlcohol.setChecked(false);
		if (rdoNoAccidenteAlcohol.isChecked())
			rdoNoAccidenteAlcohol.setChecked(false);
		if (rdoSiAlcohol.isChecked())
			rdoSiAlcohol.setChecked(false);
		if (rdoNoAlcohol.isChecked())
			rdoNoAlcohol.setChecked(false);
		if (rdoSiAnticonceptivos.isChecked())
			rdoSiAnticonceptivos.setChecked(false);
		if (rdoNoAnticonceptivos.isChecked())
			rdoNoAnticonceptivos.setChecked(false);
		if (rdoSiBorracho.isChecked())
			rdoSiBorracho.setChecked(false);
		if (rdoNoBorracho.isChecked())
			rdoNoBorracho.setChecked(false);
		if (rdoSiCabeza.isChecked())
			rdoSiCabeza.setChecked(false);
		if (rdoNoCabeza.isChecked())
			rdoNoCabeza.setChecked(false);
		if (rdoSiCafe.isChecked())
			rdoSiCafe.setChecked(false);
		if (rdoNoCafe.isChecked())
			rdoNoCafe.setChecked(false);
		if (rdoSiCigarro.isChecked())
			rdoSiCigarro.setChecked(false);
		if (rdoNoCigarro.isChecked())
			rdoNoCigarro.setChecked(false);
		if (rdoSiDolor.isChecked())
			rdoSiDolor.setChecked(false);
		if (rdoNoDolor.isChecked())
			rdoNoDolor.setChecked(false);
		if (rdoSiDrogas.isChecked())
			rdoSiDrogas.setChecked(false);
		if (rdoNoDrogas.isChecked())
			rdoNoDrogas.setChecked(false);
		if (rdoSiDuerme.isChecked())
			rdoSiDuerme.setChecked(false);
		if (rdoNoDuerme.isChecked())
			rdoNoDuerme.setChecked(false);
		if (rdoSiEco.isChecked())
			rdoSiEco.setChecked(false);
		if (rdoNoEco.isChecked())
			rdoNoEco.setChecked(false);
		if (rdoSiEmbarazada.isChecked())
			rdoSiEmbarazada.setChecked(false);
		if (rdoNoEmbarazada.isChecked())
			rdoNoEmbarazada.setChecked(false);
		if (rdoSiEndurecimiento.isChecked())
			rdoSiEndurecimiento.setChecked(false);
		if (rdoNoEndurecimiento.isChecked())
			rdoNoEndurecimiento.setChecked(false);
		if (rdoSiEnfermedad.isChecked())
			rdoSiEnfermedad.setChecked(false);
		if (rdoNoEnfermedad.isChecked())
			rdoNoEnfermedad.setChecked(false);
		if (rdoSiEsterilizacion.isChecked())
			rdoSiEsterilizacion.setChecked(false);
		if (rdoNoEsterilizacion.isChecked())
			rdoNoEsterilizacion.setChecked(false);
		if (rdoSiETS.isChecked())
			rdoSiETS.setChecked(false);
		if (rdoNoETS.isChecked())
			rdoNoETS.setChecked(false);
		if (rdoSiExtra.isChecked())
			rdoSiExtra.setChecked(false);
		if (rdoNoExtra.isChecked())
			rdoNoExtra.setChecked(false);
		if (rdoSiFisica.isChecked())
			rdoSiFisica.setChecked(false);
		if (rdoNoFisica.isChecked())
			rdoNoFisica.setChecked(false);
		if (rdoSiFlujo.isChecked())
			rdoSiFlujo.setChecked(false);
		if (rdoNoFlujo.isChecked())
			rdoNoFlujo.setChecked(false);
		if (rdoSiFumaActualmente.isChecked())
			rdoSiFumaActualmente.setChecked(false);
		if (rdoNoFumaActualmente.isChecked())
			rdoNoFumaActualmente.setChecked(false);
		if (rdoSiInfeccion.isChecked())
			rdoSiInfeccion.setChecked(false);
		if (rdoNoInfeccion.isChecked())
			rdoNoInfeccion.setChecked(false);
		if (rdoSiIntra.isChecked())
			rdoSiIntra.setChecked(false);
		if (rdoNoIntra.isChecked())
			rdoNoIntra.setChecked(false);
		if (rdoSiMamografia.isChecked())
			rdoSiMamografia.setChecked(false);
		if (rdoNoMamografia.isChecked())
			rdoNoMamografia.setChecked(false);
		if (rdoSiMedicamentoDrogas.isChecked())
			rdoSiMedicamentoDrogas.setChecked(false);
		if (rdoNoMedicamentoDrogas.isChecked())
			rdoNoMedicamentoDrogas.setChecked(false);
		if (rdoSiMedico.isChecked())
			rdoSiMedico.setChecked(false);
		if (rdoNoMedico.isChecked())
			rdoNoMedico.setChecked(false);
		if (rdoSiMenstrual.isChecked())
			rdoSiMenstrual.setChecked(false);
		if (rdoNoMenstrual.isChecked())
			rdoNoMenstrual.setChecked(false);
		if (rdoSiMesAlcohol.isChecked())
			rdoSiMesAlcohol.setChecked(false);
		if (rdoNoMesAlcohol.isChecked())
			rdoNoMesAlcohol.setChecked(false);
		if (rdoSiOvarios.isChecked())
			rdoSiOvarios.setChecked(false);
		if (rdoNoOvarios.isChecked())
			rdoNoOvarios.setChecked(false);
		if (rdoSiPezones.isChecked())
			rdoSiPezones.setChecked(false);
		if (rdoNoPezones.isChecked())
			rdoNoPezones.setChecked(false);
		if (rdoSiRehabilitacion.isChecked())
			rdoSiRehabilitacion.setChecked(false);
		if (rdoNoRehabilitacion.isChecked())
			rdoNoRehabilitacion.setChecked(false);
		if (rdoSiRehabilitacionAlcohol.isChecked())
			rdoSiRehabilitacionAlcohol.setChecked(false);
		if (rdoNoRehabilitacionAlcohol.isChecked())
			rdoNoRehabilitacionAlcohol.setChecked(false);
		if (rdoSiTransfusiones.isChecked())
			rdoSiTransfusiones.setChecked(false);
		if (rdoNoTransfusiones.isChecked())
			rdoNoTransfusiones.setChecked(false);
		if (rdoSiTratamiento.isChecked())
			rdoSiTratamiento.setChecked(false);
		if (rdoNoTratamiento.isChecked())
			rdoNoTratamiento.setChecked(false);
		if (rdoSiTratamientoAlcohol.isChecked())
			rdoSiTratamientoAlcohol.setChecked(false);
		if (rdoNoTratamientoAlcohol.isChecked())
			rdoNoTratamientoAlcohol.setChecked(false);
		if (rdoSiTratamientoDrogas.isChecked())
			rdoSiTratamientoDrogas.setChecked(false);
		if (rdoNoTratamientoDrogas.isChecked())
			rdoNoTratamientoDrogas.setChecked(false);
		if (rdoSiVIH.isChecked())
			rdoSiVIH.setChecked(false);
		if (rdoNoVIH.isChecked())
			rdoNoVIH.setChecked(false);
		if (rdoSiColores.isChecked())
			rdoSiColores.setChecked(false);
		if (rdoNoColores.isChecked())
			rdoNoColores.setChecked(false);
		if (rdgFrecuenciaAlcohol.getSelectedItem() != null) {
			Radio radio = rdgFrecuenciaAlcohol.getSelectedItem();
			radio.setChecked(false);
		}
		spnAbortos.setValue(0);
		spnCantidadAlcohol.setValue(0);
		spnCantidadCafe.setValue(0);
		spnCesareas.setValue(0);
		spnEdadDesarrollo.setValue(0);
		spnEmbarazos.setValue(0);
		spnGestacion.setValue(0);
		spnNumeroCigarro.setValue(0);
		spnPartos.setValue(0);
		txtVIH.setValue("");
		txtAlgunaEnfermedad.setValue("");
		txtCantidadMedicamento.setValue("");
		txtCantidadPeso.setValue("");
		txtCausasPeso.setValue("");
		txtExplicacionDrogas.setValue("");
		txtFrecuenciaFisica.setValue("");
		txtMedicamentoDroga.setValue("");
		txtRazonCigarro.setValue("");
		txtResultadoEco.setValue("");
		txtResultadoMamografia.setValue("");
		txtTiempoFisica.setValue("");
		txtTipoAlcohol.setValue("");
		txtTipoFisica.setValue("");
		dtbFechaFinCigarro.setValue(fecha);
		dtbFechaInicioCigarro.setValue(fecha);
		dtbFechaMedicamento.setValue(fecha);
		dtbFechaUltima.setValue(fecha);
		dtbFechaUltimaCito.setValue(fecha);
		cmbExtra.setValue("");
		spnAlturaCodo.setValue((double) 0);
		spnAlturaHombro.setValue((double) 0);
		spnSillaOjo.setValue((double) 0);
		spnCodoSilla.setValue((double) 0);
		spnLongDerecho.setValue((double) 0);
		spnLongIzquierdo.setValue((double) 0);
		spnAnchuraHombro.setValue((double) 0);
		spnCaderaAbdominal.setValue((double) 0);
		spnCircunferenciaA.setValue((double) 0);
		spnCircunferenciaC.setValue((double) 0);
		spnPoplitea.setValue((double) 0);
		spnManoPiso.setValue((double) 0);
		cmbCarta.setValue("");
		txtTelefonoDoc.setValue("");
		cmbDiente1.setValue("Normal");
		cmbDiente2.setValue("Normal");
		cmbDiente3.setValue("Normal");
		cmbDiente4.setValue("Normal");
		cmbDiente5.setValue("Normal");
		cmbDiente6.setValue("Normal");
		cmbDiente7.setValue("Normal");
		cmbDiente8.setValue("Normal");
		cmbDiente9.setValue("Normal");
		cmbDiente10.setValue("Normal");
		cmbDiente11.setValue("Normal");
		cmbDiente12.setValue("Normal");
		cmbDiente13.setValue("Normal");
		cmbDiente14.setValue("Normal");
		cmbDiente15.setValue("Normal");
		cmbDiente16.setValue("Normal");
		cmbDiente17.setValue("Normal");
		cmbDiente18.setValue("Normal");
		cmbDiente19.setValue("Normal");
		cmbDiente20.setValue("Normal");
		cmbDiente21.setValue("Normal");
		cmbDiente22.setValue("Normal");
		cmbDiente23.setValue("Normal");
		cmbDiente24.setValue("Normal");
		cmbDiente25.setValue("Normal");
		cmbDiente26.setValue("Normal");
		cmbDiente27.setValue("Normal");
		cmbDiente28.setValue("Normal");
		cmbDiente29.setValue("Normal");
		cmbDiente30.setValue("Normal");
		cmbDiente31.setValue("Normal");
		cmbDiente32.setValue("Normal");
	}

	// DIV PARA ACCIDENTE
	// @Listen("onClick = #btnAbrirAccidenteComun,#btnAbrirAccidenteLaboral")
	// public void divAccidenteComun(Event evento) {
	// Button boton = (Button) evento.getTarget();
	// final HashMap<String, Object> map = new HashMap<String, Object>();
	// if (boton.getId().equals("btnAbrirAccidenteComun")) {
	// map.put("id", "consulta");
	// map.put("lista", accidentesComunesDisponibles);
	// map.put("listbox", ltbAccidentesComunes);
	// map.put("tipo", "Comun");
	// } else {
	// map.put("id", "consulta");
	// map.put("lista", accidentesLaboralesDisponibles);
	// map.put("listbox", ltbAccidentesLaborales);
	// map.put("tipo", "Laboral");
	// }
	// Sessions.getCurrent().setAttribute("itemsCatalogo", map);
	// Arbol arbolItem = servicioArbol.buscarPorNombreArbol("Accidente");
	// cArbol.abrirVentanas(arbolItem);
	// }

	@Listen("onClick = #btnAbrirIntervencion")
	public void divIntervencion() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", "consulta");
		map.put("lista", intervencionesDisponibles);
		map.put("listbox", ltbIntervenciones);
		Sessions.getCurrent().setAttribute("itemsCatalogo", map);
		Arbol arbolItem = servicioArbol.buscarPorNombreArbol("Intervencion");
		cArbol.abrirVentanas(arbolItem);
	}

	@Listen("onClick = #btnAbrirExamen")
	public void divExamen() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", "consulta");
		map.put("lista", examenesDisponibles);
		map.put("listbox", ltbExamenes);
		Sessions.getCurrent().setAttribute("itemsCatalogo", map);
		Arbol arbolItem = servicioArbol.buscarPorNombreArbol("Examen");
		cArbol.abrirVentanas(arbolItem);
	}

	@Listen("onClick = #btnAbrirDiagnostico")
	public void divDiagnostico() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", "consulta");
		map.put("lista", diagnosticosDisponibles);
		map.put("listbox", ltbDiagnosticos);
		Sessions.getCurrent().setAttribute("itemsCatalogo", map);
		Arbol arbolItem = servicioArbol.buscarPorNombreArbol("Diagnostico");
		cArbol.abrirVentanas(arbolItem);
	}

	@Listen("onClick = #btnAbrirEspecialista")
	public void divEspecialista() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", "consulta");
		map.put("lista", especialistasDisponibles);
		map.put("listbox", ltbEspecialistas);
		Sessions.getCurrent().setAttribute("itemsCatalogo", map);
		Arbol arbolItem = servicioArbol.buscarPorNombreArbol("Especialista");
		cArbol.abrirVentanas(arbolItem);
	}

	@Listen("onClick = #btnAbrirServicioExterno")
	public void divServicio() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", "consulta");
		map.put("lista", serviciosDisponibles);
		map.put("listbox", ltbServicioExterno);
		Sessions.getCurrent().setAttribute("itemsCatalogo", map);
		Arbol arbolItem = servicioArbol
				.buscarPorNombreArbol("Servicios Externos");
		cArbol.abrirVentanas(arbolItem);
	}

	@Listen("onClick = #btnAbrirMedicina")
	public void divMedicina() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", "consulta");
		map.put("lista", medicinasDisponibles);
		map.put("listbox", ltbMedicinas);
		Sessions.getCurrent().setAttribute("itemsCatalogo", map);
		Arbol arbolItem = servicioArbol.buscarPorNombreArbol("Medicina");
		cArbol.abrirVentanas(arbolItem);
	}

	@Listen("onOK = #txtCedula")
	public void buscarCedula() {
		Paciente paciente = servicioPaciente.buscarPorCedula(txtCedula
				.getValue());
		if (paciente != null) {
			llenarCampos(paciente);
			idPaciente = Long.valueOf(paciente.getCedula());
			List<Consulta> consultas = servicioConsulta
					.buscarPorPaciente(paciente);
			ltbConsultas.setModel(new ListModelList<Consulta>(consultas));
			llenarListas();
		} else {
			limpiarCampos();
			Messagebox.show("Cedula Incorrecta", "Informacion", Messagebox.OK,
					Messagebox.INFORMATION);
		}
	}

	@Listen("onSelect = #cmbTipoPreventiva")
	public void buscarExamenesPreempleo() {

		if (cmbTipoPreventiva.getValue().equals("Pre-Empleo")) {
			row.setVisible(true);
			rowPromocion.setVisible(false);
		} else {
			if (cmbTipoPreventiva.getValue().equals("Control")) {
				cmbConsulta.setVisible(true);
				lblConsulta.setVisible(true);
			} else {
				if (cmbTipoPreventiva.getValue().equals("Cambio de Puesto")
						|| cmbTipoPreventiva.getValue().equals("Promocion"))
					rowPromocion.setVisible(true);
				else
					rowPromocion.setVisible(false);
				cmbConsulta.setVisible(false);
				lblConsulta.setVisible(false);
			}
			row.setVisible(false);
		}
	}

	@Listen("onSelect = #cmbTipoConsulta")
	public void buscarPreventiva() {
		if (cmbTipoConsulta.getValue().equals("Preventiva"))
			cmbTipoPreventiva.setModel(new ListModelList<String>(
					consultaPreventiva));
		else {
			if (cmbTipoConsulta.getValue().equals("Curativa")) {
				cmbTipoPreventiva.setModel(new ListModelList<String>(
						consultaCurativa));
				lblPreventiva.setValue("Tipo de Consulta Curativa");
			}
		}
		row.setVisible(false);
		rowPromocion.setVisible(false);
		cmbTipoPreventiva.setVisible(true);
		lblPreventiva.setVisible(true);
		cmbTipoPreventiva.setValue("");
		cmbConsulta.setValue("");
		cmbConsulta.setPlaceholder("Seleccione una Consulta");

	}

	public ListModelList<Proveedor> getProveedores() {
		proveedores = new ListModelList<Proveedor>(
				servicioProveedor.buscarTodos());
		return proveedores;
	}

	public ListModelList<Vacuna> getModelVacunas() {
		modelVacunas = new ListModelList<Vacuna>(servicioVacuna.buscarTodos());
		return modelVacunas;
	}

	public ListModelList<ParteCuerpo> getModelFisico() {
		modelFisico = new ListModelList<ParteCuerpo>(
				servicioParteCuerpo.buscarTodos());
		return modelFisico;
	}

	protected void guardarHistoria(Paciente paciente) {
		long idHistoria = 0;
		Historia historia = servicioHistoria.buscarPorPaciente(paciente);
		if (historia != null) {
			idHistoria = historia.getIdHistoria();
			servicioHistoriaAccidente.limpiar(historia);
			servicioHistoriaVacuna.limpiar(historia);
			servicioHistoriaIntervencion.limpiar(historia);
		}
		boolean valorPeso = false;
		if (rdoSiPeso.isChecked())
			valorPeso = true;
		boolean accidenteAlcohol = false;
		if (rdoSiAccidenteAlcohol.isChecked())
			accidenteAlcohol = true;
		boolean alcohol = false;
		if (rdoSiAlcohol.isChecked())
			alcohol = true;
		boolean anticonceptivos = false;
		if (rdoSiAnticonceptivos.isChecked())
			anticonceptivos = true;
		boolean borracho = false;
		if (rdoSiBorracho.isChecked())
			borracho = true;
		boolean cabeza = false;
		if (rdoSiCabeza.isChecked())
			cabeza = true;
		boolean cafe = false;
		if (rdoSiCafe.isChecked())
			cafe = true;
		boolean cigarro = false;
		if (rdoSiCigarro.isChecked())
			cigarro = true;
		boolean dolorSexo = false;
		if (rdoSiDolor.isChecked())
			dolorSexo = true;
		boolean drogas = false;
		if (rdoSiDrogas.isChecked())
			drogas = true;
		boolean dormilon = false;
		if (rdoSiDuerme.isChecked())
			dormilon = true;
		boolean eco = false;
		if (rdoSiEco.isChecked())
			eco = true;
		boolean embarazo = false;
		if (rdoSiEmbarazada.isChecked())
			embarazo = true;
		boolean endurecimiento = false;
		if (rdoSiEndurecimiento.isChecked())
			endurecimiento = true;
		boolean enfermedad = false;
		if (rdoSiEnfermedad.isChecked())
			enfermedad = true;
		boolean esterilizacion = false;
		if (rdoSiEsterilizacion.isChecked())
			esterilizacion = true;
		boolean ets = false;
		if (rdoSiETS.isChecked())
			ets = true;
		boolean extra = false;
		if (rdoSiExtra.isChecked())
			extra = true;
		boolean fisica = false;
		if (rdoSiFisica.isChecked())
			fisica = true;
		boolean flujo = false;
		if (rdoSiFlujo.isChecked())
			flujo = true;
		boolean fumaActual = false;
		if (rdoSiFumaActualmente.isChecked())
			fumaActual = true;
		boolean infeccion = false;
		if (rdoSiInfeccion.isChecked())
			infeccion = true;
		boolean intra = false;
		if (rdoSiIntra.isChecked())
			intra = true;
		boolean mamografia = false;
		if (rdoSiMamografia.isChecked())
			mamografia = true;
		boolean medicamento = false;
		if (rdoSiMedicamentoDrogas.isChecked())
			medicamento = true;
		boolean medico = false;
		if (rdoSiMedico.isChecked())
			medico = true;
		boolean menstrual = false;
		if (rdoSiMenstrual.isChecked())
			menstrual = true;
		boolean alcoholActual = false;
		if (rdoSiMesAlcohol.isChecked())
			alcoholActual = true;
		boolean ovarios = false;
		if (rdoSiOvarios.isChecked())
			ovarios = true;
		boolean pezones = false;
		if (rdoSiPezones.isChecked())
			pezones = true;
		boolean rehabilitacion = false;
		if (rdoSiRehabilitacion.isChecked())
			rehabilitacion = true;
		boolean rehabilitacionAlcohol = false;
		if (rdoSiRehabilitacionAlcohol.isChecked())
			rehabilitacionAlcohol = true;
		boolean transfusion = false;
		if (rdoSiTransfusiones.isChecked())
			transfusion = true;
		boolean tratamiento = false;
		if (rdoSiTratamiento.isChecked())
			tratamiento = true;
		boolean tratamientoAlcochol = false;
		if (rdoSiTratamientoAlcohol.isChecked())
			tratamientoAlcochol = true;
		boolean tratamientoDrogas = false;
		if (rdoSiTratamientoDrogas.isChecked())
			tratamientoDrogas = true;
		boolean vih = false;
		if (rdoSiVIH.isChecked())
			vih = true;
		boolean colores = false;
		if (rdoSiColores.isChecked())
			colores = true;
		String carta = cmbCarta.getValue();
		String telefonoOdontologo = txtTelefonoDoc.getValue();
		String a = cmbDiente1.getValue();
		String b = cmbDiente2.getValue();
		String c = cmbDiente3.getValue();
		String d = cmbDiente4.getValue();
		String e = cmbDiente5.getValue();
		String f = cmbDiente6.getValue();
		String g = cmbDiente7.getValue();
		String h = cmbDiente8.getValue();
		String i = cmbDiente9.getValue();
		String j = cmbDiente10.getValue();
		String k = cmbDiente11.getValue();
		String l = cmbDiente12.getValue();
		String m = cmbDiente13.getValue();
		String n = cmbDiente14.getValue();
		String o = cmbDiente15.getValue();
		String p = cmbDiente16.getValue();
		String q = cmbDiente17.getValue();
		String r = cmbDiente18.getValue();
		String s = cmbDiente19.getValue();
		String t = cmbDiente20.getValue();
		String u = cmbDiente21.getValue();
		String v = cmbDiente22.getValue();
		String w = cmbDiente23.getValue();
		String x = cmbDiente24.getValue();
		String y = cmbDiente25.getValue();
		String z = cmbDiente26.getValue();
		String za = cmbDiente27.getValue();
		String zb = cmbDiente28.getValue();
		String zc = cmbDiente29.getValue();
		String zd = cmbDiente32.getValue();
		String ze = cmbDiente30.getValue();
		String zf = cmbDiente31.getValue();
		double alturaHombro = spnAlturaHombro.getValue();
		double alturaCodo = spnAlturaCodo.getValue();
		double anchuraHombro = spnAnchuraHombro.getValue();
		double manoPiso = spnManoPiso.getValue();
		double derecho = spnLongDerecho.getValue();
		double izquierdo = spnLongIzquierdo.getValue();
		double indice = spnCaderaAbdominal.getValue();
		double circunferenciaAbdominal = spnCircunferenciaA.getValue();
		double circunferenciaCadera = spnCircunferenciaC.getValue();
		double codoSilla = spnCodoSilla.getValue();
		double ojo = spnSillaOjo.getValue();
		double alturaPop = spnPoplitea.getValue();
		int abortos = spnAbortos.getValue();
		int cantidadAlcohol = spnCantidadAlcohol.getValue();
		int tazas = spnCantidadCafe.getValue();
		int cesareas = spnCesareas.getValue();
		int edadDesarrollo = spnEdadDesarrollo.getValue();
		int embarazos = spnEmbarazos.getValue();
		int semanasGestando = spnGestacion.getValue();
		int cantidadCigarros = spnNumeroCigarro.getValue();
		int partos = spnPartos.getValue();
		String vihResultado = txtVIH.getValue();
		String algunaEnfermedad = txtAlgunaEnfermedad.getValue();
		String cantidadMedicamento = txtCantidadMedicamento.getValue();
		String cantidadPeso = txtCantidadPeso.getValue();
		String causasPeso = txtCausasPeso.getValue();
		String explicacionDrogas = txtExplicacionDrogas.getValue();
		String frecuenciaFisica = txtFrecuenciaFisica.getValue();
		String medicamentoDroga = txtMedicamentoDroga.getValue();
		String razonCigarro = txtRazonCigarro.getValue();
		String resultadoEco = txtResultadoEco.getValue();
		String resultadoMamografia = txtResultadoMamografia.getValue();
		String tiempoFisica = txtTiempoFisica.getValue();
		String tipoAlcohol = txtTipoAlcohol.getValue();
		String tipoFisica = txtTipoFisica.getValue();
		Date fechaFinC = dtbFechaFinCigarro.getValue();
		Timestamp fechaFinCigarro = new Timestamp(fechaFinC.getTime());
		Date fechaInicioC = dtbFechaInicioCigarro.getValue();
		Timestamp fechaInicioCigarro = new Timestamp(fechaInicioC.getTime());
		Date fechaMed = dtbFechaMedicamento.getValue();
		Timestamp fechaMedicamento = new Timestamp(fechaMed.getTime());
		Date fechaUltimaM = dtbFechaUltima.getValue();
		Timestamp fechaUltimaMenstruacion = new Timestamp(
				fechaUltimaM.getTime());
		Date fechaUltimaC = dtbFechaUltimaCito.getValue();
		Timestamp fechaUltimaCitologia = new Timestamp(fechaUltimaC.getTime());
		String actividadExtra = cmbExtra.getValue();
		String frecuenciaAlcohol = "";
		if (rdgFrecuenciaAlcohol.getSelectedItem() != null)
			frecuenciaAlcohol = rdgFrecuenciaAlcohol.getSelectedItem()
					.getLabel();
		Historia historiaGuardada = new Historia(idHistoria, paciente,
				valorPeso, cantidadPeso, causasPeso, cafe, tazas, dormilon,
				cabeza, fisica, tipoFisica, frecuenciaFisica, tiempoFisica,
				extra, actividadExtra, cigarro, fumaActual, cantidadCigarros,
				fechaInicioCigarro, fechaFinCigarro, razonCigarro, alcohol,
				alcoholActual, frecuenciaAlcohol, tipoAlcohol, cantidadAlcohol,
				borracho, tratamientoAlcochol, rehabilitacionAlcohol,
				accidenteAlcohol, drogas, explicacionDrogas, tratamientoDrogas,
				rehabilitacion, medicamento, medicamentoDroga,
				fechaMedicamento, cantidadMedicamento, enfermedad,
				algunaEnfermedad, medico, tratamiento, transfusion, ets, vih,
				vihResultado, flujo, pezones, menstrual, endurecimiento,
				infeccion, anticonceptivos, dolorSexo, esterilizacion, intra,
				edadDesarrollo, fechaUltimaMenstruacion, embarazos, partos,
				cesareas, abortos, fechaUltimaCitologia, ovarios, embarazo,
				semanasGestando, eco, resultadoEco, mamografia,
				resultadoMamografia, horaAuditoria, fechaHora,
				nombreUsuarioSesion(), a, b, c, d, e, f, g, h, i, j, k, l, m,
				n, o, p, q, r, s, t, u, v, w, x, y, z, za, zb, zc, zd, ze, zf,
				carta, colores, telefonoOdontologo, alturaHombro,
				anchuraHombro, alturaCodo, izquierdo, derecho, alturaPop, ojo,
				codoSilla, circunferenciaAbdominal, circunferenciaCadera,
				manoPiso, indice);
		servicioHistoria.guardar(historiaGuardada);
		if (idHistoria != 0)
			historiaGuardada = servicioHistoria.buscar(idHistoria);
		else
			historiaGuardada = servicioHistoria.buscarUltima();
		guardarAccidentes(historiaGuardada);
		guardarIntervenciones(historiaGuardada);
		guardarVacunas(historiaGuardada);
	}

	private void guardarVacunas(Historia historiaGuardada) {
		List<HistoriaVacuna> vacunas = new ArrayList<HistoriaVacuna>();
		if (ltbVacunas.getItemCount() != 0) {
			for (int i = 0; i < ltbVacunas.getItemCount(); i++) {
				Listitem listItem = ltbVacunas.getItemAtIndex(i);
				if (listItem.isSelected()) {
					Vacuna vacuna = listItem.getValue();
					Datebox text = (Datebox) listItem.getChildren().get(1)
							.getChildren().get(0);
					Date fechas = text.getValue();
					Timestamp fechaVacuna = new Timestamp(fechas.getTime());
					HistoriaVacuna vacunaHistoria = new HistoriaVacuna(
							historiaGuardada, vacuna, fechaVacuna);
					vacunas.add(vacunaHistoria);
				}
			}
			servicioHistoriaVacuna.guardar(vacunas);
		}
	}

	private void guardarIntervenciones(Historia historiaGuardada) {
		List<HistoriaIntervencion> historialIntervenciones = new ArrayList<HistoriaIntervencion>();
		for (int i = 0; i < ltbIntervencionesAgregadas.getItemCount(); i++) {
			Listitem listItem = ltbIntervencionesAgregadas.getItemAtIndex(i);
			long id = ((Spinner) ((listItem.getChildren().get(6)))
					.getFirstChild()).getValue();
			Intervencion intervencion = servicioIntervencion.buscar(id);
			Date fechaI = ((Datebox) ((listItem.getChildren().get(1)))
					.getFirstChild()).getValue();
			Timestamp fechaIntervencion = new Timestamp(fechaI.getTime());
			String motivo = ((Textbox) ((listItem.getChildren().get(2)))
					.getFirstChild()).getValue();
			String diagnostico = ((Textbox) ((listItem.getChildren().get(3)))
					.getFirstChild()).getValue();
			int reposo = ((Spinner) ((listItem.getChildren().get(4)))
					.getFirstChild()).getValue();
			String secuelas = ((Textbox) ((listItem.getChildren().get(5)))
					.getFirstChild()).getValue();
			HistoriaIntervencion historiaIntervencion = new HistoriaIntervencion(
					historiaGuardada, intervencion, fechaIntervencion, motivo,
					diagnostico, reposo, secuelas);
			historialIntervenciones.add(historiaIntervencion);
		}
		servicioHistoriaIntervencion.guardar(historialIntervenciones);
	}

	private void guardarAccidentes(Historia historiaGuardada) {
		List<HistoriaAccidente> historialAccidentes = new ArrayList<HistoriaAccidente>();

		for (int i = 0; i < ltbAccidentesComunesAgregados.getItemCount(); i++) {
			Listitem listItem = ltbAccidentesComunesAgregados.getItemAtIndex(i);
			long id = ((Spinner) ((listItem.getChildren().get(6)))
					.getFirstChild()).getValue();
			Accidente accidente = servicioAccidente.buscar(id);
			Date fechaA = ((Datebox) ((listItem.getChildren().get(1)))
					.getFirstChild()).getValue();
			Timestamp fechaAccidente = new Timestamp(fechaA.getTime());
			String lugar = ((Textbox) ((listItem.getChildren().get(2)))
					.getFirstChild()).getValue();
			String tipoAccidente = ((Textbox) ((listItem.getChildren().get(3)))
					.getFirstChild()).getValue();
			int reposo = ((Spinner) ((listItem.getChildren().get(4)))
					.getFirstChild()).getValue();
			String secuelas = ((Textbox) ((listItem.getChildren().get(5)))
					.getFirstChild()).getValue();
			HistoriaAccidente historiaAccidente = new HistoriaAccidente(
					historiaGuardada, accidente, fechaAccidente, lugar,
					tipoAccidente, reposo, secuelas);
			historialAccidentes.add(historiaAccidente);
		}

		for (int i = 0; i < ltbAccidentesLaboralesAgregados.getItemCount(); i++) {
			Listitem listItem = ltbAccidentesLaboralesAgregados
					.getItemAtIndex(i);
			long id = ((Spinner) ((listItem.getChildren().get(6)))
					.getFirstChild()).getValue();
			Accidente accidente = servicioAccidente.buscar(id);
			Date fechaA = ((Datebox) ((listItem.getChildren().get(1)))
					.getFirstChild()).getValue();
			Timestamp fechaAccidente = new Timestamp(fechaA.getTime());
			String lugar = ((Textbox) ((listItem.getChildren().get(2)))
					.getFirstChild()).getValue();
			String tipoAccidente = ((Textbox) ((listItem.getChildren().get(3)))
					.getFirstChild()).getValue();
			int reposo = ((Spinner) ((listItem.getChildren().get(4)))
					.getFirstChild()).getValue();
			String secuelas = ((Textbox) ((listItem.getChildren().get(5)))
					.getFirstChild()).getValue();
			HistoriaAccidente historiaAccidente = new HistoriaAccidente(
					historiaGuardada, accidente, fechaAccidente, lugar,
					tipoAccidente, reposo, secuelas);
			historialAccidentes.add(historiaAccidente);
		}

		servicioHistoriaAccidente.guardar(historialAccidentes);
	}

	private void llenarCamposHistoria(Historia historia) {
		boolean valorPeso = historia.getVarioPeso();
		if (valorPeso)
			rdoSiPeso.setChecked(true);
		else
			rdoNoPeso.setChecked(true);
		boolean accidenteAlcohol = historia.getAlcoholAccidente();
		if (accidenteAlcohol)
			rdoSiAccidenteAlcohol.setChecked(true);
		else
			rdoNoAccidenteAlcohol.setChecked(true);
		boolean alcohol = historia.getAlcoholConsume();
		if (alcohol)
			rdoSiAlcohol.setChecked(true);
		else
			rdoNoAlcohol.setChecked(true);
		boolean anticonceptivos = historia.getAnticonceptivo();
		if (anticonceptivos)
			rdoSiAnticonceptivos.setChecked(true);
		else
			rdoNoAnticonceptivos.setChecked(true);
		boolean borracho = historia.getAlcoholEmbriagado();
		if (borracho)
			rdoSiBorracho.setChecked(true);
		else
			rdoNoBorracho.setChecked(true);
		boolean cabeza = historia.getDolorCafe();
		if (cabeza)
			rdoSiCabeza.setChecked(true);
		else
			rdoNoCabeza.setChecked(true);
		boolean cafe = historia.getCafe();
		if (cafe)
			rdoSiCafe.setChecked(true);
		else
			rdoNoCafe.setChecked(true);
		boolean cigarro = historia.getCigarroConsume();
		if (cigarro)
			rdoSiCigarro.setChecked(true);
		else
			rdoNoCigarro.setChecked(true);
		boolean dolorSexo = historia.getDolorRelacion();
		if (dolorSexo)
			rdoSiDolor.setChecked(true);
		else
			rdoNoDolor.setChecked(true);
		boolean drogas = historia.getDrogaConsume();
		if (drogas)
			rdoSiDrogas.setChecked(true);
		else
			rdoNoDrogas.setChecked(true);
		boolean dormilon = historia.getDificultadDormir();
		if (dormilon)
			rdoSiDuerme.setChecked(true);
		else
			rdoNoDuerme.setChecked(true);
		boolean eco = historia.getEco();
		if (eco)
			rdoSiEco.setChecked(true);
		else
			rdoNoEco.setChecked(true);
		boolean embarazo = historia.getEmbarazo();
		if (embarazo)
			rdoSiEmbarazada.setChecked(true);
		else
			rdoNoEmbarazada.setChecked(true);
		boolean endurecimiento = historia.getEndurecimiento();
		if (endurecimiento)
			rdoSiEndurecimiento.setChecked(true);
		else
			rdoNoEndurecimiento.setChecked(true);
		boolean enfermedad = historia.getEnfermedadPosee();
		if (enfermedad)
			rdoSiEnfermedad.setChecked(true);
		else
			rdoNoEnfermedad.setChecked(true);
		boolean esterilizacion = historia.getEsterilizacion();
		if (esterilizacion)
			rdoSiEsterilizacion.setChecked(true);
		else
			rdoNoEsterilizacion.setChecked(true);
		boolean ets = historia.getEts();
		if (ets)
			rdoSiETS.setChecked(true);
		else
			rdoNoETS.setChecked(true);
		boolean extra = historia.getActividadExtra();
		if (extra)
			rdoSiExtra.setChecked(true);
		else
			rdoNoExtra.setChecked(true);
		boolean fisica = historia.getActividadFisica();
		if (fisica)
			rdoSiFisica.setChecked(true);
		else
			rdoNoFisica.setChecked(true);
		boolean flujo = historia.getFlujo();
		if (flujo)
			rdoSiFlujo.setChecked(true);
		else
			rdoNoFlujo.setChecked(true);
		boolean fumaActual = historia.getCigarroActual();
		if (fumaActual)
			rdoSiFumaActualmente.setChecked(true);
		else
			rdoNoFumaActualmente.setChecked(true);
		boolean infeccion = historia.getInfeccion();
		if (infeccion)
			rdoSiInfeccion.setChecked(true);
		else
			rdoNoInfeccion.setChecked(true);
		boolean intra = historia.getAparato();
		if (intra)
			rdoSiIntra.setChecked(true);
		else
			rdoNoIntra.setChecked(true);
		boolean mamografia = historia.getMamografia();
		if (mamografia)
			rdoSiMamografia.setChecked(true);
		else
			rdoNoMamografia.setChecked(true);
		boolean medicamento = historia.getMedicamentoConsume();
		if (medicamento)
			rdoSiMedicamentoDrogas.setChecked(true);
		else
			rdoNoMedicamentoDrogas.setChecked(true);
		boolean medico = historia.getMedico();
		if (medico)
			rdoSiMedico.setChecked(true);
		else
			rdoNoMedico.setChecked(true);
		boolean menstrual = historia.getDolor();
		if (menstrual)
			rdoSiMenstrual.setChecked(true);
		else
			rdoNoMenstrual.setChecked(true);
		boolean alcoholActual = historia.getAlcoholActual();
		if (alcoholActual)
			rdoSiMesAlcohol.setChecked(true);
		else
			rdoNoMesAlcohol.setChecked(true);
		boolean ovarios = historia.getPoliquistico();
		if (ovarios)
			rdoSiOvarios.setChecked(true);
		else
			rdoNoOvarios.setChecked(true);
		boolean pezones = historia.getSecrecion();
		if (pezones)
			rdoSiPezones.setChecked(true);
		else
			rdoNoPezones.setChecked(true);
		boolean rehabilitacion = historia.getDrogaRehabilitacion();
		if (rehabilitacion)
			rdoSiRehabilitacion.setChecked(true);
		else
			rdoNoRehabilitacion.setChecked(true);
		boolean rehabilitacionAlcohol = historia.getAlcoholRehabilitacion();
		if (rehabilitacionAlcohol)
			rdoSiRehabilitacionAlcohol.setChecked(true);
		else
			rdoNoRehabilitacionAlcohol.setChecked(true);
		boolean transfusion = historia.getTransfusion();
		if (transfusion)
			rdoSiTransfusiones.setChecked(true);
		else
			rdoNoTransfusiones.setChecked(true);
		boolean tratamiento = historia.getTratamiento();
		if (tratamiento)
			rdoSiTratamiento.setChecked(true);
		else
			rdoNoTratamiento.setChecked(true);
		boolean tratamientoAlcochol = historia.getAlcoholTratamiento();
		if (tratamientoAlcochol)
			rdoSiTratamientoAlcohol.setChecked(true);
		else
			rdoNoTratamientoAlcohol.setChecked(true);
		boolean tratamientoDrogas = historia.getDrogaTratamiento();
		if (tratamientoDrogas)
			rdoSiTratamientoDrogas.setChecked(true);
		else
			rdoNoTratamientoDrogas.setChecked(true);
		boolean vih = historia.getVih();
		if (vih)
			rdoSiVIH.setChecked(true);
		else
			rdoNoVIH.setChecked(true);
		if (historia.getVisionColor() != null) {
			boolean colores = historia.getVisionColor();
			if (colores)
				rdoSiColores.setChecked(true);
			else
				rdoNoColores.setChecked(true);
		}
		spnAlturaCodo.setValue(historia.getAlturaCodo());
		spnAlturaHombro.setValue(historia.getAlturaHombro());
		spnSillaOjo.setValue(historia.getAlturaOjo());
		spnCodoSilla.setValue(historia.getAlturaCodoSilla());
		spnLongDerecho.setValue(historia.getMiembroDerecho());
		spnLongIzquierdo.setValue(historia.getMiembroIzquierdo());
		spnAnchuraHombro.setValue(historia.getAnchuraHombro());
		spnCaderaAbdominal.setValue(historia.getIndiceCadera());
		spnCircunferenciaA.setValue(historia.getCircunferenciaAbdominal());
		spnCircunferenciaC.setValue(historia.getCircunferenciaCadera());
		spnPoplitea.setValue(historia.getAlturaPoplitea());
		spnManoPiso.setValue(historia.getManoPiso());
		cmbCarta.setValue(historia.getCarta());
		txtTelefonoDoc.setValue(historia.getTelefonoOdontologo());
		cmbDiente1.setValue(historia.getDientea());
		cmbDiente2.setValue(historia.getDienteb());
		cmbDiente3.setValue(historia.getDientec());
		cmbDiente4.setValue(historia.getDiented());
		cmbDiente5.setValue(historia.getDientee());
		cmbDiente6.setValue(historia.getDientef());
		cmbDiente7.setValue(historia.getDienteg());
		cmbDiente8.setValue(historia.getDienteh());
		cmbDiente9.setValue(historia.getDientei());
		cmbDiente10.setValue(historia.getDientej());
		cmbDiente11.setValue(historia.getDientek());
		cmbDiente12.setValue(historia.getDientel());
		cmbDiente13.setValue(historia.getDientem());
		cmbDiente14.setValue(historia.getDienten());
		cmbDiente15.setValue(historia.getDienteo());
		cmbDiente16.setValue(historia.getDientep());
		cmbDiente17.setValue(historia.getDienteq());
		cmbDiente18.setValue(historia.getDienter());
		cmbDiente19.setValue(historia.getDientes());
		cmbDiente20.setValue(historia.getDientet());
		cmbDiente21.setValue(historia.getDienteu());
		cmbDiente22.setValue(historia.getDientev());
		cmbDiente23.setValue(historia.getDientew());
		cmbDiente24.setValue(historia.getDientex());
		cmbDiente25.setValue(historia.getDientey());
		cmbDiente26.setValue(historia.getDientez());
		cmbDiente27.setValue(historia.getDienteza());
		cmbDiente28.setValue(historia.getDientezb());
		cmbDiente29.setValue(historia.getDientezc());
		cmbDiente30.setValue(historia.getDientezd());
		cmbDiente31.setValue(historia.getDienteze());
		cmbDiente32.setValue(historia.getDientezf());
		spnAbortos.setValue(historia.getNumeroAbortos());
		spnCantidadAlcohol.setValue(historia.getAlcoholCantidad());
		spnCantidadCafe.setValue(historia.getCantidadCafe());
		spnCesareas.setValue(historia.getNumeroCesareas());
		spnEdadDesarrollo.setValue(historia.getEdadDesarrollo());
		spnEmbarazos.setValue(historia.getNumeroEmbarazos());
		spnGestacion.setValue(historia.getEmbarazoSemanas());
		spnNumeroCigarro.setValue(historia.getCigarroCantidad());
		spnPartos.setValue(historia.getNumeroPartos());
		txtVIH.setValue(historia.getVihResultado());
		txtAlgunaEnfermedad.setValue(historia.getEnfermedad());
		txtCantidadMedicamento.setValue(historia.getMedicamentoCantidad());
		txtCantidadPeso.setValue(historia.getPesoCambiado());
		txtCausasPeso.setValue(historia.getPesoCausa());
		txtExplicacionDrogas.setValue(historia.getDrogaExplicacion());
		txtFrecuenciaFisica.setValue(historia.getActividadFrecuencia());
		txtMedicamentoDroga.setValue(historia.getMedicamentoCantidad());
		txtRazonCigarro.setValue(historia.getCigarroRazon());
		txtResultadoEco.setValue(historia.getEcoResultado());
		txtResultadoMamografia.setValue(historia.getMamografiaResultado());
		txtTiempoFisica.setValue(historia.getActividadTiempo());
		txtTipoAlcohol.setValue(historia.getAlcoholTipo());
		txtTipoFisica.setValue(historia.getActividadTipo());
		dtbFechaFinCigarro.setValue(historia.getCigarroFin());
		dtbFechaInicioCigarro.setValue(historia.getCigarroInicio());
		dtbFechaMedicamento.setValue(historia.getMedicamentoInicio());
		dtbFechaUltima.setValue(historia.getUltimaMenstruacion());
		dtbFechaUltimaCito.setValue(historia.getUltimaCitologia());
		cmbExtra.setValue(historia.getTipoExtra());
		Radio radio = new Radio();
		switch (historia.getAlcoholFrecuencia()) {
		case "DIARIA":
			radio = (Radio) rdgFrecuenciaAlcohol.getChildren().get(0);
			radio.setChecked(true);
			break;
		case "SEMANAL":
			radio = (Radio) rdgFrecuenciaAlcohol.getChildren().get(1);
			radio.setChecked(true);
			break;
		case "QUINCENAL":
			radio = (Radio) rdgFrecuenciaAlcohol.getChildren().get(2);
			radio.setChecked(true);
			break;
		case "MENSUAL":
			radio = (Radio) rdgFrecuenciaAlcohol.getChildren().get(3);
			radio.setChecked(true);
			break;
		default:
			break;
		}
	}

	@Listen("onChange = #spnEstatura, #spnPeso")
	public void calcularIMC() {
		double peso = spnPeso.getValue();
		double estatura = spnEstatura.getValue();
		double imc = 0;
		if (estatura != 0) {
			imc = Math.round((peso / (estatura * estatura)) * 10) / 10;
			if (imc < 18.5)
				lblIndice.setValue(imc + " Bajo Peso");
			else {
				if (imc >= 18.5 && imc < 25)
					lblIndice.setValue(imc + " Normal");
				else {
					if (imc >= 25 && imc < 30)
						lblIndice.setValue(imc + " Sobre Peso");
					else {
						if (imc >= 30 && imc < 35)
							lblIndice.setValue(imc + " Obesidad I");
						else {
							if (imc >= 35 && imc < 40)
								lblIndice.setValue(imc + " Obesidad II");
							else
								lblIndice.setValue(imc + " Obesidad III");
						}
					}
				}
			}
		}
	}

	public String getCambio() {
		if (ltbServicioExternoAgregados.getItemCount() != 0) {
			for (int i = 0; i < ltbServicioExternoAgregados.getItemCount(); i++) {
				Listitem listItem = ltbServicioExternoAgregados
						.getItemAtIndex(i);
				if (listItem != null) {
					if (((Combobox) ((listItem.getChildren().get(2)))
							.getFirstChild()).getSelectedItem() != null) {
						String proveedor = ((Combobox) ((listItem.getChildren()
								.get(2))).getFirstChild()).getSelectedItem()
								.getContext();
						long id = ((Spinner) ((listItem.getChildren().get(3)))
								.getFirstChild()).getValue();
						ProveedorServicio proveedorServicio = servicioProveedorServicio
								.buscarPorCodigoDeAmbos(
										Long.parseLong(proveedor), id);
						if (proveedorServicio != null) {
							double precioUnitario = proveedorServicio
									.getCosto();
							((Doublespinner) ((listItem.getChildren().get(1)))
									.getFirstChild()).setValue(precioUnitario);
						} else {
							Messagebox
									.show("Este proveedor no posee este estudio asignado, por favor seleccione otro o remuevalo de la lista",
											"Informacion", Messagebox.OK,
											Messagebox.INFORMATION);
							((Doublespinner) ((listItem.getChildren().get(1)))
									.getFirstChild()).setValue((double) 0);
							((Combobox) ((listItem.getChildren().get(2)))
									.getFirstChild()).setFocus(true);
						}
					}
				}
			}
		}
		return cambio;
	}

	public void recibir(List<Especialista> lista, Listbox l) {
		ltbEspecialistas = l;
		especialistasDisponibles = lista;
		ltbEspecialistas.setModel(new ListModelList<Especialista>(
				especialistasDisponibles));
		ltbEspecialistas.setMultiple(false);
		ltbEspecialistas.setCheckmark(false);
		ltbEspecialistas.setMultiple(true);
		ltbEspecialistas.setCheckmark(true);
	}

	public void recibirMedicina(List<Medicina> lista, Listbox l) {
		ltbMedicinas = l;
		medicinasDisponibles = lista;
		ltbMedicinas
				.setModel(new ListModelList<Medicina>(medicinasDisponibles));
		ltbMedicinas.setMultiple(false);
		ltbMedicinas.setCheckmark(false);
		ltbMedicinas.setMultiple(true);
		ltbMedicinas.setCheckmark(true);
	}

	public void recibirDiagnostico(List<Diagnostico> lista, Listbox l) {
		ltbDiagnosticos = l;
		diagnosticosDisponibles = lista;
		ltbDiagnosticos.setModel(new ListModelList<Diagnostico>(
				diagnosticosDisponibles));
		ltbDiagnosticos.setMultiple(false);
		ltbDiagnosticos.setCheckmark(false);
		ltbDiagnosticos.setMultiple(true);
		ltbDiagnosticos.setCheckmark(true);
	}

	public void recibirExamen(List<Examen> lista, Listbox l) {
		ltbExamenes = l;
		examenesDisponibles = lista;
		ltbExamenes.setModel(new ListModelList<Examen>(examenesDisponibles));
		ltbExamenes.setMultiple(false);
		ltbExamenes.setCheckmark(false);
		ltbExamenes.setMultiple(true);
		ltbExamenes.setCheckmark(true);
	}

	public void recibirServicio(List<ServicioExterno> lista, Listbox l) {
		ltbServicioExterno = l;
		serviciosDisponibles = lista;
		ltbServicioExterno.setModel(new ListModelList<ServicioExterno>(
				serviciosDisponibles));
		ltbServicioExterno.setMultiple(false);
		ltbServicioExterno.setCheckmark(false);
		ltbServicioExterno.setMultiple(true);
		ltbServicioExterno.setCheckmark(true);
	}

	public void recibirIntervencion(List<Intervencion> interConsulta,
			Listbox listaConsulta) {
		ltbIntervenciones = listaConsulta;
		intervencionesDisponibles = interConsulta;
		ltbIntervenciones.setModel(new ListModelList<Intervencion>(
				intervencionesDisponibles));
		ltbIntervenciones.setMultiple(false);
		ltbIntervenciones.setCheckmark(false);
		ltbIntervenciones.setMultiple(true);
		ltbIntervenciones.setCheckmark(true);
	}

	public ListModelList<String> getDientes() {
		dientes = new ListModelList<String>(tipoDiente);
		return dientes;
	}

	public void colorIzquierda() {
		if (ltbDiagnosticos.getItemCount() != 0) {
			ltbDiagnosticos.renderAll();
			for (int j = 0; j < ltbDiagnosticos.getItemCount(); j++) {
				Listitem list2 = ltbDiagnosticos.getItemAtIndex(j);
				Diagnostico diagnostico = list2.getValue();
				if (diagnostico.getEpi() != null) {
					if (diagnostico.getEpi())
						list2.setStyle("font-weight:bold; background:#FF4545; font-color:white");
				}
			}
		}
	}

	public void colorDerecha() {
		if (ltbDiagnosticosAgregados.getItemCount() != 0) {
			ltbDiagnosticosAgregados.renderAll();
			for (int j = 0; j < ltbDiagnosticosAgregados.getItemCount(); j++) {
				Listitem list2 = ltbDiagnosticosAgregados.getItemAtIndex(j);
				ConsultaDiagnostico consultaD = list2.getValue();
				if (consultaD.getDiagnostico().getEpi() != null) {
					if (consultaD.getDiagnostico().getEpi())
						list2.setStyle("font-weight:bold; background:#FF4545; font-color:white");
				}
			}
		}
	}

	// VENTANA DE ACCIDENTE

	public void ventana(Combobox a) {
		Spinner spin = (Spinner) a.getParent().getParent().getChildren().get(3)
				.getFirstChild();
		System.out.println("idDiag" + spin.getValue());
		System.out.println("idConsulta" + idConsulta);
		if (a.getValue().equals("Accidente Laboral")
				|| a.getValue().equals("Accidente Comun")) {
			// Arbol arbolItem = servicioArbol
			// .buscarPorNombreArbol("Registro Accidente");
			// cArbol.abrirVentanas(arbolItem);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("idConsulta", idConsulta);
			map.put("idDiagnostico", Long.valueOf(spin.getValue()));
			Sessions.getCurrent().setAttribute("consulta", map);
			Window window = (Window) Executions.createComponents(
					"/vistas/transacciones/VRegistroAccidente.zul", null, map);
			window.doModal();

		}
	}

	public void getVentana(Combobox combo) {
		System.out.println(combo.getValue());
		System.out.println(combo.getSelectedItem());
		System.out.println("Entro");
		if (ltbDiagnosticosAgregados.getItemCount() != 0) {
			System.out.println("For");
			for (int i = 0; i < ltbDiagnosticosAgregados.getItemCount(); i++) {
				Listitem listItem = ltbDiagnosticosAgregados.getItemAtIndex(i);
				if (listItem != null) {

					System.out.println("If1");
					if (((Combobox) ((listItem.getChildren().get(1)))
							.getFirstChild()).getValue() != null) {
						System.out.println(((Combobox) ((listItem.getChildren()
								.get(1))).getFirstChild()).getValue());
						if (((Combobox) ((listItem.getChildren().get(1)))
								.getFirstChild()).getValue().equals(
								"Accidente Laboral")
								|| ((Combobox) ((listItem.getChildren().get(1)))
										.getFirstChild()).getValue().equals(
										"Accidente Comun")) {
							System.out.println("Ifinterno");
							Arbol arbolItem = servicioArbol
									.buscarPorNombreArbol("Registro Accidente");
							cArbol.abrirVentanas(arbolItem);
						}
					}
				}
			}
		}
	}

}
