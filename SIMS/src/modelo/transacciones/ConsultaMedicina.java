package modelo.transacciones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import modelo.maestros.Medicina;
import modelo.maestros.Recipe;
import modelo.pk.ConsultaMedicinaId;

@Entity
@Table(name = "consulta_medicina", schema="dusa_sims.dbo")
@IdClass(ConsultaMedicinaId.class)
public class ConsultaMedicina {

	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_consulta", referencedColumnName = "id_consulta")
	private Consulta consulta;
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_medicina", referencedColumnName = "id_medicina")
	private Medicina medicina;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_recipe", referencedColumnName = "id_recipe")
	private Recipe recipe;
	
	@Column(length=1000)
	private String dosis;

	public ConsultaMedicina() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConsultaMedicina(Consulta consulta, Medicina medicina, String dosis, Recipe recipe) {
		super();
		this.consulta = consulta;
		this.medicina = medicina;
		this.dosis = dosis;
		this.recipe = recipe;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Medicina getMedicina() {
		return medicina;
	}

	public void setMedicina(Medicina medicina) {
		this.medicina = medicina;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public String getDosis() {
		return dosis;
	}

	public void setDosis(String dosis) {
		this.dosis = dosis;
	}
	
}
