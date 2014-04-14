package servicio.maestros;

import java.util.List;

import interfacedao.maestros.IAntecedenteDAO;

import modelo.maestros.Antecedente;
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
}
