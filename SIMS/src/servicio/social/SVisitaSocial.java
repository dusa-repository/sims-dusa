package servicio.social;

import interfacedao.social.IVisitaSocialDAO;

import modelo.social.VisitaSocial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SVisitaSocial")
public class SVisitaSocial {

	@Autowired
	private IVisitaSocialDAO visitaSocialDAO;
	
	public void guardar(VisitaSocial visita) {
		visitaSocialDAO.save(visita);
	}
}
