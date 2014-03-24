package interfacedao.maestros;

import modelo.maestros.Estado;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstadoDAO extends JpaRepository<Estado, Long> {

}
