package controlador.maestros;

import java.io.IOException;
import java.util.List;

import modelo.maestros.CategoriaDiagnostico;
import modelo.maestros.Ciudad;
import modelo.maestros.Consultorio;
import modelo.maestros.Diagnostico;
import modelo.maestros.Empresa;
import modelo.maestros.Laboratorio;
import modelo.maestros.Medicina;
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
import componentes.Validador;

public class CEmpresa extends CGenerico {

	private static final long serialVersionUID = -8397437400900885743L;
	@Wire
	private Div divEmpresa;
	@Wire
	private Div botoneraEmpresa;
	@Wire
	private Div catalogoEmpresa;
	@Wire
	private Textbox txtNombreEmpresa;
	@Wire
	private Textbox txtTelefono1Empresa;
	@Wire
	private Textbox txtTelefono2Empresa;
	@Wire
	private Textbox txtDireccionEmpresa;
	@Wire
	private Textbox txtRifEmpresa;
	@Wire
	private Combobox cmbCiudad;
	@Wire
	private Button btnBuscarEmpresa;
	long id = 0;
	Catalogo<Empresa> catalogo;

	@Override
	public void inicializar() throws IOException {


		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divEmpresa, "Empresa");
			}

			@Override
			public void limpiar() {
				txtDireccionEmpresa.setValue("");
				txtNombreEmpresa.setValue("");
				txtRifEmpresa.setValue("");
				txtTelefono1Empresa.setValue("");
				txtTelefono2Empresa.setValue("");
				cmbCiudad.setValue("");
				cmbCiudad.setPlaceholder("Seleccione una Ciudad");
				id = 0;
			}

			@Override
			public void guardar() {
				if (validar()) {
					String nombre, rif, direccion, telefono1, telefono2, correo;
					nombre = txtNombreEmpresa.getValue();
					rif = txtRifEmpresa.getValue();
					direccion = txtDireccionEmpresa.getValue();
					telefono1 = txtTelefono1Empresa.getValue();
					telefono2 = txtTelefono2Empresa.getValue();
					Ciudad ciudad = servicioCiudad
							.buscar(Long.parseLong(cmbCiudad.getSelectedItem()
									.getContext()));
					Empresa empresa = new Empresa(id, direccion, fechaHora,
							horaAuditoria, nombre, rif, telefono1, telefono2,
							nombreUsuarioSesion(), ciudad);
					servicioEmpresa.guardar(empresa);
					limpiar();
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
				}
			}

			@Override
			public void eliminar() {
				if (id != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar la Empresa?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Empresa empresa = servicioEmpresa
												.buscar(id);
										List<Paciente> pacientes = servicioPaciente
												.buscarPorEmpresa(empresa);
										List<Consultorio> consultorios = servicioConsultorio
												.buscarPorEmpresa(empresa);
										
										if (!pacientes.isEmpty() || !consultorios.isEmpty()) {
											Messagebox
													.show("No se Puede Eliminar el Registro, Esta siendo Utilizado",
															"Informacion",
															Messagebox.OK,
															Messagebox.INFORMATION);
										} else {
											servicioEmpresa.eliminar(empresa);
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
				} else
					Messagebox.show("No ha Seleccionado Ningun Registro",
							"Alerta", Messagebox.OK, Messagebox.EXCLAMATION);
			}
		};
		botoneraEmpresa.appendChild(botonera);
	}

	/* Llena el combo de Ciudades cada vez que se abre */
	@Listen("onOpen = #cmbCiudad")
	public void llenarComboCiudad(){
		List<Ciudad> ciudades = servicioCiudad.buscarTodas();
		cmbCiudad.setModel(new ListModelList<Ciudad>(ciudades));
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtDireccionEmpresa.getText().compareTo("") == 0
				|| txtNombreEmpresa.getText().compareTo("") == 0
				|| txtRifEmpresa.getText().compareTo("") == 0
				|| txtTelefono1Empresa.getText().compareTo("") == 0
				|| txtTelefono2Empresa.getText().compareTo("") == 0
				|| cmbCiudad.getText().compareTo("") == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else {
			if (!Validador.validarTelefono(txtTelefono1Empresa.getValue())) {
				Messagebox.show("Primer Telefono Invalido", "Informacion",
						Messagebox.OK, Messagebox.INFORMATION);
				return false;
			} else {
				if (!Validador.validarTelefono(txtTelefono2Empresa.getValue())) {
					Messagebox.show("Segundo Telefono Invalido", "Informacion",
							Messagebox.OK, Messagebox.INFORMATION);
					return false;
				} else
					return true;
			}
		}
	}

	/* Muestra el catalogo de las empresas */
	@Listen("onClick = #btnBuscarEmpresa")
	public void mostrarCatalogo() {
		final List<Empresa> empresas = servicioEmpresa.buscarTodas();
		catalogo = new Catalogo<Empresa>(catalogoEmpresa,
				"Catalogo de Empresas", empresas, "Rif", "Nombre", "Direccion",
				"Telefono", "Ciudad") {

			@Override
			protected List<Empresa> buscar(String valor, String combo) {

				if (combo.equals("Rif"))
					return servicioEmpresa.filtroRif(valor);
				else {
					if (combo.equals("Nombre"))
						return servicioEmpresa.filtroNombre(valor);
					else {
						if (combo.equals("Direccion"))
							return servicioEmpresa.filtroDireccion(valor);
						else {
							if (combo.equals("Telefono"))
								return servicioEmpresa.filtroTelefono(valor);
							else {
								if (combo.equals("Ciudad"))
									return servicioEmpresa.filtroCiudad(valor);
								else
									return empresas;
							}
						}
					}
				}
			}

			@Override
			protected String[] crearRegistros(Empresa objeto) {
				String[] registros = new String[5];
				registros[0] = objeto.getRif();
				registros[1] = objeto.getNombre();
				registros[2] = objeto.getDireccion();
				registros[3] = objeto.getTelefono1();
				registros[4] = objeto.getCiudad().getNombre();
				return registros;
			}

		};
		catalogo.setParent(catalogoEmpresa);
		catalogo.doModal();
	}

	/* Valida el numero telefonico */
	@Listen("onChange = #txtTelefono1Empresa")
	public void validarTelefono() {
		if (!Validador.validarTelefono(txtTelefono1Empresa.getValue())) {
			Messagebox.show("Telefono Invalido", "Informacion", Messagebox.OK,
					Messagebox.INFORMATION);
		}
	}

	/* Valida el numero telefonico */
	@Listen("onChange = #txtTelefono2Empresa")
	public void validarTelefono1() {
		if (!Validador.validarTelefono(txtTelefono2Empresa.getValue())) {
			Messagebox.show("Telefono Invalido", "Informacion", Messagebox.OK,
					Messagebox.INFORMATION);
		}
	}
	
	
	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoEmpresa")
	public void seleccinar() {
		Empresa empresa = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(empresa);
		catalogo.setParent(null);
	}

	/* Busca si existe una empresa con el mismo rif escrito */
	@Listen("onChange = #txtRifEmpresa")
	public void buscarPorNombre() {
		Empresa empresa = servicioEmpresa
				.buscarPorRif(txtRifEmpresa.getValue());
		if (empresa != null)
			llenarCampos(empresa);
	}

	/* LLena los campos del formulario dado una empresa */
	private void llenarCampos(Empresa empresa) {
		txtRifEmpresa.setValue(empresa.getRif());
		txtDireccionEmpresa.setValue(empresa.getDireccion());
		txtNombreEmpresa.setValue(empresa.getNombre());
		txtTelefono1Empresa.setValue(empresa.getTelefono1());
		txtTelefono2Empresa.setValue(empresa.getTelefono2());
		cmbCiudad.setValue(empresa.getCiudad().getNombre());
		id = empresa.getIdEmpresa();
	}

}
