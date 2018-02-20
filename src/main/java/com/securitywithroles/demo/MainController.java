package com.securitywithroles.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;

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
    public class LoginServlet extends HttpServlet {

        protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String roleType = request.getParameter("roleType");

            System.out.println("role type: " + roleType);
        }

    }


//    @RequestMapping(value = "/register/{id}")
//    public String roleType (Model model, @PathVariable("id")) String assignedRoleType){
//
//    }





    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistrationPage(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            Model model) {
//
//        model.addAttribute("roleType", userRepository.findOne(user.getId()));
//        User roleType = userRepository.findOne(id)
//        userRepository.save(roleType);
//        System.out.println(user.getRoleType());
//

// add if statement to set value of options "if roleType (from dropdown) equals APPLICANT, set to applicant, else.."
        model.addAttribute("user", user);

        if (result.hasErrors()) {
            return "registration";
        } else {
            user.setRoles(Arrays.asList());
            userService.saveUser(user);
            model.addAttribute("message",
                    "User Account Successfully Created");
            System.out.println("New user created.");
            System.out.println(user.getRoleType());
        }
        return "index";
//
//
//        user.setRoleType(roleType;
//        userRepository.save(roleType);

//        model.addAttribute("roleType",roleRepository);
//        String roleType=roleType.getRoleType;

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
