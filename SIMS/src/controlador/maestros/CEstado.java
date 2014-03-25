package controlador.maestros;

import java.io.IOException;
import java.util.List;

import modelo.maestros.Categoria;
import modelo.maestros.Ciudad;
import modelo.maestros.Diagnostico;
import modelo.maestros.Estado;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;

public class CEstado extends CGenerico {

	private static final long serialVersionUID = 4342374400625748947L;
	@Wire
	private Div botoneraEstado;
	@Wire
	private Textbox txtNombreEstado;
	@Wire
	private Button btnBuscarEstado;
	@Wire
	private Div catalogoEstado;
	@Wire
	private Div divEstado;
	private long id = 0;
	Catalogo<Estado> catalogo;

	@Override
	public void inicializar() throws IOException {
		// TODO Auto-generated method stub
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divEstado, "Estado");
			}

			@Override
			public void limpiar() {
				txtNombreEstado.setValue("");
				id = 0;
			}

			@Override
			public void guardar() {
				if (validar()) {
					String nombre = txtNombreEstado.getValue();
					Estado estado = new Estado(id, fechaHora, horaAuditoria,
							nombre, nombreUsuarioSesion());
					servicioEstado.guardar(estado);
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
					limpiar();
				}
			}

			@Override
			public void eliminar() {
				if (id != 0 && txtNombreEstado.getText().compareTo("") != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar el Estado?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Estado estado = servicioEstado
												.buscar(id);
										List<Ciudad> ciudades = servicioCiudad
												.buscarPorEstado(estado);
										if (!ciudades.isEmpty()) {
											Messagebox
													.show("No se Puede Eliminar el Registro, Esta siendo Utilizado",
															"Informacion",
															Messagebox.OK,
															Messagebox.INFORMATION);
										} else {
											servicioEstado.eliminar(estado);
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
				} else {
					Messagebox.show("No ha Seleccionado Ningun Registro",
							"Alerta", Messagebox.OK, Messagebox.EXCLAMATION);
				}
			}
		};
		botoneraEstado.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtNombreEstado.getText().compareTo("") == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else
			return true;
	}

	/* Muestra el catalogo de los estados */
	@Listen("onClick = #btnBuscarEstado")
	public void mostrarCatalogo() {
		final List<Estado> estados = servicioEstado.buscarTodos();
		catalogo = new Catalogo<Estado>(catalogoEstado, "Catalogo de Estados",
				estados, "Nombre") {

			@Override
			protected List<Estado> buscar(String valor, String combo) {
				if (combo.equals("Nombre"))
					return servicioEstado.filtroNombre(valor);
				else
					return estados;
			}

			@Override
			protected String[] crearRegistros(Estado estado) {
				String[] registros = new String[1];
				registros[0] = estado.getNombre();
				return registros;
			}
		};
		catalogo.setParent(catalogoEstado);
		catalogo.doModal();
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoEstado")
	public void seleccinar() {
		Estado estado = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(estado);
		catalogo.setParent(null);
	}

	/* Busca si existe un estado con el mismo nombre escrito */
	@Listen("onChange = #txtNombreEstado")
	public void buscarPorNombre() {
		Estado estado = servicioEstado.buscarPorNombre(txtNombreEstado
				.getValue());
		if (estado != null)
			llenarCampos(estado);
	}

	/* LLena los campos del formulario dado un estado */
	private void llenarCampos(Estado estado) {
		txtNombreEstado.setValue(estado.getNombre());
		id = estado.getIdEstado();
	}
}
