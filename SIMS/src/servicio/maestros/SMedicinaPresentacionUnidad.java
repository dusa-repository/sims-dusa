package servicio.maestros;

import java.util.List;

import interfacedao.maestros.IMedicinaPresentacionUnidadDAO;

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
		// TODO Auto-generated method stub
		return medicinaPresentacionUnidadDAO.findByPresentacionMedicina(presentacionMedicina);
	}

	public List<MedicinaPresentacionUnidad> buscarPorUnidad(
			UnidadMedicina unidadMedicina) {
		return medicinaPresentacionUnidadDAO.findByUnidadMedicina(unidadMedicina);
	}
	
	
}
