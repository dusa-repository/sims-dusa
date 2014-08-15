package controlador.maestros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import modelo.maestros.Accidente;
import modelo.seguridad.Arbol;
import modelo.sha.ClasificacionAccidente;
import modelo.transacciones.ConsultaDiagnostico;
import modelo.transacciones.HistoriaAccidente;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;

import arbol.CArbol;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;

import controlador.transacciones.CConsulta;

public class CAccidente extends CGenerico {

	private static final long serialVersionUID = -4793250803965177865L;
	@Wire
	private Longbox txtCodigo;
	@Wire
	private Textbox txtNombre;
	@Wire
	private Div botoneraAccidente;
	@Wire
	private Div catalogoAccidente;
	@Wire
	private Div divAccidente;
	@Wire
	private Button btnBuscar;
	@Wire
	private Combobox cmbClasificacion;

	private CArbol cArbol = new CArbol();
	Catalogo<Accidente> catalogo;
	long id = 0;
	private boolean consulta = false;
	private CConsulta cConsulta = new CConsulta();
	List<Accidente> accidentes = new ArrayList<Accidente>();
	String tipo = "";
	Listbox listaConsulta;

	@Override
	public void inicializar() throws IOException {
		contenido = (Include) divAccidente.getParent();
		Tabbox tabox = (Tabbox) divAccidente.getParent().getParent()
				.getParent().getParent();
		tabBox = tabox;
		tab = (Tab) tabox.getTabs().getLastChild();
		HashMap<String, Object> mapa = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (mapa != null) {
			if (mapa.get("tabsGenerales") != null) {
				tabs = (List<Tab>) mapa.get("tabsGenerales");
				System.out.println(tabs.size());
				mapa.clear();
				mapa = null;
			}
		}
		HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("itemsCatalogo");
		if (map != null) {
			if (map.get("id") != null) {
				consulta = true;
				accidentes = (List<Accidente>) map.get("lista");
				listaConsulta = (Listbox) map.get("listbox");
				tipo = (String) map.get("tipo");
				map.clear();
				map = null;
			}
		}
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divAccidente, "Accidente", tabs);
			}

			@Override
			public void limpiar() {
				txtCodigo.setText("");
				txtNombre.setText("");
				cmbClasificacion.setValue("");
				cmbClasificacion.setPlaceholder("Seleccione una Clasificacion");
				id = 0;
			}

			@Override
			public void guardar() {
				if (validar()) {
					String nombre = txtNombre.getValue();
					id = txtCodigo.getValue();
					long idClasificacion = Long.valueOf(cmbClasificacion
							.getSelectedItem().getContext());
					ClasificacionAccidente clasificacion = servicioClasificacionAccidente
							.buscar(idClasificacion);
					Accidente accidente = new Accidente(id, clasificacion,
							nombre, fechaHora, horaAuditoria,
							nombreUsuarioSesion());
					servicioAccidente.guardar(accidente);
					if (consulta) {
						accidente = servicioAccidente.buscar(txtCodigo
								.getValue());
						accidentes.add(accidente);
						cConsulta.recibirAccidente(accidentes, listaConsulta,
								tipo);
					}
					msj.mensajeInformacion(Mensaje.guardado);
					limpiar();
				}
			}

			@Override
			public void eliminar() {
				if (id != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar el Accidente?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Accidente accidente = servicioAccidente
												.buscar(id);
										List<HistoriaAccidente> historias = servicioHistoriaAccidente
												.buscarPorAccidente(accidente);
										List<ConsultaDiagnostico> consultas = servicioConsultaDiagnostico
												.buscarPorAccidente(accidente);
										if (!historias.isEmpty()
												|| !consultas.isEmpty()) {
											msj.mensajeError(Mensaje.noEliminar);
										} else {
											servicioAccidente
													.eliminar(accidente);
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
		botoneraAccidente.appendChild(botonera);
	}

	protected boolean validar() {
		if (cmbClasificacion.getText().compareTo("") == 0
				|| txtCodigo.getText().compareTo("") == 0
				|| txtNombre.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	@Listen("onOpen = #cmbClasificacion")
	public void llenarCombo() {
		List<ClasificacionAccidente> estados = servicioClasificacionAccidente
				.buscarTodos();
		cmbClasificacion.setModel(new ListModelList<ClasificacionAccidente>(
				estados));
	}

	@Listen("onClick = #btnBuscar")
	public void mostrarCatalogo() throws IOException {
		final List<Accidente> accidentes = servicioAccidente.buscarTodos();
		catalogo = new Catalogo<Accidente>(catalogoAccidente,
				"Catalogo de Doctores", accidentes, "Codigo", "Nombre",
				"Clasificacion") {

			@Override
			protected List<Accidente> buscar(String valor, String combo) {
				switch (combo) {
				case "Codigo":
					return servicioAccidente.filtroCodigo(valor);
				case "Nombre":
					return servicioAccidente.filtroNombre(valor);
				case "Clasificacion":
					return servicioAccidente.filtroClasificacion(valor);
				default:
					return accidentes;
				}
			}

			@Override
			protected String[] crearRegistros(Accidente objeto) {
				String[] registros = new String[3];
				registros[0] = String.valueOf(objeto.getIdAccidente());
				registros[1] = objeto.getNombre();
				registros[2] = objeto.getClasificacion().getNombre();
				return registros;
			}

		};
		catalogo.setParent(catalogoAccidente);
		catalogo.doModal();
	}

	@Listen("onSeleccion = #catalogoAccidente")
	public void seleccionarDoctor() {
		Accidente accidente = catalogo.objetoSeleccionadoDelCatalogo();
		id = accidente.getIdAccidente();
		txtCodigo.setValue(accidente.getIdAccidente());
		txtNombre.setValue(accidente.getNombre());
		cmbClasificacion.setValue(accidente.getClasificacion().getNombre());
		catalogo.setParent(null);
	}

	@Listen("onChange = #txtCodigo")
	public void buscar() {
		Accidente accidente = servicioAccidente.buscar(txtCodigo.getValue());
		if (accidente != null) {
			id = accidente.getIdAccidente();
			txtNombre.setValue(accidente.getNombre());
			cmbClasificacion.setValue(accidente.getClasificacion().getNombre());
		}
	}

	@Listen("onClick = #btnAbrir")
	public void abrirEstado() {
		List<Arbol> arboles = servicioArbol
				.buscarPorNombreArbol("Clasificacion de Accidente");
		if (!arboles.isEmpty()) {
			Arbol arbolItem = arboles.get(0);
			cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
		}
	}

}
