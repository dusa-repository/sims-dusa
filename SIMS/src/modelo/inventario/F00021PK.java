package modelo.inventario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the F00021 database table.
 * 
 */
@Embeddable
public class F00021PK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="NLKCO")
	private String nlkco;

	@Column(name="NLDCT")
	private String nldct;

	@Column(name="NLCTRY")
	private Double nlctry;

	@Column(name="NLFY")
	private Double nlfy;

	public F00021PK() {
	}
	public String getNlkco() {
		return this.nlkco;
	}
	public void setNlkco(String nlkco) {
		this.nlkco = nlkco;
	}
	public String getNldct() {
		return this.nldct;
	}
	public void setNldct(String nldct) {
		this.nldct = nldct;
	}
	public Double getNlctry() {
		return this.nlctry;
	}
	public void setNlctry(Double nlctry) {
		this.nlctry = nlctry;
	}
	public Double getNlfy() {
		return this.nlfy;
	}
	public void setNlfy(Double nlfy) {
		this.nlfy = nlfy;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof F00021PK)) {
			return false;
		}
		F00021PK castOther = (F00021PK)other;
		return 
			this.nlkco.equals(castOther.nlkco)
			&& this.nldct.equals(castOther.nldct)
			&& (this.nlctry == castOther.nlctry)
			&& (this.nlfy == castOther.nlfy);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.nlkco.hashCode();
		hash = hash * prime + this.nldct.hashCode();
		hash = hash * prime + ((int) (java.lang.Double.doubleToLongBits(this.nlctry) ^ (java.lang.Double.doubleToLongBits(this.nlctry) >>> 32)));
		hash = hash * prime + ((int) (java.lang.Double.doubleToLongBits(this.nlfy) ^ (java.lang.Double.doubleToLongBits(this.nlfy) >>> 32)));
		
		return hash;
	}
}