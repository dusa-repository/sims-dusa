package modelo.transacciones;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import modelo.maestros.Paciente;

@Entity
@Table(name = "historia")
public class Historia implements Serializable {

	private static final long serialVersionUID = 3136973158445875804L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_historia", unique = true, nullable = false)
	private long idHistoria;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
	private Paciente paciente;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean varioPeso;

	@Column(name = "cantidad_peso", length = 50)
	private String pesoCambiado;

	@Column(name = "razon_peso", length = 100)
	private String pesoCausa;

	@Column(name = "toma_cafe")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean cafe;

	@Column(name = "tazas_cafe")
	private Integer cantidadCafe;

	@Column(name = "dificultad_dormir")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean dificultadDormir;

	@Column(name = "dolor_cafe")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean dolorCafe;

	@Column(name = "actividad_fisica")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean actividadFisica;

	@Column(name = "actividad_tipo", length = 100)
	private String actividadTipo;

	@Column(name = "actividad_frecuencia", length = 100)
	private String actividadFrecuencia;

	@Column(name = "actividad_tiempo", length = 100)
	private String actividadTiempo;

	@Column(name = "actividad_extra")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean actividadExtra;

	@Column(name = "tipo_extra", length = 50)
	private String tipoExtra;

	@Column(name = "cigarro_consume")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean cigarroConsume;

	@Column(name = "cigarro_actual")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean cigarroActual;

	@Column(name = "cigarro_cantidad")
	private Integer cigarroCantidad;

	@Column(name = "cigarro_inicio")
	private Timestamp cigarroInicio;

	@Column(name = "cigarro_fin")
	private Timestamp cigarroFin;

	@Column(name = "cigarro_razon", length = 50)
	private String cigarroRazon;

	@Column(name = "alcohol_consume")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean alcoholConsume;

	@Column(name = "alcohol_actual")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean alcoholActual;

	@Column(name = "alcohol_frecuencia", length = 50)
	private String alcoholFrecuencia;

	@Column(name = "alcohol_tipo", length = 50)
	private String alcoholTipo;

	@Column(name = "alcohol_cantidad")
	private Integer alcoholCantidad;

	@Column(name = "alcohol_embriagado")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean alcoholEmbriagado;

	@Column(name = "alcohol_tratamiento")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean alcoholTratamiento;

	@Column(name = "alcohol_rehabilitacion")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean alcoholRehabilitacion;

	@Column(name = "alcohol_accidente")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean alcoholAccidente;

	@Column(name = "droga_consume")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean drogaConsume;

	@Column(name = "droga_explicacion", length = 250)
	private String drogaExplicacion;

	@Column(name = "droga_tratamiento")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean drogaTratamiento;

	@Column(name = "droga_rehabilitacion")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean drogaRehabilitacion;

	@Column(name = "medicamento_consume")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean medicamentoConsume;

	@Column(name = "medicamento_tipo", length = 100)
	private String medicamentoTipo;

	@Column(name = "medicamento_inicio")
	private Timestamp medicamentoInicio;

	@Column(name = "medicamento_cantidad", length = 100)
	private String medicamentoCantidad;

	@Column(name = "enfermedad_posee")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean enfermedadPosee;

	@Column(name = "enfermedad", length = 100)
	private String enfermedad;

	@Column(name = "medico")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean medico;

	@Column(name = "tratamiento")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean tratamiento;

	@Column(name = "transfusion")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean transfusion;

	@Column(name = "ets")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean ets;

	@Column(name = "vih")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean vih;

	@Column(name = "vih_resultado", length = 100)
	private String vihResultado;

	@Column(name = "flujo")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean flujo;

	@Column(name = "secrecion")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean secrecion;

	@Column(name = "dolor")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean dolor;

	@Column(name = "endurecimiento")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean endurecimiento;

	@Column(name = "infeccion")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean infeccion;

	@Column(name = "anticonceptivo")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean anticonceptivo;

	@Column(name = "dolor_relacion")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean dolorRelacion;

	@Column(name = "esterilizacion")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean esterilizacion;

	@Column(name = "aparato")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean aparato;

	@Column(name = "edad_desarrollo")
	private Integer edadDesarrollo;

	@Column(name = "fecha_mestruacion")
	private Timestamp ultimaMenstruacion;

	@Column(name = "numero_embarazo")
	private Integer numeroEmbarazos;

	@Column(name = "numero_parto")
	private Integer numeroPartos;

	@Column(name = "numero_cesarea")
	private Integer numeroCesareas;

	@Column(name = "numero_aborto")
	private Integer numeroAbortos;

	@Column(name = "fecha_citologia")
	private Timestamp ultimaCitologia;

	@Column(name = "poliquistico")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean poliquistico;

	@Column(name = "embarazo")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean embarazo;

	@Column(name = "embarazo_semana")
	private Integer embarazoSemanas;

	@Column(name = "eco")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean eco;

	@Column(name = "eco_resultado", length = 100)
	private String ecoResultado;

	@Column(name = "mamografia")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean mamografia;

	@Column(name = "mamografia_resultado", length = 100)
	private String mamografiaResultado;

	@Column(name = "hora_auditoria", length = 10)
	private String horaAuditoria;

	@Column(name = "fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name = "usuario_auditoria", length = 50)
	private String usuarioAuditoria;

	@Column(name = "dientea", length = 10)
	private String dientea;

	@Column(name = "dienteb", length = 10)
	private String dienteb;

	@Column(name = "dientec", length = 10)
	private String dientec;

	@Column(name = "diented", length = 10)
	private String diented;

	@Column(name = "dientee", length = 10)
	private String dientee;

	@Column(name = "dientef", length = 10)
	private String dientef;

	@Column(name = "dienteg", length = 10)
	private String dienteg;

	@Column(name = "dienteh", length = 10)
	private String dienteh;

	@Column(name = "dientei", length = 10)
	private String dientei;

	@Column(name = "dientej", length = 10)
	private String dientej;

	@Column(name = "dientek", length = 10)
	private String dientek;

	@Column(name = "dientel", length = 10)
	private String dientel;

	@Column(name = "dientem", length = 10)
	private String dientem;

	@Column(name = "dienten", length = 10)
	private String dienten;

	@Column(name = "dienteo", length = 10)
	private String dienteo;

	@Column(name = "dientep", length = 10)
	private String dientep;

	@Column(name = "dienteq", length = 10)
	private String dienteq;

	@Column(name = "dienter", length = 10)
	private String dienter;

	@Column(name = "dientes", length = 10)
	private String dientes;

	@Column(name = "dientet", length = 10)
	private String dientet;

	@Column(name = "dienteu", length = 10)
	private String dienteu;

	@Column(name = "dientev", length = 10)
	private String dientev;

	@Column(name = "dientew", length = 10)
	private String dientew;

	@Column(name = "dientex", length = 10)
	private String dientex;

	@Column(name = "dientey", length = 10)
	private String dientey;

	@Column(name = "dientez", length = 10)
	private String dientez;

	@Column(name = "dientezf", length = 10)
	private String dientezf;

	@Column(name = "dienteza", length = 10)
	private String dienteza;

	@Column(name = "dientezb", length = 10)
	private String dientezb;

	@Column(name = "dientezc", length = 10)
	private String dientezc;

	@Column(name = "dientezd", length = 10)
	private String dientezd;

	@Column(name = "dienteze", length = 10)
	private String dienteze;

	@Column(name = "carta", length = 10)
	private String carta;

	@Column(name = "vision_color")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean visionColor;

	@Column(name = "telefono_odontologo", length = 50)
	private String telefonoOdontologo;

	@Column(name = "altura_hombro")
	private Double alturaHombro;

	@Column(name = "anchura_hombro")
	private Double anchuraHombro;

	@Column(name = "altura_codo")
	private Double alturaCodo;

	@Column(name = "miembro_izquierdo")
	private Double miembroIzquierdo;

	@Column(name = "miembro_derecho")
	private Double miembroDerecho;

	@Column(name = "altura_poplitea")
	private Double alturaPoplitea;

	@Column(name = "altura_ojo")
	private Double alturaOjo;

	@Column(name = "altura_codo_silla")
	private Double alturaCodoSilla;

	@Column(name = "circunferencia_abdominal")
	private Double circunferenciaAbdominal;

	@Column(name = "circunferencia_cadera")
	private Double circunferenciaCadera;

	@Column(name = "distancia_mano")
	private Double manoPiso;

	@Column(name = "indice_cadera")
	private Double indiceCadera;

	@OneToMany(mappedBy = "historia")
	private Set<HistoriaVacuna> historiasVacunas;

	@OneToMany(mappedBy = "historia")
	private Set<HistoriaIntervencion> historiasIntervenciones;

	@OneToMany(mappedBy = "historia")
	private Set<HistoriaAccidente> historiasAccidentes;

	public Historia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Historia(long idHistoria, Paciente paciente, Boolean varioPeso,
			String pesoCambiado, String pesoCausa, Boolean cafe,
			Integer cantidadCafe, Boolean dificultadDormir, Boolean dolorCafe,
			Boolean actividadFisica, String actividadTipo,
			String actividadFrecuencia, String actividadTiempo,
			Boolean actividadExtra, String tipoExtra, Boolean cigarroConsume,
			Boolean cigarroActual, Integer cigarroCantidad,
			Timestamp cigarroInicio, Timestamp cigarroFin, String cigarroRazon,
			Boolean alcoholConsume, Boolean alcoholActual,
			String alcoholFrecuencia, String alcoholTipo,
			Integer alcoholCantidad, Boolean alcoholEmbriagado,
			Boolean alcoholTratamiento, Boolean alcoholRehabilitacion,
			Boolean alcoholAccidente, Boolean drogaConsume,
			String drogaExplicacion, Boolean drogaTratamiento,
			Boolean drogaRehabilitacion, Boolean medicamentoConsume,
			String medicamentoTipo, Timestamp medicamentoInicio,
			String medicamentoCantidad, Boolean enfermedadPosee,
			String enfermedad, Boolean medico, Boolean tratamiento,
			Boolean transfusion, Boolean ets, Boolean vih, String vihResultado,
			Boolean flujo, Boolean secrecion, Boolean dolor,
			Boolean endurecimiento, Boolean infeccion, Boolean anticonceptivo,
			Boolean dolorRelacion, Boolean esterilizacion, Boolean aparato,
			Integer edadDesarrollo, Timestamp ultimaMenstruacion,
			Integer numeroEmbarazos, Integer numeroPartos,
			Integer numeroCesareas, Integer numeroAbortos,
			Timestamp ultimaCitologia, Boolean poliquistico, Boolean embarazo,
			Integer embarazoSemanas, Boolean eco, String ecoResultado,
			Boolean mamografia, String mamografiaResultado,
			String horaAuditoria, Timestamp fechaAuditoria,
			String usuarioAuditoria, String dientea, String dienteb,
			String dientec, String diented, String dientee, String dientef,
			String dienteg, String dienteh, String dientei, String dientej,
			String dientek, String dientel, String dientem, String dienten,
			String dienteo, String dientep, String dienteq, String dienter,
			String dientes, String dientet, String dienteu, String dientev,
			String dientew, String dientex, String dientey, String dientez,
			String dienteza, String dientezb, String dientezc, String dientezd,
			String dienteze, String dientezf, String carta,
			Boolean visionColores, String telefonodontologo,
			Double alturaHombro, Double anchuraHombro, Double alturaCodo,
			Double izquierdo, Double derecho, Double alturaPoplitea,
			Double alturaOjo, Double alturaCodoSilla,
			Double circunferenciaAbdominal, Double circunferenciaCadera,
			Double manoPiso, Double indiceCadera) {
		super();
		this.idHistoria = idHistoria;
		this.paciente = paciente;
		this.varioPeso = varioPeso;
		this.pesoCambiado = pesoCambiado;
		this.pesoCausa = pesoCausa;
		this.cafe = cafe;
		this.cantidadCafe = cantidadCafe;
		this.dificultadDormir = dificultadDormir;
		this.dolorCafe = dolorCafe;
		this.actividadFisica = actividadFisica;
		this.actividadTipo = actividadTipo;
		this.actividadFrecuencia = actividadFrecuencia;
		this.actividadTiempo = actividadTiempo;
		this.actividadExtra = actividadExtra;
		this.tipoExtra = tipoExtra;
		this.cigarroConsume = cigarroConsume;
		this.cigarroActual = cigarroActual;
		this.cigarroCantidad = cigarroCantidad;
		this.cigarroInicio = cigarroInicio;
		this.cigarroFin = cigarroFin;
		this.cigarroRazon = cigarroRazon;
		this.alcoholConsume = alcoholConsume;
		this.alcoholActual = alcoholActual;
		this.alcoholFrecuencia = alcoholFrecuencia;
		this.alcoholTipo = alcoholTipo;
		this.alcoholCantidad = alcoholCantidad;
		this.alcoholEmbriagado = alcoholEmbriagado;
		this.alcoholTratamiento = alcoholTratamiento;
		this.alcoholRehabilitacion = alcoholRehabilitacion;
		this.alcoholAccidente = alcoholAccidente;
		this.drogaConsume = drogaConsume;
		this.drogaExplicacion = drogaExplicacion;
		this.drogaTratamiento = drogaTratamiento;
		this.drogaRehabilitacion = drogaRehabilitacion;
		this.medicamentoConsume = medicamentoConsume;
		this.medicamentoTipo = medicamentoTipo;
		this.medicamentoInicio = medicamentoInicio;
		this.medicamentoCantidad = medicamentoCantidad;
		this.enfermedadPosee = enfermedadPosee;
		this.enfermedad = enfermedad;
		this.medico = medico;
		this.tratamiento = tratamiento;
		this.transfusion = transfusion;
		this.ets = ets;
		this.vih = vih;
		this.vihResultado = vihResultado;
		this.flujo = flujo;
		this.secrecion = secrecion;
		this.dolor = dolor;
		this.endurecimiento = endurecimiento;
		this.infeccion = infeccion;
		this.anticonceptivo = anticonceptivo;
		this.dolorRelacion = dolorRelacion;
		this.esterilizacion = esterilizacion;
		this.aparato = aparato;
		this.edadDesarrollo = edadDesarrollo;
		this.ultimaMenstruacion = ultimaMenstruacion;
		this.numeroEmbarazos = numeroEmbarazos;
		this.numeroPartos = numeroPartos;
		this.numeroCesareas = numeroCesareas;
		this.numeroAbortos = numeroAbortos;
		this.ultimaCitologia = ultimaCitologia;
		this.poliquistico = poliquistico;
		this.embarazo = embarazo;
		this.embarazoSemanas = embarazoSemanas;
		this.eco = eco;
		this.ecoResultado = ecoResultado;
		this.mamografia = mamografia;
		this.mamografiaResultado = mamografiaResultado;
		this.horaAuditoria = horaAuditoria;
		this.fechaAuditoria = fechaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
		this.carta = carta;
		this.miembroDerecho = derecho;
		this.miembroIzquierdo = izquierdo;
		this.telefonoOdontologo = telefonodontologo;
		this.alturaCodo = alturaCodo;
		this.alturaHombro = alturaHombro;
		this.alturaCodoSilla = alturaCodoSilla;
		this.alturaOjo = alturaOjo;
		this.alturaPoplitea = alturaPoplitea;
		this.anchuraHombro = anchuraHombro;
		this.manoPiso = manoPiso;
		this.circunferenciaAbdominal = circunferenciaAbdominal;
		this.circunferenciaCadera = circunferenciaCadera;
		this.indiceCadera = indiceCadera;
		this.dientea = dientea;
		this.dienteb = dienteb;
		this.dientec = dientec;
		this.diented = diented;
		this.dientee = dientee;
		this.dientef = dientef;
		this.dienteg = dienteg;
		this.dienteh = dienteh;
		this.dientei = dientei;
		this.dientej = dientej;
		this.dientek = dientek;
		this.dientel = dientel;
		this.dientem = dientem;
		this.dienten = dienten;
		this.dienteo = dienteo;
		this.dientep = dientep;
		this.dienteq = dienteq;
		this.dienter = dienter;
		this.dientes = dientes;
		this.dientet = dientet;
		this.dienteu = dienteu;
		this.dientev = dientev;
		this.dientew = dientew;
		this.dientex = dientex;
		this.dientey = dientey;
		this.dientez = dientez;
		this.dienteza = dienteza;
		this.dientezb = dientezb;
		this.dientezc = dientezc;
		this.dientezd = dientezd;
		this.dienteze = dienteze;
		this.dientezf = dientezf;
		this.visionColor = visionColores;
	}

	public long getIdHistoria() {
		return idHistoria;
	}

	public void setIdHistoria(long idHistoria) {
		this.idHistoria = idHistoria;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Boolean getVarioPeso() {
		return varioPeso;
	}

	public void setVarioPeso(Boolean varioPeso) {
		this.varioPeso = varioPeso;
	}

	public String getPesoCambiado() {
		return pesoCambiado;
	}

	public void setPesoCambiado(String pesoCambiado) {
		this.pesoCambiado = pesoCambiado;
	}

	public String getPesoCausa() {
		return pesoCausa;
	}

	public void setPesoCausa(String pesoCausa) {
		this.pesoCausa = pesoCausa;
	}

	public Boolean getCafe() {
		return cafe;
	}

	public void setCafe(Boolean cafe) {
		this.cafe = cafe;
	}

	public Integer getCantidadCafe() {
		return cantidadCafe;
	}

	public void setCantidadCafe(Integer cantidadCafe) {
		this.cantidadCafe = cantidadCafe;
	}

	public Boolean getDificultadDormir() {
		return dificultadDormir;
	}

	public void setDificultadDormir(Boolean dificultadDormir) {
		this.dificultadDormir = dificultadDormir;
	}

	public Boolean getDolorCafe() {
		return dolorCafe;
	}

	public void setDolorCafe(Boolean dolorCafe) {
		this.dolorCafe = dolorCafe;
	}

	public Boolean getActividadFisica() {
		return actividadFisica;
	}

	public void setActividadFisica(Boolean actividadFisica) {
		this.actividadFisica = actividadFisica;
	}

	public String getActividadTipo() {
		return actividadTipo;
	}

	public void setActividadTipo(String actividadTipo) {
		this.actividadTipo = actividadTipo;
	}

	public String getActividadFrecuencia() {
		return actividadFrecuencia;
	}

	public void setActividadFrecuencia(String actividadFrecuencia) {
		this.actividadFrecuencia = actividadFrecuencia;
	}

	public String getActividadTiempo() {
		return actividadTiempo;
	}

	public void setActividadTiempo(String actividadTiempo) {
		this.actividadTiempo = actividadTiempo;
	}

	public Boolean getActividadExtra() {
		return actividadExtra;
	}

	public void setActividadExtra(Boolean actividadExtra) {
		this.actividadExtra = actividadExtra;
	}

	public String getTipoExtra() {
		return tipoExtra;
	}

	public void setTipoExtra(String tipoExtra) {
		this.tipoExtra = tipoExtra;
	}

	public Boolean getCigarroConsume() {
		return cigarroConsume;
	}

	public void setCigarroConsume(Boolean cigarroConsume) {
		this.cigarroConsume = cigarroConsume;
	}

	public Boolean getCigarroActual() {
		return cigarroActual;
	}

	public void setCigarroActual(Boolean cigarroActual) {
		this.cigarroActual = cigarroActual;
	}

	public Integer getCigarroCantidad() {
		return cigarroCantidad;
	}

	public void setCigarroCantidad(Integer cigarroCantidad) {
		this.cigarroCantidad = cigarroCantidad;
	}

	public Timestamp getCigarroInicio() {
		return cigarroInicio;
	}

	public void setCigarroInicio(Timestamp cigarroInicio) {
		this.cigarroInicio = cigarroInicio;
	}

	public Timestamp getCigarroFin() {
		return cigarroFin;
	}

	public void setCigarroFin(Timestamp cigarroFin) {
		this.cigarroFin = cigarroFin;
	}

	public String getCigarroRazon() {
		return cigarroRazon;
	}

	public void setCigarroRazon(String cigarroRazon) {
		this.cigarroRazon = cigarroRazon;
	}

	public Boolean getAlcoholConsume() {
		return alcoholConsume;
	}

	public void setAlcoholConsume(Boolean alcoholConsume) {
		this.alcoholConsume = alcoholConsume;
	}

	public Boolean getAlcoholActual() {
		return alcoholActual;
	}

	public void setAlcoholActual(Boolean alcoholActual) {
		this.alcoholActual = alcoholActual;
	}

	public String getAlcoholFrecuencia() {
		return alcoholFrecuencia;
	}

	public void setAlcoholFrecuencia(String alcoholFrecuencia) {
		this.alcoholFrecuencia = alcoholFrecuencia;
	}

	public String getAlcoholTipo() {
		return alcoholTipo;
	}

	public void setAlcoholTipo(String alcoholTipo) {
		this.alcoholTipo = alcoholTipo;
	}

	public Integer getAlcoholCantidad() {
		return alcoholCantidad;
	}

	public void setAlcoholCantidad(Integer alcoholCantidad) {
		this.alcoholCantidad = alcoholCantidad;
	}

	public Boolean getAlcoholEmbriagado() {
		return alcoholEmbriagado;
	}

	public void setAlcoholEmbriagado(Boolean alcoholEmbriagado) {
		this.alcoholEmbriagado = alcoholEmbriagado;
	}

	public Boolean getAlcoholTratamiento() {
		return alcoholTratamiento;
	}

	public void setAlcoholTratamiento(Boolean alcoholTratamiento) {
		this.alcoholTratamiento = alcoholTratamiento;
	}

	public Boolean getAlcoholRehabilitacion() {
		return alcoholRehabilitacion;
	}

	public void setAlcoholRehabilitacion(Boolean alcoholRehabilitacion) {
		this.alcoholRehabilitacion = alcoholRehabilitacion;
	}

	public Boolean getAlcoholAccidente() {
		return alcoholAccidente;
	}

	public void setAlcoholAccidente(Boolean alcoholAccidente) {
		this.alcoholAccidente = alcoholAccidente;
	}

	public Boolean getDrogaConsume() {
		return drogaConsume;
	}

	public void setDrogaConsume(Boolean drogaConsume) {
		this.drogaConsume = drogaConsume;
	}

	public String getDrogaExplicacion() {
		return drogaExplicacion;
	}

	public void setDrogaExplicacion(String drogaExplicacion) {
		this.drogaExplicacion = drogaExplicacion;
	}

	public Boolean getDrogaTratamiento() {
		return drogaTratamiento;
	}

	public void setDrogaTratamiento(Boolean drogaTratamiento) {
		this.drogaTratamiento = drogaTratamiento;
	}

	public Boolean getDrogaRehabilitacion() {
		return drogaRehabilitacion;
	}

	public void setDrogaRehabilitacion(Boolean drogaRehabilitacion) {
		this.drogaRehabilitacion = drogaRehabilitacion;
	}

	public Boolean getMedicamentoConsume() {
		return medicamentoConsume;
	}

	public void setMedicamentoConsume(Boolean medicamentoConsume) {
		this.medicamentoConsume = medicamentoConsume;
	}

	public String getMedicamentoTipo() {
		return medicamentoTipo;
	}

	public void setMedicamentoTipo(String medicamentoTipo) {
		this.medicamentoTipo = medicamentoTipo;
	}

	public Timestamp getMedicamentoInicio() {
		return medicamentoInicio;
	}

	public void setMedicamentoInicio(Timestamp medicamentoInicio) {
		this.medicamentoInicio = medicamentoInicio;
	}

	public String getMedicamentoCantidad() {
		return medicamentoCantidad;
	}

	public void setMedicamentoCantidad(String medicamentoCantidad) {
		this.medicamentoCantidad = medicamentoCantidad;
	}

	public Boolean getEnfermedadPosee() {
		return enfermedadPosee;
	}

	public void setEnfermedadPosee(Boolean enfermedadPosee) {
		this.enfermedadPosee = enfermedadPosee;
	}

	public String getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(String enfermedad) {
		this.enfermedad = enfermedad;
	}

	public Boolean getMedico() {
		return medico;
	}

	public void setMedico(Boolean medico) {
		this.medico = medico;
	}

	public Boolean getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(Boolean tratamiento) {
		this.tratamiento = tratamiento;
	}

	public Boolean getTransfusion() {
		return transfusion;
	}

	public void setTransfusion(Boolean transfusion) {
		this.transfusion = transfusion;
	}

	public Boolean getEts() {
		return ets;
	}

	public void setEts(Boolean ets) {
		this.ets = ets;
	}

	public Boolean getVih() {
		return vih;
	}

	public void setVih(Boolean vih) {
		this.vih = vih;
	}

	public String getVihResultado() {
		return vihResultado;
	}

	public void setVihResultado(String vihResultado) {
		this.vihResultado = vihResultado;
	}

	public Boolean getFlujo() {
		return flujo;
	}

	public void setFlujo(Boolean flujo) {
		this.flujo = flujo;
	}

	public Boolean getSecrecion() {
		return secrecion;
	}

	public void setSecrecion(Boolean secrecion) {
		this.secrecion = secrecion;
	}

	public Boolean getDolor() {
		return dolor;
	}

	public void setDolor(Boolean dolor) {
		this.dolor = dolor;
	}

	public Boolean getEndurecimiento() {
		return endurecimiento;
	}

	public void setEndurecimiento(Boolean endurecimiento) {
		this.endurecimiento = endurecimiento;
	}

	public Boolean getInfeccion() {
		return infeccion;
	}

	public void setInfeccion(Boolean infeccion) {
		this.infeccion = infeccion;
	}

	public Boolean getAnticonceptivo() {
		return anticonceptivo;
	}

	public void setAnticonceptivo(Boolean anticonceptivo) {
		this.anticonceptivo = anticonceptivo;
	}

	public Boolean getDolorRelacion() {
		return dolorRelacion;
	}

	public void setDolorRelacion(Boolean dolorRelacion) {
		this.dolorRelacion = dolorRelacion;
	}

	public Boolean getEsterilizacion() {
		return esterilizacion;
	}

	public void setEsterilizacion(Boolean esterilizacion) {
		this.esterilizacion = esterilizacion;
	}

	public Boolean getAparato() {
		return aparato;
	}

	public void setAparato(Boolean aparato) {
		this.aparato = aparato;
	}

	public Integer getEdadDesarrollo() {
		return edadDesarrollo;
	}

	public void setEdadDesarrollo(Integer edadDesarrollo) {
		this.edadDesarrollo = edadDesarrollo;
	}

	public Timestamp getUltimaMenstruacion() {
		return ultimaMenstruacion;
	}

	public void setUltimaMenstruacion(Timestamp ultimaMenstruacion) {
		this.ultimaMenstruacion = ultimaMenstruacion;
	}

	public Integer getNumeroEmbarazos() {
		return numeroEmbarazos;
	}

	public void setNumeroEmbarazos(Integer numeroEmbarazos) {
		this.numeroEmbarazos = numeroEmbarazos;
	}

	public Integer getNumeroPartos() {
		return numeroPartos;
	}

	public void setNumeroPartos(Integer numeroPartos) {
		this.numeroPartos = numeroPartos;
	}

	public Integer getNumeroCesareas() {
		return numeroCesareas;
	}

	public void setNumeroCesareas(Integer numeroCesareas) {
		this.numeroCesareas = numeroCesareas;
	}

	public Integer getNumeroAbortos() {
		return numeroAbortos;
	}

	public void setNumeroAbortos(Integer numeroAbortos) {
		this.numeroAbortos = numeroAbortos;
	}

	public Timestamp getUltimaCitologia() {
		return ultimaCitologia;
	}

	public void setUltimaCitologia(Timestamp ultimaCitologia) {
		this.ultimaCitologia = ultimaCitologia;
	}

	public Boolean getPoliquistico() {
		return poliquistico;
	}

	public void setPoliquistico(Boolean poliquistico) {
		this.poliquistico = poliquistico;
	}

	public Boolean getEmbarazo() {
		return embarazo;
	}

	public void setEmbarazo(Boolean embarazo) {
		this.embarazo = embarazo;
	}

	public Integer getEmbarazoSemanas() {
		return embarazoSemanas;
	}

	public void setEmbarazoSemanas(Integer embarazoSemanas) {
		this.embarazoSemanas = embarazoSemanas;
	}

	public Boolean getEco() {
		return eco;
	}

	public void setEco(Boolean eco) {
		this.eco = eco;
	}

	public String getEcoResultado() {
		return ecoResultado;
	}

	public void setEcoResultado(String ecoResultado) {
		this.ecoResultado = ecoResultado;
	}

	public Boolean getMamografia() {
		return mamografia;
	}

	public void setMamografia(Boolean mamografia) {
		this.mamografia = mamografia;
	}

	public String getMamografiaResultado() {
		return mamografiaResultado;
	}

	public void setMamografiaResultado(String mamografiaResultado) {
		this.mamografiaResultado = mamografiaResultado;
	}

	public String getHoraAuditoria() {
		return horaAuditoria;
	}

	public void setHoraAuditoria(String horaAuditoria) {
		this.horaAuditoria = horaAuditoria;
	}

	public Timestamp getFechaAuditoria() {
		return fechaAuditoria;
	}

	public void setFechaAuditoria(Timestamp fechaAuditoria) {
		this.fechaAuditoria = fechaAuditoria;
	}

	public String getUsuarioAuditoria() {
		return usuarioAuditoria;
	}

	public void setUsuarioAuditoria(String usuarioAuditoria) {
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public Set<HistoriaVacuna> getHistoriasVacunas() {
		return historiasVacunas;
	}

	public void setHistoriasVacunas(Set<HistoriaVacuna> historiasVacunas) {
		this.historiasVacunas = historiasVacunas;
	}

	public Set<HistoriaIntervencion> getHistoriasIntervenciones() {
		return historiasIntervenciones;
	}

	public void setHistoriasIntervenciones(
			Set<HistoriaIntervencion> historiasIntervenciones) {
		this.historiasIntervenciones = historiasIntervenciones;
	}

	public Set<HistoriaAccidente> getHistoriasAccidentes() {
		return historiasAccidentes;
	}

	public void setHistoriasAccidentes(
			Set<HistoriaAccidente> historiasAccidentes) {
		this.historiasAccidentes = historiasAccidentes;
	}

	public String getDientea() {
		return dientea;
	}

	public void setDientea(String dientea) {
		this.dientea = dientea;
	}

	public String getDienteb() {
		return dienteb;
	}

	public void setDienteb(String dienteb) {
		this.dienteb = dienteb;
	}

	public String getDientec() {
		return dientec;
	}

	public void setDientec(String dientec) {
		this.dientec = dientec;
	}

	public String getDiented() {
		return diented;
	}

	public void setDiented(String diented) {
		this.diented = diented;
	}

	public String getDientee() {
		return dientee;
	}

	public void setDientee(String dientee) {
		this.dientee = dientee;
	}

	public String getDientef() {
		return dientef;
	}

	public void setDientef(String dientef) {
		this.dientef = dientef;
	}

	public String getDienteg() {
		return dienteg;
	}

	public void setDienteg(String dienteg) {
		this.dienteg = dienteg;
	}

	public String getDienteh() {
		return dienteh;
	}

	public void setDienteh(String dienteh) {
		this.dienteh = dienteh;
	}

	public String getDientei() {
		return dientei;
	}

	public void setDientei(String dientei) {
		this.dientei = dientei;
	}

	public String getDientej() {
		return dientej;
	}

	public void setDientej(String dientej) {
		this.dientej = dientej;
	}

	public String getDientek() {
		return dientek;
	}

	public void setDientek(String dientek) {
		this.dientek = dientek;
	}

	public String getDientel() {
		return dientel;
	}

	public void setDientel(String dientel) {
		this.dientel = dientel;
	}

	public String getDientem() {
		return dientem;
	}

	public void setDientem(String dientem) {
		this.dientem = dientem;
	}

	public String getDienten() {
		return dienten;
	}

	public void setDienten(String dienten) {
		this.dienten = dienten;
	}

	public String getDienteo() {
		return dienteo;
	}

	public void setDienteo(String dienteo) {
		this.dienteo = dienteo;
	}

	public String getDientep() {
		return dientep;
	}

	public void setDientep(String dientep) {
		this.dientep = dientep;
	}

	public String getDienteq() {
		return dienteq;
	}

	public void setDienteq(String dienteq) {
		this.dienteq = dienteq;
	}

	public String getDienter() {
		return dienter;
	}

	public void setDienter(String dienter) {
		this.dienter = dienter;
	}

	public String getDientes() {
		return dientes;
	}

	public void setDientes(String dientes) {
		this.dientes = dientes;
	}

	public String getDientet() {
		return dientet;
	}

	public void setDientet(String dientet) {
		this.dientet = dientet;
	}

	public String getDienteu() {
		return dienteu;
	}

	public void setDienteu(String dienteu) {
		this.dienteu = dienteu;
	}

	public String getDientev() {
		return dientev;
	}

	public void setDientev(String dientev) {
		this.dientev = dientev;
	}

	public String getDientew() {
		return dientew;
	}

	public void setDientew(String dientew) {
		this.dientew = dientew;
	}

	public String getDientex() {
		return dientex;
	}

	public void setDientex(String dientex) {
		this.dientex = dientex;
	}

	public String getDientey() {
		return dientey;
	}

	public void setDientey(String dientey) {
		this.dientey = dientey;
	}

	public String getDientez() {
		return dientez;
	}

	public void setDientez(String dientez) {
		this.dientez = dientez;
	}

	public String getDientezf() {
		return dientezf;
	}

	public void setDientezf(String dientezf) {
		this.dientezf = dientezf;
	}

	public String getDienteza() {
		return dienteza;
	}

	public void setDienteza(String dienteza) {
		this.dienteza = dienteza;
	}

	public String getDientezb() {
		return dientezb;
	}

	public void setDientezb(String dientezb) {
		this.dientezb = dientezb;
	}

	public String getDientezc() {
		return dientezc;
	}

	public void setDientezc(String dientezc) {
		this.dientezc = dientezc;
	}

	public String getDientezd() {
		return dientezd;
	}

	public void setDientezd(String dientezd) {
		this.dientezd = dientezd;
	}

	public String getDienteze() {
		return dienteze;
	}

	public void setDienteze(String dienteze) {
		this.dienteze = dienteze;
	}

	public String getCarta() {
		return carta;
	}

	public void setCarta(String carta) {
		this.carta = carta;
	}

	public String getTelefonoOdontologo() {
		return telefonoOdontologo;
	}

	public void setTelefonoOdontologo(String telefonoOdontologo) {
		this.telefonoOdontologo = telefonoOdontologo;
	}

	public Double getAlturaHombro() {
		return alturaHombro;
	}

	public void setAlturaHombro(Double alturaHombro) {
		this.alturaHombro = alturaHombro;
	}

	public Double getAnchuraHombro() {
		return anchuraHombro;
	}

	public void setAnchuraHombro(Double anchuraHombro) {
		this.anchuraHombro = anchuraHombro;
	}

	public Double getAlturaCodo() {
		return alturaCodo;
	}

	public void setAlturaCodo(Double alturaCodo) {
		this.alturaCodo = alturaCodo;
	}

	public Double getMiembroIzquierdo() {
		return miembroIzquierdo;
	}

	public void setMiembroIzquierdo(Double miembroIzquierdo) {
		this.miembroIzquierdo = miembroIzquierdo;
	}

	public Double getMiembroDerecho() {
		return miembroDerecho;
	}

	public void setMiembroDerecho(Double miembroDerecho) {
		this.miembroDerecho = miembroDerecho;
	}

	public Double getAlturaPoplitea() {
		return alturaPoplitea;
	}

	public void setAlturaPoplitea(Double alturaPoplitea) {
		this.alturaPoplitea = alturaPoplitea;
	}

	public Double getAlturaOjo() {
		return alturaOjo;
	}

	public void setAlturaOjo(Double alturaOjo) {
		this.alturaOjo = alturaOjo;
	}

	public Double getAlturaCodoSilla() {
		return alturaCodoSilla;
	}

	public void setAlturaCodoSilla(Double alturaCodoSilla) {
		this.alturaCodoSilla = alturaCodoSilla;
	}

	public Double getCircunferenciaAbdominal() {
		return circunferenciaAbdominal;
	}

	public void setCircunferenciaAbdominal(Double circunferenciaAbdominal) {
		this.circunferenciaAbdominal = circunferenciaAbdominal;
	}

	public Double getCircunferenciaCadera() {
		return circunferenciaCadera;
	}

	public void setCircunferenciaCadera(Double circunferenciaCadera) {
		this.circunferenciaCadera = circunferenciaCadera;
	}

	public Double getManoPiso() {
		return manoPiso;
	}

	public void setManoPiso(Double manoPiso) {
		this.manoPiso = manoPiso;
	}

	public Double getIndiceCadera() {
		return indiceCadera;
	}

	public void setIndiceCadera(Double indiceCadera) {
		this.indiceCadera = indiceCadera;
	}

	public Boolean getVisionColor() {
		return visionColor;
	}

	public void setVisionColor(Boolean visionColor) {
		this.visionColor = visionColor;
	}

}
