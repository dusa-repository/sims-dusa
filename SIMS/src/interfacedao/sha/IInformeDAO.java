package interfacedao.sha;

import java.util.List;

import modelo.maestros.Empresa;
import modelo.maestros.Paciente;
import modelo.sha.Area;
import modelo.sha.ClasificacionAccidente;
import modelo.sha.Condicion;
import modelo.sha.Informe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IInformeDAO extends JpaRepository<Informe, Long> {

	List<Informe> findByCodigoStartingWithAllIgnoreCase(String valor);

	List<Informe> findByPacientePrimerNombreStartingWithAllIgnoreCase(
			String valor);

	List<Informe> findByPacientePrimerApellidoStartingWithAllIgnoreCase(
			String valor);

	List<Informe> findByEmpresaNombreStartingWithAllIgnoreCase(String valor);

	List<Informe> findByCondicionAOrCondicionBOrCondicionCOrCondicionDOrCondicionEOrCondicionF(
			Condicion condicion, Condicion condicion2, Condicion condicion3,
			Condicion condicion4, Condicion condicion5, Condicion condicion6);

	List<Informe> findByArea(Area area);

	List<Informe> findByPaciente(Paciente paciente);

	List<Informe> findByClasificacion(
			ClasificacionAccidente clasificacionAccidente);

	List<Informe> findByEmpresa(Empresa empresa);

	List<Informe> findByEmpresaB(Empresa empresa);

	Informe findByIdInforme(long parseLong);

	List<Informe> findByPacienteB(Paciente pacienteAModificar);

	List<Informe> findByPacienteM(Paciente pacienteAModificar);

	List<Informe> findByPacienteL(Paciente pacienteAModificar);

	List<Informe> findByPacienteK(Paciente pacienteAModificar);

	List<Informe> findByPacienteJ(Paciente pacienteAModificar);

	List<Informe> findByPacienteI(Paciente pacienteAModificar);

	List<Informe> findByPacienteH(Paciente pacienteAModificar);

	List<Informe> findByPacienteG(Paciente pacienteAModificar);

	List<Informe> findByPacienteF(Paciente pacienteAModificar);

	List<Informe> findByPacienteE(Paciente pacienteAModificar);

	List<Informe> findByPacienteD(Paciente pacienteAModificar);

	List<Informe> findByPacienteC(Paciente pacienteAModificar);
	
	@Query("select coalesce(max(consulta.idInforme), '0') from Informe consulta")
	long findMaxIdInforme();

}
