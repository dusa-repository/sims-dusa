package modelo.maestros;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import modelo.transacciones.ConsultaExamen;

@Entity
@Table(name="examen", schema="dusa_sims.dbo")
public class Examen implements Serializable {

	private static final long serialVersionUID = 5312629168777116428L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_examen", unique=true, nullable=false)
	private long idExamen;
	
	@Column(length=50)
	private String nombre;
	
	@Column(length=50)
	private String tipo;
	
	@Column(length=500)
	private String resultado;
	
	@Column(name = "costo")
	private double costo;
	
	@Column(name = "minimo")
	private double minimo;
	
	@Column(name = "maximo")
	private double maximo;
	
	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name="hora_auditoria", length=10)
	private String horaAuditoria;

	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;

	@OneToMany(mappedBy = "examen")
	private Set<ConsultaExamen> examenes;
	
	@OneToMany(mappedBy = "examen")
	private Set<ProveedorExamen> proveedoresExamenes;
	
	@Column(name = "id_referencia")
	private Long idReferencia;
	public Examen() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Examen(long idExamen, String nombre, String tipo, String resultado,
			double costo, double minimo, double maximo,
			Timestamp fechaAuditoria, String horaAuditoria,
			String usuarioAuditoria) {
		super();
		this.idExamen = idExamen;
		this.nombre = nombre;
		this.tipo = tipo;
		this.resultado = resultado;
		this.costo = costo;
		this.minimo = minimo;
		this.maximo = maximo;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public long getIdExamen() {
		return idExamen;
	}

	public void setIdExamen(long idExamen) {
		this.idExamen = idExamen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public double getMinimo() {
		return minimo;
	}

	public void setMinimo(double minimo) {
		this.minimo = minimo;
	}

	public double getMaximo() {
		return maximo;
	}

	public void setMaximo(double maximo) {
		this.maximo = maximo;
	}

	public Timestamp getFechaAuditoria() {
		return fechaAuditoria;
	}

	public void setFechaAuditoria(Timestamp fechaAuditoria) {
		this.fechaAuditoria = fechaAuditoria;
	}

	public String getHoraAuditoria() {
		return horaAuditoria;
	}

	public void setHoraAuditoria(String horaAuditoria) {
		this.horaAuditoria = horaAuditoria;
	}

	public String getUsuarioAuditoria() {
		return usuarioAuditoria;
	}

	public void setUsuarioAuditoria(String usuarioAuditoria) {
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public Set<ConsultaExamen> getExamenes() {
		return examenes;
	}

	public void setExamenes(Set<ConsultaExamen> examenes) {
		this.examenes = examenes;
	}

	public Set<ProveedorExamen> getProveedoresExamenes() {
		return proveedoresExamenes;
	}

	public void setProveedoresExamenes(Set<ProveedorExamen> proveedoresExamenes) {
		this.proveedoresExamenes = proveedoresExamenes;
	}

	public Long getIdReferencia() {
		return idReferencia;
	}

	public void setIdReferencia(Long idReferencia) {
		this.idReferencia = idReferencia;
	}
	
}
