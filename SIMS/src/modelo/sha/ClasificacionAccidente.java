package modelo.sha;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="clasificacion_accidente")
public class ClasificacionAccidente implements Serializable {

	private static final long serialVersionUID = 2255744639671891059L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_clasificacion_accidente", unique=true, nullable=false)
	private long idClasificacionAccidente;
	
	@Column(length=100)
	private String nombre;
	
	@OneToMany(mappedBy = "clasificacion")
	private Set<Informe> informes;
	
	public ClasificacionAccidente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClasificacionAccidente(long idClasificacion, String nombre) {
		super();
		this.idClasificacionAccidente = idClasificacion;
		this.nombre = nombre;
	}

	public long getIdClasificacionAccidente() {
		return idClasificacionAccidente;
	}

	public void setIdClasificacionAccidente(long idClasificacionAccidente) {
		this.idClasificacionAccidente = idClasificacionAccidente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Informe> getInformes() {
		return informes;
	}

	public void setInformes(Set<Informe> informes) {
		this.informes = informes;
	}

	
	
}
