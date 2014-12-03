package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Periodo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPeriodoDAO extends JpaRepository<Periodo, Long> {

	Periodo findByNombre(String value);

	List<Periodo> findByNombreStartingWithAllIgnoreCase(String valor);

	List<Periodo> findByFechaInicioStartingWithAllIgnoreCase(String valor);

	List<Periodo> findByFechaFinStartingWithAllIgnoreCase(String valor);

}
