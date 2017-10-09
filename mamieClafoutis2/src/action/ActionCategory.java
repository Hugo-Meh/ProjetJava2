package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import entities.Category;
import manager.CategoryManager;
import service.C;

public class ActionCategory {
	
	public static void displayAll(HttpServletRequest request){
		ArrayList <Category> categories = null;
		categories = CategoryManager.getAll();
		if (categories != null){
			request.setAttribute(C.CategoryList, categories);
		}
	}
	
	public static void displaycategorieNameByIdProduct (int id, HttpServletRequest request){
		String categorie = null;
		categorie = CategoryManager.getCategoryNameByIdProduct(id);
		if (categorie != null){
			request.setAttribute(C.CategoryList, categorie);
		}
	}
	
	public static void Insert(String Category){
		if (Category != null){
			CategoryManager.InsertCategory(Category);
		}
	}
}
