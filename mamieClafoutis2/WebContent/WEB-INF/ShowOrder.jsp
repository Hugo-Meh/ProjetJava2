<%@page import="entities.Product"%>
<%@page import="service.C"%>
<%@page import="entities.Order"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	ArrayList<Order> allOrder = (ArrayList<Order>) request.getAttribute(C.OrderList);
	ArrayList<Product> AllProductByIdOrder = (ArrayList<Product>) request.getAttribute(C.ProductList);
	int allQuantity = 0;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" type="text/css" href="CSS/main.css">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/HTML/header.jsp" />
	<main>
	<div class="wrapper">
		<div class="divOrder w3-container">
			<%
				if (allOrder == null && AllProductByIdOrder==null) {
			%>
			<p>Vous n'avez aucune commande à ce jour</p>
			<%
				} else if(allOrder!=null){
			%>
			<table class="w3-table-all">

				<tr>
					<th>identifiant de la commande</th>
					<th>identifiant de l'utilsateur</th>
					<th>date de la commande</th>
					<th>Validation de la commande</th>

				</tr>


				<%
					for (Order o : allOrder) {
				%>

				<tr class="w3-border w3-hover-text-red">

					<td><a href="ServeletRedirectShowOrder?idOrder=<%=o.getId()%>"><%=o.getId()%></a>
					</td>
					<td><%=o.getUserID()%>
					</td>
					<td><%=o.getDate()%>
					</td>
					<td><%=(o.isValide() ? "valide" : "en attente de la validation")%>
					</td>
				</tr>

				<%
					}
					}
				%>

			</table>
		</div>
		<div class="divOrderw3-container">
			<%
			if (AllProductByIdOrder != null) {
			%>
			<table class="w3-table-all">
				<tr>
					<th>id du produit</th>
					<th>Nom du produit</th>
					<th>Id Categorie</th>
					<th>Quantite commande</th>
				</tr>
				<%
					for (Product p : AllProductByIdOrder) {
							allQuantity += p.getQuantity();
				%>
				<tr class="w3-border w3-hover-text-red">
					<td><%=p.getId()%></td>
					<td><%=p.getName()%></td>
					<td><%=p.getIdCategroy()%></td>
					<td><%=p.getQuantity()%></td>
				</tr>
				<%
					}
				%>
			<tr><td colspan=3 >Quantite total</td><td colspan=2><%=allQuantity %></td></tr>
			</table>
			<%
				}
			%>

		</div>
	</div>

	</main>
</body>
</html>