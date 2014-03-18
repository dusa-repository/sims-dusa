package modelo.maestros;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the presentacion database table.
 * 
 */
@Entity
@Table(name="presentacion")
public class Presentacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_presentacion", unique=true, nullable=false)
	private long idPresentacion;

	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name="hora_auditoria", length=10)
	private String horaAuditoria;

	@Column(length=500)
	private String nombre;

	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;

	//bi-directional many-to-one association to Medicina
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_medicina")
	private Medicina medicina;

	public Presentacion() {
	}
	
	public Presentacion(long idPresentacion, Timestamp fechaAuditoria,
			String horaAuditoria, String nombre, String usuarioAuditoria,
			Medicina medicina) {
		super();
		this.idPresentacion = idPresentacion;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.nombre = nombre;
		this.usuarioAuditoria = usuarioAuditoria;
		this.medicina = medicina;
	}



	public long getIdPresentacion() {
		return this.idPresentacion;
	}

	public void setIdPresentacion(long idPresentacion) {
		this.idPresentacion = idPresentacion;
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

	public Medicina getMedicina() {
		return this.medicina;
	}

	public void setMedicina(Medicina medicina) {
		this.medicina = medicina;
	}

}