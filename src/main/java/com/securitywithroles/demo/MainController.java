package com.securitywithroles.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class MainController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistrationPage(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            Model model) {

        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "registration";
        } else {
            userService.saveUser(user);
            model.addAttribute("message",
                    "User Account Successfully Created");
        }
        return "index";
    }



    @Autowired
    RoleRepository roleRepository;

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/secure")
    public String secure(){
        return "secure";
    }


    @RequestMapping("/applicant")
    public String applicant(){
        return "applicant";
    }


    @GetMapping("/getemployer")
    public String showRole(Authentication auth) {
//        model.addAttribute("role", roleRepository.findAll());

        System.out.println("username: " + auth.getName());
        return "redirect:/employer";
    }

    @RequestMapping("/employer")
    public String employer(){
        return "employer";
    }


    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

}
