package modelo.inventario;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the F4211 database table.
 * 
 */
@Entity
@Table(name="F4211", schema="bizapps_inventario.dbo")
public class F4211 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private F4211PK id;

	@Column(name="SDAAID")
	private Double sdaaid;

	@Column(name="SDACOM")
	private String sdacom;

	@Column(name="SDADDJ")
	private BigDecimal sdaddj;

	@Column(name="SDADTM")
	private Double sdadtm;

	@Column(name="SDAEXP")
	private Double sdaexp;

	@Column(name="SDAITM")
	private String sdaitm;

	@Column(name="SDAN8")
	private Double sdan8;

	@Column(name="SDANBY")
	private Double sdanby;

	@Column(name="SDAOPN")
	private Double sdaopn;

	@Column(name="SDAPTS")
	private String sdapts;

	@Column(name="SDAPUM")
	private String sdapum;

	@Column(name="SDASN")
	private String sdasn;

	@Column(name="SDATXT")
	private String sdatxt;

	@Column(name="SDBACK")
	private String sdback;

	@Column(name="SDBCRC")
	private String sdbcrc;

	@Column(name="SDBSC")
	private String sdbsc;

	@Column(name="SDCADC")
	private Double sdcadc;

	@Column(name="SDCARS")
	private Double sdcars;

	@Column(name="SDCATNM")
	private String sdcatnm;

	@Column(name="SDCBSC")
	private String sdcbsc;

	@Column(name="SDCCIDLN")
	private Double sdccidln;

	@Column(name="SDCLVL")
	private String sdclvl;

	@Column(name="SDCMCG")
	private String sdcmcg;

	@Column(name="SDCMGP")
	private String sdcmgp;

	@Column(name="SDCNDJ")
	private BigDecimal sdcndj;

	@Column(name="SDCNID")
	private String sdcnid;

	@Column(name="SDCO")
	private String sdco;

	@Column(name="SDCOMM")
	private String sdcomm;

	@Column(name="SDCORD")
	private Double sdcord;

	@Column(name="SDCPNT")
	private Double sdcpnt;

	@Column(name="SDCRCD")
	private String sdcrcd;

	@Column(name="SDCRMD")
	private String sdcrmd;

	@Column(name="SDCRR")
	private Double sdcrr;

	@Column(name="SDCSTO")
	private String sdcsto;

	@Column(name="SDCTRY")
	private Double sdctry;

	@Column(name="SDDCT")
	private String sddct;

	@Column(name="SDDEID")
	private Double sddeid;

	@Column(name="SDDELN")
	private Double sddeln;

	@Column(name="SDDGL")
	private BigDecimal sddgl;

	@Column(name="SDDMBU")
	private String sddmbu;

	@Column(name="SDDMCS")
	private Double sddmcs;

	@Column(name="SDDMCT")
	private String sddmct;

	@Column(name="SDDOC")
	private Double sddoc;

	@Column(name="SDDRQJ")
	private BigDecimal sddrqj;

	@Column(name="SDDRQT")
	private Double sddrqt;

	@Column(name="SDDSC1")
	private String sddsc1;

	@Column(name="SDDSC2")
	private String sddsc2;

	@Column(name="SDDTBS")
	private String sddtbs;

	@Column(name="SDDTYS")
	private String sddtys;

	@Column(name="SDDUAL")
	private String sddual;

	@Column(name="SDDVAN")
	private Double sddvan;

	@Column(name="SDECST")
	private Double sdecst;

	@Column(name="SDEMCU")
	private String sdemcu;

	@Column(name="SDEUSE")
	private String sdeuse;

	@Column(name="SDEXDP")
	private Double sdexdp;

	@Column(name="SDEXR1")
	private String sdexr1;

	@Column(name="SDFEA")
	private Double sdfea;

	@Column(name="SDFEC")
	private Double sdfec;

	@Column(name="SDFPRC")
	private Double sdfprc;

	@Column(name="SDFRGD")
	private String sdfrgd;

	@Column(name="SDFRMP")
	private Double sdfrmp;

	@Column(name="SDFRTH")
	private String sdfrth;

	@Column(name="SDFUC")
	private Double sdfuc;

	@Column(name="SDFUN2")
	private Double sdfun2;

	@Column(name="SDFUP")
	private Double sdfup;

	@Column(name="SDFY")
	private Double sdfy;

	@Column(name="SDGLC")
	private String sdglc;

	@Column(name="SDGRWT")
	private Double sdgrwt;

	@Column(name="SDGWUM")
	private String sdgwum;

	@Column(name="SDHDBU")
	private String sdhdbu;

	@Column(name="SDHOLD")
	private String sdhold;

	@Column(name="SDINMG")
	private String sdinmg;

	@Column(name="SDIR01")
	private String sdir01;

	@Column(name="SDIR02")
	private String sdir02;

	@Column(name="SDIR03")
	private String sdir03;

	@Column(name="SDIR04")
	private String sdir04;

	@Column(name="SDIR05")
	private String sdir05;

	@Column(name="SDITM")
	private Double sditm;

	@Column(name="SDITVL")
	private Double sditvl;

	@Column(name="SDITWT")
	private Double sditwt;

	@Column(name="SDIVD")
	private BigDecimal sdivd;

	@Column(name="SDJOBN")
	private String sdjobn;

	@Column(name="SDKCO")
	private String sdkco;

	@Column(name="SDKTLN")
	private Double sdktln;

	@Column(name="SDKTP")
	private Double sdktp;

	@Column(name="SDLCOD")
	private String sdlcod;

	@Column(name="SDLITM")
	private String sdlitm;

	@Column(name="SDLNTY")
	private String sdlnty;

	@Column(name="SDLOB")
	private String sdlob;

	@Column(name="SDLOCN")
	private String sdlocn;

	@Column(name="SDLOTN")
	private String sdlotn;

	@Column(name="SDLPRC")
	private Double sdlprc;

	@Column(name="SDLTTR")
	private String sdlttr;

	@Column(name="SDMCLN")
	private Double sdmcln;

	@Column(name="SDMCU")
	private String sdmcu;

	@Column(name="SDMERL")
	private String sdmerl;

	@Column(name="SDMOT")
	private String sdmot;

	@Column(name="SDNTR")
	private String sdntr;

	@Column(name="SDNUMB")
	private Double sdnumb;

	@Column(name="SDNXTR")
	private String sdnxtr;

	@Column(name="SDOCTO")
	private String sdocto;

	@Column(name="SDODCT")
	private String sdodct;

	@Column(name="SDODLN")
	private Double sdodln;

	@Column(name="SDODOC")
	private Double sdodoc;

	@Column(name="SDOGNO")
	private Double sdogno;

	@Column(name="SDOKC")
	private String sdokc;

	@Column(name="SDOKCO")
	private String sdokco;

	@Column(name="SDOORN")
	private String sdoorn;

	@Column(name="SDOPDJ")
	private BigDecimal sdopdj;

	@Column(name="SDOPPID")
	private Double sdoppid;

	@Column(name="SDOPTT")
	private Double sdoptt;

	@Column(name="SDORP")
	private String sdorp;

	@Column(name="SDORPR")
	private String sdorpr;

	@Column(name="SDOSEQ")
	private Double sdoseq;

	@Column(name="SDOSTP")
	private String sdostp;

	@Column(name="SDOTQY")
	private String sdotqy;

	@Column(name="SDPA8")
	private Double sdpa8;

	@Column(name="SDPDDJ")
	private BigDecimal sdpddj;

	@Column(name="SDPDTT")
	private Double sdpdtt;

	@Column(name="SDPEFJ")
	private BigDecimal sdpefj;

	@Column(name="SDPEND")
	private String sdpend;

	@Column(name="SDPID")
	private String sdpid;

	@Column(name="SDPMDT")
	private Double sdpmdt;

	@Column(name="SDPMTN")
	private String sdpmtn;

	@Column(name="SDPMTO")
	private String sdpmto;

	@Column(name="SDPOE")
	private String sdpoe;

	@Column(name="SDPPDJ")
	private BigDecimal sdppdj;

	@Column(name="SDPQOR")
	private Double sdpqor;

	@Column(name="SDPRAN8")
	private Double sdpran8;

	@Column(name="SDPRCIDLN")
	private Double sdprcidln;

	@Column(name="SDPRGR")
	private String sdprgr;

	@Column(name="SDPRIO")
	private String sdprio;

	@Column(name="SDPRJM")
	private Double sdprjm;

	@Column(name="SDPROV")
	private String sdprov;

	@Column(name="SDPRP1")
	private String sdprp1;

	@Column(name="SDPRP2")
	private String sdprp2;

	@Column(name="SDPRP3")
	private String sdprp3;

	@Column(name="SDPRP4")
	private String sdprp4;

	@Column(name="SDPRP5")
	private String sdprp5;

	@Column(name="SDPSIG")
	private String sdpsig;

	@Column(name="SDPSN")
	private Double sdpsn;

	@Column(name="SDPSTM")
	private Double sdpstm;

	@Column(name="SDPTC")
	private String sdptc;

	@Column(name="SDQRLV")
	private Double sdqrlv;

	@Column(name="SDQTYT")
	private Double sdqtyt;

	@Column(name="SDRCD")
	private String sdrcd;

	@Column(name="SDRCTO")
	private String sdrcto;

	@Column(name="SDRESL")
	private String sdresl;

	@Column(name="SDRFRV")
	private String sdrfrv;

	@Column(name="SDRKCO")
	private String sdrkco;

	@Column(name="SDRKIT")
	private Double sdrkit;

	@Column(name="SDRLDJ")
	private BigDecimal sdrldj;

	@Column(name="SDRLIT")
	private String sdrlit;

	@Column(name="SDRLLN")
	private Double sdrlln;

	@Column(name="SDRLNU")
	private String sdrlnu;

	@Column(name="SDRLTM")
	private Double sdrltm;

	@Column(name="SDRORN")
	private String sdrorn;

	@Column(name="SDROUT")
	private String sdrout;

	@Column(name="SDRPRC")
	private String sdrprc;

	@Column(name="SDRSDJ")
	private BigDecimal sdrsdj;

	@Column(name="SDRSDT")
	private Double sdrsdt;

	@Column(name="SDRYIN")
	private String sdryin;

	@Column(name="SDSBAL")
	private String sdsbal;

	@Column(name="SDSBL")
	private String sdsbl;

	@Column(name="SDSBLT")
	private String sdsblt;

	@Column(name="SDSERN")
	private String sdsern;

	@Column(name="SDSFXO")
	private String sdsfxo;

	@Column(name="SDSHAN")
	private Double sdshan;

	@Column(name="SDSHCCIDLN")
	private Double sdshccidln;

	@Column(name="SDSHCM")
	private String sdshcm;

	@Column(name="SDSHCN")
	private String sdshcn;

	@Column(name="SDSHPN")
	private Double sdshpn;

	@Column(name="SDSO01")
	private String sdso01;

	@Column(name="SDSO02")
	private String sdso02;

	@Column(name="SDSO03")
	private String sdso03;

	@Column(name="SDSO04")
	private String sdso04;

	@Column(name="SDSO05")
	private String sdso05;

	@Column(name="SDSO06")
	private String sdso06;

	@Column(name="SDSO07")
	private String sdso07;

	@Column(name="SDSO08")
	private String sdso08;

	@Column(name="SDSO09")
	private String sdso09;

	@Column(name="SDSO10")
	private String sdso10;

	@Column(name="SDSO11")
	private String sdso11;

	@Column(name="SDSO12")
	private String sdso12;

	@Column(name="SDSO13")
	private String sdso13;

	@Column(name="SDSO14")
	private String sdso14;

	@Column(name="SDSO15")
	private String sdso15;

	@Column(name="SDSO16")
	private String sdso16;

	@Column(name="SDSO17")
	private String sdso17;

	@Column(name="SDSO18")
	private String sdso18;

	@Column(name="SDSO19")
	private String sdso19;

	@Column(name="SDSO20")
	private String sdso20;

	@Column(name="SDSOBK")
	private Double sdsobk;

	@Column(name="SDSOCN")
	private Double sdsocn;

	@Column(name="SDSONE")
	private Double sdsone;

	@Column(name="SDSOOR")
	private BigDecimal sdsoor;

	@Column(name="SDSOQS")
	private Double sdsoqs;

	@Column(name="SDSPATTN")
	private String sdspattn;

	@Column(name="SDSQOR")
	private Double sdsqor;

	@Column(name="SDSRP1")
	private String sdsrp1;

	@Column(name="SDSRP2")
	private String sdsrp2;

	@Column(name="SDSRP3")
	private String sdsrp3;

	@Column(name="SDSRP4")
	private String sdsrp4;

	@Column(name="SDSRP5")
	private String sdsrp5;

	@Column(name="SDSTOP")
	private String sdstop;

	@Column(name="SDSWMS")
	private String sdswms;

	@Column(name="SDTAX1")
	private String sdtax1;

	@Column(name="SDTCST")
	private Double sdtcst;

	@Column(name="SDTDAY")
	private Double sdtday;

	@Column(name="SDTHGD")
	private String sdthgd;

	@Column(name="SDTHRP")
	private Double sdthrp;

	@Column(name="SDTORG")
	private String sdtorg;

	@Column(name="SDTPC")
	private String sdtpc;

	@Column(name="SDTRDC")
	private Double sdtrdc;

	@Column(name="SDTRDJ")
	private BigDecimal sdtrdj;

	@Column(name="SDTXA1")
	private String sdtxa1;

	@Column(name="SDUKID")
	private Double sdukid;

	@Column(name="SDUNCD")
	private String sduncd;

	@Column(name="SDUNCS")
	private Double sduncs;

	@Column(name="SDUOM")
	private String sduom;

	@Column(name="SDUOM1")
	private String sduom1;

	@Column(name="SDUOM2")
	private String sduom2;

	@Column(name="SDUOM4")
	private String sduom4;

	@Column(name="SDUOPN")
	private Double sduopn;

	@Column(name="SDUORG")
	private Double sduorg;

	@Column(name="SDUPC1")
	private String sdupc1;

	@Column(name="SDUPC2")
	private String sdupc2;

	@Column(name="SDUPC3")
	private String sdupc3;

	@Column(name="SDUPMJ")
	private BigDecimal sdupmj;

	@Column(name="SDUPRC")
	private Double sduprc;

	@Column(name="SDURAB")
	private Double sdurab;

	@Column(name="SDURAT")
	private Double sdurat;

	@Column(name="SDURCD")
	private String sdurcd;

	@Column(name="SDURDT")
	private BigDecimal sdurdt;

	@Column(name="SDURRF")
	private String sdurrf;

	@Column(name="SDUSER")
	private String sduser;

	@Column(name="SDVEND")
	private Double sdvend;

	@Column(name="SDVLUM")
	private String sdvlum;

	@Column(name="SDVR01")
	private String sdvr01;

	@Column(name="SDVR02")
	private String sdvr02;

	@Column(name="SDVR03")
	private String sdvr03;

	@Column(name="SDWTUM")
	private String sdwtum;

	@Column(name="SDXCTO")
	private String sdxcto;

	@Column(name="SDXDCK")
	private String sdxdck;

	@Column(name="SDXKCO")
	private String sdxkco;

	@Column(name="SDXLLN")
	private Double sdxlln;

	@Column(name="SDXORN")
	private Double sdxorn;

	@Column(name="SDXPTY")
	private Double sdxpty;

	@Column(name="SDXSFX")
	private String sdxsfx;

	@Column(name="SDZON")
	private String sdzon;

	public F4211() {
	}

	public F4211PK getId() {
		return this.id;
	}

	public void setId(F4211PK id) {
		this.id = id;
	}

	public Double getSdaaid() {
		return this.sdaaid;
	}

	public void setSdaaid(Double sdaaid) {
		this.sdaaid = sdaaid;
	}

	public String getSdacom() {
		return this.sdacom;
	}

	public void setSdacom(String sdacom) {
		this.sdacom = sdacom;
	}

	public BigDecimal getSdaddj() {
		return this.sdaddj;
	}

	public void setSdaddj(BigDecimal sdaddj) {
		this.sdaddj = sdaddj;
	}

	public Double getSdadtm() {
		return this.sdadtm;
	}

	public void setSdadtm(Double sdadtm) {
		this.sdadtm = sdadtm;
	}

	public Double getSdaexp() {
		return this.sdaexp;
	}

	public void setSdaexp(Double sdaexp) {
		this.sdaexp = sdaexp;
	}

	public String getSdaitm() {
		return this.sdaitm;
	}

	public void setSdaitm(String sdaitm) {
		this.sdaitm = sdaitm;
	}

	public Double getSdan8() {
		return this.sdan8;
	}

	public void setSdan8(Double sdan8) {
		this.sdan8 = sdan8;
	}

	public Double getSdanby() {
		return this.sdanby;
	}

	public void setSdanby(Double sdanby) {
		this.sdanby = sdanby;
	}

	public Double getSdaopn() {
		return this.sdaopn;
	}

	public void setSdaopn(Double sdaopn) {
		this.sdaopn = sdaopn;
	}

	public String getSdapts() {
		return this.sdapts;
	}

	public void setSdapts(String sdapts) {
		this.sdapts = sdapts;
	}

	public String getSdapum() {
		return this.sdapum;
	}

	public void setSdapum(String sdapum) {
		this.sdapum = sdapum;
	}

	public String getSdasn() {
		return this.sdasn;
	}

	public void setSdasn(String sdasn) {
		this.sdasn = sdasn;
	}

	public String getSdatxt() {
		return this.sdatxt;
	}

	public void setSdatxt(String sdatxt) {
		this.sdatxt = sdatxt;
	}

	public String getSdback() {
		return this.sdback;
	}

	public void setSdback(String sdback) {
		this.sdback = sdback;
	}

	public String getSdbcrc() {
		return this.sdbcrc;
	}

	public void setSdbcrc(String sdbcrc) {
		this.sdbcrc = sdbcrc;
	}

	public String getSdbsc() {
		return this.sdbsc;
	}

	public void setSdbsc(String sdbsc) {
		this.sdbsc = sdbsc;
	}

	public Double getSdcadc() {
		return this.sdcadc;
	}

	public void setSdcadc(Double sdcadc) {
		this.sdcadc = sdcadc;
	}

	public Double getSdcars() {
		return this.sdcars;
	}

	public void setSdcars(Double sdcars) {
		this.sdcars = sdcars;
	}

	public String getSdcatnm() {
		return this.sdcatnm;
	}

	public void setSdcatnm(String sdcatnm) {
		this.sdcatnm = sdcatnm;
	}

	public String getSdcbsc() {
		return this.sdcbsc;
	}

	public void setSdcbsc(String sdcbsc) {
		this.sdcbsc = sdcbsc;
	}

	public Double getSdccidln() {
		return this.sdccidln;
	}

	public void setSdccidln(Double sdccidln) {
		this.sdccidln = sdccidln;
	}

	public String getSdclvl() {
		return this.sdclvl;
	}

	public void setSdclvl(String sdclvl) {
		this.sdclvl = sdclvl;
	}

	public String getSdcmcg() {
		return this.sdcmcg;
	}

	public void setSdcmcg(String sdcmcg) {
		this.sdcmcg = sdcmcg;
	}

	public String getSdcmgp() {
		return this.sdcmgp;
	}

	public void setSdcmgp(String sdcmgp) {
		this.sdcmgp = sdcmgp;
	}

	public BigDecimal getSdcndj() {
		return this.sdcndj;
	}

	public void setSdcndj(BigDecimal sdcndj) {
		this.sdcndj = sdcndj;
	}

	public String getSdcnid() {
		return this.sdcnid;
	}

	public void setSdcnid(String sdcnid) {
		this.sdcnid = sdcnid;
	}

	public String getSdco() {
		return this.sdco;
	}

	public void setSdco(String sdco) {
		this.sdco = sdco;
	}

	public String getSdcomm() {
		return this.sdcomm;
	}

	public void setSdcomm(String sdcomm) {
		this.sdcomm = sdcomm;
	}

	public Double getSdcord() {
		return this.sdcord;
	}

	public void setSdcord(Double sdcord) {
		this.sdcord = sdcord;
	}

	public Double getSdcpnt() {
		return this.sdcpnt;
	}

	public void setSdcpnt(Double sdcpnt) {
		this.sdcpnt = sdcpnt;
	}

	public String getSdcrcd() {
		return this.sdcrcd;
	}

	public void setSdcrcd(String sdcrcd) {
		this.sdcrcd = sdcrcd;
	}

	public String getSdcrmd() {
		return this.sdcrmd;
	}

	public void setSdcrmd(String sdcrmd) {
		this.sdcrmd = sdcrmd;
	}

	public Double getSdcrr() {
		return this.sdcrr;
	}

	public void setSdcrr(Double sdcrr) {
		this.sdcrr = sdcrr;
	}

	public String getSdcsto() {
		return this.sdcsto;
	}

	public void setSdcsto(String sdcsto) {
		this.sdcsto = sdcsto;
	}

	public Double getSdctry() {
		return this.sdctry;
	}

	public void setSdctry(Double sdctry) {
		this.sdctry = sdctry;
	}

	public String getSddct() {
		return this.sddct;
	}

	public void setSddct(String sddct) {
		this.sddct = sddct;
	}

	public Double getSddeid() {
		return this.sddeid;
	}

	public void setSddeid(Double sddeid) {
		this.sddeid = sddeid;
	}

	public Double getSddeln() {
		return this.sddeln;
	}

	public void setSddeln(Double sddeln) {
		this.sddeln = sddeln;
	}

	public BigDecimal getSddgl() {
		return this.sddgl;
	}

	public void setSddgl(BigDecimal sddgl) {
		this.sddgl = sddgl;
	}

	public String getSddmbu() {
		return this.sddmbu;
	}

	public void setSddmbu(String sddmbu) {
		this.sddmbu = sddmbu;
	}

	public Double getSddmcs() {
		return this.sddmcs;
	}

	public void setSddmcs(Double sddmcs) {
		this.sddmcs = sddmcs;
	}

	public String getSddmct() {
		return this.sddmct;
	}

	public void setSddmct(String sddmct) {
		this.sddmct = sddmct;
	}

	public Double getSddoc() {
		return this.sddoc;
	}

	public void setSddoc(Double sddoc) {
		this.sddoc = sddoc;
	}

	public BigDecimal getSddrqj() {
		return this.sddrqj;
	}

	public void setSddrqj(BigDecimal sddrqj) {
		this.sddrqj = sddrqj;
	}

	public Double getSddrqt() {
		return this.sddrqt;
	}

	public void setSddrqt(Double sddrqt) {
		this.sddrqt = sddrqt;
	}

	public String getSddsc1() {
		return this.sddsc1;
	}

	public void setSddsc1(String sddsc1) {
		this.sddsc1 = sddsc1;
	}

	public String getSddsc2() {
		return this.sddsc2;
	}

	public void setSddsc2(String sddsc2) {
		this.sddsc2 = sddsc2;
	}

	public String getSddtbs() {
		return this.sddtbs;
	}

	public void setSddtbs(String sddtbs) {
		this.sddtbs = sddtbs;
	}

	public String getSddtys() {
		return this.sddtys;
	}

	public void setSddtys(String sddtys) {
		this.sddtys = sddtys;
	}

	public String getSddual() {
		return this.sddual;
	}

	public void setSddual(String sddual) {
		this.sddual = sddual;
	}

	public Double getSddvan() {
		return this.sddvan;
	}

	public void setSddvan(Double sddvan) {
		this.sddvan = sddvan;
	}

	public Double getSdecst() {
		return this.sdecst;
	}

	public void setSdecst(Double sdecst) {
		this.sdecst = sdecst;
	}

	public String getSdemcu() {
		return this.sdemcu;
	}

	public void setSdemcu(String sdemcu) {
		this.sdemcu = sdemcu;
	}

	public String getSdeuse() {
		return this.sdeuse;
	}

	public void setSdeuse(String sdeuse) {
		this.sdeuse = sdeuse;
	}

	public Double getSdexdp() {
		return this.sdexdp;
	}

	public void setSdexdp(Double sdexdp) {
		this.sdexdp = sdexdp;
	}

	public String getSdexr1() {
		return this.sdexr1;
	}

	public void setSdexr1(String sdexr1) {
		this.sdexr1 = sdexr1;
	}

	public Double getSdfea() {
		return this.sdfea;
	}

	public void setSdfea(Double sdfea) {
		this.sdfea = sdfea;
	}

	public Double getSdfec() {
		return this.sdfec;
	}

	public void setSdfec(Double sdfec) {
		this.sdfec = sdfec;
	}

	public Double getSdfprc() {
		return this.sdfprc;
	}

	public void setSdfprc(Double sdfprc) {
		this.sdfprc = sdfprc;
	}

	public String getSdfrgd() {
		return this.sdfrgd;
	}

	public void setSdfrgd(String sdfrgd) {
		this.sdfrgd = sdfrgd;
	}

	public Double getSdfrmp() {
		return this.sdfrmp;
	}

	public void setSdfrmp(Double sdfrmp) {
		this.sdfrmp = sdfrmp;
	}

	public String getSdfrth() {
		return this.sdfrth;
	}

	public void setSdfrth(String sdfrth) {
		this.sdfrth = sdfrth;
	}

	public Double getSdfuc() {
		return this.sdfuc;
	}

	public void setSdfuc(Double sdfuc) {
		this.sdfuc = sdfuc;
	}

	public Double getSdfun2() {
		return this.sdfun2;
	}

	public void setSdfun2(Double sdfun2) {
		this.sdfun2 = sdfun2;
	}

	public Double getSdfup() {
		return this.sdfup;
	}

	public void setSdfup(Double sdfup) {
		this.sdfup = sdfup;
	}

	public Double getSdfy() {
		return this.sdfy;
	}

	public void setSdfy(Double sdfy) {
		this.sdfy = sdfy;
	}

	public String getSdglc() {
		return this.sdglc;
	}

	public void setSdglc(String sdglc) {
		this.sdglc = sdglc;
	}

	public Double getSdgrwt() {
		return this.sdgrwt;
	}

	public void setSdgrwt(Double sdgrwt) {
		this.sdgrwt = sdgrwt;
	}

	public String getSdgwum() {
		return this.sdgwum;
	}

	public void setSdgwum(String sdgwum) {
		this.sdgwum = sdgwum;
	}

	public String getSdhdbu() {
		return this.sdhdbu;
	}

	public void setSdhdbu(String sdhdbu) {
		this.sdhdbu = sdhdbu;
	}

	public String getSdhold() {
		return this.sdhold;
	}

	public void setSdhold(String sdhold) {
		this.sdhold = sdhold;
	}

	public String getSdinmg() {
		return this.sdinmg;
	}

	public void setSdinmg(String sdinmg) {
		this.sdinmg = sdinmg;
	}

	public String getSdir01() {
		return this.sdir01;
	}

	public void setSdir01(String sdir01) {
		this.sdir01 = sdir01;
	}

	public String getSdir02() {
		return this.sdir02;
	}

	public void setSdir02(String sdir02) {
		this.sdir02 = sdir02;
	}

	public String getSdir03() {
		return this.sdir03;
	}

	public void setSdir03(String sdir03) {
		this.sdir03 = sdir03;
	}

	public String getSdir04() {
		return this.sdir04;
	}

	public void setSdir04(String sdir04) {
		this.sdir04 = sdir04;
	}

	public String getSdir05() {
		return this.sdir05;
	}

	public void setSdir05(String sdir05) {
		this.sdir05 = sdir05;
	}

	public Double getSditm() {
		return this.sditm;
	}

	public void setSditm(Double sditm) {
		this.sditm = sditm;
	}

	public Double getSditvl() {
		return this.sditvl;
	}

	public void setSditvl(Double sditvl) {
		this.sditvl = sditvl;
	}

	public Double getSditwt() {
		return this.sditwt;
	}

	public void setSditwt(Double sditwt) {
		this.sditwt = sditwt;
	}

	public BigDecimal getSdivd() {
		return this.sdivd;
	}

	public void setSdivd(BigDecimal sdivd) {
		this.sdivd = sdivd;
	}

	public String getSdjobn() {
		return this.sdjobn;
	}

	public void setSdjobn(String sdjobn) {
		this.sdjobn = sdjobn;
	}

	public String getSdkco() {
		return this.sdkco;
	}

	public void setSdkco(String sdkco) {
		this.sdkco = sdkco;
	}

	public Double getSdktln() {
		return this.sdktln;
	}

	public void setSdktln(Double sdktln) {
		this.sdktln = sdktln;
	}

	public Double getSdktp() {
		return this.sdktp;
	}

	public void setSdktp(Double sdktp) {
		this.sdktp = sdktp;
	}

	public String getSdlcod() {
		return this.sdlcod;
	}

	public void setSdlcod(String sdlcod) {
		this.sdlcod = sdlcod;
	}

	public String getSdlitm() {
		return this.sdlitm;
	}

	public void setSdlitm(String sdlitm) {
		this.sdlitm = sdlitm;
	}

	public String getSdlnty() {
		return this.sdlnty;
	}

	public void setSdlnty(String sdlnty) {
		this.sdlnty = sdlnty;
	}

	public String getSdlob() {
		return this.sdlob;
	}

	public void setSdlob(String sdlob) {
		this.sdlob = sdlob;
	}

	public String getSdlocn() {
		return this.sdlocn;
	}

	public void setSdlocn(String sdlocn) {
		this.sdlocn = sdlocn;
	}

	public String getSdlotn() {
		return this.sdlotn;
	}

	public void setSdlotn(String sdlotn) {
		this.sdlotn = sdlotn;
	}

	public Double getSdlprc() {
		return this.sdlprc;
	}

	public void setSdlprc(Double sdlprc) {
		this.sdlprc = sdlprc;
	}

	public String getSdlttr() {
		return this.sdlttr;
	}

	public void setSdlttr(String sdlttr) {
		this.sdlttr = sdlttr;
	}

	public Double getSdmcln() {
		return this.sdmcln;
	}

	public void setSdmcln(Double sdmcln) {
		this.sdmcln = sdmcln;
	}

	public String getSdmcu() {
		return this.sdmcu;
	}

	public void setSdmcu(String sdmcu) {
		this.sdmcu = sdmcu;
	}

	public String getSdmerl() {
		return this.sdmerl;
	}

	public void setSdmerl(String sdmerl) {
		this.sdmerl = sdmerl;
	}

	public String getSdmot() {
		return this.sdmot;
	}

	public void setSdmot(String sdmot) {
		this.sdmot = sdmot;
	}

	public String getSdntr() {
		return this.sdntr;
	}

	public void setSdntr(String sdntr) {
		this.sdntr = sdntr;
	}

	public Double getSdnumb() {
		return this.sdnumb;
	}

	public void setSdnumb(Double sdnumb) {
		this.sdnumb = sdnumb;
	}

	public String getSdnxtr() {
		return this.sdnxtr;
	}

	public void setSdnxtr(String sdnxtr) {
		this.sdnxtr = sdnxtr;
	}

	public String getSdocto() {
		return this.sdocto;
	}

	public void setSdocto(String sdocto) {
		this.sdocto = sdocto;
	}

	public String getSdodct() {
		return this.sdodct;
	}

	public void setSdodct(String sdodct) {
		this.sdodct = sdodct;
	}

	public Double getSdodln() {
		return this.sdodln;
	}

	public void setSdodln(Double sdodln) {
		this.sdodln = sdodln;
	}

	public Double getSdodoc() {
		return this.sdodoc;
	}

	public void setSdodoc(Double sdodoc) {
		this.sdodoc = sdodoc;
	}

	public Double getSdogno() {
		return this.sdogno;
	}

	public void setSdogno(Double sdogno) {
		this.sdogno = sdogno;
	}

	public String getSdokc() {
		return this.sdokc;
	}

	public void setSdokc(String sdokc) {
		this.sdokc = sdokc;
	}

	public String getSdokco() {
		return this.sdokco;
	}

	public void setSdokco(String sdokco) {
		this.sdokco = sdokco;
	}

	public String getSdoorn() {
		return this.sdoorn;
	}

	public void setSdoorn(String sdoorn) {
		this.sdoorn = sdoorn;
	}

	public BigDecimal getSdopdj() {
		return this.sdopdj;
	}

	public void setSdopdj(BigDecimal sdopdj) {
		this.sdopdj = sdopdj;
	}

	public Double getSdoppid() {
		return this.sdoppid;
	}

	public void setSdoppid(Double sdoppid) {
		this.sdoppid = sdoppid;
	}

	public Double getSdoptt() {
		return this.sdoptt;
	}

	public void setSdoptt(Double sdoptt) {
		this.sdoptt = sdoptt;
	}

	public String getSdorp() {
		return this.sdorp;
	}

	public void setSdorp(String sdorp) {
		this.sdorp = sdorp;
	}

	public String getSdorpr() {
		return this.sdorpr;
	}

	public void setSdorpr(String sdorpr) {
		this.sdorpr = sdorpr;
	}

	public Double getSdoseq() {
		return this.sdoseq;
	}

	public void setSdoseq(Double sdoseq) {
		this.sdoseq = sdoseq;
	}

	public String getSdostp() {
		return this.sdostp;
	}

	public void setSdostp(String sdostp) {
		this.sdostp = sdostp;
	}

	public String getSdotqy() {
		return this.sdotqy;
	}

	public void setSdotqy(String sdotqy) {
		this.sdotqy = sdotqy;
	}

	public Double getSdpa8() {
		return this.sdpa8;
	}

	public void setSdpa8(Double sdpa8) {
		this.sdpa8 = sdpa8;
	}

	public BigDecimal getSdpddj() {
		return this.sdpddj;
	}

	public void setSdpddj(BigDecimal sdpddj) {
		this.sdpddj = sdpddj;
	}

	public Double getSdpdtt() {
		return this.sdpdtt;
	}

	public void setSdpdtt(Double sdpdtt) {
		this.sdpdtt = sdpdtt;
	}

	public BigDecimal getSdpefj() {
		return this.sdpefj;
	}

	public void setSdpefj(BigDecimal sdpefj) {
		this.sdpefj = sdpefj;
	}

	public String getSdpend() {
		return this.sdpend;
	}

	public void setSdpend(String sdpend) {
		this.sdpend = sdpend;
	}

	public String getSdpid() {
		return this.sdpid;
	}

	public void setSdpid(String sdpid) {
		this.sdpid = sdpid;
	}

	public Double getSdpmdt() {
		return this.sdpmdt;
	}

	public void setSdpmdt(Double sdpmdt) {
		this.sdpmdt = sdpmdt;
	}

	public String getSdpmtn() {
		return this.sdpmtn;
	}

	public void setSdpmtn(String sdpmtn) {
		this.sdpmtn = sdpmtn;
	}

	public String getSdpmto() {
		return this.sdpmto;
	}

	public void setSdpmto(String sdpmto) {
		this.sdpmto = sdpmto;
	}

	public String getSdpoe() {
		return this.sdpoe;
	}

	public void setSdpoe(String sdpoe) {
		this.sdpoe = sdpoe;
	}

	public BigDecimal getSdppdj() {
		return this.sdppdj;
	}

	public void setSdppdj(BigDecimal sdppdj) {
		this.sdppdj = sdppdj;
	}

	public Double getSdpqor() {
		return this.sdpqor;
	}

	public void setSdpqor(Double sdpqor) {
		this.sdpqor = sdpqor;
	}

	public Double getSdpran8() {
		return this.sdpran8;
	}

	public void setSdpran8(Double sdpran8) {
		this.sdpran8 = sdpran8;
	}

	public Double getSdprcidln() {
		return this.sdprcidln;
	}

	public void setSdprcidln(Double sdprcidln) {
		this.sdprcidln = sdprcidln;
	}

	public String getSdprgr() {
		return this.sdprgr;
	}

	public void setSdprgr(String sdprgr) {
		this.sdprgr = sdprgr;
	}

	public String getSdprio() {
		return this.sdprio;
	}

	public void setSdprio(String sdprio) {
		this.sdprio = sdprio;
	}

	public Double getSdprjm() {
		return this.sdprjm;
	}

	public void setSdprjm(Double sdprjm) {
		this.sdprjm = sdprjm;
	}

	public String getSdprov() {
		return this.sdprov;
	}

	public void setSdprov(String sdprov) {
		this.sdprov = sdprov;
	}

	public String getSdprp1() {
		return this.sdprp1;
	}

	public void setSdprp1(String sdprp1) {
		this.sdprp1 = sdprp1;
	}

	public String getSdprp2() {
		return this.sdprp2;
	}

	public void setSdprp2(String sdprp2) {
		this.sdprp2 = sdprp2;
	}

	public String getSdprp3() {
		return this.sdprp3;
	}

	public void setSdprp3(String sdprp3) {
		this.sdprp3 = sdprp3;
	}

	public String getSdprp4() {
		return this.sdprp4;
	}

	public void setSdprp4(String sdprp4) {
		this.sdprp4 = sdprp4;
	}

	public String getSdprp5() {
		return this.sdprp5;
	}

	public void setSdprp5(String sdprp5) {
		this.sdprp5 = sdprp5;
	}

	public String getSdpsig() {
		return this.sdpsig;
	}

	public void setSdpsig(String sdpsig) {
		this.sdpsig = sdpsig;
	}

	public Double getSdpsn() {
		return this.sdpsn;
	}

	public void setSdpsn(Double sdpsn) {
		this.sdpsn = sdpsn;
	}

	public Double getSdpstm() {
		return this.sdpstm;
	}

	public void setSdpstm(Double sdpstm) {
		this.sdpstm = sdpstm;
	}

	public String getSdptc() {
		return this.sdptc;
	}

	public void setSdptc(String sdptc) {
		this.sdptc = sdptc;
	}

	public Double getSdqrlv() {
		return this.sdqrlv;
	}

	public void setSdqrlv(Double sdqrlv) {
		this.sdqrlv = sdqrlv;
	}

	public Double getSdqtyt() {
		return this.sdqtyt;
	}

	public void setSdqtyt(Double sdqtyt) {
		this.sdqtyt = sdqtyt;
	}

	public String getSdrcd() {
		return this.sdrcd;
	}

	public void setSdrcd(String sdrcd) {
		this.sdrcd = sdrcd;
	}

	public String getSdrcto() {
		return this.sdrcto;
	}

	public void setSdrcto(String sdrcto) {
		this.sdrcto = sdrcto;
	}

	public String getSdresl() {
		return this.sdresl;
	}

	public void setSdresl(String sdresl) {
		this.sdresl = sdresl;
	}

	public String getSdrfrv() {
		return this.sdrfrv;
	}

	public void setSdrfrv(String sdrfrv) {
		this.sdrfrv = sdrfrv;
	}

	public String getSdrkco() {
		return this.sdrkco;
	}

	public void setSdrkco(String sdrkco) {
		this.sdrkco = sdrkco;
	}

	public Double getSdrkit() {
		return this.sdrkit;
	}

	public void setSdrkit(Double sdrkit) {
		this.sdrkit = sdrkit;
	}

	public BigDecimal getSdrldj() {
		return this.sdrldj;
	}

	public void setSdrldj(BigDecimal sdrldj) {
		this.sdrldj = sdrldj;
	}

	public String getSdrlit() {
		return this.sdrlit;
	}

	public void setSdrlit(String sdrlit) {
		this.sdrlit = sdrlit;
	}

	public Double getSdrlln() {
		return this.sdrlln;
	}

	public void setSdrlln(Double sdrlln) {
		this.sdrlln = sdrlln;
	}

	public String getSdrlnu() {
		return this.sdrlnu;
	}

	public void setSdrlnu(String sdrlnu) {
		this.sdrlnu = sdrlnu;
	}

	public Double getSdrltm() {
		return this.sdrltm;
	}

	public void setSdrltm(Double sdrltm) {
		this.sdrltm = sdrltm;
	}

	public String getSdrorn() {
		return this.sdrorn;
	}

	public void setSdrorn(String sdrorn) {
		this.sdrorn = sdrorn;
	}

	public String getSdrout() {
		return this.sdrout;
	}

	public void setSdrout(String sdrout) {
		this.sdrout = sdrout;
	}

	public String getSdrprc() {
		return this.sdrprc;
	}

	public void setSdrprc(String sdrprc) {
		this.sdrprc = sdrprc;
	}

	public BigDecimal getSdrsdj() {
		return this.sdrsdj;
	}

	public void setSdrsdj(BigDecimal sdrsdj) {
		this.sdrsdj = sdrsdj;
	}

	public Double getSdrsdt() {
		return this.sdrsdt;
	}

	public void setSdrsdt(Double sdrsdt) {
		this.sdrsdt = sdrsdt;
	}

	public String getSdryin() {
		return this.sdryin;
	}

	public void setSdryin(String sdryin) {
		this.sdryin = sdryin;
	}

	public String getSdsbal() {
		return this.sdsbal;
	}

	public void setSdsbal(String sdsbal) {
		this.sdsbal = sdsbal;
	}

	public String getSdsbl() {
		return this.sdsbl;
	}

	public void setSdsbl(String sdsbl) {
		this.sdsbl = sdsbl;
	}

	public String getSdsblt() {
		return this.sdsblt;
	}

	public void setSdsblt(String sdsblt) {
		this.sdsblt = sdsblt;
	}

	public String getSdsern() {
		return this.sdsern;
	}

	public void setSdsern(String sdsern) {
		this.sdsern = sdsern;
	}

	public String getSdsfxo() {
		return this.sdsfxo;
	}

	public void setSdsfxo(String sdsfxo) {
		this.sdsfxo = sdsfxo;
	}

	public Double getSdshan() {
		return this.sdshan;
	}

	public void setSdshan(Double sdshan) {
		this.sdshan = sdshan;
	}

	public Double getSdshccidln() {
		return this.sdshccidln;
	}

	public void setSdshccidln(Double sdshccidln) {
		this.sdshccidln = sdshccidln;
	}

	public String getSdshcm() {
		return this.sdshcm;
	}

	public void setSdshcm(String sdshcm) {
		this.sdshcm = sdshcm;
	}

	public String getSdshcn() {
		return this.sdshcn;
	}

	public void setSdshcn(String sdshcn) {
		this.sdshcn = sdshcn;
	}

	public Double getSdshpn() {
		return this.sdshpn;
	}

	public void setSdshpn(Double sdshpn) {
		this.sdshpn = sdshpn;
	}

	public String getSdso01() {
		return this.sdso01;
	}

	public void setSdso01(String sdso01) {
		this.sdso01 = sdso01;
	}

	public String getSdso02() {
		return this.sdso02;
	}

	public void setSdso02(String sdso02) {
		this.sdso02 = sdso02;
	}

	public String getSdso03() {
		return this.sdso03;
	}

	public void setSdso03(String sdso03) {
		this.sdso03 = sdso03;
	}

	public String getSdso04() {
		return this.sdso04;
	}

	public void setSdso04(String sdso04) {
		this.sdso04 = sdso04;
	}

	public String getSdso05() {
		return this.sdso05;
	}

	public void setSdso05(String sdso05) {
		this.sdso05 = sdso05;
	}

	public String getSdso06() {
		return this.sdso06;
	}

	public void setSdso06(String sdso06) {
		this.sdso06 = sdso06;
	}

	public String getSdso07() {
		return this.sdso07;
	}

	public void setSdso07(String sdso07) {
		this.sdso07 = sdso07;
	}

	public String getSdso08() {
		return this.sdso08;
	}

	public void setSdso08(String sdso08) {
		this.sdso08 = sdso08;
	}

	public String getSdso09() {
		return this.sdso09;
	}

	public void setSdso09(String sdso09) {
		this.sdso09 = sdso09;
	}

	public String getSdso10() {
		return this.sdso10;
	}

	public void setSdso10(String sdso10) {
		this.sdso10 = sdso10;
	}

	public String getSdso11() {
		return this.sdso11;
	}

	public void setSdso11(String sdso11) {
		this.sdso11 = sdso11;
	}

	public String getSdso12() {
		return this.sdso12;
	}

	public void setSdso12(String sdso12) {
		this.sdso12 = sdso12;
	}

	public String getSdso13() {
		return this.sdso13;
	}

	public void setSdso13(String sdso13) {
		this.sdso13 = sdso13;
	}

	public String getSdso14() {
		return this.sdso14;
	}

	public void setSdso14(String sdso14) {
		this.sdso14 = sdso14;
	}

	public String getSdso15() {
		return this.sdso15;
	}

	public void setSdso15(String sdso15) {
		this.sdso15 = sdso15;
	}

	public String getSdso16() {
		return this.sdso16;
	}

	public void setSdso16(String sdso16) {
		this.sdso16 = sdso16;
	}

	public String getSdso17() {
		return this.sdso17;
	}

	public void setSdso17(String sdso17) {
		this.sdso17 = sdso17;
	}

	public String getSdso18() {
		return this.sdso18;
	}

	public void setSdso18(String sdso18) {
		this.sdso18 = sdso18;
	}

	public String getSdso19() {
		return this.sdso19;
	}

	public void setSdso19(String sdso19) {
		this.sdso19 = sdso19;
	}

	public String getSdso20() {
		return this.sdso20;
	}

	public void setSdso20(String sdso20) {
		this.sdso20 = sdso20;
	}

	public Double getSdsobk() {
		return this.sdsobk;
	}

	public void setSdsobk(Double sdsobk) {
		this.sdsobk = sdsobk;
	}

	public Double getSdsocn() {
		return this.sdsocn;
	}

	public void setSdsocn(Double sdsocn) {
		this.sdsocn = sdsocn;
	}

	public Double getSdsone() {
		return this.sdsone;
	}

	public void setSdsone(Double sdsone) {
		this.sdsone = sdsone;
	}

	public BigDecimal getSdsoor() {
		return this.sdsoor;
	}

	public void setSdsoor(BigDecimal sdsoor) {
		this.sdsoor = sdsoor;
	}

	public Double getSdsoqs() {
		return this.sdsoqs;
	}

	public void setSdsoqs(Double sdsoqs) {
		this.sdsoqs = sdsoqs;
	}

	public String getSdspattn() {
		return this.sdspattn;
	}

	public void setSdspattn(String sdspattn) {
		this.sdspattn = sdspattn;
	}

	public Double getSdsqor() {
		return this.sdsqor;
	}

	public void setSdsqor(Double sdsqor) {
		this.sdsqor = sdsqor;
	}

	public String getSdsrp1() {
		return this.sdsrp1;
	}

	public void setSdsrp1(String sdsrp1) {
		this.sdsrp1 = sdsrp1;
	}

	public String getSdsrp2() {
		return this.sdsrp2;
	}

	public void setSdsrp2(String sdsrp2) {
		this.sdsrp2 = sdsrp2;
	}

	public String getSdsrp3() {
		return this.sdsrp3;
	}

	public void setSdsrp3(String sdsrp3) {
		this.sdsrp3 = sdsrp3;
	}

	public String getSdsrp4() {
		return this.sdsrp4;
	}

	public void setSdsrp4(String sdsrp4) {
		this.sdsrp4 = sdsrp4;
	}

	public String getSdsrp5() {
		return this.sdsrp5;
	}

	public void setSdsrp5(String sdsrp5) {
		this.sdsrp5 = sdsrp5;
	}

	public String getSdstop() {
		return this.sdstop;
	}

	public void setSdstop(String sdstop) {
		this.sdstop = sdstop;
	}

	public String getSdswms() {
		return this.sdswms;
	}

	public void setSdswms(String sdswms) {
		this.sdswms = sdswms;
	}

	public String getSdtax1() {
		return this.sdtax1;
	}

	public void setSdtax1(String sdtax1) {
		this.sdtax1 = sdtax1;
	}

	public Double getSdtcst() {
		return this.sdtcst;
	}

	public void setSdtcst(Double sdtcst) {
		this.sdtcst = sdtcst;
	}

	public Double getSdtday() {
		return this.sdtday;
	}

	public void setSdtday(Double sdtday) {
		this.sdtday = sdtday;
	}

	public String getSdthgd() {
		return this.sdthgd;
	}

	public void setSdthgd(String sdthgd) {
		this.sdthgd = sdthgd;
	}

	public Double getSdthrp() {
		return this.sdthrp;
	}

	public void setSdthrp(Double sdthrp) {
		this.sdthrp = sdthrp;
	}

	public String getSdtorg() {
		return this.sdtorg;
	}

	public void setSdtorg(String sdtorg) {
		this.sdtorg = sdtorg;
	}

	public String getSdtpc() {
		return this.sdtpc;
	}

	public void setSdtpc(String sdtpc) {
		this.sdtpc = sdtpc;
	}

	public Double getSdtrdc() {
		return this.sdtrdc;
	}

	public void setSdtrdc(Double sdtrdc) {
		this.sdtrdc = sdtrdc;
	}

	public BigDecimal getSdtrdj() {
		return this.sdtrdj;
	}

	public void setSdtrdj(BigDecimal sdtrdj) {
		this.sdtrdj = sdtrdj;
	}

	public String getSdtxa1() {
		return this.sdtxa1;
	}

	public void setSdtxa1(String sdtxa1) {
		this.sdtxa1 = sdtxa1;
	}

	public Double getSdukid() {
		return this.sdukid;
	}

	public void setSdukid(Double sdukid) {
		this.sdukid = sdukid;
	}

	public String getSduncd() {
		return this.sduncd;
	}

	public void setSduncd(String sduncd) {
		this.sduncd = sduncd;
	}

	public Double getSduncs() {
		return this.sduncs;
	}

	public void setSduncs(Double sduncs) {
		this.sduncs = sduncs;
	}

	public String getSduom() {
		return this.sduom;
	}

	public void setSduom(String sduom) {
		this.sduom = sduom;
	}

	public String getSduom1() {
		return this.sduom1;
	}

	public void setSduom1(String sduom1) {
		this.sduom1 = sduom1;
	}

	public String getSduom2() {
		return this.sduom2;
	}

	public void setSduom2(String sduom2) {
		this.sduom2 = sduom2;
	}

	public String getSduom4() {
		return this.sduom4;
	}

	public void setSduom4(String sduom4) {
		this.sduom4 = sduom4;
	}

	public Double getSduopn() {
		return this.sduopn;
	}

	public void setSduopn(Double sduopn) {
		this.sduopn = sduopn;
	}

	public Double getSduorg() {
		return this.sduorg;
	}

	public void setSduorg(Double sduorg) {
		this.sduorg = sduorg;
	}

	public String getSdupc1() {
		return this.sdupc1;
	}

	public void setSdupc1(String sdupc1) {
		this.sdupc1 = sdupc1;
	}

	public String getSdupc2() {
		return this.sdupc2;
	}

	public void setSdupc2(String sdupc2) {
		this.sdupc2 = sdupc2;
	}

	public String getSdupc3() {
		return this.sdupc3;
	}

	public void setSdupc3(String sdupc3) {
		this.sdupc3 = sdupc3;
	}

	public BigDecimal getSdupmj() {
		return this.sdupmj;
	}

	public void setSdupmj(BigDecimal sdupmj) {
		this.sdupmj = sdupmj;
	}

	public Double getSduprc() {
		return this.sduprc;
	}

	public void setSduprc(Double sduprc) {
		this.sduprc = sduprc;
	}

	public Double getSdurab() {
		return this.sdurab;
	}

	public void setSdurab(Double sdurab) {
		this.sdurab = sdurab;
	}

	public Double getSdurat() {
		return this.sdurat;
	}

	public void setSdurat(Double sdurat) {
		this.sdurat = sdurat;
	}

	public String getSdurcd() {
		return this.sdurcd;
	}

	public void setSdurcd(String sdurcd) {
		this.sdurcd = sdurcd;
	}

	public BigDecimal getSdurdt() {
		return this.sdurdt;
	}

	public void setSdurdt(BigDecimal sdurdt) {
		this.sdurdt = sdurdt;
	}

	public String getSdurrf() {
		return this.sdurrf;
	}

	public void setSdurrf(String sdurrf) {
		this.sdurrf = sdurrf;
	}

	public String getSduser() {
		return this.sduser;
	}

	public void setSduser(String sduser) {
		this.sduser = sduser;
	}

	public Double getSdvend() {
		return this.sdvend;
	}

	public void setSdvend(Double sdvend) {
		this.sdvend = sdvend;
	}

	public String getSdvlum() {
		return this.sdvlum;
	}

	public void setSdvlum(String sdvlum) {
		this.sdvlum = sdvlum;
	}

	public String getSdvr01() {
		return this.sdvr01;
	}

	public void setSdvr01(String sdvr01) {
		this.sdvr01 = sdvr01;
	}

	public String getSdvr02() {
		return this.sdvr02;
	}

	public void setSdvr02(String sdvr02) {
		this.sdvr02 = sdvr02;
	}

	public String getSdvr03() {
		return this.sdvr03;
	}

	public void setSdvr03(String sdvr03) {
		this.sdvr03 = sdvr03;
	}

	public String getSdwtum() {
		return this.sdwtum;
	}

	public void setSdwtum(String sdwtum) {
		this.sdwtum = sdwtum;
	}

	public String getSdxcto() {
		return this.sdxcto;
	}

	public void setSdxcto(String sdxcto) {
		this.sdxcto = sdxcto;
	}

	public String getSdxdck() {
		return this.sdxdck;
	}

	public void setSdxdck(String sdxdck) {
		this.sdxdck = sdxdck;
	}

	public String getSdxkco() {
		return this.sdxkco;
	}

	public void setSdxkco(String sdxkco) {
		this.sdxkco = sdxkco;
	}

	public Double getSdxlln() {
		return this.sdxlln;
	}

	public void setSdxlln(Double sdxlln) {
		this.sdxlln = sdxlln;
	}

	public Double getSdxorn() {
		return this.sdxorn;
	}

	public void setSdxorn(Double sdxorn) {
		this.sdxorn = sdxorn;
	}

	public Double getSdxpty() {
		return this.sdxpty;
	}

	public void setSdxpty(Double sdxpty) {
		this.sdxpty = sdxpty;
	}

	public String getSdxsfx() {
		return this.sdxsfx;
	}

	public void setSdxsfx(String sdxsfx) {
		this.sdxsfx = sdxsfx;
	}

	public String getSdzon() {
		return this.sdzon;
	}

	public void setSdzon(String sdzon) {
		this.sdzon = sdzon;
	}

}