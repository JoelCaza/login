<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Producto</title>
</head>
<body>
<h2>Editar Producto</h2>
<c:if test="${not empty producto}">
    <form action="editarProducto" method="post">
        <input type="hidden" name="id" value="${producto.id}">
        Nombre: <input type="text" name="nombre" value="${producto.nombre}" required><br>
        Categoría: <!-- Cargar categorías disponibles --> <br>
        Descripción: <textarea name="descripcion">${producto.descripcion}</textarea><br>
        Precio: <input type="text" name="precio" value="${producto.precio}" required><br>
        <input type="submit" value="Guardar">
    </form>
</c:if>
<c:if test="${empty producto}">
    <p>Producto no encontrado.</p>
</c:if>
</body>
</html>
