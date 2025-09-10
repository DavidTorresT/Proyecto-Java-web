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

.btn-eliminar{
display: inline-block;
padding: 3px 3px;
background-color: #007BFF;
color: white;
text-decoration: none;
border-radius: 5px;
font-weight: bold;
}

.btn-eliminar:hover {
background-color: #0056b3
}

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
.module-icon {
font-size: 2em;
margin-bottom: 10px;
color: white;
}
</style>
</head>
<body>
<a href="docMaestro.jsp">Regresar</a>
	<h2 style="text-align: center;">Documentos Registrados</h2>
	
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

	<table>
		<tr>
			<th>ID</th>
			<th>Código</th>
			<th>Nombre</th>
			<th>Tamaño</th>
			<th>Ruta</th>
			<th>Extensión</th>
			<th>Acciones</th>
		</tr>

		<% 
       List<DocumentoMaestro> lista = (List<DocumentoMaestro>) request.getAttribute("listaDocumentos"); 
       if(lista != null) {
    	   
    	   for (DocumentoMaestro docMaestro : lista) {
    		  %>
		<tr>

			<td><%= docMaestro.getId() %></td>
			<td><%= docMaestro.getCodigo() %></td>
			<td><%= docMaestro.getNombre() %></td>
			<td><%= docMaestro.getTamanio() %></td>
			<td><%= docMaestro.getRuta() %></td>
			<td><%= docMaestro.getExt() %></td>
			<td> 
			<a href="DocumentoMaestroServlet?accion=tablaActualizar&id=<%= docMaestro.getId() %>" 
			class="btn-eliminar; module-icon">🔄</a>
			<a href="DocumentoMaestroServlet?accion=tablaEliminar&id=<%= docMaestro.getId() %>" 
			class="btn-eliminar; module-icon" onclick="return confirm('¿Esta seguro de eliminar el documento con nombre <%= docMaestro.getNombre()%>?')">⛔</a>
			
			</td>
		</tr>
		<% 
    	   }
       }%>
	</table>


</body>
</html>