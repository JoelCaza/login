<%--
  Created by IntelliJ IDEA.
  User: Estudiante
  Date: 20/6/2024
  Time: 8:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Eliminar Producto</title>
</head>
<body>
<h2>Eliminar Producto</h2>
<p>¿Está seguro que desea eliminar el producto ${producto.nombre}?</p>
<form action="eliminarProducto" method="post">
    <input type="hidden" name="id" value="${producto.id}">
    <input type="submit" value="Eliminar">
</form>
</body>
</html>
