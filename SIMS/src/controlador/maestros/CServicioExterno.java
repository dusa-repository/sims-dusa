package controlador.maestros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import modelo.maestros.ProveedorServicio;
import modelo.maestros.ServicioExterno;
import modelo.transacciones.ConsultaServicioExterno;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;

import controlador.transacciones.CConsulta;

public class CServicioExterno extends CGenerico {

	private static final long serialVersionUID = -6178756189105805846L;
	@Wire
	private Textbox txtNombreServicioExterno;
	@Wire
	private Div divServicioExterno;
	@Wire
	private Div botoneraServicioExterno;
	@Wire
	private Div catalogoServicioExterno;
	@Wire
	private Button btnBuscarServicioExterno;

	long id = 0;
	Catalogo<ServicioExterno> catalogo;
	private boolean consulta = false;
	private CConsulta cConsulta = new CConsulta();
	List<ServicioExterno> servicioConsulta = new ArrayList<ServicioExterno>();
	Listbox listaConsulta;

	@Override
	public void inicializar() throws IOException {
		contenido = (Include) divServicioExterno.getParent();
		Tabbox tabox = (Tabbox) divServicioExterno.getParent().getParent()
				.getParent().getParent();
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
		HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("itemsCatalogo");
		if (map != null) {
			if (map.get("id") != null) {
				consulta = true;
				servicioConsulta = (List<ServicioExterno>) map.get("lista");
				listaConsulta = (Listbox) map.get("listbox");
				map.clear();
				map = null;
			}
		}
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divServicioExterno, "Servicios Externos", tabs);
			}

			@Override
			public void limpiar() {
				txtNombreServicioExterno.setValue("");
				id = 0;
			}

			@Override
			public void guardar() {
				if (validar()) {
					String nombre;
					nombre = txtNombreServicioExterno.getValue();
					ServicioExterno servicioExterno = new ServicioExterno(id,
							nombre, fechaHora, horaAuditoria,
							nombreUsuarioSesion());
					servicioServicioExterno.guardar(servicioExterno);
					if (consulta) {
						if (id != 0)
							servicioExterno = servicioServicioExterno
									.buscar(id);
						else {
							servicioExterno = servicioServicioExterno
									.buscarUltimo();
							servicioConsulta.add(servicioExterno);
						}
						cConsulta.recibirServicio(servicioConsulta,
								listaConsulta);
					}
					limpiar();
					msj.mensajeInformacion(Mensaje.guardado);
				}
			}

			@Override
			public void eliminar() {
				if (id != 0) {
					Messagebox.show(
							"¿Esta Seguro de Eliminar el Servicio Externo?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										ServicioExterno servicioExterno = servicioServicioExterno
												.buscar(id);

										List<ConsultaServicioExterno> consultas = servicioConsultaServicioExterno
												.buscarPorServicio(servicioExterno);
										List<ProveedorServicio> proveedores = servicioProveedorServicio
												.buscarPorServicio(servicioExterno);

										if (!proveedores.isEmpty()
												|| !consultas.isEmpty())
											msj.mensajeError(Mensaje.noEliminar);
										else {
											servicioServicioExterno
													.eliminar(servicioExterno);
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
		botoneraServicioExterno.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtNombreServicioExterno.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	/* Muestra el catalogo de los servicios externos */
	@Listen("onClick = #btnBuscarServicioExterno")
	public void mostrarCatalogo() {
		final List<ServicioExterno> serviciosExternos = servicioServicioExterno
				.buscarTodas();
		catalogo = new Catalogo<ServicioExterno>(catalogoServicioExterno,
				"Catalogo de Servicios Externos", serviciosExternos, "Nombre") {

			@Override
			protected List<ServicioExterno> buscar(String valor, String combo) {

				switch (combo) {
				case "Nombre":
					return servicioServicioExterno.filtroNombre(valor);
				default:
					return serviciosExternos;
				}
			}

			@Override
			protected String[] crearRegistros(ServicioExterno objeto) {
				String[] registros = new String[4];
				registros[0] = objeto.getNombre();
				return registros;
			}

		};
		catalogo.setParent(catalogoServicioExterno);
		catalogo.doModal();
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoServicioExterno")
	public void seleccinar() {
		ServicioExterno servicioExterno = catalogo
				.objetoSeleccionadoDelCatalogo();
		llenarCampos(servicioExterno);
		catalogo.setParent(null);
	}

	/* Busca si existe un servicio externo con el mismo nombre escrito */
	@Listen("onChange = #txtNombreServicioExterno")
	public void buscarPorNombre() {
		ServicioExterno servicioExterno = servicioServicioExterno
				.buscarPorNombre(txtNombreServicioExterno.getValue());
		if (servicioExterno != null)
			llenarCampos(servicioExterno);
	}

	/* LLena los campos del formulario dado un servicio externo */
	private void llenarCampos(ServicioExterno servicioExterno) {
		txtNombreServicioExterno.setValue(servicioExterno.getNombre());
		id = servicioExterno.getIdServicioExterno();
	}
}
