package controlador.maestros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import modelo.maestros.CategoriaMedicina;
import modelo.maestros.Laboratorio;
import modelo.maestros.Medicina;
import modelo.maestros.MedicinaPresentacionUnidad;
import modelo.maestros.PresentacionComercial;
import modelo.maestros.PresentacionMedicina;
import modelo.maestros.UnidadMedicina;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublespinner;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;

public class CMedicina extends CGenerico {

	private static final long serialVersionUID = -6022315737961269719L;

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

	List<PresentacionMedicina> presentacionesDisponibles = new ArrayList<PresentacionMedicina>();
	List<MedicinaPresentacionUnidad> presentacionesUsadas = new ArrayList<MedicinaPresentacionUnidad>();
	ListModelList<UnidadMedicina> unidades;

	Catalogo<Medicina> catalogo;
	long id = 0;

	@Override
	public void inicializar() throws IOException {

		llenarListaPresentaciones(null);
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
						if (String.valueOf(idPresentacion) == ""
								|| id == ""
								|| String.valueOf(valor) == "")
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
						Messagebox.show("Registro Guardado Exitosamente",
								"Informacion", Messagebox.OK,
								Messagebox.INFORMATION);
						limpiar();
					} else {
						Messagebox
								.show("Debe Llenar Todos los Campos de la Lista de Presentaciones",
										"Informacion", Messagebox.OK,
										Messagebox.INFORMATION);
					}
				}

			}

			@Override
			public void limpiar() {
				txtNombre.setText("");
				cmbLaboratorio.setText("");
				cmbLaboratorio.setPlaceholder("Seleccione un Laboratorio");
				cmbCategoria.setText("");
				cmbCategoria.setPlaceholder("Seleccione un Laboratorio");
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
			}

			@Override
			public void salir() {
				cerrarVentana(divMedicina, "Medicina");
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
										if (!presentaciones.isEmpty()) {
											Messagebox
													.show("No se Puede Eliminar el Registro, Esta siendo Utilizado",
															"Informacion",
															Messagebox.OK,
															Messagebox.INFORMATION);
										} else {
											servicioMedicina.eliminar(medicina);
											limpiar();
											Messagebox
													.show("Registro Eliminado Exitosamente",
															"Informacion",
															Messagebox.OK,
															Messagebox.INFORMATION);
										}
									}
								}
							});
				} else
					Messagebox.show("No ha Seleccionado Ningun Registro",
							"Alerta", Messagebox.OK, Messagebox.EXCLAMATION);

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
				|| txtNombre.getText().compareTo("") == 0
				|| txtDenominacionGenerica.getText().compareTo("") == 0
				|| txtComposicion.getText().compareTo("") == 0
				|| txtPosologia.getText().compareTo("") == 0
				|| txtIndicaciones.getText().compareTo("") == 0
				|| txtEfectos.getText().compareTo("") == 0
				|| txtPrecauciones.getText().compareTo("") == 0
				|| txtContraindicaciones.getText().compareTo("") == 0
				|| txtEmbarazo.getText().compareTo("") == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
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
		// Listitem list1 = ltbPresentaciones.getSelectedItem();
		// if (list1 == null)
		// Messagebox.show("Seleccione un Item", "Alerta", Messagebox.OK,
		// Messagebox.EXCLAMATION);
		// else
		// list1.setParent(ltbPresentacionesAgregadas);
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
		// Listitem list2 = ltbPresentacionesAgregadas.getSelectedItem();
		// if (list2 == null)
		// Messagebox.show("Seleccione un Item", "Alerta", Messagebox.OK,
		// Messagebox.EXCLAMATION);
		// else
		// list2.setParent(ltbPresentaciones);
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

}
