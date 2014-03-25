package controlador.maestros;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Div;
import org.zkoss.zul.Tab;

import servicio.maestros.SCategoria;
import servicio.maestros.SCiudad;
import servicio.maestros.SConsultorio;
import servicio.maestros.SDiagnostico;
import servicio.maestros.SEmpresa;
import servicio.maestros.SEspecialidad;
import servicio.maestros.SEstado;
import servicio.maestros.SFormaTerapeutica;
import servicio.maestros.SLaboratorio;
import servicio.maestros.SMedicina;
import servicio.maestros.SMotivoCita;
import servicio.maestros.SPaciente;
import servicio.maestros.SPresentacion;
import servicio.maestros.SUnidad;
import servicio.seguridad.SArbol;
import servicio.seguridad.SGrupo;
import servicio.seguridad.SUsuario;
import servicio.transacciones.SCita;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public abstract class CGenerico extends SelectorComposer<Component> {

	private static final long serialVersionUID = -2264423023637489596L;
	@WireVariable("SArbol")
	protected SArbol servicioArbol;
	@WireVariable("SCategoria")
	protected SCategoria servicioCategoria;
	@WireVariable("SCiudad")
	protected SCiudad servicioCiudad;
	@WireVariable("SConsultorio")
	protected SConsultorio servicioConsultorio;
	@WireVariable("SDiagnostico")
	protected SDiagnostico servicioDiagnostico;
	@WireVariable("SEmpresa")
	protected SEmpresa servicioEmpresa;
	@WireVariable("SEspecialidad")
	protected SEspecialidad servicioEspecialidad;
	@WireVariable("SEstado")
	protected SEstado servicioEstado;
	@WireVariable("SFormaTerapeutica")
	protected SFormaTerapeutica servicioFormaTerapeutica;
	@WireVariable("SLaboratorio")
	protected SLaboratorio servicioLaboratorio;
	@WireVariable("SMedicina")
	protected SMedicina servicioMedicina;
	@WireVariable("SMotivoCita")
	protected SMotivoCita servicioMotivoCita;
	@WireVariable("SPaciente")
	protected SPaciente servicioPaciente;
	@WireVariable("SPresentacion")
	protected SPresentacion servicioPresentacion;
	@WireVariable("SUnidad")
	protected SUnidad servicioUnidad;
	@WireVariable("SGrupo")
	protected SGrupo servicioGrupo;
	@WireVariable("SUsuario")
	protected SUsuario servicioUsuario;
	@WireVariable("SCita")
	protected SCita servicioCita;
	
	public static  List<Tab> tabs = new ArrayList<Tab>();
	public final Calendar calendario = Calendar.getInstance();
	public String horaAuditoria = String.valueOf(calendario
			.get(Calendar.HOUR_OF_DAY))
			+ ":"
			+ String.valueOf(calendario.get(Calendar.MINUTE))
			+ ":"
			+ String.valueOf(calendario.get(Calendar.SECOND));
	public java.util.Date fecha = new Date();
	public Timestamp fechaHora = new Timestamp(fecha.getTime());

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		inicializar();
	}

	public abstract void inicializar() throws IOException;

	public void cerrarVentana(Div div, String id) {
		div.setVisible(false);
		for(int i =0; i<tabs.size();i++){
			if(tabs.get(i).getLabel().equals(id)){
				if(i==(tabs.size()-1)&& tabs.size()>1){
					tabs.get(i-1).setSelected(true);
				}
				tabs.get(i).onClose();
				tabs.remove(i);
			}
		}
	}

	public String nombreUsuarioSesion() {
		Authentication sesion = SecurityContextHolder.getContext()
				.getAuthentication();
		return sesion.getName();
	}
	
}