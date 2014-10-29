package servicio.transacciones;

import java.util.List;

import interfacedao.transacciones.IOrdenDAO;
import modelo.maestros.Paciente;
import modelo.transacciones.Orden;

import org.springframework.beans.factory.annotation.Autowired;
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
}