package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Cargo;
import modelo.maestros.Ciudad;
import modelo.maestros.Empresa;
import modelo.maestros.Nomina;
import modelo.maestros.Paciente;
import modelo.sha.Area;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IPacienteDAO extends JpaRepository<Paciente, String> {

	List<Paciente> findByPrimerNombreStartingWithAllIgnoreCase(String valor);

	List<Paciente> findByCedulaStartingWithAllIgnoreCase(String valor);

	List<Paciente> findByPrimerApellidoStartingWithAllIgnoreCase(String valor);

	List<Paciente> findByEmpresaNombreStartingWithAllIgnoreCase(String valor);

	Paciente findByCedula(String value);

	List<Paciente> findByEmpresa(Empresa empresa);

	List<Paciente> findByCedulaFamiliar(String valueOf);

	List<Paciente> findByCargoReal(Cargo cargo);

	List<Paciente> findByCiudadVivienda(Ciudad ciudad);

	List<Paciente> findByArea(Area area);

	List<Paciente> findByNomina(Nomina nomina);

	List<Paciente> findByFicha(String value);

	List<Paciente> findByFichaStartingWithAllIgnoreCase(String valor);

	List<Paciente> findByEstatusTrue(Pageable topTen);

	List<Paciente> findByParentescoFamiliarStartingWithAllIgnoreCase(
			String valor);

	List<Paciente> findByTrabajadorTrue(Pageable topTen);

	List<Paciente> findByCedulaFamiliarAndCedulaStartingWithAllIgnoreCase(
			String value, String valor);

	List<Paciente> findByCedulaFamiliarAndPrimerNombreStartingWithAllIgnoreCase(
			String value, String valor);

	List<Paciente> findByCedulaFamiliarAndPrimerApellidoStartingWithAllIgnoreCase(
			String value, String valor);

	List<Paciente> findByTrabajadorTrueAndPrimerNombreStartingWithAllIgnoreCase(
			String valor);

	List<Paciente> findByTrabajadorTrueAndCedulaStartingWithAllIgnoreCase(
			String valor);

	List<Paciente> findByTrabajadorTrueAndPrimerApellidoStartingWithAllIgnoreCase(
			String valor);

	List<Paciente> findByCedulaFamiliarAndParentescoFamiliarStartingWithAllIgnoreCase(
			String valor2, String valor);

	List<Paciente> findByTrabajadorFalseAndPrimerNombreStartingWithAllIgnoreCase(
			String valor);

	List<Paciente> findByTrabajadorFalseAndPrimerApellidoStartingWithAllIgnoreCase(
			String valor);

	List<Paciente> findByTrabajadorFalseAndCedulaStartingWithAllIgnoreCase(
			String valor);

	List<Paciente> findByTrabajadorFalseAndEstatusTrueAndPrimerApellidoStartingWithAllIgnoreCase(
			String valor);

	List<Paciente> findByTrabajadorFalseAndEstatusTrueAndCedulaStartingWithAllIgnoreCase(
			String valor);

	List<Paciente> findByTrabajadorFalseAndEstatusTrueAndPrimerNombreStartingWithAllIgnoreCase(
			String valor);

	List<Paciente> findByPrimerApellidoStartingWithAndEstatusTrueAllIgnoreCase(
			String valor);

	List<Paciente> findByCedulaStartingWithAndEstatusTrueAllIgnoreCase(
			String valor);

	List<Paciente> findByPrimerNombreStartingWithAndEstatusTrueAllIgnoreCase(
			String valor);

	List<Paciente> findByFichaStartingWithAndEstatusTrueAllIgnoreCase(
			String valor);

	List<Paciente> findByTrabajadorFalseAndEstatusTrueAndFichaStartingWithAllIgnoreCase(
			String valor);

	@Query("select p from Paciente p order by p.cedula asc")
	List<Paciente> findAllOrderByCedula(Pageable topTen);

	Paciente findByCedulaAndEstatusTrue(String value);

	List<Paciente> findByTrabajadorTrueAndFichaStartingWithAllIgnoreCase(
			String valor);
	
	Paciente findByCedulaAndEstatusTrueAndTrabajadorFalse(String value);

	List<Paciente> findByTrabajadorFalseAndEstatusTrueOrderByCedulaAsc(
			Pageable topTen);

	List<Paciente> findByEdadBetweenAndTrabajadorAndEstatusTrue(int dea,
			int aa, boolean b, Sort o);

	List<Paciente> findByEdadBetweenAndTrabajadorFalseAndSexoAndParentescoFamiliarAndEstatusTrue(
			int dea, int aa, String sexo, String parentesco, Sort o);

	List<Paciente> findByEdadBetweenAndTrabajadorFalseAndSexoAndEstatusTrue(
			int dea, int aa, String sexo, Sort o);

	List<Paciente> findByEdadBetweenAndTrabajadorFalseAndParentescoFamiliarAndEstatusTrue(
			int dea, int aa, String parentesco, Sort o);

	List<Paciente> findByEdadBetweenAndTrabajadorFalseAndCedulaFamiliarAndEstatusTrue(
			int dea, int aa, String idTrabajador, Sort o);

	List<Paciente> findByEdadBetweenAndSexoAndCedulaFamiliarAndEstatusTrue(
			int dea, int aa, String sexo, String idTrabajador, Sort o);

	List<Paciente> findByEdadBetweenAndTrabajadorFalseAndCedulaFamiliarAndParentescoFamiliarAndEstatusTrue(
			int dea, int aa, String idTrabajador, String parentesco, Sort o);

	List<Paciente> findByEdadBetweenAndTrabajadorFalseAndCedulaFamiliarAndParentescoFamiliarAndSexoAndEstatusTrue(
			int dea, int aa, String idTrabajador, String parentesco,
			String sexo, Sort o);

	List<Paciente> findByEdadBetweenAndEstatusTrue(int dea, int aa, Sort o);

	List<Paciente> findByEdadBetweenAndTrabajadorAndSexoAndEstatusTrue(int dea,
			int aa, boolean b, String sexo, Sort o);

	List<Paciente> findByEdadBetweenAndTrabajadorAndDiscapacidadAndEstatusTrue(
			int dea, int aa, boolean b, boolean c, Sort o);

	List<Paciente> findByEdadBetweenAndTrabajadorAndDiscapacidadAndSexoAndEstatusTrue(
			int dea, int aa, boolean b, boolean c, String sexo, Sort o);

	List<Paciente> findByEdadBetweenAndDiscapacidadAndEstatusTrue(int dea,
			int aa, boolean b, Sort o);

	List<Paciente> findByEdadBetweenAndDiscapacidadAndSexoAndEstatusTrue(
			int dea, int aa, boolean b, String sexo, Sort o);

	List<Paciente> findByTrabajadorTrueAndEstatusTrue(Pageable topTen);

	List<Paciente> findByTrabajadorTrueAndEstatusTrue();

	List<Paciente> findByCedulaNotIn(List<String> ids);

	List<Paciente> findByCedulaFamiliarStartingWithAllIgnoreCase(String valor);

	List<Paciente> findByCedulaFamiliarAndCedulaFamiliarStartingWithAllIgnoreCase(
			String value, String valor);

	List<Paciente> findByCedulaFamiliarStartingWithAndEstatusTrueAllIgnoreCase(
			String valor);

	List<Paciente> findByTrabajadorFalseAndEstatusTrueAndCedulaFamiliarStartingWithAllIgnoreCase(
			String valor);

	List<Paciente> findByTrabajadorTrueAndEstatusTrueAndCedulaStartingWithOrFichaStartingWithAllIgnoreCase(
			String valor, String valor2);

	List<Paciente> findByCedulaNotInAndTrabajadorTrueAndEstatusTrue(
			List<String> ids);

	Paciente findByCedulaAndTrabajadorTrueAndEstatusTrue(String idPaciente);

	List<Paciente> findByTrabajadorTrueAndSegundoNombreStartingWithAllIgnoreCase(
			String valor);

	List<Paciente> findByTrabajadorTrueAndSegundoApellidoStartingWithAllIgnoreCase(
			String valor);

	List<Paciente> findByCedulaFamiliarAndSegundoNombreStartingWithAllIgnoreCase(
			String value, String valor);

	List<Paciente> findBySegundoNombreStartingWithAllIgnoreCase(String valor);

	List<Paciente> findByCedulaFamiliarAndSegundoApellidoStartingWithAllIgnoreCase(
			String value, String valor);

	List<Paciente> findBySegundoApellidoStartingWithAllIgnoreCase(String valor);

	List<Paciente> findBySegundoApellidoStartingWithAndEstatusTrueAllIgnoreCase(
			String valor);

	List<Paciente> findBySegundoNombreStartingWithAndEstatusTrueAllIgnoreCase(
			String valor);

	List<Paciente> findByTrabajadorFalseAndEstatusTrueAndSegundoNombreStartingWithAllIgnoreCase(
			String valor);
	
//@Query("select p from Paciente p where p.edad between ?1 and ?2 and p.trabajador= ?3 and p.cedulaFamiliar=?4 and p.sexo=?5 order by p.cedulaFamiliar asc, p.cedula asc")
}
