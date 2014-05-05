package modelo.maestros;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import modelo.pk.ProveedorExamenId;

@Entity
@Table(name = "proveedor_examen")
@IdClass(ProveedorExamenId.class)
public class ProveedorExamen {

	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
	private Proveedor proveedor;
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_examen", referencedColumnName = "id_examen")
	private Examen examen;
	
	@Column(name="costo")
	private double costo;

	public ProveedorExamen() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProveedorExamen(Proveedor proveedor, Examen examen, double costo) {
		super();
		this.proveedor = proveedor;
		this.examen = examen;
		this.costo = costo;
	}

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

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}
	
	
}
