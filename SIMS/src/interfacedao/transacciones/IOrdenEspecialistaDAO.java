package interfacedao.transacciones;

import java.util.Date;
import java.util.List;

import modelo.maestros.Especialista;
import modelo.pk.OrdenEspecialistaId;
import modelo.transacciones.ConsultaEspecialista;
import modelo.transacciones.Orden;
import modelo.transacciones.OrdenEspecialista;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IOrdenEspecialistaDAO extends JpaRepository<OrdenEspecialista, OrdenEspecialistaId> {

	List<OrdenEspecialista> findByOrden(Orden orden);

	OrdenEspecialista findByOrdenAndEspecialistaCedula(Orden orden,
			String par3);

	List<OrdenEspecialista> findByOrdenFechaOrdenBetween(Date desde,
			Date hasta, Sort o);

	List<OrdenEspecialista> findByEspecialistaAndOrdenFechaOrdenBetween(
			Especialista especialista2, Date desde, Date hasta, Sort o);

	@Query("select coalesce((SUM(c.costo)),'0') from OrdenEspecialista c where c.orden=?1")
	double sumByOrden(Orden orden);

}
