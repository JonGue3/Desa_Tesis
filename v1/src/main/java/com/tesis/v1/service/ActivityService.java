package com.tesis.v1.service;

import com.tesis.v1.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;
}
