package controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.ActionUser;

/**
 * Servlet implementation class ValidateSignUp
 */
@WebServlet("/validateSignUp")
public class ValidateSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateSignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idnewUser=request.getParameter("id");
		String token=request.getParameter("token");
		boolean isvalid;
		if(idnewUser!=null && token!=null){
			isvalid=ActionUser.verifyToken(Integer.parseInt(idnewUser), token);
			if(isvalid){
				ActionUser.validateuser(Integer.parseInt(idnewUser));
				response.sendRedirect("http://localhost:8080/mamieClafoutis/SignIn.jsp");
			}
		}
	}

}
