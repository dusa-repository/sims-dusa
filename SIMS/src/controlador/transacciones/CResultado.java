package controlador.transacciones;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.maestros.Especialista;
import modelo.maestros.Paciente;
import modelo.seguridad.Usuario;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaDiagnostico;
import modelo.transacciones.ConsultaEspecialista;
import modelo.transacciones.ConsultaExamen;
import modelo.transacciones.ConsultaServicioExterno;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
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
	
	@Listen("onClick = #btnReporteResultados")
	public void generarReporteResultadoConsulta() {
					Long id = consulta.getIdConsulta();
					Clients.evalJavaScript("window.open('"
							+ damePath()
							+ "Reportero?valor=53&valor2="
							+ id
							+ "','','top=100,left=200,height=600,width=800,scrollbars=1,resizable=1')");
				}

	public byte[] reporteResultado(Long part2) throws JRException {
		byte[] fichero = null;
		Consulta consuta = getServicioConsulta().buscar(part2);
		List<ConsultaDiagnostico> diagnosticoConsulta = getServicioConsultaDiagnostico()
				.buscarPorConsulta(consuta);
	
		List<ConsultaExamen> examenes = getServicioConsultaExamen()
				.buscarPorConsulta(consuta);
		List<ConsultaEspecialista> especialistas = getServicioConsultaEspecialista()
				.buscarPorConsulta(consuta);
		List<ConsultaServicioExterno> estudis = getServicioConsultaServicioExterno()
				.buscarPorConsulta(consuta);
		String nombreDiagnostico = "";
		String tipoDiagnostico = "";
		if (!diagnosticoConsulta.isEmpty()) {
			nombreDiagnostico = diagnosticoConsulta.get(0).getDiagnostico()
					.getNombre();
			tipoDiagnostico = diagnosticoConsulta.get(0).getTipo();
			diagnosticoConsulta.remove(0);
		}

		Paciente paciente = consuta.getPaciente();
		Usuario user = consuta.getUsuario();
		Map p = new HashMap();
		String nombreEmpresa = "DESTILERIAS UNIDAS 	S.A.";
		String direccionEmpresa = "";
		String rifEmpresa = "J-30940783-0";
		if (paciente.getEmpresa() != null) {
			nombreEmpresa = paciente.getEmpresa().getNombre();
			direccionEmpresa = paciente.getEmpresa().getDireccionCentro();
			rifEmpresa = paciente.getEmpresa().getRif();
		}
		p.put("empresaNombre", nombreEmpresa);
		p.put("empresaDireccion", direccionEmpresa);
		p.put("empresaRif", rifEmpresa);
		p.put("pacienteNombre",
				paciente.getPrimerNombre() + "   "
						+ paciente.getSegundoNombre());
		p.put("pacienteApellido", paciente.getPrimerApellido() + "   "
				+ paciente.getSegundoApellido());
		p.put("pacienteCedula", paciente.getCedula());
		p.put("pacienteNacimiento", paciente.getFechaNacimiento());
		p.put("doctorNombre", consuta.getDoctor());
		p.put("fechaConsulta", consuta.getFechaConsulta());
		p.put("tipoConsulta", consuta.getTipoConsultaSecundaria());
		p.put("enfermedad", consuta.getEnfermedadActual());
		p.put("diagnostico", nombreDiagnostico);
		p.put("tipoDiagnostico", tipoDiagnostico);
		p.put("edad",
				String.valueOf(calcularEdad(paciente.getFechaNacimiento())));
		p.put("dataExamen", new JRBeanCollectionDataSource(examenes));
		p.put("dataEspecialista", new JRBeanCollectionDataSource(especialistas));
		p.put("dataEstudio", new JRBeanCollectionDataSource(estudis));

		JasperReport reporte = (JasperReport) JRLoader.loadObject(getClass()
				.getResource("/reporte/RResultadosConsulta.jasper"));

		fichero = JasperRunManager.runReportToPdf(reporte, p,
				new JRBeanCollectionDataSource(diagnosticoConsulta));
		return fichero;
	}


}
