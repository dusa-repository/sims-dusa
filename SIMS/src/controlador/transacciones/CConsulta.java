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
import modelo.maestros.Diagnostico;
import modelo.maestros.Especialista;
import modelo.maestros.Examen;
import modelo.maestros.Medicina;
import modelo.maestros.Paciente;
import modelo.maestros.PacienteAntecedente;
import modelo.maestros.PresentacionMedicina;
import modelo.maestros.Proveedor;
import modelo.maestros.Recipe;
import modelo.maestros.ServicioExterno;
import modelo.maestros.UnidadMedicina;
import modelo.seguridad.Arbol;
import modelo.seguridad.Usuario;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaDiagnostico;
import modelo.transacciones.ConsultaEspecialista;
import modelo.transacciones.ConsultaExamen;
import modelo.transacciones.ConsultaMedicina;
import modelo.transacciones.ConsultaServicioExterno;

import org.zkoss.bind.annotation.Command;
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
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.SimpleGroupsModel;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timebox;

import com.sun.org.glassfish.external.arc.Taxonomy;

import arbol.CArbol;

import componentes.Botonera;
import componentes.Buscar;
import componentes.Catalogo;

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
	private Combobox cmbTipoDiagnostio;
	@Wire
	private Combobox cmbAccidente;
	@Wire
	private Combobox cmbConsulta;
	@Wire
	private Combobox cmbProveedor;
	@Wire
	private Label lblConsulta;
	@Wire
	private Label lblTipoDiagnostico;
	@Wire
	private Div botoneraConsultaGeneral;
	ListModelList<Proveedor> proveedores;
	// -----------------------------

	List<Listbox> listas = new ArrayList<Listbox>();

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
	ListitemRenderer renderer;

	Buscar<Medicina> buscarMedicina;
	Buscar<Diagnostico> buscarDiagnostico;
	Buscar<Examen> buscarExamen;
	Buscar<Especialista> buscarEspecialista;
	Buscar<ServicioExterno> buscarServicio;

	private CArbol cArbol = new CArbol();

	@Override
	public void inicializar() throws IOException {

		listasMultiples();
		buscadorMedicina();
		buscadorDiagnostico();
		buscadorServicio();
		buscadorExamen();
		buscadorEspecialista();
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
						tipo = cmbTipoPreventiva.getValue();
					else
						tipo = cmbTipoConsulta.getValue();
					if (cmbTipoConsulta.getValue().equals("Control"))
						consultaAsociada = Long.parseLong(cmbConsulta
								.getSelectedItem().getContext());
					if (cmbAccidente.getText().compareTo("") != 0)
						accidenteL = servicioAccidente.buscar(Long
								.parseLong(cmbAccidente.getSelectedItem()
										.getContext()));
					Consulta consulta = new Consulta(idConsulta, paciente,
							usuario, accidenteL, fechaConsulta, horaAuditoria,
							horaAuditoria, fechaHora, nombreUsuarioSesion(),
							accidente, motivo, tipo, enfermedad,
							consultaAsociada);
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
					guardarAntecedentes(paciente);
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
					arg0.setStyle("font-weight:bold; color:black");
				}

				Listcell list3 = new Listcell();
				Textbox tex = new Textbox("");
				tex.setPlaceholder("Ingrese una Observacion");
				tex.setWidth("100%");
				tex.setParent(list3);
				list3.setParent(arg0);
				if (tipoAntecedente)
					list3.setVisible(false);

			}
		};
		return renderer;
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
			Proveedor proveedor = servicioProveedor.buscar(Long.parseLong(idProveedor));
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
		Proveedor proveedor = servicioProveedor.buscar(Long
				.parseLong(cmbProveedor.getSelectedItem().getContext()));
		for (int i = 0; i < ltbExamenesAgregados.getItemCount(); i++) {
			Listitem listItem = ltbExamenesAgregados.getItemAtIndex(i);
			Integer idExamen = ((Spinner) ((listItem.getChildren().get(2)))
					.getFirstChild()).getValue();
			Examen examen = servicioExamen.buscar(idExamen);
			String valor = ((Textbox) ((listItem.getChildren().get(1)))
					.getFirstChild()).getValue();
			//buscar precio del servicio externo
			double precio = 0;
			ConsultaExamen consultaExamen = new ConsultaExamen(consultaDatos,
					examen, valor,proveedor, precio);
			listaConsultaExamen.add(consultaExamen);
		}
		servicioConsultaExamen.guardar(listaConsultaExamen);
	}

	public void guardarDiagnosticos(Consulta consultaDatos) {
		List<ConsultaDiagnostico> listaDiagnostico = new ArrayList<ConsultaDiagnostico>();
		for (int i = 0; i < ltbDiagnosticosAgregados.getItemCount(); i++) {
			Listitem listItem = ltbDiagnosticosAgregados.getItemAtIndex(i);
			Integer idDiagnostico = ((Spinner) ((listItem.getChildren().get(2)))
					.getFirstChild()).getValue();
			Diagnostico diagnostico = servicioDiagnostico.buscar(idDiagnostico);
			String valor = ((Textbox) ((listItem.getChildren().get(1)))
					.getFirstChild()).getValue();
			ConsultaDiagnostico consultaDiagnostico = new ConsultaDiagnostico(
					consultaDatos, diagnostico, valor);
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
											"Preventiva")
											&& (cmbTipoDiagnostio.getText()
													.compareTo("") == 0 || cmbTipoDiagnostio
													.getText().compareTo("") == 0)) {
										Messagebox.show(
												"Debe Llenar Todos los Campos",
												"Informacion", Messagebox.OK,
												Messagebox.INFORMATION);
										return false;
									} else {
										if (cmbTipoConsulta.getValue().equals(
												"Control")
												&& cmbConsulta.getText()
														.compareTo("") == 0) {
											Messagebox
													.show("Debe Llenar Todos los Campos",
															"Informacion",
															Messagebox.OK,
															Messagebox.INFORMATION);
											return false;
										} else {
											if (cmbTipoConsulta.getValue()
													.equals("Curativa")
													&& cmbAccidente.getText()
															.compareTo("") == 0) {
												Messagebox
														.show("Debe Llenar Todos los Campos",
																"Informacion",
																Messagebox.OK,
																Messagebox.INFORMATION);
												return false;
											} else {
												if (!cmbTipoDiagnostio
														.getValue().equals(
																"Otro")
														&& cmbAccidente
																.getText()
																.compareTo("") == 0) {
													Messagebox
															.show("Debe Llenar Todos los Campos",
																	"Informacion",
																	Messagebox.OK,
																	Messagebox.INFORMATION);
													return false;
												} else {
													if (ltbMedicinasAgregadas
															.getItemCount() != 0
															&& cmbPrioridad
																	.getText()
																	.compareTo(
																			"") == 0) {
														Messagebox
																.show("Debe Seleccionar la Prioridad del Recipe",
																		"Informacion",
																		Messagebox.OK,
																		Messagebox.INFORMATION);
														return false;
													} else {
														if (ltbExamenesAgregados
																.getItemCount() != 0
																&& cmbProveedor
																		.getText()
																		.compareTo(
																				"") == 0) {
															Messagebox
																	.show("Debe Seleccionar el Laboratorio que Realizara los Examenes",
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

		List<PacienteAntecedente> laboralesPaciente = servicioPacienteAntecedente
				.buscarAntecedentesPaciente(paciente, "Laboral");

		List<PacienteAntecedente> medicosPaciente = servicioPacienteAntecedente
				.buscarAntecedentesPaciente(paciente, "Medico");

		listasMultiples();

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

	}

	private void listasMultiples() {
		if (listas.isEmpty()) {
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
			listas.add(ltbMedicos);
		}
		for (int i = 0; i < listas.size(); i++) {
			if (!listas.get(i).getId().equals("ltbConsultas")) {
				listas.get(i).setMultiple(false);
				listas.get(i).setCheckmark(false);
				listas.get(i).setMultiple(true);
				listas.get(i).setCheckmark(true);
			}
		}
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
				llenarCampos(consulta.getPaciente());
				llenarListas();
			}
		}
	}

	private void llenarCampos(Paciente paciente) {
		// TODO Auto-generated method stub
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
		System.out.println("arriba"+medicinasAgregadas.size());
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
			System.out.println("abajo"+medicinasAgregadas.size());
		}
//		medicinasResumen.clear();
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
//		diagnosticosResumen.clear();
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
//		examenesResumen.clear();
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
//		examenesResumen.clear();
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
				if (valor == 0) {
					falta = true;
				}
				consulta.setCosto(valor);
				serviciosResumen.add(consulta);
			}
			ltbResumenServicios
					.setModel(new ListModelList<ConsultaServicioExterno>(
							serviciosResumen));
		}
//		serviciosResumen.clear();
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
		for (int q = 0; q < limpiador.size(); q++) {
			limpiador.get(q).clear();
		}
	}

	public void limpiarListBox() {
		for (int i = 0; i < listas.size(); i++) {
			if (!listas.get(i).getId().equals("ltbLaborales")) {
				if (!listas.get(i).getId().equals("ltbMedicos")) {
					listas.get(i).getItems().clear();
				}

			}
		}
	}

	public void limpiarCampos() {
		idPaciente = 0;
		idConsulta = 0;
		limpiarListBox();
		limpiarListas();
		llenarListas();
		txtCedula.setValue("");
		dtbFechaConsulta.setValue(fecha);
		dtbValido.setValue(fecha);
		cmbPrioridad.setValue("");
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
	}

	@Listen("onClick = #btnAbrirExamen")
	public void divExamen() {
		Arbol arbolItem = servicioArbol.buscarPorNombreArbol("Examen");
		cArbol.abrirVentanas(arbolItem);
	}

	@Listen("onClick = #btnAbrirDiagnostico")
	public void divDiagnostico() {
		Arbol arbolItem = servicioArbol.buscarPorNombreArbol("Diagnostico");
		cArbol.abrirVentanas(arbolItem);
	}

	@Listen("onClick = #btnAbrirEspecialista")
	public void divEspecialista() {
		Arbol arbolItem = servicioArbol.buscarPorNombreArbol("Especialista");
		cArbol.abrirVentanas(arbolItem);
	}

	@Listen("onClick = #btnAbrirServicioExterno")
	public void divServicio() {
		Arbol arbolItem = servicioArbol
				.buscarPorNombreArbol("Servicios Externos");
		cArbol.abrirVentanas(arbolItem);
	}

	@Listen("onClick = #btnAbrirMedicina")
	public void divMedicina() {
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

	@Listen("onSelect = #cmbTipoConsulta")
	public void buscarPreventiva() {
		cmbTipoPreventiva.setValue("");
		cmbConsulta.setValue("");
		cmbAccidente.setValue("");
		cmbTipoDiagnostio.setValue("");
		cmbConsulta.setPlaceholder("Seleccione una Consulta");
		cmbTipoDiagnostio.setPlaceholder("Seleccione un Tipo");
		cmbAccidente.setPlaceholder("Seleccione un Tipo");
		if (cmbTipoConsulta.getValue().equals("Control")) {
			cmbConsulta.setVisible(true);
			lblConsulta.setVisible(true);
			cmbAccidente.setVisible(false);
			lblTipoDiagnostico.setVisible(false);
			cmbTipoDiagnostio.setVisible(false);
		} else {
			cmbConsulta.setVisible(false);
			lblConsulta.setVisible(false);
			cmbAccidente.setVisible(true);
			lblTipoDiagnostico.setVisible(true);
			cmbTipoDiagnostio.setVisible(true);
		}
		if (cmbTipoConsulta.getValue().equals("Preventiva")) {
			cmbTipoPreventiva.setVisible(true);
			lblPreventiva.setVisible(true);
		} else {
			cmbTipoPreventiva.setVisible(false);
			lblPreventiva.setVisible(false);
		}

	}

	@Listen("onSelect = #cmbTipoDiagnostio")
	public void buscarAccidente() {
		cmbAccidente.setValue("");
		List<Accidente> accidentes = new ArrayList<Accidente>();
		String valor = "";
		switch (cmbTipoDiagnostio.getValue()) {
		case "Accidente Laboral":
			valor = "Accidente Laboral";
			break;

		case "Accidente Comun":
			valor = "Accidente Comun";
			break;

		case "Enfermedad Laboral":
			valor = "Enfermedad Laboral";
			break;

		case "Enfermedad Comun":
			valor = "Enfermedad Comun";
			break;

		default:
			valor = "Otro";
			break;
		}
		accidentes = servicioAccidente.buscarPorTipo(valor);
		cmbAccidente.setModel(new ListModelList<Accidente>(accidentes));
	}

	public ListModelList<Proveedor> getProveedores() {
		proveedores = new ListModelList<Proveedor>(
				servicioProveedor.buscarTodos());
		return proveedores;
	}

	@Listen("onSelect = #ltbServicioExternoAgregados > template > listitem > listcell")
	public void click(MouseEvent event) {
	    System.out.println("hola");
	    System.out.println(event.getName());
	}
}
