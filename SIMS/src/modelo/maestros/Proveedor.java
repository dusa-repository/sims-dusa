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

import modelo.transacciones.ConsultaExamen;
import modelo.transacciones.ConsultaServicioExterno;

@Entity
@Table(name="proveedor")
public class Proveedor implements Serializable {

	private static final long serialVersionUID = 4881695044311813270L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_proveedor", unique=true, nullable=false)
	private long idProveedor;

	@Column(length=1024)
	private String direccion;

	@Column(length=500)
	private String nombre;
	
	@Column(length=20)
	private String telefono;
	
	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name="hora_auditoria", length=10)
	private String horaAuditoria;
	
	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_ciudad")
	private Ciudad ciudad;
	
	@OneToMany(mappedBy = "proveedor")
	private Set<ConsultaExamen> servicios;
	
	public Proveedor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Proveedor(long idProveedor, String direccion, String nombre,
			String telefono, Timestamp fechaAuditoria, String horaAuditoria,
			String usuarioAuditoria, Ciudad ciudad) {
		super();
		this.idProveedor = idProveedor;
		this.direccion = direccion;
		this.nombre = nombre;
		this.telefono = telefono;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
		this.ciudad = ciudad;
	}

	public long getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(long idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public Set<ConsultaExamen> getServicios() {
		return servicios;
	}

	public void setServicios(Set<ConsultaExamen> servicios) {
		this.servicios = servicios;
	}

}
