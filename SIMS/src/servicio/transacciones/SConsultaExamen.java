package servicio.transacciones;

import interfacedao.transacciones.IConsultaExamenDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.maestros.Examen;
import modelo.maestros.Proveedor;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaExamen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("SConsultaExamen")
public class SConsultaExamen {

	@Autowired
	private IConsultaExamenDAO consultaExamenDAO;

	public List<ConsultaExamen> buscarPorConsulta(Consulta consulta) {
		return consultaExamenDAO
				.findByConsultaOrderByProveedorIdProveedorAsc(consulta);
	}

	public void borrarExamenesDeConsulta(Consulta consulta) {
		List<ConsultaExamen> lista = consultaExamenDAO.findByConsulta(consulta);
		if (!lista.isEmpty()) {
			consultaExamenDAO.delete(lista);
		}
	}

	public void guardar(List<ConsultaExamen> listaConsultaExamen) {
		consultaExamenDAO.save(listaConsultaExamen);
	}

	public List<ConsultaExamen> buscarPorProveedor(Proveedor proveedor) {
		// TODO Auto-generated method stub
		return consultaExamenDAO.findByProveedor(proveedor);
	}

	public List<ConsultaExamen> buscarPorExamen(Examen examen) {
		return consultaExamenDAO.findByExamen(examen);
	}

	public List<ConsultaExamen> buscarPorConsultaYProveedor(Consulta consuta,
			Long part5) {
		return consultaExamenDAO.findByConsultaAndProveedorIdProveedor(consuta,
				part5);
	}

	public double sumPorConsulta(Consulta consulta) {
		return consultaExamenDAO.sumByConsulta(consulta);
	}

	public List<ConsultaExamen> buscarPorProveedorEntreFechas(Date desde,
			Date hasta, Proveedor proveedor) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("proveedorIdProveedor");
		ordenar.add("consultaFechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaExamenDAO
				.findByProveedorAndConsultaFechaConsultaBetween(proveedor,
						desde, hasta, o);
	}

	public List<ConsultaExamen> buscarEntreFechas(Date desde, Date hasta) {

		List<String> ordenar = new ArrayList<String>();
		ordenar.add("proveedorIdProveedor");
		ordenar.add("consultaFechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaExamenDAO
				.findByConsultaFechaConsultaBetweenAndProveedorNotNull(desde,
						hasta, o);
	}
}
