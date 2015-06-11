package modelo.inventario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the F4105 database table.
 * 
 */
@Embeddable
public class F4105PK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="COITM")
	private Double coitm;

	@Column(name="COMCU")
	private String comcu;

	@Column(name="COLOCN")
	private String colocn;

	@Column(name="COLOTN")
	private String colotn;

	@Column(name="COLEDG")
	private String coledg;

	public F4105PK() {
	}
	public Double getCoitm() {
		return this.coitm;
	}
	public void setCoitm(Double coitm) {
		this.coitm = coitm;
	}
	public String getComcu() {
		return this.comcu;
	}
	public void setComcu(String comcu) {
		this.comcu = comcu;
	}
	public String getColocn() {
		return this.colocn;
	}
	public void setColocn(String colocn) {
		this.colocn = colocn;
	}
	public String getColotn() {
		return this.colotn;
	}
	public void setColotn(String colotn) {
		this.colotn = colotn;
	}
	public String getColedg() {
		return this.coledg;
	}
	public void setColedg(String coledg) {
		this.coledg = coledg;
	}

//	public boolean equals(Object other) {
//		if (this == other) {
//			return true;
//		}
//		if (!(other instanceof F4105PK)) {
//			return false;
//		}
//		F4105PK castOther = (F4105PK)other;
//		return 
//			(this.coitm == castOther.coitm)
//			&& this.comcu.equals(castOther.comcu)
//			&& this.colocn.equals(castOther.colocn)
//			&& this.colotn.equals(castOther.colotn)
//			&& this.coledg.equals(castOther.coledg);
//	}
//
//	public int hashCode() {
//		final int prime = 31;
//		int hash = 17;
//		hash = hash * prime + ((int) (java.lang.Double.doubleToLongBits(this.coitm) ^ (java.lang.Double.doubleToLongBits(this.coitm) >>> 32)));
//		hash = hash * prime + this.comcu.hashCode();
//		hash = hash * prime + this.colocn.hashCode();
//		hash = hash * prime + this.colotn.hashCode();
//		hash = hash * prime + this.coledg.hashCode();
//		
//		return hash;
//	}
}