package modelo.social;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


@Entity
@Table(name = "composicion_familiar", schema = "dusa_sims.dbo")
public class ComposicionFamiliar implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_composicion", unique = true, nullable = false)
	private long idComposicion;

	@Column(length = 500)
	private String nombre;
	
	@Column(length = 100)
	private String parentesco;
	
	@Column(length = 100)
	private String sexo;
	
	@Column(name = "fecha_nacimiento")
	private Timestamp fechaNacimiento;
	
	@Column(length = 500)
	private String discapacidad;
	
	@Column(length = 1500)
	private String enfermedades;
	
	@Column(name = "nivel_educativo",length = 500)
	private String nivelEducativo;
	
	@Column(name = "ultimo_grado",length = 500)
	private String ultimoGrado;
	
	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean estudia;
	
	@Column(name = "cedula", length = 15)
	private String cedula;
	
	@Column(length = 500)
	private String dedica;
	
	@Column(length = 500)
	private String oficio;
	
	@Column(name = "lugar_trabajo",length = 1500)
	private String lugarTrabajo;
	
	@Column(length = 1500)
	private String misiones;
	
	@Column(length = 1500)
	private String vacunacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_visita", referencedColumnName = "id_visita")
	private VisitaSocial visita;

	public ComposicionFamiliar() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ComposicionFamiliar(long idComposicion, String nombre, String sexo,
			Timestamp fechaNacimiento, String discapacidad,
			String enfermedades, String nivelEducativo, String ultimoGrado,
			boolean estudia, String cedula, String dedica, String oficio,
			String lugarTrabajo, String misiones, String vacunacion) {
		super();
		this.idComposicion = idComposicion;
		this.nombre = nombre;
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
		this.discapacidad = discapacidad;
		this.enfermedades = enfermedades;
		this.nivelEducativo = nivelEducativo;
		this.ultimoGrado = ultimoGrado;
		this.estudia = estudia;
		this.cedula = cedula;
		this.dedica = dedica;
		this.oficio = oficio;
		this.lugarTrabajo = lugarTrabajo;
		this.misiones = misiones;
		this.vacunacion = vacunacion;
	}




	public long getIdComposicion() {
		return idComposicion;
	}

	public void setIdComposicion(long idComposicion) {
		this.idComposicion = idComposicion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Timestamp getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Timestamp fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDiscapacidad() {
		return discapacidad;
	}

	public void setDiscapacidad(String discapacidad) {
		this.discapacidad = discapacidad;
	}

	public String getEnfermedades() {
		return enfermedades;
	}

	public void setEnfermedades(String enfermedades) {
		this.enfermedades = enfermedades;
	}

	public String getNivelEducativo() {
		return nivelEducativo;
	}

	public void setNivelEducativo(String nivelEducativo) {
		this.nivelEducativo = nivelEducativo;
	}

	public String getUltimoGrado() {
		return ultimoGrado;
	}

	public void setUltimoGrado(String ultimoGrado) {
		this.ultimoGrado = ultimoGrado;
	}

	public boolean isEstudia() {
		return estudia;
	}

	public void setEstudia(boolean estudia) {
		this.estudia = estudia;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getDedica() {
		return dedica;
	}

	public void setDedica(String dedica) {
		this.dedica = dedica;
	}

	public String getOficio() {
		return oficio;
	}

	public void setOficio(String oficio) {
		this.oficio = oficio;
	}

	public String getLugarTrabajo() {
		return lugarTrabajo;
	}

	public void setLugarTrabajo(String lugarTrabajo) {
		this.lugarTrabajo = lugarTrabajo;
	}

	public String getMisiones() {
		return misiones;
	}

	public void setMisiones(String misiones) {
		this.misiones = misiones;
	}

	public String getVacunacion() {
		return vacunacion;
	}

	public void setVacunacion(String vacunacion) {
		this.vacunacion = vacunacion;
	}

	public VisitaSocial getVisita() {
		return visita;
	}

	public void setVisita(VisitaSocial visita) {
		this.visita = visita;
	}

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}
	
	
}
