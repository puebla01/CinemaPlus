<%@page import="java.util.ArrayList"%>
<%@page import="domain.Reservas"%>
<%@page import="java.util.List"%>
<%@page import="servicios.ServicioReservas"%>
<%@page import="servicios.ServicioFunciones"%>
<%@page import="domain.Funciones"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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

	<%
	int idFuncion = Integer.parseInt(request.getParameter("idFuncion"));
	Funciones funcion = new Funciones();
	ServicioFunciones scFuncion = new ServicioFunciones();
	funcion = scFuncion.recuperarFuncionPorIdFuncion(idFuncion);

	int filas = funcion.getSala().getTipoSala().getFila();
	int butacas = funcion.getSala().getTipoSala().getButaca();

	ServicioReservas scReservas = new ServicioReservas();
	List<Reservas> listaReservas = new ArrayList<Reservas>();
	listaReservas = scReservas.recuperarReservasPorIDFuncion(idFuncion);
	%>

	<section class="container">
		<div class="row">
			<div class="col-4">
				<table>
					<tbody>
						<%
						for (int i = 1; i < filas + 1; i++) {
						%>
						<tr>
							<%
							for (int j = 1; j < butacas + 1; j++) {
								if (listaReservas.size() != 0) {
									for (Reservas reserva : listaReservas) {
								if (reserva.getFila() == i && reserva.getButaca() == j) {
									//reservado
									out.println("<td class='butaca_ocupada'> <span ></span></td>");
								} else {
									// 				out.println("<span style='color:blue'>"+i+","+j+"</span>");	 		
									out.println("<td class='butaca_libre'> <span ></span> </td>");

								}

									}
								}else{
									out.println("<td class='butaca_libre'> <span ></span> </td>");
								}
							}
							%>
						</tr>
						<%
						}
						%>
						<tr>
							<td colspan="<%=butacas%>"><img
								src="assets/Images/Iconos/Pantalla.webp"></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</section>

</body>
</html>