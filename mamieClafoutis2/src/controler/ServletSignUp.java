package controler;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.ActionEstablishment;
import action.ActionRole;
import action.ActionUser;
import entities.User;
import service.C;

/**
 * Servlet implementation class ServletSignUp
 */
@WebServlet("/signUp")
public class ServletSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletSignUp() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionEstablishment.displayAll(request);
		ActionRole.displayAll(request);
		request.getRequestDispatcher("/SignUp.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean testSignup = false;
		User newUser=null;
		String submit = request.getParameter("signup");
		if (submit.equals("Sign Up"))

		{
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String mail = request.getParameter("mail");
			String pwd = request.getParameter("pwd");
			String etab = request.getParameter("etab");
			String role = request.getParameter("role");
			String token = UUID.randomUUID().toString();
			newUser = new User(nom, prenom, Integer.parseInt(etab), Integer.parseInt(role), mail, token, pwd);
			if(ActionUser.verifyUserName(mail)){
			testSignup = ActionUser.insertUser(request, response, newUser);
			}else{
				testSignup=false;
			}
		}
		
		if (testSignup) {
			request.getRequestDispatcher("/WEB-INF/validateSignUp.html").forward(request, response);
		} else {
			request.setAttribute(C.ResponseSignUp, newUser.getUsername() + "   est dejà utilisé");
			request.setAttribute(C.UserErrorData, newUser);
			doGet(request, response);
		}
	}

}
