package servicio.maestros;

import interfacedao.maestros.ICargoDAO;

import java.util.List;

import modelo.maestros.Cargo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SCargo")
public class SCargo {

	@Autowired
	private ICargoDAO cargoDAO;

	public List<Cargo> buscarTodos() {
		return cargoDAO.findAllOrderByNombreAsc();
	}

	public Cargo buscar(long parseLong) {
		return cargoDAO.findOne(parseLong);
	}

	public void guardar(Cargo cargo) {
		cargoDAO.save(cargo);
		
	}

	public void eliminar(Cargo cargo) {
		cargoDAO.delete(cargo);
		
	}

	public List<Cargo> filtroNombre(String valor) {
		return cargoDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}

	public Cargo buscarPorNombre(String value) {
		return cargoDAO.findByNombre(value);
	}
}
