package controlador.maestros;

import java.io.IOException;
import java.util.List;

import modelo.maestros.Examen;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublespinner;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;

public class CExamen extends CGenerico {

	private static final long serialVersionUID = 6737059894725627866L;
	@Wire
	private Textbox txtNombreExamen;
	@Wire
	private Textbox txtTipoExamen;
	@Wire
	private Combobox cmbResultadoExamen;
	@Wire
	private Doublespinner dspCostoExamen;
	@Wire
	private Doublespinner dspMinExamen;
	@Wire
	private Doublespinner dspMaxExamen;
	@Wire
	private Button btnBuscarExamen;
	@Wire
	private Div divExamen;
	@Wire
	private Div botoneraExamen;
	@Wire
	private Div catalogoExamen;
	private long id = 0;
	Catalogo<Examen> catalogo;

	@Override
	public void inicializar() throws IOException {
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divExamen, "Examen");
			}

			@Override
			public void limpiar() {
				txtNombreExamen.setValue("");
				cmbResultadoExamen.setValue("");
				cmbResultadoExamen.setPlaceholder("Seleccione un Tipo de Resultado");
				txtTipoExamen.setValue("");
				dspCostoExamen.setValue(0.0);
				dspMaxExamen.setValue(0.0);
				dspMinExamen.setValue(0.0);
				id = 0;
			}

			@Override
			public void guardar() {
				if (validar()) {
					String nombre = txtNombreExamen.getValue();
					String tipo = txtTipoExamen.getValue();
					String resultado = cmbResultadoExamen.getValue();
					double costo = dspCostoExamen.getValue();
					double minimo = dspMinExamen.getValue();
					double maximo = dspMaxExamen.getValue();
					Examen examen = new Examen(id, nombre, tipo, resultado,
							costo, minimo, maximo, fechaHora, horaAuditoria,
							nombreUsuarioSesion());
					servicioExamen.guardar(examen);
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
					limpiar();
				}
			}

			@Override
			public void eliminar() {
				if (id != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar el Examen?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Examen examen = servicioExamen
												.buscar(id);
										servicioExamen.eliminar(examen);
										limpiar();
										Messagebox
												.show("Registro Eliminado Exitosamente",
														"Informacion",
														Messagebox.OK,
														Messagebox.INFORMATION);

									}
								}
							});
				} else {
					Messagebox.show("No ha Seleccionado Ningun Registro",
							"Alerta", Messagebox.OK, Messagebox.EXCLAMATION);
				}
			}
		};
		botoneraExamen.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtNombreExamen.getText().compareTo("") == 0
				|| cmbResultadoExamen.getText().compareTo("") == 0
				|| txtTipoExamen.getText().compareTo("") == 0
				|| dspCostoExamen.getText().compareTo("") == 0
				|| dspMaxExamen.getText().compareTo("") == 0
				|| dspMinExamen.getText().compareTo("") == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else
			return true;
	}

	/* Muestra el catalogo de los examen */
	@Listen("onClick = #btnBuscarExamen")
	public void mostrarCatalogo() {
		final List<Examen> examenes = servicioExamen.buscarTodos();
		catalogo = new Catalogo<Examen>(catalogoExamen, "Catalogo de Estados",
				examenes, "Nombre", "Tipo", "Resultado", "Costo",
				"Valor Minimo", "Valor Maximo") {

			@Override
			protected List<Examen> buscar(String valor, String combo) {
				switch (combo) {
				case "Nombre":
					return servicioExamen.filtroNombre(valor);
				case "Tipo":
					return servicioExamen.filtroTipo(valor);
				case "Resultado":
					return servicioExamen.filtroResultado(valor);
				case "Costo":
					return servicioExamen.filtroCosto(valor);
				case "Valor Minimo":
					return servicioExamen.filtroMinimo(valor);
				case "Valor Maximo":
					return servicioExamen.filtroMaximo(valor);
				default:
					return examenes;
				}
			}

			@Override
			protected String[] crearRegistros(Examen examen) {
				String[] registros = new String[6];
				registros[0] = examen.getNombre();
				registros[1] = examen.getTipo();
				registros[2] = examen.getResultado();
				registros[3] = String.valueOf(examen.getCosto());
				registros[4] = String.valueOf(examen.getMinimo());
				registros[5] = String.valueOf(examen.getMaximo());
				return registros;
			}
		};
		catalogo.setParent(catalogoExamen);
		catalogo.doModal();
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoExamen")
	public void seleccinar() {
		Examen examen = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(examen);
		catalogo.setParent(null);
	}

	/* Busca si existe un examen con el mismo nombre escrito */
	@Listen("onChange = #txtNombreExamen")
	public void buscarPorNombre() {
		Examen examen = servicioExamen.buscarPorNombre(txtNombreExamen
				.getValue());
		if (examen != null)
			llenarCampos(examen);
	}

	/* LLena los campos del formulario dado un examen */
	private void llenarCampos(Examen examen) {
		txtNombreExamen.setValue(examen.getNombre());
		cmbResultadoExamen.setValue(examen.getResultado());
		txtTipoExamen.setValue(examen.getTipo());
		dspCostoExamen.setValue(examen.getCosto());
		dspMaxExamen.setValue(examen.getMaximo());
		dspMinExamen.setValue(examen.getMinimo());
		id = examen.getIdExamen();
	}

}
