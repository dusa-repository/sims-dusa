package modelo.pk;

import java.io.Serializable;

import modelo.maestros.Antecedente;
import modelo.maestros.Paciente;

public class PacienteAntecedenteId implements Serializable {

	private static final long serialVersionUID = 2705895937941287399L;
	
	private Paciente paciente;
	private Antecedente antecedente;
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
	
	
}
