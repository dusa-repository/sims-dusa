package modelo.pk;

import java.io.Serializable;

import modelo.maestros.Paciente;
import modelo.maestros.Periodo;

public class PeriodoPacienteId implements Serializable {

	private static final long serialVersionUID = -6132342490034949325L;
	
	private Periodo periodo;
	private Paciente paciente;
	
	public Periodo getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	
}
