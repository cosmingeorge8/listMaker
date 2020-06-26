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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/recipes")

public class RecipeController {

    AppAdmin appAdmin = AppAdmin.getInstance();

    @GetMapping("/recipe")
    public String getRecipeById(@RequestParam("id")
                                        int recipe_id, Model model, HttpSession session){
        Recipe recipe = appAdmin.getRecipeById(recipe_id);
        if (recipe==null){
            return "not found";
        }
        model.addAttribute("recipe",recipe);
        if(session.getAttribute("loggedIn").equals("true")){
            User user = appAdmin.getUserByUsername((String) session.getAttribute("username"));
            model.addAttribute("cart",user.getShoppingList().getProductList());
        }else{
            model.addAttribute("cart",appAdmin.getShoppingList().getProductList());
        }
        return "recipe";
    }

    @GetMapping
    public String getRecipes(Model model,HttpSession session){

        if(session.getAttribute("loggedIn").equals("true")){
            User user = appAdmin.getUserByUsername((String) session.getAttribute("username"));
            model.addAttribute("cart",user.getShoppingList().getProductList());
        }
        else{
            model.addAttribute("cart",appAdmin.getShoppingList().getProductList());
        }
        model.addAttribute("recipes",appAdmin.getRecipes());
        return "recipes";
    }

    @GetMapping("/add")
    public String addRecipeForm(Model model){
        model.addAttribute("recipe",new Recipe());
        return "addrecipe";
    }


    @PostMapping("/add")
    public String addRecipeToList(@ModelAttribute Recipe recipe){
        if (recipe.getName()!=null && recipe.getProductList()!=null){
            appAdmin.getRecipes().add(recipe);
            for (Product product : recipe.getProductList()){
                if (product.getQuantity()>0){
                    appAdmin.getOthersCategory().addProduct(product);
                }
            }
        }
        return "redirect:/recipes/recipe?id=" + recipe.getId() ;
    }


}
