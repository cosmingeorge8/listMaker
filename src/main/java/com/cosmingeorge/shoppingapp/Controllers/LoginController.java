package com.cosmingeorge.shoppingapp.Controllers;

import com.cosmingeorge.shoppingapp.data.AppAdmin;
import com.cosmingeorge.shoppingapp.data.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")

public class LoginController {

    AppAdmin appAdmin = AppAdmin.getInstance();

    @PostMapping("/register")
    public String register(@ModelAttribute User user,Model model){
        for (User user1 : appAdmin.getUserList()){
            if (user1.getUsername().equals(user.getUsername())){
                model.addAttribute("message", "User already exists");
                return "postRegister";
            }
        }
        if (user.getUsername()!=null && user.getUsername()!=null)
            user.setShoppingList(appAdmin.getShoppingList());
        appAdmin.getUserList().add(user);
        model.addAttribute("message","User was successfully added");
        return "postRegister";
    }

    @GetMapping("/register")
    public String getRegisterForm(){
        return "register";
    }

    @GetMapping("/login")
    public String getLoginForm(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model, HttpServletRequest request, HttpSession session){
        session.setAttribute("loggedIn","true");
        session.setAttribute("username",user.getUsername());
        String referer = request.getHeader("Referer");
        if (appAdmin.logIn(user)) {
            model.addAttribute("message", "You are logged in now");
            return "postRegister";
        }
        model.addAttribute("message", "Check your username and password");
       return  "redirect:"+ referer;

    }

    @GetMapping("/lostPassword")
    @ResponseBody
    public String lostPassword(){
        return "We are working on that!";
    }


    //!!!! Only for testing
    @GetMapping("/users")
    @ResponseBody
    public List<User> getUsers(){
        return appAdmin.getUserList();
    }

}
