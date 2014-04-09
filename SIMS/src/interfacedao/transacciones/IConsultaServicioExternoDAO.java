package interfacedao.transacciones;

import java.util.List;

import modelo.pk.ConsultaServicioExternoId;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaServicioExterno;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IConsultaServicioExternoDAO extends JpaRepository<ConsultaServicioExterno, ConsultaServicioExternoId> {

	List<ConsultaServicioExterno> findByConsulta(Consulta consulta);

}
