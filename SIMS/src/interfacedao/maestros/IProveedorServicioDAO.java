package interfacedao.maestros;

import modelo.maestros.ProveedorServicio;
import modelo.pk.ProveedorServicioId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProveedorServicioDAO extends JpaRepository<ProveedorServicio, ProveedorServicioId> {

}
