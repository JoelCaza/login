<%@ page import="org.example.servlet.login.models.Producto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Optional" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Productos</title>
    <style>
        body { font-family: Arial, sans-serif; }
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #dddddd; text-align: left; padding: 8px; }
        th { background-color: #f2f2f2; }
        .navbar { overflow: hidden; background-color: #333; }
        .navbar a { float: left; display: block; color: white; text-align: center; padding: 14px 20px; text-decoration: none; }
        .navbar a:hover { background-color: #ddd; color: black; }
    </style>
</head>
<body>
<div class="navbar">
    <a href="${pageContext.request.contextPath}/index.html">Inicio</a>
    <a href="${pageContext.request.contextPath}/LogoutServlet">Salir</a>
</div>
<h1>Listado de Productos</h1>
<%-- Verificar si la lista de productos no es nula y no está vacía --%>
<%
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
    if (productos != null && !productos.isEmpty()) {
        // Obtener el atributo 'username' del request y verificar si está presente
        Optional<String> usernameOptional = (Optional<String>) request.getAttribute("username");
%>
<table>
    <tr>
        <th>Id</th>
        <th>Código</th>
        <th>Nombre</th>
        <th>Stock</th>
        <th>Descripción</th>
        <th>Precio</th>
        <th>Categoría</th>
        <th>Acciones</th>
    </tr>
    <% for (Producto p : productos) { %>
    <tr>
        <td><%= p.getId() %></td>
        <td><%= p.getCodigo() %></td>
        <td><%= p.getNombre() %></td>
        <td><%= p.getStock() %></td>
        <td><%= p.getDescripcion() %></td>
        <td><%= p.getPrecio() %></td>
        <td><%= p.getCategoria().getNombre() %></td>
        <td>
            <% if (usernameOptional.isPresent()) { %>
            <a href="${pageContext.request.contextPath}/agregar-carro?id=<%= p.getId() %>">Agregar al Carro</a>
            <% } else { %>
            Inicie sesión para agregar al carro
            <% } %>
        </td>
    </tr>
    <% } %>
</table>
<%
    } else {
        // Si la lista de productos está vacía o nula, mostrar un mensaje
        out.println("<p>No hay productos disponibles.</p>");
    }
%>
</body>
</html>

