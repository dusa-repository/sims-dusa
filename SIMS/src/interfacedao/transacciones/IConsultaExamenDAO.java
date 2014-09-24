package interfacedao.transacciones;

import java.util.List;

import modelo.maestros.Examen;
import modelo.maestros.Proveedor;
import modelo.pk.ConsultaExamenId;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaExamen;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IConsultaExamenDAO  extends JpaRepository<ConsultaExamen, ConsultaExamenId> {

	List<ConsultaExamen> findByConsulta(Consulta consulta);

	List<ConsultaExamen> findByProveedor(Proveedor proveedor);

	List<ConsultaExamen> findByExamen(Examen examen);

	List<ConsultaExamen> findByConsultaOrderByProveedorIdProveedorAsc(
			Consulta consulta);

	List<ConsultaExamen> findByConsultaAndProveedorIdProveedor(
			Consulta consuta, Long part5);

}
