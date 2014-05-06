package servicio.maestros;

import interfacedao.maestros.IProveedorServicioDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SProveedorServicio")
public class SProveedorServicio {

	@Autowired
	private IProveedorServicioDAO proveedorServicioDAO;
}
