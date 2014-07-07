package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Cargo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICargoDAO extends JpaRepository<Cargo, Long> {

	@Query("select a from Cargo a order by a.nombre asc")
	List<Cargo> findAllOrderByNombreAsc();

	List<Cargo> findByNombreStartingWithAllIgnoreCase(String valor);

	Cargo findByNombre(String value);

}
