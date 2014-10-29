package modelo.transacciones;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import modelo.maestros.Medicina;
import modelo.pk.OrdenMedicinaId;

@Entity
@Table(name = "orden_medicina", schema="dusa_sims.dbo")
@IdClass(OrdenMedicinaId.class)
public class OrdenMedicina {

	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_orden", referencedColumnName = "id_orden")
	private Orden orden;
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_medicina", referencedColumnName = "id_medicina")
	private Medicina medicina;
	
	@Column(length=1000)
	private String dosis;
	
	@Column(length=20)
	private String prioridad;
	
	@Column(length=7)
	private String tratamiento;
	
	@Column(name="validez")
	private Timestamp validez;
	
	@Column(name="cantidad")
	private Integer cantidad;

	public OrdenMedicina() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrdenMedicina(Orden orden, Medicina medicina, String dosis,
			String prioridad, String tratamiento, Timestamp validez, Integer canti) {
		super();
		this.orden = orden;
		this.medicina = medicina;
		this.dosis = dosis;
		this.prioridad = prioridad;
		this.tratamiento = tratamiento;
		this.validez = validez;
		this.cantidad = canti;
	}

	public Orden getOrden() {
		return orden;
	}

	public void setOrden(Orden orden) {
		this.orden = orden;
	}

	public Medicina getMedicina() {
		return medicina;
	}

	public void setMedicina(Medicina medicina) {
		this.medicina = medicina;
	}

	public String getDosis() {
		return dosis;
	}

	public void setDosis(String dosis) {
		this.dosis = dosis;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	public Timestamp getValidez() {
		return validez;
	}

	public void setValidez(Timestamp validez) {
		this.validez = validez;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	

}
