package servicio.maestros;

import java.util.List;

import interfacedao.maestros.IMedicinaPresentacionUnidadDAO;

import modelo.maestros.Medicina;
import modelo.maestros.MedicinaPresentacionUnidad;
import modelo.maestros.PresentacionMedicina;
import modelo.maestros.UnidadMedicina;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SMedicinaPresentacionUnidad")
public class SMedicinaPresentacionUnidad {

	@Autowired
	private IMedicinaPresentacionUnidadDAO medicinaPresentacionUnidadDAO;

	public List<MedicinaPresentacionUnidad> buscarPorPresentacion(
			PresentacionMedicina presentacionMedicina) {
		return medicinaPresentacionUnidadDAO.findByPresentacionMedicina(presentacionMedicina);
	}

	public List<MedicinaPresentacionUnidad> buscarPorUnidad(
			UnidadMedicina unidadMedicina) {
		return medicinaPresentacionUnidadDAO.findByUnidadMedicina(unidadMedicina);
	}

	public List<MedicinaPresentacionUnidad> buscarPresentacionesUsadas(
			Medicina medicina) {
		return medicinaPresentacionUnidadDAO.findByMedicina(medicina);
	}

	public void eliminar(
			List<MedicinaPresentacionUnidad> medicinasPresentacionesUnidades) {
		medicinaPresentacionUnidadDAO.delete(medicinasPresentacionesUnidades);
		
	}

	public void guardar(
			List<MedicinaPresentacionUnidad> listaMedicinasPresentacion) {
		medicinaPresentacionUnidadDAO.save(listaMedicinasPresentacion);
	}
	
	
}
