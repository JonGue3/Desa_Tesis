package com.tesis.v1.service;

import com.tesis.v1.entity.ActivityStatusEntity;
import com.tesis.v1.repository.ActivityStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityStatusService {

    @Autowired
    private ActivityStatusRepository activityStatusRepository;

    public List<ActivityStatusEntity> findAll() {
        List<ActivityStatusEntity> activityStatusEntities = new ArrayList<>();
        try {
            activityStatusEntities = activityStatusRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return activityStatusEntities;
    }
}
