package modelo.control;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import modelo.maestros.Paciente;

@Entity
@Table(name = "control_orden", schema = "dusa_sims.dbo")
public class ControlOrden implements Serializable {

	private static final long serialVersionUID = 2620124121002273032L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_control_orden", unique = true, nullable = false)
	private long idControlOrden;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;

	@Column(length = 500)
	private String observacion;

	@Column(length = 100)
	private String estado;

	@Column(name = "fecha_recepcion")
	private Timestamp fechaRecepcion;

	@Column(name = "hora_recepcion", length = 10)
	private String horaRecepcion;

	@Column(name = "fecha_entrega")
	private Timestamp fechaEntrega;

	@Column(name = "hora_entrega", length = 10)
	private String horaEntrega;

	@Column(name = "fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name = "hora_auditoria", length = 10)
	private String horaAuditoria;

	@Column(name = "usuario_auditoria", length = 50)
	private String usuarioAuditoria;

	public ControlOrden() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ControlOrden(long idControlOrden, Paciente paciente,
			String observacion, String estado, Timestamp fechaRecepcion,
			String horaRecepcion, Timestamp fechaEntrega, String horaEntrega,
			Timestamp fechaAuditoria, String horaAuditoria,
			String usuarioAuditoria) {
		super();
		this.idControlOrden = idControlOrden;
		this.paciente = paciente;
		this.observacion = observacion;
		this.estado = estado;
		this.fechaRecepcion = fechaRecepcion;
		this.horaRecepcion = horaRecepcion;
		this.fechaEntrega = fechaEntrega;
		this.horaEntrega = horaEntrega;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public long getIdControlOrden() {
		return idControlOrden;
	}

	public void setIdControlOrden(long idControlOrden) {
		this.idControlOrden = idControlOrden;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(Timestamp fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public String getHoraRecepcion() {
		return horaRecepcion;
	}

	public void setHoraRecepcion(String horaRecepcion) {
		this.horaRecepcion = horaRecepcion;
	}

	public Timestamp getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Timestamp fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public String getHoraEntrega() {
		return horaEntrega;
	}

	public void setHoraEntrega(String horaEntrega) {
		this.horaEntrega = horaEntrega;
	}

	public Timestamp getFechaAuditoria() {
		return fechaAuditoria;
	}

	public void setFechaAuditoria(Timestamp fechaAuditoria) {
		this.fechaAuditoria = fechaAuditoria;
	}

	public String getHoraAuditoria() {
		return horaAuditoria;
	}

	public void setHoraAuditoria(String horaAuditoria) {
		this.horaAuditoria = horaAuditoria;
	}

	public String getUsuarioAuditoria() {
		return usuarioAuditoria;
	}

	public void setUsuarioAuditoria(String usuarioAuditoria) {
		this.usuarioAuditoria = usuarioAuditoria;
	}
	
}
