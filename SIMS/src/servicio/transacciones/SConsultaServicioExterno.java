package servicio.transacciones;

import interfacedao.transacciones.IConsultaServicioExternoDAO;

import java.util.List;

import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaServicioExterno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SConsultaServicioExterno")
public class SConsultaServicioExterno {

	@Autowired
	private IConsultaServicioExternoDAO consultaServicioExternoDAO;

	public List<ConsultaServicioExterno> buscarPorConsulta(Consulta consulta) {
		return consultaServicioExternoDAO.findByConsulta(consulta);
	}

	public void borrarServiciosDeConsulta(Consulta consulta) {
		List<ConsultaServicioExterno> lista = consultaServicioExternoDAO.findByConsulta(consulta);
		if(!lista.isEmpty()){
			consultaServicioExternoDAO.delete(lista);
		}
	}

	public void guardar(List<ConsultaServicioExterno> listaServicioExterno) {
		consultaServicioExternoDAO.save(listaServicioExterno);
	}

	public ConsultaServicioExterno buscarPorConsultaYIdServicio(
			Consulta consuta, Long part4) {
		// TODO Auto-generated method stub
		return consultaServicioExternoDAO.findByConsultaAndServicioExternoIdServicioExterno(consuta,part4);
	}
}
