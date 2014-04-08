package controlador.maestros;

import java.io.IOException;
import java.util.List;

import modelo.maestros.Medicina;
import modelo.maestros.PresentacionComercial;
import modelo.seguridad.Arbol;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import arbol.CArbol;

import componentes.Botonera;
import componentes.Catalogo;

public class CPresentacionComercial extends CGenerico {

	private static final long serialVersionUID = 6414734962746880324L;

	@Wire
	private Textbox txtNombre;
	@Wire
	private Div botoneraPresentacion;
	@Wire
	private Div catalogoPresentacion;
	@Wire
	private Div divPresentacion;
	@Wire
	private Button btnBuscarPresentacion;
	@Wire
	private Combobox cmbMedicina;

	private CArbol cArbol = new CArbol();
	Catalogo<PresentacionComercial> catalogo;
	long id = 0;

	@Override
	public void inicializar() throws IOException {

		llenaComboMedicina();
		Botonera botonera = new Botonera() {
			@Override
			public void guardar() {

				if (validar()) {

					String nombre = txtNombre.getValue();
					long idMedicina = Long.valueOf(cmbMedicina
							.getSelectedItem().getContext());
					Medicina medicina = servicioMedicina.buscar(idMedicina);

					PresentacionComercial presentacion = new PresentacionComercial(id, fechaHora,
							horaAuditoria, nombre, nombreUsuarioSesion(),
							medicina);
					servicioPresentacion.guardar(presentacion);
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);

					limpiar();
				}

			}

			@Override
			public void limpiar() {
				txtNombre.setText("");
				cmbMedicina.setValue("");
				cmbMedicina.setPlaceholder("Seleccione una Medicina");
				id = 0;
			}

			@Override
			public void salir() {
				cerrarVentana(divPresentacion, "Presentacion Comercial");
			}

			@Override
			public void eliminar() {

				if (id != 0) {
					Messagebox.show(
							"¿Esta Seguro de Eliminar la Presentacion?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {

										PresentacionComercial presentacion = servicioPresentacion
												.buscar(id);
										servicioPresentacion
												.eliminar(presentacion);
										limpiar();
										Messagebox
												.show("Registro Eliminado Exitosamente",
														"Informacion",
														Messagebox.OK,
														Messagebox.INFORMATION);
									}
								}
							});
				} else
					Messagebox.show("No ha Seleccionado Ningun Registro",
							"Alerta", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		};
		/* Dibuja el componente botonera en el div botoneraPresentacionr */
		botoneraPresentacion.appendChild(botonera);
	}

	/* Muestra un catalogo de presentaciones */
	@Listen("onClick = #btnBuscarPresentacion")
	public void mostrarCatalogo() throws IOException {
		List<PresentacionComercial> presentaciones = servicioPresentacion.buscarTodas();
		catalogo = new Catalogo<PresentacionComercial>(catalogoPresentacion,
				"Catalogo de Presentaciones", presentaciones, "Nombre",
				"Medicina") {

			@Override
			protected String[] crearRegistros(PresentacionComercial presentacion) {
				String[] registros = new String[2];
				registros[0] = presentacion.getNombre();
				registros[1] = presentacion.getMedicina().getNombre();
				return registros;
			}

			@Override
			protected List<PresentacionComercial> buscar(String valor,String combo) {
				if(combo.equals("Nombre"))
				return servicioPresentacion.filtroNombre(valor);
				else{
					if(combo.equals("Medicina"))
					return servicioPresentacion.filtroMedicina(valor);
				}
				return servicioPresentacion.buscarTodas();
			}
		};
		catalogo.setParent(catalogoPresentacion);
		catalogo.doModal();
	}

	/* Llena el combo de medicinas cada vez que se abre */
	@Listen("onOpen = #cmbMedicina")
	public void llenaComboMedicina() {
		List<Medicina> medicina = servicioMedicina.buscarTodas();
		cmbMedicina.setModel(new ListModelList<Medicina>(medicina));
	}


	/* Validaciones de pantalla para poder realizar el guardar */
	public boolean validar() {

		if (cmbMedicina.getText().compareTo("") == 0
				|| txtNombre.getText().compareTo("") == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else
			return true;
	}

	/* Busca si existe una presentacion con el mismo nombre escrito */
	@Listen("onChange = #txtNombre")
	public void buscarPorNombre() {
		PresentacionComercial presentacion = servicioPresentacion
				.buscarPorNombre(txtNombre.getValue());
		if (presentacion != null)
			llenarCampos(presentacion);
	}

	/*
	 * Selecciona una presentacion del catalogo y llena los campos con la
	 * informacion
	 */
	@Listen("onSeleccion = #catalogoPresentacion")
	public void seleccion() {
		PresentacionComercial presentacion = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(presentacion);
		catalogo.setParent(null);
	}

	/* LLena los campos del formulario dada una presentacion */
	public void llenarCampos(PresentacionComercial presentacion) {
		txtNombre.setValue(presentacion.getNombre());
		cmbMedicina.setValue(presentacion.getMedicina().getNombre());
		id = presentacion.getIdPresentacion();
	}
	
	/* Abre la vista de Medicina*/
	@Listen("onClick = #btnAbrirMedicina")
	public void abrirMedicina(){		
		Arbol arbolItem = servicioArbol.buscarPorNombreArbol("Medicina");
		cArbol.abrirVentanas(arbolItem);	
	}
}
