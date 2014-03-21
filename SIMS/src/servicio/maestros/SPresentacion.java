package servicio.maestros;

import interfacedao.maestros.IPresentacionDAO;

import java.util.List;

import modelo.maestros.Medicina;
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

	public List<Presentacion> buscarPorMedicina(Medicina medicina) {
		return presentacionDAO.findByMedicina(medicina);
	}

	public Presentacion buscar(long id) {
		return presentacionDAO.findOne(id);
	}

	public void eliminar(Presentacion presentacion) {
		presentacionDAO.delete(presentacion);
		
	}

	public List<Presentacion> filtroMedicina(String valor) {
		return presentacionDAO.findByMedicinaNombreStartingWithAllIgnoreCase(valor);
	}

	public List<Presentacion> filtroNombre(String valor) {
		return presentacionDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}
}
