package com.tesis.v1.service;

import com.tesis.v1.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;
}
