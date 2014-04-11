package servicio.transacciones;

import interfacedao.transacciones.IConsultaMedicinaDAO;

import java.util.List;

import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaMedicina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SConsultaMedicina")
public class SConsultaMedicina {

	@Autowired
	private IConsultaMedicinaDAO consultaMedicinaDAO;

	public List<ConsultaMedicina> buscarPorConsulta(Consulta consulta) {
		return consultaMedicinaDAO.findByConsulta(consulta);
	}

	public void borrarMedicinasDeConsulta(Consulta consulta) {
		List<ConsultaMedicina> lista = consultaMedicinaDAO.findByConsulta(consulta);
		if(!lista.isEmpty()){
			consultaMedicinaDAO.delete(lista);
		}
	}

	public void guardar(List<ConsultaMedicina> listaMedicina) {
		consultaMedicinaDAO.save(listaMedicina);
	}
}
