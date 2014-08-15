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

import modelo.maestros.Paciente;
import modelo.transacciones.Consulta;

@Entity
@Table(name="area")
public class Area implements Serializable {

	private static final long serialVersionUID = 2545006923709588765L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_area", unique=true, nullable=false)
	private long idArea;
	
	@Column(length=100)
	private String nombre;
	
	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name="hora_auditoria", length=10)
	private String horaAuditoria;

	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;
	
	@OneToMany(mappedBy = "area")
	private Set<Consulta> consultas;
	
	@OneToMany(mappedBy = "areaDeseada")
	private Set<Consulta> consultasDeseadas;

	@OneToMany(mappedBy = "area")
	private Set<Paciente> pacientes;
	
	@OneToMany(mappedBy = "area")
	private Set<Informe> informes;
	
	public Area() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Area(long idArea, String nombre, Timestamp fechaAuditoria,
			String horaAuditoria, String usuarioAuditoria) {
		super();
		this.idArea = idArea;
		this.nombre = nombre;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public long getIdArea() {
		return idArea;
	}

	public void setIdArea(long idArea) {
		this.idArea = idArea;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(Set<Consulta> consultas) {
		this.consultas = consultas;
	}

	public Set<Consulta> getConsultasDeseadas() {
		return consultasDeseadas;
	}

	public void setConsultasDeseadas(Set<Consulta> consultasDeseadas) {
		this.consultasDeseadas = consultasDeseadas;
	}

	public Set<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(Set<Paciente> pacientes) {
		this.pacientes = pacientes;
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

	public Set<Informe> getInformes() {
		return informes;
	}

	public void setInformes(Set<Informe> informes) {
		this.informes = informes;
	}

	
	
}
