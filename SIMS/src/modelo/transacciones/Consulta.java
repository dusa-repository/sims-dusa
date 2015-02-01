package modelo.transacciones;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import modelo.maestros.Cargo;
import modelo.maestros.Especialidad;
import modelo.maestros.Especialista;
import modelo.maestros.Paciente;
import modelo.seguridad.Usuario;
import modelo.sha.Area;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "consulta", schema = "dusa_sims.dbo")
public class Consulta implements Serializable {

	private static final long serialVersionUID = -5405587652859401694L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_consulta", unique = true, nullable = false)
	private long idConsulta;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_orden", nullable = true)
	private Orden orden;

	@Column(name = "fecha_consulta")
	private Timestamp fechaConsulta;

	@Column(length = 500)
	private String motivoConsulta;

	@Column(length = 2500)
	private String enfermedadActual;

	@Column(length = 100)
	private String tipoConsulta;

	@Column(name = "hora_consulta", length = 10)
	private String horaConsulta;

	@Column(name = "hora_auditoria", length = 10)
	private String horaAuditoria;

	@Column(name = "fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name = "usuario_auditoria", length = 50)
	private String usuarioAuditoria;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean accidenteLaboral;

	@OneToMany(mappedBy = "consulta")
	private Set<ConsultaDiagnostico> diagnosticos;

	@OneToMany(mappedBy = "consulta")
	private Set<ConsultaEspecialista> especialistas;

	@OneToMany(mappedBy = "consulta")
	private Set<ConsultaExamen> examenes;

	@OneToMany(mappedBy = "consulta")
	private Set<ConsultaMedicina> medicinas;

	@OneToMany(mappedBy = "consulta")
	private Set<ConsultaServicioExterno> servicios;

	@OneToMany(mappedBy = "consulta")
	private Set<ConsultaParteCuerpo> partes;

	@Column(name = "consulta_asociada")
	private long consultaAsociada;

	@Column(name = "estatura")
	private Double estatura;

	@Column(name = "peso")
	private Double peso;

	@Column(name = "perimetro_ombligo")
	private Double perimetroOmbligo;

	@Column(name = "perimetro_plena")
	private Double perimetroPlena;

	@Column(name = "perimetro_forzada")
	private Double perimetroForzada;

	@Column(name = "frecuencia")
	private Integer frecuencia;

	@Column(name = "frecuencia_reposo")
	private Integer frecuenciaReposo;

	@Column(name = "frecuencia_esfuerzo")
	private Integer frecuenciaEsfuerzo;

	@Column(name = "frecuencia_post")
	private Integer frecuenciaPost;

	@Column(name = "sistolica_primera")
	private Integer sistolicaPrimera;

	@Column(name = "sistolica_segunda")
	private Integer sistolicaSegunda;

	@Column(name = "sistolica_tercera")
	private Integer sistolicaTercera;

	@Column(name = "extra_reposo")
	private Integer extraReposo;

	@Column(name = "extra_esfuerzo")
	private Integer extraEsfuerzo;

	@Column(name = "extra_post")
	private Integer extraPost;

	@Column(name = "diastolica_primera")
	private Integer diastolicaPrimera;

	@Column(name = "diastolica_segunda")
	private Integer diastolicaSegunda;

	@Column(name = "diastolica_tercera")
	private Integer diastolicaTercera;

	@Column(name = "ritmico")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean ritmico;

	@Column(name = "ritmico_reposo")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean ritmicoReposo;

	@Column(name = "ritmico_esfuerzo")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean ritmicoEsfuerzo;

	@Column(name = "ritmico_post")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean ritmicoPost;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cargo")
	private Cargo cargo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cargo_deseado", referencedColumnName = "id_cargo")
	private Cargo cargoDeseado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_area")
	private Area area;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_area_deseada", referencedColumnName = "id_area")
	private Area areaDeseada;

	@Column(name = "apto")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean apto;

	@Column(name = "reposo")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean reposo;

	@Column(name = "tipo_consulta_secundaria", length = 100)
	private String tipoConsultaSecundaria;

	@Column(name = "examen_preempleo", length = 1000)
	private String examenPreempleo;

	@Column(name = "dias_reposo")
	private Integer diasReposo;

	@Column(length = 500)
	private String observacion;

	@Column(name = "condicion_apto", length = 100)
	private String condicionApto;

	@Column(name = "id_referencia")
	private Long idReferencia;

	@Column(length = 100, name = "cedula_referencia")
	private String cedulaReferencia;

	@Column(length = 256, name = "doctor")
	private String doctor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_especialista")
	private Especialista especialista;

	@Column(length = 100, name = "tipo_reposo")
	private String tipoReposo;

	@Column(length = 100, name = "reposo_embarazo")
	private String reposoEmbarazo;

	@Column(name = "fecha_reposo")
	private Timestamp fechaReposo;

	@Column(name = "frecuencia_respiratoria")
	private Integer frecuenciaRespiratoria;

	@Column(name = "ritmico_respiratoria")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean respiratoriaRitmica;

	public Consulta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Consulta(long idConsulta, Paciente paciente, Usuario usuario,
			Timestamp fechaConsulta, String horaConsulta, String horaAuditoria,
			Timestamp fechaAuditoria, String usuarioAuditoria,
			boolean accidenteLaboral, String motivo, String tipo,
			String enfermedad, long consultaAsociada, Double estatura,
			Double peso, Double perimetroOmbligo, Double perimetroPlena,
			Double perimetroForzada, Integer frecuencia, Integer frecuenciaR,
			Integer frecuenciaE, Integer frecuenciaP, Integer sistolica1,
			Integer sistolica2, Integer sistolica3, Integer diastolica1,
			Integer diastolica2, Integer diastolica3, Integer extra1,
			Integer extra2, Integer extra3, Boolean ritmico, Boolean ritmico1,
			Boolean ritmico2, Boolean ritmico3, Cargo cargo,
			Cargo cargoDeseado, Area area, Area areaDeseada, boolean apto,
			boolean reposo, String tipoConsultaSecundaria, String examenPre,
			Integer dias, String condicion, String doctor,
			Especialista especialista, String tipoReposo,
			String reposoEmbarazo, Timestamp fechaReposo,
			Integer frecuenciaRespiratoria, Boolean respiratoriaRitmica,
			Orden orden) {
		super();
		this.frecuenciaRespiratoria = frecuenciaRespiratoria;
		this.respiratoriaRitmica = respiratoriaRitmica;
		this.idConsulta = idConsulta;
		this.paciente = paciente;
		this.fechaConsulta = fechaConsulta;
		this.horaConsulta = horaConsulta;
		this.horaAuditoria = horaAuditoria;
		this.fechaAuditoria = fechaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
		this.usuario = usuario;
		this.accidenteLaboral = accidenteLaboral;
		this.motivoConsulta = motivo;
		this.enfermedadActual = enfermedad;
		this.tipoConsulta = tipo;
		this.consultaAsociada = consultaAsociada;
		this.diastolicaPrimera = diastolica1;
		this.diastolicaSegunda = diastolica2;
		this.diastolicaTercera = diastolica3;
		this.estatura = estatura;
		this.extraReposo = extra1;
		this.extraEsfuerzo = extra2;
		this.extraPost = extra3;
		this.frecuencia = frecuencia;
		this.frecuenciaReposo = frecuenciaR;
		this.frecuenciaEsfuerzo = frecuenciaE;
		this.frecuenciaPost = frecuenciaP;
		this.perimetroForzada = perimetroForzada;
		this.perimetroOmbligo = perimetroOmbligo;
		this.perimetroPlena = perimetroPlena;
		this.peso = peso;
		this.ritmico = ritmico;
		this.ritmicoReposo = ritmico1;
		this.ritmicoEsfuerzo = ritmico2;
		this.ritmicoPost = ritmico3;
		this.sistolicaPrimera = sistolica1;
		this.sistolicaSegunda = sistolica2;
		this.sistolicaTercera = sistolica3;
		this.cargo = cargo;
		this.cargoDeseado = cargoDeseado;
		this.area = area;
		this.areaDeseada = areaDeseada;
		this.reposo = reposo;
		this.apto = apto;
		this.tipoConsultaSecundaria = tipoConsultaSecundaria;
		this.examenPreempleo = examenPre;
		this.diasReposo = dias;
		this.condicionApto = condicion;
		this.doctor = doctor;
		this.especialista = especialista;
		this.tipoReposo = tipoReposo;
		this.reposoEmbarazo = reposoEmbarazo;
		this.fechaReposo = fechaReposo;
		this.orden = orden;
	}

	public long getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(long idConsulta) {
		this.idConsulta = idConsulta;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Timestamp getFechaConsulta() {
		return fechaConsulta;
	}

	public void setFechaConsulta(Timestamp fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}

	public String getHoraConsulta() {
		return horaConsulta;
	}

	public void setHoraConsulta(String horaConsulta) {
		this.horaConsulta = horaConsulta;
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

	public Boolean isAccidenteLaboral() {
		return accidenteLaboral;
	}

	public void setAccidenteLaboral(Boolean accidenteLaboral) {
		this.accidenteLaboral = accidenteLaboral;
	}

	public Set<ConsultaDiagnostico> getDiagnosticos() {
		return diagnosticos;
	}

	public void setDiagnosticos(Set<ConsultaDiagnostico> diagnosticos) {
		this.diagnosticos = diagnosticos;
	}

	public Set<ConsultaEspecialista> getEspecialistas() {
		return especialistas;
	}

	public void setEspecialistas(Set<ConsultaEspecialista> especialistas) {
		this.especialistas = especialistas;
	}

	public Set<ConsultaExamen> getExamenes() {
		return examenes;
	}

	public void setExamenes(Set<ConsultaExamen> examenes) {
		this.examenes = examenes;
	}

	public Set<ConsultaMedicina> getMedicinas() {
		return medicinas;
	}

	public void setMedicinas(Set<ConsultaMedicina> medicinas) {
		this.medicinas = medicinas;
	}

	public Set<ConsultaServicioExterno> getServicios() {
		return servicios;
	}

	public void setServicios(Set<ConsultaServicioExterno> servicios) {
		this.servicios = servicios;
	}

	public String getMotivoConsulta() {
		return motivoConsulta;
	}

	public void setMotivoConsulta(String motivoConsulta) {
		this.motivoConsulta = motivoConsulta;
	}

	public String getEnfermedadActual() {
		return enfermedadActual;
	}

	public void setEnfermedadActual(String enfermedadActual) {
		this.enfermedadActual = enfermedadActual;
	}

	public String getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	public long getConsultaAsociada() {
		return consultaAsociada;
	}

	public void setConsultaAsociada(long consultaAsociada) {
		this.consultaAsociada = consultaAsociada;
	}

	public Set<ConsultaParteCuerpo> getPartes() {
		return partes;
	}

	public void setPartes(Set<ConsultaParteCuerpo> partes) {
		this.partes = partes;
	}

	public Double getEstatura() {
		return estatura;
	}

	public void setEstatura(Double estatura) {
		this.estatura = estatura;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getPerimetroOmbligo() {
		return perimetroOmbligo;
	}

	public void setPerimetroOmbligo(Double perimetroOmbligo) {
		this.perimetroOmbligo = perimetroOmbligo;
	}

	public Double getPerimetroPlena() {
		return perimetroPlena;
	}

	public void setPerimetroPlena(Double perimetroPlena) {
		this.perimetroPlena = perimetroPlena;
	}

	public Double getPerimetroForzada() {
		return perimetroForzada;
	}

	public void setPerimetroForzada(Double perimetroForzada) {
		this.perimetroForzada = perimetroForzada;
	}

	public Integer getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(Integer frecuencia) {
		this.frecuencia = frecuencia;
	}

	public Integer getFrecuenciaReposo() {
		return frecuenciaReposo;
	}

	public void setFrecuenciaReposo(Integer frecuenciaReposo) {
		this.frecuenciaReposo = frecuenciaReposo;
	}

	public Integer getFrecuenciaEsfuerzo() {
		return frecuenciaEsfuerzo;
	}

	public void setFrecuenciaEsfuerzo(Integer frecuenciaEsfuerzo) {
		this.frecuenciaEsfuerzo = frecuenciaEsfuerzo;
	}

	public Integer getFrecuenciaPost() {
		return frecuenciaPost;
	}

	public void setFrecuenciaPost(Integer frecuenciaPost) {
		this.frecuenciaPost = frecuenciaPost;
	}

	public Integer getSistolicaPrimera() {
		return sistolicaPrimera;
	}

	public void setSistolicaPrimera(Integer sistolicaPrimera) {
		this.sistolicaPrimera = sistolicaPrimera;
	}

	public Integer getSistolicaSegunda() {
		return sistolicaSegunda;
	}

	public void setSistolicaSegunda(Integer sistolicaSegunda) {
		this.sistolicaSegunda = sistolicaSegunda;
	}

	public Integer getSistolicaTercera() {
		return sistolicaTercera;
	}

	public void setSistolicaTercera(Integer sistolicaTercera) {
		this.sistolicaTercera = sistolicaTercera;
	}

	public Integer getExtraReposo() {
		return extraReposo;
	}

	public void setExtraReposo(Integer extraReposo) {
		this.extraReposo = extraReposo;
	}

	public Integer getExtraEsfuerzo() {
		return extraEsfuerzo;
	}

	public void setExtraEsfuerzo(Integer extraEsfuerzo) {
		this.extraEsfuerzo = extraEsfuerzo;
	}

	public Integer getExtraPost() {
		return extraPost;
	}

	public void setExtraPost(Integer extraPost) {
		this.extraPost = extraPost;
	}

	public Integer getDiastolicaPrimera() {
		return diastolicaPrimera;
	}

	public void setDiastolicaPrimera(Integer diastolicaPrimera) {
		this.diastolicaPrimera = diastolicaPrimera;
	}

	public Integer getDiastolicaSegunda() {
		return diastolicaSegunda;
	}

	public void setDiastolicaSegunda(Integer diastolicaSegunda) {
		this.diastolicaSegunda = diastolicaSegunda;
	}

	public Integer getDiastolicaTercera() {
		return diastolicaTercera;
	}

	public void setDiastolicaTercera(Integer diastolicaTercera) {
		this.diastolicaTercera = diastolicaTercera;
	}

	public Boolean getRitmico() {
		return ritmico;
	}

	public void setRitmico(Boolean ritmico) {
		this.ritmico = ritmico;
	}

	public Boolean getRitmicoReposo() {
		return ritmicoReposo;
	}

	public void setRitmicoReposo(Boolean ritmicoReposo) {
		this.ritmicoReposo = ritmicoReposo;
	}

	public Boolean getRitmicoEsfuerzo() {
		return ritmicoEsfuerzo;
	}

	public void setRitmicoEsfuerzo(Boolean ritmicoEsfuerzo) {
		this.ritmicoEsfuerzo = ritmicoEsfuerzo;
	}

	public Boolean getRitmicoPost() {
		return ritmicoPost;
	}

	public void setRitmicoPost(Boolean ritmicoPost) {
		this.ritmicoPost = ritmicoPost;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Cargo getCargoDeseado() {
		return cargoDeseado;
	}

	public void setCargoDeseado(Cargo cargoDeseado) {
		this.cargoDeseado = cargoDeseado;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Area getAreaDeseada() {
		return areaDeseada;
	}

	public void setAreaDeseada(Area areaDeseada) {
		this.areaDeseada = areaDeseada;
	}

	public Boolean getApto() {
		return apto;
	}

	public void setApto(Boolean apto) {
		this.apto = apto;
	}

	public Boolean getReposo() {
		return reposo;
	}

	public void setReposo(Boolean reposo) {
		this.reposo = reposo;
	}

	public String getTipoConsultaSecundaria() {
		return tipoConsultaSecundaria;
	}

	public void setTipoConsultaSecundaria(String tipoConsultaSecundaria) {
		this.tipoConsultaSecundaria = tipoConsultaSecundaria;
	}

	public String getExamenPreempleo() {
		return examenPreempleo;
	}

	public void setExamenPreempleo(String examenPreempleo) {
		this.examenPreempleo = examenPreempleo;
	}

	public Integer getDiasReposo() {
		return diasReposo;
	}

	public void setDiasReposo(Integer diasReposo) {
		this.diasReposo = diasReposo;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getCondicionApto() {
		return condicionApto;
	}

	public void setCondicionApto(String condicionApto) {
		this.condicionApto = condicionApto;
	}

	public String traerFecha() {
		DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		return String.valueOf(formatoFecha.format(fechaConsulta));
	}

	public Long getIdReferencia() {
		return idReferencia;
	}

	public void setIdReferencia(Long idReferencia) {
		this.idReferencia = idReferencia;
	}

	public String getCedulaReferencia() {
		return cedulaReferencia;
	}

	public void setCedulaReferencia(String cedulaReferencia) {
		this.cedulaReferencia = cedulaReferencia;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public Especialista getEspecialista() {
		return especialista;
	}

	public void setEspecialista(Especialista especialista) {
		this.especialista = especialista;
	}

	public String getTipoReposo() {
		return tipoReposo;
	}

	public void setTipoReposo(String tipoReposo) {
		this.tipoReposo = tipoReposo;
	}

	public String getReposoEmbarazo() {
		return reposoEmbarazo;
	}

	public void setReposoEmbarazo(String reposoEmbarazo) {
		this.reposoEmbarazo = reposoEmbarazo;
	}

	public Timestamp getFechaReposo() {
		return fechaReposo;
	}

	public void setFechaReposo(Timestamp fechaReposo) {
		this.fechaReposo = fechaReposo;
	}

	public Integer getFrecuenciaRespiratoria() {
		return frecuenciaRespiratoria;
	}

	public void setFrecuenciaRespiratoria(Integer frecuenciaRespiratoria) {
		this.frecuenciaRespiratoria = frecuenciaRespiratoria;
	}

	public Boolean getRespiratoriaRitmica() {
		return respiratoriaRitmica;
	}

	public void setRespiratoriaRitmica(Boolean respiratoriaRitmica) {
		this.respiratoriaRitmica = respiratoriaRitmica;
	}

	public Orden getOrden() {
		return orden;
	}

	public void setOrden(Orden orden) {
		this.orden = orden;
	}

}
