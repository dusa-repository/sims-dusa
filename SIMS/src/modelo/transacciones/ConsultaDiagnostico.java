package modelo.transacciones;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import modelo.maestros.Accidente;
import modelo.maestros.Diagnostico;
import modelo.pk.ConsultaDiagnosticoId;

@Entity
@Table(name = "consulta_diagnostico", schema="dusa_sims.dbo")
@IdClass(ConsultaDiagnosticoId.class)
public class ConsultaDiagnostico {

	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_consulta", referencedColumnName = "id_consulta")
	private Consulta consulta;
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_diagnostico", referencedColumnName = "id_diagnostico")
	private Diagnostico diagnostico;
	
	@ManyToOne(optional = true,fetch=FetchType.LAZY)
	@JoinColumn(name="id_accidente",nullable=true)
	private Accidente accidente;
	
	@Column(length=100)
	private String tipo;
	
	@Column(length=100)
	private String observacion;

	@Column(name="lugar_accidente", length=100)
	private String lugar;
	
	@Column(name="motivo_accidente", length=100)
	private String motivo;
	
	@Column(name="clasificacion_accidente", length=100)
	private String clasificacion;
	
	@Column(name="fecha_accidente")
	private Timestamp fecha;
	
	public ConsultaDiagnostico() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConsultaDiagnostico(Consulta consulta, Diagnostico diagnostico, Accidente accidente, String tipo,
			String observacion, String lugar,String motivo,Timestamp fecha, String clasificacion) {
		super();
		this.accidente = accidente;
		this.consulta = consulta;
		this.diagnostico = diagnostico;
		this.observacion = observacion;
		this.tipo = tipo;
		this.lugar = lugar;
		this.motivo = motivo;
		this.fecha = fecha;
		this.clasificacion = clasificacion;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Diagnostico getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(Diagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public Accidente getAccidente() {
		return accidente;
	}

	public void setAccidente(Accidente accidente) {
		this.accidente = accidente;
	}

}
