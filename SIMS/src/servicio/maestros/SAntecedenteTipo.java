package servicio.maestros;

import java.util.List;

import interfacedao.maestros.IAntecedenteTipoDAO;

import modelo.maestros.AntecedenteTipo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SAntecedenteTipo")
public class SAntecedenteTipo {

	@Autowired
	private IAntecedenteTipoDAO	antecedenteTipoDAO;

	public void guardar(AntecedenteTipo antecedenteTipo) {
		antecedenteTipoDAO.save(antecedenteTipo);
		
	}

	public AntecedenteTipo buscar(long id) {
		return antecedenteTipoDAO.findOne(id);
	}

	public void eliminar(AntecedenteTipo antecedenteTipo) {
		antecedenteTipoDAO.delete(antecedenteTipo);
		
	}

	public List<AntecedenteTipo> buscarTodos() {
		return antecedenteTipoDAO.findAll();
	}

	public List<AntecedenteTipo> filtroNombre(String valor) {
		return antecedenteTipoDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public AntecedenteTipo buscarPorNombre(String value) {
		return antecedenteTipoDAO.findByNombre(value);
	}

	public List<AntecedenteTipo> filtroTipo(String valor) {
		return antecedenteTipoDAO.findByTipoStartingWithAllIgnoreCase(valor);
	}
}
