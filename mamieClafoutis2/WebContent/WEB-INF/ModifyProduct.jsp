<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" type="text/css" href="CSS/main.css">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/HTML/header.jsp"></jsp:include>
<main>
	<div class="wrapper">
		<form class="form" action="ServletShowProduct" method="post">
			<fieldset>
				<legend>Modifier Produit</legend>
						
				<label for="setname"></label>
				<input type="text" id="Login" name="identifiant"/>
			
				<label for="login">Mot de passe</label>
				<input type="password" id="password" name="pwd"/>	
				
				<label for="radio-remember">Se souvenir de moi</label>
				<input type="radio" value="yes" name="remember" id="radio-remember">
				
				<input class="w3-button" value="Se connecter" type="submit">
			</fieldset>
		</form>
	</div>
</main>
</body>
</html>