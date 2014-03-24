package servicio.maestros;

import interfacedao.maestros.IEstadoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SEstado")
public class SEstado {

	@Autowired
	private IEstadoDAO estadoDAO;
}
