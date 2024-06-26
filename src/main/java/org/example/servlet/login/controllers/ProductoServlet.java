package org.example.servlet.login.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.servlet.login.models.Producto;
import org.example.servlet.login.services.LoginService;
import org.example.servlet.login.services.ProductoService;
import org.example.servlet.login.services.ProductoServiceJdbcImplement;
import org.example.servlet.login.services.LoginServiceimplment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productoServlet","/productos"})
public class ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener la conexión a la base de datos desde el contexto
        Connection con = (Connection) req.getAttribute("conn");

        // Obtener la lista de productos desde el servicio
        ProductoService productoService = new ProductoServiceJdbcImplement(con);
        List<Producto> productos = productoService.listar();

        // Obtener el nombre de usuario y el rol desde la sesión
        HttpSession session = req.getSession();
        Optional<String> usernameOptional = Optional.ofNullable((String) session.getAttribute("username"));
        String role = (String) session.getAttribute("role");

        // Configurar atributos en el request para pasar al JSP
        req.setAttribute("productos", productos);
        req.setAttribute("username", usernameOptional);
        req.setAttribute("role", role);

        // Redirigir al JSP para mostrar el listado de productos
        req.getRequestDispatcher("/listadoCarro.jsp").forward(req, resp);
    }
}
