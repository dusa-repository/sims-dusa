package servicio.maestros;

import interfacedao.maestros.IMotivoCitaDAO;

import java.util.List;

import modelo.maestros.MotivoCita;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SMotivoCita")
public class SMotivoCita {

	@Autowired
	private IMotivoCitaDAO motivoCitaDAO;

	public MotivoCita buscar(long id) {
		return motivoCitaDAO.findOne(id);
	}

	public void eliminar(MotivoCita motivoCita) {
		motivoCitaDAO.delete(motivoCita);
		
	}
	
	public List<MotivoCita> buscarTodos() {
		return motivoCitaDAO.findAll();
	}

	public List<MotivoCita> filtroDescripcion(String valor) {
		return motivoCitaDAO.findByDescripcionStartingWithAllIgnoreCase(valor);
	}

	public MotivoCita buscarPorDescripcion(String value) {
		return motivoCitaDAO.findByDescripcion(value);
	}

	public void guardar(MotivoCita motivoCita) {
		motivoCitaDAO.save(motivoCita);
		
	}


}
