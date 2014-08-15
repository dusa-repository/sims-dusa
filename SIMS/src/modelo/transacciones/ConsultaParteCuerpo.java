package modelo.transacciones;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import modelo.maestros.ParteCuerpo;
import modelo.pk.ConsultaParteCuerpoId;

@Entity
@Table(name = "consulta_parte_cuerpo")
@IdClass(ConsultaParteCuerpoId.class)
public class ConsultaParteCuerpo {
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_consulta", referencedColumnName = "id_consulta")
	private Consulta consulta;
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_parte_cuerpo", referencedColumnName = "id_parte_cuerpo")
	private ParteCuerpo parte;
	
	@Column(length=100)
	private String observacion;

	public ConsultaParteCuerpo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConsultaParteCuerpo(Consulta consulta, ParteCuerpo parte,
			String observacion) {
		super();
		this.consulta = consulta;
		this.parte = parte;
		this.observacion = observacion;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public ParteCuerpo getParte() {
		return parte;
	}

	public void setParte(ParteCuerpo parte) {
		this.parte = parte;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	

}
