package servicio.transacciones;

import interfacedao.transacciones.IConsultaEspecialistaDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.maestros.Especialista;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaEspecialista;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("SConsultaEspecialista")
public class SConsultaEspecialista {

	@Autowired
	private IConsultaEspecialistaDAO consultaEspecialistaDAO;

	public List<ConsultaEspecialista> buscarPorConsulta(Consulta consulta) {
		return consultaEspecialistaDAO.findByConsulta(consulta);
	}

	public void borrarEspecialistasDeConsulta(Consulta consulta) {
		List<ConsultaEspecialista> lista = consultaEspecialistaDAO
				.findByConsulta(consulta);
		if (!lista.isEmpty()) {
			consultaEspecialistaDAO.delete(lista);
		}
	}

	public void guardar(List<ConsultaEspecialista> listaConsultaEspecialista) {
		consultaEspecialistaDAO.save(listaConsultaEspecialista);
	}

	public ConsultaEspecialista buscarPorConsultaYIdEspecialista(
			Consulta consuta, String par3) {
		return consultaEspecialistaDAO.findByConsultaAndEspecialistaCedula(
				consuta, par3);
	}

	public List<ConsultaEspecialista> buscarPorEspecialista(
			Especialista especialista) {
		return consultaEspecialistaDAO.findByEspecialista(especialista);
	}

	public double sumPorConsulta(Consulta consulta) {
		return consultaEspecialistaDAO.sumByConsulta(consulta);
	}

	public List<ConsultaEspecialista> buscarPorEspecialistaEntreFechas(
			Date desde, Date hasta, Especialista especialista) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("consultaFechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaEspecialistaDAO
				.findByEspecialistaAndConsultaFechaConsultaBetween(
						especialista, desde, hasta, o);
	}

	public List<ConsultaEspecialista> buscarEntreFechas(Date desde, Date hasta) {

		List<String> ordenar = new ArrayList<String>();
		ordenar.add("especialistaEspecialidadDescripcion");
		ordenar.add("especialistaCedula");
		ordenar.add("consultaFechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaEspecialistaDAO.findByConsultaFechaConsultaBetween(
				desde, hasta, o);
	}
}
