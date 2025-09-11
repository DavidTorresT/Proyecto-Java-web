<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestion Ingreso de Usuarios</title>
<style type="text/css">
.mensaje {
	padding: 10px;
	margin-bottom: 15px;
	font-weight: bold;
	border-radius: 5px;
	text-align: center;
}

.error {
	background-color: #f8d7da;
	color: #721c24;
	border: 1px solid #f5c6cb;
}

body {
	font-family: Arial, sans-serif;
	background-color: #f8f9fa;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

h2 {
	color: #333;
	text-align: center;
	margin-bottom: 20px;
}

.form-container {
	background-color: white;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0 2px 6px rgba(0, 0, 0, 0.2);
	max-width: 500px;
}

label {
	font-weight: bold;
}

input[type="text"], input[type="password"] {
	width: 95%;
	padding: 8px;
	margin-bottom: 15px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

input[type="submit"] {
	padding: 10px 18px;
	margin: 5px;
	background-color: #007BFF;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type="submit"]:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>

  <div class="form-container">
  <% 
  String mensaje = (String) session.getAttribute("mensaje");
	String tipo = (String) session.getAttribute("tipo");

	session.removeAttribute("mensaje");
	session.removeAttribute("tipo");
	%>

	<%
	if (mensaje != null && tipo != null) {
	%>

	<div class="mensaje <%=tipo%>"><%=mensaje%></div>
	<%
	session.removeAttribute("Error");
	}
	%>

    <h2>Ingreso de usuarios</h2>

        <form action="LoginServlet" method="get">

			<label for="nombres">Usuario:</label><br> 
			<input type="text" name="nombres" id="nombres" maxlength="50"><br><br> 
			<label for="contrasena">Contraseña:</label><br> 
			<input type="password" name="contrasena" id="contrasena" maxlength="500"><br><br>
							
			<input type="submit" name="accion" value="Ingresar"><br><br>

		</form>
  </div>
</body>
</html>