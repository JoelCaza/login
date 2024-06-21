package org.example.servlet.login.services;

import org.example.servlet.login.models.Categoria;
import org.example.servlet.login.models.Producto;
import org.example.servlet.login.repositorio.CategoriaRepositoryJdbcImplement;
import org.example.servlet.login.repositorio.ProductoRespositoryJdbcImpl;
import org.example.servlet.login.repositorio.Repositorios;

import javax.swing.text.html.Option;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductoServiceJdbcImplement implements ProductoService {
    private Repositorios<Producto> repositorioJdbc;
    private Repositorios<Categoria> repositoryCategoriaJdbc;
    public ProductoServiceJdbcImplement(Connection connection) {

        this.repositorioJdbc = new ProductoRespositoryJdbcImpl(connection);
        this.repositoryCategoriaJdbc = new CategoriaRepositoryJdbcImplement(connection);
    }


    @Override
    public List<Producto> listar() {
        try{
            return repositorioJdbc.listar();

        }catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Producto> porId(Integer id) {
        try{
            return Optional.ofNullable(repositorioJdbc.porId(id));
        }catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());

        }
    }

    @Override
    public void guardar(Producto producto) {

    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public Optional<Producto> poridCategoria(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Categoria> listarCategorias() {

        try{
            return  repositoryCategoriaJdbc.listar();
        }catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Categoria> porIdCategoria(Integer id){
        try {
            return Optional.ofNullable(repositoryCategoriaJdbc.porId(id));
        }
        catch (SQLException throwables){
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }

    }

}
