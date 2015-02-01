package servicio.control;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import interfacedao.control.IControlConsultaDAO;
import modelo.control.ControlConsulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("SControlConsulta")
public class SControlConsulta {

	@Autowired
	private IControlConsultaDAO controlConsulta;

	public List<ControlConsulta> buscarPorFecha(Date value) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(value);
		calendario.set(Calendar.HOUR, 0);
		calendario.set(Calendar.HOUR_OF_DAY, 0);
		calendario.set(Calendar.SECOND, 0);
		calendario.set(Calendar.MILLISECOND, 0);
		calendario.set(Calendar.MINUTE, 0);
		value = calendario.getTime();
		Timestamp fecha = new Timestamp(value.getTime());
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("idControlConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return controlConsulta.findByFechaLLegada(fecha, o);
	}

	public void guardarVarios(List<ControlConsulta> seleccionados) {
		controlConsulta.save(seleccionados);
	}

	public void eliminarVarios(List<ControlConsulta> seleccionados) {
		controlConsulta.delete(seleccionados);
	}

	public List<ControlConsulta> filtroCedula(String valor, Date value) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(value);
		calendario.set(Calendar.HOUR, 0);
		calendario.set(Calendar.HOUR_OF_DAY, 0);
		calendario.set(Calendar.SECOND, 0);
		calendario.set(Calendar.MILLISECOND, 0);
		calendario.set(Calendar.MINUTE, 0);
		value = calendario.getTime();
		Timestamp fecha = new Timestamp(value.getTime());
		return controlConsulta
				.findByFechaLLegadaAndPacienteCedulaStartingWithAllIgnoreCase(
						fecha, valor);
	}

	public List<ControlConsulta> filtroNombre(String valor, Date value) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(value);
		calendario.set(Calendar.HOUR, 0);
		calendario.set(Calendar.HOUR_OF_DAY, 0);
		calendario.set(Calendar.SECOND, 0);
		calendario.set(Calendar.MILLISECOND, 0);
		calendario.set(Calendar.MINUTE, 0);
		value = calendario.getTime();
		Timestamp fecha = new Timestamp(value.getTime());
		return controlConsulta
				.findByFechaLLegadaAndPacientePrimerNombreStartingWithAllIgnoreCase(
						fecha, valor);
	}

	public List<ControlConsulta> filtroApellido(String valor, Date value) {

		Calendar calendario = Calendar.getInstance();
		calendario.setTime(value);
		calendario.set(Calendar.HOUR, 0);
		calendario.set(Calendar.HOUR_OF_DAY, 0);
		calendario.set(Calendar.SECOND, 0);
		calendario.set(Calendar.MILLISECOND, 0);
		calendario.set(Calendar.MINUTE, 0);
		value = calendario.getTime();
		Timestamp fecha = new Timestamp(value.getTime());
		return controlConsulta
				.findByFechaLLegadaAndPacientePrimerApellidoStartingWithAllIgnoreCase(
						fecha, valor);
	}

	public List<ControlConsulta> filtroObservacion(String valor, Date value) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(value);
		calendario.set(Calendar.HOUR, 0);
		calendario.set(Calendar.HOUR_OF_DAY, 0);
		calendario.set(Calendar.SECOND, 0);
		calendario.set(Calendar.MILLISECOND, 0);
		calendario.set(Calendar.MINUTE, 0);
		value = calendario.getTime();
		Timestamp fecha = new Timestamp(value.getTime());
		return controlConsulta
				.findByFechaLLegadaAndObservacionStartingWithAllIgnoreCase(
						fecha, valor);
	}

	public List<ControlConsulta> filtroHora(String valor, Date value) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(value);
		calendario.set(Calendar.HOUR, 0);
		calendario.set(Calendar.HOUR_OF_DAY, 0);
		calendario.set(Calendar.SECOND, 0);
		calendario.set(Calendar.MILLISECOND, 0);
		calendario.set(Calendar.MINUTE, 0);
		value = calendario.getTime();
		Timestamp fecha = new Timestamp(value.getTime());
		return controlConsulta
				.findByFechaLLegadaAndHoraLLegadaStartingWithAllIgnoreCase(
						fecha, valor);
	}

	public List<ControlConsulta> filtroEstado(String valor, Date value) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(value);
		calendario.set(Calendar.HOUR, 0);
		calendario.set(Calendar.HOUR_OF_DAY, 0);
		calendario.set(Calendar.SECOND, 0);
		calendario.set(Calendar.MILLISECOND, 0);
		calendario.set(Calendar.MINUTE, 0);
		value = calendario.getTime();
		Timestamp fecha = new Timestamp(value.getTime());
		return controlConsulta
				.findByFechaLLegadaAndEstadoStartingWithAllIgnoreCase(fecha,
						valor);
	}

	public ControlConsulta buscar(Long id) {
		return controlConsulta.findOne(id);
	}

	public void guardar(ControlConsulta control) {
		controlConsulta.save(control);
	}

	public List<ControlConsulta> filtroTipo(String valor, Date value) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(value);
		calendario.set(Calendar.HOUR, 0);
		calendario.set(Calendar.HOUR_OF_DAY, 0);
		calendario.set(Calendar.SECOND, 0);
		calendario.set(Calendar.MILLISECOND, 0);
		calendario.set(Calendar.MINUTE, 0);
		value = calendario.getTime();
		Timestamp fecha = new Timestamp(value.getTime());
		return controlConsulta
				.findByFechaLLegadaAndTipoConsultaSecundariaStartingWithAllIgnoreCase(
						fecha, valor);
	}
}
