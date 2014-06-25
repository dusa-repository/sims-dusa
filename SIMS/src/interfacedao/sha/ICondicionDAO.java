package interfacedao.sha;

import java.util.List;

import modelo.sha.Condicion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICondicionDAO extends JpaRepository<Condicion, Long> {

	@Query("select c from Condicion c order by c.nombre asc")
	List<Condicion> findAllOrderByNombre();

}
