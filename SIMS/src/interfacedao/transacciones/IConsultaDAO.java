package interfacedao.transacciones;

import java.util.List;

import modelo.maestros.Cargo;
import modelo.maestros.Paciente;
import modelo.sha.Area;
import modelo.transacciones.Consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IConsultaDAO  extends JpaRepository<Consulta, Long> {

	List<Consulta> findByPaciente(Paciente paciente);

	@Query("select coalesce(max(consulta.idConsulta), '0') from Consulta consulta")
	long findMaxIdMedicina();

	List<Consulta> findByPacienteAndAccidenteLaboralTrue(Paciente paciente);

	List<Consulta> findByFechaConsultaStartingWithAllIgnoreCase(String valor);

	List<Consulta> findByMotivoConsultaStartingWithAllIgnoreCase(String valor);

	List<Consulta> findByEnfermedadActualStartingWithAllIgnoreCase(String valor);

	List<Consulta> findByTipoConsultaStartingWithAllIgnoreCase(String valor);

	List<Consulta> findByTipoConsultaSecundariaStartingWithAllIgnoreCase(
			String valor);

	List<Consulta> findByUsuarioPrimerNombreStartingWithAllIgnoreCase(
			String valor);

	List<Consulta> findByUsuarioPrimerApellidoStartingWithAllIgnoreCase(
			String valor);

	List<Consulta> findByPacienteCedulaOrderByFechaConsultaAsc(String valueOf);

	List<Consulta> findByCargo(Cargo cargo);

	List<Consulta> findByArea(Area area);

	List<Consulta> findByAreaDeseada(Area area);

}
