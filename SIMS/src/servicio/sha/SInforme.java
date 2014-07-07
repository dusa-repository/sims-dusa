package servicio.sha;

import interfacedao.sha.IInformeDAO;

import modelo.sha.Informe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SInforme")
public class SInforme {

	@Autowired
	private IInformeDAO informeDAO;

	public void guardar(Informe informe) {
		informeDAO.save(informe);
		
	}
}
