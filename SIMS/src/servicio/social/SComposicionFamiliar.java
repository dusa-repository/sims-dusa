package servicio.social;

import interfacedao.social.IComposicionFamiliarDAO;

import modelo.social.ComposicionFamiliar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SComposicionFamiliar")
public class SComposicionFamiliar {
	
	@Autowired
	private IComposicionFamiliarDAO composicionFamiliarDAO;
	
	public void guardar(ComposicionFamiliar composicion) {
		composicionFamiliarDAO.save(composicion);
	}

}
