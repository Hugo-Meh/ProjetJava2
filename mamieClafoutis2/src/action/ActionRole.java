package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import entities.Role;
import manager.RoleManager;
import service.C;

public class ActionRole {
	public static void displayAll(HttpServletRequest request){
		ArrayList<Role> allrole= RoleManager.getAll();
		if(allrole!=null){
			request.setAttribute(C.RoleList, allrole);
		}
	}
}
