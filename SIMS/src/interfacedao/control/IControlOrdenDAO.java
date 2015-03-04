package interfacedao.control;

import java.sql.Timestamp;
import java.util.List;

import modelo.control.ControlOrden;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IControlOrdenDAO extends JpaRepository<ControlOrden, Long> {

	List<ControlOrden> findByFechaRecepcion(Timestamp value, Sort o);

	List<ControlOrden> findByFechaRecepcionAndPacienteCedulaStartingWithAllIgnoreCase(
			Timestamp fecha, String valor);

	List<ControlOrden> findByFechaRecepcionAndPacientePrimerNombreStartingWithAllIgnoreCase(
			Timestamp fecha, String valor);

	List<ControlOrden> findByFechaRecepcionAndPacientePrimerApellidoStartingWithAllIgnoreCase(
			Timestamp fecha, String valor);

	List<ControlOrden> findByFechaRecepcionAndObservacionStartingWithAllIgnoreCase(
			Timestamp fecha, String valor);

	List<ControlOrden> findByFechaRecepcionAndHoraRecepcionStartingWithAllIgnoreCase(
			Timestamp fecha, String valor);

	List<ControlOrden> findByFechaRecepcionAndEstadoStartingWithAllIgnoreCase(
			Timestamp fecha, String valor);

	List<ControlOrden> findByFechaRecepcionAndPacienteFichaStartingWithAllIgnoreCase(
			Timestamp fecha, String valor);

	List<ControlOrden> findByFechaRecepcionAndPacienteCedulaFamiliarStartingWithAllIgnoreCase(
			Timestamp fecha, String valor);

	List<ControlOrden> findByFechaRecepcionAndEstadoAndPacienteCedula(
			Timestamp fecha, String string, String value);

	List<ControlOrden> findByFechaRecepcionAndEstado(Timestamp fecha,
			String string, Sort o);

	List<ControlOrden> findByFechaRecepcionAndPacienteSegundoNombreStartingWithAllIgnoreCase(
			Timestamp fecha, String valor);

	List<ControlOrden> findByFechaRecepcionAndPacienteSegundoApellidoStartingWithAllIgnoreCase(
			Timestamp fecha, String valor);

}
