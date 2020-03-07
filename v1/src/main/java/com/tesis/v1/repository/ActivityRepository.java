package com.tesis.v1.repository;

import com.tesis.v1.entity.ActivityEntity;
import com.tesis.v1.entity.ProjectEntity;
import com.tesis.v1.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityEntity, Long> {

    List<ActivityEntity> getActivityEntitiesByUserEntitySet (UserEntity userEntity);

    List<ActivityEntity> getActivityEntitiesByUserEntitySetAndProjectEntity(UserEntity userEntity, ProjectEntity projectEntity);

    List<ActivityEntity> getActivityEntitiesByProjectEntity(ProjectEntity projectEntity);

    ActivityEntity findByActivityName(String activityName);


}
