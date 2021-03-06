package controlador.maestros;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import modelo.maestros.Laboratorio;
import modelo.maestros.Medicina;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;

public class CLaboratorio extends CGenerico {

	private static final long serialVersionUID = 1244878044647029761L;

	@Wire
	private Textbox txtNombre;
	@Wire
	private Div botoneraLaboratorio;
	@Wire
	private Div catalogoLaboratorio;
	@Wire
	private Div divLaboratorio;
	@Wire
	private Button btnBuscarLaboratorio;

	Catalogo<Laboratorio> catalogo;
	long id = 0;

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
		Botonera botonera = new Botonera() {
			@Override
			public void guardar() {

				if (validar()) {
					String nombre = txtNombre.getValue();

					Laboratorio laboratorio = new Laboratorio(id, fechaHora,
							horaAuditoria, nombre, nombreUsuarioSesion());
					servicioLaboratorio.guardar(laboratorio);
					msj.mensajeInformacion(Mensaje.guardado);
					limpiar();
				}
			}

			@Override
			public void limpiar() {
				txtNombre.setText("");
				id = 0;
			}

			@Override
			public void salir() {
				cerrarVentana(divLaboratorio, "Laboratorio", tabs);
			}

			@Override
			public void eliminar() {
				if (id != 0 && txtNombre.getText().compareTo("") != 0) {
					Messagebox.show("�Esta Seguro de Eliminar el Laboratorio?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Laboratorio laboratorio = servicioLaboratorio
												.buscar(id);
										List<Medicina> medicinas = servicioMedicina
												.buscarPorLaboratorio(laboratorio);
										if (!medicinas.isEmpty()) {
											msj.mensajeError(Mensaje.noEliminar);
										} else {
											servicioLaboratorio
													.eliminar(laboratorio);
											limpiar();
											msj.mensajeInformacion(Mensaje.eliminado);
										}
									}
								}
							});
				} else
					msj.mensajeAlerta(Mensaje.noSeleccionoRegistro);
			}
		};
		/* Dibuja el componente botonera en el div botoneraLaboratorio */
		botoneraLaboratorio.appendChild(botonera);
	}

	/* Muestra un catalogo de laboratorios */
	@Listen("onClick = #btnBuscarLaboratorio")
	public void mostrarCatalogo() throws IOException {
		List<Laboratorio> laboratorios = servicioLaboratorio.buscarTodos();
		catalogo = new Catalogo<Laboratorio>(catalogoLaboratorio,
				"Catalogo de Laboratorios", laboratorios, false,"Nombre") {

			@Override
			protected String[] crearRegistros(Laboratorio laboratorio) {
				String[] registros = new String[1];
				registros[0] = laboratorio.getNombre();
				return registros;
			}

			@Override
			protected List<Laboratorio> buscar(String valor, String combo) {
				if (combo.equals("Nombre"))
					return servicioLaboratorio.filtroNombre(valor);
				else
					return servicioLaboratorio.buscarTodos();
			}
		};
		catalogo.setParent(catalogoLaboratorio);
		catalogo.doModal();
	}

	/* Validaciones de pantalla para poder realizar el guardar */
	public boolean validar() {

		if (txtNombre.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	/* Busca si existe un laboratorio con el mismo nombre escrito */
	@Listen("onChange = #txtNombre")
	public void buscarPorNombre() {
		Laboratorio laboratorio = servicioLaboratorio.buscarPorNombre(txtNombre
				.getValue());
		if (laboratorio != null)
			llenarCampos(laboratorio);
	}

	/*
	 * Selecciona un laboratorio del catalogo y llena los campos con la
	 * informacion
	 */
	@Listen("onSeleccion = #catalogoLaboratorio")
	public void seleccion() {
		Laboratorio laboratorio = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(laboratorio);
		catalogo.setParent(null);
	}

	/* LLena los campos del formulario dado un laboratorio */
	public void llenarCampos(Laboratorio laboratorio) {
		txtNombre.setValue(laboratorio.getNombre());
		id = laboratorio.getIdLaboratorio();
	}
}
