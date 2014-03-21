package controlador.maestros;

import java.util.List;

import modelo.maestros.Especialidad;
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

public class CEspecialidad extends CGenerico {

	private static final long serialVersionUID = 2377150704906523958L;
	@Wire
	private Div botoneraEspecialidad;
	@Wire
	private Textbox txtDescripcionEspecialidad;
	@Wire
	private Button btnBuscarEspecialidad;
	@Wire
	private Div catalogoEspecialidad;
	@Wire
	private Div divEspecialidad;
	private long id = 0;
	Catalogo<Especialidad> catalogo;

	@Override
	public void inicializar() {

		Botonera botonera = new Botonera() {

			@Override
			public void guardar() {
				if (validar()) {
					String nombre = txtDescripcionEspecialidad.getValue();
					Especialidad especialidad = new Especialidad(id, nombre,
							fechaHora, horaAuditoria, nombreUsuarioSesion());
					servicioEspecialidad.guardar(especialidad);
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
					limpiar();
				}
			}

			@Override
			public void limpiar() {
				txtDescripcionEspecialidad.setValue("");
				id = 0;
			}

			@Override
			public void salir() {
				cerrarVentana(divEspecialidad);
			}

			@Override
			public void eliminar() {
				if (id != 0
						&& txtDescripcionEspecialidad.getText().compareTo("") != 0) {
					Messagebox.show(
							"¿Esta Seguro de Eliminar la Especialidad?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Especialidad especialidad = servicioEspecialidad
												.buscar(id);
										List<Usuario> usuarios = servicioUsuario
												.buscarPorEspecialidad(especialidad);
										if (!usuarios.isEmpty()) {
											Messagebox
													.show("No se Puede Eliminar el Registro, Esta siendo Utilizado",
															"Informacion",
															Messagebox.OK,
															Messagebox.INFORMATION);
										} else {
											servicioEspecialidad
													.eliminar(especialidad);
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
		botoneraEspecialidad.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtDescripcionEspecialidad.getText().compareTo("") == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else
			return true;
	}

	/* Muestra el catalogo de las especialidades */
	@Listen("onClick = #btnBuscarEspecialidad")
	public void mostrarCatalogo() {
		List<Especialidad> especialidades = servicioEspecialidad.buscarTodas();
		catalogo = new Catalogo<Especialidad>(catalogoEspecialidad,
				"Catalogo de Categorias", especialidades, "Nombre") {

			@Override
			protected List<Especialidad> buscar(String valor) {
				return null;
			}

			@Override
			protected String[] crearRegistros(Especialidad objeto) {
				String[] registros = new String[1];
				registros[0] = objeto.getDescripcion();
				return registros;
			}

		};
		catalogo.setParent(catalogoEspecialidad);
		catalogo.doModal();
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoEspecialidad")
	public void seleccinar() {
		Especialidad especialidad = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(especialidad);
		catalogo.setParent(null);
	}

	/* Busca si existe una especialidad con el mismo nombre escrito */
	@Listen("onChange = #txtDescripcionEspecialidad")
	public void buscarPorNombre() {
		Especialidad especialidad = servicioEspecialidad
				.buscarPorDescripcion(txtDescripcionEspecialidad.getValue());
		if (especialidad != null)
			llenarCampos(especialidad);
	}

	/* LLena los campos del formulario dada una especialidad */
	private void llenarCampos(Especialidad especialidad) {
		txtDescripcionEspecialidad.setValue(especialidad.getDescripcion());
		id = especialidad.getIdEspecialidad();
	}
}
