package servicio.transacciones;

import interfacedao.transacciones.IConsultaDAO;

import java.util.List;

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

	public void guardar(Consulta consulta) {
		consultaDAO.save(consulta);
	}

	public Consulta buscarUltima() {
		long id = consultaDAO.findMaxIdMedicina();
		if (id != 0)
			return consultaDAO.findOne(id);
		return null;
	}

	public List<Consulta> buscarPorAccidente(Paciente paciente) {
		return consultaDAO.findByPacienteAndAccidenteNotNull(paciente);
	}
}
