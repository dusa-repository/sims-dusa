package modelo.pk;

import java.io.Serializable;

import modelo.maestros.Accidente;
import modelo.transacciones.Historia;

public class HistoriaAccidenteId implements Serializable {

	private static final long serialVersionUID = -7412669527077746449L;
	private Historia historia;
	private Accidente accidente;
	
	public Historia getHistoria() {
		return historia;
	}
	public void setHistoria(Historia historia) {
		this.historia = historia;
	}
	public Accidente getAccidente() {
		return accidente;
	}
	public void setAccidente(Accidente accidente) {
		this.accidente = accidente;
	}
	
	
}
