package servicio.maestros;

import interfacedao.maestros.IMedicinaDAO;

import java.util.List;

import modelo.maestros.CategoriaMedicina;
import modelo.maestros.Laboratorio;
import modelo.maestros.Medicina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SMedicina")
public class SMedicina {

	@Autowired
	private IMedicinaDAO medicinaDAO;

	public Medicina buscar(long idMedicina) {

		return medicinaDAO.findOne(idMedicina);
	}

	public List<Medicina> buscarTodas() {
		return medicinaDAO.findAll();
	}

	public void guardar(Medicina medicina) {
		medicinaDAO.save(medicina);
	}

	public Medicina buscarPorNombre(String value) {
		return medicinaDAO.findByNombre(value);
	}

	public List<Medicina> buscarPorLaboratorio(Laboratorio laboratorio) {
		return medicinaDAO.findByLaboratorio(laboratorio);
	}

	public void eliminar(Medicina medicina) {
		medicinaDAO.delete(medicina);
	}

	public List<Medicina> filtroNombre(String valor) {
		return medicinaDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Medicina> filtroLaboratorio(String valor) {
		return medicinaDAO
				.findByLaboratorioNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Medicina> filtroPosologia(String valor) {
		return medicinaDAO.findByPosologiaStartingWithAllIgnoreCase(valor);
	}

	public List<Medicina> buscarPorCategoria(CategoriaMedicina categoriaMedicina) {
		return medicinaDAO.findByCategoriaMedicina(categoriaMedicina);
	}

	public Medicina buscarUltima() {
		long id = medicinaDAO.findMaxIdMedicina();
		if (id != 0)
			return medicinaDAO.findOne(id);
		else
			return null;
	}
}
