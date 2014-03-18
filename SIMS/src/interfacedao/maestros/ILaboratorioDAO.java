package interfacedao.maestros;

import modelo.maestros.Laboratorio;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ILaboratorioDAO extends JpaRepository<Laboratorio, Long> {

}
