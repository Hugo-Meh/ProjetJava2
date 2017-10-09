
<%@ page import="entities.User"%>
<%@ page import="action.ActionUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<header>
	<nav class="nav3">
		<ul>
			<%
				/*HttpSession session = request.getSession(true)*/User user = (User) session.getAttribute("MyUser");
				// non connecté
				if (session.getAttribute("MyUser") != null) {
			%>
			<li><a href="SignIn.jsp">Connexion</a></li>
			<li><a href="SignUp.jsp">Inscription</a></li>
			<li><a href="Product.jsp">Produits</a></li>
			<%
				} else {
					if (user.getRoleId() == 5) {
			%>
			<li><a href="Sales.jsp">Ventes</a></li>
			<%
				}
					// cuisinier
					else if (user.getRoleId() == 4) {
			%>

			<%
				} else if (user.getRoleId() == 3) {
			%>
			<li><a href="Order.jsp">Commande</a></li>
			<%
				} else if (user.getRoleId() == 2) {
			%>
			<li><a href="Order.jsp">Commande</a></li>
			<%
				} else if (user.getRoleId() == 1) {
			%>
			<li><a href="ShowOrders.jsp">Commande</a></li>
			<li><a href="ShowSales.jsp">Vente</a></li>
			<%
				}
			%>
			<li><a href="ServeletDeconnect">Deconnexion</a></li>
			<%
				}
			%>
		</ul>
	</nav>

</header>
