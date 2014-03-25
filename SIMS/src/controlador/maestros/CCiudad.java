package controlador.maestros;

import java.io.IOException;
import java.util.List;

import modelo.maestros.Ciudad;
import modelo.maestros.Consultorio;
import modelo.maestros.Empresa;
import modelo.maestros.Estado;
import modelo.maestros.Medicina;
import modelo.maestros.Presentacion;

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

public class CCiudad extends CGenerico {

	private static final long serialVersionUID = 84966503677381446L;

	@Wire
	private Textbox txtNombreCiudad;
	@Wire
	private Div botoneraCiudad;
	@Wire
	private Div catalogoCiudad;
	@Wire
	private Div divCiudad;
	@Wire
	private Button btnBuscarCiudad;
	@Wire
	private Combobox cmbEstado;

	Catalogo<Ciudad> catalogo;
	long id = 0;

	@Override
	public void inicializar() throws IOException {

		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divCiudad, "Ciudad");
			}

			@Override
			public void limpiar() {
				txtNombreCiudad.setText("");
				cmbEstado.setValue("");
				cmbEstado.setPlaceholder("Seleccione un Estado");
				id = 0;
			}

			@Override
			public void guardar() {
				if (validar()) {
					String nombre = txtNombreCiudad.getValue();
					long idEstado = Long.valueOf(cmbEstado.getSelectedItem()
							.getContext());
					Estado estado = servicioEstado.buscar(idEstado);
					Ciudad ciudad = new Ciudad(id, fechaHora, horaAuditoria,
							nombre, nombreUsuarioSesion(), estado);
					servicioCiudad.guardar(ciudad);
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);

					limpiar();
				}

			}

			@Override
			public void eliminar() {
				if (id != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar la Ciudad?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Ciudad ciudad = servicioCiudad
												.buscar(id);
										List<Empresa> empresas = servicioEmpresa
												.buscarPorCiudad(ciudad);
										List<Consultorio> consultorios = servicioConsultorio
												.buscarPorCiudad(ciudad);
										if (!empresas.isEmpty()
												|| !consultorios.isEmpty()) {
											Messagebox
													.show("No se Puede Eliminar el Registro, Esta siendo Utilizado",
															"Informacion",
															Messagebox.OK,
															Messagebox.INFORMATION);
										} else {
											servicioCiudad.eliminar(ciudad);
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
		botoneraCiudad.appendChild(botonera);
	}

	/* Validaciones de pantalla para poder realizar el guardar */
	public boolean validar() {

		if (cmbEstado.getText().compareTo("") == 0
				|| txtNombreCiudad.getText().compareTo("") == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else
			return true;
	}

	/* Muestra un catalogo de ciudades */
	@Listen("onClick = #btnBuscarCiudad")
	public void mostrarCatalogo() throws IOException {
		List<Ciudad> ciudades = servicioCiudad.buscarTodas();
		catalogo = new Catalogo<Ciudad>(catalogoCiudad, "Catalogo de Ciudades",
				ciudades, "Nombre", "Estado") {

			@Override
			protected String[] crearRegistros(Ciudad ciudad) {
				String[] registros = new String[2];
				registros[0] = ciudad.getNombre();
				registros[1] = ciudad.getEstado().getNombre();
				return registros;
			}

			@Override
			protected List<Ciudad> buscar(String valor, String combo) {
				if (combo.equals("Nombre"))
					return servicioCiudad.filtroNombre(valor);
				else {
					if (combo.equals("Estado"))
						return servicioCiudad.filtroEstado(valor);
				}
				return servicioCiudad.buscarTodas();
			}
		};
		catalogo.setParent(catalogoCiudad);
		catalogo.doModal();
	}

	/* Busca si existe una ciudad con el mismo nombre escrito */
	@Listen("onChange = #txtNombreCiudad")
	public void buscarPorNombre() {
		Ciudad ciudad = servicioCiudad.buscarPorNombre(txtNombreCiudad
				.getValue());
		if (ciudad != null)
			llenarCampos(ciudad);
	}

	/* Llena el combo de estado cada vez que se abre */
	@Listen("onOpen = #cmbEstado")
	public void llenarCombo() {
			List<Estado> estados = servicioEstado.buscarTodos();
			cmbEstado.setModel(new ListModelList<Estado>(estados));
	}

	/*
	 * Selecciona una ciudad del catalogo y llena los campos con la informacion
	 */
	@Listen("onSeleccion = #catalogoCiudad")
	public void seleccion() {
		Ciudad ciudad = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(ciudad);
		catalogo.setParent(null);
	}

	/* LLena los campos del formulario dada una ciudad */
	public void llenarCampos(Ciudad ciudad) {
		txtNombreCiudad.setValue(ciudad.getNombre());
		cmbEstado.setValue(ciudad.getEstado().getNombre());
		id = ciudad.getIdCiudad();
	}
}
