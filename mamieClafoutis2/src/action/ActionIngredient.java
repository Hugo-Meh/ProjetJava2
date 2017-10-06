package action;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import entities.Ingredient;
import manager.IngredientManager;
import service.C;

public class ActionIngredient {
	public static void displayAll(HttpServletRequest request){
		ArrayList <Ingredient> ingredients = null;
		ingredients = IngredientManager.getAll();
		if (ingredients != null){
			request.setAttribute(C.IngredientList, ingredients);
		}
	}
	/* displayIngredientsByProductId (){

	}*/
	public static void addIngredients (Ingredient anIngredient){
		if(anIngredient != null){
			IngredientManager.Insert(anIngredient);
		}
	}
	
	public static void removeIngredient (Ingredient anIngredient){
		if(anIngredient != null){
			/*IngredientManager.delete(anIngredient);*/
		}
	}
	
	public static void uptdateIngredient(Ingredient anIngredient){
		if (anIngredient != null){
			IngredientManager.Update(anIngredient);
		}
	}
	
	
}
