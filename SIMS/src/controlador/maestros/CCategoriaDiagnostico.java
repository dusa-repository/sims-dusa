package controlador.maestros;

import java.util.List;

import modelo.maestros.CategoriaDiagnostico;
import modelo.maestros.Diagnostico;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;

public class CCategoriaDiagnostico extends CGenerico {

	private static final long serialVersionUID = 3977153060950873260L;
	@Wire
	private Div botoneraCategoriaDiagnostico;
	@Wire
	private Textbox txtNombreCategoriaDiagnostico;
	@Wire
	private Button btnBuscarCategoriaDiagnostico;
	@Wire
	private Div catalogoCategoriaDiagnostico;
	@Wire
	private Div divCategoriaDiagnostico;
	private long id = 0;
	Catalogo<CategoriaDiagnostico> catalogo;

	@Override
	public void inicializar() {
		Botonera botonera = new Botonera() {
			@Override
			public void guardar() {
				if (validar()) {
					String nombre = txtNombreCategoriaDiagnostico.getValue();
					CategoriaDiagnostico categoria = new CategoriaDiagnostico(id, fechaHora,
							horaAuditoria, nombre, nombreUsuarioSesion());
					servicioCategoriaDiagnostico.guardar(categoria);
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
					limpiar();
				}
			}

			@Override
			public void limpiar() {
				txtNombreCategoriaDiagnostico.setValue("");
				id = 0;
			}

			@Override
			public void salir() {
				cerrarVentana(divCategoriaDiagnostico, "Categoria Diagnostico");
			}

			@Override
			public void eliminar() {
				if (id != 0 && txtNombreCategoriaDiagnostico.getText().compareTo("") != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar la Categoria?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										CategoriaDiagnostico categoria = servicioCategoriaDiagnostico
												.buscar(id);
										List<Diagnostico> diagnosticos = servicioDiagnostico
												.buscarPorCategoria(categoria);
										if (!diagnosticos.isEmpty()) {
											Messagebox
													.show("No se Puede Eliminar el Registro, Esta siendo Utilizado",
															"Informacion",
															Messagebox.OK,
															Messagebox.INFORMATION);
										} else {
											servicioCategoriaDiagnostico
													.eliminar(categoria);
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
		botoneraCategoriaDiagnostico.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtNombreCategoriaDiagnostico.getText().compareTo("") == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else
			return true;
	}

	/* Muestra el catalogo de las categorias */
	@Listen("onClick = #btnBuscarCategoriaDiagnostico")
	public void mostrarCatalogo() {
		List<CategoriaDiagnostico> categorias = servicioCategoriaDiagnostico.buscarTodas();
		catalogo = new Catalogo<CategoriaDiagnostico>(catalogoCategoriaDiagnostico,
				"Catalogo de Categorias", categorias, "Nombre") {

			@Override
			protected List<CategoriaDiagnostico> buscar(String valor, String combo) {
				if(combo.equals("Nombre"))
				return servicioCategoriaDiagnostico.filtroNombre(valor);
				else
					return servicioCategoriaDiagnostico.buscarTodas();
			}

			@Override
			protected String[] crearRegistros(CategoriaDiagnostico categoria) {
				String[] registros = new String[1];
				registros[0] = categoria.getNombre();
				return registros;
			}
		};
		catalogo.setParent(catalogoCategoriaDiagnostico);
		catalogo.doModal();
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoCategoriaDiagnostico")
	public void seleccinar() {
		CategoriaDiagnostico categoria = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(categoria);
		catalogo.setParent(null);
	}

	/* Busca si existe una categoria con el mismo codigo escrito */
	@Listen("onChange = #txtNombreCategoriaDiagnostico")
	public void buscarPorNombre() {
		CategoriaDiagnostico categoria = servicioCategoriaDiagnostico
				.buscarPorNombre(txtNombreCategoriaDiagnostico.getValue());
		if (categoria != null)
			llenarCampos(categoria);
	}

	/* LLena los campos del formulario dada una categoria */
	private void llenarCampos(CategoriaDiagnostico categoria) {
		txtNombreCategoriaDiagnostico.setValue(categoria.getNombre());
		id = categoria.getIdCategoriaDiagnostico();
	}
}
