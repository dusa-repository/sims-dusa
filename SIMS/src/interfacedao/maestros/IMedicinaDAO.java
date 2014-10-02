package interfacedao.maestros;

import java.util.List;

import modelo.maestros.CategoriaMedicina;
import modelo.maestros.Laboratorio;
import modelo.maestros.Medicina;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IMedicinaDAO extends JpaRepository<Medicina, Long> {

	Medicina findByNombre(String value);

	List<Medicina> findByLaboratorio(Laboratorio laboratorio);

	List<Medicina> findByNombreStartingWithAllIgnoreCase(String valor);

	List<Medicina> findByLaboratorioNombreStartingWithAllIgnoreCase(String valor);

	List<Medicina> findByPosologiaStartingWithAllIgnoreCase(String valor);

	List<Medicina> findByCategoriaMedicina(CategoriaMedicina categoriaMedicina);

	@Query("select coalesce(max(medicina.idMedicina), '0') from Medicina medicina")
	long findMaxIdMedicina();

	List<Medicina> findByIdMedicinaNotIn(List<Long> ids);

	Medicina findByIdReferencia(long idRefD);

	List<Medicina> findByDenominacionGenericaStartingWithAllIgnoreCase(
			String valor);

	@Query("select coalesce(max(medicina.idReferencia), '0') from Medicina medicina")
	long findMaxIdReferencia();


}
