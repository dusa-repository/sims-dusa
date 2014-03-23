package modelo.maestros;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the ciudad database table.
 * 
 */
@Entity
@Table(name="ciudad")
public class Ciudad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ciudad", unique=true, nullable=false)
	private long idCiudad;

	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name="hora_auditoria", length=10)
	private String horaAuditoria;

	@Column(length=500)
	private String nombre;

	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;

	//bi-directional many-to-one association to Consultorio
	@OneToMany(mappedBy="ciudad")
	private Set<Consultorio> consultorios;

	//bi-directional many-to-one association to Empresa
	@OneToMany(mappedBy="ciudad")
	private Set<Empresa> empresas;

	//bi-directional many-to-one association to Estado
	@OneToMany(mappedBy="ciudad")
	private Set<Estado> estados;

	public Ciudad() {
	}
	
	
	public Ciudad(long idCiudad, Timestamp fechaAuditoria,
			String horaAuditoria, String nombre, String usuarioAuditoria) {
		super();
		this.idCiudad = idCiudad;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.nombre = nombre;
		this.usuarioAuditoria = usuarioAuditoria;
	}


	public long getIdCiudad() {
		return this.idCiudad;
	}

	public void setIdCiudad(long idCiudad) {
		this.idCiudad = idCiudad;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuarioAuditoria() {
		return this.usuarioAuditoria;
	}

	public void setUsuarioAuditoria(String usuarioAuditoria) {
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public Set<Consultorio> getConsultorios() {
		return this.consultorios;
	}

	public void setConsultorios(Set<Consultorio> consultorios) {
		this.consultorios = consultorios;
	}

	public Consultorio addConsultorio(Consultorio consultorio) {
		getConsultorios().add(consultorio);
		consultorio.setCiudad(this);

		return consultorio;
	}

	public Consultorio removeConsultorio(Consultorio consultorio) {
		getConsultorios().remove(consultorio);
		consultorio.setCiudad(null);

		return consultorio;
	}

	public Set<Empresa> getEmpresas() {
		return this.empresas;
	}

	public void setEmpresas(Set<Empresa> empresas) {
		this.empresas = empresas;
	}

	public Empresa addEmpresa(Empresa empresa) {
		getEmpresas().add(empresa);
		empresa.setCiudad(this);

		return empresa;
	}

	public Empresa removeEmpresa(Empresa empresa) {
		getEmpresas().remove(empresa);
		empresa.setCiudad(null);

		return empresa;
	}

	public Set<Estado> getEstados() {
		return this.estados;
	}

	public void setEstados(Set<Estado> estados) {
		this.estados = estados;
	}

	public Estado addEstado(Estado estado) {
		getEstados().add(estado);
		estado.setCiudad(this);

		return estado;
	}

	public Estado removeEstado(Estado estado) {
		getEstados().remove(estado);
		estado.setCiudad(null);

		return estado;
	}

}