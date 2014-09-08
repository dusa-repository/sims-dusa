package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Empresa;
import modelo.maestros.EmpresaNomina;
import modelo.pk.EmpresaNominaId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmpresaNominaDAO extends JpaRepository<EmpresaNomina, EmpresaNominaId> {

	List<EmpresaNomina> findByEmpresa(Empresa empresa);

}
