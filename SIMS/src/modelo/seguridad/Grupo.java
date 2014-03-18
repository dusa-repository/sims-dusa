package modelo.seguridad;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


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

	@Column(name="estado_grupo", length=500)
	private String estadoGrupo;

	@Column(length=500)
	private String nombre;

	//bi-directional many-to-many association to Arbol
	@ManyToMany(mappedBy="grupos")
	private Set<Arbol> arbols;

	//bi-directional many-to-many association to Usuario
	@ManyToMany
	@JoinTable(
		name="grupo_usuario"
		, joinColumns={
			@JoinColumn(name="id_grupo", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_usuario", nullable=false)
			}
		)
	private Set<Usuario> usuarios;

	public Grupo() {
	}

	public long getIdGrupo() {
		return this.idGrupo;
	}

	public void setIdGrupo(long idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getEstadoGrupo() {
		return this.estadoGrupo;
	}

	public void setEstadoGrupo(String estadoGrupo) {
		this.estadoGrupo = estadoGrupo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Arbol> getArbols() {
		return this.arbols;
	}

	public void setArbols(Set<Arbol> arbols) {
		this.arbols = arbols;
	}

	public Set<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}