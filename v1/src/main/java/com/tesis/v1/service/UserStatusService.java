package com.tesis.v1.service;

import com.tesis.v1.repository.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserStatusService {

    @Autowired
    private UserStatusRepository userStatusRepository;
}
