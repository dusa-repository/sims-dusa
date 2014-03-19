package interfacedao.maestros;

import modelo.maestros.Unidad;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUnidadDAO extends JpaRepository<Unidad, Long> {

	Unidad findByNombre(String value);

}
