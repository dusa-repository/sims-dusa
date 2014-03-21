package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Unidad;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUnidadDAO extends JpaRepository<Unidad, Long> {

	Unidad findByNombre(String value);

	List<Unidad> findByNombreStartingWithAllIgnoreCase(String valor);

}
