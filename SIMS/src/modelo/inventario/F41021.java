package modelo.inventario;

import java.io.Serializable;

import javax.persistence.*;

import modelo.inventario.F41021PK;

import java.math.BigDecimal;


/**
 * The persistent class for the F41021 database table.
 * 
 */
@Entity
@Table(name="F4101", schema="bizapps_inventario.dbo")
public class F41021 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private F41021PK id;

	@Column(name="LICFGSID")
	private String licfgsid;

	@Column(name="LICHDF")
	private String lichdf;

	@Column(name="LIFCOM")
	private Double lifcom;

	@Column(name="LIFUN1")
	private Double lifun1;

	@Column(name="LIGLPT")
	private String liglpt;

	@Column(name="LIHCMS")
	private Double lihcms;

	@Column(name="LIHCOM")
	private Double lihcom;

	@Column(name="LIJOBN")
	private String lijobn;

	@Column(name="LILOTS")
	private String lilots;

	@Column(name="LILRCJ")
	private BigDecimal lilrcj;

	@Column(name="LINCDJ")
	private BigDecimal lincdj;

	@Column(name="LIOT1A")
	private Double liot1a;

	@Column(name="LIOT1P")
	private Double liot1p;

	@Column(name="LIOT2P")
	private Double liot2p;

	@Column(name="LIPBCK")
	private Double lipbck;

	@Column(name="LIPBIN")
	private String lipbin;

	@Column(name="LIPCOM")
	private Double lipcom;

	@Column(name="LIPID")
	private String lipid;

	@Column(name="LIPJCM")
	private Double lipjcm;

	@Column(name="LIPJDM")
	private Double lipjdm;

	@Column(name="LIPQOH")
	private Double lipqoh;

	@Column(name="LIPREQ")
	private Double lipreq;

	@Column(name="LIQONL")
	private Double liqonl;

	@Column(name="LIQOWO")
	private Double liqowo;

	@Column(name="LIQTIN")
	private Double liqtin;

	@Column(name="LIQTO1")
	private Double liqto1;

	@Column(name="LIQTO2")
	private Double liqto2;

	@Column(name="LIQTRI")
	private Double liqtri;

	@Column(name="LIQTRO")
	private Double liqtro;

	@Column(name="LIQTTR")
	private Double liqttr;

	@Column(name="LIQTY1")
	private Double liqty1;

	@Column(name="LIQTY2")
	private Double liqty2;

	@Column(name="LIQWBO")
	private Double liqwbo;

	@Column(name="LISCMS")
	private Double liscms;

	@Column(name="LISIBW")
	private Double lisibw;

	@Column(name="LISOBW")
	private Double lisobw;

	@Column(name="LISQOH")
	private Double lisqoh;

	@Column(name="LISQWO")
	private Double lisqwo;

	@Column(name="LISREQ")
	private Double lisreq;

	@Column(name="LISWHC")
	private Double liswhc;

	@Column(name="LISWSC")
	private Double liswsc;

	@Column(name="LITDAY")
	private Double litday;

	@Column(name="LIUPMJ")
	private BigDecimal liupmj;

	@Column(name="LIURAB")
	private Double liurab;

	@Column(name="LIURAT")
	private Double liurat;

	@Column(name="LIURCD")
	private String liurcd;

	@Column(name="LIURDT")
	private BigDecimal liurdt;

	@Column(name="LIURRF")
	private String liurrf;

	@Column(name="LIUSER")
	private String liuser;

	@Column(name="LIWPDF")
	private String liwpdf;

	public F41021() {
	}

	public F41021PK getId() {
		return this.id;
	}

	public void setId(F41021PK id) {
		this.id = id;
	}

	public String getLicfgsid() {
		return this.licfgsid;
	}

	public void setLicfgsid(String licfgsid) {
		this.licfgsid = licfgsid;
	}

	public String getLichdf() {
		return this.lichdf;
	}

	public void setLichdf(String lichdf) {
		this.lichdf = lichdf;
	}

	public Double getLifcom() {
		return this.lifcom;
	}

	public void setLifcom(Double lifcom) {
		this.lifcom = lifcom;
	}

	public Double getLifun1() {
		return this.lifun1;
	}

	public void setLifun1(Double lifun1) {
		this.lifun1 = lifun1;
	}

	public String getLiglpt() {
		return this.liglpt;
	}

	public void setLiglpt(String liglpt) {
		this.liglpt = liglpt;
	}

	public Double getLihcms() {
		return this.lihcms;
	}

	public void setLihcms(Double lihcms) {
		this.lihcms = lihcms;
	}

	public Double getLihcom() {
		return this.lihcom;
	}

	public void setLihcom(Double lihcom) {
		this.lihcom = lihcom;
	}

	public String getLijobn() {
		return this.lijobn;
	}

	public void setLijobn(String lijobn) {
		this.lijobn = lijobn;
	}

	public String getLilots() {
		return this.lilots;
	}

	public void setLilots(String lilots) {
		this.lilots = lilots;
	}

	public BigDecimal getLilrcj() {
		return this.lilrcj;
	}

	public void setLilrcj(BigDecimal lilrcj) {
		this.lilrcj = lilrcj;
	}

	public BigDecimal getLincdj() {
		return this.lincdj;
	}

	public void setLincdj(BigDecimal lincdj) {
		this.lincdj = lincdj;
	}

	public Double getLiot1a() {
		return this.liot1a;
	}

	public void setLiot1a(Double liot1a) {
		this.liot1a = liot1a;
	}

	public Double getLiot1p() {
		return this.liot1p;
	}

	public void setLiot1p(Double liot1p) {
		this.liot1p = liot1p;
	}

	public Double getLiot2p() {
		return this.liot2p;
	}

	public void setLiot2p(Double liot2p) {
		this.liot2p = liot2p;
	}

	public Double getLipbck() {
		return this.lipbck;
	}

	public void setLipbck(Double lipbck) {
		this.lipbck = lipbck;
	}

	public String getLipbin() {
		return this.lipbin;
	}

	public void setLipbin(String lipbin) {
		this.lipbin = lipbin;
	}

	public Double getLipcom() {
		return this.lipcom;
	}

	public void setLipcom(Double lipcom) {
		this.lipcom = lipcom;
	}

	public String getLipid() {
		return this.lipid;
	}

	public void setLipid(String lipid) {
		this.lipid = lipid;
	}

	public Double getLipjcm() {
		return this.lipjcm;
	}

	public void setLipjcm(Double lipjcm) {
		this.lipjcm = lipjcm;
	}

	public Double getLipjdm() {
		return this.lipjdm;
	}

	public void setLipjdm(Double lipjdm) {
		this.lipjdm = lipjdm;
	}

	public Double getLipqoh() {
		return this.lipqoh;
	}

	public void setLipqoh(Double lipqoh) {
		this.lipqoh = lipqoh;
	}

	public Double getLipreq() {
		return this.lipreq;
	}

	public void setLipreq(Double lipreq) {
		this.lipreq = lipreq;
	}

	public Double getLiqonl() {
		return this.liqonl;
	}

	public void setLiqonl(Double liqonl) {
		this.liqonl = liqonl;
	}

	public Double getLiqowo() {
		return this.liqowo;
	}

	public void setLiqowo(Double liqowo) {
		this.liqowo = liqowo;
	}

	public Double getLiqtin() {
		return this.liqtin;
	}

	public void setLiqtin(Double liqtin) {
		this.liqtin = liqtin;
	}

	public Double getLiqto1() {
		return this.liqto1;
	}

	public void setLiqto1(Double liqto1) {
		this.liqto1 = liqto1;
	}

	public Double getLiqto2() {
		return this.liqto2;
	}

	public void setLiqto2(Double liqto2) {
		this.liqto2 = liqto2;
	}

	public Double getLiqtri() {
		return this.liqtri;
	}

	public void setLiqtri(Double liqtri) {
		this.liqtri = liqtri;
	}

	public Double getLiqtro() {
		return this.liqtro;
	}

	public void setLiqtro(Double liqtro) {
		this.liqtro = liqtro;
	}

	public Double getLiqttr() {
		return this.liqttr;
	}

	public void setLiqttr(Double liqttr) {
		this.liqttr = liqttr;
	}

	public Double getLiqty1() {
		return this.liqty1;
	}

	public void setLiqty1(Double liqty1) {
		this.liqty1 = liqty1;
	}

	public Double getLiqty2() {
		return this.liqty2;
	}

	public void setLiqty2(Double liqty2) {
		this.liqty2 = liqty2;
	}

	public Double getLiqwbo() {
		return this.liqwbo;
	}

	public void setLiqwbo(Double liqwbo) {
		this.liqwbo = liqwbo;
	}

	public Double getLiscms() {
		return this.liscms;
	}

	public void setLiscms(Double liscms) {
		this.liscms = liscms;
	}

	public Double getLisibw() {
		return this.lisibw;
	}

	public void setLisibw(Double lisibw) {
		this.lisibw = lisibw;
	}

	public Double getLisobw() {
		return this.lisobw;
	}

	public void setLisobw(Double lisobw) {
		this.lisobw = lisobw;
	}

	public Double getLisqoh() {
		return this.lisqoh;
	}

	public void setLisqoh(Double lisqoh) {
		this.lisqoh = lisqoh;
	}

	public Double getLisqwo() {
		return this.lisqwo;
	}

	public void setLisqwo(Double lisqwo) {
		this.lisqwo = lisqwo;
	}

	public Double getLisreq() {
		return this.lisreq;
	}

	public void setLisreq(Double lisreq) {
		this.lisreq = lisreq;
	}

	public Double getLiswhc() {
		return this.liswhc;
	}

	public void setLiswhc(Double liswhc) {
		this.liswhc = liswhc;
	}

	public Double getLiswsc() {
		return this.liswsc;
	}

	public void setLiswsc(Double liswsc) {
		this.liswsc = liswsc;
	}

	public Double getLitday() {
		return this.litday;
	}

	public void setLitday(Double litday) {
		this.litday = litday;
	}

	public BigDecimal getLiupmj() {
		return this.liupmj;
	}

	public void setLiupmj(BigDecimal liupmj) {
		this.liupmj = liupmj;
	}

	public Double getLiurab() {
		return this.liurab;
	}

	public void setLiurab(Double liurab) {
		this.liurab = liurab;
	}

	public Double getLiurat() {
		return this.liurat;
	}

	public void setLiurat(Double liurat) {
		this.liurat = liurat;
	}

	public String getLiurcd() {
		return this.liurcd;
	}

	public void setLiurcd(String liurcd) {
		this.liurcd = liurcd;
	}

	public BigDecimal getLiurdt() {
		return this.liurdt;
	}

	public void setLiurdt(BigDecimal liurdt) {
		this.liurdt = liurdt;
	}

	public String getLiurrf() {
		return this.liurrf;
	}

	public void setLiurrf(String liurrf) {
		this.liurrf = liurrf;
	}

	public String getLiuser() {
		return this.liuser;
	}

	public void setLiuser(String liuser) {
		this.liuser = liuser;
	}

	public String getLiwpdf() {
		return this.liwpdf;
	}

	public void setLiwpdf(String liwpdf) {
		this.liwpdf = liwpdf;
	}

}