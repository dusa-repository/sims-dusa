package servicio.inventario;

import interfacedaoInventario.IF4101DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.inventario.F4101;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("SF4101")
public class SF4101 {

	@Autowired
	private IF4101DAO f4101DAO;
	
	@Transactional("segundo")
	public void guardar(F4101 f4101) {
		f4101DAO.save(f4101);
	}

	public void eliminarVarios(List<F4101> eliminar) {
		f4101DAO.delete(eliminar);
	}

	public void eliminarUno(double umitm) {
		f4101DAO.delete(umitm);
	}

	public F4101 buscar(double umitm) {
		return f4101DAO.findOne(umitm);
	}

	public List<F4101> buscarTodosOrdenados() {

		return f4101DAO.findAllAndOrderByIMDSC1();
	}

	public void guardarVarios(List<F4101> articulos) {
		f4101DAO.save(articulos);
	}

	public F4101 buscarPorReferencia(Long valor) {
		return f4101DAO.findByReferencia(valor);
	}

}
