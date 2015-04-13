package modelo.social;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import modelo.maestros.Paciente;

@Entity
@Table(name = "visita_social", schema = "dusa_sims.dbo")
public class VisitaSocial implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_visita", unique = true, nullable = false)
	private long idVisita;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
	private Paciente paciente;
	
	@Column(name = "numero_visita",length = 100)
	private String numero;
	
	@Column(name = "area_visita",length = 100)
	private String area;
	
	@Column(length = 250)
	private String a;
	
	@Column(length = 250)
	private String b;
	
	@Column(length = 250)
	private String c;
	
	@Column(length = 1500)
	private String d;
	
	@Column(length = 250)
	private String e;
	
	@Column(length = 250)
	private String f;
	
	@Column(length = 250)
	private String g;
	
	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean h;
	
	@Column(length = 250)
	private String i;
	
	@Column(length = 250)
	private String aj;
	
	@Column(name = "aa")
	private Integer aa;
	
	@Column(length = 250)
	private String ab;
	
	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean ac;
	
	@Column(name = "ad")
	private Integer ad;
	
	@Column(name = "af")
	private Integer af;
	
	@Column(length = 250)
	private String ag;
	
	@Column(name = "ah")
	private Integer ah;
	
	@Column(length = 1500)
	private String ai;
	
	@Column(name = "bj")
	private Double bj;
	
	@Column(length = 250)
	private String ba;
	
	@Column(length = 250)
	private String bb;
	
	@Column(length = 1500)
	private String bc;
	
	@Column(name = "bd")
	private Integer bd;
	
	@Column(name = "be")
	private Integer be;
	
	@Column(name = "cj_alimentacion")
	private Double cjAlimentacion;
	
	@Column(name = "cj_gasolina")
	private Double cjGasolina;
	
	@Column(name = "cj_alquiler")
	private Double cjAlquiler;
	
	@Column(name = "cj_agua")
	private Double cjAgua;
	
	@Column(name = "cj_electricidad")
	private Double cjElectricidad;
	
	@Column(name = "cj_residencial")
	private Double cjResidencia;
	
	@Column(name = "cj_celular")
	private Double cjCelular;
	
	@Column(name = "cj_transporte")
	private Double cjTransporte;
	
	@Column(name = "cj_educacion")
	private Double cjEducacion;
	
	@Column(name = "cj_medico")
	private Double cjMedico;
	
	@Column(name = "cj_recreacion")
	private Double cjRecreacion;
	
	@Column(name = "cj_credito")
	private Double cjCredito;
	
	@Column(name = "cj_ropa")
	private Double cjRopa;
	
	@Column(name = "cj_fondo")
	private Double cjFondo;
	
	@Column(name = "cj_habitacional")
	private Double cjHabitacional;
	
	@Column(length = 1500)
	private String dfa;
	
	@Column(length = 1500)
	private String dfb;
	
	@Column(length = 1500)
	private String dfc;
	
	@Column(length = 1500)
	private String dfd;
	
	@Column(length = 1500)
	private String dfe;
	
	@Column(length = 1500)
	private String dff;
	
	@Column(length = 1500)
	private String dfg;
	
	@Column(length = 1500)
	private String dga;
	
	@Column(length = 1500)
	private String dgb;
	
	@Column(length = 1500)
	private String dgc;
	
	@Column(length = 1500)
	private String dgd;
	
	@Column(length = 1500)
	private String dge;
	
	@Column(length = 1500)
	private String dgf;
	
	@Column(length = 1500)
	private String dgg;
	
	@Column(length = 1500)
	private String dfh;
	
	@Column(length = 1500, name="diagnostico_social")
	private String diagnosticoSocial;
		
	@Column(length = 1500)
	private String observacion;
	
	@Column(name="fecha_aplicacion")
	private Timestamp fechaAplicacion;
	
	@Column(name="fecha_procesamiento")
	private Timestamp fechaProcesamiento;
	
	@Column(name="fecha_informe")
	private Timestamp fechaInforme;
	
	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name="hora_auditoria", length=10)
	private String horaAuditoria;

	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;

	@Column(length = 100, name="res_frecuencia")
	private String resFrecuencia;
	
	@Column(length = 1000, name="res_donde")
	private String resDonde;
	
	@Column(length = 1000, name="res_como")
	private String resComo;
	
	@Column(length = 100, name="pollo_frecuencia")
	private String polloFrecuencia;
	
	@Column(length = 1000, name="pollo_donde")
	private String polloDonde;
	
	@Column(length = 1000, name="pollo_como")
	private String polloComo;
	
	@Column(length = 100, name="cerdo_frecuencia")
	private String cerdoFrecuencia;
	
	@Column(length = 1000, name="cerdo_donde")
	private String cerdoDonde;
	
	@Column(length = 1000, name="cerdo_como")
	private String cerdoComo;
	
	
	@Column(length = 100, name="pescado_frecuencia")
	private String pescadoFrecuencia;
	
	@Column(length = 1000, name="pescado_donde")
	private String pescadoDonde;
	
	@Column(length = 1000, name="pescado_como")
	private String pescadoComo;
	
	@Column(length = 100, name="leche_frecuencia")
	private String lecheFrecuencia;
	
	@Column(length = 1000, name="leche_donde")
	private String lecheDonde;
	
	@Column(length = 1000, name="leche_como")
	private String lecheComo;
	
	@Column(length = 100, name="cereales_frecuencia")
	private String cerealesFrecuencia;
	
	@Column(length = 1000, name="cereales_donde")
	private String cerealesDonde;
	
	@Column(length = 1000, name="cereales_como")
	private String cerealesComo;
	
	@Column(length = 100, name="huevos_frecuencia")
	private String huevosFrecuencia;
	
	@Column(length = 1000, name="huevos_donde")
	private String huevosDonde;
	
	@Column(length = 1000, name="huevos_como")
	private String huevosComo;
	
	@Column(length = 100, name="frutas_frecuencia")
	private String frutasFrecuencia;
	
	@Column(length = 1000, name="frutas_donde")
	private String frutasDonde;
	
	@Column(length = 1000, name="frutas_como")
	private String frutasComo;
	
	@Column(length = 100, name="granos_frecuencia")
	private String granosFrecuencia;
	
	@Column(length = 1000, name="granos_donde")
	private String granosDonde;
	
	@Column(length = 1000, name="granos_como")
	private String granosComo;
	
	@Column(length = 100, name="verduras_frecuencia")
	private String verdurasFrecuencia;
	
	@Column(length = 1000, name="verduras_donde")
	private String verdurasDonde;
	
	@Column(length = 1000, name="verduras_como")
	private String verdurasComo;

	
	public VisitaSocial() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getIdVisita() {
		return idVisita;
	}

	public void setIdVisita(long idVisita) {
		this.idVisita = idVisita;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public String getE() {
		return e;
	}

	public void setE(String e) {
		this.e = e;
	}

	public String getF() {
		return f;
	}

	public void setF(String f) {
		this.f = f;
	}

	public String getG() {
		return g;
	}

	public void setG(String g) {
		this.g = g;
	}

	public Boolean getH() {
		return h;
	}

	public void setH(Boolean h) {
		this.h = h;
	}

	public String getI() {
		return i;
	}

	public void setI(String i) {
		this.i = i;
	}

	public String getAj() {
		return aj;
	}

	public void setAj(String aj) {
		this.aj = aj;
	}

	public Integer getAa() {
		return aa;
	}

	public void setAa(Integer aa) {
		this.aa = aa;
	}

	public String getAb() {
		return ab;
	}

	public void setAb(String ab) {
		this.ab = ab;
	}

	public Boolean getAc() {
		return ac;
	}

	public void setAc(Boolean ac) {
		this.ac = ac;
	}

	public Integer getAd() {
		return ad;
	}

	public void setAd(Integer ad) {
		this.ad = ad;
	}

	public Integer getAf() {
		return af;
	}

	public void setAf(Integer af) {
		this.af = af;
	}

	public String getAg() {
		return ag;
	}

	public void setAg(String ag) {
		this.ag = ag;
	}

	public Integer getAh() {
		return ah;
	}

	public void setAh(Integer ah) {
		this.ah = ah;
	}

	public String getAi() {
		return ai;
	}

	public void setAi(String ai) {
		this.ai = ai;
	}

	public Double getBj() {
		return bj;
	}

	public void setBj(Double bj) {
		this.bj = bj;
	}

	public String getBa() {
		return ba;
	}

	public void setBa(String ba) {
		this.ba = ba;
	}

	public String getBb() {
		return bb;
	}

	public void setBb(String bb) {
		this.bb = bb;
	}

	public String getBc() {
		return bc;
	}

	public void setBc(String bc) {
		this.bc = bc;
	}

	public Integer getBd() {
		return bd;
	}

	public void setBd(Integer bd) {
		this.bd = bd;
	}

	public Integer getBe() {
		return be;
	}

	public void setBe(Integer be) {
		this.be = be;
	}

	public Double getCjAlimentacion() {
		return cjAlimentacion;
	}

	public void setCjAlimentacion(Double cjAlimentacion) {
		this.cjAlimentacion = cjAlimentacion;
	}

	public Double getCjGasolina() {
		return cjGasolina;
	}

	public void setCjGasolina(Double cjGasolina) {
		this.cjGasolina = cjGasolina;
	}

	public Double getCjAlquiler() {
		return cjAlquiler;
	}

	public void setCjAlquiler(Double cjAlquiler) {
		this.cjAlquiler = cjAlquiler;
	}

	public Double getCjAgua() {
		return cjAgua;
	}

	public void setCjAgua(Double cjAgua) {
		this.cjAgua = cjAgua;
	}

	public Double getCjElectricidad() {
		return cjElectricidad;
	}

	public void setCjElectricidad(Double cjElectricidad) {
		this.cjElectricidad = cjElectricidad;
	}

	public Double getCjResidencia() {
		return cjResidencia;
	}

	public void setCjResidencia(Double cjResidencia) {
		this.cjResidencia = cjResidencia;
	}

	public Double getCjCelular() {
		return cjCelular;
	}

	public void setCjCelular(Double cjCelular) {
		this.cjCelular = cjCelular;
	}

	public Double getCjTransporte() {
		return cjTransporte;
	}

	public void setCjTransporte(Double cjTransporte) {
		this.cjTransporte = cjTransporte;
	}

	public Double getCjEducacion() {
		return cjEducacion;
	}

	public void setCjEducacion(Double cjEducacion) {
		this.cjEducacion = cjEducacion;
	}

	public Double getCjMedico() {
		return cjMedico;
	}

	public void setCjMedico(Double cjMedico) {
		this.cjMedico = cjMedico;
	}

	public Double getCjRecreacion() {
		return cjRecreacion;
	}

	public void setCjRecreacion(Double cjRecreacion) {
		this.cjRecreacion = cjRecreacion;
	}

	public Double getCjCredito() {
		return cjCredito;
	}

	public void setCjCredito(Double cjCredito) {
		this.cjCredito = cjCredito;
	}

	public Double getCjRopa() {
		return cjRopa;
	}

	public void setCjRopa(Double cjRopa) {
		this.cjRopa = cjRopa;
	}

	public Double getCjFondo() {
		return cjFondo;
	}

	public void setCjFondo(Double cjFondo) {
		this.cjFondo = cjFondo;
	}

	public Double getCjHabitacional() {
		return cjHabitacional;
	}

	public void setCjHabitacional(Double cjHabitacional) {
		this.cjHabitacional = cjHabitacional;
	}

	public String getDfa() {
		return dfa;
	}

	public void setDfa(String dfa) {
		this.dfa = dfa;
	}

	public String getDfb() {
		return dfb;
	}

	public void setDfb(String dfb) {
		this.dfb = dfb;
	}

	public String getDfc() {
		return dfc;
	}

	public void setDfc(String dfc) {
		this.dfc = dfc;
	}

	public String getDfd() {
		return dfd;
	}

	public void setDfd(String dfd) {
		this.dfd = dfd;
	}

	public String getDfe() {
		return dfe;
	}

	public void setDfe(String dfe) {
		this.dfe = dfe;
	}

	public String getDff() {
		return dff;
	}

	public void setDff(String dff) {
		this.dff = dff;
	}

	public String getDfg() {
		return dfg;
	}

	public void setDfg(String dfg) {
		this.dfg = dfg;
	}

	public String getDga() {
		return dga;
	}

	public void setDga(String dga) {
		this.dga = dga;
	}

	public String getDgb() {
		return dgb;
	}

	public void setDgb(String dgb) {
		this.dgb = dgb;
	}

	public String getDgc() {
		return dgc;
	}

	public void setDgc(String dgc) {
		this.dgc = dgc;
	}

	public String getDgd() {
		return dgd;
	}

	public void setDgd(String dgd) {
		this.dgd = dgd;
	}

	public String getDge() {
		return dge;
	}

	public void setDge(String dge) {
		this.dge = dge;
	}

	public String getDgf() {
		return dgf;
	}

	public void setDgf(String dgf) {
		this.dgf = dgf;
	}

	public String getDgg() {
		return dgg;
	}

	public void setDgg(String dgg) {
		this.dgg = dgg;
	}

	public String getDfh() {
		return dfh;
	}

	public void setDfh(String dfh) {
		this.dfh = dfh;
	}

	public String getDiagnosticoSocial() {
		return diagnosticoSocial;
	}

	public void setDiagnosticoSocial(String diagnosticoSocial) {
		this.diagnosticoSocial = diagnosticoSocial;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Timestamp getFechaAplicacion() {
		return fechaAplicacion;
	}

	public void setFechaAplicacion(Timestamp fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}

	public Timestamp getFechaProcesamiento() {
		return fechaProcesamiento;
	}

	public void setFechaProcesamiento(Timestamp fechaProcesamiento) {
		this.fechaProcesamiento = fechaProcesamiento;
	}

	public Timestamp getFechaInforme() {
		return fechaInforme;
	}

	public void setFechaInforme(Timestamp fechaInforme) {
		this.fechaInforme = fechaInforme;
	}

	public Timestamp getFechaAuditoria() {
		return fechaAuditoria;
	}

	public void setFechaAuditoria(Timestamp fechaAuditoria) {
		this.fechaAuditoria = fechaAuditoria;
	}

	public String getHoraAuditoria() {
		return horaAuditoria;
	}

	public void setHoraAuditoria(String horaAuditoria) {
		this.horaAuditoria = horaAuditoria;
	}

	public String getUsuarioAuditoria() {
		return usuarioAuditoria;
	}

	public void setUsuarioAuditoria(String usuarioAuditoria) {
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public String getResFrecuencia() {
		return resFrecuencia;
	}

	public void setResFrecuencia(String resFrecuencia) {
		this.resFrecuencia = resFrecuencia;
	}

	public String getResDonde() {
		return resDonde;
	}

	public void setResDonde(String resDonde) {
		this.resDonde = resDonde;
	}

	public String getResComo() {
		return resComo;
	}

	public void setResComo(String resComo) {
		this.resComo = resComo;
	}

	public String getPolloFrecuencia() {
		return polloFrecuencia;
	}

	public void setPolloFrecuencia(String polloFrecuencia) {
		this.polloFrecuencia = polloFrecuencia;
	}

	public String getPolloDonde() {
		return polloDonde;
	}

	public void setPolloDonde(String polloDonde) {
		this.polloDonde = polloDonde;
	}

	public String getPolloComo() {
		return polloComo;
	}

	public void setPolloComo(String polloComo) {
		this.polloComo = polloComo;
	}

	public String getCerdoFrecuencia() {
		return cerdoFrecuencia;
	}

	public void setCerdoFrecuencia(String cerdoFrecuencia) {
		this.cerdoFrecuencia = cerdoFrecuencia;
	}

	public String getCerdoDonde() {
		return cerdoDonde;
	}

	public void setCerdoDonde(String cerdoDonde) {
		this.cerdoDonde = cerdoDonde;
	}

	public String getCerdoComo() {
		return cerdoComo;
	}

	public void setCerdoComo(String cerdoComo) {
		this.cerdoComo = cerdoComo;
	}

	public String getPescadoFrecuencia() {
		return pescadoFrecuencia;
	}

	public void setPescadoFrecuencia(String pescadoFrecuencia) {
		this.pescadoFrecuencia = pescadoFrecuencia;
	}

	public String getPescadoDonde() {
		return pescadoDonde;
	}

	public void setPescadoDonde(String pescadoDonde) {
		this.pescadoDonde = pescadoDonde;
	}

	public String getPescadoComo() {
		return pescadoComo;
	}

	public void setPescadoComo(String pescadoComo) {
		this.pescadoComo = pescadoComo;
	}

	public String getLecheFrecuencia() {
		return lecheFrecuencia;
	}

	public void setLecheFrecuencia(String lecheFrecuencia) {
		this.lecheFrecuencia = lecheFrecuencia;
	}

	public String getLecheDonde() {
		return lecheDonde;
	}

	public void setLecheDonde(String lecheDonde) {
		this.lecheDonde = lecheDonde;
	}

	public String getLecheComo() {
		return lecheComo;
	}

	public void setLecheComo(String lecheComo) {
		this.lecheComo = lecheComo;
	}

	public String getCerealesFrecuencia() {
		return cerealesFrecuencia;
	}

	public void setCerealesFrecuencia(String cerealesFrecuencia) {
		this.cerealesFrecuencia = cerealesFrecuencia;
	}

	public String getCerealesDonde() {
		return cerealesDonde;
	}

	public void setCerealesDonde(String cerealesDonde) {
		this.cerealesDonde = cerealesDonde;
	}

	public String getCerealesComo() {
		return cerealesComo;
	}

	public void setCerealesComo(String cerealesComo) {
		this.cerealesComo = cerealesComo;
	}

	public String getHuevosFrecuencia() {
		return huevosFrecuencia;
	}

	public void setHuevosFrecuencia(String huevosFrecuencia) {
		this.huevosFrecuencia = huevosFrecuencia;
	}

	public String getHuevosDonde() {
		return huevosDonde;
	}

	public void setHuevosDonde(String huevosDonde) {
		this.huevosDonde = huevosDonde;
	}

	public String getHuevosComo() {
		return huevosComo;
	}

	public void setHuevosComo(String huevosComo) {
		this.huevosComo = huevosComo;
	}

	public String getFrutasFrecuencia() {
		return frutasFrecuencia;
	}

	public void setFrutasFrecuencia(String frutasFrecuencia) {
		this.frutasFrecuencia = frutasFrecuencia;
	}

	public String getFrutasDonde() {
		return frutasDonde;
	}

	public void setFrutasDonde(String frutasDonde) {
		this.frutasDonde = frutasDonde;
	}

	public String getFrutasComo() {
		return frutasComo;
	}

	public void setFrutasComo(String frutasComo) {
		this.frutasComo = frutasComo;
	}

	public String getGranosFrecuencia() {
		return granosFrecuencia;
	}

	public void setGranosFrecuencia(String granosFrecuencia) {
		this.granosFrecuencia = granosFrecuencia;
	}

	public String getGranosDonde() {
		return granosDonde;
	}

	public void setGranosDonde(String granosDonde) {
		this.granosDonde = granosDonde;
	}

	public String getGranosComo() {
		return granosComo;
	}

	public void setGranosComo(String granosComo) {
		this.granosComo = granosComo;
	}

	public String getVerdurasFrecuencia() {
		return verdurasFrecuencia;
	}

	public void setVerdurasFrecuencia(String verdurasFrecuencia) {
		this.verdurasFrecuencia = verdurasFrecuencia;
	}

	public String getVerdurasDonde() {
		return verdurasDonde;
	}

	public void setVerdurasDonde(String verdurasDonde) {
		this.verdurasDonde = verdurasDonde;
	}

	public String getVerdurasComo() {
		return verdurasComo;
	}

	public void setVerdurasComo(String verdurasComo) {
		this.verdurasComo = verdurasComo;
	}
	
}
