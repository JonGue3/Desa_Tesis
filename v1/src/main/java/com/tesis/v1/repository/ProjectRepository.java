package com.tesis.v1.repository;

import com.tesis.v1.entity.ProjectEntity;
import com.tesis.v1.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    List<ProjectEntity> getProjectEntitiesByUserEntitySet(UserEntity userEntity);

    ProjectEntity findByProjectName(String projectName);

}
