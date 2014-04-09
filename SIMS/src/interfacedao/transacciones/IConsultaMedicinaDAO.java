package interfacedao.transacciones;

import java.util.List;

import modelo.pk.ConsultaMedicinaId;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaMedicina;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IConsultaMedicinaDAO extends JpaRepository<ConsultaMedicina, ConsultaMedicinaId> {

	List<ConsultaMedicina> findByConsulta(Consulta consulta);

}
