<%@page import="servicios.ServicioCategoriaPelicula"%>
<%@page import="java.util.ArrayList"%>
<%@page import="domain.CategoriasPeliculas"%>
<%@page import="java.util.List"%>
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
<link rel="stylesheet" href="assets/styles.css">
</head>
<body>

	<section class="container">
		<form action="GrabarPelicula" method="post"
			enctype="multipart/form-data">
			<!-- enctype="multipart/form-data" lo usamos para subir archivos y enviarlos de una forma -->

			<div class="col-4">
				<h1>InsertarPeliculas</h1>
				<div class="row">
					<label>Título película</label><input type='text' name="inputTitulo">
				</div>
				<div class="row">
					<label>Resumen:</label>
					<textarea rows="10" cols="10" name="inputResumen"></textarea>
				</div>
				<div class="row">
					<label>Duracion</label><input type="number" name="inputDuracion">
				</div>
				<div class="row">
					<label>Categoria:</label> <select name="inputCategoria">
						<%
						List<CategoriasPeliculas> listaCategorias = new ArrayList<CategoriasPeliculas>();
						ServicioCategoriaPelicula scCategoria = new ServicioCategoriaPelicula();
						listaCategorias = scCategoria.recuperarTodasCategorias();
						 for(CategoriasPeliculas categoria : listaCategorias){
						out.println("<option value='"+ categoria.getidCategorias() +"'>"+categoria.getDescripcion()+"</option>");
						}
						%>
					</select>
				</div>
				<div class="row">
					<input type='file' name="inputImagen">
				</div>

			</div>
			<button type="submit">Enviar</button>
		</form>
	</section>
</body>
</html>