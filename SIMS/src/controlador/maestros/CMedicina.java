package controlador.maestros;

import java.io.IOException;
import java.util.List;

import modelo.maestros.FormaTerapeutica;
import modelo.maestros.Laboratorio;
import modelo.maestros.Medicina;
import modelo.maestros.Presentacion;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
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
	private Combobox cmbFormaTerapeutica;
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

	Catalogo<Medicina> catalogo;
	long id = 0;

	@Override
	public void inicializar() throws IOException {

		txtDenominacionGenerica.setFocus(true);
		Botonera botonera = new Botonera() {
			@Override
			public void guardar() {

				if (validar()) {
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

					long idFormaTerapeutica = Long.valueOf(cmbFormaTerapeutica
							.getSelectedItem().getContext());
					FormaTerapeutica formaTerapeutica = servicioFormaTerapeutica
							.buscar(idFormaTerapeutica);

					Medicina medicina = new Medicina(id, composicion,
							contraindicaciones, denominacionGenerica, efectos,
							embarazo, fechaHora, horaAuditoria, indicaciones,
							nombre, posologia, precauciones,
							nombreUsuarioSesion(), formaTerapeutica,
							laboratorio);
					servicioMedicina.guardar(medicina);
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
					limpiar();
				}

			}

			@Override
			public void limpiar() {
				txtNombre.setText("");
				cmbLaboratorio.setText("");
				cmbFormaTerapeutica.setText("");
				cmbLaboratorio.setPlaceholder("Seleccione un Laboratorio");
				cmbFormaTerapeutica
						.setPlaceholder("Seleccione una Forma Terapeutica");
				txtDenominacionGenerica.setText("");
				txtComposicion.setText("");
				txtPosologia.setText("");
				txtIndicaciones.setText("");
				txtEfectos.setText("");
				txtPrecauciones.setText("");
				txtContraindicaciones.setText("");
				txtEmbarazo.setText("");
				id = 0;
			}

			@Override
			public void salir() {
				cerrarVentana(divMedicina, "Medicina");
			}

			@Override
			public void eliminar() {

				if (id != 0) {
					Messagebox.show("�Esta Seguro de Eliminar la Medicina?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Medicina medicina = servicioMedicina
												.buscar(id);
										List<Presentacion> presentaciones = servicioPresentacion
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

	/* Llena el combo de formas terapeuticas cada vez que se abre */
	@Listen("onOpen = #cmbFormaTerapeutica")
	public void llenarComboFormas() {
		List<FormaTerapeutica> formasTerapeuticas = servicioFormaTerapeutica
				.buscarTodos();
		cmbFormaTerapeutica.setModel(new ListModelList<FormaTerapeutica>(
				formasTerapeuticas));
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
		cmbFormaTerapeutica
				.setValue(medicina.getFormaTerapeutica().getNombre());
		txtDenominacionGenerica.setValue(medicina.getDenominacionGenerica());
		txtComposicion.setValue(medicina.getComposicion());
		txtPosologia.setValue(medicina.getPosologia());
		txtIndicaciones.setValue(medicina.getIndicaciones());
		txtEfectos.setValue(medicina.getEfectos());
		txtPrecauciones.setValue(medicina.getPrecaucion());
		txtContraindicaciones.setValue(medicina.getContraindicaciones());
		txtEmbarazo.setValue(medicina.getEmbarazo());
		txtNombre.setValue(medicina.getNombre());

		id = medicina.getIdMedicina();
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
