package servicio.transacciones;


import java.util.List;

import interfacedao.transacciones.ICitaDAO;

import modelo.maestros.Cita;
import modelo.maestros.MotivoCita;
import modelo.maestros.Paciente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("SCita")
public class SCita {

	@Autowired
	private ICitaDAO citaDAO;

	public List<Cita> buscarPorMotivo(MotivoCita motivoCita) {
		return citaDAO.findByMotivoCita(motivoCita);
	}

	public List<Cita> buscarPorPaciente(Paciente paciente) {
		return citaDAO.findByPaciente(paciente);
	}
}
