package interfacedao.transacciones;

import java.util.List;

import modelo.pk.OrdenExamenId;
import modelo.transacciones.Orden;
import modelo.transacciones.OrdenExamen;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrdenExamenDAO extends JpaRepository<OrdenExamen, OrdenExamenId> {

	List<OrdenExamen> findByOrden(Orden orden);

	List<OrdenExamen> findByOrdenAndProveedorIdProveedor(Orden orden, Long part5);

}
