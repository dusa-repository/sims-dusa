package modelo.inventario;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the F4101 database table.
 * 
 */
@Entity
@Table(name="F4101", schema="bizapps_inventario.dbo")
public class F4101 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "IMITM")
	private Double imitm;

	@Column(name = "IMABCI")
	private String imabci;

	@Column(name = "IMABCM")
	private String imabcm;

	@Column(name = "IMABCS")
	private String imabcs;

	@Column(name = "IMACQ")
	private Double imacq;

	@Column(name = "IMAING")
	private String imaing;

	@Column(name = "IMAITM")
	private String imaitm;

	@Column(name = "IMALN")
	private String imaln;

	@Column(name = "IMANPL")
	private Double imanpl;

	@Column(name = "IMAPSC")
	private String imapsc;

	@Column(name = "IMASHL")
	private String imashl;

	//
	@Column(name = "IMAUOM")
	private String imauom;

	@Column(name = "IMAVRT")
	private Double imavrt;

	@Column(name = "IMBACK")
	private String imback;

	@Column(name = "IMBBDD")
	private Double imbbdd;

	@Column(name = "IMBPFG")
	private String imbpfg;

	@Column(name = "IMBUYR")
	private Double imbuyr;

	@Column(name = "IMCARP")
	private Double imcarp;

	@Column(name = "IMCARS")
	private Double imcars;

	@Column(name = "IMCDCD")
	private String imcdcd;

	@Column(name = "IMCKAV")
	private String imckav;

	@Column(name = "IMCLEV")
	private String imclev;

	@Column(name = "IMCMCG")
	private String imcmcg;

	@Column(name = "IMCMDM")
	private String imcmdm;

	@Column(name = "IMCMETH")
	private String imcmeth;

	@Column(name = "IMCMGL")
	private String imcmgl;

	@Column(name = "IMCOBY")
	private String imcoby;

	@Column(name = "IMCOMH")
	private Double imcomh;

	@Column(name = "IMCONB")
	private String imconb;

	@Column(name = "IMCONT")
	private String imcont;

	@Column(name = "IMCOTY")
	private String imcoty;

	@Column(name = "IMCUTH")
	private Double imcuth;

	@Column(name = "IMCYCL")
	private String imcycl;

	@Column(name = "IMDEFD")
	private Double imdefd;

	@Column(name = "IMDFENDITM")
	private String imdfenditm;

	@Column(name = "IMDFTPCT")
	private Double imdftpct;

	@Column(name = "IMDLTL")
	private Double imdltl;

	@Column(name = "IMDPPO")
	private String imdppo;

	@Column(name = "IMDRAW")
	private String imdraw;

	@Column(name = "IMDSC1")
	private String imdsc1;

	@Column(name = "IMDSC2")
	private String imdsc2;

	@Column(name = "IMDSGP")
	private String imdsgp;

	@Column(name = "IMDSZE")
	private String imdsze;

	@Column(name = "IMDUAL")
	private String imdual;

	@Column(name = "IMEQTY")
	private String imeqty;

	@Column(name = "IMEXPD")
	private Double imexpd;

	@Column(name = "IMEXPI")
	private String imexpi;

	@Column(name = "IMFIFO")
	private String imfifo;

	@Column(name = "IMFRGD")
	private String imfrgd;

	@Column(name = "IMFRMP")
	private Double imfrmp;

	@Column(name = "IMGCMP")
	private String imgcmp;

	@Column(name = "IMGLPT")
	private String imglpt;

	@Column(name = "IMIFLA")
	private String imifla;

	@Column(name = "IMINMG")
	private String iminmg;

	@Column(name = "IMITC")
	private String imitc;

	@Column(name = "IMJOBN")
	private String imjobn;

	@Column(name = "IMKANEXLL")
	private String imkanexll;

	@Column(name = "IMKBIT")
	private String imkbit;

	@Column(name = "IMLAF")
	private String imlaf;

	@Column(name = "IMLECM")
	private String imlecm;

	@Column(name = "IMLEDD")
	private Double imledd;

	@Column(name = "IMLINE")
	private String imline;

	@Column(name = "IMLITM")
	private String imlitm;

	@Column(name = "IMLLX")
	private Double imllx;

	@Column(name = "IMLMFG")
	private String imlmfg;

	@Column(name = "IMLNPA")
	private String imlnpa;

	@Column(name = "IMLNTY")
	private String imlnty;

	@Column(name = "IMLOTC")
	private String imlotc;

	@Column(name = "IMLOTS")
	private String imlots;

	@Column(name = "IMLTCM")
	private Double imltcm;

	@Column(name = "IMLTFM")
	private String imltfm;

	@Column(name = "IMLTLV")
	private Double imltlv;

	@Column(name = "IMLTMF")
	private Double imltmf;

	@Column(name = "IMLTPU")
	private Double imltpu;

	@Column(name = "IMMAKE")
	private String immake;

	@Column(name = "IMMIC")
	private String immic;

	@Column(name = "IMMLQ")
	private Double immlq;

	@Column(name = "IMMMPC")
	private Double immmpc;

	@Column(name = "IMMPSP")
	private String immpsp;

	@Column(name = "IMMPST")
	private String immpst;

	@Column(name = "IMMRPP")
	private String immrpp;

	@Column(name = "IMMTF1")
	private Double immtf1;

	@Column(name = "IMMTF2")
	private Double immtf2;

	@Column(name = "IMMTF3")
	private Double immtf3;

	@Column(name = "IMMTF4")
	private Double immtf4;

	@Column(name = "IMMTF5")
	private Double immtf5;

	@Column(name = "IMOPC")
	private String imopc;

	@Column(name = "IMOPTH")
	private Double imopth;

	@Column(name = "IMOPV")
	private Double imopv;

	@Column(name = "IMORDW")
	private String imordw;

	@Column(name = "IMORPR")
	private String imorpr;

	@Column(name = "IMOT1Y")
	private String imot1y;

	@Column(name = "IMOT2Y")
	private String imot2y;

	@Column(name = "IMOVR")
	private String imovr;

	@Column(name = "IMPCTM")
	private Double impctm;

	@Column(name = "IMPDGR")
	private String impdgr;

	@Column(name = "IMPEFD")
	private Double impefd;

	@Column(name = "IMPID")
	private String impid;

	@Column(name = "IMPLEV")
	private String implev;

	@Column(name = "IMPMTH")
	private String impmth;

	@Column(name = "IMPOC")
	private String impoc;

	@Column(name = "IMPPLV")
	private String impplv;

	@Column(name = "IMPRGR")
	private String imprgr;

	@Column(name = "IMPRI1")
	private Double impri1;

	@Column(name = "IMPRI2")
	private Double impri2;

	@Column(name = "IMPRP0")
	private String imprp0;

	@Column(name = "IMPRP1")
	private String imprp1;

	@Column(name = "IMPRP2")
	private String imprp2;

	@Column(name = "IMPRP3")
	private String imprp3;

	@Column(name = "IMPRP4")
	private String imprp4;

	@Column(name = "IMPRP5")
	private String imprp5;

	@Column(name = "IMPRP6")
	private String imprp6;

	@Column(name = "IMPRP7")
	private String imprp7;

	@Column(name = "IMPRP8")
	private String imprp8;

	@Column(name = "IMPRP9")
	private String imprp9;

	@Column(name = "IMPRPO")
	private String imprpo;

	@Column(name = "IMPTSC")
	private String imptsc;

	@Column(name = "IMRPRC")
	private String imrprc;

	@Column(name = "IMRVNO")
	private String imrvno;

	@Column(name = "IMRWLA")
	private String imrwla;

	@Column(name = "IMSBDD")
	private Double imsbdd;

	@Column(name = "IMSCC0")
	private String imscc0;

	@Column(name = "IMSCPSELL")
	private String imscpsell;

	@Column(name = "IMSEG0")
	private String imseg0;

	@Column(name = "IMSEG1")
	private String imseg1;

	@Column(name = "IMSEG2")
	private String imseg2;

	@Column(name = "IMSEG3")
	private String imseg3;

	@Column(name = "IMSEG4")
	private String imseg4;

	@Column(name = "IMSEG5")
	private String imseg5;

	@Column(name = "IMSEG6")
	private String imseg6;

	@Column(name = "IMSEG7")
	private String imseg7;

	@Column(name = "IMSEG8")
	private String imseg8;

	@Column(name = "IMSEG9")
	private String imseg9;

	@Column(name = "IMSFLT")
	private Double imsflt;

	@Column(name = "IMSHCM")
	private String imshcm;

	@Column(name = "IMSHCN")
	private String imshcn;

	@Column(name = "IMSLD")
	private Double imsld;

	@Column(name = "IMSNS")
	private String imsns;

	@Column(name = "IMSRCE")
	private String imsrce;

	@Column(name = "IMSRNR")
	private String imsrnr;

	@Column(name = "IMSRP0")
	private String imsrp0;

	@Column(name = "IMSRP1")
	private String imsrp1;

	@Column(name = "IMSRP2")
	private String imsrp2;

	@Column(name = "IMSRP3")
	private String imsrp3;

	@Column(name = "IMSRP4")
	private String imsrp4;

	@Column(name = "IMSRP5")
	private String imsrp5;

	@Column(name = "IMSRP6")
	private String imsrp6;

	@Column(name = "IMSRP7")
	private String imsrp7;

	@Column(name = "IMSRP8")
	private String imsrp8;

	@Column(name = "IMSRP9")
	private String imsrp9;

	@Column(name = "IMSRTX")
	private String imsrtx;

	@Column(name = "IMSTDG")
	private String imstdg;

	@Column(name = "IMSTDP")
	private Double imstdp;

	@Column(name = "IMSTKT")
	private String imstkt;

	@Column(name = "IMSUTM")
	private String imsutm;

	@Column(name = "IMTDAY")
	private Double imtday;

	@Column(name = "IMTFLA")
	private String imtfla;

	@Column(name = "IMTHGD")
	private String imthgd;

	@Column(name = "IMTHRP")
	private Double imthrp;

	@Column(name = "IMTMPL")
	private String imtmpl;

	@Column(name = "IMU1DD")
	private Double imu1dd;

	@Column(name = "IMU2DD")
	private Double imu2dd;

	@Column(name = "IMU3DD")
	private Double imu3dd;

	@Column(name = "IMU4DD")
	private Double imu4dd;

	@Column(name = "IMU5DD")
	private Double imu5dd;

	@Column(name = "IMUMDF")
	private String imumdf;

	@Column(name = "IMUMS0")
	private String imums0;

	@Column(name = "IMUMS1")
	private String imums1;

	@Column(name = "IMUMS2")
	private String imums2;

	@Column(name = "IMUMS3")
	private String imums3;

	@Column(name = "IMUMS4")
	private String imums4;

	@Column(name = "IMUMS5")
	private String imums5;

	@Column(name = "IMUMS6")
	private String imums6;

	@Column(name = "IMUMS7")
	private String imums7;

	@Column(name = "IMUMS8")
	private String imums8;

	@Column(name = "IMUMTH")
	private String imumth;

	@Column(name = "IMUMUP")
	private String imumup;

	@Column(name = "IMUMVW")
	private String imumvw;

	//Esteeee
	@Column(name = "IMUOM1")
	private String imuom1;

	@Column(name = "IMUOM2")
	private String imuom2;

	@Column(name = "IMUOM3")
	private String imuom3;

	@Column(name = "IMUOM4")
	private String imuom4;

	@Column(name = "IMUOM6")
	private String imuom6;

	@Column(name = "IMUOM8")
	private String imuom8;

	@Column(name = "IMUOM9")
	private String imuom9;

	@Column(name = "IMUPCN")
	private String imupcn;

	@Column(name = "IMUPMJ")
	private BigDecimal imupmj;

	@Column(name = "IMURAB")
	private Double imurab;

	@Column(name = "IMURAT")
	private Double imurat;

	@Column(name = "IMURCD")
	private String imurcd;

	@Column(name = "IMURDT")
	private BigDecimal imurdt;

	@Column(name = "IMURRF")
	private String imurrf;

	@Column(name = "IMUSER")
	private String imuser;

	@Column(name = "IMUVM1")
	private String imuvm1;

	@Column(name = "IMUWUM")
	private String imuwum;

	@Column(name = "IMVCUD")
	private Double imvcud;

	@Column(name = "IMVMINV")
	private String imvminv;

	@Column(name = "IMWARR")
	private String imwarr;

	@Column(name = "IMWTRQ")
	private String imwtrq;

	@Column(name = "IMXDCK")
	private String imxdck;

	public F4101() {
	}

	public Double getImitm() {
		return this.imitm;
	}

	public void setImitm(Double imitm) {
		this.imitm = imitm;
	}

	public String getImabci() {
		return this.imabci;
	}

	public void setImabci(String imabci) {
		this.imabci = imabci;
	}

	public String getImabcm() {
		return this.imabcm;
	}

	public void setImabcm(String imabcm) {
		this.imabcm = imabcm;
	}

	public String getImabcs() {
		return this.imabcs;
	}

	public void setImabcs(String imabcs) {
		this.imabcs = imabcs;
	}

	public Double getImacq() {
		return this.imacq;
	}

	public void setImacq(Double imacq) {
		this.imacq = imacq;
	}

	public String getImaing() {
		return this.imaing;
	}

	public void setImaing(String imaing) {
		this.imaing = imaing;
	}

	public String getImaitm() {
		return this.imaitm;
	}

	public void setImaitm(String imaitm) {
		this.imaitm = imaitm;
	}

	public String getImaln() {
		return this.imaln;
	}

	public void setImaln(String imaln) {
		this.imaln = imaln;
	}

	public Double getImanpl() {
		return this.imanpl;
	}

	public void setImanpl(Double imanpl) {
		this.imanpl = imanpl;
	}

	public String getImapsc() {
		return this.imapsc;
	}

	public void setImapsc(String imapsc) {
		this.imapsc = imapsc;
	}

	public String getImashl() {
		return this.imashl;
	}

	public void setImashl(String imashl) {
		this.imashl = imashl;
	}

	public String getImauom() {
		return this.imauom;
	}

	public void setImauom(String imauom) {
		this.imauom = imauom;
	}

	public Double getImavrt() {
		return this.imavrt;
	}

	public void setImavrt(Double imavrt) {
		this.imavrt = imavrt;
	}

	public String getImback() {
		return this.imback;
	}

	public void setImback(String imback) {
		this.imback = imback;
	}

	public Double getImbbdd() {
		return this.imbbdd;
	}

	public void setImbbdd(Double imbbdd) {
		this.imbbdd = imbbdd;
	}

	public String getImbpfg() {
		return this.imbpfg;
	}

	public void setImbpfg(String imbpfg) {
		this.imbpfg = imbpfg;
	}

	public Double getImbuyr() {
		return this.imbuyr;
	}

	public void setImbuyr(Double imbuyr) {
		this.imbuyr = imbuyr;
	}

	public Double getImcarp() {
		return this.imcarp;
	}

	public void setImcarp(Double imcarp) {
		this.imcarp = imcarp;
	}

	public Double getImcars() {
		return this.imcars;
	}

	public void setImcars(Double imcars) {
		this.imcars = imcars;
	}

	public String getImcdcd() {
		return this.imcdcd;
	}

	public void setImcdcd(String imcdcd) {
		this.imcdcd = imcdcd;
	}

	public String getImckav() {
		return this.imckav;
	}

	public void setImckav(String imckav) {
		this.imckav = imckav;
	}

	public String getImclev() {
		return this.imclev;
	}

	public void setImclev(String imclev) {
		this.imclev = imclev;
	}

	public String getImcmcg() {
		return this.imcmcg;
	}

	public void setImcmcg(String imcmcg) {
		this.imcmcg = imcmcg;
	}

	public String getImcmdm() {
		return this.imcmdm;
	}

	public void setImcmdm(String imcmdm) {
		this.imcmdm = imcmdm;
	}

	public String getImcmeth() {
		return this.imcmeth;
	}

	public void setImcmeth(String imcmeth) {
		this.imcmeth = imcmeth;
	}

	public String getImcmgl() {
		return this.imcmgl;
	}

	public void setImcmgl(String imcmgl) {
		this.imcmgl = imcmgl;
	}

	public String getImcoby() {
		return this.imcoby;
	}

	public void setImcoby(String imcoby) {
		this.imcoby = imcoby;
	}

	public Double getImcomh() {
		return this.imcomh;
	}

	public void setImcomh(Double imcomh) {
		this.imcomh = imcomh;
	}

	public String getImconb() {
		return this.imconb;
	}

	public void setImconb(String imconb) {
		this.imconb = imconb;
	}

	public String getImcont() {
		return this.imcont;
	}

	public void setImcont(String imcont) {
		this.imcont = imcont;
	}

	public String getImcoty() {
		return this.imcoty;
	}

	public void setImcoty(String imcoty) {
		this.imcoty = imcoty;
	}

	public Double getImcuth() {
		return this.imcuth;
	}

	public void setImcuth(Double imcuth) {
		this.imcuth = imcuth;
	}

	public String getImcycl() {
		return this.imcycl;
	}

	public void setImcycl(String imcycl) {
		this.imcycl = imcycl;
	}

	public Double getImdefd() {
		return this.imdefd;
	}

	public void setImdefd(Double imdefd) {
		this.imdefd = imdefd;
	}

	public String getImdfenditm() {
		return this.imdfenditm;
	}

	public void setImdfenditm(String imdfenditm) {
		this.imdfenditm = imdfenditm;
	}

	public Double getImdftpct() {
		return this.imdftpct;
	}

	public void setImdftpct(Double imdftpct) {
		this.imdftpct = imdftpct;
	}

	public Double getImdltl() {
		return this.imdltl;
	}

	public void setImdltl(Double imdltl) {
		this.imdltl = imdltl;
	}

	public String getImdppo() {
		return this.imdppo;
	}

	public void setImdppo(String imdppo) {
		this.imdppo = imdppo;
	}

	public String getImdraw() {
		return this.imdraw;
	}

	public void setImdraw(String imdraw) {
		this.imdraw = imdraw;
	}

	public String getImdsc1() {
		return this.imdsc1;
	}

	public void setImdsc1(String imdsc1) {
		this.imdsc1 = imdsc1;
	}

	public String getImdsc2() {
		return this.imdsc2;
	}

	public void setImdsc2(String imdsc2) {
		this.imdsc2 = imdsc2;
	}

	public String getImdsgp() {
		return this.imdsgp;
	}

	public void setImdsgp(String imdsgp) {
		this.imdsgp = imdsgp;
	}

	public String getImdsze() {
		return this.imdsze;
	}

	public void setImdsze(String imdsze) {
		this.imdsze = imdsze;
	}

	public String getImdual() {
		return this.imdual;
	}

	public void setImdual(String imdual) {
		this.imdual = imdual;
	}

	public String getImeqty() {
		return this.imeqty;
	}

	public void setImeqty(String imeqty) {
		this.imeqty = imeqty;
	}

	public Double getImexpd() {
		return this.imexpd;
	}

	public void setImexpd(Double imexpd) {
		this.imexpd = imexpd;
	}

	public String getImexpi() {
		return this.imexpi;
	}

	public void setImexpi(String imexpi) {
		this.imexpi = imexpi;
	}

	public String getImfifo() {
		return this.imfifo;
	}

	public void setImfifo(String imfifo) {
		this.imfifo = imfifo;
	}

	public String getImfrgd() {
		return this.imfrgd;
	}

	public void setImfrgd(String imfrgd) {
		this.imfrgd = imfrgd;
	}

	public Double getImfrmp() {
		return this.imfrmp;
	}

	public void setImfrmp(Double imfrmp) {
		this.imfrmp = imfrmp;
	}

	public String getImgcmp() {
		return this.imgcmp;
	}

	public void setImgcmp(String imgcmp) {
		this.imgcmp = imgcmp;
	}

	public String getImglpt() {
		return this.imglpt;
	}

	public void setImglpt(String imglpt) {
		this.imglpt = imglpt;
	}

	public String getImifla() {
		return this.imifla;
	}

	public void setImifla(String imifla) {
		this.imifla = imifla;
	}

	public String getIminmg() {
		return this.iminmg;
	}

	public void setIminmg(String iminmg) {
		this.iminmg = iminmg;
	}

	public String getImitc() {
		return this.imitc;
	}

	public void setImitc(String imitc) {
		this.imitc = imitc;
	}

	public String getImjobn() {
		return this.imjobn;
	}

	public void setImjobn(String imjobn) {
		this.imjobn = imjobn;
	}

	public String getImkanexll() {
		return this.imkanexll;
	}

	public void setImkanexll(String imkanexll) {
		this.imkanexll = imkanexll;
	}

	public String getImkbit() {
		return this.imkbit;
	}

	public void setImkbit(String imkbit) {
		this.imkbit = imkbit;
	}

	public String getImlaf() {
		return this.imlaf;
	}

	public void setImlaf(String imlaf) {
		this.imlaf = imlaf;
	}

	public String getImlecm() {
		return this.imlecm;
	}

	public void setImlecm(String imlecm) {
		this.imlecm = imlecm;
	}

	public Double getImledd() {
		return this.imledd;
	}

	public void setImledd(Double imledd) {
		this.imledd = imledd;
	}

	public String getImline() {
		return this.imline;
	}

	public void setImline(String imline) {
		this.imline = imline;
	}

	public String getImlitm() {
		return this.imlitm;
	}

	public void setImlitm(String imlitm) {
		this.imlitm = imlitm;
	}

	public Double getImllx() {
		return this.imllx;
	}

	public void setImllx(Double imllx) {
		this.imllx = imllx;
	}

	public String getImlmfg() {
		return this.imlmfg;
	}

	public void setImlmfg(String imlmfg) {
		this.imlmfg = imlmfg;
	}

	public String getImlnpa() {
		return this.imlnpa;
	}

	public void setImlnpa(String imlnpa) {
		this.imlnpa = imlnpa;
	}

	public String getImlnty() {
		return this.imlnty;
	}

	public void setImlnty(String imlnty) {
		this.imlnty = imlnty;
	}

	public String getImlotc() {
		return this.imlotc;
	}

	public void setImlotc(String imlotc) {
		this.imlotc = imlotc;
	}

	public String getImlots() {
		return this.imlots;
	}

	public void setImlots(String imlots) {
		this.imlots = imlots;
	}

	public Double getImltcm() {
		return this.imltcm;
	}

	public void setImltcm(Double imltcm) {
		this.imltcm = imltcm;
	}

	public String getImltfm() {
		return this.imltfm;
	}

	public void setImltfm(String imltfm) {
		this.imltfm = imltfm;
	}

	public Double getImltlv() {
		return this.imltlv;
	}

	public void setImltlv(Double imltlv) {
		this.imltlv = imltlv;
	}

	public Double getImltmf() {
		return this.imltmf;
	}

	public void setImltmf(Double imltmf) {
		this.imltmf = imltmf;
	}

	public Double getImltpu() {
		return this.imltpu;
	}

	public void setImltpu(Double imltpu) {
		this.imltpu = imltpu;
	}

	public String getImmake() {
		return this.immake;
	}

	public void setImmake(String immake) {
		this.immake = immake;
	}

	public String getImmic() {
		return this.immic;
	}

	public void setImmic(String immic) {
		this.immic = immic;
	}

	public Double getImmlq() {
		return this.immlq;
	}

	public void setImmlq(Double immlq) {
		this.immlq = immlq;
	}

	public Double getImmmpc() {
		return this.immmpc;
	}

	public void setImmmpc(Double immmpc) {
		this.immmpc = immmpc;
	}

	public String getImmpsp() {
		return this.immpsp;
	}

	public void setImmpsp(String immpsp) {
		this.immpsp = immpsp;
	}

	public String getImmpst() {
		return this.immpst;
	}

	public void setImmpst(String immpst) {
		this.immpst = immpst;
	}

	public String getImmrpp() {
		return this.immrpp;
	}

	public void setImmrpp(String immrpp) {
		this.immrpp = immrpp;
	}

	public Double getImmtf1() {
		return this.immtf1;
	}

	public void setImmtf1(Double immtf1) {
		this.immtf1 = immtf1;
	}

	public Double getImmtf2() {
		return this.immtf2;
	}

	public void setImmtf2(Double immtf2) {
		this.immtf2 = immtf2;
	}

	public Double getImmtf3() {
		return this.immtf3;
	}

	public void setImmtf3(Double immtf3) {
		this.immtf3 = immtf3;
	}

	public Double getImmtf4() {
		return this.immtf4;
	}

	public void setImmtf4(Double immtf4) {
		this.immtf4 = immtf4;
	}

	public Double getImmtf5() {
		return this.immtf5;
	}

	public void setImmtf5(Double immtf5) {
		this.immtf5 = immtf5;
	}

	public String getImopc() {
		return this.imopc;
	}

	public void setImopc(String imopc) {
		this.imopc = imopc;
	}

	public Double getImopth() {
		return this.imopth;
	}

	public void setImopth(Double imopth) {
		this.imopth = imopth;
	}

	public Double getImopv() {
		return this.imopv;
	}

	public void setImopv(Double imopv) {
		this.imopv = imopv;
	}

	public String getImordw() {
		return this.imordw;
	}

	public void setImordw(String imordw) {
		this.imordw = imordw;
	}

	public String getImorpr() {
		return this.imorpr;
	}

	public void setImorpr(String imorpr) {
		this.imorpr = imorpr;
	}

	public String getImot1y() {
		return this.imot1y;
	}

	public void setImot1y(String imot1y) {
		this.imot1y = imot1y;
	}

	public String getImot2y() {
		return this.imot2y;
	}

	public void setImot2y(String imot2y) {
		this.imot2y = imot2y;
	}

	public String getImovr() {
		return this.imovr;
	}

	public void setImovr(String imovr) {
		this.imovr = imovr;
	}

	public Double getImpctm() {
		return this.impctm;
	}

	public void setImpctm(Double impctm) {
		this.impctm = impctm;
	}

	public String getImpdgr() {
		return this.impdgr;
	}

	public void setImpdgr(String impdgr) {
		this.impdgr = impdgr;
	}

	public Double getImpefd() {
		return this.impefd;
	}

	public void setImpefd(Double impefd) {
		this.impefd = impefd;
	}

	public String getImpid() {
		return this.impid;
	}

	public void setImpid(String impid) {
		this.impid = impid;
	}

	public String getImplev() {
		return this.implev;
	}

	public void setImplev(String implev) {
		this.implev = implev;
	}

	public String getImpmth() {
		return this.impmth;
	}

	public void setImpmth(String impmth) {
		this.impmth = impmth;
	}

	public String getImpoc() {
		return this.impoc;
	}

	public void setImpoc(String impoc) {
		this.impoc = impoc;
	}

	public String getImpplv() {
		return this.impplv;
	}

	public void setImpplv(String impplv) {
		this.impplv = impplv;
	}

	public String getImprgr() {
		return this.imprgr;
	}

	public void setImprgr(String imprgr) {
		this.imprgr = imprgr;
	}

	public Double getImpri1() {
		return this.impri1;
	}

	public void setImpri1(Double impri1) {
		this.impri1 = impri1;
	}

	public Double getImpri2() {
		return this.impri2;
	}

	public void setImpri2(Double impri2) {
		this.impri2 = impri2;
	}

	public String getImprp0() {
		return this.imprp0;
	}

	public void setImprp0(String imprp0) {
		this.imprp0 = imprp0;
	}

	public String getImprp1() {
		return this.imprp1;
	}

	public void setImprp1(String imprp1) {
		this.imprp1 = imprp1;
	}

	public String getImprp2() {
		return this.imprp2;
	}

	public void setImprp2(String imprp2) {
		this.imprp2 = imprp2;
	}

	public String getImprp3() {
		return this.imprp3;
	}

	public void setImprp3(String imprp3) {
		this.imprp3 = imprp3;
	}

	public String getImprp4() {
		return this.imprp4;
	}

	public void setImprp4(String imprp4) {
		this.imprp4 = imprp4;
	}

	public String getImprp5() {
		return this.imprp5;
	}

	public void setImprp5(String imprp5) {
		this.imprp5 = imprp5;
	}

	public String getImprp6() {
		return this.imprp6;
	}

	public void setImprp6(String imprp6) {
		this.imprp6 = imprp6;
	}

	public String getImprp7() {
		return this.imprp7;
	}

	public void setImprp7(String imprp7) {
		this.imprp7 = imprp7;
	}

	public String getImprp8() {
		return this.imprp8;
	}

	public void setImprp8(String imprp8) {
		this.imprp8 = imprp8;
	}

	public String getImprp9() {
		return this.imprp9;
	}

	public void setImprp9(String imprp9) {
		this.imprp9 = imprp9;
	}

	public String getImprpo() {
		return this.imprpo;
	}

	public void setImprpo(String imprpo) {
		this.imprpo = imprpo;
	}

	public String getImptsc() {
		return this.imptsc;
	}

	public void setImptsc(String imptsc) {
		this.imptsc = imptsc;
	}

	public String getImrprc() {
		return this.imrprc;
	}

	public void setImrprc(String imrprc) {
		this.imrprc = imrprc;
	}

	public String getImrvno() {
		return this.imrvno;
	}

	public void setImrvno(String imrvno) {
		this.imrvno = imrvno;
	}

	public String getImrwla() {
		return this.imrwla;
	}

	public void setImrwla(String imrwla) {
		this.imrwla = imrwla;
	}

	public Double getImsbdd() {
		return this.imsbdd;
	}

	public void setImsbdd(Double imsbdd) {
		this.imsbdd = imsbdd;
	}

	public String getImscc0() {
		return this.imscc0;
	}

	public void setImscc0(String imscc0) {
		this.imscc0 = imscc0;
	}

	public String getImscpsell() {
		return this.imscpsell;
	}

	public void setImscpsell(String imscpsell) {
		this.imscpsell = imscpsell;
	}

	public String getImseg0() {
		return this.imseg0;
	}

	public void setImseg0(String imseg0) {
		this.imseg0 = imseg0;
	}

	public String getImseg1() {
		return this.imseg1;
	}

	public void setImseg1(String imseg1) {
		this.imseg1 = imseg1;
	}

	public String getImseg2() {
		return this.imseg2;
	}

	public void setImseg2(String imseg2) {
		this.imseg2 = imseg2;
	}

	public String getImseg3() {
		return this.imseg3;
	}

	public void setImseg3(String imseg3) {
		this.imseg3 = imseg3;
	}

	public String getImseg4() {
		return this.imseg4;
	}

	public void setImseg4(String imseg4) {
		this.imseg4 = imseg4;
	}

	public String getImseg5() {
		return this.imseg5;
	}

	public void setImseg5(String imseg5) {
		this.imseg5 = imseg5;
	}

	public String getImseg6() {
		return this.imseg6;
	}

	public void setImseg6(String imseg6) {
		this.imseg6 = imseg6;
	}

	public String getImseg7() {
		return this.imseg7;
	}

	public void setImseg7(String imseg7) {
		this.imseg7 = imseg7;
	}

	public String getImseg8() {
		return this.imseg8;
	}

	public void setImseg8(String imseg8) {
		this.imseg8 = imseg8;
	}

	public String getImseg9() {
		return this.imseg9;
	}

	public void setImseg9(String imseg9) {
		this.imseg9 = imseg9;
	}

	public Double getImsflt() {
		return this.imsflt;
	}

	public void setImsflt(Double imsflt) {
		this.imsflt = imsflt;
	}

	public String getImshcm() {
		return this.imshcm;
	}

	public void setImshcm(String imshcm) {
		this.imshcm = imshcm;
	}

	public String getImshcn() {
		return this.imshcn;
	}

	public void setImshcn(String imshcn) {
		this.imshcn = imshcn;
	}

	public Double getImsld() {
		return this.imsld;
	}

	public void setImsld(Double imsld) {
		this.imsld = imsld;
	}

	public String getImsns() {
		return this.imsns;
	}

	public void setImsns(String imsns) {
		this.imsns = imsns;
	}

	public String getImsrce() {
		return this.imsrce;
	}

	public void setImsrce(String imsrce) {
		this.imsrce = imsrce;
	}

	public String getImsrnr() {
		return this.imsrnr;
	}

	public void setImsrnr(String imsrnr) {
		this.imsrnr = imsrnr;
	}

	public String getImsrp0() {
		return this.imsrp0;
	}

	public void setImsrp0(String imsrp0) {
		this.imsrp0 = imsrp0;
	}

	public String getImsrp1() {
		return this.imsrp1;
	}

	public void setImsrp1(String imsrp1) {
		this.imsrp1 = imsrp1;
	}

	public String getImsrp2() {
		return this.imsrp2;
	}

	public void setImsrp2(String imsrp2) {
		this.imsrp2 = imsrp2;
	}

	public String getImsrp3() {
		return this.imsrp3;
	}

	public void setImsrp3(String imsrp3) {
		this.imsrp3 = imsrp3;
	}

	public String getImsrp4() {
		return this.imsrp4;
	}

	public void setImsrp4(String imsrp4) {
		this.imsrp4 = imsrp4;
	}

	public String getImsrp5() {
		return this.imsrp5;
	}

	public void setImsrp5(String imsrp5) {
		this.imsrp5 = imsrp5;
	}

	public String getImsrp6() {
		return this.imsrp6;
	}

	public void setImsrp6(String imsrp6) {
		this.imsrp6 = imsrp6;
	}

	public String getImsrp7() {
		return this.imsrp7;
	}

	public void setImsrp7(String imsrp7) {
		this.imsrp7 = imsrp7;
	}

	public String getImsrp8() {
		return this.imsrp8;
	}

	public void setImsrp8(String imsrp8) {
		this.imsrp8 = imsrp8;
	}

	public String getImsrp9() {
		return this.imsrp9;
	}

	public void setImsrp9(String imsrp9) {
		this.imsrp9 = imsrp9;
	}

	public String getImsrtx() {
		return this.imsrtx;
	}

	public void setImsrtx(String imsrtx) {
		this.imsrtx = imsrtx;
	}

	public String getImstdg() {
		return this.imstdg;
	}

	public void setImstdg(String imstdg) {
		this.imstdg = imstdg;
	}

	public Double getImstdp() {
		return this.imstdp;
	}

	public void setImstdp(Double imstdp) {
		this.imstdp = imstdp;
	}

	public String getImstkt() {
		return this.imstkt;
	}

	public void setImstkt(String imstkt) {
		this.imstkt = imstkt;
	}

	public String getImsutm() {
		return this.imsutm;
	}

	public void setImsutm(String imsutm) {
		this.imsutm = imsutm;
	}

	public Double getImtday() {
		return this.imtday;
	}

	public void setImtday(Double imtday) {
		this.imtday = imtday;
	}

	public String getImtfla() {
		return this.imtfla;
	}

	public void setImtfla(String imtfla) {
		this.imtfla = imtfla;
	}

	public String getImthgd() {
		return this.imthgd;
	}

	public void setImthgd(String imthgd) {
		this.imthgd = imthgd;
	}

	public Double getImthrp() {
		return this.imthrp;
	}

	public void setImthrp(Double imthrp) {
		this.imthrp = imthrp;
	}

	public String getImtmpl() {
		return this.imtmpl;
	}

	public void setImtmpl(String imtmpl) {
		this.imtmpl = imtmpl;
	}

	public Double getImu1dd() {
		return this.imu1dd;
	}

	public void setImu1dd(Double imu1dd) {
		this.imu1dd = imu1dd;
	}

	public Double getImu2dd() {
		return this.imu2dd;
	}

	public void setImu2dd(Double imu2dd) {
		this.imu2dd = imu2dd;
	}

	public Double getImu3dd() {
		return this.imu3dd;
	}

	public void setImu3dd(Double imu3dd) {
		this.imu3dd = imu3dd;
	}

	public Double getImu4dd() {
		return this.imu4dd;
	}

	public void setImu4dd(Double imu4dd) {
		this.imu4dd = imu4dd;
	}

	public Double getImu5dd() {
		return this.imu5dd;
	}

	public void setImu5dd(Double imu5dd) {
		this.imu5dd = imu5dd;
	}

	public String getImumdf() {
		return this.imumdf;
	}

	public void setImumdf(String imumdf) {
		this.imumdf = imumdf;
	}

	public String getImums0() {
		return this.imums0;
	}

	public void setImums0(String imums0) {
		this.imums0 = imums0;
	}

	public String getImums1() {
		return this.imums1;
	}

	public void setImums1(String imums1) {
		this.imums1 = imums1;
	}

	public String getImums2() {
		return this.imums2;
	}

	public void setImums2(String imums2) {
		this.imums2 = imums2;
	}

	public String getImums3() {
		return this.imums3;
	}

	public void setImums3(String imums3) {
		this.imums3 = imums3;
	}

	public String getImums4() {
		return this.imums4;
	}

	public void setImums4(String imums4) {
		this.imums4 = imums4;
	}

	public String getImums5() {
		return this.imums5;
	}

	public void setImums5(String imums5) {
		this.imums5 = imums5;
	}

	public String getImums6() {
		return this.imums6;
	}

	public void setImums6(String imums6) {
		this.imums6 = imums6;
	}

	public String getImums7() {
		return this.imums7;
	}

	public void setImums7(String imums7) {
		this.imums7 = imums7;
	}

	public String getImums8() {
		return this.imums8;
	}

	public void setImums8(String imums8) {
		this.imums8 = imums8;
	}

	public String getImumth() {
		return this.imumth;
	}

	public void setImumth(String imumth) {
		this.imumth = imumth;
	}

	public String getImumup() {
		return this.imumup;
	}

	public void setImumup(String imumup) {
		this.imumup = imumup;
	}

	public String getImumvw() {
		return this.imumvw;
	}

	public void setImumvw(String imumvw) {
		this.imumvw = imumvw;
	}

	public String getImuom1() {
		return this.imuom1;
	}

	public void setImuom1(String imuom1) {
		this.imuom1 = imuom1;
	}

	public String getImuom2() {
		return this.imuom2;
	}

	public void setImuom2(String imuom2) {
		this.imuom2 = imuom2;
	}

	public String getImuom3() {
		return this.imuom3;
	}

	public void setImuom3(String imuom3) {
		this.imuom3 = imuom3;
	}

	public String getImuom4() {
		return this.imuom4;
	}

	public void setImuom4(String imuom4) {
		this.imuom4 = imuom4;
	}

	public String getImuom6() {
		return this.imuom6;
	}

	public void setImuom6(String imuom6) {
		this.imuom6 = imuom6;
	}

	public String getImuom8() {
		return this.imuom8;
	}

	public void setImuom8(String imuom8) {
		this.imuom8 = imuom8;
	}

	public String getImuom9() {
		return this.imuom9;
	}

	public void setImuom9(String imuom9) {
		this.imuom9 = imuom9;
	}

	public String getImupcn() {
		return this.imupcn;
	}

	public void setImupcn(String imupcn) {
		this.imupcn = imupcn;
	}

	public BigDecimal getImupmj() {
		return this.imupmj;
	}

	public void setImupmj(BigDecimal imupmj) {
		this.imupmj = imupmj;
	}

	public Double getImurab() {
		return this.imurab;
	}

	public void setImurab(Double imurab) {
		this.imurab = imurab;
	}

	public Double getImurat() {
		return this.imurat;
	}

	public void setImurat(Double imurat) {
		this.imurat = imurat;
	}

	public String getImurcd() {
		return this.imurcd;
	}

	public void setImurcd(String imurcd) {
		this.imurcd = imurcd;
	}

	public BigDecimal getImurdt() {
		return this.imurdt;
	}

	public void setImurdt(BigDecimal imurdt) {
		this.imurdt = imurdt;
	}

	public String getImurrf() {
		return this.imurrf;
	}

	public void setImurrf(String imurrf) {
		this.imurrf = imurrf;
	}

	public String getImuser() {
		return this.imuser;
	}

	public void setImuser(String imuser) {
		this.imuser = imuser;
	}

	public String getImuvm1() {
		return this.imuvm1;
	}

	public void setImuvm1(String imuvm1) {
		this.imuvm1 = imuvm1;
	}

	public String getImuwum() {
		return this.imuwum;
	}

	public void setImuwum(String imuwum) {
		this.imuwum = imuwum;
	}

	public Double getImvcud() {
		return this.imvcud;
	}

	public void setImvcud(Double imvcud) {
		this.imvcud = imvcud;
	}

	public String getImvminv() {
		return this.imvminv;
	}

	public void setImvminv(String imvminv) {
		this.imvminv = imvminv;
	}

	public String getImwarr() {
		return this.imwarr;
	}

	public void setImwarr(String imwarr) {
		this.imwarr = imwarr;
	}

	public String getImwtrq() {
		return this.imwtrq;
	}

	public void setImwtrq(String imwtrq) {
		this.imwtrq = imwtrq;
	}

	public String getImxdck() {
		return this.imxdck;
	}

	public void setImxdck(String imxdck) {
		this.imxdck = imxdck;
	}

}