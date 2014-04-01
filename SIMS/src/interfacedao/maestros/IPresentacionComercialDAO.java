package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Medicina;
import modelo.maestros.PresentacionComercial;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPresentacionComercialDAO extends JpaRepository<PresentacionComercial, Long> {

	PresentacionComercial findByNombre(String value);

	List<PresentacionComercial> findByMedicina(Medicina medicina);

	List<PresentacionComercial> findByNombreStartingWithAllIgnoreCase(String valor);

	List<PresentacionComercial> findByMedicinaNombreStartingWithAllIgnoreCase(
			String valor);

}
