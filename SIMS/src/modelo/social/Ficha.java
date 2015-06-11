package modelo.social;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import modelo.maestros.Paciente;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "ficha", schema = "dusa_sims.dbo")
public class Ficha implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ficha", unique = true, nullable = false)
	private long idFicha;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
	private Paciente paciente;

	@Column(name = "talla_camisa", length = 5)
	private String tallaCamisa;

	@Column(name = "talla_pantalon", length = 5)
	private String tallaPantalon;

	@Column(name = "talla_botas", length = 5)
	private String tallaBotas;

	@Column(name = "talla_goma", length = 5)
	private String tallaGoma;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean muerte;

	@Column(name = "maneja_armas")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean manejaArmas;

	@Column(name = "porta_armas")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean portaArmas;

	@Column(name = "servicio_militar")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean servicioMilitar;

	@Column(name = "clase_militar")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean claseMilitar;

	@Column(name = "conduce_vehiculo")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean conduceVehiculo;

	@Column(name = "posee_licencia")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean poseeLicencia;

	@Column(name = "certificado_medico")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean certificadoMedico;

	@Column(name = "posee_vehiculo")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean poseeVehiculo;

	@Column(name = "vivienda_propia")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean viviendaPropia;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean agua;

	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean electricidad;
	
	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean cloacas;
	
	@Column
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean aseo;
	
	@Column(name = "trabajo_afuera")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean trabajoAfuera;
	
	@Column(name = "trabajo_turnos")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean trabajoTurnos;
	
	@Column(name = "nro_porte_armas", length = 50)
	private String nroPorteArmas;
	
	@Column(name ="nro_carnet_militar", length = 50)
	private String nroCarnetMilitar;
	
	@Column(name ="grado_licencia", length = 50)
	private String gradoLicencia;
	
	@Column(name ="tipo_vehiculo", length = 50)
	private String tipoVehiculo;
	
	@Column(name ="marca_vehiculo", length = 50)
	private String marcaVehiculo;
	
	@Column(name ="modelo_vehiculo", length = 50)
	private String modeloVehiculo;
	
	@Column(name ="color_vehiculo", length = 50)
	private String colorVehiculo;
	
	@Column(name ="anno_vehiculo", length = 50)
	private String annoVehiculo;
	
	@Column(name ="placa_vehiculo", length = 50)
	private String placaVehiculo;
	
	@Column(name ="tipo_vivienda", length = 50)
	private String tipoVivienda;
	
	@Column(name ="tenencia_vivienda", length = 50)
	private String tenenciaVivienda;
	
	@Column(name ="combustible_cocinar", length = 50)
	private String combustibleCocinar;
	
	@Column(name ="servicio_agua", length = 50)
	private String servicioAgua;
	
	@Column(name ="basura_final", length = 50)
	private String basuraFinal;
	
	@Column(name ="jefe", length = 50)
	private String jefe;
	
	@Column(length = 50)
	private Integer cuartos;
	
	@Column(length = 50)
	private Integer vehiculos;
	
	@Column(name = "personas_vivienda",length = 50)
	private Integer personasVivienda;
	
	@Column(name = "personas_carga",length = 50)
	private Integer personasCarga;
	
	@Column(name = "fecha_licencia")
	private Timestamp fechaLicencia;
	
	@Column(name = "fecha_certificado")
	private Timestamp fechaCertificado;
	
	@Column(name = "fecha_ficha")
	private Timestamp fechaFicha;
	
	public Ficha() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getIdFicha() {
		return idFicha;
	}

	public void setIdFicha(long idFicha) {
		this.idFicha = idFicha;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getTallaCamisa() {
		return tallaCamisa;
	}

	public void setTallaCamisa(String tallaCamisa) {
		this.tallaCamisa = tallaCamisa;
	}

	public String getTallaPantalon() {
		return tallaPantalon;
	}

	public void setTallaPantalon(String tallaPantalon) {
		this.tallaPantalon = tallaPantalon;
	}

	public String getTallaBotas() {
		return tallaBotas;
	}

	public void setTallaBotas(String tallaBotas) {
		this.tallaBotas = tallaBotas;
	}

	public String getTallaGoma() {
		return tallaGoma;
	}

	public void setTallaGoma(String tallaGoma) {
		this.tallaGoma = tallaGoma;
	}

	public boolean isMuerte() {
		return muerte;
	}

	public void setMuerte(boolean muerte) {
		this.muerte = muerte;
	}

	public boolean isManejaArmas() {
		return manejaArmas;
	}

	public void setManejaArmas(boolean manejaArmas) {
		this.manejaArmas = manejaArmas;
	}

	public boolean isPortaArmas() {
		return portaArmas;
	}

	public void setPortaArmas(boolean portaArmas) {
		this.portaArmas = portaArmas;
	}

	public boolean isServicioMilitar() {
		return servicioMilitar;
	}

	public void setServicioMilitar(boolean servicioMilitar) {
		this.servicioMilitar = servicioMilitar;
	}

	public boolean isClaseMilitar() {
		return claseMilitar;
	}

	public void setClaseMilitar(boolean claseMilitar) {
		this.claseMilitar = claseMilitar;
	}

	public boolean isConduceVehiculo() {
		return conduceVehiculo;
	}

	public void setConduceVehiculo(boolean conduceVehiculo) {
		this.conduceVehiculo = conduceVehiculo;
	}

	public boolean isPoseeLicencia() {
		return poseeLicencia;
	}

	public void setPoseeLicencia(boolean poseeLicencia) {
		this.poseeLicencia = poseeLicencia;
	}

	public boolean isCertificadoMedico() {
		return certificadoMedico;
	}

	public void setCertificadoMedico(boolean certificadoMedico) {
		this.certificadoMedico = certificadoMedico;
	}

	public boolean isPoseeVehiculo() {
		return poseeVehiculo;
	}

	public void setPoseeVehiculo(boolean poseeVehiculo) {
		this.poseeVehiculo = poseeVehiculo;
	}

	public boolean isViviendaPropia() {
		return viviendaPropia;
	}

	public void setViviendaPropia(boolean viviendaPropia) {
		this.viviendaPropia = viviendaPropia;
	}

	public boolean isAgua() {
		return agua;
	}

	public void setAgua(boolean agua) {
		this.agua = agua;
	}

	public boolean isElectricidad() {
		return electricidad;
	}

	public void setElectricidad(boolean electricidad) {
		this.electricidad = electricidad;
	}

	public boolean isCloacas() {
		return cloacas;
	}

	public void setCloacas(boolean cloacas) {
		this.cloacas = cloacas;
	}

	public boolean isAseo() {
		return aseo;
	}

	public void setAseo(boolean aseo) {
		this.aseo = aseo;
	}

	public boolean isTrabajoAfuera() {
		return trabajoAfuera;
	}

	public void setTrabajoAfuera(boolean trabajoAfuera) {
		this.trabajoAfuera = trabajoAfuera;
	}

	public boolean isTrabajoTurnos() {
		return trabajoTurnos;
	}

	public void setTrabajoTurnos(boolean trabajoTurnos) {
		this.trabajoTurnos = trabajoTurnos;
	}

	public String getNroPorteArmas() {
		return nroPorteArmas;
	}

	public void setNroPorteArmas(String nroPorteArmas) {
		this.nroPorteArmas = nroPorteArmas;
	}

	public String getNroCarnetMilitar() {
		return nroCarnetMilitar;
	}

	public void setNroCarnetMilitar(String nroCarnetMilitar) {
		this.nroCarnetMilitar = nroCarnetMilitar;
	}

	public String getGradoLicencia() {
		return gradoLicencia;
	}

	public void setGradoLicencia(String gradoLicencia) {
		this.gradoLicencia = gradoLicencia;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public String getMarcaVehiculo() {
		return marcaVehiculo;
	}

	public void setMarcaVehiculo(String marcaVehiculo) {
		this.marcaVehiculo = marcaVehiculo;
	}

	public String getModeloVehiculo() {
		return modeloVehiculo;
	}

	public void setModeloVehiculo(String modeloVehiculo) {
		this.modeloVehiculo = modeloVehiculo;
	}

	public String getColorVehiculo() {
		return colorVehiculo;
	}

	public void setColorVehiculo(String colorVehiculo) {
		this.colorVehiculo = colorVehiculo;
	}

	public String getAnnoVehiculo() {
		return annoVehiculo;
	}

	public void setAnnoVehiculo(String annoVehiculo) {
		this.annoVehiculo = annoVehiculo;
	}

	public String getPlacaVehiculo() {
		return placaVehiculo;
	}

	public void setPlacaVehiculo(String placaVehiculo) {
		this.placaVehiculo = placaVehiculo;
	}

	public String getTipoVivienda() {
		return tipoVivienda;
	}

	public void setTipoVivienda(String tipoVivienda) {
		this.tipoVivienda = tipoVivienda;
	}

	public String getTenenciaVivienda() {
		return tenenciaVivienda;
	}

	public void setTenenciaVivienda(String tenenciaVivienda) {
		this.tenenciaVivienda = tenenciaVivienda;
	}

	public String getCombustibleCocinar() {
		return combustibleCocinar;
	}

	public void setCombustibleCocinar(String combustibleCocinar) {
		this.combustibleCocinar = combustibleCocinar;
	}

	public String getServicioAgua() {
		return servicioAgua;
	}

	public void setServicioAgua(String servicioAgua) {
		this.servicioAgua = servicioAgua;
	}

	public String getBasuraFinal() {
		return basuraFinal;
	}

	public void setBasuraFinal(String basuraFinal) {
		this.basuraFinal = basuraFinal;
	}

	public String getJefe() {
		return jefe;
	}

	public void setJefe(String jefe) {
		this.jefe = jefe;
	}

	public Integer getCuartos() {
		return cuartos;
	}

	public void setCuartos(Integer cuartos) {
		this.cuartos = cuartos;
	}

	public Integer getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(Integer vehiculos) {
		this.vehiculos = vehiculos;
	}

	public Integer getPersonasVivienda() {
		return personasVivienda;
	}

	public void setPersonasVivienda(Integer personasVivienda) {
		this.personasVivienda = personasVivienda;
	}

	public Integer getPersonasCarga() {
		return personasCarga;
	}

	public void setPersonasCarga(Integer personasCarga) {
		this.personasCarga = personasCarga;
	}

	public Timestamp getFechaLicencia() {
		return fechaLicencia;
	}

	public void setFechaLicencia(Timestamp fechaLicencia) {
		this.fechaLicencia = fechaLicencia;
	}

	public Timestamp getFechaCertificado() {
		return fechaCertificado;
	}

	public void setFechaCertificado(Timestamp fechaCertificado) {
		this.fechaCertificado = fechaCertificado;
	}

	public Timestamp getFechaFicha() {
		return fechaFicha;
	}

	public void setFechaFicha(Timestamp fechaFicha) {
		this.fechaFicha = fechaFicha;
	}
	
	

}
