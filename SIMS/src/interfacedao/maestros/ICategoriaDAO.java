package interfacedao.maestros;

import modelo.maestros.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoriaDAO extends JpaRepository<Categoria, Long> {

	Categoria findByNombre(String value);

}
