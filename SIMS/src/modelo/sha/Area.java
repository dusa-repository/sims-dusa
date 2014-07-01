package modelo.sha;
import java.io.Serializable;
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
	
	
	@OneToMany(mappedBy = "area")
	private Set<Consulta> consultas;
	
	@OneToMany(mappedBy = "areaDeseada")
	private Set<Consulta> consultasDeseadas;

	@OneToMany(mappedBy = "area")
	private Set<Paciente> pacientes;
	
	public Area() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Area(long idArea, String nombre) {
		super();
		this.idArea = idArea;
		this.nombre = nombre;
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

	
	
}
