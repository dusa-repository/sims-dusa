package interfacedao.maestros;

import java.util.List;

import modelo.maestros.ServicioExterno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IServicioExternoDAO extends JpaRepository<ServicioExterno, Long> {

	ServicioExterno findByNombre(String value);

	List<ServicioExterno> findByNombreStartingWithAllIgnoreCase(String valor);

	List<ServicioExterno> findByIdServicioExternoNotIn(List<Long> ids);

	@Query("select coalesce(max(consulta.idServicioExterno), '0') from ServicioExterno consulta")
	long findMaxIdServicio();

}
