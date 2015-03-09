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
@Table(name="estado_civil", schema="dusa_sims.dbo")
public class EstadoCivil implements Serializable {
	
	private static final long serialVersionUID = 5037866792426235260L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_estado_civil", unique=true, nullable=false)
	private long idEstadoCivil;

	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name="hora_auditoria", length=10)
	private String horaAuditoria;

	@Column(length=500)
	private String nombre;

	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;
	
	@OneToMany(mappedBy = "estadoCivil")
	private Set<Paciente> pacientes;

	public EstadoCivil() {
		super();
		// TODO Auto-generated constructor stub
	}


	public EstadoCivil(long idEstadoCivil, Timestamp fechaAuditoria,
			String horaAuditoria, String nombre, String usuarioAuditoria) {
		super();
		this.idEstadoCivil = idEstadoCivil;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.nombre = nombre;
		this.usuarioAuditoria = usuarioAuditoria;
	}


	public long getIdEstadoCivil() {
		return idEstadoCivil;
	}


	public void setIdEstadoCivil(long idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
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


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getUsuarioAuditoria() {
		return usuarioAuditoria;
	}


	public void setUsuarioAuditoria(String usuarioAuditoria) {
		this.usuarioAuditoria = usuarioAuditoria;
	}
	
	
}

