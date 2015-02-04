package interfacedao.transacciones;

import java.util.Date;
import java.util.List;

import modelo.pk.OrdenEspecialistaId;
import modelo.transacciones.ConsultaEspecialista;
import modelo.transacciones.Orden;
import modelo.transacciones.OrdenEspecialista;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrdenEspecialistaDAO extends JpaRepository<OrdenEspecialista, OrdenEspecialistaId> {

	List<OrdenEspecialista> findByOrden(Orden orden);

	OrdenEspecialista findByOrdenAndEspecialistaCedula(Orden orden,
			String par3);

	List<OrdenEspecialista> findByOrdenFechaOrdenBetween(Date desde,
			Date hasta, Sort o);

}
