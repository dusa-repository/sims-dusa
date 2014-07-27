package controlador.maestros;

import java.util.HashMap;
import java.util.List;

import modelo.maestros.UnidadUsuario;
import modelo.seguridad.Usuario;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;

public class CUnidadUsuario extends CGenerico {

	private static final long serialVersionUID = -1573821851840127496L;
	@Wire
	private Div botoneraUnidadUsuario;
	@Wire
	private Textbox txtNombreUnidad;
	@Wire
	private Button btnBuscarUnidad;
	@Wire
	private Div catalogoUnidadUsuario;
	@Wire
	private Div divUnidadUsuario;
	private long id = 0;
	Catalogo<UnidadUsuario> catalogo;

	@Override
	public void inicializar() {
		HashMap<String, Object> mapa = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (mapa != null) {
			if (mapa.get("tabsGenerales") != null) {
				tabs = (List<Tab>) mapa.get("tabsGenerales");
				mapa.clear();
				mapa = null;
			}
		}
		Botonera botonera = new Botonera() {
			@Override
			public void guardar() {
				if (validar()) {
					UnidadUsuario unidad = new UnidadUsuario(id, fechaHora,
							horaAuditoria, txtNombreUnidad.getValue(),
							nombreUsuarioSesion());
					servicioUnidadUsuario.guardar(unidad);
					limpiar();
					msj.mensajeInformacion(Mensaje.guardado);
				}
			}

			@Override
			public void limpiar() {
				txtNombreUnidad.setValue("");
				id = 0;
			}

			@Override
			public void salir() {
				cerrarVentana(divUnidadUsuario, "Unidad Usuario", tabs);
			}

			@Override
			public void eliminar() {
				if (id != 0 && txtNombreUnidad.getText().compareTo("") != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar la Unidad?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										UnidadUsuario unidad = servicioUnidadUsuario
												.buscar(id);
										List<Usuario> usuarios = servicioUsuario
												.buscarPorUnidad(unidad);
										if (!usuarios.isEmpty()) {
											msj.mensajeError(Mensaje.noEliminar);
										} else {
											servicioUnidadUsuario
													.eliminar(unidad);
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
		botoneraUnidadUsuario.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtNombreUnidad.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	/* Muestra el catalogo de las categorias */
	@Listen("onClick = #btnBuscarUnidad")
	public void mostrarCatalogo() {
		List<UnidadUsuario> unidades = servicioUnidadUsuario.buscarTodas();
		catalogo = new Catalogo<UnidadUsuario>(catalogoUnidadUsuario,
				"Catalogo de Unidades", unidades, "Nombre") {

			@Override
			protected List<UnidadUsuario> buscar(String valor, String combo) {
				if (combo.equals("Nombre"))
					return servicioUnidadUsuario.filtroNombre(valor);
				else
					return servicioUnidadUsuario.buscarTodas();
			}

			@Override
			protected String[] crearRegistros(UnidadUsuario objeto) {
				String[] registros = new String[1];
				registros[0] = objeto.getNombre();
				return registros;
			}

		};
		catalogo.setParent(catalogoUnidadUsuario);
		catalogo.doModal();
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoUnidadUsuario")
	public void seleccinar() {
		UnidadUsuario unidad = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(unidad);
		catalogo.setParent(null);
	}

	/* Busca si existe una unidad con el mismo nombre escrito */
	@Listen("onChange = #txtNombreUnidad")
	public void buscarPorNombre() {
		UnidadUsuario unidad = servicioUnidadUsuario
				.buscarPorNombre(txtNombreUnidad.getValue());
		if (unidad != null)
			llenarCampos(unidad);
	}

	/* LLena los campos del formulario dada una unidad */
	private void llenarCampos(UnidadUsuario unidad) {
		txtNombreUnidad.setValue(unidad.getNombre());
		id = unidad.getIdUnidadUsuario();
	}
}
