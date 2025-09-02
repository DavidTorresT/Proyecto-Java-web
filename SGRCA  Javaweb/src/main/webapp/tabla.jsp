<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, modelo.DocumentoMaestro"%>
<!DOCTYPE html>
<html>
<head>
<title>Lista de Documentos</title>
<style type="text/css">
table {
	border-collapse: collapse;
	width: 80%;
	margin: 20px auto;
}

th, td {
	border: 1px solid #333;
	padding: 8px;
	text-align: center;
}

th {
	background-color: #4CAF50;
	color: white;
}
</style>
</head>
<body>

	<h2 style="text-align: center;">Documentos Registrados</h2>

	<table>
		<tr>
			<th>ID</th>
			<th>Código</th>
			<th>Nombre</th>
			<th>Tamaño</th>
			<th>Ruta</th>
			<th>Extensión</th>
		</tr>

		<% 
       List<DocumentoMaestro> documentos = (List<DocumentoMaestro>) request.getAttribute("documentos"); 
       if(documentos != null) {
    	   
    	   for (DocumentoMaestro doc : documentos) {
    		  %>
		<tr>

			<td><%= doc.getId() %></td>
			<td><%= doc.getCodigo() %></td>
			<td><%= doc.getNombre() %></td>
			<td><%= doc.getTamanio() %></td>
			<td><%= doc.getRuta() %></td>
			<td><%= doc.getExt() %></td>

		</tr>
		<% 
    	   }
       }%>
	</table>

<a href="index.jsp">Regresar</a>
</body>
</html>