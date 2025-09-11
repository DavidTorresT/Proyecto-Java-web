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
<title>SGRCA</title>
<style type="text/css">
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

</style>
</head>
<body>

<div class="form-container">

<h2>Sistema Gestion De Registro Calificado y Autoevaluacion</h2>
	
<a href="docMaestro.jsp"><img alt="Gestion de Documentos de Maestros" src="https://cdn-icons-png.flaticon.com/128/3396/3396255.png"></a>
<a href=""></a>

</div>

</body>
</html>