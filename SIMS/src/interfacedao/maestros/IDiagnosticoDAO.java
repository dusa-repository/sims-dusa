package interfacedao.maestros;

import java.util.List;

import modelo.maestros.CategoriaDiagnostico;
import modelo.maestros.Diagnostico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IDiagnosticoDAO extends JpaRepository<Diagnostico, Long> {

	List<Diagnostico> findByCategoria(CategoriaDiagnostico categoria);

	Diagnostico findByCodigo(String value);

	List<Diagnostico> findByNombreStartingWithAllIgnoreCase(String valor);

	List<Diagnostico> findByCodigoStartingWithAllIgnoreCase(String valor);

	List<Diagnostico> findByGrupoStartingWithAllIgnoreCase(String valor);

	List<Diagnostico> findByCategoriaNombreStartingWithAllIgnoreCase(
			String valor);

}
