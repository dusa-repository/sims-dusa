package modelo.pk;

import java.io.Serializable;

import modelo.maestros.Medicina;
import modelo.maestros.Paciente;

public class PacienteMedicinaId implements Serializable {

	private static final long serialVersionUID = -5915284438606263705L;
	private Paciente paciente;
	private Medicina medicina;
	
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Medicina getMedicina() {
		return medicina;
	}
	public void setMedicina(Medicina medicina) {
		this.medicina = medicina;
	}
	
	
}
