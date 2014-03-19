package servicio.maestros;

import java.util.List;

import interfacedao.maestros.IMedicinaDAO;

import modelo.maestros.Medicina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SMedicina")
public class SMedicina {

	@Autowired
	private IMedicinaDAO medicinaDAO;

	public Medicina buscar(long idMedicina) {
	
		return medicinaDAO.findOne(idMedicina);
	}

	public List<Medicina> buscarTodas() {
		return medicinaDAO.findAll();
	}

	public void guardar(Medicina medicina) {
		medicinaDAO.save(medicina);		
	}

	public Medicina buscarPorNombre(String value) {
		return medicinaDAO.findByNombre(value);
	}
}
