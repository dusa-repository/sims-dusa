package interfacedao.transacciones;

import java.util.List;

import modelo.maestros.Proveedor;
import modelo.maestros.ServicioExterno;
import modelo.pk.ConsultaServicioExternoId;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaServicioExterno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IConsultaServicioExternoDAO extends JpaRepository<ConsultaServicioExterno, ConsultaServicioExternoId> {

	List<ConsultaServicioExterno> findByConsulta(Consulta consulta);

	ConsultaServicioExterno findByConsultaAndServicioExternoIdServicioExterno(
			Consulta consuta, Long part4);

	List<ConsultaServicioExterno> findByProveedor(Proveedor proveedor);

	List<ConsultaServicioExterno> findByServicioExterno(
			ServicioExterno servicioExterno);

	@Query("select coalesce((SUM(c.costo)),'0') from ConsultaServicioExterno c where c.consulta=?1")
	double sumByConsulta(Consulta consulta);

	List<ConsultaServicioExterno> findByConsultaAndProveedorIdProveedor(
			Consulta consuta, Long part5);

}
