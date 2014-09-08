package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Nomina;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
	
	public interface INominaDAO extends JpaRepository<Nomina, Long> {

		Nomina findByIdNomina(long id);

		List<Nomina> findByNombreStartingWithAllIgnoreCase(String valor);

		Nomina findByNombre(String value);

		@Query("select coalesce(max(consulta.idNomina), '0') from Nomina consulta")
		long findMaxIdExamen();

		List<Nomina> findByIdNominaNotIn(List<Long> ids);

}
