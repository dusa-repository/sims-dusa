package interfacedao.control;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import modelo.control.ControlConsulta;
import modelo.maestros.Paciente;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IControlConsultaDAO extends JpaRepository<ControlConsulta, Long> {

	List<ControlConsulta> findByFechaLLegada(Timestamp value, Sort o);

	List<ControlConsulta> findByFechaLLegadaAndHoraLLegadaStartingWithAllIgnoreCase(
			Timestamp fecha, String valor);

	List<ControlConsulta> findByFechaLLegadaAndEstadoStartingWithAllIgnoreCase(
			Timestamp fecha, String valor);

	List<ControlConsulta> findByFechaLLegadaAndObservacionStartingWithAllIgnoreCase(
			Timestamp fecha, String valor);

	List<ControlConsulta> findByFechaLLegadaAndPacientePrimerApellidoStartingWithAllIgnoreCase(
			Timestamp fecha, String valor);

	List<ControlConsulta> findByFechaLLegadaAndPacientePrimerNombreStartingWithAllIgnoreCase(
			Timestamp fecha, String valor);

	List<ControlConsulta> findByFechaLLegadaAndPacienteCedulaStartingWithAllIgnoreCase(
			Timestamp fecha, String valor);

	List<ControlConsulta> findByFechaLLegadaAndTipoConsultaSecundariaStartingWithAllIgnoreCase(
			Timestamp fecha, String valor);

	List<ControlConsulta> findByPaciente(Paciente pacienteAModificar);

}
