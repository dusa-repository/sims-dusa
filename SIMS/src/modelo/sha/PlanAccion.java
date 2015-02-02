package modelo.sha;

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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "plan_accion", schema = "dusa_sims.dbo")
public class PlanAccion implements Serializable {

	private static final long serialVersionUID = -5760551755226792027L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_plan_accion", unique = true, nullable = false)
	private long idPlan;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_informe", nullable = false)
	private Informe informe;

	@Column(length = 1000)
	private String descripcion;

	@Column(length = 1000)
	private String quien;

	@Column(length = 1000)
	private String como;

	@Column(length = 1000)
	private String donde;

	@Column(name = "fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name = "cuando")
	private Timestamp cuando;

	@Column(name = "fecha_cumplido")
	private Timestamp fechaCumplido;

	@Column(length = 1000)
	private String observacion;

	@Column(length = 100)
	private String estado;

	@Column(name = "hora_auditoria", length = 10)
	private String horaAuditoria;

	@Column(name = "usuario_auditoria", length = 50)
	private String usuarioAuditoria;

	public PlanAccion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlanAccion(long idPlan, Informe informe, String descripcion,
			String quien, String como, String donde, Timestamp cuando,
			Timestamp fechaCumplido, String observacion, String estado,
			Timestamp fechaAuditoria, String horaAuditoria,
			String usuarioAuditoria) {
		super();
		this.idPlan = idPlan;
		this.descripcion = descripcion;
		this.quien = quien;
		this.como = como;
		this.donde = donde;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
		this.cuando = cuando;
		this.fechaCumplido = fechaCumplido;
		this.observacion = observacion;
		this.informe = informe;
		this.estado = estado;
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
	
	public Timestamp getCuando() {
		return cuando;
	}

	public void setCuando(Timestamp cuando) {
		this.cuando = cuando;
	}

	public Timestamp getFechaCumplido() {
		return fechaCumplido;
	}

	public void setFechaCumplido(Timestamp fechaCumplido) {
		this.fechaCumplido = fechaCumplido;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Informe getInforme() {
		return informe;
	}

	public void setInforme(Informe informe) {
		this.informe = informe;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String traerFechaCuando() {
		DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		return String.valueOf(formatoFecha.format(cuando));
	}

	public String traerFechaFinalizado() {
		DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		return String.valueOf(formatoFecha.format(fechaCumplido));
	}

}
