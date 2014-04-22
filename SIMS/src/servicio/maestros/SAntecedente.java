package servicio.maestros;

import java.util.List;

import interfacedao.maestros.IAntecedenteDAO;

import modelo.maestros.Antecedente;
import modelo.maestros.AntecedenteTipo;
import modelo.maestros.Paciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SAntecedente")
public class SAntecedente {

	@Autowired
	private IAntecedenteDAO	antecedenteDAO;

	public List<Antecedente> buscarLaborales() {
		return antecedenteDAO.findByAntecedenteTipoTipoTrueOrderByAntecedenteTipoNombreAsc();
	}

	public List<Antecedente> buscarMedicos() {
		return antecedenteDAO.findByAntecedenteTipoTipoFalseOrderByAntecedenteTipoNombreAsc();
	}

	public List<Antecedente> buscarLaboralesPaciente(Paciente paciente) {
		return antecedenteDAO.findByPacientesAndAntecedenteTipoTipoTrueOrderByAntecedenteTipoNombreAsc(paciente);
	}

	public List<Antecedente> buscarMedicosPaciente(Paciente paciente) {
		return antecedenteDAO.findByPacientesAndAntecedenteTipoTipoFalseOrderByAntecedenteTipoNombreAsc(paciente);
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
		return antecedenteDAO.findByAntecedenteTipoNombreStartingWithAllIgnoreCase(valor);
	}

	public Antecedente buscarPorNombre(String value) {
		return antecedenteDAO.findByNombre(value);
	}

	public List<Antecedente> buscarPorAntecedenteTipo(
			AntecedenteTipo antecedenteTipo) {
		return antecedenteDAO.findByAntecedenteTipo(antecedenteTipo);
	}
}
