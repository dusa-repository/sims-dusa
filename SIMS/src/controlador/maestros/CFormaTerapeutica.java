package controlador.maestros;

import java.io.IOException;
import java.util.List;

import modelo.maestros.FormaTerapeutica;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;

public class CFormaTerapeutica extends CGenerico {

	private static final long serialVersionUID = -1626088569444819807L;
	
	@Wire
	private Textbox txtNombre;
	@Wire
	private Div botoneraFormaTerapeutica;
	@Wire
	private Div catalogoFormaTerapeutica;
	@Wire
	private Div divFormaTerapeutica;
	@Wire
	private Button btnBuscarFormaTerapeutica;

	Catalogo<FormaTerapeutica> catalogo;
	
	@Override
	public void inicializar() throws IOException {
		Botonera botonera = new Botonera() {
			@Override
			public void guardar() {

				if (validar()) {
					String nombre = txtNombre.getValue();
				
					FormaTerapeutica formaTerapeutica = new FormaTerapeutica(0, fechaHora,
							horaAuditoria, nombre, nombreUsuarioSesion());
					servicioFormaTerapeutica.guardar(formaTerapeutica);
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
					limpiar();
				}

			}

			@Override
			public void limpiar() {
				txtNombre.setText("");
			}

			@Override
			public void salir() {
				cerrarVentana(divFormaTerapeutica);
			}

			@Override
			public void eliminar() {

			}
		};
		/* Dibuja el componente botonera en el div botonera FormaTerapeutica */
		botoneraFormaTerapeutica.appendChild(botonera);
		
	}
	

	/* Muestra un catalogo de formas terapeuticas */
	@Listen("onClick = #btnBuscarFormaTerapeutica")
	public void mostrarCatalogo() throws IOException {
		List<FormaTerapeutica> formaTerapeuticas = servicioFormaTerapeutica.buscarTodos();
		catalogo = new Catalogo<FormaTerapeutica>(catalogoFormaTerapeutica,
				"Catalogo de Formas Terapeuticas", formaTerapeuticas, "Nombre") {

			@Override
			protected String[] crearRegistros(FormaTerapeutica formaTerapeutica) {
				String[] registros = new String[1];
				registros[0] = formaTerapeutica.getNombre();
				return registros;
			}

			@Override
			protected List<FormaTerapeutica> buscar(String valor) {
				// return
				// servicioPresentacion.buscarCualquierCampoContiene(valor);
				return null;
			}
		};
		catalogo.setParent(catalogoFormaTerapeutica);
		catalogo.doModal();
	}

	/*Validaciones de pantalla para poder realizar el guardar*/
	public boolean validar() {

		if (txtNombre.getText().compareTo("")==0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else
			return true;
	}
}
