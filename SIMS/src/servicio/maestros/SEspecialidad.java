package servicio.maestros;

import interfacedao.maestros.IEspecialidadDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SEspecialidad")
public class SEspecialidad {

	@Autowired
	private IEspecialidadDAO interfaceEspecialidad;
}
