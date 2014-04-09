package servicio.transacciones;

import java.util.List;

import interfacedao.transacciones.IConsultaDAO;
import modelo.maestros.Paciente;
import modelo.transacciones.Consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SConsulta")
public class SConsulta {

	@Autowired
	private IConsultaDAO consultaDAO;

	public Consulta buscar(long idConsulta) {
		return consultaDAO.findOne(idConsulta);
	}

	public List<Consulta> buscarPorPaciente(Paciente paciente) {
		return consultaDAO.findByPaciente(paciente);
	}
}
