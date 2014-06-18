package modelo.pk;

import java.io.Serializable;

import modelo.maestros.Intervencion;
import modelo.transacciones.Historia;

public class HistoriaIntervencionId implements Serializable {
	
	private static final long serialVersionUID = -8412923761657057745L;
	private Historia historia;
	private Intervencion intervencion;
	
	
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
	
	

}
