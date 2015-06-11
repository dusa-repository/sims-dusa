package interfacedaoInventario;

import java.util.List;

import modelo.inventario.F00021;
import modelo.inventario.F00021PK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IF00021DAO extends JpaRepository<F00021, F00021PK> {
	
	@Query("Select f from F00021 f order by f.id.nlkco asc, f.id.nldct asc, f.id.nlctry asc, f.id.nlfy asc")
	List<F00021> findAllOrderByIdNlkco();

	@Query("Select max(f.nln001) from F00021 f where f.id.nlkco =:value and f.id.nldct =:value2 ")
	public double Numero(
	@Param("value") String a,
	@Param("value2") String b);

	List<F00021> findByIdNlkco(String value);

}
