package modelo.pk;

import java.io.Serializable;

import modelo.maestros.ParteCuerpo;
import modelo.transacciones.Consulta;

public class ConsultaParteCuerpoId implements Serializable {

	private static final long serialVersionUID = -8300530232932850610L;
	private Consulta consulta;
	private ParteCuerpo parte;
	
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

}
