package servicio.maestros;

import java.util.List;

import interfacedao.maestros.ICargoDAO;

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
}
