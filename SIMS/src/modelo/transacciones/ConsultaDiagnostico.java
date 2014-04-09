package modelo.transacciones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import modelo.maestros.Diagnostico;
import modelo.pk.ConsultaDiagnosticoId;

@Entity
@Table(name = "consulta_diagnostico")
@IdClass(ConsultaDiagnosticoId.class)
public class ConsultaDiagnostico {

	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_consulta", referencedColumnName = "id_consulta")
	private Consulta consulta;
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_diagnostico", referencedColumnName = "id_diagnostico")
	private Diagnostico diagnostico;
	
	@Column(length=100)
	private String observacion;

	public ConsultaDiagnostico() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConsultaDiagnostico(Consulta consulta, Diagnostico diagnostico,
			String observacion) {
		super();
		this.consulta = consulta;
		this.diagnostico = diagnostico;
		this.observacion = observacion;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Diagnostico getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(Diagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

}
