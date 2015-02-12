package servicio.inventario;


import interfacedaoInventario.IF41021DAO;
import modelo.inventario.F41021;
import modelo.inventario.F41021PK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("SF41021")
public class SF41021 {

	@Autowired
	private IF41021DAO iF41021DAO;

	@Transactional("segundo")
	public F41021 buscar(F41021PK claveSaldo) {
		return iF41021DAO.findOne(claveSaldo);
	}

	public void guardar(F41021 f41021) {
		iF41021DAO.saveAndFlush(f41021);
	}
	
	@Transactional("segundo")
	public Double sumar(Double imitm, String string) {
		return iF41021DAO.sumByItemAndMcu(imitm, string);
	}
}
