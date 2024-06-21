<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="org.example.servlet.login.models.Categoria" %>
<%@ page import="org.example.servlet.login.models.Producto" %>

<% List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
   Map<String,String > errores = (Map<String, String>) request.getAttribute("errores");
   Producto producto = (Producto) request.getAttribute("producto");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar Producto</title>
</head>
<body>
<h2>Agregar Nuevo Producto</h2>
<form action="agregarProducto" method="post">
    Nombre: <input type="text" name="nombre" required><br>
    Descripción: <textarea name="descripcion"></textarea><br>
    Precio: <input type="text" name="precio" required><br>
    Categoría:
    <select name="idCategoria" required>
        <%-- Iterar sobre la lista de categorías y generar las opciones --%>
        <% for (Categoria c : categorias) { %>
        <option value="<%= c.getIdCategoria() %>"><%= c.getNombre() %></option>
        <% } %>
    </select><br>
    <input type="submit" value="Guardar">
</form>
</body>
</html>