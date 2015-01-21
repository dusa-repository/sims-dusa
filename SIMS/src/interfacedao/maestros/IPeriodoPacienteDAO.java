package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Paciente;
import modelo.maestros.Periodo;
import modelo.maestros.PeriodoPaciente;
import modelo.pk.PeriodoPacienteId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPeriodoPacienteDAO extends JpaRepository<PeriodoPaciente, PeriodoPacienteId> {

	List<PeriodoPaciente> findByPeriodoIdPeriodo(long id);

	List<PeriodoPaciente> findByPeriodo(Periodo periodo);

	List<PeriodoPaciente> findByPaciente(Paciente pacienteAModificar);

}
