package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Intervencion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IIntervencionDAO extends JpaRepository<Intervencion, Long> {

	List<Intervencion> findByIdIntervencionNotIn(List<Long> ids);

	List<Intervencion> findByNombreStartingWithAllIgnoreCase(String valor);

	Intervencion findByNombre(String value);

}
