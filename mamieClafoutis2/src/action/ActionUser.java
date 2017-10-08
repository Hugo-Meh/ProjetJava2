package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import entities.User;
import manager.UserManager;
import service.C;

public class ActionUser {
	
	public static void setUser(String login, String pwd, HttpServletRequest request){
		User user = null;
		user = UserManager.getUser(login, pwd);
		if (UserManager.verifyUser(login, pwd) ){
			HttpSession session = request.getSession(true);
			session.setAttribute(C.User, UserManager.getAllInformationById(user.getId()));;
		}
	}
	
	public static void insertUser(User newUser){
		User user = newUser;
		
	}
}
