package org.example.servlet.login.repositorio;

import org.example.servlet.login.models.Producto;
import org.example.servlet.login.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRespositoryJdbcImpl implements Repositorios<Producto>{

    private Connection connection;

    public ProductoRespositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Producto> listar() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (Statement smt = connection.createStatement();
             ResultSet rs = smt.executeQuery(
                     "SELECT p.idarticulo, p.codigo, p.nombre AS nombre_producto, " +
                             "p.stock, p.descripcion, p.imagen, p.condicion, p.precio, " +
                             "c.idcategoria, c.nombre AS nombre_categoria, c.descripcion AS descripcion_categoria, c.condicion AS condicion_categoria " +
                             "FROM producto p " +
                             "JOIN categoria c ON p.idcategoria = c.idcategoria")) {
            while (rs.next()) {
                Producto p = getProducto(rs);
                productos.add(p);
            }
        }
        return productos;
    }


    @Override
    public Producto porId(Integer id) throws SQLException {
        Producto producto = null;
        String query = "SELECT p.idarticulo, p.codigo, p.nombre AS nombre_producto, " +
                "p.stock, p.descripcion, p.imagen, p.condicion, p.precio, " +
                "c.idcategoria, c.nombre AS nombre_categoria, c.descripcion AS descripcion_categoria, c.condicion AS condicion_categoria " +
                "FROM producto p " +
                "JOIN categoria c ON p.idcategoria = c.idcategoria " +
                "WHERE p.idarticulo = ?";


        try (PreparedStatement smt = connection.prepareStatement(query)) {
            smt.setInt(1, id);
            try (ResultSet rs = smt.executeQuery()) {
                if (rs.next()) {
                    producto = getProducto(rs);
                }
            }
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) throws SQLException {
        String sql;
        if (producto.getId() != null && producto.getId() > 0) {
            // Actualizar producto existente
            sql = "UPDATE producto SET nombre=?, idcategoria=?, descripcion=?, precio=?, condicion=? WHERE idarticulo=?";
            try (PreparedStatement smt = connection.prepareStatement(sql)) {
                smt.setString(1, producto.getNombre());
                smt.setInt(2, producto.getCategoria().getIdCategoria());
                smt.setString(3, producto.getDescripcion());
                smt.setDouble(4, producto.getPrecio());
                smt.setInt(6, producto.getId());
                smt.executeUpdate();
            }
        } else {
            // Insertar nuevo producto
            sql = "INSERT INTO producto(nombre, idcategoria, descripcion, precio, condicion) VALUES(?, ?, ?, ?, ?)";
            try (PreparedStatement smt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                smt.setString(1, producto.getNombre());
                smt.setInt(2, producto.getCategoria().getIdCategoria());
                smt.setString(3, producto.getDescripcion());
                smt.setDouble(4, producto.getPrecio());

                smt.executeUpdate();

                // Obtener el ID generado para el nuevo producto
                try (ResultSet generatedKeys = smt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        producto.setId(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }


    @Override
    public void eliminar(Integer id) throws SQLException {
        String sql = "DELETE FROM producto WHERE idarticulo = ?";
        try (PreparedStatement smt = connection.prepareStatement(sql)) {
            smt.setInt(1, id);
            smt.executeUpdate();
        }
    }


    @Override
    public Producto activar(Integer id) throws SQLException {
        return null;
    }

    @Override
    public Producto desactivar(Integer id) throws SQLException {
        return null;
    }
    private static Producto getProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getInt("idarticulo"));
        p.setCodigo(rs.getString("codigo"));
        p.setNombre(rs.getString("nombre_producto"));
        p.setStock(rs.getInt("stock"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setImagen(rs.getString("imagen"));
        p.setCondicion(rs.getInt("condicion"));
        p.setPrecio(rs.getFloat("precio"));

        // Crear objeto Categoria y asignar sus atributos
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(rs.getInt("idcategoria"));
        categoria.setNombre(rs.getString("nombre_categoria"));
        categoria.setDescripcion(rs.getString("descripcion_categoria"));
        categoria.setCondicion(rs.getBoolean("condicion_categoria"));

        // Asignar la categoria al producto
        p.setCategoria(categoria);

        return p;
    }
}

