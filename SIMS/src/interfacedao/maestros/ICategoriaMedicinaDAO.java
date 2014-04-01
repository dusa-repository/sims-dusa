package interfacedao.maestros;

import java.util.List;

import modelo.maestros.CategoriaMedicina;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaMedicinaDAO extends JpaRepository<CategoriaMedicina, Long> {

	List<CategoriaMedicina> findByNombreStartingWithAllIgnoreCase(String valor);

	CategoriaMedicina findByNombre(String value);

}
