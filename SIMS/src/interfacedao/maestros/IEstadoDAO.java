package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Estado;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstadoDAO extends JpaRepository<Estado, Long> {

	List<Estado> findByNombreStartingWithAllIgnoreCase(String valor);

	Estado findByNombre(String value);

}
