package com.cosmingeorge.shoppingapp.data;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    private String name;
    private String description;
    private List<Product> productList = new ArrayList<>();
    private int time;
    private int id;
    private String img;
    private static int counter=1;


    public Recipe() {
        id=counter;
        counter++;
        img=("/images/noimage.png");

    }

    public Recipe(String name, String description, List<Product> productList, int time, String image) {
        this.name = name;
        this.description = description;
        this.productList = productList;
        this.time = time;
        this.img = image;
        id=counter;
        counter++;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "Recipe{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", productList=" + productList +
                ", time=" + time +
                ", id=" + id +
                ", img='" + img + '\'' +
                '}';
    }
}

