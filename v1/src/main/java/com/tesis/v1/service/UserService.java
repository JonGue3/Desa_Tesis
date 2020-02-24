package com.tesis.v1.service;

import com.tesis.v1.entity.ProfileEntity;
import com.tesis.v1.entity.ProjectEntity;
import com.tesis.v1.entity.UserEntity;
import com.tesis.v1.entity.UserStatusEntity;
import com.tesis.v1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Metodo que obtiene usuario mediante el nombre de usuario
    public UserEntity getUserByUserName(String username){
        UserEntity userEntity = new UserEntity();
      try{
        userEntity= userRepository.findByUsername(username.toLowerCase());
      }catch (Exception e){
        e.printStackTrace();
      }
    return  userEntity;
    }

    public UserEntity saveUser (UserEntity userEntity) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        try {
            userEntity.setUsername(userEntity.getUsername().toLowerCase());
           userEntity = userRepository.save(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userEntity;
    }

    public UserEntity saveUserAssignedRole (UserEntity userEntity) {
        try {
            userEntity = userRepository.save(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userEntity;
    }
    public  void saveEditUser(UserEntity userEntity){
        try {
          userRepository.save(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<UserEntity> getUsersByProfile (ProfileEntity profileEntity, UserStatusEntity userStatusEntity) {
        List<UserEntity> userEntityList = new ArrayList<>();
        try {
            userEntityList = userRepository.findByProfileEntityAndUserStatusEntity(profileEntity, userStatusEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userEntityList;
    }

    public UserEntity getUserById (long idUser) {
        UserEntity userEntity = null;
        try {
            userEntity = userRepository.findByIdUser(idUser);
        } catch (Exception e) {

        }
        return userEntity;
    }

    public UserEntity saveUserWithoutEncryp (UserEntity userEntity) {
        try {
            userEntity.setUsername(userEntity.getUsername().toLowerCase());
            userEntity = userRepository.save(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userEntity;
    }

    public List<UserEntity> getUsersByProfileAndNotInTheProject (ProfileEntity profileEntity, UserStatusEntity userStatusEntity, ProjectEntity projectEntity) {
        List<UserEntity> userEntityList = new ArrayList<>();
        try {
            userEntityList = userRepository.findByProfileEntityAndUserStatusEntityAndProjectEntitySetNotContaining(profileEntity, userStatusEntity, projectEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userEntityList;
    }


    public List<UserEntity> getUsersForEdit (long idLeader, long idConsultant) {
        List<UserEntity> userEntityList = new ArrayList<>();
        try {
            userEntityList = userRepository.findByProfileEntity_IdProfileOrProfileEntity_IdProfile(idLeader, idConsultant);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userEntityList;
    }
}
