package controlador.maestros;

import java.io.IOException;
import java.util.List;

import modelo.maestros.Ciudad;
import modelo.maestros.Proveedor;
import modelo.seguridad.Arbol;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import arbol.CArbol;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Validador;

public class CProveedor extends CGenerico {

	private static final long serialVersionUID = -6178756189105805846L;
	@Wire
	private Div divProveedor;
	@Wire
	private Div botoneraProveedor;
	@Wire
	private Div catalogoProveedor;
	@Wire
	private Textbox txtNombreProveedor;
	@Wire
	private Textbox txtTelefonoProveedor;
	@Wire
	private Textbox txtDireccionProveedor;
	@Wire
	private Button btnBuscarProveedor;
	@Wire
	private Combobox cmbCiudadProveedor;

	private CArbol cArbol = new CArbol();
	long id = 0;
	Catalogo<Proveedor> catalogo;

	@Override
	public void inicializar() throws IOException {
		
		llenarComboCiudad();
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divProveedor, "Proveedor");
			}

			@Override
			public void limpiar() {
				txtDireccionProveedor.setValue("");
				txtNombreProveedor.setValue("");
				txtTelefonoProveedor.setValue("");
				cmbCiudadProveedor.setValue("");
				cmbCiudadProveedor
						.setPlaceholder("Seleccione una Ciudad");
				id = 0;
			}

			@Override
			public void guardar() {
				if (validar()) {
					String nombre, direccion, telefono;
					nombre = txtNombreProveedor.getValue();
					direccion = txtDireccionProveedor.getValue();
					telefono = txtTelefonoProveedor.getValue();
					Ciudad ciudad = servicioCiudad.buscar(Long
							.parseLong(cmbCiudadProveedor
									.getSelectedItem().getContext()));
					Proveedor proveedor = new Proveedor(id,
							direccion, nombre, telefono, fechaHora,
							horaAuditoria, nombreUsuarioSesion(), ciudad);
					servicioProveedor.guardar(proveedor);
					limpiar();
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
				}
			}

			@Override
			public void eliminar() {
				if (id != 0) {
					Messagebox.show(
							"¿Esta Seguro de Eliminar el Proveedor?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Proveedor proveedor = servicioProveedor
												.buscar(id);
										servicioProveedor
												.eliminar(proveedor);
										limpiar();
										Messagebox
												.show("Registro Eliminado Exitosamente",
														"Informacion",
														Messagebox.OK,
														Messagebox.INFORMATION);

									}
								}
							});
				} else
					Messagebox.show("No ha Seleccionado Ningun Registro",
							"Alerta", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		};
		botoneraProveedor.appendChild(botonera);
	}

	/* Llena el combo de Ciudades cada vez que se abre */
	@Listen("onOpen = #cmbCiudadProveedor")
	public void llenarComboCiudad() {
		List<Ciudad> ciudades = servicioCiudad.buscarTodas();
		cmbCiudadProveedor.setModel(new ListModelList<Ciudad>(ciudades));
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtDireccionProveedor.getText().compareTo("") == 0
				|| txtNombreProveedor.getText().compareTo("") == 0
				|| txtTelefonoProveedor.getText().compareTo("") == 0
				|| cmbCiudadProveedor.getText().compareTo("") == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else {
			if (!Validador.validarTelefono(txtTelefonoProveedor
					.getValue())) {
				Messagebox.show("Telefono Invalido", "Informacion",
						Messagebox.OK, Messagebox.INFORMATION);
				return false;
			} else
				return true;
		}
	}

	/* Muestra el catalogo de los servicios externos */
	@Listen("onClick = #btnBuscarProveedor")
	public void mostrarCatalogo() {
		final List<Proveedor> proveedores = servicioProveedor
				.buscarTodos();
		catalogo = new Catalogo<Proveedor>(catalogoProveedor,
				"Catalogo de Servicios Externos",proveedores, "Nombre",
				"Direccion", "Telefono", "Ciudad") {

			@Override
			protected List<Proveedor> buscar(String valor, String combo) {

				switch (combo) {
				case "Nombre":
					return servicioProveedor.filtroNombre(valor);
				case "Direccion":
					return servicioProveedor.filtroDireccion(valor);
				case "Telefono":
					return servicioProveedor.filtroTelefono(valor);
				case "Ciudad":
					return servicioProveedor.filtroCiudad(valor);
				default:
					return proveedores;
				}
			}

			@Override
			protected String[] crearRegistros(Proveedor objeto) {
				String[] registros = new String[4];
				registros[0] = objeto.getNombre();
				registros[1] = objeto.getDireccion();
				registros[2] = objeto.getTelefono();
				registros[3] = objeto.getCiudad().getNombre();
				return registros;
			}

		};
		catalogo.setParent(catalogoProveedor);
		catalogo.doModal();
	}

	/* Valida el numero telefonico */
	@Listen("onChange = #txtTelefonoProveedor")
	public void validarTelefono() {
		if (!Validador.validarTelefono(txtTelefonoProveedor.getValue())) {
			Messagebox.show("Telefono Invalido", "Informacion", Messagebox.OK,
					Messagebox.INFORMATION);
		}
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoProveedor")
	public void seleccinar() {
		Proveedor proveedor = catalogo
				.objetoSeleccionadoDelCatalogo();
		llenarCampos(proveedor);
		catalogo.setParent(null);
	}

	/* Busca si existe un servicio externo con el mismo nombre escrito */
	@Listen("onChange = #txtNombreProveedor")
	public void buscarPorNombre() {
		Proveedor proveedor = servicioProveedor
				.buscarPorNombre(txtNombreProveedor.getValue());
		if (proveedor != null)
			llenarCampos(proveedor);
	}

	/* LLena los campos del formulario dado un servicio externo */
	private void llenarCampos(Proveedor proveedor) {
		txtDireccionProveedor.setValue(proveedor.getDireccion());
		txtNombreProveedor.setValue(proveedor.getNombre());
		txtTelefonoProveedor.setValue(proveedor.getTelefono());
		cmbCiudadProveedor.setValue(proveedor.getCiudad()
				.getNombre());
		id = proveedor.getIdProveedor();
	}

	/* Abre la vista de Ciudad*/
	@Listen("onClick = #btnAbrirCiudad")
	public void abrirCiudad(){		
		Arbol arbolItem = servicioArbol.buscarPorNombreArbol("Ciudad");
		cArbol.abrirVentanas(arbolItem);	
	}
}



