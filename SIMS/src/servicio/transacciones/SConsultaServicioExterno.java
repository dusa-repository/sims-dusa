package servicio.transacciones;

import interfacedao.transacciones.IConsultaServicioExternoDAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.maestros.Proveedor;
import modelo.maestros.ServicioExterno;
import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaServicioExterno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("SConsultaServicioExterno")
public class SConsultaServicioExterno {

	@Autowired
	private IConsultaServicioExternoDAO consultaServicioExternoDAO;

	public List<ConsultaServicioExterno> buscarPorConsulta(Consulta consulta) {
		return consultaServicioExternoDAO.findByConsulta(consulta);
	}

	public void borrarServiciosDeConsulta(Consulta consulta) {
		List<ConsultaServicioExterno> lista = consultaServicioExternoDAO
				.findByConsulta(consulta);
		if (!lista.isEmpty()) {
			consultaServicioExternoDAO.delete(lista);
		}
	}

	public void guardar(List<ConsultaServicioExterno> listaServicioExterno) {
		consultaServicioExternoDAO.save(listaServicioExterno);
	}

	public ConsultaServicioExterno buscarPorConsultaYIdServicio(
			Consulta consuta, Long part4) {
		// TODO Auto-generated method stub
		return consultaServicioExternoDAO
				.findByConsultaAndServicioExternoIdServicioExterno(consuta,
						part4);
	}

	public List<ConsultaServicioExterno> buscarPorProveedor(Proveedor proveedor) {
		// TODO Auto-generated method stub
		return consultaServicioExternoDAO.findByProveedor(proveedor);
	}

	public List<ConsultaServicioExterno> buscarPorServicio(
			ServicioExterno servicioExterno) {
		// TODO Auto-generated method stub
		return consultaServicioExternoDAO
				.findByServicioExterno(servicioExterno);
	}

	public double sumPorConsulta(Consulta consulta) {
		return consultaServicioExternoDAO.sumByConsulta(consulta);
	}

	public List<ConsultaServicioExterno> buscarPorConsultaYProveedor(
			Consulta consuta, Long part5) {
		return consultaServicioExternoDAO
				.findByConsultaAndProveedorIdProveedor(consuta, part5);
	}

	public List<ConsultaServicioExterno> buscarPorProveedorEntreFechas(
			Date desde, Date hasta, Proveedor proveedor) {

		List<String> ordenar = new ArrayList<String>();
		ordenar.add("proveedorIdProveedor");
		ordenar.add("consultaFechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaServicioExternoDAO
				.findByProveedorAndConsultaFechaConsultaBetween(proveedor,
						desde, hasta, o);
	}

	public List<ConsultaServicioExterno> buscarEntreFechas(Date desde,
			Date hasta) {
		List<String> ordenar = new ArrayList<String>();
		ordenar.add("proveedorIdProveedor");
		ordenar.add("consultaFechaConsulta");
		Sort o = new Sort(Sort.Direction.ASC, ordenar);
		return consultaServicioExternoDAO
				.findByConsultaFechaConsultaBetweenAndProveedorNotNull(desde,
						hasta, o);
	}
}
