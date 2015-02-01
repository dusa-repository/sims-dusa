package controlador.maestros;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import modelo.maestros.Estado;
import modelo.maestros.Pais;

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

public class CPais extends CGenerico {

	private static final long serialVersionUID = -2316998060132992709L;
	@Wire
	private Div botoneraPais;
	@Wire
	private Textbox txtNombrePais;
	@Wire
	private Button btnBuscarPais;
	@Wire
	private Div catalogoPais;
	@Wire
	private Div divPais;
	private long id = 0;
	Catalogo<Pais> catalogo;

	@Override
	public void inicializar() throws IOException {
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
			public void salir() {
				cerrarVentana(divPais, "Pais", tabs);
			}

			@Override
			public void limpiar() {
				txtNombrePais.setValue("");
				id = 0;
			}

			@Override
			public void guardar() {
				if (validar()) {
					String nombre = txtNombrePais.getValue();
					Pais pais = new Pais(id, fechaHora, horaAuditoria, nombre,
							nombreUsuarioSesion());
					servicioPais.guardar(pais);
					msj.mensajeInformacion(Mensaje.guardado);
					limpiar();
				}
			}

			@Override
			public void eliminar() {
				if (id != 0 && txtNombrePais.getText().compareTo("") != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar el Pais?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Pais pais = servicioPais.buscar(id);
										List<Estado> estados = servicioEstado
												.buscarPorPais(pais);
										if (!estados.isEmpty()) {
											msj.mensajeError(Mensaje.noEliminar);
										} else {
											servicioPais.eliminar(pais);
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
		botoneraPais.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtNombrePais.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	/* Muestra el catalogo de los paises */
	@Listen("onClick = #btnBuscarPais")
	public void mostrarCatalogo() {
		final List<Pais> paises = servicioPais.buscarTodos();
		catalogo = new Catalogo<Pais>(catalogoPais, "Catalogo de Paises",
				paises, false,"Nombre") {

			@Override
			protected List<Pais> buscar(String valor, String combo) {
				if (combo.equals("Nombre"))
					return servicioPais.filtroNombre(valor);
				else
					return paises;
			}

			@Override
			protected String[] crearRegistros(Pais estado) {
				String[] registros = new String[1];
				registros[0] = estado.getNombre();
				return registros;
			}
		};
		catalogo.setParent(catalogoPais);
		catalogo.doModal();
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoPais")
	public void seleccinar() {
		Pais pais = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(pais);
		catalogo.setParent(null);
	}

	/* Busca si existe un pais con el mismo nombre escrito */
	@Listen("onChange = #txtNombrePais")
	public void buscarPorNombre() {
		Pais pais = servicioPais.buscarPorNombre(txtNombrePais.getValue());
		if (pais != null)
			llenarCampos(pais);
	}

	/* LLena los campos del formulario dado un pais */
	private void llenarCampos(Pais pais) {
		txtNombrePais.setValue(pais.getNombre());
		id = pais.getIdPais();
	}
}
