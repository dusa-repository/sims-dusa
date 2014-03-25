package controlador.maestros;

import java.io.IOException;
import java.util.List;

import modelo.maestros.Ciudad;
import modelo.maestros.Consultorio;
import modelo.maestros.Diagnostico;
import modelo.maestros.Empresa;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;

public class CConsultorio extends CGenerico {

	private static final long serialVersionUID = -2116324318437565970L;
	@Wire
	private Div divConsultorio;
	@Wire
	private Div botoneraConsultorio;
	@Wire
	private Div catalogoConsultorio;
	@Wire
	private Textbox txtNombreConsultorio;
	@Wire
	private Textbox txtTelefono1Consultorio;
	@Wire
	private Textbox txtTelefono2Consultorio;
	@Wire
	private Textbox txtDireccionConsultorio;
	@Wire
	private Textbox txtDescripcionConsultorio;
	@Wire
	private Textbox txtCorreoConsultorio;
	@Wire
	private Combobox cmbCiudadConsultorio;
	@Wire
	private Button btnBuscarConsultorio;
	long id = 0;
	Catalogo<Consultorio> catalogo;

	@Override
	public void inicializar() throws IOException {
		// TODO Auto-generated method stub
		comboCiudad();
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divConsultorio, "Consultorio");
			}

			@Override
			public void limpiar() {
				txtDireccionConsultorio.setValue("");
				txtDescripcionConsultorio.setValue("");
				txtNombreConsultorio.setValue("");
				txtCorreoConsultorio.setValue("");
				txtTelefono1Consultorio.setValue("");
				txtTelefono2Consultorio.setValue("");
				cmbCiudadConsultorio.setValue("");
				cmbCiudadConsultorio.setPlaceholder("Seleccione una Ciudad");
				id = 0;
			}

			@Override
			public void guardar() {
				if (validar()) {
					String nombre, descripcion, direccion, telefono1, telefono2, correo;
					nombre = txtNombreConsultorio.getValue();
					descripcion = txtDescripcionConsultorio.getValue();
					direccion = txtDireccionConsultorio.getValue();
					telefono1 = txtTelefono1Consultorio.getValue();
					telefono2 = txtTelefono2Consultorio.getValue();
					correo = txtCorreoConsultorio.getValue();
					Ciudad ciudad = servicioCiudad.buscar(Long
							.parseLong(cmbCiudadConsultorio.getSelectedItem()
									.getContext()));
					Consultorio consultorio = new Consultorio(id, correo,
							descripcion, direccion, fechaHora, horaAuditoria,
							nombre, telefono1, telefono2,
							nombreUsuarioSesion(), ciudad);
					servicioConsultorio.guardar(consultorio);
					limpiar();
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
				}
			}

			@Override
			public void eliminar() {
				if (id != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar el Consultorio?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Consultorio consultorio = servicioConsultorio
												.buscar(id);
										servicioConsultorio
												.eliminar(consultorio);
										limpiar();
										Messagebox
												.show("Registro Eliminado Exitosamente",
														"Informacion",
														Messagebox.OK,
														Messagebox.INFORMATION);
									}
								}
							});
				} else {
					Messagebox.show("No ha Seleccionado Ningun Registro",
							"Alerta", Messagebox.OK, Messagebox.EXCLAMATION);
				}
			}
		};
		botoneraConsultorio.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtDireccionConsultorio.getText().compareTo("") == 0
				|| txtNombreConsultorio.getText().compareTo("") == 0
				|| txtDescripcionConsultorio.getText().compareTo("") == 0
				|| txtCorreoConsultorio.getText().compareTo("") == 0
				|| txtTelefono1Consultorio.getText().compareTo("") == 0
				|| txtTelefono2Consultorio.getText().compareTo("") == 0
				|| cmbCiudadConsultorio.getText().compareTo("") == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else
			return true;
	}

	/* Llena el combo de Ciudades */
	private void comboCiudad() {
		List<Ciudad> ciudades = servicioCiudad.buscarTodas();
		cmbCiudadConsultorio.setModel(new ListModelList<Ciudad>(ciudades));
	}

	/* Muestra el catalogo de los consultorios */
	@Listen("onClick = #btnBuscarConsultorio")
	public void mostrarCatalogo() {
		final List<Consultorio> consultorios = servicioConsultorio
				.buscarTodas();
		catalogo = new Catalogo<Consultorio>(catalogoConsultorio,
				"Catalogo de Consultorios", consultorios, "Nombre",
				"Direccion", "Descripcion", "Correo", "Telefono", "Ciudad") {

			@Override
			protected List<Consultorio> buscar(String valor, String combo) {

				if (combo.equals("Correo"))
					return servicioConsultorio.filtroCorreo(valor);
				else {
					if (combo.equals("Nombre"))
						return servicioConsultorio.filtroNombre(valor);
					else {
						if (combo.equals("Direccion"))
							return servicioConsultorio.filtroDireccion(valor);
						else {
							if (combo.equals("Telefono"))
								return servicioConsultorio
										.filtroTelefono(valor);
							else {
								if (combo.equals("Ciudad"))
									return servicioConsultorio
											.filtroCiudad(valor);
								else {
									if (combo.equals("Descripcion"))
										return servicioConsultorio
												.filtroDescripcion(valor);
									else
										return consultorios;
								}
							}
						}
					}
				}
			}

			@Override
			protected String[] crearRegistros(Consultorio objeto) {
				String[] registros = new String[6];
				registros[0] = objeto.getNombre();
				registros[1] = objeto.getDireccion();
				registros[2] = objeto.getDescripcion();
				registros[3] = objeto.getCorreoElectronico();
				registros[4] = objeto.getTelefono1();
				registros[5] = objeto.getCiudad().getNombre();
				return registros;
			}

		};
		catalogo.setParent(catalogoConsultorio);
		catalogo.doModal();
	}
	
	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoConsultorio")
	public void seleccinar() {
		Consultorio consultorio = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(consultorio);
		catalogo.setParent(null);
	}

	/* Busca si existe un consultorio con el mismo nombre escrito */
	@Listen("onChange = #txtNombreConsultorio")
	public void buscarPorNombre() {
		Consultorio consultorio = servicioConsultorio.buscarPorNombre(txtNombreConsultorio.getValue());
		if (consultorio != null)
			llenarCampos(consultorio);
	}

	/* LLena los campos del formulario dado un consultorio */
	private void llenarCampos(Consultorio consultorio) {
		txtCorreoConsultorio.setValue(consultorio.getCorreoElectronico());
		txtDescripcionConsultorio.setValue(consultorio.getDescripcion());
		txtNombreConsultorio.setValue(consultorio.getNombre());
		txtDireccionConsultorio.setValue(consultorio.getDireccion());
		txtTelefono1Consultorio.setValue(consultorio.getTelefono1());
		txtTelefono2Consultorio.setValue(consultorio.getTelefono2());
		cmbCiudadConsultorio.setValue(consultorio.getCiudad().getNombre());
		id = consultorio.getIdConsultorio();
	}

}
