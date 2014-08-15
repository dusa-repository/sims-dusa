package modelo.transacciones;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import modelo.maestros.Intervencion;
import modelo.pk.HistoriaIntervencionId;

@Entity
@Table(name = "historia_intervencion")
@IdClass(HistoriaIntervencionId.class)
public class HistoriaIntervencion {
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_historia", referencedColumnName = "id_historia")
	private Historia historia;
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_intervencion", referencedColumnName = "id_intervencion")
	private Intervencion intervencion;
	
	@Column(name="fecha")
	private Timestamp fecha;
	
	@Column(length=100)
	private String motivo;
	
	@Column(length=100)
	private String diagnostico;
	
	@Column(name="reposo")
	private int reposo;
	
	@Column(length=100)
	private String secuela;

	public HistoriaIntervencion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HistoriaIntervencion(Historia historia, Intervencion intervencion,
			Timestamp fecha, String motivo, String diagnostico, int reposo,
			String secuela) {
		super();
		this.historia = historia;
		this.intervencion = intervencion;
		this.fecha = fecha;
		this.motivo = motivo;
		this.diagnostico = diagnostico;
		this.reposo = reposo;
		this.secuela = secuela;
	}

	public Historia getHistoria() {
		return historia;
	}

	public void setHistoria(Historia historia) {
		this.historia = historia;
	}

	public Intervencion getIntervencion() {
		return intervencion;
	}

	public void setIntervencion(Intervencion intervencion) {
		this.intervencion = intervencion;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public int getReposo() {
		return reposo;
	}

	public void setReposo(int reposo) {
		this.reposo = reposo;
	}

	public String getSecuela() {
		return secuela;
	}

	public void setSecuela(String secuela) {
		this.secuela = secuela;
	}
	
	

}
