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
@Table(name="antecedente_tipo")
public class AntecedenteTipo implements Serializable {

	private static final long serialVersionUID = -892562834215342193L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_antecedente_tipo", unique=true, nullable=false)
	private long idAntecedenteTipo;
	
	@Column(length=100)
	private String nombre;
	
	@Column
	private String tipo;
	
	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name="hora_auditoria", length=10)
	private String horaAuditoria;

	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;
	
	@OneToMany(mappedBy="antecedenteTipo")
	private Set<Antecedente> antecedentes;

	public AntecedenteTipo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AntecedenteTipo(long idAntecedenteTipo, String nombre, String tipo,
			Timestamp fechaAuditoria, String horaAuditoria,
			String usuarioAuditoria) {
		super();
		this.idAntecedenteTipo = idAntecedenteTipo;
		this.nombre = nombre;
		this.tipo = tipo;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public long getIdAntecedenteTipo() {
		return idAntecedenteTipo;
	}

	public void setIdAntecedenteTipo(long idAntecedenteTipo) {
		this.idAntecedenteTipo = idAntecedenteTipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public Set<Antecedente> getAntecedentes() {
		return antecedentes;
	}

	public void setAntecedentes(Set<Antecedente> antecedentes) {
		this.antecedentes = antecedentes;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
