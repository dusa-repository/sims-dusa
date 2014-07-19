package modelo.sha;
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

import modelo.maestros.Accidente;

@Entity
@Table(name="clasificacion_accidente")
public class ClasificacionAccidente implements Serializable {

	private static final long serialVersionUID = 2255744639671891059L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_clasificacion_accidente", unique=true, nullable=false)
	private long idClasificacionAccidente;
	
	@Column(length=100)
	private String nombre;
	
	@OneToMany(mappedBy = "clasificacion")
	private Set<Informe> informes;
	
	@OneToMany(mappedBy = "clasificacion")
	private Set<Accidente> accidentes;
	
	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name="hora_auditoria", length=10)
	private String horaAuditoria;

	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;
	
	public ClasificacionAccidente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClasificacionAccidente(long idClasificacion, String nombre, Timestamp fecha, String hora, String auditor) {
		super();
		this.idClasificacionAccidente = idClasificacion;
		this.nombre = nombre;
		this.fechaAuditoria = fecha;
		this.horaAuditoria = hora;
		this.usuarioAuditoria = auditor;
	}

	public long getIdClasificacionAccidente() {
		return idClasificacionAccidente;
	}

	public void setIdClasificacionAccidente(long idClasificacionAccidente) {
		this.idClasificacionAccidente = idClasificacionAccidente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Informe> getInformes() {
		return informes;
	}

	public void setInformes(Set<Informe> informes) {
		this.informes = informes;
	}

	public Set<Accidente> getAccidentes() {
		return accidentes;
	}

	public void setAccidentes(Set<Accidente> accidentes) {
		this.accidentes = accidentes;
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
