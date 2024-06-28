<%-- Importa la clase NumberFormat para formatear los precios --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.text.NumberFormat, org.example.servlet.login.models.*" %>
<%-- Obtiene el objeto Carro de la sesión --%>
<% Carro carro = (Carro) session.getAttribute("carro"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Carro de Compras</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }
        h1 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .total-row {
            font-weight: bold;
        }
        .total-row td {
            border-top: 2px solid #333;
        }
        .button {
            display: inline-block;
            padding: 10px 20px;
            margin-top: 20px;
            background-color: #333;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .button:hover {
            background-color: #555;
        }
        .navbar {
            overflow: hidden;
            background-color: #333;
            padding: 10px;
        }
        .navbar a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 14px 20px;
            text-decoration: none;
        }
        .navbar a:hover {
            background-color: #ddd;
            color: black;
        }
    </style>
</head>
<body>
<div class="navbar">
    <a href="<%= request.getContextPath() %>/index.html">Inicio</a>
    <a href="<%= request.getContextPath() %>/LogoutServlet">Salir</a>
</div>
<div class="container">
    <h1>Carro de Compras</h1>
    <%-- Verifica si el carro está vacío --%>
    <% if(carro == null || carro.getItems().isEmpty()) { %>
    <p>Lo sentimos, no hay productos en el carro de compras.</p>
    <% } else { %>
    <table>
        <tr>
            <th>Id</th>
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
        <tr class="total-row">
            <td colspan="4">Total:</td>
            <%-- Formatea el total usando NumberFormat --%>
            <td><%= NumberFormat.getCurrencyInstance().format(carro.getTotal()) %></td>
        </tr>
    </table>
    <% } %>
    <a href="<%= request.getContextPath() %>/productos" class="button">Seguir Comprando</a>
    <a href="<%= request.getContextPath() %>/index.html" class="button">Volver</a>
</div>
</body>
</html>
