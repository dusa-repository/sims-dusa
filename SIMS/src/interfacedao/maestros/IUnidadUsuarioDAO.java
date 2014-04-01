package interfacedao.maestros;

import java.util.List;

import modelo.maestros.UnidadUsuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUnidadUsuarioDAO extends JpaRepository<UnidadUsuario, Long> {

	UnidadUsuario findByNombre(String value);

	List<UnidadUsuario> findByNombreStartingWithAllIgnoreCase(String valor);

}
