package modelo.seguridad;

import java.io.Serializable;
import javax.persistence.*;


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

	public Arbol() {
	}
	
	public Arbol(long idArbol, String nombre, long padre, String url) {
		super();
		this.idArbol = idArbol;
		this.nombre = nombre;
		this.padre = padre;
		this.url = url;
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

}