package interfacedao.maestros;

import java.util.List;

import modelo.maestros.FormaTerapeutica;
import modelo.maestros.Laboratorio;
import modelo.maestros.Medicina;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicinaDAO extends JpaRepository<Medicina, Long> {

	Medicina findByNombre(String value);

	List<Medicina> findByLaboratorio(Laboratorio laboratorio);

	List<Medicina> findByFormaTerapeutica(FormaTerapeutica formaTerapeutica);

	List<Medicina> findByNombreStartingWithAllIgnoreCase(String valor);

	List<Medicina> findByLaboratorioNombreStartingWithAllIgnoreCase(String valor);

	List<Medicina> findByPosologiaStartingWithAllIgnoreCase(String valor);

}
