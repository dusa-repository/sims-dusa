package modelo.maestros;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the laboratorio database table.
 * 
 */
@Entity
@Table(name="laboratorio")
public class Laboratorio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_laboratorio", unique=true, nullable=false)
	private long idLaboratorio;

	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(length=500)
	private String nombre;

	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;

	//bi-directional many-to-one association to Medicina
	@OneToMany(mappedBy="laboratorio")
	private Set<Medicina> medicinas;

	public Laboratorio() {
	}

	public long getIdLaboratorio() {
		return this.idLaboratorio;
	}

	public void setIdLaboratorio(long idLaboratorio) {
		this.idLaboratorio = idLaboratorio;
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
		medicina.setLaboratorio(this);

		return medicina;
	}

	public Medicina removeMedicina(Medicina medicina) {
		getMedicinas().remove(medicina);
		medicina.setLaboratorio(null);

		return medicina;
	}

}