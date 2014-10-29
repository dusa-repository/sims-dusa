package modelo.transacciones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import modelo.maestros.Examen;
import modelo.maestros.Proveedor;
import modelo.pk.OrdenExamenId;

@Entity
@Table(name = "orden_examen", schema="dusa_sims.dbo")
@IdClass(OrdenExamenId.class)
public class OrdenExamen {

	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_orden", referencedColumnName = "id_orden")
	private Orden orden;
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_examen", referencedColumnName = "id_examen")
	private Examen examen;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
	private Proveedor proveedor;
	
	@Column(length=300)
	private String observacion;
	
	@Column(name="costo")
	private double costo;
	
	@Column(length=20)
	private String prioridad;

	public OrdenExamen() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrdenExamen(Orden orden, Examen examen, Proveedor proveedor,
			String observacion, double costo, String prioridad) {
		super();
		this.orden = orden;
		this.examen = examen;
		this.proveedor = proveedor;
		this.observacion = observacion;
		this.costo = costo;
		this.prioridad = prioridad;
	}

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

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

}
