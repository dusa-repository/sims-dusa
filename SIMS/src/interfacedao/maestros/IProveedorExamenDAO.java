package interfacedao.maestros;

import java.util.List;

import modelo.maestros.Examen;
import modelo.maestros.Proveedor;
import modelo.maestros.ProveedorExamen;
import modelo.pk.ProveedorExamenId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProveedorExamenDAO extends JpaRepository<ProveedorExamen, ProveedorExamenId> {

	List<ProveedorExamen> findByExamen(Examen examen);

	List<ProveedorExamen> findByProveedor(Proveedor proveedor);

}
