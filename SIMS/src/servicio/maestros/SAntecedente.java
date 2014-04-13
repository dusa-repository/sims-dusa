package servicio.maestros;

import interfacedao.maestros.IAntecedenteDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SAntecedente")
public class SAntecedente {

	@Autowired
	private IAntecedenteDAO	antecedenteDAO;
}
