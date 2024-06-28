package org.example.servlet.login.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servlet.login.services.ProductoService;
import org.example.servlet.login.services.ProductoServiceJdbcImplement;


import java.io.IOException;
import java.sql.Connection;

@WebServlet("/eliminarProducto")
public class EliminarProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImplement(conn);
        Integer idProducto;
        try {
            idProducto = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            idProducto = 0;
        }

        if (idProducto > 0) {
            service.eliminar(idProducto);
            resp.sendRedirect(req.getContextPath() + "/productos");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de producto inválido");
        }
    }
}
