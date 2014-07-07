package controlador.maestros;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import modelo.maestros.Cargo;
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

public class CCargo extends CGenerico {

	@Wire
	private Div botoneraCargo;
	@Wire
	private Textbox txtNombreCargo;
	@Wire
	private Button btnBuscarCargo;
	@Wire
	private Div catalogoCargo;
	@Wire
	private Div divCargo;

	private long id = 0;
	Catalogo<Cargo> catalogo;

	@Override
	public void inicializar() throws IOException {

		txtNombreCargo.setFocus(true);

		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divCargo, "Cargo");

			}

			@Override
			public void limpiar() {
				txtNombreCargo.setValue("");
				id = 0;
				txtNombreCargo.setFocus(true);

			}

			@Override
			public void guardar() {
				if (validar()) {
					String nombre = txtNombreCargo.getValue();
					Cargo cargo = new Cargo(id, nombre, fechaHora,
							horaAuditoria, nombreUsuarioSesion());
					servicioCargo.guardar(cargo);
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
					limpiar();
				}

			}

			@Override
			public void eliminar() {
				if (id != 0 && txtNombreCargo.getText().compareTo("") != 0) {
					Messagebox.show("�Esta Seguro de Eliminar el Cargo?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Cargo cargo = servicioCargo.buscar(id);
										if (true) {
											Messagebox
													.show("No se Puede Eliminar el Registro, Esta siendo Utilizado",
															"Informacion",
															Messagebox.OK,
															Messagebox.INFORMATION);
										} else {
											servicioCargo.eliminar(cargo);
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

		botoneraCargo.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtNombreCargo.getText().compareTo("") == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else
			return true;
	}

	/* Muestra el catalogo de los areas */
	@Listen("onClick = #btnBuscarCargo")
	public void mostrarCatalogo() {
		final List<Cargo> cargos = servicioCargo.buscarTodos();
		catalogo = new Catalogo<Cargo>(catalogoCargo, "Catalogo de Areas",
				cargos, "Nombre") {

			@Override
			protected List<Cargo> buscar(String valor, String combo) {
				switch (combo) {
				case "Nombre":
					return servicioCargo.filtroNombre(valor);
				default:
					return cargos;
				}
			}

			@Override
			protected String[] crearRegistros(Cargo cargo) {
				String[] registros = new String[1];
				registros[0] = cargo.getNombre();
				return registros;
			}
		};
		catalogo.setParent(catalogoCargo);
		catalogo.doModal();
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoCargo")
	public void seleccinar() {
		Cargo cargo = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(cargo);
		catalogo.setParent(null);
	}

	/* Busca si existe un area con el mismo nombre escrito */
	@Listen("onChange = #txtNombreCargo")
	public void buscarPorNombre() {
		Cargo cargo = servicioCargo.buscarPorNombre(txtNombreCargo.getValue());
		if (cargo != null)
			llenarCampos(cargo);
	}

	/* LLena los campos del formulario dado un area */
	private void llenarCampos(Cargo cargo) {
		txtNombreCargo.setValue(cargo.getNombre());
		id = cargo.getIdCargo();
	}

}