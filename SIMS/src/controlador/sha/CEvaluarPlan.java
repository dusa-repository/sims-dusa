package controlador.sha;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import modelo.sha.Informe;
import modelo.sha.PlanAccion;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Textbox;

import componentes.Botonera;
import componentes.Catalogo;
import componentes.Mensaje;

import controlador.maestros.CGenerico;

public class CEvaluarPlan extends CGenerico {

	private static final long serialVersionUID = 7559317552419976346L;
	@Wire
	private Div botoneraEvaluar;
	@Wire
	private Div divEvaluar;
	@Wire
	private Div divCatalogoInforme;
	@Wire
	private Label lblCodigo;
	@Wire
	private Label lblTipo;
	@Wire
	private Label lblDescripcion;
	@Wire
	private Label lblFicha;
	@Wire
	private Label lblCedula;
	@Wire
	private Label lblNombre;
	@Wire
	private Datebox dtbFecha;
	@Wire
	private Listbox ltbAcciones;
	@Wire
	private Listbox ltbAccionesInspector;
	@Wire
	private Label lblFuncionario;
	@Wire
	private Label lblFechaVisita;
	@Wire
	private Label lblOrdenamientos;
	private Catalogo<Informe> catalogo;
	private Long codigoInforme = null;
	private String nombre;

	@Override
	public void inicializar() throws IOException {
		HashMap<String, Object> mapa = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("mapaGeneral");
		if (mapa != null) {
			if (mapa.get("tabsGenerales") != null) {
				tabs = (List<Tab>) mapa.get("tabsGenerales");
				nombre = (String) mapa.get("titulo");
				mapa.clear();
				mapa = null;
			}
		}
		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				cerrarVentana(divEvaluar, nombre, tabs);
			}

			@Override
			public void limpiar() {
				lblCedula.setValue("");
				lblCodigo.setValue("");
				lblDescripcion.setValue("");
				lblFicha.setValue("");
				lblNombre.setValue("");
				lblTipo.setValue("");
				dtbFecha.setValue(null);
				codigoInforme = null;
				ltbAcciones.getItems().clear();
				ltbAccionesInspector.getItems().clear();
				lblOrdenamientos.setValue("");
				lblFuncionario.setValue("");
				lblFechaVisita.setValue("");
			}

			@Override
			public void guardar() {
				if (codigoInforme == null)
					Mensaje.mensajeError(Mensaje.camposVacios);
				else {
					ltbAcciones.renderAll();
					ltbAccionesInspector.renderAll();
					if (validarLista() && validarLista2()) {
						List<PlanAccion> planes = new ArrayList<PlanAccion>();
						if (ltbAcciones.getItemCount() != 0) {
							for (int i = 0; i < ltbAcciones.getItemCount(); i++) {
								Listitem listItem = ltbAcciones
										.getItemAtIndex(i);
								PlanAccion plan = listItem.getValue();
								if (listItem.isSelected()) {
									Textbox text = (Textbox) listItem
											.getChildren().get(6).getChildren()
											.get(0);
									String observacion = text.getValue();
									Datebox date = (Datebox) listItem
											.getChildren().get(5).getChildren()
											.get(0);
									Date fecha = date.getValue();
									plan.setEstado("Finalizado");
									plan.setObservacion(observacion);
									plan.setFechaCumplido(new Timestamp(fecha
											.getTime()));
								} else {
									plan.setEstado("Programado");
									plan.setObservacion(null);
									plan.setFechaCumplido(null);
								}
								plan.setFechaAuditoria(fechaHora);
								plan.setHoraAuditoria(horaAuditoria);
								plan.setUsuarioAuditoria(nombreUsuarioSesion());
								planes.add(plan);
							}
							servicioPlanAccion.guardarVarios(planes);
						}
						
						List<PlanAccion> planes2 = new ArrayList<PlanAccion>();
						if (ltbAccionesInspector.getItemCount() != 0) {
							for (int i = 0; i < ltbAccionesInspector.getItemCount(); i++) {
								Listitem listItem = ltbAccionesInspector
										.getItemAtIndex(i);
								PlanAccion plan = listItem.getValue();
								if (listItem.isSelected()) {
									Textbox text = (Textbox) listItem
											.getChildren().get(6).getChildren()
											.get(0);
									String observacion = text.getValue();
									Datebox date = (Datebox) listItem
											.getChildren().get(5).getChildren()
											.get(0);
									Date fecha = date.getValue();
									plan.setEstado("Finalizado");
									plan.setObservacion(observacion);
									plan.setFechaCumplido(new Timestamp(fecha
											.getTime()));
								} else {
									plan.setEstado("Programado");
									plan.setObservacion(null);
									plan.setFechaCumplido(null);
								}
								plan.setFechaAuditoria(fechaHora);
								plan.setHoraAuditoria(horaAuditoria);
								plan.setUsuarioAuditoria(nombreUsuarioSesion());
								planes2.add(plan);
							}
							servicioPlanAccion.guardarVarios(planes2);
						}
						Mensaje.mensajeInformacion(Mensaje.guardado);
						limpiar();
					} else
						Mensaje.mensajeError("Debe indicar la fecha en que se cumplieron las Acciones "
								+ "que ha seleccionado como cumplidas");

				}
			}

			@Override
			public void eliminar() {
			}
		};
		botonera.getChildren().get(1).setVisible(false);
		botoneraEvaluar.appendChild(botonera);
	}

	protected boolean validarLista() {
		boolean noVacio = true;
		for (int i = 0; i < ltbAcciones.getItemCount(); i++) {
			Listitem listItem = ltbAcciones.getItemAtIndex(i);
			if (listItem.isSelected()) {
				Datebox date = (Datebox) listItem.getChildren().get(5)
						.getChildren().get(0);
				Date fecha = date.getValue();
				if (fecha == null)
					noVacio = false;
			}
		}
		return noVacio;
	}
	

	protected boolean validarLista2() {
		boolean noVacio = true;
		for (int i = 0; i < ltbAccionesInspector.getItemCount(); i++) {
			Listitem listItem = ltbAccionesInspector.getItemAtIndex(i);
			if (listItem.isSelected()) {
				Datebox date = (Datebox) listItem.getChildren().get(5)
						.getChildren().get(0);
				Date fecha = date.getValue();
				if (fecha == null)
					noVacio = false;
			}
		}
		return noVacio;
	}

	@Listen("onClick =  #btnBuscar")
	public void buscarInforme() {
		final List<Informe> informes = servicioInforme.buscarConCodigo();
		catalogo = new Catalogo<Informe>(divCatalogoInforme,
				"Catalogo de Informes", informes, false, "Codigo",
				"Nombre Trabajador", "Apellido Trabajador", "Empresa") {

			@Override
			protected List<Informe> buscar(String valor, String combo) {

				switch (combo) {
				case "Codigo":
					return servicioInforme.filtroCodigo(valor);
				case "Nombre Trabajador":
					return servicioInforme.filtroNombreTrabajador(valor);
				case "Apellido Trabajador":
					return servicioInforme.filtroApellidoTrabajador(valor);
				case "Empresa":
					return servicioInforme.filtroEmpresa(valor);
				default:
					return informes;
				}

			}

			@Override
			protected String[] crearRegistros(Informe objeto) {
				String nombreEmpresa = "";
				if (objeto.getEmpresaA() != null)
					nombreEmpresa = objeto.getEmpresaA().getNombre();
				String[] registros = new String[4];
				registros[0] = objeto.getCodigo();
				registros[1] = objeto.getPacienteA().getPrimerNombre();
				registros[2] = objeto.getPacienteA().getPrimerApellido();
				registros[3] = nombreEmpresa;
				return registros;
			}

		};
		catalogo.setParent(divCatalogoInforme);
		catalogo.doModal();
	}

	@Listen("onSeleccion = #divCatalogoInforme")
	public void seleccion() {
		Informe informe = catalogo.objetoSeleccionadoDelCatalogo();
		ltbAcciones.getItems().clear();
		List<PlanAccion> planes = servicioPlanAccion.buscarPorInformeyTipo(informe,"normal");
		if (!planes.isEmpty()) {
			ltbAcciones.setModel(new ListModelList<PlanAccion>(planes));
			ltbAcciones.setCheckmark(false);
			ltbAcciones.setMultiple(false);
			ltbAcciones.setCheckmark(true);
			ltbAcciones.setMultiple(true);
			ltbAcciones.renderAll();
			if (ltbAcciones.getItemCount() != 0) {
				for (int i = 0; i < ltbAcciones.getItemCount(); i++) {
					Listitem listItem = ltbAcciones.getItemAtIndex(i);
					PlanAccion plan = listItem.getValue();
					if (plan.getEstado().equals("Finalizado"))
						listItem.setSelected(true);
					else
						listItem.setSelected(false);
				}
			}
		}
		
		ltbAccionesInspector.getItems().clear();
		List<PlanAccion> planes2 = servicioPlanAccion.buscarPorInformeyTipo(informe,"inspector");
		if (!planes.isEmpty()) {
			ltbAccionesInspector.setModel(new ListModelList<PlanAccion>(planes2));
			ltbAccionesInspector.setCheckmark(false);
			ltbAccionesInspector.setMultiple(false);
			ltbAccionesInspector.setCheckmark(true);
			ltbAccionesInspector.setMultiple(true);
			ltbAccionesInspector.renderAll();
			if (ltbAccionesInspector.getItemCount() != 0) {
				for (int i = 0; i < ltbAccionesInspector.getItemCount(); i++) {
					Listitem listItem = ltbAccionesInspector.getItemAtIndex(i);
					PlanAccion plan = listItem.getValue();
					if (plan.getEstado().equals("Finalizado"))
						listItem.setSelected(true);
					else
						listItem.setSelected(false);
				}
			}
		}
		lblOrdenamientos.setValue(informe.getOrdenamientos());
		lblFuncionario.setValue(informe.getFuncionario());
		if (informe.getFechaVisita() != null)
			lblFechaVisita.setValue(formatoFecha.format(informe.getFechaVisita()));
		lblCodigo.setValue(informe.getCodigo());
		lblCedula.setValue(informe.getPacienteA().getCedula());
		lblFicha.setValue(informe.getPacienteA().getFicha());
		lblNombre.setValue(informe.getPacienteA().getPrimerNombre() + " "
				+ informe.getPacienteA().getPrimerApellido());
		lblDescripcion.setValue(informe.getFgad());
		lblTipo.setValue(informe.getFga());
		dtbFecha.setValue(informe.getFa());
		codigoInforme = informe.getIdInforme();
		catalogo.setParent(null);
	}
}
