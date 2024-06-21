package org.example.servlet.login.repositorio;

import org.example.servlet.login.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositoryJdbcImplement implements Repositorios<Categoria>{
    private Connection connection;
    public CategoriaRepositoryJdbcImplement(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Categoria> listar() throws SQLException {
        List <Categoria> categorias = new ArrayList<>();
        try(Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM categoria");
            while (rs.next()) {
                Categoria categoria = getCategoria(rs);
                categorias.add(categoria);
            }
        }
        return categorias;
    }


    @Override
    public Categoria porId(Integer id) throws SQLException {
        Categoria categoria = null;
        try(PreparedStatement stmt = connection.prepareStatement("SELECT * FROM categoria WHERE id = ?")) {
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()) {
                    categoria = getCategoria(rs);
                }
            }
        }
        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {

    }

    @Override
    public void eliminar(Integer id) throws SQLException {

    }

    @Override
    public Categoria activar(Integer id) throws SQLException {
        return null;
    }

    @Override
    public Categoria desactivar(Integer id) throws SQLException {
        return null;
    }

    private  Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setNombre(rs.getString("nombre"));
        categoria.setIdCategoria(rs.getInt("idcategoria"));
        return categoria;

    }

}
