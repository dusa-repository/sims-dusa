package modelo.pk;

import java.io.Serializable;

import modelo.maestros.ServicioExterno;
import modelo.transacciones.Consulta;

public class ConsultaServicioExternoId implements Serializable {

	private static final long serialVersionUID = 1879449854802286297L;
	
	private Consulta consulta;
	private ServicioExterno servicioExterno;
	public Consulta getConsulta() {
		return consulta;
	}
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	public ServicioExterno getServicioExterno() {
		return servicioExterno;
	}
	public void setServicioExterno(ServicioExterno servicioExterno) {
		this.servicioExterno = servicioExterno;
	}
	
}
