<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Inicia sesiones</h1>
	<form action="ComprobarUsuario"  method="post">
		<label>Email</label>
		<input type="text" id="email" name="email"> 
		<label>Contraseña</label>
		<input type="text" id="password" name="password"> 
		<button type="submit">Acceder </button>
	</form>
</body>
</html>