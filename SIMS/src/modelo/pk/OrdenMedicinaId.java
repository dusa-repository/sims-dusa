package modelo.pk;

import java.io.Serializable;

import modelo.maestros.Medicina;
import modelo.transacciones.Orden;

public class OrdenMedicinaId implements Serializable {
	private static final long serialVersionUID = 9221751805011361487L;
	
	private Orden orden;
	private Medicina medicina;
	
	public Orden getOrden() {
		return orden;
	}
	public void setOrden(Orden orden) {
		this.orden = orden;
	}
	public Medicina getMedicina() {
		return medicina;
	}
	public void setMedicina(Medicina medicina) {
		this.medicina = medicina;
	}
	
	
}
