package servicio.maestros;


import interfacedao.maestros.ICiudadDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SCiudad")
public class SCiudad {

	@Autowired
	private ICiudadDAO ciudadDAO;
}
