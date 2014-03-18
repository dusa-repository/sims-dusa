package controlador.maestros;

import java.io.IOException;

import modelo.maestros.Categoria;

import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Textbox;

import com.sun.jmx.snmp.Timestamp;

import componentes.Botonera;

public class CCategoria extends CGenerico {

	private static final long serialVersionUID = 3977153060950873260L;
	@Wire
	private Div botoneraCategoria;
	@Wire
	private Textbox txtNombreCategoria;
	private long id;
	
	@Override
	public void inicializar() throws IOException {
		// TODO Auto-generated method stub
		Botonera botonera = new Botonera() {
			@Override
			public void guardar() {
				String nombre = txtNombreCategoria.getValue();
				java.sql.Timestamp cosa = null;
				Categoria categoria = new Categoria(id, cosa, horaAuditoria, nombre, nombreUsuarioSesion());
				servicioCategoria.guardar(categoria);
			}

			@Override
			public void limpiar() {

			}

			@Override
			public void salir() {

			}

			@Override
			public void eliminar() {

			}
		};
		botoneraCategoria.appendChild(botonera);
	}

}
