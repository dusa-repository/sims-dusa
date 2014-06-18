package modelo.pk;

import java.io.Serializable;

import modelo.maestros.Vacuna;
import modelo.transacciones.Historia;

public class HistoriaVacunaId implements Serializable {

	private static final long serialVersionUID = 9129134877823731976L;
	private Historia historia;
	private Vacuna vacuna;
	
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
	
	
}
