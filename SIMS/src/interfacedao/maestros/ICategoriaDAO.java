package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaDAO extends JpaRepository<Categoria, Long> {

	Categoria findByNombre(String value);

	List<Categoria> findByNombreStartingWithAllIgnoreCase(String valor);

}
