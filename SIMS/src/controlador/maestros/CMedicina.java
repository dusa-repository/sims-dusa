package controlador.maestros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import modelo.maestros.CategoriaMedicina;
import modelo.maestros.Cita;
import modelo.maestros.Examen;
import modelo.maestros.Laboratorio;
import modelo.maestros.Medicina;
import modelo.maestros.MedicinaPresentacionUnidad;
import modelo.maestros.Paciente;
import modelo.maestros.PresentacionComercial;
import modelo.maestros.PresentacionMedicina;
import modelo.maestros.ServicioExterno;
import modelo.maestros.UnidadMedicina;
import modelo.seguridad.Arbol;
import modelo.transacciones.ConsultaEspecialista;
import modelo.transacciones.ConsultaMedicina;

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
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;

import arbol.CArbol;

import componentes.Botonera;
import componentes.Buscar;
import componentes.Catalogo;
import componentes.Mensaje;
import controlador.transacciones.CConsulta;

public class CMedicina extends CGenerico {

	private static final long serialVersionUID = -6022315737961269719L;

	@Wire
	private Tab tabEspecificaciones;
	@Wire
	private Tab tabPresentaciones;
	@Wire
	private Button btnSiguientePestanna;
	@Wire
	private Button btnAnteriorPestanna;
	@Wire
	private Textbox txtNombre;
	@Wire
	private Div botoneraMedicina;
	@Wire
	private Div catalogoMedicina;
	@Wire
	private Div divMedicina;
	@Wire
	private Button btnBuscarMedicina;
	@Wire
	private Combobox cmbLaboratorio;
	@Wire
	private Combobox cmbCategoria;
	@Wire
	private Textbox txtDenominacionGenerica;
	@Wire
	private Textbox txtComposicion;
	@Wire
	private Textbox txtPosologia;
	@Wire
	private Textbox txtIndicaciones;
	@Wire
	private Textbox txtEfectos;
	@Wire
	private Textbox txtPrecauciones;
	@Wire
	private Textbox txtContraindicaciones;
	@Wire
	private Textbox txtEmbarazo;
	@Wire
	private Tab tabDenominacionGenerica;
	@Wire
	private Tab tabComposicion;
	@Wire
	private Tab tabPosologia;
	@Wire
	private Tab tabIndicaciones;
	@Wire
	private Tab tabEfectos;
	@Wire
	private Tab tabPrecauciones;
	@Wire
	private Tab tabContraindicaciones;;
	@Wire
	private Tab tabEmbarazo;
	@Wire
	private Listbox ltbPresentaciones;
	@Wire
	private Listbox ltbPresentacionesAgregadas;
	@Wire
	private Textbox txtBuscadorPresentacion;

	Buscar<PresentacionMedicina> buscador;
	List<PresentacionMedicina> presentacionesDisponibles = new ArrayList<PresentacionMedicina>();
	List<MedicinaPresentacionUnidad> presentacionesUsadas = new ArrayList<MedicinaPresentacionUnidad>();
	ListModelList<UnidadMedicina> unidades;
	private CArbol cArbol = new CArbol();
	Catalogo<Medicina> catalogo;
	long id = 0;
	private boolean consulta = false;
	private CConsulta cConsulta = new CConsulta();
	List<Medicina> medicinaConsulta = new ArrayList<Medicina>();
	Listbox listaConsulta;

	@Override
	public void inicializar() throws IOException {
		contenido = (Include) divMedicina.getParent();
		Tabbox tabox = (Tabbox) divMedicina.getParent().getParent().getParent()
				.getParent();
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
				medicinaConsulta = (List<Medicina>) map.get("lista");
				listaConsulta = (Listbox) map.get("listbox");
				map.clear();
				map = null;
			}
		}
		llenarComboLaboratorios();
		llenarComboCategorias();
		llenarListaPresentaciones(null);
		buscar();
		txtDenominacionGenerica.setFocus(true);
		Botonera botonera = new Botonera() {
			@Override
			public void guardar() {
				List<MedicinaPresentacionUnidad> listaMedicinasPresentacion = new ArrayList<MedicinaPresentacionUnidad>();
				if (validar()) {
					boolean campoNulo = false;
					for (int i = 0; i < ltbPresentacionesAgregadas
							.getItemCount(); i++) {
						Listitem listItem = ltbPresentacionesAgregadas
								.getItemAtIndex(i);
						double valor = ((Doublespinner) ((listItem
								.getChildren().get(1))).getFirstChild())
								.getValue();
						String id = ((Combobox) ((listItem.getChildren().get(2)))
								.getFirstChild()).getValue();
						long idPresentacion = ((Spinner) ((listItem
								.getChildren().get(3))).getFirstChild())
								.getValue();
						if (String.valueOf(idPresentacion) == "" || id == ""
								|| valor == 0)
							campoNulo = true;
						else {
							id = ((Combobox) ((listItem.getChildren().get(2)))
									.getFirstChild()).getSelectedItem()
									.getContext();
							long idUnidad = Long.parseLong(id);
							PresentacionMedicina presentacion = servicioPresentacionMedicina
									.buscar(idPresentacion);
							UnidadMedicina unidadMedicina = servicioUnidadMedicina
									.buscar(idUnidad);
							MedicinaPresentacionUnidad medicinaPresentacionUnidad = new MedicinaPresentacionUnidad(
									null, presentacion, unidadMedicina, valor);
							listaMedicinasPresentacion
									.add(medicinaPresentacionUnidad);
						}
					}
					if (!campoNulo) {
						String nombre = txtNombre.getValue();
						String denominacionGenerica = txtDenominacionGenerica
								.getValue();
						String composicion = txtComposicion.getValue();
						String posologia = txtPosologia.getValue();
						String indicaciones = txtIndicaciones.getValue();
						String efectos = txtEfectos.getValue();
						String precauciones = txtPrecauciones.getValue();
						String contraindicaciones = txtContraindicaciones
								.getValue();
						String embarazo = txtEmbarazo.getValue();

						long idLaboratorio = Long.valueOf(cmbLaboratorio
								.getSelectedItem().getContext());
						Laboratorio laboratorio = servicioLaboratorio
								.buscar(idLaboratorio);
						CategoriaMedicina ca = servicioCategoriaMedicina
								.buscar(Long.parseLong(cmbCategoria
										.getSelectedItem().getContext()));
						Medicina medicina = new Medicina(id, composicion,
								contraindicaciones, denominacionGenerica,
								efectos, embarazo, fechaHora, horaAuditoria,
								indicaciones, nombre, posologia, precauciones,
								nombreUsuarioSesion(), laboratorio, ca);
						servicioMedicina.guardar(medicina);
						if (id != 0)
							medicina = servicioMedicina.buscar(id);
						else
							medicina = servicioMedicina.buscarUltima();

						List<MedicinaPresentacionUnidad> medicinasPresentacionesUnidades = servicioMedicinaPresentacionUnidad
								.buscarPresentacionesUsadas(medicina);
						if (!medicinasPresentacionesUnidades.isEmpty())
							servicioMedicinaPresentacionUnidad
									.eliminar(medicinasPresentacionesUnidades);
						for (int i = 0; i < listaMedicinasPresentacion.size(); i++) {
							listaMedicinasPresentacion.get(i).setMedicina(
									medicina);
						}
						servicioMedicinaPresentacionUnidad
								.guardar(listaMedicinasPresentacion);
						if (consulta) {
							if (id == 0)
								medicinaConsulta.add(medicina);
							cConsulta.recibirMedicina(medicinaConsulta,
									listaConsulta);
						}
						msj.mensajeInformacion(Mensaje.guardado);
						limpiar();
					} else {
						msj.mensajeError(Mensaje.camposPresentaciones);
					}
				}

			}

			@Override
			public void limpiar() {
				txtNombre.setText("");
				cmbLaboratorio.setText("");
				cmbLaboratorio.setPlaceholder("Seleccione un Laboratorio");
				cmbCategoria.setText("");
				cmbCategoria.setPlaceholder("Seleccione una Categoria");
				txtDenominacionGenerica.setText("");
				txtComposicion.setText("");
				txtPosologia.setText("");
				txtIndicaciones.setText("");
				txtEfectos.setText("");
				txtPrecauciones.setText("");
				txtContraindicaciones.setText("");
				txtEmbarazo.setText("");
				ltbPresentaciones.getItems().clear();
				ltbPresentacionesAgregadas.getItems().clear();
				id = 0;
				llenarListaPresentaciones(null);
				presentacionesDisponibles.clear();
				presentacionesUsadas.clear();
			}

			@Override
			public void salir() {
				cerrarVentana(divMedicina, "Medicina", tabs);
			}

			@Override
			public void eliminar() {

				if (id != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar la Medicina?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Medicina medicina = servicioMedicina
												.buscar(id);
										List<PresentacionComercial> presentaciones = servicioPresentacion
												.buscarPorMedicina(medicina);
										List<ConsultaMedicina> consultas = servicioConsultaMedicina
												.buscarPorMedicina(medicina);
										if (!presentaciones.isEmpty()
												|| !consultas.isEmpty()) {
											msj.mensajeError(Mensaje.noEliminar);
										} else {
											servicioMedicina.eliminar(medicina);
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
		/* Dibuja el componente botonera en el div botoneraPresentacionr */
		botoneraMedicina.appendChild(botonera);

	}

	/*
	 * Metodo que permite asignar una lista a un combo de manera dinamica, de
	 * tal forma que el id del combo no se repita
	 */
	public ListModelList<UnidadMedicina> getUnidades() {
		unidades = new ListModelList<UnidadMedicina>(
				servicioUnidadMedicina.buscarTodas());
		return unidades;
	}

	/* Llena la lista al iniciar con todas las presentaciones existentes */
	private void llenarListaPresentaciones(Medicina medicina) {
		if (medicina == null) {
			presentacionesDisponibles = servicioPresentacionMedicina
					.buscarTodas();
			ltbPresentaciones.setModel(new ListModelList<PresentacionMedicina>(
					presentacionesDisponibles));
		} else {
			presentacionesUsadas = servicioMedicinaPresentacionUnidad
					.buscarPresentacionesUsadas(medicina);
			ltbPresentacionesAgregadas
					.setModel(new ListModelList<MedicinaPresentacionUnidad>(
							presentacionesUsadas));
			if (!presentacionesUsadas.isEmpty()) {
				List<Long> ids = new ArrayList<Long>();
				for (int i = 0; i < presentacionesUsadas.size(); i++) {
					long id = presentacionesUsadas.get(i)
							.getPresentacionMedicina().getIdPresentacion();
					ids.add(id);
				}
				presentacionesDisponibles = servicioPresentacionMedicina
						.buscarPresentacionesDisponibles(ids);
				ltbPresentaciones
						.setModel(new ListModelList<PresentacionMedicina>(
								presentacionesDisponibles));
			}
		}
		ltbPresentacionesAgregadas.setMultiple(false);
		ltbPresentacionesAgregadas.setCheckmark(false);
		ltbPresentacionesAgregadas.setMultiple(true);
		ltbPresentacionesAgregadas.setCheckmark(true);

		ltbPresentaciones.setMultiple(false);
		ltbPresentaciones.setCheckmark(false);
		ltbPresentaciones.setMultiple(true);
		ltbPresentaciones.setCheckmark(true);
	}

	/* Muestra un catalogo de Medicinas */
	@Listen("onClick = #btnBuscarMedicina")
	public void mostrarCatalogo() throws IOException {
		List<Medicina> medicinas = servicioMedicina.buscarTodas();
		catalogo = new Catalogo<Medicina>(catalogoMedicina,
				"Catalogo de Medicinas", medicinas, "Nombre", "Laboratorio",
				"Posologia") {

			@Override
			protected String[] crearRegistros(Medicina medicina) {
				String[] registros = new String[3];
				registros[0] = medicina.getNombre();
				registros[1] = medicina.getLaboratorio().getNombre();
				registros[2] = medicina.getPosologia();

				return registros;
			}

			@Override
			protected List<Medicina> buscar(String valor, String combo) {
				if (combo.equals("Nombre"))
					return servicioMedicina.filtroNombre(valor);
				else {
					if (combo.equals("Laboratorio"))
						return servicioMedicina.filtroLaboratorio(valor);
					else {
						if (combo.equals("Posologia"))
							return servicioMedicina.filtroPosologia(valor);
						else
							return servicioMedicina.buscarTodas();
					}
				}

			}
		};
		catalogo.setParent(catalogoMedicina);
		catalogo.doModal();
	}

	/* Llena el combo de laboratorios cada vez que se abre */
	@Listen("onOpen = #cmbLaboratorio")
	public void llenarComboLaboratorios() {
		List<Laboratorio> laboratorios = servicioLaboratorio.buscarTodos();
		cmbLaboratorio.setModel(new ListModelList<Laboratorio>(laboratorios));
	}

	/* Llena el combo de categorias cada vez que se abre */
	@Listen("onOpen = #cmbCategoria")
	public void llenarComboCategorias() {
		List<CategoriaMedicina> categorias = servicioCategoriaMedicina
				.buscarTodas();
		cmbCategoria.setModel(new ListModelList<CategoriaMedicina>(categorias));
	}

	/* Validaciones de pantalla para poder realizar el guardar */
	public boolean validar() {

		if (cmbLaboratorio.getText().compareTo("") == 0
				|| cmbLaboratorio.getText().compareTo("") == 0
				|| txtNombre.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	/* Busca si existe una medicina con el mismo nombre escrito */
	@Listen("onChange = #txtNombre")
	public void buscarPorNombre() {
		Medicina medicina = servicioMedicina.buscarPorNombre(txtNombre
				.getValue());
		if (medicina != null)
			llenarCampos(medicina);

	}

	/*
	 * Selecciona una medicina del catalogo y llena los campos con la
	 * informacion
	 */
	@Listen("onSeleccion = #catalogoMedicina")
	public void seleccion() {

		Medicina medicinaSeleccionada = catalogo
				.objetoSeleccionadoDelCatalogo();
		llenarCampos(medicinaSeleccionada);
		catalogo.setParent(null);
	}

	/* LLena los campos del formulario dada una medicina */
	public void llenarCampos(Medicina medicina) {
		cmbLaboratorio.setValue(medicina.getLaboratorio().getNombre());
		cmbCategoria.setValue(medicina.getCategoriaMedicina().getNombre());
		txtDenominacionGenerica.setValue(medicina.getDenominacionGenerica());
		txtComposicion.setValue(medicina.getComposicion());
		txtPosologia.setValue(medicina.getPosologia());
		txtIndicaciones.setValue(medicina.getIndicaciones());
		txtEfectos.setValue(medicina.getEfectos());
		txtPrecauciones.setValue(medicina.getPrecaucion());
		txtContraindicaciones.setValue(medicina.getContraindicaciones());
		txtEmbarazo.setValue(medicina.getEmbarazo());
		txtNombre.setValue(medicina.getNombre());

		llenarListaPresentaciones(medicina);
		id = medicina.getIdMedicina();
	}

	/*
	 * Permite mover uno o varios elementos seleccionados desde la lista de la
	 * izquierda a la lista de la derecha
	 */
	@Listen("onClick = #pasar1")
	public void moverDerecha() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem = ltbPresentaciones.getItems();
		if (listItem.size() != 0) {
			for (int i = 0; i < listItem.size(); i++) {
				if (listItem.get(i).isSelected()) {
					PresentacionMedicina presentacionMedicina = listItem.get(i)
							.getValue();
					presentacionesDisponibles.remove(presentacionMedicina);
					MedicinaPresentacionUnidad medicinaPresentacionUnidad = new MedicinaPresentacionUnidad();
					medicinaPresentacionUnidad
							.setPresentacionMedicina(presentacionMedicina);
					presentacionesUsadas.add(medicinaPresentacionUnidad);
					ltbPresentacionesAgregadas
							.setModel(new ListModelList<MedicinaPresentacionUnidad>(
									presentacionesUsadas));
					listitemEliminar.add(listItem.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbPresentaciones.removeItemAt(listitemEliminar.get(i).getIndex());
		}
		ltbPresentacionesAgregadas.setMultiple(false);
		ltbPresentacionesAgregadas.setCheckmark(false);
		ltbPresentacionesAgregadas.setMultiple(true);
		ltbPresentacionesAgregadas.setCheckmark(true);
	}

	/*
	 * Permite mover uno o varios elementos seleccionados desde la lista de la
	 * derecha a la lista de la izquierda
	 */
	@Listen("onClick = #pasar2")
	public void moverIzquierda() {
		List<Listitem> listitemEliminar = new ArrayList<Listitem>();
		List<Listitem> listItem2 = ltbPresentacionesAgregadas.getItems();
		if (listItem2.size() != 0) {
			for (int i = 0; i < listItem2.size(); i++) {
				if (listItem2.get(i).isSelected()) {
					MedicinaPresentacionUnidad medicinaPresentacionUnidad = listItem2
							.get(i).getValue();
					presentacionesUsadas.remove(medicinaPresentacionUnidad);
					presentacionesDisponibles.add(medicinaPresentacionUnidad
							.getPresentacionMedicina());
					ltbPresentaciones
							.setModel(new ListModelList<PresentacionMedicina>(
									presentacionesDisponibles));
					listitemEliminar.add(listItem2.get(i));
				}
			}
		}
		for (int i = 0; i < listitemEliminar.size(); i++) {
			ltbPresentacionesAgregadas.removeItemAt(listitemEliminar.get(i)
					.getIndex());
		}
		ltbPresentaciones.setMultiple(false);
		ltbPresentaciones.setCheckmark(false);
		ltbPresentaciones.setMultiple(true);
		ltbPresentaciones.setCheckmark(true);
	}

	public void buscar() {
		buscador = new Buscar<PresentacionMedicina>(ltbPresentaciones,
				txtBuscadorPresentacion) {

			@Override
			protected List<PresentacionMedicina> buscar(String valor) {
				List<PresentacionMedicina> presentacionesFiltradas = new ArrayList<PresentacionMedicina>();
				List<PresentacionMedicina> presentaciones = servicioPresentacionMedicina
						.filtroNombre(valor);
				for (int i = 0; i < presentacionesDisponibles.size(); i++) {
					PresentacionMedicina presentacion = presentacionesDisponibles
							.get(i);
					for (int j = 0; j < presentaciones.size(); j++) {
						if (presentacion.getIdPresentacion() == presentaciones
								.get(j).getIdPresentacion())
							presentacionesFiltradas.add(presentacion);
					}
				}
				return presentacionesFiltradas;
			}
		};
	}

	/* Focus a la pestannas */

	@Listen("onClick = #tabDenominacionGenerica")
	public void pestanna1() {
		txtDenominacionGenerica.setFocus(true);
	}

	@Listen("onClick = #tabComposicion")
	public void pestanna2() {
		txtComposicion.setFocus(true);
	}

	@Listen("onClick = #tabPosologia")
	public void pestanna3() {
		txtPosologia.setFocus(true);
	}

	@Listen("onClick = #tabIndicaciones")
	public void pestanna4() {
		txtIndicaciones.setFocus(true);
	}

	@Listen("onClick = #tabEfectos")
	public void pestanna5() {
		txtEfectos.setFocus(true);
	}

	@Listen("onClick = #tabPrecauciones")
	public void pestanna6() {
		txtPrecauciones.setFocus(true);
	}

	@Listen("onClick = #tabContraindicaciones")
	public void pestanna7() {
		txtContraindicaciones.setFocus(true);
	}

	@Listen("onClick = #tabEmbarazo")
	public void pestanna8() {
		txtEmbarazo.setFocus(true);
	}

	/* Abre la pestanna de presentaciones */
	@Listen("onClick = #btnSiguientePestanna")
	public void siguientePestanna() {
		tabPresentaciones.setSelected(true);
	}

	/* Abre la pestanna de especificaciones */
	@Listen("onClick = #btnAnteriorPestanna")
	public void anteriorPestanna() {
		tabEspecificaciones.setSelected(true);
	}

	/* Abre la vista de Categoria */
	@Listen("onClick = #btnAbrirCategoria")
	public void abrirCategoria() {
		Arbol arbolItem = servicioArbol
				.buscarPorNombreArbol("Categoria Medicina");
		cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
	}

	/* Abre la vista de Presentacion */
	@Listen("onClick = #btnAbrirPresentacion")
	public void abrirPresentacion() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id", "medicina");
		map.put("lista", presentacionesDisponibles);
		map.put("listbox", ltbPresentaciones);
		Sessions.getCurrent().setAttribute("itemsCatalogo", map);
		Arbol arbolItem = servicioArbol
				.buscarPorNombreArbol("Presentacion Medicina");
		cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
	}

	/* Abre la vista de Laboratorio */
	@Listen("onClick = #btnAbrirLaboratorio")
	public void abrirLaboratorio() {
		Arbol arbolItem = servicioArbol.buscarPorNombreArbol("Laboratorio");
		cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);
	}

	public void recibirPresentacion(List<PresentacionMedicina> lista, Listbox l) {
		ltbPresentaciones = l;
		presentacionesDisponibles = lista;
		ltbPresentaciones.setModel(new ListModelList<PresentacionMedicina>(
				presentacionesDisponibles));
		ltbPresentaciones.setMultiple(false);
		ltbPresentaciones.setCheckmark(false);
		ltbPresentaciones.setMultiple(true);
		ltbPresentaciones.setCheckmark(true);
	}
}
