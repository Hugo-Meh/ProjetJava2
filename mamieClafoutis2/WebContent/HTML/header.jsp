
<%@ page import="entities.User"%>
<%@ page import="action.ActionUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header>
	<nav class="nav3">
		<ul>
			<%
				User user = null;
				user = (User) session.getAttribute("MyUser");
				// non connecté
				if (session.getAttribute("MyUser") == null) {
			%>
			<li><a href="SignIn.jsp">Connexion</a></li>
			<li><a href="signUp">Inscription</a></li>
			<li><a href="ServeletShowProduct">Produits</a></li>
			<%
				} else {
					if (user.getRoleId() == 5) {
			%>
			<li><a href="ServeletRedirectSales">Ventes</a></li>
			<%
				}
					// cuisinier
					else if (user.getRoleId() == 4) {
			%>

			<%
				} else if (user.getRoleId() == 3) {
			%>
			<li><a href="ServeletRedirectOrder">Commande</a></li>
			<%
				} else if (user.getRoleId() == 2) {
			%>
			<li><a href="ServeletRedirectOrder">Commande</a></li>
			<%
				} else if (user.getRoleId() == 1) {
			%>
			<li><a href="ServeletRedirectEditProduct">Produit</a></li>
			<li><a href="ServeletRedirectShowOrder">Commande</a></li>
			<li><a href="ServeletRedirectShowSales">Vente</a></li>
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
