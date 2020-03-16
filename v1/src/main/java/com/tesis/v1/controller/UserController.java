package com.tesis.v1.controller;

import com.tesis.v1.entity.*;
import com.tesis.v1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    MenuService menuService;
    @Autowired
    ProfileService profileService;
    @Autowired
    UserStatusService userStatusService;
    @Autowired
    private LoginController loginController;
    @Autowired
    private GenderService genderService;
    @Autowired
    private ProjectService projectService;

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


    @GetMapping("/getUserEditList")
    public ModelAndView getUserEditList() {
        ModelAndView modelAndView = new ModelAndView();
        List<UserEntity> userEntityList = new ArrayList<>();
        long idLeader = 2;
        long idConsultant = 3;
        try {
            userEntityList = userService.getUsersForEdit(idLeader, idConsultant);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.addObject("userEntityList", userEntityList);
        modelAndView.setViewName("viewEditUser");
        return modelAndView;
    }

    @GetMapping("/editUser/{username}")
    public ModelAndView editUser(@PathVariable @Valid String username) {
        ModelAndView modelAndView = new ModelAndView();
        UserEntity userEntity = null;
        List<ProfileEntity> profileEntityList = new ArrayList<>();
        List<UserStatusEntity> userStatusEntityList = new ArrayList<>();
        List<GenderEntity> genderEntityList = new ArrayList<>();
        String userStatusSelected;
        String profileSelected;
        String genderSelected;
        try {
            userEntity = userService.getUserByUserName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            profileEntityList = profileService.getAllProfiles();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            userStatusEntityList = userStatusService.getAllUserStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            genderEntityList = genderService.getAllGender();
        } catch (Exception e) {
            e.printStackTrace();
        }
        genderSelected = userEntity.getGenderEntity().getDescription();
        userStatusSelected = userEntity.getUserStatusEntity().getDescription();
        profileSelected = userEntity.getProfileEntity().getDescription();
        modelAndView.addObject("genderSelected", genderSelected);
        modelAndView.addObject("profileSelected", profileSelected);
        modelAndView.addObject("userStatusSelected", userStatusSelected);
        modelAndView.addObject("userEntity", userEntity);
        modelAndView.addObject("profileEntityList", profileEntityList);
        modelAndView.addObject("userStatusEntityList", userStatusEntityList);
        modelAndView.addObject("genderEntityList", genderEntityList);

        modelAndView.setViewName("editUser");
        return modelAndView;
    }

    @GetMapping("/assignRole/{username}")
    public ModelAndView assignRole(@PathVariable @Valid String username) {
        ModelAndView modelAndView = new ModelAndView();
        UserEntity userEntity = null;
        List<ProfileEntity> profileEntityList = new ArrayList<>();
        try {
            userEntity = userService.getUserByUserName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            profileEntityList = profileService.getAllProfiles();
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.addObject("userEntity", userEntity);
        modelAndView.addObject("profileEntityList", profileEntityList);
        modelAndView.setViewName("assignRole");
        return modelAndView;
    }

    @PostMapping("/saveAssingRole")
    public ModelAndView saveAssingRole(@ModelAttribute("userEntity") @Valid UserEntity userEntityFromForm, HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView();
        UserEntity userEntity = null;
        UserEntity userEntityAdmin = new UserEntity();
        userEntity = userService.getUserByUserName(userEntityFromForm.getUsername());
        List<ProjectEntity> projectEntityList = new ArrayList<>();
        userEntity.setProfileEntity(userEntityFromForm.getProfileEntity());
        UserStatusEntity userStatusEntity = new UserStatusEntity();
        userStatusEntity = userStatusService.getUserStatusByIdUserStatus(1);
        userEntity.setUserStatusEntity(userStatusEntity);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        try {
            userService.saveUserAssignedRole(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            userEntityAdmin = userService.getUserByUserName(currentPrincipalName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            projectEntityList = projectService.getProjectsByUser(userEntityAdmin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.addObject("modalSaveAssignRole", true);
        modelAndView.addObject("projectEntityList", projectEntityList);
//        return loginController.getMenu(httpServletRequest);
        modelAndView.setViewName("projects");
        return modelAndView;
    }

    @PostMapping("/saveEditUser")
    public ModelAndView saveEditUser(@ModelAttribute("userEntity") @Valid UserEntity userEntityFromForm, HttpServletRequest httpServletRequest) {
        ModelAndView modelAndView = new ModelAndView();
        UserEntity userEntity = null;
        UserEntity userEntityAdmin = new UserEntity();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        List<ProjectEntity> projectEntityList = new ArrayList<>();
        UserStatusEntity userStatusEntity = new UserStatusEntity();
        userEntity = userService.getUserByUserName(userEntityFromForm.getUsername());
        userEntity.setFullName(userEntityFromForm.getFullName());
        userEntity.setGenderEntity(userEntityFromForm.getGenderEntity());
        userEntity.setBirthday(userEntityFromForm.getBirthday());
        userEntity.setEmail(userEntityFromForm.getEmail());
        userEntity.setProfileEntity(userEntityFromForm.getProfileEntity());
        userEntity.setUserStatusEntity(userEntityFromForm.getUserStatusEntity());
        try {
            userService.saveEditUser(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            userEntityAdmin = userService.getUserByUserName(currentPrincipalName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            projectEntityList = projectService.getProjectsByUser(userEntityAdmin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.addObject("projectEntityList", projectEntityList);
        modelAndView.addObject("modalSuccessEditUser", true);
        modelAndView.setViewName("projects");
//        return loginController.getMenu(httpServletRequest);
        return modelAndView;
    }

//    @PostMapping("/recoverPassword")
//    public  ModelAndView recoverPassword(){
//
//        String token = UUID.randomUUID().toString();
//        try {
//            propertyService.getIpUrl();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String ipUrl = prop.getProperty ("spring.server.ip");
//        String resetURL = "";
//    }
}
