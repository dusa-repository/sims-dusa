package controlador.maestros;

import java.io.IOException;
import java.util.List;

import modelo.maestros.Medicina;
import modelo.maestros.Presentacion;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;

public class CPresentacion extends CGenerico {

	private static final long serialVersionUID = 6414734962746880324L;

	@Wire
	private Textbox txtNombre;
	@Wire
	private Div botoneraPresentacion;
	@Wire
	private Div catalogoPresentacion;
	@Wire
	private Div divPresentacion;
	@Wire
	private Button btnBuscarPresentacion;
	@Wire
	private Combobox cmbMedicina;

	Catalogo<Presentacion> catalogo;
	long id =0;

	@Override
	public void inicializar() throws IOException {
		
		comboMedicina();
		Botonera botonera = new Botonera() {
			@Override
			public void guardar() {

				if (validar()) {
					String nombre = txtNombre.getValue();
					long idMedicina = Long.valueOf(cmbMedicina.getSelectedItem().getId());
					Medicina medicina = servicioMedicina.buscar(idMedicina);
					Presentacion presentacion = new Presentacion(id, fechaHora,
							horaAuditoria, nombre, nombreUsuarioSesion(),
							medicina);
					servicioPresentacion.guardar(presentacion);
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
					
					limpiar();
				}

			}

			@Override
			public void limpiar() {			
				txtNombre.setText("");
				cmbMedicina.setValue("");
				cmbMedicina.setPlaceholder("Seleccione una Medicina");
				id=0;
			}

			@Override
			public void salir() {
				cerrarVentana(divPresentacion);
			}

			@Override
			public void eliminar() {

			}
		};
		/* Dibuja el componente botonera en el div botoneraPresentacionr */
		botoneraPresentacion.appendChild(botonera);
	}

	/* Muestra un catalogo de presentaciones */
	@Listen("onClick = #btnBuscarPresentacion")
	public void mostrarCatalogo() throws IOException {
		List<Presentacion> presentaciones = servicioPresentacion.buscarTodas();
		catalogo = new Catalogo<Presentacion>(catalogoPresentacion,
				"Catalogo de Presentaciones", presentaciones, "Nombre", "Medicina") {

			@Override
			protected String[] crearRegistros(Presentacion presentacion) {
				String[] registros = new String[2];
				registros[0] = presentacion.getNombre();
				registros[1] = presentacion.getMedicina().getNombre();
				return registros;
			}

			@Override
			protected List<Presentacion> buscar(String valor) {
				// return
				// servicioPresentacion.buscarCualquierCampoContiene(valor);
				return null;
			}
		};
		catalogo.setParent(catalogoPresentacion);
		catalogo.doModal();
	}

	/* Llena el combo de medicinas */
	public void comboMedicina() {
		List<Medicina> medicina = servicioMedicina.buscarTodas();
		cmbMedicina.setModel(new ListModelList<Medicina>(medicina));
	}

	/*Validaciones de pantalla para poder realizar el guardar*/
	public boolean validar() {

		if (cmbMedicina.getText().compareTo("")== 0
				|| txtNombre.getText().compareTo("")==0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else
			return true;
	}
	
	/* Busca si existe una presentacion con el mismo nombre escrito */
	@Listen("onChange = #txtNombre")
	public void buscarPorNombre() {
		Presentacion presentacion = servicioPresentacion.buscarPorNombre(txtNombre.getValue());
		if(presentacion!=null)
		llenarCampos(presentacion);
	}

	/* Selecciona una presentacion del catalogo y llena los campos con la informacion */
	@Listen("onSeleccion = #catalogoPresentacion")
	public void seleccion() {
		Presentacion presentacion = catalogo
				.objetoSeleccionadoDelCatalogo();
		llenarCampos(presentacion);
		catalogo.setParent(null);
	}
	
	/* LLena los campos del formulario dada una presentacion */
	public void llenarCampos(Presentacion presentacion) {
		txtNombre.setValue(presentacion.getNombre());
		cmbMedicina.setValue(presentacion.getMedicina().getNombre());
		id = presentacion.getIdPresentacion();
	}
}
