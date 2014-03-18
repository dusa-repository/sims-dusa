package controlador.maestros;

import java.io.IOException;

import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;

import componentes.Botonera;


public class CPresentacion extends CGenerico{
	
	private static final long serialVersionUID = 6414734962746880324L;
	
	@Wire
	private Div botoneraPresentacion;

	@Override
	public void inicializar() throws IOException {
		Botonera botonera = new Botonera() {
			@Override
			public void guardar() {

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
		/*Dibuja el componente botonera en el div botoneraPresentacionr*/
		botoneraPresentacion.appendChild(botonera);
	}

		
	}

