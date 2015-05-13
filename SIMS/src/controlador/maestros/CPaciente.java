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
import modelo.maestros.EstadoCivil;
import modelo.maestros.Familiar;
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
import org.zkoss.zul.Checkbox;
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
import controlador.transacciones.CCambiarCedula;

public class CPaciente extends CGenerico {

	private static final long serialVersionUID = -8967604751368729529L;

	@Wire
	private Div divCatalogoModeloFamiliar;
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
	private Textbox txtTelefonoAdicional;
	@Wire
	private Textbox txtCorreoEmpresa;
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
	private Button btnBuscarTrabajadores;
	@Wire
	private Row rowCargoyEmpresa;
	@Wire
	private Row rowAreayNomina;
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
	private Radio rdoSiCronico;
	@Wire
	private Radio rdoNoCronico;
	@Wire
	private Listbox ltbMedicinas;
	@Wire
	private Listbox ltbMedicinasAgregadas;
	@Wire
	private Textbox txtBuscadorMedicina;
	@Wire
	private Radiogroup rdgJefe;
	@Wire
	private Radio rdoJefe;
	@Wire
	private Radio rdoNoJefe;
	@Wire
	private Textbox txtOficio;
	@Wire
	private Combobox cmbCondicionTrabajador;
	@Wire
	private Label lblEstado;
	@Wire
	private Textbox txtUrb;
	@Wire
	private Textbox txtAvCalle;
	@Wire
	private Textbox txtSector;
	@Wire
	private Textbox txtPuntoReferencia;
	@Wire
	private Textbox txtParroquia;
	@Wire
	private Textbox txtMunicipio;
	@Wire
	private Textbox txtNro;
	@Wire
	private Textbox txtOtroTransporte;
	@Wire
	private Checkbox chkPublico;
	@Wire
	private Checkbox chkOtro;
	@Wire
	private Checkbox chkPrivado;
	@Wire
	private Checkbox chkDusa;
	@Wire
	private Checkbox chkMoto;
	@Wire
	private Checkbox chkBicicleta;
	@Wire
	private Combobox cmbRif;
	@Wire
	private Textbox txtRifPaciente;
	@Wire
	private Textbox txtPasaporte;
	@Wire
	private Radiogroup rdgEstudia;
	@Wire
	private Radio rdoEstudia;
	@Wire
	private Radio rdoTrabaja;
	@Wire
	private Textbox txtLugarEstudios;
	@Wire
	private Textbox txtCarreraActual;
	@Wire
	private Textbox txtPeriodo;
	@Wire
	private Radiogroup rdgF;

	Buscar<Medicina> buscarMedicina;

	URL url = getClass().getResource("usuario.png");
	private CArbol cArbol = new CArbol();
	CCambiarCedula cambiar = new CCambiarCedula();
	String id = "";
	String cedTrabajador = "";
	Catalogo<Paciente> catalogo;
	Catalogo<Paciente> catalogoFamiliar;

	List<Medicina> medicinasDisponibles = new ArrayList<Medicina>();
	List<PacienteMedicina> medicinasAgregadas = new ArrayList<PacienteMedicina>();
	Catalogo<Familiar> catalogoModeloFamiliar;
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
		llenarComboCivil();
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

					String oficio, condicion = "", profesion, nacionalidad, nivelEducativo, turno, retiroIVSS, nroInpsasel, nombre1, apellido1, cedula, nombre2, apellido2, ficha, detalleAlergia, lugarNac, sexo, grupoSanguineo, mano, origen, tipoDiscapacidad, otrasDiscapacidad, direccion, telefono1, telefono2, correo, nombresE, apellidosE, telefono1E, telefono2E, parentescoE, parentescoFamiliar;
					int edad, carga;
					boolean alergia = false, discapacidad = false, lentes = false, jefe = false, estudia=false;
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

					String telefonoAdicional = txtTelefonoAdicional.getValue();
					String correoEmpresa = txtCorreoEmpresa.getValue();
					String observacionEstatus = txtObservacionEstatus
							.getValue();

					edad = calcularEdad(dtbFechaNac.getValue());
					String cedulaFamiliar = "";
					Empresa empresa = null;
					Area area = null;
					Cargo cargo = null;
					Nomina nomina = null;
					EstadoCivil estadoCivil = null;
					if (rdoSiAlergico.isChecked())
						alergia = true;
					if (cmbEstadoCivil.getSelectedItem() != null) {
						if (cmbEstadoCivil.getSelectedItem().getContext() != null) {
							estadoCivil = servicioEstadoCivil.buscar(Long
									.parseLong(cmbEstadoCivil.getSelectedItem()
											.getContext()));
						}
					}
					if (cmbCargo.getSelectedItem().getContext() != null)
						cargo = servicioCargo.buscar(Long.parseLong(cmbCargo
								.getSelectedItem().getContext()));
					if (cmbArea.getSelectedItem().getContext() != null)
						area = servicioArea.buscar(Long.parseLong(cmbArea
								.getSelectedItem().getContext()));
					if (cmbEmpresa.getSelectedItem().getContext() != null)
						empresa = servicioEmpresa.buscar(Long
								.parseLong(cmbEmpresa.getSelectedItem()
										.getContext()));
					if (cmbNomina.getSelectedItem().getContext() != null)
						nomina = servicioNomina.buscar(Long.parseLong(cmbNomina
								.getSelectedItem().getContext()));

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
					Boolean brigadista = false;

					if (rdoSiBrigadista.isChecked())
						brigadista = true;
					Boolean cronico = false;
					if (rdoSiCronico.isChecked())
						cronico = true;

					oficio = txtOficio.getValue();
					if (!cmbCondicionTrabajador.getValue().equals(""))
						condicion = cmbCondicionTrabajador.getValue();

					if (rdoJefe.isChecked())
						jefe = true;
					
					if (rdoEstudia.isChecked())
						estudia = true;
					
					String lugarEstudio = txtLugarEstudios.getValue();
					String carrera = txtCarreraActual.getValue();
					String periodo = txtPeriodo.getValue();
					

					Paciente paciente = new Paciente(cedula, ficha, apellido1,
							nombre1, apellido2, nombre2, true, discapacidad,
							alergia, lentes, fechaNac, lugarNac, sexo, edad,
							grupoSanguineo, detalleAlergia, mano, estatura,
							peso, origen, tipoDiscapacidad, otrasDiscapacidad,
							fechaHora, horaAuditoria, nombreUsuarioSesion(),
							imagen, direccion, correo, telefono1, telefono2,
							nombresE, apellidosE, parentescoE, telefono1E,
							telefono2E, cedulaFamiliar, "", empresa, ciudad,
							cargo, area, cronico);
					paciente.setBrigadista(brigadista);
					paciente.setEstadoCivil(estadoCivil);
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
					paciente.setJefe(jefe);
					paciente.setCondicion(condicion);
					paciente.setOficio(oficio);
					paciente.setCargoOCarrera(carrera);
					paciente.setLugarTrabajo(lugarEstudio);
					paciente.setPeriodoEstudios(periodo);
					paciente.setEstudia(estudia);

					paciente.setMunicipio(txtMunicipio.getValue());
					paciente.setParroquia(txtParroquia.getValue());
					paciente.setSector(txtSector.getValue());
					paciente.setPuntoReferencia(txtPuntoReferencia.getValue());
					paciente.setUrb(txtUrb.getValue());
					paciente.setAvCalle(txtAvCalle.getValue());

					
					String rif = txtRifPaciente.getValue();
					String tipoRif = cmbRif.getValue();
					String pasaporte = txtPasaporte.getValue();

					paciente.setTelefonoAdicional(telefonoAdicional);
					paciente.setEmailEmpresa(correoEmpresa);
					paciente.setRif(rif);
					paciente.setTipoRif(tipoRif);
					paciente.setPasaporte(pasaporte);

					paciente.setNro(txtNro.getValue());
					paciente.setOtroTransporte(txtOtroTransporte.getValue());
					String transporte = "";
					if (chkPublico.isChecked())
						transporte = transporte + "," + chkPublico.getLabel();
					if (chkOtro.isChecked())
						transporte = transporte + "," + chkOtro.getLabel();
					if (chkPrivado.isChecked())
						transporte = transporte + "," + chkPrivado.getLabel();
					if (chkDusa.isChecked())
						transporte = transporte + "," + chkDusa.getLabel();
					if (chkMoto.isChecked())
						transporte = transporte + "," + chkMoto.getLabel();
					if (chkBicicleta.isChecked())
						transporte = transporte + "," + chkBicicleta.getLabel();

					paciente.setTransporte(transporte);
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
			}
		};
		botonera.getChildren().get(1).setVisible(false);
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
				|| dspPeso.getValue() == null
				|| dspEstatura.getValue() == null
				|| cmbCiudad.getText().compareTo("") == 0
				|| txtTelefono2.getText().compareTo("") == 0
				|| (!rdoSiAlergico.isChecked() && !rdoNoAlergico.isChecked())
				|| (!rdoE.isChecked() && !rdoV.isChecked())
				|| (!rdoNoDiscapacidad.isChecked() && !rdoSiDiscapacidad
						.isChecked())
				|| cmbCargo.getText().compareTo("") == 0
				|| cmbEmpresa.getText().compareTo("") == 0
				|| cmbArea.getText().compareTo("") == 0
				|| cmbNomina.getText().compareTo("") == 0
				|| txtFichaPaciente.getText().compareTo("") == 0
				|| (!rdoSiLentes.isChecked() && !rdoNoLentes.isChecked())
				|| (rdoMuerte.isChecked() && (dtbFechaMuerte.getText()
						.compareTo("") == 0))) {
			msj.mensajeError(Mensaje.camposVacios);
			aplicarColores(rdgF, rdgAlergia, rdgDiscapacidad, rdgLentes,
					 txtApellido1Paciente, txtNombre1Paciente,
					txtCedulaPaciente, spnCarga, cmbEstadoCivil,
					cmbGrupoSanguineo, cmbMano, cmbSexo, dspPeso, dspEstatura,
					cmbCiudad, cmbCargo, txtTelefono2, cmbEmpresa, cmbArea,
					cmbNomina, txtFichaPaciente,cmbNivelEducativo,dspPeso,dspEstatura);
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
											.getValue()))
							|| (txtTelefonoAdicional.getText().compareTo("") != 0 && !Validador
									.validarTelefono(txtTelefono1Emergencia
											.getValue()))) {
						msj.mensajeError(Mensaje.telefonoInvalido);
						return false;
					} else {
						if (!validarFicha())
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
	@Listen("onChange = #txtTelefonoAdicional")
	public void validarTelefonoAdicional() throws IOException {
		if (Validador.validarTelefono(txtTelefonoAdicional.getValue()) == false) {
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
	
	@Listen("onChange = #txtCorreoEmpresa")
	public void validarCorreoEmpresa() throws IOException {
		if (Validador.validarCorreo(txtCorreoEmpresa.getValue()) == false) {
			msj.mensajeAlerta(Mensaje.correoInvalido);
		}
	}


	/* Muestra el catalogo de los Pacientes */
	@Listen("onClick = #btnBuscarPaciente, #btnVer")
	public void mostrarCatalogo(Event evento) {
		idBoton = evento.getTarget().getId();
		List<Paciente> pacientes2 = new ArrayList<Paciente>();
		String segundo = "Ficha";
		String titulo = "Catalogo";
		if (idBoton.equals("btnBuscarPaciente")) {
			pacientes2 = servicioPaciente.buscarTodosTrabajadores();
			titulo = "Catalogo de Trabajadores Pacientes";
		} else {
			if (!txtCedulaPaciente.getValue().equals(""))
				pacientes2 = servicioPaciente.buscarParientes(txtCedulaPaciente
						.getValue());
			segundo = "Parentesco";
			titulo = "Catalogo de Familiares que aplican a Servicios Medicos";
		}
		final List<Paciente> pacientes = pacientes2;
		catalogo = new Catalogo<Paciente>(catalogoPaciente, titulo, pacientes,
				false, "Cedula", segundo, "Primer Nombre", "Segundo Nombre",
				"Primer Apellido", "Segundo Apellido", "Estado",
				"Trabajador Asociado") {

			@Override
			protected List<Paciente> buscar(String valor, String combo) {

				switch (combo) {
				case "Primer Nombre":
					if (!idBoton.equals("btnBuscarPaciente"))
						return servicioPaciente.filtroNombre1C(valor,
								txtCedulaPaciente.getValue());
					return servicioPaciente.filtroNombre1T(valor);
				case "Segundo Nombre":
					if (!idBoton.equals("btnBuscarPaciente"))
						return servicioPaciente.filtroNombre2C(valor,
								txtCedulaPaciente.getValue());
					return servicioPaciente.filtroNombre2T(valor);
				case "Parentesco":
					return servicioPaciente.filtroParentescoC(valor,
							txtCedulaPaciente.getValue());
				case "Cedula":
					if (!idBoton.equals("btnBuscarPaciente"))
						return servicioPaciente.filtroCedulaC(valor,
								txtCedulaPaciente.getValue());
					return servicioPaciente.filtroCedulaT(valor);
				case "Ficha":
					return servicioPaciente.filtroFichaT(valor);
				case "Primer Apellido":
					if (!idBoton.equals("btnBuscarPaciente"))
						return servicioPaciente.filtroApellido1C(valor,
								txtCedulaPaciente.getValue());
					return servicioPaciente.filtroApellido1T(valor);
				case "Segundo Apellido":
					if (!idBoton.equals("btnBuscarPaciente"))
						return servicioPaciente.filtroApellido2C(valor,
								txtCedulaPaciente.getValue());
					return servicioPaciente.filtroApellido2T(valor);
				case "Trabajador Asociado":
					if (!idBoton.equals("btnBuscarPaciente"))
						return servicioPaciente.filtroCedulaAsociadoC(valor,
								txtCedulaPaciente.getValue());
					return servicioPaciente.filtroCedulaAsociado(valor);
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
				String activo = "Activo";
				if (!objeto.isEstatus())
					activo = "Inactivo";
				String[] registros = new String[8];
				registros[0] = objeto.getCedula();
				registros[1] = valor;
				registros[2] = objeto.getPrimerNombre();
				registros[3] = objeto.getSegundoNombre();
				registros[4] = objeto.getPrimerApellido();
				registros[5] = objeto.getSegundoApellido();
				registros[6] = activo;
				registros[7] = objeto.getCedulaFamiliar();
				return registros;
			}

		};
		catalogo.setParent(catalogoPaciente);
		catalogo.doModal();
	}

	/* Valida la Ficha */
	@Listen("onChange = #txtFichaPaciente")
	public boolean validarFicha() {
		List<Paciente> validador = servicioPaciente
				.buscarPorFicha(txtFichaPaciente.getValue());
		if (!validador.isEmpty()) {
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

	/* Llena el combo de Empresas cada vez que se abre */
	@Listen("onSelect = #cmbCiudad")
	public void selectComboCiudad() {
		Ciudad ciudad = servicioCiudad.buscar(Long.parseLong(cmbCiudad
				.getSelectedItem().getContext()));
		if (ciudad != null)
			lblEstado.setValue(ciudad.getEstado().getNombre());
	}

	/* Llena el combo de Estado cada vez que se abre */
	@Listen("onOpen = #cmbEstadoCivil")
	public void llenarComboCivil() {
		List<EstadoCivil> ciudades = servicioEstadoCivil.buscarTodas();
		cmbEstadoCivil.setModel(new ListModelList<EstadoCivil>(ciudades));
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
		if (paciente != null) {
			llenarCampos(paciente);
			llenarMedicinas();
			ficha = paciente.getFicha();
		}
	}

	/* LLena los campos del formulario dado un paciente */
	private void llenarCampos(Paciente paciente) {

		cmbRif.setValue(paciente.getTipoRif());
		txtRifPaciente.setValue(paciente.getRif());
		txtPasaporte.setValue(paciente.getPasaporte());
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
		if (paciente.getEstadoCivil() != null)
			cmbEstadoCivil.setValue(paciente.getEstadoCivil().getNombre());
		cmbGrupoSanguineo.setValue(paciente.getGrupoSanguineo());
		cmbMano.setValue(paciente.getMano());
		cmbOrigen.setValue(paciente.getOrigenDiscapacidad());
		cmbTipoDiscapacidad.setValue(paciente.getTipoDiscapacidad());
		txtOtras.setValue(paciente.getOrigenDiscapacidad());
		txtDireccion.setValue(paciente.getDireccion());
		txtTelefono1.setValue(paciente.getTelefono1());
		txtTelefono2.setValue(paciente.getTelefono2());
		txtCorreo.setValue(paciente.getEmail());
		txtTelefonoAdicional.setValue(paciente.getTelefonoAdicional());
		txtCorreoEmpresa.setValue(paciente.getEmailEmpresa());
		txtNombresEmergencia.setValue(paciente.getNombresEmergencia());
		txtApellidosEmergencia.setValue(paciente.getApellidosEmergencia());
		txtTelefono1Emergencia.setValue(paciente.getTelefono1Emergencia());
		txtTelefono2Emergencia.setValue(paciente.getTelefono2Emergencia());
		cmbParentescoEmergencia.setValue(paciente.getParentescoEmergencia());
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

		if (paciente.getCargoReal() != null)
			cmbCargo.setValue(paciente.getCargoReal().getNombre());
		if (paciente.getArea() != null)
			cmbArea.setValue(paciente.getArea().getNombre());
		if (paciente.getEmpresa() != null)
			cmbEmpresa.setValue(paciente.getEmpresa().getNombre());
		if (paciente.getNomina() != null)
			cmbNomina.setValue(paciente.getNomina().getNombre());

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
		if (paciente.getBrigadista() != null) {
			if (paciente.getBrigadista())
				rdoSiBrigadista.setChecked(true);
			else
				rdoNoBrigadista.setChecked(true);
		} else {
			rdoNoBrigadista.setChecked(false);
			rdoSiBrigadista.setChecked(false);
		}

		if (paciente.getCronico() != null) {
			if (paciente.getCronico())
				rdoSiCronico.setChecked(true);
			else
				rdoNoCronico.setChecked(true);
		} else {
			rdoNoCronico.setChecked(false);
			rdoSiCronico.setChecked(false);
		}

		if (paciente.isJefe())
			rdoJefe.setChecked(true);
		else
			rdoNoJefe.setChecked(false);

		cmbCondicionTrabajador.setValue(paciente.getCondicion());
		txtOficio.setValue(paciente.getOficio());
		txtUrb.setValue(paciente.getUrb());
		txtParroquia.setValue(paciente.getParroquia());
		txtMunicipio.setValue(paciente.getMunicipio());
		txtAvCalle.setValue(paciente.getAvCalle());
		txtPuntoReferencia.setValue(paciente.getPuntoReferencia());
		txtSector.setValue(paciente.getSector());
		txtNro.setValue(paciente.getNro());
		txtOtroTransporte.setValue(paciente.getOtroTransporte());
		lblEstado
				.setValue(paciente.getCiudadVivienda().getEstado().getNombre());
		
		txtLugarEstudios.setValue(paciente.getLugarTrabajo());
		txtCarreraActual.setValue(paciente.getCargoOCarrera());
		txtPeriodo.setValue(paciente.getPeriodoEstudios());
		
		if (paciente.isEstudia())
			rdoEstudia.setChecked(true);
		
		if (paciente.getTransporte() != null) {
			if (!paciente.getTransporte().equals("")) {
				String valores[] = paciente.getTransporte().split(",");
				int j = 0;
				while (j < valores.length) {
					if (valores[j].equals("Transporte Dusa"))
						chkDusa.setChecked(true);
					if (valores[j].equals("Transporte Publico"))
						chkPublico.setChecked(true);
					if (valores[j].equals("Vehiculo Propio"))
						chkPrivado.setChecked(true);
					if (valores[j].equals("Bicicleta"))
						chkBicicleta.setChecked(true);
					if (valores[j].equals("Moto"))
						chkMoto.setChecked(true);
					if (valores[j].equals("Otro"))
						chkOtro.setChecked(true);
					j++;
				}
			}
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

	/* Abre la vista de Ciudad */
	@Listen("onClick = #btnAbrirEstadoCivil")
	public void abrirCivil() {
		List<Arbol> arboles = servicioArbol
				.buscarPorNombreArbol("Estado Civil");
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
			ficha = paciente.getFicha();
		} else {
			limpiarCampos();
			msj.mensajeError(Mensaje.cedulaNoExiste);
		}
	}

	public void limpiarCampos() {
		limpiarColores(rdgF, rdgAlergia, rdgDiscapacidad, rdgLentes,
				 txtApellido1Paciente, txtNombre1Paciente,
				txtCedulaPaciente, spnCarga, cmbEstadoCivil,
				cmbGrupoSanguineo, cmbMano, cmbSexo, dspPeso, dspEstatura,
				cmbCiudad, cmbCargo, txtTelefono2, cmbEmpresa, cmbArea,
				cmbNomina, txtFichaPaciente,cmbNivelEducativo,dspPeso,dspEstatura);

		cmbRif.setValue("");
		txtRifPaciente.setValue("");
		txtPasaporte.setValue("");
		txtNro.setValue("");
		txtOtroTransporte.setValue("");
		txtUrb.setValue("");
		txtParroquia.setValue("");
		txtMunicipio.setValue("");
		txtAvCalle.setValue("");
		txtPuntoReferencia.setValue("");
		txtSector.setValue("");
		cedTrabajador = "";
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
		cmbCondicionTrabajador.setValue("");
		cmbCondicionTrabajador
				.setPlaceholder("Seleccione la Condicion del Trabajador");
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
		txtTelefonoAdicional.setValue("");
		txtCorreoEmpresa.setValue("");
		txtNombresEmergencia.setValue("");
		txtApellidosEmergencia.setValue("");
		txtTelefono1Emergencia.setValue("");
		txtTelefono2Emergencia.setValue("");
		cmbParentescoEmergencia.setValue("");
		cmbParentescoEmergencia.setPlaceholder("Seleccione el Parentesco");
		lblEdad.setValue("");
		lblEstado.setValue("");
		dspEstatura.setValue((double) 0);
		dspPeso.setValue((double) 0);
		cmbCiudad.setValue("");
		cmbCiudad.setPlaceholder("Seleccione una Ciudad");
		rdoSiAlergico.setValue(null);
		rdoNoAlergico.setValue(null);
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
		rdoNoBrigadista.setChecked(false);
		rdoSiBrigadista.setChecked(false);
		rdoSiCronico.setChecked(false);
		rdoNoCronico.setChecked(false);
		dtbInscripcionIVSS.setValue(fecha);
		dtbFechaMuerte.setValue(fecha);
		dtbFechaMuerte.setVisible(false);
		txtOficio.setValue("");

		rdoActivo.setChecked(true);
		rdoInactivo.setValue(null);

		rdoSiDiscapacidad.setValue(null);
		rdoNoDiscapacidad.setValue(null);

		rdoJefe.setChecked(false);
		rdoNoJefe.setChecked(false);

		rdoSiLentes.setValue(null);
		rdoNoLentes.setValue(null);

		rdoE.setDisabled(false);
		rdoV.setDisabled(false);
		tabDatosBasicos.setSelected(true);

		chkBicicleta.setChecked(false);
		chkDusa.setChecked(false);
		chkMoto.setChecked(false);
		chkOtro.setChecked(false);
		chkPrivado.setChecked(false);
		chkPublico.setChecked(false);
		
		rdoEstudia.setChecked(false);
		rdoTrabaja.setValue(null);
		txtLugarEstudios.setValue("");
		txtCarreraActual.setValue("");
		txtPeriodo.setValue("");

		llenarMedicinas();
	}

	@Listen("onClick = #btnVerFamiliares")
	public void mostrarCatalogoCarga(Event evento) {
		List<Familiar> familiares2 = new ArrayList<Familiar>();
		if (!txtCedulaPaciente.getValue().equals(""))
			familiares2 = servicioFamiliar
					.buscarPorTrabajador(txtCedulaPaciente.getValue());

		final List<Familiar> familiares = familiares2;
		catalogoModeloFamiliar = new Catalogo<Familiar>(
				divCatalogoModeloFamiliar,
				"Catalogo de Familiares que no aplican a Servicios Medicos",
				familiares, false, "Cedula", "Primer Nombre", "Segundo Nombre",
				"Primer Apellido", "Segundo Apellido", "Trabajador Asociado",
				"Estado") {

			@Override
			protected List<Familiar> buscar(String valor, String combo) {

				switch (combo) {
				case "Primer Nombre":
					return servicioFamiliar.filtroNombre1(valor);
				case "Segundo Nombre":
					return servicioFamiliar.filtroNombre2(valor);
				case "Cedula":
					return servicioFamiliar.filtroCedula(valor);
				case "Primer Apellido":
					return servicioFamiliar.filtroApellido1(valor);
				case "Segundo Apellido":
					return servicioFamiliar.filtroApellido2(valor);
				case "Trabajador Asociado":
					return servicioFamiliar.filtroCedulaAsociado(valor);
				default:
					return familiares;
				}
			}

			@Override
			protected String[] crearRegistros(Familiar objeto) {
				String activo = "Activo";
				if (!objeto.isEstatus())
					activo = "Inactivo";
				String[] registros = new String[7];
				registros[0] = objeto.getCedula();
				registros[1] = objeto.getPrimerNombre();
				registros[2] = objeto.getSegundoNombre();
				registros[3] = objeto.getPrimerApellido();
				registros[4] = objeto.getSegundoApellido();
				registros[5] = objeto.getCedulaFamiliar();
				registros[6] = activo;
				return registros;
			}

		};
		catalogoModeloFamiliar.setParent(divCatalogoModeloFamiliar);
		catalogoModeloFamiliar.doModal();
	}

	@Listen("onSeleccion = #divCatalogoModeloFamiliar")
	public void seleccinarCarga() {
		catalogoModeloFamiliar.setParent(null);
	}
}
