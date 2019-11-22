package com.tesis.v1.service;

import com.tesis.v1.repository.ActivityPointsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityPointsService {

    @Autowired
    private ActivityPointsRepository activityPointsRepository;
}
