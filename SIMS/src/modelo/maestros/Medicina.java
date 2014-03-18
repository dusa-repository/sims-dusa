package modelo.maestros;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the medicina database table.
 * 
 */
@Entity
@Table(name="medicina")
public class Medicina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_medicina", unique=true, nullable=false)
	private long idMedicina;

	@Column(length=1000)
	private String composicion;

	@Column(length=1000)
	private String contraindicaciones;

	@Column(name="denominacion_generica", length=1000)
	private String denominacionGenerica;

	@Column(length=1000)
	private String efectos;

	@Column(length=1000)
	private String embarazo;

	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(length=1000)
	private String indicaciones;

	@Column(length=1000)
	private String nombre;

	@Column(length=1000)
	private String posologia;

	@Column(length=1000)
	private String precaucion;

	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;

	//bi-directional many-to-one association to FormaTerapeutica
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_forma_terapeutica")
	private FormaTerapeutica formaTerapeutica;

	//bi-directional many-to-one association to Laboratorio
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_laboratorio")
	private Laboratorio laboratorio;

	//bi-directional many-to-one association to Presentacion
	@OneToMany(mappedBy="medicina")
	private Set<Presentacion> presentacions;

	public Medicina() {
	}

	public long getIdMedicina() {
		return this.idMedicina;
	}

	public void setIdMedicina(long idMedicina) {
		this.idMedicina = idMedicina;
	}

	public String getComposicion() {
		return this.composicion;
	}

	public void setComposicion(String composicion) {
		this.composicion = composicion;
	}

	public String getContraindicaciones() {
		return this.contraindicaciones;
	}

	public void setContraindicaciones(String contraindicaciones) {
		this.contraindicaciones = contraindicaciones;
	}

	public String getDenominacionGenerica() {
		return this.denominacionGenerica;
	}

	public void setDenominacionGenerica(String denominacionGenerica) {
		this.denominacionGenerica = denominacionGenerica;
	}

	public String getEfectos() {
		return this.efectos;
	}

	public void setEfectos(String efectos) {
		this.efectos = efectos;
	}

	public String getEmbarazo() {
		return this.embarazo;
	}

	public void setEmbarazo(String embarazo) {
		this.embarazo = embarazo;
	}

	public Timestamp getFechaAuditoria() {
		return this.fechaAuditoria;
	}

	public void setFechaAuditoria(Timestamp fechaAuditoria) {
		this.fechaAuditoria = fechaAuditoria;
	}

	public String getIndicaciones() {
		return this.indicaciones;
	}

	public void setIndicaciones(String indicaciones) {
		this.indicaciones = indicaciones;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPosologia() {
		return this.posologia;
	}

	public void setPosologia(String posologia) {
		this.posologia = posologia;
	}

	public String getPrecaucion() {
		return this.precaucion;
	}

	public void setPrecaucion(String precaucion) {
		this.precaucion = precaucion;
	}

	public String getUsuarioAuditoria() {
		return this.usuarioAuditoria;
	}

	public void setUsuarioAuditoria(String usuarioAuditoria) {
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public FormaTerapeutica getFormaTerapeutica() {
		return this.formaTerapeutica;
	}

	public void setFormaTerapeutica(FormaTerapeutica formaTerapeutica) {
		this.formaTerapeutica = formaTerapeutica;
	}

	public Laboratorio getLaboratorio() {
		return this.laboratorio;
	}

	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}

	public Set<Presentacion> getPresentacions() {
		return this.presentacions;
	}

	public void setPresentacions(Set<Presentacion> presentacions) {
		this.presentacions = presentacions;
	}

	public Presentacion addPresentacion(Presentacion presentacion) {
		getPresentacions().add(presentacion);
		presentacion.setMedicina(this);

		return presentacion;
	}

	public Presentacion removePresentacion(Presentacion presentacion) {
		getPresentacions().remove(presentacion);
		presentacion.setMedicina(null);

		return presentacion;
	}

}