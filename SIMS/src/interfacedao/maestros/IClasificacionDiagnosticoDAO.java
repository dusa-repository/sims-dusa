package interfacedao.maestros;

import java.util.List;

import modelo.maestros.ClasificacionDiagnostico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IClasificacionDiagnosticoDAO extends JpaRepository<ClasificacionDiagnostico, Long> {

	List<ClasificacionDiagnostico> findByNombre(String value);

	List<ClasificacionDiagnostico> findByNombreStartingWithAllIgnoreCase(
			String valor);

}
