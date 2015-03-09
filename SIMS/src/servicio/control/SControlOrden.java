package servicio.control;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import interfacedao.control.IControlOrdenDAO;
import modelo.control.ControlConsulta;
import modelo.control.ControlOrden;
import modelo.maestros.Paciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("SControlOrden")
public class SControlOrden {

	@Autowired
	private IControlOrdenDAO controlOrden;

	public List<ControlOrden> buscarPorFecha(Date value) {
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
		ordenar.add("idControlOrden");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return controlOrden.findByFechaRecepcion(fecha, o);
	}

	public void guardarVarios(List<ControlOrden> seleccionados) {
		controlOrden.save(seleccionados);
	}

	public void eliminarVarios(List<ControlOrden> seleccionados) {
		controlOrden.delete(seleccionados);
	}

	public List<ControlOrden> filtroCedula(String valor, Date value) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(value);
		calendario.set(Calendar.HOUR, 0);
		calendario.set(Calendar.HOUR_OF_DAY, 0);
		calendario.set(Calendar.SECOND, 0);
		calendario.set(Calendar.MILLISECOND, 0);
		calendario.set(Calendar.MINUTE, 0);
		value = calendario.getTime();
		Timestamp fecha = new Timestamp(value.getTime());
		return controlOrden
				.findByFechaRecepcionAndPacienteCedulaStartingWithAllIgnoreCase(
						fecha, valor);
	}

	public List<ControlOrden> filtroNombre(String valor, Date value) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(value);
		calendario.set(Calendar.HOUR, 0);
		calendario.set(Calendar.HOUR_OF_DAY, 0);
		calendario.set(Calendar.SECOND, 0);
		calendario.set(Calendar.MILLISECOND, 0);
		calendario.set(Calendar.MINUTE, 0);
		value = calendario.getTime();
		Timestamp fecha = new Timestamp(value.getTime());
		return controlOrden
				.findByFechaRecepcionAndPacientePrimerNombreStartingWithAllIgnoreCase(
						fecha, valor);
	}

	public List<ControlOrden> filtroApellido(String valor, Date value) {

		Calendar calendario = Calendar.getInstance();
		calendario.setTime(value);
		calendario.set(Calendar.HOUR, 0);
		calendario.set(Calendar.HOUR_OF_DAY, 0);
		calendario.set(Calendar.SECOND, 0);
		calendario.set(Calendar.MILLISECOND, 0);
		calendario.set(Calendar.MINUTE, 0);
		value = calendario.getTime();
		Timestamp fecha = new Timestamp(value.getTime());
		return controlOrden
				.findByFechaRecepcionAndPacientePrimerApellidoStartingWithAllIgnoreCase(
						fecha, valor);
	}

	public List<ControlOrden> filtroObservacion(String valor, Date value) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(value);
		calendario.set(Calendar.HOUR, 0);
		calendario.set(Calendar.HOUR_OF_DAY, 0);
		calendario.set(Calendar.SECOND, 0);
		calendario.set(Calendar.MILLISECOND, 0);
		calendario.set(Calendar.MINUTE, 0);
		value = calendario.getTime();
		Timestamp fecha = new Timestamp(value.getTime());
		return controlOrden
				.findByFechaRecepcionAndObservacionStartingWithAllIgnoreCase(
						fecha, valor);
	}

	public List<ControlOrden> filtroHora(String valor, Date value) {

		Calendar calendario = Calendar.getInstance();
		calendario.setTime(value);
		calendario.set(Calendar.HOUR, 0);
		calendario.set(Calendar.HOUR_OF_DAY, 0);
		calendario.set(Calendar.SECOND, 0);
		calendario.set(Calendar.MILLISECOND, 0);
		calendario.set(Calendar.MINUTE, 0);
		value = calendario.getTime();
		Timestamp fecha = new Timestamp(value.getTime());
		return controlOrden
				.findByFechaRecepcionAndHoraRecepcionStartingWithAllIgnoreCase(
						fecha, valor);
	}

	public List<ControlOrden> filtroEstado(String valor, Date value) {

		Calendar calendario = Calendar.getInstance();
		calendario.setTime(value);
		calendario.set(Calendar.HOUR, 0);
		calendario.set(Calendar.HOUR_OF_DAY, 0);
		calendario.set(Calendar.SECOND, 0);
		calendario.set(Calendar.MILLISECOND, 0);
		calendario.set(Calendar.MINUTE, 0);
		value = calendario.getTime();
		Timestamp fecha = new Timestamp(value.getTime());
		return controlOrden
				.findByFechaRecepcionAndEstadoStartingWithAllIgnoreCase(fecha,
						valor);
	}

	public ControlOrden buscar(Long id) {
		return controlOrden.findOne(id);
	}

	public void guardar(ControlOrden control) {
		controlOrden.save(control);
	}

	public List<ControlOrden> buscarPorPaciente(Paciente pacienteAModificar) {
		return controlOrden.findByPaciente(pacienteAModificar);
	}

	public void limpiar(Paciente pacienteAModificar) {
		List<ControlOrden> lista = controlOrden
				.findByPaciente(pacienteAModificar);
		if (!lista.isEmpty())
			controlOrden.delete(lista);

	}
}
