package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import entities.Establishment;
import manager.EstablishmentManager;
import service.C;

public class ActionEstablishment {
	
	public static void displayAll(HttpServletRequest request){
		ArrayList <Establishment> establishments = null;
		establishments = EstablishmentManager.getAll();
		if (establishments != null){
			request.setAttribute(C.EstablishmentList, establishments);
		}
	}
	
	public static void displayByUserId(int id, HttpServletRequest request){
		Establishment establishment = null;
		establishment = EstablishmentManager.getByIdUser(id);
		if (establishment != null){
			request.setAttribute(C.Establisment, establishment);
		}
	}
	
	public static void displayByType(String type, HttpServletRequest request){
		ArrayList <Establishment> establishments = null;
		establishments = EstablishmentManager.getBytype(type);
		if (establishments != null){
			request.setAttribute(C.EstablishmentList, establishments);
		}
	}
	
	public static void insert (Establishment etab){
		if (etab != null){
			EstablishmentManager.Insert(etab);
		}
	}
	
	public static void update (Establishment etab){
		if (etab != null){
			EstablishmentManager.Update(etab);
		}
	}
}
