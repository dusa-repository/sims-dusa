package servicio.sha;

import java.util.List;

import interfacedao.sha.IAreaDAO;
import interfacedao.sha.IClasificacionAccidenteDAO;

import modelo.sha.Area;
import modelo.sha.ClasificacionAccidente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SClasificacionAccidente")
public class SClasificacionAccidente {
	
	@Autowired
	private IClasificacionAccidenteDAO  clasificacionAccidenteDAO;

	public void guardar(ClasificacionAccidente clasificacionAccidente) {
		clasificacionAccidenteDAO.save(clasificacionAccidente);
		
	}

	public ClasificacionAccidente buscar(long id) {
		return clasificacionAccidenteDAO.findOne(id);
	}

	public void eliminar(ClasificacionAccidente clasificacionAccidente) {
		clasificacionAccidenteDAO.delete(clasificacionAccidente);
		
	}

	public List<ClasificacionAccidente> buscarTodos() {
		return clasificacionAccidenteDAO.findAllOrderByNombreAsc();
	}

	public List<ClasificacionAccidente> filtroNombre(String valor) {
		return  clasificacionAccidenteDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}
	public ClasificacionAccidente buscarPorNombre(String value) {
		return clasificacionAccidenteDAO.findByNombre(value);
	}

}
