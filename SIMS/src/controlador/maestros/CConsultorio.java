package controlador.maestros;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import modelo.maestros.Ciudad;
import modelo.maestros.Consultorio;
import modelo.maestros.Diagnostico;
import modelo.maestros.Empresa;
import modelo.seguridad.Arbol;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;

import arbol.CArbol;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Validador;

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
	@Wire
	private Combobox cmbEmpresa;

	private CArbol cArbol = new CArbol();
	long id = 0;
	Catalogo<Consultorio> catalogo;

	@Override
	public void inicializar() throws IOException {
		contenido = (Include) divConsultorio.getParent();
		Tabbox tabox = (Tabbox) divConsultorio.getParent().getParent().getParent().getParent();
		tabBox = tabox;
		tab = (Tab) tabox.getTabs().getLastChild();
		HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (map != null) {
			if (map.get("tabsGenerales") != null) {
				tabs = (List<Tab>) map.get("tabsGenerales");
				System.out.println(tabs.size());
				map.clear();
				map = null;
			}
		}
		llenarComboEmpresa();
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divConsultorio, "Consultorio", tabs);
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
				cmbEmpresa.setValue("");
				cmbEmpresa.setPlaceholder("Seleccione una Empresa");
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
					Empresa empresa = servicioEmpresa.buscar(Long
							.parseLong(cmbEmpresa.getSelectedItem()
									.getContext()));

					Consultorio consultorio = new Consultorio(id, correo,
							descripcion, direccion, fechaHora, horaAuditoria,
							nombre, telefono1, telefono2,
							nombreUsuarioSesion(), ciudad, empresa);
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
				|| cmbCiudadConsultorio.getText().compareTo("") == 0
				|| cmbEmpresa.getText().compareTo("") == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else {
			if (!Validador.validarTelefono(txtTelefono1Consultorio.getValue())) {
				Messagebox.show("Primer Telefono Invalido", "Informacion",
						Messagebox.OK, Messagebox.INFORMATION);
				return false;
			} else {
				if (!Validador.validarTelefono(txtTelefono2Consultorio
						.getValue())) {
					Messagebox.show("Segundo Telefono Invalido", "Informacion",
							Messagebox.OK, Messagebox.INFORMATION);
					return false;
				} else {
					if (!Validador.validarCorreo(txtCorreoConsultorio
							.getValue())) {
						Messagebox.show("Correo Invalido", "Informacion",
								Messagebox.OK, Messagebox.INFORMATION);
						return false;
					} else
						return true;
				}
			}
		}
	}

	/* Llena el combo de Empresas cada vez que se abre */
	@Listen("onOpen = #cmbEmpresa")
	public void llenarComboEmpresa() {
		List<Empresa> empresas = servicioEmpresa.buscarTodas();
		cmbEmpresa.setModel(new ListModelList<Empresa>(empresas));
	}

	/* Valida el numero telefonico */
	@Listen("onChange = #txtTelefono1Consultorio")
	public void validarTelefono() {
		if (!Validador.validarTelefono(txtTelefono1Consultorio.getValue())) {
			Messagebox.show("Telefono Invalido", "Informacion", Messagebox.OK,
					Messagebox.INFORMATION);
		}
	}

	/* Valida el numero telefonico */
	@Listen("onChange = #txtTelefono2Consultorio")
	public void validarTelefono1() {
		if (!Validador.validarTelefono(txtTelefono2Consultorio.getValue())) {
			Messagebox.show("Telefono Invalido", "Informacion", Messagebox.OK,
					Messagebox.INFORMATION);
		}
	}

	/* Valida el correo electronico */
	@Listen("onChange = #txtCorreoConsultorio")
	public void validarCorreo() {
		if (!Validador.validarCorreo(txtCorreoConsultorio.getValue())) {
			Messagebox.show("Correo Invalido", "Informacion", Messagebox.OK,
					Messagebox.INFORMATION);
		}
	}

	/* Llena el combo de Ciudades cada vez que se abre */
	@Listen("onOpen = #cmbCiudadConsultorio")
	public void llenarComboCiudad() {
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

				switch (combo) {
				case "Correo":
					return servicioConsultorio.filtroCorreo(valor);
				case "Nombre":
					return servicioConsultorio.filtroNombre(valor);
				case "Direccion":
					return servicioConsultorio.filtroDireccion(valor);
				case "Telefono":
					return servicioConsultorio.filtroTelefono(valor);
				case "Ciudad":
					return servicioConsultorio.filtroCiudad(valor);
				case "Descripcion":
					return servicioConsultorio.filtroCiudad(valor);
				default:
					return consultorios;
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
		Consultorio consultorio = servicioConsultorio
				.buscarPorNombre(txtNombreConsultorio.getValue());
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
		cmbEmpresa.setValue(consultorio.getEmpresa().getNombre());
		id = consultorio.getIdConsultorio();
	}

	/* Abre la vista de Ciudad*/
	@Listen("onClick = #btnAbrirCiudad")
	public void abrirCiudad(){		
		Arbol arbolItem = servicioArbol.buscarPorNombreArbol("Ciudad");
		cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);	
	}

	/* Abre la vista de Empresa*/
	@Listen("onClick = #btnAbrirEmpresa")
	public void abrirEmpresa(){		
		Arbol arbolItem = servicioArbol.buscarPorNombreArbol("Empresa");
		cArbol.abrirVentanas(arbolItem, tabBox, contenido, tab, tabs);	
	}
}
