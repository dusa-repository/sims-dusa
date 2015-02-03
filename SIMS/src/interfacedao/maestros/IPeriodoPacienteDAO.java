package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Paciente;
import modelo.maestros.Periodo;
import modelo.maestros.PeriodoPaciente;
import modelo.pk.PeriodoPacienteId;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPeriodoPacienteDAO extends JpaRepository<PeriodoPaciente, PeriodoPacienteId> {

	List<PeriodoPaciente> findByPeriodoIdPeriodo(long id);

	List<PeriodoPaciente> findByPeriodo(Periodo periodo);

	List<PeriodoPaciente> findByPaciente(Paciente pacienteAModificar);

	List<PeriodoPaciente> findByPeriodoIdPeriodoAndVdrlLikeAndHecesLikeAndCitologiaLike(
			Long idPeriodo, String vdrl, String resultado, String resultado2,
			Sort o);

	List<PeriodoPaciente> findByPeriodoIdPeriodoAndVdrlNotAndHecesNotLikeAndCitologiaNotLike(
			Long idPeriodo, String vdrl, String resultado, String resultado2,
			Sort o);

	List<PeriodoPaciente> findByPeriodoIdPeriodoAndVdrlNotAndHecesLikeAndCitologiaLike(
			Long idPeriodo, String vdrl, String resultado, String resultado2,
			Sort o);

	List<PeriodoPaciente> findByPeriodoIdPeriodoAndVdrlLikeAndHecesNotLikeAndCitologiaLike(
			Long idPeriodo, String vdrl, String resultado, String vdrl2, Sort o);

	List<PeriodoPaciente> findByPeriodoIdPeriodoAndVdrlLikeAndHecesLikeAndCitologiaNotLike(
			Long idPeriodo, String vdrl, String vdrl2, String resultado, Sort o);

}
