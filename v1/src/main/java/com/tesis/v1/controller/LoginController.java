package com.tesis.v1.controller;

import com.tesis.v1.entity.GenderEntity;
import com.tesis.v1.entity.UserEntity;
import com.tesis.v1.service.GenderService;
import com.tesis.v1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller

public class LoginController {
    @Autowired
    GenderService genderService;

    @Autowired
    UserService userService;

    @GetMapping("/login")
    ModelAndView modelAndView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/dashboard_2")
    ModelAndView dashboard_2() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dashboard_2");
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
        UserEntity userEntity = new UserEntity();
        modelAndView.addObject("userEntity", userEntity);
        modelAndView.setViewName("registerUser");
        return modelAndView;
    }

    @PostMapping("/saveUser")
    public ModelAndView saveUser(@ModelAttribute("userEntity") @Valid UserEntity userEntity, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            userEntity = userService.saveUser(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.setViewName("registerUser");
            return modelAndView;
        }
        modelAndView.setViewName("login");
        return modelAndView;
    }



}
