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
}
