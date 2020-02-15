package com.tesis.v1.controller;

import com.tesis.v1.entity.*;
import com.tesis.v1.repository.ProjectRepository;
import com.tesis.v1.repository.UserRepository;
import com.tesis.v1.service.*;
import com.tesis.v1.to.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

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

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private LoginController loginController;

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
        modelAndView.setViewName("createProject");
        return modelAndView;
    }

    @PostMapping("/saveProject")
    public ModelAndView saveProject(@ModelAttribute("projectEntity") @Valid ProjectEntity projectEntity, @RequestParam("userEntity") UserEntity userEntity, HttpServletRequest httpServletRequest) {
        Set<ProjectEntity> projectEntitySet = new HashSet<>();
        List<UserEntity> userEntityList = new ArrayList<>();
        ProfileEntity profileEntity;
        UserStatusEntity userStatusEntity;
        List<ProjectEntity> projectEntityList = new ArrayList<>();
        List<ProjectEntity> projectEntityList1 = new ArrayList<>();
        Set<ProjectEntity> projectEntitySet2 = new HashSet<>();
        List<ProjectEntity> projectEntityListLider = new ArrayList<>();
        List<UserEntity> userEntityListAdmin = new ArrayList<>();
        try {
            projectEntity = projectService.saveProject(projectEntity);
            projectEntityList = projectService.getProjectsByUser(userEntity);
            for (ProjectEntity projectEntity1 : projectEntityList) {
                projectEntitySet.add(projectEntity1);
            }
            projectEntitySet.add(projectEntity);
            userEntity.setProjectEntitySet(projectEntitySet);
            userRepository.save(userEntity);
            projectEntityList1 = projectRepository.findAll();
            for (ProjectEntity projectEntity1 : projectEntityList1) {
                projectEntitySet2.add(projectEntity1);
            }
            profileEntity = profileService.getProfileById(Long.valueOf(1));
            userStatusEntity = userStatusService.getUserStatusByIdUserStatus(Long.valueOf(1));
            userEntityList = userService.getUsersByProfile(profileEntity, userStatusEntity);
/*
            for (int i = 0; i< userEntityList.size(); i++) {
                userEntityList.get(i).setProjectEntitySet(projectEntitySet2);
            }
            userRepository.saveAll(userEntityList);
*/
            /*ListIterator iterator = userEntityList.listIterator();
            while (iterator.hasNext()) {
                UserEntity userEntityAdmin = (UserEntity) iterator.next();
                userEntityAdmin.setProjectEntitySet(projectEntitySet2);
                iterator.set(userEntityAdmin);
                userRepository.saveAndFlush(userEntityAdmin);
            }*/
            for (UserEntity userEntity1 : userEntityList) {
                userEntity1.setProjectEntitySet(projectEntitySet2);
                userRepository.saveAndFlush(userEntity1);
//                userEntityListAdmin.add(userEntity1);
            }
//            userRepository.saveAll(userEntityListAdmin);
        } catch (ConcurrentModificationException c) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginController.getMenu(httpServletRequest);
    }

    @GetMapping("/editProject/{projectName}")
    public ModelAndView editProject (@PathVariable @Valid String projectName) {
        ModelAndView modelAndView = new ModelAndView();
        ProjectEntity projectEntity = null;
        List<UserEntity> userEntityList = new ArrayList<>();
        UserStatusEntity userStatusEntity;
        ProfileEntity profileEntity;
        try {
            userStatusEntity = userStatusService.getUserStatusByIdUserStatus(Long.valueOf(1));
            profileEntity = profileService.getProfileById(Long.valueOf(2));
            projectEntity = projectService.getProjectByName(projectName);
            userEntityList = userService.getUsersByProfileAndNotInTheProject(profileEntity, userStatusEntity, projectEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.addObject("projectEntity", projectEntity);
        modelAndView.addObject("userEntityList", userEntityList);
        modelAndView.setViewName("editProject");
        return modelAndView;
    }

    @PostMapping("/deleteLider")
    @ResponseBody
    public Response deleteLider (@RequestBody String jString) {
        Response response;
        ProjectEntity projectEntity = null;
        UserEntity userEntity;
        List<UserEntity> userEntityList = new ArrayList<>();
        UserStatusEntity userStatusEntity;
        ProfileEntity profileEntity;
        try {
            userStatusEntity = userStatusService.getUserStatusByIdUserStatus(Long.valueOf(1));
            profileEntity = profileService.getProfileById(Long.valueOf(2));
            userEntityList = userService.getUsersByProfile(profileEntity, userStatusEntity);
            JSONObject jsonObject = new JSONObject(jString);
            String projectName = jsonObject.getString("projectName");
            long idLider = Long.valueOf(jsonObject.getString("lider"));
            projectEntity = projectService.getProjectByName(projectName);
            userEntity = userService.getUserById(idLider);
            for (UserEntity userEntity1 : projectEntity.getUserEntitySet()) {
                if (userEntity1.getIdUser() == userEntity.getIdUser()) {
                    projectEntity.getUserEntitySet().remove(userEntity1);
                    for (ProjectEntity projectEntity1 : userEntity1.getProjectEntitySet()) {
                        if (projectEntity1.getIdProject() == projectEntity.getIdProject()) {
                            userEntity1.getProjectEntitySet().remove(projectEntity1);
                            userRepository.saveAndFlush(userEntity1);
                        }
                    }
                }
            }
        } catch (ConcurrentModificationException c) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        response = new Response("SUCCESS", projectEntity.getUserEntitySet());
        return response;

    }

    @PostMapping("/saveEditProject")
    public ModelAndView saveEditProject(@ModelAttribute("projectEntity") @Valid ProjectEntity projectEntity, @RequestParam("userEntity") UserEntity userEntity, HttpServletRequest httpServletRequest) {
        List<ProjectEntity> projectEntityList = new ArrayList<>();
        Set<ProjectEntity> projectEntitySet = new HashSet<>();
        ProjectEntity projectEntityUserSet = null;
        try {
            projectEntityUserSet = projectService.getProjectByName(projectEntity.getProjectName());
            projectEntity.setUserEntitySet(projectEntityUserSet.getUserEntitySet());
            projectEntity = projectService.saveProject(projectEntity);
            if (userEntity != null) {
                projectEntityList = projectService.getProjectsByUser(userEntity);
                for (ProjectEntity projectEntity1 : projectEntityList) {
                    projectEntitySet.add(projectEntity1);
                }
                projectEntitySet.add(projectEntity);
                userEntity.setProjectEntitySet(projectEntitySet);
                userRepository.save(userEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginController.getMenu(httpServletRequest);
    }

    @GetMapping("/statusProjects")
    public ModelAndView statusProjects () {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserEntity userEntity = null;
        try {
            userEntity = userService.getUserByUserName(currentPrincipalName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.addObject("projectEntityList", userEntity.getProjectEntitySet());
        modelAndView.setViewName("statusProjects");
        return modelAndView;
    }
}
