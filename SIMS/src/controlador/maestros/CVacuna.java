package controlador.maestros;

import java.io.IOException;
import java.util.List;

import modelo.maestros.Vacuna;
import modelo.transacciones.HistoriaVacuna;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;

public class CVacuna extends CGenerico {

	private static final long serialVersionUID = -7071243007484522110L;
	@Wire
	private Div botoneraVacuna;
	@Wire
	private Textbox txtNombre;
	@Wire
	private Div catalogoVacuna;
	@Wire
	private Div divVacuna;
	private long id = 0;
	Catalogo<Vacuna> catalogo;

	@Override
	public void inicializar() throws IOException {
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divVacuna, "Vacuna");
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
					Vacuna vacuna = new Vacuna(id, nombre, fechaHora,
							horaAuditoria, nombreUsuarioSesion());
					servicioVacuna.guardar(vacuna);
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
					limpiar();
				}
			}

			@Override
			public void eliminar() {
				if (id != 0 && txtNombre.getText().compareTo("") != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar la Vacuna?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Vacuna vacuna = servicioVacuna
												.buscar(id);
										List<HistoriaVacuna> estados = servicioHistoriaVacuna
												.buscarPorVacuna(vacuna);
										if (!estados.isEmpty()) {
											Messagebox
													.show("No se Puede Eliminar el Registro, Esta siendo Utilizado",
															"Informacion",
															Messagebox.OK,
															Messagebox.INFORMATION);
										} else {
											servicioVacuna.eliminar(vacuna);
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
		botoneraVacuna.appendChild(botonera);
	}

	protected boolean validar() {
		if (txtNombre.getText().compareTo("") == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else
			return true;
	}
	
	@Listen("onClick = #btnBuscarVacuna")
	public void mostrarCatalogo() {
		final List<Vacuna> paises = servicioVacuna.buscarTodos();
		catalogo = new Catalogo<Vacuna>(catalogoVacuna, "Catalogo de Intervenciones",
				paises, "Nombre") {

			@Override
			protected List<Vacuna> buscar(String valor, String combo) {
				if (combo.equals("Nombre"))
					return servicioVacuna.filtroNombre(valor);
				else
					return paises;
			}

			@Override
			protected String[] crearRegistros(Vacuna estado) {
				String[] registros = new String[1];
				registros[0] = estado.getNombre();
				return registros;
			}
		};
		catalogo.setParent(catalogoVacuna);
		catalogo.doModal();
	}

	@Listen("onSeleccion = #catalogoVacuna")
	public void seleccinar() {
		Vacuna vacuna = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(vacuna);
		catalogo.setParent(null);
	}

	@Listen("onChange = #txtNombre")
	public void buscarPorNombre() {
		Vacuna vacuna = servicioVacuna.buscarPorNombre(txtNombre
				.getValue());
		if (vacuna != null)
			llenarCampos(vacuna);
	}

	private void llenarCampos(Vacuna vacuna) {
		txtNombre.setValue(vacuna.getNombre());
		id = vacuna.getIdVacuna();
	}
}
