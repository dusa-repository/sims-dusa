package servicio.sha;

import interfacedao.sha.ICondicionDAO;

import java.util.List;

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

	public List<Condicion> buscarPorInformeBYTipo(Informe informe, String string) {
		return condicionDAO.findByInformesBAndTipo(informe,string);
	}

	public List<Condicion> buscarPorInformeCYTipo(Informe informe, String string) {
		return condicionDAO.findByInformesCAndTipo(informe,string);
	}

	public List<Condicion> buscarPorInformeDYTipo(Informe informe, String string) {
		return condicionDAO.findByInformesDAndTipo(informe,string);
	}

	public List<Condicion> buscarPorInformeEYTipo(Informe informe, String string) {
		return condicionDAO.findByInformesEAndTipo(informe,string);
	}

	public List<Condicion> buscarPorInformeFYTipo(Informe informe, String string) {
		return condicionDAO.findByInformesFAndTipo(informe,string);
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
