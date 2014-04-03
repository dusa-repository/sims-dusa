package servicio.maestros;

import java.util.List;

import interfacedao.maestros.IExamenDAO;

import modelo.maestros.Examen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SExamen")
public class SExamen {

	@Autowired
	private IExamenDAO examenDAO;

	public void guardar(Examen examen) {
		examenDAO.save(examen);
	}

	public Examen buscar(long id) {
		return examenDAO.findOne(id);
	}

	public void eliminar(Examen examen) {
		examenDAO.delete(examen);
	}

	public List<Examen> buscarTodos() {
		return examenDAO.findAll();
	}

	public List<Examen> filtroNombre(String valor) {
		return examenDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Examen> filtroTipo(String valor) {
		return examenDAO.findByTipoStartingWithAllIgnoreCase(valor);
	}

	public List<Examen> filtroResultado(String valor) {
		return examenDAO.findByResultadoStartingWithAllIgnoreCase(valor);
	}

	public List<Examen> filtroCosto(String valor) {
		return examenDAO.findByCostoStartingWithAllIgnoreCase(valor);
	}

	public List<Examen> filtroMinimo(String valor) {
		return examenDAO.findByMinimoStartingWithAllIgnoreCase(valor);
	}

	public List<Examen> filtroMaximo(String valor) {
		return examenDAO.findByMaximoStartingWithAllIgnoreCase(valor);
	}

	public Examen buscarPorNombre(String value) {
		return examenDAO.findByNombre(value);
	}
}
