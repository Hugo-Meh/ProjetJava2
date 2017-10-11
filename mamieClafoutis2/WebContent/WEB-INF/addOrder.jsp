<%@page import="service.C"%>
<%@page import="entities.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<Product> allProduct = (ArrayList<Product>) request.getAttribute(C.ProductList);
	int i = 0;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" type="text/css" href="CSS/main.css">

<title>AddOrder</title>
</head>
<body>
	<jsp:include page="/HTML/header.jsp"></jsp:include>
	<main>
	<div class="wrapper">
		<form action="ServeletRedirectOrder" method="post" id="orderForm"
			class=" w3-container">

			<table class="w3-table-all">
				<tr>
					<th>Listes des produits</th>
					<th>quantite a commander</th>
				</tr>
				<%
					for (; i < 10; i++) {
				%>
				<tr>
					<td><select name="ProductOrder">
							<option value=000>Aucun</option>
							<%
								for (Product p : allProduct) {
							%>
							<option value="<%=p.getId()%>"><%=p.getName()%></option>

							<%
								}
							%>
					</select></td>
					<td><input type="number" name="qte" min=0 /></td>

				</tr>

				<%
					}
				%>
			</table>
			<input type="submit" name="addOrder" value="valider commande" />

		</form>
	</div>
	</main>
</body>
</html>