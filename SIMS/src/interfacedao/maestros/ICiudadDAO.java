package interfacedao.maestros;

import modelo.maestros.Ciudad;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICiudadDAO extends JpaRepository<Ciudad, Long> {

}
