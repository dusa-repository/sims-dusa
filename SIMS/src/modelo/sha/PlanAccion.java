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
@Table(name = "plan_accion", schema="dusa_sims.dbo")
public class PlanAccion implements Serializable{

	private static final long serialVersionUID = -5760551755226792027L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_plan_accion", unique = true, nullable = false)
	private long idPlan;

	@Column(length = 1000)
	private String descripcion;

	@Column(length = 1000)
	private String quien;

	@Column(length = 1000)
	private String como;

	@Column(length = 1000)
	private String donde;

	@Column(length = 1000)
	private String cuando;

	@Column(name = "fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name = "hora_auditoria", length = 10)
	private String horaAuditoria;

	@Column(name = "usuario_auditoria", length = 50)
	private String usuarioAuditoria;
	
	@ManyToMany(mappedBy="plan")
	private Set<Informe> planes;

	public PlanAccion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlanAccion(long idPlan, String descripcion, String quien,
			String como, String donde, String cuando, Timestamp fechaAuditoria,
			String horaAuditoria, String usuarioAuditoria) {
		super();
		this.idPlan = idPlan;
		this.descripcion = descripcion;
		this.quien = quien;
		this.como = como;
		this.donde = donde;
		this.cuando = cuando;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public long getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(long idPlan) {
		this.idPlan = idPlan;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getQuien() {
		return quien;
	}

	public void setQuien(String quien) {
		this.quien = quien;
	}

	public String getComo() {
		return como;
	}

	public void setComo(String como) {
		this.como = como;
	}

	public String getDonde() {
		return donde;
	}

	public void setDonde(String donde) {
		this.donde = donde;
	}

	public String getCuando() {
		return cuando;
	}

	public void setCuando(String cuando) {
		this.cuando = cuando;
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

	public Set<Informe> getPlanes() {
		return planes;
	}

	public void setPlanes(Set<Informe> planes) {
		this.planes = planes;
	}
	
	
}
