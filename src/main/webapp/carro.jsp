<%-- Importa la clase NumberFormat para formatear los precios --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.text.NumberFormat, org.example.servlet.login.models.*" %>
<%-- Obtiene el objeto Carro de la sesión --%>
<% Carro carro = (Carro) session.getAttribute("carro"); %>
<html>
<head>
    <title>Carro de Compras</title>
</head>
<body>
<h1>Carro de Compras</h1>
<%-- Verifica si el carro está vacío --%>
<% if(carro == null || carro.getItems().isEmpty()) { %>
<p>Lo sentimos, no hay productos en el carro de compras.</p>
<% } else { %>
<table border="1">
    <tr>
        <th>id</th>
        <th>Nombre</th>
        <th>Precio</th>
        <th>Cantidad</th>
        <th>Subtotal</th>
    </tr>
    <%-- Itera sobre los ítems del carro --%>
    <% for(ItemCarro item : carro.getItems()) { %>
    <tr>
        <td><%= item.getProducto().getId() %></td>
        <td><%= item.getProducto().getNombre() %></td>
        <%-- Formatea el precio usando NumberFormat --%>
        <td><%= NumberFormat.getCurrencyInstance().format(item.getProducto().getPrecio()) %></td>
        <td><%= item.getCantidad() %></td>
        <%-- Calcula e imprime el subtotal --%>
        <td><%= NumberFormat.getCurrencyInstance().format(item.getImporte()) %></td>
    </tr>
    <% } %>
    <tr>
        <td colspan="4">Total:</td>
        <%-- Formatea el total usando NumberFormat --%>
        <td><%= NumberFormat.getCurrencyInstance().format(carro.getTotal()) %></td>
    </tr>
</table>
<% } %>
<a href="<%= request.getContextPath() %>/productos">Seguir Comprando</a>
<a href="<%= request.getContextPath() %>/index.html">Volver</a>
</body>
</html>
