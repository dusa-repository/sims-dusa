package controlador.maestros;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import modelo.maestros.Cita;
import modelo.maestros.Especialidad;
import modelo.seguridad.Arbol;
import modelo.seguridad.Grupo;
import modelo.seguridad.Usuario;
import modelo.seguridad.UsuarioSeguridad;

import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;

import arbol.CArbol;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;
import componentes.Validador;

public class CUsuario extends CGenerico {

	private static final long serialVersionUID = 7879830599305337459L;
	@Wire
	private Button btnSiguientePestanna;
	@Wire
	private Button btnAnteriorPestanna;
	@Wire
	private Tab tabBasicos;
	@Wire
	private Tab tabUsuarios;
	@Wire
	private Div divUsuario;
	@Wire
	private Div botoneraUsuario;
	@Wire
	private Div catalogoUsuario;
	@Wire
	private Textbox txtNombreUsuario;
	@Wire
	private Textbox txtCedulaUsuario;
	@Wire
	private Textbox txtApellidoUsuario;
	@Wire
	private Textbox txtNombre2Usuario;
	@Wire
	private Textbox txtApellido2Usuario;
	@Wire
	private Textbox txtFichaUsuario;
	@Wire
	private Textbox txtTelefonoUsuario;
	@Wire
	private Textbox txtCorreoUsuario;
	@Wire
	private Textbox txtDireccionUsuario;
	@Wire
	private Textbox txtLicenciaCUsuario;
	@Wire
	private Textbox txtLicenciaMUsuario;
	@Wire
	private Textbox txtLicenciaIUsuario;
	@Wire
	private Textbox txtLoginUsuario;
	@Wire
	private Textbox txtPasswordUsuario;
	@Wire
	private Textbox txtPassword2Usuario;
	@Wire
	private Spinner spnTiempoUsuario;
	@Wire
	private Spinner spnCitasUsuario;
	@Wire
	private Combobox cmbEspecialidad;
	@Wire
	private Combobox cmbUnidad;
	@Wire
	private Button btnBuscarUsuario;
	@Wire
	private Listbox ltbGruposDisponibles;
	@Wire
	private Listbox ltbGruposAgregados;
	@Wire
	private Radiogroup rdbSexoUsuario;
	@Wire
	private Radio rdoSexoFUsuario;
	@Wire
	private Radio rdoSexoMUsuario;
	@Wire
	private Image imagen;
	@Wire
	private Button fudImagenUsuario;
	@Wire
	private Media media;
	@Wire
	private Radio rdoDoctor;
	@Wire
	private Radio rdoNoDoctor;
	@Wire
	private Groupbox gpxDoctor;

	private CArbol cArbol = new CArbol();
	String id = "";
	Catalogo<Usuario> catalogo;
	List<Grupo> gruposDisponibles = new ArrayList<Grupo>();
	List<Grupo> gruposOcupados = new ArrayList<Grupo>();
	URL url = getClass().getResource("usuario.png");

	@Override
	public void inicializar() throws IOException {
		contenido = (Include) divUsuario.getParent();
		Tabbox tabox = (Tabbox) divUsuario.getParent().getParent().getParent()
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
		llenarComboEspecialidad();

		llenarListas(null);
		try {
			imagen.setContent(new AImage(url));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divUsuario, "Usuario", tabs);
			}

			@Override
			public void limpiar() {
				gruposDisponibles.clear();
				gruposOcupados.clear();
				ltbGruposAgregados.getItems().clear();
				ltbGruposDisponibles.getItems().clear();
				cmbEspecialidad.setValue("");
				cmbUnidad.setValue("");
				txtApellidoUsuario.setValue("");
				txtApellido2Usuario.setValue("");
				txtCedulaUsuario.setValue("");
				txtCedulaUsuario.setDisabled(false);
				txtCorreoUsuario.setValue("");
				txtDireccionUsuario.setValue("");
				txtFichaUsuario.setValue("");
				txtLicenciaCUsuario.setValue("");
				txtLicenciaIUsuario.setValue("");
				txtLicenciaMUsuario.setValue("");
				txtLoginUsuario.setValue("");
				txtPasswordUsuario.setValue("");
				txtPassword2Usuario.setValue("");
				txtNombreUsuario.setValue("");
				txtNombre2Usuario.setValue("");
				txtTelefonoUsuario.setValue("");
				spnCitasUsuario.setValue(0);
				spnTiempoUsuario.setValue(0);
				rdoSexoFUsuario.setChecked(false);
				rdoSexoMUsuario.setChecked(false);
				rdoDoctor.setChecked(false);
				rdoNoDoctor.setChecked(false);
				try {
					imagen.setContent(new AImage(url));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				id = "";
				llenarListas(null);
				tabBasicos.setSelected(true);
			}

			@Override
			public void guardar() {

				if (spnCitasUsuario == null)
					spnCitasUsuario.setValue(0);
				if (spnTiempoUsuario == null)
					spnTiempoUsuario.setValue(0);
				if (validar()) {
					if (buscarPorLogin()) {
						Set<Grupo> gruposUsuario = new HashSet<Grupo>();
						for (int i = 0; i < ltbGruposAgregados.getItemCount(); i++) {
							Grupo grupo = ltbGruposAgregados.getItems().get(i)
									.getValue();
							gruposUsuario.add(grupo);
						}
						Especialidad especialidad = null;
						String licenciaI = "";
						long citas = 0;
						long tiempo = 0;
						String cedula = txtCedulaUsuario.getValue();
						String correo = txtCorreoUsuario.getValue();
						String direccion = txtDireccionUsuario.getValue();
						String ficha = txtFichaUsuario.getValue();
						String licenciaC = txtLicenciaCUsuario.getValue();
						if (txtLicenciaIUsuario.getText().compareTo("") != 0)
							licenciaI = txtLicenciaIUsuario.getValue();
						String licenciaM = txtLicenciaMUsuario.getValue();
						String login = txtLoginUsuario.getValue();
						String password = txtPasswordUsuario.getValue();
						String nombre = txtNombreUsuario.getValue();
						String apellido = txtApellidoUsuario.getValue();
						String nombre2 = txtNombre2Usuario.getValue();
						String apellido2 = txtApellido2Usuario.getValue();
						String telefono = txtTelefonoUsuario.getValue();
						if (spnCitasUsuario.getValue() != null)
							citas = spnCitasUsuario.getValue();
						if (spnTiempoUsuario.getValue() != null)
							tiempo = spnTiempoUsuario.getValue();
						String sexo = "";
						boolean doctor;
						if (rdoSexoFUsuario.isChecked())
							sexo = "F";
						else
							sexo = "M";
						if (rdoDoctor.isChecked()) {
							doctor = true;
							especialidad = servicioEspecialidad.buscar(Long
									.parseLong(cmbEspecialidad
											.getSelectedItem().getContext()));
						} else
							doctor = false;
						byte[] imagenUsuario = null;
						if (media instanceof org.zkoss.image.Image) {
							imagenUsuario = imagen.getContent().getByteData();

						} else {
							try {
								imagen.setContent(new AImage(url));
							} catch (IOException e) {
								e.printStackTrace();
							}
							imagenUsuario = imagen.getContent().getByteData();
						}
						long licencia = 0;
						if (!licenciaI.equals(""))
							licencia = Long.parseLong(licenciaI);

						Usuario usuario = new Usuario(cedula, direccion,
								correo, true, "estado", fechaHora, ficha, sexo,
								imagenUsuario, licenciaC, licencia, licenciaM,
								login, nombre, apellido, nombre2, apellido2,
								citas, password, sexo, telefono, tiempo,
								nombreUsuarioSesion(), especialidad,
								cmbUnidad.getValue(), doctor);
						guardarDatosSeguridad(usuario, gruposUsuario);
						servicioUsuario.guardar(usuario);
						limpiar();
						msj.mensajeInformacion(Mensaje.guardado);
					}
				}
			}

			@Override
			public void eliminar() {
				if (!id.equals("")) {
					Messagebox.show("�Esta Seguro de Eliminar el Usuario?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Usuario usuario = servicioUsuario
												.buscarPorCedula(id);
										List<Cita> citas = servicioCita
												.buscarPorUsuario(usuario);
										if (!citas.isEmpty())
											msj.mensajeError(Mensaje.noEliminar);
										else {
											List<Usuario> list = new ArrayList<Usuario>();
											list.add(usuario);
											inhabilitarSeguridad(list);
											usuario.setEstado(false);
											servicioUsuario.guardar(usuario);
											limpiar();
											msj.mensajeInformacion(Mensaje.eliminado);
										}
									}
								}
							});
				} else
					msj.mensajeAlerta(Mensaje.noSeleccionoRegistro);
			}
		};
		botoneraUsuario.appendChild(botonera);
	}

	/* Validaciones de pantalla para poder realizar el guardar */
	public boolean validar() {
		if (txtApellidoUsuario.getText().compareTo("") == 0
				|| txtCedulaUsuario.getText().compareTo("") == 0
				|| txtCorreoUsuario.getText().compareTo("") == 0
				|| txtDireccionUsuario.getText().compareTo("") == 0
				|| txtFichaUsuario.getText().compareTo("") == 0
				|| txtLoginUsuario.getText().compareTo("") == 0
				|| txtNombreUsuario.getText().compareTo("") == 0
				|| txtPassword2Usuario.getText().compareTo("") == 0
				|| txtPasswordUsuario.getText().compareTo("") == 0
				|| txtTelefonoUsuario.getText().compareTo("") == 0
				|| cmbUnidad.getText().compareTo("") == 0
				|| (!rdoSexoFUsuario.isChecked() && !rdoSexoMUsuario
						.isChecked())
				|| (!rdoDoctor.isChecked() && !rdoNoDoctor.isChecked())
				|| (rdoDoctor.isChecked() && cmbEspecialidad.getSelectedItem() == null)) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else {
			if (!Validador.validarCorreo(txtCorreoUsuario.getValue())) {
				msj.mensajeError(Mensaje.correoInvalido);
				return false;
			} else {
				if (!Validador.validarTelefono(txtTelefonoUsuario.getValue())) {
					msj.mensajeError(Mensaje.telefonoInvalido);
					return false;
				} else {
					if (!Validador.validarNumero(txtCedulaUsuario.getValue())) {
						msj.mensajeError(Mensaje.cedulaInvalida);
						return false;
					} else {
						if (txtLicenciaIUsuario.getText().compareTo("") != 0
								&& !Validador.validarNumero(txtLicenciaIUsuario
										.getValue())) {
							msj.mensajeError("Licencia Invalida");
							return false;
						} else {
							if (!txtPasswordUsuario.getValue().equals(
									txtPassword2Usuario.getValue())) {
								msj.mensajeError(Mensaje.contrasennasNoCoinciden);
								return false;
							} else
								return true;
						}
					}
				}
			}
		}
	}

	/* Valida que los passwords sean iguales */
	@Listen("onChange = #txtPassword2Usuario")
	public void validarPassword() {
		if (!txtPasswordUsuario.getValue().equals(
				txtPassword2Usuario.getValue())) {
			msj.mensajeAlerta(Mensaje.contrasennasNoCoinciden);
		}
	}

	/* Valida el numero telefonico */
	@Listen("onChange = #txtTelefonoUsuario")
	public void validarTelefono() {
		if (!Validador.validarTelefono(txtTelefonoUsuario.getValue())) {
			msj.mensajeAlerta(Mensaje.telefonoInvalido);
		}
	}

	/* Valida el correo electronico */
	@Listen("onChange = #txtCorreoUsuario")
	public void validarCorreo() {
		if (!Validador.validarCorreo(txtCorreoUsuario.getValue())) {
			msj.mensajeAlerta(Mensaje.correoInvalido);
		}
	}

	/* Valida la cedula */
	@Listen("onChange = #txtCedulaUsuario")
	public void validarCedula() {
		if (!Validador.validarNumero(txtCedulaUsuario.getValue())) {
			msj.mensajeAlerta(Mensaje.cedulaInvalida);
		}
	}

	/* Valida licencia de ipsasel */
	@Listen("onChange = #txtLicenciaIUsuario")
	public void validarLicencia() {
		if (!Validador.validarNumero(txtLicenciaIUsuario.getValue())) {
			msj.mensajeAlerta("Licencia Invalida");
		}
	}

	/* LLena las listas dado un usario */
	public void llenarListas(Usuario usuario) {
		UsuarioSeguridad user = null;
		if (usuario != null)
			user = servicioUsuarioSeguridad.buscarPorLogin(usuario.getLogin());
		gruposDisponibles = servicioGrupo.buscarTodos();
		if (usuario == null) {
			ltbGruposDisponibles.setModel(new ListModelList<Grupo>(
					gruposDisponibles));
		} else {
			gruposOcupados = servicioGrupo.buscarGruposDelUsuario(user);
			ltbGruposAgregados
					.setModel(new ListModelList<Grupo>(gruposOcupados));
			if (!gruposOcupados.isEmpty()) {
				List<Long> ids = new ArrayList<Long>();
				for (int i = 0; i < gruposOcupados.size(); i++) {
					long id = gruposOcupados.get(i).getIdGrupo();
					ids.add(id);
				}
				gruposDisponibles = servicioGrupo.buscarGruposDisponibles(ids);
				ltbGruposDisponibles.setModel(new ListModelList<Grupo>(
						gruposDisponibles));
			}
		}
		ltbGruposAgregados.setMultiple(false);
		ltbGruposAgregados.setCheckmark(false);
		ltbGruposAgregados.setMultiple(true);
		ltbGruposAgregados.setCheckmark(true);

		ltbGruposDisponibles.setMultiple(false);
		ltbGruposDisponibles.setCheckmark(false);
		ltbGruposDisponibles.setMultiple(true);
		ltbGruposDisponibles.setCheckmark(true);
	}

	/* Llena el combo de Especialidades cada vez que se abre */
	@Listen("onOpen = #cmbEspecialidad")
	public void llenarComboEspecialidad() {
		List<Especialidad> especialidades = servicioEspecialidad.buscarTodas();
		cmbEspecialidad
				.setModel(new ListModelList<Especialidad>(especialidades));
	}

	/* Permite subir una imagen a la vista */
	@Listen("onUpload = #fudImagenUsuario")
	public void processMedia(UploadEvent event) {
		media = event.getMedia();
		imagen.setContent((org.zkoss.image.Image) media);

	}

	/*
	 * Permite mover uno o varios elementos seleccionados desde la lista de la
	 * izquierda a la lista de la derecha
	 */
	@Listen("onClick = #pasar1")
	public void moverDerecha() {
		// gruposDisponibles = servicioGrupo.buscarTodos();
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem = ltbGruposDisponibles.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					Grupo grupo = listItem.get(i).getValue();
					gruposDisponibles.remove(grupo);
					gruposOcupados.add(grupo);
					ltbGruposAgregados.setModel(new ListModelList<Grupo>(
							gruposOcupados));
					listitemEliminar.add(listItem.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbGruposDisponibles.removeItemAt(listitemEliminar.get(i)
					.getIndex());
		}
		ltbGruposAgregados.setMultiple(false);
		ltbGruposAgregados.setCheckmark(false);
		ltbGruposAgregados.setMultiple(true);
		ltbGruposAgregados.setCheckmark(true);
	}

	/*
	 * Permite mover uno o varios elementos seleccionados desde la lista de la
	 * derecha a la lista de la izquierda
	 */
	@Listen("onClick = #pasar2")
	public void moverIzquierda() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem2 = ltbGruposAgregados.getItems();
		if (listItem2.size() != 0) {
			for (int i = 0; i < listItem2.size(); i++) {
				if (listItem2.get(i).isSelected()) {
					Grupo grupo = listItem2.get(i).getValue();
					gruposOcupados.remove(grupo);
					gruposDisponibles.add(grupo);
					ltbGruposDisponibles.setModel(new ListModelList<Grupo>(
							gruposDisponibles));
					listitemEliminar.add(listItem2.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbGruposAgregados.removeItemAt(listitemEliminar.get(i).getIndex());
		}
		ltbGruposDisponibles.setMultiple(false);
		ltbGruposDisponibles.setCheckmark(false);
		ltbGruposDisponibles.setMultiple(true);
		ltbGruposDisponibles.setCheckmark(true);
	}

	/* Muestra un catalogo de Usuarios */
	@Listen("onClick = #btnBuscarUsuario")
	public void mostrarCatalogo() throws IOException {
		final List<Usuario> usuarios = servicioUsuario.buscarTodos();
		catalogo = new Catalogo<Usuario>(catalogoUsuario,
				"Catalogo de Usuarios", usuarios, false, "Cedula", "Ficha",
				"Nombre", "Apellido", "Doctor", "Login") {

			@Override
			protected List<Usuario> buscar(String valor, String combo) {
				switch (combo) {
				case "Cedula":
					return servicioUsuario.filtroCedula(valor);
				case "Ficha":
					return servicioUsuario.filtroFicha(valor);
				case "Nombre":
					return servicioUsuario.filtroNombre(valor);
				case "Login":
					return servicioUsuario.filtroLogin(valor);
				case "Apellido":
					return servicioUsuario.filtroApellido(valor);
				case "Doctor":
					return servicioUsuario.filtroDoctor(valor.toLowerCase());
				default:
					return usuarios;
				}
			}

			@Override
			protected String[] crearRegistros(Usuario objeto) {
				String doctor = "";
				if (objeto.isDoctor())
					doctor = "Si";
				else
					doctor = "No";
				String[] registros = new String[6];
				registros[0] = objeto.getCedula();
				registros[1] = objeto.getFicha();
				registros[2] = objeto.getPrimerNombre();
				registros[3] = objeto.getPrimerApellido();
				registros[4] = doctor;
				registros[5] = objeto.getLogin();
				return registros;
			}

		};
		catalogo.setParent(catalogoUsuario);
		catalogo.doModal();
	}

	/* Busca si existe un usuario con el mismo login */
	@Listen("onChange = #txtLoginUsuario")
	public boolean buscarPorLogin() {
		Usuario usuario = servicioUsuario.buscarPorLogin(txtLoginUsuario
				.getValue());
		if (usuario == null)
			return true;
		else {
			if (usuario.getCedula().equals(id))
				return true;
			else {
				msj.mensajeAlerta(Mensaje.loginUsado);
				txtLoginUsuario.setValue("");
				txtLoginUsuario.setFocus(true);
				return false;
			}
		}

		// if ((usuario != null && id.equals(""))) {
		// if (usuario != null && !usuario.getCedula().equals(id)) {
		// msj.mensajeAlerta(Mensaje.loginUsado);
		// txtLoginUsuario.setValue("");
		// txtLoginUsuario.setFocus(true);
		// return false;
		// } else
		// return true;
		// } else
		// return true;
	}

	/* Busca si existe un usuario con la misma cedula nombre escrita */
	@Listen("onChange = #txtCedulaUsuario")
	public void buscarPorCedula() {
		Usuario usuario = servicioUsuario.buscarPorCedula(txtCedulaUsuario
				.getValue());
		if (usuario != null)
			llenarCampos(usuario);
	}

	/*
	 * Selecciona un usuario del catalogo y llena los campos con la informacion
	 */
	@Listen("onSeleccion = #catalogoUsuario")
	public void seleccion() {
		Usuario usuario = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(usuario);
		catalogo.setParent(null);
	}

	/* LLena los campos del formulario dado un usuario */
	public void llenarCampos(Usuario usuario) {

		txtCedulaUsuario.setValue(usuario.getCedula());
		txtCorreoUsuario.setValue(usuario.getEmail());
		txtDireccionUsuario.setValue(usuario.getDireccion());
		txtFichaUsuario.setValue(usuario.getFicha());
		txtLicenciaCUsuario.setValue(usuario.getLicenciaCm());
		txtLicenciaIUsuario.setValue(String.valueOf(usuario
				.getLicenciaInpsasel()));
		txtLicenciaMUsuario.setValue(usuario.getLicenciaMsds());
		txtLoginUsuario.setValue(usuario.getLogin());
		txtPasswordUsuario.setValue(usuario.getPassword());
		txtPassword2Usuario.setValue(usuario.getPassword());
		txtNombreUsuario.setValue(usuario.getPrimerNombre());
		txtNombre2Usuario.setValue(usuario.getSegundoNombre());
		txtApellidoUsuario.setValue(usuario.getPrimerApellido());
		txtApellido2Usuario.setValue(usuario.getSegundoApellido());
		txtTelefonoUsuario.setValue(usuario.getTelefono());
		spnCitasUsuario.setValue((int) (long) usuario.getNumeroCitasDiarias());
		spnTiempoUsuario.setValue((int) (long) usuario
				.getTiempoEstimadoEntreCitas());
		String sexo = usuario.getSexo();
		if (sexo.equals("F"))
			rdoSexoFUsuario.setChecked(true);
		else
			rdoSexoMUsuario.setChecked(true);
		if (usuario.isDoctor()) {
			rdoDoctor.setChecked(true);
			cmbEspecialidad
					.setValue(usuario.getEspecialidad().getDescripcion());
			cmbUnidad.setValue(usuario.getUnidad());
			gpxDoctor.setVisible(true);
		} else {
			rdoNoDoctor.setChecked(true);
			gpxDoctor.setVisible(false);
		}
		BufferedImage imag;
		if (usuario.getImagen() != null) {
			try {
				imag = ImageIO.read(new ByteArrayInputStream(usuario
						.getImagen()));
				imagen.setContent(imag);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		txtCedulaUsuario.setDisabled(true);
		id = usuario.getCedula();
		llenarListas(usuario);
	}

	/* Abre la pestanna de datos de usuario */
	@Listen("onClick = #btnSiguientePestanna")
	public void siguientePestanna() {
		tabUsuarios.setSelected(true);
	}

	/* Abre la pestanna de datos basicos */
	@Listen("onClick = #btnAnteriorPestanna")
	public void anteriorPestanna() {
		tabBasicos.setSelected(true);
	}

	/* Abre la vista de Unidad */
	@Listen("onClick = #btnAbrirUnidad")
	public void abrirUnidad() {
		List<Arbol> arboles = servicioArbol
				.buscarPorNombreArbol("Unidad Usuario");
		if (!arboles.isEmpty()) {
			Arbol arbolItem = arboles.get(0);
			cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
		}
	}

	/* Abre la vista de Especialidad */
	@Listen("onClick = #btnAbrirEspecialidad")
	public void abrirEspecialidad() {
		List<Arbol> arboles = servicioArbol
				.buscarPorNombreArbol("Especialidad");
		if (!arboles.isEmpty()) {
			Arbol arbolItem = arboles.get(0);
			cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
		}
	}

	/* Abre la vista de Grupos */
	@Listen("onClick = #btnAbrirGrupo")
	public void abrirGrupo() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", "medicina");
		map.put("lista", gruposDisponibles);
		map.put("listbox", ltbGruposDisponibles);
		Sessions.getCurrent().setAttribute("itemsCatalogo", map);
		List<Arbol> arboles = servicioArbol.buscarPorNombreArbol("Grupo");
		if (!arboles.isEmpty()) {
			Arbol arbolItem = arboles.get(0);
			cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
		}
	}

	public void recibirGrupo(List<Grupo> lista, Listbox l) {
		ltbGruposDisponibles = l;
		gruposDisponibles = lista;
		ltbGruposDisponibles.setModel(new ListModelList<Grupo>(
				gruposDisponibles));
		ltbGruposDisponibles.setMultiple(false);
		ltbGruposDisponibles.setCheckmark(false);
		ltbGruposDisponibles.setMultiple(true);
		ltbGruposDisponibles.setCheckmark(true);
	}

	@Listen("onClick =#rdoDoctor")
	public void esDoctor() {
		gpxDoctor.setVisible(true);
		cmbEspecialidad.setValue("");
		cmbEspecialidad.setValue("Seleccione una Especialidad");
		cmbUnidad.setValue("");
		cmbUnidad.setValue("Seleccione una Unidad");

	}

	@Listen("onClick =#rdoNoDoctor")
	public void noDoctor() {
		gpxDoctor.setVisible(false);

	}
}
