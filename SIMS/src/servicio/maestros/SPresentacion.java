package servicio.maestros;

import interfacedao.maestros.IPresentacionDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SPresentacion")
public class SPresentacion {

	@Autowired
	private IPresentacionDAO interfacePresentacion;
}
