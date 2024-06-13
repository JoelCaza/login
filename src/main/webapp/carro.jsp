<%--
  Created by IntelliJ IDEA.
  User: Estudiante
  Date: 13/6/2024
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="org.example.servlet.login.models.*" %>
<%Carro carro = (Carro) session.getAttribute("carro"); %>
<html>
<head>
    <title>Carro de Compras</title>
</head>
<body>
    <h1>Carro de Compras</h1>
    <%if(carro == null || carro.getItems().isEmpty()){
    %>
    <p>Lo sentimos no hay productos en el carro de compras!</p>
    <%} else { %>
<table>
<tr>
    <th>id</th>
    <th>Nombre</th>
    <th>Precio</th>
    <th>Cantidad</th>
    <th>Subtotal</th>
</tr>
    <%for(ItemCarro item : carro.getItems()){%>
    <tr>
        <td><%=item.getProducto().getId()%></td>
        <td><%=item.getProducto().getNombre()%></td>
        <td><%=item.getProducto().getPrecio()%></td>
        <td><%=item.getCantidad()%></td>
        <td><%=item.getImporte()%></td>
    </tr>
    <% }%>
    <tr>
    <td colspan="4">
    Total:
    </td>
        <td><%=carro.getTotal()%></td>
    </tr>

</table>
<%}%>
    <a href="<%=request.getContextPath()%>/productos">Seguir Comprando</a>
    <a href="<%=request.getContextPath()%>/index.html">Volver</a>



</body>
</html>
