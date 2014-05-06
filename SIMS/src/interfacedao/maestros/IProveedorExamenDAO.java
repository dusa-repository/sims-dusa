package interfacedao.maestros;

import modelo.maestros.ProveedorExamen;
import modelo.pk.ProveedorExamenId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProveedorExamenDAO extends JpaRepository<ProveedorExamen, ProveedorExamenId> {

}
