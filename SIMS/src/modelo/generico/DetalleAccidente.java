package modelo.generico;

import java.sql.Timestamp;

import modelo.maestros.Accidente;

public class DetalleAccidente {
	
	private long diagnostico;
	private String lugar;
	private String motivo;
	private String clasificacion;
	private Accidente accidente;
	private Timestamp fecha;
	public DetalleAccidente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DetalleAccidente(long diagnostico, String lugar,
			String motivo,String clasificacion, Timestamp fecha, Accidente accidente) {
		super();
		this.diagnostico = diagnostico;
		this.lugar = lugar;
		this.motivo = motivo;
		this.fecha = fecha;
		this.clasificacion = clasificacion;
		this.accidente = accidente;
	}
	public long getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(long diagnostico) {
		this.diagnostico = diagnostico;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	public Accidente getAccidente() {
		return accidente;
	}
	public void setAccidente(Accidente accidente) {
		this.accidente = accidente;
	}
	
	
}
