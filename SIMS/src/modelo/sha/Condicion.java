package modelo.sha;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "condicion", schema="dusa_sims.dbo")
public class Condicion implements Serializable {

	private static final long serialVersionUID = -88415048395258025L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_condicion", unique = true, nullable = false)
	private long idCondicion;

	@Column(length = 100)
	private String nombre;

	@Column
	private String tipo;

	@Column(name = "fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name = "hora_auditoria", length = 10)
	private String horaAuditoria;

	@Column(name = "usuario_auditoria", length = 50)
	private String usuarioAuditoria;
	
	@ManyToMany(mappedBy="condicionA")
	private Set<Informe> informesA;
	
	@ManyToMany(mappedBy="condicionB")
	private Set<Informe> informesB;
	
	@ManyToMany(mappedBy="condicionC")
	private Set<Informe> informesC;
	
	@ManyToMany(mappedBy="condicionD")
	private Set<Informe> informesD;
	
	@ManyToMany(mappedBy="condicionE")
	private Set<Informe> informesE;
	
	@ManyToMany(mappedBy="condicionF")
	private Set<Informe> informesF;

	public Condicion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Condicion(long idCondicion, String nombre, String tipo,
			Timestamp fechaAuditoria, String horaAuditoria,
			String usuarioAuditoria) {
		super();
		this.idCondicion = idCondicion;
		this.nombre = nombre;
		this.tipo = tipo;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public long getIdCondicion() {
		return idCondicion;
	}

	public void setIdCondicion(long idCondicion) {
		this.idCondicion = idCondicion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public Set<Informe> getInformesF() {
		return informesF;
	}

	public void setInformesF(Set<Informe> informesF) {
		this.informesF = informesF;
	}
	
	
}
