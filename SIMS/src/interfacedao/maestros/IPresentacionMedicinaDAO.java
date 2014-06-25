package interfacedao.maestros;

import java.util.List;

import modelo.maestros.PresentacionMedicina;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPresentacionMedicinaDAO extends JpaRepository<PresentacionMedicina, Long> {

	List<PresentacionMedicina> findByNombreStartingWithAllIgnoreCase(
			String valor);

	PresentacionMedicina findByNombre(String value);

	List<PresentacionMedicina> findByIdPresentacionNotIn(List<Long> ids);

	@Query("select coalesce(max(consulta.idPresentacion), '0') from PresentacionMedicina consulta")
	long findMaxIdExamen();

}
