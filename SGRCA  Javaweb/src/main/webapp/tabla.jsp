<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, modelo.DocumentoMaestro" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Documentos</title>
</head>
<body>

    <h2>Lista de Documentos</h2>

    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>Código</th>
            <th>Nombre</th>
            <th>Tamaño</th>
            <th>Ruta</th>
            <th>Extensión</th>
        </tr>

        <c:forEach var="doc" items="${lista}">
            <tr>
                <td>${doc.id}</td>
                <td>${doc.codigo}</td>
                <td>${doc.nombre}</td>
                <td>${doc.tamano}</td>
                <td>${doc.ruta}</td>
                <td>${doc.extension}</td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>