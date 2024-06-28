package org.example.servlet.login.services;

import org.example.servlet.login.models.Categoria;
import org.example.servlet.login.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listar();
    Optional<Producto> porId(Integer id);
    void guardar(Producto producto);
    void eliminar(Integer id);
    Optional<Producto> poridCategoria(Integer id);
    List<Categoria> listarCategorias();
    Optional<Categoria> porIdCategoria(Integer id);
}

