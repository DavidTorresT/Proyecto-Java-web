<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="modelo.DocumentoMaestro" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Documentos Maestros</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            padding: 20px;
        }

        h2 {
            color: #333;
        }

        .mensaje {
            padding: 12px;
            font-weight: bold;
            border-radius: 6px;
            margin-bottom: 20px;
            width: 50%;
            text-align: center;
        }

        .mensaje.exito {
            background-color: #4CAF50;
            color: white;
        }

        .mensaje.error {
            background-color: #F44336;
            color: white;
        }

        .form-container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.2);
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
    String mensaje = (String) request.getAttribute("mensaje");
    String tipo = (String) request.getAttribute("tipo");
    DocumentoMaestro doc = (DocumentoMaestro) request.getAttribute("Documentos");

    // Si la operación fue exitosa, limpiamos los campos
    if ("exito".equals(tipo)) {
        doc = null;
    }
%>

<h2>Formulario Documento Maestro</h2>

<% if (mensaje != null && tipo != null) { %>
    <div class="mensaje <%= tipo %>"><%= mensaje %></div>
<% } %>


<div class="form-container" style="align-items:center;  ">
    <form action="DocumentoMaestroServlet" method="POST">
        <label for="codigo">Código:</label><br>
        <input type="text" name="codigo" id="codigo"
               value="<%= (doc != null) ? doc.getCodigo() : "" %>"><br>

        <label for="nombre">Nombre:</label><br>
        <input type="text" name="nombre" id="nombre"
               value="<%= (doc != null) ? doc.getNombre() : "" %>"><br>

        <label for="tamano">Tamaño:</label><br>
        <input type="text" name="tamano" id="tamano"
               value="<%= (doc != null) ? doc.getTamanio() : "" %>"><br>

        <label for="ruta">Ruta:</label><br>
        <input type="text" name="ruta" id="ruta"
               value="<%= (doc != null) ? doc.getRuta() : "" %>"><br>

        <label for="ext">Extensión:</label><br>
        <input type="text" name="ext" id="ext"
               value="<%= (doc != null) ? doc.getExt() : "" %>"><br>

        <input type="submit" name="accion" value="Registrar">
        <input type="submit" name="accion" value="Actualizar">
        <input type="submit" name="accion" value="Eliminar">
        <input type="submit" name="accion" value="Consultar">
        <input type="submit" name="accion" value="Ver todos"><br><br>

        <label for="id">ID Maestro:</label><br>
        <input type="number" name="id"><br>
    </form>
</div>

</body>
</html>