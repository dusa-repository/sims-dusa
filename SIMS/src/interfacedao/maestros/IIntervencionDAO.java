package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Intervencion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IIntervencionDAO extends JpaRepository<Intervencion, Long> {

	List<Intervencion> findByIdIntervencionNotIn(List<Long> ids);

	List<Intervencion> findByNombreStartingWithAllIgnoreCase(String valor);

	Intervencion findByNombre(String value);

	@Query("select coalesce(max(consulta.idIntervencion), '0') from Intervencion consulta")
	long findMaxIdIntervencion();

}
