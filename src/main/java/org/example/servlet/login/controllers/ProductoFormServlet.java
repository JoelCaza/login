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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ProductoService service = new ProductoServiceJdbcImplement(conn);
        String nombre = req.getParameter("nombre");
        Integer idcategoria;
        try {
            idcategoria = Integer.parseInt(req.getParameter("idcategoria"));
        }catch (NumberFormatException e) {
            idcategoria = 0;


        }
        String descripcionCategoria = req.getParameter("descripcion");
        Double precio = Double.valueOf(req.getParameter("precio"));
        try {
            precio = Double.valueOf(req.getParameter("precio"));
        }catch (NumberFormatException e) {
            precio = 0.0;
        }
        Integer idProducto;

        try {
            idProducto = Integer.parseInt(req.getParameter("idProducto"));
        }
        catch (NumberFormatException e) {
            idProducto = 0;
        }
        Map<String,String> errores = new HashMap<>();
        if (nombre == null || nombre.isBlank() ) {
            errores.put("nombre", "El nombre no puede estar vacio");
        }
        if (descripcionCategoria == null || descripcionCategoria.isBlank() ) {
            errores.put("descripcion", "El descripcion no puede estar vacio");
        }
        if (precio == 0 || precio < 0){
            errores.put("precio", "El precio no puede estar vacio");
        }
        if(idcategoria.equals(0)){
            errores.put("Categoria", "El categoria no puede estar vacio");
        }




        Producto producto = new Producto();
        producto.setNombre(nombre);
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idcategoria);
        producto.setCategoria(categoria);
        producto.setPrecio(precio);
        producto.setDescripcion(descripcionCategoria);
        if (errores.isEmpty()){
            service.guardar(producto);
            resp.sendRedirect("/productos.jsp");
        }else {
            req.setAttribute("errores", errores);
        }



    }
}
