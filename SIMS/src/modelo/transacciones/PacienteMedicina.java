package modelo.transacciones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import modelo.maestros.Medicina;
import modelo.maestros.Paciente;
import modelo.pk.PacienteMedicinaId;

@Entity
@Table(name = "paciente_medicina", schema="dusa_sims.dbo")
@IdClass(PacienteMedicinaId.class)
public class PacienteMedicina {

	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
	private Paciente paciente;
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_medicina", referencedColumnName = "id_medicina")
	private Medicina medicina;
	
	@Column(length=500)
	private String frecuencia;

	public PacienteMedicina() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PacienteMedicina(Paciente paciente, Medicina medicina,
			String frecuencia) {
		super();
		this.paciente = paciente;
		this.medicina = medicina;
		this.frecuencia = frecuencia;
	}

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

	public String getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}

}
