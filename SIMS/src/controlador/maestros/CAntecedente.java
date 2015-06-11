package controlador.maestros;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import modelo.maestros.Antecedente;
import modelo.maestros.AntecedenteTipo;
import modelo.maestros.PacienteAntecedente;

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

import security.modelo.Arbol;
import arbol.CArbol;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;

public class CAntecedente extends CGenerico {

	@Wire
	private Textbox txtNombreAntecedente;
	@Wire
	private Div botoneraAntecedente;
	@Wire
	private Div catalogoAntecedente;
	@Wire
	private Div divAntecedente;
	@Wire
	private Button btnBuscarAntecedente;
	@Wire
	private Combobox cmbTipoAntecedente;

	private CArbol cArbol = new CArbol();
	Catalogo<Antecedente> catalogo;
	long id = 0;

	@Override
	public void inicializar() throws IOException {
		contenido = (Include) divAntecedente.getParent();
		Tabbox tabox = (Tabbox) divAntecedente.getParent().getParent()
				.getParent().getParent();
		tabBox = tabox;
		tab = (Tab) tabox.getTabs().getLastChild();
		HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (map != null) {
			if (map.get("tabsGenerales") != null) {
				tabs = (List<Tab>) map.get("tabsGenerales");
				map.clear();
				map = null;
			}
		}
		llenarCombo();
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divAntecedente, "Antecedente", tabs);
			}

			@Override
			public void limpiar() {
				txtNombreAntecedente.setText("");
				cmbTipoAntecedente.setValue("");
				cmbTipoAntecedente
						.setPlaceholder("Seleccione una Clasificacion");
				id = 0;
			}

			@Override
			public void guardar() {
				if (validar()) {
					String nombre = txtNombreAntecedente.getValue();
					long idAntecedenteTipo = Long.valueOf(cmbTipoAntecedente
							.getSelectedItem().getContext());
					AntecedenteTipo antecedenteTipo = servicioAntecedenteTipo
							.buscar(idAntecedenteTipo);
					Antecedente antecedente = new Antecedente(id, nombre,
							antecedenteTipo, fechaHora, horaAuditoria,
							nombreUsuarioSesion());
					servicioAntecedente.guardar(antecedente);
					msj.mensajeInformacion(Mensaje.guardado);
					limpiar();
				}

			}

			@Override
			public void eliminar() {
				if (id != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar el Antecedente?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Antecedente antecedente = servicioAntecedente
												.buscar(id);
										List<PacienteAntecedente> pacientes = servicioPacienteAntecedente
												.buscarPorAntecedente(antecedente);
										if (!pacientes.isEmpty())
											msj.mensajeError(Mensaje.noEliminar);
										else {
											servicioAntecedente
													.eliminar(antecedente);
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
		botoneraAntecedente.appendChild(botonera);
	}

	/* Validaciones de pantalla para poder realizar el guardar */
	public boolean validar() {

		if (cmbTipoAntecedente.getText().compareTo("") == 0
				|| txtNombreAntecedente.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	/* Muestra un catalogo de antecedentees */
	@Listen("onClick = #btnBuscarAntecedente")
	public void mostrarCatalogo() throws IOException {
		final List<Antecedente> antecedentes = servicioAntecedente
				.buscarTodos();
		catalogo = new Catalogo<Antecedente>(catalogoAntecedente,
				"Catalogo de Antecedentes", antecedentes,false, "Nombre",
				"Clasificacion") {

			@Override
			protected String[] crearRegistros(Antecedente antecedente) {
				String[] registros = new String[2];
				registros[0] = antecedente.getNombre();
				registros[1] = antecedente.getAntecedenteTipo().getNombre();
				return registros;
			}

			@Override
			protected List<Antecedente> buscar(String valor, String combo) {

				switch (combo) {
				case "Nombre":
					return servicioAntecedente.filtroNombre(valor);
				case "Clasificacion":
					return servicioAntecedente.filtroAntecedenteTipo(valor);
				default:
					return antecedentes;
				}
			}
		};
		catalogo.setParent(catalogoAntecedente);
		catalogo.doModal();
	}

	/* Busca si existe una antecedente con el mismo nombre escrito */
	@Listen("onChange = #txtNombreAntecedente")
	public void buscarPorNombre() {
		Antecedente antecedente = servicioAntecedente
				.buscarPorNombre(txtNombreAntecedente.getValue());
		if (antecedente != null)
			llenarCampos(antecedente);
	}

	/* Llena el combo de antecedenteTipo cada vez que se abre */
	@Listen("onOpen = #cmbTipoAntecedente")
	public void llenarCombo() {
		List<AntecedenteTipo> antecedenteTipos = servicioAntecedenteTipo
				.buscarTodos();
		cmbTipoAntecedente.setModel(new ListModelList<AntecedenteTipo>(
				antecedenteTipos));
	}

	/*
	 * Selecciona una antecedente del catalogo y llena los campos con la
	 * informacion
	 */
	@Listen("onSeleccion = #catalogoAntecedente")
	public void seleccion() {
		Antecedente antecedente = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(antecedente);
		catalogo.setParent(null);
	}

	/* LLena los campos del formulario dada una antecedente */
	public void llenarCampos(Antecedente antecedente) {
		txtNombreAntecedente.setValue(antecedente.getNombre());
		cmbTipoAntecedente.setValue(antecedente.getAntecedenteTipo()
				.getNombre());
		id = antecedente.getIdAntecedente();
	}

	/* Abre la vista de AntecedenteTipo */
	@Listen("onClick = #btnAbrirTipoAntecedente")
	public void abrirAntecedenteTipo() {
		List<Arbol> arboles = servicioArbol
				.buscarPorNombreArbol("Clasificacion de Antecedente");
		if (!arboles.isEmpty()) {
			Arbol arbolItem = arboles.get(0);
			cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
		}
	}

}
