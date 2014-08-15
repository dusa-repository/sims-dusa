package servicio.maestros;

import interfacedao.maestros.ICategoriaMedicinaDAO;

import java.util.List;

import modelo.maestros.CategoriaMedicina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SCategoriaMedicina")
public class SCategoriaMedicina {

	@Autowired
	private ICategoriaMedicinaDAO categoriaMedicinaDAO;

	public void guardar(CategoriaMedicina categoriaMedicina) {
		categoriaMedicinaDAO.save(categoriaMedicina);
	}

	public CategoriaMedicina buscar(long id) {
		return categoriaMedicinaDAO.findOne(id);
	}

	public void eliminar(CategoriaMedicina categoriaMedicina) {
		categoriaMedicinaDAO.delete(categoriaMedicina);
	}

	public List<CategoriaMedicina> buscarTodas() {
		return categoriaMedicinaDAO.findAll();
	}

	public List<CategoriaMedicina> filtroNombre(String valor) {
		return categoriaMedicinaDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public CategoriaMedicina buscarPorNombre(String value) {
		return categoriaMedicinaDAO.findByNombre(value);
	}
}
