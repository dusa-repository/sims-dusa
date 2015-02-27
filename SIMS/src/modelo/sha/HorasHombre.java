package modelo.sha;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="horas_hombre", schema="dusa_sims.dbo")
public class HorasHombre implements Serializable {

	private static final long serialVersionUID = 2255744639671891059L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_horas_hombre", unique=true, nullable=false)
	private long idHorasHombre;
	
	@Column(length=100)
	private Double horas;

	@Column(name="fecha")
	private Timestamp fecha;
	
	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name="hora_auditoria", length=10)
	private String horaAuditoria;

	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;
	
	public HorasHombre() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HorasHombre(long idHorasHombre, Double horas, Timestamp fecha,
			Timestamp fechaAuditoria, String horaAuditoria,
			String usuarioAuditoria) {
		super();
		this.idHorasHombre = idHorasHombre;
		this.horas = horas;
		this.fecha = fecha;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public long getIdHorasHombre() {
		return idHorasHombre;
	}

	public void setIdHorasHombre(long idHorasHombre) {
		this.idHorasHombre = idHorasHombre;
	}

	public Double getHoras() {
		return horas;
	}

	public void setHoras(Double horas) {
		this.horas = horas;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
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