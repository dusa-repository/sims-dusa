package servicio.maestros;

import interfacedao.maestros.IFormaTerapeuticaDAO;

import java.util.List;

import modelo.maestros.FormaTerapeutica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SFormaTerapeutica")
public class SFormaTerapeutica {

	@Autowired
	private IFormaTerapeuticaDAO formaTerapeuticaDAO;

	public List<FormaTerapeutica> buscarTodos() {
		return formaTerapeuticaDAO.findAll();
	}

	public void guardar(FormaTerapeutica formaTerapeutica) {
		formaTerapeuticaDAO.save(formaTerapeutica);
		
	}

	public FormaTerapeutica buscar(long idFormaTerapeutica) {

		return formaTerapeuticaDAO.findOne(idFormaTerapeutica);
	}

	public FormaTerapeutica buscarPorNombre(String value) {
		return formaTerapeuticaDAO.findByNombre(value);
	}

	public void eliminar(FormaTerapeutica formaTerapeutica) {
		formaTerapeuticaDAO.delete(formaTerapeutica);
		
	}

	public List<FormaTerapeutica> filtroNombre(String valor) {
		return formaTerapeuticaDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}
}
