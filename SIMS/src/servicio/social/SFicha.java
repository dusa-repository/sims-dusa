package servicio.social;

import interfacedao.social.IFichaDAO;
import modelo.maestros.Paciente;
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

	public Ficha buscarPorPaciente(Paciente pacienteAModificar) {
		return fichaDAO.findByPaciente(pacienteAModificar);
	}

}
