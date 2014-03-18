package servicio.maestros;

import interfacedao.maestros.IMedicinaDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SMedicina")
public class SMedicina {

	@Autowired
	private IMedicinaDAO interfaceMedicina;
}
