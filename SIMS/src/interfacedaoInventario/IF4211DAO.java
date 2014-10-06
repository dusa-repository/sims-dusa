package interfacedaoInventario;

import java.util.List;

import modelo.inventario.F4211;
import modelo.inventario.F4211PK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IF4211DAO extends JpaRepository<F4211, F4211PK> {


	@Query("Select f from F4211 f where f.sdspattn='Enviada' order by f.id.sddoco asc, f.id.sddcto asc, f.id.sdkcoo asc")
	List<F4211> findAllById();

	List<F4211> findByIdSddocoAndIdSddctoOrderBySditmAsc(Double sddoco,
			String sddcto);

	@Query("select distinct(f.id.sddoco) from F4211 f where f.sdspattn='Enviada'")
	List<Double> findDocoDistinct();

	F4211 findByIdSddocoAndSditm(Double value, Double imitm);

	List<F4211> findByIdSddocoAndIdSddctoAndSdspattnOrderBySditmAsc(
			Double sddoco, String sddcto, String string);

}
