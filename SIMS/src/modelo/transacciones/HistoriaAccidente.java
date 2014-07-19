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
import modelo.pk.HistoriaAccidenteId;

@Entity
@Table(name = "historia_accidente")
@IdClass(HistoriaAccidenteId.class)
public class HistoriaAccidente {
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_historia", referencedColumnName = "id_historia")
	private Historia historia;
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_accidente", referencedColumnName = "id_accidente")
	private Accidente accidente;
	
	@Column(name="fecha")
	private Timestamp fecha;
	
	@Column(length=100)
	private String lugar;
	
	@Column(length=100)
	private String tipoLesion;
	
	@Column(name="reposo")
	private int diasReposo;
	
	@Column(length=100)
	private String secuelas;
	
	@Column(length=100)
	private String tipoAccidente;

	public HistoriaAccidente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HistoriaAccidente(Historia historia, Accidente accidente,
			Timestamp fecha, String lugar, String tipoLesion, int diasReposo,
			String secuelas, String tipo) {
		super();
		this.historia = historia;
		this.accidente = accidente;
		this.fecha = fecha;
		this.lugar = lugar;
		this.tipoLesion = tipoLesion;
		this.diasReposo = diasReposo;
		this.secuelas = secuelas;
		this.tipoAccidente = tipo;
	}



	public Historia getHistoria() {
		return historia;
	}

	public void setHistoria(Historia historia) {
		this.historia = historia;
	}

	public Accidente getAccidente() {
		return accidente;
	}

	public void setAccidente(Accidente accidente) {
		this.accidente = accidente;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getTipoLesion() {
		return tipoLesion;
	}

	public void setTipoLesion(String tipoLesion) {
		this.tipoLesion = tipoLesion;
	}

	public int getDiasReposo() {
		return diasReposo;
	}

	public void setDiasReposo(int diasReposo) {
		this.diasReposo = diasReposo;
	}

	public String getSecuelas() {
		return secuelas;
	}

	public void setSecuelas(String secuelas) {
		this.secuelas = secuelas;
	}

	public String getTipoAccidente() {
		return tipoAccidente;
	}

	public void setTipoAccidente(String tipoAccidente) {
		this.tipoAccidente = tipoAccidente;
	}

	
}
