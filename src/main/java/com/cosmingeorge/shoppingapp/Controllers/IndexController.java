package com.cosmingeorge.shoppingapp.Controllers;
import com.cosmingeorge.shoppingapp.data.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@org.springframework.stereotype.Controller
@RequestMapping("/")
public class IndexController {

    private AppAdmin appAdmin = AppAdmin.getInstance();


    @GetMapping
    public String getHome(Model model, HttpSession session, HttpServletResponse response) {
        if (session.isNew()) {
            session.setAttribute("loggedIn","false");
        }
        if (session.getAttribute("loggedIn").equals("false")){
            model.addAttribute("cart", appAdmin.getShoppingList().getProductList());
        }
        else if (session.getAttribute("loggedIn").equals("true")) {
            User user = appAdmin.getUserByUsername((String) session.getAttribute("username"));
            model.addAttribute("cart", user.getShoppingList().getProductList());
        }

            model.addAttribute("categories", appAdmin.getCategories());
            model.addAttribute("loggedIn",session.getAttribute("loggedIn"));
            Cookie cookie = new Cookie("sorting",null);
            cookie.setMaxAge(400);
            response.addCookie(cookie);
            return "index";
    }

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @GetMapping("logout")
    public String logOut(Model model,HttpSession session){
        session.setAttribute("loggedIn","false");
        model.addAttribute("cart", appAdmin.getShoppingList().getProductList());
        model.addAttribute("categories", appAdmin.getCategories());
        return "index";

    }
}
