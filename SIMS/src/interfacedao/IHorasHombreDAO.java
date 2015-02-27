package interfacedao;

import java.util.List;

import modelo.sha.ClasificacionAccidente;
import modelo.sha.HorasHombre;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IHorasHombreDAO extends JpaRepository<HorasHombre, Long>{

	List<HorasHombre> findByHorasStartingWithAllIgnoreCase(String valor);

	List<HorasHombre> findByFechaStartingWithAllIgnoreCase(String valor);

}
