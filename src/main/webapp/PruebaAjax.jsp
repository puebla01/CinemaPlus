<%@page import="java.util.Calendar"%>
<%@page import="servicios.ServicioFunciones"%>
<%@page import="java.util.ArrayList"%>
<%@page import="domain.Funciones"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js">

</script>
<script>
	$(document).ready(function() {
		$('#funcion').change(function(event) {
			var nombreVar = $('#nombre').val();
			var funcionVar=$('#funcion').val();
			
			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('GetFechasFuncionesAjax', {
				nombre : nombreVar,
				funcion: funcionVar,
			
			}, function(responseText) {
				$('#tabla').html(responseText);
			});
		});
	});
</script>
</head>
<body>

<select id="funcion">
					<%
					List<Funciones> listaFuncionesPorPelicula = new ArrayList<Funciones>();
					
					ServicioFunciones scFunciones = new ServicioFunciones();
					//tengo que recuperar las funciones segun la pelicula
					listaFuncionesPorPelicula = scFunciones.recuperarTodasFuncionesPorPelicula(3);
					
					// 					for (Funciones funcion : listaFuncionesPorPelicula){
					// 							out.println("<option >"+ funcion.getFecha()+" </option>");
					// 						}					
					java.sql.Date d = new java.sql.Date(Calendar.getInstance().getTime().getTime());
					for (int i = 0; i < listaFuncionesPorPelicula.size(); i++) {
						if (listaFuncionesPorPelicula.get(i).getFecha().compareTo(d) != 0) {
							out.println("<option value='"+ listaFuncionesPorPelicula.get(i).getFecha()+"'>"+listaFuncionesPorPelicula.get(i).getFecha()+" </option>");
						}
						d = listaFuncionesPorPelicula.get(i).getFecha();
					}
					%>
				</select>
<div>recuperar peliculas</div>
<input type="text" id="nombre"> 
<button id="submit">enviar</button>
<div id="tabla"></div>

</body>
</html>