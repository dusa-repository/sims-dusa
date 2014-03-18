package servicio.maestros;

import interfacedao.maestros.IUnidadDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SUnidad")
public class SUnidad {

	@Autowired
	private IUnidadDAO interfaceUnidad;
}
