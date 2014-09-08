package modelo.maestros;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="antecedente", schema="dusa_sims.dbo")
public class Antecedente implements Serializable {

	private static final long serialVersionUID = -4907136060149868068L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_antecedente", unique=true, nullable=false)
	private long idAntecedente;
	
	@Column(length=100)
	private String nombre;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_antecedente_tipo")
	private AntecedenteTipo antecedenteTipo;
	
	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name="hora_auditoria", length=10)
	private String horaAuditoria;

	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;
	
	@OneToMany(mappedBy="antecedente")
	private Set<PacienteAntecedente> antecedentesPacientes;

	public Antecedente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Antecedente(long idAntecedente, String nombre,
			AntecedenteTipo antecedenteTipo, Timestamp fechaAuditoria,
			String horaAuditoria, String usuarioAuditoria) {
		super();
		this.idAntecedente = idAntecedente;
		this.nombre = nombre;
		this.antecedenteTipo = antecedenteTipo;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public long getIdAntecedente() {
		return idAntecedente;
	}

	public void setIdAntecedente(long idAntecedente) {
		this.idAntecedente = idAntecedente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public AntecedenteTipo getAntecedenteTipo() {
		return antecedenteTipo;
	}

	public void setAntecedenteTipo(AntecedenteTipo antecedenteTipo) {
		this.antecedenteTipo = antecedenteTipo;
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

	public Set<PacienteAntecedente> getAntecedentesPacientes() {
		return antecedentesPacientes;
	}

	public void setAntecedentesPacientes(
			Set<PacienteAntecedente> antecedentesPacientes) {
		this.antecedentesPacientes = antecedentesPacientes;
	}	
	
}
