package controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.ActionOrder;
import action.ActionProduct;
import entities.User;

/**
 * Servlet implementation class ServeletRedirectShowOrder
 */
@WebServlet("/ServeletRedirectShowOrder")
public class ServeletRedirectShowOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServeletRedirectShowOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession(false).getAttribute("MyUser");
		String lineOrder=request.getParameter("idOrder");
		if (user == null || user.getRoleId() != 1) {
			response.sendRedirect("Index.jsp");
		} else {
			if(lineOrder!=null){
				ActionProduct.DisplayByIdOrder(Integer.parseInt(lineOrder), request);
				
			}else
			ActionOrder.displayAll(request);
			
			request.getRequestDispatcher("WEB-INF/ShowOrder.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
