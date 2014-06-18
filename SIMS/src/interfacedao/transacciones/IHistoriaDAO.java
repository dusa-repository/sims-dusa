package interfacedao.transacciones;

import modelo.maestros.Paciente;
import modelo.transacciones.Historia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IHistoriaDAO extends JpaRepository<Historia, Long> {

	Historia findByPaciente(Paciente paciente);

	@Query("select coalesce(max(h.idHistoria), '0') from Historia h")
	long findMaxIdHistoria();

}
