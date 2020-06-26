package com.cosmingeorge.shoppingapp.data;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private List<Product> productList;
    private String name;
    private int id;
    private static int counter=1;
    private String imgPath;

    public Category() {
        productList = new ArrayList<>();
        id = counter;
        counter++;
    }

    public Category(String name,String imgPath) {
        this.name = name;
        this.imgPath=imgPath;
        productList = new ArrayList<>();
        id=counter;
        counter++;
    }

    public int getId() {
        return id;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public void  addProduct(Product product){
        if (product!=null){
            productList.add(product);
        }
    }
}
