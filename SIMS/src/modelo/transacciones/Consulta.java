package modelo.transacciones;

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

import modelo.maestros.Paciente;
import modelo.seguridad.Usuario;

@Entity
@Table(name = "consulta")
public class Consulta implements Serializable {

	private static final long serialVersionUID = -5405587652859401694L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_consulta", unique = true, nullable = false)
	private long idConsulta;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@Column(name = "fecha_consulta")
	private Timestamp fechaConsulta;

	@Column(length = 500)
	private String observacion;

	@Column(name = "hora_auditoria", length = 10)
	private String horaAuditoria;

	@Column(name = "fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name = "usuario_auditoria", length = 50)
	private String usuarioAuditoria;
	
	@OneToMany(mappedBy = "consulta")
	private Set<ConsultaDiagnostico> diagnosticos;
	
	@OneToMany(mappedBy = "consulta")
	private Set<ConsultaEspecialista> especialistas;
	
	@OneToMany(mappedBy = "consulta")
	private Set<ConsultaExamen> examenes;
	
	@OneToMany(mappedBy = "consulta")
	private Set<ConsultaMedicina> medicinas;
	
	@OneToMany(mappedBy = "consulta")
	private Set<ConsultaServicioExterno> servicios;

	public Consulta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Consulta(long idConsulta, Paciente paciente, Usuario usuario, Timestamp fechaConsulta,
			String observacion, String horaAuditoria, Timestamp fechaAuditoria,
			String usuarioAuditoria) {
		super();
		this.idConsulta = idConsulta;
		this.paciente = paciente;
		this.fechaConsulta = fechaConsulta;
		this.observacion = observacion;
		this.horaAuditoria = horaAuditoria;
		this.fechaAuditoria = fechaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
		this.usuario = usuario;
	}

	

	public long getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(long idConsulta) {
		this.idConsulta = idConsulta;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Timestamp getFechaConsulta() {
		return fechaConsulta;
	}

	public void setFechaConsulta(Timestamp fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
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

	public Set<ConsultaDiagnostico> getDiagnosticos() {
		return diagnosticos;
	}

	public void setDiagnosticos(Set<ConsultaDiagnostico> diagnosticos) {
		this.diagnosticos = diagnosticos;
	}

	public Set<ConsultaEspecialista> getEspecialistas() {
		return especialistas;
	}

	public void setEspecialistas(Set<ConsultaEspecialista> especialistas) {
		this.especialistas = especialistas;
	}

	public Set<ConsultaExamen> getExamenes() {
		return examenes;
	}

	public void setExamenes(Set<ConsultaExamen> examenes) {
		this.examenes = examenes;
	}

	public Set<ConsultaMedicina> getMedicinas() {
		return medicinas;
	}

	public void setMedicinas(Set<ConsultaMedicina> medicinas) {
		this.medicinas = medicinas;
	}

	public Set<ConsultaServicioExterno> getServicios() {
		return servicios;
	}

	public void setServicios(Set<ConsultaServicioExterno> servicios) {
		this.servicios = servicios;
	}
	
	
}
