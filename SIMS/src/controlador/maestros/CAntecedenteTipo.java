package controlador.maestros;

import java.io.IOException;
import java.util.List;

import modelo.maestros.Antecedente;
import modelo.maestros.AntecedenteTipo;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;

public class CAntecedenteTipo extends CGenerico {


	@Wire
	private Radiogroup rdgAntecedenteTipo;
	@Wire
	private Radio rdoLaboral;
	@Wire
	private Radio rdoMedico;
	@Wire
	private Div botoneraAntecedenteTipo;
	@Wire
	private Textbox txtNombreAntecedenteTipo;
	@Wire
	private Button btnBuscarAntecedenteTipo;
	@Wire
	private Div catalogoAntecedenteTipo;
	@Wire
	private Div divAntecedenteTipo;
	private long id = 0;
	Catalogo<AntecedenteTipo> catalogo;
	
	@Override
	public void inicializar() throws IOException {
		Botonera botonera = new Botonera() {
			
			@Override
			public void salir() {
				cerrarVentana(divAntecedenteTipo, "Clasificacion de Antecedente");
			}
			
			@Override
			public void limpiar() {
				txtNombreAntecedenteTipo.setValue("");
				rdoLaboral.setChecked(false);
				rdoMedico.setChecked(false);
				id = 0;
			}
			
			@Override
			public void guardar() {
				if (validar()) {
					String nombre = txtNombreAntecedenteTipo.getValue();
					String tipo = "Medico";
					if (rdoLaboral.isChecked())
						tipo="Laboral";
					
					AntecedenteTipo antecedenteTipo = new AntecedenteTipo(id, nombre,tipo, fechaHora, horaAuditoria,
							nombreUsuarioSesion());
					servicioAntecedenteTipo.guardar(antecedenteTipo);
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
					limpiar();
				}
			}
			
			@Override
			public void eliminar() {
				if (id != 0 && txtNombreAntecedenteTipo.getText().compareTo("") != 0) {
					Messagebox.show("�Esta Seguro de Eliminar la Clasificacion de Antecedente?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										AntecedenteTipo antecedenteTipo = servicioAntecedenteTipo
												.buscar(id);
										List<Antecedente> antecedentes = servicioAntecedente
												.buscarPorAntecedenteTipo(antecedenteTipo);
										if (!antecedentes.isEmpty()) {
											Messagebox
													.show("No se Puede Eliminar el Registro, Esta siendo Utilizado",
															"Informacion",
															Messagebox.OK,
															Messagebox.INFORMATION);
										} else {
											servicioAntecedenteTipo.eliminar(antecedenteTipo);
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
		botoneraAntecedenteTipo.appendChild(botonera);
	}

	/* Permite validar que todos los campos esten completos */
	public boolean validar() {
		if (txtNombreAntecedenteTipo.getText().compareTo("") == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Alerta",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else
			return true;
	}
	
	/* Muestra el catalogo de los antecedenteTipoes */
	@Listen("onClick = #btnBuscarAntecedenteTipo")
	public void mostrarCatalogo() {
		final List<AntecedenteTipo> antecedenteTipos = servicioAntecedenteTipo.buscarTodos();
		catalogo = new Catalogo<AntecedenteTipo>(catalogoAntecedenteTipo, "Catalogo de Clasificaciones",
				antecedenteTipos, "Nombre","Clasificacion") {

			@Override
			protected List<AntecedenteTipo> buscar(String valor, String combo) {
				if (combo.equals("Nombre"))
					return servicioAntecedenteTipo.filtroNombre(valor);
				else
				{
					if (combo.equals("Clasificacion"))
						return servicioAntecedenteTipo.filtroTipo(valor);
					else
					return antecedenteTipos;
				}
			}

			@Override
			protected String[] crearRegistros(AntecedenteTipo estado) {
				String[] registros = new String[2];
				registros[0] = estado.getNombre();
				registros[1] = estado.getTipo();
				return registros;
			}
		};
		catalogo.setParent(catalogoAntecedenteTipo);
		catalogo.doModal();
	}

	/* Permite la seleccion de un item del catalogo */
	@Listen("onSeleccion = #catalogoAntecedenteTipo")
	public void seleccinar() {
		AntecedenteTipo antecedenteTipo = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(antecedenteTipo);
		catalogo.setParent(null);
	}

	/* Busca si existe un antecedenteTipo con el mismo nombre escrito */
	@Listen("onChange = #txtNombreAntecedenteTipo")
	public void buscarPorNombre() {
		AntecedenteTipo antecedenteTipo = servicioAntecedenteTipo.buscarPorNombre(txtNombreAntecedenteTipo
				.getValue());
		if (antecedenteTipo != null)
			llenarCampos(antecedenteTipo);
	}

	/* LLena los campos del formulario dado un antecedenteTipo */
	private void llenarCampos(AntecedenteTipo antecedenteTipo) {
		txtNombreAntecedenteTipo.setValue(antecedenteTipo.getNombre());
		if(antecedenteTipo.getTipo().equals("Medico"))
			rdoMedico.setChecked(true);
		else
			rdoLaboral.setChecked(true);
		id = antecedenteTipo.getIdAntecedenteTipo();
	}

}
