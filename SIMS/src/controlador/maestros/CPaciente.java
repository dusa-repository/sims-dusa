package controlador.maestros;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import modelo.maestros.Cita;
import modelo.maestros.Ciudad;
import modelo.maestros.Empresa;
import modelo.maestros.Paciente;
import modelo.seguridad.Arbol;

import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublespinner;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;

import arbol.CArbol;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Validador;

public class CPaciente extends CGenerico {

	private static final long serialVersionUID = -8967604751368729529L;

	@Wire
	private Button btnBuscarPaciente;
	@Wire
	private Fileupload fudImagenPaciente;
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
	private Spinner spnEdad;
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
	private Textbox txtCargo;
	@Wire
	private Combobox cmbEmpresa;
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
	private Groupbox gbxTrabajadorAsociado;
	@Wire
	private Label lblCedula;
	@Wire
	private Label lblFicha;
	@Wire
	private Label lblNombres;
	@Wire
	private Label lblApellidos;

	URL url = getClass().getResource("usuario.png");
	private CArbol cArbol = new CArbol();
	long id = 0;
	String cedTrabajador = "";
	Catalogo<Paciente> catalogo;
	Catalogo<Paciente> catalogoFamiliar;

	@Override
	public void inicializar() throws IOException {

		llenarComboCiudad();
		llenarComboEmpresa();
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divPaciente, "Paciente");

			}

			@Override
			public void limpiar() {
				txtNombre1Paciente.setValue("");
				txtCedulaPaciente.setValue("");
				txtCedulaPaciente.setDisabled(false);
				txtApellido1Paciente.setValue("");
				txtNombre2Paciente.setValue("");
				txtApellido2Paciente.setValue("");
				cmbEmpresa.setValue("");
				cmbEmpresa.setPlaceholder("Seleccione una Empresa");
				imagenPaciente.setVisible(false);
				id = 0;
				txtFichaPaciente.setValue("");
				txtAlergia.setValue("");
				txtLugarNacimiento.setValue("");
				cmbSexo.setValue("");
				cmbSexo.setValue("Seleccione el Sexo");
				cmbEstadoCivil.setValue("");
				cmbEstadoCivil.setValue("Seleccione el Estado Civil");
				cmbGrupoSanguineo.setValue("");
				cmbGrupoSanguineo.setValue("Seleccione el Grupo");
				cmbMano.setValue("");
				cmbMano.setValue("Seleccione el Valor");
				cmbOrigen.setValue("");
				cmbOrigen.setValue("Seleccione el Origen");
				cmbTipoDiscapacidad.setValue("");
				cmbTipoDiscapacidad.setValue("Seleccione un Tipo");
				txtOtras.setValue("");
				txtCargo.setValue("");
				txtDireccion.setValue("");
				txtTelefono1.setValue("");
				txtTelefono2.setValue("");
				txtCorreo.setValue("");
				txtNombresEmergencia.setValue("");
				txtApellidosEmergencia.setValue("");
				txtTelefono1Emergencia.setValue("");
				txtTelefono2Emergencia.setValue("");
				cmbParentescoEmergencia.setValue("");
				cmbParentescoEmergencia.setValue("Seleccione el Parentesco");
				cmbParentescoFamiliar.setValue("");
				cmbParentescoFamiliar.setValue("Seleccione el Parentesco");
				spnEdad.setValue(0);
				dspEstatura.setValue((double) 0);
				dspPeso.setValue((double) 0);
				cmbCiudad.setValue("");
				cmbCiudad.setValue("Seleccione una Ciudad");
				rdoSiAlergico.setValue(null);
				rdoNoAlergico.setValue(null);

				rdoTrabajador.setValue(null);
				rdoFamiliar.setValue(null);

				rdoSiDiscapacidad.setValue(null);
				rdoNoDiscapacidad.setValue(null);

				rdoSiLentes.setValue(null);
				rdoNoLentes.setValue(null);
				
				rdoTrabajador.setDisabled(false);
				rdoFamiliar.setDisabled(false);
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

					String nombre1, apellido1, cedula, nombre2, apellido2, ficha, detalleAlergia, lugarNac, sexo, estadoCivil, grupoSanguineo, mano, origen, tipoDiscapacidad, otrasDiscapacidad, cargo, direccion, telefono1, telefono2, correo, nombresE, apellidosE, telefono1E, telefono2E, parentescoE, parentescoFamiliar;
					int edad;
					boolean trabajador = false, alergia = false, discapacidad = false, lentes = false;
					double estatura, peso;

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
					cargo = txtCargo.getValue();
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

					edad = spnEdad.getValue();
					String cedulaFamiliar = "";
					Empresa empresa = null;
					cedTrabajador = lblCedula.getValue();

					if (rdoSiAlergico.isChecked())
						alergia = true;
					if (rdoTrabajador.isChecked()) {
						trabajador = true;
						empresa = servicioEmpresa.buscar(Long
								.parseLong(cmbEmpresa.getSelectedItem()
										.getContext()));
					} else
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

					Paciente paciente = new Paciente(cedula, ficha, apellido1,
							nombre1, apellido2, nombre2, trabajador,
							discapacidad, alergia, lentes, fechaNac, lugarNac,
							sexo, estadoCivil, edad, grupoSanguineo,
							detalleAlergia, mano, estatura, peso, origen,
							tipoDiscapacidad, otrasDiscapacidad, fechaHora,
							horaAuditoria, nombreUsuarioSesion(), imagen,
							cargo, direccion, correo, telefono1, telefono2,
							nombresE, apellidosE, parentescoE, telefono1E,
							telefono2E, cedulaFamiliar, parentescoFamiliar,
							empresa, ciudad);

					servicioPaciente.guardar(paciente);
					limpiar();
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
				}

			}

			@Override
			public void eliminar() {
				if (id != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar el Paciente?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Paciente paciente = servicioPaciente
												.buscar(id);
										List<Cita> citas = servicioCita
												.buscarPorPaciente(paciente);
										if (!citas.isEmpty()) {
											Messagebox
													.show("No se Puede Eliminar el Registro, Esta siendo Utilizado",
															"Informacion",
															Messagebox.OK,
															Messagebox.INFORMATION);
										} else {
											servicioPaciente.eliminar(paciente);
											limpiar();
											Messagebox
													.show("Registro Eliminado Exitosamente",
															"Informacion",
															Messagebox.OK,
															Messagebox.INFORMATION);
										}
									}
								}
							});
				} else {
					Messagebox.show("No ha Seleccionado Ningun Registro",
							"Alerta", Messagebox.OK, Messagebox.EXCLAMATION);
				}

			}
		};
		botoneraPaciente.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtApellido1Paciente.getText().compareTo("") == 0
				|| txtNombre1Paciente.getText().compareTo("") == 0
				|| txtCedulaPaciente.getText().compareTo("") == 0
				|| txtNombre2Paciente.getText().compareTo("") == 0
				|| txtApellido2Paciente.getText().compareTo("") == 0
				|| txtLugarNacimiento.getText().compareTo("") == 0
				|| txtFichaPaciente.getText().compareTo("") == 0
				|| dtbFechaNac.getText().compareTo("") == 0
				|| spnEdad.getValue() == 0
				|| cmbEstadoCivil.getText().compareTo("") == 0
				|| cmbGrupoSanguineo.getText().compareTo("") == 0
				|| cmbMano.getText().compareTo("") == 0
				|| cmbSexo.getText().compareTo("") == 0
				|| dspPeso.getValue() == 0
				|| dspEstatura.getValue() == 0
				|| cmbCiudad.getText().compareTo("") == 0
				|| txtDireccion.getText().compareTo("") == 0
				|| txtTelefono1.getText().compareTo("") == 0
				|| txtTelefono2.getText().compareTo("") == 0
				|| txtCorreo.getText().compareTo("") == 0
				|| txtTelefono1Emergencia.getText().compareTo("") == 0
				|| txtTelefono2Emergencia.getText().compareTo("") == 0
				|| cmbParentescoEmergencia.getText().compareTo("") == 0
				|| (!rdoSiAlergico.isChecked() && !rdoNoAlergico.isChecked())
				|| (!rdoFamiliar.isChecked() && !rdoTrabajador.isChecked())
				|| (!rdoNoDiscapacidad.isChecked() && !rdoSiDiscapacidad
						.isChecked())
				|| (rdoTrabajador.isChecked() && (txtCargo.getText().compareTo(
						"") == 0 || cmbEmpresa.getText().compareTo("") == 0))
				|| (rdoFamiliar.isChecked() && cmbParentescoFamiliar.getText()
						.compareTo("") == 0)
				|| (!rdoSiLentes.isChecked() && !rdoNoLentes.isChecked())) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else {
			if (rdoSiAlergico.isChecked()
					&& txtAlergia.getText().compareTo("") == 0) {
				Messagebox.show(
						"Debe Especificar la Informacion de la Alergia",
						"Alerta", Messagebox.OK, Messagebox.INFORMATION);
				return false;
			} else {
				if (rdoSiDiscapacidad.isChecked()
						&& (cmbOrigen.getText().compareTo("") == 0 || cmbTipoDiscapacidad
								.getText().compareTo("") == 0)) {
					Messagebox
							.show("Debe Especificar la Informacion de la Discapacidad",
									"Alerta", Messagebox.OK,
									Messagebox.INFORMATION);
					return false;
				} else {
					if (!Validador.validarNumero(txtCedulaPaciente.getValue())) {
						Messagebox.show("Cedula Invalida", "Alerta",
								Messagebox.OK, Messagebox.INFORMATION);
						return false;
					} else {
						if (!Validador.validarCorreo(txtCorreo.getValue())) {
							Messagebox.show("Correo Electronico Invalido",
									"Alerta", Messagebox.OK,
									Messagebox.INFORMATION);
							return false;
						} else {
							if (!Validador.validarTelefono(txtTelefono1
									.getValue())
									|| !Validador.validarTelefono(txtTelefono2
											.getValue())
									|| !Validador
											.validarTelefono(txtTelefono2Emergencia
													.getValue())
									|| !Validador
											.validarTelefono(txtTelefono1Emergencia
													.getValue())) {
								Messagebox.show(
										"Formato de Telefono No Valido",
										"Alerta", Messagebox.OK,
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

	/* Metodo que valida el formmato del telefono ingresado */
	@Listen("onChange = #txtTelefono1")
	public void validarTelefono() throws IOException {
		if (Validador.validarTelefono(txtTelefono1.getValue()) == false) {
			Messagebox.show("Formato de Telefono No Valido", "Alerta",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}

	/* Metodo que valida el formmato del telefono ingresado */
	@Listen("onChange = #txtTelefono2")
	public void validarTelefono2() throws IOException {
		if (Validador.validarTelefono(txtTelefono2.getValue()) == false) {
			Messagebox.show("Formato de Telefono No Valido", "Alerta",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}

	/* Metodo que valida el formmato del telefono ingresado */
	@Listen("onChange = #txtTelefono1Emergencia")
	public void validarTelefonoE() throws IOException {
		if (Validador.validarTelefono(txtTelefono1Emergencia.getValue()) == false) {
			Messagebox.show("Formato de Telefono No Valido", "Alerta",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}

	/* Metodo que valida el formmato del telefono ingresado */
	@Listen("onChange = #txtTelefono2Emergencia")
	public void validarTelefono2E() throws IOException {
		if (Validador.validarTelefono(txtTelefono2Emergencia.getValue()) == false) {
			Messagebox.show("Formato de Telefono No Valido", "Alerta",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}

	/* Metodo que valida el formmato del correo ingresado */
	@Listen("onChange = #txtCorreo")
	public void validarCorreo() throws IOException {
		if (Validador.validarCorreo(txtCorreo.getValue()) == false) {
			Messagebox.show("Correo Electronico Invalido", "Alerta",
					Messagebox.OK, Messagebox.EXCLAMATION);
		}
	}

	/* Muestra el catalogo de los Pacientes */
	@Listen("onClick = #btnBuscarPaciente")
	public void mostrarCatalogo() {
		final List<Paciente> pacientes = servicioPaciente.buscarTodos();
		catalogo = new Catalogo<Paciente>(catalogoPaciente,
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
		catalogo.setParent(catalogoPaciente);
		catalogo.doModal();
	}

	/* Muestra el catalogo de los Pacientes */
	@Listen("onClick = #btnBuscarTrabajadores")
	public void mostrarCatalogoFamiliar() {
		final List<Paciente> pacientes = servicioPaciente.buscarTodos();
		catalogoFamiliar = new Catalogo<Paciente>(divCatalogoFamiliar,
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

	/* Valida la cedula */
	@Listen("onChange = #txtCedulaPaciente")
	public void validarCedula() {
		if (!Validador.validarNumero(txtCedulaPaciente.getValue())) {
			Messagebox.show("Cedula Invalida", "Informacion", Messagebox.OK,
					Messagebox.INFORMATION);
		}
	}

	/* Llena el combo de Empresas cada vez que se abre */
	@Listen("onOpen = #cmbEmpresa")
	public void llenarComboEmpresa() {
		List<Empresa> empresas = servicioEmpresa.buscarTodas();
		cmbEmpresa.setModel(new ListModelList<Empresa>(empresas));
	}

	/* Llena el combo de Empresas cada vez que se abre */
	@Listen("onOpen = #cmbCiudad")
	public void llenarComboCiudad() {
		List<Ciudad> ciudades = servicioCiudad.buscarTodas();
		cmbCiudad.setModel(new ListModelList<Ciudad>(ciudades));
	}

	/*
	 * Muestra los componentes de la vista relacionados a un trabajador
	 */
	@Listen("onClick =#rdoTrabajador")
	public void esTrabajador() {
		rowCargoyEmpresa.setVisible(true);
		gbxTrabajadorAsociado.setVisible(false);
		cmbParentescoFamiliar.setValue("");
		cmbParentescoFamiliar.setPlaceholder("Seleccione un Parentesco");
	}

	/*
	 * Muestra los componentes de la vista relacionados a un Familiar
	 */
	@Listen("onClick =#rdoFamiliar")
	public void esFamiliar() {
		rowCargoyEmpresa.setVisible(false);
		gbxTrabajadorAsociado.setVisible(true);
		txtCargo.setText("");
		cmbEmpresa.setValue("");
		cmbEmpresa.setPlaceholder("Seleccione una Empresa");

	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoPaciente")
	public void seleccinar() {
		Paciente paciente = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(paciente);
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
		txtNombre1Paciente.setValue(paciente.getPrimerNombre());
		txtApellido1Paciente.setValue(paciente.getPrimerApellido());
		txtNombre2Paciente.setValue(paciente.getSegundoNombre());
		txtApellido2Paciente.setValue(paciente.getSegundoApellido());
		cmbEmpresa.setValue(paciente.getEmpresa().getNombre());
		txtCedulaPaciente.setDisabled(true);
		id = Long.valueOf(paciente.getCedula());

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
		txtCargo.setValue(paciente.getCargo());
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
		spnEdad.setValue(paciente.getEdad());
		dspEstatura.setValue(paciente.getEstatura());
		dspPeso.setValue(paciente.getPeso());
		cmbCiudad.setValue(paciente.getCiudadVivienda().getNombre());

		if (paciente.isAlergia())
			rdoSiAlergico.setChecked(true);
		else
			rdoNoAlergico.setChecked(true);

		if (paciente.isTrabajador())
		{
			rdoTrabajador.setChecked(true);
			rdoTrabajador.setDisabled(true);
			esTrabajador();
		}
		else
		{
			rdoFamiliar.setChecked(true);
			rdoFamiliar.setDisabled(true);
			esFamiliar();
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

		if (!paciente.isTrabajador()) {
			Paciente familiar = servicioPaciente.buscarPorCedula(paciente
					.getCedulaFamiliar());
			lblNombres.setValue(familiar.getPrimerNombre() + " "
					+ familiar.getSegundoNombre());
			lblApellidos.setValue(familiar.getPrimerApellido() + " "
					+ familiar.getSegundoApellido());
			lblFicha.setValue(familiar.getFicha());
			lblCedula.setValue(familiar.getCedula());
			cedTrabajador = familiar.getCedula();
			cmbParentescoFamiliar.setValue(familiar.getParentescoFamiliar());
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
		Arbol arbolItem = servicioArbol.buscarPorNombreArbol("Empresa");
		cArbol.abrirVentanas(arbolItem);
	}

	/* Abre la vista de Ciudad */
	@Listen("onClick = #btnAbrirCiudad")
	public void abrirCiudad() {
		Arbol arbolItem = servicioArbol.buscarPorNombreArbol("Ciudad");
		cArbol.abrirVentanas(arbolItem);
	}
}
