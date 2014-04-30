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

import org.hibernate.annotations.Type;

import modelo.maestros.Accidente;
import modelo.maestros.AntecedenteTipo;
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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_accidente")
	private Accidente accidente;
	
	@Column(name = "fecha_consulta")
	private Timestamp fechaConsulta;

	@Column(length = 500)
	private String motivoConsulta;
	
	@Column(length = 500)
	private String enfermedadActual;
	
	@Column(length = 50)
	private String tipoConsulta;

	@Column(name = "hora_consulta", length = 10)
	private String horaConsulta;
	
	@Column(name = "hora_auditoria", length = 10)
	private String horaAuditoria;

	@Column(name = "fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name = "usuario_auditoria", length = 50)
	private String usuarioAuditoria;
	
	@Column
	@Type(type="org.hibernate.type.NumericBooleanType")
	private boolean accidenteLaboral;
	
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

	@Column(name = "consulta_asociada")
	private long consultaAsociada;
	
	public Consulta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Consulta(long idConsulta, Paciente paciente, Usuario usuario, Accidente accidente, Timestamp fechaConsulta, String horaConsulta,
			String horaAuditoria, Timestamp fechaAuditoria,
			String usuarioAuditoria, boolean accidenteLaboral, String motivo, String tipo, String enfermedad, long consultaAsociada) {
		super();
		this.idConsulta = idConsulta;
		this.paciente = paciente;
		this.fechaConsulta = fechaConsulta;
		this.horaConsulta = horaConsulta;
		this.horaAuditoria = horaAuditoria;
		this.fechaAuditoria = fechaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
		this.usuario = usuario;
		this.accidenteLaboral = accidenteLaboral;
		this.accidente = accidente;
		this.motivoConsulta = motivo;
		this.enfermedadActual = enfermedad;
		this.tipoConsulta = tipo;
		this.consultaAsociada = consultaAsociada;
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

	public String getHoraConsulta() {
		return horaConsulta;
	}

	public void setHoraConsulta(String horaConsulta) {
		this.horaConsulta = horaConsulta;
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

	public boolean isAccidenteLaboral() {
		return accidenteLaboral;
	}

	public void setAccidenteLaboral(boolean accidenteLaboral) {
		this.accidenteLaboral = accidenteLaboral;
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

	public Accidente getAccidente() {
		return accidente;
	}

	public void setAccidente(Accidente accidente) {
		this.accidente = accidente;
	}

	public String getMotivoConsulta() {
		return motivoConsulta;
	}

	public void setMotivoConsulta(String motivoConsulta) {
		this.motivoConsulta = motivoConsulta;
	}

	public String getEnfermedadActual() {
		return enfermedadActual;
	}

	public void setEnfermedadActual(String enfermedadActual) {
		this.enfermedadActual = enfermedadActual;
	}

	public String getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	public long getConsultaAsociada() {
		return consultaAsociada;
	}

	public void setConsultaAsociada(long consultaAsociada) {
		this.consultaAsociada = consultaAsociada;
	}
	
	
}
