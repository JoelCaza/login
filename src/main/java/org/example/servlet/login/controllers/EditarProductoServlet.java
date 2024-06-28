package org.example.servlet.login.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.servlet.login.models.Producto;
import org.example.servlet.login.services.ProductoService;
import org.example.servlet.login.services.ProductoServiceJdbcImplement;
import org.example.servlet.login.models.*;


import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet("/editarProducto")
public class EditarProductoServlet extends HttpServlet {

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

        Optional<Producto> productoOptional = service.porId(idProducto);
        if (productoOptional.isPresent()) {
            req.setAttribute("producto", productoOptional.get());
            List<Categoria> categorias = service.listarCategorias();
            req.setAttribute("categorias", categorias);
            req.getRequestDispatcher("/editarProducto.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Producto no encontrado");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImplement(conn);
        Integer idProducto = Integer.parseInt(req.getParameter("id"));
        String nombre = req.getParameter("nombre");
        Integer idcategoria;
        try {
            idcategoria = Integer.parseInt(req.getParameter("idCategoria"));
        } catch (NumberFormatException e) {
            idcategoria = 0;
        }
        String descripcionCategoria = req.getParameter("descripcion");
        Double precio;
        try {
            precio = Double.valueOf(req.getParameter("precio"));
        } catch (NumberFormatException e) {
            precio = 0.0;
        }
        Integer stock;
        try {
            stock = Integer.parseInt(req.getParameter("stock"));
        } catch (NumberFormatException e) {
            stock = 0;
        }

        Map<String, String> errores = new HashMap<>();
        if (nombre == null || nombre.isBlank()) {
            errores.put("nombre", "El nombre no puede estar vacío");
        }
        if (descripcionCategoria == null || descripcionCategoria.isBlank()) {
            errores.put("descripcion", "La descripción no puede estar vacía");
        }
        if (precio == 0 || precio < 0) {
            errores.put("precio", "El precio no puede estar vacío o ser negativo");
        }
        if (idcategoria.equals(0)) {
            errores.put("Categoria", "La categoría no puede estar vacía");
        }
        if (stock == 0 || stock < 0) {
            errores.put("stock", "El stock no puede estar vacío o ser negativo");
        }

        Producto producto = new Producto();
        producto.setId(idProducto);
        producto.setNombre(nombre);
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idcategoria);
        producto.setCategoria(categoria);
        producto.setPrecio(precio);
        producto.setDescripcion(descripcionCategoria);
        producto.setStock(stock);

        if (errores.isEmpty()) {
            service.guardar(producto);
            resp.sendRedirect(req.getContextPath() + "/productos");
        } else {
            req.setAttribute("errores", errores);
            req.setAttribute("producto", producto);
            req.getRequestDispatcher("/editarProducto.jsp").forward(req, resp);
        }
    }
}

