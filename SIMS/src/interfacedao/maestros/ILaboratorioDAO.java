package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Laboratorio;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ILaboratorioDAO extends JpaRepository<Laboratorio, Long> {

	Laboratorio findByNombre(String value);

	List<Laboratorio> findByNombreStartingWithAllIgnoreCase(String valor);

}
