<%@ page import="org.example.servlet.login.models.Producto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Optional" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Productos</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body { font-family: Arial, sans-serif; background-color: #f0f0f0; margin: 0; padding: 0; }
        .container { width: 80%; margin: 0 auto; padding: 20px; background-color: #fff; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); border-radius: 8px; }
        h1 { color: #333; }
        table { border-collapse: collapse; width: 100%; margin-top: 20px; }
        th, td { border: 1px solid #dddddd; text-align: left; padding: 8px; }
        th { background-color: #f2f2f2; }
        .navbar { overflow: hidden; background-color: #333; padding: 10px; }
        .navbar a { float: left; display: block; color: white; text-align: center; padding: 14px 20px; text-decoration: none; }
        .navbar a:hover { background-color: #ddd; color: black; }
        .actions a { margin-right: 10px; color: #333; text-decoration: none; display: inline-block; padding: 5px 10px; border-radius: 4px; }
        .actions a:hover { text-decoration: underline; }
        .add-button { margin-top: 10px; display: inline-block; padding: 10px 20px; background-color: #333; color: white; text-decoration: none; border-radius: 5px; }
        .add-button:hover { background-color: #555; }
        .button-icon { margin-right: 5px; }
        .action-button { display: flex; align-items: center; }
    </style>
</head>
<body>
<div class="navbar">
    <a href="${pageContext.request.contextPath}/index.html">Inicio</a>
    <a href="${pageContext.request.contextPath}/LogoutServlet">Salir</a>
</div>
<div class="container">
    <h1>Listado de Productos Carrito</h1>
    <%
        String role = (String) request.getAttribute("role");
        if ("admin".equals(role)) {
    %>
    <a href="${pageContext.request.contextPath}/formulario" class="add-button"><i class="fas fa-plus button-icon"></i>Añadir Producto</a>
    <%
        }
    %>
    <%-- Verificar si la lista de productos no es nula y no está vacía --%>
    <%
        List<Producto> productos = (List<Producto>) request.getAttribute("productos");
        if (productos != null && !productos.isEmpty()) {
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
            <td class="actions">
                <a href="${pageContext.request.contextPath}/agregar-carro?id=<%= p.getId() %>" class="action-button"><i class="fas fa-cart-plus button-icon"></i>Agregar al Carro</a>
                <% if ("admin".equals(role)) { %>
                <a href="${pageContext.request.contextPath}/editarProducto?id=<%= p.getId() %>" class="action-button"><i class="fas fa-edit button-icon"></i>Editar</a>
                <a href="${pageContext.request.contextPath}/eliminarProducto?id=<%= p.getId() %>" class="action-button"><i class="fas fa-trash button-icon"></i>Eliminar</a>
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
</div>
</body>
</html>
