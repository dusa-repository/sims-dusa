package interfacedao.transacciones;

import java.util.Date;
import java.util.List;

import modelo.maestros.Accidente;
import modelo.maestros.Diagnostico;
import modelo.pk.ConsultaDiagnosticoId;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaDiagnostico;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IConsultaDiagnosticoDAO  extends JpaRepository<ConsultaDiagnostico, ConsultaDiagnosticoId> {

	List<ConsultaDiagnostico> findByConsulta(Consulta consulta);

	List<ConsultaDiagnostico> findByAccidente(Accidente accidente);

	List<ConsultaDiagnostico> findByDiagnostico(Diagnostico diag);
	
	List<ConsultaDiagnostico> findByConsultaFechaConsultaBetweenAndConsultaPacienteEdadBetween(
			Date fecha1, Date fecha2, int dea, int aa, Sort o);

	List<ConsultaDiagnostico> findByConsultaFechaConsultaBetweenAndConsultaPacienteEdadBetweenAndTipo(
			Date fecha1, Date fecha2, int dea, int aa, String diagnostico,
			Sort o);

	List<ConsultaDiagnostico> findByConsultaFechaConsultaBetweenAndConsultaPacienteEdadBetweenAndConsultaPacienteTrabajador(
			Date fecha1, Date fecha2, int dea, int aa, boolean b, Sort o);

	List<ConsultaDiagnostico> findByConsultaFechaConsultaBetweenAndConsultaPacienteEdadBetweenAndTipoAndConsultaPacienteTrabajador(
			Date fecha1, Date fecha2, int dea, int aa, String diagnostico,
			boolean b, Sort o);

	List<ConsultaDiagnostico> findByConsultaFechaConsultaBetween(Date fecha1,
			Date fecha2, Sort o);

	List<ConsultaDiagnostico> findByConsultaFechaConsultaBetweenAndTipo(
			Date fecha1, Date fecha2, String diagnostico, Sort o);

}
