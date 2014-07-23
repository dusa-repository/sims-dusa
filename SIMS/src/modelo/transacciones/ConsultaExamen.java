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
import modelo.pk.ConsultaExamenId;

@Entity
@Table(name = "consulta_examen")
@IdClass(ConsultaExamenId.class)
public class ConsultaExamen {

	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_consulta", referencedColumnName = "id_consulta")
	private Consulta consulta;
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_examen", referencedColumnName = "id_examen")
	private Examen examen;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
	private Proveedor proveedor;
	
	@Column(length=100)
	private String observacion;
	
	@Column(name="costo")
	private double costo;
	
	@Column(length=100)
	private String resultado;
	
	@Column(length=20)
	private String prioridad;

	public ConsultaExamen() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConsultaExamen(Consulta consulta, Examen examen, String observacion, Proveedor p, double d, String pri) {
		super();
		this.consulta = consulta;
		this.examen = examen;
		this.observacion = observacion;
		this.proveedor = p;
		this.costo = d;
		this.prioridad = pri;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Examen getExamen() {
		return examen;
	}

	public void setExamen(Examen examen) {
		this.examen = examen;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
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

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	
	
}
