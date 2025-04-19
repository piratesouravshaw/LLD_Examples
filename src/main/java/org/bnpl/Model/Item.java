package org.bnpl.Model;

public class Item {
    String name;
    Double price;

    public Item(String name, Double price) {
        this.name = name;
        this.price = price;
    }
    public Double getPrice(){
        return this.price;
    }
    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
