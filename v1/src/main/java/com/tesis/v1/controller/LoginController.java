package com.tesis.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class LoginController {

    @GetMapping("/login")
    ModelAndView modelAndView() {
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/forgotPassword")
    ModelAndView forgotPassword() {
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("forgotPassword");
        return modelAndView;
    }

    @GetMapping("/createNewUser")
    ModelAndView createNewUser() {
        ModelAndView modelAndView= new ModelAndView();
        modelAndView.setViewName("createNewUser");
        return modelAndView;
    }

}
