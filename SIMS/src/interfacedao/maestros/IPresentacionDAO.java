package interfacedao.maestros;

import modelo.maestros.Presentacion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPresentacionDAO extends JpaRepository<Presentacion, Long> {

	Presentacion findByNombre(String value);

}
