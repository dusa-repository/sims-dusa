package controlador.maestros;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import modelo.maestros.Paciente;
import modelo.seguridad.Usuario;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;

import servicio.maestros.SAccidente;
import servicio.maestros.SAntecedente;
import servicio.maestros.SAntecedenteTipo;
import servicio.maestros.SCargo;
import servicio.maestros.SCategoriaDiagnostico;
import servicio.maestros.SCategoriaMedicina;
import servicio.maestros.SCiudad;
import servicio.maestros.SConsultorio;
import servicio.maestros.SDiagnostico;
import servicio.maestros.SEmpresa;
import servicio.maestros.SEspecialidad;
import servicio.maestros.SEspecialista;
import servicio.maestros.SEstado;
import servicio.maestros.SExamen;
import servicio.maestros.SIntervencion;
import servicio.maestros.SLaboratorio;
import servicio.maestros.SMedicina;
import servicio.maestros.SMedicinaPresentacionUnidad;
import servicio.maestros.SMotivoCita;
import servicio.maestros.SNomina;
import servicio.maestros.SPaciente;
import servicio.maestros.SPacienteAntecedente;
import servicio.maestros.SPais;
import servicio.maestros.SParteCuerpo;
import servicio.maestros.SPresentacionComercial;
import servicio.maestros.SPresentacionMedicina;
import servicio.maestros.SProveedor;
import servicio.maestros.SProveedorExamen;
import servicio.maestros.SProveedorServicio;
import servicio.maestros.SRecipe;
import servicio.maestros.SServicioExterno;
import servicio.maestros.SUnidadMedicina;
import servicio.maestros.SUnidadUsuario;
import servicio.maestros.SVacuna;
import servicio.seguridad.SArbol;
import servicio.seguridad.SGrupo;
import servicio.seguridad.SUsuario;
import servicio.sha.SArea;
import servicio.sha.SClasificacionAccidente;
import servicio.sha.SCondicion;
import servicio.sha.SInforme;
import servicio.transacciones.SCita;
import servicio.transacciones.SConsulta;
import servicio.transacciones.SConsultaDiagnostico;
import servicio.transacciones.SConsultaEspecialista;
import servicio.transacciones.SConsultaExamen;
import servicio.transacciones.SConsultaMedicina;
import servicio.transacciones.SConsultaParteCuerpo;
import servicio.transacciones.SConsultaServicioExterno;
import servicio.transacciones.SHistoria;
import servicio.transacciones.SHistoriaAccidente;
import servicio.transacciones.SHistoriaIntervencion;
import servicio.transacciones.SHistoriaVacuna;

import componentes.Mensaje;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public abstract class CGenerico extends SelectorComposer<Component> {

	private static final long serialVersionUID = -2264423023637489596L;
	@WireVariable("SAccidente")
	protected SAccidente servicioAccidente;
	@WireVariable("SAntecedente")
	protected SAntecedente servicioAntecedente;
	@WireVariable("SAntecedenteTipo")
	protected SAntecedenteTipo servicioAntecedenteTipo;
	@WireVariable("SArbol")
	protected SArbol servicioArbol;
	@WireVariable("SCargo")
	protected SCargo servicioCargo;
	@WireVariable("SCategoriaDiagnostico")
	protected SCategoriaDiagnostico servicioCategoriaDiagnostico;
	@WireVariable("SCategoriaMedicina")
	protected SCategoriaMedicina servicioCategoriaMedicina;
	@WireVariable("SCita")
	protected SCita servicioCita;
	@WireVariable("SCiudad")
	protected SCiudad servicioCiudad;
	@WireVariable("SCondicion")
	protected SCondicion servicioCondicion;
	@WireVariable("SConsultorio")
	protected SConsultorio servicioConsultorio;
	@WireVariable("SConsulta")
	protected SConsulta servicioConsulta;
	@WireVariable("SConsultaParteCuerpo")
	protected SConsultaParteCuerpo servicioConsultaParteCuerpo;
	@WireVariable("SConsultaDiagnostico")
	protected SConsultaDiagnostico servicioConsultaDiagnostico;
	@WireVariable("SConsultaEspecialista")
	protected SConsultaEspecialista servicioConsultaEspecialista;
	@WireVariable("SConsultaExamen")
	protected SConsultaExamen servicioConsultaExamen;
	@WireVariable("SConsultaMedicina")
	protected SConsultaMedicina servicioConsultaMedicina;
	@WireVariable("SConsultaServicioExterno")
	protected SConsultaServicioExterno servicioConsultaServicioExterno;
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
	@WireVariable("SExamen")
	protected SExamen servicioExamen;
	@WireVariable("SHistoria")
	protected SHistoria servicioHistoria;
	@WireVariable("SHistoriaAccidente")
	protected SHistoriaAccidente servicioHistoriaAccidente;
	@WireVariable("SHistoriaIntervencion")
	protected SHistoriaIntervencion servicioHistoriaIntervencion;
	@WireVariable("SHistoriaVacuna")
	protected SHistoriaVacuna servicioHistoriaVacuna;
	@WireVariable("SInforme")
	protected SInforme servicioInforme;
	@WireVariable("SIntervencion")
	protected SIntervencion servicioIntervencion;
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
	@WireVariable("SPacienteAntecedente")
	protected SPacienteAntecedente servicioPacienteAntecedente;
	@WireVariable("SPais")
	protected SPais servicioPais;
	@WireVariable("SParteCuerpo")
	protected SParteCuerpo servicioParteCuerpo;
	@WireVariable("SPresentacionComercial")
	protected SPresentacionComercial servicioPresentacion;
	@WireVariable("SPresentacionMedicina")
	protected SPresentacionMedicina servicioPresentacionMedicina;
	@WireVariable("SProveedor")
	protected SProveedor servicioProveedor;
	@WireVariable("SProveedorExamen")
	protected SProveedorExamen servicioProveedorExamen;
	@WireVariable("SProveedorServicio")
	protected SProveedorServicio servicioProveedorServicio;
	@WireVariable("SRecipe")
	protected SRecipe servicioRecipe;
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
	@WireVariable("SVacuna")
	protected SVacuna servicioVacuna;
	@WireVariable("SArea")
	protected SArea servicioArea;
	@WireVariable("SClasificacionAccidente")
	protected SClasificacionAccidente servicioClasificacionAccidente;
	@WireVariable("SNomina")
	protected SNomina servicioNomina;

	private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"/META-INF/ConfiguracionAplicacion.xml");
	public Mensaje msj = new Mensaje();
	public Tabbox tabBox;
	public Include contenido;
	public Tab tab;
	protected DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	// public static List<Tab> tabs = new ArrayList<Tab>();
	public List<Tab> tabs = new ArrayList<Tab>();
	protected DateFormat df = new SimpleDateFormat("HH:mm:ss");
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

	public void cerrarVentana(Div div, String id, List<Tab> tabs2) {
		div.setVisible(false);
		tabs = tabs2;
		System.out.println(tabs.size());
		for (int i = 0; i < tabs.size(); i++) {
			if (tabs.get(i).getLabel().equals(id)) {
				if (i == (tabs.size() - 1) && tabs.size() > 1) {
					tabs.get(i - 1).setSelected(true);
				}
				tabs.get(i).onClose();
				tabs.remove(i);
			}
		}
	}

	public static SConsulta getServicioConsulta() {
		return applicationContext.getBean(SConsulta.class);
	}

	public static SConsultaEspecialista getServicioConsultaEspecialista() {
		return applicationContext.getBean(SConsultaEspecialista.class);
	}

	public static SConsultaServicioExterno getServicioConsultaServicioExterno() {
		return applicationContext.getBean(SConsultaServicioExterno.class);
	}

	public static SConsultaExamen getServicioConsultaExamen() {
		return applicationContext.getBean(SConsultaExamen.class);
	}

	public static SConsultaDiagnostico getServicioConsultaDiagnostico() {
		return applicationContext.getBean(SConsultaDiagnostico.class);
	}

	public static SProveedor getServicioProveedor() {
		return applicationContext.getBean(SProveedor.class);
	}

	public static SConsultaMedicina getServicioConsultaMedicina() {
		return applicationContext.getBean(SConsultaMedicina.class);
	}

	public String nombreUsuarioSesion() {
		Authentication sesion = SecurityContextHolder.getContext()
				.getAuthentication();
		return sesion.getName();
	}

	public Usuario usuarioSesion(String valor) {
		return servicioUsuario.buscarUsuarioPorNombre(valor);
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

	public static int calcularEdad(Date birthDate) {
		Calendar birth = new GregorianCalendar();
		Calendar today = new GregorianCalendar();
		int age = 0;
		int factor = 0;
		Date currentDate = new Date();
		birth.setTime(birthDate);
		today.setTime(currentDate);
		if (today.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR)) {
			factor = -1;
		}
		age = (currentDate.getYear() - birthDate.getYear()) + factor;
		if (age == -1)
			age = 0;
		return age;
	}

	public void inhabilitarTrabajadorYTodosFamiliares(Paciente paciente) {
		List<Paciente> inactivos = new ArrayList<Paciente>();
		paciente.setEstatus(false);
		inactivos.add(paciente);
		if (paciente.isTrabajador()) {
			List<Paciente> carga = servicioPaciente.buscarParientes(paciente
					.getCedula());
			for (Iterator<Paciente> iterator = carga.iterator(); iterator
					.hasNext();) {
				Paciente paciente2 = (Paciente) iterator.next();
				if (!paciente.isMuerte()) {
					paciente2.setEstatus(false);
					inactivos.add(paciente2);
				} else {
					if (!paciente2.getParentescoFamiliar().equals("Hijo(a)")) {
						paciente2.setEstatus(false);
						inactivos.add(paciente2);
					}
				}
			}
		}
		servicioPaciente.guardarVarios(inactivos);
	}
}