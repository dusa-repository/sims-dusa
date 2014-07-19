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
import modelo.pk.ConsultaServicioExternoId;

@Entity
@Table(name = "consulta_servicio_externo")
@IdClass(ConsultaServicioExternoId.class)
public class ConsultaServicioExterno {

	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_consulta", referencedColumnName = "id_consulta")
	private Consulta consulta;
	
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
	private String resultado;
	
	@Column(length=100)
	private String observacion;

	public ConsultaServicioExterno() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConsultaServicioExterno(Consulta consulta,
			ServicioExterno servicioExterno, Proveedor pro, double costo, String observacion) {
		super();
		this.consulta = consulta;
		this.servicioExterno = servicioExterno;
		this.costo = costo;
		this.proveedor = pro;
		this.observacion = observacion;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
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

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	
}
