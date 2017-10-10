<%@ page import="entities.Product" %>
<%@ page import="service.C" %>
<%@page import="java.util.ArrayList" %>
<%@page import="entities.Category"%>
<%@page import="entities.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
Product aProduct = (Product)request.getAttribute(C.Product);
ArrayList<Category> categoriesTab = (ArrayList<Category>)request.getAttribute(C.CategoryList);
String categorieName = null;
int i =0;
while (i<categoriesTab.size() && aProduct.getIdCategroy() != categoriesTab.get(i).getId()){
	i++;
};
categorieName = categoriesTab.get(i).getNom();
%>
    <div class="product w3-card-4">
    	<img alt="product" src="Image/ImageProduit/pain.jpg">
    	
    	<p><strong>Categorie:</strong><%= categorieName %></p>
    	<p><strong>nom:</strong><%= aProduct.getName() %></p>
    	<p>Short description</p>
    	<%
    	User user = (User)request.getAttribute(C.User);
    	if(user.getId() == 1){
    	%>	
    	
    	%>
    	<p>Visible : <% if(aProduct.isVisible()){
    		%>
    		oui
    	<%	
    	}
    	else{
    	%>
    		non
    	<%	
    	}
    	%>
    	</p>
    	<button type="button"><a href="ServeletModifyProduct?<%=C.Product%>=<%=aProduct.getId()%>"></a></button>
    	<%
    	}
    	%>
    </div>