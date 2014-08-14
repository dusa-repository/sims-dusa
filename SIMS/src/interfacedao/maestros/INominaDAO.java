package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Nomina;

import org.springframework.data.jpa.repository.JpaRepository;
	
	public interface INominaDAO extends JpaRepository<Nomina, Long> {

		Nomina findByIdNomina(long id);

		List<Nomina> findByNombreStartingWithAllIgnoreCase(String valor);

		Nomina findByNombre(String value);

}
