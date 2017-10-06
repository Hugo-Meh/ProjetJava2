package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import entities.Product;
import manager.ProductManager;
import service.C;

public class ActionProduct {
	
	public static void displayAll(HttpServletRequest request) {
		ArrayList<Product> products = null;
		products = ProductManager.getAll();
		if(products != null){
			request.setAttribute(C.ProductList,products);
			}	
		}
	
	public static void displayProductByID(int id,HttpServletRequest request){
		Product product = null;
		product = ProductManager.getById(id);
		if(product != null){
			request.setAttribute(C.Product,product);
			}
	}

	public static void displayProductsByCategorieId(int id,HttpServletRequest request){
		ArrayList<Product> products = null;
		products = ProductManager.getByCategoryId(id);
		if(products != null){
			request.setAttribute(C.ProductList,products);
			}
	}
	
	public static void displayProductByName(String name,HttpServletRequest request){
		ArrayList<Product> products = null;
		products = ProductManager.getByName(name);
		if(products != null){
			request.setAttribute(C.ProductList,products);
			}
	}
	
	public static void addProduct(Product aProduct){
		if(aProduct != null){
			ProductManager.Insert(aProduct);
		}
	}
	
	public static void removeProduct(Product aProduct){
		if(aProduct != null){
			/*ProductManager.delete(aProduct);*/
		}
	}
	
	
}
