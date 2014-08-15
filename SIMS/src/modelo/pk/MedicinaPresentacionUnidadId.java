package modelo.pk;

import java.io.Serializable;

import modelo.maestros.Medicina;
import modelo.maestros.PresentacionMedicina;
import modelo.maestros.UnidadMedicina;

public class MedicinaPresentacionUnidadId implements Serializable {

	private static final long serialVersionUID = 4281043524653284249L;
	private Medicina medicina;
	private PresentacionMedicina presentacionMedicina;
	private UnidadMedicina unidadMedicina;

	public Medicina getMedicina() {
		return medicina;
	}

	public void setMedicina(Medicina medicina) {
		this.medicina = medicina;
	}

	public UnidadMedicina getUnidadMedicina() {
		return unidadMedicina;
	}

	public void setUnidadMedicina(UnidadMedicina unidadMedicina) {
		this.unidadMedicina = unidadMedicina;
	}

	public PresentacionMedicina getPresentacionMedicina() {
		return presentacionMedicina;
	}

	public void setPresentacionMedicina(PresentacionMedicina presentacionMedicina) {
		this.presentacionMedicina = presentacionMedicina;
	}

}
