package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	
	public static boolean insertUser( HttpServletRequest request,HttpServletResponse response,User newUser){
	 
		boolean retour= UserManager.Insert(newUser);
		
	return retour;	
	}
	
	public static boolean verifyUserName(String login){
		boolean retour=false;
		retour=UserManager.VerifyNameUser(login);
		return retour;
	}
}
