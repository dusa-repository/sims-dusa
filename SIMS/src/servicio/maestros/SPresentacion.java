package servicio.maestros;

import java.util.List;

import interfacedao.maestros.IPresentacionDAO;

import modelo.maestros.Presentacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SPresentacion")
public class SPresentacion {

	@Autowired
	private IPresentacionDAO presentacionDAO;	

	public List<Presentacion> buscarTodas() {	
		return presentacionDAO.findAll();
	}

	public void guardar(Presentacion presentacion) {
		presentacionDAO.save(presentacion);	
	}

	public Presentacion buscarPorNombre(String value) {
	
		return presentacionDAO.findByNombre(value);
	}
}
