package modelo.transacciones;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import modelo.maestros.MotivoCita;
import modelo.maestros.Paciente;

@Entity
@Table(name = "orden", schema = "dusa_sims.dbo")
public class Orden implements Serializable {

	private static final long serialVersionUID = -9172174643327496611L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_orden", unique = true, nullable = false)
	private long idOrden;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;
	
	@Column(length=500)
	private String doctor;

	@Column(name = "fecha_orden")
	private Timestamp fechaOrden;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_motivo" ,referencedColumnName="id_motivo_cita")
	private MotivoCita motivo;

	@Column(name = "hora_auditoria", length = 10)
	private String horaAuditoria;

	@Column(name = "fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name = "usuario_auditoria", length = 50)
	private String usuarioAuditoria;
	
	@OneToMany(mappedBy = "orden")
	private Set<OrdenEspecialista> especialistas;
	
	@OneToMany(mappedBy = "orden")
	private Set<Consulta> consultas;

	@OneToMany(mappedBy = "orden")
	private Set<OrdenExamen> examenes;

	@OneToMany(mappedBy = "orden")
	private Set<OrdenMedicina> medicinas;

	@OneToMany(mappedBy = "orden")
	private Set<OrdenServicioExterno> servicios;	

	public Orden() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orden(long idOrden, Paciente paciente, String doctorcitofeoypeludo,
			Timestamp fechaOrden, MotivoCita motivo,
			String horaAuditoria, Timestamp fechaAuditoria,
			String usuarioAuditoria) {
		super();
		this.idOrden = idOrden;
		this.paciente = paciente;
		this.doctor = doctorcitofeoypeludo;
		this.fechaOrden = fechaOrden;
		this.motivo = motivo;
		this.horaAuditoria = horaAuditoria;
		this.fechaAuditoria = fechaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public long getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(long idOrden) {
		this.idOrden = idOrden;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Timestamp getFechaOrden() {
		return fechaOrden;
	}

	public void setFechaOrden(Timestamp fechaOrden) {
		this.fechaOrden = fechaOrden;
	}

	public MotivoCita getMotivo() {
		return motivo;
	}

	public void setMotivo(MotivoCita motivo) {
		this.motivo = motivo;
	}
	
	public String getHoraAuditoria() {
		return horaAuditoria;
	}

	public void setHoraAuditoria(String horaAuditoria) {
		this.horaAuditoria = horaAuditoria;
	}

	public Timestamp getFechaAuditoria() {
		return fechaAuditoria;
	}

	public void setFechaAuditoria(Timestamp fechaAuditoria) {
		this.fechaAuditoria = fechaAuditoria;
	}

	public String getUsuarioAuditoria() {
		return usuarioAuditoria;
	}

	public void setUsuarioAuditoria(String usuarioAuditoria) {
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public Set<OrdenEspecialista> getEspecialistas() {
		return especialistas;
	}

	public void setEspecialistas(Set<OrdenEspecialista> especialistas) {
		this.especialistas = especialistas;
	}

	public Set<OrdenExamen> getExamenes() {
		return examenes;
	}

	public void setExamenes(Set<OrdenExamen> examenes) {
		this.examenes = examenes;
	}

	public Set<OrdenMedicina> getMedicinas() {
		return medicinas;
	}

	public void setMedicinas(Set<OrdenMedicina> medicinas) {
		this.medicinas = medicinas;
	}

	public Set<OrdenServicioExterno> getServicios() {
		return servicios;
	}

	public void setServicios(Set<OrdenServicioExterno> servicios) {
		this.servicios = servicios;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	
	public Set<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(Set<Consulta> consultas) {
		this.consultas = consultas;
	}

	public String traerFecha() {
		DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		return String.valueOf(formatoFecha.format(fechaOrden));
	}

	
}
