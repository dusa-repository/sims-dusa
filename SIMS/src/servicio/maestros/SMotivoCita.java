package servicio.maestros;

import interfacedao.maestros.IMotivoCitaDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SMotivoCita")
public class SMotivoCita {

	@Autowired
	private IMotivoCitaDAO motivoCitaDAO;
}
