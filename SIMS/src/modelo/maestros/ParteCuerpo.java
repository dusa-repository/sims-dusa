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

import modelo.transacciones.ConsultaParteCuerpo;

@Entity
@Table(name="parte_cuerpo", schema="dusa_sims.dbo")
public class ParteCuerpo implements Serializable {

	private static final long serialVersionUID = -3594152739178826679L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_parte_cuerpo", unique=true, nullable=false)
	private long idParte;

	@Column(length=500)
	private String nombre;
	
	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name="hora_auditoria", length=10)
	private String horaAuditoria;

	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;
	
	@OneToMany(mappedBy = "parte")
	private Set<ConsultaParteCuerpo> partes;

	public ParteCuerpo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ParteCuerpo(long idParte, String nombre, Timestamp fechaAuditoria,
			String horaAuditoria, String usuarioAuditoria) {
		super();
		this.idParte = idParte;
		this.nombre = nombre;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public long getIdParte() {
		return idParte;
	}

	public void setIdParte(long idParte) {
		this.idParte = idParte;
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

	public Set<ConsultaParteCuerpo> getPartes() {
		return partes;
	}

	public void setPartes(Set<ConsultaParteCuerpo> partes) {
		this.partes = partes;
	}
	
}
