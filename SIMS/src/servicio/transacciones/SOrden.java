package servicio.transacciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import interfacedao.transacciones.IOrdenDAO;
import modelo.maestros.Cita;
import modelo.maestros.MotivoCita;
import modelo.maestros.Paciente;
import modelo.transacciones.Consulta;
import modelo.transacciones.Orden;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("SOrden")
public class SOrden {

	@Autowired
	private IOrdenDAO ordenDAO;

	public Orden buscar(long idOrden) {
		return ordenDAO.findOne(idOrden);
	}

	public List<Orden> buscarPorPaciente(Paciente paciente) {
		return ordenDAO.findByPacienteOrderByFechaOrdenAsc(paciente);
	}

	public void guardar(Orden orden) {
		ordenDAO.save(orden);
	}

	public Orden buscarUltima() {
		long id = ordenDAO.findMaxIdOrden();
		if (id != 0)
			return ordenDAO.findOne(id);
		return null;
	}

	public List<Orden> buscarPorMotivo(MotivoCita motivoCita) {
		return ordenDAO.findByMotivo(motivoCita);
	}

	public void guardarVarias(List<Orden> ordenes) {
		ordenDAO.save(ordenes);
	}

	public List<Orden> filtroFecha(String valor, Paciente paciente) {
		return ordenDAO.findByPacienteAndFechaOrdenStartingWithAllIgnoreCase(
				paciente, valor);
	}

	public List<Orden> filtroDoctor(String valor, Paciente paciente) {
		return ordenDAO.findByPacienteAndDoctorStartingWithAllIgnoreCase(
				paciente, valor);
	}

	public List<Orden> filtroMotivo(String valor, Paciente paciente) {
		return ordenDAO
				.findByPacienteAndMotivoDescripcionStartingWithAllIgnoreCase(
						paciente, valor);
	}

	public List<Orden> buscarEntreFechasFamiliaresTodosTrabajadores(
			Date desde, Date hasta) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("pacienteCedulaFamiliar");
		ordenar.add("pacienteCedula");
		ordenar.add("pacienteParentescoFamiliar");
		ordenar.add("fechaOrden");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return ordenDAO.findByFechaOrdenBetweenAndPacienteTrabajador(
				desde, hasta, false, o);
	}

	public List<Orden> buscarEntreFechasFamiliaresYUnTrabajador(Date desde,
			Date hasta, String paciente) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("pacienteCedula");
		ordenar.add("pacienteParentescoFamiliar");
		ordenar.add("fechaOrden");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return ordenDAO
				.findByFechaOrdenBetweenAndPacienteTrabajadorAndPacienteCedulaFamiliar(
						desde, hasta, false, paciente, o);
	}

	public List<Orden> buscarEntreFechasFamiliaresTodosTrabajadoresUnParentesco(
			Date desde, Date hasta, String parentesco) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("pacienteCedulaFamiliar");
		ordenar.add("pacienteCedula");
		ordenar.add("fechaOrden");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return ordenDAO
				.findByFechaOrdenBetweenAndPacienteTrabajadorAndPacienteParentescoFamiliar(
						desde, hasta, false, parentesco, o);
	}

	public List<Orden> buscarEntreFechasFamiliaresUnTrabajadorYunParentesco(
			Date desde, Date hasta, String paciente, String parentesco) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("pacienteCedula");
		ordenar.add("fechaOrden");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return ordenDAO
				.findByFechaOrdenBetweenAndPacienteTrabajadorAndPacienteParentescoFamiliarAndPacienteCedulaFamiliar(
						desde, hasta, false, parentesco, paciente, o);
	}

	public List<Orden> buscarEntreFechasTrabajadores(Date desde, Date hasta) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("pacienteCedula");
		ordenar.add("fechaOrden");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return ordenDAO.findByFechaOrdenBetweenAndPacienteTrabajador(
				desde, hasta, true, o);
	}

	public List<Orden> buscarEntreFechasUnTrabajador(Date desde, Date hasta,
			String paciente) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("fechaOrden");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return ordenDAO
				.findByFechaOrdenBetweenAndPacienteTrabajadorAndPacienteCedula(
						desde, hasta, true, paciente, o);
	}

	public List<Orden> buscarEntreFechasFamiliaresTodosTrabajadoresYParentescoLike(
			Date desde, Date hasta, String parentesco) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("pacienteParentescoFamiliar");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return ordenDAO
				.findByFechaOrdenBetweenAndPacienteTrabajadorAndPacienteParentescoFamiliarLike(
						desde, hasta, false, parentesco, o);
	}
}
