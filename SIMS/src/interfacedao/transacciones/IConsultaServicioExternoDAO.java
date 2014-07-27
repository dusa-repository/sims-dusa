package interfacedao.transacciones;

import java.util.List;

import modelo.maestros.Proveedor;
import modelo.maestros.ServicioExterno;
import modelo.pk.ConsultaServicioExternoId;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaServicioExterno;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IConsultaServicioExternoDAO extends JpaRepository<ConsultaServicioExterno, ConsultaServicioExternoId> {

	List<ConsultaServicioExterno> findByConsulta(Consulta consulta);

	ConsultaServicioExterno findByConsultaAndServicioExternoIdServicioExterno(
			Consulta consuta, Long part4);

	List<ConsultaServicioExterno> findByProveedor(Proveedor proveedor);

	List<ConsultaServicioExterno> findByServicioExterno(
			ServicioExterno servicioExterno);

}
