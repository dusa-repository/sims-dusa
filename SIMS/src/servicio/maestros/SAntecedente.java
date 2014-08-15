package servicio.maestros;

import interfacedao.maestros.IAntecedenteDAO;

import java.util.List;

import modelo.maestros.Antecedente;
import modelo.maestros.AntecedenteTipo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SAntecedente")
public class SAntecedente {

	@Autowired
	private IAntecedenteDAO antecedenteDAO;

	public List<Antecedente> buscarLaborales() {
		return antecedenteDAO
				.findByAntecedenteTipoTipoOrderByAntecedenteTipoNombreAsc("Laboral");
	}

	public List<Antecedente> buscarMedicos() {
		return antecedenteDAO
				.findByAntecedenteTipoTipoOrderByAntecedenteTipoNombreAsc("Medico");
	}
	
	public List<Antecedente> buscarFamiliares() {
		return antecedenteDAO
				.findByAntecedenteTipoTipoOrderByAntecedenteTipoNombreAsc("Familiar");
	}

	public void guardar(Antecedente antecedente) {
		antecedenteDAO.save(antecedente);

	}

	public Antecedente buscar(long id) {
		return antecedenteDAO.findOne(id);
	}

	public List<Antecedente> buscarTodos() {
		return antecedenteDAO.findAll();
	}

	public List<Antecedente> filtroNombre(String valor) {
		return antecedenteDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Antecedente> filtroAntecedenteTipo(String valor) {
		return antecedenteDAO
				.findByAntecedenteTipoNombreStartingWithAllIgnoreCase(valor);
	}

	public Antecedente buscarPorNombre(String value) {
		return antecedenteDAO.findByNombre(value);
	}

	public List<Antecedente> buscarPorAntecedenteTipo(
			AntecedenteTipo antecedenteTipo) {
		return antecedenteDAO.findByAntecedenteTipo(antecedenteTipo);
	}

	public void eliminar(Antecedente antecedente) {
		antecedenteDAO.delete(antecedente);
		
	}
}
