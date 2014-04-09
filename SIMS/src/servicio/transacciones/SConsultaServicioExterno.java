package servicio.transacciones;

import interfacedao.transacciones.IConsultaServicioExternoDAO;

import java.util.List;

import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaServicioExterno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SConsultaServicioExterno")
public class SConsultaServicioExterno {

	@Autowired
	private IConsultaServicioExternoDAO consultaServicioExternoDAO;

	public List<ConsultaServicioExterno> buscarPorConsulta(Consulta consulta) {
		return consultaServicioExternoDAO.findByConsulta(consulta);
	}
}
