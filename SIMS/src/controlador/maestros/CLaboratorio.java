package controlador.maestros;

import java.io.IOException;
import java.util.List;

import modelo.maestros.Laboratorio;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;

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

	public void inicializar() throws IOException {
		Botonera botonera = new Botonera() {
			@Override
			public void guardar() {

				if (validar()) {
					String nombre = txtNombre.getValue();

					Laboratorio laboratorio = new Laboratorio(0, fechaHora,
							horaAuditoria, nombre, nombreUsuarioSesion());
					servicioLaboratorio.guardar(laboratorio);
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
				cerrarVentana(divLaboratorio);
			}

			@Override
			public void eliminar() {

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
				"Catalogo de Laboratorios", laboratorios, "Nombre") {

			@Override
			protected String[] crearRegistros(Laboratorio laboratorio) {
				String[] registros = new String[1];
				registros[0] = laboratorio.getNombre();
				return registros;
			}

			@Override
			protected List<Laboratorio> buscar(String valor) {
				// return
				// servicioPresentacion.buscarCualquierCampoContiene(valor);
				return null;
			}
		};
		catalogo.setParent(catalogoLaboratorio);
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
