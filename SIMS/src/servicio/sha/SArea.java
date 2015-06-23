package servicio.sha;

import interfacedao.sha.IAreaDAO;

import java.util.List;

import modelo.sha.Area;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SArea")
public class SArea {

	@Autowired
	private IAreaDAO areaDAO;

	public void guardar(Area area) {
		areaDAO.save(area);		
	}

	public Area buscar(long id) {
		return areaDAO.findOne(id);
	}

	public void eliminar(Area area) {
		areaDAO.delete(area);	
	}

	public List<Area> buscarTodos() {
		return areaDAO.findAllOrderByNombreAsc();
	}

	public List<Area> filtroNombre(String valor) {
		return areaDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public Area buscarPorNombre(String value) {
		return areaDAO.findByNombre(value);
	}

	public Area buscarPorCodigo(String value) {
		return areaDAO.findByCodigo(value);
	}

	public void guardarVarios(List<Area> areas) {
		areaDAO.save(areas);
	}

	public List<Area> filtroCodigo(String valor) {
		return areaDAO.findByCodigoStartingWithAllIgnoreCase(valor);
	}
}
