package interfacedao.transacciones;

import java.util.List;

import modelo.maestros.Accidente;
import modelo.maestros.Diagnostico;
import modelo.pk.ConsultaDiagnosticoId;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaDiagnostico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IConsultaDiagnosticoDAO  extends JpaRepository<ConsultaDiagnostico, ConsultaDiagnosticoId> {

	List<ConsultaDiagnostico> findByConsulta(Consulta consulta);

	List<ConsultaDiagnostico> findByAccidente(Accidente accidente);

	List<ConsultaDiagnostico> findByDiagnostico(Diagnostico diag);

}
