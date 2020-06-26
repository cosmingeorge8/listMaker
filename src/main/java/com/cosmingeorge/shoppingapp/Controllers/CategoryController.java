package com.cosmingeorge.shoppingapp.Controllers;
import com.cosmingeorge.shoppingapp.data.AppAdmin;
import com.cosmingeorge.shoppingapp.data.Category;
import com.cosmingeorge.shoppingapp.data.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    AppAdmin appAdmin = AppAdmin.getInstance();

    @GetMapping
    @ResponseBody
    public List<Category> getCategories(){
        return appAdmin.getCategories();
    }


    @GetMapping("/{category_id}")
    public String getCategoryById(@PathVariable("category_id") int category_id,
                                  Model model, HttpSession session, @CookieValue("sorting") Cookie cookie){

        Category category = appAdmin.getCategoryById(category_id);

        if (cookie.getValue().equals("nameSorted")){
            Collections.sort(category.getProductList());
        }else if (cookie.getValue().equals("quantitySorted")){
            category.getProductList().sort((o1, o2) -> {
                if (o1.getQuantity() > o2.getQuantity()) {
                    return 1;
                } else if (o1.getQuantity() < o2.getQuantity()) {
                    return -1;
                }
                return 0;
            });
        }
        if (category==null){
            return "notfound";
        }

        model.addAttribute("category",category);
        if(session.getAttribute("loggedIn").equals("true")){
            User user = appAdmin.getUserByUsername((String) session.getAttribute("username"));
            model.addAttribute("cart",user.getShoppingList().getProductList());
            return "category";
        }
        model.addAttribute("cart",appAdmin.getShoppingList().getProductList());
        return "category";
    }

    @GetMapping("/{category_id}/sortedName")
    public String getSortedCategory(@PathVariable("category_id") int category_id, Model model, HttpSession session, HttpServletResponse response,
                                    @CookieValue("sorting") Cookie cookie){
        Category category = appAdmin.getCategoryById(category_id);
        if (category == null){
            return "notfound";
        }
        Collections.sort(category.getProductList());
        model.addAttribute("category", category);
        if (session.getAttribute("loggedIn").equals("true")){
            User user = appAdmin.getUserByUsername((String) session.getAttribute("username"));
            model.addAttribute("cart",user.getShoppingList().getProductList());
        }
        else{
            model.addAttribute("cart",appAdmin.getShoppingList().getProductList());
        }
        cookie.setValue("nameSorted");
        response.addCookie(cookie);
        return  "category";
    }

    @GetMapping("/{category_id}/sortedQuantity")
    public String getSortedCategoryByQuantity(@PathVariable("category_id") int category_id , Model model,HttpServletResponse response,
                                              HttpSession session,@CookieValue("sorting") Cookie cookie){
        Category category = appAdmin.getCategoryById(category_id);
        if (category == null){
            return "notfound";
        }
        category.getProductList().sort((o1, o2) -> {
            if (o1.getQuantity() > o2.getQuantity()) {
                return 1;
            } else if (o1.getQuantity() < o2.getQuantity()) {
                return -1;
            }
            return 0;
        });

        if (session.getAttribute("loggedIn").equals("true")){
            User user = appAdmin.getUserByUsername((String) session.getAttribute("username"));
            model.addAttribute("cart",user.getShoppingList().getProductList());
        }else{
            model.addAttribute("cart",appAdmin.getShoppingList().getProductList());
        }
        model.addAttribute("category", category);
        cookie.setValue("quantitySorted");
        response.addCookie(cookie);
        return  "category";
    }

}

