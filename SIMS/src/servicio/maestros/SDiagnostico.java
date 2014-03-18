package servicio.maestros;

import java.util.List;

import interfacedao.maestros.IDiagnosticoDAO;

import modelo.maestros.Diagnostico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SDiagnostico")
public class SDiagnostico {

	@Autowired
	private IDiagnosticoDAO diagnosticoDAO;

	public List<Diagnostico> buscarTodas() {
		return diagnosticoDAO.findAll();
	}

	public void guardar(Diagnostico diagnostico) {
		diagnosticoDAO.save(diagnostico);
	}
}
