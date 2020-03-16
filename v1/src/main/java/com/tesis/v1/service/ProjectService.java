package com.tesis.v1.service;

import com.tesis.v1.entity.ProjectEntity;
import com.tesis.v1.entity.UserEntity;
import com.tesis.v1.repository.ProfileRepository;
import com.tesis.v1.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectEntity> getProjectsByUser (UserEntity userEntity) {
        List<ProjectEntity> projectEntityList = new ArrayList<>();
        try {
            projectEntityList = projectRepository.getProjectEntitiesByUserEntitySet(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectEntityList;
    }

    public ProjectEntity saveProject (ProjectEntity projectEntity) {
        try {
            projectEntity = projectRepository.save(projectEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectEntity;
    }

    public ProjectEntity getProjectByName (String projectName) {
        ProjectEntity projectEntity = null;
        try {
            projectEntity = projectRepository.findByProjectName(projectName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectEntity;
    }

    public ProjectEntity getProjectById(long idProject) {
        ProjectEntity projectEntity = null;
        try {
            projectEntity = projectRepository.getOne(idProject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectEntity;
    }
}
