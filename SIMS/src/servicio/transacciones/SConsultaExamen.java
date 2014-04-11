package servicio.transacciones;

import interfacedao.transacciones.IConsultaExamenDAO;

import java.util.List;

import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaExamen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SConsultaExamen")
public class SConsultaExamen {

	@Autowired
	private IConsultaExamenDAO consultaExamenDAO;

	public List<ConsultaExamen> buscarPorConsulta(Consulta consulta) {
		return consultaExamenDAO.findByConsulta(consulta);
	}

	public void borrarExamenesDeConsulta(Consulta consulta) {
		List<ConsultaExamen> lista = consultaExamenDAO.findByConsulta(consulta);
		if(!lista.isEmpty()){
			consultaExamenDAO.delete(lista);
		}
	}

	public void guardar(List<ConsultaExamen> listaConsultaExamen) {
		consultaExamenDAO.save(listaConsultaExamen);
	}
}
