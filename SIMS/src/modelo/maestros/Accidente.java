package modelo.maestros;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import modelo.sha.ClasificacionAccidente;
import modelo.transacciones.ConsultaDiagnostico;
import modelo.transacciones.HistoriaAccidente;

@Entity
@Table(name = "accidente", schema="dusa_sims.dbo")
public class Accidente implements Serializable {

	private static final long serialVersionUID = -5155885529457324266L;

	@Id
	@Column(name = "id_accidente", unique = true, nullable = false)
	private long idAccidente;

	@Column(length = 100)
	private String nombre;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_clasificacion_accidente")
	private ClasificacionAccidente clasificacion;

	@Column(name = "fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name = "hora_auditoria", length = 10)
	private String horaAuditoria;

	@Column(name = "usuario_auditoria", length = 50)
	private String usuarioAuditoria;

	@OneToMany(mappedBy = "accidente")
	private Set<HistoriaAccidente> historias;
	
	@OneToMany(mappedBy = "accidente")
	private Set<ConsultaDiagnostico> diagnosticos;

	public Accidente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Accidente(long idAccidente, ClasificacionAccidente cla, String nombre, Timestamp fechaAuditoria,
			String horaAuditoria, String usuarioAuditoria) {
		super();
		this.clasificacion = cla;
		this.idAccidente = idAccidente;
		this.nombre = nombre;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public long getIdAccidente() {
		return idAccidente;
	}

	public void setIdAccidente(long idAccidente) {
		this.idAccidente = idAccidente;
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

	public Set<HistoriaAccidente> getHistorias() {
		return historias;
	}

	public void setHistorias(Set<HistoriaAccidente> historias) {
		this.historias = historias;
	}

	public Set<ConsultaDiagnostico> getDiagnosticos() {
		return diagnosticos;
	}

	public void setDiagnosticos(Set<ConsultaDiagnostico> diagnosticos) {
		this.diagnosticos = diagnosticos;
	}

	public ClasificacionAccidente getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(ClasificacionAccidente clasificacion) {
		this.clasificacion = clasificacion;
	}

}
