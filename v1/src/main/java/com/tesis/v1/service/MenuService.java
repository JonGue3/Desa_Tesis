package com.tesis.v1.service;

import com.tesis.v1.entity.MenuEntity;
import com.tesis.v1.entity.UserEntity;
import com.tesis.v1.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserService userService;

    public List<MenuEntity> getMenuByIdProfile() {
        List<MenuEntity> menuEntityList = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        UserEntity userEntity = null;
        try {
            userEntity = userService.getUserByUserName(currentPrincipalName);
            menuEntityList = menuRepository.getMenuEntitiesByTransactionEntity_ProfileEntitySet(userEntity.getProfileEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menuEntityList;
    }
}
