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

import modelo.maestros.Vacuna;
import modelo.pk.HistoriaVacunaId;

@Entity
@Table(name = "historia_vacuna", schema="dusa_sims.dbo")
@IdClass(HistoriaVacunaId.class)
public class HistoriaVacuna {
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_historia", referencedColumnName = "id_historia")
	private Historia historia;
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_vacuna", referencedColumnName = "id_vacuna")
	private Vacuna vacuna;
	
	@Column(name="fecha_vacuna")
	private Timestamp fecha;

	public HistoriaVacuna() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HistoriaVacuna(Historia historia, Vacuna vacuna, Timestamp fecha) {
		super();
		this.historia = historia;
		this.vacuna = vacuna;
		this.fecha = fecha;
	}

	public Historia getHistoria() {
		return historia;
	}

	public void setHistoria(Historia historia) {
		this.historia = historia;
	}

	public Vacuna getVacuna() {
		return vacuna;
	}

	public void setVacuna(Vacuna vacuna) {
		this.vacuna = vacuna;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	
	

}
