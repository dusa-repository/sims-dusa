package modelo.pk;

import java.io.Serializable;

import modelo.maestros.Examen;
import modelo.transacciones.Orden;

public class OrdenExamenId implements Serializable {

	private static final long serialVersionUID = 4972116711750734017L;
	private Orden orden;
	private Examen examen;
	
	public Orden getOrden() {
		return orden;
	}
	public void setOrden(Orden orden) {
		this.orden = orden;
	}
	public Examen getExamen() {
		return examen;
	}
	public void setExamen(Examen examen) {
		this.examen = examen;
	}
}
