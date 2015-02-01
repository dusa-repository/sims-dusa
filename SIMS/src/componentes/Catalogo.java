package componentes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
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

public abstract class Catalogo<Clase> extends Window {

	private static final long serialVersionUID = 1L;
	Listbox lsbCatalogo;

	public Catalogo(final Component cGenerico, String titulo,
			List<Clase> lista, boolean multiple, String... campos) {
		super(titulo, "2", true);
		this.setId("cmbCatalogo"+titulo);
		this.setStyle("background-header:#661313; background: #f4f2f2");
		setWidth("60%");
		crearLista(lista, campos, multiple);
		lsbCatalogo.addEventListener(Events.ON_SELECT,
				new EventListener<Event>() {

					@Override
					public void onEvent(Event arg0) throws Exception {
						Events.postEvent(cGenerico, new Event("onSeleccion"));
					}
				});
	}

	public void crearLista(List<Clase> lista, String[] campos,
			final boolean multiple) {
		Hbox hbxBusqueda = new Hbox();
		final Label lblBuscar = new Label();
		final Textbox txtBuscar = new Textbox();
		final Separator separador1 = new Separator();
		final Separator separador2 = new Separator();
		txtBuscar.setWidth("23em");
		txtBuscar.setPlaceholder("Introduzca el criterio de busqueda");
		final Combobox cmbBuscarPor = new Combobox();
		cmbBuscarPor.setReadonly(true);
		cmbBuscarPor.setPlaceholder("Seleccione el Campo");
		txtBuscar.addEventListener(Events.ON_CHANGING,
				new EventListener<InputEvent>() {
					@Override
					public void onEvent(InputEvent e) throws Exception {
						List<Clase> listaNueva = buscar(e.getValue(),
								cmbBuscarPor.getValue());
						lsbCatalogo.setModel(new ListModelList<Clase>(
								listaNueva));
						if (multiple) {
							lsbCatalogo.setMultiple(false);
							lsbCatalogo.setCheckmark(false);
							lsbCatalogo.setMultiple(true);
							lsbCatalogo.setCheckmark(true);
						}
					}
				});
		lsbCatalogo = new Listbox();
		lsbCatalogo.setMold("paging");
		lsbCatalogo.setPageSize(10);
		Listhead lhdEncabezado = new Listhead();
		for (int i = 0; i < campos.length; i++) {
			Listheader listHeader = new Listheader(campos[i]);
			listHeader.setHflex("min");
			lhdEncabezado.appendChild(listHeader);
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
					if (registros[i] != null) {
						if (registros[i]
								.contains("/WEB-INF/classes/controlador/")) {
							celda.setLabel(null);
							Image imagen = new Image();
							if (traerImagen(registros[i]) != null) {
								imagen.setContent(traerImagen(registros[i]));
								imagen.setParent(celda);
							} else
								celda.setLabel(registros[i]);
						}
					}
					celda.setParent(fila);
					// Listcell celda = new Listcell(registros[i]);
					// celda.setParent(fila);
				}
			}
		});
		lsbCatalogo.setWidth("100%");
		lsbCatalogo.setSpan("true");
		this.appendChild(separador1);
		this.appendChild(hbxBusqueda);
		lblBuscar.setValue("   Buscar Por :  ");
		lblBuscar.setSclass("etiqueta");
		hbxBusqueda.appendChild(lblBuscar);
		cmbBuscarPor.setModel(new ListModelList<String>(campos));
		hbxBusqueda.appendChild(cmbBuscarPor);
		hbxBusqueda.appendChild(txtBuscar);
		this.appendChild(separador2);
		this.appendChild(lsbCatalogo);
		if (multiple) {
			setWidth("100%");
			this.setTitle(null);
//			lsbCatalogo.setHflex("1");
			lsbCatalogo.setPageSize(15);
//			this.setHflex("1");
			lsbCatalogo.setHeight("450px");
			this.setClosable(false);
			lsbCatalogo.setMultiple(false);
			lsbCatalogo.setCheckmark(false);
			lsbCatalogo.setMultiple(true);
			lsbCatalogo.setCheckmark(true);
		}
	}

	/**
	 * Metodo que permite llamar un servicio dependiendo el controlador que
	 * busque, es decir que haga un filtro dentro del catalogo, ayudando asi al
	 * usuario a encontrar el registro buscado con mayor facilidad
	 */
	protected abstract List<Clase> buscar(String valor, String combo);

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

	private BufferedImage traerImagen(String string) throws IOException {
		BufferedImage imagenes;
		URI uri = null;
		try {
			uri = new URI(string);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		File fnew = new File(uri);
		imagenes = ImageIO.read(fnew);
		return imagenes;
	}

	public void actualizarLista(List<Clase> lista, boolean check) {
		lsbCatalogo.setModel(new ListModelList<Clase>(lista));
		if (check) {
			lsbCatalogo.setMultiple(false);
			lsbCatalogo.setCheckmark(false);
			lsbCatalogo.setMultiple(true);
			lsbCatalogo.setCheckmark(true);
		}
	}

	public List<Clase> obtenerSeleccionados() {
		List<Clase> valores = new ArrayList<Clase>();
		boolean entro = false;
		if (lsbCatalogo.getItemCount() != 0) {
			final List<Listitem> list1 = lsbCatalogo.getItems();
			for (int i = 0; i < list1.size(); i++) {
				if (list1.get(i).isSelected()) {
					Clase clase = list1.get(i).getValue();
					entro = true;
					valores.add(clase);
				}
			}
			if (!entro) {
				valores.clear();
				return valores;
			}
			return valores;
		} else
			return null;
	}
}