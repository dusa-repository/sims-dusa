package controlador.sha;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import modelo.sha.Condicion;
import modelo.sha.Informe;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;

import controlador.maestros.CGenerico;

public class CCondicion extends CGenerico {

	@Wire
	private Radiogroup rdgCondicion;
	@Wire
	private Radio rdoInstalaciones;
	@Wire
	private Radio rdoEquipos;
	@Wire
	private Radio rdoMateriales;
	@Wire
	private Radio rdoAmbiente;
	@Wire
	private Radio rdoOrganizacion;
	@Wire
	private Radio rdoDisergonomicos;
	@Wire
	private Div botoneraCondicion;
	@Wire
	private Textbox txtNombreCondicion;
	@Wire
	private Button btnBuscarCondicion;
	@Wire
	private Div catalogoCondicion;
	@Wire
	private Div divCondicion;
	private long id = 0;
	Catalogo<Condicion> catalogo;

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
				cerrarVentana(divCondicion, "Condicion",tabs);
			}

			@Override
			public void limpiar() {
				txtNombreCondicion.setValue("");
				rdoInstalaciones.setChecked(false);
				rdoEquipos.setChecked(false);
				rdoMateriales.setChecked(false);
				rdoDisergonomicos.setChecked(false);
				rdoOrganizacion.setChecked(false);
				rdoAmbiente.setChecked(false);
				id = 0;
			}

			@Override
			public void guardar() {
				if (validar()) {
					String nombre = txtNombreCondicion.getValue();
					String tipo = rdgCondicion.getSelectedItem().getLabel();

					Condicion condicion = new Condicion(id, nombre, tipo,
							fechaHora, horaAuditoria, nombreUsuarioSesion());
					servicioCondicion.guardar(condicion);
					msj.mensajeInformacion(Mensaje.guardado);
					limpiar();
				}
			}

			@Override
			public void eliminar() {
				if (id != 0 && txtNombreCondicion.getText().compareTo("") != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar la Condicion?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Condicion condicion = servicioCondicion
												.buscar(id);
										List<Informe> informes = servicioInforme
												.buscarPorCondicion(condicion);

										if (!informes.isEmpty()) {
											msj.mensajeError(Mensaje.noEliminar);
										} else {
											servicioCondicion
													.eliminar(condicion);
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
		botoneraCondicion.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtNombreCondicion.getText().compareTo("") == 0
				|| rdgCondicion.getSelectedItem().getLabel() == null) {
			msj.mensajeAlerta(Mensaje.camposVacios);
			return false;
		} else
			return true;
	}

	/* Muestra el catalogo de los antecedenteTipoes */
	@Listen("onClick = #btnBuscarCondicion")
	public void mostrarCatalogo() {
		final List<Condicion> condiciones = servicioCondicion.buscarTodos();
		catalogo = new Catalogo<Condicion>(catalogoCondicion,
				"Catalogo de Condiciones", condiciones, "Nombre", "Tipo") {

			@Override
			protected List<Condicion> buscar(String valor, String combo) {
				if (combo.equals("Nombre"))
					return servicioCondicion.filtroNombre(valor);
				else {
					if (combo.equals("Tipo"))
						return servicioCondicion.filtroTipo(valor);
					else
						return condiciones;
				}
			}

			@Override
			protected String[] crearRegistros(Condicion estado) {
				String[] registros = new String[2];
				registros[0] = estado.getNombre();
				registros[1] = estado.getTipo();
				return registros;
			}
		};
		catalogo.setParent(catalogoCondicion);
		catalogo.doModal();
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoCondicion")
	public void seleccinar() {
		Condicion condicion = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(condicion);
		catalogo.setParent(null);
	}

	/* Busca si existe un antecedenteTipo con el mismo nombre escrito */
	@Listen("onChange = #txtNombreCondicion")
	public void buscarPorNombre() {
		Condicion condicion = servicioCondicion
				.buscarPorNombre(txtNombreCondicion.getValue());
		if (condicion != null)
			llenarCampos(condicion);
	}

	/* LLena los campos del formulario dado un antecedenteTipo */
	private void llenarCampos(Condicion condicion) {
		txtNombreCondicion.setValue(condicion.getNombre());

		if (condicion.getTipo().equals("Instalaciones"))
			rdoInstalaciones.setChecked(true);
		else {
			if (condicion.getTipo().equals("Equipos"))
				rdoEquipos.setChecked(true);
			else {
				if (condicion.getTipo().equals("Materiales"))
					rdoMateriales.setChecked(true);
				else {
					if (condicion.getTipo().equals("Ambiente"))
						rdoAmbiente.setChecked(true);
					else {
						if (condicion.getTipo().equals("Organizacion"))
							rdoOrganizacion.setChecked(true);
						else
							rdoDisergonomicos.setChecked(true);
					}
				}
			}
		}

		id = condicion.getIdCondicion();
	}

}
