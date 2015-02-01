package controlador.maestros;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import modelo.maestros.Ciudad;
import modelo.maestros.Estado;
import modelo.maestros.Pais;
import modelo.seguridad.Arbol;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;

import arbol.CArbol;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;

public class CEstado extends CGenerico {

	private static final long serialVersionUID = 4342374400625748947L;
	@Wire
	private Div botoneraEstado;
	@Wire
	private Textbox txtNombreEstado;
	@Wire
	private Button btnBuscarEstado;
	@Wire
	private Div catalogoEstado;
	@Wire
	private Div divEstado;
	@Wire
	private Combobox cmbPais;

	private CArbol cArbol = new CArbol();
	private long id = 0;
	Catalogo<Estado> catalogo;

	@Override
	public void inicializar() throws IOException {
		contenido = (Include) divEstado.getParent();
		Tabbox tabox = (Tabbox) divEstado.getParent().getParent().getParent()
				.getParent();
		tabBox = tabox;
		tab = (Tab) tabox.getTabs().getLastChild();
		llenarCombo();
		HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (map != null) {
			if (map.get("tabsGenerales") != null) {
				tabs = (List<Tab>) map.get("tabsGenerales");
				System.out.println(tabs.size());
				map.clear();
				map = null;
			}
		}
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divEstado, "Estado", tabs);

			}

			@Override
			public void limpiar() {
				txtNombreEstado.setValue("");
				cmbPais.setValue("");
				cmbPais.setPlaceholder("Seleccione un Pais");
				id = 0;
			}

			@Override
			public void guardar() {
				if (validar()) {
					String nombre = txtNombreEstado.getValue();
					Pais pais = servicioPais.buscar(Long.parseLong(cmbPais
							.getSelectedItem().getContext()));
					Estado estado = new Estado(id, fechaHora, horaAuditoria,
							nombre, nombreUsuarioSesion(), pais);
					servicioEstado.guardar(estado);
					msj.mensajeInformacion(Mensaje.guardado);
					limpiar();
				}
			}

			@Override
			public void eliminar() {
				if (id != 0 && txtNombreEstado.getText().compareTo("") != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar el Estado?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Estado estado = servicioEstado
												.buscar(id);
										List<Ciudad> ciudades = servicioCiudad
												.buscarPorEstado(estado);
										if (!ciudades.isEmpty()) {
											msj.mensajeError(Mensaje.noEliminar);
										} else {
											servicioEstado.eliminar(estado);
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
		botoneraEstado.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (cmbPais.getText().compareTo("") == 0
				|| txtNombreEstado.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	/* Muestra el catalogo de los estados */
	@Listen("onClick = #btnBuscarEstado")
	public void mostrarCatalogo() {
		final List<Estado> estados = servicioEstado.buscarTodos();
		catalogo = new Catalogo<Estado>(catalogoEstado, "Catalogo de Estados",
				estados,false, "Nombre", "Pais") {

			@Override
			protected List<Estado> buscar(String valor, String combo) {
				if (combo.equals("Nombre"))
					return servicioEstado.filtroNombre(valor);
				else {
					if (combo.equals("Pais"))
						return servicioEstado.filtroPais(valor);
					else
						return estados;
				}
			}

			@Override
			protected String[] crearRegistros(Estado estado) {
				String[] registros = new String[2];
				registros[0] = estado.getNombre();
				registros[1] = estado.getPais().getNombre();
				return registros;
			}
		};
		catalogo.setParent(catalogoEstado);
		catalogo.doModal();
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoEstado")
	public void seleccinar() {
		Estado estado = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(estado);
		catalogo.setParent(null);
	}

	/* Busca si existe un estado con el mismo nombre escrito */
	@Listen("onChange = #txtNombreEstado")
	public void buscarPorNombre() {
		Estado estado = servicioEstado.buscarPorNombre(txtNombreEstado
				.getValue());
		if (estado != null)
			llenarCampos(estado);
	}

	/* LLena los campos del formulario dado un estado */
	private void llenarCampos(Estado estado) {
		txtNombreEstado.setValue(estado.getNombre());
		cmbPais.setValue(estado.getPais().getNombre());
		id = estado.getIdEstado();
	}

	/* Llena el combo de estado cada vez que se abre */
	@Listen("onOpen = #cmbPais")
	public void llenarCombo() {
		List<Pais> paises = servicioPais.buscarTodos();
		cmbPais.setModel(new ListModelList<Pais>(paises));
	}

	/* Abre la vista de Pais */
	@Listen("onClick = #btnAbrirPais")
	public void abrirPais() {
		List<Arbol> arboles = servicioArbol.buscarPorNombreArbol("Pais");
		if (!arboles.isEmpty()) {
			Arbol arbolItem = arboles.get(0);
			cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
		}
	}
}
