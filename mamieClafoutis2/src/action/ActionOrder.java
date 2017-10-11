package action;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import manager.IngredientManager;
import manager.OrderManager;
import service.C;
import entities.Order;

public class ActionOrder {
	public static void displayAll( HttpServletRequest request){
		ArrayList<Order> allOrder= OrderManager.getAll();
		if(allOrder!=null){
			request.setAttribute(C.OrderList, allOrder);
		}
		
		
	}
	
	public static void displayValidebyAdminIntoDate (Date dateStart, Date dateEnd, HttpServletRequest request){
		ArrayList<Order> orders = null;
		orders = OrderManager.getValidateByAdminIntoDate(dateStart,dateEnd);
		if(orders != null){
			request.setAttribute(C.OrderList,orders);
			}
	}
	
	public static void displayByCostumersIntoDate (int[] id_etablishment, Date dateStart, Date dateEnd, HttpServletRequest request){
		ArrayList<Order> orders = null;
		orders = OrderManager.getByCostumersIntoDate(id_etablishment, dateStart, dateEnd);
		if(orders != null){
			request.setAttribute(C.OrderList,orders);
			}
	}
	
	public static void getValidateByManufactureIntoDate(Date dateStart, Date dateEnd, HttpServletRequest request){
		ArrayList<Order> orders = null;
		orders = OrderManager.getValidateByManufactureIntoDate(dateStart, dateEnd);
		if(orders != null){
			request.setAttribute(C.OrderList,orders);
			}
	}
	
	public static void displayByIdUser(int User, Date dateStart, Date dateEnd, HttpServletRequest request){
		ArrayList<Order> orders = null;
		orders = OrderManager.getByIdUser(User, dateStart, dateEnd);
		if(orders != null){
			request.setAttribute(C.OrderList,orders);
			}
	}
	
	public static void Insert(int id, Order anOrder){
		if (anOrder != null){
			OrderManager.Insert(id,anOrder);
		}
	}
}
