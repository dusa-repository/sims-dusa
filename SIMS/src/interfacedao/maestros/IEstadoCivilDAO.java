package interfacedao.maestros;

import java.util.List;

import modelo.maestros.EstadoCivil;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstadoCivilDAO extends JpaRepository<EstadoCivil, Long>{

	List<EstadoCivil> findByNombreStartingWithAllIgnoreCase(String valor);

	EstadoCivil findByNombre(String value);

}
