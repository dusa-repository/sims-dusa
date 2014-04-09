package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Examen;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IExamenDAO extends JpaRepository<Examen, Long>{

	List<Examen> findByNombreStartingWithAllIgnoreCase(String valor);

	List<Examen> findByTipoStartingWithAllIgnoreCase(String valor);

	List<Examen> findByResultadoStartingWithAllIgnoreCase(String valor);

	List<Examen> findByCostoStartingWithAllIgnoreCase(String valor);

	List<Examen> findByMinimoStartingWithAllIgnoreCase(String valor);

	List<Examen> findByMaximoStartingWithAllIgnoreCase(String valor);

	Examen findByNombre(String value);

	List<Examen> findByIdExamenNotIn(List<Long> ids);

}
