package controlador.maestros;

import java.io.IOException;
import java.util.List;

import modelo.maestros.Ciudad;
import modelo.maestros.Consultorio;
import modelo.maestros.Empresa;
import modelo.maestros.Paciente;
import modelo.maestros.ServicioExterno;
import modelo.seguridad.Arbol;
import modelo.seguridad.Usuario;

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

import sun.usagetracker.UsageTrackerClient;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Validador;

public class CServicioExterno extends CGenerico {

	private static final long serialVersionUID = -6178756189105805846L;
	@Wire
	private Div divServicioExterno;
	@Wire
	private Div botoneraServicioExterno;
	@Wire
	private Div catalogoServicioExterno;
	@Wire
	private Textbox txtNombreServicioExterno;
	@Wire
	private Textbox txtTelefonoServicioExterno;
	@Wire
	private Textbox txtDireccionServicioExterno;
	@Wire
	private Button btnBuscarServicioExterno;
	@Wire
	private Combobox cmbCiudadServicioExterno;

	private CArbol cArbol = new CArbol();
	long id = 0;
	Catalogo<ServicioExterno> catalogo;

	@Override
	public void inicializar() throws IOException {
		
		llenarComboCiudad();
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divServicioExterno, "Servicios Externos");
			}

			@Override
			public void limpiar() {
				txtDireccionServicioExterno.setValue("");
				txtNombreServicioExterno.setValue("");
				txtTelefonoServicioExterno.setValue("");
				cmbCiudadServicioExterno.setValue("");
				cmbCiudadServicioExterno
						.setPlaceholder("Seleccione una Ciudad");
				id = 0;
			}

			@Override
			public void guardar() {
				if (validar()) {
					String nombre, direccion, telefono;
					nombre = txtNombreServicioExterno.getValue();
					direccion = txtDireccionServicioExterno.getValue();
					telefono = txtTelefonoServicioExterno.getValue();
					Ciudad ciudad = servicioCiudad.buscar(Long
							.parseLong(cmbCiudadServicioExterno
									.getSelectedItem().getContext()));
					ServicioExterno servicioExterno = new ServicioExterno(id,
							direccion, nombre, telefono, fechaHora,
							horaAuditoria, nombreUsuarioSesion(), ciudad);
					servicioServicioExterno.guardar(servicioExterno);
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
							"¿Esta Seguro de Eliminar el Servicio Externo?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										ServicioExterno servicioExterno = servicioServicioExterno
												.buscar(id);
										servicioServicioExterno
												.eliminar(servicioExterno);
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
		botoneraServicioExterno.appendChild(botonera);
	}

	/* Llena el combo de Ciudades cada vez que se abre */
	@Listen("onOpen = #cmbCiudadServicioExterno")
	public void llenarComboCiudad() {
		List<Ciudad> ciudades = servicioCiudad.buscarTodas();
		cmbCiudadServicioExterno.setModel(new ListModelList<Ciudad>(ciudades));
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtDireccionServicioExterno.getText().compareTo("") == 0
				|| txtNombreServicioExterno.getText().compareTo("") == 0
				|| txtTelefonoServicioExterno.getText().compareTo("") == 0
				|| cmbCiudadServicioExterno.getText().compareTo("") == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else {
			if (!Validador.validarTelefono(txtTelefonoServicioExterno
					.getValue())) {
				Messagebox.show("Primer Telefono Invalido", "Informacion",
						Messagebox.OK, Messagebox.INFORMATION);
				return false;
			} else
				return true;
		}
	}

	/* Muestra el catalogo de los servicios externos */
	@Listen("onClick = #btnBuscarServicioExterno")
	public void mostrarCatalogo() {
		final List<ServicioExterno> serviciosExternos = servicioServicioExterno
				.buscarTodas();
		catalogo = new Catalogo<ServicioExterno>(catalogoServicioExterno,
				"Catalogo de Servicios Externos", serviciosExternos, "Nombre",
				"Direccion", "Telefono", "Ciudad") {

			@Override
			protected List<ServicioExterno> buscar(String valor, String combo) {

				switch (combo) {
				case "Nombre":
					return servicioServicioExterno.filtroNombre(valor);
				case "Direccion":
					return servicioServicioExterno.filtroDireccion(valor);
				case "Telefono":
					return servicioServicioExterno.filtroTelefono(valor);
				case "Ciudad":
					return servicioServicioExterno.filtroCiudad(valor);
				default:
					return serviciosExternos;
				}
			}

			@Override
			protected String[] crearRegistros(ServicioExterno objeto) {
				String[] registros = new String[4];
				registros[0] = objeto.getNombre();
				registros[1] = objeto.getDireccion();
				registros[2] = objeto.getTelefono();
				registros[3] = objeto.getCiudad().getNombre();
				return registros;
			}

		};
		catalogo.setParent(catalogoServicioExterno);
		catalogo.doModal();
	}

	/* Valida el numero telefonico */
	@Listen("onChange = #txtTelefonoServicioExterno")
	public void validarTelefono() {
		if (!Validador.validarTelefono(txtTelefonoServicioExterno.getValue())) {
			Messagebox.show("Telefono Invalido", "Informacion", Messagebox.OK,
					Messagebox.INFORMATION);
		}
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoServicioExterno")
	public void seleccinar() {
		ServicioExterno servicioExterno = catalogo
				.objetoSeleccionadoDelCatalogo();
		llenarCampos(servicioExterno);
		catalogo.setParent(null);
	}

	/* Busca si existe un servicio externo con el mismo nombre escrito */
	@Listen("onChange = #txtNombreServicioExterno")
	public void buscarPorNombre() {
		ServicioExterno servicioExterno = servicioServicioExterno
				.buscarPorNombre(txtNombreServicioExterno.getValue());
		if (servicioExterno != null)
			llenarCampos(servicioExterno);
	}

	/* LLena los campos del formulario dado un servicio externo */
	private void llenarCampos(ServicioExterno servicioExterno) {
		txtDireccionServicioExterno.setValue(servicioExterno.getDireccion());
		txtNombreServicioExterno.setValue(servicioExterno.getNombre());
		txtTelefonoServicioExterno.setValue(servicioExterno.getTelefono());
		cmbCiudadServicioExterno.setValue(servicioExterno.getCiudad()
				.getNombre());
		id = servicioExterno.getIdServicioExterno();
	}

	/* Abre la vista de Ciudad*/
	@Listen("onClick = #btnAbrirCiudad")
	public void abrirCiudad(){		
		Arbol arbolItem = servicioArbol.buscarPorNombreArbol("Ciudad");
		cArbol.abrirVentanas(arbolItem);	
	}
}
