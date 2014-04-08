package servicio.maestros;

import java.util.List;

import interfacedao.maestros.IPresentacionMedicinaDAO;

import modelo.maestros.Medicina;
import modelo.maestros.PresentacionMedicina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SPresentacionMedicina")
public class SPresentacionMedicina {

	@Autowired
	private IPresentacionMedicinaDAO presentacionMedicinaDAO;

	public void guardar(PresentacionMedicina presentacionMedicina) {
		presentacionMedicinaDAO.save(presentacionMedicina);
	}

	public PresentacionMedicina buscar(long id) {
		return presentacionMedicinaDAO.findOne(id);
	}

	public void eliminar(PresentacionMedicina presentacionMedicina) {
		presentacionMedicinaDAO.delete(presentacionMedicina);
	}

	public List<PresentacionMedicina> buscarTodas() {
		return presentacionMedicinaDAO.findAll();
	}

	public List<PresentacionMedicina> filtroNombre(String valor) {
		return presentacionMedicinaDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public PresentacionMedicina buscarPorNombre(String value) {
		return presentacionMedicinaDAO.findByNombre(value);
	}

	public List<PresentacionMedicina> buscarPresentacionesDisponibles(
			List<Long> ids) {
		return presentacionMedicinaDAO.findByIdPresentacionNotIn(ids);
	}

}
