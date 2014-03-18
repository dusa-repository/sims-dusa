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
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Tree;
import org.zkoss.zul.TreeModel;

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
	URL url = getClass().getResource("/configuracion/imgBien.jpg");
	List<String> listmenu1 = new ArrayList<String>();

	@Override
	public void inicializar() throws IOException {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		Usuario u = servicioUsuario.buscarUsuarioPorNombre(auth.getName());

//		if (u.getImagen().toString() == "[]") {
//			imagenes.setSrc("/configuracion/imgBien.jpg");
//		} else {
//			try {
//				BufferedImage imag;
//				imag = ImageIO.read(new ByteArrayInputStream(u.getImagen()));
//				imagenes.setContent(imag);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		arbolMenu.setModel(getModel());

		HashMap<String, Object> map = (HashMap<String, Object>) Sessions
				.getCurrent().getAttribute("Vistas");

		if (map != null) {
			if ((String) map.get("vista") != null) {
				contenido.setSrc("/vistas/" + (String) map.get("vista")
						+ ".zul");
			}
		}		
	}

	public TreeModel getModel() {
		if (_model == null) {
			_model = new MArbol(getFooRoot());
		}
		return _model;
	}

	private Nodos getFooRoot() {

		Nodos root = new Nodos(null, 0, "");
		Nodos oneLevelNode = new Nodos(null, 0, "");
		Nodos twoLevelNode = new Nodos(null, 0, "");
		Nodos threeLevelNode = new Nodos(null, 0, "");
		Nodos fourLevelNode = new Nodos(null, 0, "");

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
			arbol = servicioArbol.buscarPorNombreArbol(nombre);
			if (arbol != null)
				ids.add(arbol.getIdArbol());
			arbole.add(arbol);
		}

		Collections.sort(ids);
		for (int t = 0; t < ids.size(); t++) {
			Arbol a;
			a = servicioArbol.buscarPorId(ids.get(t));
			arboles.add(a);
		}

		long temp1, temp2, temp3 = 0;

		for (int i = 0; i < arboles.size(); i++) {

			if (arboles.get(i).getPadre() == 0) {
				oneLevelNode = new Nodos(root, i, arboles.get(i).getNombre());
				root.appendChild(oneLevelNode);
				temp1 = arboles.get(i).getIdArbol();
				arboles.remove(i);

				for (int j = i; j < arboles.size(); j++) {

					if (temp1 == arboles.get(j).getPadre()) {
						twoLevelNode = new Nodos(oneLevelNode, i, arboles
								.get(j).getNombre());
						oneLevelNode.appendChild(twoLevelNode);
						temp2 = arboles.get(j).getIdArbol();
						arboles.remove(j);

						for (int k = j; k < arboles.size(); k++) {

							if (temp2 == arboles.get(k).getPadre()) {
								threeLevelNode = new Nodos(twoLevelNode, i,
										arboles.get(k).getNombre());
								twoLevelNode.appendChild(threeLevelNode);
								temp3 = arboles.get(k).getIdArbol();
								arboles.remove(k);

								for (int z = k; z < arboles.size(); z++) {

									if (temp3 == arboles.get(z).getPadre()) {
										fourLevelNode = new Nodos(
												threeLevelNode, i, arboles.get(
														z).getNombre());
										threeLevelNode
												.appendChild(fourLevelNode);
										arboles.remove(z);

										z = z - 1;
									}
								}
								k = k - 1;
							}
						}
						j = j - 1;
					}
				}
				i = i - 1;
			}
		}
		return root;
	}

	@Listen("onClick = #arbolMenu")
	public void selectedNode() {
		String item = String.valueOf(arbolMenu.getSelectedItem().getValue());
		if (arbolMenu.getSelectedItem().getLevel() > 0) {
			Arbol arbolItem = servicioArbol.buscarPorNombreArbol(item);
			if(!arbolItem.getUrl().equals("inicio")){
			String ruta = "/vistas/" + arbolItem.getUrl() + ".zul";
			contenido.setSrc(null);
			contenido.setSrc(ruta);
			}
		}
	}	
}