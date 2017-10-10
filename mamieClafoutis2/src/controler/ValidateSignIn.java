package controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.ActionUser;
import entities.User;
import manager.CookieManager;
import manager.UserManager;
import service.C;

/**
 * Servlet implementation class ValidateSignIn
 */
@WebServlet("/validateSignIn")
public class ValidateSignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ValidateSignIn() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("identifiant");
		String pwd = request.getParameter("pwd");
		String remember = request.getParameter("remember");
		String url = "http://localhost:8080/mamieClafoutis/SignIn.jsp";
		System.out.println("login " + login);
		System.out.println("pwd " + pwd);
		User userconnect = ActionUser.verifyUser(login, pwd);
		if (userconnect != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute(C.User, UserManager.getAllInformationById(userconnect.getId()));
			System.out.println("votre connexion est confirmé");
			if (remember != null && remember.equals("yes")) {
				CookieManager.setCookie(60 * 60 * 24, "remember", "checked", response);

			} else {
				if (CookieManager.getCookie("remember", request) != null)
					CookieManager.supprimerCookie("remember", request, response);
			}
			url = "http://localhost:8080/mamieClafoutis/Index.jsp";
		} else {
			System.out.println("votre connexion a echoué");
		}
		response.sendRedirect(url);
	}

}
