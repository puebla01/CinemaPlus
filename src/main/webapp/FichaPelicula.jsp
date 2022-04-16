<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@page import="servicios.ServicioFunciones"%>
<%@page import="java.util.ArrayList"%>
<%@page import="domain.Funciones"%>
<%@page import="java.util.List"%>
<%@page import="domain.Peliculas"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FichaPelicula</title>
<script src="assets/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="assets/bootstrap/js/bootstrap.min.js"></script>
<script src="sweetAlert/dist/sweetalert2.css"></script>
<script src="sweetAlert/dist/sweetalert2.js"></script>

<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">



<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	$(document).ready(function() {
		//En el onchange de la select con id inputfecha accionamos ajax
		$('#inputFecha').change(function(event) {
			//Recuperamos el id de pelicula si lo hay, si no hay idPelicula volvemos a la cartelera
			
			var idPeliculaVar =<%=request.getParameter("idPelicula")%>
	;
			//recuperamos el valor de la fecha de la select
			var fechaVar = $('#inputFecha').val();

			// Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
			$.post('GetFechasFuncionesAjax', {
				idPelicula : idPeliculaVar,
				fecha : fechaVar,

			}, function(responseText) {
				$('#horas').html(responseText);
			});
		});
	});
</script>
</head>

<body>
	<%
	Peliculas pelicula = (Peliculas) session.getAttribute("pelicula");
	//recupero todas las fechas de las peliculas
	%>


	<%@ include file="InicioSesionModelo.jsp" %>
	<section class="container">

		<div class="row text-center">
			<h1><%=pelicula.getTitulo()%></h1>
		</div>
		<div class="row justify-content-center">
			<div class="col-9">
				<div class="row ">

					<div class="col-4">

						<img alt="" src="<%=pelicula.getImagen()%>" width="100%">
					</div>
					<div class="col-8">
						<div class="row">
							<h1><%=pelicula.getTitulo()%></h1>
							<div class="col-6">
								<p><%=pelicula.getResumen()%></p>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<h1>Funciones</h1>
			<div class="row">
				<select id="inputFecha">
					<option hidden>Selecciona una fecha</option>
					<%
					List<Funciones> listaFuncionesPorPelicula = new ArrayList<Funciones>();

					ServicioFunciones scFunciones = new ServicioFunciones();
					//tengo que recuperar las funciones segun la pelicula
					listaFuncionesPorPelicula = scFunciones.recuperarTodasFuncionesPorPelicula(pelicula.getIdPelicula());
					if(listaFuncionesPorPelicula.size()!=0){
						java.sql.Date d = new java.sql.Date(Calendar.getInstance().getTime().getTime());
						for (Funciones funcion : listaFuncionesPorPelicula) {
							if (funcion.getFecha().compareTo(d) != 0) {
								out.println("<option value='" + funcion.getFecha() + "'>" + funcion.getFecha() + " </option>");
							}
							d = funcion.getFecha();
						}
					}else{
						%>
					<option>no hay funciones</option>
					<%
					}
					%>
				</select>
				<div id="horas"></div>


			</div>
		</div>
	</section>
</body>
</html>