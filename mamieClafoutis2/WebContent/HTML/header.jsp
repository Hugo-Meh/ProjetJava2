
<%@ page import="entities.User"%>
<%@ page import="action.ActionUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	User user = null;
	user = (User) session.getAttribute("MyUser"); 
		%>
<header>
	<div class="header-top">
		<div class="logo">
			<img src="Image/Logo/mamieclafoutis_logo.png" />
		</div>
		<div class="message-User">
		<% 
		if (user != null) {
		%>
		<h2>Bienvenue <%=user.getLastName() + user.getFirstName()%></h2>
		<%
		}
		%>
		</div>
		<div class="shoppingCart">
			<div>
				<img src="Image/Icon/shopping-cart.png" />
			</div>	
			<div>
				<p></p>
				<p></p>
			</div>
		</div>
	</div>
	<nav class="nav3">
		<ul>
			<li><a href="Index.jsp">Accueil</a></li>
			<%
				
				// non connecté
				if (user == null) {
			%>
			
			<li><a href="SignIn.jsp">Connexion</a></li>
			<li><a href="SignUp.jsp">Inscription</a></li>
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
