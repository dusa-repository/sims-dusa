package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Proveedor;
import modelo.maestros.ProveedorServicio;
import modelo.maestros.ServicioExterno;
import modelo.pk.ProveedorServicioId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProveedorServicioDAO extends JpaRepository<ProveedorServicio, ProveedorServicioId> {

	List<ProveedorServicio> findByServicioExterno(ServicioExterno servicio);

	List<ProveedorServicio> findByProveedor(Proveedor proveedor);

	ProveedorServicio findByProveedorIdProveedorAndServicioExternoIdServicioExterno(
			long parseLong, long id);

	List<ProveedorServicio> findByServicioExternoIdServicioExterno(long id);

}
