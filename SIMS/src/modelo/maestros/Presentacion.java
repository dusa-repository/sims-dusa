package modelo.maestros;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the presentacion database table.
 * 
 */
@Entity
@Table(name="presentacion")
public class Presentacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_presentacion", unique=true, nullable=false, length=10)
	private String idPresentacion;

	@Column(length=10)
	private String nombre;

	//bi-directional many-to-one association to Medicina
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_medicina")
	private Medicina medicina;

	public Presentacion() {
	}

	public String getIdPresentacion() {
		return this.idPresentacion;
	}

	public void setIdPresentacion(String idPresentacion) {
		this.idPresentacion = idPresentacion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Medicina getMedicina() {
		return this.medicina;
	}

	public void setMedicina(Medicina medicina) {
		this.medicina = medicina;
	}

}