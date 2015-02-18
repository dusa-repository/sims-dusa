package interfacedao.transacciones;

import java.util.Date;
import java.util.List;

import modelo.maestros.Proveedor;
import modelo.pk.OrdenExamenId;
import modelo.transacciones.Orden;
import modelo.transacciones.OrdenExamen;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IOrdenExamenDAO extends JpaRepository<OrdenExamen, OrdenExamenId> {

	List<OrdenExamen> findByOrden(Orden orden);

	List<OrdenExamen> findByOrdenAndProveedorIdProveedor(Orden orden, Long part5);

	List<OrdenExamen> findByOrdenFechaOrdenBetween(Date desde, Date hasta,
			Sort o);

	List<OrdenExamen> findByProveedorAndOrdenFechaOrdenBetween(
			Proveedor proveedor, Date desde, Date hasta, Sort o);

	@Query("select coalesce((SUM(c.costo)),'0') from OrdenExamen c where c.orden=?1")
	double sumByOrden(Orden orden);

}
