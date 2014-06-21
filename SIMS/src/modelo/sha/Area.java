package modelo.sha;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="area")
public class Area implements Serializable {

	private static final long serialVersionUID = 2545006923709588765L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_area", unique=true, nullable=false)
	private long idArea;
	
	@Column(length=100)
	private String nombre;
	

	public Area() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Area(long idArea, String nombre) {
		super();
		this.idArea = idArea;
		this.nombre = nombre;
	}

	public long getIdArea() {
		return idArea;
	}

	public void setIdArea(long idArea) {
		this.idArea = idArea;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
}
