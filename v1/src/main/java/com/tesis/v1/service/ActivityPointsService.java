package com.tesis.v1.service;

import com.tesis.v1.entity.ActivityPointsEntity;
import com.tesis.v1.repository.ActivityPointsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityPointsService {

    @Autowired
    private ActivityPointsRepository activityPointsRepository;

    public List<ActivityPointsEntity> findAll() {
        List<ActivityPointsEntity> activityPointsEntities = new ArrayList<>();
        try {
            activityPointsEntities = activityPointsRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activityPointsEntities;
    }
}
