package servicio.transacciones;

import java.util.List;

import interfacedao.transacciones.IPacienteMedicinaDAO;

import modelo.maestros.Paciente;
import modelo.transacciones.ConsultaMedicina;
import modelo.transacciones.PacienteMedicina;

import org.springframework.beans.factory.annotation.Autowired;
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
}
