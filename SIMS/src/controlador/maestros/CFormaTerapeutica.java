package controlador.maestros;

import java.io.IOException;
import java.util.List;

import modelo.maestros.FormaTerapeutica;
import modelo.maestros.Laboratorio;
import modelo.maestros.Medicina;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;

public class CFormaTerapeutica extends CGenerico {

	private static final long serialVersionUID = -1626088569444819807L;
	
	@Wire
	private Textbox txtNombre;
	@Wire
	private Div botoneraFormaTerapeutica;
	@Wire
	private Div catalogoFormaTerapeutica;
	@Wire
	private Div divFormaTerapeutica;
	@Wire
	private Button btnBuscarFormaTerapeutica;

	Catalogo<FormaTerapeutica> catalogo;
	long id=0;
	
	@Override
	public void inicializar() throws IOException {
		Botonera botonera = new Botonera() {
			@Override
			public void guardar() {
	
				if (validar()) {
					String nombre = txtNombre.getValue();
					
					FormaTerapeutica formaTerapeutica = new FormaTerapeutica(id, fechaHora,
							horaAuditoria, nombre, nombreUsuarioSesion());

					servicioFormaTerapeutica.guardar(formaTerapeutica);
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
					limpiar();
				}

			}

			@Override
			public void limpiar() {
				txtNombre.setText("");
				id=0;
			}

			@Override
			public void salir() {
				cerrarVentana(divFormaTerapeutica);
			}

			@Override
			public void eliminar() {
				
				if (id != 0 && txtNombre.getText().compareTo("") != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar la Forma Terapeutica?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										FormaTerapeutica formaTerapeutica = servicioFormaTerapeutica.buscar(id);
										List<Medicina> medicinas = servicioMedicina.buscarPorFormaTerapeutica(formaTerapeutica);
										if (!medicinas.isEmpty()) {
											Messagebox
													.show("No se Puede Eliminar el Registro, Esta siendo Utilizado",
															"Informacion",
															Messagebox.OK,
															Messagebox.INFORMATION);
										} else {
											servicioFormaTerapeutica
													.eliminar(formaTerapeutica);
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
		/* Dibuja el componente botonera en el div botonera FormaTerapeutica */
		botoneraFormaTerapeutica.appendChild(botonera);
		
	}
	

	/* Muestra un catalogo de formas terapeuticas */
	@Listen("onClick = #btnBuscarFormaTerapeutica")
	public void mostrarCatalogo() throws IOException {
		List<FormaTerapeutica> formaTerapeuticas = servicioFormaTerapeutica.buscarTodos();
		catalogo = new Catalogo<FormaTerapeutica>(catalogoFormaTerapeutica,
				"Catalogo de Formas Terapeuticas", formaTerapeuticas, "Nombre") {

			@Override
			protected String[] crearRegistros(FormaTerapeutica formaTerapeutica) {
				String[] registros = new String[1];
				registros[0] = formaTerapeutica.getNombre();
				return registros;
			}

			@Override
			protected List<FormaTerapeutica> buscar(String valor,String combo) {
				if(combo.equals("Nombre"))
					return servicioFormaTerapeutica.filtroNombre(valor);
					else
						return servicioFormaTerapeutica.buscarTodos();
			}
		};
		catalogo.setParent(catalogoFormaTerapeutica);
		catalogo.doModal();
	}

	/*Validaciones de pantalla para poder realizar el guardar*/
	public boolean validar() {

		if (txtNombre.getText().compareTo("")==0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else
			return true;
	}
	
	/* Busca si existe una forma terapeutica con el mismo nombre escrito */
	@Listen("onChange = #txtNombre")
	public void buscarPorNombre() {
		FormaTerapeutica formaTerapeutica = servicioFormaTerapeutica.buscarPorNombre(txtNombre.getValue());
		if(formaTerapeutica!=null)
		llenarCampos(formaTerapeutica);
	}

	/* Selecciona una forma terapeutica del catalogo y llena los campos con la informacion */
	@Listen("onSeleccion = #catalogoFormaTerapeutica")
	public void seleccion() {
		FormaTerapeutica formaTerapeutica = catalogo
				.objetoSeleccionadoDelCatalogo();
		llenarCampos(formaTerapeutica);
		catalogo.setParent(null);
	}
	
	/* LLena los campos del formulario dada una forma terapeutica */
	public void llenarCampos(FormaTerapeutica formaTerapeutica) {
		txtNombre.setValue(formaTerapeutica.getNombre());
		id = formaTerapeutica.getIdFormaTerapeutica();
	}
}
