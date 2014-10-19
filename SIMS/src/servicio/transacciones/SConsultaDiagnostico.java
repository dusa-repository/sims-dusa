package servicio.transacciones;

import interfacedao.transacciones.IConsultaDiagnosticoDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.maestros.Accidente;
import modelo.maestros.Diagnostico;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaDiagnostico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("SConsultaDiagnostico")
public class SConsultaDiagnostico {

	@Autowired
	private IConsultaDiagnosticoDAO consultaDiagnosticoDAO;

	public List<ConsultaDiagnostico> buscarPorConsulta(Consulta consulta) {
		return consultaDiagnosticoDAO.findByConsulta(consulta);
	}

	public void borrarDiagnosticosDeConsulta(Consulta consulta) {
		List<ConsultaDiagnostico> lista = consultaDiagnosticoDAO
				.findByConsulta(consulta);
		if (!lista.isEmpty()) {
			consultaDiagnosticoDAO.delete(lista);
		}
	}

	public void guardar(List<ConsultaDiagnostico> listaDiagnostico) {
		consultaDiagnosticoDAO.save(listaDiagnostico);
	}

	public List<ConsultaDiagnostico> buscarPorAccidente(Accidente accidente) {
		return consultaDiagnosticoDAO.findByAccidente(accidente);
	}

	public List<ConsultaDiagnostico> buscarPorDiagnostico(Diagnostico diag) {
		return consultaDiagnosticoDAO.findByDiagnostico(diag);
	}

	public List<ConsultaDiagnostico> buscarEntreFechasEntreEdades(Date fecha1,
			Date fecha2, int dea, int aa) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("tipo");
		ordenar.add("diagnosticoNombre");
		ordenar.add("consultaPacienteCedula");
		ordenar.add("consultaFechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDiagnosticoDAO
				.findByConsultaFechaConsultaBetweenAndConsultaPacienteEdadBetween(
						fecha1, fecha2, dea, aa, o);
	}

	public List<ConsultaDiagnostico> buscarEntreFechasEntreEdadesyTipoDiagnostico(
			Date fecha1, Date fecha2, int dea, int aa, String diagnostico) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("tipo");
		ordenar.add("diagnosticoNombre");
		ordenar.add("consultaPacienteCedula");
		ordenar.add("consultaFechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDiagnosticoDAO
				.findByConsultaFechaConsultaBetweenAndConsultaPacienteEdadBetweenAndTipo(
						fecha1, fecha2, dea, aa, diagnostico, o);
	}

	public List<ConsultaDiagnostico> buscarEntreFechasEntreEdadesyFamiliar(
			Date fecha1, Date fecha2, int dea, int aa, boolean b) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("tipo");
		ordenar.add("diagnosticoNombre");
		ordenar.add("consultaPacienteCedula");
		ordenar.add("consultaFechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDiagnosticoDAO
				.findByConsultaFechaConsultaBetweenAndConsultaPacienteEdadBetweenAndConsultaPacienteTrabajador(
						fecha1, fecha2, dea, aa, b, o);
	}

	public List<ConsultaDiagnostico> buscarEntreFechasEntreEdadesTipoDiagnosticoyFamiliar(
			Date fecha1, Date fecha2, int dea, int aa, String diagnostico,
			boolean b) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("tipo");
		ordenar.add("diagnosticoNombre");
		ordenar.add("consultaPacienteCedula");
		ordenar.add("consultaFechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDiagnosticoDAO
				.findByConsultaFechaConsultaBetweenAndConsultaPacienteEdadBetweenAndTipoAndConsultaPacienteTrabajador(
						fecha1, fecha2, dea, aa, diagnostico, b, o);
	}

	public List<ConsultaDiagnostico> buscarEntreFechasOrdenadoPorDiagnostico(
			Date fecha1, Date fecha2) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("tipo");
		ordenar.add("diagnosticoNombre");
		ordenar.add("consultaPacienteCedula");
		ordenar.add("consultaFechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDiagnosticoDAO.findByConsultaFechaConsultaBetween(
				fecha1, fecha2, o);
	}

	public List<ConsultaDiagnostico> buscarEntreFechasyTipoDiagnostico(
			Date fecha1, Date fecha2, String diagnostico) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("tipo");
		ordenar.add("diagnosticoNombre");
		ordenar.add("consultaPacienteCedula");
		ordenar.add("consultaFechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaDiagnosticoDAO.findByConsultaFechaConsultaBetweenAndTipo(
				fecha1, fecha2,diagnostico, o);
	}

}
