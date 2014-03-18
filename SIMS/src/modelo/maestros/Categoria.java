package modelo.maestros;

import java.io.Serializable;
import javax.persistence.*;


import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the categoria database table.
 * 
 */
@Entity
@Table(name="categoria")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_categoria", unique=true, nullable=false)
	private long idCategoria;

	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name="hora_auditoria", length=10)
	private String horaAuditoria;

	@Column(length=500)
	private String nombre;

	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;

	//bi-directional many-to-one association to Diagnostico
	@OneToMany(mappedBy="categoria")
	private Set<Diagnostico> diagnosticos;

	public Categoria() {
	}
	
	public Categoria(long idCategoria, Timestamp fechaAuditoria,
			String horaAuditoria, String nombre, String usuarioAuditoria) {
		super();
		this.idCategoria = idCategoria;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.nombre = nombre;
		this.usuarioAuditoria = usuarioAuditoria;
	}



	public long getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
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

	public Set<Diagnostico> getDiagnosticos() {
		return this.diagnosticos;
	}

	public void setDiagnosticos(Set<Diagnostico> diagnosticos) {
		this.diagnosticos = diagnosticos;
	}

	public Diagnostico addDiagnostico(Diagnostico diagnostico) {
		getDiagnosticos().add(diagnostico);
		diagnostico.setCategoria(this);

		return diagnostico;
	}

	public Diagnostico removeDiagnostico(Diagnostico diagnostico) {
		getDiagnosticos().remove(diagnostico);
		diagnostico.setCategoria(null);

		return diagnostico;
	}

}