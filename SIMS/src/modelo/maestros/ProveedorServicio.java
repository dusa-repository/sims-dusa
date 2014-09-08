package modelo.maestros;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import modelo.pk.ProveedorServicioId;

@Entity
@Table(name = "proveedor_servicio_externo", schema="dusa_sims.dbo")
@IdClass(ProveedorServicioId.class)
public class ProveedorServicio {

	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
	private Proveedor proveedor;
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_servicio_externo", referencedColumnName = "id_servicio_externo")
	private ServicioExterno servicioExterno;
	
	@Column(name="costo")
	private double costo;

	public ProveedorServicio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProveedorServicio(Proveedor proveedor,
			ServicioExterno servicioExterno, double costo) {
		super();
		this.proveedor = proveedor;
		this.servicioExterno = servicioExterno;
		this.costo = costo;
	}

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

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}
	
	
}
