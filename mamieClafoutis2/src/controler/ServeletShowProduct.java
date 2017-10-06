package controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.ActionProduct;
/**
 * Servlet implementation class ServeletShowProduct
 */
@WebServlet(name = "serveletProduct", urlPatterns = { "/serveletProduct" })
public class ServeletShowProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServeletShowProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> params = new ArrayList<String>(Collections.list(request.getParameterNames()));
		/*switch (){
		case "getAll":
			ActionProduct.displayAll(request);
			break;
		case "getById":
			ActionProduct.displayProductByID(Integer.parseInt((String) request.getAttribute("id")));
		}*/
		if (params.contains("id")){
			ActionProduct.displayProductByID(Integer.parseInt(request.getParameter("id")),request);
		}
		
		else {
			ActionProduct.displayAll(request);
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
