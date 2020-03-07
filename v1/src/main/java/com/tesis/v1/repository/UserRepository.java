package com.tesis.v1.repository;

import com.tesis.v1.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername (String username);

    List<UserEntity> findByProfileEntityAndUserStatusEntity(ProfileEntity profileEntity, UserStatusEntity userStatusEntity);
    List<UserEntity> findByProfileEntity_IdProfileOrProfileEntity_IdProfile(long idLeader,long idConsultant);
    List<UserEntity> findByProfileEntityAndUserStatusEntityAndProjectEntitySetNotContaining(ProfileEntity profileEntity, UserStatusEntity userStatusEntity, ProjectEntity projectEntity);

    UserEntity findByIdUser(long idUser);

    List<UserEntity> findByProfileEntityAndUserStatusEntityAndProjectEntitySet(ProfileEntity profileEntity, UserStatusEntity userStatusEntity, ProjectEntity projectEntity);
    List<UserEntity> findByProfileEntityAndUserStatusEntityAndProjectEntitySetAndActivityEntitySetNotContaining(ProfileEntity profileEntity, UserStatusEntity userStatusEntity, ProjectEntity projectEntity, ActivityEntity activityEntity);
}
