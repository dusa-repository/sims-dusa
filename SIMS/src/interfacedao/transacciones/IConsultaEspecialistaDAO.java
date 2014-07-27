package interfacedao.transacciones;

import java.util.List;

import modelo.maestros.Especialista;
import modelo.pk.ConsultaEspecialistaId;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaEspecialista;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IConsultaEspecialistaDAO  extends JpaRepository<ConsultaEspecialista, ConsultaEspecialistaId> {

	List<ConsultaEspecialista> findByConsulta(Consulta consulta);

	ConsultaEspecialista findByConsultaAndEspecialistaCedula(Consulta consuta,
			String par3);

	List<ConsultaEspecialista> findByEspecialista(Especialista especialista);

}
