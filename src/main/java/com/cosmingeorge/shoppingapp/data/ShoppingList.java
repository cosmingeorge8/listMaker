package com.cosmingeorge.shoppingapp.data;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList {
    private List<Product> productList;

    public ShoppingList() {
        productList = new ArrayList<>();
    }

    public ShoppingList(List<Product> productList) {
        this.productList = productList;
    }

    public void addProduct(Product product) {
        if (product != null) {
            productList.add(product);
        }
    }

    public void addRecipe(Recipe recipe) {
        for (Product product : recipe.getProductList()) {
            if (product.getQuantity() != 0)
                if (productList.contains(product)) {
                    product.setAmount(product.getAmount() + 1);
                } else {
                    productList.add(product);
                }
        }
    }

    public void removeProduct(Product product) {
        productList.remove(product);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
