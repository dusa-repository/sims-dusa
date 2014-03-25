package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Ciudad;
import modelo.maestros.Empresa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmpresaDAO extends JpaRepository<Empresa, Long> {

	List<Empresa> findByCiudad(Ciudad ciudad);

	List<Empresa> findByNombreStartingWithAllIgnoreCase(String valor);

	List<Empresa> findByRifStartingWithAllIgnoreCase(String valor);

	List<Empresa> findByDireccionStartingWithAllIgnoreCase(String valor);

	List<Empresa> findByCiudadNombreStartingWithAllIgnoreCase(String valor);

	Empresa findByRif(String value);

	List<Empresa> findByTelefono1StartingWithAllIgnoreCase(String valor);

}
