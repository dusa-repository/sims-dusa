package servicio.social;

import interfacedao.social.IComposicionFamiliarDAO;

import java.util.List;

import modelo.social.ComposicionFamiliar;
import modelo.social.VisitaSocial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SComposicionFamiliar")
public class SComposicionFamiliar {
	
	@Autowired
	private IComposicionFamiliarDAO composicionFamiliarDAO;
	
	public void guardar(ComposicionFamiliar composicion) {
		composicionFamiliarDAO.save(composicion);
	}

	public List<ComposicionFamiliar> buscarPorVisitaSocial(
			VisitaSocial visitaSocial) {
		
		return composicionFamiliarDAO.findByVisita(visitaSocial);
	}

	public void eliminarVarios(List<ComposicionFamiliar> composicionFamiliar) {
		composicionFamiliarDAO.delete(composicionFamiliar);
		
	}

	public void guardarVarios(List<ComposicionFamiliar> composicionFamiliar) {
		composicionFamiliarDAO.save(composicionFamiliar);
		
	}

}
