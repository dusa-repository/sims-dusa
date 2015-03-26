package modelo.maestros;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
@Entity
@Table(name = "familiar", schema = "dusa_sims.dbo")
public class Familiar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_familiar", length = 15, unique = true, nullable = false)
	private String cedula;

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
	private boolean discapacidad;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean estatus;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean muerte;

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

	/* Datos en caso de ser familiar */

	@Column(length = 15)
	private String cedulaFamiliar;

	@Column(length = 15)
	private String parentescoFamiliar;

	//
	@Column(length = 15)
	private String nacionalidad;

	@Column
	private String profesion;

	@Column(name = "fecha_muerte")
	private Timestamp fechaMuerte;

	@Column(name = "fecha_ingreso")
	private Timestamp fechaIngreso;

	// bi-directional many-to-one association to Usuario
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_ciudad")
	private Ciudad ciudadVivienda;
	
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
	private String oficio;
	
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
	
	public Familiar() {
	}

	public Familiar(String cedula, String primerApellido, String primerNombre,
			String segundoApellido, String segundoNombre, boolean discapacidad,
			boolean estatus, boolean muerte, Timestamp fechaNacimiento,
			String lugarNacimiento, String observacionEstatus, String sexo,
			EstadoCivil estadoCivil, Integer edad, String origenDiscapacidad,
			String tipoDiscapacidad, String observacionDiscapacidad,
			Timestamp fechaAuditoria, String horaAuditoria,
			String usuarioAuditoria, byte[] imagen, String direccion,
			String email, String telefono1, String telefono2,
			String cedulaFamiliar, String parentescoFamiliar,
			String nacionalidad, String profesion, Timestamp fechaMuerte,
			Timestamp fechaIngreso, Ciudad ciudadVivienda, boolean ayuda,
			boolean estudia, boolean vive, String rif, String oficio,
			String cargoOCarrera, String lugarTrabajo, String descripcionAyuda,
			String nroCertificado, String observacion, String tipoRif,
			String certificado) {
		super();
		this.cedula = cedula;
		this.primerApellido = primerApellido;
		this.primerNombre = primerNombre;
		this.segundoApellido = segundoApellido;
		this.segundoNombre = segundoNombre;
		this.discapacidad = discapacidad;
		this.estatus = estatus;
		this.muerte = muerte;
		this.fechaNacimiento = fechaNacimiento;
		this.lugarNacimiento = lugarNacimiento;
		this.observacionEstatus = observacionEstatus;
		this.sexo = sexo;
		this.estadoCivil = estadoCivil;
		this.edad = edad;
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
		this.cedulaFamiliar = cedulaFamiliar;
		this.parentescoFamiliar = parentescoFamiliar;
		this.nacionalidad = nacionalidad;
		this.profesion = profesion;
		this.fechaMuerte = fechaMuerte;
		this.fechaIngreso = fechaIngreso;
		this.ciudadVivienda = ciudadVivienda;
		this.ayuda = ayuda;
		this.estudia = estudia;
		this.vive = vive;
		this.rif = rif;
		this.oficio = oficio;
		this.cargoOCarrera = cargoOCarrera;
		this.lugarTrabajo = lugarTrabajo;
		this.descripcionAyuda = descripcionAyuda;
		this.nroCertificado = nroCertificado;
		this.observacion = observacion;
		this.tipoRif = tipoRif;
		this.certificado = certificado;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
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

	public boolean isDiscapacidad() {
		return discapacidad;
	}

	public void setDiscapacidad(boolean discapacidad) {
		this.discapacidad = discapacidad;
	}

	public boolean isEstatus() {
		return estatus;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}

	public boolean isMuerte() {
		return muerte;
	}

	public void setMuerte(boolean muerte) {
		this.muerte = muerte;
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

	public String getObservacionEstatus() {
		return observacionEstatus;
	}

	public void setObservacionEstatus(String observacionEstatus) {
		this.observacionEstatus = observacionEstatus;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public EstadoCivil getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
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

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
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

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public Timestamp getFechaMuerte() {
		return fechaMuerte;
	}

	public void setFechaMuerte(Timestamp fechaMuerte) {
		this.fechaMuerte = fechaMuerte;
	}

	public Timestamp getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Timestamp fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Ciudad getCiudadVivienda() {
		return ciudadVivienda;
	}

	public void setCiudadVivienda(Ciudad ciudadVivienda) {
		this.ciudadVivienda = ciudadVivienda;
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
	
	
}
