package servicio.maestros;

import java.util.List;
import java.util.Set;

import interfacedao.maestros.IPacienteAntecedenteDAO;

import modelo.maestros.Paciente;
import modelo.maestros.PacienteAntecedente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SPacienteAntecedente")
public class SPacienteAntecedente {

	@Autowired
	private IPacienteAntecedenteDAO pacienteAntecedenteDAO;

	public List<PacienteAntecedente> buscarAntecedentesPaciente(
			Paciente paciente, String valor) {
		return pacienteAntecedenteDAO
				.findByPacienteAndAntecedenteAntecedenteTipoTipoOrderByAntecedenteAntecedenteTipoNombreAsc(
						paciente, valor);
	}

	public void guardar(List<PacienteAntecedente> antecedentes) {
		pacienteAntecedenteDAO.save(antecedentes);
	}

	public void borrarAntecedentesPaciente(Paciente paciente) {
		List<PacienteAntecedente>	borrados = pacienteAntecedenteDAO.findByPaciente(paciente);
		if(!borrados.isEmpty())
			pacienteAntecedenteDAO.delete(borrados);
	}
}
