package modelo.pk;

import java.io.Serializable;

import modelo.maestros.Examen;
import modelo.maestros.Proveedor;

public class ProveedorExamenId implements Serializable {

	private static final long serialVersionUID = 2694002938210076453L;
	
	private Proveedor proveedor;
	private Examen examen;
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public Examen getExamen() {
		return examen;
	}
	public void setExamen(Examen examen) {
		this.examen = examen;
	}
	
	
}
