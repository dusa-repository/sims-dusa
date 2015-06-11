package interfacedao.maestros;


import java.util.List;

import modelo.maestros.Ciudad;
import modelo.maestros.Proveedor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

	public interface IProveedorDAO extends JpaRepository<Proveedor, Long> {

		List<Proveedor> findByNombreStartingWithAllIgnoreCase(String valor);

		List<Proveedor> findByDireccionStartingWithAllIgnoreCase(String valor);

		List<Proveedor> findByTelefonoStartingWithAllIgnoreCase(String valor);

		List<Proveedor> findByCiudadNombreStartingWithAllIgnoreCase(String valor);

		Proveedor findByNombre(String value);

		@Query("select coalesce(max(p.idProveedor), '0') from Proveedor p")
		long findMaxIdProveedor();

		List<Proveedor> findByCiudad(Ciudad ciudad);

		List<Proveedor> findByIdProveedorIn(List<Long> ids);
}
