package controlador.maestros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import modelo.maestros.Diagnostico;
import modelo.maestros.Especialidad;
import modelo.maestros.Especialista;
import modelo.seguridad.Arbol;
import modelo.transacciones.ConsultaDiagnostico;
import modelo.transacciones.ConsultaEspecialista;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublespinner;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;

import arbol.CArbol;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;
import componentes.Validador;
import controlador.transacciones.CConsulta;

public class CEspecialista extends CGenerico {

	private static final long serialVersionUID = 7577914926768857900L;
	@Wire
	private Div divEspecialista;
	@Wire
	private Div botoneraEspecialista;
	@Wire
	private Div catalogoEspecialista;
	@Wire
	private Textbox txtNombreEspecialista;
	@Wire
	private Textbox txtApellidoEspecialista;
	@Wire
	private Textbox txtCedulaEspecialista;
	@Wire
	private Combobox cmbEspecialidad;
	@Wire
	private Button btnBuscarEspecialista;
	@Wire
	private Doublespinner dspCosto;

	private CArbol cArbol = new CArbol();
	long id = 0;
	Catalogo<Especialista> catalogo;
	private boolean consulta = false;
	private CConsulta cConsulta = new CConsulta();
	List<Especialista> especialistaConsulta = new ArrayList<Especialista>();
	Listbox listaConsulta;

	@Override
	public void inicializar() throws IOException {
		contenido = (Include) divEspecialista.getParent();
		Tabbox tabox = (Tabbox) divEspecialista.getParent().getParent()
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
				especialistaConsulta = (List<Especialista>) map.get("lista");
				listaConsulta = (Listbox) map.get("listbox");
				map.clear();
				map = null;
			}
		}
		llenarComboEspecialidad();
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divEspecialista, "Especialista", tabs);
			}

			@Override
			public void limpiar() {
				txtApellidoEspecialista.setValue("");
				txtCedulaEspecialista.setValue("");
				txtNombreEspecialista.setValue("");
				cmbEspecialidad.setValue("");
				cmbEspecialidad.setPlaceholder("Seleccione una Especialidad");
				dspCosto.setValue(null);
				id = 0;
			}

			@Override
			public void guardar() {
				if (validar()) {
					String nombre, apellido, cedula;
					nombre = txtNombreEspecialista.getValue();
					apellido = txtApellidoEspecialista.getValue();
					cedula = txtCedulaEspecialista.getValue();
					double costoServicio = dspCosto.getValue();
					Especialidad especialidad = servicioEspecialidad
							.buscar(Long.parseLong(cmbEspecialidad
									.getSelectedItem().getContext()));
					Especialista especialista = new Especialista(cedula,
							apellido, nombre, costoServicio, fechaHora,
							horaAuditoria, nombreUsuarioSesion(), especialidad);
					servicioEspecialista.guardar(especialista);
					if (consulta) {
						especialista = servicioEspecialista.buscar(cedula);
						especialista.setNombre(especialista.getNombre() + " "
								+ especialista.getApellido());
						especialistaConsulta.add(especialista);
						cConsulta.recibir(especialistaConsulta, listaConsulta);
					}
					limpiar();
					msj.mensajeInformacion(Mensaje.guardado);
				}
			}

			@Override
			public void eliminar() {
				if (id != 0) {
					Messagebox.show(
							"¿Esta Seguro de Eliminar el Especialista?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Especialista especialista = servicioEspecialista
												.buscar(String.valueOf(id));
										List<ConsultaEspecialista> consultas = servicioConsultaEspecialista
												.buscarPorEspecialista(especialista);
										if (!consultas.isEmpty())
											msj.mensajeError(Mensaje.noEliminar);
										else {
											servicioEspecialista
													.eliminar(especialista);
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
		botoneraEspecialista.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtApellidoEspecialista.getText().compareTo("") == 0
				|| txtCedulaEspecialista.getText().compareTo("") == 0
				|| txtNombreEspecialista.getText().compareTo("") == 0
				|| dspCosto.getText().compareTo("") == 0
				|| cmbEspecialidad.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else {
			if (!Validador.validarNumero(txtCedulaEspecialista.getValue())) {
				msj.mensajeError(Mensaje.cedulaInvalida);
				return false;
			} else
				return true;
		}
	}

	/* Muestra el catalogo de los Especialistas */
	@Listen("onClick = #btnBuscarEspecialista")
	public void mostrarCatalogo() {
		final List<Especialista> especialistas = servicioEspecialista
				.buscarTodos();
		catalogo = new Catalogo<Especialista>(catalogoEspecialista,
				"Catalogo de Pacientes", especialistas, "Cedula", "Nombre",
				"Apellido", "Costo Servicio", "Especialidad") {

			@Override
			protected List<Especialista> buscar(String valor, String combo) {

				switch (combo) {
				case "Cedula":
					return servicioEspecialista.filtroCedula(valor);
				case "Nombre":
					return servicioEspecialista.filtroNombre(valor);
				case "Apellido":
					return servicioEspecialista.filtroApellido(valor);
				case "Costo Servicio":
					return servicioEspecialista.filtroCosto(valor);
				case "Especialidad":
					return servicioEspecialista.filtroEspecialidad(valor);
				default:
					return especialistas;
				}
			}

			@Override
			protected String[] crearRegistros(Especialista objeto) {
				String[] registros = new String[5];
				registros[0] = objeto.getCedula();
				registros[1] = objeto.getNombre();
				registros[2] = objeto.getApellido();
				registros[3] = String.valueOf(objeto.getCosto());
				registros[4] = objeto.getEspecialidad().getDescripcion();
				return registros;
			}

		};
		catalogo.setParent(catalogoEspecialista);
		catalogo.doModal();
	}

	/* Valida la cedula */
	@Listen("onChange = #txtCedulaEspecialista")
	public void validarCedula() {
		if (!Validador.validarNumero(txtCedulaEspecialista.getValue())) {
			msj.mensajeAlerta(Mensaje.cedulaInvalida);
		}
	}

	/* Llena el combo de Especialidades cada vez que se abre */
	@Listen("onOpen = #cmbEspecialidad")
	public void llenarComboEspecialidad() {
		List<Especialidad> especialidades = servicioEspecialidad.buscarTodas();
		cmbEspecialidad
				.setModel(new ListModelList<Especialidad>(especialidades));
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoEspecialista")
	public void seleccinar() {
		Especialista especialista = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(especialista);
		catalogo.setParent(null);
	}

	/* Busca si existe un especialista con la misma cedula escrita */
	@Listen("onChange = #txtCedulaEspecialista")
	public void buscarPorCedula() {
		Especialista especialista = servicioEspecialista
				.buscar(txtCedulaEspecialista.getValue());
		if (especialista != null)
			llenarCampos(especialista);
	}

	/* LLena los campos del formulario dado un especialista */
	private void llenarCampos(Especialista especialista) {
		txtCedulaEspecialista.setValue(especialista.getCedula());
		txtNombreEspecialista.setValue(especialista.getNombre());
		txtApellidoEspecialista.setValue(especialista.getApellido());
		dspCosto.setValue(especialista.getCosto());
		cmbEspecialidad.setValue(especialista.getEspecialidad()
				.getDescripcion());
		id = Long.parseLong(especialista.getCedula());
	}

	/* Abre la vista de Especialidad */
	@Listen("onClick = #btnAbrirEspecialidad")
	public void abrirEspecialidad() {
		Arbol arbolItem = servicioArbol.buscarPorNombreArbol("Especialidad");
		cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
	}
}
