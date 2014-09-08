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

@Entity
@Table(name="categoria_medicina", schema="dusa_sims.dbo")
public class CategoriaMedicina implements Serializable {

	
	private static final long serialVersionUID = 886955756424862318L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_categoria_medicina", unique=true, nullable=false)
	private long idCategoriaMedicina;
	
	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name="hora_auditoria", length=10)
	private String horaAuditoria;

	@Column(length=500)
	private String nombre;

	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;

	@OneToMany(mappedBy="categoriaMedicina")
	private Set<Medicina> medicinas;

	public CategoriaMedicina() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoriaMedicina(long idCategoriaMedicina,
			Timestamp fechaAuditoria, String horaAuditoria, String nombre,
			String usuarioAuditoria) {
		super();
		this.idCategoriaMedicina = idCategoriaMedicina;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.nombre = nombre;
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public long getIdCategoriaMedicina() {
		return idCategoriaMedicina;
	}

	public void setIdCategoriaMedicina(long idCategoriaMedicina) {
		this.idCategoriaMedicina = idCategoriaMedicina;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuarioAuditoria() {
		return usuarioAuditoria;
	}

	public void setUsuarioAuditoria(String usuarioAuditoria) {
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public Set<Medicina> getMedicinas() {
		return medicinas;
	}

	public void setMedicinas(Set<Medicina> medicinas) {
		this.medicinas = medicinas;
	}

}
