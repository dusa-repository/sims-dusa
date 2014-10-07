package modelo.transacciones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import modelo.maestros.Especialista;
import modelo.pk.ConsultaEspecialistaId;

@Entity
@Table(name = "consulta_especialista", schema="dusa_sims.dbo")
@IdClass(ConsultaEspecialistaId.class)
public class ConsultaEspecialista {

	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_consulta", referencedColumnName = "id_consulta")
	private Consulta consulta;
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_especialista", referencedColumnName = "id_especialista")
	private Especialista especialista;
	
	@Column(name="costo")
	private double costo;
	
	@Column(length=1500)
	private String observacion;
	
	@Column(length=100)
	private String resultado;
	
	@Column(length=20)
	private String prioridad;

	public ConsultaEspecialista() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConsultaEspecialista(Consulta consulta, Especialista especialista,
			double costo, String observacion, String prioridad) {
		super();
		this.consulta = consulta;
		this.especialista = especialista;
		this.costo = costo;
		this.observacion = observacion;
		this.prioridad = prioridad;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Especialista getEspecialista() {
		return especialista;
	}

	public void setEspecialista(Especialista especialista) {
		this.especialista = especialista;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	
}
