<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="CSS/main.css">
<title>Authentifiaction</title>
</head>
<body>
<jsp:include page="HTML/header.jsp"></jsp:include>
<main>
	<div class="wrapper">
		<form class="form" action="validateSignIn" method="post">
			<fieldset>
				<legend>Authentifiaction</legend>		
				<label for="login">Identifiant</label>
				<input type="text" id="Login" name="identifiant"/>
			
				<label for="login">Mot de passe</label>
				<input type="password" id="password" name="pwd"/>	
				
				<label for="remember">Se souvenir de moi</label>
				<input type="checkbox" value="yes" name="remember" id="remember">
				<input type="submit" value="connexion" name="connect"/>
			</fieldset>
		</form>
	</div>
</main>
</body>
</html>