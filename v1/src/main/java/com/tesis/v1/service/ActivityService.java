package com.tesis.v1.service;

import com.tesis.v1.entity.ActivityEntity;
import com.tesis.v1.entity.ProjectEntity;
import com.tesis.v1.entity.UserEntity;
import com.tesis.v1.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public List<ActivityEntity> getActivitiesByUser(UserEntity userEntity) {
        List<ActivityEntity> activityEntities = new ArrayList<>();
        try {
            activityEntities = activityRepository.getActivityEntitiesByUserEntitySet(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activityEntities;
    }

    public ActivityEntity saveActivity (ActivityEntity activityEntity) {
        try {
            activityEntity = activityRepository.save(activityEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activityEntity;
    }

    public ActivityEntity getActivityById (long idActivity) {
        ActivityEntity activityEntity = null;
        try {
            activityEntity = activityRepository.getOne(idActivity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activityEntity;
    }

    public List<ActivityEntity> getActivitiesByUserAndProject(UserEntity userEntity, ProjectEntity projectEntity) {
        List<ActivityEntity> activityEntities = new ArrayList<>();
        try {
            activityEntities = activityRepository.getActivityEntitiesByUserEntitySetAndProjectEntity(userEntity, projectEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activityEntities;
    }

    public List<ActivityEntity> getActivityEntitiesByProjectEntity(ProjectEntity projectEntity) {
        List<ActivityEntity> activityEntities = new ArrayList<>();
        try {
            activityEntities = activityRepository.getActivityEntitiesByProjectEntity(projectEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activityEntities;
    }

    public ActivityEntity getActivityEntitiesByProjectEntity(String activityName) {
        ActivityEntity activityEntity = null;
        try {
            activityEntity = activityRepository.findByActivityName(activityName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activityEntity;
    }
}
