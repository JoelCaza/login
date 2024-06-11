package org.example.servlet.login.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servlet.login.models.Producto;
import org.example.servlet.login.services.LoginService;
import org.example.servlet.login.services.LoginServiceimplment;
import org.example.servlet.login.services.ProductoService;
import org.example.servlet.login.services.ProductoServiceImplement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet({"/productoServlet","/productos"})
public class ProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImplement();
        List<Producto> productos = service.listar();
        LoginService auth = new LoginServiceimplment();
        Optional<String> usernameOptional = auth.getUserName(req);
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Listado de Productos</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; }");
            out.println("table { border-collapse: collapse; width: 100%; }");
            out.println("th, td { border: 1px solid #dddddd; text-align: left; padding: 8px; }");
            out.println("th { background-color: #f2f2f2; }");
            out.println(".navbar { overflow: hidden; background-color: #333; }");
            out.println(".navbar a { float: left; display: block; color: white; text-align: center; padding: 14px 20px; text-decoration: none; }");
            out.println(".navbar a:hover { background-color: #ddd; color: black; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='navbar'>");
            out.println("<a href='/login_war_exploded/index.html'>Inicio</a>");
            out.println("<a href='/login_war_exploded/LogoutServlet'>Salir</a>");
            out.println("</div>");
            out.println("<h1>Listado de Productos</h1>");
            if (usernameOptional.isPresent()) {
                out.println("<div style='color:blue;'>Hola " + usernameOptional.get() + ", ¡Bienvenido!</div>");
            }
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Id</th>");
            out.println("<th>Nombre</th>");
            out.println("<th>Categoría</th>");
            out.println("<th>Descripción</th>");
            if (usernameOptional.isPresent()) {
                out.println("<th>Precio</th>");
            }
            out.println("</tr>");
            productos.forEach(p -> {
                out.println("<tr>");
                out.println("<td>" + p.getId() + "</td>");
                out.println("<td>" + p.getNombre() + "</td>");
                out.println("<td>" + p.getCategoria() + "</td>");
                out.println("<td>" + p.getDescripcion() + "</td>");
                if (usernameOptional.isPresent()) {
                    out.println("<td>" + p.getPrecio() + "</td>");
                }
                out.println("</tr>");
            });
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
