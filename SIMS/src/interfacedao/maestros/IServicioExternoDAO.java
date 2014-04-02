package interfacedao.maestros;

import java.util.List;

import modelo.maestros.ServicioExterno;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IServicioExternoDAO extends JpaRepository<ServicioExterno, Long> {

	ServicioExterno findByNombre(String value);

	List<ServicioExterno> findByNombreStartingWithAllIgnoreCase(String valor);

	List<ServicioExterno> findByDireccionStartingWithAllIgnoreCase(String valor);

	List<ServicioExterno> findByTelefonoStartingWithAllIgnoreCase(String valor);

	List<ServicioExterno> findByCiudadNombreStartingWithAllIgnoreCase(
			String valor);

}
