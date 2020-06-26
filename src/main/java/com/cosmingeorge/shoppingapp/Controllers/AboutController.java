package com.cosmingeorge.shoppingapp.Controllers;

import com.cosmingeorge.shoppingapp.data.AppAdmin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about")
public class AboutController {

    @GetMapping
    public String getHome(Model model){
        model.addAttribute("developers", AppAdmin.getInstance().getDevelopers());
        return "about";
    }
}
