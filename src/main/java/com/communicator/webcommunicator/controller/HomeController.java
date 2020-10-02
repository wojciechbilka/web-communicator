package com.communicator.webcommunicator.controller;

import com.communicator.webcommunicator.model.User;
import com.communicator.webcommunicator.model.UserDTO;
import com.communicator.webcommunicator.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;


    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        return "/admin/admin_page";
    }

    @GetMapping("/user")
    public String user(Model model) {
        return "/user/user_page";
    }

    //TODO Autologin after successful registration
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "registration";
    }

    //TODO Add constraints and error messages
    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute(value = "userDTO") UserDTO userDTO, BindingResult result, Model model) {

        User user = userDTO.createUser();
        userService.save(user);
        return "index";
    }

    // Login form
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    /*@RequestMapping("/logout")
    public String performLogout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
       *//* if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }*//*
        return "redirect:/login";
    }*/


}
