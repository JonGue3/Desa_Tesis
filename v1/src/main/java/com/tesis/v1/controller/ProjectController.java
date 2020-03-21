package com.tesis.v1.controller;

import com.tesis.v1.entity.*;
import com.tesis.v1.repository.ProjectRepository;
import com.tesis.v1.repository.UserRepository;
import com.tesis.v1.repository.UserStatusRepository;
import com.tesis.v1.service.*;
import com.tesis.v1.to.Response;
import com.tesis.v1.to.UpdateSelectDto;
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

    @Autowired
    private ActivityService activityService;

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
        modelAndView.addObject("userEntityList", userEntityList);
        modelAndView.addObject("transactionEntityList", transactionEntityList);
        modelAndView.addObject("menuEntityList", menuEntityList);
        modelAndView.setViewName("createProject");
        return modelAndView;
    }

    @PostMapping("/saveProject")
    public ModelAndView saveProject(@ModelAttribute("projectEntity") @Valid ProjectEntity projectEntity, @RequestParam("userEntity") UserEntity userEntity,
                                    HttpServletRequest httpServletRequest) {
        Set<ProjectEntity> projectEntitySet = new HashSet<>();
        List<ProjectEntity> projectEntityListForUser = new ArrayList<>();
        UserEntity userEntityAdmin = new UserEntity();
        ModelAndView modelAndView = new ModelAndView();
        List<UserEntity> userEntityList = new ArrayList<>();
        ProfileEntity profileEntity;
        UserStatusEntity userStatusEntity;
        List<ProjectEntity> projectEntityList = new ArrayList<>();
        List<ProjectEntity> projectEntityList1 = new ArrayList<>();
        Set<ProjectEntity> projectEntitySet2 = new HashSet<>();
        List<ProjectEntity> projectEntityListLider = new ArrayList<>();
        List<UserEntity> userEntityListAdmin = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        try {
            //Guardo Project
            projectEntity.setFinishedActivities(0);
            projectEntity = projectService.saveProject(projectEntity);
            //Guardo el project en el lider
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
            c.printStackTrace();

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
//        return loginController.getMenu(httpServletRequest);
        modelAndView.addObject("projectEntityList", projectEntityList);
        modelAndView.addObject("modalCreateProjectSuccess", true);
        modelAndView.setViewName("projects");
        return modelAndView;
    }

    @GetMapping("/editProject/{idProject}")
    public ModelAndView editProject(@PathVariable @Valid long idProject) {
        ModelAndView modelAndView = new ModelAndView();
        ProjectEntity projectEntity = null;
        List<UserEntity> userEntityList = new ArrayList<>();
        UserStatusEntity userStatusEntity;
        ProfileEntity profileEntity;
        List<UserEntity> userEntityList1 = new ArrayList<>();
        try {
            userStatusEntity = userStatusService.getUserStatusByIdUserStatus(Long.valueOf(1));
            profileEntity = profileService.getProfileById(Long.valueOf(2));
            projectEntity = projectService.getProjectById(idProject);
            userEntityList = userService.getUsersByProfileAndNotInTheProject(profileEntity, userStatusEntity, projectEntity);
            profileEntity = profileService.getProfileById(Long.valueOf(3));
            userEntityList1 = userService.getUsersByProfileAndNotInTheProject(profileEntity, userStatusEntity, projectEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.addObject("projectEntity", projectEntity);
        modelAndView.addObject("userEntityList", userEntityList);
        modelAndView.addObject("userEntityListRecursos", userEntityList1);
        modelAndView.setViewName("editProject");
        return modelAndView;
    }

    @PostMapping("/deleteUserOfProject")
    @ResponseBody
    public Response deleteUserOfProject(@RequestBody String jString) {
        Response response;
        ProjectEntity projectEntity = null;
        UserEntity userEntity;
        UpdateSelectDto updateSelectDto = null;
        ProfileEntity profileEntity;
        UserStatusEntity userStatusEntity;
        List<ActivityEntity> activityEntities = new ArrayList<>();
        List<UserEntity> userEntityList = new ArrayList<>();
        List<UserEntity> userEntityList1 = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jString);
            String projectName = jsonObject.getString("projectName");
            long idUser = Long.valueOf(jsonObject.getString("user"));
            projectEntity = projectService.getProjectByName(projectName);
            userEntity = userService.getUserById(idUser);
            activityEntities = activityService.getActivitiesByUser(userEntity);
            for (UserEntity userEntity1 : projectEntity.getUserEntitySet()) {
                if (userEntity1.getIdUser() == userEntity.getIdUser()) {
                    for (ActivityEntity activityEntity : activityEntities) {
                        if (activityEntity.getProjectEntity().getIdProject() == projectEntity.getIdProject()) {
                            activityEntity.getUserEntitySet().remove(userEntity1);
                            userEntity1.getActivityEntitySet().remove(activityEntity);
                        }
                    }
                    projectEntity.getUserEntitySet().remove(userEntity1);
                    userEntity1.getProjectEntitySet().remove(projectEntity);
                    userRepository.saveAndFlush(userEntity1);
                    /*for (ProjectEntity projectEntity1 : userEntity1.getProjectEntitySet()) {
                        if (projectEntity1.getIdProject() == projectEntity.getIdProject()) {
                            userEntity1.getProjectEntitySet().remove(projectEntity1);
                            userRepository.saveAndFlush(userEntity1);
                        }
                    }*/
                }
            }
        } catch (ConcurrentModificationException c) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            JSONObject jsonObject = new JSONObject(jString);
            String projectName = jsonObject.getString("projectName");
            userStatusEntity = userStatusService.getUserStatusByIdUserStatus(Long.valueOf(1));
            profileEntity = profileService.getProfileById(Long.valueOf(2));
            projectEntity = projectService.getProjectByName(projectName);
            userEntityList = userService.getUsersByProfileAndNotInTheProject(profileEntity, userStatusEntity, projectEntity);
            profileEntity = profileService.getProfileById(Long.valueOf(3));
            userEntityList1 = userService.getUsersByProfileAndNotInTheProject(profileEntity, userStatusEntity, projectEntity);
        } catch (Exception e) {

        }
        updateSelectDto = new UpdateSelectDto(userEntityList, userEntityList1, projectEntity.getUserEntitySet());
        response = new Response("SUCCESS", updateSelectDto);
        return response;

    }

    @PostMapping("/saveEditProject")
    public ModelAndView saveEditProject(@ModelAttribute("projectEntity") @Valid ProjectEntity projectEntity, @RequestParam("userEntity") UserEntity userEntity,
                                        @RequestParam("userEntityRecurso") UserEntity userEntityRecurso, HttpServletRequest httpServletRequest) {
        List<ProjectEntity> projectEntityList = new ArrayList<>();
        ModelAndView modelAndView  = new ModelAndView();
        Set<ProjectEntity> projectEntitySet = new HashSet<>();
        ProjectEntity projectEntityUserSet = null;
        Set<ProjectEntity> projectEntitySet1 = new HashSet<>();
        UserEntity userEntityAdmin = new UserEntity();
        List<ProjectEntity> projectEntityList1 = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
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
            if (userEntityRecurso != null) {
                //Guardo el project en el recurso
                projectEntityList = projectService.getProjectsByUser(userEntityRecurso);
                for (ProjectEntity projectEntity1 : projectEntityList) {
                    projectEntitySet1.add(projectEntity1);
                }
                projectEntitySet1.add(projectEntity);
                userEntityRecurso.setProjectEntitySet(projectEntitySet1);
                userRepository.save(userEntityRecurso);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            userEntityAdmin = userService.getUserByUserName(currentPrincipalName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            projectEntityList1 = projectService.getProjectsByUser(userEntityAdmin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.addObject("projectEntityList",projectEntityList1);
        modelAndView.addObject("modalEditProjectSuccess",true);
        modelAndView.setViewName("projects");
        //return loginController.getMenu(httpServletRequest);
        return modelAndView;
    }

    @GetMapping("/statusProjects")
    public ModelAndView statusProjects() {
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

    @GetMapping("/statusProjectsBar")
    public ModelAndView statusProjectsBar() {
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
        modelAndView.setViewName("statusProjectsBar");
        return modelAndView;
    }

    @GetMapping("/seeProjects")
    ModelAndView seeProjects(HttpServletRequest httpServletRequest) {
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
        modelAndView.setViewName("seeProjects");
        return modelAndView;
    }
}
