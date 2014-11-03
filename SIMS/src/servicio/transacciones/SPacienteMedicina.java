package servicio.transacciones;

import java.util.ArrayList;
import java.util.List;

import interfacedao.transacciones.IPacienteMedicinaDAO;

import modelo.maestros.Paciente;
import modelo.transacciones.ConsultaMedicina;
import modelo.transacciones.PacienteMedicina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("SPacienteMedicina")
public class SPacienteMedicina {

	@Autowired
	private IPacienteMedicinaDAO pacienteMedicinaDAO;

	public List<PacienteMedicina> buscarPorPaciente(Paciente paciente) {
		return pacienteMedicinaDAO.findByPaciente(paciente);
	}

	public void guardar(List<PacienteMedicina> listaMedicina) {
		pacienteMedicinaDAO.save(listaMedicina);
	}

	public void limpiarMedicinas(Paciente paciente) {
		List<PacienteMedicina> lista = pacienteMedicinaDAO
				.findByPaciente(paciente);
		if (!lista.isEmpty())
			pacienteMedicinaDAO.delete(lista);
	}

	public List<PacienteMedicina> buscarTodasCronico() {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("pacienteCedula");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return pacienteMedicinaDAO.findByPacienteCronico(true,o);
	}

	public List<PacienteMedicina> buscarPorTrabajadores(boolean b) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("pacienteCedula");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return pacienteMedicinaDAO.findByPacienteCronicoAndPacienteTrabajador(true,b,o);
	}
}
