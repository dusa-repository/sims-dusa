package modelo.maestros;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the forma_terapeutica database table.
 * 
 */
@Entity
@Table(name="forma_terapeutica")
public class FormaTerapeutica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_forma_terapeutica", unique=true, nullable=false)
	private long idFormaTerapeutica;

	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(length=500)
	private String nombre;

	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;

	//bi-directional many-to-one association to Medicina
	@OneToMany(mappedBy="formaTerapeutica")
	private Set<Medicina> medicinas;

	public FormaTerapeutica() {
	}

	public long getIdFormaTerapeutica() {
		return this.idFormaTerapeutica;
	}

	public void setIdFormaTerapeutica(long idFormaTerapeutica) {
		this.idFormaTerapeutica = idFormaTerapeutica;
	}

	public Timestamp getFechaAuditoria() {
		return this.fechaAuditoria;
	}

	public void setFechaAuditoria(Timestamp fechaAuditoria) {
		this.fechaAuditoria = fechaAuditoria;
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

	public Set<Medicina> getMedicinas() {
		return this.medicinas;
	}

	public void setMedicinas(Set<Medicina> medicinas) {
		this.medicinas = medicinas;
	}

	public Medicina addMedicina(Medicina medicina) {
		getMedicinas().add(medicina);
		medicina.setFormaTerapeutica(this);

		return medicina;
	}

	public Medicina removeMedicina(Medicina medicina) {
		getMedicinas().remove(medicina);
		medicina.setFormaTerapeutica(null);

		return medicina;
	}

}