package com.cosmingeorge.shoppingapp.data;

public class Product implements Comparable<Product>{
    private String name;
    private double quantity;
    private int amount;
    private int id;
    private String img;
    private static int counter=1;


    public Product() {
        img=("/images/noimage.png");
        amount = 1;
        id = counter;
        counter++;
    }

    public Product(String name, double quantity,String img) {
        this.name = name;
        this.quantity = quantity;
        id=counter;
        counter++;
        amount =1;
        this.img=img;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getImg() {
        return img;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }


    @Override
    public int compareTo(Product o) {
        return name.compareTo(o.getName());
    }

}
