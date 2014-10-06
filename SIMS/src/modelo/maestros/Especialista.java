package modelo.maestros;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaEspecialista;

@Entity
@Table(name = "especialista", schema="dusa_sims.dbo")
public class Especialista implements Serializable {

	private static final long serialVersionUID = 5227934273608392500L;

	@Id
	@Column(name = "id_especialista", length = 12, unique = true, nullable = false)
	private String cedula;

	@Column(name = "apellido", length = 100)
	private String apellido;

	@Column(length=1024)
	private String direccion;
	
	@Column(length=20)
	private String telefono;

	@Column(name = "nombre", length = 100)
	private String nombre;

	@Column(name = "costo")
	private double costo;

	@Column(name = "fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name = "hora_auditoria", length = 10)
	private String horaAuditoria;

	@Column(name = "usuario_auditoria", length = 50)
	private String usuarioAuditoria;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_especialidad")
	private Especialidad especialidad;

	@OneToMany(mappedBy = "especialista")
	private Set<ConsultaEspecialista> especialistas;

	@OneToMany(mappedBy = "especialista")
	private Set<Consulta> especialistasConsulta;
	
	public Especialista(String cedula, String apellido, String nombre,
			double costo, Timestamp fechaAuditoria, String horaAuditoria,
			String usuarioAuditoria, Especialidad especialidad, String di, String pho) {
		super();
		this.cedula = cedula;
		this.apellido = apellido;
		this.nombre = nombre;
		this.costo = costo;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
		this.especialidad = especialidad;
		this.direccion = di;
		this.telefono = pho;
	}

	public Especialista() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
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

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public Set<ConsultaEspecialista> getEspecialistas() {
		return especialistas;
	}

	public void setEspecialistas(Set<ConsultaEspecialista> especialistas) {
		this.especialistas = especialistas;
	}

	public Set<Consulta> getEspecialistasConsulta() {
		return especialistasConsulta;
	}

	public void setEspecialistasConsulta(Set<Consulta> especialistasConsulta) {
		this.especialistasConsulta = especialistasConsulta;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
