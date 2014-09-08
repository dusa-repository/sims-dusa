package modelo.maestros;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import modelo.transacciones.HistoriaIntervencion;

@Entity
@Table(name="intervencion", schema="dusa_sims.dbo")
public class Intervencion implements Serializable {

	private static final long serialVersionUID = 2784782575138871908L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_intervencion", unique=true, nullable=false)
	private long idIntervencion;
	
	@Column(length=500)
	private String nombre;
	
	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name="hora_auditoria", length=10)
	private String horaAuditoria;

	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;
	
	@OneToMany(mappedBy = "intervencion")
	private Set<HistoriaIntervencion> historias;

	public Intervencion(long idIntervencion, String nombre,
			Timestamp fechaAuditoria, String horaAuditoria,
			String usuarioAuditoria) {
		super();
		this.idIntervencion = idIntervencion;
		this.nombre = nombre;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public Intervencion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getIdIntervencion() {
		return idIntervencion;
	}

	public void setIdIntervencion(long idIntervencion) {
		this.idIntervencion = idIntervencion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public Set<HistoriaIntervencion> getHistorias() {
		return historias;
	}

	public void setHistorias(Set<HistoriaIntervencion> historias) {
		this.historias = historias;
	}
	
	
	
}
