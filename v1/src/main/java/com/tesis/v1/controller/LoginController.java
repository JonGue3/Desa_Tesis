package com.tesis.v1.controller;

import com.tesis.v1.entity.GenderEntity;
import com.tesis.v1.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller

public class LoginController {
    @Autowired
    GenderService genderService;


    @GetMapping("/login")
    ModelAndView modelAndView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/forgotPassword")
    ModelAndView forgotPassword() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forgotPassword");
        return modelAndView;
    }

    @GetMapping("/registerUser")
    ModelAndView registerUser() {
        ModelAndView modelAndView = new ModelAndView();
        List<GenderEntity> genderEntityList = new ArrayList<>();
        try {
            genderEntityList = genderService.getAllGender();
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.addObject("genderEntityList",genderEntityList);
        modelAndView.setViewName("registerUser");
        return modelAndView;
    }

}
