package controlador.maestros;

import java.io.IOException;
import java.util.List;

import modelo.maestros.Paciente;
import modelo.seguridad.Usuario;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;

public class CCita extends CGenerico {

	private static final long serialVersionUID = 6527893397646342573L;
	@Wire
	private Div botoneraCita;
	@Wire
	private Div catalogoUsuarios;
	@Wire
	private Div divCatalogoPacientes;
	@Wire
	private Div divCita;
	@Wire
	private Button btnSiguientePestanna;
	@Wire
	private Button btnAnteriorPestanna;
	@Wire
	private Tab tabCita;
	@Wire
	private Tab tabConsultar;
	@Wire
	private Label lblCedulaDoctor;
	@Wire
	private Label lblNombreDoctor;
	@Wire
	private Label lblApellidoDoctor;
	@Wire
	private Button btnBuscarDoctor;
	@Wire
	private Button btnBuscarPaciente;
	@Wire
	private Label lblCedulaPaciente;
	@Wire
	private Label lblNombrePaciente;
	@Wire
	private Label lblApellidoPaciente;
	@Wire
	private Label lblEmpresaPaciente;
	@Wire
	private Textbox txtObservacion;
	@Wire
	private Combobox cmbMotivo;
	@Wire
	private Datebox dtbFechaCita;
	@Wire
	private Listbox ltbCitas;

	long id = 0;
	Catalogo<Usuario> catalogo;
	Catalogo<Paciente> catalogoPaciente;
	
	@Override
	public void inicializar() throws IOException {

		Botonera botonera = new Botonera() {
			
			@Override
			public void salir() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void limpiar() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void guardar() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void eliminar() {
				// TODO Auto-generated method stub
				
			}
		};
		botoneraCita.appendChild(botonera);
		
	}
	
	/* Muestra un catalogo de Usuarios */
	@Listen("onClick = #btnBuscarDoctor")
	public void mostrarCatalogo() throws IOException {
		List<Usuario> usuarios = servicioUsuario.buscarTodos();
		catalogo = new Catalogo<Usuario>(catalogoUsuarios,
				"Catalogo de Doctores", usuarios, "Cedula", "Ficha", "Nombre",
				"Especialidad") {

			@Override
			protected List<Usuario> buscar(String valor,String combo) {
				if(combo.equals("Cedula"))
				return servicioUsuario.filtroCedula(valor);
				else{
					if(combo.equals("Ficha"))
					return servicioUsuario.filtroFicha(valor);
					else{
						if(combo.equals("Nombre"))
							return servicioUsuario.filtroNombre(valor);
						else{
							if(combo.equals("Especialidad"))
								return servicioUsuario.filtroEspecialidad(valor);
								else
									return servicioUsuario.buscarTodos();		
						}						
				}
			}
			}

			@Override
			protected String[] crearRegistros(Usuario objeto) {
				String[] registros = new String[4];
				registros[0] = objeto.getCedula();
				registros[1] = objeto.getFicha();
				registros[2] = objeto.getNombre();
				registros[3] = objeto.getEspecialidad().getDescripcion();
				return registros;
			}

		};
		catalogo.setParent(catalogoUsuarios);
		catalogo.doModal();
	}
	
	/* Muestra un catalogo de Pacientes */
	@Listen("onClick = #btnBuscarPaciente")
	public void mostrarCatalogoPaciente() throws IOException {
		List<Paciente> pacientes = servicioPaciente.buscarTodos();
		catalogoPaciente = new Catalogo<Paciente>(divCatalogoPacientes,
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
		catalogoPaciente.setParent(divCatalogoPacientes);
		catalogoPaciente.doModal();
	}
	/* Abre la pestanna de consultar citas */
	@Listen("onClick = #btnSiguientePestanna")
	public void siguientePestanna() {
		tabConsultar.setSelected(true);
	}

	/* Abre la pestanna de crear cita */
	@Listen("onClick = #btnAnteriorPestanna")
	public void anteriorPestanna() {
		tabCita.setSelected(true);
	}
}
