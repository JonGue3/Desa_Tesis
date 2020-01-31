package com.tesis.v1.controller;

import com.tesis.v1.entity.MenuEntity;
import com.tesis.v1.entity.UserEntity;
import com.tesis.v1.service.MenuService;
import com.tesis.v1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    MenuService menuService;

    @GetMapping("/getUserList")
    public ModelAndView getUserList() {
        ModelAndView modelAndView = new ModelAndView();
        List<UserEntity> userEntityList = new ArrayList<>();
        List<MenuEntity> menuEntityList = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserEntity userEntity = null;

        try {
            userEntity = userService.getUserByUserName(currentPrincipalName);
        } catch (Exception e) {

        }
        try {
            userEntityList = userService.getUsersByProfile(null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            menuEntityList = menuService.getMenuByIdProfile(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.addObject("userEntity", userEntity);
        modelAndView.addObject("menuEntityList", menuEntityList);
        modelAndView.addObject("userEntityList", userEntityList);
        modelAndView.setViewName("viewUser");
        return modelAndView;
    }


}
