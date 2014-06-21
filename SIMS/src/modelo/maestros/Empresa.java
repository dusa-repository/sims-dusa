package modelo.maestros;

import java.io.Serializable;
import javax.persistence.*;
import org.zkoss.zul.Textbox;

import java.sql.Timestamp;

/**
 * The persistent class for the empresa database table.
 * 
 */
@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empresa", unique = true, nullable = false)
	private long idEmpresa;

	@Column(length = 500)
	private String nombre;

	@Column(length = 500)
	private String razon;

	@Column(length = 20)
	private String rif;

	@Column(name = "direccion_centro", length = 1024)
	private String direccionCentro;

	@Column(name = "direccion_razon", length = 1024)
	private String direccionRazon;

	@Column(length = 20)
	private String nil;

	@Column(name = "nro_ivss", length = 20)
	private String nroIvss;

	@Column(name = "codigo_ciiu", length = 20)
	private String codigoCiiu;

	@Column(name = "actividad_economica", length = 1024)
	private String actividadEconomica;

	@Column(length = 20)
	private String telefono;

	@Column(length = 20)
	private String correo;

	@Column(name = "registro_mercantil", length = 1024)
	private String registroMercantil;
	
	@Column(name = "fecha_registro")
	private Timestamp fechaRegistro;
	
	@Column(name = "bajo_nro")
	private String bajoNroEmpresa;
	
	@Column(name = "tomo")
	private String tomoEmpresa;
	
	@Column(name = "representante", length = 500)
	private String representanteEmpresa;
	
	@Column(name = "cedula_representante", length = 20)
	private String cedulaRepresentante;
	
	@Column(name = "telefono_representante", length = 20)
	private String telefonoRepresentante;
	
	@Column(name = "cargo", length = 500)
	private String cargo;
	
	@Column(name = "fecha_actualizacion")
	private Timestamp fechaActualizacion;
	
	@Column(name = "bajo_nro_actul")
	private String bajoNro2Empresa;
	
	@Column(name = "tomo_actual")
	private String tomo2Empresa;
	
	@Column(name = "representante_actual", length = 500)
	private String representante2Empresa;
	
	@Column(name = "cedula_representante_actual", length = 20)
	private String cedula2Representante;
	
	@Column(name = "telefono_representante_actual", length = 20)
	private String telefono2Representante;
	
	@Column(name = "cargo_actual", length = 500)
	private String cargo2;
	
	@Column(name = "nro_trabajadores")
	private Integer nroTrabajadores;
	
	@Column(name = "hombres")
	private Integer hombres;
	
	@Column(name = "mujeres")
	private Integer mujeres;
	
	@Column(name = "adolescentes")
	private Integer adolescentes;
	
	@Column(name = "aprendices")
	private Integer aprendices;
	
	@Column(name = "lopcymat")
	private Integer lopcymat;
	
	@Column(name = "conapdis")
	private Integer conapdis;
	
	@Column(name = "extranjeros")
	private Integer extranjeros;

	@Column(name = "fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name = "hora_auditoria", length = 10)
	private String horaAuditoria;

	@Column(name = "usuario_auditoria", length = 50)
	private String usuarioAuditoria;

	public Empresa() {
	}

	public Empresa(long idEmpresa, String nombre, String rif,
			String direccionCentro,
			Timestamp fechaAuditoria, String horaAuditoria,
			String usuarioAuditoria) {
		super();
		this.idEmpresa = idEmpresa;
		this.nombre = nombre;
		this.rif = rif;
		this.direccionCentro = direccionCentro;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRazon() {
		return razon;
	}

	public void setRazon(String razon) {
		this.razon = razon;
	}

	public String getRif() {
		return rif;
	}

	public void setRif(String rif) {
		this.rif = rif;
	}

	public String getDireccionCentro() {
		return direccionCentro;
	}

	public void setDireccionCentro(String direccionCentro) {
		this.direccionCentro = direccionCentro;
	}

	public String getDireccionRazon() {
		return direccionRazon;
	}

	public void setDireccionRazon(String direccionRazon) {
		this.direccionRazon = direccionRazon;
	}

	public String getNil() {
		return nil;
	}

	public void setNil(String nil) {
		this.nil = nil;
	}

	public String getNroIvss() {
		return nroIvss;
	}

	public void setNroIvss(String nroIvss) {
		this.nroIvss = nroIvss;
	}

	public String getCodigoCiiu() {
		return codigoCiiu;
	}

	public void setCodigoCiiu(String codigoCiiu) {
		this.codigoCiiu = codigoCiiu;
	}

	public String getActividadEconomica() {
		return actividadEconomica;
	}

	public void setActividadEconomica(String actividadEconomica) {
		this.actividadEconomica = actividadEconomica;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getRegistroMercantil() {
		return registroMercantil;
	}

	public void setRegistroMercantil(String registroMercantil) {
		this.registroMercantil = registroMercantil;
	}

	public Timestamp getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getBajoNroEmpresa() {
		return bajoNroEmpresa;
	}

	public void setBajoNroEmpresa(String bajoNroEmpresa) {
		this.bajoNroEmpresa = bajoNroEmpresa;
	}

	public String getTomoEmpresa() {
		return tomoEmpresa;
	}

	public void setTomoEmpresa(String tomoEmpresa) {
		this.tomoEmpresa = tomoEmpresa;
	}

	public String getRepresentanteEmpresa() {
		return representanteEmpresa;
	}

	public void setRepresentanteEmpresa(String representanteEmpresa) {
		this.representanteEmpresa = representanteEmpresa;
	}

	public String getCedulaRepresentante() {
		return cedulaRepresentante;
	}

	public void setCedulaRepresentante(String cedulaRepresentante) {
		this.cedulaRepresentante = cedulaRepresentante;
	}

	public String getTelefonoRepresentante() {
		return telefonoRepresentante;
	}

	public void setTelefonoRepresentante(String telefonoRepresentante) {
		this.telefonoRepresentante = telefonoRepresentante;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Timestamp getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getBajoNro2Empresa() {
		return bajoNro2Empresa;
	}

	public void setBajoNro2Empresa(String bajoNro2Empresa) {
		this.bajoNro2Empresa = bajoNro2Empresa;
	}

	public String getTomo2Empresa() {
		return tomo2Empresa;
	}

	public void setTomo2Empresa(String tomo2Empresa) {
		this.tomo2Empresa = tomo2Empresa;
	}

	public String getRepresentante2Empresa() {
		return representante2Empresa;
	}

	public void setRepresentante2Empresa(String representante2Empresa) {
		this.representante2Empresa = representante2Empresa;
	}

	public String getCedula2Representante() {
		return cedula2Representante;
	}

	public void setCedula2Representante(String cedula2Representante) {
		this.cedula2Representante = cedula2Representante;
	}

	public String getTelefono2Representante() {
		return telefono2Representante;
	}

	public void setTelefono2Representante(String telefono2Representante) {
		this.telefono2Representante = telefono2Representante;
	}

	public String getCargo2() {
		return cargo2;
	}

	public void setCargo2(String cargo2) {
		this.cargo2 = cargo2;
	}

	public Integer getNroTrabajadores() {
		return nroTrabajadores;
	}

	public void setNroTrabajadores(Integer nroTrabajadores) {
		this.nroTrabajadores = nroTrabajadores;
	}

	public Integer getHombres() {
		return hombres;
	}

	public void setHombres(Integer hombres) {
		this.hombres = hombres;
	}

	public Integer getMujeres() {
		return mujeres;
	}

	public void setMujeres(Integer mujeres) {
		this.mujeres = mujeres;
	}

	public Integer getAdolescentes() {
		return adolescentes;
	}

	public void setAdolescentes(Integer adolescentes) {
		this.adolescentes = adolescentes;
	}

	public Integer getAprendices() {
		return aprendices;
	}

	public void setAprendices(Integer aprendices) {
		this.aprendices = aprendices;
	}

	public Integer getLopcymat() {
		return lopcymat;
	}

	public void setLopcymat(Integer lopcymat) {
		this.lopcymat = lopcymat;
	}

	public Integer getConapdis() {
		return conapdis;
	}

	public void setConapdis(Integer conapdis) {
		this.conapdis = conapdis;
	}

	public Integer getExtranjeros() {
		return extranjeros;
	}

	public void setExtranjeros(Integer extranjeros) {
		this.extranjeros = extranjeros;
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

}