package servicio.maestros;

import interfacedao.maestros.IAntecedenteTipoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SAntecedenteTipo")
public class SAntecedenteTipo {

	@Autowired
	private IAntecedenteTipoDAO	antecedenteTipoDAO;
}
