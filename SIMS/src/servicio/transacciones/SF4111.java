package servicio.transacciones;

import interfacedao.transacciones.IF4111DAO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import modelo.inventario.F4111;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SF4111")
public class SF4111 {

	@Autowired
	private IF4111DAO iF4111DAO;

	public List<F4111> buscarTodosOrdenadosPorTipo(String tipo) {
		return iF4111DAO.findByIldctOrderByIlukidAsc(tipo);
	}

	public void guardar(F4111 f4111) {
		iF4111DAO.saveAndFlush(f4111);
	}

	public List<F4111> buscarPorDoc(Double ildoc, String tipo) {
		return iF4111DAO.findByIldocAndIldct(ildoc, tipo);
	}

	public Double sumar(Double value, String value2, String value3) {
		return iF4111DAO.suma(value, value2, value3);
	}

	public List<F4111> buscarPorMcuOMccu(String clave) {
		return iF4111DAO.findByIlmcuOrIlmmcu(clave, clave);
	}

	public List<F4111> buscarPorUbicaciones(String lmlocn) {
		return iF4111DAO.findByIllocnOrIlasid(lmlocn, lmlocn);
	}

	public List<F4111> buscarPorAn(double aban8) {
		return iF4111DAO.findByIlan8(aban8);
	}

	public List<F4111> buscarPorItm(double clave) {
		return iF4111DAO.findByIlitm(clave);
	}

	public List<F4111> buscarEntreFechas(BigDecimal desde, BigDecimal hasta) {
		// TODO Auto-generated method stub
		return iF4111DAO.findByIltrdjBetweenOrderByIldocAsc(desde, hasta);
	}

	public List<F4111> buscarHastaFecha(BigDecimal hasta) {
		return iF4111DAO.findByIltrdjBeforeOrderByMcuAsc(hasta);
	}

	public List<F4111> buscarTodosOrdenadosPorProveedor(String string,
			Double value) {
		List<F4111> lista = new ArrayList<F4111>();
		List<Double> lista2 = iF4111DAO.findByIlan8AndIldctOrderByIldocAsc(
				value, string);
		for (int i = 0; i < lista2.size(); i++) {
			lista.add(iF4111DAO.findByIldocAndIldct(lista2.get(i), string).get(
					0));
		}
		return lista;
	}

	public F4111 buscar(Double clave41) {
		return iF4111DAO.findOne(clave41);
	}

	public void guardarVarios(List<F4111> cardexs) {
		iF4111DAO.save(cardexs);
	}

	public List<F4111> buscarHastaFecha2(BigDecimal hasta) {
		return iF4111DAO.findByIlvpejBeforeOrderByMcuAsc(hasta);
	}

	public List<F4111> buscarEntreFechas2(BigDecimal desde, BigDecimal hasta) {
		// TODO Auto-generated method stub
		return iF4111DAO.findByIlvpejBetweenOrderByIldocAsc(desde, hasta);
	}

	public F4111 buscarTodosOrdenadosPorDoc(String string, Double claveDoc,
			Double double1) {
		return iF4111DAO.findByIldocAndIldctAndIlitm(claveDoc, string, double1);
	}

	public double sumarPorOrden(long codigoConsulta) {
		Long codigo = codigoConsulta;
		return iF4111DAO.sumByDoc(codigo.doubleValue());
	}
}
