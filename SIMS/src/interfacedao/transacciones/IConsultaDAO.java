package interfacedao.transacciones;

import java.util.Date;
import java.util.List;

import modelo.maestros.Cargo;
import modelo.maestros.Paciente;
import modelo.seguridad.Usuario;
import modelo.sha.Area;
import modelo.transacciones.Consulta;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IConsultaDAO extends JpaRepository<Consulta, Long> {

	List<Consulta> findByPaciente(Paciente paciente);

	@Query("select coalesce(max(consulta.idConsulta), '0') from Consulta consulta")
	long findMaxIdMedicina();

	List<Consulta> findByPacienteAndAccidenteLaboralTrue(Paciente paciente);

	List<Consulta> findByFechaConsultaStartingWithAllIgnoreCase(String valor);

	List<Consulta> findByMotivoConsultaStartingWithAllIgnoreCase(String valor);

	List<Consulta> findByEnfermedadActualStartingWithAllIgnoreCase(String valor);

	List<Consulta> findByTipoConsultaStartingWithAllIgnoreCase(String valor);

	List<Consulta> findByTipoConsultaSecundariaStartingWithAllIgnoreCase(
			String valor);

	List<Consulta> findByUsuarioPrimerNombreStartingWithAllIgnoreCase(
			String valor);

	List<Consulta> findByUsuarioPrimerApellidoStartingWithAllIgnoreCase(
			String valor);

	List<Consulta> findByPacienteCedulaOrderByFechaConsultaAsc(String valueOf);

	List<Consulta> findByCargo(Cargo cargo);

	List<Consulta> findByArea(Area area);

	List<Consulta> findByAreaDeseada(Area area);

	Consulta findByIdReferenciaAndCedulaReferencia(long idRefC, String cedRef);

	List<Consulta> findByPacienteOrderByFechaConsultaDesc(Paciente paciente);

	List<Consulta> findByPacienteCedulaOrderByFechaConsultaDesc(String valueOf);

	@Query("select c from Consulta c where c.fechaConsulta between ?1 and ?2 order by c.paciente.area.nombre asc,c.paciente.cargoReal.nombre asc,c.paciente.cedula asc, c.fechaConsulta desc")
	List<Consulta> findByFechaConsultaBetweenOrderByFechaConsultaDesc(
			Date fecha1, Date fecha2);

	List<Consulta> findByFechaConsultaBetweenAndPacienteArea(Date fecha1,
			Date fecha2, Area area2, Sort o);

	List<Consulta> findByFechaConsultaBetweenAndTipoConsulta(Date fecha1,
			Date fecha2, String tipo, Sort o);

	List<Consulta> findByFechaConsultaBetweenAndTipoConsultaAndTipoConsultaSecundaria(
			Date fecha1, Date fecha2, String tipo, String subTipo, Sort o);

	List<Consulta> findByFechaConsultaBetweenAndUsuario(Date fecha1,
			Date fecha2, Usuario doc, Sort o);

	List<Consulta> findByFechaConsultaBetweenAndUsuarioUnidad(Date fecha1,
			Date fecha2, String unidad, Sort o);

	List<Consulta> findByFechaConsultaBetweenAndReposoAndPacienteTrabajador(
			Date fecha1, Date fecha2, boolean b, boolean c, Sort o);

	List<Consulta> findByFechaConsultaBetweenAndReposoAndPacienteAreaAndPacienteTrabajador(
			Date fecha1, Date fecha2, boolean b, Area area2, boolean c, Sort o);

	List<Consulta> findByFechaConsultaBetweenAndReposoAndPacienteTrabajadorAndUsuarioUnidad(
			Date fecha1, Date fecha2, boolean b, boolean c, String unidad,
			Sort o);

	List<Consulta> findByFechaConsultaBetweenAndUsuarioAndReposoAndPacienteTrabajador(
			Date fecha1, Date fecha2, Usuario doc, boolean b, boolean c, Sort o);

	List<Consulta> findByFechaConsultaBetween(Date desde, Date hasta, Sort order);

	List<Consulta> findByFechaConsultaBetweenAndPacienteTrabajador(Date desde,
			Date hasta, boolean trabajador, Sort order);

}
