package interfacedao.transacciones;

import java.util.List;

import modelo.maestros.ParteCuerpo;
import modelo.pk.ConsultaParteCuerpoId;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaParteCuerpo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IConsultaParteCuerpoDAO extends JpaRepository<ConsultaParteCuerpo, ConsultaParteCuerpoId> {

	List<ConsultaParteCuerpo> findByConsulta(Consulta consulta);

	List<ConsultaParteCuerpo> findByParte(ParteCuerpo parte);

}
