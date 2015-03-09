package servicio.maestros;

import interfacedao.maestros.IEstadoCivilDAO;

import java.util.List;

import modelo.maestros.EstadoCivil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SEstadoCivil")
public class SEstadoCivil {

	@Autowired
	private IEstadoCivilDAO estadoCivilDAO;
	
	public void guardar(EstadoCivil categ) {
		estadoCivilDAO.save(categ);
	}

	public List<EstadoCivil> buscarTodas() {
		return estadoCivilDAO.findAll();
	}

	public EstadoCivil buscar(long id) {
		return estadoCivilDAO.findOne(id);
	}

	public void eliminar(EstadoCivil categoria) {
		estadoCivilDAO.delete(categoria);
	}

	public List<EstadoCivil> filtroNombre(String valor) {
		return estadoCivilDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public EstadoCivil buscarPorNombre(String value) {
		return estadoCivilDAO.findByNombre(value);
	}

}
