package controlador.maestros;

import java.io.IOException;
import java.util.List;

import modelo.maestros.Intervencion;
import modelo.maestros.ParteCuerpo;
import modelo.maestros.Vacuna;
import modelo.transacciones.ConsultaParteCuerpo;
import modelo.transacciones.HistoriaIntervencion;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;

public class COrgano extends CGenerico {

	private static final long serialVersionUID = -2045885360473336651L;
	@Wire
	private Div botoneraOrgano;
	@Wire
	private Textbox txtNombre;
	@Wire
	private Div catalogoOrgano;
	@Wire
	private Div divOrgano;
	private long id = 0;
	Catalogo<ParteCuerpo> catalogo;

	@Override
	public void inicializar() throws IOException {
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divOrgano, "Organo");
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
					ParteCuerpo parte = new ParteCuerpo(id, nombre, fechaHora,
							horaAuditoria, nombreUsuarioSesion());
					servicioParteCuerpo.guardar(parte);
					Messagebox.show("Registro Guardado Exitosamente",
							"Informacion", Messagebox.OK,
							Messagebox.INFORMATION);
					limpiar();
				}
			}

			@Override
			public void eliminar() {
				if (id != 0 && txtNombre.getText().compareTo("") != 0) {
					Messagebox.show("�Esta Seguro de Eliminar el Organo?",
							"Alerta", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION,
							new org.zkoss.zk.ui.event.EventListener<Event>() {
								public void onEvent(Event evt)
										throws InterruptedException {
									if (evt.getName().equals("onOK")) {
										ParteCuerpo parte = servicioParteCuerpo
												.buscar(id);
										List<ConsultaParteCuerpo> estados = servicioConsultaParteCuerpo
												.buscarPorParte(parte);
										if (!estados.isEmpty()) {
											Messagebox
													.show("No se Puede Eliminar el Registro, Esta siendo Utilizado",
															"Informacion",
															Messagebox.OK,
															Messagebox.INFORMATION);
										} else {
											servicioParteCuerpo.eliminar(parte);
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
		botoneraOrgano.appendChild(botonera);
	}

	protected boolean validar() {
		if (txtNombre.getText().compareTo("") == 0) {
			Messagebox.show("Debe Llenar Todos los Campos", "Informacion",
					Messagebox.OK, Messagebox.INFORMATION);
			return false;
		} else
			return true;
	}
	
	@Listen("onClick = #btnBuscarOrgano")
	public void mostrarCatalogo() {
		final List<ParteCuerpo> paises = servicioParteCuerpo.buscarTodos();
		catalogo = new Catalogo<ParteCuerpo>(catalogoOrgano, "Catalogo de Intervenciones",
				paises, "Nombre") {

			@Override
			protected List<ParteCuerpo> buscar(String valor, String combo) {
				if (combo.equals("Nombre"))
					return servicioParteCuerpo.filtroNombre(valor);
				else
					return paises;
			}

			@Override
			protected String[] crearRegistros(ParteCuerpo estado) {
				String[] registros = new String[1];
				registros[0] = estado.getNombre();
				return registros;
			}
		};
		catalogo.setParent(catalogoOrgano);
		catalogo.doModal();
	}

	@Listen("onSeleccion = #catalogoOrgano")
	public void seleccinar() {
		ParteCuerpo parte = catalogo.objetoSeleccionadoDelCatalogo();
		llenarCampos(parte);
		catalogo.setParent(null);
	}

	@Listen("onChange = #txtNombre")
	public void buscarPorNombre() {
		ParteCuerpo parte = servicioParteCuerpo.buscarPorNombre(txtNombre
				.getValue());
		if (parte != null)
			llenarCampos(parte);
	}

	private void llenarCampos(ParteCuerpo parte) {
		txtNombre.setValue(parte.getNombre());
		id = parte.getIdParte();
	}
}
