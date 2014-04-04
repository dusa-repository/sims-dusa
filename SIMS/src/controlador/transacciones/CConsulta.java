package controlador.transacciones;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import modelo.maestros.Diagnostico;
import modelo.maestros.Examen;
import modelo.maestros.Medicina;
import modelo.maestros.MedicinaPresentacionUnidad;
import modelo.maestros.Paciente;
import modelo.maestros.PresentacionMedicina;

import componentes.Botonera;
import componentes.Catalogo;
import controlador.maestros.CGenerico;

public class CConsulta extends CGenerico {

	@Wire
	private Div botoneraConsulta;
	@Wire
	private Button btnBuscarPaciente;
	@Wire
	private Div divCatalogoPacientes;
	@Wire
	private Div divConsulta;
	@Wire
	private Textbox txtCedula;
	@Wire
	private Label lblNombre;
	@Wire
	private Label lblApellido;
	@Wire
	private Label lblEmpresa;
	@Wire
	private Label lblFicha;
	@Wire
	private Listbox ltbMedicinas;
	@Wire
	private Listbox ltbMedicinasAgregadas;
	@Wire
	private Listbox ltbExamenes;
	@Wire
	private Listbox ltbExamenesAgregados;
	@Wire
	private Listbox ltbDiagnosticos;
	@Wire
	private Listbox ltbDiagnosticosAgregados;

	List<Medicina> medicinasDisponibles = new ArrayList<Medicina>();
	List<Diagnostico> diagnosticosDisponibles = new ArrayList<Diagnostico>();
	List<Examen> examenesDisponibles = new ArrayList<Examen>();
	long idPaciente = 0;
	Catalogo<Paciente> catalogoPaciente;

	@Override
	public void inicializar() throws IOException {

		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divConsulta, "Consulta");

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

		botonera.getChildren().get(1).setVisible(false);
		botoneraConsulta.appendChild(botonera);

	}

	/* Llena la listas al iniciar con todo lo existente */
	private void llenarListas(Paciente paciente) {
		medicinasDisponibles = servicioMedicina.buscarTodas();
		ltbMedicinas
				.setModel(new ListModelList<Medicina>(medicinasDisponibles));
		diagnosticosDisponibles = servicioDiagnostico.buscarTodas();
		ltbDiagnosticos.setModel(new ListModelList<Diagnostico>(
				diagnosticosDisponibles));
		examenesDisponibles = servicioExamen.buscarTodos();
		ltbExamenes.setModel(new ListModelList<Examen>(examenesDisponibles));

	}

	/* Muestra un catalogo de Pacientes */
	@Listen("onClick = #btnBuscarPaciente")
	public void mostrarCatalogoPaciente() throws IOException {
		final List<Paciente> pacientes = servicioPaciente.buscarTodos();
		catalogoPaciente = new Catalogo<Paciente>(divCatalogoPacientes,
				"Catalogo de Pacientes", pacientes, "Cedula", "Nombre",
				"Apellido", "Empresa") {

			@Override
			protected List<Paciente> buscar(String valor, String combo) {

				switch (combo) {
				case "Nombre":
					return servicioPaciente.filtroNombre1(valor);
				case "Cedula":
					return servicioPaciente.filtroCedula(valor);
				case "Apellido":
					return servicioPaciente.filtroApellido1(valor);
				case "Empresa":
					return servicioPaciente.filtroEmpresa(valor);
				default:
					return pacientes;
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

	/* Permite la seleccion de un item del catalogo de pacientes */
	@Listen("onSeleccion = #divCatalogoPacientes")
	public void seleccionarPaciente() {
		Paciente paciente = catalogoPaciente.objetoSeleccionadoDelCatalogo();
		txtCedula.setValue(paciente.getCedula());
		lblNombre.setValue(paciente.getPrimerNombre() + " "
				+ paciente.getSegundoNombre());
		lblApellido.setValue(paciente.getPrimerApellido() + " "
				+ paciente.getSegundoApellido());
		lblEmpresa.setValue(paciente.getEmpresa().getNombre());
		txtCedula.setDisabled(true);
		idPaciente = Long.valueOf(paciente.getCedula());
		
		llenarListas(paciente); 
		
		catalogoPaciente.setParent(null);
	}
}
