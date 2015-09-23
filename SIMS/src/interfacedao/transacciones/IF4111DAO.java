package interfacedao.transacciones;

import java.math.BigDecimal;
import java.util.List;

import modelo.inventario.F4111;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IF4111DAO extends JpaRepository<F4111, Double> {

	List<F4111> findByIldctOrderByIlukidAsc(String tipo);

	List<F4111> findByIldocAndIldct(Double ildoc, String tipo);

	@Query("select coalesce((SUM(f.iltrqt)),'0') from F4111 f where f.ilitm=?1 and f.ilmcu=?2 and f.illocn=?3")
	double suma(double valor1, String valor2, String valor3);

	List<F4111> findByIlmcuOrIlmmcu(String clave, String clave2);

	List<F4111> findByIllocnOrIlasid(String lmlocn, String lmlocn2);

	List<F4111> findByIlan8(double aban8);

	List<F4111> findByIlitm(double clave);

	@Query("select f from F4111 f where f.iltrdj between ?1 and ?2 order by f.ildoc asc, f.ildct asc, f.ilitm asc")
	List<F4111> findByIltrdjBetweenOrderByIldocAsc(BigDecimal desde,
			BigDecimal hasta);

	@Query("select f from F4111 f where f.iltrdj < ?1 order by f.ilmcu asc, f.illocn asc, f.ilitm asc")
	List<F4111> findByIltrdjBeforeOrderByMcuAsc(BigDecimal hasta);

	List<F4111> findByIlan8AndIldct(Double value, String string);

	@Query("select distinct fa.ildoc from F4111 fa where fa.ildct=?2 and fa.ilan8=?1")
	List<Double> findByIlan8AndIldctOrderByIldocAsc(Double value, String string);

	@Query("select f from F4111 f where f.ilvpej <= ?1 order by f.ilmcu asc, f.illocn asc, f.ilitm asc")
	List<F4111> findByIlvpejBeforeOrderByMcuAsc(BigDecimal hasta);

	@Query("select f from F4111 f where f.ilvpej between ?1 and ?2 order by f.ildoc asc, f.ildct asc, f.ilitm asc")
	List<F4111> findByIlvpejBetweenOrderByIldocAsc(BigDecimal desde,
			BigDecimal hasta);

	F4111 findByIldocAndIldctAndIlitm(Double claveDoc, String string,
			Double double1);

	@Query("select coalesce((SUM(f.ilpaid)),'0') from F4111 f where f.iltrno=?1 and f.ildct=?2")
	double sumByDoc(Double codigoConsulta, String tipo);
}
