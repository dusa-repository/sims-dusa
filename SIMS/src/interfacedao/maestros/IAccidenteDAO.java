package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Accidente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccidenteDAO extends JpaRepository<Accidente, Long> {

	List<Accidente> findByTipo(String valor);

	List<Accidente> findByTipoAndIdAccidenteNotIn(String string, List<Long> ids);

}
