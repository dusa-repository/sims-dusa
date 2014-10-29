package modelo.pk;

import java.io.Serializable;

import modelo.maestros.Especialista;
import modelo.transacciones.Orden;

public class OrdenEspecialistaId implements Serializable {

	private static final long serialVersionUID = -5127883069628052976L;
	private Orden orden;
	private Especialista especialista;
	public Orden getOrden() {
		return orden;
	}
	public void setOrden(Orden orden) {
		this.orden = orden;
	}
	public Especialista getEspecialista() {
		return especialista;
	}
	public void setEspecialista(Especialista especialista) {
		this.especialista = especialista;
	}
}
