package servicio.inventario;

import interfacedaoInventario.IF00021DAO;

import java.util.List;


import modelo.inventario.F00021;
import modelo.inventario.F00021PK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("SF00021")
public class SF00021 {

	@Autowired
	private IF00021DAO f00021DAO;

	public List<F00021> buscarTodosOrdenados() {
		return f00021DAO.findAllOrderByIdNlkco();
	}
	
	public F00021 buscar(String value, String value2) {
		F00021PK clave = new F00021PK();
		clave.setNlkco(value);
		clave.setNldct(value2);
		clave.setNlctry((double) 0);
		clave.setNlfy((double) 0);
		return f00021DAO.findOne(clave);
	}
	
	public double Numero(String value, String value2) {
		try  {
			return f00021DAO.Numero(value, value2);
	} catch (Exception e) {
		return 0;
	}
	}
	
	@Transactional("segundo")
	public void guardar(F00021 f00021) {
		f00021DAO.save(f00021);
	}

	public void eliminarVarios(List<F00021> eliminar) {
		f00021DAO.delete(eliminar);
	}

	public void eliminarUno(F00021PK clave) {
		f00021DAO.delete(clave);
	}

	public List<F00021> buscarSY(String value) {
		return f00021DAO.findByIdNlkco(value);
	}
}
