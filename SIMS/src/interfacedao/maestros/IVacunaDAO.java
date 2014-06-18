package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Vacuna;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IVacunaDAO extends JpaRepository<Vacuna, Long> {

	List<Vacuna> findByNombreStartingWithAllIgnoreCase(String valor);

	Vacuna findByNombre(String value);

}
