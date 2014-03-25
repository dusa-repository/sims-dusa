package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Ciudad;
import modelo.maestros.Consultorio;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IConsultorioDAO extends JpaRepository<Consultorio, Long> {

	List<Consultorio> findByCiudad(Ciudad ciudad);

	List<Consultorio> findByNombreStartingWithAllIgnoreCase(String valor);

	List<Consultorio> findByCorreoElectronicoStartingWithAllIgnoreCase(
			String valor);

	List<Consultorio> findByDireccionStartingWithAllIgnoreCase(String valor);

	List<Consultorio> findByDescripcionStartingWithAllIgnoreCase(String valor);

	List<Consultorio> findByTelefono1StartingWithAllIgnoreCase(String valor);

	Consultorio findByNombre(String value);

	List<Consultorio> findByCiudadNombreStartingWithAllIgnoreCase(String valor);

}
