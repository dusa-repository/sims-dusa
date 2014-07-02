package modelo.generico;

import java.sql.Timestamp;

import modelo.maestros.Diagnostico;

public class DetalleAccidente {
	
	private long diagnostico;
	private String lugar;
	private String motivo;
	private Timestamp fecha;
	public DetalleAccidente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DetalleAccidente(long diagnostico, String lugar,
			String motivo, Timestamp fecha) {
		super();
		this.diagnostico = diagnostico;
		this.lugar = lugar;
		this.motivo = motivo;
		this.fecha = fecha;
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
	
	
}
