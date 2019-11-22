package com.tesis.v1.service;

import com.tesis.v1.entity.ActivityStatusEntity;
import com.tesis.v1.repository.ActivityStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityStatusService {

    @Autowired
    private ActivityStatusRepository activityStatusRepository;
}
