package controlador.sha;

import java.io.IOException;
import java.util.List;

import modelo.sha.Area;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import componentes.Botonera;
import componentes.Catalogo;

import controlador.maestros.CGenerico;

public class CArea extends CGenerico {

	@Wire
	private Div botoneraArea;
	@Wire
	private Textbox txtNombreArea;
	@Wire
	private Button btnBuscarArea;
	@Wire
	private Div catalogoArea;
	@Wire
	private Div divArea;
	@Wire
	private Window wdwArea;

	private long id = 0;
	Catalogo<Area> catalogo;

	@Override
	public void inicializar() throws IOException {

		txtNombreArea.setFocus(true);

		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divArea, "Area");

			}

			@Override
			public void limpiar() {
				txtNombreArea.setValue("");
				id = 0;
				txtNombreArea.setFocus(true);

			}

			@Override
			public void guardar() {
				if (validar()) {
					String nombre = txtNombreArea.getValue();
					Area area = new Area(id, nombre);
					servicioArea.guardar(area);
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
					limpiar();
				}

			}

			@Override
			public void eliminar() {
				if (id != 0 && txtNombreArea.getText().compareTo("") != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar el Area?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Area area = servicioArea.buscar(id);
										if (true) {
											Messagebox
													.show("No se Puede Eliminar el Registro, Esta siendo Utilizado",
															"Informacion",
															Messagebox.OK,
															Messagebox.INFORMATION);
										} else {
											servicioArea.eliminar(area);
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
		 botonera.getChildren().get(1).setVisible(false);
		 botoneraArea.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtNombreArea.getText().compareTo("") == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else
			return true;
	}

	/* Muestra el catalogo de los areas */
	@Listen("onClick = #btnBuscarArea")
	public void mostrarCatalogo() {
		final List<Area> areas = servicioArea.buscarTodos();
		catalogo = new Catalogo<Area>(catalogoArea,
				"Catalogo de Areas", areas, "Nombre") {

			@Override
			protected List<Area> buscar(String valor, String combo) {
				switch (combo) {
				case "Nombre":
					return servicioArea.filtroNombre(valor);
				default:
					return areas;
				}
			}

			@Override
			protected String[] crearRegistros(Area area) {
				String[] registros = new String[1];
				registros[0] = area.getNombre();
				return registros;
			}
		};
		catalogo.setParent(catalogoArea);
		catalogo.doModal();
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoArea")
	public void seleccinar() {
		Area area = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(area);
		catalogo.setParent(null);
	}

	/* Busca si existe un area con el mismo nombre escrito */
	@Listen("onChange = #txtNombreArea")
	public void buscarPorNombre() {
		Area area = servicioArea.buscarPorNombre(txtNombreArea.getValue());
		if (area != null)
			llenarCampos(area);
	}

	/* LLena los campos del formulario dado un area */
	private void llenarCampos(Area area) {
		txtNombreArea.setValue(area.getNombre());
		id = area.getIdArea();
	}

}
