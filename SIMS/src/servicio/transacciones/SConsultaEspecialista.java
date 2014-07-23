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

	public void borrarEspecialistasDeConsulta(Consulta consulta) {
		List<ConsultaEspecialista> lista = consultaEspecialistaDAO.findByConsulta(consulta);
		if(!lista.isEmpty()){
			consultaEspecialistaDAO.delete(lista);
		}
	}

	public void guardar(List<ConsultaEspecialista> listaConsultaEspecialista) {
		consultaEspecialistaDAO.save(listaConsultaEspecialista);
	}

	public ConsultaEspecialista buscarPorConsultaYIdEspecialista(
			Consulta consuta, String par3) {
		return consultaEspecialistaDAO.findByConsultaAndEspecialistaCedula(consuta, par3);
	}
}
