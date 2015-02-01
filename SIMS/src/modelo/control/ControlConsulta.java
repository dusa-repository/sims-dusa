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
@Table(name = "control_consulta", schema = "dusa_sims.dbo")
public class ControlConsulta implements Serializable {

	private static final long serialVersionUID = -436608228323374086L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_control_consulta", unique = true, nullable = false)
	private long idControlConsulta;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;

	@Column(length = 500)
	private String observacion;

	@Column(length = 100)
	private String estado;

	@Column(name = "fecha_llegada")
	private Timestamp fechaLLegada;

	@Column(name = "hora_llegada", length = 10)
	private String horaLLegada;

	@Column(name = "fecha_ingreso")
	private Timestamp fechaIngreso;

	@Column(name = "hora_ingreso", length = 10)
	private String horaIngreso;

	@Column(name = "fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name = "hora_auditoria", length = 10)
	private String horaAuditoria;

	@Column(name = "usuario_auditoria", length = 50)
	private String usuarioAuditoria;

	@Column(name = "tipo_consulta", length = 100)
	private String tipoConsulta;

	@Column(name = "tipo_consulta_secundaria", length = 100)
	private String tipoConsultaSecundaria;

	public ControlConsulta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ControlConsulta(long idControlConsulta, Paciente paciente,
			String observacion, String estado, Timestamp fechaLLegada,
			String horaLLegada, Timestamp fechaIngreso, String horaIngreso,
			Timestamp fechaAuditoria, String horaAuditoria,
			String usuarioAuditoria, String tipoConsulta,
			String tipoConsultaSecundaria) {
		super();
		this.idControlConsulta = idControlConsulta;
		this.paciente = paciente;
		this.observacion = observacion;
		this.estado = estado;
		this.fechaLLegada = fechaLLegada;
		this.horaLLegada = horaLLegada;
		this.fechaIngreso = fechaIngreso;
		this.horaIngreso = horaIngreso;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
		this.tipoConsulta = tipoConsulta;
		this.tipoConsultaSecundaria = tipoConsultaSecundaria;
	}

	public long getIdControlConsulta() {
		return idControlConsulta;
	}

	public void setIdControlConsulta(long idControlConsulta) {
		this.idControlConsulta = idControlConsulta;
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

	public Timestamp getFechaLLegada() {
		return fechaLLegada;
	}

	public void setFechaLLegada(Timestamp fechaLLegada) {
		this.fechaLLegada = fechaLLegada;
	}

	public String getHoraLLegada() {
		return horaLLegada;
	}

	public void setHoraLLegada(String horaLLegada) {
		this.horaLLegada = horaLLegada;
	}

	public Timestamp getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Timestamp fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getHoraIngreso() {
		return horaIngreso;
	}

	public void setHoraIngreso(String horaIngreso) {
		this.horaIngreso = horaIngreso;
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

	public String getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	public String getTipoConsultaSecundaria() {
		return tipoConsultaSecundaria;
	}

	public void setTipoConsultaSecundaria(String tipoConsultaSecundaria) {
		this.tipoConsultaSecundaria = tipoConsultaSecundaria;
	}

}
