package controlador.maestros;

import java.util.List;

import modelo.maestros.Categoria;
import modelo.maestros.Diagnostico;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;

public class CCategoria extends CGenerico {

	private static final long serialVersionUID = 3977153060950873260L;
	@Wire
	private Div botoneraCategoria;
	@Wire
	private Textbox txtNombreCategoria;
	@Wire
	private Button btnBuscarCategoria;
	@Wire
	private Div catalogoCategoria;
	@Wire
	private Div divCategoria;
	private long id = 0;
	Catalogo<Categoria> catalogo;

	@Override
	public void inicializar() {
		Botonera botonera = new Botonera() {
			@Override
			public void guardar() {
				if (validar()) {
					String nombre = txtNombreCategoria.getValue();
					Categoria categoria = new Categoria(id, fechaHora,
							horaAuditoria, nombre, nombreUsuarioSesion());
					servicioCategoria.guardar(categoria);
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
					limpiar();
				}
			}

			@Override
			public void limpiar() {
				txtNombreCategoria.setValue("");
				id = 0;
			}

			@Override
			public void salir() {
				cerrarVentana(divCategoria);
			}

			@Override
			public void eliminar() {
				if (id != 0 && txtNombreCategoria.getText().compareTo("") != 0) {
					Messagebox.show("�Esta Seguro de Eliminar la Categoria?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Categoria categoria = servicioCategoria
												.buscar(id);
										List<Diagnostico> diagnosticos = servicioDiagnostico
												.buscarPorCategoria(categoria);
										if (!diagnosticos.isEmpty()) {
											Messagebox
													.show("No se Puede Eliminar el Registro, Esta siendo Utilizado",
															"Informacion",
															Messagebox.OK,
															Messagebox.INFORMATION);
										} else {
											servicioCategoria
													.eliminar(categoria);
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
		botoneraCategoria.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtNombreCategoria.getText().compareTo("") == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else
			return true;
	}

	/* Muestra el catalogo de las categorias */
	@Listen("onClick = #btnBuscarCategoria")
	public void mostrarCatalogo() {
		List<Categoria> categorias = servicioCategoria.buscarTodas();
		catalogo = new Catalogo<Categoria>(catalogoCategoria,
				"Catalogo de Categorias", categorias, "Nombre") {

			@Override
			protected List<Categoria> buscar(String valor) {
				return null;
			}

			@Override
			protected String[] crearRegistros(Categoria categoria) {
				String[] registros = new String[1];
				registros[0] = categoria.getNombre();
				return registros;
			}
		};
		catalogo.setParent(catalogoCategoria);
		catalogo.doModal();
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoCategoria")
	public void seleccinar() {
		Categoria categoria = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(categoria);
		catalogo.setParent(null);
	}

	/* Busca si existe una categoria con el mismo codigo escrito */
	@Listen("onChange = #txtNombreCategoria")
	public void buscarPorNombre() {
		Categoria categoria = servicioCategoria
				.buscarPorNombre(txtNombreCategoria.getValue());
		if (categoria != null)
			llenarCampos(categoria);
	}

	/* LLena los campos del formulario dada una categoria */
	private void llenarCampos(Categoria categoria) {
		txtNombreCategoria.setValue(categoria.getNombre());
		id = categoria.getIdCategoria();
	}
}
