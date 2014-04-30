package servicio.maestros;

import java.util.List;

import interfacedao.maestros.IAccidenteDAO;

import modelo.maestros.Accidente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SAccidente")
public class SAccidente {

	@Autowired
	private IAccidenteDAO accidenteDAO;

	public List<Accidente> buscarPorTipo(String valor) {
		return accidenteDAO.findByTipo(valor);
	}

	public Accidente buscar(long parseLong) {
		return accidenteDAO.findOne(parseLong);
	}
}
