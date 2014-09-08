package modelo.maestros;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import modelo.pk.EmpresaNominaId;

@Entity
@Table(name = "empresa_nomina", schema="dusa_sims.dbo")
@IdClass(EmpresaNominaId.class)
public class EmpresaNomina {
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")
	private Empresa empresa;

	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_nomina", referencedColumnName = "id_nomina")
	private Nomina nomina;
	
	@Column(name="cantidad_empleados")
	private Integer cantidad;

	public EmpresaNomina() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmpresaNomina(Empresa empresa, Nomina nomina, Integer cantidad) {
		super();
		this.empresa = empresa;
		this.nomina = nomina;
		this.cantidad = cantidad;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Nomina getNomina() {
		return nomina;
	}

	public void setNomina(Nomina nomina) {
		this.nomina = nomina;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}
