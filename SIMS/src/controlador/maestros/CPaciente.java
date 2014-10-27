package controlador.maestros;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import modelo.maestros.Cargo;
import modelo.maestros.Cita;
import modelo.maestros.Ciudad;
import modelo.maestros.Empresa;
import modelo.maestros.Medicina;
import modelo.maestros.Nomina;
import modelo.maestros.Paciente;
import modelo.seguridad.Arbol;
import modelo.sha.Area;
import modelo.sha.Informe;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaMedicina;
import modelo.transacciones.PacienteMedicina;

import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublespinner;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;

import arbol.CArbol;

import componentes.Botonera;
import componentes.Buscar;
import componentes.Catalogo;
import componentes.Mensaje;
import componentes.Validador;

public class CPaciente extends CGenerico {

	private static final long serialVersionUID = -8967604751368729529L;

	@Wire
	private Tab tabDatosBasicos;
	@Wire
	private Tab tabDatosContacto;
	@Wire
	private Tab tabDatosCronico;
	@Wire
	private Button btnBuscarPaciente;
	@Wire
	private Button fudImagenPaciente;
	@Wire
	private Media media;
	@Wire
	private Image imagenPaciente;
	@Wire
	private Div divPaciente;
	@Wire
	private Div botoneraPaciente;
	@Wire
	private Div catalogoPaciente;
	@Wire
	private Div divCatalogoFamiliar;
	@Wire
	private Textbox txtNombre1Paciente;
	@Wire
	private Textbox txtApellido1Paciente;
	@Wire
	private Textbox txtNombre2Paciente;
	@Wire
	private Textbox txtApellido2Paciente;
	@Wire
	private Textbox txtCedulaPaciente;
	@Wire
	private Radiogroup rdgTipoPaciente;
	@Wire
	private Radio rdoTrabajador;
	@Wire
	private Radio rdoFamiliar;
	@Wire
	private Radiogroup rdgEstatus;
	@Wire
	private Radio rdoActivo;
	@Wire
	private Radio rdoInactivo;
	@Wire
	private Radio rdoMuerte;
	@Wire
	private Radiogroup rdgAlergia;
	@Wire
	private Radio rdoSiAlergico;
	@Wire
	private Radio rdoNoAlergico;
	@Wire
	private Radiogroup rdgLentes;
	@Wire
	private Radio rdoSiLentes;
	@Wire
	private Radio rdoNoLentes;
	@Wire
	private Radiogroup rdgDiscapacidad;
	@Wire
	private Radio rdoSiDiscapacidad;
	@Wire
	private Radio rdoNoDiscapacidad;
	@Wire
	private Textbox txtFichaPaciente;
	@Wire
	private Datebox dtbFechaNac;
	@Wire
	private Textbox txtLugarNacimiento;
	@Wire
	private Textbox txtAlergia;
	@Wire
	private Label lblEdad;
	@Wire
	private Combobox cmbSexo;
	@Wire
	private Combobox cmbEstadoCivil;
	@Wire
	private Combobox cmbGrupoSanguineo;
	@Wire
	private Combobox cmbMano;
	@Wire
	private Doublespinner dspEstatura;
	@Wire
	private Doublespinner dspPeso;
	@Wire
	private Combobox cmbOrigen;
	@Wire
	private Combobox cmbTipoDiscapacidad;
	@Wire
	private Textbox txtOtras;
	@Wire
	private Textbox txtObservacionEstatus;
	@Wire
	private Combobox cmbCargo;
	@Wire
	private Combobox cmbArea;
	@Wire
	private Combobox cmbEmpresa;
	@Wire
	private Combobox cmbNomina;
	@Wire
	private Combobox cmbCiudad;
	@Wire
	private Textbox txtDireccion;
	@Wire
	private Textbox txtTelefono1;
	@Wire
	private Textbox txtTelefono2;
	@Wire
	private Textbox txtCorreo;
	@Wire
	private Textbox txtNombresEmergencia;
	@Wire
	private Textbox txtApellidosEmergencia;
	@Wire
	private Combobox cmbParentescoEmergencia;
	@Wire
	private Textbox txtTelefono1Emergencia;
	@Wire
	private Textbox txtTelefono2Emergencia;
	@Wire
	private Combobox cmbParentescoFamiliar;
	@Wire
	private Button btnBuscarTrabajadores;
	@Wire
	private Row rowCargoyEmpresa;
	@Wire
	private Row rowAreayNomina;
	@Wire
	private Groupbox gbxTrabajadorAsociado;
	@Wire
	private Label lblCedula;
	@Wire
	private Label lblFicha;
	@Wire
	private Label lblNombres;
	@Wire
	private Label lblApellidos;
	//
	@Wire
	private Textbox txtProfesion;
	@Wire
	private Combobox cmbNivelEducativo;
	@Wire
	private Spinner spnCarga;
	@Wire
	private Datebox dtbFechaIngreso;
	@Wire
	private Datebox dtbFechaMuerte;
	@Wire
	private Radio rdoV;
	@Wire
	private Radio rdoE;
	@Wire
	private Textbox txtNroInpsasel;
	@Wire
	private Combobox cmbTurno;
	@Wire
	private Datebox dtbInscripcionIVSS;
	@Wire
	private Textbox txtRetiroIVSS;
	@Wire
	private Datebox dtbFechaEgreso;
	@Wire
	private Button btnVer;
	@Wire
	private Label lblFichaI;
	@Wire
	private Label lblFecha;
	@Wire
	private Radio rdoSiBrigadista;
	@Wire
	private Radio rdoNoBrigadista;
	@Wire
	private Radio rdoSiSocial;
	@Wire
	private Radio rdoNoSocial;
	@Wire
	private Datebox dtbSocial;
	@Wire
	private Textbox txtResumenVisita;
	@Wire
	private Radio rdoSiCronico;
	@Wire
	private Radio rdoNoCronico;
	@Wire
	private Listbox ltbMedicinas;
	@Wire
	private Listbox ltbMedicinasAgregadas;
	@Wire
	private Textbox txtBuscadorMedicina;
	Buscar<Medicina> buscarMedicina;

	URL url = getClass().getResource("usuario.png");
	private CArbol cArbol = new CArbol();
	String id = "";
	String cedTrabajador = "";
	Catalogo<Paciente> catalogo;
	Catalogo<Paciente> catalogoFamiliar;

	List<Medicina> medicinasDisponibles = new ArrayList<Medicina>();
	List<PacienteMedicina> medicinasAgregadas = new ArrayList<PacienteMedicina>();

	private String idBoton = "";

	private String ficha = "";

	@Override
	public void inicializar() throws IOException {

		fudImagenPaciente.setClass("btn");

		contenido = (Include) divPaciente.getParent();
		Tabbox tabox = (Tabbox) divPaciente.getParent().getParent().getParent()
				.getParent();
		tabBox = tabox;
		tab = (Tab) tabox.getTabs().getLastChild();
		HashMap<String, Object> mapa = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (mapa != null) {
			if (mapa.get("tabsGenerales") != null) {
				tabs = (List<Tab>) mapa.get("tabsGenerales");
				mapa.clear();
				mapa = null;
			}
		}
		buscadorMedicina();
		llenarComboCiudad();
		llenarComboEmpresa();
		llenarComboArea();
		llenarComboCargo();
		llenarComboNomina();
		llenarMedicinas();
		rdoActivo.setChecked(true);
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divPaciente, "Paciente", tabs);

			}

			@Override
			public void limpiar() {
				limpiarCampos();
			}

			@Override
			public void guardar() {
				if (validar()) {

					byte[] imagen = null;
					if (media instanceof org.zkoss.image.Image) {
						imagen = imagenPaciente.getContent().getByteData();

					} else {
						try {
							imagenPaciente.setContent(new AImage(url));
						} catch (IOException e) {
							e.printStackTrace();
						}
						imagen = imagenPaciente.getContent().getByteData();
					}

					String profesion, nacionalidad, nivelEducativo, turno, retiroIVSS, nroInpsasel, nombre1, apellido1, cedula, nombre2, apellido2, ficha, detalleAlergia, lugarNac, sexo, estadoCivil, grupoSanguineo, mano, origen, tipoDiscapacidad, otrasDiscapacidad, direccion, telefono1, telefono2, correo, nombresE, apellidosE, telefono1E, telefono2E, parentescoE, parentescoFamiliar;
					int edad, carga;
					boolean trabajador = false, alergia = false, discapacidad = false, lentes = false;
					double estatura, peso;

					Timestamp fechaIngreso = null;
					Timestamp fechaEgreso = null;
					Timestamp fechaInscripcion = null;
					Timestamp fechaMuerte = null;

					if (dtbFechaIngreso.getValue() != null)
						fechaIngreso = new Timestamp(dtbFechaIngreso.getValue()
								.getTime());

					if (dtbFechaEgreso.getValue() != null)
						fechaEgreso = new Timestamp(dtbFechaEgreso.getValue()
								.getTime());

					if (dtbInscripcionIVSS.getValue() != null)
						fechaInscripcion = new Timestamp(dtbInscripcionIVSS
								.getValue().getTime());

					if (rdoV.isChecked())
						nacionalidad = "V";
					else
						nacionalidad = "E";

					boolean estatus = true;
					if (!rdoActivo.isChecked())
						estatus = false;
					else
						estatus = true;

					boolean muerte = false;
					if (rdoMuerte.isChecked()) {
						muerte = true;
						if (dtbFechaMuerte.getValue() != null)
							fechaMuerte = new Timestamp(dtbFechaMuerte
									.getValue().getTime());
					}

					profesion = txtProfesion.getValue();
					nivelEducativo = cmbNivelEducativo.getValue();
					turno = cmbTurno.getValue();
					retiroIVSS = txtRetiroIVSS.getValue();
					nroInpsasel = txtNroInpsasel.getValue();
					carga = spnCarga.getValue();

					nombre1 = txtNombre1Paciente.getValue();
					apellido1 = txtApellido1Paciente.getValue();
					nombre2 = txtNombre2Paciente.getValue();
					apellido2 = txtApellido2Paciente.getValue();
					cedula = txtCedulaPaciente.getValue();
					ficha = txtFichaPaciente.getValue();
					detalleAlergia = txtAlergia.getValue();
					lugarNac = txtLugarNacimiento.getValue();
					sexo = cmbSexo.getValue();
					estadoCivil = cmbEstadoCivil.getValue();
					grupoSanguineo = cmbGrupoSanguineo.getValue();
					mano = cmbMano.getValue();
					origen = cmbOrigen.getValue();
					tipoDiscapacidad = cmbTipoDiscapacidad.getValue();
					otrasDiscapacidad = txtOtras.getValue();
					direccion = txtDireccion.getValue();
					telefono1 = txtTelefono1.getValue();
					telefono2 = txtTelefono2.getValue();
					correo = txtCorreo.getValue();
					nombresE = txtNombresEmergencia.getValue();
					apellidosE = txtApellidosEmergencia.getValue();
					telefono1E = txtTelefono1Emergencia.getValue();
					telefono2E = txtTelefono2Emergencia.getValue();
					parentescoE = cmbParentescoEmergencia.getValue();
					parentescoFamiliar = cmbParentescoFamiliar.getValue();
					String observacionEstatus = txtObservacionEstatus
							.getValue();

					edad = calcularEdad(dtbFechaNac.getValue());
					String cedulaFamiliar = "";
					Empresa empresa = null;
					Area area = null;
					Cargo cargo = null;
					Nomina nomina = null;
					cedTrabajador = lblCedula.getValue();

					if (rdoSiAlergico.isChecked())
						alergia = true;
					if (rdoTrabajador.isChecked()) {
						trabajador = true;
						if (cmbCargo.getSelectedItem().getContext() != null)
							cargo = servicioCargo.buscar(Long
									.parseLong(cmbCargo.getSelectedItem()
											.getContext()));
						if (cmbArea.getSelectedItem().getContext() != null)
							area = servicioArea.buscar(Long.parseLong(cmbArea
									.getSelectedItem().getContext()));
						if (cmbEmpresa.getSelectedItem().getContext() != null)
							empresa = servicioEmpresa.buscar(Long
									.parseLong(cmbEmpresa.getSelectedItem()
											.getContext()));
						if (cmbNomina.getSelectedItem().getContext() != null)
							nomina = servicioNomina.buscar(Long
									.parseLong(cmbNomina.getSelectedItem()
											.getContext()));

					} else {
						cedulaFamiliar = cedTrabajador;
						ficha = "";
					}

					if (rdoSiDiscapacidad.isChecked())
						discapacidad = true;
					if (rdoSiLentes.isChecked())
						lentes = true;

					estatura = dspEstatura.getValue();
					peso = dspPeso.getValue();
					Timestamp fechaNac = new Timestamp(dtbFechaNac.getValue()
							.getTime());

					Ciudad ciudad = servicioCiudad
							.buscar(Long.parseLong(cmbCiudad.getSelectedItem()
									.getContext()));

					String resumenVisita = txtResumenVisita.getValue();
					Boolean brigadista = false;
					Boolean visita = false;
					if (rdoSiBrigadista.isChecked())
						brigadista = true;
					if (rdoSiSocial.isChecked())
						visita = true;
					Timestamp fechaVisita = new Timestamp(dtbSocial.getValue()
							.getTime());
					Boolean cronico = false;
					if (rdoSiCronico.isChecked())
						cronico = true;
					Paciente paciente = new Paciente(cedula, ficha, apellido1,
							nombre1, apellido2, nombre2, trabajador,
							discapacidad, alergia, lentes, fechaNac, lugarNac,
							sexo, estadoCivil, edad, grupoSanguineo,
							detalleAlergia, mano, estatura, peso, origen,
							tipoDiscapacidad, otrasDiscapacidad, fechaHora,
							horaAuditoria, nombreUsuarioSesion(), imagen,
							direccion, correo, telefono1, telefono2, nombresE,
							apellidosE, parentescoE, telefono1E, telefono2E,
							cedulaFamiliar, parentescoFamiliar, empresa,
							ciudad, cargo, area, visita, fechaVisita,
							resumenVisita, brigadista, cronico);

					paciente.setNacionalidad(nacionalidad);
					paciente.setCarga(carga);
					paciente.setNivelEducativo(nivelEducativo);
					paciente.setProfesion(profesion);
					paciente.setNroInpsasel(nroInpsasel);
					paciente.setRetiroIVSS(retiroIVSS);
					paciente.setFechaIngreso(fechaIngreso);
					paciente.setFechaInscripcionIVSS(fechaInscripcion);
					paciente.setFechaEgreso(fechaEgreso);
					paciente.setTurno(turno);
					paciente.setNomina(nomina);
					paciente.setEstatus(estatus);
					paciente.setObservacionEstatus(observacionEstatus);
					paciente.setMuerte(muerte);
					paciente.setFechaMuerte(fechaMuerte);

					servicioPaciente.guardar(paciente);
					if (!rdoActivo.isChecked()) {
						paciente = servicioPaciente.buscarPorCedula(cedula);
						inhabilitarTrabajadorYTodosFamiliares(paciente);
					}
					guardarMedicinas(paciente);
					limpiar();
					msj.mensajeInformacion(Mensaje.guardado);
				}

			}

			@Override
			public void eliminar() {
				if (id.equals("")) {
					Messagebox.show("¿Esta Seguro de Eliminar el Paciente?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Paciente paciente = servicioPaciente
												.buscarPorCedula(String
														.valueOf(id));
										List<Cita> citas = servicioCita
												.buscarPorPaciente(paciente);
										List<Informe> informe = servicioInforme
												.buscarPorPaciente(paciente);
										List<Consulta> consultas = servicioConsulta
												.buscarPorPaciente(paciente);
										if (!citas.isEmpty()
												|| !informe.isEmpty()
												|| !consultas.isEmpty()) {
											msj.mensajeError(Mensaje.noEliminar);
										} else {
											servicioPaciente.eliminar(paciente);
											limpiar();
											msj.mensajeInformacion(Mensaje.eliminado);
										}
									}
								}
							});
				} else {
					msj.mensajeAlerta(Mensaje.noSeleccionoRegistro);
				}

			}
		};
		botoneraPaciente.appendChild(botonera);
	}

	protected void guardarMedicinas(Paciente paciente) {
		servicioPacienteMedicina.limpiarMedicinas(paciente);
		List<PacienteMedicina> listaMedicina = new ArrayList<PacienteMedicina>();
		for (int i = 0; i < ltbMedicinasAgregadas.getItemCount(); i++) {
			Listitem listItem = ltbMedicinasAgregadas.getItemAtIndex(i);
			Integer idMedicina = ((Spinner) ((listItem.getChildren().get(2)))
					.getFirstChild()).getValue();
			Medicina medicina = servicioMedicina.buscar(idMedicina);
			String valor = ((Textbox) ((listItem.getChildren().get(1)))
					.getFirstChild()).getValue();
			PacienteMedicina consultaMedicina = new PacienteMedicina(paciente,
					medicina, valor);
			listaMedicina.add(consultaMedicina);
		}
		servicioPacienteMedicina.guardar(listaMedicina);
	}

	private void llenarMedicinas() {
		Paciente paciente = servicioPaciente.buscarPorCedula(id);
		medicinasDisponibles = servicioMedicina
				.buscarDisponiblesPaciente(paciente);
		ltbMedicinas
				.setModel(new ListModelList<Medicina>(medicinasDisponibles));

		medicinasAgregadas = servicioPacienteMedicina
				.buscarPorPaciente(paciente);
		ltbMedicinasAgregadas.setModel(new ListModelList<PacienteMedicina>(
				medicinasAgregadas));
		listasMultiples();
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

	@Listen("onClick = #btnAbrirMedicina")
	public void divMedicina() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", "paciente");
		map.put("lista", medicinasDisponibles);
		map.put("listbox", ltbMedicinas);
		Sessions.getCurrent().setAttribute("itemsCatalogo", map);
		List<Arbol> arboles = servicioArbol.buscarPorNombreArbol("Medicina");
		if (!arboles.isEmpty()) {
			Arbol arbolItem = arboles.get(0);
			cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
		}
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

	@Listen("onClick = #pasar1Medicina")
	public void derechaMedicina() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem = ltbMedicinas.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					Medicina medicina = listItem.get(i).getValue();
					medicinasDisponibles.remove(medicina);
					PacienteMedicina pacienteMedicina = new PacienteMedicina();
					pacienteMedicina.setMedicina(medicina);
					medicinasAgregadas.clear();
					for (int j = 0; j < ltbMedicinasAgregadas.getItemCount(); j++) {
						Listitem listItemj = ltbMedicinasAgregadas
								.getItemAtIndex(j);
						Integer idMedicina = ((Spinner) ((listItemj
								.getChildren().get(2))).getFirstChild())
								.getValue();
						Medicina medicinaj = servicioMedicina
								.buscar(idMedicina);
						String valor = ((Textbox) ((listItemj.getChildren()
								.get(1))).getFirstChild()).getValue();
						PacienteMedicina pacienteMedicinaj = new PacienteMedicina(
								null, medicinaj, valor);
						medicinasAgregadas.add(pacienteMedicinaj);
					}
					medicinasAgregadas.add(pacienteMedicina);
					ltbMedicinasAgregadas
							.setModel(new ListModelList<PacienteMedicina>(
									medicinasAgregadas));
					ltbMedicinasAgregadas.renderAll();
					listitemEliminar.add(listItem.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbMedicinas.removeItemAt(listitemEliminar.get(i).getIndex());
		}
		listasMultiples();
	}

	private void listasMultiples() {
		ltbMedicinas.setMultiple(false);
		ltbMedicinas.setCheckmark(false);
		ltbMedicinas.setMultiple(true);
		ltbMedicinas.setCheckmark(true);
		ltbMedicinasAgregadas.setMultiple(false);
		ltbMedicinasAgregadas.setCheckmark(false);
		ltbMedicinasAgregadas.setMultiple(true);
		ltbMedicinasAgregadas.setCheckmark(true);
	}

	@Listen("onClick = #pasar2Medicina")
	public void izquierdaMedicina() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem2 = ltbMedicinasAgregadas.getItems();
		if (listItem2.size() != 0) {
			for (int i = 0; i < listItem2.size(); i++) {
				if (listItem2.get(i).isSelected()) {
					PacienteMedicina consultaMedicina = listItem2.get(i)
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

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtApellido1Paciente.getText().compareTo("") == 0
				|| txtNombre1Paciente.getText().compareTo("") == 0
				|| txtCedulaPaciente.getText().compareTo("") == 0
				|| dtbFechaNac.getText().compareTo("") == 0
				|| spnCarga.getValue() == null
				|| cmbEstadoCivil.getText().compareTo("") == 0
				|| cmbGrupoSanguineo.getText().compareTo("") == 0
				|| cmbMano.getText().compareTo("") == 0
				|| cmbSexo.getText().compareTo("") == 0
				|| cmbNivelEducativo.getText().compareTo("") == 0
				|| dspPeso.getValue() == 0
				|| dspEstatura.getValue() == 0
				|| cmbCiudad.getText().compareTo("") == 0
				|| txtTelefono2.getText().compareTo("") == 0
				|| txtProfesion.getText().compareTo("") == 0
				|| (!rdoSiAlergico.isChecked() && !rdoNoAlergico.isChecked())
				|| (!rdoE.isChecked() && !rdoV.isChecked())
				|| (!rdoFamiliar.isChecked() && !rdoTrabajador.isChecked())
				|| (!rdoNoDiscapacidad.isChecked() && !rdoSiDiscapacidad
						.isChecked())
				|| (rdoTrabajador.isChecked() && (cmbCargo.getText().compareTo(
						"") == 0
						|| cmbEmpresa.getText().compareTo("") == 0
						|| cmbArea.getText().compareTo("") == 0
						|| cmbNomina.getText().compareTo("") == 0 || txtFichaPaciente
						.getText().compareTo("") == 0))
				|| (rdoFamiliar.isChecked() && (cmbParentescoFamiliar.getText()
						.compareTo("") == 0 || lblNombres.getValue() == ""))
				|| (!rdoSiLentes.isChecked() && !rdoNoLentes.isChecked())
				|| (rdoMuerte.isChecked() && (dtbFechaMuerte.getText()
						.compareTo("") == 0))) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else {
			if (rdoSiAlergico.isChecked()
					&& txtAlergia.getText().compareTo("") == 0) {
				msj.mensajeError("Debe Especificar la Informacion de la Alergia");
				return false;
			} else {
				if (rdoSiDiscapacidad.isChecked()
						&& (cmbOrigen.getText().compareTo("") == 0 || cmbTipoDiscapacidad
								.getText().compareTo("") == 0)) {
					msj.mensajeError("Debe Especificar la Informacion de la Discapacidad");
					return false;
				} else {
					if (!Validador.validarTelefono(txtTelefono2.getValue())
							|| (txtTelefono1.getText().compareTo("") != 0 && !Validador
									.validarTelefono(txtTelefono1.getValue()))
							|| (txtTelefono2Emergencia.getText().compareTo("") != 0 && !Validador
									.validarTelefono(txtTelefono2Emergencia
											.getValue()))
							|| (txtTelefono1Emergencia.getText().compareTo("") != 0 && !Validador
									.validarTelefono(txtTelefono1Emergencia
											.getValue()))) {
						msj.mensajeError(Mensaje.telefonoInvalido);
						return false;
					} else {
						if (!validarFicha() && rdoTrabajador.isChecked())
							return false;
						else
							return true;
					}

				}
			}
		}
	}

	/* Metodo que valida el formmato del telefono ingresado */
	@Listen("onChange = #txtTelefono1")
	public void validarTelefono() throws IOException {
		if (Validador.validarTelefono(txtTelefono1.getValue()) == false) {
			msj.mensajeAlerta(Mensaje.telefonoInvalido);
		}
	}

	/* Metodo que valida el formmato del telefono ingresado */
	@Listen("onChange = #txtTelefono2")
	public void validarTelefono2() throws IOException {
		if (Validador.validarTelefono(txtTelefono2.getValue()) == false) {
			msj.mensajeAlerta(Mensaje.telefonoInvalido);
		}
	}

	/* Metodo que valida el formmato del telefono ingresado */
	@Listen("onChange = #txtTelefono1Emergencia")
	public void validarTelefonoE() throws IOException {
		if (Validador.validarTelefono(txtTelefono1Emergencia.getValue()) == false) {
			msj.mensajeAlerta(Mensaje.telefonoInvalido);
		}
	}

	/* Metodo que valida el formmato del telefono ingresado */
	@Listen("onChange = #txtTelefono2Emergencia")
	public void validarTelefono2E() throws IOException {
		if (Validador.validarTelefono(txtTelefono2Emergencia.getValue()) == false) {
			msj.mensajeAlerta(Mensaje.telefonoInvalido);
		}
	}

	/* Metodo que valida el formmato del correo ingresado */
	@Listen("onChange = #txtCorreo")
	public void validarCorreo() throws IOException {
		if (Validador.validarCorreo(txtCorreo.getValue()) == false) {
			msj.mensajeAlerta(Mensaje.correoInvalido);
		}
	}

	/* Muestra el catalogo de los Pacientes */
	@Listen("onClick = #btnBuscarPaciente, #btnVer")
	public void mostrarCatalogo(Event evento) {
		idBoton = evento.getTarget().getId();
		List<Paciente> pacientes2 = new ArrayList<Paciente>();
		String segundo = "Ficha";
		if (idBoton.equals("btnBuscarPaciente"))
			pacientes2 = servicioPaciente.buscarTodos();
		else {
			if (!txtCedulaPaciente.getValue().equals(""))
				pacientes2 = servicioPaciente.buscarParientes(txtCedulaPaciente
						.getValue());
			segundo = "Parentesco";
		}
		final List<Paciente> pacientes = pacientes2;
		catalogo = new Catalogo<Paciente>(catalogoPaciente,
				"Catalogo de Pacientes", pacientes, "Cedula", segundo,
				"Nombre", "Apellido") {

			@Override
			protected List<Paciente> buscar(String valor, String combo) {

				switch (combo) {
				case "Nombre":
					if (!idBoton.equals("btnBuscarPaciente"))
						return servicioPaciente.filtroNombre1C(valor,
								txtCedulaPaciente.getValue());
					return servicioPaciente.filtroNombre1(valor);
				case "Parentesco":
					return servicioPaciente.filtroParentescoC(valor,
							txtCedulaPaciente.getValue());
				case "Cedula":
					if (!idBoton.equals("btnBuscarPaciente"))
						return servicioPaciente.filtroCedulaC(valor,
								txtCedulaPaciente.getValue());
					return servicioPaciente.filtroCedula(valor);
				case "Ficha":
					return servicioPaciente.filtroFicha(valor);
				case "Apellido":
					if (!idBoton.equals("btnBuscarPaciente"))
						return servicioPaciente.filtroApellido1C(valor,
								txtCedulaPaciente.getValue());
					return servicioPaciente.filtroApellido1(valor);
				default:
					return pacientes;
				}
			}

			@Override
			protected String[] crearRegistros(Paciente objeto) {
				String valor = objeto.getFicha();
				if (!idBoton.equals("btnBuscarPaciente")) {
					valor = objeto.getParentescoFamiliar();
				}
				String[] registros = new String[4];
				registros[0] = objeto.getCedula();
				registros[1] = valor;
				registros[2] = objeto.getPrimerNombre();
				registros[3] = objeto.getPrimerApellido();
				return registros;
			}

		};
		catalogo.setParent(catalogoPaciente);
		catalogo.doModal();
	}

	/* Muestra el catalogo de los Pacientes */
	@Listen("onClick = #btnBuscarTrabajadores")
	public void mostrarCatalogoFamiliar() {
		final List<Paciente> pacientes = servicioPaciente
				.buscarTodosTrabajadores();
		catalogoFamiliar = new Catalogo<Paciente>(divCatalogoFamiliar,
				"Catalogo de Pacientes", pacientes, "Cedula", "Ficha",
				"Nombre", "Apellido") {

			@Override
			protected List<Paciente> buscar(String valor, String combo) {

				switch (combo) {
				case "Nombre":
					return servicioPaciente.filtroNombre1T(valor);
				case "Cedula":
					return servicioPaciente.filtroCedulaT(valor);
				case "Ficha":
					return servicioPaciente.filtroFichaT(valor);
				case "Apellido":
					return servicioPaciente.filtroApellido1T(valor);
				default:
					return pacientes;
				}
			}

			@Override
			protected String[] crearRegistros(Paciente objeto) {
				String[] registros = new String[4];
				registros[0] = objeto.getCedula();
				registros[1] = objeto.getFicha();
				registros[2] = objeto.getPrimerNombre();
				registros[3] = objeto.getPrimerApellido();
				return registros;
			}

		};
		catalogoFamiliar.setParent(divCatalogoFamiliar);
		catalogoFamiliar.doModal();
	}

	/* Permite la seleccion de un item del catalogo de trabajadores */
	@Listen("onSeleccion = #divCatalogoFamiliar")
	public void seleccinarTrabajador() {
		Paciente familiar = catalogoFamiliar.objetoSeleccionadoDelCatalogo();
		cedTrabajador = familiar.getCedula();
		lblNombres.setValue(familiar.getPrimerNombre() + " "
				+ familiar.getSegundoNombre());
		lblApellidos.setValue(familiar.getPrimerApellido() + " "
				+ familiar.getSegundoApellido());
		lblFicha.setValue(familiar.getFicha());
		lblCedula.setValue(familiar.getCedula());
		catalogoFamiliar.setParent(null);
	}

	// /* Valida la cedula */
	// @Listen("onChange = #txtCedulaPaciente")
	// public void validarCedula() {
	// if (!Validador.validarNumero(txtCedulaPaciente.getValue())) {
	// msj.mensajeAlerta(Mensaje.cedulaInvalida);
	// }
	// }

	/* Valida la Ficha */
	@Listen("onChange = #txtFichaPaciente")
	public boolean validarFicha() {
		List<Paciente> validador = servicioPaciente
				.buscarPorFicha(txtFichaPaciente.getValue());
		if (!validador.isEmpty() && rdoTrabajador.isChecked()) {
			if (!id.equals("")) {
				if (ficha.equals(validador.get(0).getFicha()))
					return true;
			}
			msj.mensajeAlerta(Mensaje.fichaExistente);
			return false;
		}
		return true;
	}

	/* Llena el combo de Empresas cada vez que se abre */
	@Listen("onOpen = #cmbEmpresa")
	public void llenarComboEmpresa() {
		List<Empresa> empresas = servicioEmpresa.buscarTodas();
		cmbEmpresa.setModel(new ListModelList<Empresa>(empresas));
	}

	/* Llena el combo de Empresas cada vez que se abre */
	@Listen("onOpen = #cmbNomina")
	public void llenarComboNomina() {
		List<Nomina> empresas = servicioNomina.buscarTodos();
		cmbNomina.setModel(new ListModelList<Nomina>(empresas));
	}

	/* Llena el combo de Empresas cada vez que se abre */
	@Listen("onOpen = #cmbCiudad")
	public void llenarComboCiudad() {
		List<Ciudad> ciudades = servicioCiudad.buscarTodas();
		cmbCiudad.setModel(new ListModelList<Ciudad>(ciudades));
	}

	/* Llena el combo de Cargos cada vez que se abre */
	@Listen("onOpen = #cmbCargo")
	public void llenarComboCargo() {
		List<Cargo> cargos = servicioCargo.buscarTodos();
		cmbCargo.setModel(new ListModelList<Cargo>(cargos));
	}

	/* Llena el combo de Areas cada vez que se abre */
	@Listen("onOpen = #cmbArea")
	public void llenarComboArea() {
		List<Area> areas = servicioArea.buscarTodos();
		cmbArea.setModel(new ListModelList<Area>(areas));
	}

	/*
	 * Muestra los componentes de la vista relacionados a un trabajador
	 */
	@Listen("onClick =#rdoTrabajador")
	public void esTrabajador() {
		if (!id.equals(""))
			btnVer.setVisible(true);
		else
			btnVer.setVisible(false);
		rowCargoyEmpresa.setVisible(true);
		rowAreayNomina.setVisible(true);
		gbxTrabajadorAsociado.setVisible(false);
		cmbParentescoFamiliar.setValue("");
		cmbParentescoFamiliar.setPlaceholder("Seleccione un Parentesco");
		lblFichaI.setVisible(true);
		lblFecha.setVisible(true);
	}

	@Listen("onClick =#rdoMuerte")
	public void muerte() {
		dtbFechaMuerte.setVisible(true);
	}

	@Listen("onClick =#rdoActivo")
	public void muerte1() {
		dtbFechaMuerte.setVisible(false);
	}

	@Listen("onClick =#rdoInactivo")
	public void muerte2() {
		dtbFechaMuerte.setVisible(false);
	}

	/*
	 * Muestra los componentes de la vista relacionados a un Familiar
	 */
	@Listen("onClick =#rdoFamiliar")
	public void esFamiliar() {
		lblFichaI.setVisible(false);
		lblFecha.setVisible(false);
		btnVer.setVisible(false);
		rowCargoyEmpresa.setVisible(false);
		rowAreayNomina.setVisible(false);
		gbxTrabajadorAsociado.setVisible(true);
		cmbCargo.setValue("");
		cmbCargo.setPlaceholder("Seleccione un Cargo");
		cmbNomina.setValue("");
		cmbCargo.setPlaceholder("Seleccione un Tipo de Nomina");
		cmbArea.setValue("");
		cmbArea.setPlaceholder("Seleccione un Area");
		cmbEmpresa.setValue("");
		cmbEmpresa.setPlaceholder("Seleccione una Empresa");

	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoPaciente")
	public void seleccinar() {
		Paciente paciente = catalogo.objetoSeleccionadoDelCatalogo();
		if (!idBoton.equals("btnVer")) {
			llenarCampos(paciente);
			llenarMedicinas();
			ficha = paciente.getFicha();
		}
		catalogo.setParent(null);
	}

	/* Busca si existe un diagnostico con el mismo codigo escrito */
	@Listen("onChange = #txtCedulaPaciente")
	public void buscarPorCedula() {
		Paciente paciente = servicioPaciente.buscarPorCedula(txtCedulaPaciente
				.getValue());
		if (paciente != null)
			llenarCampos(paciente);
	}

	/* LLena los campos del formulario dado un paciente */
	private void llenarCampos(Paciente paciente) {

		txtCedulaPaciente.setValue(paciente.getCedula());
		txtObservacionEstatus.setValue(paciente.getObservacionEstatus());
		txtNombre1Paciente.setValue(paciente.getPrimerNombre());
		txtApellido1Paciente.setValue(paciente.getPrimerApellido());
		txtNombre2Paciente.setValue(paciente.getSegundoNombre());
		txtApellido2Paciente.setValue(paciente.getSegundoApellido());
		txtCedulaPaciente.setDisabled(true);
		// txtFichaPaciente.setDisabled(true);
		id = paciente.getCedula();
		txtFichaPaciente.setValue(paciente.getFicha());
		txtAlergia.setValue(paciente.getObservacionAlergias());
		txtLugarNacimiento.setValue(paciente.getLugarNacimiento());
		cmbSexo.setValue(paciente.getSexo());
		cmbEstadoCivil.setValue(paciente.getEstadoCivil());
		cmbGrupoSanguineo.setValue(paciente.getGrupoSanguineo());
		cmbMano.setValue(paciente.getMano());
		cmbOrigen.setValue(paciente.getOrigenDiscapacidad());
		cmbTipoDiscapacidad.setValue(paciente.getTipoDiscapacidad());
		txtOtras.setValue(paciente.getOrigenDiscapacidad());
		txtDireccion.setValue(paciente.getDireccion());
		txtTelefono1.setValue(paciente.getTelefono1());
		txtTelefono2.setValue(paciente.getTelefono2());
		txtCorreo.setValue(paciente.getEmail());
		txtNombresEmergencia.setValue(paciente.getNombresEmergencia());
		txtApellidosEmergencia.setValue(paciente.getApellidosEmergencia());
		txtTelefono1Emergencia.setValue(paciente.getTelefono1Emergencia());
		txtTelefono2Emergencia.setValue(paciente.getTelefono2Emergencia());
		cmbParentescoEmergencia.setValue(paciente.getParentescoEmergencia());
		cmbParentescoFamiliar.setValue(paciente.getParentescoFamiliar());
		lblEdad.setValue(String.valueOf(calcularEdad(paciente
				.getFechaNacimiento())));
		dspEstatura.setValue(paciente.getEstatura());
		dspPeso.setValue(paciente.getPeso());
		cmbCiudad.setValue(paciente.getCiudadVivienda().getNombre());
		spnCarga.setValue(paciente.getCarga());
		txtNroInpsasel.setValue(paciente.getNroInpsasel());
		txtProfesion.setValue(paciente.getProfesion());
		txtRetiroIVSS.setValue(paciente.getRetiroIVSS());
		cmbNivelEducativo.setValue(paciente.getNivelEducativo());
		cmbTurno.setValue(paciente.getTurno());
		dtbFechaEgreso.setValue(paciente.getFechaEgreso());
		dtbFechaIngreso.setValue(paciente.getFechaIngreso());
		dtbInscripcionIVSS.setValue(paciente.getFechaInscripcionIVSS());
		dtbFechaNac.setValue(paciente.getFechaNacimiento());

		if (paciente.getNacionalidad() != null) {
			if (paciente.getNacionalidad().equals("V"))
				rdoV.setChecked(true);
			else
				rdoE.setChecked(true);
		}
		if (paciente.isAlergia())
			rdoSiAlergico.setChecked(true);
		else
			rdoNoAlergico.setChecked(true);

		if (paciente.isMuerte()) {
			rdoMuerte.setChecked(true);
			dtbFechaMuerte.setVisible(true);
			dtbFechaMuerte.setValue(paciente.getFechaMuerte());
		} else {
			if (!paciente.isEstatus())
				rdoInactivo.setChecked(true);
			else
				rdoActivo.setChecked(true);
		}

		if (paciente.isTrabajador()) {
			if (paciente.getCargoReal() != null)
				cmbCargo.setValue(paciente.getCargoReal().getNombre());
			if (paciente.getArea() != null)
				cmbArea.setValue(paciente.getArea().getNombre());
			if (paciente.getEmpresa() != null)
				cmbEmpresa.setValue(paciente.getEmpresa().getNombre());
			if (paciente.getNomina() != null)
				cmbNomina.setValue(paciente.getNomina().getNombre());
			rdoTrabajador.setChecked(true);
			esTrabajador();
		} else {
			rdoFamiliar.setChecked(true);
			esFamiliar();
		}
		rdoTrabajador.setDisabled(true);
		rdoFamiliar.setDisabled(true);

		if (paciente.isDiscapacidad())
			rdoSiDiscapacidad.setChecked(true);
		else
			rdoNoDiscapacidad.setChecked(true);

		if (paciente.isLentes())
			rdoSiLentes.setChecked(true);
		else
			rdoNoLentes.setChecked(true);

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

		if (!paciente.isTrabajador()) {
			Paciente familiar = servicioPaciente.buscarPorCedula(paciente
					.getCedulaFamiliar());
			if (familiar != null) {
				lblNombres.setValue(familiar.getPrimerNombre() + " "
						+ familiar.getSegundoNombre());
				lblApellidos.setValue(familiar.getPrimerApellido() + " "
						+ familiar.getSegundoApellido());
				lblFicha.setValue(familiar.getFicha());
				lblCedula.setValue(familiar.getCedula());
				cedTrabajador = familiar.getCedula();
			}
		}
		if (paciente.getBrigadista() != null) {
			if (paciente.getBrigadista())
				rdoSiBrigadista.setChecked(true);
			else
				rdoNoBrigadista.setChecked(true);
		}
		if (paciente.getVisita() != null) {
			if (paciente.getVisita())
				rdoSiSocial.setChecked(true);
			else
				rdoNoSocial.setChecked(true);
		}
		if (paciente.getFechaVisita() != null) {
			dtbSocial.setValue(paciente.getFechaVisita());
		}
		if (paciente.getResumenVisita() != null) {
			txtResumenVisita.setValue(paciente.getResumenVisita());
		}
		if (paciente.getCronico() != null) {
			if (paciente.getCronico())
				rdoSiCronico.setChecked(true);
			else
				rdoNoCronico.setChecked(true);
		}

	}

	/* Permite subir una imagen a la vista */
	@Listen("onUpload = #fudImagenPaciente")
	public void processMedia(UploadEvent event) {
		media = event.getMedia();
		imagenPaciente.setContent((org.zkoss.image.Image) media);

	}

	/* Abre la vista de Empresa */
	@Listen("onClick = #btnAbrirEmpresa")
	public void abrirEmpresa() {
		List<Arbol> arboles = servicioArbol.buscarPorNombreArbol("Empresa");
		if (!arboles.isEmpty()) {
			Arbol arbolItem = arboles.get(0);
			cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
		}
	}

	/* Abre la vista de Cargo */
	@Listen("onClick = #btnAbrirCargo")
	public void abrirCargo() {
		List<Arbol> arboles = servicioArbol.buscarPorNombreArbol("Cargo");
		if (!arboles.isEmpty()) {
			Arbol arbolItem = arboles.get(0);
			cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
		}
	}

	/* Abre la vista de Nomina */
	@Listen("onClick = #btnAbrirNomina")
	public void abrirNomina() {
		List<Arbol> arboles = servicioArbol.buscarPorNombreArbol("Nomina");
		if (!arboles.isEmpty()) {
			Arbol arbolItem = arboles.get(0);
			cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
		}
	}

	/* Abre la vista de Area */
	@Listen("onClick = #btnAbrirArea")
	public void abrirArea() {
		List<Arbol> arboles = servicioArbol.buscarPorNombreArbol("Area");
		if (!arboles.isEmpty()) {
			Arbol arbolItem = arboles.get(0);
			cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
		}
	}

	/* Abre la vista de Ciudad */
	@Listen("onClick = #btnAbrirCiudad")
	public void abrirCiudad() {
		List<Arbol> arboles = servicioArbol.buscarPorNombreArbol("Ciudad");
		if (!arboles.isEmpty()) {
			Arbol arbolItem = arboles.get(0);
			cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
		}
	}

	/* Abre la pestanna de Datos contacto */
	@Listen("onClick = #btnSiguientePestanna")
	public void siguientePestanna() {
		tabDatosContacto.setSelected(true);
	}

	/* Abre la pestanna de datos basicos */
	@Listen("onClick = #btnAnteriorPestanna")
	public void anteriorPestanna() {
		tabDatosBasicos.setSelected(true);
	}

	@Listen("onClick = #btnAnterior2")
	public void anteriorPestanna2() {
		tabDatosContacto.setSelected(true);
	}

	@Listen("onClick = #btnSiguiente2")
	public void siguientePestanna2() {
		tabDatosCronico.setSelected(true);
	}

	/* Busca si existe un diagnostico con el mismo codigo escrito */
	@Listen("onChange = #dtbFechaNac")
	public void cambioEdad() {
		lblEdad.setValue(String.valueOf(calcularEdad(dtbFechaNac.getValue())));
	}

	@Listen("onOK = #txtCedulaPaciente")
	public void buscarCedula() {
		Paciente paciente = servicioPaciente.buscarPorCedula(txtCedulaPaciente
				.getValue());
		if (paciente != null) {
			llenarCampos(paciente);
			llenarMedicinas();
		} else {
			limpiarCampos();
			msj.mensajeError(Mensaje.cedulaNoExiste);
		}
	}

	public void limpiarCampos() {
		idBoton = "";
		txtObservacionEstatus.setValue("");
		txtNombre1Paciente.setValue("");
		txtCedulaPaciente.setValue("");
		txtCedulaPaciente.setDisabled(false);
		txtFichaPaciente.setDisabled(false);
		txtApellido1Paciente.setValue("");
		txtNombre2Paciente.setValue("");
		txtApellido2Paciente.setValue("");
		cmbEmpresa.setValue("");
		cmbEmpresa.setPlaceholder("Seleccione una Empresa");
		cmbNomina.setValue("");
		cmbNomina.setPlaceholder("Seleccione un Tipo de Nomina");
		imagenPaciente.setVisible(false);
		id = "";
		txtFichaPaciente.setValue("");
		txtAlergia.setValue("");
		txtLugarNacimiento.setValue("");
		cmbSexo.setValue("");
		cmbSexo.setPlaceholder("Seleccione el Sexo");
		cmbEstadoCivil.setValue("");
		cmbEstadoCivil.setPlaceholder("Seleccione el Estado Civil");
		cmbGrupoSanguineo.setValue("");
		cmbGrupoSanguineo.setPlaceholder("Seleccione el Grupo");
		cmbMano.setValue("");
		cmbMano.setPlaceholder("Seleccione el Valor");
		cmbOrigen.setValue("");
		cmbOrigen.setPlaceholder("Seleccione el Origen");
		cmbTipoDiscapacidad.setValue("");
		cmbTipoDiscapacidad.setPlaceholder("Seleccione un Tipo");
		txtOtras.setValue("");
		cmbCargo.setValue("");
		cmbCargo.setPlaceholder("Seleccione un Cargo");
		cmbArea.setValue("");
		cmbArea.setPlaceholder("Seleccione un Area");
		txtDireccion.setValue("");
		txtTelefono1.setValue("");
		txtTelefono2.setValue("");
		txtCorreo.setValue("");
		txtNombresEmergencia.setValue("");
		txtApellidosEmergencia.setValue("");
		txtTelefono1Emergencia.setValue("");
		txtTelefono2Emergencia.setValue("");
		cmbParentescoEmergencia.setValue("");
		cmbParentescoEmergencia.setPlaceholder("Seleccione el Parentesco");
		cmbParentescoFamiliar.setValue("");
		cmbParentescoFamiliar.setPlaceholder("Seleccione el Parentesco");
		lblEdad.setValue("");
		dspEstatura.setValue((double) 0);
		dspPeso.setValue((double) 0);
		cmbCiudad.setValue("");
		cmbCiudad.setPlaceholder("Seleccione una Ciudad");
		rdoSiAlergico.setValue(null);
		rdoNoAlergico.setValue(null);
		lblApellidos.setValue("");
		lblCedula.setValue("");
		lblFicha.setValue("");
		lblNombres.setValue("");
		txtNroInpsasel.setValue("");
		txtProfesion.setValue("");
		txtRetiroIVSS.setValue("");
		cmbNivelEducativo.setValue("");
		cmbNivelEducativo.setPlaceholder("Seleccione un Nivel");
		cmbTurno.setValue("");
		cmbTurno.setPlaceholder("Seleccione un Turno");
		spnCarga.setValue(0);
		dtbFechaEgreso.setValue(fecha);
		dtbFechaIngreso.setValue(fecha);
		dtbSocial.setValue(fecha);
		txtResumenVisita.setValue("");
		rdoNoBrigadista.setChecked(false);
		rdoSiBrigadista.setChecked(false);
		rdoSiSocial.setChecked(false);
		rdoNoSocial.setChecked(false);
		rdoSiCronico.setChecked(false);
		rdoNoCronico.setChecked(false);
		dtbInscripcionIVSS.setValue(fecha);
		dtbFechaMuerte.setValue(fecha);
		dtbFechaMuerte.setVisible(false);
		rdoTrabajador.setValue(null);
		rdoFamiliar.setValue(null);

		rdoActivo.setChecked(true);
		rdoInactivo.setValue(null);

		rdoSiDiscapacidad.setValue(null);
		rdoNoDiscapacidad.setValue(null);

		rdoSiLentes.setValue(null);
		rdoNoLentes.setValue(null);

		rdoTrabajador.setDisabled(false);
		rdoFamiliar.setDisabled(false);

		rdoE.setDisabled(false);
		rdoV.setDisabled(false);
		tabDatosBasicos.setSelected(true);
		llenarMedicinas();
	}

}
