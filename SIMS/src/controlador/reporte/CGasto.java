package controlador.reporte;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import modelo.maestros.Paciente;
import modelo.sha.Area;
import modelo.transacciones.ConsultaDiagnostico;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Tab;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;
import controlador.maestros.CGenerico;

public class CGasto extends CGenerico {

	@Wire
	private Datebox dtbDesde;
	@Wire
	private Datebox dtbHasta;
	@Wire
	private Label lblPaciente;
	@Wire
	private Hbox boxParentesco;
	@Wire
	private Combobox cmbParentescoFamiliar;
	@Wire
	private Combobox cmbTipo;
	@Wire
	private Div divGasto;
	@Wire
	private Div botoneraGasto;
	@Wire
	private Div divCatalogoPaciente;
	Catalogo<Paciente> catalogo;
	String nombre;
	int tipo;

	@Override
	public void inicializar() throws IOException {

		HashMap<String, Object> mapa = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (mapa != null) {
			if (mapa.get("tabsGenerales") != null) {
				tabs = (List<Tab>) mapa.get("tabsGenerales");
				nombre = (String) mapa.get("nombre");
				mapa.clear();
				mapa = null;
			}
		}
		switch (nombre) {
		case "Gastos por Familiares":
			tipo = 1;
			break;
		case "Gastos por Trabajador":
			boxParentesco.setVisible(true);
			cmbParentescoFamiliar.setVisible(true);
			tipo = 2;
			break;
		}
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divGasto, nombre, tabs);
			}

			@Override
			public void limpiar() {
				dtbDesde.setValue(fecha);
				dtbHasta.setValue(fecha);
				cmbParentescoFamiliar.setValue("TODOS");
				lblPaciente.setValue("");
			}

			@Override
			public void guardar() {
				if (validar()) {
					Date desde = dtbDesde.getValue();
					Date hasta = dtbHasta.getValue();
					DateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
					String fecha1 = fecha.format(desde);
					String fecha2 = fecha.format(hasta);
					String paciente = lblPaciente.getValue();
					String parentesco = cmbParentescoFamiliar.getValue();
					String tipoReporte = cmbTipo.getValue();
					List<ConsultaDiagnostico> consultas = new ArrayList<ConsultaDiagnostico>();
					switch (tipo) {
					// Reporte 1
					case 1:
						if (parentesco.equals("TODOS")) {
							if (paciente.equals("TODOS"))
								System.out.println("TODOS TODOS");
							else
								System.out.println("TODOS y un paciente");
						} else {
							if (paciente.equals("TODOS"))
								System.out
										.println("un parentesco y todos los paciente");
							else
								System.out
										.println("un parentesco y un paciente");
						}
						if (!consultas.isEmpty())
							Clients.evalJavaScript("window.open('"
									+ damePath()
									+ "Reportero?valor=28&valor6="
									+ fecha1
									+ "&valor7="
									+ fecha2
									+ "&valor8="
									+ parentesco
									+ "&valor9="
									+ paciente
									+ "&valor20="
									+ tipoReporte
									+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
						else
							msj.mensajeAlerta(Mensaje.noHayRegistros);
						break;
					// Reporte 2
					case 2:
						if (paciente.equals("todos"))
							System.out.println("todos");
						else
							System.out.println("no todos");
						if (!consultas.isEmpty())
							Clients.evalJavaScript("window.open('"
									+ damePath()
									+ "Reportero?valor=29&valor6="
									+ fecha1
									+ "&valor7="
									+ fecha2
									+ "&valor9="
									+ paciente
									+ "&valor20="
									+ tipoReporte
									+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
						else
							msj.mensajeAlerta(Mensaje.noHayRegistros);
						break;
					}
				}
			}

			@Override
			public void eliminar() {
				// TODO Auto-generated method stub

			}
		};
		Button guardar = (Button) botonera.getChildren().get(0);
		guardar.setLabel("Reporte");
		guardar.setSrc("/public/imagenes/botones/reporte.png");
		botonera.getChildren().get(1).setVisible(false);
		botoneraGasto.appendChild(botonera);
	}

	protected boolean validar() {
		if ((tipo == 1 && (lblPaciente.getValue().compareTo("") == 0 || cmbParentescoFamiliar
				.getValue().compareTo("") == 0))) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else {
			if (tipo == 2 && lblPaciente.getValue().compareTo("") == 0) {
				msj.mensajeError(Mensaje.camposVacios);
				return false;
			} else
				return true;
		}
	}

	/* Muestra el catalogo de los Pacientes */
	@Listen("onClick = #btnBuscarPaciente")
	public void mostrarCatalogoFamiliar() {
		Paciente paciente = new Paciente();
		paciente.setCedula("TODOS");
		paciente.setFicha("TODOS");
		paciente.setPrimerNombre("TODOS");
		paciente.setPrimerApellido("TODOS");
		List<Paciente> lista = new ArrayList<Paciente>();
		lista.add(paciente);
		lista.addAll(servicioPaciente.buscarTodosTrabajadores());
		final List<Paciente> pacientes = lista;
		catalogo = new Catalogo<Paciente>(divCatalogoPaciente,
				"Catalogo de Pacientes", pacientes, "Cedula", "Ficha",
				"Nombre", "Apellido") {

			@Override
			protected List<Paciente> buscar(String valor, String combo) {
				switch (combo) {
				case "Nombre":
					return servicioPaciente.filtroNombre1T(valor);
				case "Cedula":
					return servicioPaciente.filtroCedulaT(valor);
				case "Ficha":
					return servicioPaciente.filtroFichaT(valor);
				case "Apellido":
					return servicioPaciente.filtroApellido1T(valor);
				default:
					return pacientes;
				}
			}

			@Override
			protected String[] crearRegistros(Paciente objeto) {
				String[] registros = new String[4];
				registros[0] = objeto.getCedula();
				registros[1] = objeto.getFicha();
				registros[2] = objeto.getPrimerNombre();
				registros[3] = objeto.getPrimerApellido();
				return registros;
			}

		};
		catalogo.setParent(divCatalogoPaciente);
		catalogo.doModal();
	}

	/* Permite la seleccion de un item del catalogo de trabajadores */
	@Listen("onSeleccion = #divCatalogoPaciente")
	public void seleccinarTrabajador() {
		Paciente paciente = catalogo.objetoSeleccionadoDelCatalogo();
		lblPaciente.setValue(paciente.getCedula());
		catalogo.setParent(null);
	}

}
