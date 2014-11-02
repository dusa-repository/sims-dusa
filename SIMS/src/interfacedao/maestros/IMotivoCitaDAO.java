package interfacedao.maestros;

import java.util.List;

import modelo.maestros.MotivoCita;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IMotivoCitaDAO extends JpaRepository<MotivoCita, Long> {

	List<MotivoCita> findByDescripcionStartingWithAllIgnoreCase(String valor);

	MotivoCita findByDescripcion(String value);

	List<MotivoCita> findByTipo(String value);

}
