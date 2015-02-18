package interfacedao.transacciones;

import java.util.Date;
import java.util.List;

import modelo.maestros.MotivoCita;
import modelo.maestros.Paciente;
import modelo.transacciones.Orden;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IOrdenDAO extends JpaRepository<Orden, Long>  {

	List<Orden> findByPacienteOrderByFechaOrdenAsc(Paciente paciente);

	@Query("select coalesce(max(consulta.idOrden), '0') from Orden consulta")
	long findMaxIdOrden();

	List<Orden> findByMotivo(MotivoCita motivoCita);

	List<Orden> findByPacienteAndFechaOrdenStartingWithAllIgnoreCase(
			Paciente paciente, String valor);

	List<Orden> findByPacienteAndDoctorStartingWithAllIgnoreCase(
			Paciente paciente, String valor);

	List<Orden> findByPacienteAndMotivoDescripcionStartingWithAllIgnoreCase(
			Paciente paciente, String valor);

	List<Orden> findByFechaOrdenBetweenAndPacienteTrabajador(Date desde,
			Date hasta, boolean b, Sort o);

	List<Orden> findByFechaOrdenBetweenAndPacienteTrabajadorAndPacienteCedulaFamiliar(
			Date desde, Date hasta, boolean b, String paciente, Sort o);

	List<Orden> findByFechaOrdenBetweenAndPacienteTrabajadorAndPacienteParentescoFamiliar(
			Date desde, Date hasta, boolean b, String parentesco, Sort o);

	List<Orden> findByFechaOrdenBetweenAndPacienteTrabajadorAndPacienteParentescoFamiliarAndPacienteCedulaFamiliar(
			Date desde, Date hasta, boolean b, String parentesco,
			String paciente, Sort o);

	List<Orden> findByFechaOrdenBetweenAndPacienteTrabajadorAndPacienteCedula(
			Date desde, Date hasta, boolean b, String paciente, Sort o);

}
