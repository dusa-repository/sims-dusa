package modelo.maestros;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import modelo.control.ControlConsulta;
import modelo.control.ControlOrden;
import modelo.sha.Area;
import modelo.sha.Informe;
import modelo.social.Ficha;
import modelo.social.VisitaSocial;
import modelo.transacciones.Historia;
import modelo.transacciones.Orden;
import modelo.transacciones.PacienteMedicina;

import org.hibernate.annotations.Type;

/**
 * The persistent class for the paciente database table.
 * 
 */
@Entity
@Table(name = "paciente", schema = "dusa_sims.dbo")
public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_paciente", length = 15, unique = true, nullable = false)
	private String cedula;

	@Column(length = 50)
	private String ficha;
	
	@Column(name = "periodo_estudios", length = 100)
	private String periodoEstudios;

	@Column(name = "primer_apellido", length = 100)
	private String primerApellido;

	@Column(name = "primer_nombre", length = 100)
	private String primerNombre;

	@Column(name = "segundo_apellido", length = 100)
	private String segundoApellido;

	@Column(name = "segundo_nombre", length = 100)
	private String segundoNombre;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean trabajador;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean discapacidad;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean estatus;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean muerte;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean alergia;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean lentes;

	@Column(name = "fecha_nacimiento")
	private Timestamp fechaNacimiento;

	@Column(length = 500, name = "lugar_nacimiento")
	private String lugarNacimiento;

	@Column(length = 500, name = "observacion_estatus")
	private String observacionEstatus;

	@Column(length = 10)
	private String sexo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_estado_civil")
	private EstadoCivil estadoCivil;

	@Column(length = 130)
	private Integer edad;

	@Column(length = 3, name = "grupo_sanguineo")
	private String grupoSanguineo;

	@Column(length = 500, name = "observacion_alergias")
	private String observacionAlergias;

	@Column(length = 10)
	private String mano;

	@Column
	private Double estatura;

	@Column
	private Double peso;

	@Column(length = 10, name = "origen_discapacidad")
	private String origenDiscapacidad;

	@Column(length = 10, name = "tipo_discapacidad")
	private String tipoDiscapacidad;

	@Column(length = 500, name = "observacion_discapacidad")
	private String observacionDiscapacidad;

	@Column(name = "fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name = "hora_auditoria", length = 10)
	private String horaAuditoria;

	@Column(name = "usuario_auditoria", length = 50)
	private String usuarioAuditoria;

	@Lob
	private byte[] imagen;

	@Column(length = 1000)
	private String direccion;

	@Column(length = 50)
	private String email;

	@Column(length = 50)
	private String telefono1;

	@Column(length = 50)
	private String telefono2;
	
	@Column(length = 50)
	private String emailEmpresa;

	@Column(length = 50)
	private String telefonoAdicional;

	/* Datos en caso de emergencia */

	@Column(name = "nombres_emergencia", length = 200)
	private String nombresEmergencia;

	@Column(name = "apellidos_emergencia", length = 200)
	private String apellidosEmergencia;

	@Column(length = 15)
	private String parentescoEmergencia;

	@Column(length = 50)
	private String telefono1Emergencia;

	@Column(length = 50)
	private String telefono2Emergencia;

	/* Datos en caso de ser familiar */

	@Column(length = 15)
	private String cedulaFamiliar;

	@Column(length = 15)
	private String parentescoFamiliar;

	//
	@Column(length = 15)
	private String nacionalidad;

	@Column(name = "nivel_educativo")
	private String nivelEducativo;

	@Column
	private String profesion;

	@Column(name = "retiro_ivss")
	private String retiroIVSS;

	@Column
	private String turno;

	@Column(name = "nro_inpsasel")
	private String NroInpsasel;

	@Column(name = "fecha_muerte")
	private Timestamp fechaMuerte;

	@Column(name = "fecha_afiliacion")
	private Timestamp fechaAfiliacion;
	
	@Column(name = "fecha_ingreso")
	private Timestamp fechaIngreso;

	@Column(name = "fecha_egreso")
	private Timestamp fechaEgreso;

	@Column(name = "fecha_inscripcion_ivss")
	private Timestamp fechaInscripcionIVSS;

	@Column(length = 100)
	private Integer carga;
	
	// bi-directional many-to-one association to Cita
	@OneToMany(mappedBy = "paciente")
	private Set<Cita> citas;
	
	@OneToMany(mappedBy = "paciente")
	private Set<Orden> ordenes;
	
	@OneToMany(mappedBy = "paciente")
	private Set<ControlOrden> controlOrden;
	
	@OneToMany(mappedBy = "paciente")
	private Set<ControlConsulta> controlConsulta;


	// bi-directional many-to-one association to Usuario
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	// bi-directional many-to-one association to Usuario
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ciudad")
	private Ciudad ciudadVivienda;

	@OneToMany(mappedBy = "paciente")
	private Set<PacienteAntecedente> antecedentesPacientes;

	@OneToOne(mappedBy = "paciente", fetch = FetchType.LAZY)
	private Historia historia;
	
	@OneToOne(mappedBy = "paciente", fetch = FetchType.LAZY)
	private Ficha fichaMaestra;
	
	@OneToOne(mappedBy = "paciente")
	private VisitaSocial visita;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cargo")
	private Cargo cargoReal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nomina")
	private Nomina nomina;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_area")
	private Area area;

	@OneToMany(mappedBy = "paciente")
	private Set<Informe> informesA;

	@OneToMany(mappedBy = "pacienteB")
	private Set<Informe> informesB;

	@OneToMany(mappedBy = "pacienteC")
	private Set<Informe> informesC;

	@OneToMany(mappedBy = "pacienteD")
	private Set<Informe> informesD;

	@OneToMany(mappedBy = "pacienteE")
	private Set<Informe> informesE;

	@OneToMany(mappedBy = "pacienteF")
	private Set<Informe> informeF;

	@OneToMany(mappedBy = "pacienteG")
	private Set<Informe> informesG;

	@OneToMany(mappedBy = "pacienteH")
	private Set<Informe> informesH;

	@OneToMany(mappedBy = "pacienteI")
	private Set<Informe> informesI;

	@OneToMany(mappedBy = "pacienteJ")
	private Set<Informe> informesJ;

	@OneToMany(mappedBy = "pacienteK")
	private Set<Informe> informesK;

	@OneToMany(mappedBy = "pacienteL")
	private Set<Informe> informesL;

	@OneToMany(mappedBy = "pacienteM")
	private Set<Informe> informesM;
	
	@OneToMany(mappedBy = "paciente")
	private Set<PacienteMedicina> pacientesMedicinass;
	
	@OneToMany(mappedBy = "paciente")
	private Set<PeriodoPaciente> pacientesPeriodos;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean brigadista;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean cronico;
	
	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean ayuda;
	
	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean estudia;
	
	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean vive;
	
	@Column
	private String rif;
	
	@Column
	private String pasaporte;
	
	@Column
	private String oficio;
	
	@Column
	private String condicion;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean jefe;
	
	@Column(name = "cargo_carrera")
	private String cargoOCarrera;
	
	@Column(name = "lugar_trabajo")
	private String lugarTrabajo;
	
	@Column(name = "descripcion_ayuda")
	private String descripcionAyuda;
	
	@Column(name = "nro_certificado")
	private String nroCertificado;
	
	@Column(name = "observacion")
	private String observacion;
	
	@Column(name = "tipo_rif")
	private String tipoRif;
	
	@Column(name = "certificado")
	private String certificado;
	
	@Column(length = 100)
	private String municipio;
	
	@Column(length = 100)
	private String parroquia;
	
	@Column(length = 100, name = "punto_referencia")
	private String puntoReferencia;
	
	@Column(length = 100)
	private String sector;
	
	@Column(length = 100)
	private String urb;
	
	@Column(length = 100, name = "av_calle")
	private String avCalle;
	
	@Column(length = 15)
	private String nro;
	
	@Column(length = 100, name = "otro_transporte")
	private String otroTransporte;
	
	@Column(length = 500)
	private String transporte;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean jubilado;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean revision;

	public Paciente() {
	}

	public Paciente(String cedula, String ficha, String primerApellido,
			String primerNombre, String segundoApellido, String segundoNombre,
			boolean trabajador, boolean discapacidad, boolean alergia,
			boolean lentes, Timestamp fechaNacimiento, String lugarNacimiento,
			String sexo, int edad, String grupoSanguineo,
			String observacionAlergias, String mano, double estatura,
			double peso, String origenDiscapacidad, String tipoDiscapacidad,
			String observacionDiscapacidad, Timestamp fechaAuditoria,
			String horaAuditoria, String usuarioAuditoria, byte[] imagen,
			String direccion, String email, String telefono1, String telefono2,
			String nombresEmergencia, String apellidosEmergencia,
			String parentescoEmergencia, String telefono1Emergencia,
			String telefono2Emergencia, String cedulaFamiliar,
			String parentescoFamiliar, Empresa empresa, Ciudad ciudadVivienda,
			Cargo cargoa, Area area, Boolean cronico) {
		super();
		this.cronico = cronico;
		this.cedula = cedula;
		this.ficha = ficha;
		this.primerApellido = primerApellido;
		this.primerNombre = primerNombre;
		this.segundoApellido = segundoApellido;
		this.segundoNombre = segundoNombre;
		this.trabajador = trabajador;
		this.discapacidad = discapacidad;
		this.alergia = alergia;
		this.lentes = lentes;
		this.fechaNacimiento = fechaNacimiento;
		this.lugarNacimiento = lugarNacimiento;
		this.sexo = sexo;
		this.edad = edad;
		this.grupoSanguineo = grupoSanguineo;
		this.observacionAlergias = observacionAlergias;
		this.mano = mano;
		this.estatura = estatura;
		this.peso = peso;
		this.origenDiscapacidad = origenDiscapacidad;
		this.tipoDiscapacidad = tipoDiscapacidad;
		this.observacionDiscapacidad = observacionDiscapacidad;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
		this.imagen = imagen;
		this.direccion = direccion;
		this.email = email;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
		this.nombresEmergencia = nombresEmergencia;
		this.apellidosEmergencia = apellidosEmergencia;
		this.parentescoEmergencia = parentescoEmergencia;
		this.telefono1Emergencia = telefono1Emergencia;
		this.telefono2Emergencia = telefono2Emergencia;
		this.cedulaFamiliar = cedulaFamiliar;
		this.parentescoFamiliar = parentescoFamiliar;
		this.empresa = empresa;
		this.ciudadVivienda = ciudadVivienda;
		this.cargoReal = cargoa;
		this.area = area;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Timestamp getFechaAuditoria() {
		return this.fechaAuditoria;
	}

	public void setFechaAuditoria(Timestamp fechaAuditoria) {
		this.fechaAuditoria = fechaAuditoria;
	}

	public String getHoraAuditoria() {
		return this.horaAuditoria;
	}

	public void setHoraAuditoria(String horaAuditoria) {
		this.horaAuditoria = horaAuditoria;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getPrimerApellido() {
		return this.primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getPrimerNombre() {
		return this.primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getUsuarioAuditoria() {
		return this.usuarioAuditoria;
	}

	public void setUsuarioAuditoria(String usuarioAuditoria) {
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public Set<Cita> getCitas() {
		return this.citas;
	}

	public void setCitas(Set<Cita> citas) {
		this.citas = citas;
	}

	public Cita addCita(Cita cita) {
		getCitas().add(cita);
		cita.setPaciente(this);

		return cita;
	}

	public Cita removeCita(Cita cita) {
		getCitas().remove(cita);
		cita.setPaciente(null);

		return cita;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getFicha() {
		return ficha;
	}

	public void setFicha(String ficha) {
		this.ficha = ficha;
	}

	public boolean isTrabajador() {
		return trabajador;
	}

	public void setTrabajador(boolean trabajador) {
		this.trabajador = trabajador;
	}

	public boolean isDiscapacidad() {
		return discapacidad;
	}

	public void setDiscapacidad(boolean discapacidad) {
		this.discapacidad = discapacidad;
	}

	public boolean isAlergia() {
		return alergia;
	}

	public void setAlergia(boolean alergia) {
		this.alergia = alergia;
	}

	public boolean isLentes() {
		return lentes;
	}

	public void setLentes(boolean lentes) {
		this.lentes = lentes;
	}

	public Timestamp getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Timestamp fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getLugarNacimiento() {
		return lugarNacimiento;
	}

	public void setLugarNacimiento(String lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getGrupoSanguineo() {
		return grupoSanguineo;
	}

	public void setGrupoSanguineo(String grupoSanguineo) {
		this.grupoSanguineo = grupoSanguineo;
	}

	public String getObservacionAlergias() {
		return observacionAlergias;
	}

	public void setObservacionAlergias(String observacionAlergias) {
		this.observacionAlergias = observacionAlergias;
	}

	public String getMano() {
		return mano;
	}

	public void setMano(String mano) {
		this.mano = mano;
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

	public String getOrigenDiscapacidad() {
		return origenDiscapacidad;
	}

	public void setOrigenDiscapacidad(String origenDiscapacidad) {
		this.origenDiscapacidad = origenDiscapacidad;
	}

	public String getTipoDiscapacidad() {
		return tipoDiscapacidad;
	}

	public void setTipoDiscapacidad(String tipoDiscapacidad) {
		this.tipoDiscapacidad = tipoDiscapacidad;
	}

	public String getObservacionDiscapacidad() {
		return observacionDiscapacidad;
	}

	public void setObservacionDiscapacidad(String observacionDiscapacidad) {
		this.observacionDiscapacidad = observacionDiscapacidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getNombresEmergencia() {
		return nombresEmergencia;
	}

	public void setNombresEmergencia(String nombresEmergencia) {
		this.nombresEmergencia = nombresEmergencia;
	}

	public String getApellidosEmergencia() {
		return apellidosEmergencia;
	}

	public void setApellidosEmergencia(String apellidosEmergencia) {
		this.apellidosEmergencia = apellidosEmergencia;
	}

	public String getParentescoEmergencia() {
		return parentescoEmergencia;
	}

	public void setParentescoEmergencia(String parentescoEmergencia) {
		this.parentescoEmergencia = parentescoEmergencia;
	}

	public String getTelefono1Emergencia() {
		return telefono1Emergencia;
	}

	public void setTelefono1Emergencia(String telefono1Emergencia) {
		this.telefono1Emergencia = telefono1Emergencia;
	}

	public String getTelefono2Emergencia() {
		return telefono2Emergencia;
	}

	public void setTelefono2Emergencia(String telefono2Emergencia) {
		this.telefono2Emergencia = telefono2Emergencia;
	}

	public String getCedulaFamiliar() {
		return cedulaFamiliar;
	}

	public void setCedulaFamiliar(String cedulaFamiliar) {
		this.cedulaFamiliar = cedulaFamiliar;
	}

	public String getParentescoFamiliar() {
		return parentescoFamiliar;
	}

	public void setParentescoFamiliar(String parentescoFamiliar) {
		this.parentescoFamiliar = parentescoFamiliar;
	}

	public Ciudad getCiudadVivienda() {
		return ciudadVivienda;
	}

	public void setCiudadVivienda(Ciudad ciudadVivienda) {
		this.ciudadVivienda = ciudadVivienda;
	}

	public Set<PacienteAntecedente> getAntecedentesPacientes() {
		return antecedentesPacientes;
	}

	public void setAntecedentesPacientes(
			Set<PacienteAntecedente> antecedentesPacientes) {
		this.antecedentesPacientes = antecedentesPacientes;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getNivelEducativo() {
		return nivelEducativo;
	}

	public void setNivelEducativo(String nivelEducativo) {
		this.nivelEducativo = nivelEducativo;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public String getRetiroIVSS() {
		return retiroIVSS;
	}

	public void setRetiroIVSS(String retiroIVSS) {
		this.retiroIVSS = retiroIVSS;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getNroInpsasel() {
		return NroInpsasel;
	}

	public void setNroInpsasel(String nroInpsasel) {
		NroInpsasel = nroInpsasel;
	}

	public Timestamp getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Timestamp fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Timestamp getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(Timestamp fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	public Timestamp getFechaInscripcionIVSS() {
		return fechaInscripcionIVSS;
	}

	public void setFechaInscripcionIVSS(Timestamp fechaInscripcionIVSS) {
		this.fechaInscripcionIVSS = fechaInscripcionIVSS;
	}

	public Integer getCarga() {
		return carga;
	}

	public void setCarga(Integer carga) {
		this.carga = carga;
	}

	public Historia getHistoria() {
		return historia;
	}

	public void setHistoria(Historia historia) {
		this.historia = historia;
	}

	public Cargo getCargoReal() {
		return cargoReal;
	}

	public void setCargoReal(Cargo cargoReal) {
		this.cargoReal = cargoReal;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Set<Informe> getInformesA() {
		return informesA;
	}

	public void setInformesA(Set<Informe> informesA) {
		this.informesA = informesA;
	}

	public Set<Informe> getInformesB() {
		return informesB;
	}

	public void setInformesB(Set<Informe> informesB) {
		this.informesB = informesB;
	}

	public Set<Informe> getInformesC() {
		return informesC;
	}

	public void setInformesC(Set<Informe> informesC) {
		this.informesC = informesC;
	}

	public Set<Informe> getInformesD() {
		return informesD;
	}

	public void setInformesD(Set<Informe> informesD) {
		this.informesD = informesD;
	}

	public Set<Informe> getInformesE() {
		return informesE;
	}

	public void setInformesE(Set<Informe> informesE) {
		this.informesE = informesE;
	}

	public Set<Informe> getInformeF() {
		return informeF;
	}

	public void setInformeF(Set<Informe> informeF) {
		this.informeF = informeF;
	}

	public Set<Informe> getInformesG() {
		return informesG;
	}

	public void setInformesG(Set<Informe> informesG) {
		this.informesG = informesG;
	}

	public Set<Informe> getInformesH() {
		return informesH;
	}

	public void setInformesH(Set<Informe> informesH) {
		this.informesH = informesH;
	}

	public Set<Informe> getInformesI() {
		return informesI;
	}

	public void setInformesI(Set<Informe> informesI) {
		this.informesI = informesI;
	}

	public Set<Informe> getInformesJ() {
		return informesJ;
	}

	public void setInformesJ(Set<Informe> informesJ) {
		this.informesJ = informesJ;
	}

	public Set<Informe> getInformesK() {
		return informesK;
	}

	public void setInformesK(Set<Informe> informesK) {
		this.informesK = informesK;
	}

	public Set<Informe> getInformesL() {
		return informesL;
	}

	public void setInformesL(Set<Informe> informesL) {
		this.informesL = informesL;
	}

	public Set<Informe> getInformesM() {
		return informesM;
	}

	public void setInformesM(Set<Informe> informesM) {
		this.informesM = informesM;
	}

	public Nomina getNomina() {
		return nomina;
	}

	public void setNomina(Nomina nomina) {
		this.nomina = nomina;
	}

	public boolean isEstatus() {
		return estatus;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}

	public String getObservacionEstatus() {
		return observacionEstatus;
	}

	public void setObservacionEstatus(String observacionEstatus) {
		this.observacionEstatus = observacionEstatus;
	}

	public boolean isMuerte() {
		return muerte;
	}

	public void setMuerte(boolean muerte) {
		this.muerte = muerte;
	}

	public Timestamp getFechaMuerte() {
		return fechaMuerte;
	}

	public void setFechaMuerte(Timestamp fechaMuerte) {
		this.fechaMuerte = fechaMuerte;
	}

	public Boolean getBrigadista() {
		return brigadista;
	}

	public void setBrigadista(Boolean brigadista) {
		this.brigadista = brigadista;
	}

	public Set<PacienteMedicina> getPacientesMedicinass() {
		return pacientesMedicinass;
	}

	public void setPacientesMedicinass(Set<PacienteMedicina> pacientesMedicinass) {
		this.pacientesMedicinass = pacientesMedicinass;
	}

	public Boolean getCronico() {
		return cronico;
	}

	public void setCronico(Boolean cronico) {
		this.cronico = cronico;
	}

	public Set<Orden> getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(Set<Orden> ordenes) {
		this.ordenes = ordenes;
	}

	public Set<PeriodoPaciente> getPacientesPeriodos() {
		return pacientesPeriodos;
	}

	public void setPacientesPeriodos(Set<PeriodoPaciente> pacientesPeriodos) {
		this.pacientesPeriodos = pacientesPeriodos;
	}

	public Set<ControlOrden> getControlOrden() {
		return controlOrden;
	}

	public void setControlOrden(Set<ControlOrden> controlOrden) {
		this.controlOrden = controlOrden;
	}

	public Set<ControlConsulta> getControlConsulta() {
		return controlConsulta;
	}

	public void setControlConsulta(Set<ControlConsulta> controlConsulta) {
		this.controlConsulta = controlConsulta;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}


	public boolean isAyuda() {
		return ayuda;
	}

	public void setAyuda(boolean ayuda) {
		this.ayuda = ayuda;
	}

	public boolean isEstudia() {
		return estudia;
	}

	public void setEstudia(boolean estudia) {
		this.estudia = estudia;
	}

	public boolean isVive() {
		return vive;
	}

	public void setVive(boolean vive) {
		this.vive = vive;
	}

	public String getRif() {
		return rif;
	}

	public void setRif(String rif) {
		this.rif = rif;
	}

	public String getOficio() {
		return oficio;
	}

	public void setOficio(String oficio) {
		this.oficio = oficio;
	}

	public String getCargoOCarrera() {
		return cargoOCarrera;
	}

	public void setCargoOCarrera(String cargoOCarrera) {
		this.cargoOCarrera = cargoOCarrera;
	}

	public String getLugarTrabajo() {
		return lugarTrabajo;
	}

	public void setLugarTrabajo(String lugarTrabajo) {
		this.lugarTrabajo = lugarTrabajo;
	}

	public String getDescripcionAyuda() {
		return descripcionAyuda;
	}

	public void setDescripcionAyuda(String descripcionAyuda) {
		this.descripcionAyuda = descripcionAyuda;
	}

	public String getNroCertificado() {
		return nroCertificado;
	}

	public void setNroCertificado(String nroCertificado) {
		this.nroCertificado = nroCertificado;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getTipoRif() {
		return tipoRif;
	}

	public void setTipoRif(String tipoRif) {
		this.tipoRif = tipoRif;
	}

	public String getCertificado() {
		return certificado;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public boolean isJefe() {
		return jefe;
	}

	public void setJefe(boolean jefe) {
		this.jefe = jefe;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getParroquia() {
		return parroquia;
	}

	public void setParroquia(String parroquia) {
		this.parroquia = parroquia;
	}

	public String getPuntoReferencia() {
		return puntoReferencia;
	}

	public void setPuntoReferencia(String puntoReferencia) {
		this.puntoReferencia = puntoReferencia;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getUrb() {
		return urb;
	}

	public void setUrb(String urb) {
		this.urb = urb;
	}

	public String getAvCalle() {
		return avCalle;
	}

	public void setAvCalle(String avCalle) {
		this.avCalle = avCalle;
	}

	public String getNro() {
		return nro;
	}

	public void setNro(String nro) {
		this.nro = nro;
	}

	public String getOtroTransporte() {
		return otroTransporte;
	}

	public void setOtroTransporte(String otroTransporte) {
		this.otroTransporte = otroTransporte;
	}


	public String getTransporte() {
		return transporte;
	}

	public void setTransporte(String transporte) {
		this.transporte = transporte;
	}

	public Ficha getFichaMaestra() {
		return fichaMaestra;
	}

	public void setFichaMaestra(Ficha fichaMaestra) {
		this.fichaMaestra = fichaMaestra;
	}

	public VisitaSocial getVisita() {
		return visita;
	}

	public void setVisita(VisitaSocial visita) {
		this.visita = visita;
	}

	public String getPasaporte() {
		return pasaporte;
	}

	public void setPasaporte(String pasaporte) {
		this.pasaporte = pasaporte;
	}

	public String getEmailEmpresa() {
		return emailEmpresa;
	}

	public void setEmailEmpresa(String emailEmpresa) {
		this.emailEmpresa = emailEmpresa;
	}

	public String getTelefonoAdicional() {
		return telefonoAdicional;
	}

	public void setTelefonoAdicional(String telefonoAdicional) {
		this.telefonoAdicional = telefonoAdicional;
	}

	public String getPeriodoEstudios() {
		return periodoEstudios;
	}

	public void setPeriodoEstudios(String periodoEstudios) {
		this.periodoEstudios = periodoEstudios;
	}

	public Boolean getJubilado() {
		return jubilado;
	}

	public void setJubilado(Boolean jubilado) {
		this.jubilado = jubilado;
	}

	public Boolean getRevision() {
		return revision;
	}

	public void setRevision(Boolean revision) {
		this.revision = revision;
	}

	public Timestamp getFechaAfiliacion() {
		return fechaAfiliacion;
	}

	public void setFechaAfiliacion(Timestamp fechaAfiliacion) {
		this.fechaAfiliacion = fechaAfiliacion;
	}
	

}