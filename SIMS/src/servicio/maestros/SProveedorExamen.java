package servicio.maestros;

import interfacedao.maestros.IProveedorExamenDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SProveedorExamen")
public class SProveedorExamen {

	@Autowired
	private IProveedorExamenDAO proveedorExamenDAO;
}
