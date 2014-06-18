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

import modelo.transacciones.ConsultaServicioExterno;
import modelo.transacciones.HistoriaVacuna;

@Entity
@Table(name="vacuna")
public class Vacuna implements Serializable {

	private static final long serialVersionUID = -1000385887270222127L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_vacuna", unique=true, nullable=false)
	private long idVacuna;
	
	@Column(length=500)
	private String nombre;
	
	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name="hora_auditoria", length=10)
	private String horaAuditoria;

	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;
	
	@OneToMany(mappedBy = "vacuna")
	private Set<HistoriaVacuna> historias;

	public Vacuna(long idVacuna, String nombre, Timestamp fechaAuditoria,
			String horaAuditoria, String usuarioAuditoria) {
		super();
		this.idVacuna = idVacuna;
		this.nombre = nombre;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public Vacuna() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getIdVacuna() {
		return idVacuna;
	}

	public void setIdVacuna(long idVacuna) {
		this.idVacuna = idVacuna;
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

	public Set<HistoriaVacuna> getHistorias() {
		return historias;
	}

	public void setHistorias(Set<HistoriaVacuna> historias) {
		this.historias = historias;
	}
	
	

}
