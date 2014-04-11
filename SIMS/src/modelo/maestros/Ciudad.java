package modelo.maestros;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the ciudad database table.
 * 
 */
@Entity
@Table(name="ciudad")
public class Ciudad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ciudad", unique=true, nullable=false)
	private long idCiudad;

	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name="hora_auditoria", length=10)
	private String horaAuditoria;

	@Column(length=500)
	private String nombre;

	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;

	//bi-directional many-to-one association to Estado
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_estado")
	private Estado estado;

	@OneToMany(mappedBy="ciudad")
	private Set<ServicioExterno> serviciosExternos;
	
	@OneToMany(mappedBy="ciudad")
	private Set<Consultorio> consultorios;

	public Ciudad() {
	}
	

	public Ciudad(long idCiudad, Timestamp fechaAuditoria,
			String horaAuditoria, String nombre, String usuarioAuditoria,
			Estado estado) {
		super();
		this.idCiudad = idCiudad;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.nombre = nombre;
		this.usuarioAuditoria = usuarioAuditoria;
		this.estado = estado;
	}


	public long getIdCiudad() {
		return this.idCiudad;
	}

	public void setIdCiudad(long idCiudad) {
		this.idCiudad = idCiudad;
	}

	public Timestamp getFechaAuditoria() {
		return this.fechaAuditoria;
	}

	public void setFechaAuditoria(Timestamp fechaAuditoria) {
		this.fechaAuditoria = fechaAuditoria;
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

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}


	public Set<ServicioExterno> getServiciosExternos() {
		return serviciosExternos;
	}


	public void setServiciosExternos(Set<ServicioExterno> serviciosExternos) {
		this.serviciosExternos = serviciosExternos;
	}


	public Set<Consultorio> getConsultorios() {
		return consultorios;
	}


	public void setConsultorios(Set<Consultorio> consultorios) {
		this.consultorios = consultorios;
	}

}