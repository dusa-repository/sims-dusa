package arbol;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import modelo.seguridad.Arbol;
import modelo.seguridad.Usuario;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tree;
import org.zkoss.zul.TreeModel;
import org.zkoss.zul.West;

import componentes.Validador;

import controlador.maestros.CGenerico;

public class CArbol extends CGenerico {

	private static final long serialVersionUID = 4640739858132196250L;
	@Wire
	private Tree arbolMenu;
	@Wire
	private Include contenido;
	@Wire
	private Label etiqueta;
	@Wire
	private Image imagenes;
	TreeModel _model;
	URL url = getClass().getResource("/controlador/maestros/usuario.png");
	List<String> listmenu1 = new ArrayList<String>();
	@Wire
	private Tab tab;
	@Wire
	private Tabbox tabBox;
	@Wire
	private West west;

	private static Tabbox tabBox2;
	private static Include contenido2;
	private static Tab tab2;

	@Override
	public void inicializar() throws IOException {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		Usuario u = servicioUsuario.buscarUsuarioPorNombre(auth.getName());

		if (u.getImagen() == null) {
			imagenes.setContent(new AImage(url));
		} else {
			try {
				BufferedImage imag;
				imag = ImageIO.read(new ByteArrayInputStream(u.getImagen()));
				imagenes.setContent(imag);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		arbolMenu.setModel(getModel());

		HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("Vistas");

		if (tabs.size() != 0) {
			tabs.clear();
		}

		if (map != null) {
			if ((String) map.get("vista") != null) {
				contenido.setSrc("/vistas/" + (String) map.get("vista")
						+ ".zul");
			}
		}
	}

	/* Permite asignarle los nodos cargados con el metodo getFooRoot() al arbol */
	public TreeModel getModel() {
		if (_model == null) {
			_model = new MArbol(getFooRoot());
		}
		return _model;
	}

	/*
	 * Permite obtener las funcionalidades asociadas al usuario en session y asi
	 * crear un arbol estructurado, segun la distribucion de las mismas
	 */
	private Nodos getFooRoot() {

		Nodos root = new Nodos(null, 0, "");

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(
				auth.getAuthorities());

		ArrayList<Arbol> arbole = new ArrayList<Arbol>();
		List<Arbol> arboles = new ArrayList<Arbol>();
		ArrayList<Long> ids = new ArrayList<Long>();
		for (int k = 0; k < authorities.size(); k++) {

			Arbol arbol;
			String nombre = authorities.get(k).toString();
			if (Validador.validarNumero(nombre)) {
				arbol = servicioArbol.buscar(Long.parseLong(nombre));
				if (arbol != null)
					ids.add(arbol.getIdArbol());
				arbole.add(arbol);
			}
		}

		Collections.sort(ids);
		for (int t = 0; t < ids.size(); t++) {
			Arbol a;
			a = servicioArbol.buscarPorId(ids.get(t));
			arboles.add(a);
		}

		List<Long> idsPadre = new ArrayList<Long>();
		List<Nodos> nodos = new ArrayList<Nodos>();
		return crearArbol(root, nodos, arboles, 0, idsPadre);
		
	}

	private Nodos crearArbol(Nodos roote, List<Nodos> nodos,
			List<Arbol> arboles, int i, List<Long> idsPadre) {
		for (int z = 0; z < arboles.size(); z++) {
			Nodos oneLevelNode = new Nodos(null, 0, "");
			Nodos two = new Nodos(null, 0, "");
			if (arboles.get(z).getPadre() == 0) {
				oneLevelNode = new Nodos(roote, z, arboles.get(z).getNombre());
				roote.appendChild(oneLevelNode);
				idsPadre.add(arboles.get(z).getIdArbol());
				nodos.add(oneLevelNode);
			} else {
				for (int j = 0; j < idsPadre.size(); j++)
					if (idsPadre.get(j) == arboles.get(z).getPadre()) {
						oneLevelNode = nodos.get(j);
						two = new Nodos(oneLevelNode, z, arboles.get(z)
								.getNombre());
						oneLevelNode.appendChild(two);
						idsPadre.add(arboles.get(z).getIdArbol());
						nodos.add(two);
					}
			}
		}
		return roote;
	}

	/*
	 * Permite seleccionar un elemento del arbol, mostrandolo en forma de
	 * pestaña y su contenido es cargado en un div
	 */
	@Listen("onClick = #arbolMenu")
	public void selectedNode() {
		if (arbolMenu.getSelectedItem() != null) {
			String item = String
					.valueOf(arbolMenu.getSelectedItem().getValue());
			boolean abrir = true;
			Tab taba = new Tab();
			if (arbolMenu.getSelectedItem().getLevel() > 0) {
				final Arbol arbolItem = servicioArbol
						.buscarPorNombreArbol(item);
				if (!arbolItem.getUrl().equals("inicio")) {

					if (String.valueOf(arbolMenu.getSelectedItem().getValue())
							.equals("Consulta"))
						west.setOpen(false);
					for (int i = 0; i < tabs.size(); i++) {
						if (tabs.get(i).getLabel()
								.equals(arbolItem.getNombre())) {
							abrir = false;
							taba = tabs.get(i);
						}
					}
					if (abrir) {
						String ruta = "/vistas/" + arbolItem.getUrl() + ".zul";
						contenido = new Include();
						contenido.setSrc(null);
						contenido.setSrc(ruta);
						Tab newTab = new Tab(arbolItem.getNombre());
						newTab.setClosable(true);
						newTab.addEventListener(Events.ON_CLOSE,
								new EventListener<Event>() {
									@Override
									public void onEvent(Event arg0)
											throws Exception {
										for (int i = 0; i < tabs.size(); i++) {
											if (tabs.get(i)
													.getLabel()
													.equals(arbolItem
															.getNombre())) {
												if (i == (tabs.size() - 1)
														&& tabs.size() > 1) {
													tabs.get(i - 1)
															.setSelected(true);
												}

												tabs.get(i).close();
												tabs.remove(i);
											}
										}
									}
								});
						newTab.setSelected(true);
						Tabpanel newTabpanel = new Tabpanel();
						newTabpanel.appendChild(contenido);
						tabBox.getTabs().insertBefore(newTab, tab);
						newTabpanel.setParent(tabBox.getTabpanels());
						tabs.add(newTab);
					} else {
						taba.setSelected(true);
					}
				}
			}
		}
		tabBox2 = tabBox;
		contenido2 = contenido;
		tab2 = tab;
	}

	public void abrirVentanas(final Arbol arbolItem) {
		boolean abrir = true;
		Tab taba = new Tab();
		if (!arbolItem.getUrl().equals("inicio")) {
			for (int i = 0; i < tabs.size(); i++) {
				if (tabs.get(i).getLabel().equals(arbolItem.getNombre())) {
					abrir = false;
					taba = tabs.get(i);
				}
			}
			if (abrir) {
				String ruta = "/vistas/" + arbolItem.getUrl() + ".zul";
				contenido2 = new Include();
				contenido2.setSrc(null);
				contenido2.setSrc(ruta);

				Tab newTab = new Tab(arbolItem.getNombre());
				newTab.setClosable(true);
				newTab.addEventListener(Events.ON_CLOSE,
						new EventListener<Event>() {
							@Override
							public void onEvent(Event arg0) throws Exception {
								for (int i = 0; i < tabs.size(); i++) {
									if (tabs.get(i).getLabel()
											.equals(arbolItem.getNombre())) {
										if (i == (tabs.size() - 1)
												&& tabs.size() > 1) {
											tabs.get(i - 1).setSelected(true);
										}

										tabs.get(i).close();
										tabs.remove(i);
									}
								}
							}
						});
				newTab.setSelected(true);
				Tabpanel newTabpanel = new Tabpanel();
				newTabpanel.appendChild(contenido2);
				tabBox2.getTabs().insertBefore(newTab, tab2);
				newTabpanel.setParent(tabBox2.getTabpanels());
				tabs.add(newTab);
			} else {
				taba.setSelected(true);
			}
		}
	}

	/* Metodo que permite abrir la ventana de editar usuario en una pestaña */
	@Listen("onClick = #lblEditarCuenta")
	public void abrirEditarCuenta() {
		boolean abrir = true;
		Tab taba = new Tab();
		for (int i = 0; i < tabs.size(); i++) {
			if (tabs.get(i).getLabel().equals("Editar Usuario")) {
				abrir = false;
				taba = tabs.get(i);
			}
		}
		if (abrir) {
			String ruta = "/vistas/seguridad/VEditarUsuario.zul";
			contenido = new Include();
			contenido.setSrc(null);
			contenido.setSrc(ruta);
			Tab newTab = new Tab("Editar Usuario");
			newTab.setSelected(true);
			Tabpanel newTabpanel = new Tabpanel();
			newTabpanel.appendChild(contenido);
			tabBox.getTabs().insertBefore(newTab, tab);
			newTabpanel.setParent(tabBox.getTabpanels());
			tabs.add(newTab);
		} else
			taba.setSelected(true);
	}
}