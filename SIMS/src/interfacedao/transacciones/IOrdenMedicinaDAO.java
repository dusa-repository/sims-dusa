package interfacedao.transacciones;

import java.util.List;

import modelo.pk.OrdenMedicinaId;
import modelo.transacciones.Orden;
import modelo.transacciones.OrdenMedicina;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrdenMedicinaDAO extends JpaRepository<OrdenMedicina, OrdenMedicinaId> {

	List<OrdenMedicina> findByOrden(Orden orden);

}
