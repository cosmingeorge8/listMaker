package com.cosmingeorge.shoppingapp.Controllers;


import com.cosmingeorge.shoppingapp.data.AppAdmin;
import com.cosmingeorge.shoppingapp.data.Product;
import com.cosmingeorge.shoppingapp.data.Recipe;
import com.cosmingeorge.shoppingapp.data.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {
    AppAdmin appAdmin = AppAdmin.getInstance();

    @PostMapping("/recipe/{recipe_id}")
    public String addRecipeToShoppingList(@PathVariable("recipe_id")
                                                  int recipe_id, HttpServletRequest request, HttpSession session){
        Recipe recipe = appAdmin.getRecipeById(recipe_id);
        if (session.getAttribute("loggedIn").equals("true")){
            User user = appAdmin.getUserByUsername((String) session.getAttribute("username"));
            user.getShoppingList().addRecipe(recipe);
        }else{
            appAdmin.getShoppingList().addRecipe(recipe);
        }
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

    @PostMapping("/{product_id}")
    public String addProductOnShoppingList(@PathVariable("product_id") int id, HttpServletRequest request,HttpSession session){
        Product product = appAdmin.getProductById(id);
        String referer = request.getHeader("Referer");

        if(session.getAttribute("loggedIn").equals("true") ){
            User user = appAdmin.getUserByUsername((String) session.getAttribute("username"));
            for (Product product1 : user.getShoppingList().getProductList()){
                if (product.getId()==product1.getId()){
                    product.setAmount(product.getAmount()+1);
                    return "redirect:" + referer;
                }
            }
            user.getShoppingList().addProduct(product);
            return "redirect:"+ referer;
        }

        for (Product product1 : appAdmin.getShoppingList().getProductList()){
            if (product.getId()==product1.getId()){
                product.setAmount(product.getAmount()+1);
                return "redirect:" + referer;
            }
        }
        appAdmin.getShoppingList().addProduct(product);
        return "redirect:"+ referer;
    }

    @GetMapping("/{product_id}")
    public String removeProductFromShoppingList(@PathVariable("product_id")
                                                        int product_id, HttpServletRequest request,HttpSession session){
        Product product = appAdmin.getProductById(product_id);
        String referer = request.getHeader("Referer");

        if (session.getAttribute("loggedIn").equals("true")){
            if(product.getAmount()<=1){
                User user = appAdmin.getUserByUsername((String) session.getAttribute("username"));
                user.getShoppingList().removeProduct(product);
            }else{
                product.setAmount(product.getAmount()-1);
            }
            return "redirect:"+ referer;
        }

        if(product.getAmount()<=1){
            appAdmin.getShoppingList().removeProduct(product);
        }else{
            product.setAmount(product.getAmount()-1);
        }
        return "redirect:"+ referer;
    }

    @GetMapping
    public String getShoppingList(Model model){
        model.addAttribute("cart",appAdmin.getShoppingList().getProductList());
        model.addAttribute("categories",appAdmin.getCategories());
        return "index";
    }
}
