package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.User;
import manager.UserManager;
import service.C;

public class ActionUser {
	
	public static User verifyUser(String login, String pwd){
		User user=null;
		if (UserManager.verifyUser(login, pwd) ){
			user=UserManager.getUser(login, pwd);
		}
		
		return user;
	}
	
	public static int insertUser( HttpServletRequest request,HttpServletResponse response,User newUser){
	 
		int retour= UserManager.Insert(newUser);
		
	return retour;	
	}
	
	public static boolean verifyUserName(String login){
		boolean retour=false;
		retour=UserManager.VerifyNameUser(login);
		return retour;
	}
	
	public static boolean verifyToken(int id,String token){
		boolean retour=false;
		retour=UserManager.validateToken(id, token);
		return retour;
	}
	
	public static void validateuser(int id){
		UserManager.validateUser(id);
	}
}
