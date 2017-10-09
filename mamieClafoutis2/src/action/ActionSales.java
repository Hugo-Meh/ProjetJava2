package action;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import entities.Sales;
import manager.SalesManager;
import service.C;

public class ActionSales {
	
	public static void displayAll(HttpServletRequest request){
		ArrayList <Sales> sales = null;
		sales = SalesManager.getAll();
		if(sales != null){
			request.setAttribute(C.SaleList,sales);
		}
	}
	
	public static void displayByIdEstablishement (int[] id, Date dateFirst, Date dateEnd, HttpServletRequest request){
		ArrayList <Sales> sales = null;
		sales = SalesManager.getByIdEtablishement(id, dateFirst, dateEnd);
		if(sales != null){
			request.setAttribute(C.SaleList,sales);
		}
	}
	
	public static void displayStatByEstablishment(int[] id, Date dateFirst, Date dateEnd, HttpServletRequest request){
		ArrayList <Sales> sales = null;
		sales = SalesManager.getStatByEtablishement(id, dateFirst, dateEnd);
		if(sales != null){
			request.setAttribute(C.SaleList,sales);
		}
	}
	
	public static void Insert(Sales sales){
		if (sales != null){
			SalesManager.Insert(sales);
		}
	}
	
	public static void Update(Date date, Sales sales){
		if (sales != null){
			SalesManager.Update(date, sales);
		}
	}
	
	public static void isAddedInLastHour(Sales sales){
		Date date = SalesManager.isAddedInLastHour(sales.getProductId(),sales.getUserId());
		if(date != null){
			Update(date,sales);
		}
	}
}
