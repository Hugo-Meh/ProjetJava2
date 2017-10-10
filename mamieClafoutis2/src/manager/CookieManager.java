package manager;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {
	
	public static Cookie getCookie(String key, HttpServletRequest request){
		Cookie retour = null;
		
		Cookie[] cs = request.getCookies();
		
		if(cs != null)
			for(Cookie c : cs)
				if(c.getName().equalsIgnoreCase(key))
					retour = c;	
		
		return retour;
	}
	
	public static void setCookie(int temps, String key, String value, HttpServletResponse response){
		Cookie c = new Cookie(key, value);
		c.setMaxAge(temps);
		response.addCookie(c);
	}
	
	
	public static void supprimerCookie(String key, HttpServletRequest request, HttpServletResponse response){
		Cookie c = getCookie(key, request);
		c.setMaxAge(0);
		response.addCookie(c);
	}
	
	public static String getCookieValue(String key, HttpServletRequest request){
		String retout = null;
		Cookie c = getCookie(key, request);
		
		if(c != null)
			retout = c.getValue();

		return retout;
	} 
	
	
}
