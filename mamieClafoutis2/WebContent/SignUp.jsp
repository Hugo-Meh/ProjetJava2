<%@page import="entities.User"%>
<%@page import="entities.Role"%>
<%@page import="service.C"%>
<%@page import="entities.Establishment"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	ArrayList<Establishment> estab = (ArrayList<Establishment>) request.getAttribute(C.EstablishmentList);
	ArrayList<Role> allRole = (ArrayList<Role>) request.getAttribute(C.RoleList);
	String error_message = (String) request.getAttribute(C.ResponseSignUp);
	User userdata=(User)request.getAttribute(C.UserErrorData);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="CSS/main.css">
<title>Insert title here</title>
</head>
<body>
	
	<jsp:include page="HTML/header.jsp"></jsp:include>
	<main>
	<div><%=(error_message != null ? error_message : "")%></div>
	<div>
		<form class="w3-container" action="signUp" method="post">
			<fieldset>
				<legend>Inscription</legend>

				<label for="Etablissement"> Dans quel etablissement travaillez vous ? </label> <select id="Etablissement" name="etab">
					<%
						if (estab != null) {
							for (Establishment e : estab) {
					%>
					<option value="<%=e.getId()%>"><%=e.getName()%></option>
					<%
						}
						}
					%>

				</select> <label for="roles"> Quel est votre fonction dans
					l'entreprise ? </label> <select id="roles" name="role">
					<%
						if (allRole != null) {
							for (Role r : allRole) {
					%>
					<option value="<%=r.getId()%>"><%=r.getName()%></option>
					<%
						}
						}
					%>
					
				</select> <label for="nom">Nom :</label> <input type="text" id="nom"
					name="nom"  value="<%=(userdata!=null&&userdata.getLastName()!=null?userdata.getLastName():"") %>" required="required" /> <label for="Prenom">Prenom :</label> <input
					type="text" id="prenom" name="prenom" value="<%=(userdata!=null&&userdata.getFirstName()!=null?userdata.getFirstName():"") %>" r required /> <label
					for="mail">Courriel :</label> <input type="email" id="Login" name="mail" required/>
					 <label for="pwd">Mot de passe :</label> <input
					type="password" id="pwd" name="pwd" required /> <label for="pwd">confirmation
					Mot de passe :</label> <input type="password" id="pwd" name="pwd1" required />
				<input type="submit" id="sub" name="signup" value="S'inscrire" required />
			</fieldset>
		</form>
		</div>
	</main>
</body>
</html>