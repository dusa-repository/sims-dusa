package controlador.maestros;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import modelo.maestros.Periodo;
import modelo.maestros.PeriodoPaciente;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;

public class CPeriodo extends CGenerico {

	private static final long serialVersionUID = -6735635033649435487L;
	@Wire
	private Div botoneraPeriodo;
	@Wire
	private Textbox txtNombre;
	@Wire
	private Datebox dtbInicio;
	@Wire
	private Datebox dtbFin;
	@Wire
	private Button btnBuscar;
	@Wire
	private Div catalogoPeriodo;
	@Wire
	private Div divPeriodo;
	private long id = 0;
	Catalogo<Periodo> catalogo;
	String nombre = "";

	@SuppressWarnings("unchecked")
	@Override
	public void inicializar() throws IOException {
		HashMap<String, Object> mapa = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (mapa != null) {
			if (mapa.get("tabsGenerales") != null) {
				tabs = (List<Tab>) mapa.get("tabsGenerales");
				nombre = (String) mapa.get("titulo");
				mapa.clear();
				mapa = null;
			}
		}
		Botonera botonera = new Botonera() {
			private static final long serialVersionUID = 1L;

			@Override
			public void salir() {
				cerrarVentana(divPeriodo, nombre, tabs);
			}

			@Override
			public void limpiar() {
				txtNombre.setValue("");
				dtbFin.setValue(fecha);
				dtbInicio.setValue(fecha);
				id = 0;
			}

			@Override
			public void guardar() {
				if (validar()) {
					String nombre = txtNombre.getValue();
					Timestamp fecha1 = new Timestamp(dtbInicio.getValue()
							.getTime());
					Timestamp fecha2 = new Timestamp(dtbFin.getValue()
							.getTime());
					Periodo periodo = new Periodo(id, nombre, fecha1, fecha2,
							fechaHora, horaAuditoria, nombreUsuarioSesion());
					servicioPeriodo.guardar(periodo);
					msj.mensajeInformacion(Mensaje.guardado);
					limpiar();
				}
			}

			@Override
			public void eliminar() {
				if (id != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar el Periodo?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										List<PeriodoPaciente> periodos = servicioPeriodoPaciente
												.buscarPorIdPeriodo(id);
										if (!periodos.isEmpty()) {
											msj.mensajeError(Mensaje.noEliminar);
										} else {
											servicioPeriodo.eliminar(id);
											limpiar();
											msj.mensajeInformacion(Mensaje.eliminado);
										}
									}
								}
							});
				} else {
					msj.mensajeAlerta(Mensaje.noSeleccionoRegistro);
				}
			}
		};
		botoneraPeriodo.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtNombre.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else {
			if (dtbInicio.getValue().after(dtbFin.getValue())) {
				msj.mensajeError(Mensaje.fechaPosterior);
				return false;
			} else
				return true;
		}
	}

	/* Muestra el catalogo de los paises */
	@Listen("onClick = #btnBuscar")
	public void mostrarCatalogo() {
		final List<Periodo> periodos = servicioPeriodo.buscarTodos();
		catalogo = new Catalogo<Periodo>(catalogoPeriodo,
				"Catalogo de Periodos", periodos, "Nombre", "Fecha Inicio",
				"Fecha Fin") {
			private static final long serialVersionUID = 2968389472159832753L;

			@Override
			protected List<Periodo> buscar(String valor, String combo) {
				switch (combo) {
				case "Nombre":
					return servicioPeriodo.filtroNombre(valor);
				case "Fecha Inicio":
					return servicioPeriodo.filtroInicio(valor);
				case "Fecha Fin":
					return servicioPeriodo.filtroFin(valor);
				default:
					return periodos;
				}

			}

			@Override
			protected String[] crearRegistros(Periodo estado) {
				String[] registros = new String[3];
				registros[0] = estado.getNombre();
				registros[1] = traerFecha2(estado.getFechaInicio());
				registros[2] = traerFecha2(estado.getFechaFin());
				return registros;
			}
		};
		catalogo.setParent(catalogoPeriodo);
		catalogo.doModal();
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoPeriodo")
	public void seleccinar() {
		Periodo periodo = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(periodo);
		catalogo.setParent(null);
	}

	/* Busca si existe un pais con el mismo nombre escrito */
	@Listen("onChange = #txtNombre")
	public void buscarPorNombre() {
		Periodo periodo = servicioPeriodo.buscarPorNombre(txtNombre.getValue());
		if (periodo != null)
			llenarCampos(periodo);
	}

	/* LLena los campos del formulario dado un pais */
	private void llenarCampos(Periodo periodo) {
		txtNombre.setValue(periodo.getNombre());
		dtbFin.setValue(periodo.getFechaFin());
		dtbInicio.setValue(periodo.getFechaInicio());
		id = periodo.getIdPeriodo();
	}

}
