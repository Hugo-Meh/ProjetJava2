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
import utils.GestionMail;

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
		String submit = request.getParameter("SignUp");
		if (submit.equals("S'inscrire"))

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
			String to = newUser.getUsername();
			
			String sujet ="validation d'inscription" ;
			
			String messageToSend =  ""
					+ "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>\r\n"
					+ "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\r\n"
					+ "<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n"
					+ "<head>\r\n"
					+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\" />\r\n"
					+ "<title>Votre Facture</title>\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
					+ "	<p style=\"color:red\">"
					+ "Bonjour dis nous si ca marche encore"
					+ "</p>\r\n"
					
					+ "</body>\r\n" + "</html>";	
			
			//GestionMail.sendEmail(messageToSend, to, sujet);
			//request.getRequestDispatcher("/WEB-INF/validateSignUp.html").forward(request, response);
			
		} else {
			request.setAttribute(C.ResponseSignUp, newUser.getUsername() + "   est dejà utilisé");
			request.setAttribute(C.UserErrorData, newUser);
			doGet(request, response);
		}
	}

}
