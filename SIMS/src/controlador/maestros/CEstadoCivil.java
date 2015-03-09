package controlador.maestros;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import modelo.maestros.EstadoCivil;
import modelo.maestros.Paciente;

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

public class CEstadoCivil extends CGenerico {

	private static final long serialVersionUID = -2316998060132992709L;
	@Wire
	private Div botoneraEstadoCivil;
	@Wire
	private Textbox txtNombreEstadoCivil;
	@Wire
	private Button btnBuscarEstadoCivil;
	@Wire
	private Div catalogoEstadoCivil;
	@Wire
	private Div divEstadoCivil;
	private long id = 0;
	Catalogo<EstadoCivil> catalogo;
	private String nombre;

	
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
				cerrarVentana(divEstadoCivil, "Estado Civil", tabs);
			}

			@Override
			public void limpiar() {
				txtNombreEstadoCivil.setValue("");
				id = 0;
			}

			@Override
			public void guardar() {
				if (validar()) {
					String nombre = txtNombreEstadoCivil.getValue();
					EstadoCivil pais = new EstadoCivil(id, fechaHora, horaAuditoria, nombre,
							nombreUsuarioSesion());
					servicioEstadoCivil.guardar(pais);
					msj.mensajeInformacion(Mensaje.guardado);
					limpiar();
				}
			}

			@Override
			public void eliminar() {
				if (id != 0 && txtNombreEstadoCivil.getText().compareTo("") != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar el Estado Civil?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										EstadoCivil pais = servicioEstadoCivil.buscar(id);
										List<Paciente> estados = servicioPaciente
												.buscarPorEstadoCivil(pais);
										if (!estados.isEmpty()) {
											msj.mensajeError(Mensaje.noEliminar);
										} else {
											servicioEstadoCivil.eliminar(pais);
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
		botoneraEstadoCivil.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtNombreEstadoCivil.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	/* Muestra el catalogo de los paises */
	@Listen("onClick = #btnBuscarEstadoCivil")
	public void mostrarCatalogo() {
		final List<EstadoCivil> paises = servicioEstadoCivil.buscarTodas();
		catalogo = new Catalogo<EstadoCivil>(catalogoEstadoCivil, "Catalogo de Estado Civiles",
				paises, false,"Nombre") {

			@Override
			protected List<EstadoCivil> buscar(String valor, String combo) {
				if (combo.equals("Nombre"))
					return servicioEstadoCivil.filtroNombre(valor);
				else
					return paises;
			}

			@Override
			protected String[] crearRegistros(EstadoCivil estado) {
				String[] registros = new String[1];
				registros[0] = estado.getNombre();
				return registros;
			}
		};
		catalogo.setParent(catalogoEstadoCivil);
		catalogo.doModal();
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoEstadoCivil")
	public void seleccinar() {
		EstadoCivil pais = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(pais);
		catalogo.setParent(null);
	}

	/* Busca si existe un pais con el mismo nombre escrito */
	@Listen("onChange = #txtNombreEstadoCivil")
	public void buscarPorNombre() {
		EstadoCivil pais = servicioEstadoCivil.buscarPorNombre(txtNombreEstadoCivil.getValue());
		if (pais != null)
			llenarCampos(pais);
	}

	/* LLena los campos del formulario dado un pais */
	private void llenarCampos(EstadoCivil pais) {
		txtNombreEstadoCivil.setValue(pais.getNombre());
		id = pais.getIdEstadoCivil();
	}
}
