package modelo.seguridad;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the arbol database table.
 * 
 */
@Entity
@Table(name="arbol")
public class Arbol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_arbol", unique=true, nullable=false)
	private long idArbol;

	@Column(length=500)
	private String nombre;

	private long padre;

	@Column(length=500)
	private String url;

	//bi-directional many-to-many association to Grupo
	@ManyToMany
	@JoinTable(
		name="arbol_grupo"
		, joinColumns={
			@JoinColumn(name="id_arbol", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_grupo", nullable=false)
			}
		)
	private Set<Grupo> grupos;

	public Arbol() {
	}

	public long getIdArbol() {
		return this.idArbol;
	}

	public void setIdArbol(long idArbol) {
		this.idArbol = idArbol;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getPadre() {
		return this.padre;
	}

	public void setPadre(long padre) {
		this.padre = padre;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<Grupo> getGrupos() {
		return this.grupos;
	}

	public void setGrupos(Set<Grupo> grupos) {
		this.grupos = grupos;
	}

}