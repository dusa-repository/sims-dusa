package interfacedao.maestros;

import java.util.List;

import modelo.maestros.UnidadMedicina;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUnidadMedicinaDAO extends JpaRepository<UnidadMedicina, Long> {

	List<UnidadMedicina> findByNombreStartingWithAllIgnoreCase(String valor);

	UnidadMedicina findByNombre(String value);

}
