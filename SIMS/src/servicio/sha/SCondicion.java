package servicio.sha;

import java.util.List;

import interfacedao.sha.ICondicionDAO;

import modelo.sha.Condicion;
import modelo.sha.Informe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SCondicion")
public class SCondicion {

	@Autowired
	private ICondicionDAO condicionDAO;

	public List<Condicion> buscarTodos() {
		return condicionDAO.findAllOrderByTipo();
	}

	public List<Condicion> buscarPorInformeYTipo(Informe informe, String string) {
		return condicionDAO.findByInformesAAndTipo(informe,string);
	}

	public List<Condicion> buscarPorTipo(String string) {
		return condicionDAO.findByTipo(string);
	}

	public void guardar(Condicion condicion) {
		condicionDAO.save(condicion);	
	}

	public Condicion buscar(long id) {
		return condicionDAO.findByIdCondicion(id);
	}

	public void eliminar(Condicion condicion) {
		condicionDAO.delete(condicion);	
	}

	public List<Condicion> filtroNombre(String valor) {
		return  condicionDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Condicion> filtroTipo(String valor) {
		return  condicionDAO.findByTipoStartingWithAllIgnoreCase(valor);
	}

	public Condicion buscarPorNombre(String value) {
		return  condicionDAO.findByNombre(value);
	}
}
