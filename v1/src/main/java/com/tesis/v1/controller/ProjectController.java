package com.tesis.v1.controller;

import com.tesis.v1.entity.*;
import com.tesis.v1.repository.UserRepository;
import com.tesis.v1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ProjectController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserStatusService userStatusService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/createProject")
    ModelAndView createProject() {
        ModelAndView modelAndView = new ModelAndView();
        List<UserEntity> userEntityList = new ArrayList<>();
        ProfileEntity profileEntity;
        UserStatusEntity userStatusEntity;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserEntity userEntity = null;
        List<MenuEntity> menuEntityList = new ArrayList<>();
        List<TransactionEntity> transactionEntityList = new ArrayList<>();
        try {
            userEntity = userService.getUserByUserName(currentPrincipalName);
            profileEntity = profileService.getProfileById(Long.valueOf(2));
            userStatusEntity = userStatusService.getUserStatusByIdUserStatus(Long.valueOf(1));
            userEntityList = userService.getUsersByProfile(profileEntity, userStatusEntity);
            transactionEntityList = transactionService.getTransactionByIdProfile(userEntity);
            menuEntityList = menuService.getMenuByIdProfile(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.addObject("userEntity", userEntity);
        modelAndView.addObject("userEntityList",userEntityList);
        modelAndView.addObject("transactionEntityList", transactionEntityList);
        modelAndView.addObject("menuEntityList", menuEntityList);
        modelAndView.setViewName("crearProyecto");
        return modelAndView;
    }

    @PostMapping("/saveProject")
    public ModelAndView saveProject(@ModelAttribute("projectEntity") @Valid ProjectEntity projectEntity, @RequestParam("userEntity") UserEntity userEntity, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        Set<ProjectEntity> projectEntitySet = new HashSet<>();
        List<UserEntity> userEntityList = new ArrayList<>();
        ProfileEntity profileEntity;
        UserStatusEntity userStatusEntity;
        List<ProjectEntity> projectEntityList = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserEntity userEntityAuth = null;
        try {
            projectEntity = projectService.saveProject(projectEntity);
            userEntityAuth = userService.getUserByUserName(currentPrincipalName);
            projectEntityList = projectService.getProjectsByUser(userEntityAuth);
            for (ProjectEntity projectEntity1 : projectEntityList) {
                projectEntitySet.add(projectEntity1);
            }
            projectEntitySet.add(projectEntity);
            profileEntity = profileService.getProfileById(Long.valueOf(1));
            userStatusEntity = userStatusService.getUserStatusByIdUserStatus(Long.valueOf(1));
            userEntityList = userService.getUsersByProfile(profileEntity, userStatusEntity);
            for (UserEntity userEntity1 : userEntityList) {
                userEntity1.setProjectEntitySet(projectEntitySet);
                userRepository.save(userEntity1);
            }
            userEntity.setProjectEntitySet(projectEntitySet);
            userRepository.save(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/editProject/{projectName}")
    public ModelAndView editProject (@PathVariable @Valid String projectName) {
        ModelAndView modelAndView = new ModelAndView();
        ProjectEntity projectEntity = null;
        try {
            projectEntity = projectService.getProjectByName(projectName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.addObject("projectEntity", projectEntity);
        modelAndView.setViewName("editProject");
        return modelAndView;
    }
}
