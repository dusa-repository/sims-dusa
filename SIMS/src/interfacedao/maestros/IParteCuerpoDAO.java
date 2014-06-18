package interfacedao.maestros;

import java.util.List;

import modelo.maestros.ParteCuerpo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IParteCuerpoDAO extends JpaRepository<ParteCuerpo, Long> {

	List<ParteCuerpo> findByNombreStartingWithAllIgnoreCase(String valor);

	ParteCuerpo findByNombre(String value);

}
