package controlador.maestros;

import java.io.IOException;
import java.util.List;

import modelo.maestros.CategoriaDiagnostico;
import modelo.maestros.Diagnostico;
import modelo.maestros.Medicina;
import modelo.seguridad.Arbol;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import arbol.CArbol;

import componentes.Botonera;
import componentes.Catalogo;

public class CDiagnostico extends CGenerico {

	private static final long serialVersionUID = -4299008134868657236L;
	@Wire
	private Div divDiagnostico;
	@Wire
	private Div botoneraDiagnostico;
	@Wire
	private Div catalogoDiagnostico;
	@Wire
	private Textbox txtNombreDiagnostico;
	@Wire
	private Textbox txtCodigoDiagnostico;
	@Wire
	private Textbox txtGrupoDiagnostico;
	@Wire
	private Combobox cmbCategoria;
	@Wire
	private Button btnBuscarDiagnostico;
	
	private CArbol cArbol = new CArbol();
	long id = 0;
	Catalogo<Diagnostico> catalogo;

	@Override
	public void inicializar() throws IOException {
		llenarComboCategoria();
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divDiagnostico, "Diagnostico");
			}

			@Override
			public void limpiar() {
				txtNombreDiagnostico.setValue("");
				txtCodigoDiagnostico.setValue("");
				txtGrupoDiagnostico.setValue("");
				cmbCategoria.setValue("");
				cmbCategoria.setPlaceholder("Seleccione una Categoria");
				id = 0;
			}

			@Override
			public void guardar() {
				if (validar()) {
					String nombre, codigo, grupo;
					nombre = txtNombreDiagnostico.getValue();
					codigo = txtCodigoDiagnostico.getValue();
					grupo = txtGrupoDiagnostico.getValue();
					CategoriaDiagnostico categoria = servicioCategoriaDiagnostico
							.buscar(Long.parseLong(cmbCategoria
									.getSelectedItem().getContext()));
					Diagnostico diagnostico = new Diagnostico(id, codigo,
							fechaHora, grupo, horaAuditoria, nombre,
							horaAuditoria, categoria);
					servicioDiagnostico.guardar(diagnostico);
					limpiar();
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
				}
			}

			@Override
			public void eliminar() {
				if (id != 0) {
					Messagebox.show("¿Esta Seguro de Eliminar el Diagnostico?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Diagnostico diagnostico = servicioDiagnostico
												.buscar(id);
										servicioDiagnostico
												.eliminar(diagnostico);
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
		botoneraDiagnostico.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtNombreDiagnostico.getText().compareTo("") == 0
				|| txtCodigoDiagnostico.getText().compareTo("") == 0
				|| txtGrupoDiagnostico.getText().compareTo("") == 0
				|| cmbCategoria.getText().compareTo("") == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else
			return true;
	}

	/* Muestra el catalogo de los diagnosticos */
	@Listen("onClick = #btnBuscarDiagnostico")
	public void mostrarCatalogo() {
		final List<Diagnostico> diagnosticos = servicioDiagnostico
				.buscarTodas();
		catalogo = new Catalogo<Diagnostico>(catalogoDiagnostico,
				"Catalogo de Diagnosticos", diagnosticos, "Codigo", "Nombre",
				"Grupo", "Categoria") {

			@Override
			protected List<Diagnostico> buscar(String valor, String combo) {

				switch (combo) {
				case "Nombre":
					return servicioDiagnostico.filtroNombre(valor);
				case "Codigo":
					return servicioDiagnostico.filtroCodigo(valor);
				case "Grupo":
					return servicioDiagnostico.filtroGrupo(valor);
				case "Categoria":
					return servicioDiagnostico.filtroCategoria(valor);
				default:
					return diagnosticos;
				}

			}

			@Override
			protected String[] crearRegistros(Diagnostico objeto) {
				String[] registros = new String[4];
				registros[0] = objeto.getCodigo();
				registros[1] = objeto.getNombre();
				registros[2] = objeto.getGrupo();
				registros[3] = objeto.getCategoria().getNombre();
				return registros;
			}

		};
		catalogo.setParent(catalogoDiagnostico);
		catalogo.doModal();
	}

	/* Llena el combo de Categorias cada vez que se abre */
	@Listen("onOpen = #cmbCategoria")
	public void llenarComboCategoria() {
		List<CategoriaDiagnostico> categorias = servicioCategoriaDiagnostico
				.buscarTodas();
		cmbCategoria.setModel(new ListModelList<CategoriaDiagnostico>(
				categorias));
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoDiagnostico")
	public void seleccinar() {
		Diagnostico diagnostico = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(diagnostico);
		catalogo.setParent(null);
	}

	/* Busca si existe un diagnostico con el mismo codigo escrito */
	@Listen("onChange = #txtCodigoDiagnostico")
	public void buscarPorNombre() {
		Diagnostico diagnostico = servicioDiagnostico
				.buscarPorCodigo(txtCodigoDiagnostico.getValue());
		if (diagnostico != null)
			llenarCampos(diagnostico);
	}

	/* LLena los campos del formulario dado un diagnostico */
	private void llenarCampos(Diagnostico diagnostico) {
		txtCodigoDiagnostico.setValue(diagnostico.getCodigo());
		txtGrupoDiagnostico.setValue(diagnostico.getGrupo());
		txtNombreDiagnostico.setValue(diagnostico.getNombre());
		cmbCategoria.setValue(diagnostico.getCategoria().getNombre());
		id = diagnostico.getIdDiagnostico();
	}
	
	/* Abre la vista de Categoria*/
	@Listen("onClick = #btnAbrirCategoria")
	public void abrirCategoria(){		
		Arbol arbolItem = servicioArbol.buscarPorNombreArbol("Categoria Diagnostico");
		cArbol.abrirVentanas(arbolItem);	
	}
}
