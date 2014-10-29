package modelo.transacciones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import modelo.maestros.Especialista;
import modelo.pk.OrdenEspecialistaId;

@Entity
@Table(name = "orden_especialista", schema="dusa_sims.dbo")
@IdClass(OrdenEspecialistaId.class)
public class OrdenEspecialista {


	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_orden", referencedColumnName = "id_orden")
	private Orden orden;
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_especialista", referencedColumnName = "id_especialista")
	private Especialista especialista;
	
	@Column(name="costo")
	private double costo;
	
	@Column(length=500)
	private String observacion;
	
	@Column(length=20)
	private String prioridad;

	public OrdenEspecialista() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrdenEspecialista(Orden orden, Especialista especialista,
			double costo, String observacion, String prioridad) {
		super();
		this.orden = orden;
		this.especialista = especialista;
		this.costo = costo;
		this.observacion = observacion;
		this.prioridad = prioridad;
	}

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
