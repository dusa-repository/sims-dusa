package servicio.sha;

import interfacedao.IHorasHombreDAO;

import java.util.List;

import modelo.sha.HorasHombre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SHorasHombre")
public class SHorasHombre {
	
	@Autowired
	private IHorasHombreDAO  horasHombreDAO;

	public void guardar(HorasHombre horasHombre) {
		horasHombreDAO.save(horasHombre);
		
	}

	public List<HorasHombre> buscarTodos() {
		// TODO Auto-generated method stub
		return horasHombreDAO.findAll();
	}

	public List<HorasHombre> filtroHoras(String valor) {
		// TODO Auto-generated method stub
		return horasHombreDAO.findByHorasStartingWithAllIgnoreCase(valor);
	}

	public List<HorasHombre> filtroFecha(String valor) {
		// TODO Auto-generated method stub
		return horasHombreDAO.findByFechaStartingWithAllIgnoreCase(valor);
	}
}
