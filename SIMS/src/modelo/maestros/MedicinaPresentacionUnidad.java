package modelo.maestros;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import modelo.pk.MedicinaPresentacionUnidadId;

@Entity
@Table(name = "medicina_presentacion_unidad", schema="dusa_sims.dbo")
@IdClass(MedicinaPresentacionUnidadId.class)
public class MedicinaPresentacionUnidad {

	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_medicina", referencedColumnName = "id_medicina")
	private Medicina medicina;
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_presentacion_medicina", referencedColumnName = "id_presentacion_medicina")
	private PresentacionMedicina presentacionMedicina;
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_unidad_medicina", referencedColumnName = "id_unidad_medicina")
	private UnidadMedicina unidadMedicina;
	
	@Column(name="valor")
	private double valor;

	public MedicinaPresentacionUnidad(Medicina medicina, PresentacionMedicina presentacion,
			UnidadMedicina unidadMedicina, double valor) {
		super();
		this.medicina = medicina;
		this.presentacionMedicina = presentacion;
		this.unidadMedicina = unidadMedicina;
		this.valor = valor;
	}

	public MedicinaPresentacionUnidad() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Medicina getMedicina() {
		return medicina;
	}

	public void setMedicina(Medicina medicina) {
		this.medicina = medicina;
	}

	public PresentacionMedicina getPresentacionMedicina() {
		return presentacionMedicina;
	}

	public void setPresentacionMedicina(PresentacionMedicina presentacionMedicina) {
		this.presentacionMedicina = presentacionMedicina;
	}

	public UnidadMedicina getUnidadMedicina() {
		return unidadMedicina;
	}

	public void setUnidadMedicina(UnidadMedicina unidadMedicina) {
		this.unidadMedicina = unidadMedicina;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
}
