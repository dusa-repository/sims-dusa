package interfacedao.maestros;

import modelo.maestros.Especialidad;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IEspecialidadDAO extends JpaRepository<Especialidad, Long> {

	Especialidad findByDescripcion(String value);

}
