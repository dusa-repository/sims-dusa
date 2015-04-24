package servicio.social;

import interfacedao.social.IComposicionFamiliarDAO;
import interfacedao.social.IFichaDAO;

import modelo.social.Ficha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("SFicha")
public class SFicha {
	
	@Autowired
	private IFichaDAO fichaDAO;

	public void guardar(Ficha ficha) {
		fichaDAO.save(ficha);
		
	}

}
