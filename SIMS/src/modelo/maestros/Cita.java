package modelo.maestros;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import modelo.seguridad.Usuario;


/**
 * The persistent class for the cita database table.
 * 
 */
@Entity
@Table(name="cita")
public class Cita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cita", unique=true, nullable=false)
	private long idCita;

	@Column(length=50)
	private String estado;

	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name="fecha_cita")
	private Timestamp fechaCita;

	@Column(name="fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name="hora_auditoria", length=10)
	private String horaAuditoria;

	@Column(name="hora_cita", length=10)
	private String horaCita;
	
	//bi-directional many-to-one association to Usuario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	@Column(length=500)
	private String observacion;

	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;

	//bi-directional many-to-one association to MotivoCita
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_motivo_cita")
	private MotivoCita motivoCita;

	//bi-directional many-to-one association to Paciente
		@ManyToOne(fetch=FetchType.LAZY)
		@JoinColumn(name="id_paciente")
	private Paciente paciente;

	public Cita() {
	}
	
	public Cita(long idCita, String estado, Timestamp fechaAuditoria,
			Timestamp fechaCita, Timestamp fechaCreacion, String horaAuditoria,String horaCita,
			Usuario usuario, String observacion, String usuarioAuditoria,
			MotivoCita motivoCita, Paciente paciente) {
		super();
		this.idCita = idCita;
		this.estado = estado;
		this.fechaAuditoria = fechaAuditoria;
		this.fechaCita = fechaCita;
		this.fechaCreacion = fechaCreacion;
		this.horaAuditoria = horaAuditoria;
		this.usuario = usuario;
		this.observacion = observacion;
		this.usuarioAuditoria = usuarioAuditoria;
		this.motivoCita = motivoCita;
		this.paciente = paciente;
		this.horaCita=horaCita;
	}


	public long getIdCita() {
		return this.idCita;
	}

	public void setIdCita(long idCita) {
		this.idCita = idCita;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFechaAuditoria() {
		return this.fechaAuditoria;
	}

	public void setFechaAuditoria(Timestamp fechaAuditoria) {
		this.fechaAuditoria = fechaAuditoria;
	}

	public Timestamp getFechaCita() {
		return this.fechaCita;
	}

	public void setFechaCita(Timestamp fechaCita) {
		this.fechaCita = fechaCita;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getHoraAuditoria() {
		return this.horaAuditoria;
	}

	public void setHoraAuditoria(String horaAuditoria) {
		this.horaAuditoria = horaAuditoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getUsuarioAuditoria() {
		return this.usuarioAuditoria;
	}

	public void setUsuarioAuditoria(String usuarioAuditoria) {
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public MotivoCita getMotivoCita() {
		return this.motivoCita;
	}

	public void setMotivoCita(MotivoCita motivoCita) {
		this.motivoCita = motivoCita;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getHoraCita() {
		return horaCita;
	}

	public void setHoraCita(String horaCita) {
		this.horaCita = horaCita;
	}
	
	public String getFechaCitaString() {
		String fechaCitaString = new SimpleDateFormat("dd/MM/yyyy")
				.format(fechaCita);
		return fechaCitaString;
	}

}