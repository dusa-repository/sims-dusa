package controlador.maestros;

import java.util.List;

import modelo.maestros.Especialidad;
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
		// TODO Auto-generated method stub
		Botonera botonera = new Botonera() {
			@Override
			public void guardar() {
				if (validar()) {
					String nombre = txtDescripcionEspecialidad.getValue();
					Especialidad especialidad = new Especialidad(id, nombre,
							fechaHora, horaAuditoria, nombreUsuarioSesion());
					servicioEspecialidad.guardar(especialidad);
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
				if (validar()) {
					Messagebox.show("¿Desea Eliminar la Especialidad?",
							"Dialogo de confirmacion", Messagebox.OK
									| Messagebox.CANCEL, Messagebox.QUESTION,
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
													.show("No se Puede Eliminar el Registro, se Utiliza en otra Entidad",
															"Informacion",
															Messagebox.OK,
															Messagebox.INFORMATION);
										} else {
											servicioEspecialidad
													.eliminar(especialidad);
											limpiar();
											Messagebox
													.show("Especialidad Eliminada exitosamente",
															"Informacion",
															Messagebox.OK,
															Messagebox.INFORMATION);
										}
									}
								}
							});
				}
			}

		};
		botoneraEspecialidad.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtDescripcionEspecialidad.getText().compareTo("") == 0) {
			Messagebox.show("Debe completar todos los campos", "Error",
					Messagebox.OK, Messagebox.ERROR);
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
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			protected String[] crearRegistros(Especialidad objeto) {
				// TODO Auto-generated method stub
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
		txtDescripcionEspecialidad.setValue(especialidad.getDescripcion());
		id = especialidad.getIdEspecialidad();
		catalogo.setParent(null);
	}
}
