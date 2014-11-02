package interfacedao.transacciones;

import java.util.List;

import modelo.maestros.MotivoCita;
import modelo.maestros.Paciente;
import modelo.transacciones.Orden;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IOrdenDAO extends JpaRepository<Orden, Long>  {

	List<Orden> findByPacienteOrderByFechaOrdenAsc(Paciente paciente);

	@Query("select coalesce(max(consulta.idOrden), '0') from Orden consulta")
	long findMaxIdOrden();

	List<Orden> findByMotivo(MotivoCita motivoCita);

}
