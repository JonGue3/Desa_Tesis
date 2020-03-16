package com.tesis.v1.controller;

import com.tesis.v1.entity.*;
import com.tesis.v1.repository.ActivityPointsRepository;
import com.tesis.v1.repository.ActivityStatusRepository;
import com.tesis.v1.repository.UserRepository;
import com.tesis.v1.service.*;
import com.tesis.v1.to.Response;
import com.tesis.v1.to.UpdateSelectDto;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserStatusService userStatusService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivityStatusRepository activityStatusRepository;

    @Autowired
    private ActivityPointsRepository activityPointsRepository;

    @Autowired
    private LoginController loginController;

    @Autowired
    private ActivityStatusService activityStatusService;

    @Autowired
    private ActivityPointsService activityPointsService;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/activities")
    public ModelAndView getActivities(HttpServletRequest httpServletRequest, @ModelAttribute("projectEntity") @Valid ProjectEntity projectEntity) {
        ModelAndView modelAndView = new ModelAndView();
        List<ActivityEntity> activityEntityList = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserEntity userEntity = null;
        try {
            userEntity = userService.getUserByUserName(currentPrincipalName);
            if (userEntity.getProfileEntity().getIdProfile() == 2) {
                activityEntityList = activityService.getActivityEntitiesByProjectEntity(projectEntity);
            } else {
                activityEntityList = activityService.getActivitiesByUserAndProject(userEntity, projectEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.addObject("activityEntityList", activityEntityList);
        modelAndView.setViewName("activities");
        return modelAndView;
    }

    @PostMapping("/createActivity")
    public ModelAndView createActivity(@ModelAttribute("projectEntity") @Valid ProjectEntity projectEntity) {
        ModelAndView modelAndView = new ModelAndView();
        List<UserEntity> userEntityList = new ArrayList<>();
        ProfileEntity profileEntity;
        UserStatusEntity userStatusEntity;
        List<ActivityStatusEntity> activityStatusEntities = new ArrayList<>();
        List<ActivityPointsEntity> activityPointsEntities = new ArrayList<>();
        try {
            profileEntity = profileService.getProfileById(Long.valueOf(3));
            userStatusEntity = userStatusService.getUserStatusByIdUserStatus(Long.valueOf(1));
            activityStatusEntities = activityStatusRepository.findAll();
            activityPointsEntities = activityPointsRepository.findAll();
            userEntityList = userService.getUsersByProfileAndProject(profileEntity, userStatusEntity, projectEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.addObject("projectEntity", projectEntity);
        modelAndView.addObject("userEntityList", userEntityList);
        modelAndView.addObject("activityStatusEntities", activityStatusEntities);
        modelAndView.addObject("activityPointsEntities", activityPointsEntities);
        modelAndView.setViewName("createActivity");
        return modelAndView;
    }

    @PostMapping("/saveActivity")
    public ModelAndView saveActivity(@ModelAttribute("activityEntity") @Valid ActivityEntity activityEntity, @RequestParam("projectEntity") ProjectEntity projectEntity,
                                     @RequestParam("userEntity") UserEntity userEntity, HttpServletRequest httpServletRequest) {
        List<ActivityEntity> activityEntities = new ArrayList<>();
        ModelAndView modelAndView = new ModelAndView();
        Set<ActivityEntity> activityEntitySet = new HashSet<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserEntity userEntityAdmin = new UserEntity();
        List<ProjectEntity> projectEntityList = new ArrayList<>();

        try {
            activityEntity.setProjectEntity(projectEntity);
            activityEntity = activityService.saveActivity(activityEntity);
            activityEntities = activityService.getActivitiesByUser(userEntity);
            for (ActivityEntity activityEntity1 : activityEntities) {
                activityEntitySet.add(activityEntity1);
            }
            activityEntitySet.add(activityEntity);
            userEntity.setActivityEntitySet(activityEntitySet);
            userRepository.save(userEntity);
            /*for (UserEntity userEntity : userEntities) {
                activityEntities = activityService.getActivitiesByUser(userEntity);
                for (ActivityEntity activityEntity1 : activityEntities) {
                    activityEntitySet.add(activityEntity1);
                }
                activityEntitySet.add(activityEntity);
                userEntity.setActivityEntitySet(activityEntitySet);
                userRepository.save(userEntity);
            }*/
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

        modelAndView.addObject("modalCreateActivitySuccess", true);
        modelAndView.addObject("projectEntityList", projectEntityList);
        if (userEntityAdmin.getProfileEntity().getIdProfile() != 3) {
            modelAndView.setViewName("projects");
        } else {
            modelAndView.setViewName("seeProjects");
        }
//        return loginController.getMenu(httpServletRequest);
        return modelAndView;
    }

    @GetMapping("/editActivity/{idActivity}")
    public ModelAndView editActivity(@PathVariable @Valid long idActivity) {
        ModelAndView modelAndView = new ModelAndView();
        List<UserEntity> userEntityList = new ArrayList<>();
        ProfileEntity profileEntity;
        UserStatusEntity userStatusEntity;
        ActivityEntity activityEntity = null;
        List<ActivityStatusEntity> activityStatusEntities = new ArrayList<>();
        List<ActivityPointsEntity> activityPointsEntities = new ArrayList<>();
        try {
            activityEntity = activityService.getActivityById(idActivity);
            profileEntity = profileService.getProfileById(Long.valueOf(3));
            activityStatusEntities = activityStatusService.findAll();
            activityPointsEntities = activityPointsService.findAll();
            userStatusEntity = userStatusService.getUserStatusByIdUserStatus(Long.valueOf(1));
            userEntityList = userService.getUsersByProfileAndProjectAndNotInTheActivity(profileEntity, userStatusEntity, activityEntity.getProjectEntity(), activityEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.addObject("projectEntity", activityEntity.getProjectEntity());
        modelAndView.addObject("activityStatusEntities", activityStatusEntities);
        modelAndView.addObject("activityPointsEntities", activityPointsEntities);
        modelAndView.addObject("activityEntity", activityEntity);
        modelAndView.addObject("userEntityList", userEntityList);
        modelAndView.setViewName("editActivity");
        return modelAndView;
    }

    @PostMapping("/saveEditActivity")
    public ModelAndView saveEditActivity(@ModelAttribute("activityEntity") @Valid ActivityEntity activityEntity, @RequestParam("userEntity") UserEntity userEntity, HttpServletRequest httpServletRequest) {
        List<ActivityEntity> activityEntities = new ArrayList<>();
        ModelAndView modelAndView = new ModelAndView();
        ActivityEntity activityEntity1 = null;
        Set<ActivityEntity> activityEntitySet = new HashSet<>();
        ProjectEntity projectEntity = null;
        UserEntity userEntityAdmin = new UserEntity();
        List<ProjectEntity> projectEntityList = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        try {
            activityEntity1 = activityService.getActivityById(activityEntity.getIdActivity());
            activityEntity.setUserEntitySet(activityEntity1.getUserEntitySet());
            activityEntity = activityService.saveActivity(activityEntity);
            if (userEntity != null) {
                activityEntities = activityService.getActivitiesByUser(userEntity);
                for (ActivityEntity activityEntity2 : activityEntities) {
                    activityEntitySet.add(activityEntity2);
                }
                activityEntitySet.add(activityEntity);
                userEntity.setActivityEntitySet(activityEntitySet);
                userRepository.save(userEntity);
            }
            if (activityEntity.getActivityStatusEntity().getIdActivityStatus() == 3) {
                projectEntity = activityEntity.getProjectEntity();
                projectEntity.setFinishedActivities(projectEntity.getFinishedActivities() + activityEntity.getActivityPointsEntity().getPoints());
                projectService.saveProject(projectEntity);
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
            projectEntityList = projectService.getProjectsByUser(userEntityAdmin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.addObject("projectEntityList",projectEntityList);
        modelAndView.addObject("modalEditActivitySuccess",true);
        if (userEntityAdmin.getProfileEntity().getIdProfile() != 3) {
            modelAndView.setViewName("projects");
        } else {
            modelAndView.setViewName("seeProjects");
        }
    //    return loginController.getMenu(httpServletRequest);
        return  modelAndView;
    }

    @PostMapping("/deleteUserOfActivity")
    @ResponseBody
    public Response deleteUserOfActivity(@RequestBody String jString) {
        Response response;
        UpdateSelectDto updateSelectDto = null;
        ActivityEntity activityEntity = null;
        List<UserEntity> userEntityList = new ArrayList<>();
        UserEntity userEntity;
        ProfileEntity profileEntity;
        UserStatusEntity userStatusEntity;
        try {
            JSONObject jsonObject = new JSONObject(jString);
            long idActivity = jsonObject.getLong("idActivity");
            long idUser = Long.valueOf(jsonObject.getString("user"));
            activityEntity = activityService.getActivityById(idActivity);
            userEntity = userService.getUserById(idUser);
            activityEntity.getUserEntitySet().remove(userEntity);
            userEntity.getActivityEntitySet().remove(activityEntity);
            userRepository.saveAndFlush(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            userStatusEntity = userStatusService.getUserStatusByIdUserStatus(Long.valueOf(1));
            profileEntity = profileService.getProfileById(Long.valueOf(3));
            userEntityList = userService.getUsersByProfileAndProjectAndNotInTheActivity(profileEntity, userStatusEntity, activityEntity.getProjectEntity(), activityEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateSelectDto = new UpdateSelectDto(activityEntity.getUserEntitySet(), userEntityList);
        response = new Response("SUCCESS", updateSelectDto);
        return response;
    }

    @GetMapping("/createActivityModel")
    public ModelAndView createActivityModel(HttpServletRequest httpServletRequest) {
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
        modelAndView.setViewName("createActivityModel");
        return modelAndView;
    }

    @GetMapping("/editActivityModel")
    public ModelAndView editActivityModel(HttpServletRequest httpServletRequest) {
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
        modelAndView.setViewName("editActivityModel");
        return modelAndView;
    }
}
