package controlador.sha;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import modelo.maestros.Paciente;
import modelo.sha.Area;
import modelo.sha.Informe;
import modelo.transacciones.Consulta;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;

import controlador.maestros.CGenerico;

public class CArea extends CGenerico {

	@Wire
	private Div botoneraArea;
	@Wire
	private Textbox txtNombreArea;
	@Wire
	private Textbox txtCodigoArea;
	@Wire
	private Button btnBuscarArea;
	@Wire
	private Div catalogoArea;
	@Wire
	private Div divArea;
	@Wire
	private Window wdwArea;

	private long id = 0;
	Catalogo<Area> catalogo;

	@Override
	public void inicializar() throws IOException {
		HashMap<String, Object> mapa = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (mapa != null) {
			if (mapa.get("tabsGenerales") != null) {
				tabs = (List<Tab>) mapa.get("tabsGenerales");
				mapa.clear();
				mapa = null;
			}
		}
		txtNombreArea.setFocus(true);

		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divArea, "Area", tabs);

			}

			@Override
			public void limpiar() {
				txtNombreArea.setValue("");
				txtCodigoArea.setValue("");
				id = 0;
				txtCodigoArea.setFocus(true);
			}

			@Override
			public void guardar() {
				if (validar()) {
					String nombre = txtNombreArea.getValue();
					String codigo = txtCodigoArea.getValue();
					Area area = new Area(id, codigo, nombre, fechaHora,
							horaAuditoria, nombreUsuarioSesion());
					servicioArea.guardar(area);
					msj.mensajeInformacion(Mensaje.guardado);
					limpiar();
				}

			}

			@Override
			public void eliminar() {
				if (id != 0 && txtNombreArea.getText().compareTo("") != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar el Area?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Area area = servicioArea.buscar(id);
										List<Paciente> pacientes = servicioPaciente
												.buscarPorArea(area);
										List<Informe> informes = servicioInforme
												.buscarPorArea(area);
										List<Consulta> consultas1 = servicioConsulta
												.buscarPorArea(area);
										List<Consulta> consultas2 = servicioConsulta
												.buscarPorArea2(area);
										if (!pacientes.isEmpty()
												|| !informes.isEmpty()
												|| !consultas1.isEmpty()
												|| !consultas2.isEmpty()) {
											msj.mensajeError(Mensaje.noEliminar);
										} else {
											servicioArea.eliminar(area);
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

		botoneraArea.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtNombreArea.getText().compareTo("") == 0
				|| txtCodigoArea.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	/* Muestra el catalogo de los areas */
	@Listen("onClick = #btnBuscarArea")
	public void mostrarCatalogo() {
		final List<Area> areas = servicioArea.buscarTodos();
		catalogo = new Catalogo<Area>(catalogoArea, "Catalogo de Areas", areas,false,
				"Nombre") {

			@Override
			protected List<Area> buscar(String valor, String combo) {
				switch (combo) {
				case "Nombre":
					return servicioArea.filtroNombre(valor);
				default:
					return areas;
				}
			}

			@Override
			protected String[] crearRegistros(Area area) {
				String[] registros = new String[1];
				registros[0] = area.getNombre();
				return registros;
			}
		};
		catalogo.setParent(catalogoArea);
		catalogo.doModal();
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoArea")
	public void seleccinar() {
		Area area = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(area);
		catalogo.setParent(null);
	}

	/* Busca si existe un area con el mismo nombre escrito */
	@Listen("onChange = #txtNombreArea")
	public void buscarPorNombre() {
		Area area = servicioArea.buscarPorNombre(txtNombreArea.getValue());
		if (area != null)
			llenarCampos(area);
	}

	/* Busca si existe un area con el mismo nombre escrito */
	@Listen("onChange = #txtCodigoArea")
	public void buscarPorCodigo() {
		Area area = servicioArea.buscarPorCodigo(txtCodigoArea.getValue());
		if (area != null)
			llenarCampos(area);
	}

	/* LLena los campos del formulario dado un area */
	private void llenarCampos(Area area) {
		txtNombreArea.setValue(area.getNombre());
		txtCodigoArea.setValue(area.getCodigo());
		id = area.getIdArea();
	}

}
