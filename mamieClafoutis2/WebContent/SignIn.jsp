<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="HTML/header.jsp"></jsp:include>
<main>
	<form class="w3-container" action="ServletConnexion" method="post">
		<fieldset>
			<legend>Inscription</legend>		
			<label for="login">Identifiant</label>
			<input type="text" id="Login"/>
			
			<label for="login">Mot de passe</label>
			<input type="text" id="password"/>	
		</fieldset>
	</form>
</main>
</body>
</html>