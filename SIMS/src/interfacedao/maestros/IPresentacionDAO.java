package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Medicina;
import modelo.maestros.Presentacion;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPresentacionDAO extends JpaRepository<Presentacion, Long> {

	Presentacion findByNombre(String value);

	List<Presentacion> findByMedicina(Medicina medicina);

}
