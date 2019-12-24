package com.tesis.v1.service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tesis.v1.entity.ProfileEntity;
import com.tesis.v1.entity.UserEntity;
import com.tesis.v1.repository.ProfileRepository;
import com.tesis.v1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    ProfileService profileService;
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = new UserEntity();
        List<ProfileEntity> profileEntityList = new ArrayList<>();
        //Obtiene usuario por el nombre de usuario
        try {
            userEntity = userService.getUserByUserName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Obtiene la lista de todos los perfiles
        try {
            profileEntityList = profileService.getAllProfiles();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Crear la lista de los roles/accessos que tienen el usuarios
        Set grantList = new HashSet();
        for (ProfileEntity role : profileEntityList) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getDescription());
            grantList.add(grantedAuthority);
        }

        //Crear y retornar Objeto de usuario soportado por Spring Security
        UserDetails user = (UserDetails) new User(userEntity.getUsername(), userEntity.getPassword(), grantList);
        return user;

    }

}