package modelo.transacciones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import modelo.maestros.Proveedor;
import modelo.maestros.ServicioExterno;
import modelo.pk.OrdenServicioExternoId;

@Entity
@Table(name = "orden_servicio_externo", schema="dusa_sims.dbo")
@IdClass(OrdenServicioExternoId.class)
public class OrdenServicioExterno {
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_orden", referencedColumnName = "id_orden")
	private Orden orden;
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_servicio_externo", referencedColumnName = "id_servicio_externo")
	private ServicioExterno servicioExterno;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
	private Proveedor proveedor;
	
	@Column(name="costo")
	private double costo;
	
	@Column(length=100)
	private String observacion;
	
	@Column(length=20)
	private String prioridad;

	public OrdenServicioExterno(Orden orden, ServicioExterno servicioExterno,
			Proveedor proveedor, double costo, String observacion,
			String prioridad) {
		super();
		this.orden = orden;
		this.servicioExterno = servicioExterno;
		this.proveedor = proveedor;
		this.costo = costo;
		this.observacion = observacion;
		this.prioridad = prioridad;
	}

	public OrdenServicioExterno() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	
	
}
