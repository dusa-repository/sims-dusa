package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Antecedente;
import modelo.maestros.Paciente;
import modelo.maestros.PacienteAntecedente;
import modelo.pk.PacienteAntecedenteId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteAntecedenteDAO extends JpaRepository<PacienteAntecedente, PacienteAntecedenteId> {

	List<PacienteAntecedente> findByPacienteAndAntecedenteAntecedenteTipoTipoOrderByAntecedenteAntecedenteTipoNombreAsc(
			Paciente paciente, String string);

	List<PacienteAntecedente> findByPaciente(Paciente paciente);

	List<PacienteAntecedente> findByAntecedente(Antecedente antecedente);

}
