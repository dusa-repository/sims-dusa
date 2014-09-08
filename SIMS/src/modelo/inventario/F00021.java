package modelo.inventario;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the F00021 database table.
 * 
 */
@Entity
@Table(name="F00021", schema="bizapps_inventario.dbo")
public class F00021 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private F00021PK id;

	@Column(name="NLAUR")
	private Double nlaur;

	@Column(name="NLCK01")
	private String nlck01;

	@Column(name="NLIMB")
	private String nlimb;

	@Column(name="NLN001")
	private Double nln001;

	@Column(name="NLSEQ")
	private Double nlseq;

	@Column(name="NLSMAS")
	private String nlsmas;

	public F00021() {
	}

	public F00021PK getId() {
		return this.id;
	}

	public void setId(F00021PK id) {
		this.id = id;
	}

	public Double getNlaur() {
		return this.nlaur;
	}

	public void setNlaur(Double nlaur) {
		this.nlaur = nlaur;
	}

	public String getNlck01() {
		return this.nlck01;
	}

	public void setNlck01(String nlck01) {
		this.nlck01 = nlck01;
	}

	public String getNlimb() {
		return this.nlimb;
	}

	public void setNlimb(String nlimb) {
		this.nlimb = nlimb;
	}

	public Double getNln001() {
		return this.nln001;
	}

	public void setNln001(Double nln001) {
		this.nln001 = nln001;
	}

	public Double getNlseq() {
		return this.nlseq;
	}

	public void setNlseq(Double nlseq) {
		this.nlseq = nlseq;
	}

	public String getNlsmas() {
		return this.nlsmas;
	}

	public void setNlsmas(String nlsmas) {
		this.nlsmas = nlsmas;
	}

}