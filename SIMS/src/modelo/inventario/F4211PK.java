package modelo.inventario;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the F4211 database table.
 * 
 */
@Embeddable
public class F4211PK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SDDOCO")
	private Double sddoco;

	@Column(name="SDDCTO")
	private String sddcto;

	@Column(name="SDKCOO")
	private String sdkcoo;

	@Column(name="SDLNID")
	private Double sdlnid;

	public F4211PK() {
	}
	public Double getSddoco() {
		return this.sddoco;
	}
	public void setSddoco(Double sddoco) {
		this.sddoco = sddoco;
	}
	public String getSddcto() {
		return this.sddcto;
	}
	public void setSddcto(String sddcto) {
		this.sddcto = sddcto;
	}
	public String getSdkcoo() {
		return this.sdkcoo;
	}
	public void setSdkcoo(String sdkcoo) {
		this.sdkcoo = sdkcoo;
	}
	public Double getSdlnid() {
		return this.sdlnid;
	}
	public void setSdlnid(Double sdlnid) {
		this.sdlnid = sdlnid;
	}

//	public boolean equals(Object other) {
//		if (this == other) {
//			return true;
//		}
//		if (!(other instanceof F4211PK)) {
//			return false;
//		}
//		F4211PK castOther = (F4211PK)other;
//		return 
//			(this.sddoco == castOther.sddoco)
//			&& this.sddcto.equals(castOther.sddcto)
//			&& this.sdkcoo.equals(castOther.sdkcoo)
//			&& (this.sdlnid == castOther.sdlnid);
//	}
//
//	public int hashCode() {
//		final int prime = 31;
//		int hash = 17;
//		hash = hash * prime + ((int) (java.lang.Double.doubleToLongBits(this.sddoco) ^ (java.lang.Double.doubleToLongBits(this.sddoco) >>> 32)));
//		hash = hash * prime + this.sddcto.hashCode();
//		hash = hash * prime + this.sdkcoo.hashCode();
//		hash = hash * prime + ((int) (java.lang.Double.doubleToLongBits(this.sdlnid) ^ (java.lang.Double.doubleToLongBits(this.sdlnid) >>> 32)));
//		
//		return hash;
//	}
}