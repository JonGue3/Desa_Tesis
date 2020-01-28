package com.tesis.v1.service;

import com.tesis.v1.entity.ProfileEntity;
import com.tesis.v1.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

//Metodo que obtiene todos los perfiles existentes
    public List<ProfileEntity> getAllProfiles() {
        List<ProfileEntity> profileEntityList= new ArrayList<>();
        try {
            profileEntityList = profileRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    return profileEntityList;
    }

    public ProfileEntity getProfileById (long idProfile) {
        ProfileEntity profileEntity = null;
        try {
            profileEntity = profileRepository.findByIdProfile(idProfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return profileEntity;
    }
}
