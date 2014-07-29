package controlador.transacciones;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import modelo.generico.DetalleAccidente;
import modelo.maestros.Diagnostico;
import modelo.maestros.Especialista;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaEspecialista;
import modelo.transacciones.ConsultaExamen;
import modelo.transacciones.ConsultaServicioExterno;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import componentes.Botonera;
import componentes.Mensaje;

import controlador.maestros.CGenerico;

public class CResultado extends CGenerico {

	private static final long serialVersionUID = 4445427066505752688L;
	@Wire
	private Window wdwResultado;
	@Wire
	private Listbox ltbExamenes;
	@Wire
	private Listbox ltbEspecialistas;
	@Wire
	private Listbox ltbServicio;
	@Wire
	private Textbox txtObservacion;
	@Wire
	private Div botoneraResultado;
	long idConsulta = 0;
	Consulta consulta = new Consulta();
	List<ConsultaServicioExterno> listaConsultaServicio = new ArrayList<ConsultaServicioExterno>();
	List<ConsultaExamen> listaConsultaExamen = new ArrayList<ConsultaExamen>();
	List<ConsultaEspecialista> listaEspecialistas = new ArrayList<ConsultaEspecialista>();

	@Override
	public void inicializar() throws IOException {
		HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("consultaResultado");
		if (map != null) {
			if (map.get("idConsulta") != null) {
				idConsulta = (long) map.get("idConsulta");
				consulta = servicioConsulta.buscar(idConsulta);
				txtObservacion.setValue(consulta.getObservacion());
				List<ConsultaEspecialista> listaEspecialista = servicioConsultaEspecialista
						.buscarPorConsulta(consulta);
				listaEspecialistas.addAll(listaEspecialista);
				for (int i = 0; i < listaEspecialista.size(); i++) {
					String nombre = listaEspecialista.get(i).getEspecialista()
							.getNombre();
					String apellido = listaEspecialista.get(i)
							.getEspecialista().getApellido();
					Especialista especialista = listaEspecialista.get(i)
							.getEspecialista();
					especialista.setNombre(nombre + " " + apellido);
				}

				ltbEspecialistas
						.setModel(new ListModelList<ConsultaEspecialista>(
								listaEspecialista));
				listaConsultaExamen = servicioConsultaExamen
						.buscarPorConsulta(consulta);
				ltbExamenes.setModel(new ListModelList<ConsultaExamen>(
						listaConsultaExamen));

				listaConsultaServicio = servicioConsultaServicioExterno
						.buscarPorConsulta(consulta);
				ltbServicio
						.setModel(new ListModelList<ConsultaServicioExterno>(
								listaConsultaServicio));
				map.clear();
				map = null;
			}
		}

		Botonera botonera = new Botonera() {

			@Override
			public void salir() {
				limpiar();
				wdwResultado.onClose();
			}

			@Override
			public void limpiar() {
				listaConsultaExamen.clear();
				listaConsultaServicio.clear();
				listaEspecialistas.clear();
			}

			@Override
			public void guardar() {
				for (int i = 0; i < listaConsultaExamen.size(); i++) {
					Listitem listItem = ltbExamenes.getItemAtIndex(i);
					String resultado = ((Textbox) ((listItem.getChildren()
							.get(1))).getFirstChild()).getValue();
					listaConsultaExamen.get(i).setResultado(resultado);
				}
				servicioConsultaExamen.guardar(listaConsultaExamen);

				for (int i = 0; i < listaConsultaServicio.size(); i++) {
					Listitem listItem = ltbServicio.getItemAtIndex(i);
					String resultado = ((Textbox) ((listItem.getChildren()
							.get(2))).getFirstChild()).getValue();
					listaConsultaServicio.get(i).setResultado(resultado);
				}
				servicioConsultaServicioExterno.guardar(listaConsultaServicio);

				for (int i = 0; i < listaEspecialistas.size(); i++) {
					Listitem listItem = ltbEspecialistas.getItemAtIndex(i);
					String resultado = ((Textbox) ((listItem.getChildren()
							.get(2))).getFirstChild()).getValue();
					listaEspecialistas.get(i).setResultado(resultado);
				}
				servicioConsultaEspecialista.guardar(listaEspecialistas);

				consulta.setObservacion(txtObservacion.getValue());
				servicioConsulta.guardar(consulta);
				limpiar();
				msj.mensajeInformacion(Mensaje.guardado);
				salir();
			}

			@Override
			public void eliminar() {
				// TODO Auto-generated method stub

			}
		};
		botonera.getChildren().get(1).setVisible(false);
		botonera.getChildren().get(2).setVisible(false);
		botoneraResultado.appendChild(botonera);
	}

}
