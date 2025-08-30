<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="controlador.Conexion, modelo.DocumentoMaestro"%>
<%@ page
	import="java.sql.Connection, java.util.List, java.io.InputStream, java.util.Properties"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registro Maestros</title>
<style type="text/css">
.mensaje {
	padding: 10px;
	margin-bottom: 15px;
	font-weight: bold;
	border-radius: 5px;
	text-align: center;
}

.exito {
	background-color: #d4edda;
	color: #155724;
	border: 1px solid #c3e6cb;
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

input[type="text"], input[type="number"] {
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

	<%
	/* Connection test = Conexion.getConnection();
	 
	 if (test != null && !test.isClosed()){
	 	out.print("<h2>Conectado a MYSQL con la forma 2</h2>");
	 } else {
	 	out.print("<h2>No Conectado a MYSQL</h2>");
	 }*/
     %>
	

	<div class="form-container">
	 <% 
	 String mensaje = (String) session.getAttribute("mensaje");
		String tipo = (String) session.getAttribute("tipo");

		DocumentoMaestro doc = (DocumentoMaestro) session.getAttribute("Documentos");

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

		<h2>Formulario Documento Maestro</h2>

		<form action="DocumentoMaestroServlet" method="POST">

			<label for="codigo">Código:</label><br> <input type="text"
				name="codigo" id="codigo"
				value="<%=(doc != null) ? doc.getCodigo() : ""%>"><br>
			<br> <label for="nombre">Nombre:</label><br> <input
				type="text" name="nombre" id="nombre"
				value="<%=(doc != null) ? doc.getNombre() : ""%>"><br>
			<br> <label for="tamanio">Tamaño:</label><br> <input
				type="text" name="tamanio" id="tamanio"
				value="<%=(doc != null) ? doc.getTamanio() : ""%>"><br>
			<br> <label for="ruta">Ruta:</label><br> <input type="text"
				name="ruta" id="ruta"
				value="<%=(doc != null) ? doc.getRuta() : ""%>"><br>
			<br> <label for="ext">Extensión:</label><br> <input
				type="text" name="ext" id="ext"
				value="<%=(doc != null) ? doc.getExt() : ""%>"><br> <br>
			<input type="submit" name="accion" value="Registrar"><br>

			<label for="id">ID Maestro:</label><br> <input type="number"
				name="id" value="<%=(doc != null) ? doc.getId() : ""%>"><br>
			<br> 
			<input type="submit" name="accion" value="Eliminar">
			<input type="submit" name="accion" value="Actualizar"> 
			<input type="submit" name="accion" value="Consultar">
			<input type="submit" name="accion" value="Ver todos">

		</form>

		<%-- <form name="frmMaestros" action="DocumentoMaestroServlet"  method="POST">

        <label for="codigo">Codigo del documento:</label><br>
        <input type="text" name="codigo" id="codigo" maxlength="45" value="${consultar.codigo}"><br><br>

        <label for="nombre">Nombre:</label><br>
        <input type="text" name="nombre" id="nombre" maxlength="45" value="${consultar.nombre}"><br><br>

        <label for="tamanio">Tamaño del archivo:</label><br>
        <input type="text" name="tamanio" id="tamanio" maxlength="45" value="${consultar.tamanio}"><br><br>

        <label for="ruta">Ruta:</label><br>
        <input type="text" name="ruta" id="ruta" maxlength="200" value="${consultar.ruta}"><br><br>

        <label for="ext">Extension:</label><br>
        <input type="text" name="ext" id="ext" maxlength="10" value="${consultar.ext}"><br><br>

        <input type="submit" name="accion" value="Registrar">
        <input type="reset" value="Limpiar"><br><br><br>


        <label for="id">ID Maestro:</label><br>
        <input type="number" name="id" value="id"><br><br>
        <input type="submit" name="accion" value="eliminar">
        <input type="submit" name="accion" value="actualizar">
        <input type="submit" name="accion" value="consultar">
        
        </form>  --%>

	</div>

	<%
	Properties lector = new Properties();
	InputStream input = getClass().getClassLoader().getResourceAsStream("ejemplo.properties");

	lector.load(input);
	String Nombre = "", Apellido = "";

	try {

		Nombre = lector.getProperty("Nombre");
		Apellido = lector.getProperty("Apellido");

	} catch (Exception e) {
		out.println("Error: " + e.getMessage());
	}
	%>
	<div>

		<p>
			Nombre:
			<%=Nombre%></p>
		<p>
			Apellido:
			<%=Apellido%></p>

		<a href="GenerarPDFServlet" target="_blank">Descargar PDF de
			documentos</a>
	</div>


</body>
</html>