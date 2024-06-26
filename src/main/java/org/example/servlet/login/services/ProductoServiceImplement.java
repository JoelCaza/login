package org.example.servlet.login.services;

import org.example.servlet.login.models.Categoria;
import org.example.servlet.login.models.Producto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImplement implements ProductoService {

    @Override
    public List<Producto> listar(){

        return Arrays.asList();


    }

    @Override
    public Optional<Producto> porId(Integer id) {
        return listar().stream().filter(p -> p.getId().equals(id)).findFirst();
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
        return List.of();
    }

    @Override
    public Optional<Categoria> porIdCategoria(Integer id) {
        return Optional.empty();
    }

}
