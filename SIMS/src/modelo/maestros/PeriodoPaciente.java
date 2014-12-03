package modelo.maestros;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import modelo.pk.PeriodoPacienteId;

@Entity
@Table(name = "periodo_paciente", schema = "dusa_sims.dbo")
@IdClass(PeriodoPacienteId.class)
public class PeriodoPaciente {

	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_periodo", referencedColumnName = "id_periodo")
	private Periodo periodo;

	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
	private Paciente paciente;
	
	@Column(length=250)
	private String vdrl;
	
	@Column(length=250)
	private String heces;
	
	@Column(length=250)
	private String citologia;
	
	@Column(length=500)
	private String observacion;

	public PeriodoPaciente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PeriodoPaciente(Periodo periodo, Paciente paciente, String vdrl,
			String heces, String citologia, String observacion) {
		super();
		this.periodo = periodo;
		this.paciente = paciente;
		this.vdrl = vdrl;
		this.heces = heces;
		this.citologia = citologia;
		this.observacion = observacion;
	}

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

	public String getVdrl() {
		return vdrl;
	}

	public void setVdrl(String vdrl) {
		this.vdrl = vdrl;
	}

	public String getHeces() {
		return heces;
	}

	public void setHeces(String heces) {
		this.heces = heces;
	}

	public String getCitologia() {
		return citologia;
	}

	public void setCitologia(String citologia) {
		this.citologia = citologia;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

}
