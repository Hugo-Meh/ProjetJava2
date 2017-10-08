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
	public static void displayIngredientsByProductId (int id,HttpServletRequest request){
		ArrayList <Ingredient> ingredients = null;
		ingredients = IngredientManager.getAllIngredientByIdProduct(id);
		if (ingredients != null){
			request.setAttribute(C.IngredientList, ingredients);
		}
	}
	
	
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
	
	public static void uptdateIngredientByIdProduct(int id,Ingredient anIngredient){
		if (anIngredient != null /*ajouter le empty requete product*/){
			IngredientManager.UpdateIngeredientByIdProduct(id, anIngredient);
		}
	}
	 /*verif pour le produit existant*/
	
	
}
