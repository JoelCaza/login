package org.example.servlet.login.services;

import org.example.servlet.login.models.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    List<Producto> listar();
    //Metodo para obtener el producto por id
    Optional<Producto> porId(Integer id);

    void guardar(Producto producto);
    void eliminar(Integer id);
}
