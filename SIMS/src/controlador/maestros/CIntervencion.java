package controlador.maestros;

import java.io.IOException;
import java.util.List;

import modelo.maestros.Intervencion;
import modelo.transacciones.HistoriaIntervencion;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;

public class CIntervencion extends CGenerico {

	private static final long serialVersionUID = -5263743713163591765L;
	@Wire
	private Div botoneraIntervencion;
	@Wire
	private Textbox txtNombre;
	@Wire
	private Div catalogoIntervencion;
	@Wire
	private Div divIntervencion;
	private long id = 0;
	Catalogo<Intervencion> catalogo;

	@Override
	public void inicializar() throws IOException {
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divIntervencion, "Intervencion");
			}

			@Override
			public void limpiar() {
				txtNombre.setValue("");
				id = 0;
			}

			@Override
			public void guardar() {
				if (validar()) {
					String nombre = txtNombre.getValue();
					Intervencion intervencion = new Intervencion(id, nombre,
							fechaHora, horaAuditoria, nombreUsuarioSesion());
					servicioIntervencion.guardar(intervencion);
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
					limpiar();
				}
			}

			@Override
			public void eliminar() {
				if (id != 0 && txtNombre.getText().compareTo("") != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar la Intervencion?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Intervencion intervencion = servicioIntervencion
												.buscar(id);
										List<HistoriaIntervencion> estados = servicioHistoriaIntervencion
												.buscarPorIntervencion(intervencion);
										if (!estados.isEmpty()) {
											Messagebox
													.show("No se Puede Eliminar el Registro, Esta siendo Utilizado",
															"Informacion",
															Messagebox.OK,
															Messagebox.INFORMATION);
										} else {
											servicioIntervencion.eliminar(intervencion);
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
		botoneraIntervencion.appendChild(botonera);
	}

	protected boolean validar() {
		if (txtNombre.getText().compareTo("") == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else
			return true;
	}
	
	@Listen("onClick = #btnBuscarIntervencion")
	public void mostrarCatalogo() {
		final List<Intervencion> paises = servicioIntervencion.buscarTodos();
		catalogo = new Catalogo<Intervencion>(catalogoIntervencion, "Catalogo de Intervenciones",
				paises, "Nombre") {

			@Override
			protected List<Intervencion> buscar(String valor, String combo) {
				if (combo.equals("Nombre"))
					return servicioIntervencion.filtroNombre(valor);
				else
					return paises;
			}

			@Override
			protected String[] crearRegistros(Intervencion estado) {
				String[] registros = new String[1];
				registros[0] = estado.getNombre();
				return registros;
			}
		};
		catalogo.setParent(catalogoIntervencion);
		catalogo.doModal();
	}

	@Listen("onSeleccion = #catalogoIntervencion")
	public void seleccinar() {
		Intervencion intervencion = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(intervencion);
		catalogo.setParent(null);
	}

	@Listen("onChange = #txtNombre")
	public void buscarPorNombre() {
		Intervencion intervencion = servicioIntervencion.buscarPorNombre(txtNombre
				.getValue());
		if (intervencion != null)
			llenarCampos(intervencion);
	}

	private void llenarCampos(Intervencion pais) {
		txtNombre.setValue(pais.getNombre());
		id = pais.getIdIntervencion();
	}

}
