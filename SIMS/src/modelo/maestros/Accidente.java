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

import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaServicioExterno;
import modelo.transacciones.HistoriaAccidente;

@Entity
@Table(name="accidente")
public class Accidente implements Serializable {

	private static final long serialVersionUID = -5155885529457324266L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_accidente", unique=true, nullable=false)
	private long idAccidente;
	
	@Column(length=100)
	private String nombre;
	
	@Column(length=100)
	private String tipo;
	
	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name="hora_auditoria", length=10)
	private String horaAuditoria;

	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;

	@OneToMany(mappedBy = "accidente")
	private Set<HistoriaAccidente> historias;
	
	public Accidente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Accidente(long idAccidente, String nombre, String tipo,
			Timestamp fechaAuditoria, String horaAuditoria,
			String usuarioAuditoria) {
		super();
		this.idAccidente = idAccidente;
		this.nombre = nombre;
		this.tipo = tipo;
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

	public Set<HistoriaAccidente> getHistorias() {
		return historias;
	}

	public void setHistorias(Set<HistoriaAccidente> historias) {
		this.historias = historias;
	}
	
	
}
