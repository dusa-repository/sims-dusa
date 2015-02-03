package servicio.maestros;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import interfacedao.maestros.IPacienteDAO;
import interfacedao.maestros.IPeriodoDAO;
import interfacedao.maestros.IPeriodoPacienteDAO;
import modelo.maestros.Paciente;
import modelo.maestros.Periodo;
import modelo.maestros.PeriodoPaciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("SPeriodoPaciente")
public class SPeriodoPaciente {

	@Autowired
	private IPeriodoPacienteDAO periodoPacienteDAO;
	@Autowired
	private IPacienteDAO pacienteDAO;
	@Autowired
	private IPeriodoDAO periodoDAO;

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

	public List<PeriodoPaciente> buscarPorPaciente(Paciente pacienteAModificar) {
		return periodoPacienteDAO.findByPaciente(pacienteAModificar);
	}

	public void limpiar(Paciente pacienteAModificar) {
		List<PeriodoPaciente> lista = periodoPacienteDAO
				.findByPaciente(pacienteAModificar);
		if (!lista.isEmpty())
			periodoPacienteDAO.delete(lista);
	}

	public List<PeriodoPaciente> buscarSinCertificado(Long idPeriodo) {
		Periodo periodo = periodoDAO.findOne(idPeriodo);
		List<PeriodoPaciente> lista = buscarPorIdPeriodo(idPeriodo);
		List<Paciente> pacientes = new ArrayList<Paciente>();
		List<String> cedulas = new ArrayList<String>();
		if (lista.isEmpty()) {
			List<String> ordenar = new ArrayList<String>();
			ordenar.add("cedula");
			Sort o = new Sort(Sort.Direction.ASC, ordenar);
			pacientes = pacienteDAO.findAll(o);
		} else {
			for (Iterator<PeriodoPaciente> iterator = lista.iterator(); iterator
					.hasNext();) {
				PeriodoPaciente periodoPaciente = (PeriodoPaciente) iterator
						.next();
				cedulas.add(periodoPaciente.getPaciente().getCedula());
			}
			pacientes = pacienteDAO.findByCedulaNotIn(cedulas);
		}
		lista.clear();
		for (Iterator<Paciente> iterator = pacientes.iterator(); iterator
				.hasNext();) {
			Paciente paciente = (Paciente) iterator.next();
			PeriodoPaciente periodos = new PeriodoPaciente(periodo, paciente,
					"N/A", "N/A", "N/A", "N/A");
			lista.add(periodos);
		}
		return lista;
	}

	public List<PeriodoPaciente> buscarPorIdPeriodoYLikePositivo(
			Long idPeriodo, String vdrl, String resultado) {
		String heces = "NORMAL";
		switch (resultado) {
		case "TODOS":
			resultado = vdrl = heces = "%";
			break;
		case "VDRL NORMALES":
			vdrl = "NO REACTIVO";
			resultado = heces = "%";
			break;
		case "HECES NORMALES":
			heces = "NORMAL";
			resultado = vdrl = "%";
			break;
		case "CITOLOGIA NORMALES":
			resultado = "NORMAL";
			vdrl = heces = "%";
			break;
		default:
			break;
		}
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("pacienteCedula");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return periodoPacienteDAO
				.findByPeriodoIdPeriodoAndVdrlLikeAndHecesLikeAndCitologiaLike(
						idPeriodo, vdrl, heces, resultado, o);
	}

	public List<PeriodoPaciente> buscarPorIdPeriodoYNotLikePositivo(
			Long idPeriodo, String vdrl, String resultado) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("pacienteCedula");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		switch (resultado) {
		case "ANORMALES":
			resultado = "NORMAL";
			return periodoPacienteDAO
					.findByPeriodoIdPeriodoAndVdrlNotAndHecesNotLikeAndCitologiaNotLike(
							idPeriodo, vdrl, resultado, resultado, o);
		case "VDRL ANORMALES":
			resultado = "%";
			return periodoPacienteDAO
					.findByPeriodoIdPeriodoAndVdrlNotAndHecesLikeAndCitologiaLike(
							idPeriodo, vdrl, resultado, resultado, o);
		case "HECES ANORMALES":
			resultado = "NORMAL";
			vdrl = "%";
			return periodoPacienteDAO
					.findByPeriodoIdPeriodoAndVdrlLikeAndHecesNotLikeAndCitologiaLike(
							idPeriodo, vdrl, resultado, vdrl, o);
		case "CITOLOGIA ANORMALES":
			resultado = "NORMAL";
			vdrl = "%";
			return periodoPacienteDAO
					.findByPeriodoIdPeriodoAndVdrlLikeAndHecesLikeAndCitologiaNotLike(
							idPeriodo, vdrl, vdrl, resultado, o);
		}
		return null;

	}
}
