package modelo.transacciones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import modelo.maestros.Examen;
import modelo.pk.ConsultaExamenId;

@Entity
@Table(name = "consulta_examen")
@IdClass(ConsultaExamenId.class)
public class ConsultaExamen {

	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_consulta", referencedColumnName = "id_consulta")
	private Consulta consulta;
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_examen", referencedColumnName = "id_examen")
	private Examen examen;
	
	@Column(length=100)
	private String observacion;

	public ConsultaExamen() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConsultaExamen(Consulta consulta, Examen examen, String observacion) {
		super();
		this.consulta = consulta;
		this.examen = examen;
		this.observacion = observacion;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Examen getExamen() {
		return examen;
	}

	public void setExamen(Examen examen) {
		this.examen = examen;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	
}