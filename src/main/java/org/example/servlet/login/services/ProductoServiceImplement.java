package org.example.servlet.login.services;

import org.example.servlet.login.models.Producto;

import java.util.Arrays;
import java.util.List;

public class ProductoServiceImplement implements ProductoService {

    @Override
    public List<Producto> listar(){
        return Arrays.asList(new Producto(1,"Laptop","Computacion","HP",650.250),
                new Producto(2,"Pc","Electronica","Asus",1050),
                new Producto(3,"Ram","Componente","Adata",40.50),
                new Producto(4,"Disco Duro","Electronica","Adata",80.99),
                new Producto(5,"Disipador","Computacion","Intel",20.50)
                );

    }

}
