package servicio.maestros;

import interfacedao.maestros.IConsultorioDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SConsultorio")
public class SConsultorio {

	@Autowired
	private IConsultorioDAO consultorioDAO;
}
