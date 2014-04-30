package modelo.maestros;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import modelo.pk.PacienteAntecedenteId;

@Entity
@Table(name = "paciente_antecedente")
@IdClass(PacienteAntecedenteId.class)
public class PacienteAntecedente {

	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
	private Paciente paciente;
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_antecedente", referencedColumnName = "id_antecedente")
	private Antecedente antecedente;
	
	@Column(length = 100)
	private String observacion;

	public PacienteAntecedente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PacienteAntecedente(Paciente paciente, Antecedente antecedente,
			String observacion) {
		super();
		this.paciente = paciente;
		this.antecedente = antecedente;
		this.observacion = observacion;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Antecedente getAntecedente() {
		return antecedente;
	}

	public void setAntecedente(Antecedente antecedente) {
		this.antecedente = antecedente;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	
}
