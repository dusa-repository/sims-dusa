package interfacedao.transacciones;

import java.util.List;

import modelo.maestros.Paciente;
import modelo.transacciones.Consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IConsultaDAO  extends JpaRepository<Consulta, Long> {

	List<Consulta> findByPaciente(Paciente paciente);

	@Query("select coalesce(max(consulta.idConsulta), '0') from Consulta consulta")
	long findMaxIdMedicina();

	List<Consulta> findByPacienteAndAccidenteNotNull(Paciente paciente);

}
