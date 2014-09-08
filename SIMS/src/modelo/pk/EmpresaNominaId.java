package modelo.pk;

import java.io.Serializable;

import modelo.maestros.Empresa;
import modelo.maestros.Nomina;

public class EmpresaNominaId implements Serializable {

	private static final long serialVersionUID = -7676649426279267591L;
	private Empresa empresa;
	private Nomina nomina;
	
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
}
