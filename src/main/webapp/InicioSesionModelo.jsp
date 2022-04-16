


<%@page import="domain.Usuario"%>
<html>
<section class="container">
	<div class="row">
		<div class="col-4"> <a href="index.jsp">LOGOOO</a> </div>
		<div class="col-8">
			<div class="container">


				<%
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
							<button class="btn btn-secondary" onclick="">Cerrar
								sesion</button>
						</div>
					</div>
				</div>

				<%
				} else { //PINTO EL FORMULARIO
				%>
				<!-- FORMULARIO LOGGIN QUE APARECE SOLO SI NO HAY SESION-->
				<div class="row justify-content-end">
					<div class=" col-8 ">
						<!-- FORMULARIO INICIO SESION -->
						<form action="ComprobarUsuario" method="post">
							<div class="row">
								<div class="col">
									<div class="input-group mb-3">
										<input type="text" class="form-control" id="email"
											name="email" placeholder="Email" aria-label="Email">
										<input type="text" id="password" name="password"
											class="form-control" placeholder="Contraseña"
											aria-label="Server">
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
			</div>
		</div>
	</div>
</section>
</html>
