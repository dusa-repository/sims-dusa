package modelo.inventario;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the F4111 database table.
 * 
 */
@Entity
@Table(name="F4111", schema="dusa_sims_inventario.dbo")
public class F4111 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ILUKID")
	private Double ilukid;

	@Column(name="ILAID")
	private String ilaid;

	@Column(name="ILAITM")
	private String ilaitm;

	@Column(name="ILAN8")
	private Double ilan8;

	@Column(name="ILASID")
	private String ilasid;

	@Column(name="ILCMOO")
	private String ilcmoo;

	@Column(name="ILCRDJ")
	private BigDecimal ilcrdj;

	@Column(name="ILDCT")
	private String ildct;

	@Column(name="ILDCTO")
	private String ildcto;

	@Column(name="ILDGL")
	private BigDecimal ildgl;

	@Column(name="ILDMCS")
	private Double ildmcs;

	@Column(name="ILDMCT")
	private String ildmct;

	@Column(name="ILDOC")
	private Double ildoc;

	@Column(name="ILDOCO")
	private Double ildoco;

	@Column(name="ILFRTO")
	private String ilfrto;

	@Column(name="ILGLPT")
	private String ilglpt;

	@Column(name="ILHDGJ")
	private BigDecimal ilhdgj;

	@Column(name="ILICU")
	private Double ilicu;

	@Column(name="ILIPCD")
	private String ilipcd;

	@Column(name="ILITM")
	private Double ilitm;

	@Column(name="ILJELN")
	private Double iljeln;

	@Column(name="ILKCO")
	private String ilkco;

	@Column(name="ILKCOO")
	private String ilkcoo;

	@Column(name="ILKIT")
	private Double ilkit;

	@Column(name="ILLDSQ")
	private Double illdsq;

	@Column(name="ILLITM")
	private String illitm;

	@Column(name="ILLMCX")
	private String illmcx;

	@Column(name="ILLNID")
	private Double illnid;

	@Column(name="ILLOCN")
	private String illocn;

	@Column(name="ILLOTC")
	private String illotc;

	@Column(name="ILLOTG")
	private String illotg;

	@Column(name="ILLOTN")
	private String illotn;

	@Column(name="ILLOTP")
	private Double illotp;

	@Column(name="ILLOTS")
	private String illots;

	@Column(name="ILLPNU")
	private String illpnu;

	@Column(name="ILLRCD")
	private String illrcd;

	@Column(name="ILMCU")
	private String ilmcu;

	@Column(name="ILMCUZ")
	private String ilmcuz;

	@Column(name="ILMMCU")
	private String ilmmcu;

	@Column(name="ILNLIN")
	private Double ilnlin;

	@Column(name="ILOBJ")
	private String ilobj;

	@Column(name="ILOPSQ")
	private Double ilopsq;

	@Column(name="ILPAID")
	private Double ilpaid;

	@Column(name="ILPID")
	private String ilpid;

	@Column(name="ILPLOT")
	private String ilplot;

	@Column(name="ILRCD")
	private String ilrcd;

	@Column(name="ILRE")
	private String ilre;

	@Column(name="ILRFLN")
	private Double ilrfln;

	@Column(name="ILRLOT")
	private String ilrlot;

	@Column(name="ILSBL")
	private String ilsbl;

	@Column(name="ILSBLT")
	private String ilsblt;

	@Column(name="ILSFX")
	private String ilsfx;

	@Column(name="ILSHAN")
	private Double ilshan;

	@Column(name="ILSQOR")
	private Double ilsqor;

	@Column(name="ILSTUN")
	private Double ilstun;

	@Column(name="ILSUB")
	private String ilsub;

	@Column(name="ILSVDT")
	private BigDecimal ilsvdt;

	@Column(name="ILTDAY")
	private Double iltday;

	@Column(name="ILTERM")
	private String ilterm;

	@Column(name="ILTGN")
	private Double iltgn;

	@Column(name="ILTRDJ")
	private BigDecimal iltrdj;

	@Column(name="ILTREF")
	private String iltref;

	@Column(name="ILTREX")
	private String iltrex;

	@Column(name="ILTRNO")
	private Double iltrno;

	@Column(name="ILTRQT")
	private Double iltrqt;

	@Column(name="ILTRUM")
	private String iltrum;

	@Column(name="ILUNCS")
	private Double iluncs;

	@Column(name="ILUOM2")
	private String iluom2;

	@Column(name="ILUSER")
	private String iluser;

	@Column(name="ILVPEJ")
	private BigDecimal ilvpej;

	public F4111() {
	}

	public Double getIlukid() {
		return this.ilukid;
	}

	public void setIlukid(Double ilukid) {
		this.ilukid = ilukid;
	}

	public String getIlaid() {
		return this.ilaid;
	}

	public void setIlaid(String ilaid) {
		this.ilaid = ilaid;
	}

	public String getIlaitm() {
		return this.ilaitm;
	}

	public void setIlaitm(String ilaitm) {
		this.ilaitm = ilaitm;
	}

	public Double getIlan8() {
		return this.ilan8;
	}

	public void setIlan8(Double ilan8) {
		this.ilan8 = ilan8;
	}

	public String getIlasid() {
		return this.ilasid;
	}

	public void setIlasid(String ilasid) {
		this.ilasid = ilasid;
	}

	public String getIlcmoo() {
		return this.ilcmoo;
	}

	public void setIlcmoo(String ilcmoo) {
		this.ilcmoo = ilcmoo;
	}

	public BigDecimal getIlcrdj() {
		return this.ilcrdj;
	}

	public void setIlcrdj(BigDecimal ilcrdj) {
		this.ilcrdj = ilcrdj;
	}

	public String getIldct() {
		return this.ildct;
	}

	public void setIldct(String ildct) {
		this.ildct = ildct;
	}

	public String getIldcto() {
		return this.ildcto;
	}

	public void setIldcto(String ildcto) {
		this.ildcto = ildcto;
	}

	public BigDecimal getIldgl() {
		return this.ildgl;
	}

	public void setIldgl(BigDecimal ildgl) {
		this.ildgl = ildgl;
	}

	public Double getIldmcs() {
		return this.ildmcs;
	}

	public void setIldmcs(Double ildmcs) {
		this.ildmcs = ildmcs;
	}

	public String getIldmct() {
		return this.ildmct;
	}

	public void setIldmct(String ildmct) {
		this.ildmct = ildmct;
	}

	public Double getIldoc() {
		return this.ildoc;
	}

	public void setIldoc(Double ildoc) {
		this.ildoc = ildoc;
	}

	public Double getIldoco() {
		return this.ildoco;
	}

	public void setIldoco(Double ildoco) {
		this.ildoco = ildoco;
	}

	public String getIlfrto() {
		return this.ilfrto;
	}

	public void setIlfrto(String ilfrto) {
		this.ilfrto = ilfrto;
	}

	public String getIlglpt() {
		return this.ilglpt;
	}

	public void setIlglpt(String ilglpt) {
		this.ilglpt = ilglpt;
	}

	public BigDecimal getIlhdgj() {
		return this.ilhdgj;
	}

	public void setIlhdgj(BigDecimal ilhdgj) {
		this.ilhdgj = ilhdgj;
	}

	public Double getIlicu() {
		return this.ilicu;
	}

	public void setIlicu(Double ilicu) {
		this.ilicu = ilicu;
	}

	public String getIlipcd() {
		return this.ilipcd;
	}

	public void setIlipcd(String ilipcd) {
		this.ilipcd = ilipcd;
	}

	public Double getIlitm() {
		return this.ilitm;
	}

	public void setIlitm(Double ilitm) {
		this.ilitm = ilitm;
	}

	public Double getIljeln() {
		return this.iljeln;
	}

	public void setIljeln(Double iljeln) {
		this.iljeln = iljeln;
	}

	public String getIlkco() {
		return this.ilkco;
	}

	public void setIlkco(String ilkco) {
		this.ilkco = ilkco;
	}

	public String getIlkcoo() {
		return this.ilkcoo;
	}

	public void setIlkcoo(String ilkcoo) {
		this.ilkcoo = ilkcoo;
	}

	public Double getIlkit() {
		return this.ilkit;
	}

	public void setIlkit(Double ilkit) {
		this.ilkit = ilkit;
	}

	public Double getIlldsq() {
		return this.illdsq;
	}

	public void setIlldsq(Double illdsq) {
		this.illdsq = illdsq;
	}

	public String getIllitm() {
		return this.illitm;
	}

	public void setIllitm(String illitm) {
		this.illitm = illitm;
	}

	public String getIllmcx() {
		return this.illmcx;
	}

	public void setIllmcx(String illmcx) {
		this.illmcx = illmcx;
	}

	public Double getIllnid() {
		return this.illnid;
	}

	public void setIllnid(Double illnid) {
		this.illnid = illnid;
	}

	public String getIllocn() {
		return this.illocn;
	}

	public void setIllocn(String illocn) {
		this.illocn = illocn;
	}

	public String getIllotc() {
		return this.illotc;
	}

	public void setIllotc(String illotc) {
		this.illotc = illotc;
	}

	public String getIllotg() {
		return this.illotg;
	}

	public void setIllotg(String illotg) {
		this.illotg = illotg;
	}

	public String getIllotn() {
		return this.illotn;
	}

	public void setIllotn(String illotn) {
		this.illotn = illotn;
	}

	public Double getIllotp() {
		return this.illotp;
	}

	public void setIllotp(Double illotp) {
		this.illotp = illotp;
	}

	public String getIllots() {
		return this.illots;
	}

	public void setIllots(String illots) {
		this.illots = illots;
	}

	public String getIllpnu() {
		return this.illpnu;
	}

	public void setIllpnu(String illpnu) {
		this.illpnu = illpnu;
	}

	public String getIllrcd() {
		return this.illrcd;
	}

	public void setIllrcd(String illrcd) {
		this.illrcd = illrcd;
	}

	public String getIlmcu() {
		return this.ilmcu;
	}

	public void setIlmcu(String ilmcu) {
		this.ilmcu = ilmcu;
	}

	public String getIlmcuz() {
		return this.ilmcuz;
	}

	public void setIlmcuz(String ilmcuz) {
		this.ilmcuz = ilmcuz;
	}

	public String getIlmmcu() {
		return this.ilmmcu;
	}

	public void setIlmmcu(String ilmmcu) {
		this.ilmmcu = ilmmcu;
	}

	public Double getIlnlin() {
		return this.ilnlin;
	}

	public void setIlnlin(Double ilnlin) {
		this.ilnlin = ilnlin;
	}

	public String getIlobj() {
		return this.ilobj;
	}

	public void setIlobj(String ilobj) {
		this.ilobj = ilobj;
	}

	public Double getIlopsq() {
		return this.ilopsq;
	}

	public void setIlopsq(Double ilopsq) {
		this.ilopsq = ilopsq;
	}

	public Double getIlpaid() {
		return this.ilpaid;
	}

	public void setIlpaid(Double ilpaid) {
		this.ilpaid = ilpaid;
	}

	public String getIlpid() {
		return this.ilpid;
	}

	public void setIlpid(String ilpid) {
		this.ilpid = ilpid;
	}

	public String getIlplot() {
		return this.ilplot;
	}

	public void setIlplot(String ilplot) {
		this.ilplot = ilplot;
	}

	public String getIlrcd() {
		return this.ilrcd;
	}

	public void setIlrcd(String ilrcd) {
		this.ilrcd = ilrcd;
	}

	public String getIlre() {
		return this.ilre;
	}

	public void setIlre(String ilre) {
		this.ilre = ilre;
	}

	public Double getIlrfln() {
		return this.ilrfln;
	}

	public void setIlrfln(Double ilrfln) {
		this.ilrfln = ilrfln;
	}

	public String getIlrlot() {
		return this.ilrlot;
	}

	public void setIlrlot(String ilrlot) {
		this.ilrlot = ilrlot;
	}

	public String getIlsbl() {
		return this.ilsbl;
	}

	public void setIlsbl(String ilsbl) {
		this.ilsbl = ilsbl;
	}

	public String getIlsblt() {
		return this.ilsblt;
	}

	public void setIlsblt(String ilsblt) {
		this.ilsblt = ilsblt;
	}

	public String getIlsfx() {
		return this.ilsfx;
	}

	public void setIlsfx(String ilsfx) {
		this.ilsfx = ilsfx;
	}

	public Double getIlshan() {
		return this.ilshan;
	}

	public void setIlshan(Double ilshan) {
		this.ilshan = ilshan;
	}

	public Double getIlsqor() {
		return this.ilsqor;
	}

	public void setIlsqor(Double ilsqor) {
		this.ilsqor = ilsqor;
	}

	public Double getIlstun() {
		return this.ilstun;
	}

	public void setIlstun(Double ilstun) {
		this.ilstun = ilstun;
	}

	public String getIlsub() {
		return this.ilsub;
	}

	public void setIlsub(String ilsub) {
		this.ilsub = ilsub;
	}

	public BigDecimal getIlsvdt() {
		return this.ilsvdt;
	}

	public void setIlsvdt(BigDecimal ilsvdt) {
		this.ilsvdt = ilsvdt;
	}

	public Double getIltday() {
		return this.iltday;
	}

	public void setIltday(Double iltday) {
		this.iltday = iltday;
	}

	public String getIlterm() {
		return this.ilterm;
	}

	public void setIlterm(String ilterm) {
		this.ilterm = ilterm;
	}

	public Double getIltgn() {
		return this.iltgn;
	}

	public void setIltgn(Double iltgn) {
		this.iltgn = iltgn;
	}

	public BigDecimal getIltrdj() {
		return this.iltrdj;
	}

	public void setIltrdj(BigDecimal iltrdj) {
		this.iltrdj = iltrdj;
	}

	public String getIltref() {
		return this.iltref;
	}

	public void setIltref(String iltref) {
		this.iltref = iltref;
	}

	public String getIltrex() {
		return this.iltrex;
	}

	public void setIltrex(String iltrex) {
		this.iltrex = iltrex;
	}

	public Double getIltrno() {
		return this.iltrno;
	}

	public void setIltrno(Double iltrno) {
		this.iltrno = iltrno;
	}

	public Double getIltrqt() {
		return this.iltrqt;
	}

	public void setIltrqt(Double iltrqt) {
		this.iltrqt = iltrqt;
	}

	public String getIltrum() {
		return this.iltrum;
	}

	public void setIltrum(String iltrum) {
		this.iltrum = iltrum;
	}

	public Double getIluncs() {
		return this.iluncs;
	}

	public void setIluncs(Double iluncs) {
		this.iluncs = iluncs;
	}

	public String getIluom2() {
		return this.iluom2;
	}

	public void setIluom2(String iluom2) {
		this.iluom2 = iluom2;
	}

	public String getIluser() {
		return this.iluser;
	}

	public void setIluser(String iluser) {
		this.iluser = iluser;
	}

	public BigDecimal getIlvpej() {
		return this.ilvpej;
	}

	public void setIlvpej(BigDecimal ilvpej) {
		this.ilvpej = ilvpej;
	}

}