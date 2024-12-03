package model;

import utils.Utils;

public class Product {

    private static int count = 1;

    private int id;
    private String name;
    private Double price;

    public Product(Double price, String name) {
        this.id = count;
        this.price = price;
        this.name = name;
        Product.count += 1;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String toString(){
        return  "Id: " + this.getId() +
                "\nName: " + this.getName() +
                "\nPrice: " + Utils.doubleToString(this.getPrice());
    }

}