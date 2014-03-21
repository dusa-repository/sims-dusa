package controlador.maestros;

import java.util.List;

import modelo.maestros.Categoria;
import modelo.maestros.Unidad;
import modelo.seguridad.Usuario;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;

public class CUnidad extends CGenerico {

	private static final long serialVersionUID = -1573821851840127496L;
	@Wire
	private Div botoneraUnidad;
	@Wire
	private Textbox txtNombreUnidad;
	@Wire
	private Button btnBuscarUnidad;
	@Wire
	private Div catalogoUnidad;
	@Wire
	private Div divUnidad;
	private long id = 0;
	Catalogo<Unidad> catalogo;

	@Override
	public void inicializar() {

		Botonera botonera = new Botonera() {
			@Override
			public void guardar() {
				if (validar()) {
					Unidad unidad = new Unidad(id, fechaHora, horaAuditoria,
							txtNombreUnidad.getValue(), nombreUsuarioSesion());
					servicioUnidad.guardar(unidad);
					limpiar();
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
				}
			}

			@Override
			public void limpiar() {
				txtNombreUnidad.setValue("");
				id = 0;
			}

			@Override
			public void salir() {
				cerrarVentana(divUnidad);
			}

			@Override
			public void eliminar() {
				if (id != 0 && txtNombreUnidad.getText().compareTo("") != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar la Unidad?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Unidad unidad = servicioUnidad
												.buscar(id);
										List<Usuario> usuarios = servicioUsuario
												.buscarPorUnidad(unidad);
										if (!usuarios.isEmpty()) {
											Messagebox
													.show("No se Puede Eliminar el Registro, Esta siendo Utilizado",
															"Informacion",
															Messagebox.OK,
															Messagebox.INFORMATION);
										} else {
											servicioUnidad.eliminar(unidad);
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
		botoneraUnidad.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtNombreUnidad.getText().compareTo("") == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else
			return true;
	}

	/* Muestra el catalogo de las categorias */
	@Listen("onClick = #btnBuscarUnidad")
	public void mostrarCatalogo() {
		List<Unidad> unidades = servicioUnidad.buscarTodas();
		catalogo = new Catalogo<Unidad>(catalogoUnidad, "Catalogo de Unidades",
				unidades, "Nombre") {

			@Override
			protected List<Unidad> buscar(String valor,String combo) {
				if(combo.equals("Nombre"))
					return servicioUnidad.filtroNombre(valor);
					else
						return servicioUnidad.buscarTodas();
			}

			@Override
			protected String[] crearRegistros(Unidad objeto) {
				String[] registros = new String[1];
				registros[0] = objeto.getNombre();
				return registros;
			}

		};
		catalogo.setParent(catalogoUnidad);
		catalogo.doModal();
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoUnidad")
	public void seleccinar() {
		Unidad unidad = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(unidad);
		catalogo.setParent(null);
	}

	/* Busca si existe una unidad con el mismo nombre escrito */
	@Listen("onChange = #txtNombreUnidad")
	public void buscarPorNombre() {
		Unidad unidad = servicioUnidad.buscarPorNombre(txtNombreUnidad
				.getValue());
		if (unidad != null)
			llenarCampos(unidad);
	}

	/* LLena los campos del formulario dada una unidad */
	private void llenarCampos(Unidad unidad) {
		txtNombreUnidad.setValue(unidad.getNombre());
		id = unidad.getIdUnidad();
	}
}
