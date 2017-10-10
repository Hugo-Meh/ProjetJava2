package controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.ActionCategory;
import action.ActionProduct;

/**
 * Servlet implementation class ServeletShowProduct
 */
@WebServlet("/ServeletShowProduct")
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
		String filter = request.getParameter("categorie");
		if(filter == null || filter == ""){
			ActionCategory.displayAll(request);
			ActionProduct.displayAll(request);
			request.getRequestDispatcher("Product.jsp").forward(request, response);
		}
		
		else{
			int idCat = Integer.parseInt(filter);
			ActionCategory.displayAll(request);
			ActionProduct.displayProductsByCategorieId(idCat, request);
			request.getRequestDispatcher("Product.jsp").forward(request, response);
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
