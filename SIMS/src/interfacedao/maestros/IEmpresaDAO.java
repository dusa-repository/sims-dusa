package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Empresa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IEmpresaDAO extends JpaRepository<Empresa, Long> {


	List<Empresa> findByNombreStartingWithAllIgnoreCase(String valor);

	List<Empresa> findByRifStartingWithAllIgnoreCase(String valor);

	List<Empresa> findByDireccionCentroStartingWithAllIgnoreCase(String valor);


	Empresa findByRif(String value);

	List<Empresa> findByTelefonoStartingWithAllIgnoreCase(String valor);
	
	@Query("select coalesce(max(consulta.idEmpresa), '0') from Empresa consulta")
	long findMaxIdEmpresa();

}
