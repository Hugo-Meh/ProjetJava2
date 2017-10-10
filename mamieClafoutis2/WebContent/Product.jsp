<%@page import="entities.Category"%>
<%@page import="entities.Product"%>
<%@page import="java.util.ArrayList" %>
<%@page import="entities.User"%>
<%@page import="service.C" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" type="text/css" href="CSS/main.css">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="HTML/header.jsp"/>
	<main>
	<div class="wrapper">
		<div id="filterbox">
			<form class="form" method="post" action="ServeletShowProduct" >
				<div>
					<input type="text" name="search" placeholder="chercher..">
				</div>
				
				<select name="categorie">
					<%
					ArrayList<Category> categories = (ArrayList<Category>)request.getAttribute(C.CategoryList);
					for(Category Cat : categories){
						%>	
						<option value="<%=Cat.getId() %>" ><%= Cat.getNom()%></option>
					<%	
					}
					%>
				</select>
				<input type="submit" value="chercher">
			</form>
			<div id="categories">
				
			</div>
		</div>
		<%
		User user = (User)request.getAttribute(C.User);
		if(user != null && user.getId() == 1){
		%>
		<div id="addProduct">
			<a href="ServeletUpdateInsertProduct">Ajouter un Produit</a>
		</div>
		<%
		}
		%>
		<div id="containerProduct">
		<% 
		ArrayList<Product> products = (ArrayList<Product>)request.getAttribute(C.ProductList);
		for(Product product: products){
			if(product.isVisible() || user.getId() != 1){
			request.setAttribute(C.Product, product);
			
		%>
			<jsp:include page="HTML/divProduct.jsp"/>
		<%
			}
		}
		%>
		</div>
	</div>
	</main>
</body>
</html>