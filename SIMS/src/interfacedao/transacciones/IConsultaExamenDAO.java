package interfacedao.transacciones;

import java.util.List;

import modelo.pk.ConsultaExamenId;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaExamen;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IConsultaExamenDAO  extends JpaRepository<ConsultaExamen, ConsultaExamenId> {

	List<ConsultaExamen> findByConsulta(Consulta consulta);

}
