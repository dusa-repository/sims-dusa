package controlador.maestros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import modelo.maestros.Examen;
import modelo.maestros.MedicinaPresentacionUnidad;
import modelo.maestros.PresentacionMedicina;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;
import controlador.transacciones.CConsulta;

public class CPresentacionMedicina extends CGenerico {

	private static final long serialVersionUID = -4888603924033320550L;
	@Wire
	private Div botoneraPresentacionMedicina;
	@Wire
	private Textbox txtNombrePresentacionMedicina;
	@Wire
	private Button btnBuscarPresentacionMedicina;
	@Wire
	private Div catalogoPresentacionMedicina;
	@Wire
	private Div divPresentacionMedicina;
	private long id = 0;
	Catalogo<PresentacionMedicina> catalogo;

	private boolean medicina = false;
	private CMedicina cMedicina = new CMedicina();
	List<PresentacionMedicina> listaPresentacion = new ArrayList<PresentacionMedicina>();
	Listbox lista;
	
	@Override
	public void inicializar() throws IOException {
		HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("itemsCatalogo");
		if (map != null) {
			if (map.get("id") != null) {
				medicina = true;
				listaPresentacion = (List<PresentacionMedicina>) map.get("lista");
				lista = (Listbox) map.get("listbox");
				map.clear();
				map = null;
			}
		}
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divPresentacionMedicina, "Presentacion Medicina");
			}

			@Override
			public void limpiar() {
				txtNombrePresentacionMedicina.setValue("");
				id = 0;
			}

			@Override
			public void guardar() {
				if (validar()) {
					PresentacionMedicina presentacionMedicina = new PresentacionMedicina(
							id, fechaHora, horaAuditoria,
							txtNombrePresentacionMedicina.getValue(),
							nombreUsuarioSesion());
					servicioPresentacionMedicina.guardar(presentacionMedicina);
					if (medicina) {
						if (id != 0)
							presentacionMedicina = servicioPresentacionMedicina.buscar(id);
						else {
							presentacionMedicina = servicioPresentacionMedicina.buscarUltimo();
							listaPresentacion.add(presentacionMedicina);
						}
						cMedicina.recibirPresentacion(listaPresentacion,
								lista);
					}
					limpiar();
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
				}
			}

			@Override
			public void eliminar() {
				if (id != 0
						&& txtNombrePresentacionMedicina.getText()
								.compareTo("") != 0) {
					Messagebox
							.show("¿Esta Seguro de Eliminar la Presentacion de Medicina?",
									"Alerta",
									Messagebox.OK | Messagebox.CANCEL,
									Messagebox.QUESTION,
									new org.zkoss.zk.ui.event.EventListener<Event>() {
										public void onEvent(Event evt)
												throws InterruptedException {
											if (evt.getName().equals("onOK")) {
												PresentacionMedicina presentacionMedicina = servicioPresentacionMedicina
														.buscar(id);
												List<MedicinaPresentacionUnidad> medicinas = servicioMedicinaPresentacionUnidad
														.buscarPorPresentacion(presentacionMedicina);
												if (!medicinas.isEmpty()) {
													Messagebox
															.show("No se Puede Eliminar el Registro, Esta siendo Utilizado",
																	"Informacion",
																	Messagebox.OK,
																	Messagebox.INFORMATION);
												} else {
													servicioPresentacionMedicina
															.eliminar(presentacionMedicina);
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
				} else {
					Messagebox.show("No ha Seleccionado Ningun Registro",
							"Alerta", Messagebox.OK, Messagebox.EXCLAMATION);
				}
			}
		};
		botoneraPresentacionMedicina.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtNombrePresentacionMedicina.getText().compareTo("") == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else
			return true;
	}

	/* Muestra el catalogo de las categorias */
	@Listen("onClick = #btnBuscarPresentacionMedicina")
	public void mostrarCatalogo() {
		final List<PresentacionMedicina> presentacionMedicinas = servicioPresentacionMedicina
				.buscarTodas();
		catalogo = new Catalogo<PresentacionMedicina>(
				catalogoPresentacionMedicina,
				"Catalogo de Presentaciones de Medicina",
				presentacionMedicinas, "Nombre") {

			@Override
			protected List<PresentacionMedicina> buscar(String valor,
					String combo) {
				if (combo.equals("Nombre"))
					return servicioPresentacionMedicina.filtroNombre(valor);
				else
					return presentacionMedicinas;
			}

			@Override
			protected String[] crearRegistros(PresentacionMedicina objeto) {
				String[] registros = new String[1];
				registros[0] = objeto.getNombre();
				return registros;
			}

		};
		catalogo.setParent(catalogoPresentacionMedicina);
		catalogo.doModal();
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoPresentacionMedicina")
	public void seleccinar() {
		PresentacionMedicina presentacionMedicina = catalogo
				.objetoSeleccionadoDelCatalogo();
		llenarCampos(presentacionMedicina);
		catalogo.setParent(null);
	}

	/* Busca si existe una unidad con el mismo nombre escrito */
	@Listen("onChange = #txtNombrePresentacionMedicina")
	public void buscarPorNombre() {
		PresentacionMedicina presentacionMedicina = servicioPresentacionMedicina
				.buscarPorNombre(txtNombrePresentacionMedicina.getValue());
		if (presentacionMedicina != null)
			llenarCampos(presentacionMedicina);
	}

	/* LLena los campos del formulario dada una unidad */
	private void llenarCampos(PresentacionMedicina presentacionMedicina) {
		txtNombrePresentacionMedicina
				.setValue(presentacionMedicina.getNombre());
		id = presentacionMedicina.getIdPresentacion();
	}
}
