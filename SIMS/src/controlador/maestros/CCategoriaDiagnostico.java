package controlador.maestros;

import java.util.HashMap;
import java.util.List;

import modelo.maestros.CategoriaDiagnostico;
import modelo.maestros.ClasificacionDiagnostico;
import modelo.maestros.Diagnostico;
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

public class CCategoriaDiagnostico extends CGenerico {

	private static final long serialVersionUID = 3977153060950873260L;
	@Wire
	private Div botoneraCategoriaDiagnostico;
	@Wire
	private Textbox txtNombreCategoriaDiagnostico;
	@Wire
	private Button btnBuscarCategoriaDiagnostico;
	@Wire
	private Div catalogoCategoriaDiagnostico;
	@Wire
	private Div divCategoriaDiagnostico;
	@Wire
	private Combobox cmbClasificacion;
	private long id = 0;
	Catalogo<CategoriaDiagnostico> catalogo;
	CArbol cArbol = new CArbol();

	@Override
	public void inicializar() {
		contenido = (Include) divCategoriaDiagnostico.getParent();
		Tabbox tabox = (Tabbox) divCategoriaDiagnostico.getParent().getParent()
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
		Botonera botonera = new Botonera() {
			@Override
			public void guardar() {
				if (validar()) {
					String nombre = txtNombreCategoriaDiagnostico.getValue();
					ClasificacionDiagnostico clasificacion = servicioClasificacion
							.buscarPorNombre(cmbClasificacion.getValue());
					CategoriaDiagnostico categoria = new CategoriaDiagnostico(
							id, fechaHora, horaAuditoria, nombre,
							nombreUsuarioSesion(), clasificacion);
					servicioCategoriaDiagnostico.guardar(categoria);
					msj.mensajeInformacion(Mensaje.guardado);
					limpiar();
				}
			}

			@Override
			public void limpiar() {
				txtNombreCategoriaDiagnostico.setValue("");
				cmbClasificacion.setValue("");
				id = 0;
			}

			@Override
			public void salir() {
				cerrarVentana(divCategoriaDiagnostico, "Categoria Diagnostico",
						tabs);
			}

			@Override
			public void eliminar() {
				if (id != 0
						&& txtNombreCategoriaDiagnostico.getText()
								.compareTo("") != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar la Categoria?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										CategoriaDiagnostico categoria = servicioCategoriaDiagnostico
												.buscar(id);
										List<Diagnostico> diagnosticos = servicioDiagnostico
												.buscarPorCategoria(categoria);
										if (!diagnosticos.isEmpty()) {
											msj.mensajeError(Mensaje.noEliminar);
										} else {
											servicioCategoriaDiagnostico
													.eliminar(categoria);
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
		botoneraCategoriaDiagnostico.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtNombreCategoriaDiagnostico.getText().compareTo("") == 0
				|| cmbClasificacion.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	/* Muestra el catalogo de las categorias */
	@Listen("onClick = #btnBuscarCategoriaDiagnostico")
	public void mostrarCatalogo() {
		List<CategoriaDiagnostico> categorias = servicioCategoriaDiagnostico
				.buscarTodas();
		catalogo = new Catalogo<CategoriaDiagnostico>(
				catalogoCategoriaDiagnostico, "Catalogo de Categorias",
				categorias, false,"Nombre") {

			@Override
			protected List<CategoriaDiagnostico> buscar(String valor,
					String combo) {
				if (combo.equals("Nombre"))
					return servicioCategoriaDiagnostico.filtroNombre(valor);
				else
					return servicioCategoriaDiagnostico.buscarTodas();
			}

			@Override
			protected String[] crearRegistros(CategoriaDiagnostico categoria) {
				String[] registros = new String[1];
				registros[0] = categoria.getNombre();
				return registros;
			}
		};
		catalogo.setParent(catalogoCategoriaDiagnostico);
		catalogo.doModal();
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoCategoriaDiagnostico")
	public void seleccinar() {
		CategoriaDiagnostico categoria = catalogo
				.objetoSeleccionadoDelCatalogo();
		llenarCampos(categoria);
		catalogo.setParent(null);
	}

	/* Busca si existe una categoria con el mismo codigo escrito */
	@Listen("onChange = #txtNombreCategoriaDiagnostico")
	public void buscarPorNombre() {
		CategoriaDiagnostico categoria = servicioCategoriaDiagnostico
				.buscarPorNombre(txtNombreCategoriaDiagnostico.getValue());
		if (categoria != null)
			llenarCampos(categoria);
	}

	/* LLena los campos del formulario dada una categoria */
	private void llenarCampos(CategoriaDiagnostico categoria) {
		txtNombreCategoriaDiagnostico.setValue(categoria.getNombre());
		id = categoria.getIdCategoriaDiagnostico();
		if (categoria.getClasificacion() != null) {
			cmbClasificacion.setValue(categoria.getClasificacion().getNombre());
//			cmbClasificacion.getSelectedItem().setContext(
//					String.valueOf(categoria.getClasificacion()
//							.getIdClasificacion()));
		}
	}/* Llena el combo de estado cada vez que se abre */

	@Listen("onOpen = #cmbClasificacion")
	public void llenarCombo() {
		List<ClasificacionDiagnostico> estados = servicioClasificacion
				.buscarTodas();
		cmbClasificacion.setModel(new ListModelList<ClasificacionDiagnostico>(
				estados));
	}

	@Listen("onClick = #btnAbrirClasificacion")
	public void abrirEstado() {
		List<Arbol> arboles = servicioArbol
				.buscarPorNombreArbol("Clasificacion Categoria Diagnostico");
		if (!arboles.isEmpty()) {
			Arbol arbolItem = arboles.get(0);
			cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
		}
	}
}
