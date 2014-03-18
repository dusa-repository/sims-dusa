package controlador.maestros;

import java.util.List;

import modelo.maestros.Unidad;

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
		// TODO Auto-generated method stub
		Botonera botonera = new Botonera() {
			@Override
			public void guardar() {
				if (validar()) {
					Unidad unidad = new Unidad(id, fechaHora,
							horaAuditoria, txtNombreUnidad
									.getValue(),
							nombreUsuarioSesion());
					servicioUnidad.guardar(unidad);
					limpiar();
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
				if (validar()) {
					Messagebox.show("¿Desea Eliminar la Unidad?",
							"Dialogo de confirmacion", Messagebox.OK
									| Messagebox.CANCEL, Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Unidad unidad = new Unidad(id, fechaHora,
												horaAuditoria, txtNombreUnidad
														.getValue(),
												nombreUsuarioSesion());
										servicioUnidad.guardar(unidad);
										limpiar();
										Messagebox
												.show("Categoria Eliminada exitosamente",
														"Informacion",
														Messagebox.OK,
														Messagebox.INFORMATION);
									}
								}
							});
				}
			}

		};
		botoneraUnidad.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtNombreUnidad.getText().compareTo("") == 0) {
			Messagebox.show("Debe completar todos los campos", "Error",
					Messagebox.OK, Messagebox.ERROR);
			return false;
		} else
			return true;
	}
	
	/* Muestra el catalogo de las categorias */
	@Listen("onClick = #btnBuscarUnidad")
	public void mostrarCatalogo() {
		List<Unidad> unidades = servicioUnidad.buscarTodas();
		catalogo = new Catalogo<Unidad>(catalogoUnidad,
				"Catalogo de Unidades", unidades, "Nombre") {

					@Override
					protected List<Unidad> buscar(String valor) {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					protected String[] crearRegistros(Unidad objeto) {
						// TODO Auto-generated method stub
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
		txtNombreUnidad.setValue(unidad.getNombre());
		id = unidad.getIdUnidad();
		catalogo.setParent(null);
	}
}
