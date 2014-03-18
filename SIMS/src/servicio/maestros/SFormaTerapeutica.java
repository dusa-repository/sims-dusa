package servicio.maestros;

import interfacedao.maestros.IFormaTerapeuticaDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SFormaTerapeutica")
public class SFormaTerapeutica {

	@Autowired
	private IFormaTerapeuticaDAO interfaceForma;
}
