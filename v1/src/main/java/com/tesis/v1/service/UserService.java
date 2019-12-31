package com.tesis.v1.service;

import com.tesis.v1.entity.UserEntity;
import com.tesis.v1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Metodo que obtiene usuario mediante el nombre de usuario
    public UserEntity getUserByUserName(String username){
        UserEntity userEntity = new UserEntity();
      try{
        userEntity= userRepository.findByUsername(username);
      }catch (Exception e){
        e.printStackTrace();
      }
    return  userEntity;
    }

    public UserEntity saveUser (UserEntity userEntity) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        try {
           userEntity = userRepository.save(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userEntity;
    }
}
