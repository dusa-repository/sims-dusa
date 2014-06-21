package interfacedao.sha;

import java.util.List;

import modelo.sha.Area;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IAreaDAO extends JpaRepository<Area, Long> {

	List<Area> findByNombreStartingWithAllIgnoreCase(String valor);

	Area findByNombre(String value);

	@Query("select a from Area a order by a.nombre asc")
	List<Area> findAllOrderByNombreAsc();

}
