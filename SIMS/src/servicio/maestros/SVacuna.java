package servicio.maestros;

import interfacedao.maestros.IVacunaDAO;

import java.util.List;

import modelo.maestros.Vacuna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SVacuna")
public class SVacuna {

	@Autowired
	private IVacunaDAO vacunaDAO;

	public List<Vacuna> buscarTodos() {
		return vacunaDAO.findAll();
	}

	public void guardar(Vacuna vacuna) {
		vacunaDAO.saveAndFlush(vacuna);
	}

	public Vacuna buscar(long id) {
		return vacunaDAO.findOne(id);
	}

	public void eliminar(Vacuna vacuna) {
		vacunaDAO.delete(vacuna);
	}

	public List<Vacuna> filtroNombre(String valor) {
		return vacunaDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public Vacuna buscarPorNombre(String value) {
		return vacunaDAO.findByNombre(value);
	}

	public Vacuna buscarUltimo() {
		long id = vacunaDAO.findMaxIdDiagnostico();
		if (id != 0)
			return vacunaDAO.findOne(id);
		return null;
	}
}
