package interfacedaoInventario;

import java.util.List;

import modelo.inventario.F4105;
import modelo.inventario.F4105PK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IF4105DAO extends JpaRepository<F4105, F4105PK> {

	@Query("Select f from F4105 f order by f.id.coitm asc, f.id.comcu asc, f.id.coledg asc")
	List<F4105> findAllOrderByIdAsc();

	List<F4105> findByIdCoitmAndIdComcuOrderByIdCoitmAsc(Double coitm,
			String comcu);

}
