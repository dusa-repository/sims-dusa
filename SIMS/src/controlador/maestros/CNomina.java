package controlador.maestros;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import modelo.maestros.Nomina;
import modelo.maestros.Paciente;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;

public class CNomina extends CGenerico {

	private static final long serialVersionUID = -5263743713163591765L;
	@Wire
	private Div botoneraNomina;
	@Wire
	private Textbox txtNombre;
	@Wire
	private Div catalogoNomina;
	@Wire
	private Div divNomina;
	private long id = 0;
	Catalogo<Nomina> catalogo;

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
		
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divNomina, "Nomina", tabs);
			}

			@Override
			public void limpiar() {
				txtNombre.setValue("");
				id = 0;
			}

			@Override
			public void guardar() {
				if (validar()) {
					String nombre = txtNombre.getValue();
					Nomina nomina = new Nomina(id, nombre,
							fechaHora, horaAuditoria, nombreUsuarioSesion());
					servicioNomina.guardar(nomina);
					msj.mensajeInformacion(Mensaje.guardado);
					limpiar();
				}
			}

			@Override
			public void eliminar() {
				if (id != 0 && txtNombre.getText().compareTo("") != 0) {
					Messagebox.show(
							"¿Esta Seguro de Eliminar la Nomina?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Nomina nomina = servicioNomina
												.buscar(id);
										List<Paciente> nominas = servicioPaciente
												.buscarPorNomina(nomina);
										if (!nominas.isEmpty()) {
											msj.mensajeError(Mensaje.noEliminar);
										} else {
											servicioNomina
													.eliminar(nomina);
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
		botoneraNomina.appendChild(botonera);
	}

	protected boolean validar() {
		if (txtNombre.getText().compareTo("") == 0) {
			msj.mensajeError(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	@Listen("onClick = #btnBuscarNomina")
	public void mostrarCatalogo() {
		final List<Nomina> nominas = servicioNomina.buscarTodos();
		catalogo = new Catalogo<Nomina>(catalogoNomina,
				"Catalogo de Nominas", nominas, "Nombre") {

			@Override
			protected List<Nomina> buscar(String valor, String combo) {
				if (combo.equals("Nombre"))
					return servicioNomina.filtroNombre(valor);
				else
					return nominas;
			}

			@Override
			protected String[] crearRegistros(Nomina estado) {
				String[] registros = new String[1];
				registros[0] = estado.getNombre();
				return registros;
			}
		};
		catalogo.setParent(catalogoNomina);
		catalogo.doModal();
	}

	@Listen("onSeleccion = #catalogoNomina")
	public void seleccinar() {
		Nomina nomina = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(nomina);
		catalogo.setParent(null);
	}

	@Listen("onChange = #txtNombre")
	public void buscarPorNombre() {
		Nomina nomina = servicioNomina
				.buscarPorNombre(txtNombre.getValue());
		if (nomina != null)
			llenarCampos(nomina);
	}

	private void llenarCampos(Nomina pais) {
		txtNombre.setValue(pais.getNombre());
		id = pais.getIdNomina();
	}

}

