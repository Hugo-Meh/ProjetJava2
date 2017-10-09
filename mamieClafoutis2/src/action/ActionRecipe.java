package action;

import javax.servlet.http.HttpServletRequest;
import entities.Recipe;
import manager.RecipeManager;

public class ActionRecipe {
	
	public static void displayRecipe(int id, HttpServletRequest request){
		String recette = null;
		
	}
	
	public static void insert(Recipe recipe){
		if (recipe != null){
			RecipeManager.insert(recipe);
		}
	}
	
	public static void update(Recipe recipe){
		if (recipe != null){
			RecipeManager.update(recipe);
		}
	}	
}
