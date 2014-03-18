package modelo.seguridad;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario", unique=true, nullable=false)
	private long idUsuario;

	@Column(length=8)
	private String cedula;

	@Column(length=500)
	private String direccion;

	@Column(length=50)
	private String email;

	private boolean estado;

	@Column(name="estado_usuario", length=50)
	private String estadoUsuario;

	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(length=50)
	private String ficha;

	@Column(name="hora_auditoria", length=10)
	private String horaAuditoria;

	@Column(name="id_especialidad")
	private long idEspecialidad;

	@Column(name="id_unidad")
	private long idUnidad;

	@Lob
	private byte[] imagen;

	@Column(name="licencia_cm", length=50)
	private String licenciaCm;

	@Column(name="licencia_inpsasel")
	private long licenciaInpsasel;

	@Column(name="licencia_msds", length=50)
	private String licenciaMsds;

	@Column(length=50)
	private String login;

	@Column(length=500)
	private String nombre;

	@Column(name="numero_citas_diarias")
	private long numeroCitasDiarias;

	@Column(length=256)
	private String password;

	@Column(length=1)
	private String sexo;

	@Column(length=50)
	private String telefono;

	@Column(name="tiempo_estimado_entre_citas")
	private long tiempoEstimadoEntreCitas;

	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;

	public Usuario() {
	}
	
	public Usuario(long idUsuario, String cedula, String direccion,
			String email, boolean estado, String estadoUsuario,
			Timestamp fechaAuditoria, String ficha, String horaAuditoria,
			long idEspecialidad, long idUnidad, byte[] imagen,
			String licenciaCm, long licenciaInpsasel, String licenciaMsds,
			String login, String nombre, long numeroCitasDiarias,
			String password, String sexo, String telefono,
			long tiempoEstimadoEntreCitas, String usuarioAuditoria) {
		super();
		this.idUsuario = idUsuario;
		this.cedula = cedula;
		this.direccion = direccion;
		this.email = email;
		this.estado = estado;
		this.estadoUsuario = estadoUsuario;
		this.fechaAuditoria = fechaAuditoria;
		this.ficha = ficha;
		this.horaAuditoria = horaAuditoria;
		this.idEspecialidad = idEspecialidad;
		this.idUnidad = idUnidad;
		this.imagen = imagen;
		this.licenciaCm = licenciaCm;
		this.licenciaInpsasel = licenciaInpsasel;
		this.licenciaMsds = licenciaMsds;
		this.login = login;
		this.nombre = nombre;
		this.numeroCitasDiarias = numeroCitasDiarias;
		this.password = password;
		this.sexo = sexo;
		this.telefono = telefono;
		this.tiempoEstimadoEntreCitas = tiempoEstimadoEntreCitas;
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public long getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getEstadoUsuario() {
		return this.estadoUsuario;
	}

	public void setEstadoUsuario(String estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}

	public Timestamp getFechaAuditoria() {
		return this.fechaAuditoria;
	}

	public void setFechaAuditoria(Timestamp fechaAuditoria) {
		this.fechaAuditoria = fechaAuditoria;
	}

	public String getFicha() {
		return this.ficha;
	}

	public void setFicha(String ficha) {
		this.ficha = ficha;
	}

	public String getHoraAuditoria() {
		return this.horaAuditoria;
	}

	public void setHoraAuditoria(String horaAuditoria) {
		this.horaAuditoria = horaAuditoria;
	}

	public long getIdEspecialidad() {
		return this.idEspecialidad;
	}

	public void setIdEspecialidad(long idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public long getIdUnidad() {
		return this.idUnidad;
	}

	public void setIdUnidad(long idUnidad) {
		this.idUnidad = idUnidad;
	}

	public byte[] getImagen() {
		return this.imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getLicenciaCm() {
		return this.licenciaCm;
	}

	public void setLicenciaCm(String licenciaCm) {
		this.licenciaCm = licenciaCm;
	}

	public long getLicenciaInpsasel() {
		return this.licenciaInpsasel;
	}

	public void setLicenciaInpsasel(long licenciaInpsasel) {
		this.licenciaInpsasel = licenciaInpsasel;
	}

	public String getLicenciaMsds() {
		return this.licenciaMsds;
	}

	public void setLicenciaMsds(String licenciaMsds) {
		this.licenciaMsds = licenciaMsds;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getNumeroCitasDiarias() {
		return this.numeroCitasDiarias;
	}

	public void setNumeroCitasDiarias(long numeroCitasDiarias) {
		this.numeroCitasDiarias = numeroCitasDiarias;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public long getTiempoEstimadoEntreCitas() {
		return this.tiempoEstimadoEntreCitas;
	}

	public void setTiempoEstimadoEntreCitas(long tiempoEstimadoEntreCitas) {
		this.tiempoEstimadoEntreCitas = tiempoEstimadoEntreCitas;
	}

	public String getUsuarioAuditoria() {
		return this.usuarioAuditoria;
	}

	public void setUsuarioAuditoria(String usuarioAuditoria) {
		this.usuarioAuditoria = usuarioAuditoria;
	}

}