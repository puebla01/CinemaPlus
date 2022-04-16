<!DOCTYPE html>
<%@page import="domain.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="servicios.ServicioPeliculas"%>
<%@page import="domain.Peliculas"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<script src="assets/bootstrap/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/styles.css">
</head>
<body>

	<section class="container">

		<!-- RECUPERO LA LISTA DE LIBROS PARA PINTAR LA CARTELERA -->
		<%
		
		Peliculas pelicula2 = new Peliculas();
		ServicioPeliculas scPelicula = new ServicioPeliculas();
		pelicula2 = scPelicula.recuperarPeliculaID(3);
		List<Peliculas> listaPeliculas = new ArrayList<Peliculas>();
		listaPeliculas = scPelicula.recuperarTodasPeliculas();
		session.setAttribute("pagina", "/index.jsp");
		// Si hay usuario, lo guardo en el String para hacer el link y si no escribo el formulario
		if (session.getAttribute("user") != null) {
			Usuario usuario = (Usuario) session.getAttribute("user");
		%>
		<!-- PANEL ADMIN QUE APARECE SOLO SEGUN EL ADMIN Y SI HAY SESION INICIADA (ADMIN TIPO=1) -->
		<div class="row justify-content-between">
			<div class="col-4">
				<%
				if (usuario.getTipo() == 0) {
				%>
				<button class="btn btn-primary"
					onclick="window.location.href='PanelAdministrador.jsp'">Panel
					Administrador</button>
				<%
				}
				%>
			</div>
			<div class="col-4  ">
			<div class=" text-end ">
				<label>Bienvenido <a href=""> <%=usuario.getNombre()%></a></label>
				<button class="btn btn-secondary" onclick="">Cerrar sesion</button>
			</div>
			</div>
		</div>

		<%
			} else { //PINTO EL FORMULARIO
			%>
		<!-- FORMULARIO LOGGIN QUE APARECE SOLO SI NO HAY SESION-->
		<div class="row justify-content-end">
			<div class=" col-4 ">
				<!-- FORMULARIO INICIO SESION -->
				<form action="ComprobarUsuario" method="post">
					<div class="row">
						<div class="col">
							<div class="input-group mb-3">
								<input type="text" class="form-control" id="email" name="email"
									placeholder="Email" aria-label="Email"> <input
									type="text" id="password" name="password" class="form-control"
									placeholder="Contraseña" aria-label="Server">
							</div>
						</div>
						<div class="col-2">
							<button class="btn btn-primary" type="submit">Entrar</button>
						</div>
					</div>
				</form>
			</div>
			<div class="row justify-content-end">
				<div class=" col-2 ">
					<a href=""> Registrarse</a>
				</div>
			</div>
		</div>

		<%
			}
			%>



		<!-- PAGINA WEB -->

		<div class="row titulo">
			<div class="">
				<h1>CINEMA PLUS</h1>

			</div>
		</div>

		<div class="row">
			<div class="col">
				<div class="row">
					<%
						for (Peliculas pelicula : listaPeliculas) {
							out.println("<div class='col-xxl-3 col-lg-6 col-md-6 col-sm-12'><a	href='RecuperarPelicula?idPelicula="
							+ pelicula.getIdPelicula() + "'><img src='" + pelicula.getImagen() + "' alt='' width='100%'>"
							+ pelicula.getTitulo() + "</a></div>");
						}
						%>



				</div>
			</div>
		</div>
	</section>
</body>
</html>