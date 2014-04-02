package controlador.maestros;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Div;
import org.zkoss.zul.Tab;

import servicio.maestros.SCategoriaDiagnostico;
import servicio.maestros.SCategoriaMedicina;
import servicio.maestros.SCiudad;
import servicio.maestros.SConsultorio;
import servicio.maestros.SDiagnostico;
import servicio.maestros.SEmpresa;
import servicio.maestros.SEspecialidad;
import servicio.maestros.SEspecialista;
import servicio.maestros.SEstado;
import servicio.maestros.SLaboratorio;
import servicio.maestros.SMedicina;
import servicio.maestros.SMedicinaPresentacionUnidad;
import servicio.maestros.SMotivoCita;
import servicio.maestros.SPaciente;
import servicio.maestros.SPais;
import servicio.maestros.SPresentacionComercial;
import servicio.maestros.SPresentacionMedicina;
import servicio.maestros.SServicioExterno;
import servicio.maestros.SUnidadMedicina;
import servicio.maestros.SUnidadUsuario;
import servicio.seguridad.SArbol;
import servicio.seguridad.SGrupo;
import servicio.seguridad.SUsuario;
import servicio.transacciones.SCita;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public abstract class CGenerico extends SelectorComposer<Component> {

	private static final long serialVersionUID = -2264423023637489596L;
	@WireVariable("SArbol")
	protected SArbol servicioArbol;
	@WireVariable("SCategoriaDiagnostico")
	protected SCategoriaDiagnostico servicioCategoriaDiagnostico;
	@WireVariable("SCategoriaMedicina")
	protected SCategoriaMedicina servicioCategoriaMedicina;
	@WireVariable("SCita")
	protected SCita servicioCita;
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
	@WireVariable("SEspecialista")
	protected SEspecialista servicioEspecialista;
	@WireVariable("SEstado")
	protected SEstado servicioEstado;
	@WireVariable("SLaboratorio")
	protected SLaboratorio servicioLaboratorio;
	@WireVariable("SMedicina")
	protected SMedicina servicioMedicina;
	@WireVariable("SMedicinaPresentacionUnidad")
	protected SMedicinaPresentacionUnidad servicioMedicinaPresentacionUnidad;
	@WireVariable("SMotivoCita")
	protected SMotivoCita servicioMotivoCita;
	@WireVariable("SPaciente")
	protected SPaciente servicioPaciente;
	@WireVariable("SPais")
	protected SPais servicioPais;
	@WireVariable("SPresentacionComercial")
	protected SPresentacionComercial servicioPresentacion;
	@WireVariable("SPresentacionMedicina")
	protected SPresentacionMedicina servicioPresentacionMedicina;
	@WireVariable("SServicioExterno")
	protected SServicioExterno servicioServicioExterno;
	@WireVariable("SUnidadUsuario")
	protected SUnidadUsuario servicioUnidadUsuario;
	@WireVariable("SUnidadMedicina")
	protected SUnidadMedicina servicioUnidadMedicina;
	@WireVariable("SGrupo")
	protected SGrupo servicioGrupo;
	@WireVariable("SUsuario")
	protected SUsuario servicioUsuario;
	
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
	
	/* Metodo que permite enviar un correo electronico a cualquier destinatario */
	public boolean enviarEmailNotificacion(String correo, String mensajes) {
		try {

			Properties props = new Properties();
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.auth", "true");

			Session session = Session.getDefaultInstance(props);
			String asunto = "Notificacion de SITEG";
			String remitente = "siteg.ucla@gmail.com";
			String contrasena = "Equipo.2";
			String destino = correo;
			String mensaje = mensajes;

			String destinos[] = destino.split(",");

			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(remitente));

			Address[] receptores = new Address[destinos.length];
			int j = 0;
			while (j < destinos.length) {
				receptores[j] = new InternetAddress(destinos[j]);
				j++;
			}

			message.addRecipients(Message.RecipientType.TO, receptores);
			message.setSubject(asunto);
			message.setText(mensaje);

			Transport t = session.getTransport("smtp");
			t.connect(remitente, contrasena);
			t.sendMessage(message,
					message.getRecipients(Message.RecipientType.TO));

			t.close();

			return true;
		}

		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}