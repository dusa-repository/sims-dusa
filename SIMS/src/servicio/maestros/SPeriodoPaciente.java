package servicio.maestros;

import java.util.List;

import interfacedao.maestros.IPeriodoPacienteDAO;
import modelo.maestros.Periodo;
import modelo.maestros.PeriodoPaciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SPeriodoPaciente")
public class SPeriodoPaciente {

	@Autowired
	private IPeriodoPacienteDAO periodoPacienteDAO;

	public List<PeriodoPaciente> buscarPorIdPeriodo(long id) {
		return periodoPacienteDAO.findByPeriodoIdPeriodo(id);
	}

	public List<PeriodoPaciente> buscarPorPeriodo(Periodo periodo) {
		return periodoPacienteDAO.findByPeriodo(periodo);
	}

	public void guardarVarios(List<PeriodoPaciente> periodosPacientes) {
		periodoPacienteDAO.save(periodosPacientes);
	}

	public void eliminar(Periodo periodo) {
		List<PeriodoPaciente> lista = periodoPacienteDAO.findByPeriodo(periodo);
		if (!lista.isEmpty())
			periodoPacienteDAO.delete(lista);
	}
}
