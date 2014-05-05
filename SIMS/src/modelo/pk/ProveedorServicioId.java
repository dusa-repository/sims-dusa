package modelo.pk;

import java.io.Serializable;

import modelo.maestros.Proveedor;
import modelo.maestros.ServicioExterno;

public class ProveedorServicioId implements Serializable {

	private static final long serialVersionUID = -7660498003011818221L;
	
	private Proveedor proveedor;
	private ServicioExterno servicioExterno;
	
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public ServicioExterno getServicioExterno() {
		return servicioExterno;
	}
	public void setServicioExterno(ServicioExterno servicioExterno) {
		this.servicioExterno = servicioExterno;
	}
	
}
