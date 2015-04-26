package servicio.social;

import java.util.List;

import interfacedao.social.IVisitaSocialDAO;
import modelo.maestros.Paciente;
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

	public List<VisitaSocial> buscarTodas() {
		return visitaSocialDAO.findAll();
	}

	public List<VisitaSocial> filtroId(String valor) {
		return visitaSocialDAO.findByIdVisitaStartingWithAllIgnoreCase(valor);
	}

	public List<VisitaSocial> filtroNombrePaciente(String valor) {
		return visitaSocialDAO.findByPacientePrimerNombreStartingWithAllIgnoreCase(valor);
	}

	public VisitaSocial buscar(Long idVisita) {
		return visitaSocialDAO.findByIdVisita(idVisita);
	}

	public VisitaSocial buscarUltimo() {
		long id = visitaSocialDAO.findMaxIdVisita();
		if (id != 0)
			return visitaSocialDAO.findOne(id);
		return null;
	}

	public VisitaSocial buscarPorPaciente(Paciente pacienteAModificar) {
		return visitaSocialDAO.findByPaciente(pacienteAModificar);
	}
}
