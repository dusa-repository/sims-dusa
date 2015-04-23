package modelo.social;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import modelo.maestros.Paciente;

@Entity
@Table(name = "historia", schema = "dusa_sims.dbo")
public class Ficha implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ficha", unique = true, nullable = false)
	private long idFicha;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
	private Paciente paciente;
	
	@Column(name = "talla_camisa", length = 5)
	private String tallaCamisa;
	
	@Column(name = "talla_pantalon", length = 5)
	private String tallaPantalon;
	
	@Column(name = "talla_botas", length = 5)
	private String tallaBotas;
	
	@Column(name = "talla_goma", length = 5)
	private String tallaGoma;

	public Ficha() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getIdFicha() {
		return idFicha;
	}

	public void setIdFicha(long idFicha) {
		this.idFicha = idFicha;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getTallaCamisa() {
		return tallaCamisa;
	}

	public void setTallaCamisa(String tallaCamisa) {
		this.tallaCamisa = tallaCamisa;
	}

	public String getTallaPantalon() {
		return tallaPantalon;
	}

	public void setTallaPantalon(String tallaPantalon) {
		this.tallaPantalon = tallaPantalon;
	}

	public String getTallaBotas() {
		return tallaBotas;
	}

	public void setTallaBotas(String tallaBotas) {
		this.tallaBotas = tallaBotas;
	}

	public String getTallaGoma() {
		return tallaGoma;
	}

	public void setTallaGoma(String tallaGoma) {
		this.tallaGoma = tallaGoma;
	}

}
