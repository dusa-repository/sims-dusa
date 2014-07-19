package interfacedao.maestros;

import java.util.List;

import modelo.maestros.ParteCuerpo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IParteCuerpoDAO extends JpaRepository<ParteCuerpo, Long> {

	List<ParteCuerpo> findByNombreStartingWithAllIgnoreCase(String valor);

	ParteCuerpo findByNombre(String value);

	@Query("select coalesce(max(parte.idParte), '0') from ParteCuerpo parte")
	long findMaxIdParte();

}
