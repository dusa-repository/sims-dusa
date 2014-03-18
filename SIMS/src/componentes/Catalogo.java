package componentes;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 * 
 * Autor: Michell Lobo Revisado por: Michell Lobo Version: 1.0 Fecha Creacion:
 * 08/12/2013
 * 
 * ---------------------------- HISTORIAL DE MODIFICACIONES Autor: Michell Lobo
 * Revisado por: Michell Lobo Version: 1.1 Fecha Creacion: 02/02/2014 Especifica
 * por los campos que busca y se le queto lo transparente
 * 
 * 
 */

// Por hacer:
// - Que busque solo los activos

public abstract class Catalogo<Clase> extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Listbox lsbCatalogo;

	public Catalogo(final Component cGenerico, String titulo,
			List<Clase> lista, String... campos) {
		super(titulo, "2", true);
		this.setId("cmpCatalogo");
		this.setStyle("border:solid  #FF7925; background-header:#FF7925; background: #f4f2f2");
		setWidth("60%");
		crearLista(lista, campos);
		lsbCatalogo.addEventListener(Events.ON_SELECT,
				new EventListener<Event>() {

					@Override
					public void onEvent(Event arg0) throws Exception {
						Events.postEvent(cGenerico, new Event("onSeleccion"));
					}
				});
	}

	public void crearLista(List<Clase> lista, String[] campos) {
		Hbox hbxBusqueda = new Hbox();
		final Label lblBuscar = new Label();
		final Textbox txtBuscar = new Textbox();
		final Separator separador1 = new Separator();
		final Separator separador2 = new Separator();
		txtBuscar.setWidth("32em");
		txtBuscar.setPlaceholder("Introduzca el criterio de busqueda");
		final Label lblBuscarPor = new Label();
		lblBuscar.setZclass("etiqueta");
		lblBuscarPor.setZclass("etiqueta");
		lblBuscar.setValue("Buscar por los campos: ");
		txtBuscar.addEventListener(Events.ON_CHANGING,
				new EventListener<InputEvent>() {
					@Override
					public void onEvent(InputEvent e) throws Exception {
						List<Clase> listaNueva = buscar(e.getValue());
						lsbCatalogo.setModel(new ListModelList<Clase>(
								listaNueva));
					}
				});
		lsbCatalogo = new Listbox();
		lsbCatalogo.setMold("paging");
		lsbCatalogo.setPageSize(10);
		Listhead lhdEncabezado = new Listhead();
		for (int i = 0; i < campos.length; i++) {
			lhdEncabezado.appendChild(new Listheader(campos[i]));
		}
		lsbCatalogo.appendChild(lhdEncabezado);
		lhdEncabezado.setVisible(true);
		lsbCatalogo.setModel(new ListModelList<Clase>(lista));
		lsbCatalogo.setItemRenderer(new ListitemRenderer<Clase>() {

			@Override
			public void render(Listitem fila, Clase objeto, int arg2)
					throws Exception {
				fila.setValue(objeto);
				String[] registros = crearRegistros(objeto);
				for (int i = 0; i < registros.length; i++) {
					Listcell celda = new Listcell(registros[i]);
					celda.setParent(fila);
				}
			}
		});
		String lista2 = new String();
		lista2 = lista2.concat("(");
		if (campos.length != 1) {
			for (int i = 0; i < (campos.length - 2); i++) {
				lista2 = lista2.concat(campos[i] + ", ");
			}
			lista2 = lista2.concat(campos[campos.length - 2] + " y "
					+ campos[campos.length - 1] + ")");
		} else
			lista2 = lista2.concat(campos[campos.length - 1]);
		this.appendChild(separador1);
		this.appendChild(hbxBusqueda);
		hbxBusqueda.appendChild(lblBuscar);
		lblBuscarPor.setValue(lista2);
		hbxBusqueda.appendChild(lblBuscarPor);
		hbxBusqueda.appendChild(txtBuscar);
		this.appendChild(separador2);
		this.appendChild(lsbCatalogo);
	}

	/**
	 * Metodo que permite llamar un servicio dependiendo el controlador que
	 * busque, es decir que haga un filtro dentro del catalogo, ayudando asi al
	 * usuario a encontrar el registro buscado con mayor facilidad
	 */
	protected abstract List<Clase> buscar(String valor);

	/**
	 * Metodo que permite por cada controlador indicar cuales son los registros
	 * que quiere mostrar en su catalogo, formando una matriz de String
	 */
	protected abstract String[] crearRegistros(Clase objeto);

	public Clase objetoSeleccionadoDelCatalogo() {
		return lsbCatalogo.getSelectedItem().getValue();
	}

	public Listbox getListbox() {
		return lsbCatalogo;
	}
}