<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="org.example.servlet.login.models.Categoria" %>
<%@ page import="org.example.servlet.login.models.Producto" %>

<%
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
    Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
    Producto producto = (Producto) request.getAttribute("producto");
    if (producto == null) {
        producto = new Producto();  // Inicializa un nuevo objeto Producto si es null
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar Producto</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .card {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 400px;
            padding: 20px;
            box-sizing: border-box;
        }
        h2 {
            color: #333;
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-top: 10px;
            color: #555;
        }
        input, textarea, select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            box-sizing: border-box;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .error {
            color: red;
            margin-top: 5px;
        }
        .submit-btn {
            background-color: #333;
            color: white;
            padding: 10px 20px;
            border: none;
            cursor: pointer;
            margin-top: 20px;
            border-radius: 4px;
            width: 100%;
        }
        .submit-btn:hover {
            background-color: #555;
        }
    </style>
</head>
<body>
<div class="card">
    <h2>Agregar Nuevo Producto</h2>
    <form action="<%= request.getContextPath() %>/formulario" method="post">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" value="<%= producto.getNombre() %>" required>
        <% if (errores != null && errores.containsKey("nombre")) { %>
        <div class="error"><%= errores.get("nombre") %></div>
        <% } %>

        <label for="descripcion">Descripción:</label>
        <textarea id="descripcion" name="descripcion" required><%= producto.getDescripcion() %></textarea>
        <% if (errores != null && errores.containsKey("descripcion")) { %>
        <div class="error"><%= errores.get("descripcion") %></div>
        <% } %>

        <label for="precio">Precio:</label>
        <input type="text" id="precio" name="precio" value="<%= producto.getPrecio() %>" required>
        <% if (errores != null && errores.containsKey("precio")) { %>
        <div class="error"><%= errores.get("precio") %></div>
        <% } %>

        <label for="stock">Stock:</label>
        <input type="text" id="stock" name="stock" value="<%= producto.getStock() %>" required>
        <% if (errores != null && errores.containsKey("stock")) { %>
        <div class="error"><%= errores.get("stock") %></div>
        <% } %>

        <label for="idCategoria">Categoría:</label>
        <select id="idCategoria" name="idCategoria" required>
            <% if (categorias != null) { %>
            <% for (Categoria c : categorias) { %>
            <option value="<%= c.getIdCategoria() %>" <%= (producto.getCategoria() != null && c.getIdCategoria() == producto.getCategoria().getIdCategoria()) ? "selected" : "" %>><%= c.getNombre() %></option>
            <% } %>
            <% } %>
        </select>
        <% if (errores != null && errores.containsKey("Categoria")) { %>
        <div class="error"><%= errores.get("Categoria") %></div>
        <% } %>

        <input type="hidden" name="id" value="<%= producto.getId() %>">
        <button type="submit" class="submit-btn"><%= (producto.getId() != null && producto.getId() > 0 ? "Editar" : "Crear") %></button>
    </form>
</div>
</body>
</html>
