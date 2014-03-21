package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Especialidad;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IEspecialidadDAO extends JpaRepository<Especialidad, Long> {

	Especialidad findByDescripcion(String value);

	List<Especialidad> findByDescripcionStartingWithAllIgnoreCase(String valor);

}
