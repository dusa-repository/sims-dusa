package controlador.maestros;

import java.io.IOException;
import java.util.List;

import modelo.maestros.Estado;
import modelo.maestros.Pais;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;

public class CPais extends CGenerico {

	private static final long serialVersionUID = -2316998060132992709L;
	@Wire
	private Div botoneraPais;
	@Wire
	private Textbox txtNombrePais;
	@Wire
	private Button btnBuscarPais;
	@Wire
	private Div catalogoPais;
	@Wire
	private Div divPais;
	private long id = 0;
	Catalogo<Pais> catalogo;
	
	@Override
	public void inicializar() throws IOException {
		Botonera botonera = new Botonera() {
			
			@Override
			public void salir() {
				cerrarVentana(divPais, "Pais");
			}
			
			@Override
			public void limpiar() {
				txtNombrePais.setValue("");
				id = 0;
			}
			
			@Override
			public void guardar() {
				if (validar()) {
					String nombre = txtNombrePais.getValue();
					Pais pais = new Pais(id, fechaHora, horaAuditoria,
							nombre, nombreUsuarioSesion());
					servicioPais.guardar(pais);
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
					limpiar();
				}
			}
			
			@Override
			public void eliminar() {
				if (id != 0 && txtNombrePais.getText().compareTo("") != 0) {
					Messagebox.show("�Esta Seguro de Eliminar el Pais?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										Pais pais = servicioPais
												.buscar(id);
										List<Estado> estados = servicioEstado
												.buscarPorPais(pais);
										if (!estados.isEmpty()) {
											Messagebox
													.show("No se Puede Eliminar el Registro, Esta siendo Utilizado",
															"Informacion",
															Messagebox.OK,
															Messagebox.INFORMATION);
										} else {
											servicioPais.eliminar(pais);
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
		botoneraPais.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtNombrePais.getText().compareTo("") == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else
			return true;
	}
	
	/* Muestra el catalogo de los paises */
	@Listen("onClick = #btnBuscarPais")
	public void mostrarCatalogo() {
		final List<Pais> paises = servicioPais.buscarTodos();
		catalogo = new Catalogo<Pais>(catalogoPais, "Catalogo de Paises",
				paises, "Nombre") {

			@Override
			protected List<Pais> buscar(String valor, String combo) {
				if (combo.equals("Nombre"))
					return servicioPais.filtroNombre(valor);
				else
					return paises;
			}

			@Override
			protected String[] crearRegistros(Pais estado) {
				String[] registros = new String[1];
				registros[0] = estado.getNombre();
				return registros;
			}
		};
		catalogo.setParent(catalogoPais);
		catalogo.doModal();
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoPais")
	public void seleccinar() {
		Pais pais = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(pais);
		catalogo.setParent(null);
	}

	/* Busca si existe un pais con el mismo nombre escrito */
	@Listen("onChange = #txtNombrePais")
	public void buscarPorNombre() {
		Pais pais = servicioPais.buscarPorNombre(txtNombrePais
				.getValue());
		if (pais != null)
			llenarCampos(pais);
	}

	/* LLena los campos del formulario dado un pais */
	private void llenarCampos(Pais pais) {
		txtNombrePais.setValue(pais.getNombre());
		id = pais.getIdPais();
	}
}