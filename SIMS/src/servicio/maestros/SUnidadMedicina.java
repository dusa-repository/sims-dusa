package servicio.maestros;

import java.util.List;

import interfacedao.maestros.IUnidadMedicinaDAO;

import modelo.maestros.UnidadMedicina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SUnidadMedicina")
public class SUnidadMedicina {

	@Autowired
	private IUnidadMedicinaDAO unidadMedicinaDAO;

	public void guardar(UnidadMedicina unidadMedicina) {
		unidadMedicinaDAO.save(unidadMedicina);
	}

	public UnidadMedicina buscar(long id) {
		return unidadMedicinaDAO.findOne(id);
	}

	public void eliminar(UnidadMedicina unidadMedicina) {
		unidadMedicinaDAO.delete(unidadMedicina);
	}

	public List<UnidadMedicina> buscarTodas() {
		return unidadMedicinaDAO.findAll();
	}

	public List<UnidadMedicina> filtroNombre(String valor) {
		return unidadMedicinaDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public UnidadMedicina buscarPorNombre(String value) {
		return unidadMedicinaDAO.findByNombre(value);
	}
}
