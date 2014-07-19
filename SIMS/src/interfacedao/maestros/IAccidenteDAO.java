package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Accidente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IAccidenteDAO extends JpaRepository<Accidente, Long> {
//
//	List<Accidente> findByTipo(String valor);
//
//	List<Accidente> findByTipoAndIdAccidenteNotIn(String string, List<Long> ids);

//	List<Accidente> findByNombreStartingWithAndTipoAllIgnoreCase(String valor, String tipo);

	@Query("select a from Accidente a order by a.idAccidente asc")
	List<Accidente> findAllOrderByCodigoAsc();

	List<Accidente> findByIdAccidenteNotIn(List<Long> ids);

	List<Accidente> findByNombreStartingWithAllIgnoreCase(String valor);

	List<Accidente> findByIdAccidenteStartingWithAllIgnoreCase(String valor);

	List<Accidente> findByClasificacionNombreStartingWithAllIgnoreCase(
			String valor);

	@Query("select coalesce(max(consulta.idAccidente), '0') from Accidente consulta")
	long findMaxIdDiagnostico();

}
