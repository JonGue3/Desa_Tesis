package com.tesis.v1.service;

import com.tesis.v1.entity.TransactionEntity;
import com.tesis.v1.entity.UserEntity;
import com.tesis.v1.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    public List<TransactionEntity> getTransactionByIdProfile (UserEntity userEntity) {
        List<TransactionEntity> transactionEntityList = new ArrayList<>();
        try {
            transactionEntityList = transactionRepository.getTransactionEntitiesByProfileEntitySet(userEntity.getProfileEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactionEntityList;
    }
}
