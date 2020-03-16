package com.tesis.v1.controller;

import com.tesis.v1.entity.*;
import com.tesis.v1.service.*;
import com.tesis.v1.to.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller

public class LoginController {
    @Autowired
    private GenderService genderService;

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/login")
    ModelAndView modelAndView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    /*@GetMapping("/projects")
    ModelAndView projects() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("projects");
        return modelAndView;
    }*/

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
        modelAndView.addObject("genderEntityList", genderEntityList);
        UserEntity userEntity = new UserEntity();
        modelAndView.addObject("userEntity", userEntity);

        modelAndView.setViewName("registerUser");
        return modelAndView;
    }


    @PostMapping("/saveUser")
    public ModelAndView saveUser(@ModelAttribute("userEntity") @Valid UserEntity userEntity, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        UserEntity userEntityConsult = new UserEntity();
        //consultar si el nombre de usuario existe
        try {
            userEntityConsult = userService.getUserByUserName(userEntity.getUsername());
            if (userEntityConsult != null) {
                List<GenderEntity> genderEntityList = new ArrayList<>();
                try {
                    genderEntityList = genderService.getAllGender();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                modelAndView.addObject("genderEntityList", genderEntityList);
                UserEntity userEntity1 = new UserEntity();
                modelAndView.addObject("userEntity", userEntity1);
                modelAndView.setViewName("registerUser");
                modelAndView.addObject("modalExistUser", true);
                return modelAndView;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            userService.saveUser(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.setViewName("registerUser");
            return modelAndView;
        }
        modelAndView.addObject("modalRegisterSucces",true);
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/projects")
    ModelAndView getMenu(HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView();
        List<MenuEntity> menuEntityList = new ArrayList<>();

        List<TransactionEntity> transactionEntityList = new ArrayList<>();
        List<ProjectEntity> projectEntityList = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserEntity userEntity = null;
        try {
            userEntity = userService.getUserByUserName(currentPrincipalName);
            projectEntityList = projectService.getProjectsByUser(userEntity);
            transactionEntityList = transactionService.getTransactionByIdProfile(userEntity);
            menuEntityList = menuService.getMenuByIdProfile(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        httpServletRequest.getSession().setAttribute("menuEntityList", menuEntityList);
        httpServletRequest.getSession().setAttribute("transactionEntityList", transactionEntityList);
        httpServletRequest.getSession().setAttribute("userEntity", userEntity);
        modelAndView.addObject("projectEntityList", projectEntityList);
        modelAndView.addObject("userEntity", userEntity);
        modelAndView.addObject("transactionEntityList", transactionEntityList);
        modelAndView.addObject("menuEntityList", menuEntityList);
        if (userEntity.getProfileEntity() == null || userEntity.getProfileEntity().getIdProfile() != 3) {
            modelAndView.setViewName("projects");
        } else {
            modelAndView.setViewName("seeProjects");
        }
        return modelAndView;
    }
}
