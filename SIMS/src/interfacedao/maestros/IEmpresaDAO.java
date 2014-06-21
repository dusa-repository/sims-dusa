package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Ciudad;
import modelo.maestros.Empresa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmpresaDAO extends JpaRepository<Empresa, Long> {


	List<Empresa> findByNombreStartingWithAllIgnoreCase(String valor);

	List<Empresa> findByRifStartingWithAllIgnoreCase(String valor);

	List<Empresa> findByDireccionCentroStartingWithAllIgnoreCase(String valor);


	Empresa findByRif(String value);

	List<Empresa> findByTelefonoStartingWithAllIgnoreCase(String valor);

}
