package servicio.maestros;

import interfacedao.maestros.IRecipeDAO;
import modelo.maestros.Recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SRecipe")
public class SRecipe {

	@Autowired
	private IRecipeDAO recipeDAO;
	
	public Recipe buscarUltimo(){
		long id = recipeDAO.findMaxIdRecipe();
		if (id != 0)
			return recipeDAO.findOne(id);
		else
			return null;
	}

	public void guardar(Recipe recipe) {
		recipeDAO.save(recipe);
	}

	public Recipe buscar(long recipe) {
		return recipeDAO.findOne(recipe);
	}
}
