package modelo.inventario;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the F41021 database table.
 * 
 */
@Embeddable
public class F41021PK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="LIITM")
	private Double liitm;

	@Column(name="LIMCU")
	private String limcu;

	@Column(name="LILOCN")
	private String lilocn;

	@Column(name="LILOTN")
	private String lilotn;

	public F41021PK() {
	}
	public Double getLiitm() {
		return this.liitm;
	}
	public void setLiitm(Double liitm) {
		this.liitm = liitm;
	}
	public String getLimcu() {
		return this.limcu;
	}
	public void setLimcu(String limcu) {
		this.limcu = limcu;
	}
	public String getLilocn() {
		return this.lilocn;
	}
	public void setLilocn(String lilocn) {
		this.lilocn = lilocn;
	}
	public String getLilotn() {
		return this.lilotn;
	}
	public void setLilotn(String lilotn) {
		this.lilotn = lilotn;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof F41021PK)) {
			return false;
		}
		F41021PK castOther = (F41021PK)other;
		return 
			(this.liitm == castOther.liitm)
			&& this.limcu.equals(castOther.limcu)
			&& this.lilocn.equals(castOther.lilocn)
			&& this.lilotn.equals(castOther.lilotn);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (java.lang.Double.doubleToLongBits(this.liitm) ^ (java.lang.Double.doubleToLongBits(this.liitm) >>> 32)));
		hash = hash * prime + this.limcu.hashCode();
		hash = hash * prime + this.lilocn.hashCode();
		hash = hash * prime + this.lilotn.hashCode();
		
		return hash;
	}
}