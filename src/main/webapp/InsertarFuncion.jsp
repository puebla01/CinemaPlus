<%@page import="java.time.LocalDateTime"%>
<%@page import="domain.Salas"%>
<%@page import="servicios.ServicioSala"%>
<%@page import="java.util.ArrayList"%>
<%@page import="domain.Peliculas"%>
<%@page import="java.util.List"%>
<%@page import="servicios.ServicioPeliculas"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="assets/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="assets/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
</head>
<body>


	<form method="post" action="GrabarFunciones">

		<label>Pelicula: </label> <select name="inputPelicula">
			<% 
		ServicioPeliculas scPeliculas= new ServicioPeliculas();
		List <Peliculas> listaPeliculas= new ArrayList<Peliculas>();
		listaPeliculas= scPeliculas.recuperarTodasPeliculas();
		
		for(Peliculas pelicula : listaPeliculas){
			if(pelicula!=null){
			out.print("<option value='"+pelicula.getIdPelicula() +"'>"+pelicula.getTitulo()+"</option>");
			} 
		}
		%>
		</select> <label>Sala: </label> <select name="inputSala">
			<% 
		ServicioSala scSala= new ServicioSala();
		List <Salas> listaSala= new ArrayList<Salas>();
		listaSala= scSala.recuperarTodasSalas();
		
		for(Salas sala : listaSala){
			if(sala!=null){
			out.print("<option value='"+sala.getIdSala() +"'>"+sala.getNombre()+"</option>");
			} 
		}
		%>
		</select>

		<%
	LocalDateTime today = LocalDateTime.now();     //Today
    LocalDateTime tomorrow = today.plusDays(1);     //Plus 1 day 
    %>
		<label>Fecha</label><input name="inputFecha"  type="date"> <label>Hora</label><input
			name="inputHora" type="time">
		
		<button type="submit">Enviar</button>
	</form>
	

<div>

  <form action="../../form-result.php" method="post" target="_blank">


<datalist id="listafechascita">

  <option value="2015-05-05">

  <option value="2015-05-12">

  <option value="2015-05-19">

  <option value="2015-05-26">

</datalist>
  <p>

    Haz una cita: <input type="date" name="fechacita" list="listafechascita">

    <input type="submit" value="Enviar datos">

  </p>

</form>

</div>

	
</body>
</html>