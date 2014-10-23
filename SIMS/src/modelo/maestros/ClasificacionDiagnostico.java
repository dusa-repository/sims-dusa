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
@Table(name="clasificacion_diagnostico", schema="dusa_sims.dbo")
public class ClasificacionDiagnostico implements Serializable {
	
	private static final long serialVersionUID = 5037866792426235260L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_clasificacion_diagnostico", unique=true, nullable=false)
	private long idClasificacion;

	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name="hora_auditoria", length=10)
	private String horaAuditoria;

	@Column(length=500)
	private String nombre;

	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;
	
	@OneToMany(mappedBy="clasificacion")
	private Set<CategoriaDiagnostico> categorias;

	public ClasificacionDiagnostico() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClasificacionDiagnostico(long idClasificacion,
			Timestamp fechaAuditoria, String horaAuditoria, String nombre,
			String usuarioAuditoria) {
		super();
		this.idClasificacion = idClasificacion;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.nombre = nombre;
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public long getIdClasificacion() {
		return idClasificacion;
	}

	public void setIdClasificacion(long idClasificacion) {
		this.idClasificacion = idClasificacion;
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

	public Set<CategoriaDiagnostico> getCategorias() {
		return categorias;
	}

	public void setCategorias(Set<CategoriaDiagnostico> categorias) {
		this.categorias = categorias;
	}
	
	
}
