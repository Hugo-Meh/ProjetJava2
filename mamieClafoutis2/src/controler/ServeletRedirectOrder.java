package controler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Map.Entry;

import java.util.Map;

import action.ActionOrder;
import action.ActionProduct;
import entities.User;

/**
 * Servlet implementation class ServeletRedirectOrder
 */
@WebServlet("/ServeletRedirectOrder")
public class ServeletRedirectOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServeletRedirectOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession(false).getAttribute("MyUser");
		ArrayList<Integer> authorized = new ArrayList<Integer>(Arrays.asList(3, 2));
		if (user == null || !authorized.contains(user.getRoleId())) {
			response.sendRedirect("Index.jsp");
		} else {
			ActionProduct.displayAll(request);
			request.getRequestDispatcher("WEB-INF/addOrder.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User User = (User) request.getSession(false).getAttribute("MyUser");

		System.out.println("i user " + User.getId());
		Map<String, String[]> Map = request.getParameterMap();

		ArrayList<String> data = new ArrayList<>();
		for (Entry<String, String[]> ligne : Map.entrySet()) {
			
			if (!ligne.getKey().equals("addOrder")) {
				for (String v : ligne.getValue()) {
					if (!v.equals("") && !v.equals("000") && !v.equals("valider commande"))
						data.add(v);
				}
			}
		}
		
		int size=data.size();
		if(size>0){
			int idOrder=ActionOrder.Insert(User.getId());
			for(int i=0;i!=size-i;i++){
				ActionProduct.InsertProductInOrderLine(idOrder, Integer.parseInt(data.get(i)),Integer.parseInt(data.get(size-i-1)));
				
			}
			System.out.println("taile "+ size);
		}
		
		response.sendRedirect("Index.jsp");
	}
}