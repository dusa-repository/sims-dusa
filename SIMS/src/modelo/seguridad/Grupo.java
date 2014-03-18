package modelo.seguridad;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the grupo database table.
 * 
 */
@Entity
@Table(name="grupo")
public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_grupo", unique=true, nullable=false)
	private long idGrupo;

	private boolean estado;

	@Column(length=500)
	private String nombre;

	public Grupo() {
	}
	
	public Grupo(long idGrupo, boolean estado, String nombre) {
		super();
		this.idGrupo = idGrupo;
		this.estado = estado;
		this.nombre = nombre;
	}

	public long getIdGrupo() {
		return this.idGrupo;
	}

	public void setIdGrupo(long idGrupo) {
		this.idGrupo = idGrupo;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}