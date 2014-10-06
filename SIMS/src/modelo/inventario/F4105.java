package modelo.inventario;

import java.io.Serializable;

import javax.persistence.*;


import java.math.BigDecimal;


/**
 * The persistent class for the F4105 database table.
 * 
 */
@Entity
@Table(name="F4105", schema="bizapps_inventario.dbo")
public class F4105 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private F4105PK id;

	@Column(name="COAITM")
	private String coaitm;

	@Column(name="COCCFL")
	private String coccfl;

	@Column(name="COCRCS")
	private Double cocrcs;

	@Column(name="COCSIN")
	private String cocsin;

	@Column(name="COCSPO")
	private String cocspo;

	@Column(name="COJOBN")
	private String cojobn;

	@Column(name="COLITM")
	private String colitm;

	@Column(name="COLOTG")
	private String colotg;

	@Column(name="COOSTC")
	private Double coostc;

	@Column(name="COPID")
	private String copid;

	@Column(name="COSTOC")
	private Double costoc;

	@Column(name="COTDAY")
	private Double cotday;

	@Column(name="COUNCS")
	private Double councs;

	@Column(name="COUPMJ")
	private BigDecimal coupmj;

	@Column(name="COURAB")
	private Double courab;

	@Column(name="COURAT")
	private Double courat;

	@Column(name="COURCD")
	private String courcd;

	@Column(name="COURDT")
	private BigDecimal courdt;

	@Column(name="COURRF")
	private String courrf;

	@Column(name="COUSER")
	private String couser;

	public F4105() {
	}

	public F4105PK getId() {
		return this.id;
	}

	public void setId(F4105PK id) {
		this.id = id;
	}

	public String getCoaitm() {
		return this.coaitm;
	}

	public void setCoaitm(String coaitm) {
		this.coaitm = coaitm;
	}

	public String getCoccfl() {
		return this.coccfl;
	}

	public void setCoccfl(String coccfl) {
		this.coccfl = coccfl;
	}

	public Double getCocrcs() {
		return this.cocrcs;
	}

	public void setCocrcs(Double cocrcs) {
		this.cocrcs = cocrcs;
	}

	public String getCocsin() {
		return this.cocsin;
	}

	public void setCocsin(String cocsin) {
		this.cocsin = cocsin;
	}

	public String getCocspo() {
		return this.cocspo;
	}

	public void setCocspo(String cocspo) {
		this.cocspo = cocspo;
	}

	public String getCojobn() {
		return this.cojobn;
	}

	public void setCojobn(String cojobn) {
		this.cojobn = cojobn;
	}

	public String getColitm() {
		return this.colitm;
	}

	public void setColitm(String colitm) {
		this.colitm = colitm;
	}

	public String getColotg() {
		return this.colotg;
	}

	public void setColotg(String colotg) {
		this.colotg = colotg;
	}

	public Double getCoostc() {
		return this.coostc;
	}

	public void setCoostc(Double coostc) {
		this.coostc = coostc;
	}

	public String getCopid() {
		return this.copid;
	}

	public void setCopid(String copid) {
		this.copid = copid;
	}

	public Double getCostoc() {
		return this.costoc;
	}

	public void setCostoc(Double costoc) {
		this.costoc = costoc;
	}

	public Double getCotday() {
		return this.cotday;
	}

	public void setCotday(Double cotday) {
		this.cotday = cotday;
	}

	public Double getCouncs() {
		return this.councs;
	}

	public void setCouncs(Double councs) {
		this.councs = councs;
	}

	public BigDecimal getCoupmj() {
		return this.coupmj;
	}

	public void setCoupmj(BigDecimal coupmj) {
		this.coupmj = coupmj;
	}

	public Double getCourab() {
		return this.courab;
	}

	public void setCourab(Double courab) {
		this.courab = courab;
	}

	public Double getCourat() {
		return this.courat;
	}

	public void setCourat(Double courat) {
		this.courat = courat;
	}

	public String getCourcd() {
		return this.courcd;
	}

	public void setCourcd(String courcd) {
		this.courcd = courcd;
	}

	public BigDecimal getCourdt() {
		return this.courdt;
	}

	public void setCourdt(BigDecimal courdt) {
		this.courdt = courdt;
	}

	public String getCourrf() {
		return this.courrf;
	}

	public void setCourrf(String courrf) {
		this.courrf = courrf;
	}

	public String getCouser() {
		return this.couser;
	}

	public void setCouser(String couser) {
		this.couser = couser;
	}

}