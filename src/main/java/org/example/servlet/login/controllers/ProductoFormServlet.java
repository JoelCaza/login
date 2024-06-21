package org.example.servlet.login.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servlet.login.models.Categoria;
import org.example.servlet.login.models.Producto;
import org.example.servlet.login.services.ProductoService;
import org.example.servlet.login.services.ProductoServiceJdbcImplement;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/formulario")
public class ProductoFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImplement(conn);

        // Obtener la lista de categorías
        List<Categoria> categorias = service.listarCategorias();

        // Establecer categorías como atributo en la solicitud
        req.setAttribute("categorias", categorias);

        // Enviar la solicitud al JSP para mostrar el formulario de agregar producto
        req.getRequestDispatcher("/agregarProducto.jsp").forward(req, resp);
    }
}
