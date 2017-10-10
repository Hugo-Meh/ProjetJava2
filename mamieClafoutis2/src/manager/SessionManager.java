package manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionManager {

	public static boolean addToSession (HttpServletRequest request, String key, Object value){
		boolean retour = false;
		HttpSession session = request.getSession(false);
		
		if(session != null){
			session.setAttribute(key, value);
		}
		return retour;
	}
	
	public static Object get(HttpServletRequest request, String key){
		Object retour =null;
		HttpSession session = request.getSession(false);
		
		if(session != null){
			retour = session.getAttribute(key);
		}
		
		return retour;	
	}
}
