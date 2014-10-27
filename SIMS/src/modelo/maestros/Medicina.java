package modelo.maestros;

import java.io.Serializable;
import java.sql.Timestamp;
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

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import servicio.inventario.SF4101;
import servicio.inventario.SF41021;
import modelo.inventario.F4101;
import modelo.inventario.F41021;
import modelo.inventario.F41021PK;
import modelo.transacciones.ConsultaMedicina;
import modelo.transacciones.PacienteMedicina;

/**
 * The persistent class for the medicina database table.
 * 
 */
@Entity
@Table(name = "medicina", schema = "dusa_sims.dbo")
public class Medicina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_medicina", unique = true, nullable = false)
	private long idMedicina;

	@Column(length = 1000)
	private String composicion;

	@Column(length = 1000)
	private String contraindicaciones;

	@Column(name = "denominacion_generica", length = 1000)
	private String denominacionGenerica;

	@Column(length = 1000)
	private String efectos;

	@Column(length = 1000)
	private String embarazo;

	@Column(name = "fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name = "hora_auditoria", length = 10)
	private String horaAuditoria;

	@Column(length = 1000)
	private String indicaciones;

	@Column(length = 500)
	private String nombre;

	@Column(length = 1000)
	private String posologia;

	@Column(length = 1000)
	private String precaucion;

	@Column(name = "usuario_auditoria", length = 50)
	private String usuarioAuditoria;

	// bi-directional many-to-one association to Laboratorio
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_laboratorio")
	private Laboratorio laboratorio;

	// bi-directional many-to-one association to Presentacion
	@OneToMany(mappedBy = "medicina")
	private Set<PresentacionComercial> presentacions;

	@OneToMany(mappedBy = "medicina")
	private Set<MedicinaPresentacionUnidad> medicinasPresentacion;

	@OneToMany(mappedBy = "medicina")
	private Set<PacienteMedicina> pacientesMedicinas;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria_medicina")
	private CategoriaMedicina categoriaMedicina;

	@OneToMany(mappedBy = "medicina")
	private Set<ConsultaMedicina> medicinas;

	@Column(name = "id_referencia")
	private Long idReferencia;

	public Medicina() {
	}

	public Medicina(long idMedicina, String composicion,
			String contraindicaciones, String denominacionGenerica,
			String efectos, String embarazo, Timestamp fechaAuditoria,
			String horaAuditoria, String indicaciones, String nombre,
			String posologia, String precaucion, String usuarioAuditoria,
			Laboratorio laboratorio, CategoriaMedicina categoriaMedicina) {
		super();
		this.idMedicina = idMedicina;
		this.composicion = composicion;
		this.contraindicaciones = contraindicaciones;
		this.denominacionGenerica = denominacionGenerica;
		this.efectos = efectos;
		this.embarazo = embarazo;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.indicaciones = indicaciones;
		this.nombre = nombre;
		this.posologia = posologia;
		this.precaucion = precaucion;
		this.usuarioAuditoria = usuarioAuditoria;
		this.laboratorio = laboratorio;
		this.categoriaMedicina = categoriaMedicina;
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

	public String getHoraAuditoria() {
		return this.horaAuditoria;
	}

	public void setHoraAuditoria(String horaAuditoria) {
		this.horaAuditoria = horaAuditoria;
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

	public Laboratorio getLaboratorio() {
		return this.laboratorio;
	}

	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}

	public Set<PresentacionComercial> getPresentacions() {
		return this.presentacions;
	}

	public void setPresentacions(Set<PresentacionComercial> presentacions) {
		this.presentacions = presentacions;
	}

	public Set<MedicinaPresentacionUnidad> getMedicinasPresentacion() {
		return medicinasPresentacion;
	}

	public void setMedicinasPresentacion(
			Set<MedicinaPresentacionUnidad> medicinasPresentacion) {
		this.medicinasPresentacion = medicinasPresentacion;
	}

	public CategoriaMedicina getCategoriaMedicina() {
		return categoriaMedicina;
	}

	public void setCategoriaMedicina(CategoriaMedicina categoriaMedicina) {
		this.categoriaMedicina = categoriaMedicina;
	}

	public Set<ConsultaMedicina> getMedicinas() {
		return medicinas;
	}

	public void setMedicinas(Set<ConsultaMedicina> medicinas) {
		this.medicinas = medicinas;
	}

	public PresentacionComercial addPresentacion(
			PresentacionComercial presentacion) {
		getPresentacions().add(presentacion);
		presentacion.setMedicina(this);

		return presentacion;
	}

	public PresentacionComercial removePresentacion(
			PresentacionComercial presentacion) {
		getPresentacions().remove(presentacion);
		presentacion.setMedicina(null);

		return presentacion;
	}

	public Long getIdReferencia() {
		return idReferencia;
	}

	public void setIdReferencia(Long idReferencia) {
		this.idReferencia = idReferencia;
	}

	private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"/META-INF/ConfiguracionAplicacion.xml");

	public static SF41021 getServicioF41021() {
		return applicationContext.getBean(SF41021.class);
	}

	public static SF4101 getServicioF4101() {
		return applicationContext.getBean(SF4101.class);
	}

	public long inventario() {
		Long valor = idReferencia;
		F4101 objeto = getServicioF4101().buscarPorReferencia(valor);
		if (objeto != null) {
			F41021PK claveSaldo = new F41021PK();
			claveSaldo.setLilocn("LOC001");
			claveSaldo.setLiitm(objeto.getImitm());
			claveSaldo.setLilotn("");
			claveSaldo.setLimcu("Planta");
			F41021 f41021 = getServicioF41021().buscar(claveSaldo);
			if (f41021 != null)
				return f41021.getLipqoh().longValue();
			else
				return 0;
		} else
			return 0;
	}

	public Set<PacienteMedicina> getPacientesMedicinas() {
		return pacientesMedicinas;
	}

	public void setPacientesMedicinas(Set<PacienteMedicina> pacientesMedicinas) {
		this.pacientesMedicinas = pacientesMedicinas;
	}

}