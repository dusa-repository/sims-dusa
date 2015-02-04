package interfacedao.transacciones;

import java.util.Date;
import java.util.List;

import modelo.pk.OrdenServicioExternoId;
import modelo.transacciones.Orden;
import modelo.transacciones.OrdenServicioExterno;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrdenServicioExternoDAO extends JpaRepository<OrdenServicioExterno, OrdenServicioExternoId> {

	List<OrdenServicioExterno> findByOrden(Orden orden);

	List<OrdenServicioExterno> findByOrdenAndProveedorIdProveedor(Orden orden,
			Long part5);

	List<OrdenServicioExterno> findByOrdenFechaOrdenBetween(Date desde,
			Date hasta, Sort o);

}
