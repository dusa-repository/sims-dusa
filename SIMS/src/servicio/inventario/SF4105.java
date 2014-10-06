package servicio.inventario;

import interfacedaoInventario.IF4105DAO;

import java.util.List;

import modelo.inventario.F4105;
import modelo.inventario.F4105PK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SF4105")
public class SF4105 {

	@Autowired
	private IF4105DAO iF4105DAO;

	public void guardar(F4105 f4105) {
		iF4105DAO.saveAndFlush(f4105);
	}

	public F4105 buscar(F4105PK claveCostoUnitario) {
		return iF4105DAO.findOne(claveCostoUnitario);
	}

	public void actualizarCostoPromedio(F4105PK claveCostoUnitario,
			Integer inte, double cantidadAnterior, Double costoUnitario,
			Double costoUnitarioAnterior) {
		Double costoPromedioNuevo = ((inte * costoUnitario) + (cantidadAnterior * costoUnitarioAnterior))
				/ (cantidadAnterior + inte);
		F4105 f05 = buscar(claveCostoUnitario);
		if (f05 != null) {
			if (costoPromedioNuevo.isNaN())
				costoPromedioNuevo = (double) 0;
			f05.setCouncs(costoPromedioNuevo);
			guardar(f05);
		} else {
			f05 = new F4105();
			f05.setId(claveCostoUnitario);
			f05.setCouncs(costoUnitario);
			iF4105DAO.save(f05);
		}

	}

	public void actualizarUltimoCosto(F4105PK claveCostoUnitario,
			Double costoUnitario) {
		F4105 f05 = buscar(claveCostoUnitario);
		if (f05 != null) {
			f05.setCouncs(costoUnitario);
			guardar(f05);
		}else {
			f05 = new F4105();
			f05.setId(claveCostoUnitario);
			f05.setCouncs(costoUnitario);
			iF4105DAO.save(f05);
		}
	}

	public List<F4105> buscarTodosOrdenados() {
		return iF4105DAO.findAllOrderByIdAsc();
	}

	public void eliminarUno(F4105PK clave) {
		iF4105DAO.delete(clave);
	}

	public void eliminarVarios(List<F4105> eliminarLista) {
		iF4105DAO.delete(eliminarLista);
	}

	public List<F4105> buscarPorMcuEItem(String comcu, Double coitm) {
		return iF4105DAO.findByIdCoitmAndIdComcuOrderByIdCoitmAsc(coitm, comcu);
	}

	public void guardarVarios(List<F4105> guardados) {
		iF4105DAO.save(guardados);
	}

}
