package servicio.maestros;

import interfacedao.maestros.IPresentacionComercialDAO;

import java.util.List;

import modelo.maestros.Medicina;
import modelo.maestros.PresentacionComercial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SPresentacionComercial")
public class SPresentacionComercial {

	@Autowired
	private IPresentacionComercialDAO presentacionDAO;	

	public List<PresentacionComercial> buscarTodas() {	
		return presentacionDAO.findAll();
	}

	public void guardar(PresentacionComercial presentacion) {
		presentacionDAO.save(presentacion);	
	}

	public PresentacionComercial buscarPorNombre(String value) {
	
		return presentacionDAO.findByNombre(value);
	}

	public List<PresentacionComercial> buscarPorMedicina(Medicina medicina) {
		return presentacionDAO.findByMedicina(medicina);
	}

	public PresentacionComercial buscar(long id) {
		return presentacionDAO.findOne(id);
	}

	public void eliminar(PresentacionComercial presentacion) {
		presentacionDAO.delete(presentacion);
		
	}

	public List<PresentacionComercial> filtroMedicina(String valor) {
		return presentacionDAO.findByMedicinaNombreStartingWithAllIgnoreCase(valor);
	}

	public List<PresentacionComercial> filtroNombre(String valor) {
		return presentacionDAO.findByNombreStartingWithAllIgnoreCase(valor);
	}
}
