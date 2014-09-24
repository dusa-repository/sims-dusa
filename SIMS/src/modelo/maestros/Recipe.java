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

import modelo.transacciones.ConsultaMedicina;

@Entity
@Table(name="recipe", schema="dusa_sims.dbo")
public class Recipe implements Serializable {

	private static final long serialVersionUID = -9191855414562669785L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_recipe", unique=true, nullable=false)
	private long idRecipe;
	
	@Column(length=20)
	private String prioridad;
	
	@Column(length=7)
	private String tratamiento;
	
	@Column(name="validez")
	private Timestamp validez;
	
	@Column(name="fecha_auditoria")
	private Timestamp fechaAuditoria;

	@Column(name="hora_auditoria", length=10)
	private String horaAuditoria;

	@Column(name="usuario_auditoria", length=50)
	private String usuarioAuditoria;
	
	@OneToMany(mappedBy = "recipe")
	private Set<ConsultaMedicina> medicinas;

	public Recipe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Recipe(long idRecipe, String prioridad, Timestamp validez,
			Timestamp fechaAuditoria, String horaAuditoria,
			String usuarioAuditoria, String tratamiento) {
		super();
		this.idRecipe = idRecipe;
		this.tratamiento = tratamiento;
		this.prioridad = prioridad;
		this.validez = validez;
		this.fechaAuditoria = fechaAuditoria;
		this.horaAuditoria = horaAuditoria;
		this.usuarioAuditoria = usuarioAuditoria;
	}

	public long getIdRecipe() {
		return idRecipe;
	}

	public void setIdRecipe(long idRecipe) {
		this.idRecipe = idRecipe;
	}

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public Timestamp getValidez() {
		return validez;
	}

	public void setValidez(Timestamp validez) {
		this.validez = validez;
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

	public Set<ConsultaMedicina> getMedicinas() {
		return medicinas;
	}

	public void setMedicinas(Set<ConsultaMedicina> medicinas) {
		this.medicinas = medicinas;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}
	
	
}
