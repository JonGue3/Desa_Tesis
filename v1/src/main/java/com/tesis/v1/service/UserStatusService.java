package com.tesis.v1.service;

import com.tesis.v1.entity.UserEntity;
import com.tesis.v1.entity.UserStatusEntity;
import com.tesis.v1.repository.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserStatusService {

    @Autowired
    private UserStatusRepository userStatusRepository;

    public UserStatusEntity getUserStatusByIdUserStatus (long idUserStatus) {
        UserStatusEntity userStatusEntity = null;
        try {
            userStatusEntity = userStatusRepository.findByIdUserStatus(idUserStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userStatusEntity;
    }

    public List<UserStatusEntity>  getAllUserStatus(){
        List<UserStatusEntity> userEntityList = new ArrayList<>();
        try {
            userEntityList= userStatusRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userEntityList;
    }
}
