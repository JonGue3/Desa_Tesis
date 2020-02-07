package com.tesis.v1.repository;

import com.tesis.v1.entity.ProfileEntity;
import com.tesis.v1.entity.ProjectEntity;
import com.tesis.v1.entity.UserEntity;
import com.tesis.v1.entity.UserStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername (String username);

    List<UserEntity> findByProfileEntityAndUserStatusEntity(ProfileEntity profileEntity, UserStatusEntity userStatusEntity);

    List<UserEntity> findByProfileEntityAndUserStatusEntityAndProjectEntitySetNotContaining(ProfileEntity profileEntity, UserStatusEntity userStatusEntity, ProjectEntity projectEntity);

    UserEntity findByIdUser(long idUser);
}
