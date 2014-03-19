package servicio.maestros;

import java.util.List;

import interfacedao.maestros.IFormaTerapeuticaDAO;

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
}
