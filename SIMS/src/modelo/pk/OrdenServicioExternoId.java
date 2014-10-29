package modelo.pk;

import java.io.Serializable;

import modelo.maestros.ServicioExterno;
import modelo.transacciones.Orden;

public class OrdenServicioExternoId implements Serializable {

	private static final long serialVersionUID = 117568494109816629L;
	private Orden orden;
	private ServicioExterno servicioExterno;
	
	public Orden getOrden() {
		return orden;
	}
	public void setOrden(Orden orden) {
		this.orden = orden;
	}
	public ServicioExterno getServicioExterno() {
		return servicioExterno;
	}
	public void setServicioExterno(ServicioExterno servicioExterno) {
		this.servicioExterno = servicioExterno;
	}
	
	
}
