package controlador.maestros;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import modelo.maestros.Especialidad;
import modelo.maestros.Unidad;
import modelo.seguridad.Grupo;
import modelo.seguridad.Usuario;

import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.Image;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;

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
	private Fileupload fudImagenUsuario;
	@Wire
	private Media media;
	long id = 0;
	Catalogo<Usuario> catalogo;
	List<Grupo> gruposDisponibles = new ArrayList<Grupo>();
	List<Grupo> gruposOcupados = new ArrayList<Grupo>();
	URL url = getClass().getResource("usuario.png");

	@Override
	public void inicializar() throws IOException {
		llenarCombos();
		llenarListas(null);
		try {
			imagen.setContent(new AImage(url));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divUsuario);
			}

			@Override
			public void limpiar() {
				ltbGruposAgregados.getItems().clear();
				ltbGruposDisponibles.getItems().clear();
				cmbEspecialidad.setValue("");
				cmbUnidad.setValue("");
				txtApellidoUsuario.setValue("");
				txtCedulaUsuario.setValue("");
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
				txtTelefonoUsuario.setValue("");
				spnCitasUsuario.setValue(null);
				spnTiempoUsuario.setValue(null);
				rdoSexoFUsuario.setChecked(false);
				rdoSexoMUsuario.setChecked(false);
				try {
					imagen.setContent(new AImage(url));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				id = 0;
				llenarListas(null);
			}

			@Override
			public void guardar() {
				if (validar()) {
					Set<Grupo> gruposUsuario = new HashSet<Grupo>();
					for (int i = 0; i < ltbGruposAgregados.getItemCount(); i++) {
						Grupo grupo = ltbGruposAgregados.getItems().get(i)
								.getValue();
						gruposUsuario.add(grupo);
					}
					Especialidad especialidad = servicioEspecialidad
							.buscar(Long.parseLong(cmbEspecialidad
									.getSelectedItem().getContext()));
					Unidad unidad = servicioUnidad
							.buscar(Long.parseLong(cmbUnidad.getSelectedItem()
									.getContext()));
					String cedula = txtCedulaUsuario.getValue();
					String correo = txtCorreoUsuario.getValue();
					String direccion = txtDireccionUsuario.getValue();
					String ficha = txtFichaUsuario.getValue();
					String licenciaC = txtLicenciaCUsuario.getValue();
					String licenciaI = txtLicenciaIUsuario.getValue();
					String licenciaM = txtLicenciaMUsuario.getValue();
					String login = txtLoginUsuario.getValue();
					String password = txtPasswordUsuario.getValue();
					String nombre = txtNombreUsuario.getValue();
					String telefono = txtTelefonoUsuario.getValue();
					long citas = spnCitasUsuario.getValue();
					long tiempo = spnTiempoUsuario.getValue();
					String sexo = "";
					if (rdoSexoFUsuario.isChecked())
						sexo = "F";
					else
						sexo = "M";
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
					Usuario usuario = new Usuario(id, cedula, direccion,
							correo, true, "estado", fechaHora, ficha, sexo,
							imagenUsuario, licenciaC,
							Long.parseLong(licenciaI), licenciaM, login,
							nombre, citas, password, sexo, telefono, tiempo,
							nombreUsuarioSesion(), especialidad, unidad,
							gruposUsuario);
					servicioUsuario.guardar(usuario);
					limpiar();
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
				}
			}

			@Override
			public void eliminar() {
				if (id != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar el Usuario?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Usuario usuario = servicioUsuario
												.buscarUsuarioPorId(id);
										servicioUsuario.eliminar(usuario);
										limpiar();
										Messagebox
												.show("Registro Eliminado Exitosamente",
														"Informacion",
														Messagebox.OK,
														Messagebox.INFORMATION);

									}
								}
							});
				} else
					Messagebox.show("No ha Seleccionado Ningun Registro",
							"Alerta", Messagebox.OK, Messagebox.EXCLAMATION);

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
				|| txtLicenciaCUsuario.getText().compareTo("") == 0
				|| txtLicenciaIUsuario.getText().compareTo("") == 0
				|| txtLicenciaMUsuario.getText().compareTo("") == 0
				|| txtLoginUsuario.getText().compareTo("") == 0
				|| txtNombreUsuario.getText().compareTo("") == 0
				|| txtPassword2Usuario.getText().compareTo("") == 0
				|| txtPasswordUsuario.getText().compareTo("") == 0
				|| txtTelefonoUsuario.getText().compareTo("") == 0
				|| (!rdoSexoFUsuario.isChecked() && !rdoSexoMUsuario
						.isChecked())) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else {
			return true;
		}
	}

	/* LLena las listas dado un usario */
	public void llenarListas(Usuario usuario) {
		gruposDisponibles = servicioGrupo.buscarTodos();
		if (usuario == null) {
			ltbGruposDisponibles.setModel(new ListModelList<Grupo>(
					gruposDisponibles));
		} else {
			gruposOcupados = servicioGrupo.buscarGruposDelUsuario(usuario);
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

	/* LLena los combos de unidad y especialidad de la vista */
	public void llenarCombos() {
		List<Unidad> unidades = servicioUnidad.buscarTodas();
		cmbUnidad.setModel(new ListModelList<Unidad>(unidades));
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
		List<Usuario> usuarios = servicioUsuario.buscarTodos();
		catalogo = new Catalogo<Usuario>(catalogoUsuario,
				"Catalogo de Usuarios", usuarios, "Cedula", "Ficha", "Nombre",
				"Correo", "Login") {

			@Override
			protected List<Usuario> buscar(String valor) {
				return null;
			}

			@Override
			protected String[] crearRegistros(Usuario objeto) {
				String[] registros = new String[5];
				registros[0] = objeto.getCedula();
				registros[1] = objeto.getFicha();
				registros[2] = objeto.getNombre();
				registros[3] = objeto.getEmail();
				registros[4] = objeto.getLogin();
				return registros;
			}

		};
		catalogo.setParent(catalogoUsuario);
		catalogo.doModal();
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
		cmbEspecialidad.setValue(usuario.getEspecialidad().getDescripcion());
		cmbUnidad.setValue(usuario.getUnidad().getNombre());
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
		txtNombreUsuario.setValue(usuario.getNombre());
		txtTelefonoUsuario.setValue(usuario.getTelefono());
		spnCitasUsuario.setValue((int) (long) usuario.getNumeroCitasDiarias());
		spnTiempoUsuario.setValue((int) (long) usuario
				.getTiempoEstimadoEntreCitas());
		String sexo = usuario.getSexo();
		if (sexo.equals("F"))
			rdoSexoFUsuario.setChecked(true);
		else
			rdoSexoMUsuario.setChecked(true);
		BufferedImage imag;
		try {
			imag = ImageIO.read(new ByteArrayInputStream(usuario.getImagen()));
			imagen.setContent(imag);
		} catch (IOException e) {
			e.printStackTrace();
		}
		id = usuario.getIdUsuario();
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
}
