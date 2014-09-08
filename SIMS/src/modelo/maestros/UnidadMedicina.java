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
@Table(name="unidad_medicina", schema="dusa_sims.dbo")
public class UnidadMedicina implements Serializable {

	
	private static final long serialVersionUID = -2252080666380462623L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_unidad_medicina", unique=true, nullable=false)
	private long idUnidad;
	
	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name="hora_auditoria", length=10)
	private String horaAuditoria;

	@Column(length=500)
	private String nombre;

	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;
	
	@OneToMany(mappedBy="unidadMedicina")
	private Set<MedicinaPresentacionUnidad> medicinasPresentacion;

	public UnidadMedicina(long idUnidadMedicina, Timestamp fechaAuditoria,
			String horaAuditoria, String nombre, String usuarioAuditoria) {
		super();
		this.idUnidad = idUnidadMedicina;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.nombre = nombre;
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public UnidadMedicina() {
		super();
	}

	public long getIdUnidad() {
		return idUnidad;
	}

	public void setIdUnidad(long idUnidad) {
		this.idUnidad = idUnidad;
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

	public Set<MedicinaPresentacionUnidad> getMedicinasPresentacion() {
		return medicinasPresentacion;
	}

	public void setMedicinasPresentacion(
			Set<MedicinaPresentacionUnidad> medicinasPresentacion) {
		this.medicinasPresentacion = medicinasPresentacion;
	}
	
}
