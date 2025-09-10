<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="modelo.DocumentoMaestro"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Actualizacion de documento</title>
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
<div>
    <% 
	 String mensaje = (String) session.getAttribute("mensaje");
		String tipo = (String) session.getAttribute("tipo");

		session.removeAttribute("mensaje");
		session.removeAttribute("tipo");
		
		DocumentoMaestro doc = (DocumentoMaestro) session.getAttribute("Documentos");
		%>

		<%
		if (mensaje != null && tipo != null) {
		%>

		<div class="mensaje <%=tipo%>"><%=mensaje%></div>
		<%
		session.removeAttribute("Error");
		}
		%>
    <h2>Editar Documento Maestro</h2>

	<form action="DocumentoMaestroServlet" method="POST">

        <label for="id">ID Maestro:</label><br> 
        <input type="number" name="id" readonly="readonly"
            value="<%=(doc != null) ? doc.getId() : ""%>"><br><br>
		<label for="codigo">Código:</label><br> 
		<input type="text" name="codigo" id="codigo" maxlength="45" 
		    value="<%=(doc != null) ? doc.getCodigo() : ""%>"><br> <br>
		<label for="nombre">Nombre:</label><br> 
		<input type="text" name="nombre" id="nombre" maxlength="45"
		    value="<%=(doc != null) ? doc.getNombre() : ""%>"><br> <br>
		<label for="tamanio">Tamaño:</label><br> 
		<input type="text" name="tamanio" id="tamanio" maxlength="45" 
		    value="<%=(doc != null) ? doc.getTamanio() : ""%>"><br><br>
		<label for="ruta">Ruta:</label><br> 
		<input type="text" name="ruta" id="ruta" maxlength="200"
		    value="<%=(doc != null) ? doc.getRuta() : ""%>"><br> <br>
		<label for="ext">Extensión:</label><br> 
		<input type="text" name="ext" id="ext" maxlength="10"
			value="<%=(doc != null) ? doc.getExt() : ""%>"><br> <br>

		<input type="submit" name="accion" value="🔄 Actualizar">  
		<a href="DocumentoMaestroServlet?accion=📋 Ver todos">Volver</a>
	</form>
	
	</div>
</body>
</html>