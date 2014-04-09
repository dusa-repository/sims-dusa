package modelo.pk;

import java.io.Serializable;

import modelo.maestros.Especialista;
import modelo.transacciones.Consulta;

public class ConsultaEspecialistaId implements Serializable {

	private static final long serialVersionUID = 3062980809577726494L;
	
	private Consulta consulta;
	private Especialista especialista;
	public Consulta getConsulta() {
		return consulta;
	}
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	public Especialista getEspecialista() {
		return especialista;
	}
	public void setEspecialista(Especialista especialista) {
		this.especialista = especialista;
	}

}
