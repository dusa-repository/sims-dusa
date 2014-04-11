package servicio.transacciones;

import interfacedao.transacciones.IConsultaDiagnosticoDAO;

import java.util.List;

import modelo.transacciones.Consulta;
import modelo.transacciones.ConsultaDiagnostico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SConsultaDiagnostico")
public class SConsultaDiagnostico {

	@Autowired
	private IConsultaDiagnosticoDAO consultaDiagnosticoDAO;

	public List<ConsultaDiagnostico> buscarPorConsulta(Consulta consulta) {
		return consultaDiagnosticoDAO.findByConsulta(consulta);
	}

	public void borrarDiagnosticosDeConsulta(Consulta consulta) {
		List<ConsultaDiagnostico> lista = consultaDiagnosticoDAO.findByConsulta(consulta);
		if(!lista.isEmpty()){
			consultaDiagnosticoDAO.delete(lista);
		}
	}

	public void guardar(List<ConsultaDiagnostico> listaDiagnostico) {
		consultaDiagnosticoDAO.save(listaDiagnostico);
	}
}
