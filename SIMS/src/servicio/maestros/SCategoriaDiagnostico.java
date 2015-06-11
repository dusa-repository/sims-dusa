package servicio.maestros;

import interfacedao.maestros.ICategoriaDiagnosticoDAO;

import java.util.List;

import modelo.maestros.CategoriaDiagnostico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SCategoriaDiagnostico")
public class SCategoriaDiagnostico {

	@Autowired
	private ICategoriaDiagnosticoDAO categoriaDAO;

	public void guardar(CategoriaDiagnostico categ) {
		categoriaDAO.save(categ);
	}

	public List<CategoriaDiagnostico> buscarTodas() {
		return categoriaDAO.findAll();
	}

	public CategoriaDiagnostico buscar(long id) {
		return categoriaDAO.findOne(id);
	}

	public void eliminar(CategoriaDiagnostico categoria) {
		categoriaDAO.delete(categoria);
	}

	public CategoriaDiagnostico buscarPorNombre(String value) {
		return categoriaDAO.findByNombre(value);
	}

	public List<CategoriaDiagnostico> filtroNombre(String valor) {
		return categoriaDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public List<CategoriaDiagnostico> buscarPorClasificacion(long id) {
		return categoriaDAO.findByClasificacionIdClasificacion(id);
	}
}
