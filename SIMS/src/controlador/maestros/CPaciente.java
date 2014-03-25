package controlador.maestros;

import java.io.IOException;
import java.util.List;

import modelo.maestros.Cita;
import modelo.maestros.Ciudad;
import modelo.maestros.Empresa;
import modelo.maestros.Paciente;

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

public class CPaciente extends CGenerico {

	private static final long serialVersionUID = -8967604751368729529L;

	@Wire
	private Div divPaciente;
	@Wire
	private Div botoneraPaciente;
	@Wire
	private Div catalogoPaciente;
	@Wire
	private Textbox txtNombre1Paciente;
	@Wire
	private Textbox txtApellido1Paciente;
	@Wire
	private Textbox txtCedulaPaciente;
	@Wire
	private Combobox cmbEmpresa;
	@Wire
	private Button btnBuscarPaciente;

	long id = 0;
	Catalogo<Paciente> catalogo;

	@Override
	public void inicializar() throws IOException {

		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divPaciente, "Paciente");

			}

			@Override
			public void limpiar() {
				txtNombre1Paciente.setValue("");
				txtCedulaPaciente.setValue("");
				txtApellido1Paciente.setValue("");
				cmbEmpresa.setValue("");
				cmbEmpresa.setPlaceholder("Seleccione una Empresa");
				id = 0;

			}

			@Override
			public void guardar() {
				if (validar()) {
					String nombre1, apellido1, cedula;
					nombre1 = txtNombre1Paciente.getValue();
					apellido1 = txtApellido1Paciente.getValue();
					cedula = txtCedulaPaciente.getValue();
					Empresa empresa = servicioEmpresa.buscar(Long
							.parseLong(cmbEmpresa.getSelectedItem()
									.getContext()));

					Paciente paciente = new Paciente(id, cedula, fechaHora,
							horaAuditoria, empresa, apellido1, nombre1,
							nombreUsuarioSesion());

					servicioPaciente.guardar(paciente);
					limpiar();
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
				}

			}

			@Override
			public void eliminar() {
				if (id != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar el Paciente?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Paciente paciente = servicioPaciente
												.buscar(id);
										List<Cita> citas = servicioCita
												.buscarPorPaciente(paciente);
										if (!citas.isEmpty()) {
											Messagebox
													.show("No se Puede Eliminar el Registro, Esta siendo Utilizado",
															"Informacion",
															Messagebox.OK,
															Messagebox.INFORMATION);
										} else {
											servicioPaciente.eliminar(paciente);
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
		botoneraPaciente.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtApellido1Paciente.getText().compareTo("") == 0
				|| txtNombre1Paciente.getText().compareTo("") == 0
				|| txtCedulaPaciente.getText().compareTo("") == 0
				|| cmbEmpresa.getText().compareTo("") == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else
			return true;
	}

	/* Muestra el catalogo de los Pacientes */
	@Listen("onClick = #btnBuscarPaciente")
	public void mostrarCatalogo() {
		List<Paciente> pacientes = servicioPaciente.buscarTodos();
		catalogo = new Catalogo<Paciente>(catalogoPaciente,
				"Catalogo de Pacientes", pacientes, "Cedula", "Nombre",
				"Apellido", "Empresa") {

			@Override
			protected List<Paciente> buscar(String valor, String combo) {

				if (combo.equals("Nombre"))
					return servicioPaciente.filtroNombre1(valor);
				else {
					if (combo.equals("Cedula"))
						return servicioPaciente.filtroCedula(valor);
					else {
						if (combo.equals("Apellido"))
							return servicioPaciente.filtroApellido1(valor);
						else {
							if (combo.equals("Empresa"))
								return servicioPaciente.filtroEmpresa(valor);
							else
								return servicioPaciente.buscarTodos();
						}
					}
				}
			}

			@Override
			protected String[] crearRegistros(Paciente objeto) {
				String[] registros = new String[4];
				registros[0] = objeto.getCedula();
				registros[1] = objeto.getPrimerNombre();
				registros[2] = objeto.getPrimerApellido();
				registros[3] = objeto.getEmpresa().getNombre();
				return registros;
			}

		};
		catalogo.setParent(catalogoPaciente);
		catalogo.doModal();
	}

	/* Llena el combo de Empresas cada vez que se abre */
	@Listen("onOpen = #cmbEmpresa")
	public void llenarComboEmpresa(){
		List<Empresa> empresas = servicioEmpresa.buscarTodas();
		cmbEmpresa.setModel(new ListModelList<Empresa>(empresas));
	}
	
	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoPaciente")
	public void seleccinar() {
		Paciente paciente = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(paciente);
		catalogo.setParent(null);
	}

	/* Busca si existe un diagnostico con el mismo codigo escrito */
	@Listen("onChange = #txtCedulaPaciente")
	public void buscarPorCedula() {
		Paciente paciente = servicioPaciente.buscarPorCedula(txtCedulaPaciente
				.getValue());
		if (paciente != null)
			llenarCampos(paciente);
	}

	/* LLena los campos del formulario dado un paciente */
	private void llenarCampos(Paciente paciente) {
		txtCedulaPaciente.setValue(paciente.getCedula());
		txtNombre1Paciente.setValue(paciente.getPrimerNombre());
		txtApellido1Paciente.setValue(paciente.getPrimerApellido());
		cmbEmpresa.setValue(paciente.getEmpresa().getNombre());
		id = paciente.getIdPaciente();
	}

}
