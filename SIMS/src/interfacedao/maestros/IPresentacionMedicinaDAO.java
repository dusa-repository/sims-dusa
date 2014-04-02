package interfacedao.maestros;

import java.util.List;

import modelo.maestros.PresentacionMedicina;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPresentacionMedicinaDAO extends JpaRepository<PresentacionMedicina, Long> {

	List<PresentacionMedicina> findByNombreStartingWithAllIgnoreCase(
			String valor);

	PresentacionMedicina findByNombre(String value);

	List<PresentacionMedicina> findByIdPresentacionNotIn(List<Long> ids);

}
