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

import modelo.transacciones.ConsultaDiagnostico;

import org.hibernate.annotations.Type;


/**
 * The persistent class for the diagnostico database table.
 * 
 */
@Entity
@Table(name="diagnostico")
public class Diagnostico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_diagnostico", unique=true, nullable=false)
	private long idDiagnostico;

	@Column(length=50)
	private String codigo;

	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(length=50)
	private String grupo;

	@Column(name="hora_auditoria", length=10)
	private String horaAuditoria;

	@Column(length=500)
	private String nombre;

	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;

	//bi-directional many-to-one association to Categoria
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_categoria")
	private CategoriaDiagnostico categoria;

	@Column(name = "epi")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean epi;
	
	@OneToMany(mappedBy = "diagnostico")
	private Set<ConsultaDiagnostico> diagnosticos;

	@Column(name = "id_referencia")
	private Long idReferencia;
	
	public Diagnostico() {
	}
	

	public Diagnostico(long idDiagnostico, String codigo,
			Timestamp fechaAuditoria, String grupo, String horaAuditoria,
			String nombre, String usuarioAuditoria, CategoriaDiagnostico categoria, Boolean epi) {
		super();
		this.idDiagnostico = idDiagnostico;
		this.codigo = codigo;
		this.fechaAuditoria = fechaAuditoria;
		this.grupo = grupo;
		this.horaAuditoria = horaAuditoria;
		this.nombre = nombre;
		this.usuarioAuditoria = usuarioAuditoria;
		this.categoria = categoria;
		this.epi = epi;
	}


	public long getIdDiagnostico() {
		return this.idDiagnostico;
	}

	public void setIdDiagnostico(long idDiagnostico) {
		this.idDiagnostico = idDiagnostico;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Timestamp getFechaAuditoria() {
		return this.fechaAuditoria;
	}

	public void setFechaAuditoria(Timestamp fechaAuditoria) {
		this.fechaAuditoria = fechaAuditoria;
	}

	public String getGrupo() {
		return this.grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getHoraAuditoria() {
		return this.horaAuditoria;
	}

	public void setHoraAuditoria(String horaAuditoria) {
		this.horaAuditoria = horaAuditoria;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuarioAuditoria() {
		return this.usuarioAuditoria;
	}

	public void setUsuarioAuditoria(String usuarioAuditoria) {
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public CategoriaDiagnostico getCategoria() {
		return this.categoria;
	}

	public void setCategoria(CategoriaDiagnostico categoria) {
		this.categoria = categoria;
	}


	public Set<ConsultaDiagnostico> getDiagnosticos() {
		return diagnosticos;
	}


	public void setDiagnosticos(Set<ConsultaDiagnostico> diagnosticos) {
		this.diagnosticos = diagnosticos;
	}


	public Boolean getEpi() {
		return epi;
	}


	public void setEpi(Boolean epi) {
		this.epi = epi;
	}


	public Long getIdReferencia() {
		return idReferencia;
	}


	public void setIdReferencia(Long idReferencia) {
		this.idReferencia = idReferencia;
	}

}