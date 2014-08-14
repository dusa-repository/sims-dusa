package servicio.maestros;

import java.util.List;

import interfacedao.maestros.IIntervencionDAO;
import interfacedao.maestros.INominaDAO;

import modelo.maestros.Nomina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SNomina")
public class SNomina {
	
	@Autowired
	private INominaDAO nominaDAO;

	public void guardar(Nomina nomina) {
		nominaDAO.save(nomina);
		
	}

	public Nomina buscar(long id) {
		return nominaDAO.findByIdNomina(id);
	}

	public void eliminar(Nomina nomina) {
		nominaDAO.delete(nomina);
		
	}

	public List<Nomina> buscarTodos() {
		return nominaDAO.findAll();
	}

	public List<Nomina> filtroNombre(String valor) {
		return nominaDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public Nomina buscarPorNombre(String value) {
		return nominaDAO.findByNombre(value);
	}

}
