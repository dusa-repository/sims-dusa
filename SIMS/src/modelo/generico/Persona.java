package modelo.generico;

public class Persona {

	private String cedula;
	private String nombre;
	private String apellido;
	private String fechaNacimiento;
	private String lugarNacimiento;
	private String direccion;
	private String sexo;
	private String nivelEducacion;
	private String jubilado;
	private String verificacionRH;
	private String trabajador;
	private String parentesco;
	private String servicioMedico;
	private Integer Edad;
	private String vive;

	public Persona(String cedula, String nombre, String apellido,
			String fechaNacimiento, String lugarNacimiento, String direccion,
			String sexo, String nivelEducacion, String jubilado,
			String verificacionRH, String trabajador, String par,
			String servicioMedico) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.lugarNacimiento = lugarNacimiento;
		this.direccion = direccion;
		this.sexo = sexo;
		this.nivelEducacion = nivelEducacion;
		this.jubilado = jubilado;
		this.verificacionRH = verificacionRH;
		this.trabajador = trabajador;
		this.servicioMedico = servicioMedico;
		this.parentesco = par;
	}

	public Persona() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getLugarNacimiento() {
		return lugarNacimiento;
	}

	public void setLugarNacimiento(String lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getNivelEducacion() {
		return nivelEducacion;
	}

	public void setNivelEducacion(String nivelEducacion) {
		this.nivelEducacion = nivelEducacion;
	}

	public String getJubilado() {
		return jubilado;
	}

	public void setJubilado(String jubilado) {
		this.jubilado = jubilado;
	}

	public String getVerificacionRH() {
		return verificacionRH;
	}

	public void setVerificacionRH(String verificacionRH) {
		this.verificacionRH = verificacionRH;
	}

	public String getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(String trabajador) {
		this.trabajador = trabajador;
	}

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}

	public String getServicioMedico() {
		return servicioMedico;
	}

	public void setServicioMedico(String servicioMedico) {
		this.servicioMedico = servicioMedico;
	}

	public Integer getEdad() {
		return Edad;
	}

	public void setEdad(Integer edad) {
		Edad = edad;
	}

	public String getVive() {
		return vive;
	}

	public void setVive(String vive) {
		this.vive = vive;
	}

}
