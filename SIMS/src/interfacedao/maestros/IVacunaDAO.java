package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Vacuna;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IVacunaDAO extends JpaRepository<Vacuna, Long> {

	List<Vacuna> findByNombreStartingWithAllIgnoreCase(String valor);

	Vacuna findByNombre(String value);

	@Query("select coalesce(max(consulta.idVacuna), '0') from Vacuna consulta")
	long findMaxIdDiagnostico();

}
