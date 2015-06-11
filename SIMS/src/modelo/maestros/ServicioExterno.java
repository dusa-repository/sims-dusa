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

import modelo.transacciones.ConsultaServicioExterno;
import modelo.transacciones.OrdenServicioExterno;

@Entity
@Table(name="servicio_externo", schema="dusa_sims.dbo")
public class ServicioExterno implements Serializable {

	private static final long serialVersionUID = 269284669873250302L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_servicio_externo", unique=true, nullable=false)
	private long idServicioExterno;

	@Column(length=500)
	private String nombre;
	
	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name="hora_auditoria", length=10)
	private String horaAuditoria;
	
	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;
	
	@OneToMany(mappedBy = "servicioExterno")
	private Set<ConsultaServicioExterno> servicios;
	
	@OneToMany(mappedBy = "servicioExterno")
	private Set<OrdenServicioExterno> ordenesServicioExterno;
	
	@OneToMany(mappedBy = "servicioExterno")
	private Set<ProveedorServicio> proveedoresServicios;
	
	public ServicioExterno() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ServicioExterno(long idServicioExterno, String nombre,
			Timestamp fechaAuditoria, String horaAuditoria,
			String usuarioAuditoria) {
		super();
		this.idServicioExterno = idServicioExterno;
		this.nombre = nombre;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
	}



	public long getIdServicioExterno() {
		return idServicioExterno;
	}

	public void setIdServicioExterno(long idServicioExterno) {
		this.idServicioExterno = idServicioExterno;
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

	public Set<ConsultaServicioExterno> getServicios() {
		return servicios;
	}

	public void setServicios(Set<ConsultaServicioExterno> servicios) {
		this.servicios = servicios;
	}

	public Set<ProveedorServicio> getProveedoresServicios() {
		return proveedoresServicios;
	}

	public void setProveedoresServicios(Set<ProveedorServicio> proveedoresServicios) {
		this.proveedoresServicios = proveedoresServicios;
	}


	public Set<OrdenServicioExterno> getOrdenesServicioExterno() {
		return ordenesServicioExterno;
	}


	public void setOrdenesServicioExterno(
			Set<OrdenServicioExterno> ordenesServicioExterno) {
		this.ordenesServicioExterno = ordenesServicioExterno;
	}
	
}
