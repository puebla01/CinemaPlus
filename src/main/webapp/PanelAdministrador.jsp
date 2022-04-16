<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Panel Administrador</title>

<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<script src="assets/bootstrap/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/styles.css">
</head>
<body>
<%@ include file="InicioSesionModelo.jsp" %>
	<div class="container" id="accordionExample">
		<div class="col-8">
			<div class="row">
				<div class="col">
					<div class="accordion-item">
						<h2 class="accordion-header" id="headingOne">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#collapsePelicula"
								aria-expanded="false" aria-controls="#collapseOne">
								Peliculas</button>
						</h2>
						<div id="collapsePelicula" class="accordion-collapse collapse"
							aria-labelledby="headingOne" data-bs-parent="#accordionExample">
							<div class="accordion-body">
								<div class="row">
									<div class="col">
										<button class="btn btn-primary">Insertar Peliculas</button>
									</div>
									<div class="col">
										<button class="btn btn-primary">Insertar Peliculas</button>
									</div>
									<div class="col">
										<button class="btn btn-primary">Insertar Peliculas</button>
									</div>
								</div>

							</div>
						</div>
					</div>
					<div class="accordion-item">
						<h2 class="accordion-header" id="headingTwo">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#collapseFuncion"
								aria-expanded="false" aria-controls="collapseTwo">
								Funciones</button>
						</h2>
						<div id="collapseFuncion" class="accordion-collapse collapse"
							aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
							<div class="accordion-body">
								<div class="row">
									<div class="col">
										<button class="btn btn-primary">Insertar Funcion</button>
									</div>
									<div class="col">
										<button class="btn btn-primary">Borrar Funcion</button>
									</div>
									<div class="col">
										<button class="btn btn-primary">Modificar Funcion</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="accordion-item">
						<h2 class="accordion-header" id="headingThree">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#collapseCategoria"
								aria-expanded="false" aria-controls="collapseThree">
								Categorias</button>
						</h2>
						<div id="collapseCategoria" class="accordion-collapse collapse"
							aria-labelledby="headingThree" data-bs-parent="#accordionExample">
							<div class="accordion-body">
								<div class="row">
									<div class="col">
										<button class="btn btn-primary">Insertar Categoria</button>
									</div>
									<div class="col">
										<button class="btn btn-primary">Borrar Categoria</button>
									</div>
									<div class="col">
										<button class="btn btn-primary">Modificar Categoria</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="col">
					<div class="accordion-item">
						<h2 class="accordion-header" id="headingOne">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#collapseSala"
								aria-expanded="false" aria-controls="#collapseOne">
								Salas</button>
						</h2>
						<div id="collapseSala" class="accordion-collapse collapse"
							aria-labelledby="headingOne" data-bs-parent="#accordionExample">
							<div class="accordion-body">
								<div class="row">
									<div class="col">
										<button class="btn btn-primary">Insertar Sala</button>
									</div>
									<div class="col">
										<button class="btn btn-primary">Borrar Sala</button>
									</div>
									<div class="col">
										<button class="btn btn-primary">Modificar Sala</button>
									</div>
								</div>

							</div>
						</div>
					</div>
					<div class="accordion-item">
						<h2 class="accordion-header" id="headingTwo">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#collapseTipoSala"
								aria-expanded="false" aria-controls="collapseTwo">Tipo
								de Salas</button>
						</h2>
						<div id="collapseTipoSala" class="accordion-collapse collapse"
							aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
							<div class="accordion-body">
								<div class="row">
									<div class="col">
										<button class="btn btn-primary">Insertar Tipo de Sala</button>
									</div>
									<div class="col">
										<button class="btn btn-primary">Borrar Tipo de Sala</button>
									</div>
									<div class="col">
										<button class="btn btn-primary">Modificar Tipo de
											Sala</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="accordion-item">
						<h2 class="accordion-header" id="headingThree">
							<button class="accordion-button collapsed" type="button"
								data-bs-toggle="collapse" data-bs-target="#collapseReservas"
								aria-expanded="false" aria-controls="collapseThree">
								Categorias</button>
						</h2>
						<div id="collapseReservas" class="accordion-collapse collapse"
							aria-labelledby="headingThree" data-bs-parent="#accordionExample">
							<div class="accordion-body">
								<div class="row">
									<div class="col">
										<button class="btn btn-primary">Insertar Reserva</button>
									</div>
									<div class="col">
										<button class="btn btn-primary">Borrar reserva</button>
									</div>
									<div class="col">
										<button class="btn btn-primary">Modificar reserva</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>