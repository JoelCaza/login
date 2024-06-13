package org.example.servlet.login.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Carro {
    //Creamos una variable en el cual inicializamos lalista de productos comprados que van
    //a estar en el carro
    private List<ItemCarro> items;
    public Carro() {
        this.items = new ArrayList<>();
    }
    public Carro(List<ItemCarro> items) {
        this.items = items;
    }
    //Metodo para añadir los items al carrito

    public void addItem(ItemCarro itemCarro) {
        if( items.contains(itemCarro) ){
            Optional<ItemCarro> optionalItemCarro = items.stream().filter(i -> i.equals(itemCarro)).findAny();
            if( optionalItemCarro.isPresent() ){
                ItemCarro i = optionalItemCarro.get();
                i.setCantidad(i.getCantidad() + 1);
            }
        }
        else {
            this.items.add(itemCarro);
        }
    }
    public List<ItemCarro> getItems() {
        return items;
    }
    public double getTotal(){
        return items.stream().mapToDouble(ItemCarro::getImporte).sum();
    }



}
