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
@Table(name = "consulta_especialista")
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
	
	@Column(length=100)
	private String resultado;

	public ConsultaEspecialista() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConsultaEspecialista(Consulta consulta, Especialista especialista,
			double costo) {
		super();
		this.consulta = consulta;
		this.especialista = especialista;
		this.costo = costo;
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
	
}
