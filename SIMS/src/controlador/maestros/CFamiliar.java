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
import modelo.maestros.Ciudad;
import modelo.maestros.Empresa;
import modelo.maestros.EstadoCivil;
import modelo.maestros.Familiar;
import modelo.maestros.Medicina;
import modelo.maestros.Nomina;
import modelo.maestros.Paciente;
import modelo.sha.Area;
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
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;

import security.modelo.Arbol;
import arbol.CArbol;

import componentes.Botonera;
import componentes.Buscar;
import componentes.Catalogo;
import componentes.Mensaje;
import componentes.Validador;

import controlador.transacciones.CCambiarCedula;

public class CFamiliar extends CGenerico {

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
	private Div divCatalogoModeloFamiliar;
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
	private Radiogroup rdgServiciosMedicos;
	@Wire
	private Radio rdoAplica;
	@Wire
	private Radio rdoNoAplica;
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
	private Datebox dtbFechaIngreso;
	@Wire
	private Datebox dtbFechaMuerte;
	@Wire
	private Radio rdoV;
	@Wire
	private Radio rdoE;
	@Wire
	private Label lblFichaI;
	@Wire
	private Label lblFecha;
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
	private Row rowGrupoSanguineo;
	@Wire
	private Row rowEmergencia;
	@Wire
	private Row rowEmergencia2;
	@Wire
	private Row rowEmergencia3;
	@Wire
	private Row rowMano;
	// Nuevos
	@Wire
	private Radiogroup rdgVive;
	@Wire
	private Radio rdoVive;
	@Wire
	private Radio rdoNoVive;

	@Wire
	private Radiogroup rdgAyuda;
	@Wire
	private Radio rdoAyuda;
	@Wire
	private Radio rdoNoAyuda;

	@Wire
	private Combobox cmbCertificado;
	@Wire
	private Combobox cmbRif;
	@Wire
	private Textbox txtRifPaciente;
	@Wire
	private Textbox txtOficio;
	@Wire
	private Radiogroup rdgEstudia;
	@Wire
	private Radio rdoEstudia;
	@Wire
	private Radio rdoTrabaja;
	@Wire
	private Textbox txtLugarTrabajo;
	@Wire
	private Textbox txtCargoOCarrera;
	@Wire
	private Textbox txtNroCertificado;
	@Wire
	private Textbox txtAyuda;
	@Wire
	private Textbox txtObservaciones;
	@Wire
	private Button btnSiguiente2;
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
	Catalogo<Familiar> catalogoModeloFamiliar;
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
		llenarMedicinas();
		llenarComboCivil();
		rdoActivo.setChecked(true);
		rdoAplica.setChecked(true);
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divPaciente, "Familiar", tabs);

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
					String profesion, nacionalidad, nombre1, apellido1, cedula, nombre2, apellido2, ficha, detalleAlergia, lugarNac, sexo, grupoSanguineo, mano, origen, tipoDiscapacidad, otrasDiscapacidad, direccion, telefono1, telefono2, correo, nombresE, apellidosE, telefono1E, telefono2E, parentescoE, parentescoFamiliar;
					int edad;
					boolean alergia = false, discapacidad = false, lentes = false;
					double estatura, peso;
					boolean ayuda = false, vive = false, estudia = false, aplica = false;
					String rif, oficio, lugarTrabajo, cargoOCarrera, nroCertificado, descripcionAyuda, observacion, tipoRif, certificado;

					Timestamp fechaIngreso = null;
					Timestamp fechaMuerte = null;

					if (dtbFechaIngreso.getValue() != null)
						fechaIngreso = new Timestamp(dtbFechaIngreso.getValue()
								.getTime());

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
					nombre1 = txtNombre1Paciente.getValue();
					apellido1 = txtApellido1Paciente.getValue();
					nombre2 = txtNombre2Paciente.getValue();
					apellido2 = txtApellido2Paciente.getValue();
					cedula = txtCedulaPaciente.getValue();
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
					parentescoFamiliar = cmbParentescoFamiliar.getValue();
					String observacionEstatus = txtObservacionEstatus
							.getValue();

					edad = calcularEdad(dtbFechaNac.getValue());
					String cedulaFamiliar = "";
					Empresa empresa = null;
					Area area = null;
					Cargo cargo = null;
					Nomina nomina = null;
					EstadoCivil estadoCivil = null;
					cedTrabajador = lblCedula.getValue();
					if (rdoSiAlergico.isChecked())
						alergia = true;
					boolean cambioDeRepresentante = false;

					if (cmbEstadoCivil.getSelectedItem() != null) {
						if (cmbEstadoCivil.getSelectedItem().getContext() != null)
							estadoCivil = servicioEstadoCivil.buscar(Long
									.parseLong(cmbEstadoCivil.getSelectedItem()
											.getContext()));
					}

					ficha = "";
					cedulaFamiliar = cedTrabajador;

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
					Boolean cronico = false;
					if (rdoSiCronico.isChecked())
						cronico = true;

					oficio = txtOficio.getValue();
					lugarTrabajo = txtLugarTrabajo.getValue();
					cargoOCarrera = txtCargoOCarrera.getValue();
					nroCertificado = txtNroCertificado.getValue();
					descripcionAyuda = txtAyuda.getValue();
					observacion = txtObservaciones.getValue();
					rif = txtRifPaciente.getValue();
					tipoRif = cmbRif.getValue();
					certificado = cmbCertificado.getValue();
					if (rdoAyuda.isChecked())
						ayuda = true;
					if (rdoVive.isChecked())
						vive = true;
					if (rdoEstudia.isChecked())
						estudia = true;
					if (rdoAplica.isChecked())
						aplica = true;
					if (aplica) {

						if (!id.equals("")) {
							Paciente fami = servicioPaciente
									.buscarPorCedula(id);
							if (!fami.getCedulaFamiliar().equals(cedTrabajador))
								cambioDeRepresentante = true;
						}

						Paciente paciente = new Paciente(cedula, ficha,
								apellido1, nombre1, apellido2, nombre2, false,
								discapacidad, alergia, lentes, fechaNac,
								lugarNac, sexo, edad, grupoSanguineo,
								detalleAlergia, mano, estatura, peso, origen,
								tipoDiscapacidad, otrasDiscapacidad, fechaHora,
								horaAuditoria, nombreUsuarioSesion(), imagen,
								direccion, correo, telefono1, telefono2,
								nombresE, apellidosE, parentescoE, telefono1E,
								telefono2E, cedulaFamiliar, parentescoFamiliar,
								empresa, ciudad, cargo, area, cronico);

						paciente.setEstudia(estudia);
						paciente.setAyuda(ayuda);
						paciente.setVive(vive);
						paciente.setDescripcionAyuda(descripcionAyuda);
						paciente.setRif(rif);
						paciente.setTipoRif(tipoRif);
						paciente.setCertificado(certificado);
						paciente.setNroCertificado(nroCertificado);
						paciente.setObservacion(observacion);
						paciente.setOficio(oficio);
						paciente.setCargoOCarrera(cargoOCarrera);
						paciente.setLugarTrabajo(lugarTrabajo);

						paciente.setBrigadista(false);
						paciente.setEstadoCivil(estadoCivil);
						paciente.setNacionalidad(nacionalidad);
						paciente.setNivelEducativo("N/A");
						paciente.setProfesion(profesion);
						paciente.setFechaIngreso(fechaIngreso);
						paciente.setFechaInscripcionIVSS(fechaHora);
						paciente.setFechaEgreso(fechaHora);
						paciente.setNomina(nomina);
						paciente.setEstatus(estatus);
						paciente.setObservacionEstatus(observacionEstatus);
						paciente.setMuerte(muerte);
						paciente.setFechaMuerte(fechaMuerte);

						servicioPaciente.guardar(paciente);
						guardarMedicinas(paciente);
						Familiar familiar = servicioFamiliar
								.buscarPorCedula(cedula);
						if (familiar != null) {
							familiar.setEstatus(false);
							servicioFamiliar.guardar(familiar);
						}
						if (cambioDeRepresentante) {
							String cedulaNueva = paciente.getCedulaFamiliar();
							String cedulaVieja = paciente.getCedula();
							String ultimosDigitos = "";
							for (int i = 0; i < cedulaVieja.length(); i++) {
								char a = cedulaVieja.charAt(i);
								if (a == '-') {
									ultimosDigitos = cedulaVieja.substring(i);
									i = cedulaVieja.length();
								}
							}
							if (!ultimosDigitos.equals("")) {
								Paciente pacienteRepetido = servicioPaciente
										.buscarPorCedula(cedulaNueva
												+ ultimosDigitos);
								if (pacienteRepetido == null)
									cedulaNueva += ultimosDigitos;
								else {
									boolean noRepetido = false;
									int cont = 0;
									do {
										cont++;
										pacienteRepetido = servicioPaciente
												.buscarPorCedula(cedulaNueva
														+ "-" + cont);
										if (pacienteRepetido == null)
											noRepetido = true;
									} while (!noRepetido);
									cedulaNueva += "-" + cont;
								}
								cambiar.modificarHistoriaPaciente(paciente,
										cedulaNueva);
							}
						}
					} else {

						// if (!id.equals("")) {
						// Familiar fami = servicioFamiliar
						// .buscarPorCedula(id);
						// if (!fami.getCedulaFamiliar().equals(cedTrabajador))
						// cambioDeRepresentante = true;
						// }

						Familiar familiar = new Familiar(cedula, apellido1,
								nombre1, apellido2, nombre2, discapacidad,
								estatus, muerte, fechaNac, lugarNac,
								observacionEstatus, sexo, estadoCivil, edad,
								origen, tipoDiscapacidad, otrasDiscapacidad,
								fechaHora, horaAuditoria,
								nombreUsuarioSesion(), imagen, direccion,
								correo, telefono1, telefono2, cedulaFamiliar,
								parentescoFamiliar, nacionalidad, profesion,
								fechaMuerte, fechaIngreso, ciudad, ayuda,
								estudia, vive, rif, oficio, cargoOCarrera,
								lugarTrabajo, descripcionAyuda, nroCertificado,
								observacion, tipoRif, certificado);

						// Modificacion de cedula por trabjador nuevo
						servicioFamiliar.guardar(familiar);

						Paciente paciente = servicioPaciente
								.buscarPorCedula(cedula);
						if (paciente != null) {
							paciente.setEstatus(false);
							servicioPaciente.guardar(paciente);
						}

					}
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
				|| (!rdoE.isChecked() && !rdoV.isChecked())
				|| (!rdoVive.isChecked() && !rdoNoVive.isChecked())
				|| dtbFechaNac.getText().compareTo("") == 0
				|| cmbEstadoCivil.getText().compareTo("") == 0
				|| cmbSexo.getText().compareTo("") == 0
				|| cmbCiudad.getText().compareTo("") == 0
				|| txtTelefono2.getText().compareTo("") == 0
				|| (!rdoNoAplica.isChecked() && !rdoAplica.isChecked())
				|| (!rdoNoAyuda.isChecked() && !rdoAyuda.isChecked())
				|| (!rdoNoDiscapacidad.isChecked() && !rdoSiDiscapacidad
						.isChecked())
				|| (cmbParentescoFamiliar.getText().compareTo("") == 0 || lblNombres
						.getValue() == "")
				|| (rdoMuerte.isChecked() && (dtbFechaMuerte.getText()
						.compareTo("") == 0))
				|| (!rdoEstudia.isChecked() && !rdoTrabaja.isChecked())) {
			msj.mensajeError(Mensaje.camposVacios);
			aplicarColores(rdgF, rdgVive, rdgServiciosMedicos, rdgEstudia, rdgAyuda,
					rdgAlergia, rdgDiscapacidad, rdgLentes,
					txtApellido1Paciente, txtNombre1Paciente,
					txtCedulaPaciente, cmbEstadoCivil, cmbGrupoSanguineo,
					cmbMano, cmbSexo, dspPeso, dspEstatura, cmbCiudad,
					txtTelefono2, cmbParentescoFamiliar);
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
				} else
					return true;
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
	@Listen("onClick = #btnBuscarPaciente")
	public void mostrarCatalogo(Event evento) {

		List<Paciente> pacientes2 = new ArrayList<Paciente>();
		pacientes2 = servicioPaciente.buscarTodosFamiliares();

		final List<Paciente> pacientes = pacientes2;
		catalogo = new Catalogo<Paciente>(catalogoPaciente,
				"Catalogo de Pacientes", pacientes, false, "Cedula",
				"Primer Nombre", "Segundo Nombre", "Primer Apellido",
				"Segundo Apellido", "Trabajador Asociado", "Estado") {

			@Override
			protected List<Paciente> buscar(String valor, String combo) {

				switch (combo) {
				case "Primer Nombre":
					return servicioPaciente.filtroNombrePariente(valor);
				case "Segundo Nombre":
					return servicioPaciente.filtroNombre2Pariente(valor);
				case "Cedula":
					return servicioPaciente.filtroCedulaPariente(valor);
				case "Primer Apellido":
					return servicioPaciente.filtroApellidoPariente(valor);
				case "Segundo Apellido":
					return servicioPaciente.filtroApellido2Pariente(valor);
				case "Trabajador Asociado":
					return servicioPaciente.filtroCedulaAsociado(valor);
				default:
					return pacientes;
				}
			}

			@Override
			protected String[] crearRegistros(Paciente objeto) {
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
		catalogo.setParent(catalogoPaciente);
		catalogo.doModal();
	}

	/* Muestra el catalogo de los Pacientes */
	@Listen("onClick = #btnBuscarTrabajadores")
	public void mostrarCatalogoFamiliar() {
		final List<Paciente> pacientes = servicioPaciente
				.buscarTodosTrabajadores();
		catalogoFamiliar = new Catalogo<Paciente>(divCatalogoFamiliar,
				"Catalogo de Pacientes", pacientes, false, "Cedula", "Ficha",
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
				case "Ficha":
					return servicioPaciente.filtroFichaT(valor);
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
				String[] registros = new String[6];
				registros[0] = objeto.getCedula();
				registros[1] = objeto.getFicha();
				registros[2] = objeto.getPrimerNombre();
				registros[3] = objeto.getSegundoNombre();
				registros[4] = objeto.getPrimerApellido();
				registros[5] = objeto.getSegundoApellido();
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

	/* Llena el combo de Empresas cada vez que se abre */
	@Listen("onOpen = #cmbCiudad")
	public void llenarComboCiudad() {
		List<Ciudad> ciudades = servicioCiudad.buscarTodas();
		cmbCiudad.setModel(new ListModelList<Ciudad>(ciudades));
	}

	/* Llena el combo de Estado cada vez que se abre */
	@Listen("onOpen = #cmbEstadoCivil")
	public void llenarComboCivil() {
		List<EstadoCivil> ciudades = servicioEstadoCivil.buscarTodas();
		cmbEstadoCivil.setModel(new ListModelList<EstadoCivil>(ciudades));
	}

	/*
	 * Muestra los componentes de la vista relacionados a un trabajador
	 */
	@Listen("onClick =#rdoAplica")
	public void aplica() {
		rowEmergencia.setVisible(true);
		rowEmergencia2.setVisible(true);
		rowEmergencia3.setVisible(true);
		rowGrupoSanguineo.setVisible(true);
		rowMano.setVisible(true);
		tabDatosCronico.setVisible(true);
		btnSiguiente2.setVisible(true);
	}

	@Listen("onClick =#rdoNoAplica")
	public void noAplica() {
		rowEmergencia.setVisible(false);
		rowEmergencia2.setVisible(false);
		rowEmergencia3.setVisible(false);
		rowGrupoSanguineo.setVisible(false);
		rowMano.setVisible(false);
		tabDatosCronico.setVisible(false);
		btnSiguiente2.setVisible(false);

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

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoPaciente")
	public void seleccinar() {
		Paciente paciente = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(paciente);
		llenarMedicinas();
		aplica();
		ficha = paciente.getFicha();
		catalogo.setParent(null);
	}

	/* Busca si existe un diagnostico con el mismo codigo escrito */
	@Listen("onChange = #txtCedulaPaciente")
	public void buscarPorCedula() {
		if (rdoAplica.isChecked()) {
			Paciente paciente = servicioPaciente
					.buscarPorCedula(txtCedulaPaciente.getValue());
			if (paciente != null) {
				llenarCampos(paciente);
				llenarMedicinas();
				ficha = paciente.getFicha();
			}
		} else {
			if (rdoNoAplica.isChecked()) {
				Familiar familiar = servicioFamiliar
						.buscarPorCedula(txtCedulaPaciente.getValue());
				if (familiar != null)
					llenarCamposCarga(familiar);
			}
		}
	}

	/* LLena los campos del formulario dado un paciente */
	private void llenarCampos(Paciente paciente) {

		rdoAplica.setChecked(true);
		rdoAplica.setDisabled(false);
		rdoNoAplica.setDisabled(false);
		txtCedulaPaciente.setValue(paciente.getCedula());
		txtObservacionEstatus.setValue(paciente.getObservacionEstatus());
		txtNombre1Paciente.setValue(paciente.getPrimerNombre());
		txtApellido1Paciente.setValue(paciente.getPrimerApellido());
		txtNombre2Paciente.setValue(paciente.getSegundoNombre());
		txtApellido2Paciente.setValue(paciente.getSegundoApellido());
		txtCedulaPaciente.setDisabled(true);
		id = paciente.getCedula();
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
		txtProfesion.setValue(paciente.getProfesion());
		dtbFechaIngreso.setValue(paciente.getFechaIngreso());
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
		if (paciente.getCronico() != null) {
			if (paciente.getCronico())
				rdoSiCronico.setChecked(true);
			else
				rdoNoCronico.setChecked(true);
		} else {
			rdoNoCronico.setChecked(false);
			rdoSiCronico.setChecked(false);
		}

		if (paciente.isVive())
			rdoVive.setChecked(true);
		else
			rdoNoVive.setChecked(true);
		if (paciente.isEstudia())
			rdoEstudia.setChecked(true);
		else
			rdoTrabaja.setChecked(true);
		if (paciente.isAyuda())
			rdoAyuda.setChecked(true);
		else
			rdoNoAyuda.setChecked(true);

		cmbCertificado.setValue(paciente.getCertificado());
		cmbRif.setValue(paciente.getTipoRif());
		txtRifPaciente.setValue(paciente.getRif());
		txtOficio.setValue(paciente.getOficio());
		txtLugarTrabajo.setValue(paciente.getLugarTrabajo());
		txtCargoOCarrera.setValue(paciente.getCargoOCarrera());
		txtNroCertificado.setValue(paciente.getNroCertificado());
		txtAyuda.setValue(paciente.getDescripcionAyuda());
		txtObservaciones.setValue(paciente.getObservacion());
	}

	/* Permite subir una imagen a la vista */
	@Listen("onUpload = #fudImagenPaciente")
	public void processMedia(UploadEvent event) {
		media = event.getMedia();
		imagenPaciente.setContent((org.zkoss.image.Image) media);

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
		if (rdoAplica.isChecked()) {
			Paciente paciente = servicioPaciente
					.buscarPorCedula(txtCedulaPaciente.getValue());
			if (paciente != null) {
				llenarCampos(paciente);
				llenarMedicinas();
				ficha = paciente.getFicha();
			}
		} else {
			if (rdoNoAplica.isChecked()) {
				Familiar familiar = servicioFamiliar
						.buscarPorCedula(txtCedulaPaciente.getValue());
				if (familiar != null)
					llenarCamposCarga(familiar);
				else {
					limpiarCampos();
					msj.mensajeError(Mensaje.cedulaNoExiste);
				}
			}
		}
	}

	public void limpiarCampos() {
		limpiarColores(rdgF, rdgVive, rdgServiciosMedicos, rdgEstudia, rdgAyuda,
				rdgAlergia, rdgDiscapacidad, rdgLentes,
				txtApellido1Paciente, txtNombre1Paciente,
				txtCedulaPaciente, cmbEstadoCivil, cmbGrupoSanguineo,
				cmbMano, cmbSexo, dspPeso, dspEstatura, cmbCiudad,
				txtTelefono2, cmbParentescoFamiliar);
		cedTrabajador = "";
		idBoton = "";
		txtObservacionEstatus.setValue("");
		txtNombre1Paciente.setValue("");
		txtCedulaPaciente.setValue("");
		txtCedulaPaciente.setDisabled(false);
		txtApellido1Paciente.setValue("");
		txtNombre2Paciente.setValue("");
		txtApellido2Paciente.setValue("");
		imagenPaciente.setVisible(false);
		id = "";
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
		rdoSiAlergico.setChecked(false);
		rdoNoAlergico.setChecked(false);
		lblApellidos.setValue("");
		lblCedula.setValue("");
		lblFicha.setValue("");
		lblNombres.setValue("");
		txtProfesion.setValue("");
		dtbFechaIngreso.setValue(fecha);
		rdoSiCronico.setChecked(false);
		rdoNoCronico.setChecked(false);
		dtbFechaMuerte.setValue(fecha);
		dtbFechaMuerte.setVisible(false);

		rdoAplica.setChecked(true);
		rdoNoAplica.setChecked(false);

		rdoAplica.setDisabled(false);
		rdoNoAplica.setDisabled(false);

		rdoActivo.setChecked(true);
		rdoInactivo.setValue(null);

		rdoSiDiscapacidad.setChecked(false);
		rdoNoDiscapacidad.setChecked(false);

		rdoSiLentes.setChecked(false);
		rdoNoLentes.setChecked(false);

		rdoE.setDisabled(false);
		rdoV.setDisabled(false);
		tabDatosBasicos.setSelected(true);

		rdoVive.setChecked(false);
		rdoNoVive.setChecked(false);

		rdoAyuda.setChecked(false);
		rdoNoAyuda.setChecked(false);

		cmbCertificado.setValue("");
		cmbRif.setValue("");
		txtRifPaciente.setValue("");
		txtOficio.setValue("");
		rdoEstudia.setChecked(false);
		rdoTrabaja.setValue(null);
		txtLugarTrabajo.setValue("");
		txtCargoOCarrera.setValue("");
		txtNroCertificado.setValue("");
		txtAyuda.setValue("");
		txtObservaciones.setValue("");

		tabDatosCronico.setVisible(true);
		llenarMedicinas();
		aplica();
	}

	/* Muestra el catalogo de los Pacientes */
	@Listen("onClick = #btnBuscarCarga")
	public void mostrarCatalogoCarga(Event evento) {

		final List<Familiar> familiares = servicioFamiliar.buscarTodos();

		catalogoModeloFamiliar = new Catalogo<Familiar>(
				divCatalogoModeloFamiliar, "Catalogo de Familiares",
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
		Familiar familiar = catalogoModeloFamiliar
				.objetoSeleccionadoDelCatalogo();
		llenarCamposCarga(familiar);
		catalogoModeloFamiliar.setParent(null);
	}

	private void llenarCamposCarga(Familiar familiar) {
		rdoNoAplica.setChecked(true);
		rdoAplica.setDisabled(false);
		rdoNoAplica.setDisabled(false);
		txtCedulaPaciente.setValue(familiar.getCedula());
		txtObservacionEstatus.setValue(familiar.getObservacionEstatus());
		txtNombre1Paciente.setValue(familiar.getPrimerNombre());
		txtApellido1Paciente.setValue(familiar.getPrimerApellido());
		txtNombre2Paciente.setValue(familiar.getSegundoNombre());
		txtApellido2Paciente.setValue(familiar.getSegundoApellido());
		txtCedulaPaciente.setDisabled(true);
		id = familiar.getCedula();
		txtLugarNacimiento.setValue(familiar.getLugarNacimiento());
		cmbSexo.setValue(familiar.getSexo());
		if (familiar.getEstadoCivil() != null)
			cmbEstadoCivil.setValue(familiar.getEstadoCivil().getNombre());
		cmbGrupoSanguineo.setValue("");
		cmbMano.setValue("");
		cmbOrigen.setValue(familiar.getOrigenDiscapacidad());
		cmbTipoDiscapacidad.setValue(familiar.getTipoDiscapacidad());
		txtOtras.setValue(familiar.getOrigenDiscapacidad());
		txtDireccion.setValue(familiar.getDireccion());
		txtTelefono1.setValue(familiar.getTelefono1());
		txtTelefono2.setValue(familiar.getTelefono2());
		txtCorreo.setValue(familiar.getEmail());
		txtNombresEmergencia.setValue("");
		txtApellidosEmergencia.setValue("");
		txtTelefono1Emergencia.setValue("");
		txtTelefono2Emergencia.setValue("");
		cmbParentescoEmergencia.setValue("");
		cmbParentescoFamiliar.setValue(familiar.getParentescoFamiliar());
		lblEdad.setValue(String.valueOf(calcularEdad(familiar
				.getFechaNacimiento())));

		if (familiar.getCiudadVivienda() != null)
			cmbCiudad.setValue(familiar.getCiudadVivienda().getNombre());
		txtProfesion.setValue(familiar.getProfesion());
		dtbFechaIngreso.setValue(familiar.getFechaIngreso());
		dtbFechaNac.setValue(familiar.getFechaNacimiento());

		if (familiar.getNacionalidad() != null) {
			if (familiar.getNacionalidad().equals("V"))
				rdoV.setChecked(true);
			else
				rdoE.setChecked(true);
		}
		if (familiar.isMuerte()) {
			rdoMuerte.setChecked(true);
			dtbFechaMuerte.setVisible(true);
			dtbFechaMuerte.setValue(familiar.getFechaMuerte());
		} else {
			if (!familiar.isEstatus())
				rdoInactivo.setChecked(true);
			else
				rdoActivo.setChecked(true);
		}

		if (familiar.isDiscapacidad())
			rdoSiDiscapacidad.setChecked(true);
		else
			rdoNoDiscapacidad.setChecked(true);

		BufferedImage imag;
		if (familiar.getImagen() != null) {
			imagenPaciente.setVisible(true);
			try {
				imag = ImageIO.read(new ByteArrayInputStream(familiar
						.getImagen()));
				imagenPaciente.setContent(imag);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
			imagenPaciente.setVisible(false);

		Paciente familiarTrabajador = servicioPaciente.buscarPorCedula(familiar
				.getCedulaFamiliar());
		if (familiarTrabajador != null) {
			lblNombres.setValue(familiarTrabajador.getPrimerNombre() + " "
					+ familiar.getSegundoNombre());
			lblApellidos.setValue(familiarTrabajador.getPrimerApellido() + " "
					+ familiar.getSegundoApellido());
			lblFicha.setValue(familiarTrabajador.getFicha());
			lblCedula.setValue(familiarTrabajador.getCedula());
			cedTrabajador = familiarTrabajador.getCedula();
		}
		if (familiar.isVive())
			rdoVive.setChecked(true);
		else
			rdoNoVive.setChecked(true);
		if (familiar.isEstudia())
			rdoEstudia.setChecked(true);
		else
			rdoTrabaja.setChecked(true);
		if (familiar.isAyuda())
			rdoAyuda.setChecked(true);
		else
			rdoNoAyuda.setChecked(true);
		cmbRif.setValue(familiar.getTipoRif());
		cmbCertificado.setValue(familiar.getCertificado());
		txtRifPaciente.setValue(familiar.getRif());
		txtOficio.setValue(familiar.getOficio());
		txtLugarTrabajo.setValue(familiar.getLugarTrabajo());
		txtCargoOCarrera.setValue(familiar.getCargoOCarrera());
		txtNroCertificado.setValue(familiar.getNroCertificado());
		txtAyuda.setValue(familiar.getDescripcionAyuda());
		txtObservaciones.setValue(familiar.getObservacion());

		noAplica();
	}

}
