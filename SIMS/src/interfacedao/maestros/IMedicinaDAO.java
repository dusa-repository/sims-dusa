package interfacedao.maestros;

import modelo.maestros.Medicina;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicinaDAO extends JpaRepository<Medicina, Long> {

}
