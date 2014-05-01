package interfacedao.maestros;


import java.util.List;

import modelo.maestros.Proveedor;

import org.springframework.data.jpa.repository.JpaRepository;

	public interface IProveedorDAO extends JpaRepository<Proveedor, Long> {

		List<Proveedor> findByNombreStartingWithAllIgnoreCase(String valor);

		List<Proveedor> findByDireccionStartingWithAllIgnoreCase(String valor);

		List<Proveedor> findByTelefonoStartingWithAllIgnoreCase(String valor);

		List<Proveedor> findByCiudadNombreStartingWithAllIgnoreCase(String valor);

		Proveedor findByNombre(String value);
}
