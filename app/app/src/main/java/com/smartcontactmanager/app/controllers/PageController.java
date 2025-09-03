package com.smartcontactmanager.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smartcontactmanager.app.entities.User;
import com.smartcontactmanager.app.forms.UserForm;
import com.smartcontactmanager.app.services.UserService;


@Controller

public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home page handler");
        // sending data to view
        model.addAttribute("name", "Substring Technologies");
        model.addAttribute("youtubeChannel", "Learn Code With Durgesh");
        model.addAttribute("githubRepo", "https://github.com/learncodewithdurgesh/");
        return "home";
    }

    @RequestMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("isLogin", true);
        System.out.println("About page loading");
        return "about";
    }

 // services

    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("services page loading");
        return "services";
    }

    // contact page

    @GetMapping("/contact")
    public String contact() {
        return new String("contact");
    }

    // this is showing login page
    @GetMapping("/login")
    public String login() {
        return new String("login");
    }

    @RequestMapping("/register")
    public String register(Model model) {

        UserForm userForm = new UserForm();

        model.addAttribute("userForm",userForm);
        return new String("register");
    }

    @RequestMapping(value = "do-register", method = RequestMethod.POST)
    public String processRegister(@ModelAttribute UserForm userForm){

        System.out.println(userForm);

        User user = User.builder()
                        .name(userForm.getName())
                        .email(userForm.getEmail())
                        .password(userForm.getPassword())
                        .about(userForm.getAbout())
                        .phoneNumber(userForm.getPhoneNumber())
                        .profilePic("https://a.thumbs.redditmedia.com/lKF4JTswaY62HW34-DuXm5r89vk_JosBcI8C9YM2Uf4.jpg")
                        .build(); 

        User savedUser = userService.saveUser(user);

        System.out.println("User saved... : " + savedUser);         
                          
        return "redirect:/register";

    }
    

}
