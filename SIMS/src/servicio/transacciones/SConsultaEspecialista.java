package servicio.transacciones;

import interfacedao.transacciones.IConsultaEspecialistaDAO;

import java.util.List;

import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaEspecialista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SConsultaEspecialista")
public class SConsultaEspecialista {

	@Autowired
	private IConsultaEspecialistaDAO consultaEspecialistaDAO;

	public List<ConsultaEspecialista> buscarPorConsulta(Consulta consulta) {
		return consultaEspecialistaDAO.findByConsulta(consulta);
	}
}
