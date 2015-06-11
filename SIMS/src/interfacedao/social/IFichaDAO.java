package interfacedao.social;

import modelo.maestros.Paciente;
import modelo.social.Ficha;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IFichaDAO  extends JpaRepository<Ficha, Long>{

	Ficha findByPaciente(Paciente pacienteAModificar);

}
