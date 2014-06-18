package servicio.maestros;

import java.util.Collection;
import java.util.List;

import interfacedao.maestros.IParteCuerpoDAO;

import modelo.maestros.ParteCuerpo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SParteCuerpo")
public class SParteCuerpo {

	@Autowired
	private IParteCuerpoDAO parteDAO;

	public List<ParteCuerpo> buscarTodos() {
		return parteDAO.findAll();
	}

	public void guardar(ParteCuerpo parte) {
		parteDAO.save(parte);
	}

	public ParteCuerpo buscar(long id) {
		return parteDAO.findOne(id);
	}

	public void eliminar(ParteCuerpo parte) {
		parteDAO.delete(parte);
	}

	public List<ParteCuerpo> filtroNombre(String valor) {
		return parteDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public ParteCuerpo buscarPorNombre(String value) {
		return parteDAO.findByNombre(value);
	}
}
