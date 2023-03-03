package com.example.tpDevOps.controller;

import com.example.tpDevOps.sec.entities.AppUser;
import com.example.tpDevOps.service.DemandeurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {

    private DemandeurService demandeurService;

    @GetMapping("/403")
    public String notAutorized(){
        return "403";
    }


    @GetMapping("/")
    public String home(){

        return "home";
    }

    @GetMapping("/loginUser")
    public String showLogin() {
        return "login/loginpage";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute(name="loginForm") AppUser appUser, Model m) {
        String uname = appUser.getUsername();
        String pass = appUser.getPassword();
        if(uname.equals("kasse") && pass.equals("11111")) {
            m.addAttribute("uname", uname);
            m.addAttribute("pass", pass);
            return "home";
        }
        m.addAttribute("error", "Incorrect Username & Password");
        return "login/loginpage";
    }

}
