package controlador.maestros;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import modelo.maestros.CategoriaDiagnostico;
import modelo.maestros.ClasificacionDiagnostico;

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

public class CClasificacionDiagnostico extends CGenerico {

	private static final long serialVersionUID = -4054354641778709932L;
	@Wire
	private Div botoneraClasificacionDiagnostico;
	@Wire
	private Textbox txtNombre;
	@Wire
	private Button btnBuscarClasificacionDiagnostico;
	@Wire
	private Div catalogoClasificacionDiagnostico;
	@Wire
	private Div divClasificacionDiagnostico;
	private long id = 0;
	Catalogo<ClasificacionDiagnostico> catalogo;
	String nombre = "";

	@Override
	public void inicializar() throws IOException {
		HashMap<String, Object> mapa = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (mapa != null) {
			if (mapa.get("tabsGenerales") != null) {
				tabs = (List<Tab>) mapa.get("tabsGenerales");
				nombre = (String) mapa.get("titulo");
				mapa.clear();
				mapa = null;
			}
		}
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divClasificacionDiagnostico, nombre, tabs);
			}

			@Override
			public void limpiar() {
				txtNombre.setValue("");
				id = 0;
			}

			@Override
			public void guardar() {
				if (validar()) {
					ClasificacionDiagnostico unidad = new ClasificacionDiagnostico(
							id, fechaHora, horaAuditoria, txtNombre.getValue(),
							nombreUsuarioSesion());
					servicioClasificacion.guardar(unidad);
					limpiar();
					msj.mensajeInformacion(Mensaje.guardado);
				}
			}

			@Override
			public void eliminar() {
				if (id != 0 && txtNombre.getText().compareTo("") != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar la Unidad?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										List<CategoriaDiagnostico> usuarios = servicioCategoriaDiagnostico
												.buscarPorClasificacion(id);
										if (!usuarios.isEmpty()) {
											msj.mensajeError(Mensaje.noEliminar);
										} else {
											servicioClasificacion.eliminar(id);
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
		botoneraClasificacionDiagnostico.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtNombre.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	/* Muestra el catalogo de las categorias */
	@Listen("onClick = #btnBuscarUnidad")
	public void mostrarCatalogo() {
		List<ClasificacionDiagnostico> unidades = servicioClasificacion
				.buscarTodas();
		catalogo = new Catalogo<ClasificacionDiagnostico>(
				catalogoClasificacionDiagnostico, "Catalogo de Unidades",
				unidades, "Nombre") {

			@Override
			protected List<ClasificacionDiagnostico> buscar(String valor,
					String combo) {
				if (combo.equals("Nombre"))
					return servicioClasificacion.filtroNombre(valor);
				else
					return servicioClasificacion.buscarTodas();
			}

			@Override
			protected String[] crearRegistros(ClasificacionDiagnostico objeto) {
				String[] registros = new String[1];
				registros[0] = objeto.getNombre();
				return registros;
			}

		};
		catalogo.setParent(catalogoClasificacionDiagnostico);
		catalogo.doModal();
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoClasificacionDiagnostico")
	public void seleccinar() {
		ClasificacionDiagnostico unidad = catalogo
				.objetoSeleccionadoDelCatalogo();
		llenarCampos(unidad);
		catalogo.setParent(null);
	}

	/* Busca si existe una unidad con el mismo nombre escrito */
	@Listen("onChange = #txtNombre")
	public void buscarPorNombre() {
		ClasificacionDiagnostico unidad = servicioClasificacion
				.buscarPorNombre(txtNombre.getValue());
		if (unidad != null)
			llenarCampos(unidad);
	}

	/* LLena los campos del formulario dada una unidad */
	private void llenarCampos(ClasificacionDiagnostico unidad) {
		txtNombre.setValue(unidad.getNombre());
		id = unidad.getIdClasificacion();
	}

}
