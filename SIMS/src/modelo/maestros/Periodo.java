package modelo.maestros;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "periodo", schema="dusa_sims.dbo")
public class Periodo implements Serializable {

	private static final long serialVersionUID = 7735035250701134395L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_periodo", unique = true, nullable = false)
	private long idPeriodo;

	@Column(length = 100)
	private String nombre;
	
	@Column(name = "fecha_inicio")
	private Timestamp fechaInicio;
	
	@Column(name = "fecha_fin")
	private Timestamp fechaFin;
	
	@Column(name = "fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name = "hora_auditoria", length = 10)
	private String horaAuditoria;

	@Column(name = "usuario_auditoria", length = 50)
	private String usuarioAuditoria;
	
	@OneToMany(mappedBy = "periodo")
	private Set<PeriodoPaciente> pacientesPeriodos;

	public Periodo() {
		super();
	}

	public Periodo(long idPeriodo, String nombre, Timestamp fechaInicio,
			Timestamp fechaFin, Timestamp fechaAuditoria, String horaAuditoria,
			String usuarioAuditoria) {
		super();
		this.idPeriodo = idPeriodo;
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public long getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(long idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Timestamp getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Timestamp getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
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

	public Set<PeriodoPaciente> getPacientesPeriodos() {
		return pacientesPeriodos;
	}

	public void setPacientesPeriodos(Set<PeriodoPaciente> pacientesPeriodos) {
		this.pacientesPeriodos = pacientesPeriodos;
	}
	
	
}
