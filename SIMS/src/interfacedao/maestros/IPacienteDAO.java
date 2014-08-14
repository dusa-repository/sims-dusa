package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Cargo;
import modelo.maestros.Ciudad;
import modelo.maestros.Empresa;
import modelo.maestros.Nomina;
import modelo.maestros.Paciente;
import modelo.sha.Area;

import org.springframework.data.jpa.repository.JpaRepository;

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

}
