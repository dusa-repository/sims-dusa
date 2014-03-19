package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Categoria;
import modelo.maestros.Diagnostico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IDiagnosticoDAO extends JpaRepository<Diagnostico, Long> {

	List<Diagnostico> findByCategoria(Categoria categoria);

}
