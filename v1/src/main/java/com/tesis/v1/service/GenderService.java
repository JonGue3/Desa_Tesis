package com.tesis.v1.service;

import com.tesis.v1.entity.GenderEntity;
import com.tesis.v1.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenderService {
    @Autowired
    GenderRepository genderRepository;
    //Metodo que obtiene los generos
    public List<GenderEntity> getAllGender() {
        List<GenderEntity> genderEntityList= new ArrayList<>();
        try {
            genderEntityList = genderRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return genderEntityList;
    }
}

